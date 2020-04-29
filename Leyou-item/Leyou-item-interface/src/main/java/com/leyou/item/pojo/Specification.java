package com.leyou.item.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_specification")
@Data
@ToString
public class Specification {

    @Id
    private Long categoryId;
    private String specifications;


}