package com.gaofen.service;


import com.gaofen.model.UimUser;
import com.gaofen.model.User;

/**
 * Created by feng on 2017/8/22.
 */

public interface UserinfoService {
    UimUser login(User userinfo);

    boolean updateUserInfo(User muserinfo);
    User queryUserInfoByOpeanId(String openId);
    User queryUserInfoByOpeanId1(String openId);
}
