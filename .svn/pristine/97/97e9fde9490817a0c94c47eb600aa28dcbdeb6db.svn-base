package com.hanqian.action;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.util.ReportResult;

public class DataReportSk {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(DataReportSk.class);
	private BuildingAction buildingAction;
	private BuildingUseAction builduseAction;
	private BuildRepairAction buildRepairAction;
	private HospInfoAction hospInfoAction;
	private BusinessAction businessAction;
	private EquipmentAction equipmentAction;
	private MaterialAction maintenanceAction;

	public void setEquipmentAction(EquipmentAction equipmentAction) {
		this.equipmentAction = equipmentAction;
	}

	public void setMaintenanceAction(MaterialAction maintenanceAction) {
		this.maintenanceAction = maintenanceAction;
	}

	public void setBuildingAction(BuildingAction buildingAction) {
		this.buildingAction = buildingAction;
	}

	public void setBuilduseAction(BuildingUseAction builduseAction) {
		this.builduseAction = builduseAction;
	}

	public void setBuildRepairAction(BuildRepairAction buildRepairAction) {
		this.buildRepairAction = buildRepairAction;
	}

	public void setBusinessAction(BusinessAction businessAction) {
		this.businessAction = businessAction;
	}

	public void setHospitalinfoAction(HospInfoAction hospitalinfoAction) {
		this.hospInfoAction = hospitalinfoAction;
	}

	/**
	 * 总站处理接收的数据
	 */
	public String dataReportByType() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("application/text/xml");
		try {
			// 将数据读取出来
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(request.getInputStream()));
			Map map = (Map) ois.readObject();// 获取到要上报的所有数据
			ReportResult reportResult = new ReportResult();
			//将上报数据入库处理
			saveReports(map, reportResult);
			ois.close();
			if (reportResult != null) {
				String msg = JSONObject.fromObject(reportResult).toString();
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(msg.getBytes());
				outputStream.flush();
				outputStream.close();
			}
		} catch (Exception e) {
			logg.error("DataReportSk-->dataReportByType", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断MAP的值是否为空
	 * 
	 * @param map
	 */
	private void saveReports(Map map, ReportResult reportResult) {
		if (!map.isEmpty()) {
			Set keyset = map.keySet();
			for (Object object : keyset) {
				switch (Integer.parseInt(object.toString())) {
					case 1 :
						// 医院信息
						hospInfoAction.saveReports(map,reportResult);
						break;
					case 2 :
						// 楼宇维修
//						buildRepairAction.saveReports(map,reportResult);
						break;
					case 3 :
						// 设备维修巡检保养
//						maintenanceAction.saveReports(map,reportResult);
						break;
					case 4 :
						// 楼宇基建信息
//						buildingAction.saveReports(map, reportResult);
						break;
					case 5 :
						// 设备信息
//						equipmentAction.saveReports(map, reportResult);
						break;
					case 6 :
						// 能效
//						reportResult.setTypeid(6);
						break;             
					case 7 :
						// 区域信息
						//builduseAction.saveReports(map, reportResult);
						break;
					case 8 :
						// 业务量
//						businessAction.saveReports(map, reportResult);
						break;
				}
			}
		}
	}
}
