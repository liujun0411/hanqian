package com.hanqian.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.DeptBusiness;
import com.hanqian.business.UsersBusiness;
import com.hanqian.pojo.DbDept;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.opensymphony.xwork2.ActionSupport;

public class DeptAction extends ActionSupport {

	private DeptBusiness deptBusiness;
	private UsersBusiness usersBusiness;
	private HttpServletRequest request;
	
	private Page page;					//分页所用
	private int pageSize = 5;
	private int currentPage = 1;
	private String update;				//用户区别进去修改页面
	private DbDept dbDept;				//实体组织类
	private Integer userId;				//用户id
	private List dbDepts;				//组织集合
	private Map menuMap;	
	private MenuInterceptor menuInterceptor;
	
	/**添加组织
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String addDept() {
		request = ServletActionContext.getRequest();
		//查询操作员
			DbUsers use = (DbUsers) request.getSession().getAttribute("users");
			DbUsers user = new DbUsers();
			user.setSeq(use.getSeq());
			dbDept.setOpertime(new Date());
			dbDept.setStatus(0);
			dbDept.setDbUsersByOper(user);
		//添加
		boolean check=deptBusiness.insertDept(dbDept);
		request.setAttribute("message", check ? "操作成功" : "操作失败");
		return findDepts();
	}
	
	/**
	 * 去到修改 用户组织信息
	 */
	private List deptUserLst;
	private List notDeptUserLst;
	public String toUserDept(){
		//查询组织信息
		dbDept=deptBusiness.findDbDeptById(dbDept.getSeq());
		//获取该组织下已有用户
		deptUserLst=deptBusiness.findUserDept(dbDept.getSeq());
		//获取与该组织无关系的所有用户
		notDeptUserLst=deptBusiness.findUserNotDept(dbDept.getSeq());
		
		return "userDept";
	}
	
	/**
	 * 修改用户组织关系
	 */
	private String [] userSeq;
	public String userDepteUpdate(){
//		System.out.println("进入这里");
		//获取用户 获取医院
		DbUsers dbUsers=MenuInterceptor.getSessionDbUsers();
		if(dbUsers==null)
			return null;	//如果用户为空 直接返回登录首页
		//删除原所有该组织下所有用户
		deptBusiness.deleteDeptUser(dbDept.getSeq());
		
		//获取医院id；
		String hospId = "1";
//		if(null==map){
//			return null;
//		}
		
		//添加
		deptBusiness.insertDeptUser(userSeq, dbDept.getSeq(),hospId);
		
		return findDepts();
	}
	
	/**
	 * 查询组织部门
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String findDepts(){
		String menuCode="5002";
		//权限配置
		menuMap=menuInterceptor.menuIntercept(menuCode);
		//若为空 ，进入登录界面
		if(menuMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}
		RetCode rc=deptBusiness.findDept(dbDept, currentPage, pageSize);
		
		//如果查询到结果
		if(rc!=null){
			dbDepts=(List)rc.getObj();
			page=rc.getPage();
		}else{
			//如果未查出结果
			page=new Page(1,0,10);
		}
		
		return "deptList";
	}

	/**
	 * 删除组织 逻辑删除
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String deleteDept(){
		//先根据id查询
		DbDept dept=deptBusiness.findDbDeptById(dbDept.getSeq());
		//逻辑删除
		dept.setStatus(1);
		boolean check=deptBusiness.updateDept(dept);
		
		return findDepts();
	}
	
	/**
	 * 去到修改页面
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String toUpdateDept(){
		//先根据id查询
		dbDept=deptBusiness.findDbDeptById(dbDept.getSeq());
		this.update="update";
		
		return "deptAddOrUpdate";
	}
	
	/**
	 * 修改
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String updateDept(){
		//先根据id查询
		DbDept dept=deptBusiness.findDbDeptById(dbDept.getSeq());
		dept.setName(dbDept.getName());
		dept.setPrincipal(dbDept.getPrincipal());
		dept.setUpdatetime(new Date());
		
		deptBusiness.updateDept(dept);
		
		return "findDepts";
	}

	public DeptBusiness getDeptBusiness() {
		return deptBusiness;
	}


	public void setDeptBusiness(DeptBusiness deptBusiness) {
		this.deptBusiness = deptBusiness;
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


	public DbDept getDbDept() {
		return dbDept;
	}


	public void setDbDept(DbDept dbDept) {
		this.dbDept = dbDept;
	}


	public UsersBusiness getUsersBusiness() {
		return usersBusiness;
	}


	public void setUsersBusiness(UsersBusiness usersBusiness) {
		this.usersBusiness = usersBusiness;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List getDbDepts() {
		return dbDepts;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public void setDbDepts(List dbDepts) {
		this.dbDepts = dbDepts;
	}

	public Map getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map menuMap) {
		this.menuMap = menuMap;
	}

	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}

	public List getDeptUserLst() {
		return deptUserLst;
	}

	public void setDeptUserLst(List deptUserLst) {
		this.deptUserLst = deptUserLst;
	}

	public List getNotDeptUserLst() {
		return notDeptUserLst;
	}

	public void setNotDeptUserLst(List notDeptUserLst) {
		this.notDeptUserLst = notDeptUserLst;
	}

	public String[] getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String[] userSeq) {
		this.userSeq = userSeq;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	
}