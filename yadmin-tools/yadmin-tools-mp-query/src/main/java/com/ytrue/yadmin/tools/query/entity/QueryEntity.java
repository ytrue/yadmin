package com.ytrue.yadmin.tools.query.entity;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytrue.yadmin.tools.query.enums.QueryMethod;
import com.ytrue.yadmin.tools.query.utils.EmptyUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
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


    private static final HashMap<QueryMethod, AppendQueryWrapper> APPEND_QUERY_WRAPPER_MAP = new HashMap<>();

    static {
        APPEND_QUERY_WRAPPER_MAP.put(QueryMethod.eq, (queryWrapper, field) -> queryWrapper.eq(field.getColumn(), field.getValue()));
        APPEND_QUERY_WRAPPER_MAP.put(QueryMethod.ne, (queryWrapper, field) -> queryWrapper.ne(field.getColumn(), field.getValue()));
        APPEND_QUERY_WRAPPER_MAP.put(QueryMethod.like, (queryWrapper, field) -> queryWrapper.like(field.getColumn(), field.getValue()));
        APPEND_QUERY_WRAPPER_MAP.put(QueryMethod.likeLeft, (queryWrapper, field) -> queryWrapper.likeLeft(field.getColumn(), field.getValue()));
        APPEND_QUERY_WRAPPER_MAP.put(QueryMethod.likeRight, (queryWrapper, field) -> queryWrapper.likeRight(field.getColumn(), field.getValue()));
    }

    /**
     * 组合page
     *
     * @return {@link IPage<T>}
     */
    public IPage<T> getPage() {
        return new Page<>(currentPage, limit);
    }


    /**
     * 获得 QueryWrapper
     *
     * @return {@link LambdaQueryWrapper <T>}
     */
    public LambdaQueryWrapper<T> getQueryModel() {
        return queryWrapperBuilder(this.getFields()).lambda();
    }

    /**
     * QueryWrapper 构建
     *
     * @param fields
     * @return
     */
    public QueryWrapper<T> queryWrapperBuilder(List<Field> fields) {

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        //要判断一下是否为空，不然会报空指针异常
        if (CollUtil.isNotEmpty(fields)) {
            fields.forEach(field -> {
                //如果是空就不处理
                if (EmptyUtils.isEmpty(field.getValue())) {
                    return;
                }
                // 进行匹配
                AppendQueryWrapper appendQueryWrapper = APPEND_QUERY_WRAPPER_MAP.get(field.getCondition());
                Assert.notNull(appendQueryWrapper, "类型匹配错误");
                appendQueryWrapper.append(queryWrapper, field);
            });
        }

        if (!StrUtil.hasEmpty(orderField)) {
            queryWrapper.orderBy(true, asc, orderField);
        }
        return queryWrapper;
    }


    /**
     * 查询包装器追加
     */
    @FunctionalInterface
    private interface AppendQueryWrapper {
        /**
         * 追加 QueryWrapper
         *
         * @param queryWrapper
         * @param field
         */
        void append(QueryWrapper<?> queryWrapper, Field field);
    }
}
