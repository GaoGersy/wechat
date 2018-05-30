package com.gaofen.service;

import com.gaofen.common.dto.Page;
import com.gaofen.model.ImageDetailData;
import com.gaofen.model.SearchConditionBean;

import java.util.List;

public interface IImageSearchService {

//    int saveData(TbBasDataGf1 dataGf);

    Page getAllByCondition(SearchConditionBean bean) throws Exception;

    Page getAllByByProvinceOrCity(SearchConditionBean bean) throws Exception;

    ImageDetailData getImageByDataId(long dataId) throws Exception;

//    List<ImageData> getAllByConditionOnGF2(SearchConditionBean bean);
//
//    List<ImageData> getAllByConditionOnGF3(SearchConditionBean bean);
//
//    List<ImageData> getAllByConditionOnGF4(SearchConditionBean bean);

    Page getAllByCode(SearchConditionBean bean) throws Exception;

    List<String> getAllSatelliteType();
}
