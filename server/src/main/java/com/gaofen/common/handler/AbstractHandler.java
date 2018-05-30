package com.gaofen.common.handler;

import com.google.gson.Gson;

import com.gaofen.common.utils.SuperLogger;

import org.slf4j.Logger;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class AbstractHandler implements WxMpMessageHandler {

//    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected Logger logger = SuperLogger.logger();
    protected final Gson gson = new Gson();

}
