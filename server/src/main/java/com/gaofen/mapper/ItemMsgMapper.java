package com.gaofen.mapper;

import com.gaofen.model.weixin.ItemMsg;

import java.util.List;

public interface ItemMsgMapper {
    int deleteByPrimaryKey(String itemid);

    int insert(ItemMsg record);

    int insertSelective(ItemMsg record);

    ItemMsg selectByPrimaryKey(String itemid);

    int updateByPrimaryKeySelective(ItemMsg record);

    int updateByPrimaryKey(ItemMsg record);

    List<ItemMsg> getMsgByItemIds(String[] items);
}