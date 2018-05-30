package com.gaofen.model.weixin;

public class MediaMsg extends BaseMsgBean{
    private String msgId;

    private String mediaId;

    private String mediaUrl;

    public MediaMsg(String msgId, String mediaId, String mediaUrl) {
        this.msgId = msgId;
        this.mediaId = mediaId;
        this.mediaUrl = mediaUrl;
    }

    public MediaMsg() {
        super();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl == null ? null : mediaUrl.trim();
    }
}