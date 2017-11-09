package com.hanqian.action;


import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.SeriverBusiness;
import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbService;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ServicerBusinessAction extends ActionSupport{

	//权限
	private MenuInterceptor menuInterceptor;
	public Map menuIdMap; //权限MAP集合
	// 分页
	private Page page;
	private int pageSize = 10;
	private int currentPage = 1;
	public List servicerList ;
	private SeriverBusiness seriverBusiness;
	private HttpServletRequest request;
	private List<DbBaseComm> serviceTypeList ;//服务商类型
	
	private DbService dbSer;
	// 日志
	private static final Log log = LogFactory.getLog(ServicerBusinessAction.class);

	/**
	 * 得到request
	 * @param 2012-12-7  
	 * @param @return       
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getReq (){
		if (request == null) {
			request = ServletActionContext.getRequest();
		}
		return request;
	}
	/**
	 * 获取服务商信息列表
	 * @param 2012-12-6  
	 * @param        
	 */
	public String findServicerList(){
		request = ServletActionContext.getRequest();
		try {
			getReq().setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("ServicerBusinessAction-->findServicerList", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//权限配置
		menuIdMap=menuInterceptor.menuIntercept("5007003");
		//若为空 ，进入登录界面
		if(menuIdMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}
		String pageIndex=getReq().getParameter("currentPage");
		if(StringUtils.isNotEmpty(pageIndex)){
    		currentPage=Integer.parseInt(pageIndex);
    	}else{
			currentPage=1;
		}
    	pageSize=10;
		String serName = getReq().getParameter("serName");
		String serType = getReq().getParameter("serType");
		try{
			if(StringUtils.isNotEmpty(serName)){
				serName=new String(serName.getBytes("ISO-8859-1"), "UTF-8");
			}
		}catch (Exception e) {
			log.error("ServicerBusinessAction-->findServicerList", e);
			// TODO: handle exception
		}
		List list = new ArrayList();
		RetCode rc =  seriverBusiness.findServicerList(serName,serType,currentPage,pageSize);
		if (rc!=null&& rc.getObj()!=null) {
			servicerList =  (List)rc.getObj();
			page = rc.getPage();
		}else{
			servicerList = null;
			getReq().setAttribute("message", "暂无数据!");
		}
		getReq().setAttribute("serviceTypeList", seriverBusiness.findServiceTypeList());
		getReq().setAttribute("serName", serName);
		getReq().setAttribute("serType", serType);
		return "serviceList";
	}
	/**
	 * 查询服务商类型
	 * @param 2012-12-6  
	 * @param @return       
	 */
	public void findServiceTypeList(){
		HttpServletResponse response;
    	PrintWriter out = null;
    	try{
			response = ServletActionContext.getResponse();
			serviceTypeList =  seriverBusiness.findServiceTypeList();
			JSONObject jsonObj = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			JSONArray typeArray = new JSONArray();
			if (serviceTypeList!=null&&!serviceTypeList.isEmpty()) {
				for (DbBaseComm baseComm : serviceTypeList) {
					JSONObject typeJson = new JSONObject();
					typeJson.accumulate("content1", baseComm.getContent1());
					typeJson.accumulate("content2", baseComm.getContent2());
					typeJson.accumulate("seq", baseComm.getSeq());
					typeArray.add(typeJson);
				}
			}
			jsonObj.accumulate("typeList", typeArray);
			out = response.getWriter();
			out.print(jsonObj.toString());
    	}catch (Exception e) {
    		log.error("ServicerBusinessAction-->findServiceTypeList", e);
    		e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	/**
	 * 验证是否服务商唯一
	 * @param 2012-12-8  
	 * @param        
	 * @return void
	 */
	public void validateIsUnique(){
		HttpServletResponse response;
    	PrintWriter out = null;
    	try{
    		request = ServletActionContext.getRequest();
    		String serName = getReq().getParameter("serName");
			response = ServletActionContext.getResponse();
			JSONObject jsonObj = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			Integer seq = 0;
			if(dbSer!=null){
				seq = dbSer.getSeq();
			}
			jsonObj.accumulate("isUnique", seriverBusiness.validateIsUnique(serName,seq));
			out = response.getWriter();
			out.print(jsonObj.toString());
    	}catch (Exception e) {
    		log.error("ServicerBusinessAction-->validateIsUnique", e);
    		e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 更新
	 * @param 2012-12-7  
	 * @param @return       
	 * @return String
	 */
	public String updateService(){
		String message = "";
		request = ServletActionContext.getRequest();
		String seq = getReq().getParameter("seq");
		if(getReq().getSession().getAttribute("users")!=null){
			DbUsers user = (DbUsers)getReq().getSession().getAttribute("users");
			dbSer.setOper(user.getSeq());
			dbSer.setOpertime(new Date());
		}
		
		boolean	bool = seriverBusiness.updateService(dbSer);
		if (bool) {
			message = "修改成功!";
		}else{
			message = "修改失败!";
		}
		getReq().setAttribute("message", message);
	
		return	this.findServicerList();
	}
	
	/**
	 * 新增
	 * @param 2012-12-7  
	 * @param @return       
	 * @return String
	 */
	public String addService(){
		try{
		String message = "";
		request = ServletActionContext.getRequest();
		if(getReq().getSession().getAttribute("users")!=null){
			DbUsers user = (DbUsers)getReq().getSession().getAttribute("users");
			dbSer.setInput(user.getSeq());
			dbSer.setInputtime(new Date());
		}
		boolean	bool = seriverBusiness.addService(dbSer);
		if (bool) {
			message = "新增成功!";
		}else{
			message = "新增失败!";
		}
		getReq().setAttribute("message", message);
		}catch (Exception e) {
			log.error("ServicerBusinessAction-->addService", e);
			e.printStackTrace();
		}
		return	this.findServicerList();
	}
	public String updateBeforeService(){
		request = ServletActionContext.getRequest();
		String editFlag = getReq().getParameter("editFlag");//判断
		if (StringUtil.isNotEmpty(editFlag)&&editFlag.equals("update")) {
			//先通过seq找到该记录
			String seq = getReq().getParameter("seq");
			dbSer = seriverBusiness.findEntityById(Integer.parseInt(seq));
		}else{
			dbSer = new DbService();
		}
		getReq().setAttribute("editFlag", editFlag);
		getReq().setAttribute("serviceTypeList", seriverBusiness.findServiceTypeList());
		return	"serviceEdit";
	}
	/**
	 * 删除
	 * @param 2012-12-8  
	 * @param @return       
	 * @return String
	 */
	public String deleteService(){
		request = ServletActionContext.getRequest();
		String message = "";
		boolean bool = false;
		String seq = getReq().getParameter("seq");
		if (StringUtil.isNotEmpty(seq)) {
			bool = seriverBusiness.deleteService(Integer.parseInt(seq));
		}
		if (bool) {
			message = "删除成功!";
		}else{
			message = "删除失败!";
		}
		getReq().setAttribute("message", message);
		
		return	this.findServicerList();
	}
	
	
	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public static Log getLog() {
		return log;
	}


	/**
	 * @return the seriverBusiness
	 */
	public SeriverBusiness getSeriverBusiness() {
		return seriverBusiness;
	}


	/**
	 * @param seriverBusiness the seriverBusiness to set
	 */
	public void setSeriverBusiness(SeriverBusiness seriverBusiness) {
		this.seriverBusiness = seriverBusiness;
	}
	/**
	 * @return the servicerList
	 */
	public List getServicerList() {
		return servicerList;
	}
	/**
	 * @param servicerList the servicerList to set
	 */
	public void setServicerList(List servicerList) {
		this.servicerList = servicerList;
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
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	/**
	 * @return the dbSer
	 */
	public DbService getDbSer() {
		return dbSer;
	}
	/**
	 * @param dbSer the dbSer to set
	 */
	public void setDbSer(DbService dbSer) {
		this.dbSer = dbSer;
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
	                                                                                                                                                                                                     
	
	
	
}