package com.hanqian.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanqian.pojo.DbSermain;
import com.hanqian.pojo.DbSermainDetail;
import com.hanqian.pojo.DbSermainRepair;
import com.hanqian.pojo.DbService;
import com.hanqian.util.DateUtil;
import com.hanqian.util.Page;
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
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @version 1.4 2012-12-7
 * @see
 */
@Service("sermainDetailBusiness")
public class SermainDetailBsiness   extends BaseBusiness{
	/**
	 * log4g日志
	 */

	public static final Logger logg = Logger.getLogger(SermainDetailBsiness.class);
	

	//获取所有维修人员
	public List findSerMainAll(){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findSerMainAll!");
		

		RetCode rc= this.getData("findSerMainAll", null);
		if(null!=rc && null!=rc.getObj()){
			return (List)rc.getObj();
		}
		return null;
	}
	
	public Map getFindSerMainAllSql() {
		return PerformSQLUtil.get("findSerMainAll", this);
	}
	
	//获取 维修班组内所有人员
	public List findSerMainUserAll(String serMainId){
		
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findSerMainUserAll!参数serMainId:"+serMainId);
		

		Map map = new HashMap();
		map.put("serMainId", serMainId);
		
		RetCode rc= this.getData("findSerMainUserAll", map);
		
		if(null!=rc && null!=rc.getObj()){
			return (List)rc.getObj();
		}

		return null;
	}
	
	public Map getFindSerMainUserAllSql() {
		return PerformSQLUtil.get("findSerMainUserAll", this);
	}
	
	//获取尺维修班组内 的其他所有人员
	public List findSerMainNotUserAll(String serMainId){
		
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findSerMainNotUserAll!参数serMainId:"+serMainId);
		

		Map map = new HashMap();
		map.put("serMainId", serMainId);
		
		RetCode rc= this.getData("findSerMainNotUserAll", map);
		
		if(null!=rc && null!=rc.getObj()){
			return (List)rc.getObj();
		}

		return null;
	}
	
	public Map getFindSerMainNotUserAllSql() {
		return PerformSQLUtil.get("findSerMainNotUserAll", this);
	}
	
	//获取所有维修人员分页
	public RetCode findSerMainAll(int  pageSize,int currentPage){
		
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findSerMainAll!参数pageSize:"+pageSize+" currentPage:"+currentPage);
		

		RetCode rc= this.getPageData("findSerMainAll", null, currentPage, pageSize);
		return rc;
	}
	

	

	//获取维修人员分页
	@SuppressWarnings("unchecked")
	public RetCode findSerMainList(String name ,String serBusinessId,String serClassId ,int currentPage,int  pageSize){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findSerMainList!参数pageSize:"+pageSize+" currentPage:"+currentPage);
		Map mapvalue = new HashMap();
		mapvalue.put("opertype", SysUtil.BASE_COMM_CLASS_TYPE);

		if (StringUtil.isNotEmpty(name)&&StringUtil.isNotEmpty(name.trim())) {
		
			mapvalue.put("name", name.trim());
		}else{
			mapvalue.put("name", null);
		}
		if (StringUtil.isNotEmpty(serBusinessId)&&StringUtil.isNotEmpty(serBusinessId.trim())) {
			
			mapvalue.put("serBusinessId", serBusinessId.trim());
		}else{
			mapvalue.put("serBusinessId", null);
		}
		if (StringUtil.isNotEmpty(serClassId)&&StringUtil.isNotEmpty(serClassId.trim())) {
			
			mapvalue.put("serClassId", serClassId.trim());
		}else{
			mapvalue.put("serClassId", null);
		}

		RetCode rc=this.getData("findSerMainList", mapvalue);	
		RetCode rtc = new RetCode();
		List menList = new ArrayList();
		if (rc!=null&& rc.getObj()!=null) {
			List list = (List)rc.getObj();
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map)list.get(i);
				HashMap menMap = new HashMap();
				menMap.put("content1", map.get("content1")==null?"":map.get("content1").toString());
				menMap.put("mem_mobtele",map.get("mem_mobtele")==null?"":map.get("mem_mobtele").toString());
				menMap.put("mem_name",map.get("mem_name")==null?"":map.get("mem_name").toString());
				menMap.put("servname",map.get("servname")==null?"":map.get("servname").toString());
				menMap.put("seq",map.get("seq")==null?0:Integer.parseInt(map.get("seq").toString()));
				menMap.put("mem_tele",map.get("mem_tele")==null?"":map.get("mem_tele").toString());
				if (menList.isEmpty()||menList==null||menList.size()<1) {
					menList.add(menMap);
				}else{
					int flag = 0 ;
					for (int j = 0; j < menList.size(); j++) {
						HashMap inmap = (HashMap)menList.get(j);
						if (inmap.get("seq").toString().equals(map.get("seq").toString())) {
							inmap.put("content1", map.get("content1")==null?inmap.get("content1"):inmap.get("content1")==null?map.get("content1").toString():inmap.get("content1")+";"+map.get("content1").toString());
							break;
						}else{
							flag++;
							if (flag==menList.size()) {
								menList.add(menMap);
								break;
							}
							
						}
					}
				}
				
				
			}
			
			rtc.setPage(new Page(currentPage,menList.size(),pageSize));
			List endList = new ArrayList();
			if (menList.size()>=currentPage) {
/**
 * 修改“维修人员”分页错误问题
 * 
 * 2015-07-16
 * 
 */
//				if(menList.size()%pageSize==0&&currentPage>1){
//					currentPage--;
//				}
				for (int i = (currentPage - 1) * pageSize; i < (menList.size()<pageSize*currentPage?menList.size():pageSize*currentPage); i++) {
					endList.add(menList.get(i));
				}
			}
			rtc.setObj(endList);
		}
		return rtc;
	}
	
	
	public Map getFindSerMainListSql() {
		return PerformSQLUtil.get("findSerMainList", this);
	}
	/**
	 * 根据服务商查找维修人员信息
	 * @param 2012-12-11  
	 * @param @param serBusinessId
	 * @param @return       
	 * @return RetCode
	 */
	public RetCode findSerMainListBySerId(Integer serBusinessId){
		
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findSerMainListBySerId!参数serBusinessId:"+serBusinessId);
		Map mapvalue = new HashMap();
		mapvalue.put("opertype", SysUtil.BASE_COMM_CLASS_TYPE);
		if (!StringUtil.isEmpty(serBusinessId)&&!StringUtil.isEmpty(serBusinessId)) {
			mapvalue.put("serBusinessId", serBusinessId);
		}
		RetCode rc= this.getData("findSerMainListBySerId", mapvalue);
		RetCode rtc = new RetCode();
		List menList = new ArrayList();
		if (rc!=null&& rc.getObj()!=null) {
			List list = (List)rc.getObj();
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map)list.get(i);
				HashMap menMap = new HashMap();
				menMap.put("seq",map.get("seq")==null?0:Integer.parseInt(map.get("seq").toString()));
				if (menList.isEmpty()||menList==null||menList.size()<1) {
					menList.add(menMap);
				}else{
					int flag = 0 ;
					for (int j = 0; j < menList.size(); j++) {
						HashMap inmap = (HashMap)menList.get(j);
						if (inmap.get("seq").toString().equals(map.get("seq").toString())) {
							inmap.put("content1", map.get("content1")==null?inmap.get("content1"):inmap.get("content1")==null?map.get("content1").toString():inmap.get("content1")+";"+map.get("content1").toString());
							break;
						}else{
							flag++;
							if (flag==menList.size()) {
								menList.add(menMap);
								break;
							}
							
						}
					}
				}
				
				
			}
			rtc.setObj(menList);
		}
		return rtc;
	}
	
	public Map getFindSerMainListBySerIdSql() {
		return PerformSQLUtil.get("findSerMainListBySerId", this);
	}
	
	/*
	 * 
	 * 根据人员ID查询出，对应的服务商ID
	 * */
	public RetCode findRenyuanId(Integer  sermainId){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findRenyuanId!参数serMainId:"+ sermainId);
		
		Map map = new HashMap();
		map.put("sermainId",  sermainId);
		
		RetCode rc= this.getData("findSerMainNotIds", map);
		
		if(null!=rc && null!=rc.getObj()){
			return rc;
		}
		return null;
	}
	
	
	/**
	 * 删除维修人员
	 * @param seq
	 * @return
	 */
	@Transactional
	public boolean deleteMen(String seq){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.deleteMen!参数seq:"+seq);
		boolean bool = false;
		Transaction trans = null;
		try{
		//先删除班组与人员的关系

			boolean tempBool = false;
			//查询

			Map parammap = new HashMap();
			parammap.put("seq", seq);
			RetCode rc = this.getData("deleteMenFindDbSermainDetail", parammap);
			

			try {
				sqlSession.delete("deleteMenSerRepairStr", Integer.parseInt(seq));
				tempBool = true;
			} catch (Exception e) {
				e.printStackTrace();
				tempBool = false;
			}
			
			if(tempBool){

				try {
					sqlSession.delete("deleteMenSerUserStr", seq);
					tempBool = true;
				} catch (Exception e) {
					e.printStackTrace();
					tempBool = false;
				}
				
				if(tempBool){

					try {
						sqlSession.delete("deleteMenSerDetailStr", seq);
						tempBool = true;
					} catch (Exception e) {
						tempBool = false;
					}
					
				}
			}
			bool = tempBool;
			if(tempBool){
				
			}else{
				trans.rollback();

				RetCode tempRC =  this.getData("deleteMenFindDbSermainDetail", parammap);
				if (tempRC!=null&&tempRC.getObj()!=null) {
				}else{
					if (rc!=null&&rc.getObj()!=null) {
						List list = (List)rc.getObj();
						if (list!=null&&!list.isEmpty()&&list.size()>0) {
							for (int i = 0; i < list.size(); i++) {
								Map map = (Map)list.get(i);

								DbSermainRepair dbSermainRepair = new DbSermainRepair();
								
								dbSermainRepair.setRepairMan(Integer.valueOf(seq));
								dbSermainRepair.setRepairTeam(Integer.valueOf(map.get("reteam").toString()));
								sqlSession.insert("insertSerRepairAddStr",dbSermainRepair);
							}
						}
					}
				}
			}
		}catch (Exception e) {
			org.jfree.util.Log.error("SermainDetailBsiness-->deleteMen", e);
			e.printStackTrace();
			
			bool = false;
		}
		return bool;
	}
	/**
	 * 通过维修人员id获取所在班组信息
	 * @param 2012-12-10  
	 * @param @param seq       
	 * @return void
	 */
	public RetCode findSerMainByMenId(String seq){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findSerMainByMenId!参数seq:"+seq);
		Map mapvalue = new HashMap();
		mapvalue.put("opertype", SysUtil.BASE_COMM_CLASS_TYPE);
		mapvalue.put("seq", seq);

		return this.getData("findSerMainByMenId", mapvalue);
	}
	
	public Map getFindSerMainByMenIdSql() {
		return PerformSQLUtil.get("findSerMainByMenId", this);
	}
	/**
	 * 通过维修人员id获取所在班组ID信息
	 * @param 2012-12-10  
	 * @param @param seq       
	 * @return String
	 */
	public List findClassIdByMenId(String seq){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findClassIdByMenId!参数seq:"+seq);
		Map mapvalue = new HashMap();
		mapvalue.put("opertype", SysUtil.BASE_COMM_CLASS_TYPE);
		mapvalue.put("seq", seq);
		String str = "";

		//RetCode rc =  this.getData("findClassIdByMenId", mapvalue);
		RetCode rc =  this.getData("findSerMainByMenId", mapvalue);
		if(null!=rc && null!=rc.getObj()){
			return (List)rc.getObj();
		}
		return null;
		/*
		 * 新修改返回值为List不是Sting
		 * 
		 * Date 2015-07-15
		 * 
		 * 
		if (rc!=null&&rc.getObj()!=null) {
	    	List list = (List)rc.getObj();
	    	if (list!=null&&!list.isEmpty()&&list.size()>0) {
	    		for (int j = 0; j < list.size(); j++) {
	    			Map map = (Map)list.get(j);
	    			str = map.get("seq")==null?"":StringUtil.isEmpty(str)?map.get("seq").toString():str+","+map.get("seq");
				}
			}
		}
		return str;*/
	}
	
	public Map getFindClassIdByMenIdSql() {
		return PerformSQLUtil.get("findClassIdByMenId", this);
	}
	
	/**
	 * 根据id查找维修人员信息
	 * @param 2012-12-10  
	 * @param @param seq
	 * @param @return       
	 * @return DbSermainDetail
	 */
	public DbSermainDetail findMenDetailByMenId(String seq){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findMenDetailByMenId!参数seq:"+seq);
		Map mapvalue = new HashMap();		
		mapvalue.put("seq", seq);
		DbSermainDetail deSermainDetail = new DbSermainDetail();

		RetCode rc = this.getData("findMenDetailByMenIdDbSermainDetail", mapvalue);
		if (rc!=null&&rc.getObj()!=null) {
			List list = (List)rc.getObj();
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map)list.get(i);
				deSermainDetail.setSeq(map.get("seq")==null?0:Integer.parseInt(map.get("seq").toString()));
				deSermainDetail.setMemName(map.get("mem_name")==null?"":map.get("mem_name").toString());
				deSermainDetail.setMemMobtele(map.get("mem_mobtele")==null?"":map.get("mem_mobtele").toString());
				deSermainDetail.setMemTele(map.get("mem_tele")==null?"":map.get("mem_tele").toString());
				deSermainDetail.setInput(map.get("input")==null?0:Integer.parseInt(map.get("input").toString()));
				deSermainDetail.setOper(map.get("oper")==null?0:Integer.parseInt(map.get("oper").toString()));
				deSermainDetail.setOpertime(map.get("opertime")==null?null:DateUtil.parseDate(map.get("opertime").toString(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
				deSermainDetail.setInputtime(map.get("inputtime")==null?null:DateUtil.parseDate(map.get("inputtime").toString(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
				
				DbService service  = new DbService();
				service.setSeq(map.get("service_seq")==null?0:Integer.parseInt(map.get("service_seq").toString()));
				DbSermain dbSermain = new DbSermain();
				dbSermain.setDbService(service);
				deSermainDetail.setDbSermain(dbSermain);
			}
		}
		return deSermainDetail;
	}
	
	public Map getFindMenDetailByMenIdSql() {
		return PerformSQLUtil.get("findMenDetailByMenIdDbSermainDetail", this);
	}
	/**
	 * 
	 * 
	 * 
	 * 新添加人员信息时，获取所有的班组信息
	 * 
	 * @param 2015-07-06
	 * @param @param seq
	 * @param @return       
	 * @return List
	 */
	public List findClassBanzuAll(){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findClassBanzuAll!");
		Map mapvalue= new HashMap();
		mapvalue.put("opertype", SysUtil.BASE_COMM_CLASS_TYPE);
		RetCode rc= this.getData("findClassBanzuAll", mapvalue);
		if(null!=rc && null!=rc.getObj()){
			return (List)rc.getObj();
		}
		return null;
	}
	//获取所有维修人员
	public List findClassAll(String seq){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.findClassAll!");

		Map mapvalue= new HashMap();
		mapvalue.put("opertype", SysUtil.BASE_COMM_CLASS_TYPE);
		mapvalue.put("seq", seq);
		RetCode rc= this.getData("findClassAllDbSermainDetail", mapvalue);
		if(null!=rc && null!=rc.getObj()){
			return (List)rc.getObj();
		}
		return null;
	}
	
	public Map getFindClassAllSql() {
		return PerformSQLUtil.get("findClassAllDbSermainDetail", this);
	}
	
	
	
	/**
	 * 新增维修班组
	 * @param sermainDetail
	 * @param classId
	 * @return
	 */
	@Transactional
	public boolean insertMenInfo(DbSermainDetail sermainDetail,String classId){
		if (logg.isDebugEnabled())
			logg.debug("进入SermainDetailBsiness.insertMenInfo!参数DbSermainDetail:"+sermainDetail+" classId:"+classId);
		///先插入信息，再分配班组
		boolean bool = false;
		Transaction trans = null;
		try{

			DbSermainDetail dbSermainDetail = new DbSermainDetail();
			dbSermainDetail.setMemName(sermainDetail.getMemName());
			dbSermainDetail.setMemMobtele(sermainDetail.getMemMobtele());
			dbSermainDetail.setMemTele(sermainDetail.getMemTele());
			DbSermain dbSermain = new DbSermain();
			DbService dbService = new DbService();
			dbService.setSeq(sermainDetail.getDbSermain().getDbService().getSeq());
			dbSermain.setDbService(dbService);
			dbSermainDetail.setDbSermain(dbSermain);
			dbSermainDetail.setInput(sermainDetail.getInput());
			
			sqlSession.insert("insertDbSermainDetail", dbSermainDetail);
//			logg.debug(">>>>>>>>>>>         dbSermainDetailID:    "+dbSermainDetail.getSeq());
			//然后加入现在的班组关系
			if (StringUtil.isNotEmpty(classId)&&StringUtil.isNotEmpty(classId.trim())) {
				String [] repairId = classId.split(",");
				for (int i = 0; i < repairId.length; i++) {
					
					DbSermainRepair dbSermainRepair = new DbSermainRepair();
					dbSermainRepair.setRepairMan(Integer.valueOf(dbSermainDetail.getSeq()));
					dbSermainRepair.setRepairTeam(Integer.valueOf(repairId[i]));
					sqlSession.insert("insertSerRepairAddStr",dbSermainRepair);
					
				}
				
			}

			bool = true;
		}catch (Exception e) {
			org.jfree.util.Log.error("SermainDetailBsiness-->insertMenInfo", e);
			e.printStackTrace();
			
			bool = false;
		}
		return bool;
	}
	
	public Map getInsertMenInfoSql() {
		return PerformSQLUtil.get("insertDbSermainDetail", this);
	}
	/**
	 * classId;分隔
	 * @param 2012-12-10  
	 * @param @param sermainDetail
	 * @param @param classId
	 * @param @return       
	 * @return boolean
	 */
	@Transactional
	public boolean updateMenInfo(DbSermainDetail sermainDetail,String classId){
		boolean bool = false;
		Transaction trans = null;
		try{
		//先删除班组与人员的关系

			int seq= sermainDetail.getSeq();
			
			/* 
			 * 1.根据sermainDetail.getSeq()获取告警等级
			 * 2.根据sermainDetail.getSeq()删除
			 * 3.根据sermainDetail.getSeq()添加该信息
			 * 
			 */
			List totalsMap=null;
			try {
					Map mapRepairLevel = new HashMap();
					String sermainDetailGetSeq = sermainDetail.getSeq().toString();
					mapRepairLevel.put("sermainDetailSeq", sermainDetailGetSeq);
					RetCode rc = this.getData("updateMenInfoRepairLevelss",mapRepairLevel);
					totalsMap= (List)rc.getObj();
			} catch (Exception e) {
				logg.error("db_sermain_repair中无数据",e);
			}
			sqlSession.delete("deleteMenSerRepairStr", seq);
			//然后加入现在的班组关系
			if (StringUtil.isNotEmpty(classId)&&StringUtil.isNotEmpty(classId.trim())) {
				String [] repairId = classId.split(",");
				for (int i = 0; i < repairId.length; i++) {
					DbSermainRepair dbSermainRepair = new DbSermainRepair();
					if(totalsMap!=null){
							for(Object obj : totalsMap){
								Map map = (Map)obj;
								if(map.get("repair_team")!=null){
										if((map.get("repair_team").toString()).equals(repairId[i])){
											if(map.get("repairlevel")!=null){
											    dbSermainRepair.setRepairLevel((String)map.get("repairlevel"));
											}else{
												dbSermainRepair.setRepairLevel("");
											}
										}
								}
							}
					}
					dbSermainRepair.setRepairMan(Integer.valueOf(sermainDetail.getSeq()));
					dbSermainRepair.setRepairTeam(Integer.valueOf(repairId[i]));
					sqlSession.insert("insertSerRepairAddStr",dbSermainRepair);
				}
				
			}
			/**
			 * 
			if (StringUtil.isNotEmpty(classId)&&StringUtil.isNotEmpty(classId.trim())) {
				String [] repairId = classId.split(",");
				for (int i = 0; i < repairId.length; i++) {
					int seqNum = findMaxSerMain()+1;
					String serRepairAddStr ="";
					判断关系表是否有值，有值添加，没值就添加空
					if(totalsMap!=null){
							for(Object obj : totalsMap){
								Map map = (Map)obj;
								if(map.get("repair_team")!=null){
									if((map.get("repair_team").toString()).equals(repairId[i])){
										serRepairAddStr="insert into DB_SERMAIN_REPAIR values ("+seqNum+"," +sermainDetail.getSeq()+","+repairId[i]+",'"+map.get("repairlevel")+"')";
									}else{
										serRepairAddStr="insert into DB_SERMAIN_REPAIR values ("+seqNum+"," +sermainDetail.getSeq()+","+repairId[i]+",'')";
									}
								}else{
									serRepairAddStr="insert into DB_SERMAIN_REPAIR values ("+seqNum+"," +sermainDetail.getSeq()+","+repairId[i]+",'')";
								}
							}
					}else{
						serRepairAddStr="insert into DB_SERMAIN_REPAIR values ("+seqNum+"," +sermainDetail.getSeq()+","+repairId[i]+",'')";
					}
					logg.info("人员维修中修改功能中添加班组人员关系SQL--"+serRepairAddStr);
				    sermainDetailMgr.insertBySQL(sermainDetailMgr.getDbSermainDetailDAO(), serRepairAddStr);
				}
			}
	          */
			DbSermainDetail dbSermainDetail = new DbSermainDetail();
			dbSermainDetail.setSeq(sermainDetail.getSeq());
			dbSermainDetail.setMemName(sermainDetail.getMemName());
			dbSermainDetail.setMemMobtele(sermainDetail.getMemMobtele());
			dbSermainDetail.setMemTele(sermainDetail.getMemTele());
			DbSermain dbSermain = new DbSermain();
			DbService dbService = new DbService();
			dbService.setSeq(sermainDetail.getDbSermain().getDbService().getSeq());
			dbSermain.setDbService(dbService);
			dbSermainDetail.setDbSermain(dbSermain);
			dbSermainDetail.setOper(sermainDetail.getOper());
			sqlSession.update("updateMenInfoDbSermainDetail", dbSermainDetail);
			bool = true;
		}catch (Exception e) {
			org.jfree.util.Log.error("SermainDetailBsiness-->updateMenInfo", e);
			e.printStackTrace();
		
			bool = false;
		}
		return bool;
		
	}

	
	public int isExist(String userId){

		Map mapvalue = new HashMap();
		mapvalue.put("userId", userId);
		RetCode rc = this.getData("isExistSermainDetail", mapvalue);
		if(rc!=null&&rc.getObj()!=null){
			List list = (List)rc.getObj();
			if (list!=null&&!list.isEmpty()) {
				return list.size();
			}
		}
		return 0;
	}

	
}
