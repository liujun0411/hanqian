package com.hanqian.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.form.VOHospDetail;
import com.hanqian.pojo.DbHospDetail;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 李江
 * @version 1.4 2012-9-6
 * @see
 */
@Service
public class HospDetailBusiness extends BaseBusiness {
	private static final Logger log = Logger.getLogger(HospDetailBusiness.class);
//	@Resource
//	private HospDetailMgr hospDetailManager;

	/**
	 * 往年详情 获取当年的数据(一条)作为结果.
	 * 
	 * @param hospDetail
	 * @return
	 */
	private Map getHospCritMap(DbHospDetail hospDetail,
			Date hospitalTime) {
		Map map = new HashMap();
		
		if (hospitalTime != null) {
			//dc.add(Restrictions.ge("opertime", hospitalTime));
			map.put("opertime", hospitalTime);
		}else{
			map.put("opertime", null);
		}
		
		return map;
	}
	
	
	public RetCode findHospDetailBusiness(DbHospDetail hospDetail,
			Date hospitalTime) {
		//return hospDetailManager.findHospDetail(hospDetail, hospitalTime);
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.findHospDetailBusiness,对象DbHospDetail:"
					+ hospDetail);	
		Map map = new HashMap();
		map = this.getHospCritMap(hospDetail, hospitalTime);
		return this.getData("findHospDetailBusiness", map);
	}
	
	public Map getFindHospDetailBusinessDateSql() {
		return PerformSQLUtil.get("findHospDetailBusiness", this);
	}

	/**
	 * 根据医院信息 获得 医院详细信息 无序集合
	 * 
	 * @param hospInfo
	 * @return
	 */
	public List<DbHospDetail> findDbHospInfoBusiness(DbHospInfo hospInfo) {
//		List<DbHospDetail> hospDetailList = hospDetailManager
//				.findListByDbHospInfo(hospInfo);
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.findDbHospInfoBusiness,对象DbHospInfo:"
					+ hospInfo);	
		//List<DbHospDetail> hospDetailList = sqlSession.selectList("findListByDbHospInfo", hospInfo);
		List<DbHospDetail> hospDetailList = this.findListByDbHospInfoMethod(hospInfo);
		return hospDetailList;
	}
	

	
	public List<DbHospDetail> findListByDbHospInfoMethod(DbHospInfo hospInfo) {
		Set<DbHospDetail> sDbHospd = hospInfo.getDbHospDetails();
		List<DbHospDetail> hospDetailList = new ArrayList<DbHospDetail>();
		Iterator<DbHospDetail> hospDetaili = sDbHospd.iterator();
		while (hospDetaili.hasNext()) {
			hospDetailList.add(hospDetaili.next());
		}

		return hospDetailList;// hospitaldetailDAO.findby;
	}

	/**
	 * 根据医院ID, 创建年份判断 当前医字当年份记录是否存在
	 * 
	 * @param VOhospDetail
	 * @return
	 */
	public boolean findYearThereBusiness(DbHospDetail detail) {
		//return hospDetailManager.findYearThere(detail);
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.findYearThereBusiness,对象DbHospDetail:"
					+ detail);	
		boolean success = true;
		try {
			Map mapvalue = new HashMap();			
			mapvalue.put("seqHosp", detail.getDbHospInfo().getSeqHosp());
			mapvalue.put("inputtime", Systime.DateToString(detail.getInputtime(), "yyyy") );
			if(detail.getSeq() != null){
				mapvalue.put("seqHosp", detail.getSeq()); 
			}
			RetCode rt = this.getData("findYearThere", mapvalue);
			if (rt.getObj() == null) {
				success = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return success;
	}
	
	public Map getFindYearThereBusinessSql() {
		return PerformSQLUtil.get("findYearThere", this);
	}

	/**
	 * 根据ID 获得医院详细信息
	 * 
	 * @param sequence
	 * @return
	 */
	public DbHospDetail findDetailByIdBusiness(int sequence) {
		//return hospDetailManager.findDetailByid(sequence);
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.findDetailByIdBusiness,参数sequence:"
					+ sequence);	
		DbHospDetail dbHospDetail = new DbHospDetail();
		int seq = sequence;
		dbHospDetail = sqlSession.selectOne("findDetailByid", seq);
		return dbHospDetail;
	}
	
	public Map getFindDetailByIdBusinessSql() {
		return PerformSQLUtil.get("findDetailByid", this);
	}

	/**
	 * 根据医院ID 获得医院详细信息
	 * 
	 * @param detail
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	private Map getDetailHospLidCritMap(DbHospDetail detail) {
		Map map = new HashMap();

		if (detail != null) {
			if (!SysUtil.isNull(detail.getDbHospInfo().getSeqHosp().toString())) {
//				dc.add(Restrictions
//						.eq("dbHospitalinfo", detail.getDbHospInfo()));
				map.put("seqHosp", detail.getDbHospInfo().getSeqHosp());
			}else{
				map.put("seqHosp", null);
			}
			if (detail.getInputtime() != null) {
				//dc.add(Restrictions.eq("inputtime", detail.getInputtime()));
				map.put("inputtime", detail.getInputtime());
			}else{
				map.put("inputtime", null);
			}

		}else{
			map.put("seqHosp", null);
			map.put("inputtime", null);
		}		
		return map;
	}
	
	public RetCode findDetailHospIdBusiness(DbHospDetail detail,
			int currentPage, int pageSize) {
		//return hospDetailManager.findDetailHospLid(detail, currentPage, pageSize);
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.findDetailHospIdBusiness,参数DbHospDetail:"
					+ detail+" currentPage:"+currentPage+" pageSize:"+pageSize);
		Map map = new HashMap();
		map = this.getDetailHospLidCritMap(detail);
		return this.getPageData("findDetailHospLid", map, currentPage, pageSize);
	}
	
	
	public Map getFindDetailHospIdBusinessSql() {
		return PerformSQLUtil.get("findDetailHospLid", this);
	}

	/**
	 * 添加医院详细信息
	 * 
	 * @param hospDetail
	 */
	public String insertDetailBusiness(DbHospDetail detail) {
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.insertDetailBusiness,参数DbHospDetail:"
					+ detail);
		//return hospDetailManager.insertDetail(detail);
		String mgs = null;
		if (this.findYearThereBusiness(detail)) {
			mgs = Systime.DateToString(detail.getInputtime(), "yyyy")
					+ "年详情已存在！添加失败！";
		} else {
			try {
				//hospDetailDAO.save(detail);
				sqlSession.insert("insertDetailDbHospDetail", detail);
				mgs = "添加成功";
			} catch (Exception e) {
				e.printStackTrace();
				mgs = "添加失败 ,请联系管理员！";
				log.error("HospDetailBusiness-->insertDetail", e);
			}

		}

		return mgs;
	}
	
	public Map getInsertDetailBusinessSql() {
		return PerformSQLUtil.get("insertDetailDbHospDetail", this);
	}
	
	/**
	 * 根据医院ID,创建年份 分页查询医院详细信息列表
	 * 
	 * @param hospDetail
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	public RetCode findDetailByYearAndHospIdBusiness(VOHospDetail voHospDetail,
			int currentPage, int pageSize) {
				//return hospDetailManager.findDetailByYearAndHospitalId(voHospDetail, currentPage, pageSize);
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.findDetailByYearAndHospIdBusiness,参数VOHospDetail:"
					+ voHospDetail+" currentPage:"+currentPage+" pageSize:"+pageSize);
		Map map = new HashMap();
		map.put("hospitalid", voHospDetail.getHospitalid());
		if (!SysUtil.isNull(voHospDetail.getInputtime())) {
//			sql += " and to_char(this_.INPUTTIME,'YYYY')='"
//					+ voHospDetail.getInputtime() + "' ";
			map.put("inputtime", voHospDetail.getInputtime());
		}else{
			map.put("inputtime", null);
		}
		return this.getPageData("findDetailByYearAndHospitalId", map, currentPage, pageSize);
		
	}
	
	public Map getFindDetailByYearAndHospIdBusinessSql() {
		return PerformSQLUtil.get("findDetailByYearAndHospitalId", this);
	}
	
	/**
	 * 修改医院详细信息
	 * 
	 * @param hospDetail
	 */
	public String updateHospDetailBusiness(DbHospDetail detail) {
		//return hospDetailManager.updateHospDetail(detail);
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.updateHospDetailBusiness,参数DbHospDetail:"
					+ detail);
		String mgs = null;
		if (this.findYearThereBusiness(detail)) {
			mgs = Systime.DateToString(detail.getInputtime(), "yyyy")
					+ "年详情已存在！修改失败！";
		} else {
			try {
				//hospDetailDAO.update(detail);
				sqlSession.update("updateHospDetail", detail);
				mgs = "修改成功！";
			} catch (Exception e) {
				e.printStackTrace();
				mgs = "修改失败 ,请联系管理员！";
				log.error("HospDetailBusiness-->updateHospDetail", e);
			}
		}
		return mgs;
	}
	
	public Map getUpdateHospDetailBusinessSql() {
		return PerformSQLUtil.get("updateHospDetail", this);
	}
	
	/**
	 * 医院详细信息查询带分页带参数
	 * 
	 * @param vo
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public RetCode findHospDetailBusiness(DbHospDetail hospDetail, Date startDate,
			Date stopDate, int currentPage, int pageSize) {
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.findHospDetailBusiness,参数DbHospDetail:"
					+ hospDetail+" startDate:"+startDate+" stopDate:"+stopDate);
		Map param = new HashMap();
		
		if (hospDetail != null) {
			if (hospDetail.getDbHospInfo() != null) {
				param.put("seqHosp", hospDetail.getDbHospInfo().getSeqHosp());
			}
		}
				
		param.put("startDate", startDate);
		param.put("stopDate", stopDate);
		
		return this.getPageData("findHospDetailBusiness", param, currentPage, pageSize);
	}
	
	/**
	 * 获得findHospDetailBusiness方法执行的select语句
	 * 
	 * @return
	 */
	public Map getFindHospDetailBusinessSql() {
		return PerformSQLUtil.get("findHospDetailBusiness", this);
	}
	
	/**
	 * 医院详细信息查询不带分页带参数
	 * 
	 * @param vo
	 * @return
	 */
	private Map getHospDetailCritMap(DbHospDetail hospDetail,
			Date startDate, Date stopDate) {
		
		Map map = new HashMap();
		
		if (hospDetail != null) {
			if (hospDetail.getDbHospInfo() != null) {
//				dc.add(Restrictions
//						.eq("dbHospInfo", hospDetail.getDbHospInfo()));
				map.put("seqHosp", hospDetail.getDbHospInfo().getSeqHosp());
			}else{
				map.put("seqHosp", null);
			}
		}else{
			map.put("seqHosp", null);
		}
		if (startDate != null && stopDate != null) {
			//dc.add(Restrictions.between("inputtime", startDate, stopDate));
			map.put("startDate", startDate);
			map.put("stopDate", stopDate);
			map.put("inputtime", "true");
		}else{
			map.put("startDate", null);
			map.put("stopDate", null);
			map.put("inputtime", null);
		}
		
		return map;
	}
	
	
	
	public RetCode findHospitaldetail(DbHospDetail hospDetail, Date startDate,
			Date stopDate) {
				//return hospDetailManager.findHospitaldetail(hospDetail, startDate, stopDate);
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.getHospDetailCritMap,参数DbHospDetail:"
					+ hospDetail+" startDate:"+startDate+" stopDate:"+stopDate);
		Map map = new HashMap();
		map = this.getHospDetailCritMap(hospDetail, startDate, stopDate);
		return this.getData("findHospitaldetail", map);
	}
	
	public Map getFindHospitaldetailSql() {
		return PerformSQLUtil.get("findHospitaldetail", this);
	}
	
	/**
	 * 查询医院占地总面积
	 * 
	 * @param hosId
	 * @return
	 */
	public RetCode findSumAreaBusiness() {
		//return hospDetailManager.findSumArea();
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.findSumAreaBusiness!");
		return this.getData("findSumArea", null);
	}
	
	/**
	 * 验证日期的唯一性
	 * 
	 * 2015-09-06
	 * 
	 * @return
	 */
	public RetCode dateTimeById(String dateById) {
		Map map = new HashMap();
		map.put("dateById", dateById);
		if (log.isDebugEnabled())
			log.debug("进入HospDetailBusiness.dateTimeById!");
		return this.getData("dateTimeById", map);
	}
	/**
	 * 验证日期的唯一性
	 * 
	 * 2015-09-06
	 * 
	 * @return
	 */
	public Map getFinddateTimeByIdSql() {
		return PerformSQLUtil.get("dateTimeById", this);
	}
	public Map getFindSumAreaBusinessSql() {
		return PerformSQLUtil.get("findSumArea", this);
	}

//	public HospDetailMgr getHospDetailManager() {
//		return hospDetailManager;
//	}
//
//	public void setHospDetailManager(HospDetailMgr hospDetailManager) {
//		this.hospDetailManager = hospDetailManager;
//	}
	
	
}
