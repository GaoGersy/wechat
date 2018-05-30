package com.gaofen.service.order;

import com.gaofen.model.ProductOrder;
import com.gaofen.model.TbBasSateTerm;
import com.gaofen.model.TbProShopOrdMeta;
import com.github.pagehelper.PageInfo;

import java.util.Date;

/**
 * Created by feng on 2017/9/5.
 */
public interface IProductOrderService {

    PageInfo<ProductOrder> quryProductOrderByUserId(String userName,Date orderTime,int pageNo, int pageSize);

    public  PageInfo<TbProShopOrdMeta> quryChildOrderByOrderId(long  orderId,int pageNo, int pageSize);

    TbBasSateTerm quryBasSatelliteTermByDataId(double dataId);

    boolean cancelOrderById(long id);
}
