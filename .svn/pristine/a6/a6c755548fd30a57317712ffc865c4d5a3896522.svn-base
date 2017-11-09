package com.hanqian.business;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.form.VOBusiness;
import com.hanqian.pojo.DbBusiness;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 李江
 * @version 1.4 2012-9-6
 * @see
 */
@Service("volumeBusiness")
public class VolumeBusiness    extends BaseBusiness{
	public static final Logger log = Logger.getLogger(VolumeBusiness.class);
	
//	private BusinessMgr businessMgr;
//	
//	public BusinessMgr getBusinessMgr() {
//		return businessMgr;
//	}
//
//	public void setBusinessMgr(BusinessMgr businessMgr) {
//		this.businessMgr = businessMgr;
//	}

	/**
	 * 查询某年总业务量
	 * @param dbbusiness
	 * @return
	 */
	public RetCode findVoBusinSumBusiness(int seqHosp,String year){
		//return businessMgr.findBusinessSumByYear(seqHosp, year);
		if (log.isDebugEnabled())
			log.debug("进入VolumeBusiness.findVoBusinSumBusiness!参数seqHosp:"+seqHosp+" year:"+year);
		Map map = new HashMap();
		map.put("seqHosp", seqHosp);
		map.put("year", year);
		return this.getData("findBusinessSumByYear", map);
		
	}
	
	public Map getFindVoBusinSumBusinessSql() {
		return PerformSQLUtil.get("findBusinessSumByYear", this);
	}
	
	/**
	 * 医院业务量查询带分页
	 * 
	 * @param DbBusiness
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	private Map getBusinessCrit(VOBusiness voBusiness) {
		Map map = new HashMap();
		if (voBusiness != null) {
			if (voBusiness.getBusiness().getDbHospInfo() != null
					&& !SysUtil.isNull(voBusiness.getBusiness().getDbHospInfo()
							.getSeqHosp().toString())) {
//				dc.add(Restrictions.eq("dbHospInfo", voBusiness.getBusiness()
//						.getDbHospInfo()));
				map.put("seqHosp", voBusiness.getBusiness().getDbHospInfo()
							.getSeqHosp().toString());
			}else{
				map.put("seqHosp", null);
			}
			
			if (!SysUtil.isNull(voBusiness.getStartDate())
					&& !SysUtil.isNull(voBusiness.getStopDate())) {
//				dc.add(Restrictions.between("monthly", voBusiness
//						.getStartDate(), voBusiness.getStopDate()));
				map.put("startDate", voBusiness.getStartDate());
				map.put("stopDate", voBusiness.getStopDate());
			} else if (!SysUtil.isNull(voBusiness.getStartDate())) {
				//dc.add(Restrictions.ge("monthly", voBusiness.getStartDate()));
				map.put("startDate", voBusiness.getStartDate());
				map.put("stopDate", null);
			} else if (!SysUtil.isNull(voBusiness.getStopDate())) {
				//dc.add(Restrictions.le("monthly", voBusiness.getStopDate()));
				map.put("startDate", null);
				map.put("stopDate", voBusiness.getStopDate());
			} else {
				map.put("startDate", null);
				map.put("stopDate", null);
			}
		}else{
			map.put("seqHosp", null);
			map.put("startDate", null);
			map.put("stopDate", null);
		}
		
		return map;

	}
	
	
	public RetCode findBusiness(VOBusiness voBusiness, int currentPage, int pageSize) {
		//return businessMgr.findBusiness(voBusiness,currentPage,pageSize);
		if (log.isDebugEnabled())
			log.debug("进入VolumeBusiness.findVoBusinSumBusiness!参数voBusiness:"+voBusiness+" currentPage:"+currentPage+" pageSize:"+pageSize);
		Map map = new HashMap();
//		map = this.getBusinessCrit(voBusiness);
		if (voBusiness != null) {
			if (voBusiness.getBusiness().getDbHospInfo() != null
					&& !SysUtil.isNull(voBusiness.getBusiness().getDbHospInfo()
							.getSeqHosp().toString())) {
//				dc.add(Restrictions.eq("dbHospInfo", voBusiness.getBusiness()
//						.getDbHospInfo()));
				map.put("seqHosp", voBusiness.getBusiness().getDbHospInfo()
							.getSeqHosp().toString());
			}else{
				map.put("seqHosp", null);
			}
			
			if (!SysUtil.isNull(voBusiness.getStartDate())
					&& !SysUtil.isNull(voBusiness.getStopDate())) {
//				dc.add(Restrictions.between("monthly", voBusiness
//						.getStartDate(), voBusiness.getStopDate()));
				map.put("startDate", voBusiness.getStartDate());
				map.put("stopDate", voBusiness.getStopDate());
			} else if (!SysUtil.isNull(voBusiness.getStartDate())) {
				//dc.add(Restrictions.ge("monthly", voBusiness.getStartDate()));
				map.put("startDate", voBusiness.getStartDate());
				map.put("stopDate", null);
			} else if (!SysUtil.isNull(voBusiness.getStopDate())) {
				//dc.add(Restrictions.le("monthly", voBusiness.getStopDate()));
				map.put("startDate", null);
				map.put("stopDate", voBusiness.getStopDate());
			} else {
				map.put("startDate", null);
				map.put("stopDate", null);
			}
		}else{
			map.put("seqHosp", null);
			map.put("startDate", null);
			map.put("stopDate", null);
		}
		return this.getPageData("findBusinessVOBusiness", map, currentPage, pageSize);
	}
	
	public Map getFindBusinessSql() {
		return PerformSQLUtil.get("findBusinessVOBusiness", this);
	}
	
	
	public RetCode findBusiness(DbBusiness business, Date businessTime) {
		//return businessMgr.findBusiness(business, businessTime) ;
		if (log.isDebugEnabled())
			log.debug("进入VolumeBusiness.findVoBusinSumBusiness!参数DbBusiness:"+business+" businessTime:"+businessTime);
		Map map = new HashMap();
		if (businessTime != null) {
			
			map.put("businessTime", businessTime);
		}else{
			map.put("businessTime", null);
		}
		return this.getPageData("findBusinessDbBusiness", map);
	}
	
	public Map getFindBusinessByDateSql() {
		return PerformSQLUtil.get("findBusinessDbBusiness", this);
	}
	/**
	 * 修改业务量
	 * 
	 * @param dbbusiness
	 * @throws Exception
	 */
	public String updateBusiness(VOBusiness voBusiness) {
		//return businessMgr.updateBusiness(voBusiness);
		if (log.isDebugEnabled())
			log.debug("进入VolumeBusiness.updateBusiness!参数VOBusiness:"+voBusiness);
		return this.updateBusinessMethod(voBusiness);
	}
	
	public Map getUpdateBusinessSql() {
		return PerformSQLUtil.get("updateBusinessDAO", this);
	}
	
	public String updateBusinessMethod(VOBusiness voBusiness) {
		String mgs = null;
		if (findMonthThere(voBusiness, "edit")) {
			mgs = voBusiness.getMonthly() + "月业务量已存在！ 修改失败！";
		} else {
			try {
				int seq = voBusiness.getSeq();
				DbBusiness business = sqlSession.selectOne("findByIdBusinessDAO", seq);
				//DbBusiness business = businessDAO.findById(voBusiness.getSeq());
				// 门诊量
				business.setOutPatient(voBusiness.getOutPatient());
				// 急诊量
				business.setEmergency(voBusiness.getEmergency());
				// 住院量
				business.setInPatient(voBusiness.getInPatient());
				// 业务月份
				business.setMonthly(voBusiness.getMonthly());
				// 操作员
				// business.setDbUsers(dbUsers);
				//businessDAO.getHibernateTemplate().update(business);
				sqlSession.update("updateBusinessDAO", business);
				mgs = "修改成功!";
			} catch (Exception e) {
				// TODO: handle exception
				log.error("VolumeBusiness-->updateBusiness", e);
				mgs = "修改失败 ,请联系管理员！";
				e.printStackTrace();
			}
		}
		return mgs;

	}
	
	private boolean findMonthThere(VOBusiness voBusiness, String type) {
		boolean success = true;
		if (voBusiness.getHospId() == 0) {
			success = false;
		}
		//修改的是否
		if (type == "edit" && voBusiness.getMonthly()==voBusiness.getStartDate()) {
			success = false;
		} else {//新增的时候,或者修改修改的时候 月份也修改了
//			String sql = "select monthly from db_business where hosp_Id='"
//					+ voBusiness.getHospId() + "'  and monthly='"
//					+ voBusiness.getMonthly() + "'";
			
			Map mapvalue = new HashMap();
			mapvalue.put("hospId", voBusiness.getHospId());
			mapvalue.put("monthly", voBusiness.getMonthly());			
			
			if (null != voBusiness.getSeq()) {
				mapvalue.put("seq", voBusiness.getSeq());
				//sql += "  and seq !='" + voBusiness.getSeq() + "' ";
			}
			// 查询
			//RetCode rt = findBySQL(businessDAO, sql);
			RetCode rt = this.getData("findMonthThere", mapvalue);
			
			// 验证
			if (rt.getObj() == null) {
				success = false;
			}
		}
		return success;
	}
	
	public Map getFindMonthThereSql() {
		return PerformSQLUtil.get("findMonthThere", this);
	}
	
	
	/**
	 * 新增业务量
	 * 
	 * @param dbbusiness
	 * @throws Exception
	 */
	public String addBusiness(VOBusiness voBusiness) {
		if (log.isDebugEnabled())
			log.debug("进入VolumeBusiness.addBusiness!参数VOBusiness:"+voBusiness);
		return this.insertBussiness(voBusiness);
	}
	
	public Map getAddBusinessSql() {
		return PerformSQLUtil.get("insertBusinessDAO", this);
	}
	
	public String insertBussiness(VOBusiness voBusiness) {
		String mgs = null;
		// 判断是否存在这个月的业务量
		if (findMonthThere(voBusiness, "add")) {
			mgs = voBusiness.getMonthly() + "月业务量已存在！添加失败！";
		} else {
			try {
				DbBusiness business = new DbBusiness();
				// 医院
				//DbHospInfo hospInfo = hospInfoDAO.findById(voBusiness.getHospId());
				int seqHosp = voBusiness.getHospId();
				Map mapvalue = new HashMap();
				mapvalue.put("seqHosp", seqHosp);
				DbHospInfo hospInfo = sqlSession.selectOne("findByIdHospInfoDAO", seqHosp);
				
				hospInfo.setSeqHosp(seqHosp);
				
				business.setDbHospInfo(hospInfo);

				// 门诊量
				business.setOutPatient(voBusiness.getOutPatient());
				// 急诊量
				business.setEmergency(voBusiness.getEmergency());
				// 住院量
				business.setInPatient(voBusiness.getInPatient());
				// 业务月份
				business.setMonthly(voBusiness.getMonthly());
				// 操作员
				DbUsers dbUsers = new DbUsers();
				dbUsers.setSeq(1);
				business.setDbUsers(dbUsers);

				// 操作时间
				business.setOpertime(new Date());

				// 添加
				//businessDAO.save(business);
				sqlSession.insert("insertBusinessDAO", business);
				mgs = "添加成功!";
			} catch (Exception e) {
				// TODO: handle exception
				log.error("VolumeBusiness-->insertBussiness", e);
				mgs = "添加失败 ,请联系管理员！";
				e.printStackTrace();
			}
		}

		return mgs;
	}
	
}
