package com.leyou.item.mapper;

import com.leyou.item.pojo.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

@RegisterMapper
public interface StockMapper extends Mapper<Stock>, SelectByIdListMapper<Stock,Long> {

    @Update("UPDATE tb_stock SET stock = stock - #{num} where sku_id = #{id} AND stock >= #{num}")
    int decreaseStock(@Param("id") Long id,@Param("num") Integer num);
}
