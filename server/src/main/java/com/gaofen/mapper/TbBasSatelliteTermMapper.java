package com.gaofen.mapper;

import com.gaofen.model.ImageData;
import com.gaofen.model.ImageDetailData;
import com.gaofen.model.SearchConditionBean;
import com.gaofen.model.TbBasSatelliteTerm;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbBasSatelliteTermMapper {

    int insert(TbBasSatelliteTerm record);

    int insertSelective(TbBasSatelliteTerm record);

    List<ImageData> queryAllByCondition(SearchConditionBean bean);

    List<ImageData> queryAllByProvinceOrCity(SearchConditionBean bean);

    ImageDetailData getImageByDataId(long dataId);

    List<String> getAllSatelliteType();

}