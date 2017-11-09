package com.hanqian.business;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbEquipType;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 设备类型Action类
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
@Service
public class EquipTypeBusiness   extends BaseBusiness {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(EquipTypeBusiness.class);
//	@Resource
//    private EquipTypeMgr dbEquipTypeMgr;
// 
//	public EquipTypeMgr getDbEquipTypeMgr() {
//		return dbEquipTypeMgr;
//	}
//
//	public void setDbEquipTypeMgr(EquipTypeMgr dbEquipTypeMgr) {
//		this.dbEquipTypeMgr = dbEquipTypeMgr;
//	}

	/**
	 * 获得所有的顶级设备类型（系统类型）
	 * @param statement  
	 * @param        
	 * @return
	 */
	public RetCode findAllTopEquipType(){
		//return dbEquipTypeMgr.findAllTopEquipType();
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipTypeBusiness.findAllTopEquipType");	
		}
		return this.getData("findAllTopEquipType", null);
	}
	public Map getFindAllTopEquipTypeSql() {
		return PerformSQLUtil.get("findAllTopEquipType", this);
	}

	/**
	 * 根据父Id，获得所有的子项类型
	 * @param parentId
	 * @return
	 */
	public RetCode findAllChildByPId(Integer parentId){
		//return dbEquipTypeMgr.findAllChildByPId(parentId);
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipTypeBusiness.findAllChildByPId,参数parentId:"+parentId);	
		}
		Map map = new HashMap();
		map.put("parentId", parentId);
		return this.getData("findAllChildByPId", map);
	}
	public Map getFindAllChildByPIdSql() {
		return PerformSQLUtil.get("findAllChildByPId", this);
	}
	
	/**
	 * 获得生成后的HTML标记
	 * @return
	 */
	public String OutputParentHtml() {
		StringBuilder html = new StringBuilder();
		//获得所有的顶级菜单
		//List parents = (List)dbEquipTypeMgr.findAllTopEquipType().getObj();
		List parents = (List)this.findAllTopEquipType().getObj();
		if (parents != null) {
			for (int i = 0; i < parents.size(); i++) {
				Map map=(Map)parents.get(i);
				html.append("<li id='"+map.get("equiptypeid")+"'><span >" + map.get("equiptypename") + "</span>");
				if (map.get("equiptypeid")!=null && this.HasChilds(Integer.parseInt(map.get("equiptypeid").toString()))) {
					html.append("<ul>" + OutputChilds(Integer.parseInt(map.get("equiptypeid").toString())) + "</ul>");
				}
				html.append("</li>");
			}
		}
		return html.toString();
	}
	
	public boolean HasChilds(Integer parentId){
		RetCode rt = new RetCode(1001, "查无数据！", "查无数据！");
//		String sql="select t.equip_type_id equipTypeId,t.type_name equipTypeName,t.equip_type_parent_id equipTypeParentId from db_equip_type t where t.equip_type_parent_id ="+parentId;
//		if (logg.isDebugEnabled()) logg.debug("(EquipTypeMgr-->HasChilds)判断是是否拥有子项SQL=:" +sql);	
		//rt=this.findBySQL(dbEquipTypeDAO, sql);
		Map map = new HashMap();
		map.put("parentId", parentId);
		rt= this.getData("HasChilds", map);
		if(rt.getObj()!=null){
			return true;
		}else{
		    return false;
		}
	}
	
	
	//打印子项
	private String OutputChilds(int moduleId) {
		StringBuilder html = new StringBuilder();
		//List childs =(List)dbEquipTypeMgr.findAllChildByPId(moduleId).getObj();
		List childs =(List)this.findAllChildByPId(moduleId).getObj();
		if (childs != null) {
			for (int i = 0; i < childs.size(); i++) {
				Map map=(Map)childs.get(i);
				html.append("<li id='"+map.get("equiptypeid")+"'><span>" + map.get("equiptypename") + "</span>");
				if (map.get("equiptypeid") !=null && this.HasChilds(Integer.parseInt(map.get("equiptypeid").toString()))) {
					html.append("<ul>" + OutputChilds(Integer.parseInt(map.get("equiptypeid").toString())) + "</ul>");
				}
				html.append("</li>");
			}
		}
		return html.toString();
	}
	
	/**
	 * 生成JSON格式的文本
	 * @return
	 */
	private String getJson() {
		StringBuilder html = new StringBuilder();
		//获得所有的顶级设备类型
		//List parents = (List)dbEquipTypeMgr.findAllTopEquipType().getObj();
		List parents = (List)this.findAllTopEquipType().getObj();
		if (parents != null) {
			for (int i = 0; i < parents.size(); i++) {
				Map map=(Map)parents.get(i);
				html.append("{'id':'"+map.get("equiptypeid")+"','text':'" + map.get("equiptypename") + "'");
				if (map.get("equiptypeid") !=null && this.HasChilds(Integer.parseInt(map.get("equiptypeid").toString()))) {
					html.append(",'children':[" + getchildJson(Integer.parseInt(map.get("equiptypeid").toString())) );
					html=html.delete(html.lastIndexOf(","),html.lastIndexOf(",")+1);
					html.append("]");
				}
				html.append("},");
			}
		}
		return html.toString();
	}
	//打印子项
	private String getchildJson(int moduleId) {
		StringBuilder html = new StringBuilder();
		//List childs =(List)dbEquipTypeMgr.findAllChildByPId(moduleId).getObj();
		List childs =(List)this.findAllChildByPId(moduleId).getObj();
		if (childs != null) {
			for (int i = 0; i < childs.size(); i++) {
				Map map=(Map)childs.get(i);
				html.append("{'id':'"+map.get("equiptypeid")+"','text':'" + map.get("equiptypename") + "'");
				if (map.get("equiptypeid") !=null && this.HasChilds(Integer.parseInt(map.get("equiptypeid").toString()))) {
					html.append(",'children':[" + getchildJson(Integer.parseInt(map.get("equiptypeid").toString())));
					html=html.delete(html.lastIndexOf(","),html.lastIndexOf(",")+1);
					html.append("]");
				}
				html.append("},");
			}
		}
		return html.toString();
	}
	
	public void createJSON(){
		//获得当前类所在的路径
		String path=Thread.currentThread().getContextClassLoader()
		.getResource("").getPath().toString(); 
		//文件的保存目录
		File temp=new File(path); 
		//创建一个存放文件文件夹
		temp=new File(temp.getParentFile().getParentFile().getPath());
		if(!temp.exists()){ 
			temp.mkdirs(); //创建文件夹目录
		}
		File file=new File(temp.getPath()+"src/main/webapp/manager/common/equip/json");
		if(!file.exists()){ 
			file.mkdirs(); //创建文件夹目录
		}
		try{
		    File realFile=new File(file,"prov4.json");
			if(realFile.exists()){
				realFile.delete();
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(realFile),"UTF-8")); 
			String content=getJson();
			content=content.substring(0,content.lastIndexOf(','));
			content="["+content+"]";
			writer.write(content);
			//System.out.println(content);
			writer.flush();
			writer.close(); 
		}catch (Exception e) {
			logg.error("EquipTypeBusiness-->createJSON", e);
			e.printStackTrace();
		}
	}
	/**
	 * 根据主键查询
	 * @param equipTypeId
	 * @return
	 */
	public DbEquipType findById(Integer eqTypeId){
		//return dbEquipTypeMgr.findById(equipTypeId);
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipTypeBusiness.findById;参数equipTypeId:"+eqTypeId);	
		}
		DbEquipType dbEquipType = new DbEquipType();
		dbEquipType.setEquipTypeId(eqTypeId);
		dbEquipType = sqlSession.selectOne("findById1", eqTypeId);	
		return dbEquipType;
	}
	public Map getFindByIdSql() {
		return PerformSQLUtil.get("findById1", this);
	}
	//此方法改之前，在manager中未实现（现在来实现此方法，但是觉得可能返回的是多条数据，但是还是保持原来的返回对象）
	public DbEquipType findParentTypeId(String eqTypeId){
		//return dbEquipTypeMgr.findParentTypeId(eqTypeId);
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipTypeBusiness.findById;参数eqTypeId:"+eqTypeId);	
		}
		DbEquipType dbEquipType = new DbEquipType();
		dbEquipType = sqlSession.selectOne("findParentTypeId", eqTypeId);
		return dbEquipType;
		
	}
	public Map getFindParentTypeIdSql() {
		return PerformSQLUtil.get("findParentTypeId", this);
	}

}
