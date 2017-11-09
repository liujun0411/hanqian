package com.hanqian.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.HospDetailBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.business.VolumeBusiness;
import com.hanqian.pojo.DbHospDetail;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

/**
 * 
 * 描 述: 
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * @author 李江
 * @version 1.4 2012-9-4
 * @see
 */
public class HospDetailAction {	
	// 日志
	private static final Log log = LogFactory.getLog(HospDetailBusiness.class);

	private HospDetailBusiness hospDetailBusiness;// 往年详情
	
	private String resultJSON; //返回JSON格式字符串
	
	private List<DbHospDetail> detailList;
	
	private Map hospDetailMenuMap;
	
	private Map sqlmap = new HashMap();
	
	private Map seqMap = new HashMap();
	
	private Map buildareasMap = new HashMap();
	
	private Map innerareasMap = new HashMap();
	
	private Map outareasMap = new HashMap();
	
	private Map leaseareasMap = new HashMap();
	
	private Map buildamountMap = new HashMap();
	
	private Map setupareasMap = new HashMap();
	
	private Map demolishareasMap = new HashMap();
	
	private Map landareasMap = new HashMap();
	
	private Map landamountMap = new HashMap();
	
	private Map hireareasMap = new HashMap();
	
	private Map hireMap = new HashMap();
	
	private Map rentareasMap = new HashMap();
	
	private Map rentMap = new HashMap();
	
	private Map principalMap = new HashMap();
	
	private Map landdeptMap = new HashMap();
	
	private Map medicalareasMap = new HashMap();
	
	private Map plotratioMap = new HashMap();
	
	private Map afforestationMap = new HashMap();
	
	private Map bedspaceMap = new HashMap();
	
	private Map bedcheckMap = new HashMap();
	
	private Map carplaceUpMap = new HashMap();
	
	private Map carplaceDownMap = new HashMap();
	
	private Map outpatientMap = new HashMap();
	
	private Map emergencyMap = new HashMap();
	
	private Map inpatientMap = new HashMap();
	
	private Map remindmapAll = new HashMap();
	
	private MenuInterceptor menuInterceptor;
	
	public String getResultJSON() {
		return resultJSON;
	}

	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}

	/**
	 * @return the hospDetailBusiness
	 */
	public HospDetailBusiness getHospDetailBusiness() {
		return hospDetailBusiness;
	}

	/**
	 * @param hospDetailBusiness
	 *            the hospDetailBusiness to set
	 */
	public void setHospDetailBusiness(HospDetailBusiness hospDetailBusiness) {
		this.hospDetailBusiness = hospDetailBusiness;
	}

	/**
	 * @return the volumeBusiness
	 */
	public VolumeBusiness getVolumeBusiness() {
		return volumeBusiness;
	}

	/**
	 * @param volumeBusiness
	 *            the volumeBusiness to set
	 */
	public void setVolumeBusiness(VolumeBusiness volumeBusiness) {
		this.volumeBusiness = volumeBusiness;
	}

	/**
	 * @return the hospInfoBusiness
	 */
	public HospInfoBusiness getHospInfoBusiness() {
		return hospInfoBusiness;
	}

	/**
	 * @param hospInfoBusiness
	 *            the hospInfoBusiness to set
	 */
	public void setHospInfoBusiness(HospInfoBusiness hospInfoBusiness) {
		this.hospInfoBusiness = hospInfoBusiness;
	}

	/**
	 * @return the mgs
	 */
	public static HashMap<String, String> getMgs() {
		return mgs;
	}

	/**
	 * @param mgs
	 *            the mgs to set
	 */
	public static void setMgs(HashMap<String, String> mgs) {
		HospDetailAction.mgs = mgs;
	}

	/**
	 * @param sequence
	 *            the sequence to set
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	private VolumeBusiness volumeBusiness; // 业务量
	private HospInfoBusiness hospInfoBusiness; // 医院信息
	private HttpServletRequest request;
	private DbHospDetail detail; // 往年详情
	private DbHospInfo hosp;

	private Integer sequence; // 往年详情Id
	private String builYear; // 信息年份

	private Integer seqHosp; // 医院id
	private String editFlag; // 权限
	private Date startDate; // 查询时间
	private String message; // 提示信息
	private String title; // 医院名称

	private static HashMap<String, String> mgs = new HashMap<String, String>(); // 提示信息库处理中文问题

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
	 * 
	 * 验证“数据时间”是否唯一
	 * 
	 * 2015-09-06
	 * 
	 */
	public String dateTimeById()throws Exception {
		request = ServletActionContext.getRequest();
		String dateTime=request.getParameter("builYear");
			if(StringUtils.isNotEmpty(dateTime)){
				RetCode dateT = hospDetailBusiness.dateTimeById(dateTime);
				List rtList=(List)(dateT.getObj());
				if(rtList!=null){
					if(rtList.size()>0){
						resultJSON = "[{'res':'yes'}]";
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
	 * 显示修改界面
	 * 
	 * @return
	 */
	public String showByEdit() {
		request = ServletActionContext.getRequest();
		try {
			// 根据ID 获得医院详细信息
			detail = hospDetailBusiness.findDetailByIdBusiness(sequence);
			RetCode rcsum = hospDetailBusiness.findSumAreaBusiness();
			request.setAttribute("updateNo", "updateNo");
			request
					.setAttribute("hospitalarea", ((List) rcsum.getObj())
							.get(0));
			request.setAttribute("action", "hospDetailAction_editHospDetail");
			request.setAttribute("hospArea", ((List) rcsum.getObj()).get(0));
			request.setAttribute("page", request.getParameter("page"));
			log.info("hospArea="+ ((List) rcsum.getObj()).get(0)+";page="+request.getParameter("page"));
		} catch (Exception e) {
			log.error("HospDetailAction-->showByEdit", e);
			mgs.put("0", "数据加载失败!请联系管理员!");
			message = "0";
			log.error(e);
			return "showdetail";
		}

		return "edit";
	}

	/**
	 * 显示添加界面
	 * 
	 * @return
	 */
	public String showByAdd() {
		request = ServletActionContext.getRequest();
		try {
			request.setAttribute("nowDate", new Date());
			//获取页面传过来的医院ID
			Integer hospId = Integer.parseInt(request.getParameter("hospId"));
			//获取医院总面积（医院楼宇面积总和）
			RetCode rcsum = hospDetailBusiness.findSumAreaBusiness();
			request.setAttribute("action", "hospDetailAction_addHospDetail");
			request.setAttribute("hospArea", ((List) rcsum.getObj()).get(0));
			request.setAttribute("page", request.getParameter("page"));
			request.setAttribute("hospIdSeq", hospId);
			log.info("hospId="+hospId);
		} catch (Exception e) {
			log.error("HospDetailAction-->showByAdd", e);
			e.printStackTrace();
			mgs.put("0", "数据加载失败!请联系管理员!");
			message = "0";
			log.error(e);
			return "showdetail";
		}

		return "add";
	}

	/**
	 * 修改医院详细信息
	 * 
	 * @return
	 */
	public String editHospDetail() {
		request = ServletActionContext.getRequest();
		try {
			//
			detail.setInputtime(Systime.StringToDate(builYear, "yyyy"));
			if(detail.getAfforestation() != null){
				detail.setAfforestation(detail.getAfforestation()/100);
			}
			if(detail.getPlotratio() != null){
				detail.setPlotratio(detail.getPlotratio()/100);
			}
			//获取操作时间
			detail.setOpertime(new Date());
			mgs.put("2", hospDetailBusiness.updateHospDetailBusiness(detail));
			message = "2";
			String page = request.getParameter("page");
			mgs.put("page", page);
		} catch (Exception e) {
			log.error("HospDetailAction-->editHospDetail", e);
			mgs.put("1", "数据读取出错,请联系管理员!");
			message = "1";
		}
		return "showdetail";
	}

	/**
	 * 添加医院详细信息
	 * 
	 * @return
	 */
	public String addHospDetail() {
			request = ServletActionContext.getRequest();
		try {
			//获取页面的医院ID
			Integer hospIdSeq = Integer.parseInt(request.getParameter("hospIdSeq"));
			DbHospInfo dbHospInfo = new DbHospInfo();
			dbHospInfo.setSeqHosp(hospIdSeq);
			detail.setDbHospInfo(dbHospInfo);
			// 先根据逐渐把hospital对象查出来
			detail.setInputtime(Systime.StringToDate(builYear, "yyyy"));
			if(detail.getAfforestation() != null){
				detail.setAfforestation(detail.getAfforestation()/100);
			}
			if(detail.getPlotratio() != null){
				detail.setPlotratio(detail.getPlotratio()/100);
			}
			detail.setOpertime(new Date());
			// mgs.put("2", hospDetailBusiness.insertDetailBusiness(detail));
//			System.out.println("医院ID："+hospIdSeq);
			mgs.put("2",hospDetailBusiness.insertDetailBusiness(detail));
			message = "2";
			String page = request.getParameter("page");
			mgs.put("page", page);
		} catch (Exception e) {
			log.error("HospDetailAction-->addHospDetail", e);
			e.printStackTrace();
			// TODO: handle exception
			mgs.put("1", "数据读取出错,请联系管理员!");
			message = "1";
		}
		return "showdetail";
	}

	/**
	 * 获取历史详情
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String showHospDetail() {
		request = ServletActionContext.getRequest();
		//权限配置
		hospDetailMenuMap=menuInterceptor.menuIntercept("2002001");
		//若为空 ，进入登录界面
		if(hospDetailMenuMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}
		
		String page = request.getParameter("page");
		if (!SysUtil.isNull(message)) {
			message = mgs.get(message);
			page = mgs.get("page");
		}
		try {
			String roleMessage = (String) request.getSession().getAttribute(
					"roleMessage");

			int currentPage = 1;
			int pageSize = 1;
			// 获取当前页数
			if (!SysUtil.isNull(page)) {
				currentPage = Integer.parseInt(page);
			}
			// 设置条件
			DbHospDetail hospDetail = new DbHospDetail();
			DbHospInfo hospInfo = new DbHospInfo();
			//获取所有医院信息（列表）
			RetCode rcInfo = hospInfoBusiness.findAllHos();
			List hospList = (List) rcInfo.getObj();
			hospInfo = (DbHospInfo) hospList.get(0);
			request.setAttribute("hospInfo", hospInfo);
			
			title = hospInfo.getHospName();
			hospDetail.setDbHospInfo(hospInfo);
			Date startDates = null;
			Date stopDates = null;
			//获取页面传过来的查询时间
			String startDate = request.getParameter("startDate");
			
			
			if (!SysUtil.isNull(startDate)) {
				startDates = Systime.StringToDate((startDate + "-01-01"),
						"yyyy-MM-dd");
				stopDates = Systime.StringToDate((startDate + "-12-31"),
						"yyyy-MM-dd");
			}
			if(!SysUtil.isNull(startDate)){
				currentPage=1;
			}
			// 查医院详情
			RetCode hospDetailR = hospDetailBusiness.findHospDetailBusiness(
					hospDetail, startDates, stopDates, currentPage, pageSize);
			if (hospDetailR.getCode() == 0) {
				detailList = (List<DbHospDetail>) hospDetailR
						.getObj();
				if (detailList != null) {
					String year = Systime.DateToString(detailList.get(0)
							.getInputtime(), "yyyy");
					RetCode rtbu = volumeBusiness.findVoBusinSumBusiness(
							hospInfo.getSeqHosp(), year);
					if (null != rtbu.getObj()) {
						request.setAttribute("business", ((List) rtbu.getObj())
								.get(0));
					}
				}
				
				//调用任意提醒数据抽取方法
				remindHospDetailDate();
				request.setAttribute("seq", ((DbHospDetail) detailList.get(0))
						.getSeq());
				request.setAttribute("hospIdSeq", ((DbHospDetail) detailList.get(0)).getDbHospInfo().getSeqHosp());
//				System.out.println("\n\n\n"+hospInfo.getSeqHosp());
				request.setAttribute("detailList", detailList);
				request.setAttribute("page", hospDetailR.getPage());
			}
			
			request.setAttribute("hospId", hospInfo.getSeqHosp());
			return "show";
		} catch (Exception e) {
			log.error("HospDetailAction-->showHospDetail", e);
		}
		return "show";
	}
	
	
	/**
	 * 将任意提醒的数据打包成map
	 * @param statement
	 * @param
	 * @return
	 */
	public String remindHospDetailDate() {
		request = ServletActionContext.getRequest();
		//权限配置
		hospDetailMenuMap=menuInterceptor.menuIntercept("2002001");
		//若为空 ，进入登录界面
		if(hospDetailMenuMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}
		try {
			//获取医院详情sql对象放到Map对象中
			sqlmap  = hospDetailBusiness.getFindHospDetailBusinessSql();
			
			
			
			seqMap.put("seq", detailList.get(0).getSeq());
			
			//将相应的键值映射到Map（buildareasMap）中（buildareas）
			//提醒方式:1、短信提醒；2、邮件提醒；3、平台内部提醒
			buildareasMap.put("ariesPreForm", "1,2,3");
			//sql字段名称
			buildareasMap.put("sqlField", "buildareas");
			//中文字段名
			buildareasMap.put("fieldNm", "年末实际占有使用房屋建筑物面积");
			//输入类型 ：1-文本;2-数字;3-时间
			buildareasMap.put("ariesInputType", 2);
			//含有多条，或者分页数据是用来对应数据用，数据是查出来的某条数据中有标识性的字段，如seq、ID等
			buildareasMap.put("sqlFieldKey", seqMap);
			//用来判断数据是否更新的时间字段，通过sql查询出来的结果集中的时间字段等来判断
			buildareasMap.put("updateDateField", "inputtime");
			//用来判断数据是否更新的sql
			buildareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			//sql
			buildareasMap.putAll(sqlmap);
//			remindmap.put("initFunc", "testDate");
			
			//（innerareas）
			innerareasMap.put("ariesPreForm", "1,2,3");
			innerareasMap.put("sqlField", "innerareas");
			innerareasMap.put("fieldNm", "账内房屋建筑");
			innerareasMap.put("ariesInputType", 2);
			innerareasMap.put("sqlFieldKey", seqMap);
			innerareasMap.put("updateDateField", "inputtime");
			innerareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			innerareasMap.putAll(sqlmap);
			
			//（outareas）
			outareasMap.put("ariesPreForm", "1,2,3");
			outareasMap.put("sqlField", "outareas");
			outareasMap.put("fieldNm", "帐外房屋建筑物");
			outareasMap.put("ariesInputType", 2);
			outareasMap.put("sqlFieldKey", seqMap);
			outareasMap.put("updateDateField", "inputtime");
			outareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			outareasMap.putAll(sqlmap);
			
			//（leaseareas）
			leaseareasMap.put("ariesPreForm", "1,2,3");
			leaseareasMap.put("sqlField", "leaseareas");
			leaseareasMap.put("fieldNm", "租赁房屋建筑物");
			leaseareasMap.put("ariesInputType", 2);
			leaseareasMap.put("sqlFieldKey", seqMap);
			leaseareasMap.put("updateDateField", "inputtime");
			leaseareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			leaseareasMap.putAll(sqlmap);
			
			//（buildamount）
			buildamountMap.put("ariesPreForm", "1,2,3");
			buildamountMap.put("sqlField", "buildamount");
			buildamountMap.put("fieldNm", "年末房屋账面金额数");
			buildamountMap.put("ariesInputType", 2);
			buildamountMap.put("sqlFieldKey", seqMap);
			buildamountMap.put("updateDateField", "inputtime");
			buildamountMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			buildamountMap.putAll(sqlmap);
			
			//（setupareas）
			setupareasMap.put("ariesPreForm", "1,2,3");
			setupareasMap.put("sqlField", "setupareas");
			setupareasMap.put("fieldNm", "在建建筑面积");
			setupareasMap.put("ariesInputType", 2);
			setupareasMap.put("sqlFieldKey", seqMap);
			setupareasMap.put("updateDateField", "inputtime");
			setupareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			setupareasMap.putAll(sqlmap);
			
			//（demolishareas）
			demolishareasMap.put("ariesPreForm", "1,2,3");
			demolishareasMap.put("sqlField", "demolishareas");
			demolishareasMap.put("fieldNm", "待拆建筑面积");
			demolishareasMap.put("ariesInputType", 2);
			demolishareasMap.put("sqlFieldKey", seqMap);
			demolishareasMap.put("updateDateField", "inputtime");
			demolishareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			demolishareasMap.putAll(sqlmap);
			
			//（landareas）
			landareasMap.put("ariesPreForm", "1,2,3");
			landareasMap.put("sqlField", "landareas");
			landareasMap.put("fieldNm", "年末土地占用面积数");
			landareasMap.put("ariesInputType", 2);
			landareasMap.put("sqlFieldKey", seqMap);
			landareasMap.put("updateDateField", "inputtime");
			landareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			landareasMap.putAll(sqlmap);
			
			//（landamount）
			landamountMap.put("ariesPreForm", "1,2,3");
			landamountMap.put("sqlField", "landamount");
			landamountMap.put("fieldNm", "年末土地占用帐面金额数");
			landamountMap.put("ariesInputType", 2);
			landamountMap.put("sqlFieldKey", seqMap);
			landamountMap.put("updateDateField", "inputtime");
			landamountMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			landamountMap.putAll(sqlmap);
			
			//（hireareas）
			hireareasMap.put("ariesPreForm", "1,2,3");
			hireareasMap.put("sqlField", "hireareas");
			hireareasMap.put("fieldNm", "年末面积数");
			hireareasMap.put("ariesInputType", 2);
			hireareasMap.put("sqlFieldKey", seqMap);
			hireareasMap.put("updateDateField", "inputtime");
			hireareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			hireareasMap.putAll(sqlmap);
			
			//（hire）
			hireMap.put("ariesPreForm", "1,2,3");
			hireMap.put("sqlField", "hire");
			hireMap.put("fieldNm", "租金");
			hireMap.put("ariesInputType", 2);
			hireMap.put("sqlFieldKey", seqMap);
			hireMap.put("updateDateField", "inputtime");
			hireMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			hireMap.putAll(sqlmap);
			
			//（rentareas）
			rentareasMap.put("ariesPreForm", "1,2,3");
			rentareasMap.put("sqlField", "rentareas");
			rentareasMap.put("fieldNm", "年末面积数");
			rentareasMap.put("ariesInputType", 2);
			rentareasMap.put("sqlFieldKey", seqMap);
			rentareasMap.put("updateDateField", "inputtime");
			rentareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			rentareasMap.putAll(sqlmap);
			
			//（rent）
			rentMap.put("ariesPreForm", "1,2,3");
			rentMap.put("sqlField", "rent");
			rentMap.put("fieldNm", "收入");
			rentMap.put("ariesInputType", 2);
			rentMap.put("sqlFieldKey", seqMap);
			rentMap.put("updateDateField", "inputtime");
			rentMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			rentMap.putAll(sqlmap);
			
			//（principal）
			principalMap.put("ariesPreForm", "1,2,3");
			principalMap.put("sqlField", "principal");
			principalMap.put("fieldNm", "总院负责人");
			principalMap.put("ariesInputType", 1);
			principalMap.put("sqlFieldKey", seqMap);
			principalMap.put("updateDateField", "inputtime");
			principalMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			principalMap.putAll(sqlmap);
			
			//（landdept）
			landdeptMap.put("ariesPreForm", "1,2,3");
			landdeptMap.put("sqlField", "landdept");
			landdeptMap.put("fieldNm", "总院土地管理局");
			landdeptMap.put("ariesInputType", 1);
			landdeptMap.put("sqlFieldKey", seqMap);
			landdeptMap.put("updateDateField", "inputtime");
			landdeptMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			landdeptMap.putAll(sqlmap);
			
			//（medicalareas）
			medicalareasMap.put("ariesPreForm", "1,2,3");
			medicalareasMap.put("sqlField", "medicalareas");
			medicalareasMap.put("fieldNm", "医疗用房面积");
			medicalareasMap.put("ariesInputType", 1);
			medicalareasMap.put("sqlFieldKey", seqMap);
			medicalareasMap.put("updateDateField", "inputtime");
			medicalareasMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			medicalareasMap.putAll(sqlmap);
			
			//（plotratio）
			plotratioMap.put("ariesPreForm", "1,2,3");
			plotratioMap.put("sqlField", "plotratio");
			plotratioMap.put("fieldNm", "容积率");
			plotratioMap.put("ariesInputType", 2);
			plotratioMap.put("sqlFieldKey", seqMap);
			plotratioMap.put("updateDateField", "inputtime");
			plotratioMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			plotratioMap.putAll(sqlmap);
			
			//（afforestation）
			afforestationMap.put("ariesPreForm", "1,2,3");
			afforestationMap.put("sqlField", "afforestation");
			afforestationMap.put("fieldNm", "绿地率");
			afforestationMap.put("ariesInputType", 2);
			afforestationMap.put("sqlFieldKey", seqMap);
			afforestationMap.put("updateDateField", "inputtime");
			afforestationMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			afforestationMap.putAll(sqlmap);
			
			//（bedspace）
			bedspaceMap.put("ariesPreForm", "1,2,3");
			bedspaceMap.put("sqlField", "bedspace");
			bedspaceMap.put("fieldNm", "现有床位数");
			bedspaceMap.put("ariesInputType", 2);
			bedspaceMap.put("sqlFieldKey", seqMap);
			bedspaceMap.put("updateDateField", "inputtime");
			bedspaceMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			bedspaceMap.putAll(sqlmap);
			
			//（bedcheck）
			bedcheckMap.put("ariesPreForm", "1,2,3");
			bedcheckMap.put("sqlField", "bedcheck");
			bedcheckMap.put("fieldNm", "核定床位数");
			bedcheckMap.put("ariesInputType", 2);
			bedcheckMap.put("sqlFieldKey", seqMap);
			bedcheckMap.put("updateDateField", "inputtime");
			bedcheckMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			bedcheckMap.putAll(sqlmap);
			
			//（carplaceUp）
			carplaceUpMap.put("ariesPreForm", "1,2,3");
			carplaceUpMap.put("sqlField", "carplaceUp");
			carplaceUpMap.put("fieldNm", "机动车位数——地上");
			carplaceUpMap.put("ariesInputType", 2);
			carplaceUpMap.put("sqlFieldKey", seqMap);
			carplaceUpMap.put("updateDateField", "inputtime");
			carplaceUpMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			carplaceUpMap.putAll(sqlmap);
			
			//（carplaceDown）
			carplaceDownMap.put("ariesPreForm", "1,2,3");
			carplaceDownMap.put("sqlField", "carplaceDown");
			carplaceDownMap.put("fieldNm", "机动车位数——地下");
			carplaceDownMap.put("ariesInputType", 2);
			carplaceDownMap.put("sqlFieldKey", seqMap);
			carplaceDownMap.put("updateDateField", "inputtime");
			carplaceDownMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			carplaceDownMap.putAll(sqlmap);
			
			//（outpatient）
			outpatientMap.put("ariesPreForm", "1,2,3");
			outpatientMap.put("sqlField", "outpatient");
			outpatientMap.put("fieldNm", "门诊量");
			outpatientMap.put("ariesInputType", 2);
			outpatientMap.put("sqlFieldKey", seqMap);
			outpatientMap.put("updateDateField", "inputtime");
			outpatientMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			outpatientMap.putAll(sqlmap);
			
			//（emergency）
			emergencyMap.put("ariesPreForm", "1,2,3");
			emergencyMap.put("sqlField", "emergency");
			emergencyMap.put("fieldNm", "急诊量");
			emergencyMap.put("ariesInputType", 2);
			emergencyMap.put("sqlFieldKey", seqMap);
			emergencyMap.put("updateDateField", "inputtime");
			emergencyMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			emergencyMap.putAll(sqlmap);
			
			//（inpatient）
			inpatientMap.put("ariesPreForm", "1,2,3");
			inpatientMap.put("sqlField", "inpatient");
			inpatientMap.put("fieldNm", "住院量");
			inpatientMap.put("ariesInputType", 2);
			inpatientMap.put("sqlFieldKey", seqMap);
			inpatientMap.put("updateDateField", "inputtime");
			inpatientMap.put("updateFieldSql", "select inputtime from DB_HOSP_DETAIL where hosp_id = #{seqHosp}");
			inpatientMap.putAll(sqlmap);
			
			//将所有需要提醒的数据打包成map
			remindmapAll.put("buildareas", buildareasMap);
			remindmapAll.put("innerareas", innerareasMap);
			remindmapAll.put("outareas", outareasMap);
			remindmapAll.put("leaseareas", leaseareasMap);
			remindmapAll.put("buildamount", buildamountMap);
			remindmapAll.put("setupareas", setupareasMap);
			remindmapAll.put("demolishareas", demolishareasMap);
			remindmapAll.put("landareas", landareasMap);
			remindmapAll.put("landamount", landamountMap);
			remindmapAll.put("hireareas", hireareasMap);
			remindmapAll.put("hire", hireMap);
			remindmapAll.put("rentareas", rentareasMap);
			remindmapAll.put("rent", rentMap);
			remindmapAll.put("principal", principalMap);
			remindmapAll.put("landdept", landdeptMap);
			remindmapAll.put("medicalareas", medicalareasMap);
			remindmapAll.put("plotratio", plotratioMap);
			remindmapAll.put("afforestation", afforestationMap);
			remindmapAll.put("bedspace", bedspaceMap);
			remindmapAll.put("bedcheck", bedcheckMap);
			remindmapAll.put("carplaceUp", carplaceUpMap);
			remindmapAll.put("carplaceDown", carplaceDownMap);
			remindmapAll.put("outpatient", outpatientMap);
			remindmapAll.put("emergency", emergencyMap);
			remindmapAll.put("inpatient", inpatientMap);
			
			//将已经打包好的MAP转换成JSON
			JSONObject remindJson = JSONObject.fromObject(remindmapAll);
			
			request.setAttribute("remindJson", remindJson);
//			logg.debug("++++++++++++++++"+remindJson+"++++++++++++++++");
		}catch (Exception e) {
			log.error("HospDetailAction-->showHospDetail", e);
		}
		return "show";
	}
	
	/**
	 * 判断年份是否已经存在
	 * @param statement
	 * @param
	 * @return
	 */
	public String checkHospitalDetailYear() throws Exception{
		request = ServletActionContext.getRequest();
		List<String> list=new ArrayList<String>();
		String year=request.getParameter("realYear");  //当前对象
		String realYear=request.getParameter("year");  //修改后的年
		String idented=request.getParameter("idented"); //标志符（增，改）
		RetCode rc=new RetCode();
		if(StringUtils.isNotEmpty(year) && StringUtils.isNotEmpty(idented)){
			//rc=hospDetailBusiness.findHospitalDetail(year,idented);
			list=(List<String>)rc.getObj();
			if(idented.equals("in")){
				if(list.size()>0){
					resultJSON = "[{'res':'has'}]";
				}else{
					resultJSON = "[{'res':'no'}]";
				}
			}else{
				if(list!=null){
					resultJSON = "[{'res':'no'}]";
					for(int i=0;i<list.size();i++){
						if(list.get(i).toString().equals(realYear)){
							resultJSON = "[{'res':'has'}]";
							break;
						}
					}
					
				}
			}
		}
		ServletActionContext.getResponse().getWriter().print(resultJSON);
		return null;
	}

	public DbHospDetail getDetail() {
		return detail;
	}
	public void setDetail(DbHospDetail detail) {
		this.detail = detail;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBuilYear() {
		return builYear;
	}
	public void setBuilYear(String builYear) {
		this.builYear = builYear;
	}

	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Integer getSeqHosp() {
		return seqHosp;
	}

	public void setSeqHosp(Integer seqHosp) {
		this.seqHosp = seqHosp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DbHospInfo getHosp() {
		return hosp;
	}

	public void setHosp(DbHospInfo hosp) {
		this.hosp = hosp;
	}

	public Map getHospDetailMenuMap() {
		return hospDetailMenuMap;
	}

	public void setHospDetailMenuMap(Map hospDetailMenuMap) {
		this.hospDetailMenuMap = hospDetailMenuMap;
	}

	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
