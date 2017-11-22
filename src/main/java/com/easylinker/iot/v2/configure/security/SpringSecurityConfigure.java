package com.easylinker.iot.v2.configure.security;

import com.easylinker.iot.v2.configure.security.filter.CustomUsernamePasswordFilter;
import com.easylinker.iot.v2.configure.security.handler.AnonymousHandler;
import com.easylinker.iot.v2.configure.security.handler.LoginFailureHandler;
import com.easylinker.iot.v2.configure.security.handler.LoginSuccessHandler;
import com.easylinker.iot.v2.configure.security.handler.UserLogoutSuccessHandler;
import com.easylinker.iot.v2.configure.security.securityenum.SecurityCommonUrl;
import com.easylinker.iot.v2.service.AppUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by wwhai on 2017/11/15.
 */
@Configuration
public class SpringSecurityConfigure extends WebSecurityConfigurerAdapter {


    private SecurityRouter securityRouter;

    /**
     * 这个用来配置一些默认的路由
     */
    public SpringSecurityConfigure() {
        securityRouter = new SecurityRouter();
    }

    /**
     * 这个配置是对WEB资源进行拦截配置
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(securityRouter.getWebResourcesRouter());
        web.ignoring().antMatchers(SecurityCommonUrl.SWAGGER_UI_MATCHER);

    }

    /**
     * 这个是对路由进行配置
     * /
     * /index
     * /user/login
     * /user/signUp
     * /test
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(getCustomUsernamePasswordFilter());
        http.authorizeRequests().antMatchers(securityRouter.getHttpSecurityRouter()).permitAll();
        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin().disable().httpBasic().disable()
                .logout()
                .logoutSuccessHandler(new UserLogoutSuccessHandler())
                .permitAll()
                .and().rememberMe()
                .and().exceptionHandling()
                .authenticationEntryPoint(new AnonymousHandler())
                .and().csrf().disable();
    }

    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();

    }

    /**
     * @param auth
     * @throws Exception
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

        auth.userDetailsService(customUserDetailsService());
    }

    /**
     * 自定义UserDetailsService，从数据库中读取用户信息
     *
     * @return
     */
    @Bean
    public AppUserDetailService customUserDetailsService() {
        return new AppUserDetailService();
    }

    @Bean
    public CustomUsernamePasswordFilter getCustomUsernamePasswordFilter() throws Exception {
        CustomUsernamePasswordFilter customUsernamePasswordFilter = new CustomUsernamePasswordFilter();
        customUsernamePasswordFilter.setAuthenticationManager(super.authenticationManager());
        customUsernamePasswordFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
        customUsernamePasswordFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        return customUsernamePasswordFilter;
    }

}
