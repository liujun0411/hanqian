package com.hanqian.business;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;


/**
 * 描 述: 
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-9-4
 * @see
 */

@Service
public class HospInfoBusiness  extends BaseBusiness {
	
//	@Resource
//	private HospInfoMgr hospInfoMgr;
//	
	// 日志
	//private static final Logger log = Logger.getLogger(HospInfoBusiness.class);
	
	private static final Logger log = Logger.getLogger(HospInfoBusiness.class);
	/**
	 * @param 分页查询医院信息  
	 * @param @param 医院实体类
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @return       
	 */
	public RetCode findHospInfo(DbHospInfo hosp,int currentPage,int pageSize){
		if (log.isDebugEnabled()) {
			log.debug("进入HospInfoBusiness.findHospInfo,对象DbHospInfo:"
					+ hosp);	
		}
		return this.getPageData("findHospInfo", null, currentPage, pageSize);
		//return hospInfoMgr.findHospInfo(hosp, currentPage, pageSize);
	}
	
	public Map getFindHospInfoSqlByPage() {
		return PerformSQLUtil.get("findHospInfo", this);
	}
	
	/**
	 * @param 非分页查询医院信息  
	 * @param @param 医院实体类
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @return       
	 */
	public RetCode findHospInfo(DbHospInfo hosp){
		if (log.isDebugEnabled()) {
			log.debug("进入HospInfoBusiness.findHospInfo,对象DbHospInfo:"
					+ hosp);	
		}
		return this.getData("findHospInfoAAA", null);
		//return hospInfoMgr.findHospInfo(hosp);
	}
	
	public Map getFindHospInfoSql() {
		return PerformSQLUtil.get("findHospInfoAAA", this);
	}
	
	/**
	 * @param 根据ID查询医院信息  
	 * @param @param hosp
	 * @param @return  
	 */
	public DbHospInfo findHospInfoForId(int seqHosp){
		//DbHospInfo hosp = new DbHospInfo();
		//hosp.setSeqHosp(seqHosp);
		//return hospInfoMgr.findById(hosp);
		if (log.isDebugEnabled())
			log.debug("进入HospInfoBusiness.findHospInfoForId,参数seqHosp:"
					+ seqHosp);
		DbHospInfo dbHospInfo = sqlSession.selectOne("findHospInfoForId", seqHosp);
		if (null == dbHospInfo) {
			return null;
		}
		return dbHospInfo;
	}
	
	public Map getFindHospInfoForIdSql() {
		return PerformSQLUtil.get("findHospInfoForId", this);
	}

	public void insertOrUpHospInfo(DbHospInfo hospInfo){
		//hospInfoMgr.insertOrUpHospInfo(hospInfo);
		if (log.isDebugEnabled())
			log.debug("进入HospInfoBusiness.insertOrUpHospInfo,参数DbHospInfo:"
					+ hospInfo);
		sqlSession.insert("insertDbHospInfo", hospInfo);
	}
	
	public Map getInsertOrUpHospInfoSql() {
		return PerformSQLUtil.get("insertDbHospInfo", this);
	}
	/**
	 * 
	 * @param 修改医院信息  
	 * @param logisticsNews       
	 * @return
	 */
    public boolean updateHospInfo(DbHospInfo hospInfo){
		//return hospInfoMgr.updateHospInfo(hospInfo);
    	boolean bool = true;
    	try {
    		log.debug("     buildTime:      "+hospInfo.getBuildtime());
			sqlSession.update("updateByPrimaryKeyDbHospInfo", hospInfo);
		} catch (Exception e) {
			bool =false;
		}
    	return bool;
	}
    
	public Map getUpdateHospInfoSql() {
		return PerformSQLUtil.get("updateByPrimaryKeyDbHospInfo", this);
	}
    
    /**
     * 查询所有医院信息
     * @param statement  
     * @param        
     * @return
     */
	public RetCode findAllHos() {
		return this.getData("findAllHosDetail", null);
	}
	/**
	 * 获得findAllHos方法执行的select语句
	 * 
	 * @return
	 */
	public Map getFindAllHosSql() {
		return PerformSQLUtil.get("findAllHosDetail", this);
	}
	
	/**
	 * 查询医院信息
	 * @param statement  
	 * @param        
	 * @return
	 */
	public DbHospInfo findHospInfos(DbHospInfo hospInfo) {
		//return hospInfoMgr.findHospInfos(hospInfo);
		if (log.isDebugEnabled())
			log.debug("进入HospInfoBusiness.findHospInfoForId,参数DbHospInfo:"
					+ hospInfo);
		
		return sqlSession.selectOne("findHospInfos", hospInfo);
	}
	
	public Map getFindHospInfosSql() {
		return PerformSQLUtil.get("findHospInfos", this);
	}
	
	
	/**
	 * 根据用户对象 获取医院信息
	 * @param 2012-11-14  
	 * @param @param users
	 * @param @return       
	 * @return Map
	 */
	public Map findHospInfoBySql(DbUsers users){
//		String sql="select * from db_hosp_info h where " +
//		"h.seq_hosp in(select hosp_id from db_users u where u.seq = "+users.getSeq()+")";
//		if (logg.isDebugEnabled()) logg.debug("(HospInfoMgr-->findHospInfoBySql)根据用户对象 获取医院信息SQL=:" +sql);
		//RetCode rc=this.findBySQL(hospInfoDao, sql);
		if (log.isDebugEnabled())
			log.debug("进入HospInfoBusiness.findHospInfoBySql,对象DbUsers:"
					+ users);
		Map param = new HashMap();
		param.put("seq", users.getSeq());
		RetCode rc= this.getData("", param);
		List lst=(List)rc.getObj();
		if(lst!=null){
			return (Map)lst.get(0);
		}
		return null;
		
	}
	
    
	
	
//	public HospInfoMgr getHospInfoMgr() {
//		return hospInfoMgr;
//	}
//
//	public void setHospInfoMgr(HospInfoMgr hospInfoMgr) {
//		this.hospInfoMgr = hospInfoMgr;
//	}

}
