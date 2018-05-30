package com.gaofen.controller;

import com.gaofen.common.dto.Result;
import com.gaofen.common.utils.GsonQuick;
import com.gaofen.service.weixin.WeixinMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;

/**
 * <pre>
 *  注意：此contorller 实现WxMpMenuService接口，仅是为了演示如何调用所有菜单相关操作接口，
 *      实际项目中无需这样，根据自己需要添加对应接口即可
 * </pre>
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@RestController
@RequestMapping("/wechat/menu")
public class WxMenuController implements WxMpMenuService {

    @Autowired
    private WxMpService wxService;

    @Autowired
    private WeixinMenuService mWeixinMenuService;

    @Value("#{configProperties['baseUrl']}")
    private String baseUrl;

    @Value("#{configProperties['baseWebUrl']}")
    private String baseWebUrl;

    /**
     * <pre>
     * 自定义菜单创建接口
     * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
     * 如果要创建个性化菜单，请设置matchrule属性
     * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
     * </pre>
     *
     * @return 如果是个性化菜单，则返回menuid，否则返回null
     */
    @Override
    @PostMapping("/creates")
    public String menuCreate(@RequestBody WxMenu menu) throws WxErrorException {
        int i = mWeixinMenuService.saveMenuData(menu);
        return this.wxService.getMenuService().menuCreate(menu);
    }

    @PostMapping("/create")
    public Result saveMenu(@RequestBody String paramJson) {
        Result result = Result.getInstance();
        try {
            WxMenu wxMenu = GsonQuick.fromJsontoBean(paramJson, WxMenu.class);
            int i = mWeixinMenuService.saveMenuData(wxMenu);
            this.wxService.getMenuService().menuCreate(wxMenu);
            return result.success("菜单创建成功");
        } catch (Exception e) {
            return result.exception(e);
        }

    }

    @GetMapping("/create")
    public String menuCreateSample() throws WxErrorException {
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType(WxConsts.BUTTON_CLICK);
        button1.setName("我要查询");
//        button1.setKey("V1001_TODAY_MUSIC");

        WxMenuButton button11 = new WxMenuButton();
        button11.setType(WxConsts.BUTTON_VIEW);
        button11.setName("影像查询");
        button11.setUrl(baseUrl + "user/getUser?url=" + baseWebUrl + "html/shadow/position.html");

        WxMenuButton button12 = new WxMenuButton();
        button12.setType(WxConsts.BUTTON_VIEW);
        button12.setName("订单查询");
        button12.setUrl(baseUrl + "user/getUser?url=" + baseWebUrl + "html/order/orderList.html");

        button1.getSubButtons().add(button11);
        button1.getSubButtons().add(button12);

        WxMenuButton button2 = new WxMenuButton();
        button2.setType(WxConsts.BUTTON_CLICK);
        button2.setName("高分中心");

        WxMenuButton button21 = new WxMenuButton();
        button21.setType(WxConsts.BUTTON_VIEW);
        button21.setName("办事指南");
        button21.setUrl(baseWebUrl + "html/me/guide.html");

        WxMenuButton button22 = new WxMenuButton();
        button22.setType(WxConsts.BUTTON_VIEW);
        button22.setName("通知公告");
        button22.setUrl(baseWebUrl + "html/me/message.html");

        WxMenuButton button23 = new WxMenuButton();
        button23.setType(WxConsts.BUTTON_VIEW);
        button23.setName("联系我们");
        button23.setUrl(baseWebUrl + "html/me/about.html");

        button2.getSubButtons().add(button21);
        button2.getSubButtons().add(button22);
        button2.getSubButtons().add(button23);

        WxMenuButton button3 = new WxMenuButton();
        button3.setType(WxConsts.BUTTON_CLICK);
        button3.setName("个人中心");

        WxMenuButton button31 = new WxMenuButton();
        button31.setType(WxConsts.BUTTON_VIEW);
        button31.setName("我的账号");
        button31.setUrl(baseUrl + "user/getUser?url=" + baseWebUrl + "html/logout.html");

        button3.getSubButtons().add(button31);

        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        return this.wxService.getMenuService().menuCreate(menu);
    }

    @GetMapping("/getMenu")
    public Result getMenu() {
        Result result = Result.getInstance();
        try {
            WxMenu menuData = mWeixinMenuService.getMenuData();
            if (menuData != null) {
                return result.success(menuData);
            } else {
                return result.failure("没有找到相关数据");
            }
        } catch (Exception e) {
            return result.exception(e);
        }

    }

    /**
     * <pre>
     * 自定义菜单创建接口
     * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
     * 如果要创建个性化菜单，请设置matchrule属性
     * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
     * </pre>
     *
     * @return 如果是个性化菜单，则返回menuid，否则返回null
     */
    @Override
    @GetMapping("/create/{json}")
    public String menuCreate(@PathVariable String json) throws WxErrorException {
        return this.wxService.getMenuService().menuCreate(json);
    }

    /**
     * <pre>
     * 自定义菜单删除接口
     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN
     * </pre>
     */
    @Override
    @GetMapping("/delete")
    public void menuDelete() throws WxErrorException {
        this.wxService.getMenuService().menuDelete();
    }

    /**
     * <pre>
     * 删除个性化菜单接口
     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
     * </pre>
     *
     * @param menuId 个性化菜单的menuid
     */
    @Override
    @GetMapping("/delete/{menuId}")
    public void menuDelete(@PathVariable String menuId) throws WxErrorException {
        this.wxService.getMenuService().menuDelete(menuId);
    }

    /**
     * <pre>
     * 自定义菜单查询接口
     * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN
     * </pre>
     */
    @Override
    @GetMapping("/get")
    public WxMpMenu menuGet() throws WxErrorException {
        return this.wxService.getMenuService().menuGet();
    }

    /**
     * <pre>
     * 测试个性化菜单匹配结果
     * 详情请见: http://mp.weixin.qq.com/wiki/0/c48ccd12b69ae023159b4bfaa7c39c20.html
     * </pre>
     *
     * @param userid 可以是粉丝的OpenID，也可以是粉丝的微信号。
     */
    @Override
    @GetMapping("/menuTryMatch/{userid}")
    public WxMenu menuTryMatch(@PathVariable String userid) throws WxErrorException {
        return this.wxService.getMenuService().menuTryMatch(userid);
    }

    /**
     * <pre>
     * 获取自定义菜单配置接口
     * 本接口将会提供公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。
     * 请注意：
     * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自定义菜单配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
     * 2、本接口与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。
     * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
     * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
     * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
     *  接口调用请求说明:
     * http请求方式: GET（请使用https协议）
     * https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN
     * </pre>
     */
    @Override
    @GetMapping("/getSelfMenuInfo")
    public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
        return this.wxService.getMenuService().getSelfMenuInfo();
    }
}
