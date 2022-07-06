package com.ytrue.yadmin.tools.query.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytrue.yadmin.tools.query.utils.QueryUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ytrue
 * @date 2022/4/20 16:09
 * @description 查询条件实体
 */
@Data
public class QueryEntity<T> implements Serializable {


    private static final long serialVersionUID = 6551142650282442009L;


    /**
     * 当前页码，默认是1
     */
    private Integer currentPage = 1;

    /**
     * 当前页码，默认是10
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
     * 是否是asc
     */
    private boolean asc;

    /**
     * 组合page
     *
     * @return {@link IPage<T>}
     */
    public IPage<T> getPage() {
        IPage<T> page = new Page<>(currentPage, limit);
        if (!StrUtil.hasEmpty(orderField)) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAsc(asc);
            orderItem.setColumn(orderField);
            page.orders().add(orderItem);
        }
        return page;
    }


    /**
     * 获得 QueryWrapper
     *
     * @return {@link LambdaQueryWrapper <T>}
     */
    public LambdaQueryWrapper<T> getQueryModel() {

        return QueryUtils.builder(this.getFields());
    }

}
