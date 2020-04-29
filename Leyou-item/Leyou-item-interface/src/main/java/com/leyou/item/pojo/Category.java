package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_category")
@Data
public class Category implements Serializable {
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	private String name;
	private Long parentId;
	private Boolean isParent;
	private Integer sort; //排名越小越靠前

}