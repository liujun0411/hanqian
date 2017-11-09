package com.hanqian.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;

import com.hanqian.business.NengXiaoBusiness;
import com.hanqian.business.WeatherBusiness;
import com.hanqian.common.BiLiChart;
import com.hanqian.common.ChartData;
import com.hanqian.common.QuYuChart;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.DrawType;
import com.hanqian.util.ETimeStep;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

/**
 * 能效数据分析
 * @author czpsky
 *
 */

public class NengXiaoAction {	
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(NengXiaoAction.class);
	private HttpServletRequest request;
	private NengXiaoBusiness	nengXiaoMgr;	
	//private HospInfoMgr  hospMgr;
	private WeatherBusiness weBs;		//天气情况
	private String menuUrl;
	private String imagePath;			//图片保存位置
	private String useMap;				//显示图片
	
	private Short selObject;			//统计对象	
	private Short  power=2;				//能源类型(1水、2电、3油、4气(医用)、5气(工业用))
	private String startDate;			//开始时间	
	private String endDate;				//结束时间
	private ETimeStep timeStep;			//时间隔
	private String step="month";		//时间间隔(年year、月month)
	private DrawType drawType=DrawType.zheXian;	//图片类型(柱状图 zhuzhuang、折线图zhexian、饼状图bingzhuang)
	private String selUnits;			//统计单元
	private String message;				//提示信息
	private boolean fenxi;				//分析
	private short jiZhun;				//基准
	private Float referenceDeviation;	//基准偏差
	private String title;
	
	/**
	 * 实现对map按照key排序
	 * @param h
	 * @return
	 */
	@SuppressWarnings("unchecked") 
	public Map.Entry[] getSortedHashtableByKey(Map h) {
		Set set = h.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);
				
		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Object key1 = ((Map.Entry) arg0).getKey();
				Object key2 = ((Map.Entry) arg1).getKey();
				return ((Comparable) key2).compareTo(key1);
			}

		});

		return entries;
	} 
	
	
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
	
	
	/**
	 * 删除上一次的图片
	 * 
	 * 从imagePath 读取用上一次该用户生成的该模块图片，并将之删除
	 * @param dbusers
	 */
	private void deleOldIMG(DbUsers dbusers){
		if (null != dbusers ) {
			try {
				String dirPath = request.getRealPath(imagePath).replaceAll("\\", "/");
	    		File myfile = new File(dirPath);
	    		File[] files =myfile.listFiles();
	    		for (int i = 0; i < files.length; i++) {
	    			File f = files[i];
	    			if (f.getName().indexOf("jfreechartNxiao"+dbusers.getUserid()) !=-1) {
	    				f.delete();
					}	    			
	    		}
			} catch (Exception e) {
				logg.error("NengXiaoAction-->deleOldIMG", e);
				// TODO: handle exceptione
			}
		}
	}
	/**
	 * 初始化时间
	 * 
	 * 初始化 按月度/年度 条件查询的时间段
	 * 月度  : yyyy01~ yyyyM
	 * 年度  : yyyy ~ yyyy 
	 */
	private void initiTime(){
		if (null !=step) {
			if (SysUtil.isNull(startDate) && SysUtil.isNull(endDate) ) {
				String  formatstr="";
				String  end="";
				if ("month".equals(step)) {
					formatstr="MM";
					end="01";
				}
				Date now = new Date();
				now.setYear(now.getYear()-1);
				startDate=Systime.DateToString(now, "yyyy")+end;
				now.setYear(now.getYear()+1);
				endDate=Systime.DateToString(now, "yyyy"+formatstr);
			}			
		}		
	}
	
	
		
	
	
	
	/**
	 * 楼宇能耗
	 * 判断Session 是否失效
	 * 查询数据
	 * 输出数据
	 * @return
	 */
	public String showBuildEnergy(){
		request = ServletActionContext.getRequest();
		
		//返回路径
		String returnStr="buildenergy";		
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		if (!this.LoadisFail(dbusers)) {
			request.setAttribute("isfail", "yeas");
			return returnStr;
		}		
		
		//查询数据
		
		
		//输出数据
			
		
		
		return returnStr;
	}
	
	/**
	 * 加载标题
	 */
	private void loadtitle(){
		title = "数据分析——能耗分析";
		if (null != selObject) {
			switch (selObject) {
				case 30001 :
					title="特征区域能效分析——特征区域能耗量";
					break;
				case 30002:
					title="特征区域能效分析——特征区域能耗价值";
					break;
				case 30101:
					title="楼宇能效分析——楼宇能耗量";
					break;
				case 30102:
					title="楼宇能效分析——楼宇能耗价值";
					break;
				case 30201:
					title="业务能效分析——每门诊能耗量 ";
					break;
				case 30202:
					title="业务能效分析——每门诊能耗价值";
					break;
				case 30203:
					title="业务能效分析——每急诊能耗量";
					break;
				case 30204:
					title="业务能效分析——每急诊能耗价值";
					break;
				case 30205:
					title="业务能效分析——每住院能耗量";
					break;
				case 30206:
					title="业务能效分析——每住院能耗价值";
					break;
				case 30302:
					title="区域能效分析——门诊区域能耗量";
					break;
				case 30303:
					title="区域能效分析——急诊区域能耗量";
					break;
				case 30304:
					title="区域能效分析——住院区域能耗量";
					break;
				case 30305:
					title="区域能效分析——医技区域能耗量";
					break;
				case 30306:
					title="区域能效分析——保障区域能耗量";
					break;
				case 30307:
					title="区域能效分析——行政区域能耗量";
					break;
				case 30308:
					title="区域能效分析——科研区域能耗量";
					break;
				case 30309:
					title="区域能效分析——教育区域能耗量";
					break;
				case 30310:
					title="区域能效分析——生活区域能耗量";
					break;
				case 30311:
					title="区域能效分析——车库区域能耗量";
					break;
				case 30312:
					title="区域能效分析——其它区域能耗量";
					break;
				case 30401:
					title="各类设备能效分析——各类设备能耗量";
					break;
				case 30402:
					title="各类设备能效分析——同类设备能耗比较";
					break;
				case 30403:
					title="各类设备能效分析——同类设备维修比较";
					break;
				default :
					title = "数据分析——能耗分析";
					break;
			}
		}
		
	}
	
	/**
	 * 加载主界面
	 * 
	 * @return
	 */
	public String showNengXiao(){
		request = ServletActionContext.getRequest();
		this.loadtitle();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		if (!this.LoadisFail(dbusers)) {
			request.setAttribute("isfail", "yeas");
			return "show";
		}	
		
		//统计单元  "menuid|parentid|unitType" 
		String[] menuids=null ;	
		String parentid=null;
		String unitType=null;	
		
		//医院标题
		DbHospInfo dbhos =null;
		
		try {
			deleOldIMG(dbusers);//删除上一次的图片			
			initiTime();//初始化时间
			
			//解析统计单元 字符串					
			if (!SysUtil.isNull(selUnits)) {				
				String[] units = selUnits.split(",");
				unitType =units[0].substring(units[0].lastIndexOf("|")+1);
				parentid = units[0].substring(units[0].indexOf("|")+1,units[0].lastIndexOf("|"));
				
				if ("yiyuan".equals(unitType)) {
					parentid=units[0].substring(0,units[0].indexOf("|"));
				}
				
				menuids = new String[units.length];
				for (int i = 0; i < units.length; i++) {
					String tmp = units[i];
					menuids[i] = tmp.substring(0,tmp.indexOf("|"));					
				}				
			}else{
				unitType ="yiyuan";				
			}
			
			//医院Id
			if (null == parentid || "0".equals(parentid) ) {
				dbhos = dbusers.getDbHospInfo();
				parentid=dbhos.getSeqHosp().toString();
									
			}
			
			//查询数据
			RetCode rt=new RetCode();
			RetCode rc=new RetCode();
			RetCode rtBase=new RetCode();
			
			//标题
			if (null != parentid) {
				if (null != dbhos) {
					request.setAttribute("tCaption", dbhos.getHospName());
				}
			}
			
			
//			if ("louyu".equals(unitType)) {	//楼宇				
				if (2 == power) {
					//分析基线
					if ((selObject+"").substring(0, 3).equals("301") && fenxi) {
						rtBase =nengXiaoMgr.findBaseLine(startDate, endDate, step,jiZhun);						
					}
					
					if (fenxi) {
						if(selObject==30101){
							rt=nengXiaoMgr.findPowerUseAvg(parentid,menuids,power, startDate, endDate,step,1f);//楼宇能源使用量标准化							
						}else if(selObject==30102){
							rt=nengXiaoMgr.findPowerUseAvg(parentid,menuids,power, startDate, endDate,step,0.8f);//楼宇能源使用量折算价值标准化
						}
					}else{ 
						if (selObject==30101) {
							rt=nengXiaoMgr.findPowerUseB(parentid, menuids,power, startDate, endDate,step,1f);//楼宇能源使用量
						}else if(selObject==30102){
							rt=nengXiaoMgr.findPowerUse(parentid, menuids,power, startDate, endDate,step,0.8f);//楼宇能源使用量折算为价值
						}else if(selObject==30201){
							rt=nengXiaoMgr.findOutpatientsPowerAvg(parentid, power, startDate, endDate,step,1,1f); //门诊量平均能耗
							rc=nengXiaoMgr.findOutpatientsPower(parentid, power, startDate, endDate,step,1,1f);    //门诊量能耗							
						}else if(selObject==30202){
							rt=nengXiaoMgr.findOutpatientsPowerAvg(parentid, power, startDate, endDate,step,1,0.8f); //门诊量平均能耗折算价值
							rc=nengXiaoMgr.findOutpatientsPower(parentid, power, startDate, endDate,step,1,0.8f);    //门诊量能耗折算价值
						}else if(selObject==30203){
							rt=nengXiaoMgr.findOutpatientsPowerAvg(parentid, power, startDate, endDate,step,2,1f); 	//急诊量平均能耗
							rc=nengXiaoMgr.findOutpatientsPower(parentid, power, startDate, endDate,step,2,1f);		//急诊量平均能耗
						}else if(selObject==30204){
							rt=nengXiaoMgr.findOutpatientsPowerAvg(parentid, power, startDate, endDate,step,2,0.8f); //急诊量平均能耗折算价值
							rc=nengXiaoMgr.findOutpatientsPower(parentid, power, startDate, endDate,step,2,0.8f);	//急诊量平均能耗折算价值
						}else if(selObject==30205){
							rt=nengXiaoMgr.findOutpatientsPowerAvg(parentid, power, startDate, endDate,step,3,1f); 	//住院量平均能耗
							rc=nengXiaoMgr.findOutpatientsPower(parentid, power, startDate, endDate,step,3,1f);		//住院量平均能耗
						}else if(selObject==30206){
							rt=nengXiaoMgr.findOutpatientsPowerAvg(parentid, power, startDate, endDate,step,3,0.8f); //住院量平均能耗折算价值
							rc=nengXiaoMgr.findOutpatientsPower(parentid, power, startDate, endDate,step,3,0.8f);	//住院量平均能耗折算价值
						}
						
					}
				}				
//			}
//			if ("yiyuan".equals(unitType) && !fenxi && selObject==30101 ) {
//				rc =nengXiaoMgr.findOutpatientsPowerByHospital(parentid, power, startDate, endDate,step);
//			}	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			List rtlist =  (List)rt.getObj();
			List rclist = (List)rc.getObj();
			List balist = (List)rtBase.getObj();
			
			
			
			
			
			
			
			
			//提示信息
			if (null == rtlist && null == rclist) {
				request.setAttribute("message", "暂无数据");
			}
			
			int width=650,heigth=400;			
			

			
			//数据展式	
			HashMap<String,HashMap> table3D= new HashMap<String,HashMap>();		//数据
			HashMap tableclm = new HashMap();									//字段
			if (rtlist != null) {				
				//图形
				String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				String imgpath = request.getRealPath(imagePath) +"\\jfreechartNxiao"+ dbusers.getUserid()+name+".png";
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				if (nengXiaoMgr.saveDraw(drawType, rtlist, balist, imgpath.replaceAll("\\", "/"),width,heigth,info)) {
					useMap = imagePath+"jfreechartNxiao"+ dbusers.getUserid()+name+".png";
					useMap = useMap.replaceAll("\\", "/");
					
					//map热点
					String usemap0=ChartUtilities.getImageMap("usemap0", info);
					request.setAttribute("usemap0", usemap0);
				}
				
				//基线重组
				HashMap baseMap = new HashMap();
				if(null != balist){
					for (int i = 0; i < balist.size(); i++) {
						HashMap m = (HashMap)balist.get(i);
						baseMap.put(m.get("type").toString(), m.get("value"));
					}
				}
				
				//四维表格   (value,name,type)  {[type, {[name, {[value,color]} ]} ]}  
				HashMap tmp= null;
				String year=null;
				for (int i = rtlist.size()-1; i >=0; i--) {
					tmp =(HashMap)rtlist.get(i);
					year = tmp.get("type").toString();
					if (year.length()==4) {
						year = Systime.DateToString((Systime.StringToDate(year, "yyyy")), "yyyy年");
					}else{
						year = Systime.DateToString((Systime.StringToDate(year, "yyyyMM")), "yyyy年MM月");
					}
					//字段
					Object clmobj = tableclm.get(tmp.get("name").toString());
					if ( null == clmobj) {
						tableclm.put(tmp.get("name").toString(), true);
					}
					
					//时间 列 (行唯一标识:时间)( 时间,HashMap)    --- 享元模式获取行 map
					HashMap<String,HashMap> rlm= table3D.get(year);
					if (null ==rlm ) {
						rlm = new HashMap<String,HashMap>();
						table3D.put(year, rlm);   
					}
					
					//其它列值 (该行单元格唯一标识:列名)(列名,HashMap)  --- 享元模式获取当前行中的其它 列 map 
					HashMap<String,Object> cobj  = rlm.get(tmp.get("name").toString()); 
					if (null ==cobj ) {
						cobj = new HashMap<String,Object>();
						rlm.put(tmp.get("name").toString(), cobj);
					}
					
						
					// 当前行指定列值
					cobj.put("value", tmp.get("value"));

					// 高亮数据 color 值
					if (null != baseMap.get(year)  && null !=referenceDeviation) {
						try {
							Double old = Double.parseDouble(tmp.get("value").toString());
							Double now = Double.parseDouble(baseMap.get(year).toString());

							if (old > (now + now * referenceDeviation) || old < (now - now * referenceDeviation)) {
								cobj.put("color", true);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}

					}				
								
				}				
				
			}
			if (rclist != null) {
				//图形
				String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				String imgpath = request.getRealPath(imagePath) +"\\jfreechartNxiao"+ dbusers.getUserid()+name+"2.png";
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				if (nengXiaoMgr.saveDraw(DrawType.zhuZhuang, rclist,null, imgpath.replaceAll("\\", "/"),width,heigth,info)) {
					String useMap2 = imagePath+"jfreechartNxiao"+ dbusers.getUserid()+name+"2.png";
					useMap2 = useMap2.replaceAll("\\", "/");
					
					request.setAttribute("useMap2", useMap2);
					//map热点
					String usemap1=ChartUtilities.getImageMap("usemap1", info);
					request.setAttribute("usemap1", usemap1);
				}
				//三维表格 (value,name,type)
				HashMap<String,Object> tmp= null;
				String year = null;
				for (int i = rclist.size()-1; i >=0; i--) {
					tmp =(HashMap)rclist.get(i);					
					year = tmp.get("type").toString();
					if (year.length()==4) {
						year = Systime.DateToString((Systime.StringToDate(year, "yyyy")), "yyyy年");
					}else{
						year = Systime.DateToString((Systime.StringToDate(year, "yyyyMM")), "yyyy年MM月");
					}
					//字段
					Object clmobj = tableclm.get(tmp.get("name").toString());
					if ( null == clmobj) {
						tableclm.put(tmp.get("name").toString(), true);
					}
					
					//时间 列 (行唯一标识:时间)( 时间,HashMap)    --- 享元模式获取行 map
					HashMap<String,HashMap> rlm= table3D.get(year);
					if (null ==rlm ) {
						rlm = new HashMap<String,HashMap>();
						table3D.put(year, rlm);   
					}
					
					//其它列值 (该行单元格唯一标识:列名)(列名,HashMap)  --- 享元模式获取当前行中的其它 列 map 
					HashMap<String,Object> cobj  = rlm.get(tmp.get("name").toString()); 
					if (null ==cobj ) {
						cobj = new HashMap<String,Object>();
						rlm.put(tmp.get("name").toString(), cobj);
					}
					
						
					// 当前行指定列值
					cobj.put("value", tmp.get("value"));
				}		
				
			}
			
			//排序后返回
			request.setAttribute("table3D",getSortedHashtableByKey(table3D));
			request.setAttribute("tableclm",getSortedHashtableByKey(tableclm));
			if (null != referenceDeviation) {
				if (referenceDeviation>0) {
					request.setAttribute("rd", (float)referenceDeviation*100);
				}
			}
			
			
			
			
			if ( rtlist !=null) {
				//天气情况
				RetCode rw = weBs.findAvgWeather(request.getRealPath(imagePath),imagePath);
				request.setAttribute("wobj", rw.getObj());
			}
			
					
			
		} catch (Exception e) {
			logg.error("NengXiaoAction-->showNengXiao", e);
			// TODO: handle exception
			
		}	
		
		
		return "show";
	}

	
	/**
	 * 能效菜单
	 * @return
	 */
	public String showMenu(){
		request = ServletActionContext.getRequest();
		DbUsers dbUsers = (DbUsers) request.getSession().getAttribute("users");
		String hospid=request.getParameter("hospid");
		if (null != dbUsers && null ==dbUsers.getDbHospInfo() && hospid==null) {
			hospid="sk";			
		}
		
		
		if (null != hospid && "sk".equals(hospid)) {
			//菜单树
			RetCode rt = nengXiaoMgr.findNengXiaoMenu();
			request.setAttribute("menulist", rt.getObj());
		}else{
			
			if (null !=dbUsers) {
				hospid =dbUsers.getDbHospInfo().getSeqHosp().toString();
				
				//菜单树
				RetCode rt = nengXiaoMgr.findNengXiaoMenu(hospid);
				request.setAttribute("menulist", rt.getObj());		
			}
		}
		
		return "menu";		
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}




	public NengXiaoBusiness getNengXiaoMgr() {
		return nengXiaoMgr;
	}


	public void setNengXiaoMgr(NengXiaoBusiness nengXiaoMgr) {
		this.nengXiaoMgr = nengXiaoMgr;
	}


	public WeatherBusiness getWeBs() {
		return weBs;
	}


	public void setWeBs(WeatherBusiness weBs) {
		this.weBs = weBs;
	}


	public String getMenuUrl() {
		return menuUrl;
	}


	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getUseMap() {
		return useMap;
	}


	public void setUseMap(String useMap) {
		this.useMap = useMap;
	}


	public Short getSelObject() {
		return selObject;
	}


	public void setSelObject(Short selObject) {
		this.selObject = selObject;
	}


	public Short getPower() {
		return power;
	}


	public void setPower(Short power) {
		this.power = power;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public ETimeStep getTimeStep() {
		return timeStep;
	}


	public void setTimeStep(ETimeStep timeStep) {
		this.timeStep = timeStep;
	}


	public String getStep() {
		return step;
	}


	public void setStep(String step) {
		this.step = step;
	}


	public DrawType getDrawType() {
		return drawType;
	}


	public void setDrawType(DrawType drawType) {
		this.drawType = drawType;
	}


	public String getSelUnits() {
		return selUnits;
	}


	public void setSelUnits(String selUnits) {
		this.selUnits = selUnits;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean isFenxi() {
		return fenxi;
	}


	public void setFenxi(boolean fenxi) {
		this.fenxi = fenxi;
	}


	public short getJiZhun() {
		return jiZhun;
	}


	public void setJiZhun(short jiZhun) {
		this.jiZhun = jiZhun;
	}


	public Float getReferenceDeviation() {
		return referenceDeviation;
	}


	public void setReferenceDeviation(Float referenceDeviation) {
		this.referenceDeviation = referenceDeviation;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * 生成图片
	 * @param drewtype	图片类形
	 * @param datalist	数据集
	 * @param baselist	基准数据集
	 * @param imagePath	图片保存地址
	 * @return
	 */
	public boolean saveDraw(DrawType drawType,List datalist,List baselist,String imagePath,int width,int height,ChartRenderingInfo info){		
		JFreeChart chart = (new QuYuChart(datalist,baselist)).getChart(drawType);
		try {			
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height,info);				
			fileout.close();
			
			return true;
		} catch (Exception e) {
			logg.error("NengXiaoMgr-->boolean saveDraw", e);
		}
		
		return false;
	}
	
	public void saveDraw(List<ChartData> list,ChartRenderingInfo info,int width,int height,String imagePath,String zChart){
		JFreeChart chart = (new BiLiChart(list,null)).getChart();
		try {			
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height,info);				
			fileout.close();
		} catch (Exception e) {
			logg.error("NengXiaoMgr-->void saveDraw", e);
		}
	}
	
	
	
	

}
