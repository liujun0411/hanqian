package com.hanqian.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.EnergyBillBusiness;
import com.hanqian.business.UnitPriceBusiness;
import com.hanqian.pojo.DbEnergyBill;
import com.hanqian.pojo.DbUnitPrice;
import com.hanqian.util.DateUtil;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 描 述: 能源账单
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author lg
 * @version 1.4 2013-8-26
 * @see
 */
public class EnergyBillAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(EnergyBillAction.class);
	private EnergyBillBusiness energyBillMgr;
	private UnitPriceBusiness unitPriceMgr;
	private Page page;
	private DbEnergyBill energyBill;
	private DbEnergyBill insertEnergyBill;
	private HttpServletRequest request;
	private Integer currentPage;
	private String messeage = "";
	private String correctMsg = "";
	private final String ltStr = "lt";
	private final String gtStr = "gt";
	private static final Logger log = Logger.getLogger(EnergyBillAction.class);
	private String tableId;
	/**
	 * @return the tableId
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 * 查询能源账单
	 * @param 2013-8-28  
	 * @param @return       
	 * @return String
	 */
	public String findEnergyBill(){
		try {
			request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
		int pageSize = 10;
		if(currentPage == null){
			currentPage = 1 ;
		}
		
		String billMonth = request.getParameter("billMonth");
		String billMonth2 = request.getParameter("bil");
		String billMonth3 = request.getParameter("bill");
		//System.out.println("----------billMonth-"+billMonth+"------billMonth2-"+billMonth2+"-------billMonth3--"+billMonth3);
		//调用方法getAjaxEnergy（）；
		String typesss= request.getParameter("energyBill.type");
		ActionContext.getContext().getSession().put("typesss", typesss);
		
		if(billMonth !=null){
			ActionContext.getContext().getSession().put("billMonth", billMonth);
			if (energyBill!=null) {
				energyBill.setBillMonth(billMonth);
			}else{
				energyBill = new DbEnergyBill();
				energyBill.setBillMonth(billMonth);
			}
		}else{
			if (energyBill!=null) {
				energyBill.setBillMonth(billMonth);
			}else{
				energyBill = new DbEnergyBill();
				energyBill.setBillMonth(billMonth);
			}
		}
		if(billMonth2 !=null){
			ActionContext.getContext().getSession().put("billMonth", billMonth2);
			if (energyBill!=null) {
				energyBill.setBillMonth(billMonth2);
			}else{
				energyBill = new DbEnergyBill();
				energyBill.setBillMonth(billMonth2);
			}
		}
		if(billMonth3 !=null){
			ActionContext.getContext().getSession().put("billMonth", billMonth3);
			if (energyBill!=null) {
				energyBill.setBillMonth(billMonth3);
			}else{
				energyBill = new DbEnergyBill();
				energyBill.setBillMonth(billMonth3);
			}
		}
		RetCode rt =  energyBillMgr.findEnergyBill(energyBill, currentPage, pageSize);
		if(rt!=null&&rt.getObj()!=null){
			
			if(StringUtil.isNotEmpty(request.getParameter("correctMsg"))){
				messeage = "";
			}else{
				messeage = correctMsg;
			}
			request.setAttribute("energyBillList",rt.getObj());
			page=rt.getPage();
		}else{
			messeage = "暂无数据";
		}
		RetCode rc = energyBillMgr.findEnergyType();
		request.setAttribute("energyTypeList",rc.getObj());
		} catch (Exception e) {
			logg.error("EnergyBillAction-->findEnergyBill", e);
			log.error(e.getMessage());
		}
		return "energyBill";
	}
	
	/**
	 * 新加入账单之前的动作
	 * @return
	 */
	public String insertEnergyBillBefore(){
		try {
			request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			RetCode rc = new RetCode();
			request = ServletActionContext.getRequest();
			JSONArray jsonArr = new JSONArray();
		    HttpServletResponse response = ServletActionContext.getResponse();
			rc = energyBillMgr.findEnergyType();
			JSONArray array = new JSONArray();
			if(rc.getObj()!=null){
				List list = (List)rc.getObj();
				if(list.size()>0){
					array=JSONArray.fromObject(list);
				}
				
				log.info("添加或修改的集合"+array);
			}
			request.setAttribute("energyTypeList2",rc.getObj());
			String billMonth = request.getParameter("bil");
			
			if(billMonth !=null){
				ActionContext.getContext().getSession().put("billMonth", billMonth);
			}
			String type = request.getParameter("type");
			String eneryBillId = request.getParameter("seq");
			if (StringUtil.isNotEmpty(type)&&"2".equals(type)) {//修改
				if (StringUtil.isNotEmpty(eneryBillId)) {
			    	RetCode rct = energyBillMgr.findEnergyBillById(eneryBillId);
			    	request.setAttribute("energyBillList",rct.getObj());
			    	RetCode monthRc = energyBillMgr.findUpdateMonth(eneryBillId);
			    	request.setAttribute("monthRc",monthRc.getObj());
				}
			}
			request.setAttribute("update",type);
		} catch (Exception e) {
			logg.error("EnergyBillAction-->insertEnergyBillBefore", e);
			log.error(e.getMessage());
		}
		return "addOrUpdateEnergyBill";
	}
	
	
	
	/**
	 * Ajax 获取用户已经添加过的账单的月份
	 * @param 2013-9-5  
	 * @param        
	 * @return void
	 */
	public void getUsedBillMonth(){
		JSONArray array = new JSONArray();
		try{
			request = ServletActionContext.getRequest();
			JSONArray jsonArr = new JSONArray();
		    HttpServletResponse response = ServletActionContext.getResponse();
		    String cuurentHospCode = (String) request.getSession().getAttribute("currentHospCode");
			String typeId = request.getParameter("typeId");
			RetCode rct = energyBillMgr.findUpDatePrice(typeId);
			RetCode rt = new RetCode();
			rt = energyBillMgr.findUsedBillMonth(typeId, cuurentHospCode);
			if(rt.getObj()!=null){
				List temp = (List)rt.getObj();
				array = JSONArray.fromObject(temp);
				jsonArr.add(array);
			}
			if (rct!=null&&rct.getObj()!=null) {
				jsonArr.add( JSONArray.fromObject(rct.getObj()));
			}
			response.getWriter().write(jsonArr.toString());
		}catch (Exception e) {
			logg.error("EnergyBillAction-->getUsedBillMonth", e);
			e.printStackTrace();
		}
	}
	/**
	 * json得到所有的单位
	 * @return ajaxTypeSuccess
	 * @throws Exception
	 */
	public void getAjaxType(){
		try{
			request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			String tableId = request.getParameter("tableId");
			JSONArray array = new JSONArray();
			if(StringUtils.isNotEmpty(tableId)){
				RetCode monthRc = new RetCode();
				monthRc = energyBillMgr.findUnitBySeq(tableId);
				if(monthRc.getObj()!=null){
					List list = (List)monthRc.getObj();
					if(list.size()>0){
						array=JSONArray.fromObject(list);
					}
				}
			}
			log.info("单位："+array);
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().write(array.toString());
		}catch (Exception e) {
			logg.error("EnergyBillAction-->getAjaxType", e);
			e.printStackTrace();
		}
	}
	/**
	 * json得到所有的单位
	 * @return ajaxTypeSuccess
	 * @throws Exception
	 */
	public void getAjaxEnergy(){
		try{
			request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			String energyType = request.getParameter("energyType");
			JSONArray array = new JSONArray();
			if(StringUtils.isNotEmpty(energyType)){
				RetCode monthRc = new RetCode();
				monthRc = energyBillMgr.findEnergyBySeq(energyType);
				if(monthRc.getObj()!=null){
					List list = (List)monthRc.getObj();
					if(list.size()>0){
						array=JSONArray.fromObject(list);
					}
				}
			}
			log.info("类型："+array);
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().write(array.toString());
		}catch (Exception e) {
			logg.error("EnergyBillAction-->getAjaxEnergy", e);
			e.printStackTrace();
		}
	}
	/**
	 * 根据能源类型改变可选月份
	 * @param 2013-9-12  
	 * @param        
	 * @return void
	 */
	public void changeMonthByTypeId(){
		try{
			request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String typeId = request.getParameter("typeId");
			JSONArray array = new JSONArray();
			if(StringUtils.isNotEmpty(typeId)){
				RetCode monthRc = new RetCode();
				monthRc = energyBillMgr.findUpdateByTypeId(typeId);
				if(monthRc.getObj()!=null){
					List list = (List)monthRc.getObj();
					if(list.size()>0){
						array=JSONArray.fromObject(list);
					}
				}
			}
			response.getWriter().write(array.toString());
		}catch (Exception e) {
			logg.error("EnergyBillAction-->changeMonthByTypeId", e);
			e.printStackTrace();
		}
	}
	/**
	 * 新增账单
	 * @param 2013-9-12  
	 * @param @return       
	 * @return String
	 */
	public String insertEnergyBill(){
		try{
			
			request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			if(insertEnergyBill!=null){
				 DbUnitPrice dbunitPrice = null;
				String cuurentHospCode = (String) request.getSession().getAttribute("currentHospCode");
				//描述
				//String des =(String) request.getSession().getAttribute("des");
				//使用量
				//String consumption =(String) request.getSession().getAttribute("consumption");
//				String used =(String) request.getSession().getAttribute("used");
				String billMonth = insertEnergyBill.getBillMonth();//现有月份
				Date nowDate = new Date();
				//查找上一个月记录
				RetCode rc = unitPriceMgr.findHasRecord(billMonth, insertEnergyBill.getId(),insertEnergyBill.getDbUnitPrice().getUnitPrice(), ltStr);
				if (rc!=null&&rc.getObj()!=null) {
					List list = (List) rc.getObj();
					 Map map = (Map) list.get(0);
					 dbunitPrice = getDbUnitPrice(map);
					 //查找下一个月的记录
					 RetCode nextrc = unitPriceMgr.findHasRecord(billMonth, insertEnergyBill.getId(),insertEnergyBill.getDbUnitPrice().getUnitPrice(), gtStr);
					 if(nextrc!=null&& nextrc.getObj()!=null){//上一月 下一月 都有
						List nextList = (List<?>) nextrc.getObj();
						 Map nextMap = (Map) nextList.get(0);
						 DbUnitPrice nextUnitPrice = getDbUnitPrice(nextMap);
						//更新账单
						boolean updateFlag = energyBillMgr.updateEnergyBillUnitPriceId(nextUnitPrice.getId(),dbunitPrice.getId());
						if (updateFlag) {
							 dbunitPrice.setPriiceMonthRangEnd(nextUnitPrice.getPriiceMonthRangEnd());
							 unitPriceMgr.updateUnitPrice(dbunitPrice);
							 unitPriceMgr.deleteUnitPriceBySql(nextUnitPrice);
						}
						
					}else{//米有下一月
						dbunitPrice.setPriiceMonthRangEnd(billMonth);
						unitPriceMgr.updateUnitPrice(dbunitPrice);
					}
				}else{//没有上一月记录
					 RetCode nextrc = unitPriceMgr.findHasRecord(billMonth, insertEnergyBill.getId(),insertEnergyBill.getDbUnitPrice().getUnitPrice(), gtStr);
					 if(nextrc!=null&& nextrc.getObj()!=null){
							 List list = (List) nextrc.getObj();
							 Map map = (Map) list.get(0);
							 dbunitPrice = getDbUnitPrice(map);
						    dbunitPrice.setPriiceMonthRangStart(billMonth);
						    unitPriceMgr.updateUnitPrice(dbunitPrice);
						}else{//米有下一月
							
							dbunitPrice = new DbUnitPrice();
							dbunitPrice.setCustomerCode(cuurentHospCode);
							dbunitPrice.setOpTime(nowDate);
							dbunitPrice.setPriiceMonthRangStart(billMonth);
							dbunitPrice.setPriiceMonthRangEnd(billMonth);
							dbunitPrice.setType(insertEnergyBill.getType());
							dbunitPrice.setUnitPrice(insertEnergyBill.getDbUnitPrice().getUnitPrice());
							Set<DbEnergyBill> dbEnergyBills = new HashSet<DbEnergyBill>();
							dbEnergyBills.add(insertEnergyBill);
							dbunitPrice.setDbEnergyBills(dbEnergyBills);
							unitPriceMgr.insertUnitPrice(dbunitPrice);
						}
				}
				insertEnergyBill.setDbUnitPrice(dbunitPrice);
				insertEnergyBill.setCustomerCode(cuurentHospCode);
				insertEnergyBill.setOpTime(new Date());
				insertEnergyBill.setDes(insertEnergyBill.getDes());
				insertEnergyBill.setConsumption(insertEnergyBill.getConsumption());
//				insertEnergyBill.setUsed(used);
				log.info("DB_ENERGY_BILL数据UNIT_PRICE_ID="+insertEnergyBill.getDbUnitPrice().getId());
				log.info("新增账单Type="+insertEnergyBill.getType()+";PriiceMonthRangStart="+insertEnergyBill.getDbUnitPrice().getPriiceMonthRangStart()+";PriiceMonthRangEnd="+insertEnergyBill.getDbUnitPrice().getPriiceMonthRangEnd()+";UnitPrice="+insertEnergyBill.getDbUnitPrice().getUnitPrice());
				boolean flag = energyBillMgr.insertEnergyBill(insertEnergyBill);
				if (flag) {
					correctMsg = "添加成功";
				}else{
					correctMsg = "添加失败";
				}
			}
		}catch(Exception e){
			logg.error("EnergyBillAction-->insertEnergyBill", e);
			e.printStackTrace();
			correctMsg = "添加失败";
		}
		
		return findEnergyBill();
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	public DbUnitPrice getDbUnitPrice(Map map){
		 DbUnitPrice dbunitPrice = new DbUnitPrice();
		 dbunitPrice.setCustomerCode(map.get("customer_code").toString());
		 dbunitPrice.setId(Integer.valueOf(map.get("id").toString()));
		 dbunitPrice.setOpTime(DateUtil.parseDate(map.get("optime").toString(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		 dbunitPrice.setPriiceMonthRangStart(map.get("priice_month_rang_start").toString());
		 dbunitPrice.setPriiceMonthRangEnd(map.get("priice_month_rang_end").toString());
		 dbunitPrice.setType(Short.parseShort(map.get("type").toString()));
		 dbunitPrice.setUnitPrice(Double.parseDouble(map.get("unit_price").toString()));
		 return dbunitPrice;
	}

	
	/**
	 * 
	 * @return
	 */
	public String updateEnergyBill(){
		try{
			String startStr = "start";
			String endStr = "end";
			request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String beforeUnitPrice = request.getParameter("beforeUnitPrice");
			String beforeEnergyType = request.getParameter("befor" +
					"eEnergyType");
			Integer unitPriceId = insertEnergyBill.getDbUnitPrice().getId();
			String billMonth = request.getParameter("beforeBillMonth");
			//没点击查询的时候月份
			String billMonth2 = request.getParameter("beforeBillMonth2");
			
			String cuurentHospCode = (String) request.getSession().getAttribute("currentHospCode");
			String id = request.getParameter("energyId");
			 insertEnergyBill.setId(Integer.parseInt(id));
//				insertEnergyBill = insertOrUpdateEnergyBill();
			 if(billMonth == ""){
				 insertEnergyBill.setBillMonth(billMonth2);
			 }else{
				 insertEnergyBill.setBillMonth(billMonth);
			 }
			 insertEnergyBill.setType(Short.valueOf(beforeEnergyType));
			 insertEnergyBill.setConsumption(insertEnergyBill.getConsumption());
			 insertEnergyBill.setDes(insertEnergyBill.getDes());
			 Date nowDate = new Date();
			if (unitPriceId!=null) {
				DbUnitPrice dbunitPrice = null;
				DbUnitPrice tempDbUnitPrice = new DbUnitPrice();
				tempDbUnitPrice.setId(unitPriceId);
				RetCode rc = unitPriceMgr.findUnitPrice(tempDbUnitPrice);
				if (rc!=null&&rc.getObj()!=null) {
					List list = (List) rc.getObj();
					Map map = (Map) list.get(0);
					dbunitPrice = getDbUnitPrice(map);
					List joinIds = new ArrayList();
						dbunitPrice.setOpTime(nowDate);
						insertEnergyBill.setOpTime(nowDate);
						 boolean flag =  energyBillMgr.updateEnergyBillBySql(insertEnergyBill);
						if (flag) {
							boolean flaga =unitPriceMgr.updateEnergyBillBypriceSql(insertEnergyBill);
							if(flaga){
								correctMsg = "修改成功";
							}else{
								correctMsg ="修改失败";
							}
						}else{
							correctMsg ="修改失败";
						}
			}
		}
			return findEnergyBill();
			
		}catch(Exception e){
			logg.error("EnergyBillAction-->updateEnergyBill", e);
			e.printStackTrace();
			correctMsg =  "修改失败";
		}
		return findEnergyBill();
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the energyBillMgr
	 */
	public void setEnergyBillMgr(EnergyBillBusiness energyBillMgr) {
		this.energyBillMgr = energyBillMgr;
	}

	/**
	 * @param energyBillMgr the energyBillMgr to set
	 */


	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @return the energyBill
	 */
	public DbEnergyBill getEnergyBill() {
		return energyBill;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * @param energyBill the energyBill to set
	 */
	public void setEnergyBill(DbEnergyBill energyBill) {
		this.energyBill = energyBill;
	}



	public UnitPriceBusiness getUnitPriceMgr() {
		return unitPriceMgr;
	}

	public void setUnitPriceMgr(UnitPriceBusiness unitPriceMgr) {
		this.unitPriceMgr = unitPriceMgr;
	}

	public EnergyBillBusiness getEnergyBillMgr() {
		return energyBillMgr;
	}

	/**
	 * @return the insertEnergyBill
	 */
	public DbEnergyBill getInsertEnergyBill() {
		return insertEnergyBill;
	}

	/**
	 * @param insertEnergyBill the insertEnergyBill to set
	 */
	public void setInsertEnergyBill(DbEnergyBill insertEnergyBill) {
		this.insertEnergyBill = insertEnergyBill;
	}

	/**
	 * @return the messeage
	 */
	public String getMesseage() {
		return messeage;
	}

	/**
	 * @param messeage the messeage to set
	 */
	public void setMesseage(String messeage) {
		this.messeage = messeage;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the correctMsg
	 */
	public String getCorrectMsg() {
		return correctMsg;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @param correctMsg the correctMsg to set
	 */
	public void setCorrectMsg(String correctMsg) {
		this.correctMsg = correctMsg;
	}
	
}
