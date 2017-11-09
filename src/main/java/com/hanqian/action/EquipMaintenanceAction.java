package com.hanqian.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.EquipMaintenanceBusiness;
import com.hanqian.business.EquipTypeBusiness;
import com.hanqian.form.VOMaintenance;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.DbEquipType;
import com.hanqian.pojo.DbMaintenance;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

/**
 * 
 * 描 述: 设备维护信息Action类 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c)
 * 2010 Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
public class EquipMaintenanceAction extends ActionSupport {

	private static final Log log = LogFactory
			.getLog(EquipMaintenanceAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private EquipMaintenanceBusiness equipMaintenanceBusiness;
	private EquipTypeBusiness equipTypeBusiness; // 设备的系统类型
	private HttpServletRequest request;
	private List<DbMaintenance> mainTenanceList;
	private EquipListBusiness equipListBusiness;
	private DbEquipList dbEquipList;
	private String editFlag = "2"; // 权限标识
	private String message; // 显示信息
	private VOMaintenance voMaintenance;
	private Page page;
	private Page mainPage;
	private Integer pageSize=10;
	private Integer currentPage;
	// 权限
	private MenuInterceptor menuInterceptor;
	private Map menuMap;
	private DbMaintenance dbMaintenance;
	/**
	 * 根据条件查询设备信息，以分页展示
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String findEquipmentByPage() {
		RetCode rc = new RetCode();
		DbMaintenance dbMaintenance = new DbMaintenance();
		DbEquipList equipList = new DbEquipList();
		request = ServletActionContext.getRequest();
		String equipId = request.getParameter("equipId");
		if(currentPage==null){
			currentPage=1;
		}
		if (StringUtils.isNotEmpty(equipId)) {
			equipList.setEquipId(Integer.parseInt(equipId));
			request.setAttribute("equipId", equipId);
		}
		if (equipList.getEquipId() != null) {
			dbMaintenance.setDbEquipList(equipList);
		}
		if (dbMaintenance.getDbEquipList() != null) {
			if (dbMaintenance.getDbEquipList().getEquipId() != null) {
				rc = equipMaintenanceBusiness.findAllMaintenance(dbMaintenance,
						currentPage, pageSize);
			}
		}
		mainTenanceList = new ArrayList<DbMaintenance>();
		if (rc != null) {
			if (rc.getObj() != null) {
				mainTenanceList = (List<DbMaintenance>) rc.getObj();
				page=rc.getPage();
			}
		}
		dbEquipList = equipListBusiness.findDbEquipListById(Integer
				.parseInt(equipId));
		return "maintenanceList";
	}

	/**
	 * 跳转到设备维护信息添加页面
	 * 
	 * @return
	 */
	public String addMaintenanceUI() {
		request = ServletActionContext.getRequest();
		String equipId = request.getParameter("equipId");
		if (StringUtils.isNotEmpty(equipId)) {
			request.setAttribute("equipId", equipId);
		}
		return "addUI";
	}
	/**
	 * 保存设备维护信息
	 * 
	 * @return
	 */
	public String insertMaintenance() {
		request = ServletActionContext.getRequest();
		try {
			String oper = "";
			if(request.getSession().getAttribute("users")!=null){
				DbUsers user = (DbUsers) request.getSession().getAttribute("users");
				oper = user.getUsername();
			}
			RetCode rc = new RetCode();
			String equipId = request.getParameter("equipId");
			String repairPerson = request.getParameter("serviceman");
			String repairCompany = request.getParameter("company");
			String repairTime = request.getParameter("servicetime");
			//维修合同编号
			String pact = request.getParameter("pact");
			String mtype = request.getParameter("maintenancetype");
			//备注
			String remark = request.getParameter("remark");
			//内容
			String content = request.getParameter("content");
			dbMaintenance.setCompany(repairCompany);
			dbMaintenance.setServiceman(repairPerson);
			dbMaintenance.setContent(content);
			dbMaintenance.setPact(pact);
			dbMaintenance.setRemark(remark);
			if (StringUtil.isNotEmpty(mtype)) {
				dbMaintenance.setMaintenancetype(Integer.parseInt(mtype));
			}
			if (StringUtil.isNotEmpty(repairTime)) {
				repairTime +=" 00:00:00";
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d1 = format.parse(repairTime);
				dbMaintenance.setServicetime(d1);
			}
			DbEquipList equipList = new DbEquipList();
			if (StringUtils.isNotEmpty(equipId)) {
				equipList.setEquipId(Integer.parseInt(equipId));
				request.setAttribute("equipId", equipId);
			}
			
			dbMaintenance.setDbEquipList(equipList);
			dbMaintenance.setOper(oper);
			dbMaintenance.setOpertime(new Date());
			equipMaintenanceBusiness.insertMaintenance(dbMaintenance);
	
			DbMaintenance dbMainten = new DbMaintenance();
	
			if (equipList.getEquipId() != null) {
				dbMainten.setDbEquipList(equipList);
			}
			if (dbMainten.getDbEquipList() != null) {
				if (dbMainten.getDbEquipList().getEquipId() != null) {
					rc = equipMaintenanceBusiness.findAllMaintenance(dbMainten,
							1, 10);
				}
			}
			mainTenanceList = new ArrayList<DbMaintenance>();
			if (rc != null) {
				if (rc.getObj() != null) {
					mainTenanceList = (List<DbMaintenance>) rc.getObj();
					page=rc.getPage();
				}
			}
			dbEquipList = equipListBusiness.findDbEquipListById(Integer
				.parseInt(equipId));
		} catch (Exception e) {
			
			log.error("EquipMaintenanceAction-->insertMaintenance",e);
		}
		return "maintenanceList";
	}

	/**
	 * 根据条件查询设备信息，以分页展示
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String findMaintenance() {
		// 判断权限
		menuMap = menuInterceptor.menuIntercept("2004001");
		// 若为空 ，进入登录界面
		if (menuMap == null) {
			MenuInterceptor.toLoginjsp();
			return null;
		}
		request = ServletActionContext.getRequest();
		int currentmPage = 1;
		message = "查询失败";
		RetCode rc = new RetCode();
		String returnStr = "showMaintenance";
		try {
			String currentHospCode = (String) request.getSession().getAttribute("currentHospCode");
			if("RUJ".equalsIgnoreCase(currentHospCode)){
				returnStr  = "showRujMaintenance";
			}
			// 得到第几页
			String mpage = request.getParameter("mpage");
			//楼层
			String storey = request.getParameter("storey");
			if(StringUtil.isNotEmpty(storey)&&!"0".equals(storey)){
				if(voMaintenance==null){
					voMaintenance = new VOMaintenance();
				}
				voMaintenance.setStorey(storey);
			}else{
				if(voMaintenance!=null){
					voMaintenance.setStorey(null);
				}
			}
			if (!SysUtil.isNull(mpage)) {
				currentmPage = Integer.parseInt(mpage);
			}
			// 根据页面查询条件查询相关数据
			request.setAttribute("equipTypeList",
					(List<DbEquipType>) (equipTypeBusiness
							.findAllTopEquipType()).getObj());
			// 放入参数，查询数据
			if (StringUtil.isNotEmpty(request.getParameter("showFlag"))) {
				voMaintenance = new VOMaintenance();
			}
			rc = equipMaintenanceBusiness.findMaintenance(voMaintenance,currentmPage, pageSize);
			
			List mainList = new ArrayList();
			if (rc.getObj() != null) {
				if(voMaintenance!=null && voMaintenance.getEqTypeId()!=null &&  !"".equals(voMaintenance.getEqTypeId())){
					DbEquipType dbEt= equipTypeBusiness.findById(Integer.parseInt(voMaintenance.getEqTypeId()));
					voMaintenance.setEqTypeName(dbEt.getTypeName());
				}
				mainList = (List) rc.getObj();
				request.setAttribute("page", rc.getPage());
				request.setAttribute("mainList", mainList);
				request.setAttribute("currentmPage", currentmPage);
				message = "";
			} else {
				message = "没有查询到数据";
			}
			request.setAttribute("editFlag", "2");
			
		} catch (Exception e) {
			log.error("EquipMaintenanceAction-->findMaintenance",e);
			e.printStackTrace();
		}
	
		return returnStr;
	}

	/**
	 * 跳转修改页面
	 * 
	 * @param statement
	 * @param logisticsNews
	 * @return
	 */
	public String showEditUI() {
		request = ServletActionContext.getRequest();
		int mid = SysUtil.toInt(request.getParameter("mid"));
		try {
			dbMaintenance = equipMaintenanceBusiness.findMaintenanceById(mid);
			request.setAttribute("mark", "edit");
			if(dbMaintenance!=null&&dbMaintenance.getDbEquipList()!=null){
				String equip_id=String.valueOf(dbMaintenance.getDbEquipList().getEquipId());
				String equip_type_id=equipListBusiness.getTypeIdByEpuipId(equip_id);
				List<Map<String,Object>> equipList = equipListBusiness.getEpuipByEquipId(equip_id);
				List<Map<String,Object>> equipListOpt = equipListBusiness.getEpuipByTypeId(equip_type_id);
				request.setAttribute("equipList",equipList);
				request.setAttribute("equipListOpt",equipListOpt);
			}else{
				log.error("dbMaintenance为空");
			}
		} catch (Exception e) {
			log.error("EquipMaintenanceAction-->showEditUI",e);
			e.printStackTrace();
		}
		return "editMain";
	}

	/**
	 * 修改
	 * 
	 * @param statement
	 * @param logisticsNews
	 * @return
	 */
	public String updateMaintenance() {
		request = ServletActionContext.getRequest();
		message = "修改失败，请重新登录";
		try {
			dbMaintenance.setDbEquipList(dbEquipList);
			// 执行修改的方法
			boolean isRight = equipMaintenanceBusiness.updateMaintenance(dbMaintenance);
			if (isRight) {
				message = "修改成功";
			} else {
				message = "修改失败，请重新登录";
			}
		} catch (Exception e) {
			log.error("EquipMaintenanceAction-->updateMaintenance",e);
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		voMaintenance = new VOMaintenance();
		return findMaintenance();
	}

	/**
	 * 删除
	 * 
	 * @param statement
	 * @param logisticsNews
	 * @return
	 */
	public String deleteMaintenance() {
		request = ServletActionContext.getRequest();
		// 从页面得到要删除的数据的ID
		int mid = SysUtil.toInt(request.getParameter("mid"));
		message = "删除失败，请重新登录";
		try {
			// 执行修改的方法
			equipMaintenanceBusiness.deleteMaintenance(mid);
			message = "删除成功";
		} catch (Exception e) {
			log.error("EquipMaintenanceAction-->deleteMaintenance",e);
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		voMaintenance  = new VOMaintenance();
		return findMaintenance();
	}

	/**
	 * 查看需要维修的设备
	 * 
	 * @param hospId 医院ID
	 * @return
	 */
	public String findServeMaintenance() {
		int currentPage = 1;
		int pageSize = 10;
		int num = 0;
		request = ServletActionContext.getRequest();
		String page = request.getParameter("page1");
		if (!SysUtil.isNull(page)) {
			currentPage = Integer.parseInt(page);
		}
		String hospid = "1";
		RetCode rc = null;
		try {
			rc = equipListBusiness.findEquipMaintenance(hospid, currentPage,
					pageSize);
			request.setAttribute("page", rc.getPage());
			List equipList = (List) rc.getObj();
			if (equipList != null) {
				num = equipList.size();
			}
			request.setAttribute("equipList", equipList);
			request.setAttribute("num", num);
		} catch (Exception e) {
			log.error("EquipMaintenanceAction-->findServeMaintenance",e);
			e.printStackTrace();
		}
		request.setAttribute("editFlag", "2");
		return "showNeedServe";
	}

	/**
	 * 查询需要维修的设备数
	 * 
	 */
	public void findServiceEquip(){
		equipListBusiness = new EquipListBusiness();
		try {
			RetCode rc = equipListBusiness.findEquipMaintenance("1");
			if (rc != null) {
				List equipList = (List) rc.getObj();
				if (equipList != null) {
					request.setAttribute("num", equipList.size());
				}
			}
		} catch (Exception e) {
			log.error("EquipMaintenanceAction-->findServiceEquip",e);
			e.printStackTrace();
		}
	}
	
	public void getEpuipByTypeId(){
		request = ServletActionContext.getRequest();
		String equip_type_id=request.getParameter("type_id");
		List<Map<String,Object>> equipList = equipListBusiness.getEpuipByTypeId(equip_type_id);
		JSONArray ja=JSONArray.fromObject(equipList);
		String resultJSON=ja.toString();
		try {
			ServletActionContext.getResponse().getWriter().print(resultJSON);
		} catch (IOException e) {
			log.error("EquipMaintenanceAction-->getEpuipByTypeId",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * get、set
	 */
	public DbEquipList getDbEquipList() {
		return dbEquipList;
	}

	public void setDbEquipList(DbEquipList dbEquipList) {
		this.dbEquipList = dbEquipList;
	}

	public EquipListBusiness getEquipListBusiness() {
		return equipListBusiness;
	}

	public void setEquipListBusiness(EquipListBusiness equipListBusiness) {
		this.equipListBusiness = equipListBusiness;
	}

	public List<DbMaintenance> getMainTenanceList() {
		return mainTenanceList;
	}

	public void setMainTenanceList(List<DbMaintenance> mainTenanceList) {
		this.mainTenanceList = mainTenanceList;
	}

	public DbMaintenance getDbMaintenance() {
		return dbMaintenance;
	}

	public void setDbMaintenance(DbMaintenance dbMaintenance) {
		this.dbMaintenance = dbMaintenance;
	}

	public EquipMaintenanceBusiness getEquipMaintenanceBusiness() {
		return equipMaintenanceBusiness;
	}

	public void setEquipMaintenanceBusiness(
			EquipMaintenanceBusiness equipMaintenanceBusiness) {
		this.equipMaintenanceBusiness = equipMaintenanceBusiness;
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EquipTypeBusiness getEquipTypeBusiness() {
		return equipTypeBusiness;
	}

	public void setEquipTypeBusiness(EquipTypeBusiness equipTypeBusiness) {
		this.equipTypeBusiness = equipTypeBusiness;
	}

	public VOMaintenance getVoMaintenance() {
		return voMaintenance;
	}

	public void setVoMaintenance(VOMaintenance voMaintenance) {
		this.voMaintenance = voMaintenance;
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

	public Page getMainPage() {
		return mainPage;
	}

	public void setMainPage(Page mainPage) {
		this.mainPage = mainPage;
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
}
