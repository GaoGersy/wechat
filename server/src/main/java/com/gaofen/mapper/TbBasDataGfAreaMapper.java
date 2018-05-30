package com.gaofen.mapper;

import com.gaofen.model.SearchConditionBean;
import com.gaofen.model.TbBasDataGfArea;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbBasDataGfAreaMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBasDataGfArea record);

    int insertSelective(TbBasDataGfArea record);

    TbBasDataGfArea selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBasDataGfArea record);

    int updateByPrimaryKey(TbBasDataGfArea record);

    List<Long> getAllDataByCode(SearchConditionBean bean);

    List<Long> getAllDataProvinceOrCity(SearchConditionBean bean);
}