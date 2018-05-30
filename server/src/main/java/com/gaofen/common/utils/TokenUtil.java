package com.gaofen.common.utils;

import com.gaofen.common.constant.Constants;
import com.gaofen.common.shiro.realm.StatelessToken;

import org.apache.shiro.SecurityUtils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class TokenUtil {
    public static final int COOKIE_TIMEOUT = 7*24*3600;
    private TokenUtil() {
    }

    private static final TokenUtil instance = new TokenUtil();

    public static TokenUtil getInstance() {
        return instance;
    }

    public String makeToken(String userName) {

        if (userName==null){
            throw new NullPointerException("userName 为空");
        }
        String token = System.currentTimeMillis() + userName;
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            //对于给定数量的更新数据，digest 方法只能被调用一次。digest 方法被调用后，MessageDigest对象被重新设置成其初始状态。
            byte md5[] = md.digest(token.getBytes());
            //base64编码--任意二进制编码明文字符   adfsdfsdfsf
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginShiro(HttpServletResponse response,String userName,String pwd){
        String token = TokenUtil.getInstance().makeToken(userName);
        Cookie cookie = new Cookie(Constants.ShiroKey.TOKEN,token);
        cookie.setMaxAge(COOKIE_TIMEOUT);
        cookie.setPath("/");
        response.addCookie(cookie);
        StatelessToken statelessToken = new StatelessToken(userName, pwd,token);
        statelessToken.setRememberMe(true);
        SecurityUtils.getSubject().login(statelessToken);
    }
}