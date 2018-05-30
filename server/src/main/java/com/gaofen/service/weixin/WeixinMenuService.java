package com.gaofen.service.weixin;

import me.chanjar.weixin.common.bean.menu.WxMenu;

public interface WeixinMenuService {
    int saveMenuData(WxMenu menu);

    WxMenu getMenuData();
}
