package com.gaofen.mapper;

import com.gaofen.model.weixin.WeixinMsg;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeixinMsgMapper {
    int deleteByPrimaryKey(String id);

    int insert(WeixinMsg record);

    int insertSelective(WeixinMsg record);

    WeixinMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WeixinMsg record);

    int updateByPrimaryKey(WeixinMsg record);

    int updateByKeyword(WeixinMsg record);

    WeixinMsg getWeixinMsgByContent(String content);

    WeixinMsg getWeixinMsgByMsgId(@Param("msgId") String msgId);

    List<WeixinMsg> getAllMsgByMsgType(int msgType);
}