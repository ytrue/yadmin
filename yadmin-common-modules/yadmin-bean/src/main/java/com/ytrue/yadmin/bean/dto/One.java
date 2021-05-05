package com.ytrue.yadmin.bean.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/5/4 18:01
 * @description One
 */
@Data
public class One {

    private String router;

    private List<Two> children;
}
