package com.gaofen.service.weixin;


import com.gaofen.model.weixin.BaseMsgBean;
import com.gaofen.model.weixin.ImgTextMsgBean;
import com.gaofen.model.weixin.TextMsg;
import com.gaofen.model.weixin.WeixinMsg;

import java.util.List;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public interface IWeixinMsgService {

    int addTextReplyMsg(String content, String keyWord);

    WxMpXmlOutMessage getOutMessage(String content, WxMpXmlMessage wxMessage, WxMpService wxMpService);

    int addImageTextReplyMsg(ImgTextMsgBean imgTextMsgBean);

    int updateTextReplyMsg(TextMsg textMsg);

    int deleteTextReplyMsg(String msgId);

    int addMediaReplyMsg(String mediaId, String keyWord, String mediaUrl);

    BaseMsgBean getMsgDetail(String msgId, int msgType);

    List<WeixinMsg> getMsgList(int msgType);

    int updateImageTextReplyMsg(ImgTextMsgBean imgTextMsgBean);

    int deleteImageTextReplyMsg(ImgTextMsgBean imgTextMsgBean);

}
