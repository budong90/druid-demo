package cc.eslink.service;

import cc.eslink.entity.TableSeg;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.*;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class ShardTableInterceptor implements Interceptor {

    private final static Logger logger = LoggerFactory.getLogger(ShardTableInterceptor.class);

    private static final String tag = ShardTableInterceptor.class.getName();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        String sqlId = mappedStatement.getId();

        String className = sqlId.substring(0, sqlId.lastIndexOf("."));
        Class<?> classObj = Class.forName(className);

        TableSeg tableSeg = classObj.getAnnotation(TableSeg.class);
        if (null == tableSeg) {
            //不需要分表，直接传递给下一个拦截器处理
            return invocation.proceed();
        }

        //根据配置获取分表字段，生成分表SQL
        String accountMonth = genShardByValue(metaStatementHandler, mappedStatement, tableSeg, boundSql);
        String newSql = boundSql.getSql().replace(tableSeg.tableName(), tableSeg.tableName() + "_" + accountMonth);

        if (newSql != null) {
            logger.debug(tag, "分表后SQL =====>" + newSql);
            metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
        }

        // 传递给下一个拦截器处理
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }


    @Override
    public void setProperties(Properties properties) {
        logger.info("scribeDbNames:" + properties.getProperty("scribeDbNames"));
    }

    //根据配置获取分表的表名后缀
    private String genShardByValue(MetaObject metaStatementHandler, MappedStatement mappedStatement, TableSeg tableSeg, BoundSql boundSql) {
        String accountMonth = null;
        Map<String, Object> additionalParameters = (Map<String, Object>) metaStatementHandler.getValue("delegate.boundSql.additionalParameters");

        if (null != additionalParameters.get(tableSeg.shardBy())) {
            accountMonth = boundSql.getAdditionalParameter(tableSeg.shardBy()).toString();
        } else {
            Configuration configuration = mappedStatement.getConfiguration();
            String showSql = showSql(configuration, boundSql);
            accountMonth = getShardByValue(showSql, tableSeg);
        }
        return accountMonth;
    }

    //根据配置获取分表参数值
    public static String getShardByValue(String showSql, TableSeg tableSeg) {
        final String conditionWhere = "where";
        String accountMonth = null;
        if (StringUtils.isBlank(showSql)) {
            return null;
        } else {
            String[] sqlSplit = showSql.toLowerCase().split(conditionWhere);
            if (sqlSplit.length > 1 && sqlSplit[1].contains(tableSeg.shardByTable())) {
                accountMonth = sqlSplit[1].replace(" ", "").split(tableSeg.shardByTable())[1].substring(2, 8);
            }
        }
        return accountMonth;
    }

    //组装查询语句参数
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        } else {
            return null;
        }
        return sql;
    }

    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

}