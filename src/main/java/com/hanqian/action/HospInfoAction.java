package com.hanqian.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java_cup.Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BaseCommBusiness;
import com.hanqian.business.HospDetailBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbHospDetail;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.ConfigUtil;
import com.hanqian.util.ReportResult;
import com.hanqian.util.RequestIntrospectHelper;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;
import com.hanqian.util.UploadFile;
import com.mysql.jdbc.Clob;

/**
 * 描 述: 医院信息的action 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-9-4
 * @see
 */
public class HospInfoAction {

	// 日志
	private static final Log log = LogFactory.getLog(HospInfoAction.class);

	// 医院基本信息
	private HospInfoBusiness hospInfoBusiness;
	// 医院相关外键信息
	private BaseCommBusiness baseCommBusiness;
	//	
	private HospDetailBusiness hospDetailBusiness;
	// 权限
	private MenuInterceptor menuInterceptor;

	// 医院实体类
	private DbHospInfo hospInfo;
	private HttpServletRequest request;
	private HttpServletResponse res;
	private String editFlag = "2"; // 权限
	private String message; // 提示信息
	private Integer hospId = 0; // 医院ID
	private File fullView; // 全貌图
	private File images1; // 宣传图
	private File images2; // 宣传图
	private File images3; // 宣传图
	private File images4; // 宣传图

	private String filefullView = "manager"; // 全貌图
	private Map menuMap;
	
	
    public void showHospInfoPic(){
    	res = ServletActionContext.getResponse();
    	request = ServletActionContext.getRequest();
		OutputStream os = null;
		InputStream is = null;
    	try{
    		String orcalUrl = request.getParameter("urls");
    		//【Win本地测试时需要的获取的目录】
    		//String	picUrl =new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/HostInfoPic/";
    		//Linux系统获取Tomcat目录
    		String	picUrl = System.getProperty("catalina.base")+"/Logistics_UploadPic/HostInfoPic/";
			File downFolder = new File(picUrl + File.separator + orcalUrl);
			os = res.getOutputStream();// 写
			is = new FileInputStream(downFolder);//读
			
			byte[] buffer = new byte[1024];
			int l = 0;
			while ((l = is.read(buffer)) > 0) {
				os.write(buffer, 0, l);
			}
    	}catch(Exception e){
			log.debug("图片显示出错:"+e);
    	}
    }
	
	
	/**
	 * 
	 * @param 查询医院基本信息
	 * @param @return
	 * @return @return
	 */
	public String showHospInfo() {
		request = ServletActionContext.getRequest();
		// 判断Session 是否失效
		DbUsers user = (DbUsers) request.getSession().getAttribute("users");
		if (null == user) {
			return "login";
		}
		// 判断权限
		menuMap = menuInterceptor.menuIntercept("2001001");
		// 若为空 ，进入登录界面
		if (menuMap == null) {
			MenuInterceptor.toLoginjsp();
			return null;
		}
		if (request.getParameter("showMsg")!=null) {
			message="";
		}
		try {
			DbHospInfo infos = new DbHospInfo();
			// 查询医院基本信息
			RetCode hospinfo = hospInfoBusiness.findHospInfo(infos);
			if (hospinfo.getObj() != null) {
				// 医院列表
				List<DbHospInfo> infolist = (List<DbHospInfo>) hospinfo
						.getObj();
				if (null != infolist) {
					hospInfo = infolist.get(0);
					// //判断是否有图片
					// if (!SysUtil.isNull(hospInfo.getFullView())) {
					// filefullView = filefullView.replace("manager\\", "");
					// filefullView = filefullView.replace("\\", "/");
					// hospInfo.setFullView(filefullView+hospInfo.getFullView());
					// }
					request.setAttribute("hospInfo", hospInfo);
					try {
						String	picUrl = new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()
								+"Logistics_UploadPic/HostInfoPic/";
						File downFolder = new File(picUrl);
						request.setAttribute("picUrl", downFolder);
					} catch (MalformedURLException e) {}
				}
			} else {
				message += hospinfo.getDesc();
			}
		} catch (Exception e) {
			log.error("HospInfoAction-->showHospInfo", e);
			// TODO Auto-generated catch block
			log.debug(e.getMessage() + e.toString());
			e.printStackTrace();
			message += "异常出错,请咨询系统管理员!";
		}
		/**
		 * add start 2013.4.12 by zhangdiannan
		 * 加上img 随机数 解决缓存问题
		 */
		double randomNum=Math.random();
		request.setAttribute("randomNum", randomNum);
		/**
		 * add end 2013.4.12 by zhangdiannan
		 */
		return "show";
	}

	/**
	 * 显示修改界面
	 * 
	 * @return
	 */
	public String showHospInfoEdit() {
		request = ServletActionContext.getRequest();
		message = "数据加载出错，请联系管理员!";
		editFlag = request.getParameter("editFlag");
		try {
			// 根据Id查询出医院信息，传到修改页面显示
			hospInfo = hospInfoBusiness.findHospInfoForId(1);
			// 查询医院级别
			request.setAttribute("levels", baseCommBusiness
					.findHospRelated(SysUtil.BASE_COMM_HOSP_LEVELS));
			// 医院类型
			request.setAttribute("hospType", baseCommBusiness
					.findHospRelated(SysUtil.BASE_COMM_HOSP_TYPE));
			// 医院行政区域
			request.setAttribute("distInfo", baseCommBusiness
					.findHospRelated(SysUtil.BASE_COMM_HOSP_DIST));
		} catch (Exception e) {
			log.error("HospInfoAction-->showHospInfoEdit", e);
			// TODO: handle exception
			message = request.getParameter("message");
			return "show";
		}
		return "edit";
	}

	/**
	 * 修改医院
	 * 
	 * @return
	 */
	public String updateHospInfo() {
		request = ServletActionContext.getRequest();
		message = "修改失败!请联系系统管理员！";
		SimpleDateFormat sd = new SimpleDateFormat("yyyy");
		try {
			// 医院基本信息
			DbHospInfo hospInfoss = new DbHospInfo();
			int seqHosp = Integer.parseInt(request.getParameter("seqHosp"));
			String buildtime = request.getParameter("buildtime");
			hospInfoss.setSeqHosp(seqHosp);
			hospInfoss.setBuildtime(sd.parse(buildtime));
			// 医院级别
			DbBaseComm hospLevels = new DbBaseComm();
			hospLevels.setSeq(SysUtil.toInt(request.getParameter("hospLevel")));
			hospLevels.setContent1(request.getParameter("levelName"));
			// 医院类型
			DbBaseComm hospType = new DbBaseComm();
			hospType.setSeq(SysUtil.toInt(request.getParameter("hosptype")));

			// 区域
			DbBaseComm distInfo = new DbBaseComm();
			distInfo.setSeq(SysUtil.toInt(request.getParameter("distinfo")));
			distInfo.setContent1(request.getParameter("distInfoName"));
			
//			Clob hospInfoClob;
//			hospInfoClob.getSubString(hospInfoss.getHospInfo(), hospInfoClob.length());
			
			// 医院编码
			// DbBaseComm hospCode = new DbBaseComm();
			// hospCode.setSeq(SysUtil.toInt(request.getParameter("hospCode")));
			// 加入查询参数中
			hospInfoss.setDbBaseCommByHosplevel(hospLevels);
			hospInfoss.setDbBaseCommByHospdist(distInfo);
			hospInfoss.setDbBaseCommByKind(hospType);
			hospInfoss.setOpertime(new Date());
			
			// hospInfoss.setDbBaseCommByHospCode(hospCode);
			// 转换成表单提交方式（可以得到页面上的实体类字段相同的字段的值）
			RequestIntrospectHelper.introspect(hospInfoss, request);
//			System.out.println("得到的图片文件" + hospInfoss.getFullView() + "?1:"
//					+ hospInfoss.getImages1() + "?wewe:" + fullView);
			hospInfoss.setImages1(" ");
			hospInfoss.setImages2(" ");
			hospInfoss.setImages3(" ");
			hospInfoss.setImages4(" ");
			
			// 修改的方法
			boolean isTrue = hospInfoBusiness.updateHospInfo(hospInfoss);
			if (isTrue) {
				message = "修改成功!";
				// 更新图片
			} else {
				message = "修改失败，请重新登录！";
			}
			
			String url =new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()
					+"Logistics_UploadPic/HostInfoPic/images/hospImages"; // 获取工程所在目录路径

			
			// 更新全貌图
			if (fullView != null && hospInfoss.getFullView() != null) {
				if (!uploadFile(fullView, url, hospInfoss.getFullView())) {
					message += "但图片上传失败!";
				}
			}
//			// 更新宣传图1
//			if (images1 != null && hospInfoss.getImages1() != null) {
//				if (!uploadFile(images1, request.getRealPath("/")
//						+ filefullView, hospInfoss.getImages1())) {
//					message += "但图片上传失败!";
//				}
//			}
			// 更新宣传图2
//			if (images2 != null && hospInfoss.getImages2() != null) {
//				if (!uploadFile(images2, request.getRealPath("/")
//						+ filefullView, hospInfoss.getImages2())) {
//					message += "但图片上传失败!";
//				}
//			}
//			// 更新宣传图3
//			if (images3 != null && hospInfoss.getImages3() != null) {
//				if (!uploadFile(images3, request.getRealPath("/")
//						+ filefullView, hospInfoss.getImages3())) {
//					message += "但图片上传失败!";
//				}
//			}
//			// 更新宣传图4
//			if (images4 != null && hospInfoss.getImages4() != null) {
//				if (!uploadFile(images4, request.getRealPath("/")
//						+ filefullView, hospInfoss.getImages4())) {
//					message += "但图片上传失败!";
//				}
//			}
		} catch (Exception e) {
			log.error("HospInfoAction-->updateHospInfo", e);
			message = "操作失败，请联系管理员！";
			e.printStackTrace();
		}
		/**
		 * add start 2013-4-11 by zhangdiannan
		 * 图片缓存问题 添加随机数
		 */
		request.setAttribute("randomNum", Math.random());
		
		/**
		 * add end 2013-4-11 by zhangdiannan
		 */
		// 转到查询的页面
		showHospInfo();
		editFlag = request.getParameter("editFlag");
		request.setAttribute("message", message);
		return "show";
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件
	 * @param filePath
	 *            保存位置
	 * @param fileName
	 *            文件名
	 * @return
	 */
	private boolean uploadFile(File file, String filePath, String fileName) {
//		System.out.println("图：" + filePath);
		UploadFile upload = new UploadFile();
		try {
			upload.saveFile(file, filePath, fileName);
			return true;
		} catch (Exception e) {
			log.error("HospInfoAction-->uploadFile", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void getReportInfo(HashMap reportMap, DbReportRate reportRate) {
		RetCode retCode = hospInfoBusiness.findHospInfo(new DbHospInfo());
		if (retCode.getObj() != null) {
			List<DbHospInfo> hospitalinfos = (List<DbHospInfo>) retCode
					.getObj();
			// 取往年详情
			DbHospDetail dbHospitaldetail = new DbHospDetail();
			dbHospitaldetail.setDbHospInfo(hospitalinfos.get(0));
			RetCode detailRetCode = hospDetailBusiness.findHospDetailBusiness(
					dbHospitaldetail, reportRate.getReportdate()); //
			if (detailRetCode.getObj() != null) {
				hospitalinfos.get(0).setDbHospDetails(
						new HashSet<DbHospDetail>(
								(List<DbHospDetail>) detailRetCode.getObj()));
			}
			reportMap.put(reportRate.getDbReportType().getTypeId() + "",
					(List<DbHospInfo>) retCode.getObj());
		}
	}

	/**
	 * 数据上报医院信息入口操作
	 * 
	 * @param map
	 * @param reportResult
	 * @param succed
	 * @param error
	 */
	public void saveReports(Map map, ReportResult reportResult) {
		reportResult.setTypeid(1);
		reportResult.setDatadescr("医院基本信息");
		String statusdescr = "";

		List<DbHospInfo> hospList = (List<DbHospInfo>) map.get("1");
		DbHospInfo hospInfo = hospList.get(0);
		// 将医院基本信息入库
		try {
			hospInfoBusiness.insertOrUpHospInfo(hospInfo);
			Set<DbHospDetail> hospDetails = (Set<DbHospDetail>) hospList.get(0)
					.getDbHospDetails();
			for (DbHospDetail detail : hospDetails) {// 对它的往年详情逐个添加
				try {
					if (hospDetailBusiness.findYearThereBusiness(detail)) {
						// 当年详情存在修改
						hospDetailBusiness.updateHospDetailBusiness(detail);// .updateReportHospitaldetail(detail);
					} else {
						// 否则插入
						hospDetailBusiness.insertDetailBusiness(detail);
					}
					statusdescr += "医院历史详情(年份:"
							+ Systime.DateToString(detail.getInputtime(),
									"yyyy") + "),上报成功。";
				} catch (Exception e) {
					log.error("HospInfoAction-->saveReports", e);
					statusdescr += "医院历史详情(年份:"
							+ Systime.DateToString(detail.getInputtime(),
									"yyyy") + "),上报失败。失败原因：" + e.getMessage();
					continue;
				}
			}
			statusdescr += "医院基本信息,上报成功。";
			reportResult.setStatus(1);
		} catch (Exception e) {
			log.error("HospInfoAction-->saveReports", e);
			statusdescr += "医院基本信息,上报失败。失败原因：" + e.getMessage();
			reportResult.setStatus(2);
		}

		reportResult.setStatusdescr(statusdescr);
	}

	/**
	 * 
	 */
	public void judgeCurrentHosp() throws Exception{
		HttpServletResponse response;
    	PrintWriter out = null;
    	try{
			request = ServletActionContext.getRequest();
			String currentHospCode = ConfigCST.CST_CURRENT_HOSP_CODE;
//			String currentHospCode = ConfigUtil.getConfig("config.properties", "currentHospCode");
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			JSONObject levelJson = new JSONObject();
			levelJson.accumulate("currentHospCode",currentHospCode);
			out.print(levelJson.toString());
    	}catch (Exception e) {
    		log.error("HospInfoAction-->judgeCurrentHosp", e);
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * get/set方法
	 * 
	 * @return
	 */
	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getHospId() {
		return hospId;
	}

	public void setHospId(Integer hospId) {
		this.hospId = hospId;
	}

	public HospInfoBusiness getHospInfoBusiness() {
		return hospInfoBusiness;
	}

	public void setHospInfoBusiness(HospInfoBusiness hospInfoBusiness) {
		this.hospInfoBusiness = hospInfoBusiness;
	}

	public DbHospInfo getHospInfo() {
		return hospInfo;
	}

	public void setHospInfo(DbHospInfo hospInfo) {
		this.hospInfo = hospInfo;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public BaseCommBusiness getBaseCommBusiness() {
		return baseCommBusiness;
	}

	public void setBaseCommBusiness(BaseCommBusiness baseCommBusiness) {
		this.baseCommBusiness = baseCommBusiness;
	}

	public File getImages1() {
		return images1;
	}

	public void setImages1(File images1) {
		this.images1 = images1;
	}

	public File getImages2() {
		return images2;
	}

	public void setImages2(File images2) {
		this.images2 = images2;
	}

	public File getImages3() {
		return images3;
	}

	public void setImages3(File images3) {
		this.images3 = images3;
	}

	public File getImages4() {
		return images4;
	}

	public void setImages4(File images4) {
		this.images4 = images4;
	}

	public String getFilefullView() {
		return filefullView;
	}

	public void setFilefullView(String filefullView) {
		this.filefullView = filefullView;
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
	
	public File getFullView() {
		return fullView;
	}

	public void setFullView(File fullView) {
		this.fullView = fullView;
	}


	public HttpServletResponse getRes() {
		return res;
	}


	public void setRes(HttpServletResponse res) {
		this.res = res;
	}
	
}
