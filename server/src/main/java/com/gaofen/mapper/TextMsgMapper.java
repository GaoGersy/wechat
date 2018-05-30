package com.gaofen.mapper;

import com.gaofen.model.weixin.TextMsg;

import java.util.List;

public interface TextMsgMapper {
    int deleteByPrimaryKey(String msgid);

    int insert(TextMsg record);

    int insertSelective(TextMsg record);

    TextMsg selectByPrimaryKey(String msgid);

    int updateByPrimaryKeySelective(TextMsg record);

    int updateByPrimaryKey(TextMsg record);

    List<TextMsg> getAllTextMsg();
}