package com.leyou.item.bo;

import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author: 98050
 * Time: 2018-08-14 22:10
 * Feature:
 *  //Transient别把这个当数据库字段 javax.persistence下的
 * 一旦变量被transient修饰,变量将不再是对象持久化的一部分,该变量内容在序列化后无法获得访问
 * 表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性.如果一个属性并非数据库表的字段映射,就务必将其标示为@Transient
 * ORM框架默认其注解为@Basic
 */

@Data
public class SpuBo extends Spu {

    @Transient
    private String cname;//商品分类名称

    @Transient
    private String bname;//品牌名称

    @Transient
    private SpuDetail spuDetail;//商品详情

    @Transient
    private List<Sku> skus;//sku列表

    public SpuBo() {
    }

    //queryGoodsById专门用的
    public SpuBo(Long id,Long brandId, Long cid1, Long cid2,Long cid3, String title, String subTitle, Boolean saleable, Boolean valid, Date createTime, Date lastUpdateTime) {
        super(id,brandId, cid1, cid2, cid3, title, subTitle, saleable, valid, createTime, lastUpdateTime);
        this.cname = cname;
        this.bname = bname;
    }
}
