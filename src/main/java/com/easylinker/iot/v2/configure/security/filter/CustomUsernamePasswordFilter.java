package com.easylinker.iot.v2.configure.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.configure.security.RequestUsernamePasswordBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(DEFAULT_LOGIN_URL, DEFAULT_LOGIN_METHOD));
        requestUsernamePasswordBean = new RequestUsernamePasswordBean(request);

        String loginParam = requestUsernamePasswordBean.getUsername();
        String password = requestUsernamePasswordBean.getPassword();


        Authentication authentication = null;

        if (true && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("AuthenticSation method not supported: " + request.getMethod());
        } else {

            try {
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginParam, password);
                this.setDetails(request, authRequest);
                authentication = getAuthenticationManager().authenticate(authRequest);

            } catch (Exception e) {
                logger.error("登录失败"+e.getMessage());
                JSONObject resultJson = new JSONObject();
                resultJson.put("state", 0);
                resultJson.put("message", "登录失败!失败信息:" + e.getMessage());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                try {
                    response.getWriter().write(resultJson.toJSONString());
                    response.getWriter().flush();
                } catch (IOException e1) {
                    //e1.printStackTrace();
                }

            }
        }
        return authentication;
    }


}
