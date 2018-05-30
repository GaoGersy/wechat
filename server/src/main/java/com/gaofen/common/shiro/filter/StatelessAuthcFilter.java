package com.gaofen.common.shiro.filter;


import com.gaofen.common.constant.Constants;
import com.gaofen.common.utils.SuperLogger;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class StatelessAuthcFilter extends AccessControlFilter {
    @Autowired
    RedisTemplate<String, String> mRedisTemplate;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = ((HttpServletRequest)request);
        String url = httpRequest.getRequestURI();
        if(url.contains("/login.html")){
            return Boolean.TRUE;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String cookie_token = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (Constants.ShiroKey.TOKEN.equals(name)) {
                    cookie_token = cookie.getValue();
                    break;
                }
            }
        }

        String token = null;
        if (cookie_token!=null){
            token = mRedisTemplate.opsForValue().get(cookie_token);
        }

        if (token==null){
            saveRequest(request);
            WebUtils.issueRedirect(request, response, "/html/login.html");
            return false;
        }else {
            String requestURL = httpRequest.getRequestURL().toString();
            SuperLogger.e(requestURL);
        }
        return true;
    }
}
