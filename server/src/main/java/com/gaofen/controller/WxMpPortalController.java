package com.gaofen.controller;


import com.gaofen.common.utils.SuperLogger;
import com.gaofen.service.weixin.impl.WeixinService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author Binary Wang
 */
@RestController
@RequestMapping("/wechat/portal")
public class WxMpPortalController {
    @Autowired
    private WeixinService wxService;

    @ResponseBody
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (this.getWxService().checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @ResponseBody
    @PostMapping(produces = "application/xml; charset=UTF-8")
//  @RequestMapping(produces = "application/xml; charset=UTF-8", method = RequestMethod.POST)
    public String post(@RequestBody String requestBody, @RequestParam(name = "signature", required = false) String signature,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature,
                       @RequestParam(name = "timestamp", required = false) String timestamp, @RequestParam(name = "nonce", required = false) String nonce, HttpServletRequest req) {
        SuperLogger.e(signature + encType + msgSignature + timestamp + nonce + requestBody);

        if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            String userOpenId = inMessage.getFromUser();
            req.getSession().setAttribute("openId",userOpenId);
            SuperLogger.e(userOpenId);
            WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toXml();
        } else if ("aes".equals(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
                    this.getWxService().getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            SuperLogger.e("\n消息解密后内容为： "+ inMessage.toString());
            WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toEncryptedXml(this.getWxService().getWxMpConfigStorage());
        }

        SuperLogger.e("\n组装回复信息：{}"+ out);

        return out;
    }

    protected WeixinService getWxService() {
        return this.wxService;
    }


}
