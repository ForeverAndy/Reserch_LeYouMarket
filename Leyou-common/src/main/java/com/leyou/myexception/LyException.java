package com.leyou.myexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: 98050
 * @Time: 2018-11-05 16:09
 * @Feature:
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum LyException {
    /**
     * 分类信息无法找到
     */
    CATEGORY_NOT_FOUND(404,"分类不存在"),

    /**
     * 品牌信息无法找到
     */
    BRAND_NOT_FOUND(404,"品牌不存在"),
    BRAND_SAVE_ERROR(500,"新增商品失败"),
    UPLOAD_FILE_ERROR(500,"文件上传失败"),
    INVALID_FILE_TYPE(400,"无效文件类型"),
    FILE_UPLOAD_ERROR(500,"上传文件失败"),
    SPU_DETAIL_ULOAD_ERROR(500,"SPU细节上传失败"),




    ;
    private int code;
    private String msg;
}
