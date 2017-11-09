package com.hanqian.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.MenusBusiness;
import com.hanqian.business.RoleBusiness;
import com.hanqian.pojo.DbMenus;
import com.hanqian.pojo.DbRoles;
import com.hanqian.pojo.DbUserole;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport {

	//日志
	private static final Log log = LogFactory.getLog(RoleAction.class);
	
	private Page page;				//分页
	private int pageSize = 5;
	private int currentPage = 1;
	
	private MenusBusiness menusBusiness;			//权限逻辑层
	private RoleBusiness  roleBusiness;				//角色逻辑层
	private MenuInterceptor menuInterceptor;		//权限控制
	
	
	private DbRoles dbRoles;
	private DbUserole dbUserole;
	private DbMenus dbMenus; 
	private List roleList;
	private List menuList;
	private List roleLevels;			//角色等级
	private String update;				// 判断是否 修改或新增页面
	
	

	/**
	 * 去到添加页面
	 * @param statement  
	 * @param        
	 * @return
	 */
	
	private String menuParent;
	public String getMenuParent() {
		return menuParent;
	}


	public void setMenuParent(String menuParent) {
		this.menuParent = menuParent;
	}

	
	public String toAddRole() {
		menuList=menusBusiness.findMenuAll();
		roleLevels=(List)roleBusiness.findLevels().getObj();
		menuParent=roleBusiness.FindMenu();
		
		return "roleAdd";
	}
	
	
	/**
	 * 去到用户角色关系管理界面
	 * @param 2012-10-22  
	 * @param @return       
	 * @return String
	 */
	private List userLst;			//所有用户
	private List roleUserLst;		//该角色所对应的用户
	public String toRoleUser(){
		//查询角色信息
		dbRoles=(DbRoles)roleBusiness.findObj(DbRoles.class, dbRoles.getRoleId());
		//所有用户
		userLst=roleBusiness.findRoleUsers(dbRoles.getRoleId());
		//自己以有用户
		roleUserLst=roleBusiness.findRoleNotUsers(dbRoles.getRoleId());
	
		return "roleUser";
	}
	
	private int[] userId;
	public String updateRoleUser(){
		HttpServletRequest req=ServletActionContext.getRequest();
//		System.out.println(userId);
		//获取登录用户
		DbUsers dbUsers=(DbUsers)req.getSession().getAttribute("users");
		//修改角色信息
		
		//修改所有用户,与角色关系
		roleBusiness.updateRoleUser(dbUsers,dbRoles.getRoleId(), userId);
		
		return findRoles();
	}
	
	/**
	 * 去到修改页面
	 * @param statement  
	 * @param        
	 * @return
	 */
	private Map menIdMap=new HashMap();
	public String toUpdateRole(){
		this.update="update";									//设置为修改
		menuList=menusBusiness.findMenuAll();					//所有权限
		
		roleLevels=(List)roleBusiness.findLevels().getObj();	//所有等级
		
		//查询当前角色讯息
		dbRoles=(DbRoles)roleBusiness.findObj(DbRoles.class, dbRoles.getRoleId());
		
		//设置需要默认选中的checkbox //获取该角色所拥有的所有权限CODE
		List lst=roleBusiness.findRoleMenuCodeAll(dbRoles.getRoleId());
		if(null!=lst ){
			for (int i = 0; i < lst.size(); i++) {
				Map map=(Map)lst.get(i);
				menIdMap.put(map.get("menuid"), map.get("menuid"));
			}			
		}
		
//		System.out.println("menIdMap size :"+menIdMap.size());
		return "roleUpdate";
	}
	
	
	/**
	 * 修改
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String updateRole(){
		HttpServletRequest req=ServletActionContext.getRequest();
		//查询	
		DbRoles role=(DbRoles)roleBusiness.findObj(DbRoles.class, dbRoles.getRoleId());
		//
		role.setStatus(dbRoles.getStatus()) ;   	//状态
		role.setName(dbRoles.getName());			//角色名称
		role.setDepict(dbRoles.getDepict());		//描述
		role.setRoleLevel(dbRoles.getRoleLevel());	//等级
		role.setUpdatertime(new Date());			//修改时间
		//修改人
		DbUsers user=(DbUsers)req.getSession().getAttribute("users");
		if(null!=user){
			role.setUpdater(user.getSeq());
		}

		//修改角色信息
		roleBusiness.updateDbRole(role);
		//删除角色权限
		roleBusiness.deleteRoleMenu(role);
		//添加角色权限
		try {
			roleBusiness.insertRoleMenu(role, menuCode);
		} catch (Exception e) {
			log.error("RoleAction-->updateRole",e);
		}
		
		
		return findRoles();
	}
	
	/**
	 * 添加
	 * @param statement  
	 * @param        
	 * @return
	 */
	private String[] menuCode;						//所有 权限ID
	public String addRole(){
		HttpServletRequest request=ServletActionContext.getRequest();
		try {
			DbUsers user=(DbUsers)request.getSession().getAttribute("users");
			if(null!=user){
				//创建人
				dbRoles.setOper(user.getSeq());			
			}
			dbRoles.setOpertime(new Date());
			dbRoles.setStatus(0);
		 roleBusiness.insertRole(dbRoles, menuCode);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		
		return findRoles();
	}
	

	/**
	 * 查询所有角色
	 * @param statement  
	 * @param        
	 * @return
	 */
	private Map menuMap;
	public String findRoles(){
		
		//权限配置
		menuMap=menuInterceptor.menuIntercept("5003");
		//若为空 ，进入登录界面
		if(menuMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}

		
		//查询所有角色
		RetCode rc=roleBusiness.findRoles(dbRoles, currentPage, pageSize);
		roleList=(List)rc.getObj();
		page=rc.getPage();
		return "roleList";
	}
	
	
	/**
	 * 逻辑删除
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String deleteRole(){

		//获取id ,查询
		try {
			DbRoles role=(DbRoles)roleBusiness.findObj(DbRoles.class, dbRoles.getRoleId());
			//修改状态，逻辑删除
			role.setStatus(1);
			boolean updateCheck=roleBusiness.updateDbRole(role);
			List  listRole=roleBusiness.listRole(dbRoles.getRoleId());
			if(listRole!=null&&listRole.size()>0){
			/*
			 * 角色逻辑删除后
			 * 在逻辑删除db_usesRoles表中数据
			 * 状态修改为“1”
			 */
				boolean updateDbUsers=roleBusiness.updateDbUsers(dbRoles.getRoleId());
			}
		} catch (Exception e) {
           log.error("-逻辑删除角色-"+e);
		}
		return findRoles();
	}
	
	public RoleBusiness getRoleBusiness() {
		return roleBusiness;
	}

	public void setRoleBusiness(RoleBusiness roleBusiness) {
		this.roleBusiness = roleBusiness;
	}

	public DbRoles getDbRoles() {
		return dbRoles;
	}

	public void setDbRoles(DbRoles dbRoles) {
		this.dbRoles = dbRoles;
	}

	public DbUserole getDbUserole() {
		return dbUserole;
	}

	public void setDbUserole(DbUserole dbUserole) {
		this.dbUserole = dbUserole;
	}

	public static Log getLog() {
		return log;
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

	public MenusBusiness getMenusBusiness() {
		return menusBusiness;
	}

	public void setMenusBusiness(MenusBusiness menusBusiness) {
		this.menusBusiness = menusBusiness;
	}

	public List getRoleList() {
		return roleList;
	}

	public List getRoleLevels() {
		return roleLevels;
	}

	public void setRoleLevels(List roleLevels) {
		this.roleLevels = roleLevels;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public DbMenus getDbMenus() {
		return dbMenus;
	}

	public void setDbMenus(DbMenus dbMenus) {
		this.dbMenus = dbMenus;
	}


	public String[] getMenuCode() {
		return menuCode;
	}


	public void setMenuCode(String[] menuCode) {
		this.menuCode = menuCode;
	}


	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public Map getMenIdMap() {
		return menIdMap;
	}

	public void setMenIdMap(Map menIdMap) {
		this.menIdMap = menIdMap;
	}


	public List getUserLst() {
		return userLst;
	}


	public void setUserLst(List userLst) {
		this.userLst = userLst;
	}


	public List getRoleUserLst() {
		return roleUserLst;
	}


	public void setRoleUserLst(List roleUserLst) {
		this.roleUserLst = roleUserLst;
	}


	public int[] getUserId() {
		return userId;
	}


	public void setUserId(int[] userId) {
		this.userId = userId;
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