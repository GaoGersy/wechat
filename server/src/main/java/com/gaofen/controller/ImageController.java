package com.gaofen.controller;

import com.gaofen.common.utils.SuperLogger;
import com.gaofen.common.utils.vcode.Captcha;
import com.gaofen.common.utils.vcode.GifCaptcha;
import com.gaofen.common.utils.vcode.SpecCaptcha;
import com.gaofen.mapper.TbBasMetaBlobMapper;
import com.gaofen.model.TbBasMetaBlobWithBLOBs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    TbBasMetaBlobMapper mTbBasMetaBlobMapper;

    /**
     * 获取验证码（Gif版本）
     */
    @RequestMapping(value = "/getGifCode", method = RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146, 42, 4);
            //输出
            ServletOutputStream out = response.getOutputStream();
            captcha.out(out);
            out.flush();
            HttpSession session = request.getSession(true);
            session.setAttribute("code", captcha.text().toLowerCase());
        } catch (Exception e) {
            SuperLogger.e(e.getMessage());
        }
    }

    /**
     * 获取验证码（jpg版本）
     */
    @RequestMapping(value = "/getJpgCode", method = RequestMethod.GET)
    public void getJPGCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");
            /**
             * jgp格式验证码
             * 宽，高，位数。
             */
            Captcha captcha = new SpecCaptcha(146, 33, 4);
            HttpSession session = request.getSession(true);

            //输出
            captcha.out(response.getOutputStream());
            //存入Session
            session.setAttribute("code", captcha.text().toLowerCase());
            Object code = session.getAttribute("code");

        } catch (Exception e) {
            SuperLogger.e(e.getMessage());
        }
    }


    /**
     * 获取影像列表的缩略图
     */
    @RequestMapping(value = "/getThumb", method = RequestMethod.GET)
    public void getThumb(HttpServletResponse response, HttpServletRequest request) {
        ServletOutputStream os = null;
        try {
            response.reset();
            response.setContentType("image/jpeg");
            request.setCharacterEncoding("gb2312");
            os = response.getOutputStream();
            long metaId = Long.parseLong(request.getParameter("metaId"));
            TbBasMetaBlobWithBLOBs blob = mTbBasMetaBlobMapper.getImageById(metaId);
            os.write(blob.getF_Thumimage());
            os.flush();
        } catch (Exception e) {
            SuperLogger.e(e.getMessage());
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
