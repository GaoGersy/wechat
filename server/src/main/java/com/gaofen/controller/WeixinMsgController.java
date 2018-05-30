package com.gaofen.controller;

import com.google.gson.JsonObject;

import com.gaofen.common.config.WxMpConfig;
import com.gaofen.common.constant.Constants;
import com.gaofen.common.dto.Result;
import com.gaofen.common.utils.GsonQuick;
import com.gaofen.common.utils.MD5Util;
import com.gaofen.common.utils.OkHttpUtils;
import com.gaofen.mapper.MediaMsgMapper;
import com.gaofen.mapper.WeixinMsgMapper;
import com.gaofen.model.weixin.BaseMsgBean;
import com.gaofen.model.weixin.ImgTextMsgBean;
import com.gaofen.model.weixin.MediaMsg;
import com.gaofen.model.weixin.TextMsg;
import com.gaofen.model.weixin.WeixinMsg;
import com.gaofen.service.weixin.IWeixinMsgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import static com.gaofen.common.constant.Constants.AddMsgResult.KEY_WORD_NOT_EXISTS;
import static com.gaofen.common.constant.Constants.KEY_WORD_EXISTS;

@Controller
@ResponseBody
@RequestMapping("/weixin")
public class WeixinMsgController {
    private static final String ADD_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    @Autowired
    private WxMpConfig wxConfig;

    @Autowired
    IWeixinMsgService mWeixinMsgService;

    @Autowired
    MediaMsgMapper mMediaMsgMapper;

    @Autowired
    WeixinMsgMapper mWeixinMsgMapper;

    @Autowired
    RedisTemplate<String, String> mRedisTemplate;

    @RequestMapping(value = "/addTextReplyMsg", method = RequestMethod.POST)
    public Result addTextReplyMsg(@RequestBody String jsonParam) {
        Result result = Result.getInstance();
        try {
            JsonObject jsonObject = GsonQuick.getJsonObject(jsonParam);
            String content = GsonQuick.getString(jsonObject, "content");
            String keyWord = GsonQuick.getString(jsonObject, "keyWord");
            int count = mWeixinMsgService.addTextReplyMsg(content, keyWord);
            if (count == Constants.AddMsgResult.SUCCESS) {
                return result.success("添加成功");
            } else if (count == Constants.AddMsgResult.FAILED) {
                return result.failure("添加失败");
            } else if (count == Constants.AddMsgResult.KEY_WORD_EXISTS) {
                return result.failure("关键字已经存在，请检查后再提交！", KEY_WORD_EXISTS);
            }
            return result.failure("添加失败");
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/updateTextReplyMsg", method = RequestMethod.POST)
    public Result updateTextReplyMsg(@RequestBody String jsonParam) {
        Result result = Result.getInstance();
        try {
            TextMsg textMsg = GsonQuick.fromJsontoBean(jsonParam, TextMsg.class);
            int count = mWeixinMsgService.updateTextReplyMsg(textMsg);
            if (count == Constants.AddMsgResult.SUCCESS) {
                return result.success("更新成功");
            } else if (count == Constants.AddMsgResult.FAILED) {
                return result.failure("更新失败");
            }
            return result.failure("更新失败");
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/deleteTextReplyMsg", method = RequestMethod.GET)
    public Result deleteTextReplyMsg(HttpServletRequest request) {
        Result result = Result.getInstance();
        try {
            String msgId = request.getParameter("msgId");
            int count = mWeixinMsgService.deleteTextReplyMsg(msgId);
            if (count == Constants.AddMsgResult.SUCCESS) {
                return result.success("删除成功");
            } else if (count == Constants.AddMsgResult.FAILED) {
                return result.failure("删除失败");
            }
            return result.failure("删除失败");
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/addMediaReplyMsg", method = RequestMethod.POST)
    public Result addMediaReplyMsg(@RequestBody String jsonParam) {
        Result result = Result.getInstance();
        try {
            JsonObject jsonObject = GsonQuick.getJsonObject(jsonParam);
            String mediaId = GsonQuick.getString(jsonObject, "mediaId");
            String keyWord = GsonQuick.getString(jsonObject, "keyWord");
            String mediaUrl = GsonQuick.getString(jsonObject, "mediaUrl");
            int count = mWeixinMsgService.addMediaReplyMsg(mediaId, keyWord, mediaUrl);
            if (count == Constants.AddMsgResult.SUCCESS) {
                return result.success("添加成功");
            } else if (count == Constants.AddMsgResult.FAILED) {
                return result.failure("添加失败");
            } else if (count == Constants.AddMsgResult.KEY_WORD_EXISTS) {
                return result.failure("关键字已经存在，请检查后再提交！", KEY_WORD_EXISTS);
            }
            return result.failure("添加失败");
        } catch (Exception e) {
            return result.exception(e);
        }

    }

    @RequestMapping(value = "/addImgTextReplyMsg", method = RequestMethod.POST)
    public Result addImgTextReplyMsg(@RequestBody String jsonParam) {
        Result result = Result.getInstance();
        try {
            ImgTextMsgBean imgTextMsgBean = GsonQuick.fromJsontoBean(jsonParam, ImgTextMsgBean.class);
            int count = mWeixinMsgService.addImageTextReplyMsg(imgTextMsgBean);
            if (count == Constants.AddMsgResult.SUCCESS) {
                return result.success("添加成功");
            } else if (count == Constants.AddMsgResult.FAILED) {
                return result.failure("添加失败");
            } else if (count == Constants.AddMsgResult.KEY_WORD_EXISTS) {
                return result.failure("关键字已经存在，请检查后再提交！", KEY_WORD_EXISTS);
            }
            return result.failure("添加失败");
        } catch (Exception e) {
            return result.exception(e);
        }

    }

    @RequestMapping(value = "/updateImgTextReplyMsg", method = RequestMethod.POST)
    public Result updateImgTextReplyMsg(@RequestBody String jsonParam) {
        Result result = Result.getInstance();
        try {
            ImgTextMsgBean imgTextMsgBean = GsonQuick.fromJsontoBean(jsonParam, ImgTextMsgBean.class);
            int count = mWeixinMsgService.updateImageTextReplyMsg(imgTextMsgBean);
            if (count == Constants.AddMsgResult.SUCCESS) {
                return result.success("更新成功");
            } else if (count == Constants.AddMsgResult.FAILED) {
                return result.failure("更新失败");
            } else if (count == KEY_WORD_NOT_EXISTS) {
                return result.failure("关键字不存在，请检查后再提交！", KEY_WORD_EXISTS);
            }
            return result.failure("更新失败");
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/deleteImgTextReplyMsg", method = RequestMethod.POST)
    public Result deleteImgTextReplyMsg(@RequestBody String jsonParam) {
        Result result = Result.getInstance();
        try {
            ImgTextMsgBean imgTextMsgBean = GsonQuick.fromJsontoBean(jsonParam, ImgTextMsgBean.class);
            int count = mWeixinMsgService.deleteImageTextReplyMsg(imgTextMsgBean);
            if (count == Constants.AddMsgResult.SUCCESS) {
                return result.success("删除成功");
            } else if (count == Constants.AddMsgResult.FAILED) {
                return result.failure("删除失败");
            } else if (count == KEY_WORD_NOT_EXISTS) {
                return result.failure("关键字不存在，请检查后再提交！", KEY_WORD_EXISTS);
            }
            return result.failure("删除失败");
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/getMsgDetail", method = RequestMethod.GET)
    public Result getMsgDetail(HttpServletRequest req) {
        Result result = Result.getInstance();
        try {
            String msgId = req.getParameter("msgId");
            int msgType = Integer.parseInt(req.getParameter("msgType"));
            BaseMsgBean msgDetail = mWeixinMsgService.getMsgDetail(msgId, msgType);
            if (msgDetail != null) {
                return result.success(msgDetail);
            } else {
                return result.failure("没有找到相关的数据");
            }
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/getMsgList", method = RequestMethod.GET)
    public Result getMsgList(HttpServletRequest req) {
        Result result = Result.getInstance();
        try {
            int msgType = Integer.parseInt(req.getParameter("msgType"));
            List<WeixinMsg> msgList = mWeixinMsgService.getMsgList(msgType);
            return result.success(msgList);
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/getAddMaterialUrl", method = RequestMethod.GET)
    public Result getAddMaterialUrl(HttpServletRequest req) {
        Result result = Result.getInstance();
        try {
            File file = new File("D:\\project\\server\\src\\main\\webapp\\resources\\img\\wxcode.png");
            String s = uplod2Weixin(file, "image");
            return result.success(s);
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    private String uplod2Weixin(File file, String type) {
        String url = ADD_MATERIAL_URL.replace("ACCESS_TOKEN", getToken()).replace("TYPE", type);
        return OkHttpUtils.uploadMultiFile(url, file);
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public Result uploadImage(
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest req) {
        Result result = Result.getInstance();
        try {
            String fileId = MD5Util.getMultipartFileMD5(file);
            String imgUrl = Constants.MEDIA;
            String path = req.getSession().getServletContext().getRealPath(imgUrl);
            String filename = file.getOriginalFilename();
            int index = filename.lastIndexOf(".");

            if (index < 0) {
                return result.failure("文件格式未知");
            }

            String filetype = filename.substring(index + 1).toLowerCase();
            List<String> imageTypes = Arrays.asList(Constants.IMAGE_TYPE);
            if (!imageTypes.contains(filetype)){
                return result.failure("媒体格式不支持");
            }

            String newFilename = fileId + "." + filetype;

            File target = new File(path, newFilename);
            if (!target.exists()) {
                target.mkdirs();
            }

            file.transferTo(target);
            return result.success(imgUrl + fileId + "." + filetype);

        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/addMediaMaterial", method = RequestMethod.POST)
    public Result addMediaMaterial(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "mediaType") String mediaType,
            @RequestParam(value = "keyWord") String keyWord,
            HttpServletRequest req) {
        Result result = Result.getInstance();
        try {
            String fileId = MD5Util.getMultipartFileMD5(file);
            WeixinMsg weixinMsg = mWeixinMsgMapper.getWeixinMsgByContent(keyWord);
            String imgUrl = Constants.MEDIA;
            String path = req.getSession().getServletContext().getRealPath(imgUrl);
            String filename = file.getOriginalFilename();
            int index = filename.lastIndexOf(".");

            if (index < 0) {
                return result.failure("媒体格式不支持");
            }

            String filetype = filename.substring(index + 1).toLowerCase();

            String newFilename = fileId + "." + filetype;

            File target = new File(path, newFilename);
            if (!target.exists()) {
                target.mkdirs();
            }

            file.transferTo(target);
            String mediaResult = uplod2Weixin(target, mediaType);
            if (mediaResult.contains("errcode")) {
                return result.failure("上传失败");
            }

            MediaMsg mediaMsg = new MediaMsg();
            mediaMsg.setMediaUrl(fileId);
            mediaMsg.setMediaId(GsonQuick.getString(mediaResult, "media_Id"));
            mediaMsg.setMsgId(weixinMsg.getMsgId());
            int i = -1;
            if (weixinMsg != null) {
                i = mMediaMsgMapper.updateByPrimaryKey(mediaMsg);
            } else {
                i = mMediaMsgMapper.insert(mediaMsg);
            }

            if (i > 0) {
                return result.success("上传成功");
            } else {
                return result.failure("上传失败");
            }

        } catch (Exception e) {
            return result.exception(e);
        }
    }

    public synchronized String getToken() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - Constants.WeiXin.ACCESS_TOKEN_GET_TIME < 7200) {
            return Constants.WeiXin.ACCESS_TOKEN;
        } else {
            return getAccessTokenByNet();
        }
    }

    private String getAccessTokenByNet() {
        String url = TOKEN_URL
                .replace("APPID", wxConfig.getAppid())
                .replace("APPSECRET", wxConfig.getAppsecret());
        String result = OkHttpUtils.getSync(url);
        if (result != null) {
            if (!result.contains("errcode")) {
                TokenBean tokenBean = GsonQuick.fromJsontoBean(result, TokenBean.class);
                Constants.WeiXin.TOKEN = tokenBean.access_token;
                Constants.WeiXin.TOKEN_GET_TIME = System.currentTimeMillis();
                return tokenBean.access_token;
            }
        }
        return null;
    }

    static class TokenBean {
        public String access_token;
        public int expires_in;
    }
}
