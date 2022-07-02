package com.ytrue.yadmin.core.utils.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/7/2 13:01
 * @description Fields
 *
 * 这里要
 */
@Data
@EqualsAndHashCode
public class Fields {
    private List<Field> fields;
}
