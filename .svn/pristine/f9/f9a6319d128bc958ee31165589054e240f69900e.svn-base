package com.hanqian.action;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.DbEquipParamBusiness;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.EquipParamBusiness;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbEqParam;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.DicEqParameter;
import com.hanqian.util.RetCode;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 描 述: 设备图纸信息Action类 
 * 前置条件: 
 * 后置条件: 
 * 调用者 : 
 * 被调用者: 
 * 重载说明: 
 * Copyright: Copyright (c)
 * 2010 Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-13
 * @see
 */
public class EqParamAction extends ActionSupport {
	 private static final Log log = LogFactory.getLog(EqParamAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private HttpServletRequest request;
	private DbEquipParamBusiness dbEquipParamBusiness;
	private DbEquipList dbEquipListDetail;
	private BuildingBusiness buildBusiness;     
	private EquipListBusiness equipBusiness;
	private List equipParamList;
	private EquipParamBusiness equipParamBusiness;
	private List baseCommList;  //下拉框所有的值
	/**
	 * @return the baseCommList
	 */
	public List getBaseCommList() {
		return baseCommList;
	}
	/**
	 * @param baseCommList the baseCommList to set
	 */
	public void setBaseCommList(List baseCommList) {
		this.baseCommList = baseCommList;
	}
	public EquipParamBusiness getEquipParamBusiness() {
		return equipParamBusiness;
	}
	public void setEquipParamBusiness(EquipParamBusiness equipParamBusiness) {
		this.equipParamBusiness = equipParamBusiness;
	}
	public List getEquipParamList() {
		return equipParamList;
	}
	public void setEquipParamList(List equipParamList) {
		this.equipParamList = equipParamList;
	}
	public DbEquipList getDbEquipListDetail() {
		return dbEquipListDetail;
	}
	public void setDbEquipListDetail(DbEquipList dbEquipListDetail) {
		this.dbEquipListDetail = dbEquipListDetail;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public DbEquipParamBusiness getDbEquipParamBusiness() {
		return dbEquipParamBusiness;
	}
	public void setDbEquipParamBusiness(
			DbEquipParamBusiness dbEquipParamBusiness) {
		this.dbEquipParamBusiness = dbEquipParamBusiness;
	}

	/**
	 * 新增设备参数信息
	 * 
	 * @param dbEqParam
	 * @return
	 */
	public String addDbEqParam() {
		request = ServletActionContext.getRequest();

		DicEqParameter dicPar = new DicEqParameter();
		DbEquipList dbEquipList = new DbEquipList();
		String equipId = request.getParameter("equipId");
		if (StringUtils.isNotEmpty(equipId)) {
			dbEquipList.setEquipId(Integer.parseInt(equipId));
		}
		String vLength = request.getParameter("vLength");
		log.info("equipId="+equipId+";vLength="+vLength);
		int count = 0;
		if (StringUtils.isNotEmpty(vLength)) {
			count = Integer.parseInt(vLength);
		}
		log.info("equipId="+equipId+";vLength="+vLength+";count="+count);
		for (int i = 0; i < count; i++) {
			DbEqParam dbEqParam = new DbEqParam();
			dbEqParam.setDbEquipList(dbEquipList);
			String praId = request.getParameter("k" + i);
			String praVal = request.getParameter("v" + i);
			log.info("praId="+praId+";praVal="+praVal);
			if (StringUtils.isNotEmpty(praId)) {
				dicPar.setSeq(Integer.parseInt(praId));
			}else{
				log.info("praVal为空");
			}
			dbEqParam.setDicEqParameter(dicPar);
			dbEqParam.setParamValue(praVal);
			dbEquipParamBusiness.insertDbEquipParam(dbEqParam);
		}
		request.setAttribute("dbEquipListDetail",equipBusiness.findDbEquipListById(Integer.parseInt(equipId)));
		request.setAttribute("equipId", equipId);
		return "showDetails";
	}

	/**
	 * 操作了那张表，条件 ，页面如何操作的
	 * 更新设备参数
	 * 1.判断代码表里面是否有值，没有就是添加，有值就是修改
	 * 2.从页面获取typeId 和form表单的所有值
	 * 3.根据typeId获取下拉框的所有值
	 * 4.跳转页面
	 * @return
	 */
	public String updateDbEqParam() {
		log.info("updateDbEqParam....");
		request = ServletActionContext.getRequest();
		DicEqParameter dicPar = new DicEqParameter();
		DbEquipList dbEquipList = new DbEquipList();
		String dir=request.getParameter("doRedirect");
		String typeId = request.getParameter("typeId");
		String equipId = request.getParameter("equipId");
		String flag=request.getParameter("flag");  //标识是否为（doInsert增加）
		if (StringUtils.isNotEmpty(equipId)) {
			dbEquipList.setEquipId(Integer.parseInt(equipId));
		}else{
			log.info("equipId为空");
		}
		String vLength = request.getParameter("vLength");
		log.info("dir="+dir+";equipId="+equipId+";flag="+flag+";vLength="+vLength+";typeId="+typeId);
		int count = 0;
		if (StringUtils.isNotEmpty(vLength)) {
			count = Integer.parseInt(vLength);
		}else
		{
			log.info("vLength为空");
		}
		if(StringUtils.isNotEmpty(flag)){
			//增加
			for (int i = 0; i < count; i++) {
				DbEqParam dbEqParam = new DbEqParam();
				dbEqParam.setDbEquipList(dbEquipList);
				String praId = request.getParameter("k" + i);
				String praVal = request.getParameter("v" + i);
					if (StringUtils.isNotEmpty(praId)) {
						dicPar.setSeq(Integer.parseInt(praId));
					}else{
						log.info("praId为空");
					}
				log.info("praId="+praId+";praVal="+praVal);
					dbEqParam.setDicEqParameter(dicPar);
					if(("-1").equals(praVal)){
						praVal=null;
						dbEqParam.setParamValue(praVal);
					}else{
						dbEqParam.setParamValue(praVal);
					}
					dbEquipParamBusiness.insertDbEquipParam(dbEqParam);
				
			}
		}else{
			//修改
			for (int i = 0; i < count; i++) {
				DbEqParam dbEqParam = new DbEqParam();
				dbEqParam.setDbEquipList(dbEquipList);
				String praId = request.getParameter("k" + i);
				String praVal = request.getParameter("v" + i);
					if (StringUtils.isNotEmpty(praId)) {
						dicPar.setSeq(Integer.parseInt(praId));
					}else{
						log.info("praId为空");
					}
			    	log.info("praId="+praId+";praVal="+praVal);
					dbEqParam.setDicEqParameter(dicPar);
					dbEqParam.setSeq(Integer.parseInt(praId));
					
					if(("-1").equals(praVal)){
						praVal="";
						dbEqParam.setParamValue(praVal);
					}else{
						dbEqParam.setParamValue(praVal);
					}
					dbEquipParamBusiness.updateDbEquipParam(dbEqParam);
				
			}
		}
		equipParamList = new ArrayList();
		RetCode rt = new RetCode(1001, "查无数据！", "查无数据！");
		if (StringUtils.isNotEmpty(equipId)) {
			/**
			 * 判断参数表里面是否有值
			 */
			rt = equipParamBusiness.findDicParamByEquipId(equipId);
		}
		//当页面没有下拉框
		if(typeId==null||typeId==""){
			log.info("这里不需要下拉框...");
		}else{
			try {
				/**
				 * 根据类型获取comm表对应的类型(整个的下拉框里面的值)
				 */
				if (StringUtils.isNotEmpty(equipId)) {
						baseCommList=(List)(equipParamBusiness.findDicParamByTypeIdXLK(typeId)).getObj();
				}
				//判断下拉框是否有值
				if(baseCommList==null||baseCommList.size()==0){
					log.info("获取下拉框的值失败,请联系管理员updateDbEqParam");
				}else{
					request.setAttribute("typeId", typeId);
					request.setAttribute("baseCommList", baseCommList);
					}
			} catch (Exception e) {
				log.error("EqParamAction-->updateDbEqParam", e);
			}
		}
		if (rt.getObj() != null) {
			equipParamList = (List) rt.getObj();
			request.setAttribute("vLength", equipParamList.size());
		} else {
			log.info("获取下拉框的值失败,请联系管理员findEquipByEquipId");
		}
		request.setAttribute("dbEquipListDetail",equipBusiness.findDbEquipListById(Integer.parseInt(equipId)));
		request.setAttribute("equipId", equipId);
		//如果是修改页面,跳转到设备详情
		if(StringUtils.isNotEmpty(dir)){
			DbBuilding building = new DbBuilding();
			request.setAttribute("listBuilding",(List<DbBuilding>)buildBusiness.findDBBuilding(building).getObj());
			log.info("跳转equipmentDetail.jsp");
			return "showDetails";
		}else{
		   //更新
			log.info("跳转new_eqparam.jsp");
		   return "showEquipParams";
		}
	}
	public EquipListBusiness getEquipBusiness() {
		return equipBusiness;
	}
	public void setEquipBusiness(EquipListBusiness equipBusiness) {
		this.equipBusiness = equipBusiness;
	}
	/**
	 * @return the buildBusiness
	 */
	public BuildingBusiness getBuildBusiness() {
		return buildBusiness;
	}
	/**
	 * @param buildBusiness the buildBusiness to set
	 */
	public void setBuildBusiness(BuildingBusiness buildBusiness) {
		this.buildBusiness = buildBusiness;
	}
}
