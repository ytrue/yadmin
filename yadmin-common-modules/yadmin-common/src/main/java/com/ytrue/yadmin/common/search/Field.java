package com.ytrue.yadmin.common.search;

import lombok.Data;

/**
 * @author ytrue
 * @date 2021/4/20 13:34
 * @description 字段
 */
@Data
public class Field {

    private String column;

    private Object value;

    private QueryMethod type;

}
