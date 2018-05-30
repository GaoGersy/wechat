package com.gaofen.common.builder;


import com.gaofen.service.weixin.impl.WeixinService;

import java.util.List;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

/**
 * 
 * @author Binary Wang
 *
 */
public class ImageTextBuilder {

  public WxMpXmlOutMessage build(WxMpXmlOutNewsMessage.Item item, List<WxMpXmlOutNewsMessage.Item> items, WxMpXmlMessage wxMessage,
                                 WeixinService service) {

    WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().addArticle(item).articles(items)
        .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
        .build();

    return m;
  }

}
