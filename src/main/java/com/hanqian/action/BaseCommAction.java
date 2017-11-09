package com.hanqian.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BaseCommBusiness;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2013-12-20
 * @see
 */
public class BaseCommAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(BaseCommAction.class);
	private HttpServletRequest request;
	private List dataList;
	private List typeList;
	private Page page;
	private Integer pageSize = 10;
	private Integer currentPage = 1;
	private String typeId;
	private BaseCommBusiness baseCommBusiness;
	private String resultJSON;
	
	private MenuInterceptor menuInterceptor;  //权限过滤器
	public Map menuIdMap; //权限MAP集合

	public String getResultJSON() {
		return resultJSON;
	}

	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}

	/**
	 * 获得所有的基础数据
	 * 
	 * @param 2013-12-20
	 * @param @return
	 * @return String
	 */
	public String getAllDataBase() {
		request = ServletActionContext.getRequest();
		typeList = new ArrayList();
		dataList = new ArrayList();
		//权限配置
		menuIdMap=menuInterceptor.menuIntercept("5012");
		//若为空 ，进入登录界面
		if(menuIdMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}
		String current = request.getParameter("currentPage");
		typeId = request.getParameter("typeId");
		if(StringUtils.isNotEmpty(current)){
			currentPage = Integer.parseInt(current);
		}else{
			currentPage = 1;
		}
		RetCode rcType = baseCommBusiness.findAllBaseType();
		if (rcType != null && rcType.getObj() != null) {
			typeList = (List) rcType.getObj();
			if (typeList.size() > 0) {
				Map temp = new HashMap();
				if(StringUtils.isNotEmpty(typeId)){
					for (int i=0;i<typeList.size();i++) {
						Map map = new HashMap();
						map = (Map)typeList.get(i);
						if(map.get("seq").toString().equals(typeId)){
							temp = map;
							break;
						}
					}
				}else{
					temp = (Map)typeList.get(0);
				}
				String seq = temp.get("seq").toString();
				RetCode rc = baseCommBusiness.findAllBaseData(seq, currentPage,
						pageSize);
				if (temp.get("field1") != null && temp.get("field1") != "") {
					request.setAttribute("title1", temp.get("field1"));
				} else {
					request.setAttribute("title1", "类型");
				}
				if (temp.get("field2") != null && temp.get("field2") != "") {
					request.setAttribute("title2", temp.get("field2"));
				} else {
					request.setAttribute("title2", "描述");
				}
				if (rc != null && rc.getObj() != null) {
					dataList = (List) rc.getObj();
					page = rc.getPage();
					request.setAttribute("pageSum", page.getTotalPages());
				}
			}
		}
		return "baseData";
	}

	public void addOrUpdateBaseComm() {
		try {
			JSONObject json = new JSONObject();
			request = ServletActionContext.getRequest();
			String content1 = request.getParameter("cont1");
			String content2 = request.getParameter("cont2");
			String type = request.getParameter("type");
			String id = request.getParameter("id");
			String flag = request.getParameter("flag");
			Map temp = new HashMap();
			temp.put("content1", content1);
			temp.put("content2", content2);
			temp.put("type", type);
			temp.put("id", id);
			if (flag.equals("add")) {
				if (baseCommBusiness.addBaseData(temp)) {
					json.accumulate("resStr", "true");
				} else {
					json.accumulate("resStr", "false");
				}
			} else {
				if (baseCommBusiness.updateBaseData(temp)) {
					json.accumulate("resStr", "true");
				} else {
					json.accumulate("resStr", "false");
				}
			}
			resultJSON = json.toString();
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(
					resultJSON.toString());
		} catch (Exception e) {
			logg.error("BaseCommAction-->addOrUpdateBaseComm", e);
			e.printStackTrace();
		}
	}

	/**
	 * 理论删除基础数据
	 * 
	 * @param 2013-12-23
	 * @param
	 * @return void
	 */
	public void deleteBaseData() {
		try {
			request = ServletActionContext.getRequest();
			JSONObject json = new JSONObject();
			String id = request.getParameter("delId");
			if (StringUtils.isNotBlank(id)) {
				if (baseCommBusiness.deleteBaseData(Integer.parseInt(id))) {
					json.accumulate("resStr", "true");
				} else {
					json.accumulate("resStr", "true");
				}
			} else {
				json.accumulate("resStr", "true");
			}
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(
					json.toString());
		} catch (Exception e) {
			logg.error("BaseCommAction-->deleteBaseData", e);
			e.printStackTrace();
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public BaseCommBusiness getBaseCommBusiness() {
		return baseCommBusiness;
	}

	public void setBaseCommBusiness(BaseCommBusiness baseCommBusiness) {
		this.baseCommBusiness = baseCommBusiness;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}

	public Map getMenuIdMap() {
		return menuIdMap;
	}

	public void setMenuIdMap(Map menuIdMap) {
		this.menuIdMap = menuIdMap;
	}
}
