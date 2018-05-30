package com.gaofen.service.weixin.impl;


import com.gaofen.common.builder.ImageBuilder;
import com.gaofen.common.builder.ImageTextBuilder;
import com.gaofen.common.builder.TextBuilder;
import com.gaofen.common.constant.Constants;
import com.gaofen.common.utils.SuperLogger;
import com.gaofen.common.utils.UUIDUtils;
import com.gaofen.mapper.ImgTextMsgMapper;
import com.gaofen.mapper.ItemMsgMapper;
import com.gaofen.mapper.MediaMsgMapper;
import com.gaofen.mapper.TextMsgMapper;
import com.gaofen.mapper.WeixinMsgMapper;
import com.gaofen.model.weixin.BaseMsgBean;
import com.gaofen.model.weixin.ImgTextMsg;
import com.gaofen.model.weixin.ImgTextMsgBean;
import com.gaofen.model.weixin.ItemMsg;
import com.gaofen.model.weixin.MediaMsg;
import com.gaofen.model.weixin.TextMsg;
import com.gaofen.model.weixin.WeixinMsg;
import com.gaofen.service.weixin.IWeixinMsgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

import static com.gaofen.common.constant.Constants.MsgType.IMAGE_TEXT_MSG;
import static com.gaofen.common.constant.Constants.MsgType.MEDIA_MSG;
import static com.gaofen.common.constant.Constants.MsgType.TEXT_MSG;


@Service
public class WeixinMsgService implements IWeixinMsgService {

    @Autowired
    MediaMsgMapper mMediaMsgMapper;

    @Autowired
    TextMsgMapper mTextMsgMapper;

    @Autowired
    ImgTextMsgMapper mImgTextMsgMapper;

    @Autowired
    WeixinMsgMapper mWeixinMsgMapper;

    @Autowired
    ItemMsgMapper mItemMsgMapper;

    @Value("#{configProperties['baseUrl']}")
    private String baseUrl;

    @Value("#{configProperties['defaultMsgKeyWord']}")
    private String defaultMsgKeyWord;

    private String mBaseLoginUrl;

    @Override
    public BaseMsgBean getMsgDetail(String msgId, int msgType) {
        WeixinMsg weixinMsg = mWeixinMsgMapper.selectByPrimaryKey(msgId);
        if (weixinMsg == null || weixinMsg.getMsgType() != msgType) {
            return null;
        }
        String keyWord = weixinMsg.getContent();
        if (msgType == TEXT_MSG) {
            TextMsg textMsg = mTextMsgMapper.selectByPrimaryKey(msgId);
            textMsg.setKeyWord(keyWord);
            return textMsg;
        } else if (msgType == MEDIA_MSG) {
            MediaMsg mediaMsg = mMediaMsgMapper.selectByPrimaryKey(msgId);
            mediaMsg.setKeyWord(keyWord);
            return mediaMsg;
        } else if (msgType == IMAGE_TEXT_MSG) {
            ImgTextMsg imgTextMsg = mImgTextMsgMapper.getMsgByMsgId(msgId);
            ItemMsg itemMsg = mItemMsgMapper.selectByPrimaryKey(imgTextMsg.getMasterItemId());
            itemMsg.setBaseUrl(baseUrl);
            String subItemIds = imgTextMsg.getSubItemIds();

            ImgTextMsgBean imgTextMsgBean = new ImgTextMsgBean();
            imgTextMsgBean.setMasterItem(itemMsg);
            imgTextMsgBean.setKeyWord(keyWord);
            imgTextMsgBean.setMsgId(msgId);
            if (subItemIds != null) {
                String[] split = subItemIds.split(",");
                List<ItemMsg> msgByItemIds = mItemMsgMapper.getMsgByItemIds(split);
                for (ItemMsg msgByItemId : msgByItemIds) {
                    msgByItemId.setBaseUrl(baseUrl);
                }
                imgTextMsgBean.setSubItems(msgByItemIds);
            }
            return imgTextMsgBean;
        }
        return null;
    }

    /**
     * 获取所有对应消息类型的消息列表
     *
     * @param msgType 消息类型
     */
    @Override
    public List<WeixinMsg> getMsgList(int msgType) {
        return mWeixinMsgMapper.getAllMsgByMsgType(msgType);
    }

    /**
     * 根据用户输入的关键字返回相应的 WxMpXmlOutMessage
     *
     * @param content 用户输入的关键字
     */
    @Override
    public WxMpXmlOutMessage getOutMessage(String content, WxMpXmlMessage wxMessage, WxMpService wxMpService) {
        WeixinMsg weixinMsg = mWeixinMsgMapper.getWeixinMsgByContent(content);
        if (weixinMsg != null) {
            String msgId = weixinMsg.getMsgId();
            int msgType = weixinMsg.getMsgType();
            if (msgType == TEXT_MSG) {
                TextMsg textMsg = mTextMsgMapper.selectByPrimaryKey(msgId);
                return new TextBuilder().build(textMsg.getContent(), wxMessage, (WeixinService) wxMpService);
            } else if (msgType == MEDIA_MSG) {
                MediaMsg mediaMsg = mMediaMsgMapper.selectByPrimaryKey(msgId);
                return new ImageBuilder().build(mediaMsg.getMediaId(), wxMessage, (WeixinService) wxMpService);
            } else if (msgType == IMAGE_TEXT_MSG) {
                return getImgTextMsg(msgId, wxMessage, wxMpService);
            }
            return null;
        } else {
            weixinMsg = mWeixinMsgMapper.getWeixinMsgByContent(defaultMsgKeyWord);
            String msgId = weixinMsg.getMsgId();
            TextMsg textMsg = mTextMsgMapper.selectByPrimaryKey(msgId);
            return new TextBuilder().build(textMsg.getContent(), wxMessage, (WeixinService) wxMpService);
        }
    }

    private WxMpXmlOutMessage getImgTextMsg(String msgId, WxMpXmlMessage wxMessage, WxMpService wxMpService) {
        ImgTextMsg imgTextMsg = mImgTextMsgMapper.getMsgByMsgId(msgId);
        String subItemIds = imgTextMsg.getSubItemIds();
        List<WxMpXmlOutNewsMessage.Item> subItems = new ArrayList<>();
        if (subItemIds != null) {
            String[] items = subItemIds.split(",");
            List<ItemMsg> itemMsgs = mItemMsgMapper.getMsgByItemIds(items);
            if (itemMsgs != null && itemMsgs.size() > 0) {
                for (ItemMsg itemMsg : itemMsgs) {
                    itemMsg.setBaseUrl(baseUrl);
                    subItems.add(itemMsg2Item(itemMsg));
                }
            }
        }
        ItemMsg itemMsg = mItemMsgMapper.selectByPrimaryKey(imgTextMsg.getMasterItemId());
        itemMsg.setBaseUrl(baseUrl);
        WxMpXmlOutNewsMessage.Item item = itemMsg2Item(itemMsg);
        subItems.add(0, item);
        return new ImageTextBuilder().build(item, subItems, wxMessage, (WeixinService) wxMpService);
    }

    /**
     * 添加文本回复消息
     *
     * @param content 回复给用户的内容
     * @param keyWord 用户输入的关键字
     */
    @Override
    @Transactional
    public int addTextReplyMsg(String content, String keyWord) {
        if (checkKeyWordExists(keyWord)) {
            return Constants.AddMsgResult.KEY_WORD_EXISTS;
        }
        String msgId = UUIDUtils.getUUID();
        WeixinMsg weixinMsg = new WeixinMsg();
        weixinMsg.setContent(keyWord);
        weixinMsg.setMsgType(TEXT_MSG);
        weixinMsg.setMsgId(msgId);
        int insert = mWeixinMsgMapper.insert(weixinMsg);
        if (weixinMsg.getMsgId() == null) {
            throw new RuntimeException("获取 msgId 失败");
        }
        if (insert <= 0) {
            throw new RuntimeException("插入 WEIXIN_BAS_MSG keyWord = " + keyWord + "失败");
        }
        TextMsg textMsg = new TextMsg();
        textMsg.setMsgId(msgId);
        textMsg.setContent(content);
        insert = mTextMsgMapper.insert(textMsg);
        if (insert <= 0) {
            throw new RuntimeException("插入 WEIXIN_TEXT_MSG keyWord = " + keyWord + "失败");
        }
        return insert;
    }

    /**
     * 更新文本回复消息
     */
    @Override
    @Transactional
    public int updateTextReplyMsg(TextMsg textMsg) {
        WeixinMsg weixinMsg = new WeixinMsg();
        weixinMsg.setMsgId(textMsg.getMsgId());
        weixinMsg.setMsgType(Constants.MsgType.TEXT_MSG);
        weixinMsg.setContent(textMsg.getKeyWord());
        weixinMsg.setMsgId(textMsg.getMsgId());
        int update = mWeixinMsgMapper.updateByPrimaryKey(weixinMsg);
        if (update <= 0) {
            throw new RuntimeException("更新 WEIXIN_BAS_MSG msgId = " + textMsg.getMsgId() + "失败");
        }
        update = mTextMsgMapper.updateByPrimaryKey(textMsg);
        if (update <= 0) {
            throw new RuntimeException("更新 WEIXIN_TEXT_MSG msgId = " + textMsg.getMsgId() + "失败");
        }
        return update;
    }

    /**
     * 删除文本回复消息
     */
    @Override
    @Transactional
    public int deleteTextReplyMsg(String msgId) {
        int delete = mWeixinMsgMapper.deleteByPrimaryKey(msgId);
        if (delete <= 0) {
            throw new RuntimeException("删除 WEIXIN_BAS_MSG msgId = " + msgId + "失败");
        } else {
            delete = mTextMsgMapper.deleteByPrimaryKey(msgId);
            if (delete <= 0) {
                throw new RuntimeException("删除 WEIXIN_TEXT_MSG msgId = " + msgId + "失败");
            }
        }
        return delete;
    }

    /**
     * 添加媒体回复消息
     *
     * @param mediaId 回复给用户的媒体内容的ID
     * @param keyWord 用户输入的关键字
     */
    @Override
    @Transactional
    public int addMediaReplyMsg(String mediaId, String keyWord, String mediaUrl) {
        if (checkKeyWordExists(keyWord)) {
            return Constants.AddMsgResult.KEY_WORD_EXISTS;
        }
        String msgId = UUIDUtils.getUUID();
        WeixinMsg weixinMsg = new WeixinMsg();
        weixinMsg.setContent(keyWord);
        weixinMsg.setMsgType(MEDIA_MSG);
        weixinMsg.setMsgId(msgId);
        int insert = mWeixinMsgMapper.insert(weixinMsg);
        if (weixinMsg.getMsgId() == null) {
            throw new RuntimeException("获取 msgId 失败");
        }
        if (insert <= 0) {
            throw new RuntimeException("插入 WEIXIN_BAS_MSG keyWord = " + keyWord + "失败");
        }
        SuperLogger.e(weixinMsg.getMsgId());
        MediaMsg mediaMsg = new MediaMsg();
        mediaMsg.setMsgId(msgId);
        mediaMsg.setMediaId(mediaId);
        mediaMsg.setMediaUrl(mediaUrl);
        insert = mMediaMsgMapper.insert(mediaMsg);
        if (insert <= 0) {
            throw new RuntimeException("插入 WEIXIN_MEDIA_MSG msgId = " + weixinMsg.getMsgId() + "失败");
        }
        return insert;
    }

    /**
     * 添加图文回复消息
     *
     * @param imgTextMsgBean 前端传过来的所有的条目
     */
    @Override
    @Transactional
    public int addImageTextReplyMsg(ImgTextMsgBean imgTextMsgBean) {
        String keyWord = imgTextMsgBean.getKeyWord();
        if (checkKeyWordExists(keyWord)) {
            return Constants.AddMsgResult.KEY_WORD_EXISTS;
        }
        String msgId = UUIDUtils.getUUID();
        WeixinMsg weixinMsg = new WeixinMsg();
        weixinMsg.setContent(keyWord);
        weixinMsg.setMsgType(IMAGE_TEXT_MSG);
        weixinMsg.setMsgId(msgId);
        int insert = mWeixinMsgMapper.insert(weixinMsg);
        if (insert <= 0) {
            throw new RuntimeException("插入 WEIXIN_BAS_MSG msgId = " + weixinMsg.getMsgId() + "失败");
        }

        ItemMsg itemMsg = imgTextMsgBean.getMasterItem();
        String itemId = UUIDUtils.getUUID();
        itemMsg.setItemId(itemId);
        SuperLogger.e(itemMsg);
        insert = mItemMsgMapper.insert(itemMsg);
        if (insert <= 0) {
            throw new RuntimeException("插入 WEIXIN_ITEM_MSG title = " + itemMsg.getTitle() + "失败");
        }

        StringBuffer sb = new StringBuffer();
        List<ItemMsg> items = imgTextMsgBean.getSubItems();
        for (ItemMsg itemMsgBean : items) {
            String uuid = UUIDUtils.getUUID();
            itemMsgBean.setItemId(uuid);
            SuperLogger.e(itemMsgBean);
            insert = mItemMsgMapper.insert(itemMsgBean);
            if (insert > 0) {
                sb.append(uuid).append(",");
            } else {
                throw new RuntimeException("插入 WEIXIN_ITEM_MSG title = " + itemMsgBean.getTitle() + "失败");
            }
        }

        ImgTextMsg imgTextMsg = new ImgTextMsg();
        imgTextMsg.setMsgId(weixinMsg.getMsgId());
        imgTextMsg.setMasterItemId(itemId);
        imgTextMsg.setSubItemIds(sb.toString());
        insert = mImgTextMsgMapper.insert(imgTextMsg);
        if (insert > 0) {
            return insert;
        } else {
            throw new RuntimeException("插入 WEIXIN_IMG_TEXT_MSG msgId = " + weixinMsg.getMsgId() + "失败");
        }
    }

    /**
     * 更新图文回复消息
     *
     * @param imgTextMsgBean 前端传过来的所有的条目
     */
    @Override
    @Transactional
    public int updateImageTextReplyMsg(ImgTextMsgBean imgTextMsgBean) {
        if (imgTextMsgBean != null) {
            mBaseLoginUrl = baseUrl + "user/getUser?url=";
            ItemMsg masterItem = imgTextMsgBean.getMasterItem();
            String imageUrl = masterItem.getImageUrl();
            String url = masterItem.getUrl();
            imageUrl=imageUrl.replace(baseUrl,"");
            url = url.replace(mBaseLoginUrl,"").replace(baseUrl,"");
            masterItem.setImageUrl(imageUrl);
            masterItem.setUrl(url);
            List<ItemMsg> subItems = imgTextMsgBean.getSubItems();
            if (subItems != null && subItems.size() > 0) {
                for (ItemMsg subItem : subItems) {
                    String subImageUrl = masterItem.getImageUrl();
                    String subUrl = masterItem.getUrl();
                    subImageUrl=subImageUrl.replace(baseUrl,"");
                    subUrl = subUrl.replace(mBaseLoginUrl,"");
                    subItem.setUrl(subUrl);
                    subItem.setImageUrl(subImageUrl);
                }
            }
        }
        String msgId = imgTextMsgBean.getMsgId();
        WeixinMsg weixinMsg = mWeixinMsgMapper.getWeixinMsgByMsgId(msgId);
        if (weixinMsg == null) {
            return Constants.AddMsgResult.KEY_WORD_NOT_EXISTS;
        } else {
            weixinMsg.setContent(imgTextMsgBean.getKeyWord());
            int i = mWeixinMsgMapper.updateByKeyword(weixinMsg);
            if (i < 0) {
                throw new RuntimeException("更新 WEIXIN_BAS_MSG msgId = " + msgId + "失败");
            }
        }
        ItemMsg itemMsg = imgTextMsgBean.getMasterItem();
        int count = mItemMsgMapper.updateByPrimaryKey(itemMsg);
        if (count <= 0) {
            throw new RuntimeException("更新 WEIXIN_ITEM_MSG msgId = " + itemMsg.getItemId() + "失败");
        }
        String itemId = itemMsg.getItemId();
        StringBuffer sb = new StringBuffer();
        List<ItemMsg> items = imgTextMsgBean.getSubItems();
        if (items != null && items.size() > 0) {
            for (ItemMsg itemMsgBean : items) {
                count = mItemMsgMapper.updateByPrimaryKey(itemMsgBean);
                if (count > 0) {
                    sb.append(itemMsgBean.getItemId()).append(",");
                } else {
                    throw new RuntimeException("更新 WEIXIN_ITEM_MSG itemId = " + itemMsgBean.getItemId() + "失败");
                }
            }
        }

        ImgTextMsg imgTextMsg = new ImgTextMsg();
        imgTextMsg.setMsgId(weixinMsg.getMsgId());
        imgTextMsg.setMasterItemId(itemId);
        imgTextMsg.setSubItemIds(sb.toString());
        int insertCount = mImgTextMsgMapper.updateByMsgId(imgTextMsg);
        if (insertCount > 0) {
            return 1;
        } else {
            throw new RuntimeException("更新 WEIXIN_IMG_TEXT_MSG msgId = " + weixinMsg.getMsgId() + "失败");
        }
    }

    /**
     * 删除图文回复消息
     *
     * @param imgTextMsgBean 前端传过来的所有的条目
     */
    @Override
    @Transactional
    public int deleteImageTextReplyMsg(ImgTextMsgBean imgTextMsgBean) {
        String msgId = imgTextMsgBean.getMsgId();
        WeixinMsg weixinMsg = mWeixinMsgMapper.getWeixinMsgByMsgId(msgId);
        if (weixinMsg != null) {
            int i = mWeixinMsgMapper.deleteByPrimaryKey(msgId);
            if (i <= 0) {
                throw new RuntimeException("更新 WEIXIN_IMG_TEXT_MSG msgId = " + msgId + "失败");
            }
        } else {
            return Constants.AddMsgResult.KEY_WORD_NOT_EXISTS;
        }
        ItemMsg itemMsg = imgTextMsgBean.getMasterItem();
        int count = mItemMsgMapper.deleteByPrimaryKey(itemMsg.getItemId());
        if (count <= 0) {
            throw new RuntimeException("更新 WEIXIN_ITEM_MSG itemId = " + itemMsg.getItemId() + "失败");
        }
        SuperLogger.e(itemMsg.getItemId());
        List<ItemMsg> items = imgTextMsgBean.getSubItems();
        if (items != null && items.size() > 0) {
            for (ItemMsg itemMsgBean : items) {
                count = mItemMsgMapper.deleteByPrimaryKey(itemMsgBean.getItemId());
                if (count < 0) {
                    throw new RuntimeException("更新 WEIXIN_ITEM_MSG itemId = " + itemMsgBean.getItemId() + "失败");
                }
            }
        }

        count = mImgTextMsgMapper.removeMsgByMsgId(msgId);
        if (count <= 0) {
            throw new RuntimeException("更新 WEIXIN_IMG_TEXT_MSG msgId = " + msgId + "失败");
        }
        return count;
    }

    private boolean checkKeyWordExists(String keyWord) {
        WeixinMsg weixinMsgByContent = mWeixinMsgMapper.getWeixinMsgByContent(keyWord);
        return weixinMsgByContent != null;
    }

    private WxMpXmlOutNewsMessage.Item itemMsg2Item(ItemMsg itemMsg) {
        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
        item.setDescription(itemMsg.getDescription());
        item.setPicUrl(itemMsg.getImageUrl());
        item.setTitle(itemMsg.getTitle());
        item.setUrl(itemMsg.getUrl());
        return item;
    }

    private ItemMsg item2ItemMsg(WxMpXmlOutNewsMessage.Item item) {
        ItemMsg itemMsg = new ItemMsg();
        itemMsg.setDescription(item.getDescription());
        itemMsg.setImageUrl(item.getPicUrl());
        itemMsg.setTitle(item.getTitle());
        itemMsg.setUrl(item.getUrl());
        return itemMsg;
    }
}
