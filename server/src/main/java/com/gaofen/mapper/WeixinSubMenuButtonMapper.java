package com.gaofen.mapper;

import com.gaofen.model.WeixinSubMenuButton;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeixinSubMenuButtonMapper {
    int deleteByPrimaryKey(String menuid);

    int insert(WeixinSubMenuButton record);

    int insertSelective(WeixinSubMenuButton record);

    WeixinSubMenuButton selectByPrimaryKey(String menuid);

    int updateByPrimaryKeySelective(WeixinSubMenuButton record);

    int updateByPrimaryKey(WeixinSubMenuButton record);

    List<WeixinSubMenuButton> getAllSubMenuButtons(@Param("subMenuIds") String[] subMenuIds);

    int clearAll();
}