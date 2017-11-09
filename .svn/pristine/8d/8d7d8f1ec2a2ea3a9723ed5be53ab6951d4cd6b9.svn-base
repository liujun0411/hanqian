package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbLogin;
import com.hanqian.pojo.DbUserHosp;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 张电男
 * @version 1.4 2012-9-5
 * @see
 */
@Service
public class UsersBusiness extends BaseBusiness {

//	@Resource
//	private UsersManager usersManager;
//
//	@Resource
//	private HospInfoMgr hospInfoMgr;

	// 日志
	private static final Logger log = Logger.getLogger(UsersBusiness.class);

	/**
	 * 查询所有医院
	 * 
	 * @param 2012-11-27
	 * @param @return
	 * @return List
	 */
	public List findHospAll() {
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.findHospAll");
		List list = sqlSession.selectList("findHospAll");
		if (list == null && list.size() == 0) {
			return null;
		}
		return list;		
	}
	
	public Map getFindHospAllSql() {
		return PerformSQLUtil.get("findHospAll", this);
	}

	/**
	 * 查询系统头部菜单
	 * 
	 * @param 2012-11-27
	 * @param @param userId
	 * @param @return
	 * @return List
	 */
	public List findUsertopMeuns(Integer userId) {

		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.findUsertopMeuns,参数userId:" + userId);

		List<Map> list = sqlSession.selectList("findUsertopMeuns", userId);
		if (list == null && list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 根据用户ID 获取自己所有的权限ID
	 * 
	 * @param 2012-9-26
	 * @param @param dbUsers
	 * @param @return
	 * @return RetCode
	 */
	public List menuInterceptor(DbUsers dbUsers, String menuCode) {
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.menuInterceptor,参数dbUsers:"
					+ dbUsers+" menuCode:"+menuCode);
		if (dbUsers == null)
			return null;
//		String sql = "select menu.menu_code from db_menus menu where menu.menu_code in"
//				+ "   (select m.menu_code from db_menus  m "
//				+ "	where m.menu_code in (select rm.menuId from db_role_menu rm where rm.roleid in "
//				+ "	(select ur.roleid from db_userole ur where ur.userId="
//				+ dbUsers.getSeq()
//				+ ")) )"
//				+ "   and menu.parent_code='"
//				+ menuCode + "'";
//		if (log.isDebugEnabled())
//			log.debug("(UsersBusiness-->menuInterceptor)SQL=" + sql);
//
//		RetCode rc = usersManager.findBySQL(usersManager.getDbUsersDAO(), sql);
		Map mapvalue = new HashMap();
		mapvalue.put("seq", dbUsers.getSeq());
		mapvalue.put("menuCode", menuCode);
		RetCode rc = this.getData("menuInterceptor", mapvalue);
		
		
		return (List) rc.getObj();
	}

	/**
	 * 查询用户的所有权限
	 * 
	 * @param 2015-3-24
	 * @param @param dbUsers
	 * @param @return
	 * @return Map
	 */
	public Map findUserAllMenuId(DbUsers dbUsers) {

		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.findUserAllMenuId,参数seq:"
					+ dbUsers.getSeq());

		List<Map> lst = sqlSession.selectList("findUserAllMenuId",
				dbUsers.getSeq());

		if (lst == null && lst.size() == 0) {
			return null;
		}
		Map menuIdMap = null;
		if (lst != null) {
			menuIdMap = new HashMap();
			for (Map map : lst) {
				Object o = map.get("menu_code");
				menuIdMap.put(o.toString(), o.toString());
			}
		}

		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.findUserAllMenuId,获取到的map对象:"
					+ menuIdMap.toString());

		return menuIdMap;
	}

	public Map getFindUserAllMenuIdSql() {
		return PerformSQLUtil.get("findUserAllMenuId", this);
	}

	/**
	 * 验证用户登录
	 * 
	 * @param statement
	 * @param DbUsers
	 *            用户实体类
	 * @return 数据库是否存在的结果集
	 */
	public List<DbUsers> findDbUsersLogin(DbUsers dbUsers) throws Exception {
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.findDbUsersLogin,参数dbUsers对象:"
					+ dbUsers);

		DbUsers Users = new DbUsers();
		Users.setUserid(dbUsers.getUserid());
		Users.setPassword(dbUsers.getPassword());
		Users.setStatus(0);
		return sqlSession.selectList("findDbUsersLogin1", Users);
	}

	public Map getFindDbUsersLoginSql() {
		return PerformSQLUtil.get("findDbUsersLogin", this);
	}

	/**
	 * 判断该userid 是否存在
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public boolean checkUserid(String userid) {
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.checkUserid,参数userid:"
					+ userid);
		DbUsers dbUser = sqlSession.selectOne("checkUserid", userid);
		return dbUser != null?true:false;
	}
	
	public Map getCheckUseridSql() {
		return PerformSQLUtil.get("checkUserid", this);
	}

	/**
	 * 添加 实体
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public boolean insertObj(Object obj) {		
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.insertObj,对象obj:"
					+ obj);		
		boolean bool = true;		
		if(obj instanceof DbLogin){
			try{
				sqlSession.insert("insertDbLogin", obj);  
			}catch(Exception e){
				bool = false;
			}			
		}		
		if(obj instanceof DbUsers){
			try{
				sqlSession.insert("insertDbUsers", obj); 
			}catch(Exception e){
				bool = false;
			}	
		}	
		if(obj instanceof DbUserHosp){
			try{
				sqlSession.insert("insertDbUserHosp", obj); 
			}catch(Exception e){
				bool = false;
			}	
		}	
		return bool;
	}
	
	public Map getInsertObjSql(Object obj) {
		if(obj instanceof DbLogin){
			return PerformSQLUtil.get("insertDbLogin", this);
		}
		if(obj instanceof DbUsers){
			return PerformSQLUtil.get("insertDbUsers", this);
		}
		if(obj instanceof DbUserHosp){
			return PerformSQLUtil.get("insertDbUserHosp", this);
		}
		return null;
		
	}
	
	
	/**
	 * 分页
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findByHql(int currentPage, int pageSize) {	
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.findByHql,参数currentPage:"
					+ currentPage+" 参数pageSize:"+pageSize);			
		return this.getPageData("findByHql", null, currentPage, pageSize);		
	}
	
	public Map getFindByHqlSql() {
		return PerformSQLUtil.get("findByHql", this);
	}

	/**
	 * 查询系统访问量
	 * 
	 * @param 2015-3-24
	 * @param @return
	 * @return int
	 */
	public int findSysCount() {
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.findSysCount");
		int countnum = sqlSession.selectOne("findSysCount");
		if (countnum < 1) {
			countnum = 998;
		}

		return countnum;

	}

	public Map getFindSysCountSql() {
		return PerformSQLUtil.get("findSysCount", this);
	}

	/**
	 * 修改登录人 注销时间
	 * 
	 * @param 2012-9-24
	 * @param @param dbLogin
	 * @return void
	 */
	public void updateUserCloseTmie(DbLogin dbLogin) {
//		String sql = "update db_login set logouttime=sysdate where seq="
//				+ dbLogin.getSeq();
//		if (log.isDebugEnabled())
//			log.debug("(UsersBusiness-->updateUserCloseTmie)SQL=" + sql);
//		usersManager.updateBySQL(usersManager.getDbUsersDAO(), sql);
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.updateUserCloseTmie,参数dbLogin:"
					+ dbLogin);	
		sqlSession.update("updateUserCloseTmie", dbLogin);
		
		
	}

	/**
	 * 修改
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public boolean updateDbUsers(DbUsers dbUsers) {
		//return usersManager.updateDbUsers(dbUsers);
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.updateDbUsers,参数dbUsers:"
					+ dbUsers);			
		boolean bool = true;
		try{
			sqlSession.update("updateDbUsersById", dbUsers);
		}catch(Exception e){
			bool = false;
		}				
		return bool;
	}
	
	
	public Map getUpdateDbUsersSql() {
		return PerformSQLUtil.get("updateDbUsers", this);
	}

	/**
	 * 删除用户
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
//	public boolean deleteObj(Object obj) {
//		return usersManager.deleteObj(obj);
//	}

	/**
	 * 查询实体
	 * 
	 * @param statement
	 * @param class， id
	 * @return
	 */
	public Object findObjectByClassAndId(Class c, Object id) {	
		if (log.isDebugEnabled())
			log.debug("进入UsersBusiness.findObjectByClassAndId,类为:"
					+ c.getName()+"  参数id为:"+id);		
		String classname = c.getName();
		String boolname = classname.substring(classname.lastIndexOf(".")+1); 		
		if("DbUsers".equals(boolname)){
			return sqlSession.selectOne("selectDbUsersByPrimaryKey", id);
		}
		return null;		
	}
	
	public Map getFindObjectByClassAndIdSql() {
		return PerformSQLUtil.get("selectDbUsersByPrimaryKey", this);
	}
	
	public Map findHospInfoBySql(DbUsers users){
		if (log.isDebugEnabled()) 
			log.debug("进入UsersBusiness.findHospInfoBySql,对象为DbUsers:"+users);
		Map map = new HashMap();
		map.put("seq", users.getSeq());
		RetCode rc = this.getPageData("findHospInfoBySql", map);		
		List lst=(List)rc.getObj();
		if(lst!=null){
			return (Map)lst.get(0);
		}
		return null;
		
	}
	
	public Map getFindHospInfoBySql() {
		return PerformSQLUtil.get("findHospInfoBySql", this);
	}
	
	
	public RetCode findPhoneUtil(){
		if (log.isDebugEnabled()) 
			log.debug("进入UsersBusiness.findPhoneUtil!");
		
		RetCode rc = this.getPageData("findPhoneUtil", null);		
	
		return rc;
		
	}
	

	/*
	public UsersManager getUsersManager() { return usersManager; }
	  
	public void setUsersManager(UsersManager usersManager) {
	  this.usersManager = usersManager; 
	  }
	*/
//	public HospInfoMgr getHospInfoMgr() {
//		return hospInfoMgr;
//	}
//
//	public void setHospInfoMgr(HospInfoMgr hospInfoMgr) {
//		this.hospInfoMgr = hospInfoMgr;
//	}

}
