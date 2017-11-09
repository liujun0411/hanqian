package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbDept;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 张电男
 * @version 1.4 2012-9-5
 * @see
 */
@Service
public class DeptBusiness extends BaseBusiness {

	private static final Logger log = Logger.getLogger(DeptBusiness.class);

	/**
	 * 根据id查询组织
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public DbDept findDbDeptById(Integer deptId) {
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.findDbDeptById,参数deptId:" + deptId);
		DbDept dbDeptreturn = sqlSession.selectOne("findDbDeptById", deptId);

		return dbDeptreturn;
	}

	public Map getFindDbDeptByIdSql() {
		return PerformSQLUtil.get("findDbDeptById", this);
	}

	/**
	 * 获取该组织，下的用户
	 * 
	 * @param 2012-10-30
	 * @param @param deptId
	 * @param @return
	 * @return List
	 */
	public List findUserDept(Integer deptId) {
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.findUserDept,参数deptId:" + deptId);
		Map map = new HashMap();
		map.put("deptId", deptId);
		RetCode rc = this.getData("findUserDept", map);// this.getPageData("findUserDept",
														// map,1,100);
		List lst = null;
		if (null != rc.getObj())
			lst = (List) rc.getObj();
		return lst;
	}

	public Map getFindUserDeptSql() {
		return PerformSQLUtil.get("findUserDept", this);
	}

	/**
	 * 获取不是该组织，下的所有用户
	 * 
	 * @param 2012-10-30
	 * @param @param deptId
	 * @param @return
	 * @return List
	 */
	public List findUserNotDept(Integer deptId) {
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.findUserNotDept,参数deptId:" + deptId);
		Map map = new HashMap();
		map.put("deptId", deptId);
		RetCode rc = this.getData("findUserNotDept", map);
		List lst = null;
		if (null != rc.getObj())
			lst = (List) rc.getObj();
		return lst;
	}

	public Map getFindUserNotDeptSql() {
		return PerformSQLUtil.get("findUserNotDept", this);
	}

	/**
	 * 添加用户组织
	 * 
	 * @param 2012-10-30
	 * @param @param userseq
	 * @param @param deptId
	 * @param @return
	 * @return boolean
	 */
	public boolean insertDeptUser(String[] userseq, Integer deptId,
			String hospId) {
		// 医院ID 暂定
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.insertDeptUser,参数deptId:" + deptId
					+ " hospId:" + hospId + " userseq:" + userseq);
		boolean check = true;
		try {
			Map map = null;
			for (int i = 0; i < userseq.length; i++) {
				map = new HashMap();
				map.put("seqHosp", hospId);
				map.put("seq", userseq[i]);
				map.put("dept", deptId);
				sqlSession.insert("insertDeptUser", map);
			}
		} catch (Exception e) {
			check = false;
			log.error("进入DeptBusiness.insertDeptUser,对象插入失败！", e);
		}
		return check;
	}

	public Map getInsertDeptUserSql() {
		return PerformSQLUtil.get("insertDeptUser", this);
	}

	/**
	 * 删除该组织下所有用户
	 * 
	 * @param 2012-10-30
	 * @param @param userSeq
	 * @param @return
	 * @return boolean
	 */
	public boolean deleteDeptUser(Integer deptId) {

		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.deleteDeptUser,对象deptId:" + deptId);
		boolean check = true;
		try {
			sqlSession.delete("deleteDeptUser", deptId);
		} catch (Exception e) {
			check = false;
			log.error("进入DeptBusiness.deleteDeptUser,对象删除失败！", e);
		}
		return check;
	}

	public Map getDeleteDeptUserSql() {
		return PerformSQLUtil.get("deleteDeptUser", this);
	}

	/**
	 * 添加
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public boolean insertDept(DbDept dbDept) {
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.insertDept,对象DbDept:" + dbDept);
		boolean bool = true;
		try {
			sqlSession.insert("insertDept", dbDept);
		} catch (Exception e) {
			bool = false;
			log.error("进入DeptBusiness.insertDept,对象插入失败！", e);
		}
		return bool;
	}

	public Map getInsertDeptSql() {
		return PerformSQLUtil.get("insertDept", this);
	}

	/**
	 * 修改
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public boolean updateDept(DbDept dbDept) {
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.updateDept,对象DbDept:" + dbDept);
		boolean bool = true;
		try {
			sqlSession.update("updateDept", dbDept);
		} catch (Exception e) {
			bool = false;
			log.error("进入DeptBusiness.updateDept,对象更新失败！", e);
		}
		return bool;

	}

	public Map getUpdateDeptSql() {
		return PerformSQLUtil.get("updateDept", this);
	}

	/**
	 * 修改
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public boolean updateDeptStatus(Integer deptId) {
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.updateDeptStatus,参数deptId:" + deptId);
		boolean bool = true;
		try {
			sqlSession.update("updateDeptStatus", deptId);
		} catch (Exception e) {
			bool = false;
			log.error("进入DeptBusiness.updateDeptStatus,对象更新失败！", e);
		}
		return bool;
	}

	public Map getUpdateDeptStatusSql() {
		return PerformSQLUtil.get("updateDeptStatus", this);
	}

	/**
	 * 删除
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public boolean deleteDept(DbDept dbDept) {

		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.deleteDept,对象dbDept:" + dbDept);
		boolean bool = true;
		try {
			sqlSession.delete("deleteDept", dbDept);
		} catch (Exception e) {
			bool = false;
			log.error("进入DeptBusiness.deleteDept,对象删除失败！", e);
		}
		return bool;

	}

	public Map getDeleteDeptSql() {
		return PerformSQLUtil.get("deleteDept", this);
	}

	/**
	 * 查询所有组织
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findDept(DbDept dbDept, int currentPage, int pageSize) {
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.findDept,对象DbDept:" + dbDept);
		Map map = new HashMap();
		if (null == dbDept) {
			map.put("dbDeptName", null);
			map.put("dbDeptPrincipal", null);
		} else {
			if (dbDept.getName() != null && dbDept.getName() != "") {
				map.put("dbDeptName", dbDept.getName());
			} else {
				map.put("dbDeptName", null);
			}

			if (dbDept.getPrincipal() != null && dbDept.getPrincipal() != "") {
				map.put("dbDeptPrincipal", dbDept.getPrincipal());
			} else {
				map.put("dbDeptPrincipal", null);
			}

		}
		return this.getPageData("findDept", map, currentPage, pageSize);

	}

	public Map getFindDeptSql() {
		return PerformSQLUtil.get("findDept", this);
	}

	public List findAll() {
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.findAll");
		return sqlSession.selectList("findAll");

	}

	public Map getFindAllSql() {
		return PerformSQLUtil.get("findAll", this);
	}

}
