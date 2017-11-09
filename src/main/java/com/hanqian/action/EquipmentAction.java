package com.hanqian.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BaseCommBusiness;
import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.EnergyTypeBusiness;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.EquipParamBusiness;
import com.hanqian.business.EquipSerEquipBusiness;
import com.hanqian.business.EquipServiceBusiness;
import com.hanqian.business.EquipTypeBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbEnergyType;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.DbEquipType;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.pojo.DbUsers;
import com.hanqian.pojo.EquipService;
import com.hanqian.pojo.EquipServiceEquip;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 描 述: 设备信息列表Action类
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
public class EquipmentAction extends ActionSupport {
	 private static final Log log = LogFactory.getLog(EquipmentAction.class);
	private static final String equipList="equipList";  //设备列表
	private static final String equipAddPage="addEquipPage"; //设备添加页面
	private static final String equipEditPage="editEquipPage"; //设备编辑界面
	private static final String equipDetailPage="equipDetailPage"; //设备详细信息
	
	private HttpServletRequest request;
	private List<DbBuilding> listBuilding; // 楼宇信息列表
	private List<DbEnergyType> listEnergy;  //能源类型
	private List listArea;//区域名称
	private EquipListBusiness equipBusiness;
	private List<DbEquipList> listEquipList;
	private BuildingBusiness buildBusiness;     //楼宇类型
	private HospInfoBusiness hospitalBusiness;   //医院类型
	private EnergyTypeBusiness energyTypeBusiness;  //能源类型
	private BaseCommBusiness baseCommBusiness;
	private DbEquipList dbEquipListDetail;
	private List<Integer> storeyList;  //楼层信息
	private List<Integer> serviceStoreyList;
	private EquipService equipService;
	private EquipServiceBusiness equipServiceBusiness;  //设备服务区域
	private String resultJSON;   //返回Ajax处理反馈结果
	private EquipParamBusiness equipParamBusiness; //设备参数
	private List equipParamList;  //设备参数集合
	private String equipId;
	private EquipTypeBusiness equipTypeBusiness;  //设备类型
	private DbEquipType dbEquipType;  //设备类型
	private DbEquipList dbEquipList;
	private Page page;
	private Integer pageSize=10;
	private Integer currentPage;
	private MenuInterceptor menuInterceptor;  //权限过滤器
	public Map menuIdMap; //权限MAP集合
	private EquipSerEquipBusiness eqSerEquipBusiness;
	private String index="";
	
	private List<EquipService> equipServiceList;//设备服务设备
	private List<EquipServiceEquip> eqSerEquipList;//设备服务设备集合
	
	private Map sqlmap = new HashMap();
    
    private Map seqMap = new HashMap();
	
    private Map equipCodeMap = new HashMap();
	
	private Map equipNameMap = new HashMap();
	
	private Map typeNameMap = new HashMap();
	
	private Map unitTypeMap = new HashMap();
	
	private Map buildingNameMap = new HashMap();
	
	private Map storeyMap = new HashMap();
	
	private Map placeMap = new HashMap();

	private Map controlFlagMap = new HashMap();

	private Map equipServiceEquipMap = new HashMap();

	private Map equipServicesMap = new HashMap();

	private Map assetscodeMap = new HashMap();

	private Map productionMap = new HashMap();

	private Map fieldMap = new HashMap();

	private Map productionDateMap = new HashMap();
	
	private Map installDateMap = new HashMap();
	
	private Map useDateMap = new HashMap();
	
	private Map limitedMap = new HashMap();
	
	private Map serviceLifeMap = new HashMap();
	
	private Map serviceCycleMap = new HashMap();
	
	private Map purchaseMap = new HashMap();
	
	private Map brandMap = new HashMap();

	private Map accessoryMap = new HashMap();

	private Map remindmapAll = new HashMap();
	
	private List baseCommList;  //下拉框所有的值
	/**
	 * 根据条件查询设备信息，以分页展示
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String findEquipmentByPage(){
		request = ServletActionContext.getRequest();
		equipTypeBusiness.createJSON();
		//权限配置
		menuIdMap=menuInterceptor.menuIntercept("2003001");
		//若为空 ，进入登录界面
		if(menuIdMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}
	    if(currentPage==null){
	      currentPage=1;
	    }
	    String storey = request.getParameter("astorey");
	    log.info("storey=="+storey);
	    if (StringUtil.isNotEmpty(storey)&&!"0".equals(storey)) {
	    	if (dbEquipList==null) {
	    		dbEquipList = new DbEquipList();
			}
				dbEquipList.setStorey(Double.parseDouble(storey));
		}
	    DbBuilding build=new DbBuilding();
	    if(dbEquipList!=null){
		    if(dbEquipList.getDbEquipType()!=null){
		    	if(dbEquipList.getDbEquipType().getEquipTypeId()!=null){
		    		dbEquipType=equipTypeBusiness.findById(dbEquipList.getDbEquipType().getEquipTypeId());
		    		dbEquipList.setDbEquipType(dbEquipType);
		    	}
		    }
		    if(dbEquipList.getDbBuilding()!=null){
		    	if(dbEquipList.getDbBuilding().getBuildingId()!=null&&dbEquipList.getDbBuilding().getBuildingId() != 0){
		    		build=buildBusiness.findBuildingById(dbEquipList.getDbBuilding());
		    	}
		    }
	    }
	    if (build!=null) {
	    	if(build.getBuildingId()!=null&& build.getBuildingId() != 0){
	    		int storyUp=build.getStoreyNumUp();
	    		int storyDown=build.getStoreyNumDown();
	    		storeyList=new ArrayList<Integer>();
	    		for(int i=storyUp;i>0;i--){
	    			storeyList.add(i);
	    		}
	    		for(int i=1;i<=storyDown;i++){
	    			storeyList.add(0-i);
	    		}
	    	}
		}
		DbBuilding building=new DbBuilding();
		listBuilding=(List<DbBuilding>)buildBusiness.findDBBuilding(building).getObj();
		// 根据条件获取设备列表信息
		RetCode rc = equipBusiness.findALlEquipListInfo(dbEquipList, currentPage, pageSize);
		if (rc.getObj() != null) {
			listEquipList=new ArrayList();
			listEquipList = (List<DbEquipList>) rc.getObj();
			page=rc.getPage();
		}else{
			page=new Page(1, 0, 10);
		}
		if(rc==null||rc.getObj()==null){
			request.setAttribute("msg","暂无数据！");
		}
	
			return equipList;
		
	}
	
	/**
	 * 跳转到添加页面
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String toAddPage(){
		DbBuilding building=new DbBuilding();
		listBuilding=(List<DbBuilding>)buildBusiness.findDBBuilding(building).getObj();
		listEnergy=(List<DbEnergyType>)energyTypeBusiness.findAllEnergyType().getObj();
		listArea=(List)baseCommBusiness.findAreaName().getObj();
		List<Map<String,Object>> allElec = equipBusiness.getAllElec(null);
		request = ServletActionContext.getRequest();
	    request.setAttribute("allElec",allElec);
		return equipAddPage;
 	}

	
	/**
	 * 添加设备信息
	 * @param statement  
	 * @param        
	 * @return 
	 */
	public String addEquipment(){
		
	
		request = ServletActionContext.getRequest();
		String controlFlag=request.getParameter("controlFlag");
//		String enercyType=request.getParameter("enercyType");
		String elecLevel=request.getParameter("elecLevel");
		String parentElec=request.getParameter("parentElec");
		 log.info("controlFlag="+controlFlag);
		if(StringUtils.isNotEmpty(controlFlag)){
			dbEquipList.setControlFlag(Short.parseShort(controlFlag));
		}else{
			log.info("controlFlag为空");
		}
		if(StringUtils.isNotEmpty(elecLevel)){
			if(dbEquipList.getDbEquipType()!=null){
				if(dbEquipList.getDbEquipType().getEquipTypeId()==10001){
					dbEquipList.setNode_level(Integer.parseInt(elecLevel));
				}
			}
		}
		if(StringUtils.isNotEmpty(parentElec)){
			if(dbEquipList.getDbEquipType()!=null){
				if(dbEquipList.getDbEquipType().getEquipTypeId()==10001){
					dbEquipList.setNode_parent(Integer.parseInt(parentElec));
				}
			}
		}
		
//		DbEnergyType dbEnergyType=new DbEnergyType();
//		if(StringUtils.isNotEmpty(enercyType)){
//			dbEnergyType.setSeq(Integer.parseInt(enercyType));
//			dbEquipList.setDbEnergyType(dbEnergyType);
//		}
		DbUsers user=new DbUsers();
		user.setSeq(1);
		dbEquipList.setDbUsers(user);
		dbEquipList.setOpertime(new Date());
		dbEquipList.setStatus(Short.parseShort("0"));
		DbHospInfo hospital=new DbHospInfo();
		hospital.setSeqHosp(1);
		dbEquipList.setDbHospInfo(hospital);
		DbEquipList equip=null;
		/**
		 * 设备管理-->设备列表-->添加
		 * 
		 */
		try {
			
			equip = equipBusiness.insertEquipList(dbEquipList);
		} catch (Exception e1) {
			request.setAttribute("msg", "<script>alert('添加失败,请联系管理员！');location.href='javascript:history.go(-1);'</script>");
			log.error("EquipmentAction-->addEquipment", e1);
		}
		if(equip!=null){
			try{
				//加入服务区域
				DbEquipList equipList=new DbEquipList();
				RetCode rt=new RetCode();
				equipList.setEquipId(equip.getEquipId());
				for (Iterator iterator = insertEquipServ().iterator(); iterator
						.hasNext();) {
					EquipService object = (EquipService) iterator.next();
					object.setDbEquipList(equipList);
					equipServiceBusiness.insertEquipService(object);
				}
				log.info("equip.getEquipId()="+equip.getEquipId());
				//加入服务设备
				for (Iterator iterator = insertEquipServEquip().iterator(); iterator
				.hasNext();) {
					EquipServiceEquip object = (EquipServiceEquip) iterator.next();
					object.setDbEquipListByEquipId(equipList);
					eqSerEquipBusiness.insertEquipServiceEquip(object);
					//加入
				}
				
			   //获取参数模版
			    rt=equipParamBusiness.findDicParamByTypeId(dbEquipList.getDbEquipType().getEquipTypeId().toString());
			    if(rt.getObj()!=null){
			    	equipParamList=(List)rt.getObj(); 
			    	request.setAttribute("vLength", equipParamList.size());
			    }else{
			    	request.setAttribute("msg", "<script>alert('该设备暂未提供参数模板');location.href='equipment_showDetail?equipId="+equip.getEquipId()+"';</script>");
			    }
			    request.setAttribute("equipId", equip.getEquipId());
			}catch (Exception e) {
				log.error("EquipmentAction-->addEquipment",e);
				e.printStackTrace();
			}
		}else{
			log.info("equip为空");
		}
		return "addParam";
	}
	
	/**
	 * 插入服务设备
	 * @return
	 */
	private Set insertEquipServ(){
		Set equipServiceSet = new HashSet();
		boolean bool = true;
		int i = 0;
		while(bool){
			int flagCount = 0;
			EquipService equipServiceEn=new EquipService();
			DbBaseComm dbBaseComm=new DbBaseComm();
			String serviceBuild = "";
			String serviceStorey = "";
			String serviceArea = "";
			String serviceSquare = "";
			String serEnercyType = "";
			String serviceComments = "";
			if (i>0) {
				//服务区域（设备ID，楼宇，  楼层，服务区域，服务面积，能源,服务备注）
				 serviceBuild=request.getParameter("serviceBuild"+i);
				 serviceStorey=request.getParameter("serviceStorey"+i);
				 serviceArea=request.getParameter("serviceAreas"+i);
				 serviceSquare=request.getParameter("serviceSquare"+i);
				 serEnercyType=request.getParameter("serEnercyType"+i);
				 serviceComments = request.getParameter("serviceComments"+i);
				 log.info("(i>0)serviceBuild="+serviceBuild+";serviceStorey="+serviceStorey+";serviceArea="+serviceArea+";serviceSquare="+serviceSquare+";serEnercyType="+serEnercyType+";serviceComments="+serviceComments);
			}else{
				//服务区域（设备ID，楼宇，  楼层，服务区域，服务面积，能源,服务备注）
				 serviceBuild=request.getParameter("serviceBuild");
				 serviceStorey=request.getParameter("serviceStorey");
				 serviceArea=request.getParameter("serviceAreas");
				 serviceSquare=request.getParameter("serviceSquare");
				 serEnercyType=request.getParameter("serEnercyType");
				 serviceComments = request.getParameter("serviceComments");
				 log.info("(i<=0)serviceBuild="+serviceBuild+";serviceStorey="+serviceStorey+";serviceArea="+serviceArea+";serviceSquare="+serviceSquare+";serEnercyType="+serEnercyType+";serviceComments="+serviceComments);
			}
			
		
				
			//RequestIntrospectHelper.introspect(dbEquipList, request);
			try{
				if(StringUtils.isNotEmpty(serviceComments)){
					flagCount ++;
					equipServiceEn.setComments(serviceComments);
				}
				if(StringUtils.isNotEmpty(serviceBuild)){
					flagCount ++;
					equipServiceEn.setBuildId(Integer.parseInt(serviceBuild));
				}
				if(StringUtils.isNotEmpty(serviceStorey)){
					flagCount ++;
					equipServiceEn.setStorey(Integer.parseInt(serviceStorey));
				}
				if(StringUtils.isNotEmpty(serviceArea)){
					flagCount ++;
					dbBaseComm.setSeq(Integer.parseInt(serviceArea));
					equipServiceEn.setDbBaseComm(dbBaseComm);
				}
				if(StringUtils.isNotEmpty(serviceSquare)){
					flagCount ++;
					equipServiceEn.setSquare(Double.parseDouble(serviceSquare));
				}
				DbEnergyType serEnergyType  = new DbEnergyType();
				if(StringUtils.isNotEmpty(serEnercyType)){
					flagCount ++;
					serEnergyType.setSeq(Integer.parseInt(serEnercyType));
					equipServiceEn.setDbEnergyType(serEnergyType);
				}
				log.info("flagCount="+flagCount);
			}catch (Exception e) {
				log.error("EquipmentAction-->insertEquipServ",e);
				e.printStackTrace();
			}
			if(equipServiceEn==null||flagCount==0){
				bool = false;
				
			}else{
				equipServiceSet.add(equipServiceEn);
				bool = true;
				i++;
			}
		}
		return equipServiceSet;
	}
	
	/**
	 * 
	 * @return
	 */
	private Set insertEquipServEquip(){
		Set equipServiceEquipSet = new HashSet();
		boolean	boolflag = true;
		int j = 0;
		while(boolflag){
			EquipServiceEquip equipServiceEquip = new EquipServiceEquip();
			
			//服务设备（设备ID，服务设备ID）
			String serEQID =  "";
			if (j>0) {
				 serEQID = request.getParameter("equipNames"+j);
			}else{
				serEQID = request.getParameter("equipNames");
			}
			
			DbEquipList equip  = new DbEquipList();
			int flagNum = 0;
			if(StringUtils.isNotEmpty(serEQID)){
				flagNum++;
				equip.setEquipId(Integer.parseInt(serEQID));
			}
			equipServiceEquip.setDbEquipListBySerEquipId(equip);
			if(equipServiceEquip==null||flagNum==0){
				boolflag = false;
			}else{
				equipServiceEquipSet.add(equipServiceEquip);
				boolflag = true;
				j++;
			}
		}
		return equipServiceEquipSet;
	}
	/**
	 * 设备报废
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String deleteEquipment(){
		request = ServletActionContext.getRequest();
		String equipListId=request.getParameter("eqId");
		int result=equipBusiness.deleteEquipList(equipListId);
		if(result>0){
			request.setAttribute("msg", "<script>alert('报废成功');</script>");
		}
	    DbEquipList dbEquipList=new DbEquipList();
		//加载楼宇信息
		DbBuilding building=new DbBuilding();
		// 根据条件获取设备列表信息
		if (currentPage==null) {
			currentPage = 1;
		}
		RetCode rc = equipBusiness.findALlEquipListInfo(dbEquipList, currentPage, pageSize);
		if (rc.getObj() != null) {
			listEquipList = (List<DbEquipList>) rc.getObj();
		}
		request.setAttribute("page", rc.getPage());
		return equipList;
	}
	
	/**
	 * 跳转到修改设备信息页面
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String showEditUI(){
		request = ServletActionContext.getRequest();
		String equipId=request.getParameter("equipId");
		if(StringUtils.isNotEmpty(equipId)){
		   dbEquipListDetail=equipBusiness.findDbEquipListById(Integer.parseInt(equipId));
		    List<Map<String,Object>> allElec = equipBusiness.getAllElec(equipId);
		    request.setAttribute("allElec",allElec);
		}
		int storyUp=dbEquipListDetail.getDbBuilding().getStoreyNumUp();
		int storyDown=dbEquipListDetail.getDbBuilding().getStoreyNumDown();
		storeyList=new ArrayList<Integer>();
		for(int i=storyUp;i>0;i--){
			storeyList.add(i);
		}
		for(int i=1;i<=storyDown;i++){
			storeyList.add(0-i);
		}
		DbBuilding building=new DbBuilding();
		listBuilding=(List<DbBuilding>)buildBusiness.findDBBuilding(building).getObj();
		listEnergy=(List<DbEnergyType>)energyTypeBusiness.findAllEnergyType().getObj();
//		equipService=new EquipService();
		equipServiceList =equipServiceBusiness.findByEquipId(equipId);
		listArea=(List)baseCommBusiness.findAreaName().getObj();
		List l = new ArrayList();
		for (int i = 0; i < equipServiceList.size(); i++) {
			List sstoreyList = new ArrayList();
		    DbBuilding build=new DbBuilding();
			if(equipServiceList.get(i).getBuildId()!=null){
				build.setBuildingId(equipServiceList.get(i).getBuildId());
				build=buildBusiness.findBuildingById(build);
			}
			if(build!=null && build.getBuildingId()!=null){
				for(int j=build.getStoreyNumUp();j>0;j--){
					sstoreyList.add(j);
				}
				for(int j=1;j<=build.getStoreyNumDown();j++){
					sstoreyList.add(0-j);
				}
				l.add(sstoreyList);
			}
		
		}
		request.setAttribute("sstoreyList",l );
		//服务设备
		eqSerEquipList = eqSerEquipBusiness.findEquipSerEquipByEquipId(Integer.parseInt(equipId));
		List tempList = new ArrayList();
		for (int i = 0; i < eqSerEquipList.size(); i++) {
			
			int equipTypeId = eqSerEquipList.get(i).getDbEquipListBySerEquipId().getDbEquipType().getEquipTypeId();
//			List<DbEquipList> lll= (List<DbEquipList>)equipBusiness.findByEquipTypeId(equipTypeId);
//			List<DbEquipList> lll = new ArrayList<DbEquipList>();
			RetCode rc = equipBusiness.findByEquipTypeId(equipTypeId);
			if(rc.getObj() != null){
				List<DbEquipList> lll = (List<DbEquipList>) rc.getObj();
				tempList.add(lll);
//				tempList.add(rc.getObj());
			}
		}
		request.setAttribute("eequipList",tempList );
		return equipEditPage;
	}
	
	/**
	 * 修改并保存设备信息
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String editEquipment(){
		request = ServletActionContext.getRequest();
		equipService=new EquipService();
//		DbBaseComm dbBaseComm=new DbBaseComm();
//		String serviceBuild=request.getParameter("serviceBuild");
//		String serviceStorey=request.getParameter("serviceStorey");
//		String serviceArea=request.getParameter("serviceAreas");
//		String serviceSquare=request.getParameter("serviceSquare");
//		String serviceId=request.getParameter("serviceId");
		String controlFlag=request.getParameter("controlFlag");
		String elecLevel=request.getParameter("elecLevel");
		String parentElec=request.getParameter("parentElec");
	
		try{
//			if(StringUtils.isNotEmpty(serviceBuild)){
//				equipService.setBuildId(Integer.parseInt(serviceBuild));
//			}
//			if(StringUtils.isNotEmpty(serviceStorey)){
//				equipService.setStorey(Integer.parseInt(serviceStorey));
//			}
//			if(StringUtils.isNotEmpty(serviceArea)){
//				dbBaseComm.setSeq(Integer.parseInt(serviceArea));
//				equipService.setDbBaseComm(dbBaseComm);
//			}
//			if(StringUtils.isNotEmpty(serviceSquare)){
//				equipService.setSquare(Double.parseDouble(serviceSquare));
//			}
//			if(StringUtils.isNotEmpty(serviceId)){
//				equipService.setSeq(Integer.parseInt(serviceId));
//			}
			if(StringUtils.isNotEmpty(controlFlag)){
				dbEquipList.setControlFlag(Short.parseShort(controlFlag));
			}
			if(StringUtils.isNotEmpty(elecLevel)){
				if(dbEquipList.getDbEquipType()!=null){
					if(dbEquipList.getDbEquipType().getEquipTypeId()==10001){
						dbEquipList.setNode_level(Integer.parseInt(elecLevel));
					}
				}
			}
			if(StringUtils.isNotEmpty(parentElec)){
				if(dbEquipList.getDbEquipType()!=null){
					if(dbEquipList.getDbEquipType().getEquipTypeId()==10001){
						dbEquipList.setNode_parent(Integer.parseInt(parentElec));
					}
				}
			}
			//equipService.setEquipId(dbEquipList.getEquipId().toString());
//			equipService.setDbEquipList(dbEquipList);
		}catch (Exception e) {
			log.error("EquipmentAction-->editEquipment",e);
			e.printStackTrace();
		}
		DbUsers user=new DbUsers();
		user.setSeq(1);
		dbEquipList.setDbUsers(user);
		dbEquipList.setOpertime(new Date());
		dbEquipList.setStatus(Short.parseShort("0"));
		DbHospInfo hospital=new DbHospInfo();
		hospital.setSeqHosp(1);
		dbEquipList.setDbHospInfo(hospital);
		try{
			RetCode rt=new RetCode();
			//更新设备信息
			dbEquipList=equipBusiness.updateEquipList(dbEquipList);
			//更新设备服务设备信息（先删除、后加入）
			if (dbEquipList!=null&&dbEquipList.getEquipId()!=null) {
				equipServiceBusiness.deleteEquipService(dbEquipList.getEquipId().toString());
				DbEquipList tempEquip = new DbEquipList();
				tempEquip.setEquipId(dbEquipList.getEquipId());
				for (Iterator iterator = insertEquipServ().iterator(); iterator
						.hasNext();) {
					EquipService object = (EquipService) iterator.next();
					object.setDbEquipList(tempEquip);
					equipServiceBusiness.insertEquipService(object);
				}
				eqSerEquipBusiness.deleteEquipSerEquip(dbEquipList.getEquipId().toString());
				//加入服务设备
				for (Iterator iterator = insertEquipServEquip().iterator(); iterator.hasNext();) {
					EquipServiceEquip object = (EquipServiceEquip) iterator.next();
					object.setDbEquipListByEquipId(tempEquip);
					eqSerEquipBusiness.insertEquipServiceEquip(object);
					//加入
				}
			}
			//当页面没有下拉框
			String typeId = dbEquipList.getDbEquipType().getEquipTypeId().toString();
			if(typeId==null||typeId==""){
				log.info("这里不需要下拉框...");
			}else{
				try {
					/**
					 * 根据类型获取comm表对应的类型(整个的下拉框里面的值)
					 */
					if (StringUtils.isNotEmpty(dbEquipList.getEquipId().toString())) {
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
			
//	        equipServiceBusiness.updateEquipService(equipService);
	        //根据设备编号，查询所有的相关的设备参数信息
	        rt=equipParamBusiness.findDicParamByEquipId(dbEquipList.getEquipId().toString());
		    if(rt.getObj()!=null){
		    	equipParamList=(List)rt.getObj(); 
		    	request.setAttribute("vLength", equipParamList.size());
		    }else{
		    	/* edit start 2013.4.25 by zhangdiannan
		    	   js 提示框  "该设备暂未提供参数模板" 加入 修改成功*/
		    	request.setAttribute("msg", "<script>alert('修改成功');alert('该设备暂未提供参数模板');location.href='equipment_showDetail?equipId="+dbEquipList.getEquipId()+"';</script>");
		    	/*
		    	request.setAttribute("msg", "<script>alert('该设备暂未提供参数模板');location.href='equipment_showDetail?equipId="+dbEquipList.getEquipId()+"';</script>");
		   		*/
		    	/*edit end 2013.4.25 by zhangdiannan */
		    }
		}catch (Exception e) {
			log.error("EquipmentAction-->editEquipment",e);
			e.printStackTrace();
		}
		//equipId=dbEquipList.getEquipId().toString();
		request.setAttribute("equipId", dbEquipList.getEquipId());
        return "editParam";
	}
	
	/**
	 * 检测设备编号是否存在
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String checkEquipCode() throws Exception{
		request = ServletActionContext.getRequest();
		String equipCode=request.getParameter("equipCode");
		String equipId=request.getParameter("equipId");
		if(StringUtils.isNotEmpty(equipCode)){
			RetCode rt=equipBusiness.checkEquipCode(equipCode,equipId);
			List rtList=(List)(rt.getObj());
			if(rtList!=null){
				//当返回设备数大于0，证明当前设备编号已存在，反之，设备编号不存在
				if(rtList.size()>0){
					resultJSON = "[{'res':'has'}]";
				}else{
					resultJSON = "[{'res':'no'}]";
				}
			}else{
				resultJSON = "[{'res':'no'}]";
			}
			ServletActionContext.getResponse().getWriter().print(resultJSON);
		}
		return null;
	}
	
	/**
	 * 根据楼宇编号，加载对应楼层下拉列表
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String findStoreyByBuildId() throws Exception{
		request = ServletActionContext.getRequest();
		String buildId=request.getParameter("buildId");
		DbBuilding build=new DbBuilding();
		build.setBuildingId(Integer.parseInt(buildId));
		build=buildBusiness.findBuildingById(build);
		serviceStoreyList=new ArrayList<Integer>();
		if(build!=null && build.getBuildingId()!=null){
			for(int i=build.getStoreyNumUp();i>0;i--){
				serviceStoreyList.add(i);
			}
			for(int i=1;i<=build.getStoreyNumDown();i++){
				serviceStoreyList.add(0-i);
			}
		}
		JSONArray ja=JSONArray.fromObject(serviceStoreyList);
		resultJSON=ja.toString();
		ServletActionContext.getResponse().getWriter().print(resultJSON);
		log.info("buildId."+buildId);
		return null;
	}
	
	
	/**
	 * 显示设备详细信息
	 * @return
	 */
	public String showDetail(){
		request = ServletActionContext.getRequest(); 
			//查询里面的信息
			String searchBuildId = request.getParameter("buildId");
			String searchStorey = request.getParameter("storey");
			String hisName = request.getParameter("hisName");
			String place = request.getParameter("place");
			String unitType = request.getParameter("unitType");
			String eqTypeId = request.getParameter("eqTypeId");
			String equipTypeName = request.getParameter("equipTypeId");
			String equipName = request.getParameter("equipName");
			log.info("searchBuildId="+searchStorey+";searchBuildId="+searchBuildId+";hisName="+hisName);
			log.info("hisName="+hisName+";place="+place+";unitType="+unitType+";eqTypeId="+eqTypeId+";equipTypeName="+equipTypeName+";equipName="+equipName);
			DbBuilding building=new DbBuilding();
			DbEquipList searchEquiplist = new DbEquipList();
			DbBuilding searchBuilding = new DbBuilding();
			DbEquipType dbequipType = new DbEquipType();
			if (StringUtil.isNotEmpty(searchBuildId)&&!"0".equals(searchBuildId)) {
				searchBuilding.setBuildingId(Integer.parseInt(searchBuildId));
				
			}
			if (StringUtil.isNotEmpty(searchStorey)&&!"0".equals(searchStorey)) {
				searchEquiplist.setStorey(Double.parseDouble(searchStorey));
			}
			if (StringUtil.isNotEmpty(hisName)) {
				searchBuilding.setHisName(hisName);
			}
			if (StringUtil.isNotEmpty(place)) {
				searchBuilding.setPlace(place);
			}
			if (StringUtil.isNotEmpty(unitType)) {
				searchEquiplist.setUnitType(unitType);
			}
			
			if (StringUtil.isNotEmpty(eqTypeId)) {
				searchEquiplist.setEquipName(eqTypeId);
			}
			if (StringUtil.isNotEmpty(equipName)) {
				searchEquiplist.setEquipName(equipName);
			}
			if (StringUtil.isNotEmpty(equipTypeName)) {
				dbequipType.setTypeName(equipTypeName);
			}
			 
			searchEquiplist.setDbEquipType(dbequipType);
			searchEquiplist.setDbBuilding(searchBuilding);
			listBuilding=(List<DbBuilding>)buildBusiness.findDBBuilding(building).getObj();
			String equipId=request.getParameter("equipId");
			if(StringUtils.isNotEmpty(equipId)){
			   dbEquipListDetail=equipBusiness.findDbEquipListById(Integer.parseInt(equipId));
			   DbBuilding dbBuilding = buildBusiness.findById(dbEquipListDetail.getDbBuilding().getBuildingId());
			   //根据设备ID获取设备服务信息
//			   eqSerEquipList = eqSerEquipBusiness.findEquipSerEquipByEquipId(Integer.parseInt(equipId));
			   dbEquipListDetail.setDbBuilding(dbBuilding);
//			   dbEquipListDetail.setEquipServiceEquipsForEquipId(eqSerEquipList.get(0).getDbEquipListByEquipId().getEquipServiceEquipsForEquipId());
			   request.setAttribute("dbEquipListDetail",dbEquipListDetail);
			}else{
				log.info("equipId为空");
			}
			String listToDetail = request.getParameter("listToDetail");
			if(StringUtils.isNotEmpty(listToDetail)){
				request.setAttribute("listToDetail", listToDetail);
				}else{
					log.info("listToDetail为空");
				}
			log.info("listToDetail="+listToDetail);
			request.setAttribute("equipId", equipId);
			request.setAttribute("searchEquipList", searchEquiplist);
		
		//edit by lg 2013.5.8 注释
		/*
		 * add start 2013.5.2 by zhangdiannan
		 * 添加返回上一页
		 */
//		if(request.getParameter("history")!=null){
//			request.setAttribute("js", "<a href='javascript:history.go(-1);' class='btn blue'>返回</a>");
//		}
		/*
		 * add end 2013.5.2 by zhangdiannan
		 */
			
		//调用抽取任意提醒需要的数据的方法
		remindBuildInfoDate();
		
		return equipDetailPage;
	}
	
	/**
	 * 根据条件查询设备信息
	 * @return
	 */
	public String findEquipListByParam() throws Exception{
		request = ServletActionContext.getRequest();
		DbEquipList equipList=new DbEquipList();
		DbBuilding build=new DbBuilding();
		DbEquipType dbEquipType=new DbEquipType();
		
		String buildId=request.getParameter("buildId");
		String hisName=request.getParameter("hisName");
		String stroey=request.getParameter("storey");
		String place=request.getParameter("place");
     	String unitType=request.getParameter("unitType");
		String equipTypeId=request.getParameter("eqTypeId");
		
		if(StringUtils.isNotEmpty(buildId)&&!"0".equals(buildId)){
			build.setBuildingId(Integer.parseInt(buildId));
		}
		if(StringUtils.isNotEmpty(hisName)){
			build.setHisName(hisName);
		}
		if(StringUtils.isNotEmpty(stroey)&&!"0".equals(stroey)){
			equipList.setStorey(Double.parseDouble(stroey));
		}
		if(StringUtils.isNotEmpty(place)){
			equipList.setPlace(place);
		}
		if(StringUtils.isNotEmpty(unitType)){
			equipList.setUnitType(unitType);
		}
		if(StringUtils.isNotEmpty(equipTypeId)){
			dbEquipType.setEquipTypeId(Integer.parseInt(equipTypeId));
		}
		equipList.setDbEquipType(dbEquipType);
		equipList.setDbBuilding(build);
		RetCode rc=equipBusiness.findEquipmentByParam(equipList);
		if (rc.getObj() != null) {
			listEquipList = (List<DbEquipList>) rc.getObj();
		}
	
		
		//查询结果
		JSONArray ja=JSONArray.fromObject(listEquipList);
		resultJSON=ja.toString();
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");   
		ServletActionContext.getResponse().getWriter().print(resultJSON);
		return null;
	}
	/**
	 *  取上报数据
	 * @param reportMap
	 * @param reportRate
	 */
	public void getEquipment(Map reportMap, DbReportRate reportRate) {
		RetCode EquipmentCode = equipBusiness.reportEquipment(reportRate
				.getReportdate());
		if (EquipmentCode.getObj() != null) {
			reportMap.put(reportRate.getDbReportType().getTypeId() + "",
					EquipmentCode.getObj());
		}
	}
	
	
	
	
	/**
	 * 获取楼宇名称及楼层
	 * 
	 */
	public void getBuildingInfoList(){
		try{
			JSONArray array = new  JSONArray();
			RetCode rc = buildBusiness.findBuildingBySql();
			if (rc.getObj()!=null) {
				List buildList = (List)rc.getObj();
				for (int i = 0; i < buildList.size(); i++) {
					Map map = (Map)buildList.get(i);
					JSONObject object = new JSONObject();
					object.accumulate("buildName", map.get("building_name"));
					object.accumulate("buildId", map.get("building_id"));
					object.accumulate("storeyDown", map.get("storey_num_down"));
					object.accumulate("storeyUp", map.get("storey_num_up"));
					array.add(object);
				}
			}
			JSONObject obj = new  JSONObject();
			obj.accumulate("builds", array);
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(obj);
		}catch (Exception e) {
			log.error("EquipmentAction-->getBuildingInfoList",e);
			// TODO: handle exception
		}
	}
	
	/**
	 * 获取设备类型
	 * 
	 */
	public void findEquipType(){
		request = ServletActionContext.getRequest();
		JSONObject object = new JSONObject();
		try{
			String eqTypeId=request.getParameter("eqTypeId");
			dbEquipType=new DbEquipType();
			index+=eqTypeId+",";
			getEqTypeId(eqTypeId);
			index=index.substring(0,index.lastIndexOf(","));
			object.accumulate("types", index);
		}catch (Exception e) {
			log.error("EquipmentAction-->findEquipType",e);
			object.accumulate("types", "error");
		}finally{
			try{
				ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				ServletActionContext.getResponse().getWriter().print(object.toString());
			}catch (Exception e) {
				log.error("EquipmentAction-->findEquipType",e);
				// TODO: handle exception
			}
		}
	}
	
	/**
	 * 获取设备类型ID
	 * @param eqTypeId
	 */
	public void getEqTypeId(String eqTypeId){
		if(StringUtils.isNotBlank(eqTypeId)){
			DbEquipType equipType=new DbEquipType();
			equipType=equipTypeBusiness.findById(Integer.parseInt(eqTypeId));
			if(equipType.getDbEquipType()!=null){
				index+=equipType.getDbEquipType().getEquipTypeId().toString()+",";
				getEqTypeId(equipType.getDbEquipType().getEquipTypeId().toString());
			}
		}
	}
	
	
	/**
	 * 将任意提醒的数据打包成map
	 * @param 
	 * @param
	 * @return
	 */
	public String remindBuildInfoDate() {
		int currentPage = 1;
		int pageSize = 10;
		request = ServletActionContext.getRequest();
		
		try {
			dbEquipListDetail = equipBusiness.findDbEquipListById(Integer.parseInt(equipId));
			//获取设备详情sql对象放到Map对象中
			sqlmap  = equipBusiness.getFindDbEquipListByIdSql();
			
			log.debug("***************sqlmap="+sqlmap);
			
			String equipId=request.getParameter("equipId");
			seqMap.put("equipId", equipId);
			
			//（buildingName）
			//提醒方式:1、短信提醒；2、邮件提醒；3、平台内部提醒
			buildingNameMap.put("ariesPreForm", "1,2,3");
			//sql字段名称
			buildingNameMap.put("sqlField", "buildingNamTwo");
			//中文字段名
			buildingNameMap.put("fieldNm", "所在楼宇");
			//输入类型 ：1-文本;2-数字;3-时间
			buildingNameMap.put("ariesInputType", 1);
			//sql
			buildingNameMap.putAll(sqlmap);
			//含有多条，或者分页数据是用来对应数据用，数据是查出来的某条数据中有标识性的字段，如seq、ID等
			buildingNameMap.put("sqlFieldKey", seqMap);
			//用来判断数据是否更新的时间字段，通过sql查询出来的结果集中的时间字段等来判断
			buildingNameMap.put("updateDateField", "opertime");
			//用来判断数据是否更新的sql
			buildingNameMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
//			remindmap.put("initFunc", "testDate");
			
			//（equipCode）
			equipCodeMap.put("ariesPreForm", "1,2,3");
			equipCodeMap.put("sqlField", "equipCode");
			equipCodeMap.put("fieldNm", "设备编号");
			equipCodeMap.put("ariesInputType", 1);
			equipCodeMap.putAll(sqlmap);
			equipCodeMap.put("sqlFieldKey", seqMap);
			equipCodeMap.put("updateDateField", "opertime");
			equipCodeMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（equipNameTow）
			equipNameMap.put("ariesPreForm", "1,2,3");
			equipNameMap.put("sqlField", "equipNameTow");
			equipNameMap.put("fieldNm", "设备名称");
			equipNameMap.put("ariesInputType", 1);
			equipNameMap.putAll(sqlmap);
			equipNameMap.put("sqlFieldKey", seqMap);
			equipNameMap.put("updateDateField", "opertime");
			equipNameMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（storeyNum）
			typeNameMap.put("ariesPreForm", "1,2,3");
			typeNameMap.put("sqlField", "storeyNum");
			typeNameMap.put("fieldNm", "设备类型");
			typeNameMap.put("ariesInputType", 2);
			typeNameMap.putAll(sqlmap);
			typeNameMap.put("sqlFieldKey", seqMap);
			typeNameMap.put("updateDateField", "opertime");
			typeNameMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（oper）
			unitTypeMap.put("ariesPreForm", "1,2,3");
			unitTypeMap.put("sqlField", "unitTypeTwo");
			unitTypeMap.put("fieldNm", "设备型号");
			unitTypeMap.put("ariesInputType", 1);
			unitTypeMap.putAll(sqlmap);
			unitTypeMap.put("sqlFieldKey", seqMap);
			unitTypeMap.put("updateDateField", "opertime");
			unitTypeMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（storey）
			storeyMap.put("ariesPreForm", "1,2,3");
			storeyMap.put("sqlField", "storeyTwo");
			storeyMap.put("fieldNm", "所在楼层");
			storeyMap.put("ariesInputType", 2);
			storeyMap.putAll(sqlmap);
			storeyMap.put("sqlFieldKey", seqMap);
			storeyMap.put("updateDateField", "opertime");
			storeyMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（place）
			placeMap.put("ariesPreForm", "1,2,3");
			placeMap.put("sqlField", "placeTwo");
			placeMap.put("fieldNm", "安装位置");
			placeMap.put("ariesInputType", 1);
			placeMap.putAll(sqlmap);
			placeMap.put("sqlFieldKey", seqMap);
			placeMap.put("updateDateField", "opertime");
			placeMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（controlFlag）
			controlFlagMap.put("ariesPreForm", "1,2,3");
			controlFlagMap.put("sqlField", "controlFlag");
			controlFlagMap.put("fieldNm", "是否监控");
			controlFlagMap.put("ariesInputType", 1);
			controlFlagMap.putAll(sqlmap);
			controlFlagMap.put("sqlFieldKey", seqMap);
			controlFlagMap.put("updateDateField", "opertime");
			controlFlagMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（equipServiceEquip）
			equipServiceEquipMap.put("ariesPreForm", "1,2,3");
			equipServiceEquipMap.put("sqlField", "equipServiceEquip");
			equipServiceEquipMap.put("fieldNm", "服务设备");
			equipServiceEquipMap.put("ariesInputType", 1);
			equipServiceEquipMap.putAll(sqlmap);
			equipServiceEquipMap.put("sqlFieldKey", seqMap);
			equipServiceEquipMap.put("updateDateField", "opertime");
			equipServiceEquipMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（equipServices）
			equipServicesMap.put("ariesPreForm", "1,2,3");
			equipServicesMap.put("sqlField", "equipServices");
			equipServicesMap.put("fieldNm", "服务区域");
			equipServicesMap.put("ariesInputType", 1);
			equipServicesMap.putAll(sqlmap);
			equipServicesMap.put("sqlFieldKey", seqMap);
			equipServicesMap.put("updateDateField", "opertime");
			equipServicesMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（assetscode）
			assetscodeMap.put("ariesPreForm", "1,2,3");
			assetscodeMap.put("sqlField", "assetscode");
			assetscodeMap.put("fieldNm", "资产编号");
			assetscodeMap.put("ariesInputType", 1);
			assetscodeMap.putAll(sqlmap);
			assetscodeMap.put("sqlFieldKey", seqMap);
			assetscodeMap.put("updateDateField", "opertime");
			assetscodeMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（production）
			productionMap.put("ariesPreForm", "1,2,3");
			productionMap.put("sqlField", "production");
			productionMap.put("fieldNm", "生产单位");
			productionMap.put("ariesInputType", 1);
			productionMap.putAll(sqlmap);
			productionMap.put("sqlFieldKey", seqMap);
			productionMap.put("updateDateField", "opertime");
			productionMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（field）
			fieldMap.put("ariesPreForm", "1,2,3");
			fieldMap.put("sqlField", "field");
			fieldMap.put("fieldNm", "产地");
			fieldMap.put("ariesInputType", 1);
			fieldMap.putAll(sqlmap);
			fieldMap.put("sqlFieldKey", seqMap);
			fieldMap.put("updateDateField", "opertime");
			fieldMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（productionDate）
			productionDateMap.put("ariesPreForm", "1,2,3");
			productionDateMap.put("sqlField", "productionDate");
			productionDateMap.put("fieldNm", "生产日期");
			productionDateMap.put("ariesInputType", 3);
			productionDateMap.putAll(sqlmap);
			productionDateMap.put("sqlFieldKey", seqMap);
			productionDateMap.put("updateDateField", "opertime");
			productionDateMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（installDate）
			installDateMap.put("ariesPreForm", "1,2,3");
			installDateMap.put("sqlField", "installDate");
			installDateMap.put("fieldNm", "安装日期");
			installDateMap.put("ariesInputType", 3);
			installDateMap.putAll(sqlmap);
			installDateMap.put("sqlFieldKey", seqMap);
			installDateMap.put("updateDateField", "opertime");
			installDateMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（useDate）
			useDateMap.put("ariesPreForm", "1,2,3");
			useDateMap.put("sqlField", "useDate");
			useDateMap.put("fieldNm", "使用日期");
			useDateMap.put("ariesInputType", 3);
			useDateMap.putAll(sqlmap);
			useDateMap.put("sqlFieldKey", seqMap);
			useDateMap.put("updateDateField", "opertime");
			useDateMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（limited）
			limitedMap.put("ariesPreForm", "1,2,3");
			limitedMap.put("sqlField", "limited");
			limitedMap.put("fieldNm", "质保期");
			limitedMap.put("ariesInputType", 1);
			limitedMap.putAll(sqlmap);
			limitedMap.put("sqlFieldKey", seqMap);
			limitedMap.put("updateDateField", "opertime");
			limitedMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（serviceLife）
			serviceLifeMap.put("ariesPreForm", "1,2,3");
			serviceLifeMap.put("sqlField", "serviceLife");
			serviceLifeMap.put("fieldNm", "使用年限");
			serviceLifeMap.put("ariesInputType", 1);
			serviceLifeMap.putAll(sqlmap);
			serviceLifeMap.put("sqlFieldKey", seqMap);
			serviceLifeMap.put("updateDateField", "opertime");
			serviceLifeMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（serviceCycle）
			serviceCycleMap.put("ariesPreForm", "1,2,3");
			serviceCycleMap.put("sqlField", "serviceCycle");
			serviceCycleMap.put("fieldNm", "保养周期");
			serviceCycleMap.put("ariesInputType", 1);
			serviceCycleMap.putAll(sqlmap);
			serviceCycleMap.put("sqlFieldKey", seqMap);
			serviceCycleMap.put("updateDateField", "opertime");
			serviceCycleMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（purchase）
			purchaseMap.put("ariesPreForm", "1,2,3");
			purchaseMap.put("sqlField", "purchase");
			purchaseMap.put("fieldNm", "采购价");
			purchaseMap.put("ariesInputType", 2);
			purchaseMap.putAll(sqlmap);
			purchaseMap.put("sqlFieldKey", seqMap);
			purchaseMap.put("updateDateField", "opertime");
			purchaseMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（brand）
			brandMap.put("ariesPreForm", "1,2,3");
			brandMap.put("sqlField", "brand");
			brandMap.put("fieldNm", "主要附件");
			brandMap.put("ariesInputType", 1);
			brandMap.putAll(sqlmap);
			brandMap.put("sqlFieldKey", seqMap);
			brandMap.put("updateDateField", "opertime");
			brandMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（accessory）
			accessoryMap.put("ariesPreForm", "1,2,3");
			accessoryMap.put("sqlField", "accessory");
			accessoryMap.put("fieldNm", "设备品牌");
			accessoryMap.put("ariesInputType", 1);
			accessoryMap.putAll(sqlmap);
			accessoryMap.put("sqlFieldKey", seqMap);
			accessoryMap.put("updateDateField", "opertime");
			accessoryMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");

			//将所有需要提醒的数据打包成map
			remindmapAll.put("buildingNamTwo", buildingNameMap);
			remindmapAll.put("equipCode", equipCodeMap);
			remindmapAll.put("equipNameTow", equipNameMap);
			remindmapAll.put("typeName", typeNameMap);
			remindmapAll.put("unitTypeTwo", unitTypeMap);
			remindmapAll.put("storeyTwo", storeyMap);
			remindmapAll.put("placeTwo", placeMap);
			remindmapAll.put("controlFlag", controlFlagMap);
			remindmapAll.put("equipServiceEquip", equipServiceEquipMap);
			remindmapAll.put("equipServices", equipServicesMap);
			remindmapAll.put("assetscode", assetscodeMap);
			remindmapAll.put("production", productionMap);
			remindmapAll.put("field", fieldMap);
			remindmapAll.put("productionDate", productionDateMap);
			remindmapAll.put("installDate", installDateMap);
			remindmapAll.put("useDate", useDateMap);
			remindmapAll.put("limited", limitedMap);
			remindmapAll.put("serviceLife", serviceLifeMap);
			remindmapAll.put("serviceCycle", serviceCycleMap);
			remindmapAll.put("purchase", purchaseMap);
			remindmapAll.put("brand", brandMap);
			remindmapAll.put("accessory", accessoryMap);
			
			
			//将已经打包好的MAP转换成JSON
			JSONObject remindJson = JSONObject.fromObject(remindmapAll);
			
			request.setAttribute("remindJson", remindJson);
			log.debug("    remindJson=   "+remindJson);
		}catch (Exception e) {
			log.error("HospDetailAction-->showHospDetail", e);
		}
		return "show";
	}
	
	
	
	

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the listBuilding
	 */
	public List<DbBuilding> getListBuilding() {
		return listBuilding;
	}

	/**
	 * @param listBuilding the listBuilding to set
	 */
	public void setListBuilding(List<DbBuilding> listBuilding) {
		this.listBuilding = listBuilding;
	}

	/**
	 * @return the listEnergy
	 */
	public List<DbEnergyType> getListEnergy() {
		return listEnergy;
	}

	/**
	 * @param listEnergy the listEnergy to set
	 */
	public void setListEnergy(List<DbEnergyType> listEnergy) {
		this.listEnergy = listEnergy;
	}

	/**
	 * @return the equipBusiness
	 */
	public EquipListBusiness getEquipBusiness() {
		return equipBusiness;
	}

	/**
	 * @param equipBusiness the equipBusiness to set
	 */
	public void setEquipBusiness(EquipListBusiness equipBusiness) {
		this.equipBusiness = equipBusiness;
	}

	/**
	 * @return the listEquipList
	 */
	public List<DbEquipList> getListEquipList() {
		return listEquipList;
	}

	/**
	 * @param listEquipList the listEquipList to set
	 */
	public void setListEquipList(List<DbEquipList> listEquipList) {
		this.listEquipList = listEquipList;
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

	/**
	 * @return the hospitalBusiness
	 */
	public HospInfoBusiness getHospitalBusiness() {
		return hospitalBusiness;
	}

	/**
	 * @param hospitalBusiness the hospitalBusiness to set
	 */
	public void setHospitalBusiness(HospInfoBusiness hospitalBusiness) {
		this.hospitalBusiness = hospitalBusiness;
	}

	/**
	 * @return the energyTypeBusiness
	 */
	public EnergyTypeBusiness getEnergyTypeBusiness() {
		return energyTypeBusiness;
	}

	/**
	 * @param energyTypeBusiness the energyTypeBusiness to set
	 */
	public void setEnergyTypeBusiness(EnergyTypeBusiness energyTypeBusiness) {
		this.energyTypeBusiness = energyTypeBusiness;
	}

	/**
	 * @return the dbEquipListDetail
	 */
	public DbEquipList getDbEquipListDetail() {
		return dbEquipListDetail;
	}

	/**
	 * @param dbEquipListDetail the dbEquipListDetail to set
	 */
	public void setDbEquipListDetail(DbEquipList dbEquipListDetail) {
		this.dbEquipListDetail = dbEquipListDetail;
	}

	/**
	 * @return the storeyList
	 */
	public List<Integer> getStoreyList() {
		return storeyList;
	}

	/**
	 * @param storeyList the storeyList to set
	 */
	public void setStoreyList(List<Integer> storeyList) {
		this.storeyList = storeyList;
	}

	/**
	 * @return the serviceStoreyList
	 */
	public List<Integer> getServiceStoreyList() {
		return serviceStoreyList;
	}

	/**
	 * @param serviceStoreyList the serviceStoreyList to set
	 */
	public void setServiceStoreyList(List<Integer> serviceStoreyList) {
		this.serviceStoreyList = serviceStoreyList;
	}

	/**
	 * @return the equipService
	 */
	public EquipService getEquipService() {
		return equipService;
	}

	/**
	 * @param equipService the equipService to set
	 */
	public void setEquipService(EquipService equipService) {
		this.equipService = equipService;
	}

	/**
	 * @return the equipServiceBusiness
	 */
	public EquipServiceBusiness getEquipServiceBusiness() {
		return equipServiceBusiness;
	}

	/**
	 * @param equipServiceBusiness the equipServiceBusiness to set
	 */
	public void setEquipServiceBusiness(EquipServiceBusiness equipServiceBusiness) {
		this.equipServiceBusiness = equipServiceBusiness;
	}

	/**
	 * @return the resultJSON
	 */
	public String getResultJSON() {
		return resultJSON;
	}

	/**
	 * @param resultJSON the resultJSON to set
	 */
	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}

	/**
	 * @return the equipParamBusiness
	 */
	public EquipParamBusiness getEquipParamBusiness() {
		return equipParamBusiness;
	}

	/**
	 * @param equipParamBusiness the equipParamBusiness to set
	 */
	public void setEquipParamBusiness(EquipParamBusiness equipParamBusiness) {
		this.equipParamBusiness = equipParamBusiness;
	}

	/**
	 * @return the equipParamList
	 */
	public List getEquipParamList() {
		return equipParamList;
	}

	/**
	 * @param equipParamList the equipParamList to set
	 */
	public void setEquipParamList(List equipParamList) {
		this.equipParamList = equipParamList;
	}

	/**
	 * @return the equipId
	 */
	public String getEquipId() {
		return equipId;
	}

	/**
	 * @param equipId the equipId to set
	 */
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	/**
	 * @return the equipTypeBusiness
	 */
	public EquipTypeBusiness getEquipTypeBusiness() {
		return equipTypeBusiness;
	}

	/**
	 * @param equipTypeBusiness the equipTypeBusiness to set
	 */
	public void setEquipTypeBusiness(EquipTypeBusiness equipTypeBusiness) {
		this.equipTypeBusiness = equipTypeBusiness;
	}

	/**
	 * @return the dbEquipType
	 */
	public DbEquipType getDbEquipType() {
		return dbEquipType;
	}

	/**
	 * @param dbEquipType the dbEquipType to set
	 */
	public void setDbEquipType(DbEquipType dbEquipType) {
		this.dbEquipType = dbEquipType;
	}

	/**
	 * @return the dbEquipList
	 */
	public DbEquipList getDbEquipList() {
		return dbEquipList;
	}

	/**
	 * @param dbEquipList the dbEquipList to set
	 */
	public void setDbEquipList(DbEquipList dbEquipList) {
		this.dbEquipList = dbEquipList;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the menuInterceptor
	 */
	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	/**
	 * @param menuInterceptor the menuInterceptor to set
	 */
	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}

	/**
	 * @return the menuIdMap
	 */
	public Map getMenuIdMap() {
		return menuIdMap;
	}

	/**
	 * @param menuIdMap the menuIdMap to set
	 */
	public void setMenuIdMap(Map menuIdMap) {
		this.menuIdMap = menuIdMap;
	}

	/**
	 * @return the equiplist
	 */
	public static String getEquiplist() {
		return equipList;
	}

	/**
	 * @return the equipaddpage
	 */
	public static String getEquipaddpage() {
		return equipAddPage;
	}

	/**
	 * @return the equipeditpage
	 */
	public static String getEquipeditpage() {
		return equipEditPage;
	}

	/**
	 * @return the equipdetailpage
	 */
	public static String getEquipdetailpage() {
		return equipDetailPage;
	}

	public BaseCommBusiness getBaseCommBusiness() {
		return baseCommBusiness;
	}

	public void setBaseCommBusiness(BaseCommBusiness baseCommBusiness) {
		this.baseCommBusiness = baseCommBusiness;
	}

	public List getListArea() {
		return listArea;
	}

	public void setListArea(List listArea) {
		this.listArea = listArea;
	}

	/**
	 * @return the equipServiceList
	 */
	public List<EquipService> getEquipServiceList() {
		return equipServiceList;
	}

	/**
	 * @param equipServiceList the equipServiceList to set
	 */
	public void setEquipServiceList(List<EquipService> equipServiceList) {
		this.equipServiceList = equipServiceList;
	}

	/**
	 * @return the eqSerEquipBusiness
	 */
	public EquipSerEquipBusiness getEqSerEquipBusiness() {
		return eqSerEquipBusiness;
	}

	/**
	 * @param eqSerEquipBusiness the eqSerEquipBusiness to set
	 */
	public void setEqSerEquipBusiness(EquipSerEquipBusiness eqSerEquipBusiness) {
		this.eqSerEquipBusiness = eqSerEquipBusiness;
	}

	/**
	 * @return the eqSerEquipList
	 */
	public List<EquipServiceEquip> getEqSerEquipList() {
		return eqSerEquipList;
	}

	/**
	 * @param eqSerEquipList the eqSerEquipList to set
	 */
	public void setEqSerEquipList(List<EquipServiceEquip> eqSerEquipList) {
		this.eqSerEquipList = eqSerEquipList;
	}

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
}
