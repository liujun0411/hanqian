package com.hanqian.action;
/**
 * @author zdl
 */
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.ReportDetailBusiness;
import com.hanqian.business.ReportRateBusiness;
import com.hanqian.pojo.DbReportDetail;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.pojo.DbReportType;
import com.hanqian.util.PropertiesUtil;
import com.hanqian.util.ReportResult;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

public class DataReportHosp {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(DataReportHosp.class);
	private ReportRateBusiness reportRateMgr;
	private ReportDetailBusiness reportDetailMgr;
	private HospInfoAction hospitalinfoAction;
	private BusinessAction businessAction;


	private BuildRepairAction buildRepairAction;
	private MaterialAction maintenanceAction;
	private BuildingAction buildAction;
	private EquipmentAction equipmentAction;
	private BuildingStoreyAction buildingStoreyAction;
	/**
	 * 数据上报方法
	 */
	public void dataReportByType() {
		RetCode reportRateCode = reportRateMgr
				.findReportRate(new DbReportRate());// 获取到所有数据上报类型
		if (reportRateCode.getCode() == 0) {// 判断是否查找到了记录
			List<DbReportRate> dbReportRates = (List<DbReportRate>) reportRateCode
					.getObj();
			HashMap reportMap = new HashMap(0);
			Date date = new Date();
			try {
				// 循环上报类型的集合
				for (DbReportRate reportRate : dbReportRates) {
					// 当天日期和最后上报日期相差天数
					long lastDate = 0;
					if (reportRate.getReportdate() != null) {
						lastDate = Math.abs((date.getTime() - reportRate
								.getReportdate().getTime())
								/ (3600 * 24 * 1000));
					}
					int gettime = getTime(reportRate.getRate().intValue()); // 上报周期的天数
					// 获得需要上报的数据
					getMapObj(reportMap, reportRate, (int) lastDate, gettime);

					// 提交数据，取返回结果，入上报日志
					if(reportMap.size()>0){
					resultPost(reportMap, date);
					}
				}
			} catch (Exception e) {
				logg.error("DataReportHosp-->dataReportByType", e);
				e.printStackTrace();
			}
		}
	}

	public ReportDetailBusiness getReportDetailMgr() {
		return reportDetailMgr;
	}

	public void setReportDetailMgr(ReportDetailBusiness reportDetailMgr) {
		this.reportDetailMgr = reportDetailMgr;
	}

	/**
	 * 获取需要上报的集合
	 * 
	 * @param reportRate
	 * @param date
	 * 
	 * @param gettime
	 * @return
	 */
	private void getMapObj(HashMap reportMap, DbReportRate reportRate,
			int date, int gettime) {

		if ((date - gettime) >= 0) {// 根据时间差判断是否需要数据上报
			switch (reportRate.getDbReportType().getTypeId().intValue()) {// 通过数据上报类型来判断需要上报对象的集合
				case 1 :
					// 医院基本信息

					hospitalinfoAction.getReportInfo(reportMap, reportRate);
					break;
				case 2 :
					// 楼宇维修

					buildRepairAction.getBuildrepair(reportMap, reportRate);
					break;
				case 3 :
//					 设备维修巡检保养
					maintenanceAction.getMaintenance(reportMap, reportRate);
					break;
				case 4 :
					// 楼宇信息
					buildAction.getBuilding(reportMap, reportRate);
					break;
				case 5 :
					// 设备信息
					equipmentAction.getEquipment(reportMap, reportRate);
					break;
				case 6 :
					// 医院所有能效
					break;
				case 7 :
					// 楼宇区域信息
					buildingStoreyAction.getBuilduse(reportMap, reportRate);
					break;
				case 8 :
					// 业务量
					businessAction.getBusiness(reportMap, reportRate);
					break;

				case 9 :
					// 特征区域能效
					
					break;
				default :
					break;
			}
		}
	}

	/**
	 * 获取天数的辅助方法 注意此处是: 1.一天 2.七天 3.一个月 4.一个季度 5.一年
	 * 
	 * @param reportRate
	 * @return
	 */
	private int getTime(int rate) {
		int rateDate = 0;
		switch (rate) {// 根据传递进来的频率值判断上报周期
			case 1 :
				rateDate = 1;
				break;
			case 2 :
				rateDate = 7;
				break;
			case 3 :
				rateDate = 30;
				break;
			case 4 :
				rateDate = 120;
				break;
			case 5 :
				rateDate = 365;
				break;
			default :
				break;

		}
		return rateDate;
	}
	/**
	 * 临时数据上报
	 * 
	 * @return
	 */
	public String dataReportByOne() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String staticReportTypeId = request.getParameter("staticReportTypeId");// 获得上报类型ID
		String typeid = request.getParameter("ReportTypeId");// 获得上报状态是静态、动态或其他
		try {
			// 获取reporttypeid
			List<DbReportRate> list = getReportTypeIdAndEndTime(request,
					staticReportTypeId, typeid);
			if (list == null || list.size() <= 0) {
				request.setAttribute("message", "数据已经是最新的了");
			} else {
				HashMap reportMap = null;
				Date date = new Date();
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					// 设置查询数据
					DbReportRate reportRate = new DbReportRate();
					if (m.get("reportdate") != null) {
						reportRate.setReportdate(Systime.StringToDate(m.get(
								"reportdate").toString(), "yyyy-MM-dd"));
					}
					DbReportType reportTypedate = new DbReportType();
					reportTypedate.setTypeId(Integer.parseInt(m.get("type_id")
							.toString()));
					reportRate.setDbReportType(reportTypedate);

					// 获取到要上报的数据
					reportMap = new HashMap(0);
					getMapObj(reportMap, reportRate, 0, 0);
					// 提交一次数据，份额到返回结果，并且处理上报日志
					resultPost(reportMap, date);
				}
				request.setAttribute("message", "操作成功");
			}
		} catch (Exception e) {
			request.setAttribute("message", "操作失败");
			logg.error("DataReportHosp-->dataReportByOne", e);
			e.printStackTrace();

		}
		return "show";
	}

	private void resultPost(HashMap reportMap, Date date) throws IOException,
			MalformedURLException, ProtocolException {
		if (reportMap.size() > 0) {// 如果reportMap不等于空就访问总站
			Date startLogDate = new Date();// 获取数据开始上报时间
			long startdate = startLogDate.getTime();
			String skUrl = PropertiesUtil.pps.getProperty("sk")
					+ "/DataReportSk_dataReportByType";
			HttpURLConnection con = (HttpURLConnection) new URL(skUrl)
					.openConnection();// 得到需要POST到的地址并且设置以下参数
			con.setDoOutput(true);// 参数放在正文内
			con.setRequestMethod("POST");// 发送方式
			con.setUseCaches(false);// 是否使用缓存
			con.setInstanceFollowRedirects(true);
			con.setRequestProperty("Content-Type", "application/octet-stream");// 设定传送的内容类型是可序列化的java对象
			con.connect();// 连接服务器
			ObjectOutputStream oos = new ObjectOutputStream(// 往目标servlet中提供参数
					new BufferedOutputStream(con.getOutputStream()));
			if (reportMap != null) {
				oos.writeObject(reportMap);// 将得到的集合写入对象流
			}
			oos.flush();// 强制将输出流缓冲区的数据送出
			oos.close();// 关闭写入流
			// 此处等待返回................................................
			// 处理请求结果,将返回信息入库到上报日志表中
			Date endLogDate = new Date();
			long enddate = endLogDate.getTime();
			BufferedReader rd = new BufferedReader(new InputStreamReader(con
					.getInputStream()));
			String testSTr = null;
			StringBuffer buffer = new StringBuffer();
			while ((testSTr = rd.readLine()) != null) {
				buffer.append(testSTr);
			}
			rd.close();// 关闭输出流
			if (buffer != null) {
				ReportResult reportResult = (ReportResult) JSONObject.toBean(
						JSONObject.fromObject(buffer.toString()),
						ReportResult.class);
				// 创建上报日志并且设置值
				DbReportDetail reportDetail = new DbReportDetail();
				reportDetail.setReporttime(startLogDate);
				reportDetail.setEndtime(endLogDate);
				reportDetail.setLh(new BigDecimal(enddate - startdate));
				reportDetail.setStatus(Short.parseShort(reportResult.getStatus() + ""));
				DbReportType dbReportType = new DbReportType();
				dbReportType.setTypeId(Integer.parseInt(reportResult
						.getTypeid()
						+ ""));
				reportDetail.setDbReportType(dbReportType);
				reportDetail.setDatadescr(reportResult.getDatadescr());
				reportDetail.setStatusdescr(reportResult.getStatusdescr());
				// 此处添加日志
				reportDetailMgr.insertReportDetail(reportDetail);
				/*// 修改上报时间
				reportRateMgr.updateReportRate(date, dbReportType.getTypeId());*/
			}
			// 结束
			con.disconnect();// 断开连接
		}
	}
	/**
	 * 获得上报类型 ID AND ENDTIME
	 * 
	 * @return
	 */
	private List<DbReportRate> getReportTypeIdAndEndTime(
			HttpServletRequest request, String staticReportTypeId, String typeid) {

		DbReportRate dbreportRate = null;// 创建一个上报设置对象
		if (typeid.equals("all")) {// 判断数据属于那种状态，然后再根据上报类型
			dbreportRate = new DbReportRate();// 此种状态所有数据
		} else if (typeid.equals("static")) {// 静态
			dbreportRate = new DbReportRate();
			DbReportType dbReportType = new DbReportType();
			if (!SysUtil.isNull(staticReportTypeId)) {
				dbReportType.setTypeId(Integer.parseInt(staticReportTypeId));
			} else {
				dbReportType.setStatus((short)1);
			}
			dbreportRate.setDbReportType(dbReportType);
		} else if (typeid.equals("trends")) {// 动态
			dbreportRate = new DbReportRate();
			DbReportType dbReportType = new DbReportType();
			if (!SysUtil.isNull(staticReportTypeId)) {
				dbReportType.setTypeId(Integer.parseInt(staticReportTypeId));
			} else {
				dbReportType.setStatus((short)2);
			}
			dbreportRate.setDbReportType(dbReportType);
		} else {// 其他
			// ....
		}
		RetCode rateCode = reportRateMgr.findReportRatesql(dbreportRate);// 获取到上报类型ID，最后上报时间
		if (rateCode.getCode() == 0) {// 判断是否查找到了记录
			List<DbReportRate> list = (List<DbReportRate>) rateCode.getObj();
			return list;
		}
		return null;
	}

//	public void setReportRateMgr(ReportRateMgr reportRateMgr) {
//		this.reportRateMgr = reportRateMgr;
//	}

//	public void setReportDetailMgr(ReportDetailMgr reportDetailMgr) {
//		this.reportDetailMgr = reportDetailMgr;
//	}
	public ReportRateBusiness getReportRateMgr() {
		return reportRateMgr;
	}

	public void setReportRateMgr(ReportRateBusiness reportRateMgr) {
		this.reportRateMgr = reportRateMgr;
	}

	public void setHospitalinfoAction(HospInfoAction hospitalinfoAction) {
		this.hospitalinfoAction = hospitalinfoAction;
	}

	public void setBusinessAction(BusinessAction businessAction) {
		this.businessAction = businessAction;
	}

	public void setBuildRepairAction(BuildRepairAction buildRepairAction) {
		this.buildRepairAction = buildRepairAction;
	}

	public void setMaintenanceAction(MaterialAction maintenanceAction) {
		this.maintenanceAction = maintenanceAction;
	}

	public void setBuildAction(BuildingAction buildAction) {
		this.buildAction = buildAction;
	}

	public void setEquipmentAction(EquipmentAction equipmentAction) {
		this.equipmentAction = equipmentAction;
	}

	/**
	 * @param buildingStoreyAction the buildingStoreyAction to set
	 */
	public void setBuildingStoreyAction(BuildingStoreyAction buildingStoreyAction) {
		this.buildingStoreyAction = buildingStoreyAction;
	}


}