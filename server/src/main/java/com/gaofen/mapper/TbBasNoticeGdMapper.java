package com.gaofen.mapper;

import com.gaofen.model.TbBasNoticeGd;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbBasNoticeGdMapper {

    int insert(TbBasNoticeGd record);

    int insertSelective(TbBasNoticeGd record);

    List<TbBasNoticeGd> getAllNotice(@Param("levelTwo")String levelTwo);

    TbBasNoticeGd getNoticeDetail(@Param("id")String id);
}