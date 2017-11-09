package com.hanqian.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.EquipServiceEquip;
import com.hanqian.util.PerformSQLUtil;

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
 * @version 1.4 2012-12-18
 * @see
 */
@Service
public class EquipSerEquipBusiness   extends BaseBusiness{
	private static final Logger log = Logger.getLogger(EquipSerEquipBusiness.class);
//	private static final Log log = LogFactory.getLog(EquipSerEquipBusiness.class);
//	@Resource
//	EquipSerEquipMgr equipSerEquipMgr;
	
	/**
	 * 根据设备ID查询服务设备
	 * @param equipId
	 * @return
	 */
	public List<EquipServiceEquip> findEquipSerEquipByEquipId(Integer equipId){
		//return (List<EquipServiceEquip>)equipSerEquipMgr.getEquipServiceEquipDAO().findByProperty("dbEquipListByEquipId.equipId", equipId);
		if (log.isDebugEnabled()) {
			log.debug("进入EquipSerEquipBusiness.findEquipSerEquipByEquipId,参数equipId:"
					+ equipId);	
		}
		List<EquipServiceEquip> lst = sqlSession.selectList("findEquipSerEquipByEquipId", equipId);
		return lst;
	}
	
	public Map getFindEquipSerEquipByEquipIdSql() {
		return PerformSQLUtil.get("findEquipSerEquipByEquipId", this);
	}
	
	/**
	 * 添加设备服务区域对象
	 * @param statement  
	 * @param        
	 * @return
	 */
	public void insertEquipServiceEquip(EquipServiceEquip equipService){
		//equipSerEquipMgr.insertEquipServiceEquip(equipService);
		if (log.isDebugEnabled()) {
			log.debug("进入EquipSerEquipBusiness.insertEquipServiceEquip,对象EquipServiceEquip:"
					+ equipService);	
		}
		try {
			sqlSession.insert("insertEquipServiceEquip", equipService);
		} catch (Exception e) {
			
		}		
	}
	
	public Map getInsertEquipServiceEquipSql() {
		return PerformSQLUtil.get("insertEquipServiceEquip", this);
	}
	
	
	public void deleteEquipSerEquip(String equipId){
		//String sql = "delete EQUIP_SERVICE_EQUIP ese where ese.equip_id = "+equipId;
		//equipSerEquipMgr.deleteBySQL(equipSerEquipMgr.getEquipServiceEquipDAO(), sql);
		if (log.isDebugEnabled()) {
			log.debug("进入EquipSerEquipBusiness.deleteEquipSerEquip,参数equipId:"
					+ equipId);	
		}
		try {
			sqlSession.delete("deleteEquipSerEquip", equipId);
		} catch (Exception e) {
			
		}		
	}
	
	public Map getDeleteEquipSerEquipSql() {
		return PerformSQLUtil.get("deleteEquipSerEquip", this);
	}
	
	/**
	 * @return the equipSerEquipMgr
	 */
//	public EquipSerEquipMgr getEquipSerEquipMgr() {
//		return equipSerEquipMgr;
//	}
//
//	/**
//	 * @param equipSerEquipMgr the equipSerEquipMgr to set
//	 */
//	public void setEquipSerEquipMgr(EquipSerEquipMgr equipSerEquipMgr) {
//		this.equipSerEquipMgr = equipSerEquipMgr;
//	}
}
