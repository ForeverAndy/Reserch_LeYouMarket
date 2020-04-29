package com.leyou.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum  ExceptionEnum {

    PRICE_CANNOT_BE_NULL(400,"价格不能为空！")
    ;
    private int code;
    private String msg;

}
