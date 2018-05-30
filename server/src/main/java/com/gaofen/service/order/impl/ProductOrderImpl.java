package com.gaofen.service.order.impl;

import com.gaofen.mapper.ProductOrderMapper;
import com.gaofen.mapper.TbBasSateTermMapper;
import com.gaofen.mapper.TbProShopOrdMetaMapper;
import com.gaofen.model.ProductOrder;
import com.gaofen.model.TbBasSateTerm;
import com.gaofen.model.TbProShopOrdMeta;
import com.gaofen.service.order.IProductOrderService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by feng on 2017/9/5.
 */
@Service
public class ProductOrderImpl implements IProductOrderService {
    @Autowired
    ProductOrderMapper productOrderMapper;
    @Autowired
    TbProShopOrdMetaMapper tbProShopOrdMetaMapper;
    @Autowired
    TbBasSateTermMapper basSatelliteTermMapper;

/*    public List<ProductOrder> quryProductOrderByUserId(String username) {
||||||| .r25
    public List<ProductOrder> quryProductOrderByUserId(String username) {
=======
    public List<ProductOrder> quryProductOrderByUserId(String username,Date orderTime) {
>>>>>>> .r29

        return productOrderMapper.queryProductByUserId(username,orderTime);
    }*/
@Override
    public PageInfo<ProductOrder> quryProductOrderByUserId(String username ,Date orderTime,int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<ProductOrder> list = productOrderMapper.queryProductByUserId(username,orderTime);
        PageInfo<ProductOrder> pageInfo =new PageInfo<ProductOrder>(list);




        return  pageInfo;
    }

    @Override
    public  PageInfo<TbProShopOrdMeta> quryChildOrderByOrderId(long  orderId,int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<TbProShopOrdMeta> list = tbProShopOrdMetaMapper.selectByOrderId(orderId);
        PageInfo<TbProShopOrdMeta> pageInfo =new PageInfo<TbProShopOrdMeta>(list);




        return pageInfo;
    }

    @Override
    public TbBasSateTerm quryBasSatelliteTermByDataId(double dataId) {
        return   basSatelliteTermMapper.selectByPrimaryKey(dataId) ;
    }

    @Override
    public boolean cancelOrderById(long id) {
        int i = productOrderMapper.deleteByPrimaryKey(id);
        return i>0;
    }
}
