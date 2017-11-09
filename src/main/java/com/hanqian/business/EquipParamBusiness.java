package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
/**
 * 描 述: 设备参数
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
public class EquipParamBusiness   extends BaseBusiness{
	private static final Logger log = Logger.getLogger(EquipParamBusiness.class);
//	@Resource
//	private EquipParamMgr equipParamMgr;
//	
//	public EquipParamMgr getEquipParamMgr() {
//		return equipParamMgr;
//	}
//	public void setEquipParamMgr(EquipParamMgr equipParamMgr) {
//		this.equipParamMgr = equipParamMgr;
//	}
	/**
	 * 根据设备参数获得对象
	 * @param dicParam
	 * @return
	 */
	public RetCode findDicParamByTypeId(String equipTypeId){
		//return equipParamMgr.findDicParamByTypeId(equipTypeId);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findAllMaintenance,参数equipTypeId:"
					+ equipTypeId);	
		Map map = new HashMap();
		map.put("equipTypeId", equipTypeId);
		return this.getPageData("findDicParamByTypeId", map);
	}
	
	public Map getFindDicParamByTypeIdSql() {
		return PerformSQLUtil.get("findDicParamByTypeId", this);
	}
	
	/**
	 * 判断代码表和参数表的记录一样，如果不一样，就把不一样的数据显示出来
	 * @param dicParam
	 * @return
	 */
	public RetCode findDicParamByTypeIdNewNum(String equipTypeId,String equipType){
		//return equipParamMgr.findDicParamByTypeIdNewNum(equipTypeId,equipType);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findDicParamByTypeIdNewNum,参数equipTypeId:"
					+ equipTypeId+"  equipType:"
					+ equipType);	
		Map map = new HashMap();
		map.put("equipTypeId", equipTypeId);
		map.put("equipType", equipType);
		return this.getPageData("findDicParamByTypeIdNewNum", map);
	}
	
	public Map getFindDicParamByTypeIdNewNumSql() {
		return PerformSQLUtil.get("findDicParamByTypeIdNewNum", this);
	}
	/**
	 * 获取下拉框里面的值。。根据设备参数获得对象
	 * @param dicParam
	 * @return
	 */
	public RetCode findDicParamByTypeIdXLK(String equipTypeId){
		//return equipParamMgr.findDicParamByTypeIdXLK(equipTypeId);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findDicParamByTypeIdXLK,参数equipTypeId:"
					+ equipTypeId);	
		Map map = new HashMap();
		map.put("equipTypeId", equipTypeId);		
		return this.getPageData("findDicParamByTypeIdXLK", map);
	}
	
	public Map getFindDicParamByTypeIdXLKSql() {
		return PerformSQLUtil.get("findDicParamByTypeIdXLK", this);
	}
	
	/**
	 * 根据设备Id,查询设备参数值
	 * @param equipId
	 * @return
	 */
	public RetCode findDicParamByEquipId(String equipId){
		//return equipParamMgr.findDicParamByEquipId(equipId);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findDicParamByEquipId,参数equipId:"
					+ equipId);	
		Map map = new HashMap();
		map.put("equipId", equipId);		
		return this.getPageData("findDicParamByEquipId", map);
	}
	
	public Map getFindDicParamByEquipIdSql() {
		return PerformSQLUtil.get("findDicParamByEquipId", this);
	}
	
	/**
	 * new根据设备Id,查询设备参数值
	 * @param equipId
	 * @return
	 */
	public RetCode findDicParamByEquipIdNew(String equipId){
		//return equipParamMgr.findDicParamByEquipIdNew(equipId);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findDicParamByEquipIdNew,参数equipId:"
					+ equipId);	
		Map map = new HashMap();
		map.put("equipId", equipId);		
		return this.getPageData("findDicParamByEquipIdNew", map);
	}
	
	public Map getFindDicParamByEquipIdNewSql() {
		return PerformSQLUtil.get("findDicParamByEquipIdNew", this);
	}
	
	/**
	 * 下拉框的设置根据设备Id,查询设备参数值
	 * @param equipId
	 * @return
	 */
	public RetCode findDicParamByEquipIdXLK(String equipId){
		//return equipParamMgr.findDicParamByEquipIdXLK(equipId);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findDicParamByEquipIdXLK,参数equipId:"
					+ equipId);	
		Map map = new HashMap();
		map.put("equipId", equipId);		
		return this.getPageData("findDicParamByEquipIdXLK", map);
	}
	public Map getFindDicParamByEquipIdXLKSql() {
		return PerformSQLUtil.get("findDicParamByEquipIdXLK", this);
	}
	/**
	 * New 文本优先值
	 * @param equipId
	 * @return
	 */
	public RetCode findEqParamByEquipId(String equipId){
		//return equipParamMgr.findEqParamByEquipId(equipId);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findEqParamByEquipId,参数equipId:"
					+ equipId);	
		Map map = new HashMap();
		map.put("equipId", equipId);		
		return this.getPageData("findEqParamByEquipId", map);
	}
	
	public Map getFindEqParamByEquipIdSql() {
		return PerformSQLUtil.get("findEqParamByEquipId", this);
	}
	
	
}
