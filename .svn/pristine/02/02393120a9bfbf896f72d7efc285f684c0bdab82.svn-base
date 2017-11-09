package com.hanqian.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 描 述: 
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-12-21
 * @see
 */
public class ReportMDXAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(ReportMDXAction.class);
	private static final String fileUrl = "//manager//mdxFile//";
	private MenuInterceptor menuInterceptor;
	private Map menuMap;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * 权限设置
	 * @param 2013-3-13  
	 * @param        
	 * @return void
	 */
	public void findRoles(){
		request=ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		try {
			String type=request.getParameter("type");
			if("1".equals(type)){
				menuMap=menuInterceptor.menuIntercept("3001");
			}else if("2".equals(type)){
				menuMap=menuInterceptor.menuIntercept("3002");
			}else if("3".equals(type)){
				menuMap=menuInterceptor.menuIntercept("3003");
			}
			//若为空 ，进入登录界面
			if(menuMap==null ){
				MenuInterceptor.toLoginjsp();
			}
			JSONObject jsonObject = new JSONObject();
			JSONArray array = new JSONArray();
			for (Object str : menuMap.keySet()) {
					JSONObject obj1 = new JSONObject();
					obj1.accumulate("menuCode", menuMap.get(str));
					array.add(obj1);
			}
//			System.out.println(array);
			jsonObject.accumulate("menuMap", array);
			resp.getWriter().print(jsonObject);
		} catch (Exception e) {
			logg.error("ReportMDXAction-->findRoles", e);
			e.printStackTrace();
		}
	}

	/**
	 * 保存报表
	 * @param 2012-12-28  
	 * @param @throws Exception       
	 * @return void
	 */
	public void saveReportFile() throws Exception{
		request=ServletActionContext.getRequest();
		String mdx=request.getParameter("mdxText");//获取mdx语句
		if(mdx==null || mdx.length()==0){
			return;
		}
		 
		 String path = Thread.currentThread().getContextClassLoader()
			.getResource("").getPath().toString();
		 String fileName=request.getParameter("fileName");
	     File temp = new File(path);
	     String rootpath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
	     ServletActionContext.getRequest().getSession().setAttribute("pContent", fileName);
		 File realFile = new File(rootpath + fileUrl +"//"+fileName+".txt");

		if (!realFile.exists()) {
				realFile.createNewFile();
			}
		 
		 String path1=realFile.toString();
		try{
			   File write = new File(path1);
			   FileOutputStream fos  =   new  FileOutputStream(write);
		        OutputStreamWriter osw  =   new  OutputStreamWriter(fos,"UTF-8" );
			    //写文件
			    osw.write(mdx);
			    osw.flush();
			    osw.close();
		  }catch(Exception e){
			  logg.error("ReportMDXAction-->saveReportFile", e);
			  e.printStackTrace();
	   }
	}

	/**
	 * 打开报表
	 * @param 2012-12-28  
	 * @param        
	 * @return void
	 */
	public void openReportFile(){
		 request=ServletActionContext.getRequest();
		 String fileName=request.getParameter("fileName");
		 String path = Thread.currentThread().getContextClassLoader()
			.getResource("").getPath().toString();
	     File temp = new File(path);
	     String rootpath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
	     File realFile = new File(rootpath + fileUrl+fileName);
	     
	     ServletActionContext.getRequest().getSession().setAttribute("pContent", fileName);
	     File newsContentFile = new File(realFile.getParentFile().getParentFile().getParentFile(), "WEB-INF/queries/mdx_template.jsp");
	     StringBuffer newsContent = new StringBuffer();
		 StringBuffer oldContent = new StringBuffer();
		 if (newsContentFile.exists()) {
		      try{
				   File reader = new File(realFile.toString());
				   BufferedReader brr = new BufferedReader(new InputStreamReader(
							new FileInputStream(reader), "UTF-8"));
				   BufferedReader br = new BufferedReader(new InputStreamReader(
							new FileInputStream(newsContentFile), "UTF-8"));
				   String line1=null;
				   String line2=null;
				   while((line1=brr.readLine()) != null){
				    //读文件
					   newsContent.append(line1+" "); 
				   } 
				   while((line2=br.readLine()) != null){
					    //读文件
					   oldContent.append(line2); 
				   } 
				   br.close();
				   String startStr = "connectionPooling=\"false\">";
				   String tempStr = oldContent.substring(oldContent.indexOf(startStr)+startStr.length(),oldContent.indexOf("</jp:mondrianQuery>"));
				   String newsMdx=newsContent.toString();
				   String oldContents = oldContent.toString();
				   String newsContents = oldContents.replace(tempStr,newsMdx);
				   String filePath=newsContentFile.toString();
				   File write = new File(filePath);
				   FileOutputStream fos  =   new  FileOutputStream(write);
			       OutputStreamWriter osw  =   new  OutputStreamWriter(fos,"UTF-8" );
				   if(newsContents != null){
				    //写文件
					   osw.write(newsContents); 
					   osw.flush();
				   } 
				   osw.close();
				  }catch(Exception e1){
					  logg.error("ReportMDXAction-->openReportFile", e1);
				   e1.printStackTrace();
				 } 
		      }
	 }
	/**
	 * 获取所有报表文件
	 * @param 2013-3-13  
	 * @param        
	 * @return void
	 */
	public void getFileNameSel(){
		try{
			 String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().toString();
		     File temp = new File(path);
		     String rootpath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
		     File realFile = new File(rootpath + fileUrl);
		     List fileNameList = new ArrayList();
		     File [] listFiles =  realFile.listFiles();
		     for (int i = 0; i < listFiles.length; i++) {
				if(!listFiles[i].isDirectory()){
					fileNameList.add(listFiles[i].getName());
				}
		     }
		     JSONObject jsonObjec = new JSONObject();
		     JSONArray array = new JSONArray();
				if (fileNameList != null && !fileNameList.isEmpty()) {
					for (int i = 0; i < fileNameList.size(); i++) {
						JSONObject fileNameObj = new JSONObject();
						fileNameObj.accumulate("fname", fileNameList.get(i));
						array.add(fileNameObj);
					}
				}
				jsonObjec.accumulate("fsel", array);
		     HttpServletResponse resp =  ServletActionContext.getResponse();
		     resp.setCharacterEncoding("UTF-8");
		     PrintWriter out = resp.getWriter();
		     out.print(jsonObjec.toString());
		}catch (Exception e) {
			logg.error("ReportMDXAction-->getFileNameSel", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除报表
	 * @param 2013-3-14  
	 * @param @return       
	 * @return MenuInterceptor
	 */public void deleteReportFile(){  
		 request=ServletActionContext.getRequest();
		 String fileName=request.getParameter("fileName");
		 String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().toString();
	     File temp = new File(path);
	     String rootpath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
	     File realFile = new File(rootpath + fileUrl+fileName+".txt");
		 //File  file = new File(realFile);   
		    // 路径为文件且不为空则进行删除   
		    if (realFile.isFile() && realFile.exists()) {   
		    	realFile.delete();
		    }      
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

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public static String getFileurl() {
		return fileUrl;
	}
}
