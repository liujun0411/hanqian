package com.hanqian.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.DbEquipParamBusiness;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.EquipParamBusiness;
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
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-13
 * @see
 */
public class EquipParamAction extends ActionSupport {
	private static final Logger logg = Logger.getLogger(EquipParamAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private HttpServletRequest request;
	private EquipParamBusiness equipParamBusiness;
	private List equipParamList;  //设备参数列表
	private List baseCommList;  //下拉框所有的值
	private List equipEqParamList;  
	private DbEquipParamBusiness dbEquipParamBusiness;
	private List<DicEqParameter>  dicEqParmeterSeq;
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

	private EquipListBusiness equipListBusiness;  //设备信息
	
	public List getEquipParamList() {
		return equipParamList;
	}
	public void setEquipParamList(List equipParamList) {
		this.equipParamList = equipParamList;
	}
	public EquipListBusiness getEquipListBusiness() {
		return equipListBusiness;
	}
	public void setEquipListBusiness(EquipListBusiness equipListBusiness) {
		this.equipListBusiness = equipListBusiness;
	}
	public EquipParamBusiness getEquipParamBusiness() {
		return equipParamBusiness;
	}
	public void setEquipParamBusiness(EquipParamBusiness equipParamBusiness) {
		this.equipParamBusiness = equipParamBusiness;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * @return the dbEquipParamBusiness
	 */
	public DbEquipParamBusiness getDbEquipParamBusiness() {
		return dbEquipParamBusiness;
	}
	/**
	 * @param dbEquipParamBusiness the dbEquipParamBusiness to set
	 */
	public void setDbEquipParamBusiness(DbEquipParamBusiness dbEquipParamBusiness) {
		this.dbEquipParamBusiness = dbEquipParamBusiness;
	}
	
	/**
	 * @return the dicEqParmeterSeq
	 */
	public List<DicEqParameter> getDicEqParmeterSeq() {
		return dicEqParmeterSeq;
	}
	/**
	 * @param dicEqParmeterSeq the dicEqParmeterSeq to set
	 */
	public void setDicEqParmeterSeq(List<DicEqParameter> dicEqParmeterSeq) {
		this.dicEqParmeterSeq = dicEqParmeterSeq;
	}
	/**
	 * 显示设备参数(查询默认值)
	 * @return
	 */
	public String findEquipPicList(){
		logg.info("findEquipPicList....开始");
		request = ServletActionContext.getRequest();
		DbEquipList dbEquipList=new DbEquipList();
		String equipId=request.getParameter("equipId");
		logg.info("equipId...."+equipId);
        if(StringUtils.isNotEmpty(equipId)){
        	dbEquipList=equipListBusiness.findDbEquipListById(Integer.parseInt(equipId));
        }
		RetCode rt = new RetCode(1001, "查无数据！", "查无数据！");
		if(null!=dbEquipList){
			if(dbEquipList.getDbEquipType()!=null){
			  rt=equipParamBusiness.findDicParamByTypeId(dbEquipList.getDbEquipType().getEquipTypeId().toString());
			}
		}
		if(rt!=null){
			equipParamList=(List)rt.getObj();
		}else{
			request.setAttribute("msg", "<script>alert('该设备暂未提供参数模板');location.href='equipment_showDetail?equipId="+equipId+"';</script>");
		}
		logg.info("findEquipPicList....结束");
		return "showEquipParam";
	}
	
	/**
	 * 文本框和下拉框Action
	 * 
	 * 1.查询参数表是否有值(参数表为主表和次表代码表相连，条件是设备编号)
	 * 2.获取下拉框的所有值
	 *       2.1 有值，就传过去(在页面判断，==1就是文本框，==2就是下拉框，下拉框的话根据参数表里面的param_value这个字段循环
	 *        配对base_comm中id，如果配对上了，就让它selected='selected')；
	 *        
	 *       2.2  没有值，就执行默认值
	 *           已代码表为主表，
	 *                 如果是文本框就直接显示代码表中default_value
	 *                 如果是下拉框就显示base_type对应的base_comm中的默认值
	 * 3.点击保存
	 *     如果是第一次就让它直接insetinto 到参数表里里面  如果页面上是--请选择--这个值也保存在参数表里面，但是值为null。
	 *     如果是参数表里面有值，就update参数表里面的数据，如果页面下拉框已确定有值，客户还是选择--请选择--就在这个Id对应的数据
	 *          直接修改为null
	 * 
	 * 页面操作：
	 * 页面 [第一下拉框 ,第三下拉框]选择框是参数表里面没有值显示的（第一次初始化的时候）
	 * 页面 [第二下拉框 ,第四下拉框] 选择框是参数表里面有值显示的（有值,已经添加过了）
	 * 页面操作:先判断参数表里里面是否为空，
	 *     如果为空就是代码表里面的默认值
	 *     如果不为空就是代码表里面的param_value
	 * 页面去数据的是根据，Action里面传出的list集合的个数，取值就是在循环里面：集合名称+索引+数据库的字段的名称来确定值的
	 * 
	 * @author 
	 * @version 1.4 2014-10-31
	 * @see
	 * 
	 */
	public String findEquipByEquipId(){
		logg.info("findEquipByEquipId...");
		request = ServletActionContext.getRequest();
		equipParamList=new ArrayList();
		RetCode rt = new RetCode(1001, "查无数据！", "查无数据！");
		//文本框优先选择
		String equipId=request.getParameter("equipId");
		/**
		 *   判断参数表里面是否有值
		 */
		if(StringUtils.isNotEmpty(equipId)){
			logg.info("进去以后...."+equipId);
			   rt=equipParamBusiness.findDicParamByEquipId(equipId);
		    logg.info("进去以后1111....");
			}
		//文本框里面的如果不存在,提供默认的参数
		DbEquipList dbEquipList=new DbEquipList();
		dbEquipList=equipListBusiness.findDbEquipListById(Integer.parseInt(equipId));
		/**
		 * 获取下拉框的所有值
		 */
		try {
			//根据类型获取comm表对应的类型(整个的下拉框里面的值)
			if(null!=dbEquipList){
				if(dbEquipList.getDbEquipType()!=null){
					baseCommList=(List)(equipParamBusiness.findDicParamByTypeIdXLK(dbEquipList.getDbEquipType().getEquipTypeId().toString())).getObj();
				}
			}
			//判断下拉框是否有值
			if(baseCommList==null||baseCommList.size()==0){
				logg.info("获取下拉框的值失败,请联系管理员findEquipByEquipId");
			}else{
				logg.info("changdu00baseCommList="+baseCommList.size());
				request.setAttribute("typeId", dbEquipList.getDbEquipType().getEquipTypeId().toString());
				request.setAttribute("baseCommList", baseCommList);
				
			}
		} catch (Exception e) {
			logg.error("EquipParamAction-->findEquipByEquipId", e);
		}
		if(rt.getObj()!=null){
			logg.info("说明参数表里面有值...."+equipParamList.size());
			/**
			 * 查询代码表中的数据时候和参数表里面的数据时候一致，如果不一致，就把那多出来的数据添加到参数表里面去
			 */
			DicEqParameter dicPar = new DicEqParameter();
			RetCode ws =equipParamBusiness.findDicParamByTypeIdNewNum(equipId,dbEquipList.getDbEquipType().getEquipTypeId().toString());
		if(ws.getObj()!=null){
			dicEqParmeterSeq = (List<DicEqParameter>) ws.getObj();
				dbEquipList.setEquipId(Integer.parseInt(equipId));
				for (int i = 0; i < dicEqParmeterSeq.size(); i++) {
					DbEqParam dbEqParam = new DbEqParam();
					Map haspMap = new HashMap();
					haspMap = (Map) dicEqParmeterSeq.get(i);
					    dicPar.setSeq(Integer.parseInt(haspMap.get("seq").toString()));
						dbEqParam.setDbEquipList(dbEquipList);
						dbEqParam.setDicEqParameter(dicPar);
						dbEqParam.setParamValue(null);
					dbEquipParamBusiness.insertDbEquipParam(dbEqParam);
				}
		}
			rt=equipParamBusiness.findDicParamByEquipId(equipId);
			equipParamList=(List)rt.getObj();
			request.setAttribute("vLength", equipParamList.size());
		}else{
				if(null!=dbEquipList){
					if(dbEquipList.getDbEquipType()!=null){
					  rt=equipParamBusiness.findDicParamByTypeId(dbEquipList.getDbEquipType().getEquipTypeId().toString());
					}
				}
				if(rt.getObj()!=null){
					/**
					 *equipParamList来确定页面显示的参数的个数
					 */
					equipParamList=(List)rt.getObj();
					request.setAttribute("flag", "doInsert");
					request.setAttribute("vLength", equipParamList.size());
				}else{
					request.setAttribute("msg", "<script>alert('该设备暂未提供设备参数模板');</script>");
				}
		}
		request.setAttribute("equipId", equipId);
		return "showEquipParam";
	}
}
