package com.hanqian.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.hanqian.business.DeptBusiness;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.business.RoleBusiness;
import com.hanqian.business.UsersBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbLogin;
import com.hanqian.pojo.DbUserHosp;
import com.hanqian.pojo.DbUserHospId;
import com.hanqian.pojo.DbUserole;
import com.hanqian.pojo.DbUseroleId;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.MD5;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 张电男
 * @version 1.4 2012-9-5
 * @see
 */
public class UsersAction extends ActionSupport {

	private MenuInterceptor menuInterceptor;

	// Mybatis新增开始
	private HospInfoBusiness hospInfoBusiness; // 医院信息

	public HospInfoBusiness getHospInfoBusiness() {
		return hospInfoBusiness;
	}

	public void setHospInfoBusiness(HospInfoBusiness hospInfoBusiness) {
		this.hospInfoBusiness = hospInfoBusiness;
	}

	// Mybatis新增结束

	// 日志
	private static final Log log = LogFactory.getLog(UsersAction.class);
	private DbUsers dbUsers; // 用户实体
	private DbLogin dbLogin; // 登录日志
	private UsersBusiness usersBusiness; // 用户逻辑层
	private DeptBusiness deptBusiness; // 组织
	private RoleBusiness roleBusiness; // 角色

	// 分页
	private Page page;
	private int pageSize = 5;
	private int currentPage = 1;
	private int systemCount; // 系统访问量
	private List dbUserList; // 用户集合
	private List dbDepts; // 组织集合
	private List dbRoles; // 组织集合
	private int[] roleIds; // 角色id数组
	private int[] deptIds; // 组织 ID数组

	private EquipListBusiness equipListBusiness;
	private List equipList;

	public String findMyTopMenus() {
		DbUsers user = menuInterceptor.getSessionDbUsers();
		if (user == null) {
			menuInterceptor.toLoginjsp();
			return null;
		}
		HttpServletRequest req = ServletActionContext.getRequest();
		List lst = usersBusiness.findUsertopMeuns(user.getSeq());
		req.setAttribute("topMenus", lst);
		return "top";
	}

	/**
	 * 去到添加用户页面
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	private List hospAll;

	public String toAddUser() {
		// dbDepts=deptBusiness.findAll(); //所有组织//
		// dbRoles=roleBusiness.findRoleAll(); //所有角色//
		// System.out.println("进入");
		// 查询所有医院
		hospAll = usersBusiness.findHospAll();
		return "userAdd";
	}

	/**
	 * 去到修改页面
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	// private Map deptMap=new HashMap(); //保存该用户所有组织ID
	// private Map roleMap=new HashMap(); //保存该用户所有角色ID
	private String update;

	// 判断是进入修改页面还是添加页面
	public String toUpdateUser() {

		HttpServletRequest request = ServletActionContext.getRequest();

		// dbDepts=deptBusiness.findAll(); //所有组织
		// dbRoles=roleBusiness.findRoleAll(); //所有角色
		// 查询用户信息
		dbUsers = (DbUsers) usersBusiness.findObjectByClassAndId(DbUsers.class,
				dbUsers.getSeq());

		hospAll = usersBusiness.findHospAll();
		// 将用户密码解密
		if (null != dbUsers) {
			MD5 md5 = new MD5();

		}

		// 得到组织
		// List list =new ArrayList(dbUsers.getDbUserHosps());
		// for (int i = 0; i < list.size(); i++) {
		// DbUserHosp dept=(DbUserHosp)list.get(i);
		// deptMap.put(dept.getDbDept().getSeq(), dept.getDbDept().getSeq() );
		// }
		//
		// //得到组织
		// List list1 =new ArrayList(dbUsers.getDbUseroles());
		// for (int i = 0; i < list1.size(); i++) {
		// DbUserole r=(DbUserole)list1.get(i);
		// roleMap.put(r.getDbRoles().getRoleId(), r.getDbRoles().getRoleId() );
		// }

		this.update = "update";
		return "userAdd";
	}

	/**
	 * 修改用户信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String updateUser() {
		// 先查询
		DbUsers user = (DbUsers) usersBusiness.findObjectByClassAndId(
				DbUsers.class, dbUsers.getSeq());
		// //将原来所有的组织，以及角色删除
		// List list =new ArrayList(user.getDbUserHosps()); //删除组织
		// for (int i = 0; i < list.size(); i++) {
		// DbUserHosp dept=(DbUserHosp)list.get(i);
		// usersBusiness.deleteObj(dept);
		// }
		//
		// //得到组织
		// List list1 =new ArrayList(user.getDbUseroles()); //删除角色
		// for (int i = 0; i < list1.size(); i++) {
		// DbUserole r=(DbUserole)list1.get(i);
		// usersBusiness.deleteObj(r);
		// }
		// 替换用户信息
		MD5 md5 = new MD5(); // 密码加密
		user.setPassword(md5.getMD5ofStr(dbUsers.getPassword()));

		user.setUsername(dbUsers.getUsername());
		user.setPhone(dbUsers.getPhone());
		user.setTel1(dbUsers.getTel1());
		user.setTel2(dbUsers.getTel2());
		user.setAddress1(dbUsers.getAddress1());
		user.setAddress2(dbUsers.getAddress2());
		user.setEmail(dbUsers.getEmail());
		// 修改用户
		// System.out.println("\n\n"+user.getPassword());
		usersBusiness.updateDbUsers(user);

		// 添加新的组织
		if (deptIds != null) {
			for (int i = 0; i < deptIds.length; i++) {
				// 生成ID ，医院id暂写 为1 ，
				Map hospInfoMap = usersBusiness.findHospInfoBySql(user);
				int hospId = Integer.parseInt(hospInfoMap.get("seq_hosp")
						.toString());
				// System.out.println("医院ID" + hospId);
				DbUserHospId uhId = new DbUserHospId(hospId, dbUsers.getSeq(),
						deptIds[i]);
				// 表内容
				DbUserHosp d = new DbUserHosp();
				d.setId(uhId);
				usersBusiness.insertObj(d);
				log.info("hospId=" + hospId);
			}
		}

		// 添加角色
		if (roleIds != null) {
			for (int i = 0; i < roleIds.length; i++) {
				DbUseroleId urid = new DbUseroleId(dbUsers.getSeq(),
						(short) roleIds[i]);
				DbUserole d = new DbUserole();
				d.setId(urid);
				d.setOpertime(new Date());
				d.setStatus(0);
				usersBusiness.insertObj(d);
				log.info("dbUsers.getSeq=" + dbUsers.getSeq());
			}
		}
		findUsers();
		return "userList";
	}

	/**
	 * 添加用户
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String addUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		dbUsers.setStatus(0);
		dbUsers.setOpertime(new Date());//
		DbHospInfo info = new DbHospInfo();
		info.setSeqHosp(1);
		dbUsers.setDbHospInfo(info);
		// System.out.println(dbUsers.getDbHospInfo().getSeqHosp());
		// 添加用户
		MD5 md5 = new MD5(); // 为密码加密
		dbUsers.setPassword(md5.getMD5ofStr(dbUsers.getPassword()));
		
		boolean addCheck = usersBusiness.insertObj(dbUsers);
		if(addCheck == true){
			request.setAttribute("message", "操作成功");
		} else {
			request.setAttribute("message", "操作失败");
		}
		// //添加组织
		// if(deptIds!=null){
		// for (int i = 0; i < deptIds.length; i++) {
		// //生成ID ，医院id暂写 为1 ，
		// DbUserHospId uhId=new DbUserHospId(1, dbUsers.getSeq(), deptIds[i]);
		// //表内容
		// DbUserHosp d=new DbUserHosp();
		// d.setId(uhId);
		// usersBusiness.insertObj(d);
		// }
		// }
		//
		//
		// //添加角色
		// if(roleIds!=null){
		// for (int i = 0; i < roleIds.length; i++) {
		// DbUseroleId urid=new DbUseroleId(dbUsers.getSeq(),(short)roleIds[i]);
		// DbUserole d=new DbUserole();
		// d.setId(urid);
		// d.setOpertime(new Date());
		// d.setStatus(0);
		// usersBusiness.insertObj(d);
		// }
		// }
		
		return findUsers();
	}

	/**
	 * 查询用户信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	private String menuCode = "5001";
	private Map menuMap;

	public String findUsers() {

		// 获取权限
		menuMap = menuInterceptor.menuIntercept(menuCode);
		if (menuMap == null) {
			MenuInterceptor.toLoginjsp();
			return null;
		}

		// 查询
		RetCode rc = usersBusiness.findByHql(currentPage, pageSize);
		dbUserList = (List) rc.getObj();
		if (rc.getPage() == null) {
			page = new Page(1, 0, 5);
		} else {
			page = rc.getPage();
		}
		return "userList";
	}

	/**
	 * ajax 判断该userid 是否存在
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String checkUserid() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();

		String userid = request.getParameter("userid");
		log.info("userid=" + userid);
		boolean check = usersBusiness.checkUserid(userid);

		PrintWriter out = null;
		res.setCharacterEncoding("utf-8");
		try {
			res.setContentType("text/plain");
			out = res.getWriter();
			out.print(check);

		} catch (Exception e) {
			log.error("UsersAction-->checkUserid", e);
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return null;
	}

	/**
	 * 验证用户登录成功
	 * 
	 * @return login 成功页面,error 异常页面
	 */
	private Map menuIdMap;

	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userPwdError = "<script language='javascript'>alert('用户名,密码有误！');"
				+ "location.href='manager/login.jsp';</script>";
		String logError = "<script language='javascript'>alert('请登录！');"
				+ "location.href='manager/login.jsp';</script>";
		String sysError = "<script language='javascript'>alert('系统忙，请稍后再试！');"
				+ "location.href='manager/login.jsp';</script>";
		List<DbUsers> list;
		try {
			if (dbUsers == null) {
				String userID = request.getParameter("uid");
				String password = request.getParameter("pwd");
				log.info("userID=" + userID + ";password=" + password);
				if (StringUtils.isNotEmpty(userID)
						&& StringUtils.isNotEmpty(password)) {
					dbUsers = new DbUsers();
					dbUsers.setUserid(userID);
					dbUsers.setPassword(password);
				} else {
					menuInterceptor.toLoginjsp();
				}
			}
			if (null != dbUsers) {
				if (dbUsers.getUserid() == null
						|| dbUsers.getPassword() == null) {
					menuInterceptor.toLoginjsp();
					return null;
				}
			}
			if (request.getParameter("toSub") == null) {
				// 为密码加密
				MD5 md5 = new MD5(); // 密码加密
				if (dbUsers != null) {
					dbUsers.setPassword(md5.getMD5ofStr(dbUsers.getPassword()));
				}
			}

			// 查询
			list = usersBusiness.findDbUsersLogin(dbUsers);
			// 验证
			if (null != list && 1 > list.size()) {

				return toLoginError(userPwdError);
			}
			DbUsers users = (DbUsers) list.get(0);

			if (null == users) {
				return toLoginError(userPwdError);
			}

			// String currentHospCode = ConfigUtil.getConfig(
			// "config.properties", "currentHospCode");
			// String loadingFlag = ConfigUtil.getConfig(
			// "config.properties", "loadingFlag");
			// String olapTabStatus = ConfigUtil.getConfig(
			// "config.properties", "olapTabStatus");
			// String flashNewFlag = ConfigUtil.getConfig(
			// "config.properties", "flashNewFlag");
			// String speciaEqFlash = ConfigUtil.getConfig(
			// "config.properties", "speciaEqFlash");
			// String flashVersion = ConfigUtil.getConfig(
			// "config.properties", "flashVersion");
			// String menuOffset=ConfigUtil.getConfig("config.properties",
			// "menuOffset");
			// String menuTip=ConfigUtil.getConfig("config.properties",
			// "menuTip");
			log.info("users=" + users + ";users.getUserid()="
					+ users.getUserid() + ";dbUsers.getUserid()="
					+ dbUsers.getUserid());
			if (null != users && users.getUserid().equals(dbUsers.getUserid())) {
				// 登录成功
				// System.out.println(users.getDbHospInfo().getHospName());
				request.getSession().setAttribute("users", users);
				// 加入当前医院标识currentHospCode
				request.getSession().setAttribute("currentHospCode",
						ConfigCST.CST_CURRENT_HOSP_CODE);
				// loading花瓣与文字显示状态
				request.getSession().setAttribute("loadingFlag",
						ConfigCST.CST_LOADING_FLAG);
				// 加入OLAP选项卡状态
				request.getSession().setAttribute("olapTabStatus",
						ConfigCST.CST_olapTab_Status);
				// 加入OLAP选项卡状态Flash规则标识
				request.getSession().setAttribute("flashNewFlag",
						ConfigCST.CST_FLASH_NEWFLAG);
				// 加入特种设备展现方式(y:flash 其他是html)标识
				request.getSession().setAttribute("speciaEqFlash",
						ConfigCST.CST_SPECIAEQ_FLASH);
				// 加入新版本flash和旧版本flash展现方式标识
				request.getSession().setAttribute("flashVersion",
						ConfigCST.CST_FLASH_VERSION);
				// 加入菜单合同到期的弹性时间
				request.getSession().setAttribute("menuOffset",
						ConfigCST.CST_MEUU_OFFSET);
				// 加入菜单功能开启状态
				request.getSession().setAttribute("menuTip",
						ConfigCST.CST_MEUU_TIP);
				// 加入天气预报功能开启状态
				request.getSession().setAttribute("weatherShowFlag",
						ConfigCST.CST_WEATHER_SHOWFLAG);
				// 添加登录日志
				dbLogin = new DbLogin();
				dbLogin.setDbUsers(users);
				dbLogin.setLogintime(new Date());
				dbLogin.setIp(request.getRemoteAddr());
				usersBusiness.insertObj(dbLogin); // 添加日志
				// 查询系统访问量
				systemCount = usersBusiness.findSysCount();
				dbLogin.setSystemCount(systemCount);
				// 将登陆日志，以及访问量 放入session
				request.getSession().setAttribute("dbLogin", dbLogin);

				// 获取所有权限
				menuIdMap = usersBusiness.findUserAllMenuId(users);
				request.getSession().setAttribute("menuIdMap", menuIdMap);
				
				if (equipList == null) {
					RetCode rc = equipListBusiness.findEquipMaintenance("1");
					if (rc != null) {
						equipList = new ArrayList();
						equipList = (List) rc.getObj();
						if (equipList != null) {
							request.setAttribute("num", equipList.size());
							log.info("equipList="+equipList.size());
						}else{
							request.setAttribute("num", 0);
						}
					}else{
						request.setAttribute("num", 0);
					}
				}
				

				return "hosp_main";
				// return "change";
			} else {
				log.info("users为空，或者users.getUserid()和dbUsers.getUserid()不相等");
			}
		} catch (Exception e) {
			log.error("UsersAction-->login", e);
			return toLoginError(sysError);
		}
		menuInterceptor.toLoginjsp();
		return null;
	}

	/**
	 * 进入主界面
	 * 
	 * @param 2013-3-5
	 * @param @return
	 * @return String
	 */
	public String IntoMainPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 注销用户
		if (!SysUtil.isNullObject(request.getSession().getAttribute("users"))) {
			dbUsers = (DbUsers) request.getSession().getAttribute("users");
			// 获取所有权限
			menuIdMap = usersBusiness.findUserAllMenuId(dbUsers);
			request.getSession().setAttribute("menuIdMap", menuIdMap);
		}
		try {
			if (dbUsers == null) {
				menuInterceptor.toLoginjsp();
			} else {
				return "hosp_main";
			}
		} catch (Exception e) {
			log.error("UsersAction-->IntoMainPage", e);
			menuInterceptor.toLoginjsp();
		}
		menuInterceptor.toLoginjsp();
		return null;
	}

	/**
	 * 逻辑删除用户
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String deleteUser() {
		DbUsers user = (DbUsers) usersBusiness.findObjectByClassAndId(
				DbUsers.class, dbUsers.getSeq());
		user.setStatus(1);
		boolean check = usersBusiness.updateDbUsers(user);
		return findUsers();
	}

	/**
	 * 注销登录
	 * 
	 * @param statement
	 * @param
	 * @return login 登录页面
	 */
	public String closeUsers() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 注销用户
		request.getSession().setAttribute("users", null);
		// 修改登录人退出系统时间
		if (null != dbLogin) {
			usersBusiness.updateUserCloseTmie(dbLogin);
		}

		return "login";
	}

	/**
	 * 登录用户名，密码有误，跳转至manager/login.jsp
	 * 
	 * @param statement
	 * @param
	 * @return null
	 */

	public String toLoginError(String str) {
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html; charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.write(str);
		} catch (IOException e) {
			log.error("UsersAction-->toLoginError", e);
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return null;
	}

	/**
	 * 验证原密码与新密码是否一致
	 * 
	 * @param 2013-3-7
	 * @param @return
	 * @return String
	 */
	public void checkPassword() {
		boolean result = false;

		HttpServletRequest request = ServletActionContext.getRequest();
		// 接收前台页面传过来的用户ID与原密码
		String userId = request.getParameter("userid");
		String oldPassword = request.getParameter("oldPassword");
		log.info("userId=" + userId + ";oldPassword=" + oldPassword);
		// 通过前台页面传过来的用户ID到数据库中查询该ID所匹配的用户信息
		try {
			dbUsers = (DbUsers) usersBusiness.findObjectByClassAndId(
					DbUsers.class, StringUtil.getIntValue(userId));
		} catch (Exception e) {
			log.error("UsersAction-->checkPassword", e);
			e.printStackTrace();
		}
		// MD5加密
		MD5 md5 = new MD5();
		// 通过给页面输入的原密码加密
		String md5OldPwd = md5.getMD5ofStr(oldPassword);
		log.info("加密后的密码：MD5OldPwd=" + md5OldPwd);
		// 将页面输入的加密过的原密码，与数据库中加密的密码匹配是否一致。
		if (dbUsers.getPassword().equals(md5OldPwd)) {
			result = updatePassword(dbUsers);
		}
		PrintWriter pw = null;
		try {
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(result ? "y" : "n");
			pw.flush();
		} catch (IOException e) {
			log.error("UsersAction-->checkPassword", e);
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

	/**
	 * 修改用户密码
	 * 
	 * @param 2013-3-7
	 * @param @return
	 * @return String
	 */
	private boolean updatePassword(DbUsers dbUsers) {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 从页面获取新密码
		String newPassword = request.getParameter("newPassword");
		MD5 md5 = new MD5();
		// 修改用户密码，并通过MD5加密。
		dbUsers.setPassword(md5.getMD5ofStr(newPassword));
		log.info("newPassword=" + newPassword);
		return usersBusiness.updateDbUsers(dbUsers);
	}

	public DbUsers getDbUsers() {
		return dbUsers;
	}

	public void setDbUsers(DbUsers dbUsers) {
		this.dbUsers = dbUsers;
	}

	public UsersBusiness getUsersBusiness() {
		return usersBusiness;
	}

	public void setUsersBusiness(UsersBusiness usersBusiness) {
		this.usersBusiness = usersBusiness;
	}

	public static Log getLog() {
		return log;
	}

	public DeptBusiness getDeptBusiness() {
		return deptBusiness;
	}

	public void setDeptBusiness(DeptBusiness deptBusiness) {
		this.deptBusiness = deptBusiness;
	}

	public List getDbDepts() {
		return dbDepts;
	}

	public void setDbDepts(List dbDepts) {
		this.dbDepts = dbDepts;
	}

	public RoleBusiness getRoleBusiness() {
		return roleBusiness;
	}

	public DbLogin getDbLogin() {
		return dbLogin;
	}

	public void setDbLogin(DbLogin dbLogin) {
		this.dbLogin = dbLogin;
	}

	public int getSystemCount() {
		return systemCount;
	}

	public void setSystemCount(int systemCount) {
		this.systemCount = systemCount;
	}

	public void setRoleBusiness(RoleBusiness roleBusiness) {
		this.roleBusiness = roleBusiness;
	}

	public List getDbRoles() {
		return dbRoles;
	}

	public void setDbRoles(List dbRoles) {
		this.dbRoles = dbRoles;
	}

	public int[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(int[] roleIds) {
		this.roleIds = roleIds;
	}

	public int[] getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(int[] deptIds) {
		this.deptIds = deptIds;
	}

	public List getDbUserList() {
		return dbUserList;
	}

	public void setDbUserList(List dbUserList) {
		this.dbUserList = dbUserList;
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

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public void setMenuId(Map menuIdMap) {
		this.menuIdMap = menuIdMap;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public Map getMenuIdMap() {
		return menuIdMap;
	}

	public void setMenuIdMap(Map menuIdMap) {
		this.menuIdMap = menuIdMap;
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

	public List getHospAll() {
		return hospAll;
	}

	public void setHospAll(List hospAll) {
		this.hospAll = hospAll;
	}

	public EquipListBusiness getEquipListBusiness() {
		return equipListBusiness;
	}

	public void setEquipListBusiness(EquipListBusiness equipListBusiness) {
		this.equipListBusiness = equipListBusiness;
	}

	public List getEquipList() {
		return equipList;
	}

	public void setEquipList(List equipList) {
		this.equipList = equipList;
	}

}