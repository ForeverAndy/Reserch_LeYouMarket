package com.leyou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * spu对应的索引对象
 */
@Document(indexName = "goods", type = "docs", shards = 1, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable{
    @Id
    private Long id; // spuId
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String all; // 所有需要被搜索的信息，包含标题、分类、品牌等 ,即页面的过滤条件被选项
    @Field(type = FieldType.Keyword, index = false)
    private String subTitle;// 卖点 副标题
    //用于搜索过滤的，隐藏信息不展示给用户
    private Long brandId;// 品牌id
    private Long cid1;// 1级分类id
    private Long cid2;// 2级分类id
    private Long cid3;// 3级分类id

    private Date createTime;// 创建时间
    private List<Long> price;// 价格  一个spu包含多个sku
    @Field(type = FieldType.Keyword, index = false)
    private String skus;// sku集合的json结构,只用于展示
    private Map<String, Object> specs;// 可搜索的规格参数，key是参数名，值是参数值


}