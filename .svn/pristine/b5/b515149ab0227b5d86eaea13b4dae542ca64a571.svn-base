package com.hanqian.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.FeatureBusiness;
import com.hanqian.form.VOStatCondition;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;


/**
 * 特征区域数据分析 Action
 * @author clczp
 *
 */
public class FeatureAction {
	private VOStatCondition voobj;			//特征区域数据分析
	private FeatureBusiness feBs;	//特征区域能效分析业务类			
	private HttpServletRequest request;
	
	public VOStatCondition getVoobj() {
		return voobj;
	}

	public void setVoobj(VOStatCondition voobj) {
		this.voobj = voobj;
	}

	public void setFeBs(FeatureBusiness feBs) {
		this.feBs = feBs;
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
	 * 特征区域能耗量界面
	 * 1.判断用户登录是否失效
	 * 2.查询数据
	 * 3.输出数据
	 * @return
	 */
	public String showFeatrueUI(){
		String backPath="showfea";
		request=ServletActionContext.getRequest();		
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		if (voobj == null) {
			voobj = new VOStatCondition();			
		}
		voobj.setGoWhere("量");
		
		RetCode rt= feBs.findFeatureData(voobj);			
		request.setAttribute("obj", rt.getObj());
		request.setAttribute("message", rt.getDesc());
				
		return backPath;
	}
	
	/**
	 * 特征区域能耗价值界面
	 * 1.判断用户登录是否失效
	 * 2.查询数据
	 * 3.输出数据
	 * @return
	 */
	public String showFeatrueCUI(){
		String backPath="showfeac";
		request=ServletActionContext.getRequest();		
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}		
		
		if (voobj == null) {
			voobj = new VOStatCondition();			
		}
		voobj.setGoWhere("价值");
		
		RetCode rt= feBs.findFeatureData(voobj);			
		request.setAttribute("obj", rt.getObj());
		request.setAttribute("message", rt.getDesc());
			
		return backPath;
	}
}
