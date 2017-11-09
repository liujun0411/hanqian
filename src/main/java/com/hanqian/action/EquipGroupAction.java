package com.hanqian.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.EquipGroupBusiness;
import com.hanqian.form.VOEquipGroup;
import com.hanqian.form.VOEquipList;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 设备分组 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-9-19
 * @see
 */
public class EquipGroupAction {

	private Log log = LogFactory.getLog(EquipGroupAction.class);

	private EquipGroupBusiness equipGroupBusiness;
	private BuildingBusiness buildingBusiness;
	private HttpServletRequest request;
	// 权限
	private MenuInterceptor menuInterceptor;
	private Map menuMap;
	private String resultJSON; // 返回JSON格式的文本字符串

	private String resultStr = ""; // 返回到页面的辨识（新增，修改）
	private String message = "";
	private List childItemList;
	private Integer currentPage;
	int pageSize = 10;
	//修改时，当前设备组中，经页面填写前后的ID
	String equipBefore = "";
	String equipAfter = "";
	private File groupPic;
	private String groupPicFileName;
	private String groupPicContentType;
	private String eqTypeId;
	/**
	 * 查询设备组列表
	 * 
	 * @param statement
	 * @param logisticsNews
	 * @return
	 */
	public String showEquipGroupList() {
		
		// 判断权限
		menuMap = menuInterceptor.menuIntercept("5006");
		// 若为空 ，进入登录界面
		if (menuMap == null) {
			MenuInterceptor.toLoginjsp();
			return null;
		}
		request = ServletActionContext.getRequest();
		if (request.getParameter("showMsg")!=null) {
			message="";
		}
		if(currentPage == null){
			currentPage =1;
		}
		// 判断session是否失效，跳到登录页面
//		if(request.getParameter("flag")!=null){
//			message="";
//		}
		String _parameter = request.getParameter("wherestr");
		request.setAttribute("wherestr", _parameter);
//		try {
//			currentPage = Integer.parseInt(request.getParameter("currentPage"));
//		} catch (Exception e) {
//			log.error("EquipGroupAction-->showEquipGroupList", e);
//			currentPage = 1;
//		}
		try {
			RetCode rc = equipGroupBusiness.findGroupList(currentPage,pageSize, _parameter);
			
			if (rc != null) {
				if (rc.getObj() != null) {
					request.setAttribute("equipGroupList",
							(List<VOEquipGroup>) rc.getObj());
				}else{
					message = "暂无数据";
				}
				request.setAttribute("page", rc.getPage());
			} else {
				message = "暂无数据";
			}
			if (request.getParameter("flagMsg")!=null) {
				message = "图纸超出6兆";
			}
		} catch (Exception e) {
			log.error("EquipGroupAction-->showEquipGroupList", e);
			log.debug(e);
			e.printStackTrace();
		}
		return "showEGList";
	}

	/**
	 * 删除设备分组
	 * 
	 * @param 2012-9-20
	 * @param @return
	 * @return String
	 */
	public String deleteEquipGroup() {
		request = ServletActionContext.getRequest();
		String groupId = request.getParameter("groupId");
//		System.out.println("进入了del方法");
		message = "删除失败，系统错误，请重新登录";
		try {
//			String imgUrl =request.getRealPath(File.separator)+ConfigCST.CST_GROUP_PIC_LOCATION;
			String imgUrl = new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"GroupPic";
//			String imgUrl =request.getRealPath(File.separator)+ConfigUtil.getConfig("config.properties", "groupPicLocation");
			imgUrl = replacePath(imgUrl);
			boolean result = equipGroupBusiness.deleteEquipGroup(groupId,imgUrl);
			message = result ? "删除操作成功!" : "删除操作失败!";
		} catch (Exception e) {
			log.error("EquipGroupAction-->deleteEquipGroup", e);
			e.printStackTrace();
		}
		return showEquipGroupList();
	}

	/**
	 * 设备列表
	 * 
	 * @return
	 */
	public String showEquipList() {
		request = ServletActionContext.getRequest();
		// DbUsers dbusers = (DbUsers)
		// request.getSession().getAttribute("users");
		// if (!this.LoadisFail(dbusers)) {
		// return "login";
		// }
		//加入楼层  楼宇
		String build_id = request.getParameter("build_id");
		
		String eqtype = request.getParameter("eqtype");
		HttpServletResponse rsp = ServletActionContext.getResponse();
		PrintWriter out = null;
		Gson g = new Gson();
		try {
			List eqList = equipGroupBusiness.findEqList(eqtype,"",build_id);
			
			rsp.setCharacterEncoding("utf-8");
			rsp.setContentType("text/plain");
			out = rsp.getWriter();
			// 判断时候有数据
			if (eqList != null) {
				out.print(g.toJson(eqList));
			}
//			List buildList = getBuildingInfoList();
//			if (buildList != null) {
//				out.print(g.toJson(buildList));
//			}
		} catch (Exception e) {
			log.error("EquipGroupAction-->showEquipList", e);
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 获取楼宇信息列表
	 * @return
	 */
	private List getBuildingInfoList(){
		RetCode rc = buildingBusiness.findBuildingBySql();
		List buildList = (List)rc.getObj();
		return buildList;
	}
	
	
	/**
	 * 加载更新界面
	 * 
	 * @return
	 */
	public String showGroupEdit() {
		request = ServletActionContext.getRequest();

		String groupId = request.getParameter("groupId");
		String eBuildId = request.getParameter("eBuildId");
		String equipType = request.getParameter("equipType");
		String groupName = request.getParameter("groupName");
		String picName = request.getParameter("picName");
		String groupRemark = request.getParameter("groupRemark");
		String groupCode=request.getParameter("groupCode");
		try {
			// 查询设备列表
			List eqtypelist = equipGroupBusiness.findEqTypeListNotPei();
			request.setAttribute("eqtypelist", eqtypelist);
			request.setAttribute("buildList", getBuildingInfoList());
			if (!SysUtil.isNull(groupId) && !SysUtil.isNull(equipType)) {
				//得到已有的设备，和相关的信息
				String[] result = equipGroupBusiness.findGrouEq(groupId);
				request.setAttribute("equiplist", result[0]);
				//得到当前设备组的设备类型的设备
				List<VOEquipList> eqList = equipGroupBusiness.findEqList(equipType,groupId,eBuildId);
				request.setAttribute("eqList", eqList);
				resultStr = "up";
				equipBefore = result[1];
				picName = StringUtil.trimNull(picName);
				request.setAttribute("picName", picName);
				request.setAttribute("eBuildId", eBuildId);
				request.setAttribute("groupId", groupId);
				request.setAttribute("equipId", equipBefore);
				request.setAttribute("groupName", groupName);
				request.setAttribute("equipType", equipType);
				request.setAttribute("groupRemark", groupRemark);
				request.setAttribute("groupCode", groupCode);
				
			} else {
				resultStr = "add";
			}
		} catch (Exception e) {
			log.error("EquipGroupAction-->showGroupEdit", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "updateGroup";
	}

	/**
	 * 修改设备分组
	 * 
	 * @param 2012-9-25
	 * @param @return
	 * @return String
	 */
	public String updateGroupEquip() {
		request = ServletActionContext.getRequest();
		// seq
		String groupId = request.getParameter("groupId");
		equipAfter = request.getParameter("equipId");
		String picName = request.getParameter("picName");
		String groupName = request.getParameter("groupName");
		String equipType = request.getParameter("equipType");
		String build_id = request.getParameter("build_id");
		String groupRemark = request.getParameter("groupRemark");
		String groupCode = request.getParameter("groupCode");
		String isChangeFlag =  request.getParameter("isChangeFlag");
		try {
//			String imgUrl =request.getRealPath(File.separator)+ConfigCST.CST_GROUP_PIC_LOCATION;
			String imgUrl =new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"GroupPic";
			//String imgUrl =request.getSession().getServletContext().getRealPath("/manager/images/groupPic/");
//			String imgUrl =request.getRealPath(File.separator)+ConfigUtil.getConfig("config.properties", "groupPicLocation");
			imgUrl = replacePath(imgUrl);
			//调用修改的方法
			if (isChangeFlag!=null&&StringUtil.isNotEmpty(isChangeFlag)&&StringUtil.trimNull(isChangeFlag).length()>0&&"1".equals(isChangeFlag)) {//上传的图片
				groupPicFileName = null;
			}
			message = equipGroupBusiness.updateEquipGroup(equipBefore, equipAfter, groupId, groupName, groupRemark, equipType,groupPic,groupPicFileName,imgUrl,picName,build_id,groupCode);
		} catch (Exception e) {
			log.error("EquipGroupAction-->updateGroupEquip", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "修改失败，请重新登录！";
		}
		return showEquipGroupList();
	}

	/**
	 * 新增设备分组
	 * 
	 * @param 2012-9-25
	 * @param @return
	 * @return String
	 */
	public String addGroupEquip() {
		request = ServletActionContext.getRequest();
		String equipId = request.getParameter("equipId");
		String groupName = request.getParameter("groupName");
		String equipType = request.getParameter("equipType");
		String build_id = request.getParameter("build_id");
		String groupRemark = request.getParameter("groupRemark");
		String isChangeFlag =  request.getParameter("isChangeFlag");
		try {
			//先上传
//			String imgUrl =request.getRealPath(File.separator)+ConfigCST.CST_GROUP_PIC_LOCATION;
			String imgUrl = new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"GroupPic";
			//       		new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"GroupPic";
//			String imgUrl =request.getRealPath(File.separator)+ConfigUtil.getConfig("config.properties", "groupPicLocation");
			
			imgUrl = replacePath(imgUrl);
			if (isChangeFlag!=null&&StringUtil.isNotEmpty(isChangeFlag)&&StringUtil.trimNull(isChangeFlag).length()>0&&"1".equals(isChangeFlag)) {//上传的图片
				groupPicFileName = null;
			}
			message =  equipGroupBusiness.insertEquipGroup(equipId, groupName, groupRemark, equipType,groupPic,groupPicFileName,imgUrl,build_id);
		} catch (Exception e) {
			log.error("EquipGroupAction-->addGroupEquip", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "操作失败，请联系管理员";
		}
		return showEquipGroupList();
	}
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
	 * 根据父组Id获得所有的子设备组
	 * @param 2012-11-6  
	 * @param @param groupId
	 * @param @return       
	 * @return String
	 */
	public String findChildGroup(){
		request = ServletActionContext.getRequest();
		String groupId=request.getParameter("groupId");
		RetCode rc=new RetCode();
		RetCode rt=new RetCode();
		childItemList=new ArrayList();
		if(StringUtils.isNotEmpty(groupId)){
           rc=equipGroupBusiness.findChildGroup(groupId);
		}
        if(rc.getObj()!=null){
        	childItemList=(List)rc.getObj();
        }
        //获得配电间名称
        rt=equipGroupBusiness.findById(groupId);
        if(rt.getObj()!=null){
        	List list=(List)rt.getObj();
        	if(list.size()>0){
              Map haspMap=new HashMap();
              haspMap=(Map)list.get(0);
              request.setAttribute("groupName", haspMap.get("group_name"));
            }
        }
		return "peiDianPage";
	}
	
	/**
	 * 根据设备获得设备所属的组
	 */
	public String findGroupByEquip(){
		request = ServletActionContext.getRequest();
		String equipId=request.getParameter("equipId");
		RetCode rc=new RetCode();
		if(StringUtils.isNotEmpty(equipId)){
			rc=equipGroupBusiness.findGroupByEquip(equipId);
		}
		List list=new ArrayList();
		if(rc.getObj()!=null){
			list=(List)rc.getObj();
		}
		JSONArray json=JSONArray.fromObject(list);
		resultJSON=json.toString();
		try{
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");   
			ServletActionContext.getResponse().getWriter().print(resultJSON);
		}catch (Exception e) {
			log.error("EquipGroupAction-->findGroupByEquip", e);
			// TODO: handle exception
		}
		return null;
	}
	/**
	 * 根据设备获得设备所属的组
	 */
	public String findPicNameByGroupId(){
		request = ServletActionContext.getRequest();
		String groupId=request.getParameter("groupId");
		String picName = "";
		if (StringUtil.isNotEmpty(groupId)) {
			picName = equipGroupBusiness.findPicNameByGroupId(Integer.parseInt(groupId));
		}
		JSONObject object = new JSONObject();
		object.accumulate("picName", picName);
		try {
			ServletActionContext.getResponse().getWriter().print(object);
		} catch (Exception e) {
			log.error("EquipGroupAction-->findPicNameByGroupId", e);
			// TODO Auto-generated catch block
		}
//		System.out.println(object);
		return null;
	}
	
	/**
	 * 根据设备ID，获得设备图纸
	 */
	public String findPicNameByEquipId(){
		request = ServletActionContext.getRequest();
		String equipId=request.getParameter("equipId");
		String picName = "";
		if (StringUtil.isNotEmpty(equipId)) {
			picName = equipGroupBusiness.findPicNameByEquipId(Integer.parseInt(equipId));
		}
		JSONObject object = new JSONObject();
		object.accumulate("picName", picName);
		try {
			ServletActionContext.getResponse().getWriter().print(object);
		} catch (Exception e) {
			log.error("EquipGroupAction-->findPicNameByEquipId", e);
			// TODO Auto-generated catch block
		}
//		System.out.println(object);
		return null;
	}
	/*
	 * 
	 * 获取图片
	 */
	public void pic(){
		try {
			request = ServletActionContext.getRequest();
			String picPath = request.getParameter("pic");
			String dir_path = new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"GroupPic";
			File temp = new File(dir_path);
			// 创建一个存放文件文件夹
			File file = new File(temp+File.separator+ picPath);
			
			if (file.exists()) {
				FileInputStream is = new FileInputStream(dir_path+File.separator+picPath);
				int i = is.available(); // 得到文件大小
				byte data[] = new byte[i];
				is.read(data); //读数据
				is.close();
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("*"); // 设置返回的文件类型
				OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
				toClient.write(data); // 输出数据
				toClient.close();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void validPicIsExist(){
		request = ServletActionContext.getRequest();
		String picPath = request.getParameter("url");
		JSONObject json = new JSONObject();
		try{
			//本地测试win用这个
			//String dir_path = new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"GroupPic";
			
			//Linux系统上线时获取Tomcat目录用这个
			String dir_path = System.getProperty("catalina.base")+"/Logistics_UploadPic/"+"GroupPic";
			
			File temp = new File(dir_path);
			// 创建一个存放文件文件夹
			File file = new File(temp+File.separator+ picPath);

			if (file.exists()) {
				json.accumulate("resStatus", true);
			}else{
				json.accumulate("resStatus", false);
			}
			ServletActionContext.getResponse().getWriter().write(json.toString());
		}catch (Exception e) {
			log.error("EquipGroupAction-->validPicIsExist", e);
		}
		
	}
	
	/**
	 * get/set
	 */
	public EquipGroupBusiness getEquipGroupBusiness() {
		return equipGroupBusiness;
	}

	public void setEquipGroupBusiness(EquipGroupBusiness equipGroupBusiness) {
		this.equipGroupBusiness = equipGroupBusiness;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResultJSON() {
		return resultJSON;
	}

	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getEquipBefore() {
		return equipBefore;
	}

	public void setEquipBefore(String equipBefore) {
		this.equipBefore = equipBefore;
	}

	public String getEquipAfter() {
		return equipAfter;
	}

	public List getChildItemList() {
		return childItemList;
	}

	public void setChildItemList(List childItemList) {
		this.childItemList = childItemList;
	}

	public void setEquipAfter(String equipAfter) {
		this.equipAfter = equipAfter;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getEqTypeId() {
		return eqTypeId;
	}

	public void setEqTypeId(String eqTypeId) {
		this.eqTypeId = eqTypeId;
	}

	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}

	public Map getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map menuMap) {
		this.menuMap = menuMap;
	}

	/**
	 * @return the buildingBusiness
	 */
	public BuildingBusiness getBuildingBusiness() {
		return buildingBusiness;
	}

	/**
	 * @param buildingBusiness the buildingBusiness to set
	 */
	public void setBuildingBusiness(BuildingBusiness buildingBusiness) {
		this.buildingBusiness = buildingBusiness;
	}


	/**
	 * @param groupPic the groupPic to set
	 */
	public void setGroupPic(File groupPic) {
		this.groupPic = groupPic;
	}

	/**
	 * @return the groupPicFileName
	 */
	public String getGroupPicFileName() {
		return groupPicFileName;
	}

	/**
	 * @param groupPicFileName the groupPicFileName to set
	 */
	public void setGroupPicFileName(String groupPicFileName) {
		this.groupPicFileName = groupPicFileName;
	}

	/**
	 * @return the groupPicContentType
	 */
	public String getGroupPicContentType() {
		return groupPicContentType;
	}

	/**
	 * @param groupPicContentType the groupPicContentType to set
	 */
	public void setGroupPicContentType(String groupPicContentType) {
		this.groupPicContentType = groupPicContentType;
	}

	/**
	 * @return the groupPic
	 */
	public File getGroupPic() {
		return groupPic;
	}
}
