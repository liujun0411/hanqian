/**
 * 
 */
package com.hanqian.common.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathNotFoundException;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import com.hanqian.util.PageMysql;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.StringUtil;

/**
 * mybatis分页插件,只针对mysql数据库
 * 
 * @author Eden.Cui
 *
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }) })
public class PageInterceptorMySql implements Interceptor {

	/** 日志 */
	private static final Logger log = Logger.getLogger(PageInterceptorMySql.class);

	public Object intercept(Invocation invocation) throws Throwable {

		// 当前环境 MappedStatement，BoundSql，及sql取得
		MappedStatement mappedStatement = (MappedStatement) invocation
				.getArgs()[0];
		Object parameter = invocation.getArgs()[1];
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		String originalSql = boundSql.getSql().trim();
		Object parameterObject = boundSql.getParameterObject();

		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		
		String id = mappedStatement.getId();
		
		// Page对象获取，“信使”到达拦截器！
		PageMysql page = searchPageWithXpath(boundSql.getParameterObject(), ".",	"page", "*/page");

		if (log.isDebugEnabled())
			log.debug("\r\nmybatis查询,是否取得分页对象:" + (page == null ? "否" : "是"));

		if (page != null) {
			
			// Page对象存在的场合，开始分页处理
			String countSql = getCountSql(originalSql);
			if (log.isDebugEnabled())
				log.debug("\r\nmybatis查询,取总数sql:" + countSql);
			Connection connection = mappedStatement.getConfiguration()
					.getEnvironment().getDataSource().getConnection();
			PreparedStatement countStmt = connection.prepareStatement(countSql);
			BoundSql countBS = copyFromBoundSql(mappedStatement, boundSql,
					countSql);
			DefaultParameterHandler parameterHandler = new DefaultParameterHandler(
					mappedStatement, parameterObject, countBS);
			parameterHandler.setParameters(countStmt);
			ResultSet rs = countStmt.executeQuery();
			int totpage = 0;
			if (rs.next()) {
				totpage = rs.getInt(1);
			}
			rs.close();
			countStmt.close();
			connection.close();
             
			if (log.isDebugEnabled())
				log.debug("\r\nmybatis查询,总记录数:" + totpage);

			String pageSql = getPageSql(originalSql, page);
			if (log.isDebugEnabled())
				log.debug("\r\nmybatis查询,分页sql:" + pageSql);
			//根据分页sql语句,倒转回mybatis sql语句
			reverseSql(id, pageSql, parameterMappings, parameterObject);

		      BoundSql newBoundSql = copyFromBoundSql(mappedStatement, boundSql,
					pageSql);
			MappedStatement newMs = copyFromMappedStatement(mappedStatement,
					new BoundSqlSqlSource(newBoundSql));
			invocation.getArgs()[0] = newMs;
               
			// 分页计算
			page.settotalCount(totpage);
		} else {
			
			//根据原始sql语句,倒转回mybatis sql语句
			reverseSql(id, originalSql, parameterMappings, parameterObject);
		}
		return invocation.proceed();
	}

	private void reverseSql(String id, String originalSql, List<ParameterMapping> parameterMappings, Object parameterObject) {
		if (parameterMappings == null || parameterMappings.size() == 0) {
			Map map = new CaseInsensitiveMap();
			map.put("resultsql", originalSql);
			
			PerformSQLUtil.put(id, map);
		} else {
			
			String mybatisSql = originalSql;
			for (ParameterMapping p:parameterMappings) {
				mybatisSql = mybatisSql.replaceFirst("\\?", "#{" + p.getProperty() + "}");
			}
			
			Map map = new CaseInsensitiveMap();
			map.put("resultsql", mybatisSql);
			
			if (parameterObject instanceof Map) {
				map.putAll((Map)parameterObject);
				map.remove("page");   //删除不需要的page对象,只保留sql和替换变量
			} else {
				map.put(parameterMappings.get(0).getProperty(), parameterObject);
			}
						
			PerformSQLUtil.put(id, map);
		}
		
		if (log.isDebugEnabled()) log.debug("\r\nmybatis查询,还原mybatis语句:" + PerformSQLUtil.get(id) + ", 对象id:" + id);
	}
	
	/**
	 * 根据给定的xpath查询Page对象
	 */
	private PageMysql searchPageWithXpath(Object o, String... xpaths) {
		JXPathContext context = JXPathContext.newContext(o);
		Object result;
		for (String xpath : xpaths) {
			try {
				result = context.selectSingleNode(xpath);
			} catch (JXPathNotFoundException e) {
				continue;
			}
			if (result instanceof PageMysql) {
				return (PageMysql) result;
			}
		}
		return null;
	}

	/**
	 * 复制MappedStatement对象
	 */
	private MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(), ms.getId(),
				newSqlSource, ms.getSqlCommandType());

		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.keyProperty(StringUtil.arrayToString(ms.getKeyProperties(), ","));
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());

		return builder.build();
	}

	/**
	 * 复制BoundSql对象
	 */
	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
			String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql,
				boundSql.getParameterMappings(), boundSql.getParameterObject());
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
			String prop = mapping.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop,
						boundSql.getAdditionalParameter(prop));
			}
		}
		return newBoundSql;
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 */
	private String getCountSql(String sql) {
		return "SELECT COUNT(*) FROM (" + sql + ") aliasForPage";
	}

	private String getPageSql(String originalSql, PageMysql page) {
		return "select * from  ("
				+ originalSql + ") a limit "+ (page.getpageSize() * (page.getCurrentPage() - 1))
				+","+page.getpageSize();
	}

	public class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties arg0) {
	}
}