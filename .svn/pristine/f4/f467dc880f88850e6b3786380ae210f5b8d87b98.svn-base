package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.EquipService;
import com.hanqian.util.PerformSQLUtil;

/**
 * 描 述: 
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
public class EquipServiceBusiness   extends BaseBusiness {
	private static final Logger log = Logger.getLogger(EquipServiceBusiness.class);
	//private static final Log log = LogFactory.getLog(EquipServiceBusiness.class);
//	@Resource
//	private EquipServiveMgr equipServiceMgr;

	/**
	 * @return the equipServiceMgr
	 */
//	public EquipServiveMgr getEquipServiceMgr() {
//		return equipServiceMgr;
//	}
//
//	/**
//	 * @param equipServiceMgr the equipServiceMgr to set
//	 */
//	public void setEquipServiceMgr(EquipServiveMgr equipServiceMgr) {
//		this.equipServiceMgr = equipServiceMgr;
//	}
	
	/**
	 * 根据设备ID查询设备服务区域
	 * @param statement  
	 * @param        
	 * @return
	 */
	public List findByEquipId(EquipService equipService){
		//return equipServiceMgr.findByEquipId(equipService);
		if (log.isDebugEnabled()) {
			log.debug("进入EquipServiceBusiness.findByEquipId,对象equipService:"
					+ equipService);	
		}
		List lst = sqlSession.selectList("findByEquipIdSer", equipService);
		return lst;
	}
	
	public Map getFindByEquipIdSql() {
		return PerformSQLUtil.get("findByEquipIdSer", this);
	}
	/**
	 * 根据设备ID查询设备服务区域
	 * @param statement  
	 * @param        
	 * @return
	 */
	public List<EquipService> findByEquipId(String  equipId){
		//return equipServiceMgr.findByEquipId(equipId);
		if (log.isDebugEnabled()) {
			log.debug("进入EquipServiceBusiness.findByEquipId,参数equipId:"
					+ equipId);	
		}
		List<EquipService> lst = sqlSession.selectList("findByEquipId1", equipId);
		return lst;
	}
	
	public Map getFindByEquipId1Sql() {
		return PerformSQLUtil.get("findByEquipId1", this);
	}
	
	/**
	 * 添加设备服务区域对象
	 * @param statement  
	 * @param        
	 * @return
	 */
	public void insertEquipService(EquipService equipService){
		//equipServiceMgr.insertEquipService(equipService);
		if (log.isDebugEnabled()) {
			log.debug("进入EquipServiceBusiness.insertEquipService,对象equipService:"
					+ equipService);	
		}	
		Map map = new HashMap();
		if(equipService!=null){
			if (equipService.getBuildId()!=null) {
				map.put("buildId", equipService.getBuildId());
			} else {
				map.put("buildId", null);
			}
			if (StringUtils.isNotEmpty(equipService.getComments())) {
				map.put("comments", equipService.getComments());
			} else {
				map.put("comments", null);
			}
			if (equipService.getDbBaseComm()!=null&&equipService.getDbBaseComm().getSeq()!=null) {
				map.put("areas", equipService.getDbBaseComm().getSeq());
			} else {
				map.put("areas", null);
			}
			if (equipService.getStorey()!=null) {
				map.put("storey", equipService.getStorey());
			} else {
				map.put("storey", null);
			}
			if (equipService.getSquare()!=null) {
				map.put("square", equipService.getSquare());
			} else {
				map.put("square", null);
			}
			if (equipService.getDbEnergyType()!=null&&equipService.getDbEnergyType().getSeq()!=null) {
				map.put("energyId",equipService.getDbEnergyType().getSeq());
			} else {
				map.put("energyId", null);
			}
			if (equipService.getDbEquipList()!=null&&equipService.getDbEquipList().getEquipId()!=null) {
				map.put("equipId",equipService.getDbEquipList().getEquipId());
			} else {
				map.put("equipId", null);
			}
		}else{
			map.put("buildId", null);
			map.put("comments", null);
			map.put("areas", null);
			map.put("storey", null);
			map.put("square", null);
			map.put("energyId", null);
			map.put("equipId", null);
		}
		
		
		
		try {
			sqlSession.insert("insertEquipService",map);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public Map getInsertEquipServiceSql() {
		return PerformSQLUtil.get("insertEquipService", this);
	}
	
	/**
	 * 修改设备服务区域
	 * @param statement  
	 * @param        
	 * @return
	 */
	public void updateEquipService(EquipService equipService){
		//equipServiceMgr.updateEquipService(equipService);
		if (log.isDebugEnabled()) {
			log.debug("进入EquipServiceBusiness.updateEquipService,对象equipService:"
					+ equipService);	
		}		
		try {
			sqlSession.update("updateEquipService", equipService);
		} catch (Exception e) {
			
		}	
	}
	
	public Map getUpdateEquipServiceSql() {
		return PerformSQLUtil.get("updateEquipService", this);
	}
	/**
	 * 删除设备服务区域通过设备
	 * @param statement  
	 * @param        
	 * @return
	 */
	public void deleteEquipService(String equipId){
		//equipServiceMgr.deleteEquipService(equipId);
		if (log.isDebugEnabled()) {
			log.debug("进入EquipServiceBusiness.deleteEquipService,参数equipId:"
					+ equipId);	
		}		
		try {
			sqlSession.delete("deleteEquipService", equipId);
		} catch (Exception e) {
			
		}	
	}
	public Map getDeleteEquipServiceSql() {
		return PerformSQLUtil.get("deleteEquipService", this);
	}
}
