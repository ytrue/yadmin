package com.ytrue.yadmin.tools.query.entity;

import com.ytrue.yadmin.tools.query.enums.QueryMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author ytrue
 * @date 2022/4/20 16:06
 * @description 字段
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class Field {
    /**
     * 字段
     */
    private String column;

    /**
     * 内容 ,一般是 string 和 int, array
     */
    private Object value;

    /**
     * 条件
     */
    private QueryMethod condition;
}
