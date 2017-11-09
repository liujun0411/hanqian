package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.util.RetCode;

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
 * @version 1.4 2013-8-20
 * @see
 */
@Service
public class StaticHistoryDataBusiness   extends BaseBusiness{
	public static final Logger logg = Logger.getLogger(StaticHistoryDataBusiness.class);
	
//	private StaticHistoryDataMgr staticHistoryDataMgr;
	public RetCode StaticHistoryData(String tableName,int currentPage,int pageSize){
		if (logg.isDebugEnabled())
			logg.debug("进入StaticHistoryDataBusiness.StaticHistoryData!参数tableName:"+tableName);
		return this.StaticHistoryDataMethod(tableName, currentPage, pageSize);
	}
	
	public RetCode StaticHistoryDataMethod(String tableName,int currentPage,int pageSize){
		String sql="";
		if(("DB_BUILDING_LOG").equals(tableName)){																																																																															
			return this.getPageData("StaticHistoryDataMethod1", null, currentPage, pageSize);																								
		}else if("DB_BUILDING_PIC_LOG".equals(tableName)){
			return this.getPageData("StaticHistoryDataMethod2", null, currentPage, pageSize);																																								
		}else if("DB_BUILDING_REPAIR_LOG".equals(tableName)){
			return this.getPageData("StaticHistoryDataMethod3", null, currentPage, pageSize);																						
		}else if("DB_BUILDING_STOREY_LOG".equals(tableName)){
			return this.getPageData("StaticHistoryDataMethod4", null, currentPage, pageSize);																																						
		}else if("DB_BUILD_AREA_LOG".equals(tableName)){
			return this.getPageData("StaticHistoryDataMethod5", null, currentPage, pageSize);			
		}else if("DB_BUILD_MATER_LOG".equals(tableName)){
			return this.getPageData("StaticHistoryDataMethod6", null, currentPage, pageSize);			
		}else if("DB_EQUIP_GROUP_LOG".equals(tableName)){
			return this.getPageData("StaticHistoryDataMethod7", null, currentPage, pageSize);			
	    }else if("DB_EQUIP_LIST_LOG".equals(tableName)){
	    	return this.getPageData("StaticHistoryDataMethod8", null, currentPage, pageSize);			
	    }else if("DB_EQUIP_PIC_LOG".equals(tableName)){
	    	return this.getPageData("StaticHistoryDataMethod9", null, currentPage, pageSize);			
	    }else if("DB_EQUIP_TYPE_LOG".equals(tableName)){
	    	return this.getPageData("StaticHistoryDataMethod10", null, currentPage, pageSize);			
		}	
		return null;																																										
	}
	
	
	
	public List StaticHistoryDataById(String tableName,int seq){
		if (logg.isDebugEnabled())
			logg.debug("进入StaticHistoryDataBusiness.StaticHistoryDataById!参数tableName:"+tableName+" seq:"+seq);
		return this.StaticHistoryDataByIdMethod(tableName, seq);
	}
	
	public List StaticHistoryDataByIdMethod(String tableName,int seq){
		Map mapvalue =new HashMap();
		mapvalue.put("seq", seq);
		
		String sql="";
		if(("DB_BUILDING_LOG").equals(tableName)){																																																																															
			return (List) this.getData("StaticHistoryDataByIdMethod1", mapvalue);
		}else if("DB_BUILDING_PIC_LOG".equals(tableName)){
				 return (List) this.getData("StaticHistoryDataByIdMethod2", mapvalue);
		}else if("DB_BUILDING_REPAIR_LOG".equals(tableName)){
			return (List) this.getData("StaticHistoryDataByIdMethod3", mapvalue);																																							
		}else if("DB_BUILDING_STOREY_LOG".equals(tableName)){
			return (List) this.getData("StaticHistoryDataByIdMethod4", mapvalue);					
		}else if("DB_BUILD_AREA_LOG".equals(tableName)){
			return (List) this.getData("StaticHistoryDataByIdMethod5", mapvalue);	
		}else if("DB_BUILD_MATER_LOG".equals(tableName)){
			return (List) this.getData("StaticHistoryDataByIdMethod6", mapvalue);		
		}else if("DB_EQUIP_GROUP_LOG".equals(tableName)){
			return (List) this.getData("StaticHistoryDataByIdMethod7", mapvalue);		
	    }else if("DB_EQUIP_LIST_LOG".equals(tableName)){
	    	return (List) this.getData("StaticHistoryDataByIdMethod8", mapvalue);	
	    }else if("DB_EQUIP_PIC_LOG".equals(tableName)){
	    	return (List) this.getData("StaticHistoryDataByIdMethod9", mapvalue);	
	    }else if("DB_EQUIP_TYPE_LOG".equals(tableName)){
	    	return (List) this.getData("StaticHistoryDataByIdMethod10", mapvalue);	
		}
		return null;
	}
	
	
	public RetCode db_log_detailById(String tableName,String log_seq){
		if (logg.isDebugEnabled())
			logg.debug("进入StaticHistoryDataBusiness.db_log_detailById!参数tableName:"+tableName+" log_seq:"+log_seq);
		//return staticHistoryDataMgr.db_log_detailById(tableName, log_seq);
		Map mapvalue = new HashMap();
		mapvalue.put("seq", log_seq);
		return this.getData("dblogdetailById", mapvalue);
	}
//	public StaticHistoryDataMgr getStaticHistoryDataMgr() {
//		return staticHistoryDataMgr;
//	}
//	public void setStaticHistoryDataMgr(StaticHistoryDataMgr staticHistoryDataMgr) {
//		this.staticHistoryDataMgr = staticHistoryDataMgr;
//	}
}
