package com.hanqian.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.hanqian.business.DroolsBusiness;
import com.hanqian.business.SermainDetailBsiness;
import com.hanqian.pojo.Dbrule;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 天气情况
 *
 */
public class WeathersAction extends ActionSupport{

	
	//维修人员集合
	private List sermainDetailList;
	private List<Dbrule> dbrulelist;
	//维修人员
	private String detailId; 
	//规则类别
	private String sle;
//	private HttpServletRequest request;



	//
	private SermainDetailBsiness sermainDetailBsiness;
	
	private DroolsBusiness  droolsBusiness;
	
	private 	HttpServletRequest request ;
	
	

	/**
	 * 查询规则下的维修人员    包含的维修人员
	 * @param 2012-12-10  
	 * @param @return       
	 * @return String
	 */
	public  List<Dbrule> selectrulebysermain(){
		
		 request = ServletActionContext.getRequest();
	    String id=	 request.getParameter("id");
	    Dbrule d=new Dbrule();
	    d.setSermainseql(Integer.valueOf(id));
	   List<Dbrule> list=droolsBusiness.selectDbruleindex(d);
	   
	 //给前台
	 		HttpServletResponse rsp=ServletActionContext.getResponse();
	 		PrintWriter out=null;
	 		Gson g=new Gson();
	 		rsp.setCharacterEncoding("utf-8");
	 		try {
	 			rsp.setContentType("text/plain");
	 			out= rsp.getWriter();
	 			out.print(g.toJson(list));
	 			
	 		}catch (Exception e) {
	 			e.printStackTrace();
	 		
	 		}finally{
	 			if(null!=out){
	 				out.flush();
	 				out.close();
	 			}
	 		}
	return null;
	}
	
	
	/** 
	 * 查询规则下维修人员（部分） 不包含的维修人员
	 * @param 2012-12-10  
	 * @param @return       
	 * @return String
	 */
	public  List<Dbrule> selectrulechang(){
		
		 request = ServletActionContext.getRequest();
	    String id=	 request.getParameter("id");  // 查询不等于这个iD的规则的其他维修人员返回
	    Dbrule d=new Dbrule();
	    d.setSermainseql(Integer.valueOf(id));
	    List<Dbrule> list=droolsBusiness.selectDbrulenotindex(d);
	
	    
	    
	    
	    
	    //给前台
	 		HttpServletResponse rsp=ServletActionContext.getResponse();
	 		PrintWriter out=null;
	 		Gson g=new Gson();
	 		rsp.setCharacterEncoding("utf-8");
	 		try {
	 			rsp.setContentType("text/plain");
	 			out= rsp.getWriter();
	 			out.print(g.toJson(list));
	 			
	 		}catch (Exception e) {
	 			e.printStackTrace();
	 		
	 		}finally{
	 			if(null!=out){
	 				out.flush();
	 				out.close();
	 			}
	 		}
	return null;
	}
	
	
	
	
	/**
	 * 添加
	 * @param 2012-12-10  
	 * @param @return       
	 * @return String
	 */
	public String addweather(){
		
		 request = ServletActionContext.getRequest();
		String roles = request.getParameter("sle");
//		String detailId = request.getParameter("detailId");
		String []dlId=null;

		//获取设备维修人员
		if(detailId!=null && !detailId.equals("")){
			dlId=detailId.split(",");	
		}
		System.out.println(sle+"  :   "+detailId+"5255555555555555555555555555555555555555555555555555555555555");
		
	     droolsBusiness.insertindex(sle,dlId);
		
		return toAddweaterwarning();
	}
	
	
	
	
	/**
	 * 去到添加页面
	 * @param 2012-12-6  
	 * @param @return       
	 * @return String
	 */
	public String toAddweaterwarning(){
		Dbrule Dbrule=new Dbrule();
		
       // 获取天气类型		
		dbrulelist=droolsBusiness.finddbrulealldbrule(Dbrule);
		
		//获取所有维修人员
		sermainDetailList=sermainDetailBsiness.findSerMainAll();

		return "toAddweaterwarning";
	}
	
	
	
	public List getSermainDetailList() {
		return sermainDetailList;
	}

	public void setSermainDetailList(List sermainDetailList) {
		this.sermainDetailList = sermainDetailList;
	}

	public SermainDetailBsiness getSermainDetailBsiness() {
		return sermainDetailBsiness;
	}

	public void setSermainDetailBsiness(SermainDetailBsiness sermainDetailBsiness) {
		this.sermainDetailBsiness = sermainDetailBsiness;
	}



	public List<Dbrule> getDbrulelist() {
		return dbrulelist;
	}



	public void setDbrulelist(List<Dbrule> dbrulelist) {
		this.dbrulelist = dbrulelist;
	}



	public DroolsBusiness getDroolsBusiness() {
		return droolsBusiness;
	}



	public void setDroolsBusiness(DroolsBusiness droolsBusiness) {
		this.droolsBusiness = droolsBusiness;
	}




	public String getDetailId() {
		return detailId;
	}




	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getSle() {
		return sle;
	}




	public void setSle(String sle) {
		this.sle = sle;
	}

	
	
}
