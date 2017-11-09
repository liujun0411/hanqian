package com.hanqian.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbMenus;
import com.hanqian.pojo.DbRoleMenu;
import com.hanqian.pojo.DbRoleMenuId;
import com.hanqian.pojo.DbRoles;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 张电男
 * @version 1.4 2012-9-5
 * @see
 */
@Service
public class RoleBusiness  extends BaseBusiness {
	//日志
	private static final Logger log = Logger.getLogger(RoleBusiness.class);
//	@Resource
//	private RoleManager roleMgr;

	/**
	 * 查询角色 ，分页
	 * @param statement  
	 * @param        
	 * @return retcode
	 */
	public RetCode findRoles(DbRoles dbRoles,int currentPage,int pageSize){
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.findDept,对象DbRoles:"
					+ dbRoles+" 当前页:"+currentPage+" 每页条数:"+pageSize);  
		return this.getPageData("findRoles", null, currentPage, pageSize);		
	}
	
	public Map getFindRolesSql() {
		return PerformSQLUtil.get("findRoles", this);
	}
	

	public String FindMenu(){
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.FindMenu,获取头部菜单!");  
		Map map = new HashMap();
		map.put("id", 0);
		RetCode rc=  this.getPageData("FindMenu", map);		
		List lst=(List)rc.getObj();
		Map menMap=(Map)lst.get(0);
		StringBuffer html=new StringBuffer("<li id="+menMap.get("menu_code")+">");
		html.append("<span>"+menMap.get("name")+"</span>");
		//根据父类ID查询旗下所有ID 2级菜单
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.FindMenu,获取2级菜单,参数menu_code:"+menMap.get("menu_code").toString());  
		Map map1 = new HashMap();
		map1.put("id", Integer.parseInt(menMap.get("menu_code").toString()));
		RetCode rc1=  this.getPageData("FindMenu", map1);
		List lst1=(List)rc1.getObj();
		html.append("<ul> ");
		for (int i = 0; i < lst1.size(); i++) {
			Map menMap1=(Map)lst1.get(i);
			html.append("<li id="+menMap1.get("menu_code")+"><span>"+menMap1.get("name")+"</span>");
			if (log.isDebugEnabled())
				log.debug("进入RoleBusiness.FindMenu,获取3级菜单,参数menu_code:"+menMap1.get("menu_code").toString());  
			Map map2 = new HashMap();
			map2.put("id", Integer.parseInt(menMap1.get("menu_code").toString()));
			RetCode rc2=  this.getPageData("FindMenu", map2);
			//获取三级菜单;			
			if(null!=rc2 && null!=rc2.getObj()){
				List lst2=(List)rc2.getObj();
				html.append("<ul>");
				for (int j = 0; j < lst2.size(); j++) {
					Map menMap2=(Map)lst2.get(j);
					html.append("<li id="+menMap2.get("menu_code")+"><span>"+menMap2.get("name")+"</span></li>");
				}
				html.append("</ul>");
			}
			html.append("</li>");
		}	
		html.append("</ul>");
		html.append("</li>");
		return html.toString();
	}
	public Map getFindMenuSql() {
		return PerformSQLUtil.get("FindMenu", this);
	}
	
	
	
	/**
	 * 根据id,class 查询
	 * @param statement  
	 * @param        
	 * @return Object
	 */
	public DbRoles findObj(Class c,Object id){		
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.findObj,对象id:"
					+ id);	
		DbRoles dbRoles  = new DbRoles();
		int roleId = Integer.parseInt(String.valueOf(id));
		dbRoles = (DbRoles)sqlSession.selectOne("findDbRoles",roleId);
		dbRoles.setRoleId(roleId);
		return dbRoles;
	}
	public Map getFindObjSql() {
		return PerformSQLUtil.get("findObj", this);
	}
	
	
	
	/**
	 *修改
	 * @param statement  
	 * @param        
	 * @return
	 */
	public boolean updateDbRole(DbRoles dbRoles){		
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.updateDbRole,对象dbRoles:"
					+ dbRoles);		
		boolean bool = true;
		try{
			sqlSession.update("updateDbRole", dbRoles);  
		}catch(Exception e){
			bool = false;
			log.error("进入RoleBusiness.updateDbRole,对象更新失败！", e);			
		}	
		return bool;
	}
	public Map getUpdateDbRoleSql() {
		return PerformSQLUtil.get("updateDbRole", this);
	}
	
	
	
	
	/**
	 * 查询所有角色信息
	 * @param statement  
	 * @param        
	 * @return list
	 */
	public List findRoleAll(){		
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.findRoleAll");		
		
		List lst= sqlSession.selectList("findRoleAll");  
		
		return lst;
	}
	public Map getFindRoleAllSql() {
		return PerformSQLUtil.get("findRoleAll", this);
	}
	
	/**
	 * 查询所有角色级别
	 * @param statement  
	 * @param        
	 * @return
	 */
	public RetCode findLevels(){
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.findLevels");
		RetCode rc = this.getPageData("findLevels", null);		
		return rc;
	}
	
	public Map getFindLevelsSql() {
		return PerformSQLUtil.get("findLevels", this);
	}
	
	
	/**
	 * 传入角色id,返回该角色对应的所有用户
	 * @param 2012-10-22  
	 * @param @param roleId
	 * @param @return       
	 * @return List
	 */
	public List findRoleUsers(int roleId){
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.findRoleUsers,参数roleid:"+roleId);
		
		List lst = sqlSession.selectList("findRoleUsers", roleId);
	
		return lst;
	}
	
	public Map getFindRoleUsersSql() {
		return PerformSQLUtil.get("findRoleUsers", this);
	}
	
	public boolean updateRoleUser(DbUsers dbUsers,int roleId,int [] userId){
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.updateRoleUser,对象dbUsers:"
					+ dbUsers);		
		boolean bool = true;
		try{			
			sqlSession.delete("deleteBySQL", roleId); 
		}catch(Exception e){
			bool = false;
			if (log.isDebugEnabled())
				log.debug("进入RoleBusiness.updateDbRole,对象删除失败！"
						+ e);	
		}	
		
		if(null!=userId){
			for (int i = 0; i < userId.length; i++) {
				//循环添加
				Map map = new HashMap();
				map.put("roleid",roleId );
				map.put("userid",userId[i] );
				map.put("oper",dbUsers.getSeq() );				
				map.put("status",0 );
				sqlSession.insert("insertBySQL", map);
				
			}
		}
		
		return bool;
		
	}
	public Map getUpdateRoleUserSql() {
		return PerformSQLUtil.get("updateRoleUser", this);
	}
	
	
	/**
	 * 传入角色id,返回与该角色无关系的所有用户
	 * @param 2012-10-22  
	 * @param @param roleId
	 * @param @return       
	 * @return List
	 */
	public List findRoleNotUsers(int roleId){
		
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.findRoleNotUsers,参数roleid:"+roleId);		
		List lst = sqlSession.selectList("findRoleNotUsers", roleId);		
		return lst;
	}
	public Map getFindRoleNotUsersSql() {
		return PerformSQLUtil.get("findRoleNotUsers", this);
	}
	
	/**
	 * 添加角色
	 * @param statement  
	 * @param        
	 * @return
	 */
	public boolean insertRole(DbRoles dbRoles,String[] menuCode)throws Exception{
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.insertRole,对象dbRoles:"
					+ dbRoles);		
		boolean chekc = true;
		try{			
			sqlSession.insert("insertRole", dbRoles);  
		}catch(Exception e){
			chekc = false;
			if (log.isDebugEnabled())
				log.debug("进入RoleBusiness.updateDbRole,insertRole插入对象失败！"
						+ e);	
		}	
		
		//添加角色与权限
		this.insertRoleMenu(dbRoles, menuCode);		
		return true;
	}
	
	public Map getInsertRoleSql() {
		return PerformSQLUtil.get("insertRole", this);
	}
	
	/**
	 * 添加角色与权限关系
	 * @param statement  
	 * @param        
	 * @return
	 */
	public boolean insertRoleMenu(DbRoles dbRoles,String[] menuId)throws Exception{
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.insertRoleMenu,对象dbRoles:"
					+ dbRoles);		
		if(null!=menuId && null!=dbRoles){
			for (int i = 0; i < menuId.length; i++) {

				//设置角色权限类
				DbRoleMenuId rmId=new DbRoleMenuId(menuId[i], dbRoles.getRoleId());
				//权限
				DbMenus menus=new DbMenus();
				menus.setMenuCode(menuId[i].toString());
				DbRoleMenu d=new DbRoleMenu(rmId, menus, dbRoles);
				d.setStatus(0);
				d.setOpertime(new Date());
				//循环添加
				sqlSession.insert("insertRoleMenu", d);				
			}
			
		}
		return true;
	}
	
	public Map getInsertRoleMenuSql() {
		return PerformSQLUtil.get("insertRoleMenu", this);
	}
	
	/**
	 * 删除角色与权限关系,传入dbroles ID 逻辑删除
	 * @param statement   update db_role_menu rm set rm.status=1 where rm.roleid=?;
	 * @param        
	 * @return boolean 
	 */
	public boolean deleteRoleMenu(DbRoles dbRoles){
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.deleteRoleMenu,对象dbRoles:"
					+ dbRoles);		
				
		boolean chekc = true;
		try{
			sqlSession.delete("deleteRoleMenu", dbRoles);  
		}catch(Exception e){
			chekc = false;
			if (log.isDebugEnabled())
				log.debug("进入RoleBusiness.deleteRoleMenu,删除对象失败！"
						+ e);	
		}	
		return chekc;		
	}
	
	public Map getDeleteRoleMenuSql() {
		return PerformSQLUtil.get("deleteRoleMenu", this);
	}
	
	/**
	 * 返回角色所用户的所有menuCode
	 * @param 2012-11-7  
	 * @param @param roleId
	 * @param @return       
	 * @return List
	 */
	public List findRoleMenuCodeAll(int roleId){
		
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.findRoleMenuCodeAll,对象roleId:"
					+ roleId);	
		List lst = sqlSession.selectList("findRoleMenuCodeAll", roleId);
		
		Map mapvalue= new HashMap();
		mapvalue.put("roleId", roleId);
		
		RetCode rc=this.getData("findDbRoleByRoleId", mapvalue);
		
		if(null!=rc)
			return (List)rc.getObj();
		else
			return null;
	}
	
	public Map getFindRoleMenuCodeAllSql() {
		return PerformSQLUtil.get("findRoleMenuCodeAll", this);
	}
	
	
	/**
	 * 角色逻辑删除后
	 * 
	 * 判断db_userRole是否有数据
	 * 
	 *  New 2015-06-18
	 * @param statement  
	 * @param        
	 * @return retcode
	 */
	public List listRole(Integer id){
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.findRoleMenuCodeAll,对象roleId:"
					+ id);	
		List lst = sqlSession.selectList("findListRole", id);
		
			return lst;
	}
	
	/**
	 * 
	 * 角色逻辑删除后
	 * 
	 * 在逻辑删除db_usesRoles表中数据
	 * 
	 *  New 2015-06-18
	 * @param statement  
	 * @param        
	 * @return retcode
	 */
	public boolean updateDbUsers(Integer id){
		if (log.isDebugEnabled())
			log.debug("进入RoleBusiness.deleteRoleMenu,对象dbRoles:"
					+ id);		
				
		boolean chekc = true;
		try{
			sqlSession.update("updateDbUsers", id);
		}catch(Exception e){
			chekc = false;
			if (log.isDebugEnabled())
				log.debug("进入RoleBusiness.deleteRoleMenu,删除对象失败！"
						+ e);	
		}	
		return chekc;		
	}
	
}
