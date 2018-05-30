package com.gaofen.controller;

import com.google.gson.Gson;

import com.alibaba.fastjson.JSON;
import com.gaofen.common.config.WxMpConfig;
import com.gaofen.common.constant.Constants;
import com.gaofen.common.dto.Result;
import com.gaofen.common.shiro.realm.StatelessToken;
import com.gaofen.common.utils.JavaWebToken;
import com.gaofen.common.utils.MD5Util;
import com.gaofen.common.utils.SuperLogger;
import com.gaofen.common.utils.WeixinUtils;
import com.gaofen.model.User;
import com.gaofen.service.IUserinfoService;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by feng on 2017/8/22.
 */
@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Autowired
    IUserinfoService mUserinfoService;

    @Autowired
    private WxMpConfig wxConfig;

    @Autowired
    RedisTemplate<String, String> mRedisTemplate;

    @Autowired
    WeixinUtils mWeixinUtils;

    @Value("#{configProperties['baseWebUrl']}")
    private String baseWebUrl;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody @Validated String jsonParams
            , HttpServletRequest req, HttpServletResponse res) {
        Result result = Result.getInstance();
        Gson gson = new Gson();
        int insert;

        if (jsonParams == null || jsonParams.length() == 0) {
            result.setResult(Result.ERROR);
            result.setError("参数不能为空");
            return result;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        String token = JavaWebToken.createJavaWebToken(m);
        User userinfo = gson.fromJson(jsonParams, User.class);
        SuperLogger.e(userinfo.getopenId());
        userinfo.setpassWord(MD5Util.getMD5(userinfo.getpassWord()));
        HttpSession session = req.getSession();
        String rand = (String) session.getAttribute("code");

        if (userinfo.getRandCode() != null) {
            if (userinfo.getRandCode().equalsIgnoreCase(rand)) {
                m.put("userId", userinfo.getuserName());
                if (userinfo.getopenId() != null) {
                    result = mUserinfoService.login(userinfo);
                    ///shiro登录验证，暂时不启用
//                try {
//                    TokenUtil.getInstance().loginShiro(res,userinfo.getopenId(),userinfo.getpassWord());
//                }catch (Exception e){
//                    SuperLogger.e(e);
//                }
                } else {
                    result.setError("openId不能为空");
                }
            } else {
                result.setError("验证码错误");
            }
        }

        if (rand == null) {
            result.setError("验证码不能为空");
        }
        return result;
    }


    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public Result getUserInfo(@RequestBody @Validated String jsonParams) {
        Result result = Result.getInstance();
        Gson gson = new Gson();


        if (jsonParams == null || jsonParams.length() == 0) {
            result.setResult(Result.ERROR);
            result.setError("参数不能为空");
            return result;
        }
        User userinfo = gson.fromJson(jsonParams, User.class);
//        String openId = getOpenId(userinfo.getopenId());
        String token = (String) JSON.parseObject(jsonParams).get("token");
        /*  if(JavaWebToken.parserJavaWebToken(token) != null){*/
        //表示token合法
        User muserinfo = mUserinfoService.queryUserInfoByOpeanId(userinfo.getopenId());
        if (muserinfo != null) {
            result.success(muserinfo);
        } else {
            result.setError("查询失败");
        }

     /*   }else{
            //token不合法或者过期index.html

                result.setError("token不合法或者过期");

        }
*/
        return result;

    }


    @RequestMapping(value = "/unLock", method = RequestMethod.POST)
    public Result unLock(@RequestBody @Validated String jsonParams
            , HttpServletRequest req, HttpServletResponse res) {
        Result result = Result.getInstance();
        Gson gson = new Gson();
        if (jsonParams == null || jsonParams.length() == 0) {
            result.setResult(Result.ERROR);
            result.setError("参数不能为空");
            return result;
        }
        User userinfo = gson.fromJson(jsonParams, User.class);
        userinfo.setpassWord(MD5Util.getMD5(userinfo.getpassWord()));
        userinfo.setstatus(0);
        String token = (String) JSON.parseObject(jsonParams).get("token");
        /*      if(JavaWebToken.parserJavaWebToken(token) != null){*/
        User openUser = mUserinfoService.queryUserInfoByOpeanId(userinfo.getopenId());

        if (openUser != null) {
            if (openUser.getopenId().equals(userinfo.getopenId()) && openUser.getuserName().equals(openUser.getuserName())) {
                if (mUserinfoService.updateUserInfo(userinfo)) {
                    result.success("解除绑定成功");
                } else {
                    result.setError("解除绑定失败");

                }
            } else {
                result.setError("参数错误");
            }

        } else {
            result.setError("该账号没有绑定");
        }
        /*}*//*else {
            result.setError("token不合法或者过期");
        }*/


        return result;

    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public void getOpenId(HttpServletResponse res, HttpServletRequest req) {
        try {
            HttpSession session = req.getSession();
            String sessionOpenId = mRedisTemplate.opsForValue().get(session.getId() + "_openId");
            String url = req.getParameter("url");
            if (sessionOpenId != null) {
                sendRedirect(res, session, url, sessionOpenId);
            } else {
                String code = req.getParameter("code");
                if (url != null) {
                    session.setAttribute("url", url);
                    mWeixinUtils.getCode(res);
                } else {
                    String openId = mWeixinUtils.getOpenId(code);
                    String redirectUrl = (String) session.getAttribute("url");
                    mRedisTemplate.opsForValue().set(session.getId() + "_openId", openId, 30, TimeUnit.MINUTES);
                    SuperLogger.e(openId);
                    sendRedirect(res, session, redirectUrl, openId);
                }
            }

        } catch (Exception e) {
            SuperLogger.e(e);
        }
    }

    private void sendRedirect(HttpServletResponse res, HttpSession session, String url, String openId) throws IOException {
        User userinfo = mUserinfoService.queryUserInfoByOpeanId(openId);
        String redirectUrl = url + "?openId=" + openId;
        if (userinfo != null) {
            res.sendRedirect(redirectUrl);
        } else {
            res.sendRedirect(baseWebUrl + "html/login.html?openId=" + openId + "&redirectUrl=" + redirectUrl);
        }
    }

    @RequestMapping("/logins")
    public Result logins(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(Constants.ShiroKey.TOKEN, "token");
        Cookie cookie2 = new Cookie(Constants.ShiroKey.USER_INFO, "admin");
        response.addCookie(cookie);
        response.addCookie(cookie2);
        Result result = Result.getInstance();
        result.success("登录成功");
        StatelessToken token = new StatelessToken("admin", "admin", "token");
        token.setRememberMe(true);
        SecurityUtils.getSubject().login(token);
        return result;
    }

}
