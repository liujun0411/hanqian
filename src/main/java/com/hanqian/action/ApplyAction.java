package com.hanqian.action;
/**
 * @author lx
 */
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.ApplyBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.business.MaterialBusiness;
import com.hanqian.pojo.DbApply;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbMaterial;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

@SuppressWarnings("unchecked")
public class ApplyAction {
	private DbApply dbApply;
	private ApplyBusiness applyBusiness;
	private HospInfoBusiness hospInfoBusiness;
	private MaterialBusiness materialBusiness;
	private String hospid;
	private String hospName;
	private List<DbHospInfo> listdbHospInfo;
	private MenuInterceptor menuInterceptor;
	private static final Logger logg = Logger.getLogger(ApplyAction.class);
	private Map menuMap;
	public String findRoles(){
		menuMap=menuInterceptor.menuIntercept("2005002");
		//若为空 ，进入登录界面
		if(menuMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}
		return "roleList";
	}
	/**
	 * 显示物资领用记录列表
	 * @return
	 */
	public String showApply() {
		HttpServletRequest request = ServletActionContext.getRequest();
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
			DbApply apply= new DbApply();
			
			RetCode applyCode = applyBusiness.findApply(apply, currentPage,
					pageSize);
			if (applyCode.getCode() == 0) {
				List<DbApply> dbApplys = (List<DbApply>) applyCode.getObj();
				request.setAttribute("applyList", dbApplys);
				request.setAttribute("page", applyCode.getPage());

			}
			request.setAttribute("editFlag",request.getSession().getAttribute("editFlag"));
			logg.info("page="+page+";editFlag="+request.getSession().getAttribute("editFlag"));
		} catch (NullPointerException e) {
			e.printStackTrace();
			logg.error("ApplyAction-->showApply",e);
		}
		findRoles();
		//DbHospInfo info = hospInfoBusiness.findHospInfoForId(hospid);
		//hospName = info.getHospName();
		return "show";
	}
	
	/**
	 * 添加物资领用记录
	 * @param 2013-3-18  
	 * @param @return       
	 * @return String
	 */
	public String addApply() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DbMaterial material=new DbMaterial();
		//查询库存记录
		RetCode retCode= materialBusiness.findMaterial(material, null, null);
		if(retCode.getObj()!=null){
			List<DbMaterial> dbMaterials=(List<DbMaterial>) retCode.getObj();
			request.setAttribute("materialList", dbMaterials);
		}
		Integer seq=Integer.parseInt(request.getParameter("seq"));
		String materialName=request.getParameter("materialName");
		try {
			materialName = new String (materialName.getBytes("iso-8859-1"),"UTF-8");
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			logg.error("ApplyAction-->addApply",e1);
			e1.printStackTrace();
			
		}
		//DbMaterial material=new DbMaterial();
		material.setSeq(seq);
		material.setName(materialName);
		logg.info("seq="+seq+";添加物资领用记录:materialName="+materialName);
		try {
			dbApply.setStatus(0);
			dbApply.setDbMaterial(material);
			//添加领用记录
			applyBusiness.insertApply(dbApply);
			request.setAttribute("message", "操作成功");
			request.setAttribute("hsopid", hospid);
		} catch (Exception e) {
			request.setAttribute("message", "操作失败");
			logg.error("ApplyAction-->addApply",e);
			e.printStackTrace();
		}
		request.setAttribute("editFlag",request.getParameter("editFlag"));
		showApply();
		return "show";
	}
	/**
	 * 根据id查找领用记录
	 * @return
	 */
	public String findById() {
		HttpServletRequest request = ServletActionContext.getRequest();

		try {
			DbMaterial material=new DbMaterial();
			RetCode retCode= materialBusiness.findMaterial(material, null, null);
			if(retCode.getObj()!=null){
				List<DbMaterial> dbMaterials=(List<DbMaterial>) retCode.getObj();
				request.setAttribute("materialList", dbMaterials);
			}
			
			Integer id = Integer.parseInt(request.getParameter("seq"));
			DbApply apply = new DbApply();
			apply.setSeq(id);
			RetCode dbApplyCode = applyBusiness.findApply(apply);
			if (dbApplyCode.getCode() == 0) {
				List<DbApply> dbApplies = (List<DbApply>) dbApplyCode.getObj();
				DbApply dbApply = new DbApply();
				dbApply = dbApplies.get(0);
				request.setAttribute("dbApply", dbApply);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logg.error("ApplyAction-->findById",e);
			e.printStackTrace();
		}
		return "applyjsp";
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String updateApply() {
        HttpServletRequest request = ServletActionContext.getRequest();
		DbApply dbApplyObj= applyBusiness.findById(dbApply.getSeq());
		dbApplyObj=dbApply;
		dbApplyObj.setStatus(0);
		DbApply dbApply = applyBusiness.updateApply(dbApplyObj);
		
		if (dbApply != null) {
			request.setAttribute("message", "操作成功");
		} else {
			request.setAttribute("message", "操作失败");
		}
		showApply();
		return "show";
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 */
	public String deleteMaterial() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			Integer id =Integer.parseInt(request.getParameter("seq")) ;
			DbApply dbApply =applyBusiness.findById(id);
			applyBusiness.deleteApply(dbApply);
			request.setAttribute("message", "操作成功");
			logg.info("id="+id);
		} catch (Exception e) {
			logg.error("ApplyAction-->deleteMaterial",e);
			// TODO Auto-generated catch block
			request.setAttribute("message", "操作失败");
			e.printStackTrace();
		}
		showApply();
		return "show";
	}
	
	public String toApplyJSP() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DbMaterial material=new DbMaterial();
		try {
			RetCode retCode= materialBusiness.findMaterial(material, null, null);
			if(retCode.getObj()!=null){
				List<DbMaterial> dbMaterials=(List<DbMaterial>) retCode.getObj();
				request.setAttribute("materialList", dbMaterials);
				String applyAdd = request.getParameter("applyAdd");
				request.setAttribute("applyAdd", applyAdd);
				logg.info("applyAdd="+applyAdd);
			}
		} catch (Exception e) {
			logg.error("ApplyAction-->toApplyJSP",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("editFlag",request.getParameter("editFlag"));
		return "applyjsp";
	}

	public DbApply getDbApply() {
		return dbApply;
	}

	public void setDbApply(DbApply dbApply) {
		this.dbApply = dbApply;
	}

	public ApplyBusiness getApplyBusiness() {
		return applyBusiness;
	}

	public void setApplyBusiness(ApplyBusiness applyBusiness) {
		this.applyBusiness = applyBusiness;
	}

	public HospInfoBusiness getHospInfoBusiness() {
		return hospInfoBusiness;
	}

	public void setHospInfoBusiness(HospInfoBusiness hospInfoBusiness) {
		this.hospInfoBusiness = hospInfoBusiness;
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

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
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
