package com.leyou.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_spu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spu {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;  //spu id
    private Long brandId; //品牌id
    private Long cid1; //1级类
    private Long cid2;//2级
    private Long cid3;//3级
    private String title;//标题
    private String subTitle;//子标题
    private Boolean saleable;//是否商家
    @JsonIgnore
    private Boolean valid;//是否有效 逻辑删除使用
    private Date createTime;//创建时间

    //JsonIgnore返回页面的时候会忽略这个字段
    @JsonIgnore
    private Date lastUpdateTime;//最后修改时间







}