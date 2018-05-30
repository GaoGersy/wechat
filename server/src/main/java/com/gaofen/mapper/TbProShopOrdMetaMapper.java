package com.gaofen.mapper;

import com.gaofen.model.TbProShopOrdMeta;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TbProShopOrdMetaMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TbProShopOrdMeta record);

    int insertSelective(TbProShopOrdMeta record);

    TbProShopOrdMeta selectByPrimaryKey(BigDecimal id);

    List<TbProShopOrdMeta> selectByOrderId(long id);

    int updateByPrimaryKeySelective(TbProShopOrdMeta record);

    int updateByPrimaryKey(TbProShopOrdMeta record);
}