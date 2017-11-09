/**
 * 
 */
package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;

import com.hanqian.util.Page;
import com.hanqian.util.PageMysql;
import com.hanqian.util.RetCode;
import com.hanqian.util.RetCodeMysql;

/**
 * @author Eden
 *
 */
public class MysqlBaseBusiness {

	/** 日志 */
	private static final Logger log = Logger.getLogger(MysqlBaseBusiness.class);

	@Resource
	protected SqlSessionTemplate mysqlSqlSession;

	/**
	 * 根据传入的分页sql,进行分页查询,返回RetCode对象 默认currentPage=1, pageSize=10
	 * 
	 * @param sqlKey
	 */
	protected RetCodeMysql getPageData(String sqlKey, Map param) {
		return getPageData(sqlKey, param, 1, 10);

	}

	/**
	 * 根据传入的分页sql,进行分页查询,返回RetCode对象
	 * 
	 * @param sqlKey
	 */
	protected RetCodeMysql getPageData(String sqlKey, Map param, int currentPage,
			int pageSize) {

		PageMysql page = new PageMysql(currentPage, 1, pageSize);

		if (param == null) {
			param = new HashMap();
		}
		param.put("page", page);

		RetCodeMysql rt = getData(sqlKey, param);

		PageMysql finalPage = new PageMysql(currentPage, page.gettotalCount(), pageSize);
		rt.setPage(finalPage);
        
		page = null;
		return rt;
	}

	/**
	 * 配合原V1.4版本检索数据后需要返回RetCode而封装的方法
	 * 
	 * @param sqlKey
	 *            mybatis xml文件内的sql id
	 * @param param
	 *            参数map
	 * @return
	 */
	protected RetCodeMysql getData(String sqlKey, Map param) {
		RetCodeMysql rt = new RetCodeMysql(1001, "查无数据！", "查无数据！");

		try {
			List data = mysqlSqlSession.selectList(sqlKey, param);

			if (data != null && data.size() > 0) {
				rt.setCode(0);
				rt.setDesc("操作成功");
				rt.setDetail("操作成功");
				rt.setObj(data);
			}
		} catch (Exception e) {
			log.error("执行分页查询时出现异常.", e);
			rt.setCode(1004);
			rt.setDesc("异常出错");
			rt.setDetail("异常出错");
		}

		return rt;
	}
	
}
