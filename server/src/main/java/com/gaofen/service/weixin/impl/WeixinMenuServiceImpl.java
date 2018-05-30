package com.gaofen.service.weixin.impl;

import com.gaofen.common.utils.UUIDUtils;
import com.gaofen.mapper.WeixinMenuButtonMapper;
import com.gaofen.mapper.WeixinSubMenuButtonMapper;
import com.gaofen.model.WeixinMenuButton;
import com.gaofen.model.WeixinSubMenuButton;
import com.gaofen.service.weixin.WeixinMenuService;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;

@Service
public class WeixinMenuServiceImpl implements WeixinMenuService {

    @Autowired
    WeixinMenuButtonMapper mWeixinMenuButtonMapper;

    @Autowired
    WeixinSubMenuButtonMapper mWeixinSubMenuButtonMapper;

    @Override
    @Transactional
    public int saveMenuData(WxMenu menu){
        mWeixinMenuButtonMapper.clearAll();
        mWeixinSubMenuButtonMapper.clearAll();
        List<WxMenuButton> buttons = menu.getButtons();
        for (WxMenuButton button : buttons) {
            List<WxMenuButton> subButtons = button.getSubButtons();
            StringBuffer sb = new StringBuffer();
            if (subButtons!=null&&subButtons.size()>0){
                for (WxMenuButton subButton : subButtons) {
                    WeixinSubMenuButton weixinSubMenuButton = new WeixinSubMenuButton(subButton);
                    String uuid = UUIDUtils.getUUID();
                    sb.append(uuid).append(",");
                    weixinSubMenuButton.setMenuId(uuid);
                    int insert = mWeixinSubMenuButtonMapper.insert(weixinSubMenuButton);
                    if (insert<=0){
                        throw new RuntimeException("插入子菜单失败 name="+subButton.getName());
                    }
                }
            }
            WeixinMenuButton weixinMenuButton = new WeixinMenuButton(button);
            weixinMenuButton.setMenuId(UUIDUtils.getUUID());
            weixinMenuButton.setSubMenuIds(sb.toString());
            int insert = mWeixinMenuButtonMapper.insert(weixinMenuButton);
            if (insert<=0){
                throw new RuntimeException("插入主菜单失败 name="+button.getName());
            }
        }
        return 1;
    }

    @Override
    public WxMenu getMenuData() {
        WxMenu wxMenu = new WxMenu();
        List masterButtons=mWeixinMenuButtonMapper.getMasterButtons();
        for (Object obj : masterButtons) {
            WeixinMenuButton masterButton = (WeixinMenuButton) obj;
            List<WxMenuButton> list = new ArrayList<>();
            String subMenuIdStr = masterButton.getSubMenuIds();
            if (!TextUtils.isEmpty(subMenuIdStr)) {
                String[] subMenuIds = subMenuIdStr.split(",");
                for (String subMenuId : subMenuIds) {
                    WeixinSubMenuButton weixinSubMenuButton = mWeixinSubMenuButtonMapper.selectByPrimaryKey(subMenuId);
                    list.add(weixinSubMenuButton);
                }
            }

            masterButton.setSubButtons(list);
        }
        wxMenu.setButtons(masterButtons);
        return wxMenu;
    }
}
