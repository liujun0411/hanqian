package com.hanqian.business;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbMaterial;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * �� ��: 
 * ǰ������: 
 * ��������:
 * ������ : 
 * ��������: 
 * ����˵��:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author ����
 * @version 1.4 2012-9-13
 * @see
 */
@Service("MaterialBusiness")
public class MaterialBusiness    extends BaseBusiness {
	private static final Logger logg = Logger.getLogger(MaterialBusiness.class);
//	@Resource
//	private MaterialMgr materialMgr;
//	public MaterialMgr getMaterialMgr() {
//		return materialMgr;
//	}
//	public void setMaterialMgr(MaterialMgr materialMgr) {
//		this.materialMgr = materialMgr;
//	}
	
	private Map getMaterialdMap(DbMaterial material,String begin, String end) {
		Map map = new HashMap();
		if (material != null) {
			if (material.getSeq()!=null) {
				map.put("seq", material.getSeq());				
			}else{
				map.put("seq", null);			
			}
			if (material.getApparatusno()!=null && material.getApparatusno()!="") {
				map.put("apparatusno", material.getApparatusno());					
			}else{
				map.put("apparatusno", null);
			}
			if (material.getName()!=null && material.getName()!="") {
				map.put("name", material.getName());					
			}else{
				map.put("name", null);
			}
		}
		
		if (begin!=null && end!=null && begin!="" && end!="") {
			map.put("between", "between");
			map.put("beginDate", begin);
			map.put("endDate", end);
		}else if(begin!=null && begin!=""){
			map.put("beginDate", begin);
			map.put("onlybeginDateendDate", null);
		}else if(end!=null && end!=""){
			map.put("endDate", end);
			map.put("endDate", null);
		}		
		return map;
	}
	
	public RetCode findMaterial(DbMaterial material, String begin,
			String end) {
		if (logg.isDebugEnabled())
			logg.debug("进入MaterialBusiness.findMaterial!对象DbMaterial:"+material+" beginDate"+begin +" endDate"+begin  );	
		//return materialMgr.findMaterial(material, beginDate, endDate);
		Map map = new HashMap();
		map = this.getMaterialdMap(material, begin, end);
		RetCode rc = this.getData("findMaterial", map);
		return rc ;
	}
	
	public Map getFindMaterialSql() {
		return PerformSQLUtil.get("findMaterial", this);
	}
	
	public RetCode findMaterial(DbMaterial material, int currentPage,
			int pageSize, String begin, String end) {
		//return materialMgr.findMaterial(material, currentPage, pageSize, beginDate, endDate);
		if (logg.isDebugEnabled())
			logg.debug("进入MaterialBusiness.findMaterial!对象DbMaterial:   "+material+" beginDate:   "+begin +" endDate:  "+end  );	
		//return materialMgr.findMaterial(material, beginDate, endDate);
		Map map = new HashMap();
		map = this.getMaterialdMap(material, begin, end);
		RetCode rc = this.getPageData("findMaterial", map, currentPage, pageSize);
		return rc ;
	}
	
	
	public DbMaterial findById(Integer id){
		//return materialMgr.findById(id);
		if (logg.isDebugEnabled())
			logg.debug("进入MaterialBusiness.findById!参数id:"+id);
		DbMaterial dbMaterial = sqlSession.selectOne("findByIdDbMaterial", id);
		return dbMaterial;
	}
	
	public Map getFindByIdSql() {
		return PerformSQLUtil.get("findByIdDbMaterial", this);
	}
	
	
	public void insertMaterial(DbMaterial material){
		//materialMgr.insertMaterial(material);
		if (logg.isDebugEnabled())
			logg.debug("进入MaterialBusiness.insertMaterial!对象material:"+material);
		sqlSession.insert("insertMaterial", material);
	}
	
	public Map getInsertMaterialSql() {
		return PerformSQLUtil.get("insertMaterial", this);
	}
	
	
	public DbMaterial updateMaterial(DbMaterial material){
		//return materialMgr.updateMaterial(material);
		if (logg.isDebugEnabled())
			logg.debug("进入MaterialBusiness.updateMaterial!对象material:"+material);
		sqlSession.update("updateMaterial", material);
		return material;
	}
	
	public Map getUpdateMaterialSql() {
		return PerformSQLUtil.get("updateMaterial", this);
	}
	
	public void deleteMaterial(DbMaterial material){
		//material.setStatus2(1);
		//materialMgr.updateMaterial(material);
		if (logg.isDebugEnabled())
			logg.debug("进入MaterialBusiness.deleteMaterial!对象material:"+material);
		material.setStatus2(1);
		sqlSession.update("updateMaterial", material);
		
	}
	
	public Map getDeleteMaterialSql() {
		return PerformSQLUtil.get("updateMaterial", this);
	}
	
	public RetCode reportMaintenance(Date lastTime) {
		//return materialMgr.reportMaintenance(lastTime);
		if (logg.isDebugEnabled())
			logg.debug("进入MaterialBusiness.reportMaintenance!参数lastTime:"+lastTime);
		Map map = new HashMap();
		if (lastTime != null) {
			map.put("lastTime", lastTime);
		}else{
			map.put("lastTime", null);
		}
		return this.getData("reportMaintenanceDbMaterial", null);
	}
	public Map getReportMaintenanceSql() {
		return PerformSQLUtil.get("reportMaintenanceDbMaterial", this);
	}
	
}
