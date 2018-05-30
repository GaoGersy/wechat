package com.gaofen.model.weixin;

public class TextMsgBean extends BaseMsgBean {
    private String msgId;
    private String content;

    public void setTextMsg(TextMsg textMsg) {
        msgId = textMsg.getMsgId();
        content = textMsg.getContent();
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
        this.content = content;
    }
}
