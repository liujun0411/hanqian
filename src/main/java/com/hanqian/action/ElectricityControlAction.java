package com.hanqian.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.hanqian.business.ElectricityControlBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.form.VOElectCondition;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.ETimeStep;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 描 述: 实时监控(电流趋势图)
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-12-10
 * @see
 */
public class ElectricityControlAction extends ActionSupport {
	public HospInfoBusiness getHospInfoMgr() {
		return hospInfoMgr;
	}

	public void setHospInfoMgr(HospInfoBusiness hospInfoMgr) {
		this.hospInfoMgr = hospInfoMgr;
	}

	private static final Log log = LogFactory.getLog(ElectricityControlAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private HttpServletRequest request;
	private ElectricityControlBusiness electricityControlBusiness;
	private List resultList;
	private VOElectCondition resultVoElect;
	private  String basePath = "\\manager\\monitoring\\electticityPic\\";
	private HospInfoBusiness hospInfoMgr;
	
/**
	public String createAndShowElect(){
		request = ServletActionContext.getRequest();
		basePath = StringUtil.replacePath(basePath);
		File file = new File(getConfigFile().getPath()+File.separator+ basePath);
		log.debug("       ----getConfigFile().getPath()+File.separator---：         "+getConfigFile().getPath()+File.separator);
		File config=new File(getConfigFile().getPath()+ StringUtil.replacePath("/src/main/webapp/manager/monitoring/control/electricity/config/targetConfig.xml"));
		VOElectCondition voElectCondition=new VOElectCondition();
		try{
			if(config.exists()){
				   XMLWriter writer = null;// 声明写XML的对象
				   SAXReader reader = new SAXReader();
				   OutputFormat format = OutputFormat.createPrettyPrint();
				   format.setEncoding("GBK");
				   Document document = reader.read(config);// 读取XML文件
				   Element root = document.getRootElement();// 得到根节点
				   for (Iterator i = root.elementIterator("transformer"); i.hasNext();) {
						Element node = (Element) i.next();
						String quantum =node.selectSingleNode("quantum").getText();
						String [] items=quantum.split(",");
						if(items.length>0){
							voElectCondition.setConfigValue(items[0]);
							if(items[1].equals("hour")){
								voElectCondition.seteTimeStep(ETimeStep.hour);
							}else if(items[1].equals("day")){
								voElectCondition.seteTimeStep(ETimeStep.day);
							}
						}
						break;
				   }
			}
			if (!file.exists()) {
				file.mkdirs(); // 创建文件夹目录
			}
			String eqTypeId=request.getParameter("eqTypeId");
			String flag=request.getParameter("flag");
			
			voElectCondition.setEqTypeId(eqTypeId);
			//voElectCondition.seteTimeStep(ETimeStep.hour);
			//voElectCondition.setStartTime("201211190000");
			//voElectCondition.setEndTime("201211190100");
			resultList=new ArrayList();
			if(StringUtils.isNotEmpty(flag)){
				if("total".equals(flag)){
					resultList=electricityControlBusiness.createTotalPic(voElectCondition, file.getPath(), basePath);
				}else{
					resultList=electricityControlBusiness.createAndResultPic(voElectCondition, file.getPath(), basePath);
				}
			}
		}catch (Exception e) {
			log.error("ElectricityControlAction-->createAndShowElect", e);
			e.printStackTrace();
		}
		return "threeElect";
	}
	
	*/
	/**
	 * 综合电流
	 * @return
	 */
	public String createAndShowElectZHdianliu(){
		request = ServletActionContext.getRequest();
		basePath = StringUtil.replacePath(basePath);
		File file = new File(getConfigFile().getPath()+File.separator+ basePath);
		log.debug("       ----getConfigFile().getPath()+File.separator---：         "+getConfigFile().getPath()+File.separator);
		log.debug("       ----config path---：         "+getConfigFile().getPath()+ StringUtil.replacePath("/manager/monitoring/control/electricity/config/targetConfig.xml"));
		File config=new File(getConfigFile().getPath()+ StringUtil.replacePath("/manager/monitoring/control/electricity/config/targetConfig.xml"));
//		File config=new File(getConfigFile().getPath()+ StringUtil.replacePath("/src/main/webapp/manager/monitoring/control/electricity/config/targetConfig.xml")); //本地测试用
		VOElectCondition voElectCondition=new VOElectCondition();
		try{
			if(config.exists()){
				   XMLWriter writer = null;// 声明写XML的对象
				   SAXReader reader = new SAXReader();
				   OutputFormat format = OutputFormat.createPrettyPrint();
				   format.setEncoding("GBK");
				   Document document = reader.read(config);// 读取XML文件
				   Element root = document.getRootElement();// 得到根节点
				   for (Iterator i = root.elementIterator("transformer"); i.hasNext();) {
						Element node = (Element) i.next();
						String quantum =node.selectSingleNode("quantum").getText();
						String [] items=quantum.split(",");
						if(items.length>0){
							log.debug("items[0]:"+items[0]);
							log.debug("items[1]:"+items[1]);
							voElectCondition.setConfigValue(items[0]);
							if(items[1].equals("hour")){
								voElectCondition.seteTimeStep(ETimeStep.hour);
							}else if(items[1].equals("day")){
								voElectCondition.seteTimeStep(ETimeStep.day);
							}
							log.debug("voElectCondition.geteTimeStep():"+voElectCondition.geteTimeStep());
						}else{
							log.debug("items.length==0");
						}
						break;
				   }
			}else{
				log.debug("!config.exists()");
			}
			if (!file.exists()) {
				file.mkdirs(); // 创建文件夹目录
				log.debug("!file.exists()");
			}
		  //String eqTypeId=request.getParameter("eqTypeId");
		  //  String eqTypeId="9002";
			/**
			 * 2015-11-02 
			 * 不要变压器服务于电表
			 * 直接显示电表类型
			 */
			  String eqTypeId="10001";
			//String flag=request.getParameter("flag");
			
			voElectCondition.setEqTypeId(eqTypeId);
			//voElectCondition.seteTimeStep(ETimeStep.hour);
			//voElectCondition.setStartTime("201211190000");
			//voElectCondition.setEndTime("201211190100");
			resultList=new ArrayList();
			//if(StringUtils.isNotEmpty(flag)){
			//	if("total".equals(flag)){
					resultList=electricityControlBusiness.createTotalPic(voElectCondition, file.getPath(), basePath);
			//	}else{
			//		resultList=electricityControlBusiness.createAndResultPic(voElectCondition, file.getPath(), basePath);
			//	}
			//}
		}catch (Exception e) {
			log.error("ElectricityControlAction-->createAndShowElect", e);
			e.printStackTrace();
		}
		return "threeElect";
	}
	
	/**
	 * 三相电流
	 * @return
	 */
	public String createAndShowElect3Adianliu(){
		request = ServletActionContext.getRequest();
		basePath = StringUtil.replacePath(basePath);
		File file = new File(getConfigFile().getPath()+File.separator+ basePath);
		log.debug("       ----getConfigFile().getPath()+File.separator---：         "+getConfigFile().getPath()+File.separator);
		log.debug("       ----config path---：         "+getConfigFile().getPath()+ StringUtil.replacePath("/manager/monitoring/control/electricity/config/targetConfig.xml"));
		File config=new File(getConfigFile().getPath()+ StringUtil.replacePath("/manager/monitoring/control/electricity/config/targetConfig.xml"));
//		File config=new File(getConfigFile().getPath()+ StringUtil.replacePath("/src/main/webapp/manager/monitoring/control/electricity/config/targetConfig.xml")); //本地测试用
		VOElectCondition voElectCondition=new VOElectCondition();
		try{
			if(config.exists()){
				   XMLWriter writer = null;// 声明写XML的对象
				   SAXReader reader = new SAXReader();
				   OutputFormat format = OutputFormat.createPrettyPrint();
				   format.setEncoding("GBK");
				   Document document = reader.read(config);// 读取XML文件
				   Element root = document.getRootElement();// 得到根节点
				   for (Iterator i = root.elementIterator("transformer"); i.hasNext();) {
						Element node = (Element) i.next();
						String quantum =node.selectSingleNode("quantum").getText();
						String [] items=quantum.split(",");
						if(items.length>0){
							log.debug("items[0] (3):"+items[0]);
							log.debug("items[1] (3):"+items[1]);
							voElectCondition.setConfigValue(items[0]);
							if(items[1].equals("hour")){
								voElectCondition.seteTimeStep(ETimeStep.hour);
							}else if(items[1].equals("day")){
								voElectCondition.seteTimeStep(ETimeStep.day);
							}
							log.debug("voElectCondition.geteTimeStep()3:"+voElectCondition.geteTimeStep());
						}else{
							log.debug("items.length==0 (3)");
						}
						break;
				   }
			}else{
				log.debug("!config.exists() (3)");
			}
			if (!file.exists()) {
				file.mkdirs(); // 创建文件夹目录
				log.debug("!file.exists() (3)");
			}
			//String eqTypeId="9002";
			/**
			 * 2015-11-02 
			 * 不要变压器服务于电表
			 * 直接显示电表类型
			 */
			  String eqTypeId="10001";
			//String eqTypeId=request.getParameter("eqTypeId");
			//String flag=request.getParameter("flag");
			
			voElectCondition.setEqTypeId(eqTypeId);
			//voElectCondition.seteTimeStep(ETimeStep.hour);
			//voElectCondition.setStartTime("201211190000");
			//voElectCondition.setEndTime("201211190100");
			resultList=new ArrayList();
			//if(StringUtils.isNotEmpty(flag)){
			//	if("total".equals(flag)){
			//		resultList=electricityControlBusiness.createTotalPic(voElectCondition, file.getPath(), basePath);
			//	}else{
					resultList=electricityControlBusiness.createAndResultPic(voElectCondition, file.getPath(), basePath);
			//	}
			//}
		}catch (Exception e) {
			log.error("ElectricityControlAction-->createAndShowElect", e);
			e.printStackTrace();
		}
		return "threeElect";
	}
	/**
	 * 获取节能指数
	 * @param 2012-12-17  
	 * @param @return       
	 * @return String
	 */
	public String getEnergySaveing(){
		request = ServletActionContext.getRequest();
		File config=new File(getConfigFile().getPath()+ StringUtil.replacePath("/manager/monitoring/control/electricity/config/targetConfig.xml"));
		//获取登录用户
		DbUsers dbUsers=(DbUsers)request.getSession().getAttribute("users");
		try{
			if(config.exists()){
				   VOElectCondition voElectCondition=new VOElectCondition();
				   XMLWriter writer = null;// 声明写XML的对象
				   SAXReader reader = new SAXReader();
				   OutputFormat format = OutputFormat.createPrettyPrint();
				   format.setEncoding("GBK");
				   Document document = reader.read(config);// 读取XML文件
				   Element root = document.getRootElement();// 得到根节点
				   for (Iterator i = root.elementIterator("energysave"); i.hasNext();) {
						Element node = (Element) i.next();
						voElectCondition.setStartTime(node.selectSingleNode("statTime").getText());
						break;
				   }
				   if(voElectCondition.getStartTime()!=null){
					   if(dbUsers!=null){
						   if(dbUsers.getDbHospInfo()!=null){
							   if(dbUsers.getDbHospInfo().getSeqHosp()!=null){
								   DbHospInfo hosp = hospInfoMgr.findHospInfoForId(dbUsers.getDbHospInfo().getSeqHosp());
								   if(hosp.getDbBaseCommByKind().getSeq()!=null){
									   resultVoElect=electricityControlBusiness.getEnergySaveing(voElectCondition, dbUsers.getDbHospInfo().getSeqHosp().toString(),hosp.getDbBaseCommByKind().getSeq().toString());
								   }
								  log.info("获取节能指数--hosp.getDbBaseCommByKind()="+hosp.getDbBaseCommByKind()+";voElectCondition="+voElectCondition+"; dbUsers.getDbHospInfo().getSeqHosp().toString()="+ dbUsers.getDbHospInfo().getSeqHosp().toString()+";hosp.getDbBaseCommByKind().getSeq().toString()="+hosp.getDbBaseCommByKind().getSeq().toString());
							   }else{
								   log.info("ElectricityControlAction-->getEnergySaveing跳转至login.原因:dbUsers.getDbHospInfo().getSeqHosp()为空");
								   MenuInterceptor.toLoginjsp();
							   }
						   }else{
							   log.info("ElectricityControlAction-->getEnergySaveing跳转至login.原因:dbUsers.getDbHospInfo()为空");
							   MenuInterceptor.toLoginjsp();
						   }
					   }else{
						   log.info("ElectricityControlAction-->getEnergySaveing跳转至login.原因:dbUsers为空");
						   MenuInterceptor.toLoginjsp();
					   }
				   } else {
					   log.info("ElectricityControlAction-->getEnergySaveing跳转至login.原因:voElectCondition.getStartTime()为空");
				   }
			}
		}catch (Exception e) {
			log.error("ElectricityControlAction-->getEnergySaveing", e);
			e.printStackTrace();
		}
		return "energySaveing";
	}
	
	private File getConfigFile(){
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
		return temp;
	}
	
	public ElectricityControlBusiness getElectricityControlBusiness() {
		return electricityControlBusiness;
	}

	public void setElectricityControlBusiness(
			ElectricityControlBusiness electricityControlBusiness) {
		this.electricityControlBusiness = electricityControlBusiness;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public VOElectCondition getResultVoElect() {
		return resultVoElect;
	}

	public void setResultVoElect(VOElectCondition resultVoElect) {
		this.resultVoElect = resultVoElect;
	}

	/**
	 * @return the hospInfoMgr
	 */
//	public HospInfoMgr getHospInfoMgr() {
//		return hospInfoMgr;
//	}
//
//	/**
//	 * @param hospInfoMgr the hospInfoMgr to set
//	 */
//	public void setHospInfoMgr(HospInfoMgr hospInfoMgr) {
//		this.hospInfoMgr = hospInfoMgr;
//	}
//	
	
}
