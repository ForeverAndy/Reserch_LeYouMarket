package com.leyou.paremeter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: 98050
 * Time: 2018-08-08 11:38
 * Feature:
 */
@Data
@AllArgsConstructor
@ToString
public class BrandQueryByPageParameter {

    /*
    *   - page：当前页，int
        - rows：每页大小，int
        - sortBy：排序字段，String
        - desc：是否为降序，boolean
        - key：搜索关键词，String
    * */

    private Integer page;
    private Integer rows;
    private String sortBy;
    private Boolean desc;
    private String key;


    public BrandQueryByPageParameter(){
        super();
    }


}
