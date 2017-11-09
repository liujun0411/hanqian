package com.hanqian.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.HospInfoBusiness;
import com.hanqian.business.VolumeBusiness;
import com.hanqian.form.VOBusiness;
import com.hanqian.pojo.DbBusiness;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.util.RequestIntrospectHelper;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 医院业务量，显示列表，新增，修改 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c)
 * 2012 Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-9-11
 * @see
 */
public class BusinessAction {

	// 日志
	private static final Log log = LogFactory.getLog(BusinessAction.class);

	private VolumeBusiness volumeBusiness;
	private VOBusiness vobusiness = new VOBusiness();
	private HttpServletRequest request;
	// 医院基本信息
	private HospInfoBusiness hospInfoBusiness;
	// 权限
	private MenuInterceptor menuInterceptor;
	private Map menuMap;

	// 查询条件
	private String hospId = "1"; // 医院ID
	private String smonth; // 开始月份
	private String emonth; // 结束月份
	private String title; // 医院名
	private String editFlag = "2"; // 权限
	private String editsequence = null; // 标识要修改的ID

	// 分页
	int currentPage = 1;
	int pageSize = 10;

	/**
	 * 医院业务量列表 
	 * @param @return       
	 * @return String
	 */
	public String showBusiness() {
		// 判断权限
		menuMap = menuInterceptor.menuIntercept("2001002");
		// 若为空 ，进入登录界面
		if (menuMap == null) {
			MenuInterceptor.toLoginjsp();
			return null;
		}
		request = ServletActionContext.getRequest();
		// 开始时间和结束时间
		smonth = request.getParameter("smonth");
		emonth = request.getParameter("emonth");
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			log.error("BusinessAction-->showBusiness", e);
			currentPage = 1;
		}
		try {
			// 查询条件
			DbHospInfo dbHospInfo = new DbHospInfo();
			dbHospInfo.setSeqHosp(1);
			// 用辅助类做参数查询数据
			VOBusiness voBusiness = new VOBusiness();
			DbBusiness business = new DbBusiness();
			business.setDbHospInfo(dbHospInfo);
			voBusiness.setBusiness(business);
			voBusiness.setStartDate(smonth);
			voBusiness.setStopDate(emonth);
			voBusiness.setHospId(SysUtil.toInt(hospId));

			// 查询业务量列表
			RetCode rtBus = volumeBusiness.findBusiness(voBusiness,currentPage, pageSize);
			// 判断是否查询到数据
			if (rtBus.getObj() != null) {
				List<DbBusiness> businesslist = (List<DbBusiness>) rtBus.getObj();
				if (null != businesslist) {
					request.setAttribute("page", rtBus.getPage());
					request.setAttribute("businessList", businesslist);
					request.setAttribute("smonth", smonth);
					request.setAttribute("emonth", emonth);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BusinessAction-->showBusiness", e);
			request.setAttribute("message", "查询出错!请与管理员联系");
		}
		return "show";
	}

	/**
	 * 显示修改界面
	 * 
	 * @return
	 */
	public String showBusinessEdit() {
		try {
			request = ServletActionContext.getRequest();
			request.setAttribute("editsequence", request
					.getParameter("editsequence"));
			request.setAttribute("editFlag", request.getParameter("editFlag"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BusinessAction-->showBusinessEdit", e);
			request.setAttribute("message", "查询出错!请与管理员联系");
		}
		return showBusiness();
	}

	/**
	 * 显示添加界面
	 * 
	 * @return
	 */
	public String showBusinessAdd() {
		try {
			request = ServletActionContext.getRequest();
			// String hospitalid =
			// ((DbUsers)request.getSession().getAttribute("users")).getDbHospitalinfo().getSequence();
			DbHospInfo hospInfo = hospInfoBusiness.findHospInfoForId(1);
			request.setAttribute("editFlag", request.getParameter("editFlag"));
			request.setAttribute("hospInfo", hospInfo);
			request.setAttribute("isAdd", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BusinessAction-->showBusinessAdd", e);
			request.setAttribute("message", "查询出错!请与管理员联系");
		}
		return showBusiness();
	}

	/**
	 * 修改业务量
	 * 
	 * @return
	 */
	public String editBusiness() {
		try {
			request = ServletActionContext.getRequest();
			VOBusiness voBusiness = new VOBusiness();
			// 转换成表单提交方式（可以得到页面上的实体类字段相同的字段的值）
			RequestIntrospectHelper.introspect(voBusiness, request);
			request.setAttribute("message", volumeBusiness
					.updateBusiness(voBusiness));
			request.setAttribute("editFlag", request.getParameter("editFlag"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BusinessAction-->editBusiness", e);
			request.setAttribute("message", "修改出错!请与管理员联系");
		}
		return showBusiness();
	}

	/**
	 * 添加业务量
	 * 
	 * @return
	 */
	public String addBusiness() {
		try {
			request = ServletActionContext.getRequest();
			VOBusiness voBusiness = new VOBusiness();
			
			// 转换成表单提交方式（可以得到页面上的实体类字段相同的字段的值）
			RequestIntrospectHelper.introspect(voBusiness, request);
			
			request.setAttribute("message", volumeBusiness.addBusiness(voBusiness));
			request.setAttribute("editFlag", request.getParameter("editFlag"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BusinessAction-->addBusiness", e);
			request.setAttribute("message", "新增出错!请与管理员联系");
		}
		return showBusiness();
	}
	//取上报数据
	public void getBusiness(HashMap reportMap, DbReportRate reportRate) {
		RetCode business = volumeBusiness.findBusiness(
				new DbBusiness(), reportRate.getReportdate());
		if (business.getObj() != null) {
			reportMap.put(reportRate.getDbReportType().getTypeId()
					+ "", business.getObj());
		}
	}

	/**
	 * get,set方法
	 */
	public String getHospId() {
		return hospId;
	}

	public VolumeBusiness getVolumeBusiness() {
		return volumeBusiness;
	}

	public void setVolumeBusiness(VolumeBusiness volumeBusiness) {
		this.volumeBusiness = volumeBusiness;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public String getSmonth() {
		return smonth;
	}

	public void setSmonth(String smonth) {
		this.smonth = smonth;
	}

	public String getEmonth() {
		return emonth;
	}

	public void setEmonth(String emonth) {
		this.emonth = emonth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getEditsequence() {
		return editsequence;
	}

	public void setEditsequence(String editsequence) {
		this.editsequence = editsequence;
	}

	public VOBusiness getVobusiness() {
		return vobusiness;
	}

	public void setVobusiness(VOBusiness vobusiness) {
		this.vobusiness = vobusiness;
	}

	public HospInfoBusiness getHospInfoBusiness() {
		return hospInfoBusiness;
	}

	public void setHospInfoBusiness(HospInfoBusiness hospInfoBusiness) {
		this.hospInfoBusiness = hospInfoBusiness;
	}

	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}

	public Map getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map menuMap) {
		this.menuMap = menuMap;
	}

}
