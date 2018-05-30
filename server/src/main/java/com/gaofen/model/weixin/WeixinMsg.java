package com.gaofen.model.weixin;

public class WeixinMsg {
    private String msgId;

    private Integer msgType;

    private String content;

    public WeixinMsg(String msgId, Integer msgType, String content) {
        this.msgId = msgId;
        this.msgType = msgType;
        this.content = content;
    }

    public WeixinMsg() {
        super();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "WeixinMsg{" +
                "msgId=" + msgId +
                ", msgType=" + msgType +
                ", content='" + content + '\'' +
                '}';
    }
}