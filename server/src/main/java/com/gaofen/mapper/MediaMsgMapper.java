package com.gaofen.mapper;

import com.gaofen.model.weixin.MediaMsg;

public interface MediaMsgMapper {
    int deleteByPrimaryKey(String msgid);

    int insert(MediaMsg record);

    int insertSelective(MediaMsg record);

    MediaMsg selectByPrimaryKey(String msgid);

    int updateByPrimaryKeySelective(MediaMsg record);

    int updateByPrimaryKey(MediaMsg record);

    MediaMsg selectByMediaUrl(String url);

}