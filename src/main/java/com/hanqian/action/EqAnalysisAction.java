package com.hanqian.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.EqAnalysisBusiness;
import com.hanqian.form.VOEqAnalysis;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;


/**
 * 各设备能耗分析
 * @author clczp
 *
 */
public class EqAnalysisAction {
	private EqAnalysisBusiness steqBs;	//设备能耗Business
	private String imgPath;				//图形保存路径
	private VOEqAnalysis voeqa;			
	private HttpServletRequest request;
	
	public VOEqAnalysis getVoeqa() {
		return voeqa;
	}
	public void setVoeqa(VOEqAnalysis voeqa) {
		this.voeqa = voeqa;
	}
	public void setSteqBs(EqAnalysisBusiness steqBs) {
		this.steqBs = steqBs;
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
	 * 加载同类各设备能耗界面
	 * 1.判断用户登录是否失效
	 * 2.查询数据
	 * 3.输出数据
	 * @return
	 */
	public String showEqAnalysisUI(){
		String backPath="showEqAn";
		request=ServletActionContext.getRequest();		
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		
		if (!this.LoadisFail(dbusers)) {
			request.setAttribute("isfail", "yeas");
			return backPath;
		}		
		if (null != voeqa && !SysUtil.isNull(voeqa.getEqcodes())) {
			RetCode rt= steqBs.findEqAnData(voeqa,
					request.getRealPath(imgPath),imgPath);
			
			request.setAttribute("obj", rt.getObj());
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("selobj", steqBs.findEqSelData());	
		return backPath;
	}
	
}
