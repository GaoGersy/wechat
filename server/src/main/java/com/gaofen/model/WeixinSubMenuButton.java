package com.gaofen.model;

import java.io.Serializable;

import me.chanjar.weixin.common.bean.menu.WxMenuButton;

public class WeixinSubMenuButton extends WxMenuButton implements Serializable {
//    private String menuId;
//

//
//    public WeixinSubMenuButton(String menuId, String type, String name, String key, String url, String mediaId, String appId, String pagePath) {
//        this.menuId = menuId;
//        this.setName(name);
//        this.setType(type);
//        this.setUrl(url);
//        this.setAppId(appId);
//        this.setMediaId(mediaId);
//        this.setKey(key);
//        this.setPagePath(pagePath);
//    }

    private String menuId;

    private String type;

    private String name;

    private String key;

    private String url;

    private String mediaId;

    private String appId;

    private String pagePath;

    public WeixinSubMenuButton(String menuId, String type, String name, String key, String url, String mediaId, String appId, String pagePath) {
        this.menuId = menuId;
        this.type = type;
        this.name = name;
        this.key = key;
        this.url = url;
        this.mediaId = mediaId;
        this.appId = appId;
        this.pagePath = pagePath;
    }

    public WeixinSubMenuButton(WxMenuButton wxMenuButton) {
        this.setName(wxMenuButton.getName());
        this.setType(wxMenuButton.getType());
        this.setUrl(wxMenuButton.getUrl());
        this.setAppId(wxMenuButton.getAppId());
        this.setMediaId(wxMenuButton.getMediaId());
        this.setKey(wxMenuButton.getKey());
        this.setPagePath(wxMenuButton.getPagePath());
    }

    public WeixinSubMenuButton() {
        super();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath == null ? null : pagePath.trim();
    }
}