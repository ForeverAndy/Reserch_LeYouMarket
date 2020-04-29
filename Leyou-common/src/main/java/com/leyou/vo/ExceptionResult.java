package com.leyou.vo;

import com.leyou.myexception.LyException;
import lombok.Data;

/**
 * 请求响应体的vo对象
 * 放入ResponseEntity中的body中返回前端
 */
@Data
public class ExceptionResult {

    private Integer statusCode;
    private String msg;
    private Long timeStamp;

    public ExceptionResult(LyException e) {
        statusCode = e.getCode();
        msg = e.getMsg();
        timeStamp = System.currentTimeMillis();
    }

}
