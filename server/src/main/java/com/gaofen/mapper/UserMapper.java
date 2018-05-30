package com.gaofen.mapper;

import com.gaofen.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int insert(User record);
    User queryUser(String OPENID);
    int insertSelective(User record);
     int updateUserStaus(User user);
     int     deleteUserById(String openId);
    User  queryUserByUserName(String userName);
}