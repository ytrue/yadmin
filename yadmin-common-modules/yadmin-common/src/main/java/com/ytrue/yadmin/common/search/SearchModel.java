package com.ytrue.yadmin.common.search;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytrue.yadmin.common.exeption.YadminException;
import lombok.Data;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/20 13:36
 * @description 核心
 */
@Data
public class SearchModel<T> {
    /**
     * 当前页码，默认是1
     */
    private Integer currentPage = 1;
    /**
     * 每页取多少条，默认是10
     */
    private Integer limit = 10;
    /**
     * 字段条件
     */
    private List<Field> fields;
    /**
     * 排序的字段
     */
    private String orderField;
    /**
     * 什么风格的
     */
    private boolean isAsc;


    /**
     * 组合page
     *
     * @return
     */
    public IPage<T> getPage() {
        IPage<T> page = new Page<>(currentPage, limit);
        if (!StrUtil.hasEmpty(orderField)) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAsc(isAsc);
            orderItem.setColumn(orderField);
            page.orders().add(orderItem);
        }
        return page;
    }

    /**
     * 获得 QueryWrapper
     *
     * @return
     */
    public QueryWrapper<T> getQueryModel() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        //要判断一下是否为空，不然会报空指针异常
        if (CollUtil.isNotEmpty(fields)) {
            fields.forEach(field -> {
                switch (field.getType()) {
                    case eq:
                        queryWrapper.eq(true, field.getColumn(), field.getValue());
                        break;
                    case like:
                        queryWrapper.like(true, field.getColumn(), field.getValue());
                        break;
                    default:
                        throw new YadminException("非法操作");
                }
            });
        }
        if (!StrUtil.hasEmpty(orderField)) {
            queryWrapper.orderBy(true, isAsc, orderField);
        }
        return queryWrapper;
    }
}
