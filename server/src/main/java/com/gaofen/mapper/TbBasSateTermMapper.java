package com.gaofen.mapper;

import com.gaofen.model.TbBasSateTerm;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TbBasSateTermMapper {
    int deleteByPrimaryKey(BigDecimal dataid);

    int insert(TbBasSateTerm record);

    int insertSelective(TbBasSateTerm record);

    TbBasSateTerm selectByPrimaryKey(double dataid);

    int updateByPrimaryKeySelective(TbBasSateTerm record);

    int updateByPrimaryKey(TbBasSateTerm record);
}