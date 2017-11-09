package com.hanqian.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BuildRateBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.business.StandardRatioBusiness;
import com.hanqian.pojo.DbBuildingrate;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.PropertiesUtil;
import com.hanqian.util.ReportResult;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

public class BuildRateAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(BuildRateAction.class);
	private BuildRateBusiness rateMgr;
	private StandardRatioBusiness rationBusiness;	//区域标准比例与医院实际比例
//	private List<DbBuildingrate> listRate; // 用于保存楼宇用途比例
//	private HashMap map;    //用途为KEY,值为VALUE
//	private HashMap sequenceMap;  //用途为KEY，值为sequence
	private HttpServletRequest request;
	private String editFlag;				//权限 
	private String hospid;					//医院Id
	private String selUnits;				//医院Id集
	private HospInfoBusiness hospInfoMgr;
//	// 获得当前年份
//	SimpleDateFormat sim = new SimpleDateFormat("yyyy");
//	String currentDate = sim.format(new Date());

	
	/**
	 * 登陆是否失效
	 * false 失效 ；true 正常
	 * @return
	 */
	private boolean LoadisFail(DbUsers dbusers){		
		if (null == dbusers) {
			return false;
		}		
		return true;
	}
	
	
	public HospInfoBusiness getHospInfoMgr() {
		return hospInfoMgr;
	}


	public void setHospInfoMgr(HospInfoBusiness hospInfoMgr) {
		this.hospInfoMgr = hospInfoMgr;
	}


	/**
	 * 标准与医院实际比例情况
	 * 判断用户登录是否失效
	 * 查询数据
	 * 输出数据
	 * @return
	 */
	public String showBuildingrate(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		if (null==dbusers) {
			request.setAttribute("isfail", "yeas");
			return "login";
		}		
		
		//查询数据
		if (dbusers!=null) {
			Map hsopMap=hospInfoMgr.findHospInfoBySql(dbusers);
			selUnits = hsopMap.get("seq_hosp").toString();
		}
		RetCode rt=rationBusiness.findData(selUnits);

		//输出数据
		request.setAttribute("obj", rt.getObj());
		
		return "input";
	}
	
	/**
	 * 修改标准比例情况
	 * 修改数据
	 * 输出结果
	 * @return
	 */
	public String editBuildingrate(){
		request = ServletActionContext.getRequest();
		
		//修改数据
		String str=request.getParameter("obj");
		JSONObject obj=JSONObject.fromObject(str);
		int result=rateMgr.insertBhildRate(obj);
		
		//操作成功POST数据到分站
		if (result==1) {
			String rmsg = this.postHosp();			
		}
		
		//输出结果
		try {
			HttpServletResponse response= ServletActionContext.getResponse();
			response.getWriter().print(result);			
		} catch (Exception e) {
			logg.error("BuildRateAction-->editBuildingrate", e);
			e.printStackTrace();
		}
		return null;
	}
	
//	/**
//	 * 查询当前年份的楼宇参数设置信息
//	 * */ 
//	public String showBuildingrate2() {
//		DbBuildingrate dbRate = new DbBuildingrate();
//		dbRate.setInfoyear(currentDate);
//		RetCode rtRate = rateMgr.findBuildingrate(dbRate);
//		if (rtRate.getObj() != null) {
//			List<DbBuildingrate> list = new ArrayList<DbBuildingrate>();
//			list = (List<DbBuildingrate>) rtRate.getObj();
//			map = new HashMap();
//			sequenceMap=new HashMap();
//			for (DbBuildingrate rate : list) {
//				StringBuffer sb = new StringBuffer();
//				sb.append(rate.getFh().trim());
//				sb.append(rate.getFz());
//				map.put(rate.getYt(), sb);
//				sequenceMap.put(rate.getYt(),rate.getSequence());
//			}
//			request = ServletActionContext.getRequest();
//			String roleMessage = (String) request.getSession().getAttribute("roleMessage");
//			if(!SysUtil.isNull(editFlag)){
//	            editFlag=request.getParameter("editFlag");
//			}
//		}
//		return "input";
//	}
	
	public BuildRateBusiness getRateMgr() {
		return rateMgr;
	}


	public void setRateMgr(BuildRateBusiness rateMgr) {
		this.rateMgr = rateMgr;
	}


	/**
	 * 将数据插入或更新本地库
	 * */
	public String addBuildingParam(){
		request=ServletActionContext.getRequest();
		String message = null;
		try {
			rateMgr.insertBhrate(getParamList());
			message = "操作成功";
			this.postHosp();
		} catch (Exception e) {
			logg.error("BuildRateAction-->addBuildingParam", e);
			e.printStackTrace();
			message = "操作失败";
		} finally {
			request.setAttribute("message", message);
			this.showBuildingrate();
		}
		return "input";
	}
	
	/**
	 * 将数据post到其他分站
	 * */
	private String postHosp(){
		String result = "";
		try {
			this.ReportBuildingrate();
			result="操作成功";	
		} catch (MalformedURLException e) {
			logg.error("BuildRateAction-->postHosp", e);
			result="操作失败";
			e.printStackTrace();
		} catch (IOException e) {
			logg.error("BuildRateAction-->postHosp", e);
			result="操作失败";
			e.printStackTrace();
		}
		return result;
	}
	
	//取页面提交参数
	private List getParamList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String yt1 = request.getParameter("yt1"); // 门诊
		String yt2 = request.getParameter("yt2"); // 急诊
		String yt3 = request.getParameter("yt3"); // 病房
		String yt4 = request.getParameter("yt4"); // 医技
		String yt5 = request.getParameter("yt5"); // 后勤
		String yt6 = request.getParameter("yt6"); // 行政
		String yt7 = request.getParameter("yt7"); // 科研
		String yt8 = request.getParameter("yt8"); // 教育
		String yt9 = request.getParameter("yt9"); // 生活
		String yt10 = request.getParameter("yt10"); // 车库
		
		String sq1 = request.getParameter("sq1"); // 门诊
		String sq2 = request.getParameter("sq2"); // 急诊
		String sq3 = request.getParameter("sq3"); // 住院
		String sq4 = request.getParameter("sq4"); // 医技
		String sq5 = request.getParameter("sq5"); // 保障
		String sq6 = request.getParameter("sq6"); // 行政
		String sq7 = request.getParameter("sq7"); // 科研
		String sq8 = request.getParameter("sq8"); // 教育
		String sq9 = request.getParameter("sq9"); // 生活
		String sq10 = request.getParameter("sq10"); // 车库
		String sq11 = request.getParameter("sq11"); // 其它
		
		List li = new ArrayList();
		if (yt1 != null && yt2 != null && yt3 != null && yt4 != null
				&& yt5 != null && yt6 != null && yt7 != null && yt8 != null
				&& yt9 != null && yt10 != null) {

			Double yt11 = 100-Double.parseDouble(yt1.substring(2))
					-Double.parseDouble(yt2.substring(2))-Double.parseDouble(yt3.substring(2))
					-Double.parseDouble(yt4.substring(2))-Double.parseDouble(yt5.substring(2))
					-Double.parseDouble(yt6.substring(2))-Double.parseDouble(yt7.substring(2))
					-Double.parseDouble(yt8.substring(2))-Double.parseDouble(yt9.substring(2))
					-Double.parseDouble(yt10.substring(2));

			li.add(sq1+"门诊"+yt1);
			li.add(sq2+"急诊"+yt2);
			li.add(sq3+"住院"+yt3);
			li.add(sq4+"医技"+yt4);
			li.add(sq5+"保障"+yt5);
			li.add(sq6+"行政"+yt6);
			li.add(sq7+"科研"+yt7);
			li.add(sq8+"教育"+yt8);
			li.add(sq9+"生活"+yt9);
			li.add(sq10+"车库"+yt10);
			if(yt11>0||!SysUtil.isNull(sq11))
				li.add(sq11+"其它<="+yt11);
		}
			return li;
	}
	
	/**
	 * 总站 获取楼宇参数设置
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * */
	private void ReportBuildingrate() throws MalformedURLException, IOException{
		List listRate = new ArrayList();
		//设置需要POST的数据.
		Enumeration<Object> hospUrl = PropertiesUtil.pps.keys();
		while(hospUrl.hasMoreElements()){
			String keys = (String) hospUrl.nextElement();
			if(!keys.equals("sk")){
		RetCode rcBuildingrate = rateMgr.findBuildingrate(new DbBuildingrate());
		if (rcBuildingrate.getObj() != null) {
			listRate.add(rcBuildingrate.getObj());
		}              
		if (listRate.size() > 0) {// 如果reportMap不等于空就访问分站
					String postUrl = PropertiesUtil.pps.getProperty(keys)
					+ "/rate_dataReportByType";
					HttpURLConnection con = (HttpURLConnection) new URL(postUrl).openConnection();// 得到需要POST到的地址并且设置以下参数
					con.setDoOutput(true);// 参数放在正文内
					con.setRequestMethod("POST");// 发送方式
					con.setUseCaches(false);// 是否使用缓存
					con.setInstanceFollowRedirects(true);
					con.setRequestProperty("Content-Type", "application/octet-stream");// 设定传送的内容类型是可序列化的java对象
					con.connect();// 连接服务器
					ObjectOutputStream oos = new ObjectOutputStream( // 往目标servlet中提供参数
							new BufferedOutputStream(con.getOutputStream()));
					if (listRate != null) {
						oos.writeObject(listRate);// 将得到的集合写入对象流
					}
					listRate.clear();
					oos.flush();// 强制将输出流缓冲区的数据送出
					oos.close();// 关闭写入流
					BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String testSTr = null;
					StringBuffer buffer = new StringBuffer();
					while ((testSTr = rd.readLine()) != null) {
						buffer.append(testSTr);
					}
					rd.close();// 关闭输出流
					con.disconnect();// 断开连接
				}
			}
		}
	}
	
	
	/**
	 * 分站处理接收的数据
	 */
	public String dataReportByType() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("application/text/xml");
		try {
			// 将数据读取出来
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(request.getInputStream()));
			List list = (List) ois.readObject();// 获取到要上报的所有数据
			ReportResult reportResult = new ReportResult();
			addReportBuildingrate(list);// 判断list里的值是否为空.
			ois.close();
			if (reportResult != null) {
				String msg = JSONObject.fromObject(reportResult).toString();
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(msg.getBytes());
				outputStream.flush();
				outputStream.close();
			}
		} catch (Exception e) {
			logg.error("BuildRateAction-->dataReportByType", e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断MAP的值是否为空
	 * 
	 * @param map
	 */
	private void addReportBuildingrate(List list) {
		/**
		 * 判断post过来的集合
		 * */
		if (!list.isEmpty()) {
			List<DbBuildingrate> listBuildingrate = (List<DbBuildingrate>) list.get(0);
			try {
				rateMgr.insertReportBuilding(listBuildingrate);
			} catch (Exception e) {
				logg.error("BuildRateAction-->addReportBuildingrate", e);
				e.printStackTrace();
			}

		}
	}
	
	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
//	public HashMap getMap() {
//		return map;
//	}
//
//	public void setMap(HashMap map) {
//		this.map = map;
//	}
//
//	public List<DbBuildingrate> getListRate() {
//		return listRate;
//	}
//
//	public void setListRate(List<DbBuildingrate> listRate) {
//		this.listRate = listRate;
//	}

	public String getHospid() {
		return hospid;
	}



	public void setHospid(String hospid) {
		this.hospid = hospid;
	}



//	public void setRateMgr(BuildRateMgr rateMgr) {
//		this.rateMgr = rateMgr;
//	}



	public String getSelUnits() {
		return selUnits;
	}


	public void setSelUnits(String selUnits) {
		this.selUnits = selUnits;
	}


	public void setRationBusiness(StandardRatioBusiness rationBusiness) {
		this.rationBusiness = rationBusiness;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


//	public HospInfoMgr getHospInfoMgr() {
//		return hospInfoMgr;
//	}
//
//
//	public void setHospInfoMgr(HospInfoMgr hospInfoMgr) {
//		this.hospInfoMgr = hospInfoMgr;
//	}


//	public BuildRateMgr getRateMgr() {
//		return rateMgr;
//	}


	public StandardRatioBusiness getRationBusiness() {
		return rationBusiness;
	}

 
}