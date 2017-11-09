package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbApply;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 刘新
 * @version 1.4 2012-9-18
 * @see
 */
@Service
public class ApplyBusiness extends BaseBusiness {
	private static final Logger log = Logger.getLogger(ApplyBusiness.class);

	public RetCode findApply(DbApply apply, int currentPage, int pageSize) {

		if (log.isDebugEnabled()) {
			log.debug("进入ApplyBusiness.findApply,对象apply:" + apply);
		}
		Map map = new HashMap();
		map.put("seq", apply.getSeq());
		return this.getPageData("findApply", map, currentPage, pageSize);
	}

	public Map getFindApplySql() {
		return PerformSQLUtil.get("findApply", this);
	}

	public RetCode findApply(DbApply apply) {

		if (log.isDebugEnabled()) {
			log.debug("进入ApplyBusiness.findApply,对象apply:" + apply);
		}
		Map map = new HashMap();
		map.put("seq", apply.getSeq());
		return this.getData("findApply1", map);
	}

	public Map getFindApply1Sql() {
		return PerformSQLUtil.get("findApply1", this);
	}

	public DbApply findById(int id) {

		if (log.isDebugEnabled()) {
			log.debug("进入ApplyBusiness.findById,参数id:" + id);
		}
		Map map = new HashMap();
		map.put("id", id);

		return sqlSession.selectOne("findByIdApplyBusiness", map);
	}

	public Map getFindByIdSql() {
		return PerformSQLUtil.get("findByIdApplyBusiness", this);
	}

	/**
	 * 添加领用记录
	 * 
	 * @param apply
	 */
	public void insertApply(DbApply apply) {

		if (log.isDebugEnabled()) {
			log.debug("进入ApplyBusiness.insertApply,对象DbApply:" + apply);
		}
		sqlSession.insert("insertApply", apply);
	}

	public Map getInsertApplySql() {
		return PerformSQLUtil.get("insertApply", this);
	}

	/**
	 * 修改领用记录
	 * 
	 * @param apply
	 */
	public DbApply updateApply(DbApply apply) {

		if (log.isDebugEnabled()) {
			log.debug("进入ApplyBusiness.updateApply,对象DbApply:" + apply);
		}
		sqlSession.update("updateApply", apply);
		return apply;
	}

	public Map getUpdateApplySql() {
		return PerformSQLUtil.get("updateApply", this);
	}

	/**
	 * 删除领用记录
	 * 
	 * @param apply
	 */
	public DbApply deleteApply(DbApply apply) {

		if (log.isDebugEnabled()) {
			log.debug("进入ApplyBusiness.deleteApply,对象DbApply:" + apply);
		}
		apply.setStatus(1);
		sqlSession.update("updateApply", apply);
		return apply;
	}

	public Map getDeleteApplySql() {
		return PerformSQLUtil.get("updateApply", this);
	}

}
