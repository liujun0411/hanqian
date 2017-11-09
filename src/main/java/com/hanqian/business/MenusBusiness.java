package com.hanqian.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbMenus;
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
public class MenusBusiness   extends BaseBusiness {
	
	//日志
	//private static final Log log = LogFactory.getLog(MenusBusiness.class);
	public static final Logger log = Logger.getLogger(MenusBusiness.class);
//	@Resource
//	private MenusManager menusManager;
	
	/**
	 * 获取拥有子级菜单的 菜单
	 * @param 2012-9-26  
	 * @param @return       
	 * @return String
	 */
	public String FindMenu(){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.FindMenu!");
		
		//String sql="select m.menu_id,m.name from db_menus m where m.menu_id=";
		//获取头部菜单
		//RetCode rc= menusManager.findBySQL(menusManager.getDbMenusDAO(), sql+0);
		RetCode rc= this.getData("findMenuDbMenusDAOFirst", null);		
		List lst=(List)rc.getObj();
		Map menMap=(Map)lst.get(0);
		StringBuffer html=new StringBuffer("<li id="+menMap.get("menu_id")+">");
		html.append("<span>"+menMap.get("name")+"</span>");
		
		//根据父类ID查询旗下所有ID 2级菜单
//		String sql1="select m.menu_id,m.name from db_menus m where m.parent_id=";
//		RetCode rc1=menusManager.findBySQL(menusManager.getDbMenusDAO(), 
//				sql1+Integer.parseInt(menMap.get("menu_id").toString()));
		Map mapparam = new HashMap();
		int  parentId = Integer.parseInt(menMap.get("menu_id").toString());
		mapparam.put("parentId", parentId);
		
		RetCode rc1= this.getData("findMenuDbMenusDAOTwo", mapparam);
		List lst1=(List)rc1.getObj();
		html.append("<ul> ");
		for (int i = 0; i < lst1.size(); i++) {
			Map menMap1=(Map)lst1.get(i);
			html.append("<li id="+menMap1.get("menu_id")+"><span>"+menMap1.get("name")+"</span>");
			
			//获取三级菜单;
//			RetCode rc2=menusManager.findBySQL(menusManager.getDbMenusDAO(), 
//					sql1+Integer.parseInt(menMap1.get("menu_id").toString()));
			
			Map mapparam2 = new HashMap();
			parentId = Integer.parseInt(menMap1.get("menu_id").toString());
			mapparam2.put("parentId", parentId);
			
			RetCode rc2= this.getData("findMenuDbMenusDAOTwo", mapparam2);
			
			
			if(null!=rc2 && null!=rc2.getObj()){
				List lst2=(List)rc2.getObj();
				html.append("<ul>");
				for (int j = 0; j < lst2.size(); j++) {
					Map menMap2=(Map)lst2.get(j);
					html.append("<li id="+menMap2.get("menu_id")+"><span>"+menMap2.get("name")+"</span></li>");
				}
				html.append("</ul>");
			}
			html.append("</li>");
		}	
		html.append("</ul>");
		html.append("</li>");

		return html.toString();
	}
	

	
	
	
	public void test(List lst,List myLst){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.test!集合List:"+lst+" List:"+myLst);
		
		for (int i = 0; i < lst.size(); i++) {
			DbMenus men=(DbMenus)lst.get(i);
			String menuId=men.getMenuCode();
			//List lst1=menusManager.findMenus(menuId);
			List lst1=sqlSession.selectList("findMenusTest", menuId);
			men.setDbMenuses(new HashSet(lst1));
			test1(men);
			myLst.add(men);
		}
	}
	
	public Map getTestSql() {
		return PerformSQLUtil.get("findMenusTest", this);
	}
	
	public void test1(DbMenus dbMenu){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.test1!对象DbMenus:"+dbMenu);
		String menuId=dbMenu.getMenuCode();
		//List lst1=menusManager.findMenus(dbMenu.getMenuCode());
		List lst1=sqlSession.selectList("findMenusTest", menuId);
		dbMenu.setDbMenuses(new HashSet(lst1));
	}
	
	public Map getTest1Sql() {
		return PerformSQLUtil.get("findMenusTest", this);
	}
	
	/**
	 * 获取所有权限
	 * @param statement  
	 * @param        
	 * @return list
	 */
	public List findMenus(String parentMenuId){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.findMenus!参数parentMenuId:"+parentMenuId);
		//String menuId = parentMenuId;
		List lstfianlly = new ArrayList(); 
		Map map = new HashMap();
		map.put("parentMenuId", parentMenuId);
		List lst =sqlSession.selectList("findMenusTest", map);
		for (int i = 0; i < lst.size(); i++) {
			DbMenus dbMenus = (DbMenus)lst.get(i);
			String childid = dbMenus.getMenuCode();
			Map map1 = new HashMap();
			map1.put("parentMenuId", childid);
			List lst1 =sqlSession.selectList("findMenusTest", map1);
			if (lst1.size()>0) {
				//Set set = new HashSet();
//				Set<DbMenus> set = new TreeSet<DbMenus>(
//					new Comparator()
//					{
//						int r=0;
//						@Override
//						public int compare(Object o1, Object o2)
//						{
//							int n1=Integer.parseInt(((DbMenus)o1).getMenuId().toString());
//							int n2=Integer.parseInt(((DbMenus)o2).getMenuId().toString());
//							return 0;
//						}
//						
//					}
//				);
				Set<DbMenus> set =new TreeSet<DbMenus>(
					     new java.util.Comparator<DbMenus>(){
					      @Override
					      public int compare(DbMenus o1, DbMenus o2) {
					       return (o1.getMenuId()-o2.getMenuId());
					      }
					      
					     }
					    );
				
				
				
				for (int j = 0; j < lst1.size(); j++) {
					DbMenus dbMenus1 = (DbMenus)lst1.get(j);					
					set.add(dbMenus1);
				}
				dbMenus.setDbMenuses(set);
				lstfianlly.add(dbMenus);
			}else{
				lstfianlly.add(dbMenus);
			}
			
		}
		
		
		return lstfianlly;
		//return menusManager.findMenus(parentMenuId);
	}
	
	public Map getFindMenusSql() {
		return PerformSQLUtil.get("findMenusTest", this);
	}
	
	
	/**
	 * 查询所有
	 * @param statement  
	 * @param        
	 * @return
	 */
	public List findMenuAll(){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.findMenuAll!");
		
		return sqlSession.selectList("findMenuAll");
		//return menusManager.findMenuAll();
	}
	
	public Map getFindMenuAllSql() {
		return PerformSQLUtil.get("findMenuAll", this);
	}
	
	/**
	 * 条件 ，分页查询 
	 * @param statement  
	 * @param        
	 * @return
	 */
	public RetCode findMenus(DbMenus dbMenus,int currentPage,int pageSize){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.findMenus!对象dbMenus:"+dbMenus+" currentPage:"+currentPage+" pageSize:"+pageSize);
		
//		StringBuffer sql=new StringBuffer("select m.menu_id,m.name,m.menu_url,m.menu_level,m.remarks,m.depict," +
//				"(select m1.name from db_menus m1 where m1.menu_id=m.parent_id) as parentName " +
//				"from db_menus m where m.status=0 ");
		Map mapvalue = new HashMap();
		
		
		if(null!=dbMenus){
			if(null!=dbMenus.getName() && !"".equals(dbMenus.getName())){
				mapvalue.put("name", dbMenus.getName())	;		//匹配查询 名称条件
			}else{
				mapvalue.put("name", null)	;
			}
			if(null!=dbMenus.getMenuUrl() && !"".equals(dbMenus.getMenuUrl())){
				mapvalue.put("menuUrl", dbMenus.getMenuUrl())	;//匹配查询 url地址
			}else{
				mapvalue.put("menuUrl", null)	;
			}
			if(null!=dbMenus.getRemarks() && !"".equals(dbMenus.getRemarks())){
				mapvalue.put("remarks", dbMenus.getRemarks())	;//匹配查询 备注
			}else{
				mapvalue.put("remarks", null)	;
			}
			if(null!=dbMenus.getDepict() && !"".equals(dbMenus.getDepict())){
				mapvalue.put("depict", dbMenus.getDepict());			//匹配查询 描述
			}else{
				mapvalue.put("depict", null);	
			}
		}else{
			mapvalue.put("name", null)	;
			mapvalue.put("menuUrl", null)	;
			mapvalue.put("remarks", null)	;
			mapvalue.put("depict", null);
		}
		//sql.append(" order by m.parent_id ");
//		System.out.println(sql.toString());
		//return menusManager.findMenus(sql.toString(), currentPage, pageSize);
		return this.getPageData("findMenusByPage", mapvalue, currentPage, pageSize);
	}
	
	
	public Map getFindMenusByPageSql() {
		return PerformSQLUtil.get("findMenusByPage", this);
	}

	/**
	 * 根据ID查询
	 * @param statement  
	 * @param        
	 * @return
	 */
	public Object findObject(Class c,Object id){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.findObject!对象Object:"+id);
		
		Map map = new HashMap();
		map.put("menuId", Integer.valueOf(id.toString()));
		
		return sqlSession.selectOne("findObjectMenusBusiness", map);
		//return  menusManager.findObject(c, id);
	}
	
	public Map getFindObjectSql() {
		return PerformSQLUtil.get("findObjectMenusBusiness", this);
	}
	
	/**
	 * 根据ID查询
	 * @param statement  
	 * @param        
	 * @return
	 */
	public boolean updateMenu(DbMenus dbMenus){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.updateMenu!对象DbMenus:"+dbMenus);
		
		boolean bool =true;
		try {
			sqlSession.update("updateMenu", dbMenus);
		} catch (Exception e) {
			bool=false;
		}
		return bool;
		//return  menusManager.updateDbMenus(dbMenus);
	}
	
	public Map getUpdateMenuSql() {
		return PerformSQLUtil.get("updateMenu", this);
	}
	
	
	/**
	 * 逻辑删除权限
	 * @param statement  
	 * @param        
	 * @return
	 */
	public boolean deleteMenus(Integer menuId){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.deleteMenus!参数menuId:"+menuId);
		
//		String sql="update db_menus set status=1 where menu_id="+(menuId==null?-1:menuId);
//		
//		int count=menusManager.updateBySQL(menusManager.getDbMenusDAO(), sql);		
		Map map = new HashMap();
		map.put("menuId", (null==menuId?-1:menuId));
		
		int count = sqlSession.update("deleteMenus", map);
		return count>0?true:false;
	}
	
	public Map getDeleteMenusSql() {
		return PerformSQLUtil.get("deleteMenus", this);
	}
	
	/**
	 * 添加权限
	 * @param statement  
	 * @param        
	 * @return
	 */
	
	public boolean insertMenus(DbMenus dbMenus){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.insertMenus!对象dbMenus:"+dbMenus);
		boolean bool =true;
		try {
			sqlSession.insert("insertMenus", dbMenus);
		} catch (Exception e) {
			bool=false;
		}
		return bool;
		//return menusManager.insertMenus(dbMenus);

	}
	
	public Map getInsertMenusSql() {
		return PerformSQLUtil.get("insertMenus", this);
	}
	
	/**
	 * 根据设备类型Id和父菜单Code，查询子菜单（JKJ）
	 * @param 2012-11-16  
	 * @param @param MenuParCode
	 * @param @param eqTypeParId
	 * @param @return       
	 * @return List
	 */
	public List findEquipForEquip(String MenuParCode,String eqTypeParId){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.findEquipForEquip!参数MenuParCode:"+MenuParCode+" eqTypeParId:"+eqTypeParId);
		
//		String sql="select t.menu_id,t.name,t.menu_code," +
//				"(select m.equip_type_id from db_equip_type m  where m.equip_type_parent_id='"+eqTypeParId+"' and m.type_name= t.name) eqtypeid " +
//				"from db_menus t where t.parent_code ='"+MenuParCode+"' and t.status !=2 order by eqTypeId";
//		RetCode rc=menusManager.findBySQL(menusManager.getDbMenusDAO(), sql);
		//log.info("根据设备类型Id和父菜单Code，查询子菜单(JKJ)SQL="+sql);
		Map map = new HashMap();
		map.put("MenuParCode", MenuParCode);
		map.put("eqTypeParId", eqTypeParId);
		RetCode rc= this.getData("findEquipForEquip", map);
		if(rc.getObj()!=null){
			return (List)rc.getObj();
		}else{
			return null;
		}
		
	}
	
	
	public Map getFindEquipForEquipSql() {
		return PerformSQLUtil.get("findEquipForEquip", this);
	}
	
	public RetCode getReminders(String offset){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.getReminders!参数offset:"+offset);
		//return menusManager.getReminders(offset);
		Map map = new HashMap();
		map.put("offset", offset);
		return this.getData("getReminders", map);
	}
	
	public Map getGetRemindersSql() {
		return PerformSQLUtil.get("getReminders", this);
	}
	
	public RetCode findOutDateMens(String offset,int currentPage,int pageSize){
		if (log.isDebugEnabled())
			log.debug("进入MenusBusiness.findOutDateMens!参数offset:"+offset+" currentPage:"+currentPage+" pageSize:"+pageSize );
		//return menusManager.findOutDateMens(offset,currentPage,pageSize);
		Map map = new HashMap();
		map.put("offset", offset);
		return this.getPageData("findOutDateMens", map, currentPage, pageSize);
	}
	public Map getFindOutDateMensSql() {
		return PerformSQLUtil.get("findOutDateMens", this);
	}
	
//	public MenusManager getMenusManager() {
//		return menusManager;
//	}
//
//	public void setMenusManager(MenusManager menusManager) {
//		this.menusManager = menusManager;
//	}
	
	
	
}
