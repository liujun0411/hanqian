package com.hanqian.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.HospDataAnalysisBusiness;
import com.hanqian.business.WeatherBusiness;
import com.hanqian.form.VOStatCondition;
import com.hanqian.form.VOWeather;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;



/**
 * 医院数据分析
 * @author clczp
 *
 */
public class HospDataAnalysisAction {
	
	private String imgPath;			//图片路径		
	private WeatherBusiness wBs;	//天气情况
	private VOStatCondition  voobj;	//查询条件
	private HttpServletRequest request;
	private HospDataAnalysisBusiness sthosBs;	
	
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
	 * 显示界面
	 * 用户登录失效判断
	 * 查询
	 * 输出
	 * @return
	 */
	public String showUI(){
		request = ServletActionContext.getRequest();
	
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");		
		if (null==dbusers) {
			return "login";
		}
		//查询
		RetCode rt=sthosBs.findAnalysisZH(voobj,request.getRealPath(imgPath),imgPath,dbusers);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", ((Object[])rt.getObj())[0]);	
			
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		return "show";
	}
	
	
	
	
	public VOStatCondition getVoobj() {
		return voobj;
	}

	public void setVoobj(VOStatCondition voobj) {
		this.voobj = voobj;
	}



	public String getImgPath() {
		return imgPath;
	}



	public void setImgPath(String imgPath) {
		imgPath=SysUtil.replacePath(imgPath);
		this.imgPath = imgPath;
	}


	


	public void setwBs(WeatherBusiness wBs) {
		this.wBs = wBs;
	}



	public void setSthosBs(HospDataAnalysisBusiness sthosBs) {
		this.sthosBs = sthosBs;
	}
	public static void main(String[] args) {
//		System.out.println(     );
	}
}
