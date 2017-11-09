package com.hanqian.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbService;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;

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
 * @see
 */
@Service("seriverBusiness")
public class SeriverBusiness   extends BaseBusiness{
	/**
	 * log4g日志
	 */
	//private static final Log logg = LogFactory.getLog(SeriverBusiness.class);
	public static final Logger logg = Logger.getLogger(SeriverBusiness.class);
	//@Resource
	//private ServicerBusinessMgr businessMgr;
	@Autowired
	private SermainDetailBsiness serDetailBussiness;
	/**
	 * @return the businessMgr
	 */
//	public ServicerBusinessMgr getBusinessMgr() {
//		return businessMgr;
//	}

	/**
	 * @param businessMgr the businessMgr to set
	 */
//	public void setBusinessMgr(ServicerBusinessMgr businessMgr) {
//		this.businessMgr = businessMgr;
//	}
	
	/**
	 * 获取服务商信息列表
	 * @param 2012-12-6  
	 * @param        
	 * @return void
	 */
	public RetCode findServicerList(String serName,String serType,Integer currentPage,Integer pageSize){
		//return businessMgr.findServicerList(serName,serType,currentPage,pageSize);
		if (logg.isDebugEnabled())
			logg.debug("进入SeriverBusiness.findServicerList,参数serName:"
					+ serName+" serType:"+serType+" currentPage:"+currentPage+" pageSize:"+pageSize);
		Map map = new HashMap();
		if (StringUtil.isNotEmpty(serName)&&StringUtil.isNotEmpty(serName.trim())) {
			//strBuff.append(" and s.servname like  '%"+serName+"%' ");
			map.put("serName", serName);
		}else{
			map.put("serName", null);
		}
		if (StringUtil.isNotEmpty(serType)) {
			//strBuff.append(" and s.servtype =  "+Integer.parseInt(serType));
			map.put("serType", Integer.parseInt(serType));
		}else{
			map.put("serType", null);
		}
		map.put("opertype", SysUtil.BASE_COMM_SERVICE_TYPE);
		return this.getPageData("findServicerList", map, currentPage, pageSize);
		
	}
	
	public Map getFindServicerListSql() {
		return PerformSQLUtil.get("findServicerList", this);
	}
	/**
	 * 查询服务商类型
	 * @param 2012-12-6  
	 * @param @return       
	 * @return RetCode
	 */
	public List<DbBaseComm> findServiceTypeList(){
		if (logg.isDebugEnabled())
			logg.debug("进入SeriverBusiness.findServiceTypeList!");
		List<DbBaseComm> serviceTypeList = new ArrayList<DbBaseComm>();
		//RetCode rc =businessMgr.findServiceTypeList();
		Map mapvalue = new HashMap();
		mapvalue.put("opertype", SysUtil.BASE_COMM_SERVICE_TYPE);
		RetCode rc = this.getData("findServiceTypeList", mapvalue);
		
		if (rc != null && rc.getObj() != null) {
			List list = (List)rc.getObj();
			DbBaseComm dbBaseComm =null;
			for (int i = 0; i < list.size(); i++) {
				dbBaseComm = new DbBaseComm();
				Map map = (Map) list.get(i);
				dbBaseComm.setContent1(map.get("content1")==null?"":map.get("content1").toString());
				dbBaseComm.setSeq(Integer.parseInt(map.get("seq").toString()));
				dbBaseComm.setContent2(map.get("content2")==null?"":map.get("content2").toString());
				serviceTypeList.add(dbBaseComm);
			}
		}
		return serviceTypeList;
	}
	
	public Map getFindServiceTypeListSql() {
		return PerformSQLUtil.get("findServiceTypeList", this);
	}
	/**
	 * 查询服务商
	 * @param 2012-12-6  
	 * @param @return       
	 * @return RetCode
	 */
	public List<DbService> findServiceList(){
		if (logg.isDebugEnabled())
			logg.debug("进入SeriverBusiness.findServiceList!");
		List<DbService> serviceList = new ArrayList<DbService>();
		//RetCode rc =businessMgr.findServiceList();
		
		RetCode rc = this.getData("findServiceList", null);
		if (rc != null && rc.getObj() != null) {
			List list = (List)rc.getObj();
			DbService dbService =null;
			for (int i = 0; i < list.size(); i++) {
				dbService = new DbService();
				Map map = (Map) list.get(i);
				dbService.setServname(map.get("servname")==null?"":map.get("servname").toString());
				dbService.setSeq(Integer.parseInt(map.get("seq").toString()));
				serviceList.add(dbService);
			}
		}
		return serviceList;
	}
	
	public Map getFindServiceListSql() {
		return PerformSQLUtil.get("findServiceList", this);
	}
	/**
	 * 更新
	 * @param 2012-12-7  
	 * @param        
	 * @return boolean
	 */
	public boolean updateService(DbService service){
//		int flag = businessMgr.updateService(service);
//		if (flag==0) {
//			return true;
//		}
//		return false;
		if (logg.isDebugEnabled())
			logg.debug("进入SeriverBusiness.updateService!对象DbService:"+service);
		try{
			sqlSession.update("updateService", service);
		 	return true;
		}catch (Exception e) {
			logg.error("SeriverBusiness-->updateService", e);
			return false;
		}
		
	}
	
	public Map getUpdateServiceSql() {
		return PerformSQLUtil.get("updateService", this);
	}
	/**
	 *新增
	 * @param 2012-12-7  
	 * @param        
	 * @return boolean
	 */
	public boolean addService(DbService service){
//		int flag = businessMgr.insertService(service);
//		if (flag==0) {
//			return true;
//		}
//		return false;
		if (logg.isDebugEnabled())
			logg.debug("进入SeriverBusiness.addService!对象DbService:"+service);
		try{
			sqlSession.insert("insertService", service);
		 	return true;
		}catch (Exception e) {
			e.printStackTrace();
			logg.error("SeriverBusiness-->insertService", e);
			return false;
		}
	}
	
	public Map getAddServiceSql() {
		return PerformSQLUtil.get("insertService", this);
	}
	
	/**
	 * 
	 * @param seq
	 * @return
	 */
	public DbService findEntityById(Integer seq){
		//return businessMgr.findEntityById(seq);
		if (logg.isDebugEnabled())
			logg.debug("进入SeriverBusiness.findEntityById!参数seq:"+seq);
		DbService dbService =sqlSession.selectOne("findEntityById", seq);
		return dbService;
	}
	
	public Map getFindEntityByIdSql() {
		return PerformSQLUtil.get("findEntityById", this);
	}
	/**
	 * 删除
	 * @param 2012-12-7  修改时间2015-10-10
	 * @param        
	 * @return void
	 * 根据传入的参数，查询出来人员表中对应的seq。在根据seq查询出来和服务表中对应的字段serviceSeq 
	 * 最后在和传入的seq进行比较，如果相等则可以删除，否则无效
	 * 
	 */
	public boolean deleteService(Integer seq){
		boolean bool = false;
		try{
			//根据服务商ID查找维修人员
			RetCode rc = serDetailBussiness.findSerMainListBySerId(seq);
			if(rc!=null&&rc.getObj()!=null){
				List list = (List)rc.getObj();
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map)list.get(i);
					if (map.get("seq")!=null) {
						RetCode rcId = serDetailBussiness.findRenyuanId(Integer.parseInt(map.get("seq").toString()));
						List listSerMain = (List)rcId.getObj();
						Map mapSerMain = (Map)listSerMain.get(0);
						if(seq==Integer.parseInt(mapSerMain.get("serviceSeq").toString())){
							bool =	serDetailBussiness.deleteMen(map.get("seq").toString());
						}
					}
				}
			}
			
			
			try{
			DbService ser = new DbService();
			if (ser.getSeq()==null) {
				ser.setSeq(seq);
			}
			
			sqlSession.delete("deleteService",ser);
			//serviceDAO.delete(ser);
			bool = true;
			}catch (Exception e) {
				logg.error("SeriverBusiness-->deleteService", e);
				bool =false;
			}
			return bool;
			
			//bool = businessMgr.deleteService(seq);
		}catch (Exception e) {
			logg.error("SeriverBusiness-->deleteService", e);
			bool =false;
		}
		return bool;
	}
	
	public Map getDeleteServiceSql() {
		return PerformSQLUtil.get("deleteService", this);
	}
	
	public boolean validateIsUnique(String serName,Integer seq){
		return  this.validateIsUniqueMethod(serName,seq);
	}
	
	public boolean validateIsUniqueMethod(String serName,Integer seq){
		boolean bool = false;
		try{
			Map map = new HashMap();
			map.put("serName", serName);
			if (seq!=null&&seq!=0) {
				map.put("seq", seq);
			}else{
				map.put("seq", null);
			}
			
			//RetCode rc = this.findBySQL(serviceDAO, sql);
			RetCode rc = this.getData("validateIsUnique", map);
			if(rc==null||rc.getObj()==null){
				bool = true;
			}else{
				List l = (List)rc.getObj();
				if(l.size()>0){
					bool = false;
				}else{
					bool = true;
				}
			}
		
		}catch (Exception e) {
			logg.error("ServicerBusinessMgr-->validateIsUnique", e);
		}
		return bool;
	}
	
	public Map getValidateIsUniqueSql() {
		return PerformSQLUtil.get("validateIsUnique", this);
	}


	/**
	 * @return the serDetailBussiness
	 */
	public SermainDetailBsiness getSerDetailBussiness() {
		return serDetailBussiness;
	}

	@Autowired
	public void setSerDetailBussiness(SermainDetailBsiness serDetailBussiness) {
		this.serDetailBussiness = serDetailBussiness;
	}
}
