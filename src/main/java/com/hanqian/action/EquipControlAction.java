package com.hanqian.action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jfree.util.Log;

import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.DbAlarmBusiness;
import com.hanqian.business.DbControldataBusiness;
import com.hanqian.business.EquipGroupBusiness;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.EquipServiceBusiness;
import com.hanqian.business.EquipTypeBusiness;
import com.hanqian.business.MenusBusiness;
import com.hanqian.form.VOElevatorPoint;
import com.hanqian.form.VOEquipGroup;
import com.hanqian.form.VOIllmineFloor;
import com.hanqian.form.VOIllminePoint;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.DbEquipType;
import com.hanqian.pojo.DbPointEquip;
import com.hanqian.pojo.EquipService;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 描 述: 实时监控 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @version 1.4 2012-9-13
 * @see
 */
@SuppressWarnings("all")
public class EquipControlAction extends ActionSupport {

	private static final long serialVersionUID = 2029318058729777012L;
	private HttpServletRequest request;
	private BuildingBusiness buildBusiness; // 楼宇类型
	private List listBuilding; // 楼宇信息列表
	private List listEnergyBuilding; // 楼宇信息列表
	private EquipListBusiness equipListBusiness;
	private Page page;
	private Integer pageSize = 10;
	private Integer currentPage = 1;
	private DbEquipList dbEquipList;
	private List listEquipList;
	private List<Integer> floorList; // 楼层集合
	private EquipTypeBusiness equipTypeBusiness;
	private DbAlarmBusiness dbAlarmBusiness;
	private DbPointEquip dbPointEquip;
	private List alarmList; // 告警信息
	private EquipGroupBusiness equipGroupBusiness;
	private String eqTypeId;
	private List<VOEquipGroup> equipGroupList;
	private DbControldataBusiness dbControlDataBusiness;
	private MenusBusiness menuBusiness;
	private List<VOEquipGroup> groupSelectList;
	private Page gpage;
	private List childEquipList;
	private List childItemList;
	private List buildList;
	private List menuTabList;
	//测试的时候使用src开头的目录，上线的时候使用没有src开头的目录
private static final String basePath = "//src//main//webapp//manager//monitoring//realtimeXMl//temp//";
//	private static final String basePath = "//manager//monitoring//realtimeXMl//temp//";
	private List powerList;
	private List listItem;
	private EquipServiceBusiness equipServiceBusiness;
	private List<EquipService> equipServices;

	private HttpServletRequest getRequest(HttpServletRequest request) {
		if (request == null) {
			request = ServletActionContext.getRequest();
		}
		return request;
	}
	/**
	 * 返回监控设备列表
	 * 
	 * @return
	 */
	public String showEquipList() {
		request = ServletActionContext.getRequest();
		// 读取权限,显示选项卡菜单
		request.setAttribute("eqTypeId", request.getParameter("eqTypeId"));
		String childTypeId = request.getParameter("childTypeId");
		if (StringUtils.isNotEmpty(childTypeId)) {
			request.setAttribute("childTypeId", childTypeId);
		}
		/**
		 * 获取医院的名称，对应的大写英文
		 */
		String yiyuanName = (String) ServletActionContext.getRequest()
		.getSession().getAttribute("currentHospCode");
		String falg ="";
		if(("RJ").equals(yiyuanName)){
			/**
			 * 【仁济】太平间独立要求
			 */
			falg=request.getParameter("falg");
			if(falg==null){
				falg="3";
			}	
		}else if(("XK").equals(yiyuanName)){
			/**
			 * 【胸科】电力安全独立要求
			 */
			falg=request.getParameter("falg");
			if(falg==null){
				falg="2";
			}
		}
		request.setAttribute("falg", falg);
		String equipTypeId = request.getParameter("eqTypeId");
		String parentMenuCode = "";
		if (StringUtils.isNotEmpty(equipTypeId)) {
			if ("1".equals(equipTypeId)) {// 空调系统
				parentMenuCode = "4003";
			} else if ("10".equals(equipTypeId)) { // 能源计量
				parentMenuCode = "4012";
			} else if ("12".equals(equipTypeId)) { // 天然气
				parentMenuCode = "4014";
			}
			else if ("13".equals(equipTypeId)) { // 第三方点位信息查询
				//给个标识符判断是否是第三方点位信息
				request.setAttribute("threePoint", "threePoint");
				parentMenuCode = "4015";
			}
			// 读取权限,显示选项卡菜单
			menuTabList = menuBusiness.findEquipForEquip(parentMenuCode,
					equipTypeId);
		}
		// 显示列表页面
		return "show";
	}

	/**
	 * 根据楼宇，设备编号获得设备信息
	 * 
	 * @return
	 */
	public void findEquipByParam() {
		request = ServletActionContext.getRequest();
		dbEquipList = new DbEquipList();
		listEquipList = new ArrayList();
		RetCode rc = new RetCode();
		DbBuilding build = new DbBuilding();
		floorList = new ArrayList<Integer>();
		DbEquipType dbEquipType = new DbEquipType();
		request = getRequest(request);
		// 获得楼宇Id
		String buildId = request.getParameter("buildId");
		request.setAttribute("buildId", buildId);
		// 获得设备类型Id
		String eqTypeId = request.getParameter("eqTypeId");

		request.setAttribute("eqTypeId", eqTypeId);
		if (StringUtils.isNotEmpty(buildId)) {
			build.setBuildingId(Integer.parseInt(buildId));
			dbEquipList.setDbBuilding(build);
			build = buildBusiness.findBuildingById(build);
		}
		// 设备类型为监控
		dbEquipList.setControlFlag(Short.parseShort("1"));
		// 根据楼宇获得楼层
		if (build != null && build.getBuildingId() != null) {
			if (build.getStoreyNumUp() > 0) {
				floorList.add(build.getStoreyNumUp() + 1);
			}
			for (int i = build.getStoreyNumUp(); i > 0; i--) {
				floorList.add(i);
			}
			for (int i = 1; i <= build.getStoreyNumDown(); i++) {
				floorList.add(0 - i);
			}
		}
		if (StringUtils.isNotEmpty(eqTypeId)) {
			dbEquipType.setEquipTypeId(Integer.parseInt(eqTypeId));
			dbEquipList.setDbEquipType(dbEquipType);
			listBuilding = (List) buildBusiness.findBuildByParam(dbEquipList)
					.getObj();
		}
		rc = equipListBusiness.findByEquipControl(dbEquipList);
		if (rc.getObj() != null) {
			listEquipList = (List) rc.getObj();
		}

	}

	/**
	 * 展示监控页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findToControl() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("X-XSS-Protection", "0");
		request = ServletActionContext.getRequest();
		dbEquipList = new DbEquipList();
		String equipTypeId = null;
		String yiyuanName = (String) ServletActionContext.getRequest()
		.getSession().getAttribute("currentHospCode");
		String equipId = request.getParameter("equipId");
		if (StringUtils.isNotEmpty(request.getParameter("eqTypeId"))) {
			equipTypeId = request.getParameter("eqTypeId");
		}
		// 跳转页面
		String result = eqTypeId;

		String groupId = request.getParameter("groupId");
		String groupCode = request.getParameter("groupCode");
		request.setAttribute("showFlag", request.getParameter("showFlag"));
		String sysTm = request.getParameter("sysTm"); // (水冷机组，系统时间标志)
		String groupName = request.getParameter("groupName"); // 设备组名称
		if (StringUtils.isNotEmpty(groupName)) {
			try {
				groupName = URLDecoder.decode(groupName, "UTF-8");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (StringUtils.isNotEmpty(groupId)) {
			int size = findAllGroupAlarmPoint(groupId);
			request.setAttribute("alarmCount", size);
		}
		if (equipTypeId != null && equipTypeId.equals("4")) {// 电梯
			int currentgPage = 1;
			String gpage = request.getParameter("gPage");
			if (!SysUtil.isNull(gpage)) {
				currentgPage = Integer.parseInt(gpage);
			}
			String buildId = request.getParameter("buildId"); // 楼编号
			RetCode rc = dbControlDataBusiness.findElevatorControlData(groupId,
					currentgPage, 5);
			if (rc != null) {
				request.setAttribute("page", rc.getPage());
				request.setAttribute("currentPage", rc.getPage()
						.getTotalPages());
			} else {
				request.setAttribute("page", null);
				request.setAttribute("currentPage", 0);
			}
			request.setAttribute("groupId", groupId);
			request.setAttribute("groupCode", groupCode);
			request.setAttribute("buildId", buildId);
			request.setAttribute("gPage", currentgPage);
			request.setAttribute("groupName", groupName);
			return equipTypeId;
		}
		if (equipTypeId.equals("10001")) {
			// 能源计量
			return showPowerContorl();
		}
		if (equipTypeId.equals("9")) {// 配电
			return findToPeiDian(groupId);
		}
		if (equipTypeId != null && equipTypeId.equals("3")) {// 照明
			RetCode outdoorRt = dbControlDataBusiness.findOutdoorIllumination();
			request.setAttribute("outdoorRt", (List) outdoorRt.getObj());
			String buildId = request.getParameter("buildId"); // 楼编号
			request.setAttribute("buildId", buildId);
			request.setAttribute("groupId", groupId);
			request.setAttribute("groupCode", groupCode);
			request.setAttribute("groupName", groupName);
			return equipTypeId;
		}
		request.setAttribute("eqTypeId", equipTypeId);
		if (StringUtils.isNotEmpty(equipTypeId)) {
			request.setAttribute("eqTypeId", eqTypeId);
			if (StringUtils.isNotEmpty(eqTypeId)) {
				// 判断是否设备类型是否分组
				DbEquipType dbEquipType = new DbEquipType();
				dbEquipType = equipTypeBusiness.findById(Integer
						.parseInt(equipTypeId));
				if (dbEquipType != null) {
					// 如果分组(设备组)
					if (dbEquipType.getGroupStatus()) {
						// 生成点位读数
						dbControlDataBusiness.findGroupControlData(groupId,
								equipTypeId);
						if (StringUtils.isNotEmpty(groupId)) {
							int size = findAllGroupAlarmPoint(groupId);
							request.setAttribute("alarmCount", size);
						}
						// 返回键值对Map集合，展示所有的监控点位的设备ID
						request.setAttribute("controlPoint",
								findEquipMap(groupId));
					} else {
						// 生成点位读数
						dbControlDataBusiness.findEquipControlData(equipId,
								equipTypeId);
						if (StringUtils.isNotEmpty(equipId)) {
							List list = findEquipAlarmPoint(equipId);
							request.setAttribute("alarmCount", list.size());
						}
					}
				}
			}
			RetCode AirFLSL = new RetCode();
			// 根据设备类型，生成实时状态XML文档
			switch (Integer.parseInt(equipTypeId)) {
				case 1001 :
					// 新风机
				try {
					request.setAttribute("xfj", createFlashEquipXML(equipId,
							equipTypeId));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					findEquipByParam();
					break;
				case 1002 :
					// 空调机
					// createFlashEquipXML(equipId, equipTypeId);
					// 获取空调机状态
					String record1 = createFlashEquipXML(equipId, equipTypeId);
					request.setAttribute("ktjStatus", record1);
					// 返回值为1 转动，其他全部为停止

					findEquipByParam();
					break;
				case 1004 :
				try {
						AirFLSL = dbControlDataBusiness.findAirFlSljzControl();
						List listSLSL=(List) AirFLSL.getObj();
						if(listSLSL!=null){
						request.setAttribute("AirFLSL", listSLSL);
						}else{
							request.setAttribute("AirFLSL", "");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 水冷机组
					request.setAttribute("dataJson", createFlashGroupXML(
							groupCode, groupId, equipTypeId, sysTm, request));
					break;
				case 1005 :
					try {
					 AirFLSL = dbControlDataBusiness.findAirFlSljzControl();
					 List listFLSL=(List) AirFLSL.getObj();
						if(listFLSL!=null){
						 request.setAttribute("AirFLSL", listFLSL);
						}else{
							request.setAttribute("AirFLSL", "");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 风冷机组
					request.setAttribute("dataFL", createFlashGroupXML(
							groupCode, groupId, equipTypeId, null, request));
					break;
				case 2 :
					// 锅炉
					request.setAttribute("dataGL", createFlashGroupXML(
							groupCode, groupId, equipTypeId, null, request));
					break;
				case 5 :
					// 生活水
					request.setAttribute("df", createFlashGroupXML(groupCode,
							groupId, equipTypeId, null, request));
					break;
				case 6 :
					String currentHospCode = "";
					if (request.getSession().getAttribute("currentHospCode") != null) {
						currentHospCode = (String) request.getSession()
								.getAttribute("currentHospCode");
					}
					// 集水井
					List list2 = this.findXYElement(request
							.getRealPath(File.separator)
							+ "manager"
							+ File.separator
							+ "monitoring"
							+ File.separator
							+ "control"
							+ File.separator
							+ "jsj"
							+ File.separator
							+ currentHospCode
							+ File.separator + "jsjXY.xml", groupCode);
					 
					StringBuffer sb = new StringBuffer(""); // 获取 falsh 串号
					String htmlDiv = setJsjInfo(groupId, equipTypeId, request,
							list2, sb);
					request.setAttribute("textHtml", htmlDiv); // 传给jsp
					// 保存 falsh 串号
					request.setAttribute("jsjStatus", sb.toString());
					break;
				case 8 : // 空压机\
					String buildId = request.getParameter("buildId"); // 楼编号
					request.setAttribute("buildId", buildId);
					request.setAttribute("groupId", groupId);
					request.setAttribute("groupCode", groupCode);
					request.setAttribute("groupName", groupName);
					request.setAttribute("equipTypeId", equipTypeId);
					request.setAttribute("dataJson", createFlashGroupXML(
							groupCode, groupId, equipTypeId, null, request));
					break;
				case 11 :
					// 负压吸引
					request.setAttribute("dataFY", createFlashGroupXML(
							groupCode, groupId, equipTypeId, null, request));
					break;
				case 7 :
					// 液氧
					request.setAttribute("dataYeYang", createFlashGroupXML(
							groupCode, groupId, equipTypeId, null, request));
					break;

			}

			// 获得监控设备名
			if (StringUtils.isNotEmpty(equipId)) {
				request.setAttribute("equipId", equipId);
				// 查询设备信息，获取设备名称
				dbEquipList = equipListBusiness.findDbEquipListById(Integer
						.parseInt(equipId));
				//空调系统增加标题(服务区域显示)
				/**
				 * 曙光【独立要求：增加服务区域名称】
				 * 
				 * 显示新加服务区域位置：空调机flash图上面加。
				 * 
				 * Date：2015-06-29 修改
				 */
				if("SG".equals(yiyuanName)){
					RetCode rc1 = equipListBusiness.findDbEquipListByIdFuWu(Integer
							.parseInt(equipId));
					if (rc1.getObj() != null) {
					List DbequipListFuwuName = (List) rc1.getObj();
							request.setAttribute("DbequipListFuwuName3", DbequipListFuwuName);
						}
				}
				if (dbEquipList != null) {
					request.setAttribute("equipName", dbEquipList
							.getEquipName());
				}
			}
			if (StringUtils.isNotEmpty(groupId)) {
				request.setAttribute("groupId", groupId);
				request.setAttribute("groupCode", groupCode);
				List list = equipGroupBusiness.findEquipGroup(groupId);
				if (list.size() > 0) {
					Map map = (Map) list.get(0);
					request.setAttribute("groupName", map.get("group_name"));
				}
			}
		}
		return result;
	}

	/**
	 * 用于监控页面Ajax后台请求,达到页面无刷新效果
	 * 
	 * @param 2013-2-21
	 * @param
	 * @return void
	 */
	public void reFreshAjax() {
		request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String equipTypeId = null;
		try {
			String equipId = request.getParameter("equipId");
			String groupId = request.getParameter("groupId");
			String groupCode = request.getParameter("groupCode");
			if (StringUtils.isNotEmpty(request.getParameter("eqTypeId"))) {
				equipTypeId = request.getParameter("eqTypeId");
				// 判断是否设备类型是否分组
				DbEquipType dbEquipType = new DbEquipType();
				dbEquipType = equipTypeBusiness.findById(Integer
						.parseInt(equipTypeId));
				if (dbEquipType != null) {
					// 如果分组(设备组)
					if (dbEquipType.getGroupStatus()) {
						// 生成点位读数
						dbControlDataBusiness.findGroupControlData(groupId,
								equipTypeId);
						if (StringUtils.isNotEmpty(groupId)) {
							int size = findAllGroupAlarmPoint(groupId);
							request.setAttribute("alarmCount", size);
						}
						// 返回键值对Map集合，展示所有的监控点位的设备ID
						request.setAttribute("controlPoint",
								findEquipMap(groupId));
					} else {
						// 生成点位读数
						dbControlDataBusiness.findEquipControlData(equipId,
								equipTypeId);
						if (StringUtils.isNotEmpty(equipId)) {
							List list = findEquipAlarmPoint(equipId);
							request.setAttribute("alarmCount", list.size());
						}
					}
				}
				StringBuffer returnFalsh = new StringBuffer();
				// 根据设备类型，生成flash所需的特定的字符串
				switch (Integer.parseInt(equipTypeId)) {
					case 1001 :
						// 新风机
						// 获取读数
						String record1 = createFlashEquipXML(equipId,
								equipTypeId);
						response = ServletActionContext.getResponse();
						PrintWriter out1 = response.getWriter();
						out1.print(record1);
						break;
					case 1002 :
						// String record1 = createFlashEquipXML(equipId,
						// equipTypeId);
						// 空调机
						String record2 = createFlashEquipXML(equipId,
								equipTypeId);
						response = ServletActionContext.getResponse();
						PrintWriter out2 = response.getWriter();
//						//System.out.println(record2);
						out2.print(record2);

						break;
					case 1004 :
						// 水冷机组---1 .系统时间 2.点位是否运行-----状态读数
						String sysTm = request.getParameter("sysTm"); // (水冷机组，系统时间标志)
						if (!StringUtils.isNotEmpty(sysTm)) {
							// 获取系统月份
							Calendar calendar = Calendar.getInstance(TimeZone
									.getDefault(), Locale.CHINESE);
							calendar.setTime(new Date());
							int month = calendar.get(Calendar.MONTH) + 1;
							if (month >= 5 && month <= 10) {
								sysTm = "1";
							} else {
								sysTm = "2";
							}
						}
						request.setAttribute("sysTm", sysTm);
						// 获取读数
						String record = createFlashGroupXML(groupCode, groupId,
								equipTypeId, sysTm, request);
						response = ServletActionContext.getResponse();
						PrintWriter out = response.getWriter();
						out.print(record);
						break;
					case 1005 :
						// 风冷机组
						String recordFL = createFlashGroupXML(groupCode,
								groupId, equipTypeId, null, request);
						response = ServletActionContext.getResponse();
						PrintWriter outFL = response.getWriter();
						outFL.print(recordFL);
						break;
					case 2 :
						// 锅炉
						String recordGL = createFlashGroupXML(groupCode,
								groupId, equipTypeId, null, request);
						response = ServletActionContext.getResponse();
						PrintWriter outGL = response.getWriter();
						outGL.print(recordGL);
						break;
					case 5 :
						// 生活水
						request = ServletActionContext.getRequest();
						String record5 = createFlashGroupXML(groupCode,
								groupId, equipTypeId, null, request);
						response = ServletActionContext.getResponse();
						PrintWriter out5 = response.getWriter();
						out5.print(record5);
						break;
					case 6 :
						// 集水井
						String currentHospCode = "";
						if (request.getSession()
								.getAttribute("currentHospCode") != null) {
							currentHospCode = (String) request.getSession()
									.getAttribute("currentHospCode");
						}
						List list2 = this.findXYElement(request
								.getRealPath(File.separator)
								+ "manager"
								+ File.separator
								+ "monitoring"
								+ File.separator
								+ "control"
								+ File.separator
								+ "jsj"
								+ File.separator
								+ currentHospCode
								+ File.separator + "jsjXY.xml", groupCode);

						StringBuffer sb = new StringBuffer(""); // 获取 falsh 串号
						String htmlDiv = setJsjInfo(groupId, equipTypeId,
								request, list2, sb);

						// 将串号给ajax
						response = ServletActionContext.getResponse();
						response.setContentType("text/html; charset=GBK");
						PrintWriter outFY1 = response.getWriter();
						outFY1.print(sb.toString() + "%jsj%" + htmlDiv);

						break;
					case 8 :
						// 空压机
						response = ServletActionContext.getResponse();
						response.setCharacterEncoding("UTF-8");
						//获取医院编号
						currentHospCode = (String)request.getSession().getAttribute("currentHospCode");
						//根据医院不同，使用不同方法
							String recordKY = createFlashGroupXML(groupCode,
									groupId, equipTypeId, null, request);
							response = ServletActionContext.getResponse();
							PrintWriter outKY = response.getWriter();
							outKY.print(recordKY);
						break;
					case 11 :
						// 负压吸引
						String recordFY = createFlashGroupXML(groupCode,
								groupId, equipTypeId, null, request);
						response = ServletActionContext.getResponse();
						PrintWriter outFY = response.getWriter();
						outFY.print(recordFY);
						break;
					case 7 :
						// 医用气体--液氧
						String recordYeYang = createFlashGroupXML(groupCode,
								groupId, equipTypeId, null, request);
						response = ServletActionContext.getResponse();
						PrintWriter outYeYang = response.getWriter();
						outYeYang.print(recordYeYang);
						break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 创建一个复杂的JSON
	 * 
	 * @param 2013-2-20
	 * @param
	 * @return void
	 */
	public void createJson(String equipId, String json) {
		request = ServletActionContext.getRequest();
		// 获得当前类所在的路径
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath().toString();
		// 文件的保存目录
		File temp = new File(path);
		// 创建一个存放文件文件夹
		temp = new File(temp.getParentFile().getParentFile().getPath());
		if (!temp.exists()) {
			temp.mkdirs(); // 创建文件夹目录
		}
		File file = new File(temp.getPath()
				+ "/src/main/webapp/manager/monitoring/controlJson/test");
		if (!file.exists()) {
			file.mkdirs(); // 创建文件夹目录
		}
		// 书写当前组内所有的监控的json字符串（equipJSON）

		// 书写当前设备组内所有监控设备的点位读数字符串（controlDataJson）

		// 组装这两个小JSON为一个大型JSON,其中json中可以自定义一些指令和特殊的属性，生成为服务器端的JSON文本文件，存在指定的目录下

		try {
			File realFile = new File(file, eqTypeId + "_" + equipId + ".json");
			if (realFile.exists()) {
				realFile.delete();
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(realFile), "UTF-8"));
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// 集水井 特殊方法
	public String setJsjInfo(String groupId, String eqTypeId,
			HttpServletRequest request, List list2, StringBuffer sb) {
		JSONObject jsonObject = new JSONObject();
		String hospCode = request.getSession().getAttribute("currentHospCode").toString();
		// 将所有 div放入 textHtml
		StringBuffer textHtml = new StringBuffer();
		String divbegin = "<div class='jishuijing_bg' onclick='divHide(this)' title='集水井' id='jsj_id' style='position: absolute; z-index: 1000; display: none; "
				+ " topLeft'>"
				+ "<table border='0' width='100' align='center'>"
				+ "<tr><td colspan='3' height='1'></td></tr>";

		String divend = " </table></div>";

		int count = 0;
		if (list2 != null && !list2.isEmpty()) {// 加入非空判断
			for (Iterator iterator2 = list2.iterator(); iterator2.hasNext();) {
				Element e = (Element) iterator2.next();
				StringBuffer divStr = new StringBuffer("");
				// 第一次进来获取widht，height 宽度与高度
				if (count == 0) {
					String width = e.getParent().attributeValue("flashWidth");
					String height = e.getParent().attributeValue("flashHeight");
					request.setAttribute("flashWidth", width);
					request.setAttribute("flashHeight", height);
					count++;
				}
				try {
					// 获取井号
					String jsjIndex = e.attributeValue("bengIndex");
					// 设置div的title
					String temp = divbegin.replaceAll("集水井", "集水井" + jsjIndex);

					// 设置 div id
					temp = temp.replaceAll("jsj_id", "jsj_" + jsjIndex);

					// 子节点里 点位信息
					List pointLst = e.selectNodes("*");
					// 循环获取节点力 point点位
					List<Map> listId = new ArrayList();
					for (Iterator iterator3 = pointLst.iterator(); iterator3
							.hasNext();) {
						Element e1 = (Element) iterator3.next();
						Map map=new HashMap();
						
						String id = e1.attributeValue("id");
						String num = e1.attributeValue("num");
						map.put("id", id);
						if(num!=null)
							map.put("number",num);
						listId.add(map);
					}
					// 根据集合id 获取 数据
					RetCode rc = dbControlDataBusiness
							.findControlDataByGroupJsj(groupId, listId);
					appendJsjHtml(divStr, sb, (List) rc.getObj(),e.attributeValue("keyNameRed"),e.attributeValue("keyNameGreen"),hospCode,jsonObject);
					
					if(!iterator2.hasNext()){
						sb.append(jsonObject.toString());
					}
					// 当有3个排水泵时 为true时 改变css
					if (pointLst.size() == 4) {
						temp = temp.replaceAll("class='jishuijing_bg",
								"class='jishuijing_bg4'");
					}
					// 拼接 ，形成 div
					textHtml.append(temp + divStr.toString() + divend + "");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return textHtml.toString();
	}
	
	/**
	 * 集水井监控页面
	 * @param divStr
	 * @param falshStr
	 * @param lst
	 * @param keyNameRed
	 * @param keyNameGreen
	 * @param hospCode
	 * @param jsonObject
	 */
	public void appendJsjHtml(StringBuffer divStr, StringBuffer falshStr,
			List lst,String keyNameRed,String keyNameGreen,String hospCode,JSONObject jsonObject) {
		
		int pasBoolCount = 0;
		boolean psbBool = true;
		boolean yishuiBool=false;  //集水井溢水告警标识
		for (int i = 0; i < lst.size(); i++) {
			Map map = (Map) lst.get(i);
			
			
			// 如果是溢水报警
			if (map.get("eqtypeid").toString().equals("6001")) {
				/**
				 * 修改  去掉后缀"。0"
				 * 
				 * Date：2015-07-08
				 * 
				 */
				// 设置 溢水报警的 div
				String o=SysUtil.subZeroAndDot((Double)map.get("record")+"");
				//Object o = map.get("record");
				String str = (!("null").equals(o))? o : "0";

				if (str != null && str.equals("0")) {
					// 正常
					divStr
							.append("<tr><td>溢水状态</td><td><div class='shishijiankong_yxzt4' /></td></tr>");
					// falsh 需要串号
				} else if (str != null && str.equals("1")) {
					// 报警
					divStr
							.append("<tr><td>溢水状态</td><td><div class='shishijiankong_yxzt7' /></td></tr>");
					// falsh 需要串号
						yishuiBool=true;
				} else {
					// 如果 找不到值，也为 故障
					divStr
							.append("<tr><td>溢水状态</td><td><div class='shishijiankong_yxzt7' /></td></tr>");
					// falsh 需要串号
						yishuiBool=true;
				}
			} else {
				/**
				 * 修改  去掉后缀"。0"
				 * 
				 * Date：2015-07-08
				 * 
				 */
				// 设置 溢水报警的 div
				String o=SysUtil.subZeroAndDot((Double)map.get("record")+"");
				// 否则为 排水泵判断
				// 将值读取
				//Object o = map.get("record");
				String point = (!("null").equals(o))? o : "0";
				//String point = o != "null" ? o : "0";
				// 故障状态 如果 为1直接 为故障状态
				if ((point.equals("-1") || point.equals("1"))
						&& map.get("project_point").toString().indexOf("02_CV") != -1) {
					// System.out.println(" 排水泵：故障"+i);
					divStr
							.append("<tr><td>排水泵"
									+ map.get("bengnum")
									+ "#</td><td><div class='shishijiankong_yxzt3' /></td></tr>");
					i++;
					psbBool = false;
					continue;
				} else if (map.get("project_point").toString().indexOf("01_CV") != -1) {
					// 获取下一行 运行状态点位
					map = (Map) lst.get(i);
					/**
					 * 修改  去掉后缀"。0"
					 * 
					 * Date：2015-07-08
					 * 
					 */
					o = SysUtil.subZeroAndDot((Double)map.get("record")+"");
					//o = map.get("record");
					// 判断监控读数
					//point = o != null ? o.toString() : "0";
				    point = (!("null").equals(o))? o : "0";
					// 判断是否是运行
					if (point != null
							&& (point.equals("1") || point.equals("-1"))) {
						// 运行
						// System.out.println(" 排水泵：运行"+i);
						divStr
								.append("<tr><td>排水泵"
										+ map.get("bengnum")
										+ "#</td><td><div class='shishijiankong_yxzt1' /></td></tr>");
					} else if (point != null && point.equals("0")) {
						// 停止
						// System.out.println(" 排水泵：停止"+i);
						divStr
								.append("<tr><td>排水泵"
										+ map.get("bengnum")
										+ "#</td><td><div class='shishijiankong_yxzt2' /></td></tr>");
						pasBoolCount++;

					}
				}
			}
		}
			if(psbBool==false || yishuiBool){ //任何一项告警，则显示为告警
				jsonObject.accumulate(keyNameRed, "0");
				jsonObject.accumulate(keyNameGreen, "1");
			}else{
				if(psbBool && (lst.size() - 1) / 2 == pasBoolCount){
					jsonObject.accumulate(keyNameRed, "1");
					jsonObject.accumulate(keyNameGreen, "1");
				}else if(psbBool && !((lst.size() - 1) / 2 == pasBoolCount)){
					jsonObject.accumulate(keyNameRed, "1");
					jsonObject.accumulate(keyNameGreen, "0");
				}
			}
	}
	
	/**
	 * 查询监控读数
	 * @param lst
	 * @param lstStartIndex
	 * @param bengWhere
	 * @return
	 */
	public String findControlRecord(List lst, int lstStartIndex,
			String bengWhere) {
		for (int i = lstStartIndex; i < lst.size(); i++) {
			Map map = (Map) lst.get(i);
			String descr = map.get("descr").toString();
			if (descr.indexOf(bengWhere) != -1) {
				/**
				 * 修改  去掉后缀"。0"
				 * 
				 * Date：2015-07-08
				 * 
				 */
				// 设置 溢水报警的 div
				String o=SysUtil.subZeroAndDot((Double)map.get("record")+"");
				//Object o = map.get("record");
				 String record = (!("null").equals(o))? o : "0";
				if (record.equals("")) {
					return "2";
				} else if (record.equals("0")) {
					return "0";
				} else if (record.equals("1")) {
					return "1";
				}
			} else {
				continue;
			}

		}
		return null;
	}

	/**
	 * 获得所有监控设备的ID，以键值对的形式展现出来
	 * @param groupId
	 * @return
	 */
	public String findEquipMap(String groupId) {
		RetCode rc = new RetCode();
		Map mapList = new HashMap();
		rc = equipListBusiness.findIdListByGroupId(groupId);
		List list = new ArrayList();
		if (rc.getObj() != null) {
			list = (List) rc.getObj();
		}
		JSONArray ja = JSONArray.fromObject(list);
		return ja.toString();
	}

	/**
	 * 获取所有分组设备的告警点位数量（分组）
	 * 
	 * @param equipTypeId
	 * @param request
	 */
	public Integer findAllGroupAlarmPoint(String groupId) {
		RetCode rc = new RetCode();
		rc = dbAlarmBusiness.findAlarmPointByGroup(groupId);
		if (rc.getObj() != null) {
			List list = (List) rc.getObj();
			return list.size();
		} else {
			return 0;
		}
	}

	/**
	 * 显示配电系统
	 * 
	 * @param 2012-11-18
	 * @param @param eqTypeId
	 * @param @return
	 * @return String
	 */
	public String findToPeiDian(String groupId) {
		RetCode rc = new RetCode();
		RetCode rt = new RetCode();
		childItemList = new ArrayList();
		if (StringUtils.isNotEmpty(groupId)) {
			rc = equipGroupBusiness.findChildGroup(groupId);
		}
		if (rc.getObj() != null) {
			childItemList = (List) rc.getObj();
		}
		rt = equipGroupBusiness.findById(groupId);
		if (rt.getObj() != null) {
			List list = (List) rt.getObj();
			if (list.size() > 0) {
				Map haspMap = new HashMap();
				haspMap = (Map) list.get(0);
				request.setAttribute("groupName", haspMap.get("group_name"));
			}
		}
		return "peiDianPage";
	}

	/**
	 * 查询单个设备告警点位数量（不分组）
	 * @param equipId
	 * @return
	 */
	public List findEquipAlarmPoint(String equipId) {
		alarmList = new ArrayList();
		RetCode rc = dbAlarmBusiness.findAlarmPointByEquip(equipId);
		if (rc.getObj() != null) {
			alarmList = (List) rc.getObj();
		}
		return alarmList;
	}

	/**
	 * 查询组相关的设备信息
	 * 
	 * @param 2012-10-08
	 * @param @return
	 * @return String
	 */
	public String showGroupEquip() {
		request = ServletActionContext.getRequest();
		String groupId = request.getParameter("seq");
		request.setAttribute("seq", groupId);
		String curpage = request.getParameter("curpage");
		listItem = new ArrayList();
		pageSize = 5;
		if (StringUtils.isNotEmpty(curpage)) {
			currentPage = Integer.parseInt(curpage);
		} else {
			currentPage = 1;
		}
		try {
			RetCode rc = equipListBusiness.findEquipList(groupId, currentPage,
					pageSize);
			if (rc.getObj() != null) {
				listItem = (List) rc.getObj();
				page = rc.getPage();
			}
		} catch (Exception e) {

		}
		return "childList";
	}

	/**
	 * 查询组相关的设备信息(返回页面)
	 * 
	 * @param 2012-10-08
	 * @param @return
	 * @return String
	 */
	public String showChildEquipList() {
		request = ServletActionContext.getRequest();
		String groupId = request.getParameter("seq");
		String curPage = request.getParameter("curpage");
		request.setAttribute("seq", groupId);
		childEquipList = new ArrayList();

		if (StringUtils.isNotEmpty(curPage)) {
			currentPage = Integer.parseInt(curPage);
		} else {
			currentPage = 1;
		}
		try {
			RetCode rc = equipListBusiness.findEquipList(groupId, currentPage,
					5);
			if (rc.getObj() != null) {
				childEquipList = (List) rc.getObj();
				request.setAttribute("page", rc.getPage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showChild";
	}

	/**
	 * 创建单个设备的Flash状态XML文档
	 * @param equipId
	 * @param eqTypeId
	 * @return
	 */
	public String createFlashEquipXML(String equipId, String eqTypeId) {
		String resultString = "";
		if (StringUtil.isNotEmpty(equipId) && StringUtil.isNotEmpty(eqTypeId)) {
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath().toString();
			String currentHospCode = (String) ServletActionContext.getRequest()
					.getSession().getAttribute("currentHospCode");
			File temp = new File(path);
			String rootpath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
			String filePath = rootpath + basePath + currentHospCode
					+ File.separator + eqTypeId + ".xml";
			filePath = StringUtil.replacePath(filePath);
			File realFile = new File(filePath);
			SAXReader reader = new SAXReader();
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			// 获得记录数
			RetCode rc = dbControlDataBusiness.findEquipment(equipId);
			int record = 0;
			if (rc.getObj() != null) {
				Map mapList = new HashMap();
				List list = (List) rc.getObj();
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					String projectPoint = map.get("project_point").toString();
					projectPoint = projectPoint.substring(projectPoint
							.indexOf(".") + 1, projectPoint.indexOf("_"));
					if (map.get("controlpoint") != null) {
						/**
						 * 修改  去掉后缀"。0"
						 * 
						 * Date：2015-07-08
						 * 
						 */
						// 设置 溢水报警的 div
						String o=SysUtil.subZeroAndDot((Double)map.get("record")+"");
						if(!("null").equals(o)){
							mapList.put(projectPoint, o);
						}else{
							mapList.put(projectPoint, null);
						}
					} else {
						mapList.put(projectPoint, null);
					}

				}
                 
				// 未告警的情况下，判断运行状态
				if (mapList.get("03") != null) {
					if (Math.abs(Integer.parseInt(mapList.get("03").toString())) == 1) {
//					if (Math.abs(Integer.parseInt(new java.text.DecimalFormat("0").format(mapList.get("03")))) == 1) {
						record = 2;
					} else {
						if (mapList.get("04") != null) {
							// 取绝对值
							record = Math.abs(Integer.parseInt(mapList
									.get("04").toString()));
//							record = Math.abs(Integer.parseInt(new java.text.DecimalFormat("0").format(mapList.get("04"))));
						} else {
							record = 3;
						}
					}
				} else {
					if (mapList.get("04") != null) {
						// 取绝对值
						record = Math.abs(Integer.parseInt(mapList.get("04")
								.toString()));
//						record = Math.abs(Integer.parseInt(new java.text.DecimalFormat("0").format(mapList.get("04"))));
					} else {
						record = 3;
					}
				}
			}
			//如果是新规则
            if(checkHospCodeWithFlash()){
            	JSONObject jsonObject = new JSONObject();
            	try{
					Document document = reader.read(realFile);// 读取XML文件
					Element root = document.getRootElement();// 得到根节点
					for (Iterator i = root.elementIterator("Equip"); i.hasNext();) {
						 Element node = (Element) i.next();
						 jsonObject.accumulate(node.attributeValue("keyName"),record);
					}
					resultString=jsonObject.toString();
            	}catch (Exception e) {
					// TODO: handle exception
				}
            }else{
            	resultString = record + "";
            }
		}
		return resultString;
	}

	/**
	 * 创建新文件
	 * 
	 * @param 2012-11-2
	 * @param @param filePath 文件路径
	 * @param @param fileName 文件名称（包括后缀）
	 * @param @return
	 * @return File
	 */
	private File createNewFile(String filePath, String fileName) {
		File realFile = null;
		try {
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath().toString();
			File temp = new File(path);
			String basePath = temp.getParentFile().getParentFile()
					.getParentFile().getPath(); // 项目根目录
			// 创建一个存放文件文件夹
			temp = new File(StringUtil.replacePath(basePath + filePath));
			if (!temp.exists()) {
				temp.mkdirs();
			}
			if (StringUtils.isNotEmpty(fileName)) {
				realFile = new File(
						StringUtil.replacePath(basePath + filePath), fileName);
			}
			return realFile;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取点位运行状态值
	 * 
	 * @param 2012-11-2
	 * @param @param eqTypeId
	 * @param @param run
	 * @param @param warn
	 * @param @return
	 * @return int
	 */
	private String getRecord(List itemList, String eqTypeId, String run,
			String warn) {
		String record = "";
		if (itemList.size() > 0) {
			Map mapList = new HashMap();
			String flag = "";
			for (int i = 0; i < itemList.size(); i++) {
				Map map = new HashMap();
				map = (Map) itemList.get(i);
				String equipId = map.get("equipid").toString();
				if (map.get("eqtypeid").toString().equals(eqTypeId)) {
					if (flag.trim() != "") {
						if (!flag.equals(map.get("equipid").toString())) {
							break;
						}
					} else {
						flag = equipId;
					}
					String projectPoint = map.get("project_point").toString();
					if(projectPoint.indexOf("_")>0){
						projectPoint = projectPoint.substring(projectPoint
								.indexOf(".") + 1, projectPoint.indexOf("_"));
					}else{
						projectPoint = projectPoint.substring(projectPoint
								.indexOf(".") + 1, projectPoint
								.indexOf(".") + 3);
					}
					if (map.get("controlpoint") != null) {
						Double recordNum=(Double)map.get("record");
						String records=SysUtil.subZeroAndDot(recordNum+"");
						if(("null").equals(records)){
						  mapList.put(projectPoint, null);
						}else{
							mapList.put(projectPoint,records);
						}
					} else {
						mapList.put(projectPoint, null);
					}
					itemList.remove(i);
					i -= 1;
				}
			}
			// 未告警的情况下，判断运行状态
			if (mapList.get(warn) != null) {
				if (Math.abs(Integer.parseInt(mapList.get(warn).toString())) == 1) {
					record = "2";
				} else {
					if (mapList.get(run) != null) {
						// 取绝对值
						record = String.valueOf(Math.abs(Integer
								.parseInt(mapList.get(run).toString())));
					} else {
						int re = Math.abs(Integer.parseInt(mapList.get(warn)
								.toString()));
						record = String.valueOf(re);
					}
				}
			} else {
				if (mapList.get(run) != null) {
					// 取绝对值
					record = String.valueOf(Math.abs(Integer.parseInt(mapList
							.get(run).toString())));
				} else {
					// 未监控
					record = "3";
				}
			}
		} else {
			// 未监控
			record = "3";
		}
		return record;
	}

	/**
	 * 创建设备组实时状态XML文档
	 * 
	 * @param equipId
	 * @param eqTypeId
	 */
	public String createFlashGroupXML(String groupCode, String groupId,
			String eqTypeId, String sysTm, HttpServletRequest request) {
		String record = "";
		if (StringUtil.isNotEmpty(groupCode)
				&& StringUtil.isNotEmpty(groupCode)) {
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath().toString();
			File temp = new File(path);
			String rootpath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
			List itemList = new ArrayList();
			RetCode rc = dbControlDataBusiness.findControlDataByGroup(groupId);
			if (rc.getObj() != null) {
				itemList = (List) rc.getObj();
			}
			String currentHospCode = (String) ServletActionContext.getRequest()
					.getSession().getAttribute("currentHospCode");

			String filePath = rootpath + basePath + currentHospCode
					+ File.separator + eqTypeId + "_" + groupCode + ".xml";
			filePath = StringUtil.replacePath(filePath);
			File realFile = new File(filePath);
			SAXReader reader = new SAXReader();
			if (!StringUtils.isNotEmpty(sysTm)) {
				// 获取系统月份
				Calendar calendar = Calendar.getInstance(TimeZone.getDefault(),
						Locale.CHINESE);
				calendar.setTime(new Date());
				int month = calendar.get(Calendar.MONTH) + 1;
				if (month >= 5 && month <= 10) {
					sysTm = "1";
				} else {
					sysTm = "2";
				}
			}
			request.setAttribute("sysTm", sysTm);
			// 如果存在
			if (realFile.exists()) {
				// 直接读取文件
				try {
					Document document = reader.read(realFile);// 读取XML文件
					Element root = document.getRootElement();// 得到根节点
					//判断Flash使用的规则
					if(checkHospCodeWithFlash()){
						// 循环遍历节点，用来获取运行状态值
						JSONObject jsonObject = new JSONObject();
						for (Iterator i = root.elementIterator("Equip"); i
								.hasNext();) {
							Element node = (Element) i.next();
							String tempRecord = getRecord(itemList, node
									.attributeValue("eqTypeId"), node
									.attributeValue("run"), node
									.attributeValue("warn"));
							String key=node.attributeValue("keyName");
							jsonObject.accumulate(key,tempRecord);
						}
						record=jsonObject.toString();
					}else{
						// 循环遍历节点，用来获取运行状态值
						for (Iterator i = root.elementIterator("Equip"); i
								.hasNext();) {
							Element node = (Element) i.next();
							record += getRecord(itemList, node
									.attributeValue("eqTypeId"), node
									.attributeValue("run"), node
									.attributeValue("warn"))
									+ ",";
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// 如果不存在，读取指定类型的模板文件
				record = "";
			}
		}
		return record;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean checkHospCodeWithFlash(){
		boolean result=false;
		String hospCode=null;
		if(request.getSession().getAttribute("currentHospCode")!=null){
			hospCode=request.getSession().getAttribute("currentHospCode").toString();
		}
		//查看医院简编
		if(request.getSession().getAttribute("flashNewFlag")!=null){
			String flashNewFlag=request.getSession().getAttribute("flashNewFlag").toString();
			if(flashNewFlag.indexOf(",")>0){
				String [] hospShort=flashNewFlag.split(",");
				if(hospShort.length>0 && StringUtils.isNotEmpty(hospCode)){
					for(int i=0;i<hospShort.length;i++){
						if(hospCode.equals(hospShort[i])){
							result=true;
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * 复制文件
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	/**
	 * 单独出来的方法，查询电梯相关的点位信息
	 * 
	 * @param 2012-11-5
	 * @param @return
	 * @return String
	 */
	public String findToElevatorControl(String equipTypeId) {
		request = ServletActionContext.getRequest();
		String groupId = request.getParameter("groupId"); // 设备分组编号
		String groupCode = request.getParameter("groupCode");
		String groupName = request.getParameter("groupName"); // 设备类型编号
		request.setAttribute("flag", request.getParameter("flag"));
		if (StringUtils.isNotEmpty(groupName)) {
			try {
				groupName = URLDecoder.decode(groupName, "UTF-8");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		// 分页空间
		int currentgPage = 1;
		String gpage = request.getParameter("gPage");
		if (!SysUtil.isNull(gpage)) {
			currentgPage = Integer.parseInt(gpage);
		}
		String currentHospCode = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("currentHospCode");
		String filePath = basePath + currentHospCode + File.separator
				+ eqTypeId;
		String fileName = "4001.xml";
		filePath = StringUtil.replacePath(filePath);
		File realFile = createNewFile(filePath, fileName);
		XMLWriter writer = null;// 声明写XML的对象
		SAXReader reader = new SAXReader();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");
		List<VOElevatorPoint> list = new ArrayList<VOElevatorPoint>();
		try {
			// 查询得到数据
			RetCode rc = dbControlDataBusiness.findElevatorControlData(groupId,
					currentgPage, 5);
			Map mapList = new HashMap();
			// 判断是否存在
			if (rc != null) {
				if (rc.getObj() != null) {
					list = (ArrayList<VOElevatorPoint>) rc.getObj();
					for (int i = 0; i < list.size(); i++) {

						VOElevatorPoint ep = list.get(i);
						Map mapEp = new HashMap();
						// 去的点位和
						for (int j = 0; j < list.get(i).getShowPointList()
								.size(); j++) {
							// 得到点位
							String projectPoint = ep.getShowPointList().get(j)
									.getProjectPoint();
							projectPoint = projectPoint.substring(projectPoint
									.indexOf(".") + 1, projectPoint
									.indexOf("_"));
							String record = ep.getShowPointList().get(j)
									.getRecord();
							mapEp.put(projectPoint, record);
						}
						mapList.put(i, mapEp);
					}
				}
				// 如果存在
				if (realFile.exists()) {
					// 直接读取文件
					try {
						Document document = reader.read(realFile);// 读取XML文件
						Element root = document.getRootElement();// 得到根节点
						int index = 0;
						for (Iterator i = root.elementIterator("equip"); i
								.hasNext();) {
							Element student = (Element) i.next();
							// 得到每一个设备的各个监控点的读数
							Map elevator;
							VOElevatorPoint ep;
							if (index >= list.size()) {
								elevator = null;
								ep = null;
							} else {
								ep = list.get(index);
								elevator = (Map) mapList.get(index);
							}
							index++;
							// 是否显示
							if (elevator == null) {
								// 修改显示状态
								student.selectSingleNode("Show").setText(
										String.valueOf(0));
							} else {
								student.selectSingleNode("Show").setText(
										String.valueOf(1));
								// 判断是否得到了数据，没有得到数据就给个默认值
								if (ep != null) {
									if (ep.getStoreyUp() == null) {
										ep.setStoreyUp("0");
									}
									if (ep.getStoreyDown() == null) {
										ep.setStoreyDown("0");
									}
								}
								if (elevator.get("01") == null
										|| elevator.get("01").toString()
												.equals("")
										|| elevator.get("01").toString()
												.equals("null")) {
									elevator.put("01", 1);
								}
								//龙华 电梯 0201  1代表运行 0代表停止  
								if("LH".equals(currentHospCode)){
									if (elevator.get("0201") == null) {
										elevator.put("0201", 0);
									} else {
										// 先判断是否运行
										if (elevator.get("0201").toString().equals(
												"1")) {
											// 上，下
											if (elevator.get("0202").toString()
													.equals("1")) {
												elevator.put("02", 1);
											} else if (elevator.get("0202")
													.toString().equals("2")) {
												elevator.put("02", 2);
											} else {
												elevator.put("02", 0);
											}
										} else {
											elevator.put("02", 0);
										}
									}
								}else{//其他医院 0201  1代表停止 0 代表运行
									if (elevator.get("0201") == null) {
										elevator.put("0201", 1);
									} else {
										// 先判断是否运行
										if (elevator.get("0201").toString().equals(
												"0")) {
											// 上，下
											if (elevator.get("0202").toString()
													.equals("1")) {
												elevator.put("02", 1);
											} else if (elevator.get("0202")
													.toString().equals("2")) {
												elevator.put("02", 2);
											} else {
												elevator.put("02", 0);
											}
										} else {
											elevator.put("02", 0);
										}
									}
								}
								if (elevator.get("03") == null
										|| elevator.get("03").toString()
												.equals("")
										|| elevator.get("03").toString()
												.equals("null")
										|| elevator.get("03").toString()
												.equals("0")) {
									elevator.put("03", 1);
								}
								// 设备名称
								student.selectSingleNode("element").setText(
										String.valueOf(ep.getEquipName()));
								// 总楼层数
								student.selectSingleNode("Floor").setText(
										String.valueOf(SysUtil.toInt(ep
												.getStoreyUp())
												+ SysUtil.toInt(ep
														.getStoreyDown())));
								// 地上楼层
								student.selectSingleNode("StoreyUp").setText(
										String.valueOf(ep.getStoreyUp()));
								// 运行状态
								student.selectSingleNode("State").setText(
										String.valueOf(elevator.get("02")));
								// 停靠的楼层
								student.selectSingleNode("Dock").setText(
										String.valueOf(elevator.get("01")));
								// 停靠的楼层
								student.selectSingleNode("Fault").setText(
										String.valueOf(Math.abs(Integer
												.parseInt(elevator.get("03")
														.toString()))));
							}
						}
						writer = new XMLWriter(new FileWriter(realFile), format);
						writer.write(document);
						writer.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// 如果不存在，读取指定类型的模板文件
					File tempPath = createNewFile(StringUtil
							.replacePath(basePath + File.separator
									+ currentHospCode), "4001.xml");
					if (tempPath.exists()) {
						// 重新输出新的实时监控状态XML
						try {
							format.setEncoding("GBK");
							File newFile = createNewFile(filePath, fileName);
							Document document = reader.read(tempPath);// 读取XML模板文件
							Element root = document.getRootElement();// 得到根节点
							int index = 0;
							for (Iterator i = root.elementIterator("equip"); i
									.hasNext();) {
								Element student = (Element) i.next();
								// 得到每一个设备的各个监控点的读数
								Map elevator = new HashMap();
								VOElevatorPoint ep = new VOElevatorPoint();
								if (index + 1 > list.size()) {
									elevator = null;
									ep = null;
								} else if (list.size() > 0) {
									ep = list.get(index);
									elevator = (Map) mapList.get(index);
								}
								index++;
								// 是否显示
								if (elevator == null) {
									// 修改显示状态
									student.selectSingleNode("Show").setText(
											String.valueOf(0));
								} else {
									student.selectSingleNode("Show").setText(
											String.valueOf(1));
									if (ep != null) {
										if (ep.getStoreyUp() == null) {
											ep.setStoreyUp("0");
										}
										if (ep.getStoreyDown() == null) {
											ep.setStoreyDown("0");
										}
									}
									if (elevator.get("01") == null
											|| elevator.get("01").toString()
													.equals("")
											|| elevator.get("01").toString()
													.equals("null")) {
										elevator.put("01", 1);
									}
									if (elevator.get("0201") == null) {
										elevator.put("0201", 1);
									} else {
										// 先判断是否运行
										if (elevator.get("0201").toString()
												.equals("0")) {
											// 上，下
											if (elevator.get("0202").toString()
													.equals("1")) {
												elevator.put("02", 1);
											} else if (elevator.get("0202")
													.toString().equals("2")) {
												elevator.put("02", 2);
											} else {
												elevator.put("02", 0);
											}
										} else {
											elevator.put("02", 0);
										}
									}
									if (elevator.get("03") == null
											|| elevator.get("03").toString()
													.equals("")
											|| elevator.get("03").toString()
													.equals("null")
											|| elevator.get("03").toString()
													.equals("0")) {
										elevator.put("03", 1);
									}

									// 设备名称
									student.selectSingleNode("element")
											.setText(
													String.valueOf(ep
															.getEquipName()));
									// 总楼层数
									student.selectSingleNode("Floor").setText(
											String.valueOf(SysUtil.toInt(ep
													.getStoreyUp())
													+ SysUtil.toInt(ep
															.getStoreyDown())));
									// 地上楼层
									student.selectSingleNode("StoreyUp")
											.setText(
													String.valueOf(ep
															.getStoreyUp()));
									// 运行状态
									student.selectSingleNode("State").setText(
											String.valueOf(elevator.get("02")));
									// 停靠的楼层
									student.selectSingleNode("Dock").setText(
											String.valueOf(elevator.get("01")));
									// 停靠的楼层
									student.selectSingleNode("Fault").setText(
											String.valueOf(Math.abs(Integer
													.parseInt(elevator
															.get("03")
															.toString()))));
								}
							}
							writer = new XMLWriter(new FileWriter(newFile),
									format);
							writer.write(document);
							writer.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				request.setAttribute("page", rc.getPage());
				request.setAttribute("gPage", currentgPage);
				request.setAttribute("groupId", groupId);
				request.setAttribute("groupCode", groupCode);
				request.setAttribute("eqTypeId", eqTypeId);
				request.setAttribute("groupName", groupName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug(e);
		}
		return equipTypeId;
	}

	/**
	 * 根据设备ID获取能源计量监控页面数据
	 * 
	 * @param 2012-11-6
	 * @param
	 * @return void
	 */
	public String showPowerContorl() {
		RetCode rc = new RetCode();
		request = ServletActionContext.getRequest();
		try {
		     /** 
		      * 
		      * 能源计量中电表显示详情页面的开关状态取值规则说明
		      * 
		      * 
		      * 点表中开关状态显示问题：由于SDCD取的数据是 ：null[页面显示：--]、0[页面显示：开]、1[页面显示：关]，
		      * 由于和数据库有冲突(具体冲突：v_equip_control_point_record中da字段把null和'0'都处理为'0'，
		      * 与需求不符(需求：null[页面显示：--]、0[页面显示：开]、1[页面显示：关]))特此在0和1的基础上面加1 
		      * 变成nul或0[页面显示：--]1[页面显示：开]2[页面显示：关] 
		      * 胸科医院项目显示nul或0[--]1[开]2[关] 
		      * 
		      * 以后按照胸科+1的显示为标准 
		      * 
		      * @DATE 2015-06-12
		      * @param  FMM
		      * 
		      */
			String equipId = request.getParameter("equipId");
			if (StringUtils.isNotEmpty(equipId)) {
				RetCode powerRc = equipListBusiness.findPowerContorl(equipId);
				if (powerRc.getObj() != null) {
					powerList = (List) powerRc.getObj();
				}
				equipServices = equipServiceBusiness.findByEquipId(equipId);
				listEnergyBuilding = (List) buildBusiness.findDBBuilding(
						new DbBuilding()).getObj();
			}
                 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "powerContorl";
	}

	/**
	 * 单独出来的方法，查询照明相关的点位信息
	 * 
	 * @param 2012-11-5
	 * @param @return
	 * @return String
	 */
	public String findToIllumineControl() {
		request = ServletActionContext.getRequest();
		String groupId = request.getParameter("groupId"); // 设备分组编号
		String groupCode = request.getParameter("groupCode"); // 设备分组编号
		String buildId = request.getParameter("buildId"); // 设备分组编号
		String equipTypeId = request.getParameter("eqTypeId"); // 设备类型编号
		String groupName = request.getParameter("groupName");
		if (StringUtils.isNotEmpty(groupName)) {
			try {
				groupName = URLDecoder.decode(groupName, "UTF-8");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		String currentHospCode = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("currentHospCode");

		String filePath = basePath + currentHospCode + File.separator
				+ eqTypeId;
		String fileName = "3001";
		File realFile;
		XMLWriter writer = null;// 声明写XML的对象
		SAXReader reader = new SAXReader();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("gbk");
		List<VOIllmineFloor> contrMap = new ArrayList<VOIllmineFloor>();
		List<VOIllminePoint> list = new ArrayList<VOIllminePoint>();
		filePath = StringUtil.replacePath(filePath);
		try {
			// 查询得到数据
			RetCode rc = dbControlDataBusiness.findIllmineControlData(buildId,
					groupId);
			RetCode outdoorRt = dbControlDataBusiness.findOutdoorIllumination();
			Map mapList = new HashMap();
			// 总楼层数
			int storey = 0;
			// 判断是否存在
			if (rc != null) {
				if (rc.getObj() != null) {
					// 转换类型
					contrMap = (List<VOIllmineFloor>) rc.getObj();
					VOIllmineFloor illFloor = null;
					if (contrMap != null) {
						if (contrMap.get(0) != null
								&& contrMap.get(0).getBuildId() != null) {
							fileName += "_" + contrMap.get(0).getBuildId()
									+ ".xml";
						} else {
							fileName += "_7.xml";
						}
					}
					realFile = createNewFile(filePath, fileName);
					// 写xml,如果存在
					if (realFile.exists()) {
						// 直接读取文件
						try {
							Document document = reader.read(realFile);// 读取XML文件
							Element root = document.getRootElement();// 得到根节点
							int index = 0;
							for (Iterator j = root.elementIterator("equip"); j
									.hasNext();) {
								// 得到节点
								Element student = (Element) j.next();
								for (int i = 0; i < contrMap.size(); i++) {
									if (index == i) {
										// 得到每一个
										illFloor = contrMap.get(i);
										break;
									}
								}

								String record = "";
								if (illFloor != null) {
									for (int i = 0; i < illFloor
											.getShowIllmineList().size(); i++) {
										for (int k = 0; k < illFloor
												.getShowIllmineList().get(i)
												.getShowPointList().size(); k++) {
											// 得到这个点位
											String showPoint = illFloor
													.getShowIllmineList()
													.get(i).getShowPointList()
													.get(k).getProjectPoint();
											// 截取中间的
											showPoint = showPoint.substring(
													showPoint.indexOf(".") + 1,
													showPoint.indexOf("_"));
											// 得到读数
											String re = illFloor
													.getShowIllmineList()
													.get(i).getShowPointList()
													.get(k).getRecord();
											// 判断是否开关状态
											if ("02".equals(showPoint)) {
												// 把读数用1,1,1的形式保存起来
												if (re != null
														&& !"null".equals(re)
														&& !""
																.equals(re
																		.trim())) {
													record += Math.abs(Integer
															.parseInt(re))
															+ ",";
												} else {
													record += 2 + ",";
												}
											}
										}
									}
									// 把读数转成“1,1,1,1“的格式
									if (record != null && !"".equals(record)) {
										record = record.substring(0, record
												.length() - 1);

										student.selectSingleNode("State")
												.setText(record);
									}
								}
								index++;
							}
							writer = new XMLWriter(new FileWriter(realFile),
									format);
							writer.write(document);
							writer.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						// 如果不存在，读取指定类型的模板文件
						File tempPath = createNewFile(StringUtil
								.replacePath(basePath + File.separator
										+ currentHospCode), "3001.xml");
						if (tempPath.exists()) {
							// 重新输出新的实时监控状态XML
							try {
								format.setEncoding("gbk");
								File newFile = createNewFile(filePath, fileName);
								Document document = reader.read(tempPath);// 读取XML模板文件
								Element root = document.getRootElement();// 得到根节点
								int index = 0;
								Element student = null;
								for (Iterator n = root.elementIterator("equip"); n
										.hasNext();) {
									student = (Element) n.next();
									for (int i = 0; i < contrMap.size(); i++) {
										if (index == i) {
											// 得到每一个
											illFloor = contrMap.get(i);
											break;
										}
									}
									String record = "";
									if (illFloor != null) {
										for (int i = 0; i < illFloor
												.getShowIllmineList().size(); i++) {
											for (int k = 0; k < illFloor
													.getShowIllmineList()
													.get(i).getShowPointList()
													.size(); k++) {
												// 得到这个点位
												String showPoint = illFloor
														.getShowIllmineList()
														.get(i)
														.getShowPointList()
														.get(k)
														.getProjectPoint();
												// 截取中间的
												showPoint = showPoint
														.substring(
																showPoint
																		.indexOf(".") + 1,
																showPoint
																		.indexOf("_"));
												// 得到读数
												String re = illFloor
														.getShowIllmineList()
														.get(i)
														.getShowPointList()
														.get(k).getRecord();
												// 判断是否开关状态
												if ("02".equals(showPoint)) {
													// 把读数用1,1,1的形式保存起来
													if (re != null
															&& !"null"
																	.equals(re)
															&& !"".equals(re
																	.trim())) {
														record += Math
																.abs(Integer
																		.parseInt(re))
																+ ",";
													} else {
														record += 2 + ",";
													}
												}
											}
										}
										if (record != null
												&& !"".equals(record)) {
											record = record.substring(0, record
													.length() - 1);
											student.selectSingleNode("State")
													.setText(record);
										}
									}
									index++;
								}
								writer = new XMLWriter(new FileWriter(newFile),
										format);
								writer.write(document);
								writer.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			request.setAttribute("groupId", groupId);
			request.setAttribute("groupCode", groupCode);
			request.setAttribute("eqTypeId", eqTypeId);
			request.setAttribute("groupName", groupName);
			request.setAttribute("buildId", buildId);
			request.setAttribute("outdoorRt", (List) outdoorRt.getObj());
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug(e);
		}
		return equipTypeId;
	}

	/**
	 * 电梯刷新页面的方法，给出参数，js中接收
	 */
	public void findElevatorControl() {
		request = ServletActionContext.getRequest();
		String currentHospCode = (String) ServletActionContext.getRequest()
		.getSession().getAttribute("currentHospCode");
		String groupId = request.getParameter("groupId"); // 设备分组编号
		String groupCode = request.getParameter("groupCode"); // 设备分组编号
		String groupName = request.getParameter("groupName"); // 设备名称
		request.setAttribute("flag", request.getParameter("flag"));
		if (StringUtils.isNotEmpty(groupName)) {
			try {
				groupName = URLDecoder.decode(groupName, "UTF-8");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		// 分页空间
		int currentgPage = 1;
		String gpage = request.getParameter("gPage");
		if (!SysUtil.isNull(gpage)) {
			currentgPage = Integer.parseInt(gpage);
		}
		HttpServletResponse response;
		PrintWriter out = null;
		List<VOElevatorPoint> list = new ArrayList<VOElevatorPoint>();
		String retuStr = "";
		try {
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			// 查询得到数据
			RetCode rc = dbControlDataBusiness.findElevatorControlData(groupId,
					currentgPage, 5);
			Map mapList = new HashMap();
			// 判断是否存在
			if (rc != null) {
				if (rc.getObj() != null) {
					list = (ArrayList<VOElevatorPoint>) rc.getObj();
					for (int i = 0; i < list.size(); i++) {

						VOElevatorPoint ep = list.get(i);
						Map mapEp = new HashMap();
						// 去的点位和
						for (int j = 0; j < list.get(i).getShowPointList()
								.size(); j++) {
							// 得到点位
							String projectPoint = ep.getShowPointList().get(j)
									.getProjectPoint();
							projectPoint = projectPoint.substring(projectPoint
									.indexOf(".") + 1, projectPoint
									.indexOf("_"));
							String record = ep.getShowPointList().get(j)
									.getRecord();
							mapEp.put(projectPoint, record);
						}
						mapList.put(i, mapEp);
					}
				}
				for (int i = 0; i < list.size(); i++) {
					// 得到每一个设备的各个监控点的读数
					Map elevator = (Map) mapList.get(i);
					VOElevatorPoint ep = list.get(i);
					// 是否显示
					String show = "0";
					// 运行模式
					String state = "0";
					if (elevator == null) {
						// 修改显示状态
						show = "0";
					} else {
						show = "1";
						// 先判断是否运行
						
						//龙华 电梯 0201  1代表运行 0代表停止  
						if("LH".equals(currentHospCode)){
							if (elevator.get("0201") != null
									&&(elevator.get("0201").toString().equals("1")||elevator.get("0201").toString().equals("-1"))) {
								// 上，下
								if (elevator.get("0202") != null) {
									if (elevator.get("0202").toString().equals("1")||elevator.get("0202").toString().equals("-1")) {
										state = "1";
									} else if (elevator.get("0202").toString()
											.equals("2")||elevator.get("0202").toString()
											.equals("-2")) {
										state = "2";
									} else {
										state = "0";
									}
								} else {
									state = "0";
								}
								elevator.put("02", state);// add by lg
								// del by llg } else {
								// elevator.put("02", 0);
							}
						}else{//其他医院 0201  1代表停止 0 代表运行
							if (elevator.get("0201") != null
									&& elevator.get("0201").toString().equals("0")) {
								// 上，下
								if (elevator.get("0202") != null) {
									if (elevator.get("0202").toString().equals("1")||elevator.get("0202").toString().equals("-1")) {
										state = "1";
									} else if (elevator.get("0202").toString()
											.equals("2")||elevator.get("0202").toString()
											.equals("-2")) {
										state = "2";
									} else {
										state = "0";
									}
								} else {
									state = "0";
								}
								elevator.put("02", state);// add by lg
								// del by llg } else {
								// elevator.put("02", 0);
							}
						}
						if (elevator.get("01") == null
								|| elevator.get("01").toString().equals("")
								|| elevator.get("01").toString().equals("null")
								|| elevator.get("01").toString().equals("0")) {
							elevator.put("01", 1);
						}

						if (elevator.get("03") == null
								|| elevator.get("03").toString().equals("")
								|| elevator.get("03").toString().equals("null")) {
							elevator.put("03", 1);
						}
					}
					// 总楼层，地上楼层，运行状态，是否显示，显示的楼层，故障状态，名称
					retuStr += (SysUtil.toInt(ep.getStoreyUp()) + SysUtil
							.toInt(ep.getStoreyDown()))
							+ ","
							+ ep.getStoreyUp()
							+ ","
							// + state //del by lg
							// +elevator.get("02")//add by lg // edit by lg 2013
							// 05 15
							+ (StringUtil.isEmpty(elevator.get("02"))||StringUtil.isEmpty(StringUtil.trimNull(elevator.get("02").toString()))?"0":Math.abs(Double.parseDouble(elevator.get("02").toString())))
							+ ","
							+ show
							+ ","
							+ (StringUtil.isEmpty(elevator.get("01"))||StringUtil.isEmpty(StringUtil.trimNull(elevator.get("01").toString()))?"0":elevator.get("01"))
							+ ","+
							// + elevator.get("03") edit by lg 2013 05 15
							(StringUtil.isEmpty(elevator.get("03"))||StringUtil.isEmpty(StringUtil.trimNull(elevator.get("03").toString()))?"0":Math.abs(Double.parseDouble(elevator.get("03").toString())))
							+ ","
							+ ep.getEquipName()
							+ ";";
				}
				if (retuStr.length() > 1) {
					retuStr = retuStr.substring(0, retuStr.length() - 1);
				}
			}
			request.setAttribute("gPage", currentgPage);
			request.setAttribute("groupName", groupName);
			out.print(retuStr);
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug(e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 电梯刷新页面的方法，给出参数，js中接收
	 */
	public void find8ElevatorControl() {
		request = ServletActionContext.getRequest();
		String groupId = request.getParameter("groupId"); // 设备分组编号
		String groupCode = request.getParameter("groupCode"); // 设备分组编号
		String groupName = request.getParameter("groupName"); // 设备名称
		request.setAttribute("flag", request.getParameter("flag"));
		if (StringUtils.isNotEmpty(groupName)) {
			try {
				groupName = URLDecoder.decode(groupName, "UTF-8");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		// 分页空间
		int currentgPage = 1;
		String gpage = request.getParameter("gPage");
		if (!SysUtil.isNull(gpage)) {
			currentgPage = Integer.parseInt(gpage);
		}
		HttpServletResponse response;
		PrintWriter out = null;
		List<VOElevatorPoint> list = new ArrayList<VOElevatorPoint>();
		String retuStr = "";
		try {
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			// 查询得到数据
			RetCode rc = dbControlDataBusiness.findElevatorControlData(groupId,
					currentgPage, 5);
			Map mapList = new HashMap();
			// 判断是否存在
			if (rc != null) {
				if (rc.getObj() != null) {
					list = (ArrayList<VOElevatorPoint>) rc.getObj();
					for (int i = 0; i < list.size(); i++) {

						VOElevatorPoint ep = list.get(i);
						Map mapEp = new HashMap();
						// 去的点位和
						for (int j = 0; j < list.get(i).getShowPointList()
								.size(); j++) {
							// 得到点位
							String projectPoint = ep.getShowPointList().get(j)
									.getProjectPoint();
							projectPoint = projectPoint.substring(projectPoint
									.indexOf(".") + 1, projectPoint
									.indexOf("_"));
							String record = ep.getShowPointList().get(j)
									.getRecord();
							mapEp.put(projectPoint, record);
						}
						mapList.put(i, mapEp);
					}
				}
				for (int i = 0; i < list.size(); i++) {
					// 得到每一个设备的各个监控点的读数
					Map elevator = (Map) mapList.get(i);
					VOElevatorPoint ep = list.get(i);
					// 是否显示
					String show = "0";
					// 运行模式
					String state = "0";
					if (elevator == null) {
						// 修改显示状态
						show = "0";
					} else {
						show = "1";
						// 楼层当楼层为0时 改为1；
						if (elevator.get("01").toString().equals("0")) {
							elevator.put("01", 1);
						}

						if (elevator.get("03") == null
								|| elevator.get("03").toString().equals("")
								|| elevator.get("03").toString().equals("null")) {
							elevator.put("03", 1);
						}
					}
					retuStr += (SysUtil.toInt(ep.getStoreyUp()) + SysUtil
							.toInt(ep.getStoreyDown()))
							+ ","
							+ ep.getStoreyUp()
							+ ","
							// + elevator.get("02")
							+ Math.abs(Integer.parseInt(elevator.get("02")
									.toString()))// edit by lg 2013 05 15
							+ ","
							+ show
							+ ","
							+ elevator.get("01")
							+ ","
							// + elevator.get("03") edit by lg 2013 05 15
							+ Math.abs(Integer.parseInt(elevator.get("03")
									.toString()))
							+ ","
							+ ep.getEquipName()
							+ ";";
				}
				if (retuStr.length() > 1) {
					retuStr = retuStr.substring(0, retuStr.length() - 1);
				}
			}
			request.setAttribute("page", rc.getPage());
			request.setAttribute("groupName", groupName);
			out.print(retuStr);
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug(e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 单独出来的方法，查询照明相关的点位信息
	 * 
	 * @param 2012-11-5
	 * @param @return
	 * @return String
	 */
	public void findIllumineControl() {
		request = ServletActionContext.getRequest();
		String groupId = request.getParameter("groupId"); // 设备分组编号
		String groupCode = request.getParameter("groupCode"); // 设备分组编号
		String buildId = request.getParameter("buildId"); // 设备分组编号
		String equipTypeId = request.getParameter("eqTypeId"); // 设备类型编号
		HttpServletResponse response;
		PrintWriter out = null;

		String record = "";
		String downRecord = "";
		List<VOIllmineFloor> contrMap = new ArrayList<VOIllmineFloor>();
		List<VOIllminePoint> list = new ArrayList<VOIllminePoint>();
		try {
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			// 查询得到数据
			RetCode rc = dbControlDataBusiness.findIllmineControlData(buildId,
					groupId);
			// 判断是否存在
			if (rc != null) {
				if (rc.getObj() != null) {

					// 转换类型
					contrMap = (List<VOIllmineFloor>) rc.getObj();
					VOIllmineFloor illFloor = null;
					if (contrMap != null && !contrMap.isEmpty()) {

						String storyUp = contrMap.get(0).getStoreyUp();
						String storyDown = contrMap.get(0).getStoreyDown();
						
						// 楼上
						int floorNum = 1;
						//楼下
						int floorDownNum = Integer.parseInt(storyDown);
						for (int j = 0; j < contrMap.size(); j++) {
							int flagUp = 0;//loushang
							int flagDown = 0;//楼下
							String tempRecord = "";
							// 得到每一个
							illFloor = contrMap.get(j);
							if (illFloor != null) {
								/**
								 * 地下层数
								 */
								if (Integer.parseInt(illFloor.getFloor())==(0-floorDownNum)) {
									for (int i = 0; i < illFloor.getShowIllmineList()
									.size(); i++) {
										/**
										 * 这个循环获取点位的控制权
										 */
										for (int k = 0; k < illFloor
												.getShowIllmineList().get(i)
												.getShowPointList().size(); k++) {
											String controlPoint = illFloor
											.getShowIllmineList().get(i)
											.getShowPointList().get(k).getControlPoint();
											
											// 得到这个点位
											String showPoint = illFloor
													.getShowIllmineList().get(i)
													.getShowPointList().get(k)
													.getProjectPoint();
											// 截取中间的
											showPoint = showPoint.substring(showPoint
													.indexOf(".") + 1, showPoint
													.indexOf("_"));
											/* 
											 * 修改以后
											String re = illFloor.getShowIllmineList()
													.get(i).getShowPointList().get(k)
													.getRecord();
										    */
											String bb = "";
											String re = "";
											try {
												bb =illFloor.getShowIllmineList()
												.get(i).getShowPointList().get(k)
												.getRecord();
												re = SysUtil.subZeroAndDot(bb);
											} catch (Exception e) {
												
											}
											// 判断是否开关状态
											if ("02".equals(showPoint)) {
												if(StringUtil.isEmpty(controlPoint)||StringUtil.isEmpty(StringUtil.trimNull(controlPoint))){
													tempRecord += 3 + ",";
												}else{
													// 把读数用1,1,1的形式保存起来
													if (re != null && !"null".equals(re)
															&& !"".equals(re.trim())) {
														tempRecord += Math.abs(Integer
																.parseInt(re))
																+ ",";
													} else {
														tempRecord += 2 + ",";
													}
												}
											}
										}
									}
									flagDown++;
								}else if(Integer.parseInt(illFloor.getFloor())!=(0-floorDownNum)&&floorDownNum!=0){
									j--;
									tempRecord = tempRecord +";";
									flagDown++;
								}else if(Integer.parseInt(illFloor.getFloor())==floorNum){//地上层数
									
									for (int i = 0; i < illFloor.getShowIllmineList()
									.size(); i++) {
										/**
										 * 把这个循环的比较，按照从小到大的顺序来排序好
										 */
										
										for (int k = 0; k < illFloor
												.getShowIllmineList().get(i)
												.getShowPointList().size(); k++) {
											String controlPoint = illFloor
											.getShowIllmineList().get(i)
											.getShowPointList().get(k).getControlPoint();
											
												// 得到这个点位
												String showPoint = illFloor
														.getShowIllmineList().get(i)
														.getShowPointList().get(k)
														.getProjectPoint();
												// 截取中间的
												showPoint = showPoint.substring(showPoint
														.indexOf(".") + 1, showPoint
														.indexOf("_"));
												// 得到读数
												String bb = "";
												String re = "";
												try {
													bb =illFloor.getShowIllmineList()
													.get(i).getShowPointList().get(k)
													.getRecord();
													re = SysUtil.subZeroAndDot(bb);
												} catch (Exception e) {
													
												}
												// 判断是否开关状态
												if ("02".equals(showPoint)) {
													if(StringUtil.isEmpty(controlPoint)||StringUtil.isEmpty(StringUtil.trimNull(controlPoint))){
														record += 3 + ",";
													}else{
														// 把读数用1,1,1的形式保存起来
														if (re != null && !"null".equals(re) && !"".equals(re.trim())) {
															record += Math.abs(Double.parseDouble(re))+ ",";
														} else {
															record += 2 + ",";
														}
												}
											}
										}
									}
									flagUp++;
								}else if(Integer.parseInt(illFloor.getFloor())!=floorNum&&Integer.parseInt(illFloor.getFloor())>0){
									j--;
									record += ";";
									flagUp++;
								}
								
								if (flagDown>0) {
									// 把读数转成“1,1,1,1“的格式
									downRecord = tempRecord+downRecord;
									floorDownNum--;
								}
								if (flagUp>0) {
									// 把读数转成“1,1,1,1“的格式
									if (record != null && !"".equals(record)) {
										record = record.substring(0,
												record.length() - 1);
										record += ";";
									}
									floorNum++;
								}
								
							}
							int otherFloor = Integer.parseInt(storyUp)-Integer.parseInt(illFloor.getFloor());
							if(j==contrMap.size()-1&&otherFloor>0){
								for (int i = 0; i < otherFloor; i++) {
									record += ";";
								}
							}
						}
					}

					// 把读数转成“1,1,1,1“的格式
					if (record != null && !"".equals(record)) {
						record = record.substring(0, record.length() - 1);
						if (downRecord != null && !"".equals(downRecord)) {
							downRecord = downRecord.substring(0,
									downRecord.length() - 1);
							record = record +":"+downRecord;
						}
					}
				}
			}
			request.setAttribute("groupId", groupId);
			request.setAttribute("groupCode", groupCode);
			request.setAttribute("eqTypeId", eqTypeId);
			request.setAttribute("buildId", buildId);
			out.print(record);
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug(e);
		}
	}

	/**
	 * 未使用 空压机单独查询数据的方法
	 * 
	 * @param 2013-2-21
	 * @param
	 * @return void
	 */
	public void findAirCompressor() {
		request = ServletActionContext.getRequest();
		String groupId = request.getParameter("groupId"); // 设备分组编号
		String groupCode = request.getParameter("groupCode");
		HttpServletResponse response;
		PrintWriter out = null;
		try {
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			// 查询数据库中的点位
			RetCode rc = dbControlDataBusiness.findControlDataByGroup(groupId);
			// 得到空压机的状态
			// 判断是否存在
			String record = "";
			if (rc != null) {
				Map m = null;
				String point = null;
				if (rc.getObj() != null) {
					List list = (List) rc.getObj();
					for (int i = 0; i < list.size(); i++) {
						m = (Map) list.get(i);
						point = m.get("project_point") + "";
						point = point.substring(point.indexOf(".") + 1, point
								.indexOf("_"));
						if ("01".equals(point)) {
							/**
							 * 修改  去掉后缀"。0"
							 * 
							 * Date：2015-07-08
							 * 
							 */
							// 设置 溢水报警的 div
							String o=SysUtil.subZeroAndDot((Double)m.get("record")+"");
							record += o + ",";
						}
					}
				}
				// 把读数转成“1,1,1,1“的格式
				if (record != null && !"".equals(record)) {
					record = record.substring(0, record.length() - 1);
				}
			}
			out.print(record);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e);
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public BuildingBusiness getBuildBusiness() {
		return buildBusiness;
	}

	public void setBuildBusiness(BuildingBusiness buildBusiness) {
		this.buildBusiness = buildBusiness;
	}

	public List getListBuilding() {
		return listBuilding;
	}

	public void setListBuilding(List listBuilding) {
		this.listBuilding = listBuilding;
	}

	public EquipListBusiness getEquipListBusiness() {
		return equipListBusiness;
	}

	public void setEquipListBusiness(EquipListBusiness equipListBusiness) {
		this.equipListBusiness = equipListBusiness;
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

	public DbEquipList getDbEquipList() {
		return dbEquipList;
	}

	public void setDbEquipList(DbEquipList dbEquipList) {
		this.dbEquipList = dbEquipList;
	}

	public List getListEquipList() {
		return listEquipList;
	}

	public void setListEquipList(List listEquipList) {
		this.listEquipList = listEquipList;
	}

	public List<Integer> getFloorList() {
		return floorList;
	}

	public void setFloorList(List<Integer> floorList) {
		this.floorList = floorList;
	}

	public EquipTypeBusiness getEquipTypeBusiness() {
		return equipTypeBusiness;
	}

	public void setEquipTypeBusiness(EquipTypeBusiness equipTypeBusiness) {
		this.equipTypeBusiness = equipTypeBusiness;
	}

	public DbAlarmBusiness getDbAlarmBusiness() {
		return dbAlarmBusiness;
	}

	public void setDbAlarmBusiness(DbAlarmBusiness dbAlarmBusiness) {
		this.dbAlarmBusiness = dbAlarmBusiness;
	}

	public DbPointEquip getDbPointEquip() {
		return dbPointEquip;
	}

	public void setDbPointEquip(DbPointEquip dbPointEquip) {
		this.dbPointEquip = dbPointEquip;
	}

	public List getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(List alarmList) {
		this.alarmList = alarmList;
	}

	public EquipGroupBusiness getEquipGroupBusiness() {
		return equipGroupBusiness;
	}

	public void setEquipGroupBusiness(EquipGroupBusiness equipGroupBusiness) {
		this.equipGroupBusiness = equipGroupBusiness;
	}

	public String getEqTypeId() {
		return eqTypeId;
	}

	public void setEqTypeId(String eqTypeId) {
		this.eqTypeId = eqTypeId;
	}

	public List<VOEquipGroup> getEquipGroupList() {
		return equipGroupList;
	}

	public void setEquipGroupList(List<VOEquipGroup> equipGroupList) {
		this.equipGroupList = equipGroupList;
	}

	public DbControldataBusiness getDbControlDataBusiness() {
		return dbControlDataBusiness;
	}

	public void setDbControlDataBusiness(
			DbControldataBusiness dbControlDataBusiness) {
		this.dbControlDataBusiness = dbControlDataBusiness;
	}

	public List<VOEquipGroup> getGroupSelectList() {
		return groupSelectList;
	}

	public void setGroupSelectList(List<VOEquipGroup> groupSelectList) {
		this.groupSelectList = groupSelectList;
	}

	public Page getGpage() {
		return gpage;
	}

	public void setGpage(Page gpage) {
		this.gpage = gpage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List getChildEquipList() {
		return childEquipList;
	}

	public void setChildEquipList(List childEquipList) {
		this.childEquipList = childEquipList;
	}

	public List getPowerList() {
		return powerList;
	}

	public void setPowerList(List powerList) {
		this.powerList = powerList;
	}
	public List getChildItemList() {
		return childItemList;
	}

	public void setChildItemList(List childItemList) {
		this.childItemList = childItemList;
	}

	public List getBuildList() {
		return buildList;
	}

	public void setBuildList(List buildList) {
		this.buildList = buildList;
	}

	public MenusBusiness getMenuBusiness() {
		return menuBusiness;
	}

	public void setMenuBusiness(MenusBusiness menuBusiness) {
		this.menuBusiness = menuBusiness;
	}

	public List getMenuTabList() {
		return menuTabList;
	}

	public void setMenuTabList(List menuTabList) {
		this.menuTabList = menuTabList;
	}

	public List getListItem() {
		return listItem;
	}

	public void setListItem(List listItem) {
		this.listItem = listItem;
	}

	public static String getBasepath() {
		return basePath;
	}
	/**
	 * @return the equipServiceBusiness
	 */
	public EquipServiceBusiness getEquipServiceBusiness() {
		return equipServiceBusiness;
	}
	/**
	 * @param equipServiceBusiness
	 *            the equipServiceBusiness to set
	 */
	public void setEquipServiceBusiness(
			EquipServiceBusiness equipServiceBusiness) {
		this.equipServiceBusiness = equipServiceBusiness;
	}
	/**
	 * @return the equipServices
	 */
	public List<EquipService> getEquipServices() {
		return equipServices;
	}
	/**
	 * @param equipServices
	 *            the equipServices to set
	 */
	public void setEquipServices(List<EquipService> equipServices) {
		this.equipServices = equipServices;
	}
	/**
	 * @return the listEnergyBuilding
	 */
	public List getListEnergyBuilding() {
		return listEnergyBuilding;
	}
	/**
	 * @param listEnergyBuilding
	 *            the listEnergyBuilding to set
	 */
	public void setListEnergyBuilding(List listEnergyBuilding) {
		this.listEnergyBuilding = listEnergyBuilding;
	}

	/**
	 * @param 2013-1-16
	 * @param
	 * @return void
	 * @throws Exception
	 */

	private List findXYElement(String fileName, String groupCode) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document doc = saxReader.read(inputXml); // 把文件读入到文档
			Element employees = doc.getRootElement(); // 获取文档根节点

			List lst = doc.selectNodes("/equipment/equip[@groupCode='"
					+ groupCode + "']");

			for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
				Element stu = (Element) iterator.next();
				// 获得每个学生的"所有"的信息
				List list2 = stu.selectNodes("*");
				return list2;
				// for (Iterator iterator2 = list2.iterator();
				// iterator2.hasNext();) {
				// Element e = (Element) iterator2.next();
				// System.out.print(e.attributeValue("x") + "   ");
				// }

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public static void main(String[] args) {
//		System.out.println("4,4:,".split(":")[1].split(";")[0].split(",").length);
	}
}
