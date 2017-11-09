package com.hanqian.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.EquipService;
import com.hanqian.util.Page;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
@Service
public class EquipListBusiness extends BaseBusiness {

	private static final Logger log = Logger.getLogger(EquipListBusiness.class);

	/**
	 * 根据楼宇id查询设备
	 * 
	 * @param 2013-4-10
	 * @param @param buildingId
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findEquipByBuildingId(String buildingId) {
		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findEquipByBuildingId,参数buildingId:"
					+ buildingId);
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("buildingId", buildingId);
		RetCode rc = this.getData("findEquipByBuildingId", map);
		return rc;

	}

	public Map<?, ?> getFindEquipByBuildingIdSql() {
		return PerformSQLUtil.get("findEquipByBuildingId", this);
	}

	/**
	 * 太平间系统列表
	 * 
	 *【单独要求：现在只有仁济医院有这个功能，其他医院没有这个功能】
	 * 
	 * @param statement  
	 * @param        
	 * @return
	 */
	public RetCode findALlEquipTaipingjianList(DbEquipList dbEquipList, EquipService equipService,int currentPage,int pageSize){
		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findALlEquipListInfo,对象dbEquipList:"
					+ dbEquipList +"   ||对象equipService:  "+ equipService);
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		RetCode rtc = new RetCode();
		RetCode rt = new RetCode();
		if (dbEquipList != null) {
			//设备类型ID
			if (!SysUtil.isNullObject(dbEquipList.getDbEquipType().getEquipTypeId())) {
				map.put("equipTypeId", dbEquipList.getDbEquipType().getEquipTypeId());
			} else {
				map.put("equipTypeId", null);

			}
			//楼宇ID
			if (!SysUtil.isNullObject(dbEquipList.getDbBuilding())) {
				map.put("buildingId", dbEquipList.getDbBuilding().getBuildingId());
			} else {
				map.put("buildingId", null);
				
			}
			//楼层
			if (!SysUtil.isNullObject(dbEquipList.getStorey())) {
				String storey = dbEquipList.getStorey().toString().substring(0,
						dbEquipList.getStorey().toString().indexOf("."));
				String lase = dbEquipList.getStorey().toString().substring(
						dbEquipList.getStorey().toString().indexOf(".") + 1);
				if (StringUtils.isNotEmpty(lase)) {
					if (Integer.parseInt(lase) != 0) {
						storey += "." + lase;
					}
				}
				map.put("storey", storey);
			} else {
				map.put("storey", null);
			}
			//安装位置
			if (!SysUtil.isNullObject(dbEquipList.getPlace())) {
				map.put("place", dbEquipList.getPlace());
			} else {
				map.put("place", null);
			}
		} else if(equipService != null) {
			//楼宇
			if(!SysUtil.isNullObject(equipService.getBuildId()) || !SysUtil.isNullObject(equipService.getStorey()) || !SysUtil.isNullObject(equipService.getDbBaseComm())){
				map.put("inner", "inner");
			} else {
				map.put("inner", null);
			}
			//服务区域：服务楼宇
			if (!SysUtil.isNullObject(equipService.getBuildId())) {
				map.put("serviceBuildingId", equipService.getBuildId());
			} else {
				map.put("serviceBuildingId", null);
				
			}
			// 服务区域：服务楼层
			if (!SysUtil.isNullObject(equipService.getStorey())) {
				map.put("serviceStorey", equipService.getStorey());
			} else {
				map.put("serviceStorey", null);
			}
			// 服务区域：服务区域
			if (!SysUtil.isNullObject(equipService.getDbBaseComm())) {
				map.put("serviceArea", equipService.getDbBaseComm().getContent1());
			} else {
				map.put("serviceArea", null);
				
			}
		}
		
		//rt = this.getPageData("findALlEquipTaipingjianList", map, currentPage, pageSize);
		rt = this.getData("findALlEquipTaipingjianList", map);
		List equipList = new ArrayList();
		if (rt!=null&& rt.getObj()!=null) {
			List list = (List)rt.getObj();
			for (int i = 0; i < list.size(); i++) {
				Map serMap = (Map)list.get(i);
				Map equipMap = new HashMap();//设备map
				List serverList = new ArrayList();//服务区域集合 装载servermap
				Map serverMap = new HashMap();//服务区域map  每一个服务区域
				
				if (equipList.isEmpty()||equipList==null||equipList.size()<1) {//当还没有设备信息的时候，即第一次循环
					for (Object object : serMap.keySet()) {
						if(object.toString().contains("server_")){
							if ( serMap.get(object)!=null) {
								serverMap.put(object, serMap.get(object));//服务区域到服务区域map
							}
						}else{
							equipMap.put(object, serMap.get(object));//加入设备信息到设备map
						}
					}
					serverList.add(serverMap);//将服务区域map装载到服务区域list
					equipMap.put("server", serverList);//将服务区域装载到设备map
					equipList.add(equipMap);//将设备map装载到设备list
				}else{
					
					int flag = 0 ;
					for (int j = 0; j < equipList.size(); j++) {
						Map inmap = (Map)equipList.get(j);
						//若有该设备了，则只需要将现有设备的服务区域list信息改变
						if (inmap.get("equipid").toString().equals(serMap.get("equipid").toString())) {
							flag--;
							//加入该设备的服务区域
							List tempServerList = inmap.get("server")==null?new ArrayList():(List)inmap.get("server");
							
							for (Object object : serMap.keySet()) {
								if(object.toString().contains("server_")){
									if (serMap.get(object)!=null) {
										serverMap.put(object, serMap.get(object));//服务区域到服务区域map
									}
								}else{
									equipMap.put(object, serMap.get(object));//加入设备信息到设备map
								}
							}
							equipList.remove(inmap);
							tempServerList.add(serverMap);
							inmap.put("server", tempServerList);
							equipList.add(inmap);
							break;
						}else{
							flag++;
							if (flag==equipList.size()) {
								for (Object object : serMap.keySet()) {
									if(object.toString().contains("server_")){
										if ( serMap.get(object)!=null) {
											serverMap.put(object, serMap.get(object));//服务区域到服务区域map
										}
									}else{
										equipMap.put(object, serMap.get(object));//加入设备信息到设备map
									}
								}
								serverList.add(serverMap);//将服务区域map装载到服务区域list
								equipMap.put("server", serverList);//将服务区域装载到设备map
								equipList.add(equipMap);//将设备map装载到设备list
								break;
							}
							
						}
					}
				}
			}
			rtc.setPage(new Page(currentPage,equipList.size(),pageSize));
			List endList = new ArrayList();
			if (equipList.size()>=currentPage) {
				if(equipList.size()%pageSize==0&&currentPage>1){
					currentPage--;
				}
				for (int i = (currentPage - 1) * pageSize; i < (equipList.size()<pageSize*currentPage?equipList.size():pageSize*currentPage); i++) {
					endList.add(equipList.get(i));
				}
			}
			rtc.setObj(endList);
		}
		
		return rtc;
	}
	
	/**
	 * 
	 * 电力安全
	 * 
	 * 【(胸科)单独要求：现在只有胸科医院有这个功能，其他医院没有这个功能】
	 * @param statement  
	 * @param        
	 * @return
	 */
	public RetCode findALlEquipListyongdianxitong(DbEquipList dbEquipList, EquipService equipService,int currentPage,int pageSize){
		RetCode rtc = new RetCode();
		RetCode rt = new RetCode();
		Map map = new HashMap();
		if (dbEquipList != null) {
			// 设备类型ID
			if (!SysUtil.isNullObject(dbEquipList.getDbEquipType().getEquipTypeId())) {
				map.put("equipTypeId", dbEquipList.getDbEquipType().getEquipTypeId());
			}else{
				map.put("equipTypeId", null);
			}
			// 楼宇ID
			if (!SysUtil.isNullObject(dbEquipList.getDbBuilding())) {
				if (!SysUtil.isNullObject(dbEquipList.getDbBuilding()
						.getBuildingId())) {
					map.put("buildingId", dbEquipList.getDbBuilding().getBuildingId());
				}else{
					map.put("buildingId", null);
				}
			}
			// 楼层
			if (!SysUtil.isNullObject(dbEquipList.getStorey())) {
				String storey = dbEquipList.getStorey().toString().substring(0,
						dbEquipList.getStorey().toString().indexOf("."));
				String lase = dbEquipList.getStorey().toString().substring(
						dbEquipList.getStorey().toString().indexOf(".") + 1);
				if (StringUtils.isNotEmpty(lase)) {
					if (Integer.parseInt(lase) != 0) {
						storey += "." + lase;
					}
				}
				map.put("storey", storey);
			} else {
				map.put("storey", null);
			}
			// 安装位置
			if (StringUtils.isNotEmpty(dbEquipList.getPlace())) {
				map.put("place", dbEquipList.getPlace());
			}else{
				map.put("place", null);
			}
			/**
			 * 设备型号PM850
			 *
			 * if (StringUtils.isNotEmpty(dbEquipList.getUnitType())) {
			 *	  // 处理查询条件大小写的问题
			 *	  sql += " and upper(l.unit_type) like '%'||upper('"
			 *		  	+ dbEquipList.getUnitType() + "')||'%'";
			 *  }
			 */  
			}else if(equipService != null) {
				//服务区域：服务楼宇
				if (!SysUtil.isNullObject(equipService.getBuildId())) {
					map.put("serviceBuildingId", equipService.getBuildId());
				} else {
					map.put("serviceBuildingId", null);
				}
				// 服务区域：服务楼层
				if (!SysUtil.isNullObject(equipService.getStorey())) {
					map.put("serviceStorey", equipService.getStorey());
				} else {
					map.put("serviceStorey", null);
				}
				// 服务区域：服务区域
				if (!SysUtil.isNullObject(equipService.getDbBaseComm())) {
					map.put("serviceArea", equipService.getDbBaseComm().getContent1());
				} else {
					map.put("serviceArea", null);
				}
			}
		
		rt = this.getData("findALlEquipListyongdianxitong", map);
		
		List equipList = new ArrayList();
		if (rt!=null&& rt.getObj()!=null) {
			List list = (List)rt.getObj();
			for (int i = 0; i < list.size(); i++) {
				Map serMap = (Map)list.get(i);
				Map equipMap = new HashMap();//设备map
				List serverList = new ArrayList();//服务区域集合 装载servermap
				Map serverMap = new HashMap();//服务区域map  每一个服务区域
				
				if (equipList.isEmpty()||equipList==null||equipList.size()<1) {//当还没有设备信息的时候，即第一次循环
					for (Object object : serMap.keySet()) {
						if(object.toString().contains("server_")){
							if ( serMap.get(object)!=null) {
								serverMap.put(object, serMap.get(object));//服务区域到服务区域map
							}
						}else{
							equipMap.put(object, serMap.get(object));//加入设备信息到设备map
						}
					}
					serverList.add(serverMap);//将服务区域map装载到服务区域list
					equipMap.put("server", serverList);//将服务区域装载到设备map
					equipList.add(equipMap);//将设备map装载到设备list
				}else{
					
					int flag = 0 ;
					for (int j = 0; j < equipList.size(); j++) {
						Map inmap = (Map)equipList.get(j);
						//若有该设备了，则只需要将现有设备的服务区域list信息改变
						if (inmap.get("equipid").toString().equals(serMap.get("equipid").toString())) {
							flag--;
							//加入该设备的服务区域
							List tempServerList = inmap.get("server")==null?new ArrayList():(List)inmap.get("server");
							
							for (Object object : serMap.keySet()) {
								if(object.toString().contains("server_")){
									if (serMap.get(object)!=null) {
										serverMap.put(object, serMap.get(object));//服务区域到服务区域map
									}
								}else{
									equipMap.put(object, serMap.get(object));//加入设备信息到设备map
								}
							}
							equipList.remove(inmap);
							tempServerList.add(serverMap);
							inmap.put("server", tempServerList);
							equipList.add(inmap);
							break;
						}else{
							flag++;
							if (flag==equipList.size()) {
								for (Object object : serMap.keySet()) {
									if(object.toString().contains("server_")){
										if ( serMap.get(object)!=null) {
											serverMap.put(object, serMap.get(object));//服务区域到服务区域map
										}
									}else{
										equipMap.put(object, serMap.get(object));//加入设备信息到设备map
									}
								}
								serverList.add(serverMap);//将服务区域map装载到服务区域list
								equipMap.put("server", serverList);//将服务区域装载到设备map
								equipList.add(equipMap);//将设备map装载到设备list
								break;
							}
							
						}
					}
				}
			}
			rtc.setPage(new Page(currentPage,equipList.size(),pageSize));
			List endList = new ArrayList();
			if (equipList.size()>=currentPage) {
				if(equipList.size()%pageSize==0&&currentPage>1){
					currentPage--;
				}
				for (int i = (currentPage - 1) * pageSize; i < (equipList.size()<pageSize*currentPage?equipList.size():pageSize*currentPage); i++) {
					endList.add(equipList.get(i));
				}
			}
			rtc.setObj(endList);
		}
		
		return rtc;
	}
	
	
	/**
	 * 获得所有的设备信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findALlEquipListInfo(DbEquipList dbEquipList,int currentPage, int pageSize) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findALlEquipListInfo,对象dbEquipList:"
					+ dbEquipList);
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		int equipStatus=0;
		if (null == dbEquipList) {
			map.put("equipStatus", equipStatus);
		} else {
			if(dbEquipList.getDbBuilding() != null){
				//楼宇ID
				if (!SysUtil.isNullObject(dbEquipList.getDbBuilding().getBuildingId())&&dbEquipList.getDbBuilding().getBuildingId()!=0) {
					map.put("buildingid", dbEquipList.getDbBuilding().getBuildingId());
				} else {
					map.put("buildingid", null);
				}
				//曾用名
				if (!SysUtil.isNullObject(dbEquipList.getDbBuilding().getHisName())) {
					map.put("hisName", dbEquipList.getDbBuilding().getHisName());
				} else {
					map.put("hisName", null);
				}
			}
			//楼层
			if (!SysUtil.isNullObject(dbEquipList.getStorey())) {
				map.put("storey", dbEquipList.getStorey());
			} else {
				map.put("storey", null);
				
			}//安装位置
			if (!SysUtil.isNullObject(dbEquipList.getPlace())) {
				map.put("place", dbEquipList.getPlace());
			} else {
				map.put("place", null);
				
			}//设备型号
			if (!SysUtil.isNullObject(dbEquipList.getUnitType())) {
				map.put("unitType", dbEquipList.getUnitType());
			} else {
				map.put("unitType", null);
				
			}//设备名称
			if (!SysUtil.isNullObject(dbEquipList.getEquipName())) {
				map.put("equipName", dbEquipList.getEquipName());
			} else {
				map.put("equipName", null);
				
			}
			if(!SysUtil.isNullObject(dbEquipList.getControlFlag())){
				map.put("controlFlag", dbEquipList.getControlFlag());
			}
			else {
				map.put("controlFlag", null);
			}
			//设备编号
			if (!SysUtil.isNullObject(dbEquipList.getEquipCode())) {
				map.put("equipCode", dbEquipList.getEquipCode());
			} else {
				map.put("equipCode", null);
			}
			//设备类型
			if (!SysUtil.isNullObject(dbEquipList.getDbEquipType())) {
				map.put("eqTypeId", dbEquipList.getDbEquipType().getEquipTypeId());
			} else {
				map.put("eqTypeId", null);
			}
			//设备状态
			if (!SysUtil.isNullObject(dbEquipList.getStatus())) {
				
			} else {
				map.put("equipStatus", null);
			}
			try {
				if (("1").equals(dbEquipList.getStatus().toString())) {
					equipStatus=dbEquipList.getStatus();
					map.put("equipStatus", equipStatus);
				}
			} catch (Exception e1) {
				log.error(e1);
			}
		}

		RetCode rc = this.getPageData("findAllEquipment", map, currentPage,pageSize);
		return rc;
	}

	public Map<?, ?> getFindALlEquipListInfoSql() {
		return PerformSQLUtil.get("findAllEquipment", this);
	}

	/**
	 * 获得所有的设备信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findALlEquipList(DbEquipList dbEquipList,EquipService equipService, int currentPage, int pageSize) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findALlEquipList,对象dbEquipList:"
					+ dbEquipList);
		RetCode rtc = new RetCode();
		try {
		Map<String, Comparable> map1 = new HashMap<String, Comparable>();

		if (dbEquipList.getDbEquipType().getEquipTypeId() == 10002
				|| dbEquipList.getDbEquipType().getEquipTypeId() == 12001) {
			// 如果查询水表或天然气，则去掉上述infor条件
			map1.put("equipTypeId", null);
		} else {
			map1.put("equipTypeId", dbEquipList.getDbEquipType()
					.getEquipTypeId());
		}

		if (!SysUtil
				.isNullObject(dbEquipList.getDbEquipType().getEquipTypeId())) {
			map1.put("equipTypeId2", dbEquipList.getDbEquipType()
					.getEquipTypeId());
		} else {
			map1.put("equipTypeId2", null);
		}

		if (!SysUtil.isNullObject(dbEquipList.getDbBuilding())) {
			if (!SysUtil.isNullObject(dbEquipList.getDbBuilding()
					.getBuildingId())) {
				map1.put("buildingId", dbEquipList.getDbBuilding()
						.getBuildingId());
			} else {
				map1.put("buildingId", null);
			}
		} else {
			map1.put("buildingId", null);
		}

		if (!SysUtil.isNullObject(dbEquipList.getStorey())) {
			String storey = dbEquipList
					.getStorey()
					.toString()
					.substring(0,
							dbEquipList.getStorey().toString().indexOf("."));
			String lase = dbEquipList
					.getStorey()
					.toString()
					.substring(
							dbEquipList.getStorey().toString().indexOf(".") + 1);
			if (StringUtils.isNotEmpty(lase)) {
				if (Integer.parseInt(lase) != 0) {
					storey += "." + lase;
				}
			}
			map1.put("storey", storey);
		} else {
			map1.put("storey", null);
		}

		if (StringUtils.isNotEmpty(dbEquipList.getPlace())) {
			map1.put("place", dbEquipList.getPlace());
		} else {
			map1.put("place", null);
		}
		if (StringUtils.isNotEmpty(dbEquipList.getUnitType())) {
			// 处理查询条件大小写的问题
			map1.put("unitType", dbEquipList.getUnitType());
		} else {
			map1.put("unitType", null);
		}


		if (SysUtil.isNullObject(equipService.getBuildId())) {
			map1.put("buildId", null);
		} else {
			map1.put("buildId", equipService.getBuildId());
		}
		// 服务区域：服务楼层
		if (SysUtil.isNullObject(equipService.getStorey())) {
			map1.put("storey1", null);
		} else {
			map1.put("storey1", equipService.getStorey());
		}

		if (SysUtil.isNullObject(equipService.getDbBaseComm())) {
			map1.put("content1", null);
		} else {
			map1.put("content1",equipService.getDbBaseComm().getContent1());//serviceAreas的值存入map1 by yangmin
		}
		RetCode rc = this.getData("findPower", map1);
		
		List equipList = new ArrayList();
		if (rc!=null&& rc.getObj()!=null) {
			List list = (List)rc.getObj();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map)list.get(i);
			Map equipMap = new HashMap();//设备map
			List serverList = new ArrayList();//服务区域集合 装载servermap
			Map serverMap = new HashMap();//服务区域map  每一个服务区域
			
			if (equipList.isEmpty()||equipList==null||equipList.size()<1) {//当还没有设备信息的时候，即第一次循环
				for (Object object : map.keySet()) {
					if(object.toString().contains("server_")){
						if ( map.get(object)!=null) {
							serverMap.put(object, map.get(object));//服务区域到服务区域map
						}
					}else{
						equipMap.put(object, map.get(object));//加入设备信息到设备map
					}
				}
				serverList.add(serverMap);//将服务区域map装载到服务区域list
				equipMap.put("server", serverList);//将服务区域装载到设备map
				equipList.add(equipMap);//将设备map装载到设备list
			}else{
				
				int flag = 0 ;
				for (int j = 0; j < equipList.size(); j++) {
					Map inmap = (Map)equipList.get(j);
					//若有该设备了，则只需要将现有设备的服务区域list信息改变
					if (inmap.get("equipid").toString().equals(map.get("equipid").toString())) {
						flag--;
						//加入该设备的服务区域
						List tempServerList = inmap.get("server")==null?new ArrayList():(List)inmap.get("server");
						
						for (Object object : map.keySet()) {
							if(object.toString().contains("server_")){
								if ( map.get(object)!=null) {
									serverMap.put(object, map.get(object));//服务区域到服务区域map
								}
							}else{
								equipMap.put(object, map.get(object));//加入设备信息到设备map
							}
						}
						equipList.remove(inmap);
						tempServerList.add(serverMap);
						inmap.put("server", tempServerList);
						equipList.add(inmap);
						break;
					}else{
						flag++;
						if (flag==equipList.size()) {
							for (Object object : map.keySet()) {
								if(object.toString().contains("server_")){
									if ( map.get(object)!=null) {
										serverMap.put(object, map.get(object));//服务区域到服务区域map
									}
								}else{
									equipMap.put(object, map.get(object));//加入设备信息到设备map
								}
							}
							serverList.add(serverMap);//将服务区域map装载到服务区域list
							equipMap.put("server", serverList);//将服务区域装载到设备map
							equipList.add(equipMap);//将设备map装载到设备list
							break;
						}
						
					}
				}
			   }
		      }
		     }
		rtc.setPage(new Page(currentPage,equipList.size(),pageSize));
		List endList = new ArrayList();
		if (equipList.size()>=currentPage) {
			if(equipList.size()%pageSize==0&&currentPage>1){
				currentPage--;
			}
			for (int i = (currentPage - 1) * pageSize; i < (equipList.size()<pageSize*currentPage?equipList.size():pageSize*currentPage); i++) {
				endList.add(equipList.get(i));
			}
		}
		rtc.setObj(endList);
	} catch (Exception e) {
		rtc.setObj(null);
	}
	 return rtc;
	}

	public Map<?, ?> getFindALlEquipListSql() {
		return PerformSQLUtil.get("findPower", this);
	}

	/**
	 * 检测设备编号是否存在
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode checkEquipCode(String equipCode, String equipId) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.checkEquipCode,参数equipCode:"
					+ equipCode + " equipId" + equipId);
		Map<String, Comparable> map = new HashMap<String, Comparable>();

		if (null == equipCode) {
			map.put("equipCode", null);
		} else {
			map.put("equipCode", equipCode);
		}
		if (null == equipId || !StringUtils.isNotEmpty(equipId)) {
			map.put("equipId", null);
		} else {
			map.put("equipId", equipId);
		}

		RetCode rc = this.getData("checkEquipCode", map);
		return rc;

	}

	public Map<?, ?> getCheckEquipCodeSql() {
		return PerformSQLUtil.get("checkEquipCode", this);
	}

	/**
	 * 根据设备Id删除数据（理论删除）
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public int deleteEquipList(String equipListId) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.deleteEquipList,参数equipListId:"
					+ equipListId);

		return sqlSession.update("deleteEquipList", equipListId);
	}

	public Map<?, ?> getDeleteEquipListSql() {
		return PerformSQLUtil.get("deleteEquipList", this);
	}

	/**
	 * 添加设备信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public DbEquipList insertEquipList(DbEquipList dbEquipList) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.insertEquipList,对象dbEquipList:"
					+ dbEquipList);
		sqlSession.insert("insertEquipList", dbEquipList);

		return dbEquipList;
	}

	public Map<?, ?> getInsertEquipListSql() {
		return PerformSQLUtil.get("insertEquipList", this);
	}

	/**
	 * 更新设备信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public DbEquipList updateEquipList(DbEquipList dbEquipList) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.updateEquipList,对象dbEquipList:"
					+ dbEquipList);
		sqlSession.update("updateEquipList", dbEquipList);

		return dbEquipList;

	}

	public Map<?, ?> getUpdateEquipListSql() {
		return PerformSQLUtil.get("updateEquipList", this);
	}

	/**
	 * 根据设备Id查询设备信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public DbEquipList findDbEquipListById(int equipId) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findDbEquipListById,参数equipId:"
					+ equipId);

		DbEquipList dbEquipList = sqlSession.selectOne("findDbEquipListById",
				equipId);

		return dbEquipList;
	}

	public Map<?, ?> getFindDbEquipListByIdSql() {
		return PerformSQLUtil.get("findDbEquipListById", this);
	}

	/**
	 * 根据条件查询设备信息（不分页）
	 * 
	 * @param dbEquipList
	 * @return
	 */
	public RetCode findEquipmentByParam(DbEquipList dbEquipList) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findEquipmentByParam,对象dbEquipList:"
					+ dbEquipList);
		Map<String, Comparable> map = new HashMap<String, Comparable>();

		if (!SysUtil.isNullObject(dbEquipList.getDbBuilding())) {
			if (!SysUtil.isNullObject(dbEquipList.getDbBuilding()
					.getBuildingId())) {
				map.put("BuildingId", dbEquipList.getDbBuilding()
						.getBuildingId());
			} else {
				map.put("BuildingId", null);
			}
			if (null != dbEquipList.getDbBuilding().getHisName()) {
				map.put("HisName", dbEquipList.getDbBuilding().getHisName()
						.trim());
			} else {
				map.put("HisName", null);
			}
		} else {
			map.put("BuildingId", null);
			map.put("HisName", null);
		}

		if (null != dbEquipList.getStorey()) {
			map.put("Storey", dbEquipList.getStorey().toString().trim());
		} else {
			map.put("Storey", null);
		}

		if (null != dbEquipList.getPlace()) {
			map.put("Place", dbEquipList.getPlace().trim());
		} else {
			map.put("Place", null);
		}

		if (null != dbEquipList.getUnitType()) {
			map.put("UnitType", dbEquipList.getUnitType().trim());
		} else {
			map.put("UnitType", null);
		}
		if (null != dbEquipList.getDbEquipType()) {
			if (null != dbEquipList.getDbEquipType().getEquipTypeId()) {
				map.put("EquipTypeId", dbEquipList.getDbEquipType()
						.getEquipTypeId());
			} else {
				map.put("EquipTypeId", null);
			}
		} else {
			map.put("EquipTypeId", null);
		}

		return this.getData("findEquipmentByParam", map);
	}

	public Map<?, ?> getFindEquipmentByParamSql() {
		return PerformSQLUtil.get("findEquipmentByParam", this);
	}

	public RetCode findByEquipId(int equipId) {
		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findByEquipId,参数equipId:" + equipId);

		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("equipId", equipId);
		return this.getData("findByEquipId", map);
	}

	public Map<?, ?> getFindByEquipIdSql() {
		return PerformSQLUtil.get("findByEquipId", this);
	}

	/**
	 * 实时监控（根据楼宇和设备类型查询监控设备信息）
	 * 
	 * @param dbEquipList
	 * @return
	 */
	public RetCode findByEquipControl(DbEquipList dbEquipList) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findByEquipControl,参数DbEquipList:"
					+ dbEquipList);
		List<?> lst = sqlSession.selectList("findByEquipControl", dbEquipList);
		RetCode rc = new RetCode();
		rc.setObj(lst);
		return rc;

	}

	public Map<?, ?> getFindByEquipControlSql() {
		return PerformSQLUtil.get("findByEquipControl", this);
	}

	/**
	 * 查询需要巡检提醒的设备
	 * 
	 * @param statement
	 * @param logisticsNews
	 * @return
	 */
	public RetCode findEquipMaintenance(String hospId, int pageSize,
			int currentPage) {
		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findEquipMaintenance,参数hospId:"
					+ hospId);

		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("hospId", hospId);
		return this.getPageData("findEquipMaintenance", map, pageSize, currentPage);

	}

	/**
	 * 查询需要巡检提醒的设备
	 * 
	 * @param statement
	 * @param logisticsNews
	 * @return
	 */
	public RetCode findEquipMaintenance(String hospId) {
		return this.findEquipMaintenance(hospId, 1, 10);
	}

	public Map<?, ?> getFindEquipMaintenanceSql() {
		return PerformSQLUtil.get("findEquipMaintenance", this);
	}

	/**
	 * 根据组ID查询的设备信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findEquipList(String groupId, int currentPage, int pageSize) {
		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findEquipList,参数groupId:" + groupId);

		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("groupId", groupId);
		return this.getPageData("findEquipListEquipListBusiness", map,
				currentPage, pageSize);
	}

	public Map<?, ?> getFindEquipListSql() {
		return PerformSQLUtil.get("findEquipListEquipListBusiness", this);
	}

	/**
	 * 根据组ID查询的设备ID集合
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findEquipIdList(String groupId) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findEquipIdList,参数groupId:"
					+ groupId);
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("groupId", groupId);
		return this.getPageData("findEquipIdList", map);
	}

	public Map<?, ?> getFindEquipIdListSql() {
		return PerformSQLUtil.get("findEquipIdList", this);
	}

	public RetCode findIdListByGroupId(String groupId) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findIdListByGroupId,参数groupId:"
					+ groupId);
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("groupId", groupId);
		return this.getData("findIdListByGroupId", map);
	}

	public Map<?, ?> getFindIdListByGroupIdSql() {
		return PerformSQLUtil.get("findIdListByGroupId", this);
	}

	/**
	 * 设备数据上报
	 * 
	 * @param lastTime
	 * @return
	 */
	public RetCode reportEquipment(Date lastTime) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.reportEquipment,参数lastTime:"
					+ lastTime);
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("lastTime", lastTime);
		return this.getPageData("reportEquipment", map);
	}

	public Map<?, ?> getReportEquipmentSql() {
		return PerformSQLUtil.get("reportEquipment", this);
	}

	/**
	 * 根据组ID查询的设备信息(不分页)
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findEquipList(String groupId) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findEquipList,参数groupId:" + groupId);
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("groupId", groupId);
		return this.getPageData("findEquipList1", map);
	}

	public Map<?, ?> getFindEquipList1Sql() {
		return PerformSQLUtil.get("findEquipList1", this);
	}

	/**
	 * 根据设备ID获取能源计量监控页面数据
	 * 
	 * @param 2012-11-16
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findPowerContorl(String equipId) {

		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findPowerContorl,参数equipId:"
					+ equipId);
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("equipId", equipId);
		return this.getData("findPowerContorl", map);
	}

	public Map<?, ?> getFindPowerContorlSql() {
		return PerformSQLUtil.get("findPowerContorl", this);
	}
	
	public RetCode findByEquipTypeId(int equipTypeId) {
		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findByEquipTypeId,参数equipTypeId:"
					+ equipTypeId);

		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("equipTypeId", equipTypeId);
		return this.getData("findByEquipTypeId", map);
	}

	/**
	 * 曙光【独立要求：增加服务区域名称】
	 * 显示新加服务区域位置：空调机flash图上面加。
	 * 空调系统增加标题(服务区域显示)
	 * @param equipId
	 * @return
	 */
	public RetCode findDbEquipListByIdFuWu(int equipId) {
		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.findPowerContorl,参数equipId:"
					+ equipId);
		
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("equipId", equipId);
		
		return this.getData("findDbEquipListByIdFuWu", map);
	}

	public RetCode threePoint(Integer currentPage, Integer pageSize) {
		if (log.isDebugEnabled())
			log.debug("进入EquipListBusiness.threePoint--第三方点位查询");
		Map<String, Object> map = new HashMap<String, Object>();
		
		/*RetCode rc = this.getData("threePointList", null);*/
		RetCode rc = this.getPageData("threePointList", null,
				currentPage, pageSize);
		return rc;
	}
	public Map<?, ?> threePointSql() {
		return PerformSQLUtil.get("threePointList", this);
	}
	
	/**
	 * 获取所有电表
	 * @return
	 */
	public List<Map<String, Object>> getAllElec(String equip_id) {
		return sqlSession.selectList("getAllElec",equip_id);
	}
	/**
	 * 通过typeid获取设备
	 * @return
	 */
	public List<Map<String, Object>> getEpuipByTypeId(String equip_type_id) {
		return sqlSession.selectList("getEpuipByTypeId",equip_type_id);
	}
	/**
	 * 通过equipid获取设备
	 * @return
	 */
	public List<Map<String, Object>> getEpuipByEquipId(String equip_id) {
		return sqlSession.selectList("getEpuipByEquipId",equip_id);
	}
	
	public String getTypeIdByEpuipId(String equip_id) {
		return sqlSession.selectOne("getTypeIdByEpuipId",equip_id);
	}
	
}
