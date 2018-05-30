package com.gaofen.service;


import com.gaofen.common.dto.Result;
import com.gaofen.model.User;

/**
 * Created by feng on 2017/8/22.
 */

public interface IUserinfoService {
    Result login(User userinfo);

    boolean updateUserInfo(User muserinfo);
    User queryUserInfoByOpeanId(String openId);

}
