package com.gaofen.mapper;

import com.gaofen.model.TbBasMetaBlobWithBLOBs;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbBasMetaBlobMapper {
    int insert(TbBasMetaBlobWithBLOBs record);

    int insertSelective(TbBasMetaBlobWithBLOBs record);

    TbBasMetaBlobWithBLOBs getImageById(@Param("dataId") long dataId);
}