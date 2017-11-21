package com.easylinker.iot.v2.configure.security.filter;

import com.easylinker.iot.v2.configure.security.RequestUsernamePasswordBean;
import com.easylinker.iot.v2.configure.security.handler.LoginFailureHandler;
import com.easylinker.iot.v2.configure.security.handler.LoginSuccessHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {
    public static final String DEFAULT_LOGIN_URL = "/login";
    public static final String DEFAULT_LOGIN_METHOD = "POST";
    public Log logger = LogFactory.getLog(CustomUsernamePasswordFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(DEFAULT_LOGIN_URL, DEFAULT_LOGIN_METHOD));

        logger.info("进入自定义认证");
        try {
            RequestUsernamePasswordBean requestUsernamePasswordBean = new RequestUsernamePasswordBean(request);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(requestUsernamePasswordBean.getUserrname(), requestUsernamePasswordBean.getPassword());
            setDetails(request, usernamePasswordAuthenticationToken);

        } catch (Exception e) {
            logger.error("参数获取失败!");

        }

        return super.attemptAuthentication(request, response);
    }

    @Override
    protected AuthenticationSuccessHandler getSuccessHandler() {

        return new LoginSuccessHandler();
    }

    @Override
    protected AuthenticationFailureHandler getFailureHandler() {
        return new LoginFailureHandler();
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return super.requiresAuthentication(request, response);
    }

}
