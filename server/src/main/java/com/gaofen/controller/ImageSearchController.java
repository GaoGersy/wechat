package com.gaofen.controller;

import com.gaofen.common.dto.Page;
import com.gaofen.common.dto.Result;
import com.gaofen.common.utils.GsonQuick;
import com.gaofen.model.ImageDetailData;
import com.gaofen.model.SearchConditionBean;
import com.gaofen.service.IImageSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
public class ImageSearchController {

    @Autowired
    IImageSearchService mIImageSearchService;

    @RequestMapping(value = "/getImageList", method = RequestMethod.POST)
    public Result getImageList(@RequestBody String paramJson) {
        Result result = Result.getInstance();
        try {
            SearchConditionBean searchConditionBean = GsonQuick.fromJsontoBean(paramJson, SearchConditionBean.class);
            Page page = null;
            Boolean isAllCity = searchConditionBean.isAllCity();
            Boolean isAllProvince = searchConditionBean.isAllProvince();
            String code = searchConditionBean.getCode();
            if (isAllCity != null || isAllProvince != null) {
                if (isAllProvince != null && isAllProvince) {
                    searchConditionBean.setCode(code.substring(0, 2) + "%");
                } else {
                    searchConditionBean.setCode(code.substring(0, 4) + "%");
                }
                page = mIImageSearchService.getAllByByProvinceOrCity(searchConditionBean);
            } else {
                if (searchConditionBean.isArea()) {
                    page = mIImageSearchService.getAllByCode(searchConditionBean);
                } else {
                    page = mIImageSearchService.getAllByCondition(searchConditionBean);
                }
            }
            return result.success(page);
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/getImageByDataId", method = RequestMethod.GET)
    public Result getImageByDataId(HttpServletRequest req) {
        Result result = Result.getInstance();
        try {
            long dataId = Long.parseLong(req.getParameter("dataId"));
            ImageDetailData image = mIImageSearchService.getImageByDataId(dataId);
            if (image != null) {
                return result.success(image);
            }
            return result.failure("没有找到相关数据");
        } catch (Exception e) {
            return result.exception(e);
        }
    }

    @RequestMapping(value = "/getAllSatelliteType", method = RequestMethod.GET)
    public Result getAllSatelliteType() {
        Result result = Result.getInstance();
        try {
            List<String> image = mIImageSearchService.getAllSatelliteType();
            if (image != null) {
                return result.success(image);
            }
            return result.failure("没有找到相关数据");
        } catch (Exception e) {
            return result.exception(e);
        }
    }

}
