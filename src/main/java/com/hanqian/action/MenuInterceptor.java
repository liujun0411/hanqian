package com.hanqian.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanqian.business.BaseBusiness;
import com.hanqian.business.UsersBusiness;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
@Service
public class MenuInterceptor  extends BaseBusiness {
 
	//日志	
	private static final Logger log = Logger.getLogger(MenuInterceptor.class);	
	private UsersBusiness usersBusiness;
	/**
	 * 权限拦截
	 */
	public Map menuIntercept(String menuCode){
		try {
			HttpServletRequest req=ServletActionContext.getRequest();
			//判断没有登录
			DbUsers dbUsers =(DbUsers)req.getSession().getAttribute("users");
			if(dbUsers==null ){
				return null;
			}
			//进入后台验证
			List list=usersBusiness.menuInterceptor( dbUsers,menuCode);
			Map menuMap=new HashMap();
			if(list==null)
				return menuMap;
			for (int i = 0; i < list.size(); i++) {
				Map m=(Map)list.get(i);
				menuMap.put(m.get("menu_code").toString(), m.get("menu_code").toString());
			}

			//如无错误继续执行
			return menuMap;
		} catch (Exception e) {
			log.error("MenuInterceptor-->menuIntercept", e);
			return null;
		}
	} 
	
	/**
	 * 根据用户对象 获取医院信息
	 * @param 2012-11-14  
	 * @param @param users
	 * @param @return       
	 * @return Map
	 */
	public Map findHospInfo(){
		DbUsers users=null;
		users=this.getSessionDbUsers();
		if(null==users){
			this.toLoginjsp();
			return null;
		}
		Map param = new HashMap();
		param.put("seq", users.getSeq());
		RetCode rc=this.getData("findHospInfoMenuInterceptor", param);
		
		List lst=(List)rc.getObj();
		if(lst!=null){
			return (Map)lst.get(0);
		}
		return null;
		
	}
	
	/**
	 * 验证用户是否登录
	 * @param 2012-11-19  
	 * @param @return       
	 * @return DbUsers
	 */
	public static DbUsers getSessionDbUsers(){
		HttpServletRequest req=ServletActionContext.getRequest();
		//判断没有登录
		DbUsers dbUsers =(DbUsers)req.getSession().getAttribute("users");
		return dbUsers;
	}
	
	//跳转至前一个页面
	public void checkMenu(){
		HttpServletResponse res=ServletActionContext.getResponse();
		res.setContentType("text/html; charset=UTF-8"); 
		res.setCharacterEncoding("utf-8");
		PrintWriter out=null;
		try {
			out = res.getWriter();
			String str="<script language='javascript'>history.go(-1);" +
			"</script>";
			out.write(str);	
		} catch (IOException e) {
			log.error("MenuInterceptor-->checkMenu", e);
			e.printStackTrace();
		}finally{
			if(null!=out){
				out.flush();
				out.close();
			}
		}
	}
	
	public static void toLoginjsp(){
		HttpServletResponse res=ServletActionContext.getResponse();

	    res.setContentType("text/html; charset=UTF-8"); 
		res.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		try {
			out = res.getWriter();			
			String str="<script language='javascript'>" +
					"window.top.location.href='manager/login.jsp';" +
			"</script>";
			out.write(str);	
		} catch (IOException e) {
			log.error("MenuInterceptor-->toLoginjsp", e);
			e.printStackTrace();
		}finally{
			if(null!=out){
				out.flush();
				out.close();
			}
		}
	}
	
	

	
	public void destroy() {
		
		
	}

	public void init() {
		
		
	}

	public UsersBusiness getUsersBusiness() {
		return usersBusiness;
	}
	@Autowired
	public void setUsersBusiness(UsersBusiness usersBusiness) {
		this.usersBusiness = usersBusiness;
	}

	public static Logger getLog() {
		return log;
	}
}