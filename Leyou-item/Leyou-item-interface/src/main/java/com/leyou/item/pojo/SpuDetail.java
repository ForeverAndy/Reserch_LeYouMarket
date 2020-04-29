package com.leyou.item.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="tb_spu_detail")
@Data
@ToString
public class SpuDetail {
    @Id
    private Long spuId; //spu的id ，没有加上自增组件
    private String description;//商品描述
//    private String specTemplate;//商品的全局规格属性   generic_spec
    private String genericSpec;
    private String specialSpec;//商品特殊规格的名称和可选择的模板
    private String packingList;//包装清单
    private String afterService;//售后服务

}