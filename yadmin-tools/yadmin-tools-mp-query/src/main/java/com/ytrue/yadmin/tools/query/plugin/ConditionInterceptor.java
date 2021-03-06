package com.ytrue.yadmin.tools.query.plugin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.ytrue.yadmin.tools.query.entity.Field;
import com.ytrue.yadmin.tools.query.entity.Fields;
import com.ytrue.yadmin.tools.query.enums.QueryMethod;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.io.StringReader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ytrue
 * @date 2022/7/2 11:59
 * @description ConditionInterceptor 条件拦截
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class ConditionInterceptor implements Interceptor {

    private final static String TARGET_DELEGATE_BOUNDS_SQL = "target.delegate.boundSql.sql";
    private final static String TARGET_DELEGATE_PARAMETERIZABLE_PARAMETERISED = "target.delegate.parameterHandler.parameterObject";
    private static final HashMap<QueryMethod, AppendCondition> APPEND_CONDITION_MAP = new HashMap<>();

    static {
        APPEND_CONDITION_MAP.put(QueryMethod.eq, (stringBuffer, field) -> splicingString(stringBuffer, field, "="));
        APPEND_CONDITION_MAP.put(QueryMethod.ne, (stringBuffer, field) -> splicingString(stringBuffer, field, "!="));
        APPEND_CONDITION_MAP.put(QueryMethod.like, (stringBuffer, field) -> splicingString(stringBuffer, field, "LIKE", "'%", "%'"));
        APPEND_CONDITION_MAP.put(QueryMethod.likeLeft, (stringBuffer, field) -> splicingString(stringBuffer, field, "LIKE", "'%", "'"));
        APPEND_CONDITION_MAP.put(QueryMethod.likeRight, (stringBuffer, field) -> splicingString(stringBuffer, field, "LIKE", "'", "%'"));
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 通过反射处理
        MetaObject metaObject = SystemMetaObject.forObject(invocation);
        // 获取执行的sql语句
        String sql = (String) metaObject.getValue(TARGET_DELEGATE_BOUNDS_SQL);

        //直接通过DAO方法的 Fields参数
        Object value = metaObject.getValue(TARGET_DELEGATE_PARAMETERIZABLE_PARAMETERISED);
        HashSet<Fields> fieldsSet = new HashSet<>();

        // 如果是多个参数 这里就要处理
        if (value instanceof MapperMethod.ParamMap) {
            ((MapperMethod.ParamMap) value).forEach((k, v) -> {
                if (v instanceof Fields) {
                    fieldsSet.add((Fields) v);
                }
            });
        }

        //只有一个参数，直接加入就行
        if (value instanceof Fields) {
            fieldsSet.add((Fields) value);
        }
        // 获取全部的Field
        HashSet<Field> fieldList = new HashSet<>();
        fieldsSet.forEach(fields -> fieldList.addAll(fields.getFields()));

        String newSql = addWhereCondition(sql, getCondExpression(fieldList));
        //如果是原来的sql 那就不用处理了
        if (!sql.equals(newSql)) {
            // 替换之前的sql
            metaObject.setValue(TARGET_DELEGATE_BOUNDS_SQL, newSql);
        }

        // 放行
        return invocation.proceed();
    }


    /**
     * 追加条件
     *
     * @param sql
     * @param appendWhereCondition
     * @return
     * @throws JSQLParserException
     */
    private String addWhereCondition(String sql, String appendWhereCondition) throws JSQLParserException {

        if (StrUtil.isBlank(appendWhereCondition)) {
            return sql;
        }

        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Statement statement = parserManager.parse(new StringReader(sql));

        if (statement instanceof Select) {
            return parseSelect((Select) statement, appendWhereCondition);
        } else if (statement instanceof Update) {
            return parseUpdate((Update) statement, appendWhereCondition);
        } else if (statement instanceof Delete) {
            return parseDelete((Delete) statement, appendWhereCondition);
        } else {
            return sql;
        }
    }


    /**
     * 针对 Delete 类型的处理
     *
     * @param delete
     * @param whereString
     * @return
     * @throws JSQLParserException
     */
    private String parseDelete(Delete delete, String whereString) throws JSQLParserException {

        final Expression expression = delete.getWhere();
        final Expression envCondition = CCJSqlParserUtil.parseCondExpression(whereString);
        if (expression == null) {
            delete.setWhere(envCondition);
        } else {
            AndExpression andExpression = new AndExpression(expression, envCondition);
            delete.setWhere(andExpression);
        }

        return delete.toString();
    }

    /**
     * 针对 Update 类型的处理
     *
     * @param update
     * @param whereString
     * @return
     * @throws JSQLParserException
     */
    private String parseUpdate(Update update, String whereString) throws JSQLParserException {

        final Expression expression = update.getWhere();
        final Expression envCondition = CCJSqlParserUtil.parseCondExpression(whereString);
        if (expression == null) {
            update.setWhere(envCondition);
        } else {
            AndExpression andExpression = new AndExpression(expression, envCondition);
            update.setWhere(andExpression);
        }

        return update.toString();
    }


    /**
     * 针对 Select 类型的处理
     *
     * @param select
     * @param whereString
     * @return
     */
    private String parseSelect(Select select, String whereString) throws JSQLParserException {
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        final Expression expression = plainSelect.getWhere();
        final Expression envCondition = CCJSqlParserUtil.parseCondExpression(whereString);

        if (expression == null) {
            plainSelect.setWhere(envCondition);
        } else {
            AndExpression andExpression = new AndExpression(expression, envCondition);
            plainSelect.setWhere(andExpression);
        }

        return select.toString();
    }


    /**
     * 处理 fields 转成字符串
     *
     * @param fields
     * @return
     */
    private String getCondExpression(HashSet<Field> fields) {

        //要判断一下是否为空，不然会报空指针异常
        if (CollUtil.isEmpty(fields)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();

        fields.forEach(field -> {
            // 进行匹配
            AppendCondition appendCondition = APPEND_CONDITION_MAP.get(field.getCondition());
            Assert.notNull(appendCondition, "类型匹配错误");
            appendCondition.append(stringBuffer, field);

        });
        //删除前面的 and
        String s = stringBuffer.toString();
        return s.substring(5);
    }


    /**
     * 条件拼接
     */
    @FunctionalInterface
    private interface AppendCondition {
        /**
         * 追加条件
         *
         * @param stringBuffer
         * @param field
         */
        void append(StringBuffer stringBuffer, Field field);
    }

    /**
     * 拼接字符串
     *
     * @param stringBuffer
     * @param field
     * @param type
     */
    private static void splicingString(StringBuffer stringBuffer, Field field, String type) {
        splicingString(stringBuffer, field, type, "", "");
    }

    /**
     * 拼接字符串
     *
     * @param stringBuffer
     * @param field
     * @param type
     * @param prefixString
     * @param suffixString
     */
    private static void splicingString(StringBuffer stringBuffer, Field field, String type, String prefixString, String suffixString) {
        stringBuffer.append(" and ");
        stringBuffer.append(field.getColumn());
        stringBuffer.append(" ");
        stringBuffer.append(type);
        stringBuffer.append(" ");
        stringBuffer.append(prefixString).append(field.getValue()).append(suffixString);
    }
}
