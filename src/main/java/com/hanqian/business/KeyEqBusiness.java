package com.hanqian.business;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.form.HashList;
import com.hanqian.form.VOKeyEqGroup;
import com.hanqian.form.VOKeyEqS;
import com.hanqian.form.VOKeyPoint;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.DbKeyEq;
import com.hanqian.pojo.DbPoint;
import com.hanqian.util.Page;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;

/**
 * 
 * 描 述: 关键设备业备层 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 李江
 * @version 1.4 2012-9-27
 * @see
 */
@Service
public class KeyEqBusiness  extends BaseBusiness{
	/**
	 * log4g日志
	 */
	private static final Logger logg = Logger.getLogger(KeyEqBusiness.class);

	private DbEquipList equipList; // 设备列表实体类

	private DbPoint dbPoint; // 点位实体类
	
	private List pointList;
	
	private String [] pointsId=new String[]{}; 

	public DbPoint getDbPoint() {
		return dbPoint;
	}

	public void setDbPoint(DbPoint dbPoint) {
		this.dbPoint = dbPoint;
	}

	public DbEquipList getEquipList() {
		return equipList;
	}

	public void setEquipList(DbEquipList equipList) {
		this.equipList = equipList;
	}

	/**
	 * 点位当前读数
	 * 
	 * @param points
	 *            点位集 point[,point[...]]
	 * @return
	 */
	public List<VOKeyPoint> findCurKeyPointValue(String points, String img ,String pointsTemp) {
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findCurKeyPointValue,参数points:"
					+ points+ " 参数img:"
					+ img+" 参数pointsTemp:"
					+ pointsTemp);	
		List<VOKeyPoint> rlist = null;
		DecimalFormat fm = null; // 数据格式化
		pointsId=pointsTemp.split(",");
		pointList=new ArrayList();
		for(int i=0;i<pointsId.length;i++){
			if(pointsId[i].indexOf("U")>0){
				if(pointList.indexOf(pointsId[i].substring(pointsId[i].indexOf("U")+1))<0){
					pointList.add(pointsId[i].substring(pointsId[i].indexOf("U")+1));
				}
			}
		}
		if (!SysUtil.isNull(points)) {
			points = "'" + points.replace(",", "','") + "'";
			points = points.replace("','0", ",0");   //对逗号的替换
			//List list = keyEqMgr.findPointsValue(points);
			List list = sqlSession.selectList("findPointsValue", points);
			if (list != null && !list.isEmpty()) {
				Map m = null;
				String keypoint = null;
				String value = null;
				String flagValue="";
				String typeid = null;
				String fmstr = null;
				String des = null;
				String name = null;
				String controlPoint = "";
				VOKeyPoint obj = null;
				VOKeyPoint tempObj = null;  //水位用
				boolean flag=false;
				boolean virtflag=false;
				boolean isChange=false;
				rlist = new ArrayList<VOKeyPoint>();

				for (int i = 0; i < list.size(); i++) {
					System.out.println(list);
					m = (Map) list.get(i);
					keypoint = m.get("project_point") + "";
					value = m.get("value") + "";
					flagValue = m.get("value") + "";
					name = m.get("point_name") + "";
					typeid = m.get("typeid") + "";
					des = m.get("des") + "";
					
					if(m.get("controlpoint")!=null){
						controlPoint=m.get("controlpoint").toString();
					}else{
						controlPoint=null;
					}
					// fmstr =
					// SysUtil.getFmStr(typeid.substring(0,(typeid.length()%3)));
					// if (fmstr != null) {
					// fm = new DecimalFormat(fmstr);
					// value = fm.format(SysUtil.toDouble(value));
					// }
 					if (StringUtil.isEmpty(value)
							|| StringUtil.isEmpty(StringUtil.trimNull(value))
							|| StringUtil.trimNull(value) == "") {
						value = "0";
						flagValue="0";
					}
					value = getShowDes(name, des, value);
					obj = new VOKeyPoint();
					obj.setKeyPoint(keypoint);
					obj.setValue(value);
					obj.setName(name);
					obj.setDes(des);
					obj.setEqTypeId(typeid);
		
					//如果存在虚拟点
					if(SysUtil.getCludStyle().containsKey(typeid) && !containsVir(rlist,keypoint)){//
						if(IsContaisCondition(typeid,keypoint)){
							
							//List virualPointList = keyEqMgr.findVirtualPoint(keypoint.substring(0,keypoint.indexOf(".")+3));
							List virualPointList = sqlSession.selectList("findVirtualPoint", keypoint.substring(0,keypoint.indexOf(".")+3));
							System.out.println("virualPointList.length==="+virualPointList.size());
							List xunHuanList = virualPointList;
							if(virualPointList!= null&&!virualPointList.isEmpty()&&virualPointList.size()>0){
								isChange=true;
								double tempValue=0.0;
								for(int t=0;t<virualPointList.size();t++){
									Map temp=(Map)virualPointList.get(t);
									//0: 不做任何计算
									//1: 将所有虚拟点进行合并sum()
									//2: 电表功率计算方法 pt=sum(p)/sum(s)
									//3. 相减
									if(temp.get("sumflag")!=null && temp.get("sumflag")!=""){
										if(temp.get("sumflag").toString().equals("0")){
											if(temp.get("value")!=null && temp.get("value")!=""){
												//如果是异常状态
												if(Math.abs(Double.parseDouble(temp.get("value").toString()))==1){
													if(!containVirtualPoint(rlist,temp.get("project_point").toString())){
														obj=new VOKeyPoint();
														obj.setDes(des);
														obj.setEqTypeId(typeid);
														obj.setKeyPoint(temp.get("project_point")+"");
														value = getShowDes(name, des, temp.get("value").toString());
														obj.setValue(value);
														obj.setName(name);
														rlist.add(obj);
														virtflag=true;
													}
												}else{
													if(!containVirtualPoint(rlist,temp.get("project_point").toString()) && t<virualPointList.size()-1){
														obj=new VOKeyPoint();
														obj.setKeyPoint(temp.get("project_point").toString());
														obj.setValue("FLAG");
														obj.setEqTypeId(typeid);
														obj.setDes(des);
														obj.setName(name);
														rlist.add(obj);
													}
												}
												if(t==virualPointList.size()-1 && !virtflag){
													obj=new VOKeyPoint();
													obj.setEqTypeId(typeid);
													obj.setValue(value);
													obj.setName(name);
													obj.setKeyPoint(temp.get("project_point").toString());
													obj.setValue(value);
													rlist.add(obj);
												}
											}
										}else if(temp.get("sumflag").toString().equals("1")){
											//锅炉累计流量
											String tempPoint=temp.get("project_point").toString();
											if(SysUtil.getConditByEqTypeId().containsKey(typeid) && tempPoint.indexOf(",")>0 ){
												double pointTemp=0;
												List tempList=new ArrayList();
												//获得设备Id，点位编号
												if(temp.get("project_point").toString().indexOf(".")>0 && temp.get("project_point").toString().indexOf(",")+1>0 && !containsVir(rlist,temp.get("project_point").toString())){
													String equipid=temp.get("project_point").toString().substring(0,temp.get("project_point").toString().indexOf("."));
													String pointNo=temp.get("project_point").toString().substring(temp.get("project_point").toString().indexOf(",")+1);
													for(int e=0;e<xunHuanList.size();e++){
														Map newTemp=(Map)xunHuanList.get(e);
														String conpoint=newTemp.get("project_point").toString();
														if(conpoint.substring(0,conpoint.indexOf(".")).equals(equipid) && conpoint.substring(conpoint.indexOf(",")+1).equals(pointNo)){
															pointTemp+=Double.parseDouble(newTemp.get("value").toString());
															tempList.add(conpoint);
														}
													}
												}
												//System.out.println("pointTemp============================="+pointTemp);
												if(tempList.size()>0){
													for(int c=0;c<tempList.size();c++){
														obj=new VOKeyPoint();
														obj.setKeyPoint(tempList.get(c).toString());
														obj.setEqTypeId(typeid);
														obj.setDes(des);
														obj.setName(name);
														if(c<tempList.size()-1){
															obj.setValue("FLAG");
														}else if(c==tempList.size()-1){
															obj.setValue(String.valueOf(pointTemp));
														}
														rlist.add(obj);
													}
												}
											}else if(SysUtil.getConditByEqTypeId().containsKey(typeid) && temp.get("project_point").toString().indexOf(",")<0){ //给水箱点合成
												double pointTemp=0;
												if(!containsVir(rlist,temp.get("project_point").toString())){
													for(int y=0;y<virualPointList.size();y++){
														Map ytemp=(Map)virualPointList.get(y);
														obj=new VOKeyPoint();
														obj.setKeyPoint(ytemp.get("project_point").toString());
														obj.setEqTypeId(typeid);
														obj.setDes(des);
														obj.setName(name);
														if(y<virualPointList.size()-1){
															pointTemp+=Double.parseDouble(ytemp.get("value").toString());
															obj.setValue("FLAG");
														}else if(y==virualPointList.size()-1){
															pointTemp+=Double.parseDouble(ytemp.get("value").toString());
															obj.setValue(String.valueOf(pointTemp));
														}
														rlist.add(obj);
													}
												}
											}
										}else if(temp.get("sumflag").toString().equals("2")){
											
										}else if(temp.get("sumflag").toString().equals("3")){
											if(t==0){
												tempValue=Double.parseDouble(temp.get("value").toString());
												obj=new VOKeyPoint();
												obj.setKeyPoint(temp.get("project_point").toString());
												obj.setValue("FLAG");
												obj.setEqTypeId(typeid);
												obj.setDes(des);
												obj.setName(name);
												rlist.add(obj);
											}else if(t>0 && t<virualPointList.size()-1){
												tempValue-=Double.parseDouble(temp.get("value").toString());
												obj=new VOKeyPoint();
												obj.setKeyPoint(temp.get("project_point").toString());
												obj.setValue("FLAG");
												obj.setEqTypeId(typeid);
												obj.setDes(des);
												obj.setName(name);
												rlist.add(obj);
											}else if(t==virualPointList.size()-1){
												tempValue-=Double.parseDouble(temp.get("value").toString());
												obj=new VOKeyPoint();
												obj.setKeyPoint(temp.get("project_point").toString());
												obj.setValue(String.valueOf(tempValue));
												obj.setEqTypeId(typeid);
												obj.setDes(des);
												obj.setName(name);
												rlist.add(obj);
											}else{
												rlist.add(obj);
											}
										}
									}else{
									}
								}
							}
							//移除其他虚拟点的处理
							//removeVirtualPoint(list,keypoint.substring(keypoint.indexOf(".")+1,keypoint.indexOf(".")+3));
							isChange=false;
						}
					}
					

					// 如果存在多点合成一个点的情况
					if (SysUtil.getCondition().containsKey(des)) {
						Map coditionMap = SysUtil.getCondition();
						String[] conditionList = coditionMap.get(des)
								.toString().split(",");
						//如果当前项不是最后一项
						if(!getControlPoint(keypoint).equals(conditionList[conditionList.length-1])){
							if(controlPoint!=null){
								if (Math.abs(Double.parseDouble(flagValue)) == 1 && flag==false) {
									rlist.add(obj);
									flag=true;
								}else{
									obj.setValue("FLAG");
									rlist.add(obj);
								}
							}else{
								obj.setValue("FLAG");
								rlist.add(obj);
							}
						}else{
							//如果是最后一项
							tempObj=obj;
						}
						//如果是最后一个前置条件
						if(getControlPoint(keypoint).equals(conditionList[conditionList.length-2])){
							if(flag){
								tempObj.setValue("FLAG");
							}else{
								tempObj.setValue("正常");
							}
							rlist.add(tempObj);
							flag=false;
						}
					} else {
						if(!isChange){
							if(!containsVir(rlist,keypoint)){
								rlist.add(obj);	
							}
						}
					}
				}
			}
		}
		return rlist;
	}
	
	/**
	 * 锅炉累计流量点的合成
	 * @param 2013-4-18  
	 * @param        
	 * @return void
	 */
	private void bolidPointChange(List<VOKeyPoint> rlist){
		if(rlist.size()>0){
			for (VOKeyPoint voKeyPoint : rlist) {
				
				if(voKeyPoint.getEqTypeId().equals("2002")){
					
				}
			}
		}
	}
	
	private boolean IsContaisCondition(String typeid,String keyPoint){
		boolean result=false;
		if(SysUtil.getCludStyle().get(typeid)!=null && SysUtil.getCludStyle().get(typeid).indexOf(",")>0){
			String [] condList=SysUtil.getCludStyle().get(typeid).split(",");
			for(int i=0;i<condList.length;i++){
				if(condList[i].equals(keyPoint.substring(keyPoint.indexOf(".")+1, keyPoint.indexOf(".")+3))){
					result=true;
					break;
				}
			}
		}else{
			if(keyPoint.substring(keyPoint.indexOf(".")+1,keyPoint.indexOf(".")+3).equals(SysUtil.getCludStyle().get(typeid))){
				result=true;
			}
		}
		return result;
	}
	
	private void removeVirtualPoint(List list,String point){
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			if(map.get("project_point").toString().subSequence(map.get("project_point").toString().indexOf(".")+1, map.get("project_point").toString().indexOf(".")+3).equals(point)){
				list.remove(map);
			}
		}
	}
	
	private boolean containVirtualPoint(List<VOKeyPoint> rlist,String keyPoint){
		boolean flag=false;
		for (VOKeyPoint pointObj : rlist) {
			if(pointObj.getKeyPoint().equals(keyPoint) && !(pointObj.getValue().equals("FLAG"))){
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	private boolean containsVir(List<VOKeyPoint> rlist,String keyPoint){
		boolean flag=false;
		for (VOKeyPoint pointObj : rlist) {
			if(pointObj.getKeyPoint().equals(keyPoint)){
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	
	/**
	 * 判断是否存在相同的展示项
	 * @param 2013-4-7  
	 * @param @return       
	 * @return boolean
	 */
	private boolean containSameObj(List<VOKeyPoint> rlist,String des){
		boolean flag=false;
		for (VOKeyPoint pointObj : rlist) {
			if(pointObj.getDes().equals(des) && !(pointObj.getValue().equals("FLAG"))){
				flag=true;
				break;
			}
		}
		return flag;
	}

	private String getControlPoint(String keypoint) {
		String resultPoint = "";
		if (StringUtils.isNotEmpty(keypoint)) {
			int pointIndex = keypoint.indexOf(".");
			int pointLine = keypoint.indexOf("_");
			if (pointIndex > 0 && pointLine > 0) {
				resultPoint = keypoint.substring(pointIndex + 1, pointLine);
			} else if (pointIndex > 0) {
				resultPoint = keypoint.substring(pointIndex + 1);
			}
		}
		return resultPoint;
	}

	// 得到界面展示值
	private String getShowDes(String name, String des, String value) {
		Map<String, String> desValueMap = SysUtil.getDesValue();
		for (String str : desValueMap.keySet()) {
			if (str.equals(name)) {
				String values = desValueMap.get(str);
				String[] desValues = values.split("\\|");
				for (int i = 0; i < desValues.length; i++) {
					String v = (Math.abs(Double.parseDouble(value)) + "");
					value = v.substring(0, v.lastIndexOf("."));
					if (value.equals(desValues[i])) {
						value = desValues[i + 1];
						break;
					}
				}
			}
		}
		return value;
	}

	/**
	 * 关键设备组列表
	 * 
	 * @return
	 */
	public RetCode findSelGroup() {
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findSelGroup");	
		RetCode rc = new RetCode();
		//List list = keyEqMgr.findGroupList();
		List list = sqlSession.selectList("findGroupList");
		//rc.setObj(list);
		if (list != null && list.size()>0) {
			Map m = null;
			VOKeyEqGroup obj = null;
			Integer groupId = null;
			String groupName = null;
			List<VOKeyEqGroup> rlist = new ArrayList<VOKeyEqGroup>();
			for (int i = 0; i < list.size(); i++) {
				m = (Map) list.get(i);
				groupId = Integer.parseInt(m.get("group_id") + "");
				groupName = m.get("group_name") + "";

				obj = new VOKeyEqGroup();
				obj.setGroupId(groupId);
				obj.setGroupName(groupName);

				rlist.add(obj);
			}
			rc.setObj(rlist);
		} else {
			rc.setDesc("暂无数据");
		}
		return rc;
	}
	
	public Map getFindSelGroupSql() {
		return PerformSQLUtil.get("findGroupList", this);
	}
	

	/**
	 * 关键设备组关键设备列表 LG
	 * 
	 * @param groupid
	 *            组编号
	 * @return
	 */
	public RetCode findKeyEqCollect(Integer groupId) {
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findKeyEqCollect,参数groupid"+groupId);	
		RetCode rc = new RetCode();
		VOKeyEqGroup obj = null;
		try {
			//List rlist = keyEqMgr.findGroup(groupId);
			List rlist = sqlSession.selectList("findGroup", groupId);
			if (rlist != null && rlist.size()>0) {
				Map m = null;
				VOKeyEqS eq = null;
				VOKeyPoint point = null;
				String groupName = null;
				Integer equipId = null;
				Integer typeId = null;
				String name = null;
				String keyPoint = null;
				String depict = null;
				String des = null;
				String unit = null;
				String controlPoint = null;

				for (int i = 0, j = 1; i < rlist.size(); i++) {
					m = (Map) rlist.get(i);
					groupId = Integer.parseInt(m.get("group_id") + "");
					groupName = m.get("group_name") + "";
					equipId = Integer.parseInt(m.get("equip_id") + "");
					typeId = Integer.parseInt(m.get("equip_type_id") + "");
					name = m.get("equip_name") + "";
					keyPoint = m.get("project_point") + "";
					controlPoint+=keyPoint+"|";
					depict = m.get("descr") + "";
					des = m.get("depict") + "";
					unit = m.get("unit_type") + "";
					if (SysUtil.isNull(unit)) {
						unit = "";
					}
					if (SysUtil.isNull(des)) {
						des = depict.split("--")[1];
					}
					// 添加组
					if (obj == null) {
						obj = new VOKeyEqGroup();
						obj.setGroupId(groupId);
						obj.setGroupName(groupName);
						obj.setEquipList(new ArrayList<VOKeyEqS>());
					}
					// 添加设备
					if (obj.findEq(equipId) == null) {
						eq = new VOKeyEqS();
						eq.setEquipId(equipId);
						eq.setName(name);
						eq.setTypeId(typeId);
						eq.setPoints(new ArrayList<VOKeyPoint>());
						obj.getEquipList().add(eq);
					}

					// 添加点
					if (obj.findEq(equipId).findPoint(keyPoint) == null) {

						point = new VOKeyPoint();
						keyPoint=keyPoint.replace(",", "U");   //替换逗号（2013-04-18）
						point.setKeyPoint(keyPoint);
						if (depict != null) {
							depict = depict.substring(depict.indexOf("--") + 2);
						}
						point.setName(depict);
						point.setUnit(unit);
						point.setDes(des);
						obj.findEq(equipId).getPoints().add(point);
//						if(SysUtil.getCondition().containsKey(des)){
//							if(!containSamePoint(obj.findEq(equipId).getPoints(),des)){
//								obj.findEq(equipId).getPoints().add(point);
//							}
//						}else{
//							obj.findEq(equipId).getPoints().add(point);
//						}
					}
				}
			}

		} catch (Exception e) {
			logg.error("KeyEqBusiness-->findKeyEqCollect", e);
			// TODO: handle exception
			rc.setDesc("操作异常");
		}

		if (obj != null) {
			rc.setObj(obj);
		} else {
			rc.setDesc("暂无数据");
		}

		return rc;
	}
	
	
	public Map getFindKeyEqCollectSql() {
		return PerformSQLUtil.get("findGroup", this);
	}
	
	private boolean containSamePoint(List<VOKeyPoint> voKeyPointList,String des){
		boolean flag=false;
		if(voKeyPointList.size()>0){
			for (VOKeyPoint voKeyPoint : voKeyPointList) {
				if(voKeyPoint.getDes().equals(des)){
					flag=true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 获取监控点标识
	 * 
	 * @param 2013-4-2
	 * @param @param point
	 * @param @return
	 * @return String
	 */
	private String getControlFlag(String point) {
		String resultFlag = "";
		// 判断是否存在点
		int decimalIndex = point.indexOf(".");
		int underlineIndex = point.indexOf("_");
		if (decimalIndex > 0 && underlineIndex > 0) {
			resultFlag = point.substring(decimalIndex + 1, underlineIndex);
		} else if (decimalIndex > 0 && underlineIndex <= 0) {
			resultFlag = point.substring(decimalIndex + 1, decimalIndex + 3);
		}
		return resultFlag;
	}
	/**
	 * 根据设备类型，获取对应的状态点集合
	 * 
	 * @param 2013-4-2
	 * @param @param eqTypeId
	 * @param @return
	 * @return String
	 */
	private String[] getStatePoint(int eqTypeId) {
		String[] pointList = new String[2];
		Map<String, String> map = SysUtil.getZhuanjiaMap();
		String condition = ""; // 状态点位集合
		if (condition != null) {
			if (condition.indexOf(",") > 0) {
				pointList = condition.split(",");
			} else {
				return null;
			}
		} else {
			return null;
		}
		return pointList;
	}
	/**
	 * 查询设备类型列表
	 * 
	 * @return
	 */
	public RetCode findEqTypeList() {
		// 设备类型
		//return equipTypeMgr.findAllTopEquipType();
		
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findEqTypeList");	
		RetCode rc = new RetCode();
		List lst = sqlSession.selectList("findAllTopEquipTypeKey");
		rc.setObj(lst);
		return rc;
		
	}
	
	public Map getFindEqTypeListSql() {
		return PerformSQLUtil.get("findAllTopEquipTypeKey", this);
	}

	/**
	 * 查询设备列表 (当前类型及二级子类型所有设备)
	 * 
	 * @param eqtype
	 *            设备类型
	 * @return
	 */
	public List<VOKeyEqS> findEqList(Integer eqtype) {

		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findEqList,参数eqtype:"+eqtype);	
		List<VOKeyEqS> list = null;
		//List rlist = keyEqMgr.findEqList(eqtype);
		Map map = new HashMap();
		map.put("eqtype", eqtype);
		List rlist = sqlSession.selectList("findEqListKey", map);

		if (rlist != null) {
			list = new ArrayList<VOKeyEqS>();
			Map m = null;
			VOKeyEqS obj = null;
					 
			for (int i = 0; i < rlist.size(); i++) {				
				m = (Map) rlist.get(i);				
				obj = new VOKeyEqS();
				obj.setEquipId(Integer.parseInt(m.get("equip_id") + ""));
				obj.setName(m.get("equip_name") + "");
				list.add(obj);
			}
			/*	
			for (int i = 0; i < rlist.size(); i++) {				
				obj = new VOKeyEqS();
				obj.setEquipId(((DbEquipList)rlist.get(i)).getEquipId());
				obj.setName(((DbEquipList)rlist.get(i)).getEquipName());
				list.add(obj);
			}
			*/
		}

		return list;
	}
	
	
	public Map getFindEqListSql() {
		return PerformSQLUtil.get("findEqListKey", this);
	}

	/**
	 * 查询点位列表
	 * 
	 * @param eqcode
	 *            设备编号
	 * @return
	 */
	public List<VOKeyPoint> findPointList(Integer equipId) {
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findPointList,参数equipId:"+equipId);	
		List<VOKeyPoint> list = null;
		//List rlist = keyEqMgr.findPointList(equipId);
		Map map = new HashMap();
		map.put("equipId", equipId+".");
		
		List rlist = sqlSession.selectList("findPointList", map);

		if (rlist != null) {
			list = new ArrayList<VOKeyPoint>();
			Map m = null;
			VOKeyPoint obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				m = (Map) rlist.get(i);
				String name = m.get("depict") + "(" + m.get("title") + ")";
				if (SysUtil.isNullObject(m.get("title"))) {
					name = m.get("depict") + "";
				} else {
					name = m.get("depict") + "(" + m.get("title") + ")";
				}
				int a = 0;
				if (list.size() == 0) {
					obj = new VOKeyPoint();
					obj.setKeyPoint(m.get("seq") + "");
					obj.setName(name);
					list.add(obj);
				} else {
					for (int j = 0; j < list.size(); j++) {
						VOKeyPoint vo = list.get(j);
						// 若name相同，则认为是虚拟点位，此时，将point的seq与之前的seq用逗号连接，覆盖之前的keyPoint值
						if (vo.getName().equals(name)) {
							String seq = vo.getKeyPoint();
							if (!m.get("seq").toString().equals(seq)) {
								vo.setKeyPoint(seq + "," + m.get("seq") + "");
							}
							break;
						} else {
							// 若不等，则先将该点位加入到list
							a++;
							if (a != 0 && a == list.size()) {
								obj = new VOKeyPoint();
								obj.setKeyPoint(m.get("seq") + "");
								obj.setName(name);
								list.add(obj);
							}
						}
					}
				}
			}
		}
		return list;
	}
	
	public Map getFindPointListSql() {
		return PerformSQLUtil.get("findPointList", this);
	}

	/**
	 * 查询当前组设备列表(名称列表)
	 * 
	 * @param groupid
	 * @return
	 */
	public String findGrouEq(Integer groupid) {
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findGrouEq,参数groupid:"+groupid);	
		//List list = keyEqMgr.findEq(groupid);
		List list = sqlSession.selectList("findEq", groupid);
		
		String eqlist = "";
		if (list != null) {
			
			Map m = null;
			for (int i = 0; i < list.size(); i++) {
				m = (Map) list.get(i);
				if ("" == eqlist) {
					eqlist = m.get("equip_id") + "=" + m.get("equip_name");
				} else {
					eqlist += ";" + m.get("equip_id") + "="
							+ m.get("equip_name");
				}
			}
			
			/*
			HashMap<String, Object> m = null;
			
			DbEquipList objm = new DbEquipList();
			
			for (int i = 0; i < list.size(); i++) {
				//m = (HashMap) list.get(i);
				
				objm = (DbEquipList)list.get(0);
				
				if ("" == eqlist) {
					//eqlist = m.get("equip_id") + "=" + m.get("equip_name");
					eqlist = objm.getEquipId()+"="+objm.getEquipName();					
					
				} else {
					//eqlist += ";" + m.get("equip_id") + "="	+ m.get("equip_name");
					eqlist += ";" + objm.getEquipId()+"="+objm.getEquipName();	
				}
			}
			*/
		}

		return eqlist;
	}
	
	
	public Map getFindGrouEqSql() {
		return PerformSQLUtil.get("findEq", this);
	}

	/**
	 * 查询当前组设备列表(编号列表)
	 * 
	 * @param groupid
	 * @return
	 */
	public String findGrouEqStr(Integer groupid) {
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findGrouEqStr,参数groupid:"+groupid);	
		//List list = keyEqMgr.findEqs(groupid);
		List list = sqlSession.selectList("findEqs", groupid);
		
		String eqlist = "";
		if (list != null) {
			Map m = null;
			Integer equipId = null;
			String pointName = null;
			Integer point = null;
			HashMap<Integer, String> equipIdM = new HashMap<Integer, String>();
			DbKeyEq objm = new DbKeyEq();

			for (int i = 0; i < list.size(); i++) {
//				m = (Map) list.get(i);
				objm = (DbKeyEq)list.get(i);
				point = objm.getDbPoint().getSeq();
//				point = Integer.parseInt(m.get("key_point") + ""); 
//				equipId =Integer.parseInt(m.get("equip_id") + ""); 
				equipId = objm.getDbEquipList().getEquipId();
//				pointName = m.get("name") + ""; 
				pointName = objm.getDbEquipList().getEquipName();
				if (equipIdM.get(equipId) == null) {

					equipIdM.put(equipId, point + "");
				} else {
					equipIdM.put(equipId, equipIdM.get(equipId) + "," + point);
				}
			}
			for (Iterator it = equipIdM.keySet().iterator(); it.hasNext();) {
				equipId = (Integer) it.next();
				if ("" == eqlist) {
					eqlist = equipId + "=" + equipIdM.get(equipId);
				} else {
					eqlist += ";" + equipId + "=" + equipIdM.get(equipId);
				}
			}
		}

		return eqlist;
	}
	
	public Map getFindGrouEqStrSql() {
		return PerformSQLUtil.get("findEqs", this);
	}

	/**
	 * 删除关键设备组
	 * 
	 * @param groupid
	 * @return
	 */
	public boolean deleteGroup(Integer groupid) {
		//return keyEqMgr.deleteGroup(groupid);
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.deleteGroup,参数groupid:"+groupid);	
		
		boolean check = true;	
		try{
			sqlSession.delete("deleteGroup", groupid);
		}catch(Exception e){
			check = false;
			logg.error("进入KeyEqBusiness.deleteGroup,对象删除失败！", e);
		}	
		return check;
	}
	
	public Map getDeleteGroupSql() {
		return PerformSQLUtil.get("deleteGroup", this);
	}

	/**
	 * 更新关键设备组
	 * 
	 * @param grname
	 * @param sqlstr
	 * @return
	 */
	public RetCode updateGroupList(Integer groupId, String groupName,
			String sqlStr) {
		RetCode rt = new RetCode();
		try {
			// if (!SysUtil.isNull(groupid) ) {
			List<DbKeyEq> list = new ArrayList<DbKeyEq>();
			String[] eqs = sqlStr.split(";");
			String[] tmp = new String[]{};
			String[] points = null;
			Integer equipId = null;
			String point = null;
			DbKeyEq obj = null;

			for (int i = 0; i < eqs.length; i++) {
				equipList = new DbEquipList();
				tmp = eqs[i].split("=");
				if (tmp != null&&tmp.length>1) {
					equipId = Integer.parseInt(tmp[0]);
				
					points = tmp[1].split(",");
					for (int j = 0; j < points.length; j++) {
						dbPoint = new DbPoint();
						point = points[j];
						obj = new DbKeyEq();
						obj.setGroupName(groupName);
						equipList.setEquipId(equipId);
						obj.setDbEquipList(equipList);
						dbPoint.setSeq(Integer.parseInt(point));
						obj.setDbPoint(dbPoint);
						if (!list.contains(obj)) {
							list.add(obj);
						}
					}
				}
			}
			int result = this.updateDbKeyEqS(groupId, list);
			rt.setDesc(result > 0 ? "操作成功!" : "操作失败!");
			// }
		} catch (Exception e) {
			logg.error("KeyEqBusiness-->updateGroupList", e);
			// TODO: handle exception
			rt.setDesc("操作异常!");
			e.printStackTrace();
		}
		return rt;
	}
	
	
	
	public int updateDbKeyEqS(Integer groupid,List<DbKeyEq> list){
		int count=0;
		if (list !=null) {
			
			//删除原关键设备组
			if (!SysUtil.isNullObject(groupid)) {
				this.deleteGroup(groupid);
			}
			//新增关键设备
			count =this.insertDbKeyEqS(groupid,list);
			if (count != list.size()) {
				count =0;
			}
		}
		return count;
	}
	
	public int insertDbKeyEqS(Integer groupId,List<DbKeyEq> list){

		String sql=null;
		int count=0;
		int result=0;
		Integer g =0;
		if (list !=null) {
			DbKeyEq obj =null;
			for (int i = 0; i <list.size(); i++) {
				obj = list.get(i);
				DbKeyEq dbKeyEq = new DbKeyEq();
				dbKeyEq.setGroupName(obj.getGroupName());
				DbEquipList dbEquipList = new DbEquipList();
				dbEquipList.setEquipId(obj.getDbEquipList().getEquipId());
				dbKeyEq.setDbEquipList(dbEquipList);
				DbPoint dbPoint = new DbPoint();
				dbPoint.setSeq(obj.getDbPoint().getSeq());
				dbKeyEq.setDbPoint(dbPoint);
				
				if(groupId!=null){
//					sql = "insert into db_key_eq values( seq_key_eq.nextval,"+groupId+",'" + obj.getGroupName() + "'," + ""
//					+ obj.getDbEquipList().getEquipId() + "," + obj.getDbPoint().getSeq() + ")";
					dbKeyEq.setGroupId(groupId);
					result = sqlSession.insert("insertDbKeyEqS", dbKeyEq);
				}else{
					if(g==0){
						//RetCode rc = this.findBySQL(keyEqSDao,"select seq_key_eq.nextval from dual");
						RetCode rc = this.getData("insertDbKeyEqSRetCode", null);
						g = Integer.parseInt(((Map)((List)rc.getObj()).get(0)).get("nextval").toString());
					}
//					sql = "insert into db_key_eq values( seq_key_eq.nextval,"+g+",'" + obj.getGroupName() + "'," + ""
//					+ obj.getDbEquipList().getEquipId() + "," + obj.getDbPoint().getSeq() + ")";
					dbKeyEq.setGroupId(g);
					result = sqlSession.insert("insertDbKeyEqS", dbKeyEq);
				}
				
				
//				System.out.println("新增关键设备:\n\n"+sql);
				//result = this.updateBySQL(keyEqSDao, sql);
				if (result!=1) {
					break;
				}
				else{
					count++;
				}
			}
			
			if (count != list.size()) {
				count =0;
				
			}
		}
		
		return count;
	}
	
	
	public Map getInsertDbKeyEqSSql() {
		return PerformSQLUtil.get("insertDbKeyEqSRetCode", this);
	}
	

	/**
	 * 查询关键设备组列表
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findGroupList(int currentPage, int pageSize,@Param(value="_parameter") String _parameter) {
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findGroupList,参数wherestr:"
					+ _parameter);		
		RetCode rt = new RetCode();

		HashList<VOKeyEqGroup> list = null;
		try {
			//根据条件查询,关键设备组列表
			List rlist = sqlSession.selectList("findGroupLists", _parameter);
			
			
			if (rlist != null) {
				list = new HashList<VOKeyEqGroup>();
				Map m = null;
				VOKeyEqGroup obj = null;
				VOKeyEqS eq = null;
				VOKeyPoint point = null;
				Integer groupId = null;
				String groupName = null;
				Integer equipId = null;
				// Integer typeId = null;
				String equipName = null;
				String keyPoint = null;
				String pointName = null;
				// String depict = null;
				
				DbKeyEq objm = new DbKeyEq();
				
				for (int i = 0, j = 1; i < rlist.size(); i++) {
					//objm = (DbKeyEq)rlist.get(i);
					
					m = (Map) rlist.get(i);
					groupId = Integer.parseInt(m.get("group_id") + ""); // objm.getGroupId();
					groupName = m.get("group_name") + "";  //  objm.getGroupName();
					equipId = Integer.parseInt(m.get("equip_id") + "");  // objm.getDbEquipList().getEquipId();
					// typeId = Integer.parseInt(m.get("equip_type_id")+"");
					equipName = m.get("equip_name") + "";  //  objm.getDbEquipList().getEquipName();
					keyPoint = m.get("key_point") + "";   //objm.getDbPoint().getSeq()+"";
					pointName = m.get("point_name") + "";  // objm.getDbPoint().getPointName();
					// depict = m.get("depict")+"";
					if (obj == null) {
						obj = list.find(groupId);
						// 添加组 若存在该组ID，则 不进行加入，直接在该组内加入设备即可
						obj = new VOKeyEqGroup();
						obj.setAt(j++);
						obj.setGroupId(groupId);
						obj.setGroupName(groupName);
						obj.setEquipList(new ArrayList<VOKeyEqS>());
						list.add(groupId + "", obj);
					} else {
						if (!groupId.equals(obj.getGroupId())) {
							obj = list.find(groupId);
							obj = new VOKeyEqGroup();
							obj.setAt(j++);
							obj.setGroupId(groupId);
							obj.setGroupName(groupName);
							obj.setEquipList(new ArrayList<VOKeyEqS>());
							list.add(groupId + "", obj);
						}
					}

					// 添加设备
					if (obj.getEquipList() == null
							|| obj.getEquipList().size() < 1) {
						eq = new VOKeyEqS();
						eq.setEquipId(equipId);
						eq.setName(equipName);
						eq.setPoints(new ArrayList<VOKeyPoint>());
						obj.getEquipList().add(eq);
					} else {
						int flag = 0;
						for (int a = 0; a < obj.getEquipList().size(); a++) {
							if (!equipId.equals(obj.getEquipList().get(a)
									.getEquipId())) {
								flag++;
							}
						}
						if (flag == obj.getEquipList().size() && flag != 0) {
							eq = new VOKeyEqS();
							eq.setEquipId(equipId);
							eq.setName(equipName);
							// eq.setTypeId(typeId);
							eq.setPoints(new ArrayList<VOKeyPoint>());
							obj.getEquipList().add(eq);
						}
					}

					// 添加点
					for (VOKeyEqS e : obj.getEquipList()) {
						if (equipId.equals(e.getEquipId())) {
							if (e.getPoints() == null
									|| e.getPoints().size() < 1) {
								point = new VOKeyPoint();
								point.setKeyPoint(keyPoint);
								point.setName(pointName);
								e.getPoints().add(point);
							} else {
								int flag = 0;
								for (VOKeyPoint vkp : e.getPoints()) {
									if (!keyPoint.equals(vkp.getKeyPoint())) {
										flag++;
									}
								}
								if (flag != 0 && flag == e.getPoints().size()) {
									point = new VOKeyPoint();
									point.setKeyPoint(keyPoint);
									point.setName(pointName);
									e.getPoints().add(point);
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			logg.error("KeyEqBusiness-->findGroupList", e);
			// TODO: handle exception
			e.printStackTrace();
		}

		if (list != null) {
			HashList<VOKeyEqGroup> rlist = new HashList<VOKeyEqGroup>();
			for (int i = (currentPage - 1) * pageSize; i < list.size()
					&& i < currentPage * pageSize; i++) {
				rlist.add(list.get(i));
			}
			rt.setObj(rlist);
			rt.setPage(new Page(currentPage, list.size(), pageSize));
		}

		return rt;
	}
	
	
	public Map getFindGroupListSql() {
		return PerformSQLUtil.get("findGroupLists", this);
	}

	/**
	 * 查询关键设备列表
	 * 
	 * @param groupid
	 *            组编号
	 * @return
	 */
	public List<VOKeyEqS> findKeyEqList(Integer groupid) {
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findKeyEqList,对象groupid:"
					+ groupid);		
		List<VOKeyEqS> list = null;
		//List rlist = keyEqMgr.findEq(groupid);
		List rlist = sqlSession.selectList("findEq", groupid);

		if (rlist != null) {
			list = new ArrayList<VOKeyEqS>();
			Map m = null;
			VOKeyEqS obj = null;

			for (int i = 0; i < rlist.size(); i++) {
				m = (Map) rlist.get(i);
				obj = new VOKeyEqS();
				obj.setEquipId(Integer.parseInt(m.get("equip_id") + ""));
				obj.setName(m.get("name") + "");
				list.add(obj);
			}
		}

		return list;
	}
	
	
	public Map getFindKeyEqListSql() {
		return PerformSQLUtil.get("findEq", this);
	}

	/**
	 * 查询关键设备点位列表
	 * 
	 * @param groupid
	 *            组编号
	 * @param eqcode
	 *            设备编号
	 * @return
	 */
	public List<VOKeyPoint> findKeyPointList(Integer groupid, String eqcode) {
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.findKeyEqList,参数groupid:"
					+ groupid+" eqcode:"
					+ eqcode);		
		List<VOKeyPoint> list = null;
		//List rlist = keyEqMgr.findKeyPoint(groupid, eqcode);
		Map map = new HashMap();
		map.put("groupid", groupid);
		map.put("eqcode", eqcode);
		List rlist = sqlSession.selectList("findKeyPoint", map);

		if (rlist != null) {
			list = new ArrayList<VOKeyPoint>();
			Map m = null;
			VOKeyPoint obj = null;

			for (int i = 0; i < rlist.size(); i++) {
				m = (Map) rlist.get(i);
				obj = new VOKeyPoint();
				obj.setKeyPoint(m.get("key_point") + "");
				obj.setName(m.get("equip_name") + "");
				list.add(obj);
			}
		}

		return list;
	}
	
	
	public Map getFindKeyPointListSql() {
		return PerformSQLUtil.get("findKeyPoint", this);
	}
	/**
	 * 判断是否为唯一的组名
	 * 
	 * @param 2013-4-2
	 * @param @param groupName
	 * @param @param groupId
	 * @param @return
	 * @return boolean
	 */
	public boolean IsUniqueGroup(String groupName, String groupId) {
		//return keyEqMgr.IsUniqueGroup(groupName, groupId);
		if (logg.isDebugEnabled())
			logg.debug("进入KeyEqBusiness.IsUniqueGroup,参数groupName:"
					+ groupName+" groupId:"
					+ groupId);	
		
		List grouplist = null;
		Map map = new HashMap();
		map.put("groupName", groupName);
		
		if (null==groupId||"".equals(groupId)) {
			map.put("groupId", null);
		}else{
			map.put("groupId", groupId);
		}
		boolean check = true;	
		try{
			grouplist = sqlSession.selectList("IsUniqueGroup",map);
			if (grouplist!=null&&!grouplist.isEmpty()&&grouplist.size()>0) {
				check= false;
			}
			
		}catch(Exception e){
			
			logg.error("进入KeyEqBusiness.IsUniqueGroup,对象查询失败！", e);
		}					
		return check;
		
		
	}
	
	public Map getIsUniqueGroupSql() {
		return PerformSQLUtil.get("IsUniqueGroup", this);
	}
	
	
}
