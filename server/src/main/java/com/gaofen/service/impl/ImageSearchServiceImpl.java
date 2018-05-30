package com.gaofen.service.impl;

import com.gaofen.common.dto.Page;
import com.gaofen.common.utils.SuperLogger;
import com.gaofen.mapper.TbBasDataGfAreaMapper;
import com.gaofen.mapper.TbBasMetaBlobMapper;
import com.gaofen.mapper.TbBasSatelliteTermMapper;
import com.gaofen.model.ImageData;
import com.gaofen.model.ImageDetailData;
import com.gaofen.model.SearchConditionBean;
import com.gaofen.service.IImageSearchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageSearchServiceImpl implements IImageSearchService {

    @Autowired
    TbBasMetaBlobMapper mTbBasMetaBlobMapper;

    @Autowired
    TbBasDataGfAreaMapper mTbBasDataGfAreaMapper;

    @Autowired
    TbBasSatelliteTermMapper mTbBasSatelliteTermMapper;

    @Override
    public Page getAllByCondition(SearchConditionBean bean) throws Exception {
        PageHelper.startPage(bean.getCurrentPage(), bean.getPageSize());
        getSatelliteSql(bean);
        List<ImageData> dataByPage = mTbBasSatelliteTermMapper.queryAllByCondition(bean);
        PageInfo<ImageData> pageInfo = new PageInfo<ImageData>(dataByPage);
        int pageSize = bean.getPageSize();
        int currentPage = bean.getCurrentPage();
        Page page = new Page(pageSize, bean.getStart(), currentPage);
        page.setResultCount(pageInfo.getTotal());
        page.setData(pageInfo.getList());
        return page;
    }

    @Override
    public Page getAllByCode(SearchConditionBean bean) throws Exception {
        PageHelper.startPage(bean.getCurrentPage(), bean.getPageSize());
        getSatelliteSql(bean);
        List<Long> dataByPage = mTbBasDataGfAreaMapper.getAllDataByCode(bean);
        Page page = getPageData(bean, dataByPage);
        return page;
    }

    @Override
    public List<String> getAllSatelliteType() {
        return mTbBasSatelliteTermMapper.getAllSatelliteType();
    }

    /**
     * 搜索全省和全市数据，采用模糊搜索
     */
    @Override
    public Page getAllByByProvinceOrCity(SearchConditionBean bean) throws Exception {
        PageHelper.startPage(bean.getCurrentPage(), bean.getPageSize());
        getSatelliteSql(bean);
        List<Long> dataByPage = mTbBasDataGfAreaMapper.getAllDataProvinceOrCity(bean);
        Page page = getPageData(bean, dataByPage);
        return page;
    }

    private Page getPageData(SearchConditionBean bean, List<Long> dataByPage) {
        SuperLogger.e(bean);
        List<ImageData> imageData = null;
        if (dataByPage.size() > 0) {
            bean.setDataIds(dataByPage);
            imageData = mTbBasSatelliteTermMapper.queryAllByProvinceOrCity(bean);
        }
        PageInfo<Long> pageInfo = new PageInfo<Long>(dataByPage);

        int pageSize = bean.getPageSize();
        int currentPage = bean.getCurrentPage();
        Page page = new Page(pageSize, bean.getStart(), currentPage);
        page.setResultCount(pageInfo.getTotal());
        page.setData(imageData == null ? new ArrayList() : imageData);
        return page;
    }

    /**
     * 动态拼接卫星查询sql
     * @param bean
     */
    private void getSatelliteSql(SearchConditionBean bean) {
        List<String> satelliteIds = bean.getSatelliteIds();
        StringBuffer sb = new StringBuffer(" AND(");
        StringBuffer sbCode = new StringBuffer(" AND(");
        int size = satelliteIds.size();
        for (int i = 0; i < size; i++) {
            String satelliteId = satelliteIds.get(i);
            String trim = satelliteId.trim();
            if (trim.equals("GF1")) {
                StringBuffer sbf = new StringBuffer("( SATELLITEID='GF1' AND substr(SENSORID,0,3) IN (");
                List<String> sensorIds_1 = bean.getSensorIds_1();
                int sensorIdSize = sensorIds_1.size();
                for (int j = 0; j < sensorIdSize; j++) {
                    String sensorId = sensorIds_1.get(j);
                    sbf.append("'").append(sensorId).append("'");
                    if (j < sensorIdSize - 1) {
                        sbf.append(",");
                    } else {
                        sbf.append(") ");
                    }
                }
                sbCode.append("(SATELLITEID='GF1')");
                sb.append(sbf.toString()).append(")");
            } else {
                sb.append(" ( SATELLITEID='" + satelliteId + "' ) ");
                sbCode.append(" ( SATELLITEID='" + satelliteId + "' ) ");
            }
            if (i < size - 1) {
                sb.append(" OR ");
                sbCode.append(" OR ");
            }
        }
        sb.append(")");
        sbCode.append(")");
        bean.setCodeSql(sbCode.toString());
        bean.setSatelliteSql(sb.toString());
    }

    @Override
    public ImageDetailData getImageByDataId(long dataId) throws Exception {
        return mTbBasSatelliteTermMapper.getImageByDataId(dataId);
    }

}
