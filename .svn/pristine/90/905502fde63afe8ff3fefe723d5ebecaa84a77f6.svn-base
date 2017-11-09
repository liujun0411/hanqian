package com.hanqian.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.EquipPicBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.DbEquipPic;
import com.hanqian.pojo.DbEquipPicRel;
import com.hanqian.pojo.DbEquipPicType;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 描 述: 设备图纸信息Action类
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-13
 * @see
 */
public class EquipPicAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(EquipPicAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private EquipPicBusiness equipPicBusiness;  //设备图纸
	private HttpServletRequest request;
	private List equipPicList;
	private DbEquipPic dbEquipPic;  //设备图纸
	private DbEquipPicRel dbEquipPicRel;
	private BuildingBusiness buildBusiness;     //楼宇类型
	private List listBuilding; // 楼宇信息列表
	private List listPicTypeList; //楼宇图纸类型
	private EquipListBusiness equipListBusiness;
	private String fileUrl;
	private String fileName;
	private Page page;
	private Integer pageSize=10;
	private Integer currentPage;
	private String [] picCode;
	private File[] image;
	private String [] picName;
	private String[] imageFileName;
	private String[] imageContentType;
	
	private Map sqlmap = new HashMap();
    
    private Map seqMap = new HashMap();
	
    private Map picCodeMap = new HashMap();
	
	private Map equipNameMap = new HashMap();
	
	private Map picTypeNameMap = new HashMap();
	
	private Map equipPicNameMap = new HashMap();
	
	private Map remindmapAll = new HashMap();
	
	private RetCode rt=new RetCode();
	
	
	/**
	 * 显示设备图纸列表
	 * @return
	 */
	public String findEquipPicList(){
		request = ServletActionContext.getRequest();
		dbEquipPicRel=new DbEquipPicRel();
		String equipId=request.getParameter("equipId");
		DbEquipList dbEquipList=new DbEquipList();
	    if(currentPage==null){
			 currentPage=1;
		}
		if(StringUtils.isNotEmpty(equipId)){
			dbEquipList.setEquipId(Integer.parseInt(equipId));
		}
		dbEquipPicRel.setDbEquipList(dbEquipList);
		//获得当前设备图纸列表信息
		equipPicList=new ArrayList();
		rt=equipPicBusiness.findDbEquipPic(dbEquipPicRel, currentPage, pageSize);
		if(rt.getObj()!=null){
			equipPicList=(List)rt.getObj();
		}
		page=rt.getPage();
        if(null==page){
        	page=new Page(1, 0, 10);
        }
		request.setAttribute("equipId", equipId);
		
		
		//调用抽取任意提醒需要的数据的方法
//		remindBuildInfoDate();
		
		
		
		return "equipPicList";
	}
	
	/**
	 * 跳转到添加设备图纸页面
	 * @return
	 */
	public String showAddPicUI(){
		request = ServletActionContext.getRequest();
		String equipId=request.getParameter("equipId");
		DbBuilding building=new DbBuilding();
		listBuilding=(List<DbBuilding>)buildBusiness.findDBBuilding(building).getObj();
		if(equipPicBusiness.findALLEquipPicType().getObj()!=null){
			listPicTypeList=(List)equipPicBusiness.findALLEquipPicType().getObj();
		}
		request.setAttribute("equipId", equipId);
		return "showAddPicUI";
	}
	
	/**
	 * 设备图纸上传
	 * @return
	 */
	public String uploadEquipPic(){
		request = ServletActionContext.getRequest();
		DbEquipPicType dept=new DbEquipPicType();
		DbEquipList dbEquipList=new DbEquipList();
		dbEquipPicRel=new DbEquipPicRel();
		dbEquipPic=new DbEquipPic();
		DbUsers user=new DbUsers();
		if(request.getSession().getAttribute("users")!=null){
			user=(DbUsers)request.getSession().getAttribute("users");
		}
		dbEquipPic.setStatus(Short.parseShort("0"));
		dbEquipPic.setDbUsers(user);
		dbEquipPic.setOpertime(new Date());
		String equipPicType=request.getParameter("equipPicType");  //设备图纸类型
		String buildId=request.getParameter("buildId");  //楼宇编号
		String storey=request.getParameter("storey");   //所在楼层
		String equipId=request.getParameter("equipId");  //设备编号
		if(StringUtils.isNotEmpty(equipPicType)){
			dept.setPicTypeId(Integer.parseInt(equipPicType));
		}
		if(StringUtils.isNotEmpty(buildId)){
			dbEquipPic.setBuildId(Integer.parseInt(buildId));
		}
		if(StringUtils.isNotEmpty(storey)){
			dbEquipPic.setStorey(Integer.parseInt(storey));
		}
		if(StringUtils.isNotEmpty(equipId)){
			dbEquipList.setEquipId(Integer.parseInt(equipId));
		}
		dbEquipPic.setDbEquipPicType(dept);
		dbEquipPicRel.setDbEquipPic(dbEquipPic);
		dbEquipPicRel.setDbEquipList(dbEquipList);
		boolean result=equipPicBusiness.insertBuildingPic(image,picCode,imageFileName, picName, dbEquipPicRel, request);
		if(result){
//			System.out.println("success");
			dbEquipPicRel=new DbEquipPicRel();
			RetCode rt=new RetCode();
			DbEquipList dbEquipLists=new DbEquipList();
			if(StringUtils.isNotEmpty(equipId)){
				dbEquipLists.setEquipId(Integer.parseInt(equipId));
				 if(equipListBusiness.findByEquipId(Integer.parseInt(equipId)).getObj()!=null){
		            	List list=(List)equipListBusiness.findByEquipId(Integer.parseInt(equipId)).getObj();
		            	if(list.size()>0){
		            		request.setAttribute("equipName",list.get(0));
		          	}
		         }
			}
			dbEquipPicRel.setDbEquipList(dbEquipLists);
			//获得当前设备图纸列表信息
			rt=equipPicBusiness.findDbEquipPic(dbEquipPicRel, currentPage, pageSize);
			if(rt.getObj()!=null){
				equipPicList=(List)rt.getObj();
			}
			page=rt.getPage();
	        if(null==page){
	        	page=new Page(1, 0, 10);
	        }
			request.setAttribute("equipId", equipId);
			
		}
		return "equipPicList";
	}
	
	/**
	 * 删除设备图纸
	 * @return
	 */
	public String deletePic(){
		request = ServletActionContext.getRequest();
		dbEquipPicRel=new DbEquipPicRel();
		DbEquipList equipList=new DbEquipList();
		DbEquipPic pic=new DbEquipPic();
		String seq=request.getParameter("seq");
		String equipId=request.getParameter("equipId");
		String picId=request.getParameter("picId");
		if(StringUtils.isNotEmpty(equipId)){
		  equipList.setEquipId(Integer.parseInt(equipId));
		}
		
		if(StringUtils.isNotEmpty(picId)){
			pic=equipPicBusiness.findById(Integer.parseInt(picId));
		}
		if(StringUtils.isNotEmpty(seq)){
			dbEquipPicRel.setSeq(Integer.parseInt(seq));
		}
		pic.setStatus(Short.parseShort("1"));
		pic.setOpertime(new Date());
		dbEquipPicRel.setDbEquipList(equipList);
		dbEquipPicRel.setDbEquipPic(pic);
		equipPicBusiness.deleteEquipPic(dbEquipPicRel);
		equipPicBusiness.deletePic(pic);
		findEquipPicList();
		return "equipPicList";
	}
	

	/**
	 * 楼宇图纸下载方法
	 * @param statement  
	 * @param        
	 * @return
	 */
	public void equipPicDownload(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
	            
	            OutputStream os = null;
	    		InputStream is = null;

	    	try { 
	            os = response.getOutputStream();
	            //【Win本地测试时需要的获取的目录】
	            //String folder = new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"EquipPic";
                //String folder =request.getRealPath(File.separator)+ConfigCST.CST_EQUIP_PIC_LOCATION;
	            //2016-9-01 新修改获取Linux系统下tomcat的目录【Linux上线时需要的目录】
	            String folder = System.getProperty("catalina.base")+"/Logistics_UploadPic/"+"EquipPic";
                // String folder = request.getRealPath(File.separator)+ConfigUtil.getConfig("config.properties", "equipPicLocation");
				folder = StringUtil.replacePath(folder);
				File downFolder = new File(folder);
				File file = null;
				if(downFolder.isDirectory()) {
					File[] files = downFolder.listFiles();
					for (int i = 0; i < files.length; i++) {
						if(!StringUtil.isEmpty(fileUrl)&&files[i].getName().equals(fileUrl)){
							file = files[i];
							break;
						}
					}
				}
				
				is = new FileInputStream(file);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition", "attachment;filename="
						+ fileName);
				byte[] buffer = new byte[1024];
				int l = 0;
				while ((l = is.read(buffer)) > 0) {
					os.write(buffer, 0, l);
				}
			} catch (Exception e) {
				logg.error("EquipPicAction-->equipPicDownload", e);
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment;filename=error.txt");
				try {
					os.write("下载图纸失败，请重试".getBytes("utf-8"));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				if (os != null) {
					try {
						os.flush();
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
//			System.out.println(fileName);
//			String fileName = URLEncoder.encode(this.fileName, "utf-8");
//	        if (fileName.length() > 150) { 
//	            String guessCharset = "gb2312"; /*根据request的locale 得出可能的编码，中文操作系统通常是gb2312*/
//	            fileName = new String(this.fileName.getBytes(guessCharset), "ISO8859-1");
//	        } 
//	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

//		} catch (Exception e) {
//
//		} 
//		return "download";

	}

	
	/**
	 * 将任意提醒的数据打包成map
	 * @param 
	 * @param
	 * @return
	 */
	public String remindBuildInfoDate() {
		int currentPage = 1;
		int pageSize = 10;
		request = ServletActionContext.getRequest();
		
		try {
			rt=equipPicBusiness.findDbEquipPic(dbEquipPicRel, currentPage, pageSize);
			//获取设备详情sql对象放到Map对象中
			sqlmap  = equipPicBusiness.getFindDbEquipPicSql();
			
			logg.debug("***************sqlmap="+sqlmap);
			
			if(rt.getObj()!=null){
				equipPicList=(List)rt.getObj();
			}
			
			String equipPicId=request.getParameter("equipId");
			seqMap.put("equipPicId", equipPicId);
			
			//（picCode）
			//提醒方式:1、短信提醒；2、邮件提醒；3、平台内部提醒
			picCodeMap.put("ariesPreForm", "1,2,3");
			//sql字段名称
			picCodeMap.put("sqlField", "picCode");
			//中文字段名
			picCodeMap.put("fieldNm", "图纸编号");
			//输入类型 ：1-文本;2-数字;3-时间
			picCodeMap.put("ariesInputType", 1);
			//sql
			picCodeMap.putAll(sqlmap);
			//含有多条，或者分页数据是用来对应数据用，数据是查出来的某条数据中有标识性的字段，如seq、ID等
			picCodeMap.put("sqlFieldKey", seqMap);
			//用来判断数据是否更新的时间字段，通过sql查询出来的结果集中的时间字段等来判断
			picCodeMap.put("updateDateField", "opertime");
			//用来判断数据是否更新的sql
			picCodeMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
//			remindmap.put("initFunc", "testDate");
			
			//（equipName）
			equipNameMap.put("ariesPreForm", "1,2,3");
			equipNameMap.put("sqlField", "equipName");
			equipNameMap.put("fieldNm", "设备名称");
			equipNameMap.put("ariesInputType", 1);
			equipNameMap.putAll(sqlmap);
			equipNameMap.put("sqlFieldKey", seqMap);
			equipNameMap.put("updateDateField", "opertime");
			equipNameMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（picTypeName）
			picTypeNameMap.put("ariesPreForm", "1,2,3");
			picTypeNameMap.put("sqlField", "picTypeName");
			picTypeNameMap.put("fieldNm", "图纸类型");
			picTypeNameMap.put("ariesInputType", 1);
			picTypeNameMap.putAll(sqlmap);
			picTypeNameMap.put("sqlFieldKey", seqMap);
			picTypeNameMap.put("updateDateField", "opertime");
			picTypeNameMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//（equipPicName）
			equipPicNameMap.put("ariesPreForm", "1,2,3");
			equipPicNameMap.put("sqlField", "equipPicName");
			equipPicNameMap.put("fieldNm", "图纸名称");
			equipPicNameMap.put("ariesInputType", 1);
			equipPicNameMap.putAll(sqlmap);
			equipPicNameMap.put("sqlFieldKey", seqMap);
			equipPicNameMap.put("updateDateField", "opertime");
			equipPicNameMap.put("updateFieldSql", "select opertime from DB_EQUIP_LIST where EQUIP_ID = #{equipId}");
			
			//将所有需要提醒的数据打包成map
//			for (int i = 0; i < array.length; i++) {
				
				remindmapAll.put("picCode", picCodeMap);
				remindmapAll.put("equipName", equipNameMap);
				remindmapAll.put("picTypeName", picTypeNameMap);
				remindmapAll.put("equipPicName", equipPicNameMap);
//			}
			
			
			//将已经打包好的MAP转换成JSON
			JSONObject remindJson = JSONObject.fromObject(remindmapAll);
			
			request.setAttribute("remindJson", remindJson);
			logg.debug("    remindJson=   "+remindJson);
		}catch (Exception e) {
			logg.error("HospDetailAction-->showHospDetail", e);
		}
		return "show";
	}
	
	
	/**
	 * 设备安装位置图
	 * @return
	 */
	public String EquipPositionPicList(){
		
		
		return null;
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public EquipListBusiness getEquipListBusiness() {
		return equipListBusiness;
	}

	public void setEquipListBusiness(EquipListBusiness equipListBusiness) {
		this.equipListBusiness = equipListBusiness;
	}
	
	public String[] getPicCode() {
		return picCode;
	}

	public void setPicCode(String[] picCode) {
		this.picCode = picCode;
	}

	public File[] getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
	}

	public String[] getPicName() {
		return picName;
	}

	public void setPicName(String[] picName) {
		this.picName = picName;
	}

	public String[] getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String[] getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}

	public List getListPicTypeList() {
		return listPicTypeList;
	}

	public void setListPicTypeList(List listPicTypeList) {
		this.listPicTypeList = listPicTypeList;
	}

	public EquipPicBusiness getEquipPicBusiness() {
		return equipPicBusiness;
	}

	public void setEquipPicBusiness(EquipPicBusiness equipPicBusiness) {
		this.equipPicBusiness = equipPicBusiness;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List getEquipPicList() {
		return equipPicList;
	}

	public void setEquipPicList(List equipPicList) {
		this.equipPicList = equipPicList;
	}

	public DbEquipPic getDbEquipPic() {
		return dbEquipPic;
	}

	public void setDbEquipPic(DbEquipPic dbEquipPic) {
		this.dbEquipPic = dbEquipPic;
	}

	public DbEquipPicRel getDbEquipPicRel() {
		return dbEquipPicRel;
	}

	public void setDbEquipPicRel(DbEquipPicRel dbEquipPicRel) {
		this.dbEquipPicRel = dbEquipPicRel;
	}

	public BuildingBusiness getBuildBusiness() {
		return buildBusiness;
	}

	public void setBuildBusiness(BuildingBusiness buildBusiness) {
		this.buildBusiness = buildBusiness;
	}

	public List getListBuilding() {
		return listBuilding;
	}

	public void setListBuilding(List listBuilding) {
		this.listBuilding = listBuilding;
	}

}
