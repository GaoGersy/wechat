package com.gaofen.model.weixin;


import java.util.List;

public class ImgTextMsgBean  extends BaseMsgBean{

    private ItemMsg masterItem;

    private List<ItemMsg> subItems;

    private String msgId;

    public ItemMsg getMasterItem() {
        return masterItem;
    }

    public void setMasterItem(ItemMsg masterItem) {
        this.masterItem = masterItem;
    }

    public List<ItemMsg> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<ItemMsg> subItems) {
        this.subItems = subItems;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
