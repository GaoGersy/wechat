package com.gaofen.controller;

import com.google.gson.Gson;

import com.alibaba.fastjson.JSON;
import com.gaofen.common.dto.Result;
import com.gaofen.model.PageMode;
import com.gaofen.model.ProductOrder;
import com.gaofen.model.TbBasSateTerm;
import com.gaofen.model.TbProShopOrdMeta;
import com.gaofen.service.order.IProductOrderService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by feng on 2017/9/5.
 */
@Controller
@RequestMapping("/order")
@ResponseBody
public class OrderContoller {

    @Autowired
    IProductOrderService IProductOrderService;

    @Autowired
    IProductOrderService mProductOrderService;

    @RequestMapping(value = "/getOrderByUserName", method = RequestMethod.POST)
    public Result getOrderByUserName(@RequestBody @Validated String jsonParams
            , HttpServletRequest req, HttpServletResponse res) {
        Result result = Result.getInstance();
        Gson gson = new Gson();


        if (jsonParams == null || jsonParams.length() == 0) {
            result.setResult(Result.ERROR);
            result.setError("参数不能为空");
            return result;
        }
        ProductOrder productOrder = gson.fromJson(jsonParams, ProductOrder.class);
        String token = (String) JSON.parseObject(jsonParams).get("token");
/*        if(JavaWebToken.parserJavaWebToken(token) != null){*/
        PageInfo<ProductOrder> productOrders = IProductOrderService.quryProductOrderByUserId(productOrder.getuserId(), productOrder.getORDERDATE(), (int) JSON.parseObject(jsonParams).get("currentPage"), (int) JSON.parseObject(jsonParams).get("pageSize"));

        PageMode<ProductOrder> pageMode = new PageMode<>();
        pageMode.setList(productOrders.getList());
        pageMode.setPageSize(productOrders.getPageSize());
        pageMode.setCurrentPage(productOrders.getPageNum());
        pageMode.setPageCount(productOrders.getPages());

        result.success(pageMode);
    /*    }else {

            result.setError("token不合法或者过期");

        }
*/


        return result;
    }


    @RequestMapping(value = "/getChildOrderByOrderId", method = RequestMethod.POST)
    public Result getBasSatelitrTerm(@RequestBody @Validated String jsonParams
            , HttpServletRequest req, HttpServletResponse res) {
        Result result = Result.getInstance();
        Gson gson = new Gson();
        if (jsonParams == null || jsonParams.length() == 0) {
            result.setResult(Result.ERROR);
            result.setError("参数不能为空");
            return result;
        }

        String token = (String) JSON.parseObject(jsonParams).get("token");
 /*       if(JavaWebToken.parserJavaWebToken(token) != null){*/
        TbProShopOrdMeta tbProShopOrdMeta = gson.fromJson(jsonParams, TbProShopOrdMeta.class);
        PageInfo<TbProShopOrdMeta> tbProShopOrdMetaList = IProductOrderService.quryChildOrderByOrderId(tbProShopOrdMeta.getorderId(), (int) JSON.parseObject(jsonParams).get("currentPage"), (int) JSON.parseObject(jsonParams).get("pageSize"));
        PageMode<TbProShopOrdMeta> pageMode = new PageMode<>();
        pageMode.setList(tbProShopOrdMetaList.getList());
        pageMode.setPageSize(tbProShopOrdMetaList.getPageSize());
        pageMode.setCurrentPage(tbProShopOrdMetaList.getPageNum());

        pageMode.setPageCount(tbProShopOrdMetaList.getPages());
        result.success(pageMode);
      /*  }else {
            result.setError("token不合法或者过期");
        }*/


        return result;
    }

    @RequestMapping(value = "/getBasSateliteTerm", method = RequestMethod.POST)
    public Result getChildOrderName(@RequestBody @Validated String jsonParams
            , HttpServletRequest req, HttpServletResponse res) {
        Result result = Result.getInstance();
        Gson gson = new Gson();
        if (jsonParams == null || jsonParams.length() == 0) {
            result.setResult(Result.ERROR);
            result.setError("参数不能为空");
            return result;
        }
     /*   String token = (String) JSON.parseObject(jsonParams).get("token");*/
/*        if(JavaWebToken.parserJavaWebToken(token) != null){*/
        TbBasSateTerm basSatelliteTerm = gson.fromJson(jsonParams, TbBasSateTerm.class);
       /*     JavaWebToken.parserJavaWebToken(token).get("");*/
        TbBasSateTerm resultTbMeta = IProductOrderService.quryBasSatelliteTermByDataId(basSatelliteTerm.getdataid());
        if (resultTbMeta != null) {
            result.success(resultTbMeta);
        }
    /*    }else {
            result.setError("token不合法或者过期");
        }

*/

        return result;
    }

    @RequestMapping(value = "/cancelOrderById", method = RequestMethod.GET)
    public Result cancelOrderById(HttpServletRequest req) {
        Result result = Result.getInstance();
        try {
            long id = Long.parseLong(req.getParameter("id"));
            boolean cancelOrder = mProductOrderService.cancelOrderById(id);
            if (cancelOrder){
                return result.success("取消成功");
            }
            return result.failure("取消失败");
        }catch (Exception e){
            return result.exception(e);
        }
    }

}
