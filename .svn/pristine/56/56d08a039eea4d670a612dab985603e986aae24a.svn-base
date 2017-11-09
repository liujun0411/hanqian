package com.hanqian.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * 描 述: 过滤Session中的值
 * 
 * 前置条件: 后置条件: 调用者 : 被调用者: 
 * 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2013-04-27
 * @see
 */
public class UsersFilter implements javax.servlet.Filter {
	private static ArrayList<String> array ;
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse respones,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse res = (HttpServletResponse) respones;
		HttpServletRequest req = (HttpServletRequest) request;

		//访问地址
		String path=req.getRequestURI();
		if(ISOtherFile(path)){
			chain.doFilter(request, respones);
		}else{
			HttpSession session=req.getSession();
		
			//用户简编和选项卡状态,登录状态
			if(session.getAttribute("currentHospCode")==null || session.getAttribute("olapTabStatus")==null || session.getAttribute("users")==null){
//				System.out.println("path = " + path + " to login");
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/html;charset=utf-8");
				//res.getWriter().write("<script>alert('请登录！');window.top.location='"+req.getContextPath()+"/manager/login.jsp'</script>");
				res.getWriter().write("<script>window.top.location='"+req.getContextPath()+"/manager/login.jsp'</script>");
				return;
			}else{
//				System.out.println("path = " + path + " going on");
				chain.doFilter(request, respones);
			}
		}
		
	}
	/**
	 * 过滤jsp,action，对其他类型文件不过滤
	 * @param path
	 * @return
	 */
	public boolean ISOtherFile(String path){
		boolean resultFlag=false;
		List<String> fileType=new ArrayList<String>();
		fileType.add("jsp");
		fileType.add("xml");
		fileType.add("action");
		fileType.add("css");
		fileType.add("swf");
		fileType.add("js");
		fileType.add("ico");
		fileType.add("png");
		fileType.add("jpg");
		fileType.add("mp3");
		fileType.add("gif");
		fileType.add("json");
		fileType.add("htm");
		fileType.add("properties");
		fileType.add("servlet");
		
		String fileHouZhui = path.substring(path.lastIndexOf(".")+1);
		String fileNames = path.substring(path.lastIndexOf("/")+1);
		if(fileHouZhui.indexOf(";")>0){
			fileHouZhui=fileHouZhui.substring(0,fileHouZhui.indexOf(";"));
		}
		if(fileNames.indexOf(";")>0){
			fileNames=fileNames.substring(0,fileNames.indexOf(";"));
		}

		if(fileType.contains(fileHouZhui)){
			//如果是jsp，而且是login.jsp
			if(fileHouZhui.equals(fileType.get(0))){
				if(fileNames.equals("login.jsp") || fileNames.equals("bottom.jsp") || fileNames.equals("Contract.jsp")){
					resultFlag=true;
				}
			}else if(fileHouZhui.equals(fileType.get(1))){
				//对热区文件过滤(实时监控)
				if(!fileNames.equals("hotSpotPath.xml")){  
					resultFlag=true;
				}
			}else if(fileHouZhui.equals(fileType.get(2))){
				//对Struts Action进行过滤
				if(fileNames.equals("userLogin.action")||fileNames.endsWith("judgeCurrentHosp.action")){  
					resultFlag=true;
				}else if(fileNames.equals("info_receiptIinformation.action")){
					resultFlag=true;
				}
			}
			else if(!fileHouZhui.equals(fileType.get(0)) && !fileHouZhui.equals(fileType.get(1)) && !fileHouZhui.equals(fileType.get(2))){
				resultFlag=true;
			}
		}
		return resultFlag;
	}
	

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	


}
