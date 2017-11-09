package com.hanqian.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.jfree.util.Log;

import com.hanqian.business.DbControldataBusiness;
import com.hanqian.business.DbSQLControldataBusiness;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.util.Page;
import com.hanqian.util.PageMysql;
import com.hanqian.util.RetCode;
import com.hanqian.util.RetCodeMysql;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 
 * 描 述: 实时监控
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
public class ControlDataAction extends ActionSupport {
	public static final Logger log = Logger
			.getLogger(ControlDataAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private HttpServletRequest request;
	private DbControldataBusiness dbControldataBusiness;  
	private DbSQLControldataBusiness  dbsqlcontroldatabusiness;
	private String resultJSON;    //返回JSON格式的文本
	private List controlDataList;
	private List controlDataByEquipTypeList;
	private int pageSize=10;
	private int currentPage;
	private int typecurrentPage;
	private Page page;
	private int totalDataCount = 0;//总个数
	private int statusDataCount = 0;//状态点位个数
	private int alarmDataCount = 0;//报警点位个数
	private int collectDataCount = 0; //计量点位个数
	private PageMysql pagemysql;
	private int boilderCount = 0;//锅炉点位个数
	private int shuilengCount = 0;//水冷点位个数
	private int waterCount = 0;//水表点位个数
	private int eleCount = 0; //电表点位个数
	
	//查询条件
	private String pointName;//点位名称
	private String projectPoint;//工程点位
	private String equipName;//设备查询
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String dwbmSDCD;//点位编码
	/**
	 * 根据设备编号，获取设备编号对应的所有点位读数
	 * @return
	 */
	public String findControlData() throws Exception{
		request = ServletActionContext.getRequest();
		RetCode rc=new RetCode();
		DbEquipList equipList=new DbEquipList();
		List list=new ArrayList();
		String equipId=request.getParameter("equipId");
		if(StringUtils.isNotEmpty(equipId)){
			equipList.setEquipId(Integer.parseInt(equipId));
			//rc=dbControldataBusiness.findControlData(equipList);
			list=(List)rc.getObj();
			JSONArray ja=JSONArray.fromObject(list);
			resultJSON=ja.toString();
//			System.out.println("resultJSON::::::::::::::::::::::::::::::"+resultJSON);
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");   
			ServletActionContext.getResponse().getWriter().print(resultJSON);
		}
		return null;
	}
	
	
	
	/**
	 * edit by lg 5-24
	 * 获取所有点位读数
	 * @return
	 */
	public String findControlDataList() throws Exception{
		request = ServletActionContext.getRequest();
		String pageIndex=request.getParameter("currentPage");
//		int pageSize = 10;
		Object objType = request.getParameter("type");
		int type = 0 ;
    	if(StringUtils.isNotEmpty(pageIndex)){
    		currentPage=Integer.parseInt(pageIndex);
    	}else{
			currentPage=1; 
		}
    	if (!StringUtil.isEmpty(objType)) {
    		type = Integer.parseInt(objType.toString());
    		request.setAttribute("type", type);
		}
			
		RetCode rc=new RetCode();
		rc=dbControldataBusiness.findControlDataList(currentPage,pageSize,type);
		if (rc!=null&&rc.getObj()!=null) {
			controlDataList = (List)rc.getObj();
			page=rc.getPage();
		}
//		Object obj = request.getSession().getAttribute("currentHospCode");
//		String currentHospCode = "";
//		if (obj!=null) {
//			currentHospCode = (String)obj;
//		}
//		String controlDataCollect = ConfigUtil.getConfig(
//				"config.properties", currentHospCode+"controlDataCollect");
//		
//		if(StringUtils.isNotEmpty(controlDataCollect)&&controlDataCollect.length()>0){
//			String[] collectCounts = controlDataCollect.split(",");
//			totalDataCount = Integer.parseInt(collectCounts[0]);
//			collectDataCount = Integer.parseInt(collectCounts[1]);
//			statusDataCount = Integer.parseInt(collectCounts[2]);
//			alarmDataCount = Integer.parseInt(collectCounts[3]);
//		}
		//获取各种点位数  
		int []collectCounts = dbControldataBusiness.findControlCount();
		totalDataCount = collectCounts[0];
		collectDataCount = collectCounts[1];
		statusDataCount = collectCounts[2];
		alarmDataCount = collectCounts[3];
		return "collectList";
	}
	/**
	 * 
	 * 跳转一个显示选项卡的页面
	 * @return String 
	 * @Date 2015-12-03
	 * 
	 */
	public String selectTablListInfo(){
		return "showTabList";
	}
	
	/**
	 * 获取ＳＤＣＤ对应mysql数据库中的数据列表
	 * @return String 
	 * @Date 2015-12-03
	 * 
	 */
	public String sDCDfindDiwanweiInfoList(){
		request = ServletActionContext.getRequest();
		//分页
		String pageIndex=request.getParameter("currentPage");
		//点位编码
		String projectPoint1=request.getParameter("dwbm1");
		//时间
		String dateTime=request.getParameter("dateTimeSDCD");
//		int pageSize = 10;
		int type = 0 ;
    	if(StringUtils.isNotEmpty(pageIndex)){
    		currentPage=Integer.parseInt(pageIndex);
    	}else{
			currentPage=1; 
		}
		RetCodeMysql rc=new RetCodeMysql();      
		List sDCDfindDiwanweiInfoList =null;
		/**
		 * SDCD 取的对应的数据，
		 * 现在暂无连接到mysql上面去，
		 * 所以不能使用这个查询数据
		 */
		//List rc=dbsqlcontroldatabusiness.sDCDfindDiwanweiInfoList(currentPage,pageSize,projectPoint1,dateTime);
		 rc=dbsqlcontroldatabusiness.sDCDfindDiwanweiInfoList(currentPage,pageSize,projectPoint1,dateTime);
		if (rc!=null&&rc.getObj()!=null) {
			sDCDfindDiwanweiInfoList = (List)rc.getObj();
			pagemysql=rc.getPage();
			//点位编码
			request.setAttribute("dwbm1",projectPoint1);
			//时间
			request.setAttribute("dateTimeSDCD",dateTime);
			request.setAttribute("sDCDfindDiwanweiInfoList",sDCDfindDiwanweiInfoList);
		}
		return "selectSDCDdianweiInfoList";
	}
	
	/**
	 * 生产库的数据list
	 * @return String 
	 * @Date 2015-12-03
	 *  
	 */
	public String selectfindDianweiInfoList() throws Exception{
		request = ServletActionContext.getRequest();
		String pageIndex=request.getParameter("currentPage");
    	if(StringUtils.isNotEmpty(pageIndex)){
    		currentPage=Integer.parseInt(pageIndex);
    	}else{
			currentPage=1; 
		}
		RetCode rc=new RetCode();      
		List selectDianweiIfosList1 =null;
		rc=dbControldataBusiness.selectfindDianweiInfoList(currentPage,pageSize,pointName,projectPoint,equipName,startTime,endTime,dwbmSDCD);
		if (rc!=null&&rc.getObj()!=null) {
			selectDianweiIfosList1 = (List)rc.getObj();
			page=rc.getPage();
			request.setAttribute("selectDianweiIfosList1",selectDianweiIfosList1);
		}
		return "selectDianweiInfoList";
	}
	/**
	 * Excel 导出方法
	 * @return
	 * @Date 2015-12-03
	 */
	public void export(){
		HttpServletResponse response =ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=export.xls");
		//创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet0");
		sheet.createFreezePane(0, 1);
		//查询表头信息
		 List headersInfo = dbControldataBusiness.selectfindDianweiBiaoTaoCount();
		//查询数据
		 List cloumnsInfo = dbControldataBusiness.selectfindDianweiExcelList(pointName,equipName,startTime,endTime,dwbmSDCD);
		//给Excel表头赋值
		this.outputHeaders(headersInfo, sheet);
		//给Excel单元格赋值
		this.outputColumns(cloumnsInfo, sheet, 1); 
		//获取输入流，写入并且关闭
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("Excel下载失败="+e);
		}
	}
	
	/**
	 * 给表头赋值
	 * @param headersInfo
	 * @param sheet
	 */
    public  void  outputHeaders(List headersInfo,HSSFSheet sheet){
  	  //创建表头
  	  HSSFRow row  = sheet.createRow(0);
  	  try {
		for(int i =0; i<headersInfo.size();i++){
			  sheet.setColumnWidth(i,4000);
			  row.createCell(i).setCellValue(headersInfo.get(i).toString());
		  }
	} catch (Exception e) {
		sheet.setColumnWidth(0,20);
		row.createCell(0).setCellValue("表头信息未获取到！");
		log.error("表头信息未获取到="+e);
	}
    }
    /**
     * 给单元格赋值
     * 
     */
    public  void outputColumns(List columnsInfo ,HSSFSheet sheet,int rowIndex){
  	  HSSFRow row;
  	  //循环多少航
  	  try {
		for(int i=0;i<columnsInfo.size();i++){
			  row = sheet.createRow(rowIndex+i);
			  Map  mapCoude =(Map) columnsInfo.get(i);
			  //每一行的单元格
					  row.createCell(0).setCellValue(mapCoude.get("gcdw") == null ? "" : mapCoude.get("gcdw").toString());      
					  row.createCell(1).setCellValue(mapCoude.get("dwbm") == null ? "" : mapCoude.get("dwbm").toString());   
					  row.createCell(2).setCellValue(mapCoude.get("dwmc") == null ? "" : mapCoude.get("dwmc").toString());	
					  row.createCell(3).setCellValue(mapCoude.get("ds") == null ? "" : mapCoude.get("ds").toString());	       
					  row.createCell(4).setCellValue(mapCoude.get("cjsj") == null ? "" : mapCoude.get("cjsj").toString());	       
					  row.createCell(5).setCellValue(mapCoude.get("jlsj") == null ? "" : mapCoude.get("jlsj").toString());	       
					  row.createCell(6).setCellValue(mapCoude.get("sbdlbm") == null ? "" : mapCoude.get("sbdlbm").toString());
					  row.createCell(7).setCellValue(mapCoude.get("sbdl") == null ? "" : mapCoude.get("sbdl").toString());	       
					  row.createCell(8).setCellValue(mapCoude.get("sblx") == null ? "" : mapCoude.get("sblx").toString());	       
					row.createCell(9).setCellValue(mapCoude.get("sbxh") == null ? "" : mapCoude.get("sbxh").toString()); 	
					row.createCell(10).setCellValue(mapCoude.get("sbmc") == null ? "" : mapCoude.get("sbmc").toString());      
					row.createCell(11).setCellValue(mapCoude.get("sbbm") == null ? "" : mapCoude.get("sbbm").toString()); 	
					row.createCell(12).setCellValue(mapCoude.get("azly") == null ? "" : mapCoude.get("azly").toString()); 	       
					row.createCell(13).setCellValue(mapCoude.get("azlc") == null ? "" : mapCoude.get("azlc").toString()); 	       
					row.createCell(14).setCellValue(mapCoude.get("fwly") == null ? "" : mapCoude.get("fwly").toString());          
					row.createCell(15).setCellValue(mapCoude.get("fwlc") == null ? "" : mapCoude.get("fwlc").toString()); 	       
					row.createCell(16).setCellValue(mapCoude.get("bz") == null ? "" : mapCoude.get("bz").toString()); 	       
					//已去掉row.createCell(17).setCellValue(mapCoude.get("zsbs") == null ? "" : mapCoude.get("zsbs").toString()); 
		  }
	} catch (Exception e) {
		row = sheet.createRow(1);
		row.createCell(0).setCellValue("暂无数据！");
		log.error("暂未获取到数据="+e);
	}
    }
	
	
	/**
	 * edit by lg 5-24
	 * 根据设备编号，获取设备编号对应的所有点位读数
	 * @return
	 */
	public String findControlDataListByEquipType() throws Exception{
		request = ServletActionContext.getRequest();
		String pageIndex=request.getParameter("typecurrentPage");
		String objType = request.getParameter("type");
		int type = 0;
    	if(StringUtils.isNotEmpty(pageIndex)){
    		typecurrentPage=Integer.parseInt(pageIndex);
    	}else{
    		typecurrentPage=1; 
		}
    	if (!StringUtil.isEmpty(objType)) {
    		request.setAttribute("type", Integer.parseInt(objType));
    		type = Integer.parseInt(objType);
		}
			
		RetCode rc=new RetCode();
		rc=dbControldataBusiness.findControlDataListByEquipType(typecurrentPage,pageSize,type);
		if (rc!=null&&rc.getObj()!=null) {
			controlDataByEquipTypeList = (List)rc.getObj();
			page=rc.getPage();
		}
		//获取各种点位数   锅炉 水冷 水表 电表
		int []collectCounts = dbControldataBusiness.findControlCountByEquipType();
		boilderCount = collectCounts[0];
		shuilengCount = collectCounts[1];
		waterCount = collectCounts[2];
		eleCount = collectCounts[3];
		return "collectByEquipTypeList";
	}
	public String getResultJSON() {
		return resultJSON;
	}
	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}
	public DbControldataBusiness getDbControldataBusiness() {
		return dbControldataBusiness;
	}
	public void setDbControldataBusiness(DbControldataBusiness dbControldataBusiness) {
		this.dbControldataBusiness = dbControldataBusiness;
	}

	/**
	 * @return the controlDataList
	 */
	public List getControlDataList() {
		return controlDataList;
	}

	/**
	 * @param controlDataList the controlDataList to set
	 */
	public void setControlDataList(List controlDataList) {
		this.controlDataList = controlDataList;
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
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the totalDataCount
	 */
	public int getTotalDataCount() {
		return totalDataCount;
	}

	/**
	 * @param totalDataCount the totalDataCount to set
	 */
	public void setTotalDataCount(int totalDataCount) {
		this.totalDataCount = totalDataCount;
	}

	/**
	 * @return the statusDataCount
	 */
	public int getStatusDataCount() {
		return statusDataCount;
	}

	/**
	 * @param statusDataCount the statusDataCount to set
	 */
	public void setStatusDataCount(int statusDataCount) {
		this.statusDataCount = statusDataCount;
	}

	/**
	 * @return the alarmDataCount
	 */
	public int getAlarmDataCount() {
		return alarmDataCount;
	}

	/**
	 * @param alarmDataCount the alarmDataCount to set
	 */
	public void setAlarmDataCount(int alarmDataCount) {
		this.alarmDataCount = alarmDataCount;
	}

	/**
	 * @return the collectDataCount
	 */
	public int getCollectDataCount() {
		return collectDataCount;
	}

	/**
	 * @param collectDataCount the collectDataCount to set
	 */
	public void setCollectDataCount(int collectDataCount) {
		this.collectDataCount = collectDataCount;
	}



	/**
	 * @return the controlDataByEquipTypeList
	 */
	public List getControlDataByEquipTypeList() {
		return controlDataByEquipTypeList;
	}



	/**
	 * @param controlDataByEquipTypeList the controlDataByEquipTypeList to set
	 */
	public void setControlDataByEquipTypeList(List controlDataByEquipTypeList) {
		this.controlDataByEquipTypeList = controlDataByEquipTypeList;
	}



	/**
	 * @return the boilderCount
	 */
	public int getBoilderCount() {
		return boilderCount;
	}



	/**
	 * @param boilderCount the boilderCount to set
	 */
	public void setBoilderCount(int boilderCount) {
		this.boilderCount = boilderCount;
	}



	/**
	 * @return the shuilengCount
	 */
	public int getShuilengCount() {
		return shuilengCount;
	}



	/**
	 * @param shuilengCount the shuilengCount to set
	 */
	public void setShuilengCount(int shuilengCount) {
		this.shuilengCount = shuilengCount;
	}



	/**
	 * @return the waterCount
	 */
	public int getWaterCount() {
		return waterCount;
	}



	/**
	 * @param waterCount the waterCount to set
	 */
	public void setWaterCount(int waterCount) {
		this.waterCount = waterCount;
	}



	/**
	 * @return the eleCount
	 */
	public int getEleCount() {
		return eleCount;
	}



	/**
	 * @param eleCount the eleCount to set
	 */
	public void setEleCount(int eleCount) {
		this.eleCount = eleCount;
	}



	/**
	 * @return the typecurrentPage
	 */
	public Integer getTypecurrentPage() {
		return typecurrentPage;
	}



	/**
	 * @param typecurrentPage the typecurrentPage to set
	 */
	public void setTypecurrentPage(Integer typecurrentPage) {
		this.typecurrentPage = typecurrentPage;
	}



	public String getPointName() {
		return pointName;
	}



	public void setPointName(String pointName) {
		this.pointName = pointName;
	}



	public String getProjectPoint() {
		return projectPoint;
	}



	public void setProjectPoint(String projectPoint) {
		this.projectPoint = projectPoint;
	}



	public String getEquipName() {
		return equipName;
	}



	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}



	public String getStartTime() {
		return startTime;
	}



	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public DbSQLControldataBusiness getDbsqlcontroldatabusiness() {
		return dbsqlcontroldatabusiness;
	}



	public void setDbsqlcontroldatabusiness(
			DbSQLControldataBusiness dbsqlcontroldatabusiness) {
		this.dbsqlcontroldatabusiness = dbsqlcontroldatabusiness;
	}



	public PageMysql getPagemysql() {
		return pagemysql;
	}



	public void setPagemysql(PageMysql pagemysql) {
		this.pagemysql = pagemysql;
	}



	public String getDwbmSDCD() {
		return dwbmSDCD;
	}



	public void setDwbmSDCD(String dwbmSDCD) {
		this.dwbmSDCD = dwbmSDCD;
	}
	
}
