package com.easylinker.iot.v2.configure.security;

import com.easylinker.iot.v2.configure.security.handler.AnonymousHandler;
import com.easylinker.iot.v2.configure.security.handler.LoginFailureHandler;
import com.easylinker.iot.v2.configure.security.handler.LoginSuccessHandler;
import com.easylinker.iot.v2.configure.security.securityenum.SecurityCommonUrl;
import com.easylinker.iot.v2.service.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public SpringSecurityConfigure() {
        securityRouter = new SecurityRouter();
        securityRouter.addHttpSecurityRouter(SecurityCommonUrl.DEFAULT_TEST_PATH.getUrl());
        securityRouter.addWebResourcesRouter(SecurityCommonUrl.DEFAULT_STATIC_PATH.getUrl());
        securityRouter.addHttpSecurityRouter("/api/pdf");

    }

    /**
     * 这个配置是对WEB资源进行拦截配置
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(securityRouter.getWebResourcesRouter());

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
        http.authorizeRequests().antMatchers(securityRouter.getHttpSecurityRouter()).permitAll();
        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage(SecurityCommonUrl.DEFAULT_LOGIN_PAGE.getUrl())
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                .usernameParameter(SecurityCommonUrl.DEFAULT_USERNAME_NAME.getUrl())
                .passwordParameter(SecurityCommonUrl.DEFAULT_PASSWORD_NAME.getUrl())
                .and().logout()
                .logoutUrl(SecurityCommonUrl.DEFAULT_LOGOUT_PAGE.getUrl())
                .logoutSuccessUrl(SecurityCommonUrl.DEFAULT_INDEX.getUrl())
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


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
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

}
