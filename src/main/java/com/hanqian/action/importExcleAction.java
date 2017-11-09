package com.hanqian.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.hanqian.business.EquipTypeBusiness;
import com.hanqian.business.ImportExcleBusiness;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.DbEquipType;
import com.hanqian.pojo.EquipService;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.mysql.jdbc.Clob;
import com.mysql.jdbc.StringUtils;
import com.opensymphony.xwork2.ActionSupport;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class importExcleAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(importExcleAction.class);

	private HttpServletRequest request;
	private List<DbBuilding> listBuilding; // 楼宇信息列表
	private String imortExcleResult;
	private ImportExcleBusiness importExcleBusiness;
	private Page page;
	private Integer pageSize = 10;
	private Integer currentPage;
	private String  improtMessage;
	private MenuInterceptor menuInterceptor;  //权限过滤器
	public Map menuIdMap; //权限MAP集合


	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}

	public String getImprotMessage() {
		return improtMessage;
	}

	public void setImprotMessage(String improtMessage) {
		this.improtMessage = improtMessage;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/*
	 * fileupload,fileuploadFileName,fileuploadContentType命名规则，否则无法取值
	 * fileupload为客户端input上传组件的name,fileuploadFileName文件名称是fileupload+FileName组成
	 * ，必须这样 fileuploadContentType 文件类型是fileupload+ContentType组成，必须这样写
	 */
	private File fileupload;// 上传的文件
	private String fileuploadFileName;// 文件的名称 如上传的文件是a.png
										// 则fileuploadFileName值为"a.png"
	private String fileuploadContentType;// 文件的类型
											// 如上传的是png格式的图片，则fileuploadContentType值为"image/png"
	/*
	 * 指定上传文件在服务器上的保存目录，需要在Action中为定义savePath变量并为其添加相应的setter和getter方法
	 * 便于Struts2将struts.xml中的<param
	 * name="savePath">uploads/</param>值赋给savePath属性
	 */
	private String savePath;// 文件的保存位置

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String uploadFileFileName;
	private File uploadFile;
	private String fileName;

	public String getImortExcleResult() {
		return imortExcleResult;
	}

	public void setImortExcleResult(String imortExcleResult) {
		this.imortExcleResult = imortExcleResult;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<DbBuilding> getListBuilding() {
		return listBuilding;
	}

	public void setListBuilding(List<DbBuilding> listBuilding) {
		this.listBuilding = listBuilding;
	}

	public EquipTypeBusiness getEquipTypeBusiness() {
		return equipTypeBusiness;
	}

	public void setEquipTypeBusiness(EquipTypeBusiness equipTypeBusiness) {
		this.equipTypeBusiness = equipTypeBusiness;
	}

	public DbEquipType getDbEquipType() {
		return dbEquipType;
	}

	public void setDbEquipType(DbEquipType dbEquipType) {
		this.dbEquipType = dbEquipType;
	}

	public List<EquipService> getEquipServiceList() {
		return equipServiceList;
	}

	public void setEquipServiceList(List<EquipService> equipServiceList) {
		this.equipServiceList = equipServiceList;
	}

	private EquipTypeBusiness equipTypeBusiness; // 设备类型
	private DbEquipType dbEquipType; // 设备类型
	private List<EquipService> equipServiceList;// 设备服务设备
	private static final String targetpath = System.getProperty("catalina.base").toLowerCase() + File.separator + "temp"
			+ File.separator;

	/**
	 * 删除点位，更新设备
	 */

	public String deletePoint(){
		request = ServletActionContext.getRequest();
		String eqid = request.getParameter("eqid");
		String projectPoint = request.getParameter("projectPoint");
		importExcleBusiness.deletePoint(eqid,projectPoint);
		return "toImportPage";
	}

	/**
	 * 去导入页面
	 */
	public String toImportExcle() {
		request = ServletActionContext.getRequest();
		log.info("进入初始信息页面，开始获取表格信息。。");
		//权限配置
				menuIdMap=menuInterceptor.menuIntercept("5012");
				//若为空 ，进入登录界面
				if(menuIdMap==null){
					MenuInterceptor.toLoginjsp();
					return null;
				}
		if (currentPage == null) {
			currentPage = 1;
		}
		Map<String, String> map = new HashMap<String, String>();
	
		String buildingName = request.getParameter("buildingName");
		if (StringUtil.isEmpty(buildingName)) {
			map.put("buildingName", null);
		} else {
			map.put("buildingName", buildingName.trim());
			request.setAttribute("buildingName", buildingName.trim());
		}
		String equipName = request.getParameter("equipName");
		if (StringUtil.isEmpty(equipName)) {
			map.put("equipName", null);
		} else {
			map.put("equipName", equipName.trim());
			request.setAttribute("equipName", equipName.trim());
		}
		String nodeLevel = request.getParameter("nodeLevel");
		if (StringUtil.isEmpty(nodeLevel)) {
			map.put("nodeLevel", null);
		} else {
			map.put("nodeLevel", nodeLevel);
			request.setAttribute("nodeLevel", nodeLevel);
		}
		String equipTypeName = request.getParameter("equipTypeName");
		if (StringUtil.isEmpty(equipTypeName)) {
			map.put("equipTypeName", null);
		} else {
			map.put("equipTypeName", equipTypeName);
			request.setAttribute("equipTypeName", equipTypeName);
		}
		String equipTypeId = request.getParameter("equipTypeId");
		if (StringUtil.isEmpty(equipTypeId)) {
			map.put("equipTypeId", null);
		} else {
			map.put("equipTypeId", equipTypeId);
			request.setAttribute("equipTypeId", equipTypeId);
		}
		String pointName = request.getParameter("pointName");
		if (StringUtil.isEmpty(pointName)) {
			map.put("pointName", null);
		} else {
			map.put("pointName", pointName.trim());
			request.setAttribute("pointName", pointName.trim());
		}
		String pointCode = request.getParameter("pointCode");
		if (StringUtil.isEmpty(pointCode)) {
			map.put("pointCode", null);
		} else {
			map.put("pointCode", pointCode.trim());
			request.setAttribute("pointCode", pointCode.trim());
		}
		String controlFlag = request.getParameter("controlFlag");
		if (StringUtil.isEmpty(controlFlag)) {
			map.put("controlFlag", null);
		} else {
			map.put("controlFlag", controlFlag);
			request.setAttribute("controlFlag",controlFlag);
		}
		String alertLevel = request.getParameter("alertLevel");
		if (StringUtil.isEmpty(alertLevel)) {
			map.put("alertLevel", null);
		} else {
			map.put("alertLevel", alertLevel);
			request.setAttribute("alertLevel",alertLevel);
		}
		log.info("查询条件：" + map);
		RetCode rc = importExcleBusiness.findPointList(map, currentPage, pageSize);
		if (rc.getObj() != null) {
			List<Map<String, String>> pointList = new ArrayList<>();
			pointList = (List<Map<String, String>>) rc.getObj();
			if (pointList.size() > 0) {
				for (int a = 0; a < pointList.size(); a++) {
					Object eqTypeId = pointList.get(a).get("EQUIP_TYPE_ID");
					if (!StringUtil.isEmpty(eqTypeId)) {
						String eqTypeName = importExcleBusiness.getEqTypeByID(eqTypeId.toString());
						pointList.get(a).put("eqTypeName", eqTypeName);
					} else {
						pointList.get(a).put("eqTypeName", "");
					}
					Object nodeParent = pointList.get(a).get("NODE_PARENT");
					if (!StringUtil.isEmpty(nodeParent)) {
						String eqpName = importExcleBusiness.getEqNameByID(nodeParent.toString());
						pointList.get(a).put("eqpName", eqpName);
					}
				}
			}
			page = rc.getPage();
			request.setAttribute("pointList", pointList);
		} else {
			page = new Page(1, 0, 10);
		}
		if (rc == null || rc.getObj() == null) {
			request.setAttribute("mesg", "暂无数据！");
		}
		return SUCCESS;
	}

	public Map getMenuIdMap() {
		return menuIdMap;
	}

	public void setMenuIdMap(Map menuIdMap) {
		this.menuIdMap = menuIdMap;
	}

	// 导出EXCLE
	@SuppressWarnings("unchecked")
	public void outPutExcle() throws BiffException, IOException {
		request = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		// 获取医院信息
		List<Map<String, String>> hospInfoList = new ArrayList<Map<String, String>>();
		hospInfoList = importExcleBusiness.getHospInfo("1");
		String hospName = "医院_1.5基础数据_";
		if (hospInfoList.size() > 0) {
			Object Object1 = hospInfoList.get(0).get("short_name");
			if(Object1 !=null){
				hospName = Object1.toString() + "_1.5基础数据_";
			}
		}
		String directory = "/WEB-INF/downLoadExcle/Template.xls";
		// 获取文件的路径
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
		File oldFile = new File(targetDirectory);
		String uploadFileFileName = directory;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		// 文件名
		uploadFileFileName = hospName + format.format(new Date()).toString()
				+ uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."));
		File newFile = new File(targetpath + uploadFileFileName);
		log.info("newFile路径信息：" + targetpath + uploadFileFileName);
		try {
			FileUtils.copyFile(oldFile, newFile);
			log.info("copyFile:复制" + targetDirectory + "到" + newFile);
		} catch (IOException e) {
			log.error("copyFile:复制" + targetDirectory + "到" + newFile + "，失败，初始信息导出失败！", e);
			e.printStackTrace();
		}
		// 设置表头，导出文件名称编码
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/octet-stream");
		String fileName_1 = new String(uploadFileFileName.getBytes("UTF-8"), "iso8859-1");
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName_1);
		OutputStream os = res.getOutputStream();
		Workbook workbook = null;
		InputStream ins = new FileInputStream(newFile);
		workbook = Workbook.getWorkbook(ins);
		WorkbookSettings ws = new WorkbookSettings();
		Locale locale = new Locale("zh", "CN");
		ws.setLocale(locale);
		ws.setEncoding("utf-8");
		// 下面这句代码是关键，将workbook写入os流中
		WritableWorkbook wbook = Workbook.createWorkbook(os, workbook, ws);
		WritableSheet hospSheet = wbook.getSheet("医院信息");
		WritableSheet writeSheet = wbook.getSheet("大表");// sheet名称
		WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 11);
		jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#"); // 设置数字格式
		jxl.write.NumberFormat dob = new jxl.write.NumberFormat("#.##"); // 设置数字格式
		jxl.write.WritableCellFormat formatInt = new jxl.write.WritableCellFormat(NormalFont, nf); // 设置表单格式
		jxl.write.WritableCellFormat formatDouble = new jxl.write.WritableCellFormat(NormalFont, dob); // 设置表单格式
		DateFormat df = new jxl.write.DateFormat("yyyy/MM/dd");
		WritableCellFormat formatDate = new WritableCellFormat(NormalFont, df);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		try {
			// 先导出医院信息
			hospSheet.addCell(new Label(0, 1, "1"));
			if (hospInfoList.size() > 0) {// }00
				Object Object1 = hospInfoList.get(0).get("HOSP_CODE");
				if (!StringUtil.isEmpty(Object1)) {
					hospSheet.addCell(new Label(1, 1, Object1.toString()));
				}
				Object HOSPLEVEL = hospInfoList.get(0).get("HOSPLEVEL");
				if (!StringUtil.isEmpty(HOSPLEVEL)) {
					hospSheet.addCell(new Label(2, 1, HOSPLEVEL.toString()));
				}
				Object KIND = hospInfoList.get(0).get("KIND");
				if (!StringUtil.isEmpty(KIND)) {
					hospSheet.addCell(new Label(3, 1, KIND.toString()));
				}
				Object HOSP_NAME = hospInfoList.get(0).get("HOSP_NAME");
				if (!StringUtil.isEmpty(HOSP_NAME)) {
					hospSheet.addCell(new Label(4, 1, HOSP_NAME.toString()));
				}
				Object SHORT_NAME = hospInfoList.get(0).get("SHORT_NAME");
				if (!StringUtil.isEmpty(SHORT_NAME)) {
					hospSheet.addCell(new Label(5, 1, SHORT_NAME.toString()));
				}
				Object ZIPCODE = hospInfoList.get(0).get("ZIPCODE");
				if (!StringUtil.isEmpty(ZIPCODE)) {
					hospSheet.addCell(new Label(6, 1, ZIPCODE.toString()));
				}
				Object ADDRESS = hospInfoList.get(0).get("ADDRESS");
				if (!StringUtil.isEmpty(ADDRESS)) {
					hospSheet.addCell(new Label(7, 1, ADDRESS.toString()));
				}
				Object PRINCIPAL = hospInfoList.get(0).get("PRINCIPAL");
				if (!StringUtil.isEmpty(PRINCIPAL)) {
					hospSheet.addCell(new Label(8, 1, PRINCIPAL.toString()));
				}
				Object HOSPDIST = hospInfoList.get(0).get("HOSPDIST");
				if (!StringUtil.isEmpty(HOSPDIST)) {
					hospSheet.addCell(new Label(9, 1, HOSPDIST.toString()));
				}
				Object BUILDTIME = hospInfoList.get(0).get("BUILDTIME");
				if (!StringUtil.isEmpty(BUILDTIME)) {
					String data1 = BUILDTIME.toString();
					try {
						writeSheet.addCell(new jxl.write.DateTime(10, 1, sdf.parse(data1), formatDate));
					} catch (ParseException e) {

						e.printStackTrace();
					}
				}
				Object LAND_AREAS = hospInfoList.get(0).get("LAND_AREAS");
				if (!StringUtil.isEmpty(LAND_AREAS)) {
					hospSheet.addCell(new Label(11, 1, LAND_AREAS.toString()));
				}
				Object BUILD_AREAS = hospInfoList.get(0).get("BUILD_AREAS");
				if (!StringUtil.isEmpty(BUILD_AREAS)) {
					hospSheet.addCell(new Label(12, 1, BUILD_AREAS.toString()));
				}

				Object TEL1 = hospInfoList.get(0).get("TEL1");
				if (!StringUtil.isEmpty(TEL1)) {
					hospSheet.addCell(new Label(13, 1, TEL1.toString()));
				}

				Object TEL2 = hospInfoList.get(0).get("TEL2");
				if (!StringUtil.isEmpty(TEL2)) {
					hospSheet.addCell(new Label(14, 1, TEL2.toString()));
				}
				Object TEL3 = hospInfoList.get(0).get("TEL3");
				if (!StringUtil.isEmpty(TEL3)) {
					hospSheet.addCell(new Label(15, 1, TEL3.toString()));
				}
				Object FAX1 = hospInfoList.get(0).get("FAX1");
				if (!StringUtil.isEmpty(FAX1)) {
					hospSheet.addCell(new Label(16, 1, FAX1.toString()));
				}
				Object FAX2 = hospInfoList.get(0).get("FAX2");
				if (!StringUtil.isEmpty(FAX2)) {
					hospSheet.addCell(new Label(17, 1, FAX2.toString()));
				}
				Object FULL_VIEW = hospInfoList.get(0).get("FULL_VIEW");
				if (!StringUtil.isEmpty(FULL_VIEW)) {
					hospSheet.addCell(new Label(18, 1, FULL_VIEW.toString()));
				}
				Object IMAGES1 = hospInfoList.get(0).get("IMAGES1");
				if (!StringUtil.isEmpty(IMAGES1)) {
					hospSheet.addCell(new Label(19, 1, IMAGES1.toString()));
				}
				Object IMAGES2 = hospInfoList.get(0).get("IMAGES2");
				if (!StringUtil.isEmpty(IMAGES2)) {
					hospSheet.addCell(new Label(20, 1, IMAGES2.toString()));
				}
				Object IMAGES3 = hospInfoList.get(0).get("IMAGES3");
				if (!StringUtil.isEmpty(IMAGES3)) {
					hospSheet.addCell(new Label(21, 1, IMAGES3.toString()));
				}
				Object IMAGES4 = hospInfoList.get(0).get("IMAGES4");
				if (!StringUtil.isEmpty(IMAGES4)) {
					hospSheet.addCell(new Label(22, 1, IMAGES4.toString()));
				}
				Object HOSP_PARENT = hospInfoList.get(0).get("HOSP_PARENT");
				if (!StringUtil.isEmpty(HOSP_PARENT)) {
					hospSheet.addCell(new Label(23, 1, HOSP_PARENT.toString()));
				}
				Object STATUS = hospInfoList.get(0).get("STATUS");
				if (!StringUtil.isEmpty(STATUS)) {
					hospSheet.addCell(new Label(24, 1, STATUS.toString()));
				}
				Object INPUT = hospInfoList.get(0).get("INPUT");
				if (!StringUtil.isEmpty(INPUT)) {
					hospSheet.addCell(new Label(25, 1, INPUT.toString()));
				}
				Object INPUTTIME = hospInfoList.get(0).get("INPUTTIME");
				if (!StringUtil.isEmpty(INPUTTIME)) {
					String data1 = INPUTTIME.toString();
					try {
						writeSheet.addCell(new jxl.write.DateTime(26, 1, sdf.parse(data1), formatDate));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				Object OPER = hospInfoList.get(0).get("OPER");
				if (!StringUtil.isEmpty(OPER)) {
					hospSheet.addCell(new Label(27, 1, OPER.toString()));
				}

				Object OPERTIME = hospInfoList.get(0).get("OPERTIME");
				if (!StringUtil.isEmpty(OPERTIME)) {
					String data1 = OPERTIME.toString();
					try {
						writeSheet.addCell(new jxl.write.DateTime(28, 1, sdf.parse(data1), formatDate));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				Object HOSP_INFO = hospInfoList.get(0).get("HOSP_INFO");
				if (!StringUtil.isEmpty(HOSP_INFO)) {
					hospSheet.addCell(new Label(29, 1, HOSP_INFO.toString()));
				}
			}
			log.info("导出数据前先删除EQUIP_SERVICE中可能存在的重复数据");
			importExcleBusiness.deleteEquipService();
			log.info("EQUIP_SERVICE去重复完毕");
			log.info("获取设备名称与ID集合");
			List<Map<String, String>> equipNameIdList = new ArrayList<Map<String, String>>();
			equipNameIdList = importExcleBusiness.getEquipNameIdList();
			Map<String, String> eqNameIdMap = new HashMap<>();
			if (equipNameIdList.size() > 0) {
				for (int i = 0; i < equipNameIdList.size(); i++) {
					Object eqName = equipNameIdList.get(i).get("EQUIP_NAME");
					String EQUIP_NAME = eqName.toString();
					Object eqID = equipNameIdList.get(i).get("EQUIP_ID");
					String EQUIP_ID = eqID.toString();
					eqNameIdMap.put(EQUIP_ID, EQUIP_NAME);
				}
			}

			List<Map<String, String>> excleList = new ArrayList<Map<String, String>>();
			excleList = importExcleBusiness.getAllData();
			if (excleList.size() > 0) {
				log.info("预计导出点位数：" + excleList.size());
				for (int i = 0; i < excleList.size(); i++) {
					// 建筑开始
					Object bid = excleList.get(i).get("BUILDING_ID");
					String bid1 = bid.toString();
					double BUILDING_ID = Double.parseDouble(bid1);
					writeSheet.addCell(new jxl.write.Number(0, i + 1, BUILDING_ID, formatInt));
					Object obj2 = excleList.get(i).get("building_en_code");
					if (!StringUtil.isEmpty(obj2)) {
						writeSheet.addCell(new Label(1, i + 1, obj2.toString()));
					}
					Object obj3 = excleList.get(i).get("building_name");
					writeSheet.addCell(new Label(2, i + 1, obj3.toString()));
					Object obj4 = excleList.get(i).get("his_name");
					if (!StringUtil.isEmpty(obj4)) {
						writeSheet.addCell(new Label(3, i + 1, obj4.toString()));
					}
					Object obj5 = excleList.get(i).get("building_areas");
					writeSheet.addCell(new Label(5, i + 1, obj5.toString()));
					Object obj6 = excleList.get(i).get("building_code");
					if (!StringUtil.isEmpty(obj6)) {
						writeSheet.addCell(new Label(4, i + 1, obj6.toString()));
					}
					Object cell7 = excleList.get(i).get("amount");
					if (!StringUtil.isEmpty(cell7)) {
						writeSheet.addCell(new Label(6, i + 1, cell7.toString()));
					}
					Object completime = excleList.get(i).get("completime");
					if (!StringUtil.isEmpty(completime)) {
						String completime1 = completime.toString();
						double completime11 = Double.parseDouble(completime1);
						writeSheet.addCell(new jxl.write.Number(7, i + 1, completime11, formatInt));
					}

					Object storey_num_down = excleList.get(i).get("storey_num_down");
					if (!StringUtil.isEmpty(storey_num_down)) {
						writeSheet.addCell(new Label(8, i + 1, storey_num_down.toString()));
					}
					Object storey_num_up = excleList.get(i).get("storey_num_up");
					if (!StringUtil.isEmpty(storey_num_up)) {
						double storeyUp = Double.parseDouble(storey_num_up.toString());
						writeSheet.addCell(new jxl.write.Number(9, i + 1, storeyUp, formatInt));
					}
					Object mend_num = excleList.get(i).get("mend_num");
					if (!StringUtil.isEmpty(mend_num)) {
						double mend_num1 = Double.parseDouble(mend_num.toString());
						writeSheet.addCell(new jxl.write.Number(10, i + 1, mend_num1, formatInt));
					}
					Object structure = excleList.get(i).get("structure");
					if (!StringUtil.isEmpty(structure)) {
						double structure1 = Double.parseDouble(structure.toString());
						writeSheet.addCell(new jxl.write.Number(11, i + 1, structure1, formatInt));
					}
					Object height = excleList.get(i).get("height");
					if (!StringUtil.isEmpty(height)) {
						writeSheet.addCell(new Label(12, i + 1, height.toString()));
					}
					Object out_wall = excleList.get(i).get("out_wall");
					if (!StringUtil.isEmpty(out_wall)) {
						String outwall = out_wall.toString();
						writeSheet.addCell(new Label(13, i + 1, outwall));
					}
					Object audits = excleList.get(i).get("audits");
					if (!StringUtil.isEmpty(audits)) {
						String audits1 = audits.toString();
						writeSheet.addCell(new Label(14, i + 1, audits1));
					}
					Object pro_cost = excleList.get(i).get("pro_cost");
					if (!StringUtil.isEmpty(pro_cost)) {
						String pro_cost1 = pro_cost.toString();
						writeSheet.addCell(new Label(15, i + 1, pro_cost1));
					}
					Object door_mater = excleList.get(i).get("door_mater");
					if (!StringUtil.isEmpty(door_mater)) {
						String door_mater1 = door_mater.toString();
						writeSheet.addCell(new Label(16, i + 1, door_mater1));
					}
					Object window_mater = excleList.get(i).get("window_mater");
					if (!StringUtil.isEmpty(window_mater)) {
						String window_mater1 = window_mater.toString();
						writeSheet.addCell(new Label(17, i + 1, window_mater1));
					}
					Object ceiling_mater = excleList.get(i).get("ceiling_mater");
					if (!StringUtil.isEmpty(ceiling_mater)) {
						String ceiling_mater1 = ceiling_mater.toString();
						writeSheet.addCell(new Label(18, i + 1, ceiling_mater1));
					}
					Object wall_mater = excleList.get(i).get("wall_mater");
					if (!StringUtil.isEmpty(wall_mater)) {
						String wall_mater1 = wall_mater.toString();
						writeSheet.addCell(new Label(19, i + 1, wall_mater1));
					}
					Object floor_mater = excleList.get(i).get("floor_mater");
					if (!StringUtil.isEmpty(floor_mater)) {
						String floor_mater1 = floor_mater.toString();
						writeSheet.addCell(new Label(20, i + 1, floor_mater1));
					}
					Object place = excleList.get(i).get("place");
					if (!StringUtil.isEmpty(place)) {
						String place1 = place.toString();
						writeSheet.addCell(new Label(21, i + 1, place1));
					}
					Object waterproof = excleList.get(i).get("waterproof");
					if (!StringUtil.isEmpty(waterproof)) {
						String waterproof1 = waterproof.toString();
						writeSheet.addCell(new Label(22, i + 1, waterproof1));
					}
					Object quakeproof = excleList.get(i).get("quakeproof");
					if (!StringUtil.isEmpty(quakeproof)) {
						String quakeproof1 = quakeproof.toString();
						writeSheet.addCell(new Label(23, i + 1, quakeproof1));
					}
					Object costaccord = excleList.get(i).get("costaccord");
					if (!StringUtil.isEmpty(costaccord)) {
						String costaccord1 = costaccord.toString();
						writeSheet.addCell(new Label(24, i + 1, costaccord1));
					}
					Object problem = excleList.get(i).get("problem");
					if (!StringUtil.isEmpty(problem)) {
						String problem1 = problem.toString();
						writeSheet.addCell(new Label(25, i + 1, problem1));
					}
					Object usetype = excleList.get(i).get("usetype");
					if (!StringUtil.isEmpty(usetype)) {
						String usetype1 = usetype.toString();
						writeSheet.addCell(new Label(26, i + 1, usetype1));
					}
					Object ower = excleList.get(i).get("ower");
					if (!StringUtil.isEmpty(ower)) {
						String ower1 = ower.toString();
						writeSheet.addCell(new Label(27, i + 1, ower1));
					}
					Object buildstatus = excleList.get(i).get("buildstatus");
					if (!StringUtil.isEmpty(buildstatus)) {
						String buildstatus1 = buildstatus.toString();
						writeSheet.addCell(new Label(28, i + 1, buildstatus1));
					}
					// 建筑结束，设备开始
					Object eqid = excleList.get(i).get("equip_id");
					if (!StringUtil.isEmpty(eqid)) {
						String eqid1 = eqid.toString();
						writeSheet.addCell(new Label(29, i + 1, eqid1));
					}
					Object equip_code = excleList.get(i).get("equip_code");
					if (!StringUtil.isEmpty(equip_code)) {
						String equip_code1 = equip_code.toString();
						writeSheet.addCell(new Label(30, i + 1, equip_code1));
					}
					Object equip_type_id = excleList.get(i).get("equip_type_id");
					if (!StringUtil.isEmpty(equip_type_id)) {
						String equip_type_id1 = equip_type_id.toString();
						writeSheet.addCell(new Label(31, i + 1, equip_type_id1));
					}
					Object equip_name = excleList.get(i).get("equip_name");
					if (!StringUtil.isEmpty(equip_name)) {
						String equip_name1 = equip_name.toString();
						writeSheet.addCell(new Label(32, i + 1, equip_name1));
					}
					Object assetscode = excleList.get(i).get("assetscode");
					if (!StringUtil.isEmpty(assetscode)) {
						String assetscode1 = assetscode.toString();
						writeSheet.addCell(new Label(33, i + 1, assetscode1));
					}
					Object storey = excleList.get(i).get("storey");
					if (!StringUtil.isEmpty(storey)) {
						String storey1 = storey.toString();
						writeSheet.addCell(new Label(34, i + 1, storey1));
					}
					Object place1 = excleList.get(i).get("place");
					if (!StringUtil.isEmpty(place1)) {
						String place11 = place1.toString();
						writeSheet.addCell(new Label(35, i + 1, place11));
					}
					Object control_flag = excleList.get(i).get("control_flag");
					if (!StringUtil.isEmpty(control_flag)) {
						String control_flag1 = control_flag.toString();
						writeSheet.addCell(new Label(36, i + 1, control_flag1));
					}
					Object brand = excleList.get(i).get("brand");
					if (!StringUtil.isEmpty(brand)) {
						String brand1 = brand.toString();
						writeSheet.addCell(new Label(37, i + 1, brand1));
					}
					Object unit_type = excleList.get(i).get("unit_type");
					if (!StringUtil.isEmpty(unit_type)) {
						String unittype = unit_type.toString();
						writeSheet.addCell(new Label(38, i + 1, unittype));
					}
					Object production = excleList.get(i).get("production");
					if (!StringUtil.isEmpty(production)) {
						String production1 = production.toString();
						writeSheet.addCell(new Label(39, i + 1, production1));
					}
					Object field = excleList.get(i).get("field");
					if (!StringUtil.isEmpty(field)) {
						String field1 = field.toString();
						writeSheet.addCell(new Label(40, i + 1, field1));
					}
					try {
						Object production_date = excleList.get(i).get("productiondate");
						if (!StringUtil.isEmpty(production_date)) {
							String data1 = production_date.toString();
							writeSheet.addCell(new jxl.write.DateTime(41, i + 1, sdf.parse(data1), formatDate));
						}
						Object install_date = excleList.get(i).get("installdate");
						if (!StringUtil.isEmpty(install_date)) {
							String install_date1 = install_date.toString();
							Date data = sdf.parse(install_date1);
							writeSheet.addCell(new jxl.write.DateTime(42, i + 1, data, formatDate));
						}
						Object use_date = excleList.get(i).get("usedate");
						if (!StringUtil.isEmpty(use_date)) {
							String use_date1 = use_date.toString();
							writeSheet.addCell(new jxl.write.DateTime(43, i + 1, sdf.parse(use_date1), formatDate));
						}
					} catch (ParseException e) {
						log.error("导出EXCLE时，日期转换错误！", e);
						e.printStackTrace();
					}
					Object service_life = excleList.get(i).get("service_life");
					if (!StringUtil.isEmpty(service_life)) {
						String service_life1 = service_life.toString();
						writeSheet.addCell(new Label(44, i + 1, service_life1));
					}
					Object imited = excleList.get(i).get("imited");
					if (!StringUtil.isEmpty(imited)) {
						String imited1 = imited.toString();
						writeSheet.addCell(new Label(45, i + 1, imited1));
					}
					Object purchase = excleList.get(i).get("purchase");
					if (!StringUtil.isEmpty(purchase)) {
						writeSheet.addCell(new Label(46, i + 1, purchase.toString()));
					}
					Object service_cycle = excleList.get(i).get("service_cycle");
					if (!StringUtil.isEmpty(service_cycle)) {
						String service_cycle1 = service_cycle.toString();
						writeSheet.addCell(new Label(47, i + 1, service_cycle1));
					}
					Object accessory = excleList.get(i).get("accessory");
					if (!StringUtil.isEmpty(accessory)) {
						String accessory1 = accessory.toString();
						writeSheet.addCell(new Label(48, i + 1, accessory1));
					}
					Object power_type = excleList.get(i).get("power_type");
					if (!StringUtil.isEmpty(power_type)) {
						String power_type1 = power_type.toString();
						writeSheet.addCell(new Label(49, i + 1, power_type1));
					}
					Object node_level = excleList.get(i).get("node_level");
					if (!StringUtil.isEmpty(node_level)) {
						String node_level1 = node_level.toString();
						writeSheet.addCell(new Label(50, i + 1, node_level1));
					}
					Object node_parent = excleList.get(i).get("node_parent");
					if (!StringUtil.isEmpty(node_parent)) {
						String node_parent1 = node_parent.toString();
						String node_pare = (String) eqNameIdMap.get(node_parent1);
						writeSheet.addCell(new Label(51, i + 1, node_pare));
					}
					Object node_top = excleList.get(i).get("node_top");
					if (!StringUtil.isEmpty(node_top)) {
						String node_top1 = node_top.toString();
						String nodeTop1 = (String) eqNameIdMap.get(node_top1);
						writeSheet.addCell(new Label(52, i + 1, nodeTop1));
					}
					// 设备完毕，服务开始
					Object seq = excleList.get(i).get("seq");
					if (!StringUtil.isEmpty(seq)) {
						String seq1 = seq.toString();
						writeSheet.addCell(new Label(53, i + 1, seq1));
					}
					Object comments = excleList.get(i).get("comments");
					if (!StringUtil.isEmpty(comments)) {
						String comments1 = comments.toString();
						writeSheet.addCell(new Label(54, i + 1, comments1));
					}
					Object energy_id = excleList.get(i).get("energy_id");
					if (!StringUtil.isEmpty(energy_id)) {
						String energy_id1 = energy_id.toString();
						writeSheet.addCell(new Label(55, i + 1, energy_id1));
					}
					// 服务结束，设备分组开始
					Object seqg = excleList.get(i).get("seqg");
					if (!StringUtil.isEmpty(seqg)) {
						String seqg1 = seqg.toString();
						writeSheet.addCell(new Label(56, i + 1, seqg1));
					}
					Object GROUPCODE = excleList.get(i).get("groupcode");
					if (!StringUtil.isEmpty(GROUPCODE)) {
						String GROUPCODE1 = GROUPCODE.toString();
						writeSheet.addCell(new Label(57, i + 1, GROUPCODE1));
					}
					Object GROUP_NAME = excleList.get(i).get("group_name");
					if (!StringUtil.isEmpty(GROUP_NAME)) {
						String GROUP_NAME1 = GROUP_NAME.toString();
						writeSheet.addCell(new Label(58, i + 1, GROUP_NAME1));
					}
					Object EQUIP_TYPE = excleList.get(i).get("equip_type");
					if (!StringUtil.isEmpty(EQUIP_TYPE)) {
						String EQUIP_TYPE1 = EQUIP_TYPE.toString();
						writeSheet.addCell(new Label(59, i + 1, EQUIP_TYPE1));
					}
					Object STOREYG = excleList.get(i).get("storeyg");
					if (!StringUtil.isEmpty(STOREYG)) {
						double STOREYG1 = Double.parseDouble(STOREYG.toString());
						writeSheet.addCell(new jxl.write.Number(60, i + 1, STOREYG1, formatInt));
					}
					// 设备分组完毕，采集开始
					Object PROJECT_POINT = excleList.get(i).get("PROJECT_POINT");
					String ppTtpe = null;
					if (!StringUtil.isEmpty(PROJECT_POINT)) {
						String PROJECT_POINT1 = PROJECT_POINT.toString();
						writeSheet.addCell(new Label(61, i + 1, PROJECT_POINT1));
						String a[] = PROJECT_POINT1.split("\\.");
						String p11 = a[1];
						String b[] = p11.split("_");
						ppTtpe = b[0];
					}
					Object EQUIP_TYPE_ID = excleList.get(i).get("EQUIP_TYPE_ID");
					if (!StringUtil.isEmpty(EQUIP_TYPE_ID)) {
						String EQUIP_TYPE_ID1 = EQUIP_TYPE_ID.toString();
						if (EQUIP_TYPE_ID1.equals("10001")) {
							EQUIP_TYPE_ID1 = "E_" + ppTtpe;
						} else if (EQUIP_TYPE_ID1.equals("10002")) {
							EQUIP_TYPE_ID1 = "W_" + ppTtpe;
						} else {
							EQUIP_TYPE_ID1 = EQUIP_TYPE_ID1 + "_" + ppTtpe;
						}
						writeSheet.addCell(new Label(62, i + 1, EQUIP_TYPE_ID1));
					}

					Object POINT_DESC = excleList.get(i).get("DESCR");
					if (!StringUtil.isEmpty(POINT_DESC)) {
						String POINT_DESC1 = POINT_DESC.toString();
						writeSheet.addCell(new Label(63, i + 1, POINT_DESC1));
					}
					Object CONTRPOINT = excleList.get(i).get("CONTROLPOINT");
					if (!StringUtil.isEmpty(CONTRPOINT)) {
				    	String CONTRPOINT1 = CONTRPOINT.toString();
						writeSheet.addCell(new Label(64, i + 1, CONTRPOINT1));
					}

				}
			}
			wbook.write(); // 写入文件
			wbook.close();
			workbook.close();
			ins.close();
			log.info("导出完成，共：" + excleList.size()+"个点位！");
		} catch (WriteException e) {
			log.error("EXCLE导出失败！");
			e.printStackTrace();
		}
	}

	// 导入excle
	public String uploadFile() throws Exception {
		request = ServletActionContext.getRequest();
		String absolutePath = ServletActionContext.getServletContext().getRealPath(""); // 获取项目根路径
		// 文件路径
		String path = absolutePath + "/" + this.savePath + "/";
		// 创建路径，如果目录不存在，则创建
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 文件路径+文件名
		path += this.getFileuploadFileName();
		// 1.构建输入流
		FileInputStream fis = new FileInputStream(fileupload);
		// 2.构建输出流
		FileOutputStream fos = new FileOutputStream(path);
		// 3.通过字节写入输出流
		try {
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) > 0) {
				fos.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fis.close();
			fos.close();
		}
		File diskFile = new File(path);
		improtMessage = "导入失败！";
		String msg1 = importExcleBusiness.excleImport(diskFile);
		if (!StringUtils.isNullOrEmpty(msg1)) {
			improtMessage = msg1;
		}
		deleteFile(path);
		getTableList();
		request.setAttribute("improtMessage", improtMessage);
		return "success";
	}
	
	//获取表格数据
	public void getTableList(){
		Map map=new HashMap<>();
		log.info("查询条件：" + map);
		RetCode rc = importExcleBusiness.findPointList(map, currentPage, pageSize);
		if (rc.getObj() != null) {
			List<Map<String, String>> pointList = new ArrayList<>();
			pointList = (List<Map<String, String>>) rc.getObj();
			if (pointList.size() > 0) {
				for (int a = 0; a < pointList.size(); a++) {
					Object eqTypeId = pointList.get(a).get("EQUIP_TYPE_ID");
					if (!StringUtil.isEmpty(eqTypeId)) {
						String eqTypeName = importExcleBusiness.getEqTypeByID(eqTypeId.toString());
						pointList.get(a).put("eqTypeName", eqTypeName);
					} else {
						pointList.get(a).put("eqTypeName", "");
					}
					Object nodeParent = pointList.get(a).get("NODE_PARENT");
					if (!StringUtil.isEmpty(nodeParent)) {
						String eqpName = importExcleBusiness.getEqNameByID(nodeParent.toString());
						pointList.get(a).put("eqpName", eqpName);
					}
				}
			}
			page = rc.getPage();
			request.setAttribute("pointList", pointList);
		} else {
			page = new Page(1, 0, 10);
		}
		if (rc == null || rc.getObj() == null) {
			request.setAttribute("mesg", "暂无数据！");
		}
	}

	// 删除文件
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public ImportExcleBusiness getImportExcleBusiness() {
		return importExcleBusiness;
	}

	public void setImportExcleBusiness(ImportExcleBusiness importExcleBusiness) {
		this.importExcleBusiness = importExcleBusiness;
	}

	@JSON(serialize = false)
	public File getFileupload() {
		return fileupload;
	}

	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}

	@JSON(serialize = false)
	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(String fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public String getFileuploadContentType() {
		return fileuploadContentType;
	}

	public void setFileuploadContentType(String fileuploadContentType) {
		this.fileuploadContentType = fileuploadContentType;
	}
	// 导出SDCD，暂时废弃不用
	/*
	 * public void outPutSDCDExcle() throws BiffException, IOException { request
	 * = ServletActionContext.getRequest(); HttpServletResponse res =
	 * ServletActionContext.getResponse(); List<Map<String, String>>
	 * hospInfoList = new ArrayList<Map<String, String>>(); hospInfoList =
	 * importExcleBusiness.getHospInfo("1"); String hospName = "SDCD导出表_"; if
	 * (hospInfoList.size() > 0) { Object Object1 =
	 * hospInfoList.get(0).get("short_name"); hospName = Object1.toString() +
	 * "_SDCD导出表_"; } String directory =
	 * "/WEB-INF/downLoadExcle/SDCDTemplate.xls"; String targetDirectory =
	 * ServletActionContext.getServletContext().getRealPath(directory); File
	 * oldFile = new File(targetDirectory); String uploadFileFileName =
	 * directory; SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); //
	 * 文件名 uploadFileFileName = hospName + format.format(new Date()).toString()
	 * + uploadFileFileName.substring(uploadFileFileName.lastIndexOf(".")); File
	 * newFile = new File(targetpath + uploadFileFileName); try {
	 * FileUtils.copyFile(oldFile, newFile); log.info("copyFile:复制" +
	 * targetDirectory + "到" + targetpath);
	 * importExcleBusiness.outPutSDCDExcle(request, res, newFile,
	 * uploadFileFileName); } catch (IOException e) { log.error("copyFile:复制" +
	 * targetDirectory + "到" + targetpath + "出错，SDCD导出失败！", e);
	 * e.printStackTrace(); } }
	 */
}
