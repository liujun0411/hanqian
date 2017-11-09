package com.hanqian.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.MenusBusiness;
import com.hanqian.business.UsersBusiness;
import com.hanqian.pojo.DbMenus;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 张电男
 * @version 1.4 2012-9-5
 * @see
 */
public class MenusAction extends ActionSupport {

	// 权限逻辑层
	//日志
	private static final Log log = LogFactory.getLog(MenusAction.class);
	//权限逻辑层
	private MenusBusiness menusBusiness;
	private UsersBusiness usersBusiness;
	private MenuInterceptor menuInterceptor;
	private DbMenus dbMenus;
	private List menuList;
	private Map menuMap;
     
	private int dateMenuPageSize = 10;
	private int dateMenuPageCurrentPage = 1;

	// 分页
	private Page page; // 分页
	private int pageSize = 10;
	private int currentPage = 1;
	private String update;

	private Map menuIdMap;
	/**
	 * 传入父级ID,或者子项所有菜单
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String findMenus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//获取用户对象
		DbUsers dbUsers = MenuInterceptor.getSessionDbUsers();
		if(null==dbUsers){
			menuInterceptor.toLoginjsp();
			return null;
		}
//		System.out.println("进入查询");

		// 根据父级ID获取子项所有
		menuList = menusBusiness.findMenus(dbMenus.getMenuCode());

		// 获取自己所拥有的所有menuID
		menuIdMap = (Map) session.getAttribute("menuIdMap");
		if (null == menuIdMap) {
			menuIdMap = usersBusiness.findUserAllMenuId(dbUsers);
		//根据父级ID获取子项所有
		menuList=menusBusiness.findMenus(dbMenus.getMenuCode());
		//获取自己所拥有的所有menuID
		}
		

			menuMap=menuInterceptor.menuIntercept("3001001");
			//若为空 ，进入登录界面
			if(menuMap==null){
				MenuInterceptor.toLoginjsp();
				return null;
			}
			String eqTypeId=request.getParameter("eqTypeId");
			if(StringUtils.isNotEmpty(eqTypeId)){
				request.setAttribute("eqTypeId", eqTypeId);
			}
			request.setAttribute("menuMap", menuMap);
		return "menuLeft";
	}

	/**
	 * 权限列表
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String findMenuList() {

		RetCode rc = menusBusiness.findMenus(dbMenus, currentPage, pageSize);
		if (rc != null) {
			menuList = (List) rc.getObj();
			page = rc.getPage();
		} else {
			page = new Page(1, 0, 10);
		}

		return "menuList";
	}

	/**
	 * 逻辑删除权限
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String deleteMenus() {
		boolean check = menusBusiness.deleteMenus(dbMenus.getMenuId());
		return findMenuList();
	}

	/**
	 * 去到修改页面
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String toUpdateMenus() {
		// 所有父级权限
		menuParent = menusBusiness.FindMenu();
		// 获得权限信息
		dbMenus = (DbMenus) menusBusiness.findObject(DbMenus.class, dbMenus
				.getMenuId());

		this.update = "update";
		return "menuUpdate";
	}

	/**
	 * 修改权限
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String updateMenus() {

		DbMenus men = (DbMenus) menusBusiness.findObject(DbMenus.class, dbMenus
				.getMenuId());
		// 修改信息
		men.setDbMenus(dbMenus.getDbMenus());
		men.setName(dbMenus.getName());
		men.setDepict(dbMenus.getDepict());
		men.setSorts(dbMenus.getSorts());
		men.setDueDate(dbMenus.getDueDate());
		men.setMenuUrl(dbMenus.getMenuUrl());
		men.setMenuLevel(dbMenus.getMenuLevel());

		boolean check = menusBusiness.updateMenu(men);

		return findMenuList();
	}

	/**
	 * 去到添加页面
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	private String menuParent; // 父级权限
	public String toAddMenus() {
		// 获取所有父级权限
		menuParent = menusBusiness.FindMenu();
		return "menuUpdate";
	}

	public String addMenus() {
		// 设置是否显示
	dbMenus.setStatus(0);
		// 添加
		boolean check = menusBusiness.insertMenus(dbMenus);

		return findMenuList();
	}

	
	public String toLoginError(String str){
		HttpServletResponse res=ServletActionContext.getResponse();
		res.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		try {
			out = res.getWriter();
			out.write(str);	
		} catch (IOException e) {
			log.error("MenusAction-->toLoginError", e);
			log.debug(e.getMessage());
			e.printStackTrace();
		}finally{
			if(null!=out){
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 获取即将过期的菜单
	 * @param 2013-7-18  
	 * @param        
	 * @return void
	 */
	public void getReminders(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse respone = ServletActionContext.getResponse();
		String valid = "0";
		List list=new ArrayList();
		JSONObject obj = new JSONObject();
		try{
			if(request.getSession().getAttribute("menuOffset") != null){
				String offset = request.getSession().getAttribute("menuOffset").toString();
				RetCode rc = menusBusiness.getReminders(offset);
				if(rc!=null && rc.getObj()!=null){
					list=(List)rc.getObj();
					if(list.size()>0){
						Map temp = (Map)list.get(0);
						valid = temp.get("records") + "";
						obj.accumulate("record", valid);
					}
				}
			}
			respone.setCharacterEncoding("UTF-8");
			respone.getWriter().print(obj);
		}catch (Exception e) {
			log.error("MenusAction-->getReminders", e);	
		}
	}
	/**
	 * 得到即将过期或在过期缓冲期的菜单项
	 * @param 2013-7-19  
	 * @param        
	 * @return String
	 */
	public String findOutDateMens(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession().getAttribute("menuOffset") != null){
			String offset = request.getSession().getAttribute("menuOffset").toString();
			RetCode rt = menusBusiness.findOutDateMens(offset,dateMenuPageCurrentPage,dateMenuPageSize);
			if(rt!=null){
				request.setAttribute("outDateMenuList", rt.getObj());
				page = rt.getPage();
			}
		}
		return "outDatemenuList";
	}
	public MenusBusiness getMenusBusiness() {
		return menusBusiness;
	}
	public void setMenusBusiness(MenusBusiness menusBusiness) {
		this.menusBusiness = menusBusiness;
	}

	public DbMenus getDbMenus() {
		return dbMenus;
	}

	public void setDbMenus(DbMenus dbMenus) {
		this.dbMenus = dbMenus;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(String menuParent) {
		this.menuParent = menuParent;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public Map getMenuIdMap() {
		return menuIdMap;
	}

	public void setMenuIdMap(Map menuIdMap) {
		this.menuIdMap = menuIdMap;
	}

	public UsersBusiness getUsersBusiness() {
		return usersBusiness;
	}

	public void setUsersBusiness(UsersBusiness usersBusiness) {
		this.usersBusiness = usersBusiness;
	}

	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}

	public static Log getLog() {
		return log;
	}

	public Map getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map menuMap) {
		this.menuMap = menuMap;
	}


	/**
	 * @return the dateMenuPageSize
	 */
	public int getDateMenuPageSize() {
		return dateMenuPageSize;
	}

	/**
	 * @return the dateMenuPageCurrentPage
	 */
	public int getDateMenuPageCurrentPage() {
		return dateMenuPageCurrentPage;
	}


	/**
	 * @param dateMenuPageSize the dateMenuPageSize to set
	 */
	public void setDateMenuPageSize(int dateMenuPageSize) {
		this.dateMenuPageSize = dateMenuPageSize;
	}

	/**
	 * @param dateMenuPageCurrentPage the dateMenuPageCurrentPage to set
	 */
	public void setDateMenuPageCurrentPage(int dateMenuPageCurrentPage) {
		this.dateMenuPageCurrentPage = dateMenuPageCurrentPage;
	}

}