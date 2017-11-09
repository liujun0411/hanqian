package com.hanqian.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BuildAnalysisBusiness;
import com.hanqian.business.WeatherBusiness;
import com.hanqian.form.VOStatCondition;
import com.hanqian.form.VOWeather;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;


/**
 * 楼宇数据分析
 * @author clczp
 *
 */
public class BuildAnalysisAction {
	
	
	private String imgPath;					//图片位置
	private WeatherBusiness wBs;			//天气情况
	private VOStatCondition  voobj;			//查询条件
	private BuildAnalysisBusiness buBs;		//业务层
	private HttpServletRequest request;
	
	
	
	
	
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
	 * 能耗分析
	 * @return
	 */
	public String showWastageUIA(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		//查询
		RetCode rt=buBs.findBuild(voobj,request.getRealPath(imgPath),imgPath,dbusers,true,true);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		request.setAttribute("name", "楼宇能耗量");
		
		return "wastage";
	}
	/**
	 * 折算金额 分析
	 * @return
	 */
	public String showConvertUIA(){
		request = ServletActionContext.getRequest();
		
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		//查询
		RetCode rt=buBs.findBuild(voobj,request.getRealPath(imgPath),imgPath,dbusers,false,true);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			//天气情况
			VOWeather vowe =wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("name", "楼宇能耗价值");
	
		return "convert";
	}
	
	/**
	 * 楼宇能耗
	 * 用户登录失效判断
	 * 查询
	 * 输出
	 * @return
	 */
	public String showWastageUI(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		//查询
		RetCode rt=buBs.findBuild(voobj,request.getRealPath(imgPath),imgPath,dbusers,true,false);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		request.setAttribute("name", "楼宇能耗量");
		
		return "wastage";
	}
	
	
	/**
	 * 能耗折算金额
	 * 用户登录失效判断
	 * 查询
	 * 输出
	 * @return
	 */
	public String showConvertUI(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		//查询
		RetCode rt=buBs.findBuild(voobj,request.getRealPath(imgPath),imgPath,dbusers,false,false);
		
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());	
			
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		request.setAttribute("name", "楼宇能耗价值");
		
		return "convert";
	}
	
	public VOStatCondition getVoobj() {
		return voobj;
	}
	public void setVoobj(VOStatCondition voobj) {
		this.voobj = voobj;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void setBuBs(BuildAnalysisBusiness buBs) {
		this.buBs = buBs;
	}
	public void setwBs(WeatherBusiness wBs) {
		this.wBs = wBs;
	}
	
	
}
