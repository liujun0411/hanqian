package com.hanqian.sk;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.DbinfoBuiness;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.ConfigUtil;
import com.hanqian.util.DateUtil;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author cc
 * @version 1.4 2014-10-20
 * @see
 */
public class ManagementAction extends ActionSupport {
	/**
	 * 申康信息的service层
	 */
	private DbinfoBuiness dbinfoBuiness;

	private static final Logger log = Logger.getLogger(ManagementAction.class);

	/**
	 * 上传的附件
	 */

	private File file;
	private String SendCode;
	private String SendName;
	private String ReceiveCode;
	private String ReceiveName;
	private String Title;
	private String Content;
	private String Attach;
	/**
	 * 用户名 用户简称 用户标题 用户内容 信息的文件路径 时间字段
	 */
	private String Otime;
	private String messageType;
	private String userId;
	private String fileUrl;
	private String resultJSON;
	/**
	 * 内容
	 */
	private Map<String, Object> contentMap;

	private HttpServletRequest request;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getTitle() {
		return Title;
	}

	public String getSendCode() {
		return SendCode;
	}

	public void setSendCode(String sendCode) {
		SendCode = sendCode;
	}

	public String getSendName() {
		return SendName;
	}

	public void setSendName(String sendName) {
		SendName = sendName;
	}

	public String getReceiveCode() {
		return ReceiveCode;
	}

	public void setReceiveCode(String receiveCode) {
		ReceiveCode = receiveCode;
	}

	public String getReceiveName() {
		return ReceiveName;
	}

	public void setReceiveName(String receiveName) {
		ReceiveName = receiveName;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getAttach() {
		return Attach;
	}

	public void setAttach(String attach) {
		Attach = attach;
	}

	public String getOtime() {
		return Otime;
	}

	public void setOtime(String otime) {
		Otime = otime;
	}

	/**
	 * 附件名称
	 */
	private String fileFileName;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public DbinfoBuiness getDbinfoBuiness() {
		return dbinfoBuiness;
	}

	public void setDbinfoBuiness(DbinfoBuiness dbinfoBuiness) {
		this.dbinfoBuiness = dbinfoBuiness;
	}

	public String getResultJSON() {
		return resultJSON;
	}

	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}

	/**
	 * 申康中心主action
	 * 
	 * @param 2014-10-20
	 * @param @return
	 * @return String
	 */
	public String infoMain() {

		Map totalCountMap = new HashMap();
		request = ServletActionContext.getRequest();
		DbUsers dbuser = (DbUsers) request.getSession().getAttribute("users");
		String fanException = "";
		if(request.getParameter("fanException") != null && request.getParameter("fanException")!=""){
			fanException = request.getParameter("fanException");
		};
		// 封装查询条件
		Map<String, Object> pagingMap = new HashMap<String, Object>();

		// 获取到当前页，每页多少条
		Integer pg_num = Integer.valueOf(request.getParameter("pg_num")); // 第一页
		Integer pageNumber = Integer
				.valueOf(request.getParameter("pageNumber"));

		// 分页开始值
		Integer min = (pg_num - 1) * pageNumber;
		// 分页的结束值
		Integer max = pg_num * pageNumber;

		int pageTotalCount = dbinfoBuiness.findallpagenum();
		totalCountMap.put("pageTotalCount", pageTotalCount);
		pagingMap.put("min", min);
		pagingMap.put("max", max);
		pagingMap.put("userid", dbuser.getSeq());
		List info_all = dbinfoBuiness.find_infobynum(pagingMap);

		try {
       
			request.setAttribute("data", JSONArray.fromObject(info_all)
					.toString());
			request.setAttribute("pageallnumber", pageTotalCount);
			request.setAttribute("fanException", fanException);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ManagementAction-->infoMain", e);
		}
		return "infomain";
	}

	/**
	 * 分页查询
	 * 
	 * @param 2014-10-23
	 * @param @return
	 * @return String
	 * @throws IOException
	 */
	public String listPageInfo() throws Exception {

		Map totalCountMap = new HashMap();
		request = ServletActionContext.getRequest();
		// 封装查询条件
		Map<String, Object> pagingMap = new HashMap<String, Object>();
		DbUsers dbuser = (DbUsers) request.getSession().getAttribute("users");

		// 获取到当前页，每页多少条
		Integer pg_num = Integer.valueOf(request.getParameter("pg_num")); // 第一页
		Integer pageNumber = Integer
				.valueOf(request.getParameter("pageNumber"));

		// 第一次是第一页 共二十条
		// 分页开始值
		Integer min = (pg_num - 1) * pageNumber;
		// 分页的结束值
		Integer max = pg_num * pageNumber;

		int pageTotalCount = dbinfoBuiness.findallpagenum();
		totalCountMap.put("pageTotalCount", pageTotalCount);
		pagingMap.put("min", min);
		pagingMap.put("max", max);
		pagingMap.put("userid", dbuser.getSeq());
		List info_all = dbinfoBuiness.find_infobynum(pagingMap);

		resultJSON = JSONArray.fromObject(info_all).toString();
		request.setAttribute("pageallnumber", pageTotalCount);
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(resultJSON);

		return null;
	}

	/**
	 * 根据mid 获取该条信息的所有详细信息
	 * 
	 * @param 2014-10-21
	 * @param @return
	 * @return String
	 */
	public String findInfo_byid() {

		request = ServletActionContext.getRequest();
		String mid = request.getParameter("mid");
		// 查询具体信息
		contentMap = dbinfoBuiness.findinfo_byid(mid);
		Map pagingMap = new HashMap();
		DbUsers dbuser = (DbUsers) request.getSession().getAttribute("users");
		pagingMap.put("userid", dbuser.getSeq());
		pagingMap.put("mid", mid);
		// 修改状态
		try {

			dbinfoBuiness.updateInfoStatusByid(pagingMap);
		} catch (Exception e) {
			log.error("ManagementAction-->infoMain", e);
		}

		request.setAttribute("contentMap", contentMap);
		return "infobyid";
	}
	/**
	 * 从信息详情页面跳到编辑页面（有下载）
	 * 
	 * @param 2014-10-22
	 * @param @return
	 * @return String
	 */
	public String findInfo_byidreply() {

		request = ServletActionContext.getRequest();
		String mid = request.getParameter("mid");
		contentMap = dbinfoBuiness.findinfo_byid(mid);
		request.setAttribute("contentMap", contentMap);
		request.setAttribute("data", JSONObject.fromObject(contentMap)
				.toString());

		return "reply";
	}
	/**
	 * 发布一条信息
	 * 
	 * 1、查询所有的数据，封装成map 集合。 2、传递数据到目标服务器上。 3、把该条数据写到数据库中。
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addInfo() throws Exception {
     
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		DbUsers user = (DbUsers) session.getAttribute("users");
		Integer userid = user.getSeq();

		Map dbinfo = new HashMap();
		request = ServletActionContext.getRequest();

		String text_to = request.getParameter("text_to");
		String text_from = request.getParameter("text_from");
		dbinfo.put("text_to", text_to);
		dbinfo.put("text_from", text_from);
		
		

		// String root =
		// ServletActionContext.getServletContext().getRealPath("/upload");
		String root = this.getFilePath();
		File filedir = new File(root);

		boolean mkdirs = filedir.mkdirs();
		if (file == null) {

			log.info("没有附件需要上传...");
		} else {
			InputStream is = new FileInputStream(file);
			OutputStream os = new FileOutputStream(new File(root, fileFileName));
			dbinfo.put("filename", fileFileName);

			byte[] buffer = new byte[500];
			int length = 0;

			while (-1 != (length = is.read(buffer, 0, buffer.length))) {
				os.write(buffer);
			}
			os.close();
			is.close();
		}

		Integer mId = 0;
		try {

			mId = dbinfoBuiness.finddbmessageMaxId();
			if (mId == null)
				mId = 1;
		} catch (Exception e) {
			mId = 1;
			log.error("获取不到最大id值:设置id初始值为1",e);
		}

		// 上传附件名称
		String fileName = request.getParameter("fileName");
		if (fileFileName != null && !"".equals(fileFileName)) {

			dbinfo.put("attach", root + "\\" + fileFileName);// 附件
		} else {

			dbinfo.put("attach", "");

		}
		String info_to =   request.getParameter("info_from");
		String info_from = request.getParameter("info_to");
		// 标题
		String txtTitle = request.getParameter("txtTitle");
		//String info_from = request.getParameter("info_from");
		String from_name = request.getParameter("from_name");
		//String info_to = request.getParameter("info_to");
		String to_name = request.getParameter("to_name");
		dbinfo.put("info_from", info_from);
		dbinfo.put("from_name", from_name);
		dbinfo.put("info_to", info_to);//接收着
		dbinfo.put("to_name", to_name);
		dbinfo.put("userid", info_from);//创建者

		txtTitle = new String(txtTitle.getBytes("utf-8"), "utf-8");
		dbinfo.put("title", txtTitle);
		dbinfo.put("mid", mId);
		// 内容
		String content = request.getParameter("Content");
		content = new String(content.getBytes("utf-8"), "utf-8");
		dbinfo.put("content", StringUtil.toString(content));

		dbinfo.put("otime",
				DateUtil.dateToString(new Date(), DateUtil.LONG_TIME_FORMAT));
		String sendfileException = sendfile(dbinfo);

		dbinfo.put("userid", userid);
		 log.info("-----userid---"+userid+"--info_from--"+info_from+"---from_name--"+from_name+"---info_to--"+info_to+"--to_name--"+to_name+"--mId--"+mId);
		dbinfoBuiness.inserInfoByMap(dbinfo);
		quartzList();
		fanException= "";
		if(("fanException").equals(sendfileException)){
			fanException = "fanException";
			request.setAttribute("fanException",fanException);
			return "successInfomain";
		}
		request.setAttribute("fanException",fanException);
		return "successInfomain";
	}
	
	private String fanException;
	
	public String getFanException() {
		return fanException;
	}

	public void setFanException(String fanException) {
		this.fanException = fanException;
	}

	/**
	 * 获取附件上传的路径
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getFilePath() throws IOException {

		// linux下tomcat的路径
		String tomcatPath = System.getProperty("catalina.base");

		// 得到文件存储路径
		InputStream ins = getClass().getResourceAsStream("/config.properties");
		Properties p = new Properties();
		p.load(ins);
		String filePath = File.separator + "upload";

		return tomcatPath + filePath;
	}
	/**
	 * 
	 * @param 2014-10-28
	 * @param @param str
	 * @param @return
	 * @return String
	 */
	public String PromptResult(String str) {

		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html; charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.write(str);
		} catch (IOException e) {

			if (log.isDebugEnabled()) {
				log.error("ManagementAction-->infoMain", e);
				log.debug(e.getMessage());
			}
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return null;
	}
	/**
	 * 上传文件
	 * 
	 * @param 2014-10-24
	 * @param @param file
	 * @param @param storePath
	 * @param @throws Exception
	 * @return void
	 */
	public void uploadFormFile(File file, String storePath) throws Exception {
		// 开始上传
		InputStream is = null;
		OutputStream os = null;
		try {

			is = ((ServletRequest) file).getInputStream();
			os = new FileOutputStream(storePath);
			int bytes = 0;
			byte[] buffer = new byte[8192];
			while ((bytes = is.read(buffer, 0, 8192)) != -1) {

				os.write(buffer, 0, bytes);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			log.error("ManagementAction-->uploadFormFile", e);
			throw e;
		} finally {
			if (os != null) {
				try {
					os.close();
					os = null;
				} catch (Exception e1) {

					log.info("输出流关闭失败");
				}
			}
			if (is != null) {

				try {
					is.close();
					is = null;
				} catch (Exception e1) {
					log.error("ManagementAction-->uploadFormFile(输入流关闭失败)", e1);
				}
			}
		}
	}
	/**
	 * 前台上传的附件
	 * 
	 * @throws Exception
	 */
	public String sendFile() throws Exception {
		request.setCharacterEncoding("UTF-8");
		String filePath = getFilePath();

		try {
			String fileName = this.getFileFileName();
			fileName = new String(fileName.getBytes("utf-8"), "utf-8");
			File savefile = new File(new File(filePath), fileName);
			File f = this.getFile();

			if (!savefile.getParentFile().exists()) {
				savefile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(f, savefile);
		} catch (Exception e) {
			log.error("ManagementAction-->sendFile(附件上传失败)", e);
			// log.error("附件上传失败");
			PromptResult("<script language='javascript'>alert('附件上传失败');"
					+ "location.href='PageDistribute!getMain.action?flag=publish';</script>");

		}

		PromptResult("<script language='javascript'>alert('发布成功');"
				+ "location.href='PageDistribute!getMain.action?flag=publish';</script>");
		return null;
	}
	/**
	 * 定时器查询所有的信息记录。
	 * 
	 * @param 2014-10-24
	 * @param @return
	 * @param @throws IOException
	 * @return String
	 */
	public String quartzList() throws IOException {

		Map totalCountMap = new HashMap();
		request = ServletActionContext.getRequest();
		// 封装查询条件
		Map<String, Object> pagingMap = new HashMap<String, Object>();

		Integer pg_num = 1; // 第一页
		Integer pageNumber = 20;

		Integer min = (pg_num - 1) * pageNumber;
		Integer max = pg_num * pageNumber;

		int pageTotalCount = dbinfoBuiness.findallpagenum();
		totalCountMap.put("pageTotalCount", pageTotalCount);
		pagingMap.put("min", min);
		pagingMap.put("max", max);

		totalCountMap.put("pageTotalCount", pageTotalCount);
		pagingMap.put("min", min);
		pagingMap.put("max", max);
		List info_all = dbinfoBuiness.find_infobynum(pagingMap);

		request.setAttribute("data", JSONArray.fromObject(info_all).toString());
		request.setAttribute("pageallnumber", pageTotalCount);

		return null;
	}
	/**
	 * 接收总平台的 信息 (使用的核心还是struts2
	 * 的内部封装的一些技术，总平台发送过来的数据，post请求发送过来。字段必须有set、get方法。)
	 * 
	 * @param 2014-10-24
	 * @param @return
	 * @return String
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	public String receiptIinformation() throws Exception {
		log.info("文件接收时间为" + DateUtil.getNowDate(DateUtil.LONG_TIME_FORMAT));
		try {
			log.info("开始从总平台接收信息.");

			if (file != null) {
				log.info("总平台接收信息中含有附件. 文件长度:" + file.length());
			}

			// 发布信息的id
			Integer mId = 0;
			try {

				mId = dbinfoBuiness.finddbmessageMaxId();
				if (mId == null) {
					mId = 1;
				}
			} catch (Exception e) {
				mId = 1;
				log.error("获取最大id值, 取不到,使用默认初始化值1。", e);
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			
			String info_from = URLDecoder.decode(
					request.getParameter("SendCode"), "utf-8");
			String info_to = URLDecoder.decode(
					request.getParameter("ReceiveCode"), "utf-8");
			String from_name = URLDecoder.decode(
					request.getParameter("SendName"), "utf-8");
			String to_name = URLDecoder.decode(
					request.getParameter("ReceiveName"), "utf-8");

			String title = URLDecoder.decode(request.getParameter("Title"),
					"utf8");
			String content = URLDecoder.decode(request.getParameter("Content"),
					"utf8");
			String attach = request.getParameter("Attach");
			log.info("attach:" + attach);
			String strname = "";
			if (attach != null) {

				strname = (new File(URLDecoder.decode(attach, "utf8")))
						.getName();
			}

			String otime = request.getParameter("Otime");
			Map map = new HashMap();
			map.put("Title", title);
			map.put("Content", content);
			map.put("Otime", otime);
			map.put("mid", mId);

			map.put("info_from", info_from);
			map.put("info_to", info_to);
			map.put("from_name", from_name);
			map.put("to_name", to_name);
			log.info("总平台接收文件存放路径----------");
			String root = this.getFilePath();
			log.info("总平台接收文件存放路径:" + (root + strname));
			File filedir = new File(root);
			boolean mkdirs = filedir.mkdirs();
			if (file != null) {

				
				IOUtils.copy(new FileInputStream(file), new FileOutputStream(
						new File(root, strname)));

				map.put("Attach", root + File.separator + strname);

				
			} else {

				map.put("Attach", "");
			}

//			String hosname = ConfigUtil.getUrlConfig("/config.properties",
//					"currentHospCode");

			dbinfoBuiness.insertInforeceipt(map);
		} catch (Exception ee) {
			log.error("从总平台接收信息时出现异常.", ee);
			throw ee;
		}
		return null;
	}
	/**
	 * 向总平台发送信息的方法，参数是一个map集合。
	 * 
	 * @param 2014-10-24
	 * @param @param dbinfo
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws Exception
	 */
	public String sendfile(Map dbinfo) {
		log.info("信息开始进行发送。。。。" + dbinfo);
		int code = 0;

		String url_config = "";
		MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
		try {
			url_config = ConfigUtil.getUrlConfig("/config.properties",
					"to_infoURL");
			
			String hosName = new String(ConfigUtil.getUrlConfig("/config.properties",
				"currentHospName").getBytes("ISO-8859-1"), "UTF-8");
			//String hosName = ConfigUtil.getUrlConfig("/config.properties",
			//		"currentHospName");
			reqEntity.addTextBody("usercode",
					StringUtil.toString(dbinfo.get("userid")));
			reqEntity.addTextBody("username",
					URLEncoder.encode(hosName, "utf-8"));
			reqEntity.addTextBody("title", URLEncoder.encode(
					StringUtil.toString(dbinfo.get("title")), "utf-8"));
			reqEntity.addTextBody("otime", URLEncoder.encode(
					StringUtil.toString(dbinfo.get("otime")), "utf-8"));
			reqEntity.addTextBody("content", URLEncoder.encode(
					StringUtil.toString(dbinfo.get("content")), "utf-8"));
			reqEntity.addTextBody("filename", URLEncoder.encode(
					StringUtil.toString(dbinfo.get("filename")), "utf-8"));
			reqEntity.addTextBody("messageType", "common");
		} catch (Exception e) {
			log.error("创建http entity时出现异常", e);
		}

		// 对请求的表单域进行填充 ,对应的action 也必须要有set get方法。
		if (dbinfo.get("attach") == null || dbinfo.get("attach") == "") {

			log.info("附件为空。。。。。。。。。");
		} else {

			reqEntity.addBinaryBody("file", file,
					ContentType.MULTIPART_FORM_DATA, file.getName());
			try {
				reqEntity.addTextBody("Attach", URLEncoder.encode(
						StringUtil.toString(dbinfo.get("attach")), "utf8"));
			} catch (UnsupportedEncodingException e) {
				log.error("编码出现异常",e);
			}
		}

		// 创建默认的httpClient实例.
		HttpClient httpclient = HttpClients
				.custom()
				.setDefaultRequestConfig(
						RequestConfig.custom().setSocketTimeout(20000)
								.setConnectTimeout(5000)
								.setConnectionRequestTimeout(5000).build())
				.build();
		// 创建httppost
		log.info("创建httppost");
		HttpPost httppost = new HttpPost(url_config);

		httppost.setEntity(reqEntity.build());
		log.info("开始向总平台发送信息,url:" + url_config);

		for (Header h : httppost.getAllHeaders()) {

			log.info(h.getName() + ":" + h.getValue());
		}
		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (Exception e) {
			log.info("发送异常" + e);
		}
	//	log.info("statusCode is " + response.getStatusLine().getStatusCode());
		HttpEntity resEntity = null;
		try {
			resEntity = response.getEntity();
			log.info(response.getStatusLine());
		} catch (Exception e1) {
			log.info("response.getEntity()--没有找到对应运行的项目，导致NullException报错" + e1);
		}
		if (resEntity != null) {
			InputStream in;
			try {
				in = resEntity.getContent();
			} catch (Exception e) {
				log.info("获取发送信息 的详情。。");
			}
		}
		
		if (resEntity != null) {
			try {
				EntityUtils.consume(resEntity);
			} catch (IOException e) {
				log.error("-->sendfile",e);
			}
		}
		try {
			code = response.getStatusLine().getStatusCode();
			if (code != 200) {
				log.info("该分平台信息发布异常,url为：" + url_config);
			}
			log.info("分平台向总平台回复信息成功！");
		} catch (Exception e) {
			log.info("未返回状态值：没有找到对应运行的项目，导致NullException报错" + e);
			return "fanException";
		}
		return null;
	}

	/**
	 * 文件下载
	 * 
	 * @param 2014-10-27
	 * @param @return
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public String infofileDownload() {

		try {
			// 得到响应对象
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			// 设置响应的格式
			response.setContentType("text/html; charset=UTF-8");
			// 得到文件名
			/**
			 * 测试Win
			 */
			/*String fileName = new String(request.getParameter("fileUrl")
					.getBytes("ISO-8859-1"), "UTF-8");*/
			/**
			 * 测试Liunx
			 */
			String fileName = request.getParameter("fileUrl");
					
			// 创建要下载的文件的对象(参数为要下载的文件在服务器上的路径)
			File serverFile = new File(fileName);
			int lastIndexOf = fileName.lastIndexOf(File.separator);
			String strname = fileName.substring(lastIndexOf + 1,
					fileName.length());
			log.info(File.separator + "要下载的 文件名称为" + strname);
			// 设置response的编码方式
			response.setContentType("application/octet-stream");
			// 设置要显示在保存窗口的文件名，如果文件名中有中文的话，则要设置字符集，否则会出现乱码。另外，要写上文件后缀名
			// 该步是最关键的一步，使用setHeader()方法弹出"是否要保存"的对话框，打引号的部分都是固定的值，不要改变
			//strname = URLDecoder.decode(strname, "UTF-8"); 
			response.setHeader("Content-disposition","attachment;filename="
					+ new String(strname.getBytes("UTF-8"), "ISO-8859-1"));

			
			// 读出文件到i/o流
			FileInputStream fis = null;
			try {

				fis = new FileInputStream(serverFile);
			} catch (Exception e) {
				log.error("文件不存在",e);
				throw new RuntimeException("文件不存在", e);
			}

			BufferedInputStream buff = new BufferedInputStream(fis);
			byte[] b = new byte[1024];// 相当于我们的缓存
			long k = 0;// 该值用于计算当前实际下载了多少字节
			// 从response对象中得到输出流,准备下载
			OutputStream myout = response.getOutputStream();
			// 开始循环下载
			while (k < serverFile.length()) {
				int j = buff.read(b, 0, 1024);
				k += j;
				// 将b中的数据写到客户端的内存
				myout.write(b, 0, j);
			}
		} catch (Exception e) {
			log.info("down load error.......",e);
		}
		return null;

	}
	/**
	 * 下载方法
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public InputStream getInputStream() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		InputStream in = null;
		try {

			in = new FileInputStream(new File(
					request.getRealPath(File.separator) + this.fileUrl));
			// in=
			// ServletActionContext.getServletContext().getResourceAsStream("/"+this.fileName);
		} catch (Exception e) {

			log.debug(request.getRealPath(File.separator) + this.fileUrl);
			log.error("ManagementAction-->getInputStream", e);
		}
		return in;
	}
	
	public String createView(){
		return "createView";
	}
	/**
	 * 创建一条信息
	 * 
	 * 1、查询所有的数据，封装成map 集合。 2、传递数据到目标服务器上。 3、把该条数据写到数据库中。
	 * 
	 * @return
	 * @throws Exception
	 */
	public String creatInfo() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		DbUsers user = (DbUsers) session.getAttribute("users");
		Integer userid = user.getSeq();
           
		Map dbinfo = new HashMap();
		request = ServletActionContext.getRequest();

		String text_to = request.getParameter("text_to");
		String text_from = request.getParameter("text_from");
		dbinfo.put("text_to", text_to);
		dbinfo.put("text_from", text_from);
       
		//获取配置文件信心
		 ManagementAction loadProp = new ManagementAction(); 
		  InputStream in = loadProp.getClass().getResourceAsStream("/config.properties"); 
		  Properties prop = new Properties(); 
		  try {
		   prop.load(in); 
		  } catch (IOException e) { 
		   e.printStackTrace(); 
		   log.error("-->creatInfo",e);
		  } 
		//配置文件中获取接收者和发送者
		 //接收者
		String sk_new_info_to = prop.getProperty("sk_new_info_to");
		//发送者
		String sk_new_info_from = prop.getProperty("sk_new_info_from");
		
		
		String root = this.getFilePath();
		File filedir = new File(root);

		boolean mkdirs = filedir.mkdirs();
		if (file == null) {

			log.info("没有附件需要上传...");
		} else {
			InputStream is = new FileInputStream(file);
			OutputStream os = new FileOutputStream(new File(root, fileFileName));
			dbinfo.put("filename", fileFileName);

			byte[] buffer = new byte[500];
			int length = 0;

			while (-1 != (length = is.read(buffer, 0, buffer.length))) {
				os.write(buffer);
			}
			os.close();
			is.close();
		}

		Integer mId = 0;
		try {

			mId = dbinfoBuiness.finddbmessageMaxId();
			if (mId == null)
				mId = 1;
		} catch (Exception e) {
			mId = 1;
			log.error("获取不到最大id值:设置id初始值为1",e);
		}

		// 上传附件名称
		String fileName = request.getParameter("fileName");
		if (fileFileName != null && !"".equals(fileFileName)) {

			dbinfo.put("attach", root + "\\" + fileFileName);// 附件
		} else {

			dbinfo.put("attach", "");

		}
		String info_to = sk_new_info_to;
		String info_from = sk_new_info_from;
		/*String info_to = sk_new_info_from;
		String info_from = sk_new_info_to;*/
		// 标题
		String txtTitle = request.getParameter("txtTitle");
		//String info_from = request.getParameter("info_from");
		String from_name = request.getParameter("from_name");
		//String info_to = request.getParameter("info_to");
		String to_name = request.getParameter("to_name");
		dbinfo.put("info_from", info_from);
		dbinfo.put("from_name", from_name);
		dbinfo.put("info_to", info_to);//接受者
		dbinfo.put("to_name", to_name);
		dbinfo.put("userid", info_from);//创建者

		txtTitle = new String(txtTitle.getBytes("utf-8"), "utf-8");
		dbinfo.put("title", txtTitle);
		dbinfo.put("mid", mId);
		// 内容
		String content = request.getParameter("Content");
		content = new String(content.getBytes("utf-8"), "utf-8");
		dbinfo.put("content", StringUtil.toString(content));

		dbinfo.put("otime",
				DateUtil.dateToString(new Date(), DateUtil.LONG_TIME_FORMAT));
		String sendfileException = sendfile(dbinfo);
		dbinfo.put("userid", userid);
		dbinfoBuiness.inserInfoByMap(dbinfo);
		quartzList();
	    fanException ="";
		if(("fanException").equals(sendfileException)){
			fanException = "fanException";
			request.setAttribute("fanException",fanException);
			return "successInfomain";
		}
		request.setAttribute("fanException",fanException);
		return "successInfomain";
	}

}
