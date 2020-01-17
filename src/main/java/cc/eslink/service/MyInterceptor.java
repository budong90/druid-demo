package cc.eslink.service;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 *@ClassName MyInterceptor
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/1/17 13:52
 *@Version 1.0
 **/
@Intercepts({
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class),
        @Signature(method = "query", type = Executor.class,
                args = {MappedStatement.class, Object.class, RowBounds.class,
                        ResultHandler.class, CacheKey.class, BoundSql.class}
        )})
@SuppressWarnings("unchecked")
public class MyInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 该方法写入自己的逻辑
        if (invocation.getTarget() instanceof StatementHandler) {
            RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
            BoundSql boundSql = handler.getBoundSql();
            System.out.println("--->" + boundSql.getSql());
        }
        // SQL execute start time
        long startTimeMillis = System.currentTimeMillis();
        // get execute result
        Object proceedReslut = invocation.proceed();
        // SQL execute end time
        long endTimeMillis = System.currentTimeMillis();
        logger.debug("<< ==== sql execute runnung time：{} millisecond ==== >>", (endTimeMillis - startTimeMillis));
        return proceedReslut;
    }

    @Override
    public Object plugin(Object target) {
        // 调用插件
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println(properties);
    }
}
