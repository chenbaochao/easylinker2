package com.easylinker.iot.v2.configure.security.filter;

import com.easylinker.iot.v2.configure.security.RequestUsernamePasswordBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 关于这个Filter的理解
 * 框架在处理登录的时候 如果你自定义认证过程 一般都是直接在attemptAuthentication 中实现
 * 如果你想用子类的loadUserByUsername 来实现 就在下面加上代码:setDetails(request, usernamePasswordAuthenticationToken);
 */
public class CustomUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {

    public static final String DEFAULT_LOGIN_URL = "/userLogin";
    public static final String DEFAULT_LOGIN_METHOD = "POST";
    public Log logger = LogFactory.getLog(CustomUsernamePasswordFilter.class);
    RequestUsernamePasswordBean requestUsernamePasswordBean = null;

    public CustomUsernamePasswordFilter() {
        setAuthenticationManager(super.getAuthenticationManager());

        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(DEFAULT_LOGIN_URL, DEFAULT_LOGIN_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(DEFAULT_LOGIN_URL, DEFAULT_LOGIN_METHOD));
        requestUsernamePasswordBean = new RequestUsernamePasswordBean(request);

        String loginParam = requestUsernamePasswordBean.getUsername();
        String password = requestUsernamePasswordBean.getPassword();

        if (true && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginParam, password);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


}
