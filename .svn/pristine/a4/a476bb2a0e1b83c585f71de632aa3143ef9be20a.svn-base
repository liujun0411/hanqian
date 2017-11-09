package com.hanqian.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbBaseType;
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
 * @author wyy
 * @version 1.4 2012-12-6
 * @see
 */
@Service("serMainBusiness")
public class SerMainBusiness  extends BaseBusiness{
	// 日志
	//private static final Log log = LogFactory.getLog(SerMainBusiness.class);
	public static final Logger log = Logger.getLogger(SerMainBusiness.class);
//	@Resource
//	private SerMainMgr serMainMgr;
	
	//查询短信告警间隔
	public List findALertLevelIntervalDate() {
//		String sql="select * from DB_ALERTLEVEL_INTERVAL_DATE d order by d.alertlevel asc";
//		if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->findALertLevelIntervalDate)SQL="+sql);
//		RetCode rc=serMainMgr.findBySQL(serMainMgr.getDbBaseCommDAO(), sql);
		
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.findALertLevelIntervalDate!");
		RetCode rc= this.getData("findALertLevelIntervalDate", null);
		
		List lst=(List)rc.getObj();
		
		return lst;
	}	
	
	public Map getFindALertLevelIntervalDateSql() {
		return PerformSQLUtil.get("findALertLevelIntervalDate", this);
	}
	
	
	//修改短信告警
	public boolean updateALertLevelIntervalDate(String level1,String level2,String level3){
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.updateALertLevelIntervalDate!参数level1:"+level1+" level2:"+level2+" level3:"+level3);
		try {
//			String sql1="update DB_ALERTLEVEL_INTERVAL_DATE d set d.interval_date="+level1+" where d.alertlevel=1";
//			String sql2="update DB_ALERTLEVEL_INTERVAL_DATE d set d.interval_date="+level2+" where d.alertlevel=2";
//			String sql3="update DB_ALERTLEVEL_INTERVAL_DATE d set d.interval_date="+level3+" where d.alertlevel=3";
//			if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->updateALertLevelIntervalDate)SQL="+sql1);
//			if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->updateALertLevelIntervalDate)SQL="+sql2);
//			if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->updateALertLevelIntervalDate)SQL="+sql3);
//			//修改等级1
//			serMainMgr.updateBySQL(serMainMgr.getDbBaseCommDAO(), sql1);
//			//修改等级2
//			serMainMgr.updateBySQL(serMainMgr.getDbBaseCommDAO(), sql2);
//			//修改等级3
//			serMainMgr.updateBySQL(serMainMgr.getDbBaseCommDAO(), sql3);
			
			sqlSession.update("updateALertLevelIntervalDate1", level1);
			sqlSession.update("updateALertLevelIntervalDate2", level2);
			sqlSession.update("updateALertLevelIntervalDate3", level3);
			return true;
		} catch (Exception e) {
			log.error("SerMainBusiness-->updateALertLevelIntervalDate", e);
			e.printStackTrace();
			return false;
		}
 
	}
	

	
	/**
	 * 查询班组
	 * @param 2012-12-6  
	 * @param @return       
	 * @return List
	 */
	public RetCode findSerMain(String groupName,int pageSize,int currentPage){
//		StringBuffer sb=new StringBuffer("select c.seq,c.content1 as  name," +
//				"(select  count(*) from DB_SERMAIN_REPAIR sr where sr.repair_team=c.seq) as serviceCount," +
//				"(select count(*) from db_equip_sermain es where es.team_id=c.seq ) as equipCount " +
//				"from db_base_comm c where c.oper_type=42 and  " +
//				"c.delet_flag=0   ");
		
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.findSerMain!参数groupName:"+groupName+" pageSize:"+pageSize+" currentPage:"+currentPage);
		Map map = new HashMap();
		
		if(null!= groupName && !"".equals(groupName)){
			map.put("groupName", groupName);
		}else{
			map.put("groupName", null);
		}
		
//		RetCode rc=serMainMgr.findPageBySql(serMainMgr.getDbBaseCommDAO(),
//				sb.toString(),currentPage, pageSize);
		RetCode rc= this.getPageData("findSerMain", map, currentPage, pageSize);
		return rc;
	}
	
	
	public Map getFindSerMainSql() {
		return PerformSQLUtil.get("findSerMain", this);
	}
	/**
	 * 
	 * 
	 * 查询已发送短信列表
	 * @param 2015-11-12
	 * @param @return       
	 * @return RetCode
	 * 
	 * 
	 */
	public RetCode findSerCueLog(String name,String menName,String sendTime,int pageSize,int currentPage){
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.findSerCueLog!参数userId:"+name+";cueId:"+menName+";sendTime"+sendTime+";pageSize:"+pageSize+" currentPage:"+currentPage);
		Map map = new HashMap();
		if(null!= name && !"".equals(name)){
			map.put("name", name);
		}else{
			map.put("name", null);
		}
		if(null!= menName && !"".equals(menName)){
			map.put("menName", menName);
		}else{
			map.put("menName", null);
		}
		if(null!= sendTime && !"".equals(sendTime)){
			map.put("sendTime", sendTime);
		}else{
			map.put("sendTime", null);
		}
		RetCode rc= this.getPageData("findSerCueLog", map,currentPage, pageSize);
		return rc;
	}
	public Map getFindSerCueLogSql() {
		return PerformSQLUtil.get("findSerCueLog", this);
	}
	/*
	 * 查找班组(班组下拉框的数据)
	 */
	public List<DbBaseComm> findClassList(){
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.findClassList!");
		Map mapvalue = new HashMap();
		mapvalue.put("opertype", SysUtil.BASE_COMM_CLASS_TYPE);
		
//		String sql = "select bc.* from db_base_comm bc where bc.delet_flag = 0 and bc.oper_type = "+SysUtil.BASE_COMM_CLASS_TYPE;
//		if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->findClassList)SQL="+sql);
		List<DbBaseComm> classList = new ArrayList<DbBaseComm>();
//		if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->findClassList)SQL="+sql);
//		RetCode rc =serMainMgr.findBySQL(serMainMgr.getDbBaseCommDAO(), sql);
		RetCode rc = this.getData("findClassList", mapvalue);
		
		
		
		if (rc != null && rc.getObj() != null) {
			List list = (List)rc.getObj();
			DbBaseComm dbBaseComm =null;
			for (int i = 0; i < list.size(); i++) {
				dbBaseComm = new DbBaseComm();
				Map map = (Map) list.get(i);
				dbBaseComm.setContent1(map.get("content1")==null?"":map.get("content1").toString());
				dbBaseComm.setSeq(Integer.parseInt(map.get("seq").toString()));
				dbBaseComm.setContent2(map.get("content2")==null?"":map.get("content2").toString());
				classList.add(dbBaseComm);
			}
		}
		return classList;
	}
	
	public Map getFindClassListSql() {
		return PerformSQLUtil.get("findClassList", this);
	}
	
	/**
	 * 添加  ：维修班组， 维修班组与设备关系，维修班组与维修人员
	 * @param 2012-12-10  
	 * @param @param groupName	班组名
	 * @param @param eqId	设备数组ID
	 * @param @param dlId	维修人员ID
	 * @param @return       返回是否成功
	 * @return boolean
	 */
	
	//维修人员  与组SEQ SEQ_SERMAINREPAIR    设备ID与组SEQ_SERMAINDETAIL
	public boolean insertSerMainAll(String groupName,String []eqId,String [] dlId,String repairLevel)
	{
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.insertSerMainAll!参数groupName:"+groupName);
		//添加班组
		DbBaseType type=new DbBaseType();
		type.setSeq(42);
		DbBaseComm sermain=new DbBaseComm();
		sermain.setContent1(groupName);	 //维修班组名称
		sermain.setDbBaseType(type);	//
		sermain.setDeletFlag(0);		//逻辑删除标识
		//添加
		//boolean check=serMainMgr.insertSerMain(sermain);
		boolean check =false;
		
		try {
			sqlSession.insert("insertSerMain", sermain)	;
			check =true;
		} catch (Exception e) {
			//log.debug(e.getMessage());
			e.printStackTrace();
			log.error("SerMainBusiness-->insertSerMain", e);
			check =false;
		}
		
		
		
		
		String groupId=sermain.getSeq().toString();
		
		
		try {
			//如果维修班组添加成功
			if(check){
				//添加维修班组与维修人员
				if(null!=dlId){
					for (int i = 0; i < dlId.length; i++) {
						boolean ck=this.insertSerMainRepalr(groupId, dlId[i],repairLevel);
//						System.out.println(ck+" repalr 添加 "+i);
					}
				}
				
				//添加维修班组与设备关系
				if(null!=eqId){
					for (int i = 0; i < eqId.length; i++) {
						boolean ck=this.insertSerMainEquip(groupId.toString(),eqId[i]);
//						System.out.println(ck+" equip添加 "+i);
					}
				}
			}
		} catch (Exception e) {
			log.error("SerMainBusiness-->insertSerMainAll", e);
			e.printStackTrace();
			log.debug(e.getMessage());
			return false;
		}
		return true;
	}	
	
	public Map getInsertSerMainAllSql() {
		return PerformSQLUtil.get("insertSerMain", this);
	}
 
	
	/**
	 * 添加班组
	 * @param 2012-12-10  
	 * @param @param groupName
	 * @param @return       
	 * @return boolean
	 */
	public boolean insertSerMain(String groupName){
//		String sql="insert into  db_base_comm(seq,content1,oper_type) " +
//				    "   values(seq_basecomm.nextval,'"+groupName+"',42)";
//		if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->insertSerMain)SQL="+sql);
//		int count =serMainMgr.insertBySQL(serMainMgr.getDbBaseCommDAO(), sql);
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.insertSerMain!参数groupName:"+groupName);
		
		int count =sqlSession.insert("insertSerMainString", groupName);
		return count>0?true:false;
	}
	
	public Map getInsertSerMainSql() {
		return PerformSQLUtil.get("insertSerMainString", this);
	}
	
	/**
	 * 添加维修班组与设备 关系表
	 * @param 2012-12-10  
	 * @param @param groupId
	 * @param @param equipId
	 * @param @return       
	 * @return boolean
	 */
	public boolean insertSerMainEquip (String groupId,String equipId){
//		String sql="insert into db_equip_sermain (seq ,equip_id,team_id) values(" +
//				" SEQ_equipSermain.Nextval,'"+equipId+"','"+groupId+"')";
//			
//		if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->insertSerMainEquip)SQL="+sql);
//		int count =serMainMgr.insertBySQL(serMainMgr.getDbBaseCommDAO(), sql);
		
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.insertSerMainEquip!参数groupId:"+groupId+" equipId:"+equipId);
		Map map = new HashMap();
		map.put("groupId", groupId);
		map.put("equipId", equipId);
		int count =sqlSession.insert("insertSerMainEquip", map);
		return count>0?true:false;
		
	}
	
	public Map getInsertSerMainEquipSql() {
		return PerformSQLUtil.get("insertSerMainEquip", this);
	}
	
	
	/**
	 * 获取该维修人员选择的级别
	 * @param 2012-12-10  
	 * @param @param groupId
	 * @param @return       
	 * @return List
	 */
	public List gaojingjibie(String groupId){
		List lst=null;
		
			if (log.isDebugEnabled())
				log.debug("进入SerMainBusiness.toUpdateSerMain!参数groupId:"+groupId);
			Map map = new HashMap();
			map.put("groupId", groupId);
			String sql="select repair_level as repairLevel from DB_SERMAIN_REPAIR where repair_team='"+groupId+"' and rownum='1' ";
			log.info("获取该维修人员选择的级别sql-"+sql);
			//RetCode rc=serMainMgr.findBySQL(serMainMgr.getDbBaseCommDAO(), sql);
			RetCode rc= this.getData("gaojingjibie", map);
			if(null!=rc && null!=rc.getObj()){
				lst=(List)rc.getObj();
			}
		return lst;
	}
	
	
	
	
	
	
	
	/**
	 * 获取该维修组别下的 所有设备
	 * @param 2012-12-10  
	 * @param @param groupId
	 * @param @return       
	 * @return List
	 */
	public List findSerMianEquip(String groupId){
//		String sql="select e.equip_id,e.equip_name from db_equip_list e " +
//				"where e.status=0 and e.equip_id in " +
//				"(select es.equip_id from db_equip_sermain es where es.team_id="+groupId+")";
//		if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->findSerMianEquip)SQL="+sql);
//		RetCode rc=serMainMgr.findBySQL(serMainMgr.getDbBaseCommDAO(), sql);
		
		
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.findSerMianEquip!参数groupId:"+groupId);
		Map map = new HashMap();
		map.put("groupId", groupId);
		RetCode rc= this.getData("findSerMianEquip", map);
		List lst=null;
		if(null!=rc && null!=rc.getObj()){
			lst=(List)rc.getObj();
		}
		return lst;
	}
	
	public Map getFindSerMianEquipSql() {
		return PerformSQLUtil.get("findSerMianEquip", this);
	}
	

	/**
	 * 添加维修班组与维修人员 关系表
	 * @param 2012-12-10  
	 * @param @param groupId
	 * @param @param equipId
	 * @param @return       
	 * @return boolean
	 */
	public boolean insertSerMainRepalr(String groupId,String pepalrId,String repairLevel){
//		String sql="insert into DB_SERMAIN_REPAIR (seq ,repair_man,repair_team) values(" +
//				" SEQ_equipSermain.Nextval,'"+pepalrId+"','"+groupId+"')";
//		if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->insertSerMainRepalr)SQL="+sql);
//		int count =serMainMgr.insertBySQL(serMainMgr.getDbBaseCommDAO(), sql);
		
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.insertSerMainRepalr!参数groupId:"+groupId+" pepalrId:"+pepalrId);
		Map map = new HashMap();
		map.put("groupId", groupId);
		map.put("pepalrId", pepalrId);
		map.put("repairLevel", repairLevel);
		int count =sqlSession.insert("insertSerMainRepalr", map);
		
		return count>0?true:false;
		
	}
	
	public Map getInsertSerMainRepalrSql() {
		return PerformSQLUtil.get("insertSerMainRepalr", this);
	}

	/**
	 * 修改维修班组，以及设备，维修人员关系
	 * @param 2012-12-10  
	 * @param @param groupId
	 * @param @return       
	 * @return boolean
	 */
	public boolean updateSerMainAll(String groupId,String groupName,String []eqId,String [] dlId,String repairLevel){
		try {
			
			if (log.isDebugEnabled())
				log.debug("进入SerMainBusiness.updateSerMainAll!参数groupId:"+groupId+" groupName:"+groupName);
//			System.out.println("修改  ： 班组ID "+groupId);
			//修改班组名
//			String updateGroupName="update db_base_comm c set c.content1='"+groupName+"' where c.seq="+groupId;
//			if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->updateSerMainAll)SQL="+updateGroupName);
			//修改
//			serMainMgr.updateBySQL(serMainMgr.getDbBaseCommDAO(), updateGroupName);
			
			Map mapvalue = new HashMap();
			mapvalue.put("groupId", groupId);
			mapvalue.put("groupName", groupName);
			sqlSession.update("updateSerMainAll", mapvalue);
			
			
			//删除维修人员关系
//			String deleteRepair="delete DB_SERMAIN_REPAIR e where e.repair_team="+groupId;
//			if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->updateSerMainAll)SQL="+deleteRepair);
//			serMainMgr.deleteBySQL(serMainMgr.getDbBaseCommDAO(), deleteRepair);
			
			sqlSession.delete("updateSerMainAllDeleteRepair", groupId);
			
			
			//添加新的关系
			//添加维修班组与维修人员
			if(null!=dlId){
				for (int i = 0; i < dlId.length; i++) {
					boolean ck=this.insertSerMainRepalr(groupId, dlId[i],repairLevel);
				}
			}
			
			//删除设备关系
//			String deleteEquip="delete db_equip_sermain e where e.team_id="+groupId;
//			if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->updateSerMainAll)SQL="+deleteEquip);
//			serMainMgr.deleteBySQL(serMainMgr.getDbBaseCommDAO(), deleteEquip);
			
			sqlSession.delete("updateSerMainAllDeleteEquip", groupId);
			
			
			//添加维修班组与设备关系
			if(null!=eqId){
				for (int i = 0; i < eqId.length; i++) {
					boolean ck=this.insertSerMainEquip(groupId,eqId[i]);
				}
			}
			
			return true;
		} catch (Exception e) {
			log.error("SerMainBusiness-->updateSerMainAll",e);
			e.printStackTrace();
			log.debug(e.getMessage());
			return false;
		}
	}
	
	public Map getUpdateSerMainAllSql() {
		return PerformSQLUtil.get("updateSerMainAllDeleteEquip", this);
	}
	
	
	/**
	 * 逻辑删除 维修班组
	 * @param 2012-12-10  
	 * @param @param groupId
	 * @param @return       
	 * @return boolean
	 */
	public boolean deleteSerMain(String groupId){
//		String sql="update db_base_comm c set c.delet_flag=1 where c.seq="+groupId;
//		if (log.isDebugEnabled()) log.debug("(SerMainBusiness-->deleteSerMain)SQL="+sql);
//		int count =serMainMgr.updateBySQL(serMainMgr.getDbBaseCommDAO(), sql);
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.deleteSerMain!参数groupId:"+groupId);
		int count = sqlSession.update("deleteSerMain", groupId);		
		return count>0?true:false;
	}
	public Map getDeleteSerMainSql() {
		return PerformSQLUtil.get("deleteSerMain", this);
	}
	/**
	 * 物理删除班组和设备的关系表信息
	 * @param groupId
	 * @return
	 */
	public boolean deleteEquipSerMain(String groupId){
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.deleteEquipSerMain!参数groupId:"+groupId);
		int count = sqlSession.delete("deleteEquipSerMain", groupId);
		return count>0?true:false;
	}
	public Map getDeleteEquipSerMainSql() {
		return PerformSQLUtil.get("deleteEquipSerMain", this);
	}
	
	/**
	 * 物理删除班组和人员的关系表信息
	 * @param groupId
	 * @return
	 */
	public boolean deleteSerMainDetail(String groupId){
		if (log.isDebugEnabled())
			log.debug("进入SerMainBusiness.deleteSerMainDetail!参数groupId:"+groupId);
		int count = sqlSession.delete("deleteSerMainDetail", groupId);
		return count>0?true:false;
	}
	public Map getDeleteSerMainDetail() {
		return PerformSQLUtil.get("deleteSerMainDetail", this);
	}

	
//	public SerMainMgr getSerMainMgr() {
//		return serMainMgr;
//	}
//	public void setSerMainMgr(SerMainMgr serMainMgr) {
//		this.serMainMgr = serMainMgr;
//	}
//	
	
	
}
