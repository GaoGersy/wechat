package com.gaofen.controller;

import com.gaofen.common.dto.Result;
import com.gaofen.model.TbBasNoticeGd;
import com.gaofen.service.NoticeService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value ="/notice")
public class NoticeController {

    @Autowired
    private NoticeService mNoticeService;

    @RequestMapping(value = "/getNoticeDetail",method = RequestMethod.GET)
    public Result getNoticeDetail(String id){
        Result result = Result.getInstance();
        try {
            TbBasNoticeGd noticeDetail = mNoticeService.getNoticeDetail(id);
            return result.success(noticeDetail);
        }catch (Exception e){
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/getAllNotice",method = RequestMethod.GET)
    public Result getAllNotice(int currentPage,int pageSize){
        Result result = Result.getInstance();
        try {
            PageInfo<TbBasNoticeGd> allNotice = mNoticeService.getAllNotice(currentPage, pageSize);
            return result.success(allNotice);
        }catch (Exception e){
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/getContactUsContent",method = RequestMethod.GET)
    public Result getAllNotice(){
        Result result = Result.getInstance();
        try {
            TbBasNoticeGd allNotice = mNoticeService.getContactUsContent();
            return result.success(allNotice);
        }catch (Exception e){
            return result.exception(e);
        }
    }
}
