package com.gaofen.mapper;

import com.gaofen.model.weixin.ImgTextMsg;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImgTextMsgMapper {
    int deleteByPrimaryKey(String msgid);

    int insert(ImgTextMsg record);

    int insertSelective(ImgTextMsg record);

    ImgTextMsg selectByPrimaryKey(String msgid);

    int updateByPrimaryKeySelective(ImgTextMsg record);

    int updateByPrimaryKey(ImgTextMsg record);

    ImgTextMsg getMsgByMsgId(@Param("msgId") String msgId);

    int removeMsgByMsgId(String msgId);

    int updateByMsgId(ImgTextMsg record);

    List<ImgTextMsg> getAllImgTextMsg();

}