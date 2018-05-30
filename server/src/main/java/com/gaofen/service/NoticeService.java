package com.gaofen.service;

import com.gaofen.model.TbBasNoticeGd;
import com.github.pagehelper.PageInfo;

public interface NoticeService {

    PageInfo<TbBasNoticeGd> getAllNotice(int currentPage, int pageSize);

    TbBasNoticeGd getContactUsContent();

    TbBasNoticeGd getNoticeDetail(String id);
}
