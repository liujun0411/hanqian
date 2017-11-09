package com.hanqian.action;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.DbAlarmBusiness;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.pojo.DbPointEquip;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 描 述: 实时监控告警提示
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-13
 * @see
 */
public class AlarmAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(AlarmAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private HttpServletRequest request;
	
	private DbAlarmBusiness dbAlarmBusiness;
	private EquipListBusiness equipListBusiness;
    private DbPointEquip dbPointEquip;
    private List alarmList;  //告警信息
    private Page page;
	private Integer pageSize=4;
	private Integer currentPage;
	private Integer eqcurrentPage;
//	private List listAlerts;
	private Integer hisCurrentPage;
	private Integer eqcurrentPageLess;
	private Page hisAlarmpage;
	private MenuInterceptor menuInterceptor;  //权限过滤器
	public Map menuIdMap; //权限MAP集合
	private String outMsg = "网络异常";
    private List listAlertsCurrentLess;
    public int levelLess;
    /**
     * 查询所有的报警点位
     * @return
     */
    public String findAllAlarmPoint(){
    	request = ServletActionContext.getRequest();
    	dbPointEquip=new DbPointEquip();
    	RetCode rc=new RetCode();
    	alarmList=new ArrayList();
    	String pageIndex=request.getParameter("alarmcurrentPage");
    	if(StringUtils.isNotEmpty(pageIndex)){
    		currentPage=Integer.parseInt(pageIndex);
    	}else{
			currentPage=1; 
		}
    	pageSize=1;
    	String equipId=request.getParameter("equipId");
    	String groupId=request.getParameter("groupId");//分组ID
    	//如果分组ID不为空
    	if(StringUtils.isNotEmpty(groupId)){
    		rc=dbAlarmBusiness.findAlarmPointByEquipPage(groupId,currentPage,pageSize);
			request.setAttribute("groupId",groupId);
    	}else{
    		//不分组
    		if(StringUtils.isNotEmpty(equipId)){
        		request.setAttribute("equipId",equipId);
        		rc=dbAlarmBusiness.findAlarmPointByEquip(equipId,currentPage,pageSize);
        	}
    	}
    	if(rc.getObj()!=null){
			alarmList=(List)rc.getObj();
			page=rc.getPage();
		}
    	return "showAlarm";
    }
    /**
     * 查询历史告警信息
     * @param 2012-9-28  
     * @param @return       
     * @return String
     */
    public String findAllHisAlarm(){
    	request = ServletActionContext.getRequest();		
		int pageSize = 10;
		Short level = 0;
		if(hisCurrentPage==null){
			hisCurrentPage=1;
		}
		try{
			if(StringUtil.isNotEmpty(getRequest().getParameter("level"))){
				level = Short.parseShort(getRequest().getParameter("level"));
			}
		
		String name = request.getParameter("name");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String timeType = request.getParameter("timeType");//查询时间的<0 ,全部,1 告警时间,2 消除时间>
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RetCode rt= dbAlarmBusiness.findAlarmHisInfo(timeType,level,name,startTime,endTime, hisCurrentPage, pageSize);
		List listAlerts = new ArrayList();
		if(rt.getObj() != null){
			listAlerts=(List)rt.getObj();
		}
		request.setAttribute("message", rt.getDesc());
		page = rt.getPage();
		request.setAttribute("listAlerts", listAlerts);
		request.setAttribute("name", name);
		request.setAttribute("timeType", timeType);
		request.setAttribute("level", level);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		}catch (Exception e) {
			logg.error("AlarmAction-->findAllHisAlarm",e);
			e.printStackTrace();
		}
		return "alarmHisList";
    }
    
    
    /**
     * 获取当前告警信息汇总
     * 一级：个数：备注
     * 二级：个数：备注
     * 三级：个数：备注
     *需要维修的设备数：个数
     * 
     * @param 2012-9-28  
     * @param        
     * @return void
     */
    public void findCurrentAlarmCount(){
    	HttpServletResponse response;
    	PrintWriter out = null;
    	try{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.print(dbAlarmBusiness.findCurrentAlarmCount());
    	}catch (Exception e) {
    		logg.error("AlarmAction-->findCurrentAlarmCount",e);
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
    /**
     * 获取全部告警信息或者某一类型的告警信息
     * @param 2012-9-28  
     * @param        
     * @return void
     */
    public String  findCurrentAlarmInfoLess(){
    	pageSize = 3;
    	HttpServletResponse response;
    	RetCode rt = new RetCode();
    	try{
	    	request = ServletActionContext.getRequest();
	    	response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
	    	 levelLess = 0;
	    	//page=null;
	    	
	    	if(eqcurrentPageLess==null){
	    		eqcurrentPageLess=1;
	    	}
	    	//权限配置
			menuIdMap=menuInterceptor.menuIntercept("4001");
			//若为空 ，进入登录界面
			if(menuIdMap==null){
				MenuInterceptor.toLoginjsp();
				return null;
			}
			request = ServletActionContext.getRequest();
			if(getRequest().getParameter("level")!=null&&StringUtil.isNotEmpty(getRequest().getParameter("level"))){
				levelLess = Integer.parseInt(getRequest().getParameter("level"));
			}
			rt = dbAlarmBusiness.findCurrentAlarmInfo(levelLess, eqcurrentPageLess, pageSize);
			if (rt!=null&&rt.getObj()!=null) {
				page=rt.getPage();
				listAlertsCurrentLess = (List) rt.getObj();
				//request.setAttribute("message",rt.getDesc());
				//request.setAttribute("level", level);
				outMsg = "";
			}else{
				//page=rt.getPage();
				listAlertsCurrentLess = null;
				//request.setAttribute("message",rt.getDesc());
				//request.setAttribute("level", level);
				levelLess = 0;
				outMsg = "暂无数据";
			}
			
    	}catch (Exception e) {
    		logg.error("AlarmAction-->findCurrentAlarmInfoLess",e);
    		request = ServletActionContext.getRequest();
    		if (rt!=null&&rt.getObj()!=null) {
    			listAlertsCurrentLess = null;
    			outMsg = "网络异常!";
			}else{
				listAlertsCurrentLess = null;
				outMsg = "网络异常!";
			}
    		
    		e.printStackTrace();
		}
		return "alarmListLess";
    }
    
    
    /**
     * 获取任意提醒的个数
     * 
     * @param 2015-03-23  
     * @param        
     * @return void
     */
    public void findCurrentAlarmCountSuiYi(){
    	HttpServletResponse response;
    	PrintWriter out = null;
    	try{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.print(dbAlarmBusiness.findCurrentAlarmCountSuiYi());
    	}catch (Exception e) {
    		logg.error("AlarmAction-->findCurrentAlarmCountSuiYi",e);
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
    
    
    /**
     * 获取未读任意提醒信息
     * @param 2015-03-23  
     * @param        
     * @return String
     */
    public String  findCurrentAlarmInfoSuiYi(){
    	HttpServletResponse response;
    	RetCode rt = new RetCode();
    	String groupId = "";
    	String equipId = "";
    	int level = 0;
			try {
				request = ServletActionContext.getRequest();
				response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				String nums2=getRequest().getParameter("hisCurrentPage");
				int pageSize = 10;
				page=null;
				if(eqcurrentPage==null){
					eqcurrentPage=1;
				}
				
				if(nums2==null){
					nums2 ="2";
				}
				Integer num = Integer.parseInt(nums2);
				if(!("1").equals(nums2)){
					/**
					 * 修改状态方法
					 */
					dbAlarmBusiness.updateRemarksSuiYi();
				}
				rt = dbAlarmBusiness.findCurrentAlarmInfoSuIYitixing(level, eqcurrentPage, pageSize);
				page=rt.getPage();
				request.setCharacterEncoding("UTF-8");
				request.setAttribute("listAlertsCurrent", rt.getObj());
				request.setAttribute("flags",nums2);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				logg.error(e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				request = ServletActionContext.getRequest();
	    		request.setAttribute("message", "网络异常!");
				logg.error(e);
			}
		
		return "alarmListSuiYitixing";
    }
    /**
     * 修改已执行操作
     * @param 2015-03-23  
     * @param        
     * @return String
     */
    public String  findCurrentAlarmInfoSuiYiIsDone(){
    	HttpServletResponse response;
    	RetCode rt = new RetCode();
				try {
					request = ServletActionContext.getRequest();
					response = ServletActionContext.getResponse();
					response.setCharacterEncoding("UTF-8");
					String seq=getRequest().getParameter("seq");
					/**
					 * 修改状态方法
					 */
					dbAlarmBusiness.updateRemarksSuiYiIsDone(seq);
				} catch (Exception e) {
					logg.error(e);
					request = ServletActionContext.getRequest();
					request.setAttribute("message", "网络异常!");
				}
		return "findCurrentAlarmInfoSuiYiIsDone";
    }
    
    /**
     * 任意提醒详情页面
     */
    public String ariesIdlist(){
    	HttpServletResponse response;
    	request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			String seq=getRequest().getParameter("seq");
			String flags=getRequest().getParameter("flags");
			Integer seqId = Integer.parseInt(seq);
			RetCode rt= dbAlarmBusiness.findCurrentAlarmInfoSuIYitixingfillId(seqId);
			List list= (List)rt.getObj();
			getRequest().setAttribute("content",list);
		   // getRequest().setAttribute("content",list.get(0));
			//getRequest().setAttribute("content", (List)rt.getObj());
			getRequest().setAttribute("hisCurrentPage", hisCurrentPage);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logg.error("AlarmAciotn-->ariesIdlist"+e);
		}
    	return "ariesIdlist";
    } 
    /**
     * 获取全部告警信息或者某一类型的告警信息
     * @param 2012-9-28  
     * @param        
     * @return void
     */
    public String  findCurrentAlarmInfo(){
    	HttpServletResponse response;
    	RetCode rt = new RetCode();
    	String groupId = "";
    	String equipId = "";
    	int level = 0;
    	try{
	    	request = ServletActionContext.getRequest();
	    	response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			int pageSize = 10;
	    	
	    	page=null;
	    	if(eqcurrentPage==null){
	    		eqcurrentPage=1;
	    		
	    	}
	    	//权限配置
			menuIdMap=menuInterceptor.menuIntercept("4001");
			//若为空 ，进入登录界面
			if(menuIdMap==null){
				MenuInterceptor.toLoginjsp();
				return null;
			}
			if(getRequest().getParameter("level")!=null&&StringUtil.isNotEmpty(getRequest().getParameter("level"))){
				level = Integer.parseInt(getRequest().getParameter("level"));
			}
			//监控页面传递过来groupId ,equipId
			 groupId = getRequest().getParameter("groupId");
			 equipId=getRequest().getParameter("equipId");
			
			if (StringUtil.isNotEmpty(groupId)) {
				pageSize = 10;
				rt = dbAlarmBusiness.findCurrentAlarmInfoByGroupId(groupId,eqcurrentPage,pageSize);
				if (rt.getObj()!=null&&((List)rt.getObj()).size()>0) {
					rt.setDesc("");
				}
			}else if(StringUtil.isNotEmpty(equipId)){//不存在groupId，则通过设备查找
				pageSize = 10;
				rt = dbAlarmBusiness.findCurrentAlarmInfoByEquipId(equipId,eqcurrentPage,pageSize);
				if (rt.getObj()!=null&&((List)rt.getObj()).size()>0) {
					rt.setDesc("");
				}
			}else{
				request = ServletActionContext.getRequest();
				
				rt = dbAlarmBusiness.findCurrentAlarmInfo(level, eqcurrentPage, pageSize);
			}
			rt = dbAlarmBusiness.findCurrentAlarmInfo(level, eqcurrentPage, pageSize);
			page=rt.getPage();
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("equipId",equipId);
			request.setAttribute("groupId",groupId);
			request.setAttribute("listAlertsCurrent", rt.getObj());
			request.setAttribute("message",rt.getDesc());
			request.setAttribute("level", level);
			
    	}catch (Exception e) {
    		logg.error("AlarmAction-->findCurrentAlarmInfo",e);
    		request = ServletActionContext.getRequest();
    		request.setAttribute("message", "网络异常!");
    		e.printStackTrace();
		}
		return "alarmList";
    }
    /**
	 * 更新备注
	 */
	public void updateRemarks(){
		PrintWriter out = null;
		request = ServletActionContext.getRequest();
		try {			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			String sequence = getRequest().getParameter("seq");
			String remarks = getRequest().getParameter("remarks");
//			remarks=URLDecoder.decode(remarks, "UTF-8");

			int result = dbAlarmBusiness.updateRemarks(sequence, remarks);
			
			out =  response.getWriter();
			if(result==1){
				out.print("操作成功!");
			}else{
				out.print("操作失败!请联系管理员");
			}
		} catch (Exception e) {
			logg.error("AlarmAction-->updateRemarks",e);
		}finally{
			out.flush();
			out.close();
		}
	}
	
	
	/**
	 * 消除告警
	 */
	public void removeAlarm(){
		PrintWriter out = null;
		request = ServletActionContext.getRequest();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			getRequest().setCharacterEncoding("UTF-8");
			String oper = getRequest().getParameter("oper");
			
			if(StringUtil.isEmpty(oper)){
				DbUsers dbusers = (DbUsers) getRequest().getSession().getAttribute("users");
				oper = dbusers.getUsername();
			}
			String sequence = getRequest().getParameter("seq");
			String remarks = getRequest().getParameter("remarks");
			
//			remarks=URLDecoder.decode(remarks, "UTF-8");	
			String result = dbAlarmBusiness.removeAlarm(Integer.parseInt(sequence), remarks, oper);
			out = response.getWriter();
			out.print(result);
		
		} catch (Exception e) {
			logg.error("AlarmAction-->removeAlarm",e);
			e.printStackTrace();
		}finally{
			if (out!=null) {
				out.flush();
				out.close();
			}
		}
		
	}
    private HttpServletRequest getRequest(){
    	if(request == null){
    		request = ServletActionContext.getRequest();
    	}
    	return request;
    }
    /**
     * 根据序号获取安装位置，设备，监控点等信息
     * @param 2012-11-9  
     * @param        
     * @return void
     */
	public void findAlarmAboutInfo(){
		PrintWriter out = null;
		request = ServletActionContext.getRequest();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			getRequest().setCharacterEncoding("UTF-8");
			String seq = getRequest().getParameter("id");
			String result = dbAlarmBusiness.findAlarmAboutInfo(Integer.parseInt(seq));
			out = response.getWriter();
			out.print(result);
		} catch (Exception e) {
			logg.error("AlarmAction-->findAlarmAboutInfo",e);
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		
	}
	 /**
     * 根据序号获取安装位置，设备，监控点等信息
     * @param 2012-11-9  
     * @param        
     * @return void
     */
	public void findHisAlarmAboutInfo(){
		PrintWriter out = null;
		request = ServletActionContext.getRequest();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			String seq = getRequest().getParameter("id");
			String result = dbAlarmBusiness.findHisAlarmAboutInfo(Integer.parseInt(seq));
			out = response.getWriter();
			out.print(result);
		} catch (Exception e) {
			logg.error("AlarmAction-->findHisAlarmAboutInfo",e);
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		
	}
	public DbAlarmBusiness getDbAlarmBusiness() {
		return dbAlarmBusiness;
	}
	public void setDbAlarmBusiness(DbAlarmBusiness dbAlarmBusiness) {
		this.dbAlarmBusiness = dbAlarmBusiness;
	}
	public DbPointEquip getDbPointEquip() {
		return dbPointEquip;
	}
	public void setDbPointEquip(DbPointEquip dbPointEquip) {
		this.dbPointEquip = dbPointEquip;
	}
	public List getAlarmList() {
		return alarmList;
	}
	public void setAlarmList(List alarmList) {
		this.alarmList = alarmList;
	}

	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}





	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}










	public Integer getCurrentPage() {
		return currentPage;
	}










	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

//	public List getListAlerts() {
//		return listAlerts;
//	}
//
//	public void setListAlerts(List listAlerts) {
//		this.listAlerts = listAlerts;
//	}

	public Integer getHisCurrentPage() {
		return hisCurrentPage;
	}

	public EquipListBusiness getEquipListBusiness() {
		return equipListBusiness;
	}

	public void setEquipListBusiness(EquipListBusiness equipListBusiness) {
		this.equipListBusiness = equipListBusiness;
	}

	public void setHisCurrentPage(Integer hisCurrentPage) {
		this.hisCurrentPage = hisCurrentPage;
	}
	public Integer getEqcurrentPage() {
		return eqcurrentPage;
	}
	public void setEqcurrentPage(Integer eqcurrentPage) {
		this.eqcurrentPage = eqcurrentPage;
	}
	/**
	 * @return the menuInterceptor
	 */
	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}
	/**
	 * @param menuInterceptor the menuInterceptor to set
	 */
	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}
	/**
	 * @return the menuIdMap
	 */
	public Map getMenuIdMap() {
		return menuIdMap;
	}
	/**
	 * @param menuIdMap the menuIdMap to set
	 */
	public void setMenuIdMap(Map menuIdMap) {
		this.menuIdMap = menuIdMap;
	}
	
	/**
	 * @return the hisAlarmpage
	 */
	public Page getHisAlarmpage() {
		return hisAlarmpage;
	}
	/**
	 * @param hisAlarmpage the hisAlarmpage to set
	 */
	public void setHisAlarmpage(Page hisAlarmpage) {
		this.hisAlarmpage = hisAlarmpage;
	}
	/**
	 * @return the outMsg
	 */
	public String getOutMsg() {
		return outMsg;
	}
	/**
	 * @param outMsg the outMsg to set
	 */
	public void setOutMsg(String outMsg) {
		this.outMsg = outMsg;
	}
	/**
	 * @return the listAlertsCurrentLess
	 */
	public List getListAlertsCurrentLess() {
		return listAlertsCurrentLess;
	}
	/**
	 * @param listAlertsCurrentLess the listAlertsCurrentLess to set
	 */
	public void setListAlertsCurrentLess(List listAlertsCurrentLess) {
		this.listAlertsCurrentLess = listAlertsCurrentLess;
	}
	/**
	 * @return the levelLess
	 */
	public int getLevelLess() {
		return levelLess;
	}
	/**
	 * @param levelLess the levelLess to set
	 */
	public void setLevelLess(int levelLess) {
		this.levelLess = levelLess;
	}
	/**
	 * @return the eqcurrentPageLess
	 */
	public Integer getEqcurrentPageLess() {
		return eqcurrentPageLess;
	}
	/**
	 * @param eqcurrentPageLess the eqcurrentPageLess to set
	 */
	public void setEqcurrentPageLess(Integer eqcurrentPageLess) {
		this.eqcurrentPageLess = eqcurrentPageLess;
	}

	
	
}
