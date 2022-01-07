package com.learning.mybatis.interceptor;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.apache.ibatis.transaction.TransactionException;

import java.util.Objects;

public class DBExpressionUtils {

    private DBExpressionUtils() {
        throw new UnsupportedOperationException();
    }

    public static Expression appendMidExpression(Expression originWhere) {
        Expression appendExpression = acquireMidExpression();
        return Objects.nonNull(originWhere) ? new AndExpression(originWhere, appendExpression) : appendExpression;
    }

    private static Expression acquireMidExpression() {
        String appendSql = "tenant_id = '" + forTencent() + "'";
        try {
            return CCJSqlParserUtil.parseCondExpression(appendSql);
        } catch (JSQLParserException e) {
            throw new TransactionException("");
        }
    }


    private static String forTencent() {
        return "1";
    }
}
