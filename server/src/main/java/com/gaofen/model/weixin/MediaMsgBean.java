package com.gaofen.model.weixin;


public class MediaMsgBean extends BaseMsgBean {
    private String msgId;
    private String mediaUrl;
    private String mediaId;

    public void setMediaMsg(MediaMsg mediaMsg) {
        msgId = mediaMsg.getMsgId();
        mediaUrl = mediaMsg.getMediaUrl();
        mediaId = mediaMsg.getMediaId();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
