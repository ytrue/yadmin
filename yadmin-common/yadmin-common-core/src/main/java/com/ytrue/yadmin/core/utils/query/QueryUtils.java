package com.ytrue.yadmin.core.utils.query;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytrue.yadmin.core.excptions.MatchException;

/**
 * @author ytrue
 * @date 2022/4/20 17:16
 * @description 条件构建
 */
public class QueryUtils<T> {

    /**
     * 日期数组的长度
     */
    private static final int ARRAY_LENGTH = 2;

    /**
     * 构建查询
     *
     * @param queryEntity
     * @return {@link LambdaQueryWrapper<T> }
     * @throws RuntimeException
     */
    public LambdaQueryWrapper<T> builder(QueryEntity<T> queryEntity) throws RuntimeException {

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        //要判断一下是否为空，不然会报空指针异常
        if (CollUtil.isNotEmpty(queryEntity.getFields())) {
            queryEntity.getFields().forEach(field -> {

                //如果是字符串并且是空就不处理
                if (field.getValue() instanceof String) {
                    if (StrUtil.hasBlank((Convert.toStr(field.getValue())))) {
                        return;
                    }
                }

                //循环处理,后续这里使用map优化掉switch
                switch (field.getType()) {
                    case eq:
                        queryWrapper.eq(true, field.getColumn(), field.getValue());
                        break;
                    case ne:
                        queryWrapper.ne(true, field.getColumn(), field.getValue());
                        break;
                    case like:
                        queryWrapper.like(true, field.getColumn(), field.getValue());
                        break;
                    case betweenDate:
                        String[] arr = Convert.toStr(field.getValue()).split(",");
                        if (ARRAY_LENGTH != arr.length) {
                            throw new MatchException("日期参数不正确");
                        }
                        queryWrapper.apply(true, "DATE_FORMAT( " + field.getColumn() + ", '%Y-%m-%d %H:%i:%s' ) " +
                                " >= DATE_FORMAT( '" + arr[0] + "', '%Y-%m-%d %H:%i:%s' )");
                        queryWrapper.apply(true, "DATE_FORMAT( " + field.getColumn() + ", '%Y-%m-%d %H:%i:%s' ) " +
                                " <= DATE_FORMAT( '" + arr[1] + "', '%Y-%m-%d %H:%i:%s' )");
                        break;
                    default:
                        throw new MatchException("非法操作");
                }
            });
        }

        if (!StrUtil.hasEmpty(queryEntity.getOrderField())) {
            queryWrapper.orderBy(true, queryEntity.isAsc(), queryEntity.getOrderField());
        }
        return queryWrapper.lambda();
    }
}
