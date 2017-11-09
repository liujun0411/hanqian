package com.hanqian.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BusinessAnalysisBusiness;
import com.hanqian.business.WeatherBusiness;
import com.hanqian.form.VOStatCondition;
import com.hanqian.form.VOWeather;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;


/**
 * 业务数据分析
 * @author clczp
 *
 */
public class BusinessAnalysisAction {
	private String imgPath;					//图片位置
	private WeatherBusiness wBs;			//天气情况
	private VOStatCondition  voobj;			//查询条件
	private HttpServletRequest request;
	private BusinessAnalysisBusiness busBs;
	
	
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public VOStatCondition getVoobj() {
		return voobj;
	}
	public void setwBs(WeatherBusiness wBs) {
		this.wBs = wBs;
	}
	public void setVoobj(VOStatCondition voobj) {
		this.voobj = voobj;
	}
	public void setBusBs(BusinessAnalysisBusiness busBs) {
		this.busBs = busBs;
	}
	
	
	/**
	 * 登陆是否失效
	 * false 失效 ；true 正常
	 * @return
	 */
	private boolean LoadisFail(DbUsers dbusers){		
		if (null == dbusers) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 门诊能耗量
	 * @return
	 */
	public String showMZUI(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		boolean isfx = false;
		
		//查询
		RetCode rt = busBs.findBuild(voobj, 1, request.getRealPath(imgPath), imgPath, dbusers, true ,isfx);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("title", "每门诊能耗量");
		
		return "showmz";
	}
	
	/**
	 * 急诊能耗量
	 * @return
	 */
	public String showJZUI(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		boolean isfx = false;
//		String fx = request.getParameter("isfx");
//		if(!SysUtil.isNull(fx)){
//			isfx = true;
//		}
		
		//查询
		RetCode rt = busBs.findBuild(voobj, 2, request.getRealPath(imgPath), imgPath, dbusers, true,isfx);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("title", "每急诊能耗量");
		
		return "showjz";
	}
	
	
	/**
	 * 住院能耗量
	 * @return
	 */
	public String showZYUI(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		boolean isfx = false;
//		String fx = request.getParameter("isfx");
//		if(!SysUtil.isNull(fx)){
//			isfx = true;
//		}
		
		//查询
		RetCode rt = busBs.findBuild(voobj, 3, request.getRealPath(imgPath), imgPath, dbusers, true,isfx);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("title", "每住院能耗量");
		
		return "showmz";
	}
	
	
	/**
	 * 门诊能耗价值
	 * @return
	 */
	public String showMZUIC(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		boolean isfx = false;
//		String fx = request.getParameter("isfx");
//		if(!SysUtil.isNull(fx)){
//			isfx = true;
//		}
		
		//查询
		RetCode rt = busBs.findBuild(voobj, 1, request.getRealPath(imgPath), imgPath, dbusers, false ,isfx);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("title", "每门诊能耗量价值");
		
		return "showmzc";
	}
	
	/**
	 * 急诊能耗价值
	 * @return
	 */
	public String showJZUIC(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		boolean isfx = false;
//		String fx = request.getParameter("isfx");
//		if(!SysUtil.isNull(fx)){
//			isfx = true;
//		}
		
		//查询
		RetCode rt = busBs.findBuild(voobj, 2, request.getRealPath(imgPath), imgPath, dbusers, false,isfx);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("title", "每急诊能耗量价值");
		
		return "showjzc";
	}
	
	/**
	 * 住院能耗价值
	 * @return
	 */
	public String showZYUIC(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		boolean isfx = false;
//		String fx = request.getParameter("isfx");
//		if(!SysUtil.isNull(fx)){
//			isfx = true;
//		}
		
		//查询
		RetCode rt = busBs.findBuild(voobj, 3, request.getRealPath(imgPath), imgPath, dbusers, false,isfx);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("title", "每住院能耗量价值");
		
		return "showzyc";
	}
}
