package com.gaofen.mapper;

import com.gaofen.model.UimUser;

import org.springframework.stereotype.Repository;

@Repository
public interface UimUserMapper {
    int deleteByPrimaryKey(String USERID);

    int insert(UimUser record);

    int insertSelective(UimUser record);

    UimUser selectByPrimaryKey(String USERID);

    int updateByPrimaryKeySelective(UimUser record);

    int updateByPrimaryKey(UimUser record);
}