package com.gaofen.model.weixin;

public class ImgTextMsg extends BaseMsgBean {
    private String msgId;

    private String masterItemId;

    private String subItemIds;

    public ImgTextMsg(String msgId, String masterItemId, String subItemIds) {
        this.msgId = msgId;
        this.masterItemId = masterItemId;
        this.subItemIds = subItemIds;
    }

    public ImgTextMsg() {
        super();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMasterItemId() {
        return masterItemId;
    }

    public void setMasterItemId(String masterItemId) {
        this.masterItemId = masterItemId;
    }

    public String getSubItemIds() {
        return subItemIds;
    }

    public void setSubItemIds(String subItemIds) {
        this.subItemIds = subItemIds == null ? null : subItemIds.trim();
    }
}