package com.hanqian.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.StaticHistoryDataBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.form.VOStaticHistoryData;
import com.hanqian.form.VoObject;
import com.hanqian.util.ConfigUtil;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.opensymphony.xwork2.ActionSupport;

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
public class StaticHistoryDataAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(StaticHistoryDataAction.class);
	private StaticHistoryDataBusiness staticHistoryDataBusiness;
	private HttpServletRequest request;
	private Integer currentPage;
	private Page page;
	private String tableName = null;
	private List voList = null;
	/**
	 * 根据表名查询数据
	 * @param 2013-8-26  
	 * @param @return       
	 * @return String
	 */
	public String StaticHistoryData(){
		request = ServletActionContext.getRequest();
		try {
		String pageIndex=request.getParameter("currentPage");
		
		String columns="";
		int pageSize = 10;																																													
		if (!SysUtil.isNull(pageIndex)) {																							
			currentPage = Integer.parseInt(pageIndex);																						
		}																							
		request=ServletActionContext.getRequest();																							
		//获取从前台页面传过来的下拉列表框的表名			
		if(StringUtils.isNotEmpty(request.getParameter("tableName"))){
			tableName=request.getParameter("tableName");
		}
		if(tableName==null){
			tableName =ConfigCST.CST_TABLE_NAME;
		}
			columns=ConfigUtil.getConfig("/tablename.properties", "columnName");
		
			RetCode rc=staticHistoryDataBusiness.StaticHistoryData(tableName,currentPage,pageSize);	
			page= rc.getPage();
			List<VOStaticHistoryData> voStaticHistoryDataList=(List<VOStaticHistoryData>) rc.getObj();																							
			request.setAttribute("voStaticHistoryDataList", voStaticHistoryDataList);																							
			request.setAttribute("tableName", tableName);
			request.setAttribute("columns", columns);
		} catch (Exception e) {
			logg.error("StaticHistoryDataAction-->StaticHistoryData", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "show";																								
     }		
	
	
	/**
	 * 根据表名和主键查询详细信息
	 * @param 2013-8-26  
	 * @param @return
	 * @param @throws Exception       
	 * @return String
	 */
	public String StaticHistoryDataById() throws Exception{
		request = ServletActionContext.getRequest();
		voList = new ArrayList();
		if(StringUtils.isNotEmpty(request.getParameter("tableName"))){
			tableName=request.getParameter("tableName");
		}
		request.setAttribute("tableName", tableName);
		int seq=Integer.parseInt(request.getParameter("seq"));
		List list=staticHistoryDataBusiness.StaticHistoryDataById(tableName, seq);
		Map map= (Map) list.get(0);
		String log_seq=map.get("log_seq").toString();
		Object s [] = map.keySet().toArray();
		//String key = tableName+"_CN";
		//加载配置文件
		String CNNameInfo = ConfigUtil.getConfig("tablename.properties", tableName);
		JSONObject json = JSONObject.fromObject(CNNameInfo);
		for(int i=0;i<map.size();i++){
			VoObject voObj= new VoObject(); 
			if(json.containsKey(s[i].toString())){
				voObj.setCNName(json.getString(s[i].toString()));
				if(map.get(s[i])!=null){
					voObj.setCNValue(map.get(s[i]).toString());
				}else{
					voObj.setCNValue(null);
				}
				voList.add(voObj);
			}
		}
		RetCode rc=staticHistoryDataBusiness.db_log_detailById(tableName, log_seq);
		Map liMap=new HashMap();
		List logList=(List) rc.getObj();
		Iterator it = logList.iterator(); 
		String cn_name="";
		String cn_value="";
		String CNNameDETAIL = ConfigUtil.getConfig("tablename.properties", tableName);
		JSONObject jsonObj = JSONObject.fromObject(CNNameDETAIL);
		   while(it.hasNext()) {  
		      liMap= (Map) it.next();  
		       String col_name=liMap.get("col_name").toString().toLowerCase();
		    		if(jsonObj.containsKey(col_name)){
			    		cn_name+=jsonObj.getString(col_name)+"!";
			    		if(liMap.get("new_value")!=null){
			    			cn_value+=liMap.get("new_value")+"!";
						} 
					}
	       } 
		   cn_name = cn_name.substring(0,cn_name.lastIndexOf("!"));
		   if(!"".equals(cn_value)){
		      cn_value = cn_value.substring(0,cn_value.lastIndexOf("!"));
		   }
		   request.setAttribute("cn_name", cn_name);
		   request.setAttribute("cn_value", cn_value);
		return "voDetail";
	}


	public StaticHistoryDataBusiness getStaticHistoryDataBusiness() {
		return staticHistoryDataBusiness;
	}

	public void setStaticHistoryDataBusiness(
			StaticHistoryDataBusiness staticHistoryDataBusiness) {
		this.staticHistoryDataBusiness = staticHistoryDataBusiness;
	}


	public Integer getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}



	public List getVoList() {
		return voList;
	}



	public void setVoList(List voList) {
		this.voList = voList;
	}																

}
