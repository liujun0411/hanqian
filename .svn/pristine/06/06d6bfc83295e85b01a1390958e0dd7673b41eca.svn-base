package com.hanqian.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.HospInfoBusiness;
import com.hanqian.business.MaterialBusiness;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbMaterial;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

public class MaterialAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(MaterialAction.class);
	private String echoCheck;
	private String echoName;
	private String echoBeginDate;
	private String echoEndDate;
	private DbMaterial material;
	private MaterialBusiness materialBusiness;
	private String hospid;
	private HospInfoBusiness hospInfoBusiness;
	private List<DbHospInfo> listdbHospInfo;
	private MenuInterceptor menuInterceptor;
	
	private Map menuMap;
	public String findRoles(){
		menuMap=menuInterceptor.menuIntercept("2005001");
		//若为空 ，进入登录界面
		if(menuMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}
		return "roleList";
	}

	/**
	 * 资产物资列表显示
	 * 
	 * @return
	 */
	public String showMaterial() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String strMess=request.getParameter("message");
		
		// 获取 所有医院列表
		RetCode rc2 = hospInfoBusiness.findAllHos();
		listdbHospInfo = (List<DbHospInfo>) rc2.getObj();

		try {
			int currentPage = 1;
			int pageSize = 10;
			String page = request.getParameter("page");
			if (!SysUtil.isNull(page)) {
				currentPage = Integer.parseInt(page);
			}

			String checks = request.getParameter("checks");
			String materialName = request.getParameter("materialName");
			String begin = request.getParameter("beginDate");
			String end = request.getParameter("endDate");
			DbMaterial dbmaterial = new DbMaterial();
			if (!SysUtil.isNull(checks)) {
				echoCheck = checks;
				dbmaterial.setApparatusno(checks);
			}
			if (!SysUtil.isNull(materialName)) {
				echoName = materialName;
				dbmaterial.setName(materialName);
			}
//			Date beginDate = null;
//			Date endDate = null;
			if (!SysUtil.isNull(begin)) {
				echoBeginDate = begin;
//				beginDate = Systime.StringToDate(begin, "yyyy-MM-dd");
			}
			if (!SysUtil.isNull(end)) {
				echoEndDate = end;
//				endDate = Systime.StringToDate(end, "yyyy-MM-dd");
			}
			
			RetCode materialCode = materialBusiness.findMaterial(dbmaterial,
					currentPage, pageSize, begin, end);
			if (materialCode.getCode() == 0) {
				List<DbMaterial> dbMaterials = (List<DbMaterial>) materialCode
						.getObj();
				request.setAttribute("materialList", dbMaterials);
				request.setAttribute("page", materialCode.getPage());
			}
			if(strMess!=null){
			request.setAttribute("message", strMess);
			}
			
			request.setAttribute("editFlag",request.getSession().getAttribute("editFlag"));
		} catch (Exception e) {
			logg.error("MaterialAction-->showMaterial", e);
			e.printStackTrace();
		}
		findRoles();
		return "show";
	}
	
	
	//添加物资
	public String insertMaterial() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			material.setStatus2(0);
			materialBusiness.insertMaterial(material);
			//findRoles();
			request.setAttribute("message", "操作成功");
			request.setAttribute("hospid", hospid);
		} catch (Exception e) {
			logg.error("MaterialAction-->insertMaterial", e);
			request.setAttribute("message", "操作失败");
			e.printStackTrace();
		}

		String editFlag=request.getParameter("editFlag");
		request.setAttribute("hospid", hospid);
		request.setAttribute("editFlag",editFlag);
		showMaterial();
		return "show";
	}
	
	/**
	 * 根据ID查询物资
	 * @return
	 */
	public String findMaterialId() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = Integer.parseInt(request.getParameter("seq"));
		DbMaterial dbMaterial = new DbMaterial();
		dbMaterial.setSeq(id);
		RetCode materialCode = materialBusiness.findMaterial(dbMaterial, null, null);
		if (materialCode.getCode() == 0) {
			List<DbMaterial> materials = (List<DbMaterial>) materialCode
					.getObj();
			
			Map materialsMap = (Map) materials.get(0);
//			DbMaterial dbmaterials = materials.get(0);
			logg.debug("         materialsMap:         "+materialsMap);
			request.setAttribute("material", materialsMap);
		}
		return "updateMaterial";
	}
	/**
	 * 修改
	 * 
	 * @return
	 */
	public String updateMaterial() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		DbMaterial dbMaterialObj=materialBusiness.findById(material.getSeq());
		dbMaterialObj=material;
		dbMaterialObj.setStatus2(0);
		DbMaterial dbMaterial = materialBusiness.updateMaterial(dbMaterialObj);
		
		if (dbMaterial != null) {
			request.setAttribute("message", "操作成功");
		} else {
			request.setAttribute("message", "操作失败");
		}
		
		String editFlag=request.getParameter("editFlag");
		request.setAttribute("hospid", hospid);
		request.setAttribute("editFlag",editFlag);
		showMaterial();
		return "show";
	}
	/**
	 * 报废
	 * 
	 * @return
	 */
	public String deleteMaterial() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			Integer id =Integer.parseInt(request.getParameter("seq")) ;
			
			
			DbMaterial dbMaterial =materialBusiness.findById(id);
			materialBusiness.deleteMaterial(dbMaterial);
			request.setAttribute("message", "操作成功");
		} catch (Exception e) {
			logg.error("MaterialAction-->deleteMaterial", e);
			// TODO Auto-generated catch block
			request.setAttribute("message", "操作失败");
			e.printStackTrace();
		}
		String editFlag=request.getParameter("editFlag");
		request.setAttribute("hospid", hospid);
		request.setAttribute("editFlag",editFlag);
		showMaterial();
		return "show";
	}
	
	public String toMaterialAdd() {
		// materialAdd
		HttpServletRequest request = ServletActionContext.getRequest();
		String materialAdd = request.getParameter("materialAdd");
		request.setAttribute("materialAdd", materialAdd);
		String editFlag=request.getParameter("editFlag");
		request.setAttribute("editFlag",editFlag);
		return "materialadd";
	}

	public void getMaintenance(HashMap reportMap, DbReportRate reportRate) {
		RetCode MaintenanceCode = materialBusiness.reportMaintenance(reportRate
				.getReportdate());
		if (MaintenanceCode.getObj() != null) {
			reportMap.put(reportRate.getDbReportType().getTypeId() + "",
					MaintenanceCode.getObj());
		}
	}
	public String getEchoCheck() {
		return echoCheck;
	}


	public void setEchoCheck(String echoCheck) {
		this.echoCheck = echoCheck;
	}


	public String getEchoName() {
		return echoName;
	}


	public void setEchoName(String echoName) {
		this.echoName = echoName;
	}


	public String getEchoBeginDate() {
		return echoBeginDate;
	}


	public void setEchoBeginDate(String echoBeginDate) {
		this.echoBeginDate = echoBeginDate;
	}


	public String getEchoEndDate() {
		return echoEndDate;
	}


	public void setEchoEndDate(String echoEndDate) {
		this.echoEndDate = echoEndDate;
	}


	public DbMaterial getMaterial() {
		return material;
	}


	public void setMaterial(DbMaterial material) {
		this.material = material;
	}


	public MaterialBusiness getMaterialBusiness() {
		return materialBusiness;
	}


	public void setMaterialBusiness(MaterialBusiness materialBusiness) {
		this.materialBusiness = materialBusiness;
	}


	public String getHospid() {
		return hospid;
	}


	public void setHospid(String hospid) {
		this.hospid = hospid;
	}



	public HospInfoBusiness getHospInfoBusiness() {
		return hospInfoBusiness;
	}


	public void setHospInfoBusiness(HospInfoBusiness hospInfoBusiness) {
		this.hospInfoBusiness = hospInfoBusiness;
	}


	public List<DbHospInfo> getListdbHospInfo() {
		return listdbHospInfo;
	}


	public void setListdbHospInfo(List<DbHospInfo> listdbHospInfo) {
		this.listdbHospInfo = listdbHospInfo;
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
