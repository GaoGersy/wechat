package com.gaofen.model.weixin;

public class TextMsg extends BaseMsgBean {
    private String msgId;

    private String content;

    public TextMsg(String msgId, String content) {
        this.msgId = msgId;
        this.content = content;
    }

    public TextMsg() {
        super();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}