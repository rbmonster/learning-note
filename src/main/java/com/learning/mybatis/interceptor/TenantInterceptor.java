package com.learning.mybatis.interceptor;

import lombok.extern.log4j.Log4j2;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
    })
@Log4j2
public class TenantInterceptor implements Interceptor {

    private final TenantSelectVisitorAdapter midSelectVisitorAdapter = new TenantSelectVisitorAdapter();

    private static final String DOT = ".";

    public Object intercept(Invocation invocation) throws Throwable {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            return invocation.proceed();
        }
        Object target = invocation.getTarget();
        Object[] args = invocation.getArgs();
        if (target instanceof Executor) {
            MappedStatement mappedStatement = ((MappedStatement)args[0]);
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            if (sqlCommandType != SqlCommandType.SELECT|| !matchTenantAnnotation(mappedStatement)) {
                return invocation.proceed();
            }
            Executor executor = (Executor)target;
            Object parameter = args[1];
            RowBounds rowBounds = (RowBounds)args[2];
            ResultHandler resultHandler = (ResultHandler)args[3];
            BoundSql boundSql;
            if (args.length == 4) {
                boundSql = mappedStatement.getBoundSql(parameter);
            } else {
                // for proxy executor invoke
                boundSql = (BoundSql)args[5];
            }
            Statement statement = CCJSqlParserUtil.parse(boundSql.getSql());
            Select select = (Select) statement;
            SelectBody selectBody = select.getSelectBody();
            selectBody.accept(midSelectVisitorAdapter);
            MetaObject metaObject = SystemMetaObject.forObject(boundSql);
            metaObject.setValue("sql", select.toString());
            CacheKey cacheKey = executor.createCacheKey(mappedStatement, parameter, rowBounds, boundSql);
            return executor.query(mappedStatement, parameter, rowBounds, resultHandler, cacheKey, boundSql);
        }
        if (target instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) target;
            BoundSql boundSql = statementHandler.getBoundSql();
            Statement statement = CCJSqlParserUtil.parse(boundSql.getSql());
            MappedStatement mappedStatement = getMappedStatement(statementHandler);
            if (mappedStatement.getSqlCommandType() != SqlCommandType.UPDATE
                    || !matchTenantAnnotation(mappedStatement)) {
                return invocation.proceed();
            }
            if (statement instanceof Update) {
                Update update = (Update)statement;
                update.setWhere(DBExpressionUtils.appendMidExpression(update.getWhere()));
                MetaObject metaObject = SystemMetaObject.forObject(boundSql);
                metaObject.setValue("sql", update.toString());
            }
        }
        return invocation.proceed();
    }

    private boolean matchTenantAnnotation(MappedStatement mappedStatement) throws ClassNotFoundException {
        String id = mappedStatement.getId();
        String className = id.substring(0, id.lastIndexOf(DOT));
        Class<?> clazz = Class.forName(className);
        TenantAppend annotation = clazz.getAnnotation(TenantAppend.class);
        if (Objects.isNull(annotation)) {
            return false;
        }
        return Arrays.stream(annotation.sqlType()).anyMatch(sqlCommandType -> sqlCommandType == mappedStatement.getSqlCommandType());
    }

    private MappedStatement getMappedStatement(StatementHandler statementHandler) {
        MetaObject object = SystemMetaObject.forObject(realTarget(statementHandler));
        MetaObject metaObject = SystemMetaObject.forObject(object.getValue("delegate"));
        return (MappedStatement) metaObject.getValue("mappedStatement");
    }

    public static <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        } else {
            return (T) target;
        }
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) { }


}