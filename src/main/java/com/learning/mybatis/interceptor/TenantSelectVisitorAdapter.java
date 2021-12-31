package com.learning.mybatis.interceptor;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectVisitorAdapter;

public class TenantSelectVisitorAdapter extends SelectVisitorAdapter {

    @Override
    public void visit(PlainSelect plainSelect) {
        Expression originWhere = plainSelect.getWhere();
        Expression appendExpression = DBExpressionUtils.appendMidExpression(originWhere);
        plainSelect.setWhere(appendExpression);
    }
}
