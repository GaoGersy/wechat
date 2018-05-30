package com.gaofen.mapper;

import com.gaofen.model.ProductOrder;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductOrderMapper {
    int deleteByPrimaryKey(long ID);

    int insert(ProductOrder record);

    int insertSelective(ProductOrder record);

    ProductOrder selectByPrimaryKey(BigDecimal ID);

    int updateByPrimaryKeySelective(ProductOrder record);

    int updateByPrimaryKey(ProductOrder record);

    List<ProductOrder> queryProductByUserId(@Param("userId") String userId, @Param("orderTime") Date orderTime);
}