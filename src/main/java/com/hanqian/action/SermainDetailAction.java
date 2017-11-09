package com.hanqian.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.SerMainBusiness;
import com.hanqian.business.SeriverBusiness;
import com.hanqian.business.SermainDetailBsiness;
import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbSermainDetail;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class SermainDetailAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(SermainDetailAction.class);
	private SermainDetailBsiness sermainDetailBsiness;
	private SeriverBusiness seriverBusiness;
	private SerMainBusiness serMainBusiness;
	// 分页
	private Page page;
	private int pageSize = 10;
	private int currentPage = 1;
	private HttpServletRequest request;
	private List<DbBaseComm> classList;// 班组
	private List menList;// 维修人员list
	private DbSermainDetail dbSerDetail;
	// 权限
	private MenuInterceptor menuInterceptor;
	public Map menuIdMap; // 权限MAP集合

	/**
	 * 得到request
	 * 
	 * @param 2012-12-7
	 * @param @return
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getReq() {
		if (request == null) {
			request = ServletActionContext.getRequest();
		}
		return request;
	}
	/**
	 * 获取维修人员信息列表
	 * 
	 * @param 2012-12-6
	 * @param
	 */
	public String findMenList() {
		request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logg.error("SermainDetailAction-->findMenList", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pageIndex = getReq().getParameter("currentPage");
		if (StringUtils.isNotEmpty(pageIndex)) {
			currentPage = Integer.parseInt(pageIndex);
		} else {
			currentPage = 1;
		}
		pageSize = 10;
		String menName = getReq().getParameter("menName");
		String serId = getReq().getParameter("serId");
		String mainId = getReq().getParameter("mainId");
		try {
			if (StringUtils.isNotEmpty(menName)) {
				menName = new String(menName.getBytes("ISO-8859-1"), "UTF-8");
			}
		} catch (Exception e) {
			logg.error("SermainDetailAction-->findMenList", e);
			// TODO: handle exception
		}
		RetCode rc = sermainDetailBsiness.findSerMainList(menName, serId,mainId, currentPage, pageSize);
		List list = new ArrayList();
		if (rc != null && rc.getObj() != null) {
			menList = (List) rc.getObj();
			page = rc.getPage();
		}

		// 服务商列表
		getReq().setAttribute("serviceList", seriverBusiness.findServiceList());
		// 班组列表
		getReq().setAttribute("classList", serMainBusiness.findClassList());
		getReq().setAttribute("menName", menName);
		getReq().setAttribute("serId", serId);
		getReq().setAttribute("mainId", mainId);
		return "menList";
	}
	/**
	 * 删除
	 * 
	 * @param 2012-12-10
	 * @param @return
	 * @return String
	 */
	public String deleteMen() {
		request = ServletActionContext.getRequest();
		String message = "";
		boolean bool = false;
		//人员编号
		String seq = getReq().getParameter("seq");
		String yesOrNo = getReq().getParameter("yesOrNo");
		int exist = 0;
		if (StringUtil.isNotEmpty(seq)) {
			exist = sermainDetailBsiness.isExist(seq);
			if (exist>0&&(StringUtil.isEmpty(yesOrNo)||(!yesOrNo.equals("1")))) {
				getReq().setAttribute("yesOrNo", "no");
				getReq().setAttribute("tempId", seq);
			}else{
				getReq().setAttribute("yesOrNo", "yes");
				bool = sermainDetailBsiness.deleteMen(seq);
			}
		}
		if (bool) {
			message = "删除成功!";
		} else {
			message = "删除失败!";
		}
		if (exist>0) {
			message = "";
		}
		getReq().setAttribute("message", message);
		return this.findMenList();
	}
	
	/**
	 * 进入修改页面
	 * @return
	 */
	public String updateBeforeMen() {
		request = ServletActionContext.getRequest();
		String editFlag =null; 
		String seq =null;
		 try {
			  editFlag =getReq().getParameter("editFlag");// 判断
			  seq =getReq().getParameter("seq");
		} catch (Exception e) {
			logg.error("SermainDetailAction-->updateBeforeMen", e);
		}
		if (StringUtil.isNotEmpty(editFlag) && editFlag.equals("update")) {
			// 先通过seq找到该记录
			dbSerDetail = sermainDetailBsiness.findMenDetailByMenId(seq);
			getReq().setAttribute("alreayMains",sermainDetailBsiness.findClassIdByMenId(seq));

		} else {
			dbSerDetail = new DbSermainDetail();
		}
		if(("").equals(seq)){
			/**
			 * Des:新添加人员时，获取所有班组信息
			 * 
			 * Date:2015-08-18
			 */
			getReq().setAttribute("allMainList",sermainDetailBsiness.findClassBanzuAll());
		}else{
			/**
			 * Des:修改人员时，获取未选班组的信息
			 * 
			 * Date:2015-08-18
			 */
			// 所有人员
			getReq().setAttribute("allMainList",sermainDetailBsiness.findClassAll(seq));
		}

		getReq().setAttribute("editFlag", editFlag);
		getReq().setAttribute("serviceList", seriverBusiness.findServiceList());
		return "menEdit";
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String updateMen() {
		String message = "";
		request = ServletActionContext.getRequest();
		String classId = getReq().getParameter("classId");
		if (getReq().getSession().getAttribute("users") != null) {
			DbUsers user = (DbUsers) getReq().getSession()
					.getAttribute("users");
			dbSerDetail.setOper(user.getSeq());
			dbSerDetail.setOpertime(new Date());
		}

		boolean bool = sermainDetailBsiness.updateMenInfo(dbSerDetail, classId);
		if (bool) {
			message = "修改成功!";
		} else {
			message = "修改失败!";
		}
		getReq().setAttribute("message", message);
		return this.findMenList();
	}
	
	/**
	 * 新增
	 * @return
	 */
	public String insertMen(){
		try{
			String message = "";
			request = ServletActionContext.getRequest();
			String classId = getReq().getParameter("classId");
			if(getReq().getSession().getAttribute("users")!=null){
				DbUsers user = (DbUsers)getReq().getSession().getAttribute("users");
				dbSerDetail.setInput(user.getSeq());
				dbSerDetail.setInputtime(new Date());
			}
			boolean	bool = sermainDetailBsiness.insertMenInfo(dbSerDetail,classId);
			if (bool) {
				message = "新增成功!";
			}else{
				message = "新增失败!";
			}
			getReq().setAttribute("message", message);
		}catch (Exception e) {
			logg.error("SermainDetailAction-->insertMen", e);
			e.printStackTrace();	
		}
		return	this.findMenList();
	}
	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the classList
	 */
	public List<DbBaseComm> getClassList() {
		return classList;
	}

	/**
	 * @param classList
	 *            the classList to set
	 */
	public void setClassList(List<DbBaseComm> classList) {
		this.classList = classList;
	}

	public SermainDetailBsiness getSermainDetailBsiness() {
		return sermainDetailBsiness;
	}

	public void setSermainDetailBsiness(
			SermainDetailBsiness sermainDetailBsiness) {
		this.sermainDetailBsiness = sermainDetailBsiness;
	}

	/**
	 * @return the menList
	 */
	public List getMenList() {
		return menList;
	}

	/**
	 * @param menList
	 *            the menList to set
	 */
	public void setMenList(List menList) {
		this.menList = menList;
	}

	/**
	 * @return the seriverBusiness
	 */
	public SeriverBusiness getSeriverBusiness() {
		return seriverBusiness;
	}

	/**
	 * @param seriverBusiness
	 *            the seriverBusiness to set
	 */
	public void setSeriverBusiness(SeriverBusiness seriverBusiness) {
		this.seriverBusiness = seriverBusiness;
	}

	/**
	 * @return the serMainBusiness
	 */
	public SerMainBusiness getSerMainBusiness() {
		return serMainBusiness;
	}

	/**
	 * @param serMainBusiness
	 *            the serMainBusiness to set
	 */
	public void setSerMainBusiness(SerMainBusiness serMainBusiness) {
		this.serMainBusiness = serMainBusiness;
	}
	/**
	 * @return the dbSerDetail
	 */
	public DbSermainDetail getDbSerDetail() {
		return dbSerDetail;
	}
	/**
	 * @param dbSerDetail
	 *            the dbSerDetail to set
	 */
	public void setDbSerDetail(DbSermainDetail dbSerDetail) {
		this.dbSerDetail = dbSerDetail;
	}
	/**
	 * @return the menuInterceptor
	 */
	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}
	/**
	 * @param menuInterceptor
	 *            the menuInterceptor to set
	 */
	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}
	/**
	 * @return the menuIdMap
	 */
	public Map getMenuIdMap() {
		return menuIdMap;
	}
	/**
	 * @param menuIdMap
	 *            the menuIdMap to set
	 */
	public void setMenuIdMap(Map menuIdMap) {
		this.menuIdMap = menuIdMap;
	}

}