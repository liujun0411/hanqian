package com.hanqian.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.AreaRatioBusiness;
import com.hanqian.common.CMyListData;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;



/**
 * 数据分析 面积比例情况
 * @author clczp
 *
 */
public class AreaRatioAction {
	private HttpServletRequest request;
	private AreaRatioBusiness areaMgr;
	
	
//	public AreaRatioMgr getAreaMgr() {
//		return areaMgr;
//	}

	public AreaRatioBusiness getAreaMgr() {
		return areaMgr;
	}

	public void setAreaMgr(AreaRatioBusiness areaMgr) {
		this.areaMgr = areaMgr;
	}

	private String imagePath;			//图片保存位置
	private Integer useId;				//区域编号

	
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
	 * 单区域面积比例
	 * 判断Session 是否失效
	 * 查询数据
	 * 输出数据
	 * @return
	 */
	public String showAreaOfOne(){
		request = ServletActionContext.getRequest();
		
		//返回路径
		String returnStr="showbili";		
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		if (!this.LoadisFail(dbusers)) {
			request.setAttribute("isfail", "yeas");
			return returnStr;
		}		
		
		//查询数据
		String selUnits= request.getParameter("selUnits");
		RetCode rt = areaMgr.findUseArea(dbusers, selUnits,useId);
		request.setAttribute("message",rt.getDetail());
		
		if (null !=rt.getObj()) {
			//输出数据
			request.setAttribute("obj",areaMgr.findManyAreaCMyShowData((CMyListData)rt.getObj(),request.getRealPath(imagePath),imagePath,dbusers,useId));			
		}
		
		
		return returnStr;
	}
	/**
	 * 区域面积,面积比例
	 * 判断Session 是否失效
	 * 查询数据
	 * 输出数据
	 * @return
	 */
	public String showAreaOf(){
		request = ServletActionContext.getRequest();
		//返回路径
		String returnStr="showarea";		
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		if (!this.LoadisFail(dbusers)) {
			request.setAttribute("isfail", "yeas");
			return returnStr;
		}		
		
		//查询数据
		String selUnits= request.getParameter("selUnits");
		RetCode rt = areaMgr.findArea(dbusers,selUnits);
		request.setAttribute("message",rt.getDetail());
		
		if (null !=rt.getObj()) {
			//输出数据
			request.setAttribute("obj",areaMgr.findCMyShowData((CMyListData)rt.getObj(),request.getRealPath(imagePath),imagePath,dbusers));			
		}
		
		return returnStr;
	}
	
	
	
	
	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
	public Integer getUseId() {
		return useId;
	}

	public void setUseId(Integer useId) {
		this.useId = useId;
	}

//	public void setAreaMgr(AreaRatioMgr areaMgr) {
//		this.areaMgr = areaMgr;
//	}

	
}
