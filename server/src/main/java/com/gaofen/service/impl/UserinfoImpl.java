package com.gaofen.service.impl;


import com.gaofen.common.dto.Result;
import com.gaofen.mapper.EmployeeMapper;
import com.gaofen.mapper.UimUserMapper;
import com.gaofen.mapper.UserMapper;
import com.gaofen.model.Employee;
import com.gaofen.model.User;
import com.gaofen.service.IUserinfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by feng on 2017/8/22.
 */
@Service
@Transactional
public class UserinfoImpl implements IUserinfoService {
    private static final int OUT_TIME = 7 * 24 * 60 * 60 * 1000;

    @Autowired
    UimUserMapper uimUserMapper;

    @Autowired
    UserMapper userMapper;
    @Autowired
    EmployeeMapper employMapper;

    @Override
    public Result login(User muserinfo) {

        Result result = new Result();
        User userinfo = userMapper.queryUser(muserinfo.getopenId());

        try {
            if (muserinfo.getAutoStatus() == 1) {
                muserinfo.setstatus(1);
            } else {
                muserinfo.setstatus(0);
            }
            muserinfo.setloginTime(System.currentTimeMillis());
            Employee uimUser = employMapper.selectByPrimaryKey(muserinfo.getuserName());
            if (uimUser != null) {
                User user = userMapper.queryUserByUserName(uimUser.getLoginname());
                if (userinfo == null) {
                    //这个openid第一次登录,uimuser
                    //要查一下这个uimser有没有绑过
                    if (user == null) {
                        muserinfo.setloginTime(System.currentTimeMillis());
                        if (uimUser != null) {
                            if (uimUser.getPassword().equals(muserinfo.getpassWord())) {
                                //设置为MD5密码
                                int insert = userMapper.insert(muserinfo);
                                if (insert > 0) {
                                    return result.success(uimUser);
                                } else {
                                    return result.failure("更新用户信息失败");
                                }
                            } else {
                                result.setError("密码错误");
                                return result;
                            }
                        }
                    } else if (user.getstatus() == 0 /*|| user.getuserName().equals("admin")*/) {
                        if (uimUser.getPassword().equals(muserinfo.getpassWord())) {
                            //暂时admin 为通用登录账号
                            muserinfo.setpassWord(uimUser.getPassword());
                            userMapper.deleteUserById(user.getopenId());
                            int insert = userMapper.insert(muserinfo);
                            if (insert > 0) {
                                return result.success(uimUser);
                            } else {
                                return result.failure("更新用户信息失败");
                            }
                        } else {
                            result.setError("密码错误");
                            return result;
                        }
                    } else {
                        result.setError("这个账号已经给绑定了");
                        return result;
                    }
                    return result.success(uimUser);
                } else {
                    //这个openid已经绑定过账号
                    if (user != null) {
                        if (userMapper.queryUserByUserName(uimUser.getLoginname()) != null /*&& !user.getuserName().equals("admin")*/) {
                            if (uimUser.getPassword().equals(muserinfo.getpassWord())) {
                                userMapper.updateUserStaus(muserinfo);
                            } else {
                                result.setError("密码错误");
                                return result;
                            }
                        } else {
                            result.setError("这个账号已经给绑定了");
                            return result;
                        }
                        return result.success(uimUser);
                    } else {
                        result.setError("你的微信已经给绑定过了");
                        return result;
                    }
                }
            } else {
                result.setError("该用户不存在");
            }
        } catch (Exception e) {
            return result.exception(e);
        }
        return result;
    }

    @Override
    public User queryUserInfoByOpeanId(String openId) {
        User user = userMapper.queryUser(openId);
        if (user != null) {
            if (user.getstatus() == 0) {
                return null;
            } else {
                Long logintime = user.getloginTime();
                if (logintime == null) {
                    return null;
                }
                long time = System.currentTimeMillis() - logintime;
                if (time >= OUT_TIME) {
                    user.setstatus(0);
                    userMapper.updateUserStaus(user);
                    return null;
                } else {
                    return user;
                }
            }
        }
        return user;
    }

    @Override
    public boolean updateUserInfo(User muserinfo) {

        int insert = userMapper.deleteUserById(muserinfo.getopenId());
        return insert != -1;


    }
}
