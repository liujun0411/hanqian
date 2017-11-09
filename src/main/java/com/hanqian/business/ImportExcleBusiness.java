package com.hanqian.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;

import bsh.ParseException;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Service
public class ImportExcleBusiness extends BaseBusiness {
	public static final Logger logg = Logger.getLogger(ImportExcleBusiness.class);

	/**
	 * 获取初始信息列表
	 */
	public RetCode findPointList(Map<String, String> map, Integer currentPage, Integer pageSize) {
		RetCode rc = this.getPageData("selectPointList", map, currentPage, pageSize);
		return rc;
	}

	/**
	 * 导入初始信息
	 */
	@SuppressWarnings("unchecked")
	public String excleImport(File file) throws ParseException {
		String msg = "导入成功！";
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(file);
			String[] sheetNames = book.getSheetNames();
			boolean ifCan = true;
			int checkExcle = 0;
			for (int i = 0; i < sheetNames.length; i++) {
				String sheetName = sheetNames[i];
				if (sheetName.equals("大表")) {
					checkExcle = checkExcle + 1;
				}
				if (sheetName.equals("医院信息")) {
					Sheet hospSheet = book.getSheet("医院信息");
					int columns = hospSheet.getColumns();
					Map hospMap = new HashMap();
					// for (int e = 0; e < columns; e++) {
					String SEQ_HOSP = hospSheet.getCell(0, 1).getContents();
					if (!StringUtil.isEmpty(SEQ_HOSP)) {
						hospMap.put("SEQ_HOSP", SEQ_HOSP);
					} else {
						hospMap.put("SEQ_HOSP", "1");
						/*
						 * ifCan = false; msg = "医院信息,第" + 2 +
						 * "行，SEQ_HOSP不可为空！"; break;
						 */
					}
					String HOSP_CODE = hospSheet.getCell(1, 1).getContents();
					hospMap.put("HOSP_CODE", HOSP_CODE);
					String HOSPLEVEL = hospSheet.getCell(2, 1).getContents();
					String KIND = hospSheet.getCell(3, 1).getContents();
					String HOSP_NAME = hospSheet.getCell(4, 1).getContents();
					if (!StringUtil.isEmpty(HOSP_NAME)) {
						hospMap.put("HOSP_NAME", HOSP_NAME);
					} else {
						ifCan = false;
						msg = "医院信息,第" + 2 + "行，HOSP_NAME不可为空！";
						break;
					}
					String SHORT_NAME = hospSheet.getCell(5, 1).getContents();
					if (!StringUtil.isEmpty(SHORT_NAME)) {
						hospMap.put("SHORT_NAME", SHORT_NAME);
					} else {
						ifCan = false;
						msg = "医院信息,第" + 2 + "行，SHORT_NAME不可为空！";
						break;
					}
					String ZIPCODE = hospSheet.getCell(6, 1).getContents();
					String ADDRESS = hospSheet.getCell(7, 1).getContents();
					if (!StringUtil.isEmpty(ADDRESS)) {
						hospMap.put("ADDRESS", ADDRESS);
					} else {
						ifCan = false;
						msg = "医院信息,第" + 2 + "行，ADDRESS不可为空！";
						break;
					}
					String PRINCIPAL = hospSheet.getCell(8, 1).getContents();
					hospMap.put("PRINCIPAL", PRINCIPAL);
					String HOSPDIST = hospSheet.getCell(9, 1).getContents();
					hospMap.put("HOSPDIST", HOSPDIST);
					String BUILDTIME = hospSheet.getCell(10, 1).getContents();
					hospMap.put("BUILDTIME", StrToDate(BUILDTIME));
					String LAND_AREAS = hospSheet.getCell(11, 1).getContents();
					hospMap.put("LAND_AREAS", LAND_AREAS);
					String BUILD_AREAS = hospSheet.getCell(12, 1).getContents();
					hospMap.put("BUILD_AREAS", BUILD_AREAS);
					String TEL1 = hospSheet.getCell(13, 1).getContents();
					hospMap.put("TEL1", TEL1);
					String TEL2 = hospSheet.getCell(14, 1).getContents();
					hospMap.put("TEL2", TEL2);
					String TEL3 = hospSheet.getCell(15, 1).getContents();
					hospMap.put("TEL3", TEL3);
					String FAX1 = hospSheet.getCell(16, 1).getContents();
					hospMap.put("FAX1", FAX1);
					String FAX2 = hospSheet.getCell(17, 1).getContents();
					hospMap.put("FAX2", FAX2);
					String FULL_VIEW = hospSheet.getCell(18, 1).getContents();
					hospMap.put("FULL_VIEW", FULL_VIEW + " ");
					String IMAGES1 = hospSheet.getCell(19, 1).getContents() + " ";
					hospMap.put("IMAGES1", IMAGES1);
					String IMAGES2 = hospSheet.getCell(20, 1).getContents() + " ";
					hospMap.put("IMAGES2", IMAGES2);
					String IMAGES3 = hospSheet.getCell(21, 1).getContents() + " ";
					hospMap.put("IMAGES3", IMAGES3);
					String IMAGES4 = hospSheet.getCell(22, 1).getContents() + " ";
					hospMap.put("IMAGES4", IMAGES4);
					String HOSP_PARENT = hospSheet.getCell(23, 1).getContents();
					hospMap.put("HOSP_PARENT", HOSP_PARENT);
					String STATUS = hospSheet.getCell(24, 1).getContents();
					hospMap.put("STATUS", STATUS);
					String INPUT = hospSheet.getCell(25, 1).getContents();
					hospMap.put("INPUT", INPUT);
					String INPUTTIME = hospSheet.getCell(26, 1).getContents();
					hospMap.put("INPUTTIME", INPUTTIME);
					String OPER = hospSheet.getCell(27, 1).getContents();
					hospMap.put("OPER", OPER);
					String OPERTIME = hospSheet.getCell(28, 1).getContents();
					hospMap.put("OPERTIME", OPERTIME);
					String HOSP_INFO = hospSheet.getCell(29, 1).getContents();
					hospMap.put("HOSP_INFO", HOSP_INFO);
					List<Map<String, String>> ifHospInfoHas = null;
					if (ifCan) {
						ifHospInfoHas = sqlSession.selectList("ifHospInfoHas", hospMap);
						if (ifHospInfoHas.size() == 0) {
							sqlSession.insert("insertHospInfo001", hospMap);
						} else {
							sqlSession.update("updateHospInfo001", hospMap);
						}
					}
					// }
					checkExcle = checkExcle + 1;
				}
			}
			if (ifCan) {
				if (checkExcle == 2) {
					Sheet sheetWaterTable = book.getSheet("电表_水表_点位类型对码表");
					int sheetWaterRows = sheetWaterTable.getRows();
					List<Map<String, String>> sheetWaterList = new ArrayList<Map<String, String>>();
					for (int a = 0; a < sheetWaterRows; a++) {
						String code = sheetWaterTable.getCell(0, a).getContents();
						String pointName = sheetWaterTable.getCell(1, a).getContents();
						String unitT = sheetWaterTable.getCell(2, a).getContents();
						Map<String, String> mao = new HashMap<String, String>();
						mao.put("code", code);
						mao.put("pointName", pointName);
						mao.put("unitT", unitT);
						sheetWaterList.add(mao);
					}
					Map<String, String> mao2 = new HashMap<String, String>();
					mao2.put("code", "W_01");
					mao2.put("pointName", "累计流量");
					mao2.put("unitT", "T");
					sheetWaterList.add(mao2);

					Sheet pointSheetTable = book.getSheet("非电表_点位类型对码表");
					List pointSheetList = new ArrayList<>();
					int pointSheetRows = pointSheetTable.getRows();
					for (int a = 0; a < pointSheetRows; a++) {
						String code = pointSheetTable.getCell(0, a).getContents();
						pointSheetList.add(code);
					}
					for (int w = 0; w < sheetNames.length; w++) {
						Sheet sheet = book.getSheet(w);
						int rows = sheet.getRows();
						int columns = sheet.getColumns();
						String sheetName = sheet.getName();
						if (sheetName.equals("大表")) {
							// 导入之前先删除服务区域表中的重复数据
							logg.info("导入之前先删除服务区域表中的重复数据deleteEquipService");
							deleteEquipService();
							Set<String> equipIdsSetForDelete = new HashSet<String>();
							Set<String> equipIdsSetNoDelete = new HashSet<String>();
							Set<String> pointCollectProtocolIdsSet = new HashSet<String>();
							// 遍历每行每列的单元格 从0开始，第0行是表头
							if (ifCan) {
								List<String> projectPointHasList = new ArrayList<>();
								Long insertNum = (long) 0;
								Long deleteNum = (long) 0;
								List<Map<String, String>> dbcontroldataList = null;
								List dbcontroldataPointList = new ArrayList<>();
								dbcontroldataList = sqlSession.selectList("selectDbControDateList");
								for (int p = 0; p < dbcontroldataList.size(); p++) {
									Object ppoint = dbcontroldataList.get(p).get("PROJECT_POINT");
									dbcontroldataPointList.add(ppoint.toString());
								}
								for (int i = 1; i < rows; i++) {// 行
									String proPoint = sheet.getCell(61, i).getContents();
									if (!StringUtil.isEmpty(proPoint)) {
										boolean ifppHas = false;
										int num = 0; //
										for (int o = 0; o < projectPointHasList.size(); o++) {
											String proPoint1 = (String) projectPointHasList.get(o);
											if (proPoint1.equals(proPoint)) {
												ifppHas = true;
												num = o + 2;
											}
										}
										if (ifppHas) {
											msg = "应用点位编码，第" + num + "行与第" + (i + 1) + "不可重复！";
											ifCan = false;
											break;
										}
										projectPointHasList.add(proPoint);
									}

									// 是否删除
									String isStruckoutCan = sheet.getCell(0, i).getContents();// 楼宇id是否为空
									Boolean isStruckout = false;
									if (!StringUtil.isEmpty(isStruckoutCan)) {
										isStruckout = sheet.getCell(0, i).getCellFormat().getFont().isStruckout();
									}
									if (isStruckout) {
										String equipID = sheet.getCell(29, i).getContents();// 设备id
										if (!StringUtil.isEmpty(equipID)) {
											equipIdsSetForDelete.add(equipID);
										}
										String pproject00 = sheet.getCell(61, i).getContents();// 应用点位类型
										if (!StringUtil.isEmpty(pproject00)) {
											pointCollectProtocolIdsSet.add(pproject00);
										}
									} else {
										// 前29列为楼宇信息map
										Map<String, Object> map = new HashMap<String, Object>();
										String buildID = sheet.getCell(0, i).getContents();
										if (StringUtil.isNotEmpty(buildID)) {
											if(ifNumer(buildID)){
												map.put("buildID", sheet.getCell(0, i).getContents());// 楼宇id
											}else{
												msg = "第" + (i + 1) + "行，楼宇ID只能为数字！";
												ifCan = false;
												break;
											}
										}
										map.put("buildingCode", sheet.getCell(1, i).getContents());// 楼宇编号
										String buildingName = sheet.getCell(2, i).getContents();
										if (!StringUtil.isEmpty(sheet.getCell(2, i).getContents())) {
											map.put("buildingName", sheet.getCell(2, i).getContents());// 楼宇名称*
										} else {
											//根据必填的字段（建筑面积，设备名称，点位对码），判断是否该行为空
											if((StringUtil.isEmpty(sheet.getCell(5, i).getContents()))
													&&(StringUtil.isEmpty(sheet.getCell(32, i).getContents()))
													&&(StringUtil.isEmpty(sheet.getCell(63, i).getContents()))){
												//如果当前行为空，结束当前循环，跳入下次循环
												continue;
											}else{
												msg = "第" + (i + 1) + "行，楼宇名称不可为空！";
												ifCan = false;
												break;
											}
										}
										map.put("hisName", sheet.getCell(3, i).getContents());// 楼宇曾用名
										map.put("buildingEnCode", sheet.getCell(4, i).getContents());// 楼宇编码
										String buildingAreas = sheet.getCell(5, i).getContents();
										if (!StringUtil.isEmpty(buildingAreas)) {
											if (isDouble(buildingAreas)
													&& Double.valueOf(buildingAreas).doubleValue() > 0) {
												double d = Double.valueOf(buildingAreas).doubleValue();
												map.put("buildingAreas", buildingAreas);// 建筑总面积*
											} else {
												msg = "第" + (i + 1) + "行，总建筑面积  必须输入,并且只能是数字,不可以是负数！";
												ifCan = false;
												break;
											}
										} else {
											msg = "第" + (i + 1) + "行，总建筑面积  必须输入,并且只能是数字,不可以是负数！";
											ifCan = false;
											break;
										}
										String amount = sheet.getCell(6, i).getContents();
										if (!StringUtil.isEmpty(amount)) {
											if (isDouble(amount)) {
												if (Double.parseDouble(amount) > 0) {
													map.put("amount", amount);// 投资额
												} else {
													msg = "第" + (i + 1) + "行，投资额,不可以是0或者负数！";
													ifCan = false;
													break;
												}
											} else {
												map.put("amount", amount);// 投资额
											}
										}
										String completime = sheet.getCell(7, i).getContents();
										if (!StringUtil.isEmpty(completime)) {
											if (StringUtils.isNumeric(completime) && completime.length() == 4) {
												map.put("completime", completime);// 竣工时间
											} else {
												msg = "第" + (i + 1) + "行，竣工时间必须是4位数字, 不能有负数和0！";
												ifCan = false;
												break;
											}
										}
										String storeyNumDown = sheet.getCell(8, i).getContents();
										if (!StringUtil.isEmpty(storeyNumDown)) {
											if (StringUtils.isNumeric(storeyNumDown)
													&& Integer.parseInt(storeyNumDown) >= 0) {
												map.put("storeyNumDown", sheet.getCell(8, i).getContents());// 地下层数*
											} else {
												msg = "第" + (i + 1) + "行，楼层数地下 不能出现负数,可以为0！";
												ifCan = false;
												break;
											}
										} else {
											msg = "第" + (i + 1) + "行，楼宇地下层数不可为空！";
											ifCan = false;
											break;
										}
										String storeyNumUp = sheet.getCell(9, i).getContents();
										if (!StringUtil.isEmpty(storeyNumUp)) {
											if (StringUtils.isNumeric(storeyNumUp)
													&& Integer.parseInt(storeyNumUp) > 0) {
												map.put("storeyNumUp", storeyNumUp);// 地上层数*
											} else {
												msg = "第" + (i + 1) + "行，楼层数地上 不能出现负数,不可以为0！";
												ifCan = false;
												break;
											}
										} else {
											msg = "第" + (i + 1) + "行，楼宇地上层数不可为空！";
											ifCan = false;
											break;
										}
										String mendNum = sheet.getCell(10, i).getContents();
										if (!StringUtil.isEmpty(mendNum)) {
											if (StringUtils.isNumeric(mendNum) && Integer.parseInt(mendNum) >= 0) {
												map.put("mendNum", mendNum);// 地上层数*
											} else {
												msg = "第" + (i + 1) + "行，大修次数   必须输入, 必须是数字,不可以为负数！";
												ifCan = false;
												break;
											}
										}

										String[] buildingStructure = new String[] { "1001", "1002", "1003", "1004",
												"1005", "1006" };
										String buildingStru = sheet.getCell(11, i).getContents();// 建筑结构（对码）
										if (!StringUtil.isEmpty(buildingStru)) {
											if (arrayContainArray(buildingStructure, buildingStru)) {
												map.put("structure", buildingStru);
											} else {
												msg = "第" + (i + 1) + "行，建筑对码不合规范！";
												ifCan = false;
												break;
											}
										}
										String height = sheet.getCell(12, i).getContents();
										if (!StringUtil.isEmpty(height)) {
											if (isDouble(height) && Double.parseDouble(height) > 0) {
												map.put("height", height);
											} else {
												msg = "第" + (i + 1) + "行，高度   必须输入数字,不可为0和负数！";
												ifCan = false;
												break;
											}
										}

										String[] outWallArr = new String[] { "2001", "2002", "2003", "2004", "2005" };
										String outWall = sheet.getCell(13, i).getContents();// 外墙，多选逗号分隔（对码）
										if (!StringUtil.isEmpty(outWall)) {
											if (arrayContainArray(outWallArr, outWall)) {
												map.put("outWall", sheet.getCell(13, i).getContents());
											} else {
												msg = "第" + (i + 1) + "行，外墙对码不合规范！";
												ifCan = false;
												break;
											}
										}
										map.put("audits", sheet.getCell(14, i).getContents());// 审计结果
										String proCost = sheet.getCell(15, i).getContents();
										if (!StringUtil.isEmpty(proCost)) {
											if (isDouble(proCost) && Double.parseDouble(proCost) > 0) {
												map.put("proCost", proCost);// 建安工程造价
											} else {
												msg = "第" + (i + 1) + "行，  建安工程造价   必须输入数字,不可为0和负数！";
												ifCan = false;
												break;
											}
										}

										String[] doorArr = new String[] { "7001", "7002", "7003" };
										if (!StringUtil.isEmpty(sheet.getCell(16, i).getContents())) {
											if (arrayContainArray(doorArr, sheet.getCell(16, i).getContents())) {
												map.put("doorMater", sheet.getCell(16, i).getContents());// 门用材料，多选逗号隔开（对码）
											} else {
												msg = "第" + (i + 1) + "行，门用材料对码不合规范！";
												ifCan = false;
												break;
											}
										}
										String[] windowArr = new String[] { "8001", "8002", "8003" };

										if (!StringUtil.isEmpty(sheet.getCell(17, i).getContents())) {
											if (arrayContainArray(windowArr, sheet.getCell(17, i).getContents())) {
												map.put("windowMater", sheet.getCell(17, i).getContents());// 窗用材料，多选逗号隔开（对码）
											} else {
												msg = "第" + (i + 1) + "行，窗用材料对码不合规范！";
												ifCan = false;
												break;
											}
										}
										if (!StringUtil.isEmpty(sheet.getCell(18, i).getContents())) {
											String[] housTopArr = new String[] { "9001", "9002", "9003", "9004" };
											if (arrayContainArray(housTopArr, sheet.getCell(18, i).getContents())) {
												map.put("ceilingMater", sheet.getCell(18, i).getContents());// 屋内顶材料，多选逗号隔开（对码）
											} else {
												msg = "第" + (i + 1) + "行，屋顶材料对码不合规范！";
												ifCan = false;
												break;
											}
										}

										String[] wallArr = new String[] { "10001", "10002", "10003" };
										if (!StringUtil.isEmpty(sheet.getCell(19, i).getContents())) {
											if (arrayContainArray(wallArr, sheet.getCell(19, i).getContents())) {
												map.put("wallMater", sheet.getCell(19, i).getContents());// 墙体材料，多选逗号隔开（对码）
											} else {
												msg = "第" + (i + 1) + "行，墙体材料对码不合规范！";
												ifCan = false;
												break;
											}
										}

										String[] floorArr = new String[] { "11001", "11002", "11003", "11004",
												"11005" };
										if (!StringUtil.isEmpty(sheet.getCell(20, i).getContents())) {
											if (arrayContainArray(floorArr, sheet.getCell(20, i).getContents())) {
												map.put("floorMater", sheet.getCell(20, i).getContents());// 地板材料，多选逗号隔开（对码）
											} else {
												msg = "第" + (i + 1) + "行，地板材料对码不合规范！";
												ifCan = false;
												break;
											}
										}
										map.put("place", sheet.getCell(21, i).getContents());// 位置
										if (!StringUtil.isEmpty(sheet.getCell(21, i).getContents())) {
											if (sheet.getCell(21, i).getContents().length() > 60) {
												msg = "第" + (i + 1) + "行，楼宇位置长度（"
														+ sheet.getCell(21, i).getContents().length() + "）不能大于60！";
												ifCan = false;
												break;
											}
										}
										String[] waterproofArr = new String[] { "3001", "3002", "3003" };
										if (!StringUtil.isEmpty(sheet.getCell(22, i).getContents())) {
											if (arrayContainArray(waterproofArr, sheet.getCell(22, i).getContents())) {
												map.put("dbBuildMaterByWaterproof", sheet.getCell(22, i).getContents());// 屋面防水等级，（对码）
											} else {
												msg = "第" + (i + 1) + "行，屋面防水等级对码不合规范！";
												ifCan = false;
												break;
											}
										}
										String[] seisimicfArr = new String[] { "4001", "4002", "4003", "4004", "4005" };
										if (!StringUtil.isEmpty(sheet.getCell(23, i).getContents())) {
											if (arrayContainArray(seisimicfArr, sheet.getCell(23, i).getContents())) {
												map.put("dbBuildMaterByQuakeproof", sheet.getCell(23, i).getContents());// 抗震强度，（对码）
											} else {
												msg = "第" + (i + 1) + "行，抗震强度对码不合规范！";
												ifCan = false;
												break;
											}
										}
										String[] costfArr = new String[] { "5001", "5002", "5003", "5004" };
										if (!StringUtil.isEmpty(sheet.getCell(24, i).getContents())) {
											if (arrayContainArray(costfArr, sheet.getCell(24, i).getContents())) {
												map.put("dbBuildMaterByCostaccord", sheet.getCell(24, i).getContents());// 造价依据（对码）
											} else {
												msg = "第" + (i + 1) + "行，造价依据对码不合规范！";
												ifCan = false;
												break;
											}
										}
										String[] perimalProblemfArr = new String[] { "6001", "6002", "6003" };
										if (!StringUtil.isEmpty(sheet.getCell(25, i).getContents())) {
											if (arrayContainArray(perimalProblemfArr,
													sheet.getCell(25, i).getContents())) {
												map.put("dbBuildMaterByProblem", sheet.getCell(25, i).getContents());// 改造前主要问题（对码）可为空
											} else {
												msg = "第" + (i + 1) + "行，改造前主要问题对码不合规范！";
												ifCan = false;
												break;
											}
										}
										String[] purposeArr = new String[] { "12001", "12002", "12003" };
										if (!StringUtil.isEmpty(sheet.getCell(26, i).getContents())) {
											if (arrayContainArray(purposeArr, sheet.getCell(26, i).getContents())) {
												map.put("dbBuildMaterByUsetype", sheet.getCell(26, i).getContents());// 用途（对码）
											} else {
												msg = "第" + (i + 1) + "行，楼宇用途对码不合规范！";
												ifCan = false;
												break;
											}
										}
										map.put("ower", sheet.getCell(27, i).getContents());// 产权拥有着
										String[] buildStateArr = new String[] { "54", "55", "56", "57", "58" };
										if (!StringUtil.isEmpty(sheet.getCell(28, i).getContents())) {
											if (arrayContainArray(buildStateArr, sheet.getCell(28, i).getContents())) {
												map.put("dbBaseComm", sheet.getCell(28, i).getContents());// 楼宇状态（对码）
											} else {
												msg = "第" + (i + 1) + "行，楼宇状态对码不合规范！";
												ifCan = false;
												break;
											}
										}
										map.put("status", "0");// 0.正常 1.删除
										map.put("dbUsersByOper", "0");// oper，操作员
										map.put("opertime", "0");// opertime
										String buildingID = null;
										List<Map<String, String>> dbBuildingList = null;
										if (StringUtil.isEmpty(buildID)) {
											dbBuildingList = sqlSession.selectList("findBuildingByName", buildingName);
											if (dbBuildingList.size() > 0) {
												Object buildingID0 = dbBuildingList.get(0).get("building_id");
												buildingID = buildingID0.toString();
												sqlSession.update("updateBuilding001", map);// 更新楼宇信息
											} else {
												sqlSession.insert("insertDbBuilding001", map);
												buildingID = map.get("buildingId").toString();
											}
										} else {
											dbBuildingList = sqlSession.selectList("findBuildingByID", buildID);
											if (dbBuildingList.size() > 0) {
												sqlSession.update("updateBuilding001", map);// 更新楼宇信息
												Object buildingID0 = dbBuildingList.get(0).get("building_id");
												buildingID = buildingID0.toString();
											} else {
												dbBuildingList = sqlSession.selectList("findBuildingByName",
														buildingName);
												if (dbBuildingList.size() > 0) {
													Object buildingID0 = dbBuildingList.get(0).get("building_id");
													buildingID = buildingID0.toString();
													sqlSession.update("updateBuilding001", map);// 更新楼宇信息
												} else {
													sqlSession.insert("insertDbBuilding001", map);
													buildingID = buildID;
												}
											}
										}
										// 30--52列为设备信息
										Map<String, Object> eqmap = new HashMap<String, Object>();
										String equipID001 = sheet.getCell(29, i).getContents();
										if (!StringUtil.isEmpty(equipID001)) {
											if(ifNumer(equipID001)){
												eqmap.put("equipID001", equipID001);
											}else{
												msg = "第" + (i + 1) + "行，设备ID只能为数字！";
												ifCan = false;
												break;
											}
										}
										String equipCode = sheet.getCell(30, i).getContents();
										if (!StringUtil.isEmpty(equipCode)) {
											eqmap.put("equipCode", sheet.getCell(30, i).getContents());// 设备编号*
										} else {
											ifCan = false;
											msg = "第" + (i + 1) + "行， 设备编号不能为空！";
											break;
										}
										String[] eqTypes = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9",
												"10", "11", "1001", "1002", "1003", "1004", "1005", "1006", "1007",
												"1008", "2001", "2002", "2003", "2004", "2005", "3001", "4001", "5001",
												"5002", "5003", "5004", "5005", "5006", "6001", "6002", "7001", "8001",
												"8002", "9001", "9002", "10001", "10002", "11001", "11002", "1004001",
												"1004002", "1004003", "1004004", "1004005", "1004006", "1004007",
												"1004008", "1004009", "1004010", "1004011", "1004012", "1004013",
												"1004014", "1005001", "1005002", "1005003", "1005004" };
										if (!StringUtil.isEmpty(sheet.getCell(31, i).getContents())) {
											if (arrayContainArray(eqTypes, sheet.getCell(31, i).getContents())) {
												eqmap.put("dbEquipType", sheet.getCell(31, i).getContents());// 设备类型*
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行， 设备类型对码不合规范！";
												break;
											}
										} else {
											ifCan = false;
											msg = "第" + (i + 1) + "行， 设备类型不能为空！";
											break;
										}
										String equipName = sheet.getCell(32, i).getContents();
										if (!StringUtil.isEmpty(equipName)) {
											eqmap.put("equipName", equipName);// 设备名称*
										} else {
											ifCan = false;
											msg = "第" + (i + 1) + "行，设备名称不能为空！";
											break;
										}
										eqmap.put("assetscode", sheet.getCell(33, i).getContents());// 资产编号
										eqmap.put("dbHospInfo", "1");// HOSP_ID
										eqmap.put("dbUsers", "0");// oper
										eqmap.put("opertime", "0");// OPERTIME
										if (!StringUtil.isEmpty(sheet.getCell(34, i).getContents())) {
											if (ifNumer(sheet.getCell(34, i).getContents())) {
												eqmap.put("storey", sheet.getCell(34, i).getContents());// 楼宇楼成*
												eqmap.put("dbBuilding", buildingID);// 楼宇id*
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行，楼宇楼成必须输入数字！";
												break;
											}
										} else {
											ifCan = false;
											msg = "第" + (i + 1) + "行，楼层不能为空！";
											break;
										}

										if (!StringUtil.isEmpty(sheet.getCell(35, i).getContents())) {
											eqmap.put("place", sheet.getCell(35, i).getContents());// 安装位置*
										} else {
											ifCan = false;
											msg = "第" + (i + 1) + "行，安装位置不能为空！";
											break;
										}
										String controlFlag = sheet.getCell(36, i).getContents();

										if (!StringUtil.isEmpty(controlFlag)) {
											if (controlFlag.equals("0") || controlFlag.equals("1")) {
												eqmap.put("controlFlag", sheet.getCell(36, i).getContents());// 是否监控（0.1）
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行，是否监控的值只能填写0或1！";
												break;
											}
										} else {
											ifCan = false;
											msg = "第" + (i + 1) + "行，是否监控不能为空！";
											break;
										}
										eqmap.put("status", "0");// 0正常，1删除
										eqmap.put("brand", sheet.getCell(37, i).getContents());// 品牌
										eqmap.put("unitType", sheet.getCell(38, i).getContents());// 设备型号
										eqmap.put("production", sheet.getCell(39, i).getContents());// 生产单位
										eqmap.put("field", sheet.getCell(40, i).getContents());// 产地

										if (!StringUtil.isEmpty(sheet.getCell(41, i).getContents())) {
											String cell10 = "";
											// 如果是Date类型
											if (sheet.getCell(41, i).getType() == CellType.DATE) {
												DateCell dc = (DateCell) sheet.getCell(41, i);
												cell10 = new SimpleDateFormat("yyyy/MM/dd").format(dc.getDate());
												eqmap.put("productionDate", cell10);
											} else {
												if (isValidDate(sheet.getCell(41, i).getContents())) {
													eqmap.put("productionDate",
															StrToDate(sheet.getCell(41, i).getContents()));// 生产日期
												} else {
													ifCan = false;
													msg = "第" + (i + 1) + "行，设备生产日期格式错误（yyyy/MM/dd） ！";
													break;
												}
											}
										}
										if (!StringUtil.isEmpty(sheet.getCell(42, i).getContents())) {
											String cell10 = "";
											// 如果是Date类型
											if (sheet.getCell(42, i).getType() == CellType.DATE) {
												DateCell dc = (DateCell) sheet.getCell(42, i);
												cell10 = new SimpleDateFormat("yyyy/MM/dd").format(dc.getDate());
												eqmap.put("installDate", cell10);
											} else {
												if (isValidDate(sheet.getCell(42, i).getContents())) {
													eqmap.put("installDate",
															StrToDate(sheet.getCell(42, i).getContents()));// 安装日期
												} else {
													ifCan = false;
													msg = "第" + (i + 1) + "行，设备安装日期格式错误（yyyy/MM/dd） ！";
													break;
												}
											}
										}
										if (!StringUtil.isEmpty(sheet.getCell(43, i).getContents())) {
											String cell10 = "";
											// 如果是Date类型
											if (sheet.getCell(43, i).getType() == CellType.DATE) {
												DateCell dc = (DateCell) sheet.getCell(43, i);
												cell10 = new SimpleDateFormat("yyyy/MM/dd").format(dc.getDate());
												eqmap.put("useDate", cell10);
											} else {
												if (isValidDate(sheet.getCell(43, i).getContents())) {
													eqmap.put("useDate", StrToDate(sheet.getCell(43, i).getContents()));// 使用日期
												} else {
													ifCan = false;
													msg = "第" + (i + 1) + "行，设备使用日期格式错误（yyyy/MM/dd） ！";
													break;
												}
											}
										}
										String serviceLife = sheet.getCell(44, i).getContents();
										if (!StringUtil.isEmpty(serviceLife)) {
											if (StringUtils.isNumeric(serviceLife)
													&& Integer.parseInt(serviceLife) > 0) {
												eqmap.put("serviceLife", sheet.getCell(44, i).getContents());// 使用年限
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行，使用年限  必须是数字,不能为负数 ！";
												break;
											}
										}
										if (!StringUtil.isEmpty(sheet.getCell(45, i).getContents())) {
											eqmap.put("limited", sheet.getCell(45, i).getContents());// 质保期
										}
										eqmap.put("purchase", sheet.getCell(46, i).getContents());// 采购价
										eqmap.put("serviceCycle", sheet.getCell(47, i).getContents());// 保养周期
										eqmap.put("accessory", sheet.getCell(48, i).getContents());// 主要附件
										String[] useEnergy = new String[] { "0", "2001", "2002", "2003", "1", "2", "3",
												"4", "5", "3001", "3002", "4001", "4002", "5001", "5002", "5003",
												"5004", "5005", "5006", "5007" };
										if (!StringUtil.isEmpty(sheet.getCell(49, i).getContents())) {
											if (arrayContainArray(useEnergy, sheet.getCell(49, i).getContents())) {
												eqmap.put("dbEnergyType", sheet.getCell(49, i).getContents());// 使用能源（对码）
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行， 使用能源不合对码规范！";
												break;
											}
										}

										String a001 = sheet.getCell(62, i).getContents();
										String nodeLevel = sheet.getCell(50, i).getContents();
										if (a001.contains("E")) {
											if (!StringUtil.isEmpty(nodeLevel) && (nodeLevel.equals("1")
													|| nodeLevel.equals("2") || nodeLevel.equals("3"))) {
												eqmap.put("nodeLevel", sheet.getCell(50, i).getContents());// 设备分级节点（电表必填）
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行， 设备分级节点（电表必填，只能填写1.2.3）！";
												break;
											}
										} else {
											eqmap.put("nodeLevel", sheet.getCell(50, i).getContents());// 设备分级节点（电表必填）
										}
										// 获取父节点设备id eqNameIdMap
										eqmap.put("nodeParent",
												selectEquipParByEqname(sheet.getCell(51, i).getContents()));// 上级节点(选填)(隶属关系,上级设备的设备名称)
										eqmap.put("nodeTop",
												selectEquipParByEqname(sheet.getCell(52, i).getContents()));// 根节点(选填,根级设备的设备名称
										// 处理设备表开始
										String equipID = null;
										List<Map<String, String>> dbEquipList = null;
										if (StringUtil.isEmpty(equipID001)) {
											dbEquipList = sqlSession.selectList("findEquipByCode", eqmap);
											if (dbEquipList.size() > 0) {
												Object e1quipID = dbEquipList.get(0).get("equip_id");
												equipID = e1quipID.toString();
												sqlSession.update("updateEqList", eqmap);// 更新设备问题
											} else {
												equipID = insertEquipList(eqmap);
											}
										} else {
											dbEquipList = sqlSession.selectList("findEquipById", equipID001);
											if (dbEquipList.size() > 0) {
												Object e1quipID = dbEquipList.get(0).get("equip_id");
												equipID = e1quipID.toString();
												sqlSession.update("updateEqList", eqmap);
											} else {
												dbEquipList = sqlSession.selectList("findEquipByCode", eqmap);
												if (dbEquipList.size() > 0) {
													Object e1quipID = dbEquipList.get(0).get("equip_id");
													equipID = e1quipID.toString();
													sqlSession.update("updateEqList", eqmap);
												} else {
													equipID = insertEquipList(eqmap);
													equipID = equipID001;
												}
											}
										}
										// 处理设备表结束
										// 53--55为服务
										Map<String, Object> serviceMap = new HashMap<String, Object>();
										String serviceAreaId = sheet.getCell(53, i).getContents();
										if (StringUtil.isNotEmpty(serviceAreaId)) {
											if(ifNumer(serviceAreaId)){
												serviceMap.put("serviceAreaId", sheet.getCell(53, i).getContents());// 服务区域id
											}else{
												msg = "第" + (i + 1) + "行，服务区域ID只能为数字！";
												ifCan = false;
												break;
											}
										}
										serviceMap.put("equipId", equipID);
										serviceMap.put("buildId", buildingID);
										serviceMap.put("storey", sheet.getCell(34, i).getContents());// 楼层

										serviceMap.put("comments", sheet.getCell(54, i).getContents());// 服务区域*

										String[] energyIds = new String[] { "0", "2001", "2002", "2003", "1", "2", "3",
												"4", "5", "3001", "3002", "4001", "4002", "5001", "5002", "5003",
												"5004", "5005", "5006", "5007" };
										if (!StringUtil.isEmpty(sheet.getCell(55, i).getContents())) {
											if (arrayContainArray(energyIds, sheet.getCell(55, i).getContents())) {
												serviceMap.put("energyId", sheet.getCell(55, i).getContents());// 服务能源（对码）
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行， 服务能源对码不合规范！";
												break;
											}
										}
										String serviceSeq = null;
										List<Map<String, String>> eqServiceList = null;
										if (StringUtil.isEmpty(serviceAreaId)) {
											eqServiceList = sqlSession.selectList("findServiceByBuildIdAndEqid",
													serviceMap);
											if (eqServiceList.size() > 0) {
												Object serviceSeq1 = eqServiceList.get(0).get("seq");
												serviceSeq = serviceSeq1.toString();
												sqlSession.update("updateEquipService001", serviceMap);
											} else {
												sqlSession.insert("insertServiceEquip", serviceMap);
												serviceSeq = serviceMap.get("seq").toString();
											}
										} else {
											eqServiceList = sqlSession.selectList("findServiceById", serviceMap);
											if (eqServiceList.size() > 0) {
												Object serviceSeq1 = eqServiceList.get(0).get("seq");
												serviceSeq = serviceSeq1.toString();
												sqlSession.update("updateEquipService001", serviceMap);
											} else {
												eqServiceList = sqlSession.selectList("findServiceByBuildIdAndEqid",
														serviceMap);
												if (eqServiceList.size() > 0) {
													Object serviceSeq1 = eqServiceList.get(0).get("seq");
													serviceSeq = serviceSeq1.toString();
													sqlSession.update("updateEquipService001", serviceMap);
												} else {
													sqlSession.insert("insertServiceEquip", serviceMap);
													serviceSeq = serviceAreaId;
												}
											}
										}
										// }
										// 56--60为设备分组
										Map<String, Object> eqGroupMap = new HashMap<String, Object>();
										String equipGroupid = sheet.getCell(56, i).getContents();
										if (StringUtil.isNotEmpty(equipGroupid)) {
											if(ifNumer(equipGroupid)){
												eqGroupMap.put("equipGroupid", equipGroupid);
											}else{
												msg = "第" + (i + 1) + "行，设备分组ID只能为数字！";
												ifCan = false;
												break;
											}
										}
										int eqGroupNum = 0;
										if (!StringUtil.isEmpty(sheet.getCell(57, i).getContents())) {
											eqGroupNum = eqGroupNum + 1;
										}
										if (!StringUtil.isEmpty(sheet.getCell(58, i).getContents())) {
											String groupName0 = sheet.getCell(58, i).getContents();
											if (groupName0.length() > 30) {
												ifCan = false;
												msg = "第" + (i + 1) + "行，设备分组名称长度（" + groupName0.length() + "）不能多于30！";
												break;
											}
											eqGroupNum = eqGroupNum + 1;
										}
										if (!StringUtil.isEmpty(sheet.getCell(59, i).getContents())) {
											if (arrayContainArray(eqTypes, sheet.getCell(59, i).getContents())) {
												eqGroupNum = eqGroupNum + 1;
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行， 分组属于设备类型对码不合规范！";
												break;
											}
										}
										
										  if(!StringUtil.isEmpty(sheet.getCell( 60, i).getContents())) { //验证数字 
											  if (!ifNumer(sheet.getCell (60, i).getContents())) {
												  msg = "第" + (i + 1) + "行，分组楼宇楼层必须是数字！"; 
												  ifCan = false;
												  break; 
												  } 
											  }
										
										if (eqGroupNum == 0 || eqGroupNum == 3) {
											eqGroupMap.put("groupCode", sheet.getCell(57, i).getContents());// 设备分组编码*
											String groupName0 = sheet.getCell(58, i).getContents();
											if (!StringUtil.isEmpty(groupName0)) {
												groupName0 = groupName0.trim();
											}
											eqGroupMap.put("groupName", groupName0);// 设备分组名称*
											eqGroupMap.put("dbEquipType", sheet.getCell(59, i).getContents());// 分组属于设备类型(必填)(设备类型对码表)
											eqGroupMap.put("storey",sheet.getCell(60,i).getContents());// 分组楼宇楼层
											eqGroupMap.put("buildId", buildingID);
										} else {

											ifCan = false;
											msg = "第" + (i + 1) + "行， 分组编码,分组名称,设备类型不合约定！";
											break;
										}
										List<Map<String, String>> eqGroupList = null;
										String eqGroupSeq = null;
										if (StringUtil.isEmpty(equipGroupid)) {
											if (eqGroupNum == 3) {
												eqGroupList = sqlSession.selectList("findEqGroupByName", eqGroupMap);
												if (eqGroupList.size() > 0) {
													Object eqGroupSeq1 = eqGroupList.get(0).get("seq");
													eqGroupSeq = eqGroupSeq1.toString();
													sqlSession.update("updateEqGroup001", eqGroupMap);
												} else {
													sqlSession.insert("insertEquipGroup001", eqGroupMap);
													eqGroupSeq = eqGroupMap.get("seq").toString();
												}
											}
										} else {
											if (eqGroupNum == 3) {
												eqGroupList = sqlSession.selectList("findEqGroupById", equipGroupid);
												if (eqGroupList.size() > 0) {
													Object eqGroupSeq1 = eqGroupList.get(0).get("seq");
													eqGroupSeq = eqGroupSeq1.toString();
												} else {
													sqlSession.insert("insertEquipGroup001", eqGroupMap);
													eqGroupSeq = equipGroupid;
												}
											}
										}
										// 插入groupEquip
										if (eqGroupNum == 3) {
											Map<String, String> groupEquipMap = new HashMap<String, String>();
											groupEquipMap.put("eqGroupSeq", eqGroupSeq);
											groupEquipMap.put("equipID", equipID);
											List<Map<String, String>> groupEquipList = null;
											groupEquipList = sqlSession.selectList("findByGroupidAndEqid",
													groupEquipMap);
											if (groupEquipList.size() == 0) {
												sqlSession.insert("insertGroupEquip001", groupEquipMap);
											}
										}
										eqGroupNum = 0;

										// 61-63点位对码与描述
										Map<String, Object> pointMap = new HashMap<String, Object>();
										String pointName0 = null;
										String unitT = null;
										if (!StringUtil.isEmpty(sheet.getCell(62, i).getContents())) {
											String pointCode = sheet.getCell(62, i).getContents();
											// 判断是否规范
											boolean flag = false;
											if (pointCode.contains("W") || pointCode.contains("E")) {
												for (int q = 0; q < sheetWaterList.size(); q++) {
													Map mao = sheetWaterList.get(q);
													if (mao.get("code").equals(pointCode)) {
														flag = true;
														pointName0 = (String) mao.get("pointName");
														unitT = (String) mao.get("unitT");
														break;
													}
												}
											} else {
												for (int w1 = 0; w1 < pointSheetList.size(); w1++) {
													String code = (String) pointSheetList.get(w1);
													String tow[] = pointCode.split("_");
													String nof = tow[1];
													String qq = nof.substring(0, 2);
													String pointCode1 = tow[0] + "_" + qq;
													if (code.equals(pointCode1)) {
														flag = true;
													}
												}
											}
											if (flag) {
												pointMap.put("POINT_TYPE", pointCode);// 点位对码(必填)(非电表_点位类型对码表/电表_水表_点位类型对码表)
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行，点位对码不合规范！";
												break;
											}
										} else {
											ifCan = false;
											msg = "第" + (i + 1) + "行，点位对码不能为空！";
											break;
										}
										// 处理db_point表开始
										pointMap.put("HOSP_ID", "1");
										pointMap.put("RATE", "1");
										if (!StringUtil.isEmpty(sheet.getCell(63, i).getContents())) {
											pointMap.put("DESCR", sheet.getCell(63, i).getContents());// 文字描述
										} else {
											ifCan = false;
											msg = "第" + (i + 1) + "行，文字描述不能为空！";
											break;
										}
										pointMap.put("CONTROLPOINT", sheet.getCell(64, i).getContents());// 工程点位
										String pointType = sheet.getCell(62, i).getContents();
										String[] pointTypedArr = pointType.split("_");
										String eqTypeId = pointTypedArr[0];
										String controlPoint1 = pointTypedArr[1];
										String controlPoint = controlPoint1.substring(0, 2);
										Map ruleMap = new HashMap<String, Object>();
										ruleMap.put("eqTypeId", eqTypeId);
										ruleMap.put("controlPoint", controlPoint);
										String PROJECT_POINT = null;
										String CONTROL_POINT = null;
										if (pointType.contains("W") || pointType.contains("E")) {
											pointMap.put("POINT_NAME", pointName0);
											pointMap.put("UNIT_TYPE", unitT);
											PROJECT_POINT = equipID + "." + controlPoint1;
											pointMap.put("PROJECT_POINT", PROJECT_POINT);// 系统使用

										} else {
											List<Map<String, String>> ruleMapList = null;
											ruleMapList = sqlSession.selectList("findByRuleMap001", ruleMap);
											if (ruleMapList.size() > 0) {
												Object UNIT_TYPE1 = ruleMapList.get(0).get("unit_type");
												if (!StringUtil.isEmpty(UNIT_TYPE1)) {
													String UNIT_TYPE = UNIT_TYPE1.toString();
													pointMap.put("UNIT_TYPE", UNIT_TYPE);
												}
												Object CONTROL_POINT1 = ruleMapList.get(0).get("control_point");
												if (!StringUtil.isEmpty(CONTROL_POINT1)) {
													CONTROL_POINT = CONTROL_POINT1.toString();
												}
												Object obj1 = ruleMapList.get(0).get("alert_level");
												if (!StringUtil.isEmpty(obj1)) {
													String ALERT_LEVEL = obj1.toString();
													pointMap.put("ALERT_LEVEL", ALERT_LEVEL);// 0:非告警1:一级告警2：二级告警3：三级告警
												}
												Object POWER_TYP1 = ruleMapList.get(0).get("power_type");
												if (!StringUtil.isEmpty(POWER_TYP1)) {
													String POWER_TYPE = POWER_TYP1.toString();
													pointMap.put("POWER_TYPE", POWER_TYPE);// 1、水2、电3、油4、医用气5、能源气
												}
												Object POINT_DEPICT1 = ruleMapList.get(0).get("point_depict");
												if (!StringUtil.isEmpty(POINT_DEPICT1)) {
													String POINT_DEPICT = POINT_DEPICT1.toString();
													pointMap.put("POINT_NAME", POINT_DEPICT);
												}
												PROJECT_POINT = equipID + "." + controlPoint1 + "_CV";
												pointMap.put("PROJECT_POINT", PROJECT_POINT);// 系统使用
											} else {
												ifCan = false;
												msg = "第" + (i + 1) + "行，未获取点位相关信息，请检查点位对码相关表！";
												break;
											}
										}
										String projectPoint = sheet.getCell(61, i).getContents();
										String pointID = null;
										List<Map<String, String>> pointList = null;
										Boolean ifConTrd = false;
										if (!StringUtil.isEmpty(projectPoint)) {
											pointMap.put("projectPoint", sheet.getCell(61, i).getContents());// 应用点位类型
											pointList = sqlSession.selectList("ifPointHas", projectPoint);
											if (pointList.size() > 0) {
												Object pointID1 = pointList.get(0).get("seq");
												pointID = pointID1.toString();
												sqlSession.update("updateDbPoint001", pointMap);
												deleteNum++;
											} else {
												// 2.不存在则添加
												sqlSession.insert("insertDbPoint001", pointMap);
												ifConTrd = true;
												// logg.info("新增点位信息：" +
												// pointMap);
												insertNum++;
												pointID = pointMap.get("seq").toString();
											}
										} else {
											pointList = sqlSession.selectList("ifPointHaBys", PROJECT_POINT);
											if (pointList.size() > 0) {
												Object pointID1 = pointList.get(0).get("seq");
												pointID = pointID1.toString();
												sqlSession.update("updateDbPoint001", pointMap);
												logg.info("点位更新，第" + (i + 1) + "行");
												deleteNum++;
											} else {
												// 2.不存在则添加
												sqlSession.insert("insertDbPoint001", pointMap);
												ifConTrd = true;
												// logg.info("新增点位信息：" +
												// pointMap);
												insertNum++;
												pointID = pointMap.get("seq").toString();
											}
										}
										// 处理db_point表结束
										// 处理DB_POINT_EQUIP开始
										Map pointEquipMap = new HashMap();
										pointEquipMap.put("SEQ", pointID);
										pointEquipMap.put("EQUIP_ID", equipID);
										pointEquipMap.put("EQUIP_TYPE_ID", sheet.getCell(31, i).getContents());
										pointEquipMap.put("HOSP_ID", "1");
										List<Map<String, String>> pointEquipList = null;
										pointEquipList = sqlSession.selectList("ifpointEquipHas", pointEquipMap);
										if (pointEquipList.size() == 0) {
											sqlSession.insert("insertPointEquip001", pointEquipMap);
											dbcontroldataPointList.add(PROJECT_POINT);
										} else {
											sqlSession.update("updatePointEquip001", pointEquipMap);
										}
										// 处理DB_POINT_EQUIP结束
										// 处理DB_CONTROLDATA开始
										Map controldataMap = new HashMap();
										controldataMap.put("C_SEQ", pointID);
										controldataMap.put("PROJECT_POINT", PROJECT_POINT);
										controldataMap.put("CONTROLPOINT", sheet.getCell(64, i).getContents());
										controldataMap.put("HOSP_ID", "1");

										// 如果新增了点位则新增DB_CONTROLDATA
										/*
										 * if(ifConTrd){ sqlSession.insert(
										 * "insertDbcontroldata001",
										 * controldataMap); }
										 */
										List<Map<String, String>> dbControldataList0 = null;
										dbControldataList0 = sqlSession.selectList("ifcontroldataHas", controldataMap);
										if (dbControldataList0.size() == 0) {
											sqlSession.insert("insertDbcontroldata001", controldataMap);
										}

										equipIdsSetNoDelete.add(sheet.getCell(29, i).getContents());
										pointCollectProtocolIdsSet.remove(sheet.getCell(61, i).getContents());
										// 处理DB_CONTROLDATA结束
									}
								}
								pointCollectProtocolIdsSet.remove("");
								logg.info("插入点位数量：" + insertNum);
								logg.info("更新点位数量：" + deleteNum);
								logg.info("删除点位数量：" + pointCollectProtocolIdsSet.size());
								if (pointCollectProtocolIdsSet.size() > 0) {
									List<Map<String, String>> projectPointList = null;
									if (pointCollectProtocolIdsSet.size() > 0) {
										Iterator<String> iterator = pointCollectProtocolIdsSet.iterator();
										while (iterator.hasNext()) {
											String proPointlId = iterator.next();
											String pointId;
											// 1.根据projectpoint获取pointid
											if (!StringUtil.isEmpty(proPointlId)) {
												projectPointList = sqlSession.selectList("findPointIDBypProPointlId",
														proPointlId);
												if (projectPointList.size() > 0) {
													Object SEQQ = projectPointList.get(0).get("SEQ");
													pointId = SEQQ.toString();
													// 2.删除db_controldata
													sqlSession.delete("deleteDbcontroldata", proPointlId);
													logg.info("db_controldata删除proPointlId：" + proPointlId);
													// 3.删除db_point_equip
													sqlSession.delete("deleteDbPointEquip", pointId);
													logg.info("db_point_equip删除pointId：" + pointId);
//													delete  from  DB_ALARM_HIS   t
//													where  t.C_SEQ=14637;
													
													// 3.删除db_point_equip
													sqlSession.delete("deletedbpointhis", pointId);
													logg.info("DB_ALARM_HIS删除pointId：" + pointId);
													
													// 4.删除DB_POINT
													sqlSession.delete("deleteDbpoint", pointId);
													logg.info("DB_POINT删除pointId：" + pointId);
												}
											}
										}
									}
									logg.info("更新设备数量：" + equipIdsSetForDelete.size());
									if (equipIdsSetForDelete.size() > 0) {
										Iterator<String> equipIdsIter = equipIdsSetForDelete.iterator();
										while (equipIdsIter.hasNext()) {
											String eqId = equipIdsIter.next();
											if (!StringUtil.isEmpty(eqId)) {
												if (!equipIdsSetNoDelete.contains(eqId)) {
													// 1.更新DB_EQUIP_LIST
													sqlSession.update("updateDbEquipList", eqId);
													logg.info("更新DB_EQUIP_LIST,Id：" + eqId);
												}
											}
										}
									}
								}
							}
						}
					}
				} else {
					msg = "没有大表或没有医院信息";
					logg.info("导入初始信息失败：" + msg);
					ifCan = false;
				}
			}
		} catch (BiffException | IOException e) {
			e.printStackTrace();
			logg.error("导入初始信息失败：", e);
			msg = "系统错误！";
		}
		logg.info("导入初始信息返回信息：" + msg);
		return msg;
	}

	public String insertEquipList(Map eqmap) {
		sqlSession.insert("insertEquipList001", eqmap);
		Object equipID = eqmap.get("equipId");
		return equipID.toString();
	}

	public Map<?, ?> getInsertEquipListSql() {
		return PerformSQLUtil.get("insertEquipList001", this);
	}

	/**
	 * 数组是否包含字符串(或数组)
	 */
	public boolean arrayContainArray(String[] arr, String arrString) {
		Boolean flag = false;
		if (arrString.contains(",")) {
			String[] childArr = arrString.split(",");
			flag = containsAll(arr, childArr);
		} else if (arrString.contains("，")) {
			String[] childArr = arrString.split("，");
			flag = containsAll(arr, childArr);
		} else {
			flag = Arrays.asList(arr).contains(arrString);
		}
		return flag;
	}

	/**
	 * 数组是否包含数组
	 */
	private static boolean containsAll(String[] array1, String[] array2) {
		for (String str : array2) {
			String str1 = str.trim();
			if (!ArrayUtils.contains(array1, str1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串转日期
	 */
	public static Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (java.text.ParseException e) {
			logg.error(str + "转换为日期时出现异常！", e);
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据名称获取父节点ID
	 */
	public String selectEquipParByEqname(String eqName) {
		String eqID = null;
		if (!StringUtil.isEmpty(eqName)) {
			Map<String, String> map = new HashMap<>();
			map.put("eqName", eqName);
			List<Map<String, String>> eqIdList = null;
			eqIdList = sqlSession.selectList("selectEquipParByName", map);
			if (eqIdList.size() > 0) {
				Object eqid1 = eqIdList.get(0).get("EQUIP_ID");
				eqID = eqid1.toString();
			}
			// logg.info("根据设备名称:" + eqName + "获取的设备ID为：" + eqID);
		}
		return eqID;
	}

	/**
	 * 字符串是否可以转换为日期
	 */
	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		// 设置lenient为false.
		// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		format.setLenient(false);
		try {
			format.parse(str);
		} catch (java.text.ParseException e) {
			logg.debug("日期转换错误，当前字符" + str + "不能转换为日期！");
			convertSuccess = false;
			e.printStackTrace();
		}
		return convertSuccess;
	}

	/**
	 * 是否是double型的
	 */
	public static boolean isDouble(String str) {
		boolean convertSuccess = true;
		try {
			Double d = Double.parseDouble(str);
		} catch (Exception e) {
			logg.debug("数据类型有误：" + str);
			convertSuccess = false;
		}
		return convertSuccess;
	}

	// 获取导出数据
	public List<Map<String, String>> getAllData() {
		return sqlSession.selectList("selectExcle");
	}

	// 获取医院信息
	public List<Map<String, String>> getHospInfo(String string) {
		return sqlSession.selectList("getHospInfoList");
	}

	/**
	 * 导出数据前先删除EQUIP_SERVICE中可能存在的重复数据
	 */
	public void deleteEquipService() {
		sqlSession.delete("deleteEqService");
		logg.info("EQUIP_SERVICE去重复执行完毕");
	}

	public List<Map<String, String>> getEquipNameIdList() {
		List<Map<String, String>> equipNameIdList = new ArrayList<Map<String, String>>();
		equipNameIdList = sqlSession.selectList("equipNameIdList");
		logg.info("获取设备名称ID执行完毕");
		return equipNameIdList;
	}

	/**
	 * 根据设备类型ID获取设备类型名称
	 * 
	 * @param typeid
	 * @return
	 */
	public String getEqTypeByID(String typeid) {
		String eqName = null;
		if (!StringUtil.isEmpty(typeid)) {
			List<Map<String, String>> equipNameList = new ArrayList<Map<String, String>>();
			equipNameList = sqlSession.selectList("getEqTypeByID", typeid);
			Object name = equipNameList.get(0).get("TYPE_NAME");
			if (!StringUtil.isEmpty(name)) {
				eqName = name.toString();
			}
		}
		return eqName;
	}

	/**
	 * 根据设备ID获取设备名称
	 */
	public String getEqNameByID(String eqid) {
		String eqName = null;
		if (!StringUtil.isEmpty(eqid)) {
			List<Map<String, String>> equipNameList = new ArrayList<Map<String, String>>();
			equipNameList = sqlSession.selectList("getEqNameByID", eqid);
			Object name = equipNameList.get(0).get("EQUIP_NAME");
			if (!StringUtil.isEmpty(name)) {
				eqName = name.toString();
			}
		}
		return eqName;
	}

	/**
	 * 删除点位和点位相关表，更新相关设备
	 * 
	 */
	public void deletePoint(String eqId, String proPointlId) {
		if (!StringUtil.isEmpty(proPointlId)) {
			List<Map<String, String>> projectPointList = null;
			projectPointList = sqlSession.selectList("findPointIDBypProPointlId", proPointlId);
			if (projectPointList.size() > 0) {
				String pointId = null;
				Object SEQQ = projectPointList.get(0).get("SEQ");
				pointId = SEQQ.toString();
				// 2.删除db_controldata
				sqlSession.delete("deleteDbcontroldata", proPointlId);
				logg.info("db_controldata删除proPointlId：" + proPointlId);
				// 3.删除db_point_equip
				sqlSession.delete("deleteDbPointEquip", pointId);
				logg.info("db_point_equip删除pointId：" + pointId);
				// 3.删除db_point_equip
				sqlSession.delete("deletedbpointhis", pointId);
				logg.info("DB_ALARM_HIS删除pointId：" + pointId);
				
				// 4.删除DB_POINT
				sqlSession.delete("deleteDbpoint", pointId);
				logg.info("DB_POINT删除pointId：" + pointId);
			} else {
				logg.info("proPoint为" + proPointlId + "的点位不存在！");
			}
		}
		if (!StringUtil.isEmpty(eqId)) {
			List<Map<String, String>> pointEqList = null;
			pointEqList = sqlSession.selectList("findPointByEqID", eqId);
			if (pointEqList.size() == 0) {
				sqlSession.update("updateDbEquipList", eqId);
				logg.info("更新DB_EQUIP_LIST,Id：" + eqId);
			} else {
				logg.info("设备id为" + eqId + "的设备下还有点位" + pointEqList.size() + "个，不执行更新！");
			}
		}
	}

	/**
	 * 是否为数字
	 */
	public boolean ifNumer(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
		/*
		 * Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+"); Matcher isNum
		 * = pattern.matcher(str); if( !isNum.matches() ){ return false; }
		 * return true;
		 */
	}
	/*
	 * 导出SDCD暂时不用
	 * 
	 * @SuppressWarnings("unchecked") public void
	 * outPutSDCDExcle(HttpServletRequest request, HttpServletResponse res, File
	 * newFile, String uploadFileFileName) { res.setCharacterEncoding("UTF-8");
	 * res.setContentType("application/octet-stream"); String fileName_1;
	 * OutputStream os; Workbook workbook = null; InputStream ins; try {
	 * fileName_1 = new String(uploadFileFileName.getBytes("UTF-8"),
	 * "iso8859-1"); res.setHeader("Content-Disposition", "attachment;filename="
	 * + fileName_1); os = res.getOutputStream(); ins = new
	 * FileInputStream(newFile); workbook = Workbook.getWorkbook(ins);
	 * WorkbookSettings ws = new WorkbookSettings(); Locale locale = new
	 * Locale("zh", "CN"); ws.setLocale(locale); ws.setEncoding("utf-8");
	 * WritableWorkbook wbook; wbook = Workbook.createWorkbook(os, workbook,
	 * ws); WritableSheet pointSheet = wbook.getSheet("点位");// sheet名称
	 * WritableSheet modbusSheet = wbook.getSheet("MODBUS");// sheet名称
	 * WritableSheet opcSheet = wbook.getSheet("OPC");// sheet名称 WritableFont
	 * NormalFont = new WritableFont(WritableFont.createFont("宋体"), 11);
	 * jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#"); // 设置数字格式
	 * jxl.write.NumberFormat dob = new jxl.write.NumberFormat("#.##"); //
	 * 设置数字格式 jxl.write.WritableCellFormat formatInt = new
	 * jxl.write.WritableCellFormat(NormalFont, nf); // 设置表单格式
	 * jxl.write.WritableCellFormat formatDouble = new
	 * jxl.write.WritableCellFormat(NormalFont, dob); // 设置表单格式 DateFormat df =
	 * new jxl.write.DateFormat("yyyy/MM/dd"); WritableCellFormat formatDate =
	 * new WritableCellFormat(NormalFont, df); SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy/MM/dd"); List<Map<String, String>> sdcdPointList =
	 * new ArrayList<Map<String, String>>(); List<Map<String, String>> opcList =
	 * new ArrayList<Map<String, String>>(); List<Map<String, String>>
	 * modbusList = new ArrayList<Map<String, String>>(); sdcdPointList =
	 * sqlSession.selectList("sdcdPointList"); if (sdcdPointList.size() > 0) {
	 * pointSheet.addCell(new Label(1, 1, uploadFileFileName.split("_")[0]));
	 * for (int i = 0; i < sdcdPointList.size(); i++) { Object obj2 =
	 * sdcdPointList.get(i).get("id"); if (!StringUtil.isEmpty(obj2)) {
	 * pointSheet.addCell(new Label(0, i + 3, obj2.toString())); } Object
	 * project_point = sdcdPointList.get(i).get("project_point"); if
	 * (!StringUtil.isEmpty(project_point)) { pointSheet.addCell(new Label(1, i
	 * + 3, project_point.toString())); } Object protocol =
	 * sdcdPointList.get(i).get("protocol"); if (!StringUtil.isEmpty(protocol))
	 * { String procol = protocol.toString(); if (procol.contains("OPC")) {
	 * pointSheet.addCell(new Label(2, i + 3, "90000")); pointSheet.addCell(new
	 * Label(3, i + 3, "1")); opcList.add(sdcdPointList.get(i)); } else {
	 * pointSheet.addCell(new Label(2, i + 3, "600000"));
	 * modbusList.add(sdcdPointList.get(i));// Object real_id =
	 * sdcdPointList.get(i).get("real_id"); if (!StringUtil.isEmpty(real_id)) {
	 * pointSheet.addCell(new Label(3, i + 3, "1")); } else {
	 * pointSheet.addCell(new Label(3, i + 3, "2")); } } } Object point_desc =
	 * sdcdPointList.get(i).get("point_desc"); if
	 * (!StringUtil.isEmpty(point_desc)) { pointSheet.addCell(new Label(4, i +
	 * 3, point_desc.toString())); } } for (int a = 0; a < modbusList.size();
	 * a++) { Object obj2 = modbusList.get(a).get("id"); if
	 * (!StringUtil.isEmpty(obj2)) { modbusSheet.addCell(new Label(0, a + 3,
	 * obj2.toString())); } Object project_point =
	 * modbusList.get(a).get("project_point"); if
	 * (!StringUtil.isEmpty(project_point)) { modbusSheet.addCell(new Label(1, a
	 * + 3, project_point.toString())); } Object sdcd_ip =
	 * modbusList.get(a).get("sdcd_ip"); if (!StringUtil.isEmpty(sdcd_ip)) {
	 * modbusSheet.addCell(new Label(2, a + 3, sdcd_ip.toString())); } Object
	 * com = modbusList.get(a).get("com"); if (!StringUtil.isEmpty(com)) {
	 * modbusSheet.addCell(new Label(3, a + 3, com.toString())); } Object
	 * baud_rate = modbusList.get(a).get("baud_rate"); if
	 * (!StringUtil.isEmpty(baud_rate)) { modbusSheet.addCell(new Label(4, a +
	 * 3, baud_rate.toString())); } Object parity =
	 * modbusList.get(a).get("parity"); if (!StringUtil.isEmpty(parity)) {
	 * modbusSheet.addCell(new Label(5, a + 3, parity.toString())); } Object
	 * byte_size = modbusList.get(a).get("byte_size"); if
	 * (!StringUtil.isEmpty(byte_size)) { modbusSheet.addCell(new Label(6, a +
	 * 3, byte_size.toString())); } Object stop_bits =
	 * modbusList.get(a).get("stop_bits"); if (!StringUtil.isEmpty(stop_bits)) {
	 * modbusSheet.addCell(new Label(7, a + 3, stop_bits.toString())); } Object
	 * modbus_ip = modbusList.get(a).get("modbus_ip"); if
	 * (!StringUtil.isEmpty(modbus_ip)) { modbusSheet.addCell(new Label(8, a +
	 * 3, modbus_ip.toString())); } Object modbus_address =
	 * modbusList.get(a).get("modbus_address"); if
	 * (!StringUtil.isEmpty(modbus_address)) { modbusSheet.addCell(new Label(12,
	 * a + 3, modbus_address.toString())); } Object modbus_device_name =
	 * modbusList.get(a).get("modbus_device_name"); if
	 * (!StringUtil.isEmpty(modbus_device_name)) { modbusSheet.addCell(new
	 * Label(14, a + 3, modbus_device_name.toString())); } Object real_id =
	 * modbusList.get(a).get("real_id"); if (!StringUtil.isEmpty(real_id)) {
	 * modbusSheet.addCell(new Label(15, a + 3, real_id.toString())); } Object
	 * virtual_id = modbusList.get(a).get("virtual_id"); if
	 * (!StringUtil.isEmpty(virtual_id)) { modbusSheet.addCell(new Label(16, a +
	 * 3, virtual_id.toString())); } } for (int b = 0; b < opcList.size(); b++)
	 * { Object obj2 = opcList.get(b).get("id"); if (!StringUtil.isEmpty(obj2))
	 * { opcSheet.addCell(new Label(0, b + 3, obj2.toString())); } Object
	 * project_point = opcList.get(b).get("project_point"); if
	 * (!StringUtil.isEmpty(project_point)) { opcSheet.addCell(new Label(1, b +
	 * 3, project_point.toString())); } Object sdcd_ip =
	 * opcList.get(b).get("sdcd_ip"); if (!StringUtil.isEmpty(sdcd_ip)) {
	 * opcSheet.addCell(new Label(2, b + 3, sdcd_ip.toString())); }
	 * opcSheet.addCell(new Label(3, b + 3, "DCOM")); opcSheet.addCell(new
	 * Label(4, b + 3, "localhost")); opcSheet.addCell(new Label(5, b + 3,
	 * "Insight.OPCServerDA.1")); opcSheet.addCell(new Label(7, b + 3,
	 * "Administarat")); opcSheet.addCell(new Label(8, b + 3, "SH@hanqian"));
	 * opcSheet.addCell(new Label(10, b + 3, "1")); Object opc_point =
	 * opcList.get(b).get("opc_point"); if (!StringUtil.isEmpty(opc_point)) {
	 * opcSheet.addCell(new Label(11, b + 3, opc_point.toString())); }
	 * opcSheet.addCell(new Label(12, b + 3, "15000")); } } wbook.write(); //
	 * 写入文件 wbook.close(); workbook.close(); ins.close();
	 * 
	 * } catch (Exception e1) { e1.printStackTrace(); } }
	 */

}
