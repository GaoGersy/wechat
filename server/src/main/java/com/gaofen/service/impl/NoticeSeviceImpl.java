package com.gaofen.service.impl;

import com.gaofen.mapper.TbBasNoticeGdMapper;
import com.gaofen.model.TbBasNoticeGd;
import com.gaofen.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeSeviceImpl implements NoticeService {

    @Autowired
    private TbBasNoticeGdMapper mTbBasNoticeGdMapper;

    @Override
    public PageInfo<TbBasNoticeGd> getAllNotice(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<TbBasNoticeGd> allNotice = mTbBasNoticeGdMapper.getAllNotice("通知公告");
        PageInfo<TbBasNoticeGd> pageInfo = new PageInfo<TbBasNoticeGd>(allNotice);
        return pageInfo;
    }

    @Override
    public TbBasNoticeGd getContactUsContent() {
        List<TbBasNoticeGd> allNotice = mTbBasNoticeGdMapper.getAllNotice("联系我们");
        return allNotice.size()>0?allNotice.get(0):null;
    }

    @Override
    public TbBasNoticeGd getNoticeDetail(String id) {
        return mTbBasNoticeGdMapper.getNoticeDetail(id);
    }
}
