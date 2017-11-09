package com.hanqian.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.EqRepairBusiness;
import com.hanqian.form.VOEqRepair;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;




/**
 * 设备维修比较Action
 * @author clczp
 *
 */
public class EqRepairAction {
	private HttpServletRequest request;
	private EqRepairBusiness eqrBs;		// 设备维修比较业务
	private String imgPath;				//图片保存位置
	private VOEqRepair voobj;			
	
	
	
	public VOEqRepair getVoobj() {
		return voobj;
	}
	public void setVoobj(VOEqRepair voobj) {
		this.voobj = voobj;
	}
	public void setEqrBs(EqRepairBusiness eqrBs) {
		this.eqrBs = eqrBs;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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
	 * 加载界面
	 * 1.判断用户登录是否失效
	 * 2.查询数据
	 * 3.输出数据
	 * @return
	 */
	public String showUI(){
		String backPath="show";
		request=ServletActionContext.getRequest();		
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}		
		if (null != voobj && !SysUtil.isNull(voobj.getEqids())) {
			RetCode rt= eqrBs.findEqRpairData(voobj,
					request.getRealPath(imgPath),imgPath);
			
			request.setAttribute("obj", rt.getObj());
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("selobj", eqrBs.findEqSelData());	
		return backPath;
	}
	
}
