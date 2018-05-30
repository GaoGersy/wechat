package com.gaofen.mapper;

import com.gaofen.model.WeixinMenuButton;

import java.util.List;

public interface WeixinMenuButtonMapper {
    int deleteByPrimaryKey(String menuid);

    int insert(WeixinMenuButton record);

    int insertSelective(WeixinMenuButton record);

    WeixinMenuButton selectByPrimaryKey(String menuid);

    int updateByPrimaryKeySelective(WeixinMenuButton record);

    int updateByPrimaryKey(WeixinMenuButton record);

    List<WeixinMenuButton> getMasterButtons();

    int clearAll();
}