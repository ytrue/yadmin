package com.ytrue.yadmin.common.search;

import lombok.Data;

/**
 * @author ytrue
 * @date 2021/4/20 13:34
 * @description 字段
 */
@Data
public class Field {

    /**
     * 字段
     */
    private String column;

    /**
     * 内容
     */
    private String value;

    /**
     * 类型
     */
    private QueryMethod type;

}
