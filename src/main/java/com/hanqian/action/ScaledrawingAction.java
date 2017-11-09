package com.hanqian.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jfree.util.Log;

import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.business.ScaledrawingBusiness;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

public class ScaledrawingAction {

	private HttpServletRequest request;
	private BuildingBusiness buildingBusiness;
	private HospInfoBusiness hospBusiness;//医院
	private ScaledrawingBusiness drawBussiness; // 比例图
	private Integer buildingId;		//楼宇Id
	private Integer hospId;		//医院Id
	private String hospName;		//医院名称
	private List<DbBuilding> listB;  //楼宇集合
	private String imagePath = "";  //楼宇占比图片路径
	private String showPath;
	
	private Map sqlmap = new HashMap();
    
    private Map seqMap = new HashMap();
	
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
	
	private Map remindmapAll = new HashMap();

	private static final Logger logg = Logger.getLogger(ScaledrawingAction.class);


	/**
	 * 楼层用途比例
	 * @return
	 */
	public String showStoreyUse() {
		request = ServletActionContext.getRequest();
        
			try {
				if(request.getSession().getAttribute("users")!=null){
					DbUsers user = (DbUsers) request.getSession().getAttribute("users");
					hospId = user.getDbHospInfo().getSeqHosp();
				}
				if (!SysUtil.isNull(hospId.toString())) {
					DbHospInfo dbhosp=hospBusiness.findHospInfoForId(hospId);
					hospName = dbhosp.getHospName();
//					System.out.println(hospName);
				}
				String buildId=request.getParameter("buildingId");
				if(StringUtils.isNotEmpty(buildId)){
					 buildingId=Integer.parseInt(buildId);
				}
				DbHospInfo hospInfo = new DbHospInfo();
				hospInfo.setSeqHosp(hospId);
				DbBuilding b=new DbBuilding();
				b.setDbHospInfo(hospInfo);
				RetCode rcc=buildingBusiness.findDBBuilding(b);
				if(rcc.getObj()!=null){
					listB=(List<DbBuilding>) rcc.getObj();
				}
				
				if (!SysUtil.isNull(buildingId.toString())) {
					//楼宇用途比例图
					HashMap<String, Object> bm = drawBussiness.findBuildArea(1,buildingId);
					request.setAttribute("buildinguse", bm);
					
					//用途面积
					Map yongtu= new HashMap();
					if (null != bm) {
						ArrayList list =(ArrayList)bm.get("obj");
						if (null != list) {
							for (int i = 0; i < list.size(); i++) {
								Map m =(Map)list.get(i);
								if (null == yongtu.get(m.get("content1"))) {
									yongtu.put(m.get("content1").toString(), m);
								}
							}
						}
					}
					request.setAttribute("yongtu", yongtu);
					
					DbBuilding dbBuilding=new DbBuilding();
					dbBuilding.setBuildingId(buildingId);
					
					dbBuilding = buildingBusiness.findBuildingById(dbBuilding);		
					List storeylist = new ArrayList();
					if (null != dbBuilding){			
							Map tmp = new HashMap();
							for (int i = dbBuilding.getStoreyNumUp(); i>= dbBuilding.getStoreyNumDown()*-1; i--) {					
									// 封装楼层比例 storeylist
									HashMap<String, Object> m = drawBussiness.findStoreyArea(buildingId,i);
									m.put("name", i>0?"地上"+i:"地下"+(i*-1)); // 楼层名
									m.put("storey", i);
									storeylist.add(m);
							}
							
							if (storeylist.size() > 0) {
								//各楼层比例图
								request.removeAttribute("storeylist");
								request.setAttribute("storeylist", storeylist);
							}
					}
				
				//入口位置
				request.setAttribute("showOrhide", request.getParameter("showOrhide"));
				request.setAttribute("showtop", request.getParameter("showtop"));
				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			//调用抽取任意提醒需要的数据的方法
			remindBuildInfoDate();
			
			
			request.setAttribute("buildingId", buildingId);
			return "storeyuse";
	}
	
	/**
	 * 
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String showStoreyUseById() {
		request = ServletActionContext.getRequest();
		try{
		 buildingId=Integer.parseInt(request.getParameter("buildingId"));
		if (!SysUtil.isNull(buildingId.toString())) {
			//楼宇用途比例图
			HashMap<String, Object> bn = drawBussiness.findBuildArea(null,buildingId);
			request.setAttribute("buildinguse", bn);
			
			if(request.getSession().getAttribute("users")!=null){
				DbUsers user = (DbUsers) request.getSession().getAttribute("users");
				hospId = user.getDbHospInfo().getSeqHosp();
			}
			DbHospInfo hospInfo = new DbHospInfo();
			hospInfo.setSeqHosp(hospId);
			DbBuilding b=new DbBuilding();
			b.setDbHospInfo(hospInfo);
			RetCode rcc=buildingBusiness.findDBBuilding(b);
			if(rcc.getObj()!=null){
				listB=(List<DbBuilding>) rcc.getObj();
			}
			
			//用途面积
			Map yongtu= new HashMap();
			if (null != bn) {
				ArrayList list =(ArrayList)bn.get("obj");
				if (null != list) {
					for (int i = 0; i < list.size(); i++) {
						Map m =(Map)list.get(i);
						if (null == yongtu.get(m.get("content1"))) {
							yongtu.put(m.get("content1").toString(), m);
						}
					}
				}
			}
			request.setAttribute("yongtu", yongtu);
//			System.out.println("用途"+yongtu);
			
			DbBuilding dbBuilding=new DbBuilding();
			dbBuilding.setBuildingId(buildingId);
			
			dbBuilding = buildingBusiness.findBuildingById(dbBuilding);		
			List storeylist = new ArrayList();
			if (null != dbBuilding){			
					Map tmp = new HashMap();
					for (int i = dbBuilding.getStoreyNumUp(); i>= dbBuilding.getStoreyNumDown()*-1; i--) {					
							// 封装楼层比例 mylist
							HashMap<String, Object> m = drawBussiness.findStoreyArea(buildingId,i);
							m.put("name", i>0?"地上"+i:"地下"+(i*-1)); // 楼层名
							m.put("storey", i);
							storeylist.add(m);						
					}
					
					if (storeylist.size() > 0) {
						//各楼层比例图
						request.removeAttribute("storeylist");
						request.setAttribute("storeylist", storeylist);
					}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("buildingId", buildingId);
		return "storeyuse";
	}
	
	/**
	 * 医院楼宇饼形比例图
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String showHospitalUse() {
		request = ServletActionContext.getRequest();		
		if(request.getSession().getAttribute("users")!=null){
			DbUsers user = (DbUsers) request.getSession().getAttribute("users");
			hospId = user.getDbHospInfo().getSeqHosp();
		}
//		}	
		
			DbHospInfo hospInfo = new DbHospInfo();
	try{
		if (!SysUtil.isNullObject(hospId)) {
			if (!SysUtil.isNullObject(hospId)) {
				 hospInfo = hospBusiness.findHospInfoForId(hospId);
				 hospName = hospInfo.getHospName();	
			}
		}
	}catch (Exception e) {
		Log.error(e);
	}	
			clearImages();
			//比例图
			String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
			String useImage = imagePath + name+".png";	
			useImage=this.replacePath(useImage);
			drawBussiness.findScaleDrawing(hospId,buildingId, useImage);
			request.setAttribute("useImage", showPath +name+ ".png");
			
			//医院名称			
			if (hospInfo != null) {
				request.setAttribute("name", hospName);
			}
		
		return "buildingPro";
	}
	
	/**
	 * 清理图片
	 * @param statement  
	 * @param        
	 * @return
	 */
	private void clearImages() {
		request = ServletActionContext.getRequest();
			//删除上一次的文件
			try {
				String dirPath = request.getRealPath(imagePath).replaceAll("\\", "/");
				
				dirPath=this.replacePath(dirPath);
				File myfile = new File(dirPath);
	    		File[] files =myfile.listFiles();
	    		for (int i = 0; i < files.length; i++) {
	    			File f = files[i];
	    			if (f.getName().indexOf(("jfreechart")) != -1) {
						f.delete();
					}
	    		}
			} catch (Exception e) {
				// TODO: handle exception
			}			
			
			
			showPath=this.replacePath(imagePath);
			showPath = showPath+"jfreechart";
			imagePath = request.getRealPath(imagePath) +File.separator+"jfreechart";
	}
	
	/**
	 * 
	 * @param statement  
	 * @param        
	 * @return
	 */
	private static String replacePath(String rootPath) {
		if("\\".equals(File.separator)){   		 
			rootPath = rootPath.replace("/", "\\");
		}
		//linux下
		if("/".equals(File.separator)){   
			  rootPath = rootPath.replace("\\", "/");
		}
		return rootPath;
	}
	
	
	/**
	 * 将任意提醒的数据打包成map
	 * @param 
	 * @param
	 * @return
	 */
	public String remindBuildInfoDate() {

		request = ServletActionContext.getRequest();
		try {
			drawBussiness.findBuildArea(1,buildingId);
			//获取医院详情sql对象放到Map对象中
			sqlmap  = drawBussiness.getFindBuildAreaSql();
			
			logg.debug("***************sqlmap="+sqlmap);
			
			Map rcc=drawBussiness.findBuildArea(1,buildingId);
			
			seqMap.put("buildingId", rcc.get("area_id"));
			
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
			
			//将已经打包好的MAP转换成JSON
			JSONObject remindJson = JSONObject.fromObject(remindmapAll);
			
			request.setAttribute("remindJson", remindJson);
			logg.debug("    remindJson=   "+remindJson);
		}catch (Exception e) {
			logg.error("HospDetailAction-->showHospDetail", e);
		}
		return "show";
	}



	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HospInfoBusiness getHospBusiness() {
		return hospBusiness;
	}

	public void setHospBusiness(HospInfoBusiness hospBusiness) {
		this.hospBusiness = hospBusiness;
	}

	public ScaledrawingBusiness getDrawBussiness() {
		return drawBussiness;
	}

	public void setDrawBussiness(ScaledrawingBusiness drawBussiness) {
		this.drawBussiness = drawBussiness;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingid(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public BuildingBusiness getBuildingBusiness() {
		return buildingBusiness;
	}

	public void setBuildingBusiness(BuildingBusiness buildingBusiness) {
		this.buildingBusiness = buildingBusiness;
	}

	public List<DbBuilding> getListB() {
		return listB;
	}

	public void setListB(List<DbBuilding> listB) {
		this.listB = listB;
	}

	public Integer getHospId() {
		return hospId;
	}

	public void setHospId(Integer hospId) {
		this.hospId = hospId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getShowPath() {
		return showPath;
	}

	public void setShowPath(String showPath) {
		this.showPath = showPath;
	}

}
