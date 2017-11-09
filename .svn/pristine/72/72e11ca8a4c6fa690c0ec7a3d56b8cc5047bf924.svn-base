package com.hanqian.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.KeyEqBusiness;
import com.hanqian.form.VOKeyEqGroup;
import com.hanqian.form.VOKeyEqS;
import com.hanqian.form.VOKeyPoint;
import com.hanqian.pojo.DbEquipType;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;

/**
 * 
 * 描 述: 关键设备Action
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * @author 李江
 * @version 1.4 2012-9-20
 * @see
 */
public class KeyEqAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(KeyEqAction.class);
	private HttpServletRequest request;
	
	private KeyEqBusiness keyEqBusiness;	
	
	private Integer groupId; //组别ID
	
	private String groupName; //组别名称
	
	private String sqlStr; //
	
	private Integer eqtype; //设备类型
	
	private List<DbEquipType> typeList; //设备类型集合
	
	private Map keyEqMenuMap;
	
	private MenuInterceptor menuInterceptor;
	
	/**
	 * 登陆是否失效 false 失效 ；true 正常
	 * 
	 * @return
	 */
	private boolean LoadisFail(DbUsers dbusers) {
		if (null == dbusers) {
			return false;
		}
		return true;
	}
	/**
	 * 显示组列表
	 */
	private void gotoGroupList() {
		int currentPage = 1;
		int pageSize = 10;
		String wherestr = request.getParameter("wherestr");

		request.setAttribute("wherestr", wherestr);
		try {
			String page = request.getParameter("page");
			if (!SysUtil.isNull(page)) {
				currentPage = Integer.parseInt(page);
			}
		} catch (Exception e) {
			logg.error("KeyEqAction-->gotoGroupList",e);
			e.printStackTrace();
			currentPage = 1;
		}
		RetCode rc = keyEqBusiness.findGroupList(currentPage, pageSize,
				wherestr);
		List<VOKeyEqGroup> groupListTest = (List<VOKeyEqGroup>) rc.getObj();
		if (!SysUtil.isNullObject(groupListTest)) {
			for (int i = 0; i < groupListTest.size(); i++) {
				List<VOKeyEqS> elist = (List<VOKeyEqS>) groupListTest.get(i)
						.getEquipList();
				for (int j = 0; j < elist.size(); j++) {
					List<VOKeyPoint> l = (List<VOKeyPoint>) elist.get(j)
							.getPoints();
					// System.err.println(elist.get(j).getName()+"设备点位个数"+l.size());
				}
			}
		}
		request.setAttribute("groupList", groupListTest);

		request.setAttribute("page", rc.getPage());
	}
	/**
	 * 设备类型列表
	 * @return
	 */
	public String showEqTypeList() {
		request = ServletActionContext.getRequest();

		RetCode rt = new RetCode();
		rt = keyEqBusiness.findEqTypeList();
		if (rt.getObj() != null) {
			typeList = (List<DbEquipType>) rt.getObj();
		}
		request.setAttribute("eqtypeList", typeList);
		for (int i = 0; i < typeList.size(); i++) {
		}
		return "eqtypeList";
	}

	/**
	 * 设备列表
	 * 
	 * @return
	 */
	public String showEqList() {
		request = ServletActionContext.getRequest();
		request.setAttribute("eqList", keyEqBusiness.findEqList(eqtype));
		return "eqList";
	}

	/**
	 * 设备点位列表
	 * 
	 * @return
	 */
	public String showPointList() {
		request = ServletActionContext.getRequest();
		
		if (!SysUtil.isNullObject(request.getParameter("equipId"))) {
			Integer equipId = Integer.parseInt(request.getParameter("equipId"));
			List list = keyEqBusiness.findPointList(equipId);
			request.setAttribute("pointList", keyEqBusiness
					.findPointList(equipId));
			request.setAttribute("equipId", equipId);
		}
		return "pointList";
	}

	/**
	 * 删除关键设备组
	 * 
	 * @return
	 */
	public String deleteGroup() {
		request = ServletActionContext.getRequest();
		
		boolean result = keyEqBusiness.deleteGroup(groupId);
		this.gotoGroupList();
		request.setAttribute("message", result ? "操作成功!" : "操作失败!");
		return "groupList";
	}

	/**
	 * 更新关键设备组
	 * 
	 * @return
	 */
	public String updateGroupList() {
		request = ServletActionContext.getRequest();
		RetCode rt = keyEqBusiness.updateGroupList(groupId, groupName, sqlStr);
		this.gotoGroupList();
		request.setAttribute("message", rt.getDesc());
		return "groupList";
	}
	/**
	 * 判断关键设备组组名是否存在
	 * @param 2013-4-2  
	 * @param        
	 * @return void
	 */
	public void judgeIsUnique(){
		request = ServletActionContext.getRequest();
		JSONObject object = new JSONObject();
		String groupName = request.getParameter("groupName");
		String groupId = request.getParameter("groupId");
		try {
			if(StringUtil.isNotEmpty(groupName)){
				object.accumulate("result",keyEqBusiness.IsUniqueGroup(groupName, groupId));
			}
			ServletActionContext.getResponse().getWriter().print(object);
		} catch (IOException e) {
			logg.error("KeyEqAction-->judgeIsUnique",e);
			e.printStackTrace();
		}
	}
	/**
	 * 加载更新界面
	 * 
	 * @return
	 */
	public String showGroup() {
		request = ServletActionContext.getRequest();
		
		if (!SysUtil.isNullObject(groupId)&& !SysUtil.isNull(groupName)) {
			request.setAttribute("eqList", keyEqBusiness.findGrouEq(groupId));
			request.setAttribute("sqlStr", keyEqBusiness.findGrouEqStr(groupId));
			request.setAttribute("groupId", groupId);
			request.setAttribute("groupName", groupName);
		}else{
			request.setAttribute("groupId", "");
			request.setAttribute("sqlStr", "");
		}

		return "updateGroup";
	}

	/**
	 * 关键设备组列表
	 * 
	 * @return
	 */
	public String showGroupList() {
		
		request = ServletActionContext.getRequest();
		//权限配置
		keyEqMenuMap=menuInterceptor.menuIntercept("5005");
		//若为空 ，进入登录界面
		if(keyEqMenuMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}

		this.gotoGroupList();
		return "groupList";
	}

	/**
	 * 关键设备组列表
	 * 
	 * @return
	 */
	public String showSelGroup() {
		request = ServletActionContext.getRequest();

		try {
			RetCode rc = keyEqBusiness.findSelGroup();
			request.setAttribute("groupList", rc.getObj());
			request.setAttribute("message", rc.getDesc());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "selGroup";
	}

	/**
	 * 关键设备列表
	 * 
	 * @return
	 */
	public String showKeyEqCollect() {
		request = ServletActionContext.getRequest();

		try {
			RetCode rt = keyEqBusiness.findKeyEqCollect(groupId);
			request.setAttribute("obj", rt.getObj());
			request.setAttribute("message", rt.getDesc());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "keyeqcollect";
	}

	/**
	 * 关键点位读数
	 */
	public void showKeyPointValue() {
		request = ServletActionContext.getRequest();
		String points = request.getParameter("points");
		String img = request.getParameter("param");
		if (!SysUtil.isNull(points)) {
			String pointsTemp = points;
			points=points.replace("U", ",");
			// System.out.println(points.replace("__", "."));
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=null;
			try {
				out = response.getWriter();
				List<VOKeyPoint>  list =keyEqBusiness.findCurKeyPointValue(points.replace("__", "."),img,pointsTemp);
				JSONObject jsonobj = new JSONObject();
				JSONArray array = new JSONArray();
				if (list != null) {
					VOKeyPoint obj = null;
					String key = null;
					String value = null;
					Map itemMap=new HashMap();
					List removeList=new ArrayList();
					boolean hsBolior=false;
					for (int i = 0; i < list.size(); i++) {
						obj = list.get(i);
						Map map=SysUtil.getZhuanjiaMap();
						String status="";
						String [] item=null;
						if(map.get(obj.getEqTypeId())!=null){
							status=map.get(obj.getEqTypeId()).toString();
							item=status.split(",");
						}
						key = obj.getKeyPoint();
						if(item!=null){
							for(int m=0;m<item.length;m++){
	//							//如果等于故障状态
								int tempLength = 0;
								if(key.indexOf("_")>=0){
									tempLength = key.indexOf("_");
								}else{
									tempLength = key.length();
								}
									
									if(key.substring(key.indexOf(".")+1, tempLength).equals(item[0])){
										if(obj.getValue().equals("故障")){
											if(key.indexOf("_")>=0){
												key=key.substring(0,key.indexOf(".")+1)+item[1]+key.substring(key.indexOf("_"));
											}else{
												key=key.substring(0,key.indexOf(".")+1)+item[1];
												
											}
											removeList.add(key);
										}else{
											removeList.add(obj.getKeyPoint());
										}
									}
								}
							
						}
						key = obj.getKeyPoint().replace(".", "__");
						key = key.replace(",", "U");
						value = obj.getValue();
						if(!hsBolior){
							if(obj!=null && StringUtil.isNotEmpty(obj.getDes())){
								if(obj.getDes().equals("压力状态")){
									removeList.add(key);
									hsBolior=true;
								}
							}
						}
						JSONObject jsonObject = new JSONObject();
						jsonObject.accumulate("projectPoint",key);
						jsonObject.accumulate("pointValue", value);
						array.add(jsonObject);
					}
					 for(int k=0;k<removeList.size();k++){
					    String moveKey=removeList.get(k).toString().replace(".", "__");
					    for(int m=0;m<array.size();m++){
					    	JSONObject jsonObject=(JSONObject)array.get(m);
					    	if(jsonObject.get("projectPoint").equals(moveKey)){
					    		array.remove(jsonObject);
					    		JSONObject jsonObj=new JSONObject();
					    		jsonObj.accumulate("pointValue", "FLAG");
					    		jsonObj.accumulate("projectPoint",moveKey);
					    		array.add(jsonObj);
					    	}
					    }
					 }
				}
				jsonobj.accumulate("points",array);
				out.println(jsonobj.toString());	
				//System.out.println("JSONOBJ:===="+jsonobj.toString());
			} catch (Exception e) {
				logg.error("KeyEqAction-->showKeyPointValue",e);
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush();
					out.close();
				}
			}
		}
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public KeyEqBusiness getKeyEqBusiness() {
		return keyEqBusiness;
	}
	public void setKeyEqBusiness(KeyEqBusiness keyEqBusiness) {
		this.keyEqBusiness = keyEqBusiness;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public Integer getEqtype() {
		return eqtype;
	}
	public void setEqtype(Integer eqtype) {
		this.eqtype = eqtype;
	}
	public List<DbEquipType> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<DbEquipType> typeList) {
		this.typeList = typeList;
	}
	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}
	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}
	public Map getKeyEqMenuMap() {
		return keyEqMenuMap;
	}
	public void setKeyEqMenuMap(Map keyEqMenuMap) {
		this.keyEqMenuMap = keyEqMenuMap;
	}
}
