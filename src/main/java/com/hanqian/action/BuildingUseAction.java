package com.hanqian.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BuildingUseBusiness;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;


public class BuildingUseAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(BuildingUseAction.class);
	private BuildingUseBusiness buildingUseBusiness;
	private HttpServletRequest request;
	
	private Map sqlmap = new HashMap();

	private Map seqMap = new HashMap();

	private Map remindmapAll = new HashMap();
	
	private Map outpatientMap = new HashMap();
	
	private Map emergencyMap = new HashMap();
	
	private Map hospitalizationMap = new HashMap();
	
	private Map medicalMap = new HashMap();
	
	private Map securityMap = new HashMap();
	
	private Map administrationMap = new HashMap();
	
	private Map scientificMap = new HashMap();
	
	private Map educationMap = new HashMap();
	
	private Map lifeMap = new HashMap();
	
	private Map garageMap = new HashMap();
	
	private Map othersMap = new HashMap();

	private Map totalMap = new HashMap();
	
	private Map remindmapUse = new HashMap();
	
	private RetCode rcArea;
	
	private List buildAreaList = null;
	
	private Integer buildingId; // 楼宇ID
	
	public BuildingUseBusiness getBuildingUseBusiness() {
		return buildingUseBusiness;
	}

	public void setBuildingUseBusiness(BuildingUseBusiness buildingUseBusiness) {
		this.buildingUseBusiness = buildingUseBusiness;
	}
	/**
	 * 楼宇用途列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findBuildingUse() throws Exception{
		DecimalFormat df = new DecimalFormat(".00");
		
		request = ServletActionContext.getRequest();
		//获取楼宇用途信息
		RetCode rc = buildingUseBusiness.findBuildingUse();
		List list = new ArrayList();
		if (rc.getObj()!=null) {
			list = (List)rc.getObj();
		}
		Double buildAreaTotal =0.00;
		for (int i = 0; i < list.size(); i++) {
		    BigDecimal bd1 = new BigDecimal(Double.toString(buildAreaTotal)); 
	        BigDecimal bd2 = new BigDecimal(((Map)list.get(i)).get("a_area").toString()); 
	        buildAreaTotal = bd1.add(bd2).doubleValue(); 
		}
		//医院总面积
		request.setAttribute("buildAreaTotal",buildAreaTotal);
		//加入用途比例
		for (int i = 0; i < list.size(); i++) {
			Double area = Double.parseDouble(((Map)list.get(i)).get("a_area").toString());
			if (buildAreaTotal==0) {
				((Map)list.get(i)).put("buildUsePer",0);
			}else{
				Double areaPer = (area/buildAreaTotal)*100;
				((Map)list.get(i)).put("buildUsePer", Double.parseDouble(df.format(areaPer)));
			}
		}
		
		logg.debug("     buildUseList:    "+list);
		request.setAttribute("buildUseList",list);
		//颜色匹配
		request.setAttribute("colorMap",SysUtil.getColor());  
		//获取楼宇名字
		rcArea = buildingUseBusiness.findBuildingArea();
		if (rcArea.getObj()!=null) {
			buildAreaList = (List)rcArea.getObj();
			request.setAttribute("buildArea",buildAreaList);  
		}
		//获取单体楼用途面积
		RetCode rcBuildUseArea = buildingUseBusiness.findBuildUseArea();
		List buildUserAreaList = null;
		if(rcBuildUseArea.getObj()!=null){
			buildUserAreaList = (List)rcBuildUseArea.getObj();
		}
		try{
		//获取单体楼各用途面积比
		if (buildAreaList!=null&&!buildAreaList.isEmpty()) {
			for (int j = 0; j < buildAreaList.size(); j++) {
				for (int i = 0; i < buildUserAreaList.size(); i++) {
					Map userAreaMap =(Map)buildUserAreaList.get(i);
					Map areaMap = (Map)buildAreaList.get(j);
					if (userAreaMap.get("building_id").equals(areaMap.get("building_id"))) {
						if(Double.parseDouble(areaMap.get("total").toString())>0){
							Double perNum = 0.0;
							if(userAreaMap.get("sa") == null){
								perNum = 0.0/Double.parseDouble(areaMap.get("total").toString())*100;
							}else{
								perNum = Double.parseDouble(userAreaMap.get("sa").toString())/Double.parseDouble(areaMap.get("total").toString())*100;
							}
							userAreaMap.put("useAreaPer", Double.parseDouble(df.format(perNum)));
						}else{
							userAreaMap.put("useAreaPer", 0);
						}
					}
				}
			}
		}
		}catch (Exception e) {
			logg.error("BuildingUseAction-->findBuildingUse", e);
			e.printStackTrace();
		}
		
		//调用抽取任意提醒需要的数据的方法
		remindBuildInfoDate();
		
		request.setAttribute("buildUseArea",buildUserAreaList);  
		return "buildUse";
	}
	
	
	/**
	 * 将任意提醒的数据打包成map
	 * @param 
	 * @param
	 * @return
	 */
	public String remindBuildInfoDate() {
		int currentPage = 1;
		int pageSize = 10;
		request = ServletActionContext.getRequest();
		try {
			
			//获取楼宇用途sql对象放到Map对象中
			sqlmap  = buildingUseBusiness.getFindBuildingAreaSql();
			
			logg.debug("***************sqlmap="+sqlmap);
			
			rcArea = buildingUseBusiness.findBuildingArea();
			
			if(rcArea.getObj()!=null){
				buildAreaList = (List)rcArea.getObj();
				if(buildingId==null){
				Map areaMap = (Map)buildAreaList.get(0);
					Object ob = areaMap.get("building_id");
					buildingId=Integer.parseInt(ob.toString());
				}
				logg.debug("    buildAreaList.get(0).getClass()=     "+buildAreaList.get(0).getClass());
			}
			seqMap.put("buildingId", buildingId);
			
			//（total）
			//提醒方式:1、短信提醒；2、邮件提醒；3、平台内部提醒
			totalMap.put("ariesPreForm", "1,2,3");
			//sql字段名称
			totalMap.put("sqlField", "total");
			//中文字段名
			totalMap.put("fieldNm", "总面积");
			//输入类型 ：1-文本;2-数字;3-时间
			totalMap.put("ariesInputType", 2);
			//sql
			totalMap.putAll(sqlmap);
			//含有多条，或者分页数据是用来对应数据用，数据是查出来的某条数据中有标识性的字段，如seq、ID等
			totalMap.put("sqlFieldKey", seqMap);
			//用来判断数据是否更新的时间字段，通过sql查询出来的结果集中的时间字段等来判断
			totalMap.put("updateDateField", "inputtime");
			//用来判断数据是否更新的sql
			totalMap.put("updateFieldSql", "select b.building_id,b.building_name,bs.total,b.storey_num_down,b.storey_num_up , "
					+ "b.inputtime from db_building b,(select sum(acreage) total,building_id from"
					+ " DB_BUILDING_STOREY where status = 0 group by building_id) bs where"
					+ " bs.building_id = b.building_id and b.status = 0 order by bs.building_id");
//			remindmap.put("initFunc", "testDate");

			//将所有需要提醒的数据打包成map
			for (int i = 0; i < buildAreaList.size(); i++) {
				remindmapAll.put("total"+i, totalMap);
			}
			
			//将已经打包好的MAP转换成JSON
			JSONObject remindJson = JSONObject.fromObject(remindmapAll);
			
			
			
			//提醒方式:1、短信提醒；2、邮件提醒；3、平台内部提醒
			outpatientMap.put("ariesPreForm", "1,2,3");
			//sql字段名称
			outpatientMap.put("sqlField", "outpatient");
			//中文字段名
			outpatientMap.put("fieldNm", "门诊");
			//输入类型 ：1-文本;2-数字;3-时间
			outpatientMap.put("ariesInputType", 2);
			//sql
			outpatientMap.putAll(sqlmap);
			//含有多条，或者分页数据是用来对应数据用，数据是查出来的某条数据中有标识性的字段，如seq、ID等
			outpatientMap.put("sqlFieldKey", seqMap);
			//用来判断数据是否更新的时间字段，通过sql查询出来的结果集中的时间字段等来判断
			outpatientMap.put("updateDateField", "inputtime");
			//用来判断数据是否更新的sql
			outpatientMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
//			remindmap.put("initFunc", "testDate");

			//（emergency）
			emergencyMap.put("ariesPreForm", "1,2,3");
			emergencyMap.put("sqlField", "emergency");
			emergencyMap.put("fieldNm", "急诊");
			emergencyMap.put("ariesInputType", 2);
			emergencyMap.putAll(sqlmap);
			emergencyMap.put("sqlFieldKey", seqMap);
			emergencyMap.put("updateDateField", "inputtime");
			emergencyMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（hospitalization）
			hospitalizationMap.put("ariesPreForm", "1,2,3");
			hospitalizationMap.put("sqlField", "hospitalization");
			hospitalizationMap.put("fieldNm", "住院");
			hospitalizationMap.put("ariesInputType", 2);
			hospitalizationMap.putAll(sqlmap);
			hospitalizationMap.put("sqlFieldKey", seqMap);
			hospitalizationMap.put("updateDateField", "inputtime");
			hospitalizationMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（medical）
			medicalMap.put("ariesPreForm", "1,2,3");
			medicalMap.put("sqlField", "medical");
			medicalMap.put("fieldNm", "医技");
			medicalMap.put("ariesInputType", 2);
			medicalMap.putAll(sqlmap);
			medicalMap.put("sqlFieldKey", seqMap);
			medicalMap.put("updateDateField", "inputtime");
			medicalMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（security）
			securityMap.put("ariesPreForm", "1,2,3");
			securityMap.put("sqlField", "security");
			securityMap.put("fieldNm", "保障");
			securityMap.put("ariesInputType", 2);
			securityMap.putAll(sqlmap);
			securityMap.put("sqlFieldKey", seqMap);
			securityMap.put("updateDateField", "inputtime");
			securityMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（administration）
			administrationMap.put("ariesPreForm", "1,2,3");
			administrationMap.put("sqlField", "administration");
			administrationMap.put("fieldNm", "行政");
			administrationMap.put("ariesInputType", 2);
			administrationMap.putAll(sqlmap);
			administrationMap.put("sqlFieldKey", seqMap);
			administrationMap.put("updateDateField", "inputtime");
			administrationMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（scientific）
			scientificMap.put("ariesPreForm", "1,2,3");
			scientificMap.put("sqlField", "scientific");
			scientificMap.put("fieldNm", "科研");
			scientificMap.put("ariesInputType", 2);
			scientificMap.putAll(sqlmap);
			scientificMap.put("sqlFieldKey", seqMap);
			scientificMap.put("updateDateField", "inputtime");
			scientificMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（education）
			educationMap.put("ariesPreForm", "1,2,3");
			educationMap.put("sqlField", "education");
			educationMap.put("fieldNm", "教育");
			educationMap.put("ariesInputType", 2);
			educationMap.putAll(sqlmap);
			educationMap.put("sqlFieldKey", seqMap);
			educationMap.put("updateDateField", "inputtime");
			educationMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（life）
			lifeMap.put("ariesPreForm", "1,2,3");
			lifeMap.put("sqlField", "life");
			lifeMap.put("fieldNm", "生活");
			lifeMap.put("ariesInputType", 2);
			lifeMap.putAll(sqlmap);
			lifeMap.put("sqlFieldKey", seqMap);
			lifeMap.put("updateDateField", "inputtime");
			lifeMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（garage）
			garageMap.put("ariesPreForm", "1,2,3");
			garageMap.put("sqlField", "garage");
			garageMap.put("fieldNm", "车库");
			garageMap.put("ariesInputType", 2);
			garageMap.putAll(sqlmap);
			garageMap.put("sqlFieldKey", seqMap);
			garageMap.put("updateDateField", "inputtime");
			garageMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（others）
			othersMap.put("ariesPreForm", "1,2,3");
			othersMap.put("sqlField", "others");
			othersMap.put("fieldNm", "其它");
			othersMap.put("ariesInputType", 2);
			othersMap.putAll(sqlmap);
			othersMap.put("sqlFieldKey", seqMap);
			othersMap.put("updateDateField", "inputtime");
			othersMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			
			//将所有需要提醒的数据打包成map
			remindmapAll.put("outpatient", outpatientMap);
			remindmapAll.put("emergency", emergencyMap);
			remindmapAll.put("hospitalization", hospitalizationMap);
			remindmapAll.put("medical", medicalMap);
			remindmapAll.put("security", securityMap);
			remindmapAll.put("administration", administrationMap);
			remindmapAll.put("scientific", scientificMap);
			remindmapAll.put("education", educationMap);
			remindmapAll.put("life", lifeMap);
			remindmapAll.put("garage", garageMap);
			remindmapAll.put("others", othersMap);
			
			
			
			
			
			request.setAttribute("remindJson", remindJson);
			logg.debug("    remindJson=   "+remindJson);
		}catch (Exception e) {
			logg.error("HospDetailAction-->showHospDetail", e);
		}
		return "show";
	}
}
