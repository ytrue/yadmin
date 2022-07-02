package com.ytrue.yadmin.core.utils.query;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ytrue.yadmin.core.excptions.MatchException;
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
import java.util.HashSet;

/**
 * @author ytrue
 * @date 2022/7/2 11:59
 * @description CondInterceptor 条件拦截
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class CondInterceptor implements Interceptor {

    private final static String TARGET_DELEGATE_BOUNDSQL_SQL = "target.delegate.boundSql.sql";
    private final static String TARGET_DELEGATE_PARAMETERHANDLER_PARAMETEROBJECT = "target.delegate.parameterHandler.parameterObject";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 通过反射处理
        MetaObject metaObject = SystemMetaObject.forObject(invocation);
        // 获取执行的sql语句
        String sql = (String) metaObject.getValue(TARGET_DELEGATE_BOUNDSQL_SQL);

        //直接通过DAO方法的 Fields参数
        Object value = metaObject.getValue(TARGET_DELEGATE_PARAMETERHANDLER_PARAMETEROBJECT);
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
            metaObject.setValue(TARGET_DELEGATE_BOUNDSQL_SQL, newSql);
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
            //循环处理,后续这里使用map优化掉switch
            switch (field.getType()) {
                case eq:
                    splicingString(stringBuffer, field, "=");
                    break;
                case ne:
                    splicingString(stringBuffer, field, "!=");
                    break;
                case like:
                    splicingString(stringBuffer, field, "LIKE", "'%", "%'");
                    break;
                case likeLeft:
                    splicingString(stringBuffer, field, "LIKE", "'%", "'");
                    break;
                case likeRight:
                    splicingString(stringBuffer, field, "LIKE", "'", "%'");
                    break;
                default:
                    throw new MatchException("非法操作");
            }
        });
        //删除前面的 and
        String s = stringBuffer.toString();
        return s.substring(5);
    }

    /**
     * 拼接字符串
     *
     * @param stringBuffer
     * @param field
     * @param type
     */
    private void splicingString(StringBuffer stringBuffer, Field field, String type) {
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
    private void splicingString(StringBuffer stringBuffer, Field field, String type, String prefixString, String suffixString) {
        stringBuffer.append(" and ");
        stringBuffer.append(field.getColumn());
        stringBuffer.append(" ");
        stringBuffer.append(type);
        stringBuffer.append(" ");
        stringBuffer.append(prefixString).append(field.getValue()).append(suffixString);
    }
}
