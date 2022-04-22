package com.ytrue.yadmin.core.utils.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/4/20 16:09
 * @description 查询条件实体
 */
@Data
@ApiModel(value = "查询实体")
public class QueryEntity<T> {


    @ApiModelProperty(value = "当前页码，默认是1")
    private Integer currentPage = 1;

    @ApiModelProperty(value = "当前页码，默认是10")
    private Integer limit = 10;

    @ApiModelProperty(value = "字段条件")
    private List<Field> fields;

    @ApiModelProperty(value = "排序的字段")
    private String orderField;

    @ApiModelProperty(value = "是否是asc")
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
     * @return {@link QueryWrapper<T>}
     */
    public QueryWrapper<T> getQueryModel() {

        return new QueryUtils<T>().builder(this);
    }

}
