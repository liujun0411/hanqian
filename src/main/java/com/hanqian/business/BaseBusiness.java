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
import com.hanqian.util.RetCode;

/**
 * @author Eden
 *
 */
public class BaseBusiness {

	/** 日志 */
	private static final Logger log = Logger.getLogger(BaseBusiness.class);

	@Resource
	protected SqlSessionTemplate sqlSession;

	/**
	 * 根据传入的分页sql,进行分页查询,返回RetCode对象 默认currentPage=1, pageSize=10
	 * 
	 * @param sqlKey
	 */
	protected RetCode getPageData(String sqlKey, Map param) {
		return getPageData(sqlKey, param, 1, 10);

	}

	/**
	 * 根据传入的分页sql,进行分页查询,返回RetCode对象
	 * 
	 * @param sqlKey
	 */
	protected RetCode getPageData(String sqlKey, Map param, int currentPage,
			int pageSize) {

		Page page = new Page(currentPage, 1, pageSize);

		if (param == null) {
			param = new HashMap();
		}
		param.put("page", page);

		RetCode rt = getData(sqlKey, param);

		Page finalPage = new Page(currentPage, page.gettotalCount(), pageSize);
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
	protected RetCode getData(String sqlKey, Map param) {
		RetCode rt = new RetCode(1001, "查无数据！", "查无数据！");

		try {
			List data = sqlSession.selectList(sqlKey, param);

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
