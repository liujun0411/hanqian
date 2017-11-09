package com.hanqian.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;













import com.google.gson.Gson;
import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.BuildingPicBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbBuildingPic;
import com.hanqian.util.ConfigUtil;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BuildingPicAction  extends ActionSupport{
	//日志
	private static final Log log = LogFactory.getLog(BuildingPicAction.class);
	
	private BuildingPicBusiness buildingPicBusiness;
	private BuildingBusiness buildingBusiness;
	
	private MenuInterceptor menuInterceptor;

	private DbBuildingPic dbBuildingPic;			//楼宇图纸类
//	private DbBuildingPicType dBbuildingPicType;	//图纸类型
	private List dbBuildings;						//所有楼宇类
	private List dbBuildingPics;					//图纸集合
	private List dbBuildingPicTypes;				//图纸类型集合
	private HttpServletRequest request;
	
	private Map sqlmap = new HashMap();
    
    private Map seqMap = new HashMap();
	
	private Map buildingNameMap = new HashMap();
	
	private Map picNameMap = new HashMap();
	
	private Map picTypeNameMap = new HashMap();
	
	private Map storeyNumMap = new HashMap();
	
	private Map operMap = new HashMap();
	
	private Map opertimeMap = new HashMap();
	
	private Map remindmapAll = new HashMap();
	
	private Integer buildingId; // 楼宇ID
	
	private Integer picId; // 楼宇ID
	
	private RetCode rc;
	
	private DbBuilding dbBuilding=new DbBuilding();

	private Page page;
	private int pageSize = 10;
	private int currentPage = 1;
	
	public String updatePic(){
		//查询
		DbBuildingPic buildPic=buildingPicBusiness.findPicById(dbBuildingPic.getPicId());
		//修改
		buildPic.setStatus((short)1);
		buildingPicBusiness.updateBuildingPic(buildPic);
		return findBuilbingPic();
	}
	
	
	
	
	/**
	 * 查询图纸
	 * @param statement  
	 * @param        
	 * @return
	 */
	private Map menuMap;
	public String findBuilbingPic(){
		request=ServletActionContext.getRequest();
		//获取权限
		menuMap=menuInterceptor.menuIntercept("2002003005");
		if(menuMap==null){
			return null;
		}
		
		if(!SysUtil.isNull(request.getParameter("buildingId"))){
			dbBuilding.setBuildingId(Integer.parseInt(request.getParameter("buildingId")));
			if(dbBuildingPic!=null){
				if(dbBuildingPic.getDbBuilding()!=null){
					dbBuildingPic.setDbBuilding(dbBuilding);
				}
			}else{
				dbBuildingPic=new DbBuildingPic();
				dbBuildingPic.setDbBuilding(dbBuilding);
			}
		}
		
		if(dbBuildingPic!=null&&dbBuildingPic.getStorey()==null)
		{
			if (request.getParameter("storey")!=null&&StringUtil.isNotEmpty(request.getParameter("storey"))&&!"0".equals(request.getParameter("storey"))) {
				dbBuildingPic.setStorey(Short.parseShort(request.getParameter("storey").toString()));
			}
		}
		//所有类型
		dbBuildingPicTypes=buildingPicBusiness.findPicTypes();
		if (ServletActionContext.getRequest().getParameter("flagMsg")!=null) {
			ServletActionContext.getRequest().setAttribute("errorMsg","图纸超过6兆");
		}
		rc=buildingPicBusiness.findBuildingPic(dbBuildingPic, pageSize,currentPage);
		if (rc.getObj()!=null) {
			dbBuildingPics=(List)rc.getObj();
			page=rc.getPage();
		}else{
			if (ServletActionContext.getRequest().getAttribute("errorMsg")==null) {
				ServletActionContext.getRequest().setAttribute("msg", "暂无数据");
			}
		}
		
		//调用抽取任意提醒需要的数据的方法
//		remindBuildInfoDate();
		
		return "list";
	}
	
	/**
	 * 去至图片列表页面
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String toFindBuilbingPic(){
		//获取所有图片类型
		dbBuildingPicTypes=buildingPicBusiness.findPicTypes();
		return "list";
	}
	
	/**
	 * 获取图片类型，跳转至图片上传页面
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String toUpload(){
		//获取所有图片类型
		dbBuildingPicTypes=buildingPicBusiness.findPicTypes();
		//获取所有楼宇名称和ID	
		RetCode rc=buildingBusiness.findBuildingBySql();
		if(null!=rc && null!=rc.getObj()){
			dbBuildings=(List)rc.getObj();
		}
		return "upload";
	}
	
	/**
	 * ajax 根据楼宇ID 获取楼层数
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String findStorey(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String str=request.getParameter("buildingId");
		int buildingId=null!=str?Integer.parseInt(str):0;
		//获取
		RetCode rc=buildingBusiness.findBuildingStroey(buildingId);
		if(null!=rc && null!=rc.getObj()){
			dbBuildings=(List)rc.getObj();
		}
		//给前台
		HttpServletResponse rsp=ServletActionContext.getResponse();
		PrintWriter out=null;
		Gson g=new Gson();
		rsp.setCharacterEncoding("utf-8");
		try {
			rsp.setContentType("text/plain");
			out= rsp.getWriter();
			out.print(g.toJson(rc.getObj()));
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("BuildingPicAction-->findStorey",e);
			//log.debug(e.getMessage());
		}finally{
			if(null!=out){
				out.flush();
				out.close();
			}
		}
		
		return null;
	}
	
	
	private File[] image;
	private String [] picName;
	private String[] imageFileName;
	private String[] imageContentType;
	/** 上传图片信息
	 * @param statement  
	 * @param        
	 * @return
	 */
	public String upload() {
		HttpServletRequest request=ServletActionContext.getRequest();
		
		//上传图片
		String check=buildingPicBusiness.
			insertBuildingPic(image, imageFileName,picName, dbBuildingPic,request);
		ServletActionContext.getRequest().setAttribute("errorMsg", check);
		return findBuilbingPic(); 		//成功页面  图片列表信息
//		else	
//			return null;		//异常页面
	}
	
	/**
	 * 楼宇图纸下载方法
	 * @param statement  
	 * @param        
	 * @return
	 */
	private String fileUrl;
	private String fileName;
	
	public void buildingPicDownload(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream os = null;
   		InputStream is = null;

		try { 
            os = response.getOutputStream();
            //【Win本地测试时需要的获取的目录】
            //String folder =new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"BuildingPic";
            //String folder = request.getRealPath(File.separator)+ConfigCST.CST_BUILDING_PIC_LOCATION;
			//Linux系统获取Tomcat目录
            String folder =System.getProperty("catalina.base")+"/Logistics_UploadPic/"+"BuildingPic";

			//			String folder = request.getRealPath(File.separator)+ConfigUtil.getConfig("config.properties", "buildingPicLocation");
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
//			if (StringUtil.isEmpty(fileName)) {
//				Random e = new Random();
//				fileName = e.nextInt(10)+".jpg";
//			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			fileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			byte[] buffer = new byte[1024];
			int l = 0;
			while ((l = is.read(buffer)) > 0) {
				os.write(buffer, 0, l);
			}
		} catch (Exception e) {
			log.error("EquipPicAction-->equipPicDownload", e);
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment;filename=error.txt");
			try {
				os.write("下载图纸失败，请重试".getBytes("utf-8"));
			} catch (UnsupportedEncodingException e1) {
				log.error("BuildingPicAction-->buildingPicDownload",e);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				log.error("BuildingPicAction-->buildingPicDownload",e1);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					log.error("BuildingPicAction-->buildingPicDownload",e);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		return "download";

	}
	/**
	 * 下载方法
	 * @param statement  
	 * @param        
	 * @return
	 */
	public InputStream getInputStream(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse res=ServletActionContext.getResponse();
		InputStream in=null;
		try {
			in=new  FileInputStream(new File(request.getRealPath(File.separator)+this.fileUrl));
			//in= ServletActionContext.getServletContext().getResourceAsStream("/"+this.fileName);
		} catch (Exception e) {
			log.debug(request.getRealPath(File.separator)+this.fileUrl);
			log.debug(e);
		}
		return in;
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
		
		
//		int buildingId=null!=str?Integer.parseInt(str):0;
		try {
			
			rc = buildingPicBusiness.findBuildingPic(dbBuildingPic, pageSize,currentPage);
			//获取楼宇图纸sql对象放到Map对象中
			sqlmap  = buildingPicBusiness.getFindBuildingPicSql();
			
			log.debug("***************sqlmap="+sqlmap);
			
			if(rc.getObj()!=null){
				//获取buildingId
				List picList = (List)rc.getObj();
				Map map = (Map)picList.get(0);
				buildingId = Integer.valueOf((map.get("building_id").toString()));
				if(picId==null){
					//获取PicId
					Map areaMap = (Map)dbBuildingPics.get(0);
					Object ob = areaMap.get("pic_id");
					picId=Integer.parseInt(ob.toString());
				}
				
			}
			seqMap.put("buildingId", buildingId);
			seqMap.put("picId", picId);
			
			//（buildingName）
			//提醒方式:1、短信提醒；2、邮件提醒；3、平台内部提醒
			buildingNameMap.put("ariesPreForm", "1,2,3");
			//sql字段名称
			buildingNameMap.put("sqlField", "buildingName");
			//中文字段名
			buildingNameMap.put("fieldNm", "楼宇名称");
			//输入类型 ：1-文本;2-数字;3-时间
			buildingNameMap.put("ariesInputType", 1);
			//sql
			buildingNameMap.putAll(sqlmap);
			//含有多条，或者分页数据是用来对应数据用，数据是查出来的某条数据中有标识性的字段，如seq、ID等
			buildingNameMap.put("sqlFieldKey", seqMap);
			//用来判断数据是否更新的时间字段，通过sql查询出来的结果集中的时间字段等来判断
			buildingNameMap.put("updateDateField", "opertime");
			//用来判断数据是否更新的sql
			buildingNameMap.put("updateFieldSql", "select opertime from ( select rownum rownum_, a.* from "
					+ "(select b.pic_id,b.pic_name,b.opertime,b.storey,b.pic_address, b.inputtime ,"
					+ "(select u.username from db_users u where u.seq=b.oper ) as oper,"
					+ "(select bd.building_name from db_building bd where b.building_id=bd.building_id) as building_name,"
					+ "(select bd.storey_num_up from db_building bd where b.building_id=bd.building_id) as storey_up,"
					+ "(select bd.storey_num_down from db_building bd where b.building_id=bd.building_id) as storey_down ,"
					+ "(select base.content1 from db_base_comm base where base.seq=b.pic_type) as pic_type_name from "
					+ "db_building_pic b where b.status=0  order by b.opertime desc) a)");
//			remindmap.put("initFunc", "testDate");
			
			//（picName）
			picNameMap.put("ariesPreForm", "1,2,3");
			picNameMap.put("sqlField", "picName");
			picNameMap.put("fieldNm", "图片名称");
			picNameMap.put("ariesInputType", 1);
			picNameMap.putAll(sqlmap);
			picNameMap.put("sqlFieldKey", seqMap);
			picNameMap.put("updateDateField", "opertime");
			picNameMap.put("updateFieldSql", "select opertime from ( select rownum rownum_, a.* from "
					+ "(select b.pic_id,b.pic_name,b.opertime,b.storey,b.pic_address, b.inputtime ,"
					+ "(select u.username from db_users u where u.seq=b.oper ) as oper,"
					+ "(select bd.building_name from db_building bd where b.building_id=bd.building_id) as building_name,"
					+ "(select bd.storey_num_up from db_building bd where b.building_id=bd.building_id) as storey_up,"
					+ "(select bd.storey_num_down from db_building bd where b.building_id=bd.building_id) as storey_down ,"
					+ "(select base.content1 from db_base_comm base where base.seq=b.pic_type) as pic_type_name from "
					+ "db_building_pic b where b.status=0  order by b.opertime desc) a)");
			
			//（picTypeName）
			picTypeNameMap.put("ariesPreForm", "1,2,3");
			picTypeNameMap.put("sqlField", "picTypeName");
			picTypeNameMap.put("fieldNm", "图纸类型");
			picTypeNameMap.put("ariesInputType", 1);
			picTypeNameMap.putAll(sqlmap);
			picTypeNameMap.put("sqlFieldKey", seqMap);
			picTypeNameMap.put("updateDateField", "opertime");
			picTypeNameMap.put("updateFieldSql", "select opertime from ( select rownum rownum_, a.* from "
					+ "(select b.pic_id,b.pic_name,b.opertime,b.storey,b.pic_address, b.inputtime ,"
					+ "(select u.username from db_users u where u.seq=b.oper ) as oper,"
					+ "(select bd.building_name from db_building bd where b.building_id=bd.building_id) as building_name,"
					+ "(select bd.storey_num_up from db_building bd where b.building_id=bd.building_id) as storey_up,"
					+ "(select bd.storey_num_down from db_building bd where b.building_id=bd.building_id) as storey_down ,"
					+ "(select base.content1 from db_base_comm base where base.seq=b.pic_type) as pic_type_name from "
					+ "db_building_pic b where b.status=0  order by b.opertime desc) a)");
			
			//（storeyNum）
			storeyNumMap.put("ariesPreForm", "1,2,3");
			storeyNumMap.put("sqlField", "storeyNum");
			storeyNumMap.put("fieldNm", "楼层");
			storeyNumMap.put("ariesInputType", 2);
			storeyNumMap.putAll(sqlmap);
			storeyNumMap.put("sqlFieldKey", seqMap);
			storeyNumMap.put("updateDateField", "opertime");
			storeyNumMap.put("updateFieldSql", "select opertime from ( select rownum rownum_, a.* from "
					+ "(select b.pic_id,b.pic_name,b.opertime,b.storey,b.pic_address, b.inputtime ,"
					+ "(select u.username from db_users u where u.seq=b.oper ) as oper,"
					+ "(select bd.building_name from db_building bd where b.building_id=bd.building_id) as building_name,"
					+ "(select bd.storey_num_up from db_building bd where b.building_id=bd.building_id) as storey_up,"
					+ "(select bd.storey_num_down from db_building bd where b.building_id=bd.building_id) as storey_down ,"
					+ "(select base.content1 from db_base_comm base where base.seq=b.pic_type) as pic_type_name from "
					+ "db_building_pic b where b.status=0  order by b.opertime desc) a)");
			
			//（oper）
			operMap.put("ariesPreForm", "1,2,3");
			operMap.put("sqlField", "oper");
			operMap.put("fieldNm", "上传人");
			operMap.put("ariesInputType", 1);
			operMap.putAll(sqlmap);
			operMap.put("sqlFieldKey", seqMap);
			operMap.put("updateDateField", "opertime");
			operMap.put("updateFieldSql", "select opertime from ( select rownum rownum_, a.* from "
					+ "(select b.pic_id,b.pic_name,b.opertime,b.storey,b.pic_address, b.inputtime ,"
					+ "(select u.username from db_users u where u.seq=b.oper ) as oper,"
					+ "(select bd.building_name from db_building bd where b.building_id=bd.building_id) as building_name,"
					+ "(select bd.storey_num_up from db_building bd where b.building_id=bd.building_id) as storey_up,"
					+ "(select bd.storey_num_down from db_building bd where b.building_id=bd.building_id) as storey_down ,"
					+ "(select base.content1 from db_base_comm base where base.seq=b.pic_type) as pic_type_name from "
					+ "db_building_pic b where b.status=0  order by b.opertime desc) a)");
			
			//（opertime）
			opertimeMap.put("ariesPreForm", "1,2,3");
			opertimeMap.put("sqlField", "opertime");
			opertimeMap.put("fieldNm", "上传时间");
			opertimeMap.put("ariesInputType", 3);
			opertimeMap.putAll(sqlmap);
			opertimeMap.put("sqlFieldKey", seqMap);
			opertimeMap.put("updateDateField", "opertime");
			opertimeMap.put("updateFieldSql", "select opertime from ( select rownum rownum_, a.* from "
					+ "(select b.pic_id,b.pic_name,b.opertime,b.storey,b.pic_address, b.inputtime ,"
					+ "(select u.username from db_users u where u.seq=b.oper ) as oper,"
					+ "(select bd.building_name from db_building bd where b.building_id=bd.building_id) as building_name,"
					+ "(select bd.storey_num_up from db_building bd where b.building_id=bd.building_id) as storey_up,"
					+ "(select bd.storey_num_down from db_building bd where b.building_id=bd.building_id) as storey_down ,"
					+ "(select base.content1 from db_base_comm base where base.seq=b.pic_type) as pic_type_name from "
					+ "db_building_pic b where b.status=0  order by b.opertime desc) a)");

			//将所有需要提醒的数据打包成map
			for (int i = 0; i < dbBuildingPics.size(); i++) {
				remindmapAll.put("buildingName"+i, buildingNameMap);
				remindmapAll.put("picName"+i, picNameMap);
				remindmapAll.put("picTypeName"+i, picTypeNameMap);
				remindmapAll.put("storeyNum"+i, storeyNumMap);
				remindmapAll.put("oper"+i, operMap);
				remindmapAll.put("opertime"+i, opertimeMap);
			}
			
			//将已经打包好的MAP转换成JSON
			JSONObject remindJson = JSONObject.fromObject(remindmapAll);
			
			request.setAttribute("remindJson", remindJson);
			log.debug("    remindJson=   "+remindJson);
		}catch (Exception e) {
			log.error("HospDetailAction-->showHospDetail", e);
		}
		return "show";
	}
	
	public File[] getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
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

	public BuildingPicBusiness getBuildingPicBusiness() {
		return buildingPicBusiness;
	}
	public void setBuildingPicBusiness(BuildingPicBusiness buildingPicBusiness) {
		this.buildingPicBusiness = buildingPicBusiness;
	}

	public DbBuildingPic getDbBuildingPic() {
		return dbBuildingPic;
	}


	public void setDbBuildingPic(DbBuildingPic dbBuildingPic) {
		this.dbBuildingPic = dbBuildingPic;
	}

	public static Log getLog() {
		return log;
	}

	public List getDbBuildingPics() {
		return dbBuildingPics;
	}

	public String[] getPicName() {
		return picName;
	}

	public void setPicName(String[] picName) {
		this.picName = picName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		try {
			this.fileUrl = new String(ServletActionContext.getRequest().getParameter(
			"fileUrl").getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("BuildingPicAction-->setFileUrl",e);
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		try {
			this.fileName = new String(ServletActionContext.getRequest().getParameter(
			"fileName").getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("BuildingPicAction-->setFileName",e);
		}
	}

	public void setDbBuildingPics(List dbBuildingPics) {
		this.dbBuildingPics = dbBuildingPics;
	}

	public List getDbBuildingPicTypes() {
		return dbBuildingPicTypes;
	}

	public void setDbBuildingPicTypes(List dbBuildingPicTypes) {
		this.dbBuildingPicTypes = dbBuildingPicTypes;
	}

	public BuildingBusiness getBuildingBusiness() {
		return buildingBusiness;
	}

	public void setBuildingBusiness(BuildingBusiness buildingBusiness) {
		this.buildingBusiness = buildingBusiness;
	}

	public List getDbBuildings() {
		return dbBuildings;
	}

	public void setDbBuildings(List dbBuildings) {
		this.dbBuildings = dbBuildings;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
}