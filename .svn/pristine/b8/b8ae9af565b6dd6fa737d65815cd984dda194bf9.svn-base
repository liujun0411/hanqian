package com.hanqian.business;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.Dbrule;
import com.hanqian.pojo.Watthourmeter;

@Service("DroolsBusiness")
public class DroolsBusiness extends BaseBusiness{

	private static final Logger log = Logger.getLogger(DroolsBusiness.class);
	
	
	
	
	
	/**
	 * 查询今日所有总表峰值
	 * 
	 * @param apply
	 */
	public Watthourmeter  selectrecord() {
		
		 List<Watthourmeter> list=this.sqlSession.selectList("selectrecord",null);
		 
		 
		 
		 Watthourmeter w=new Watthourmeter();
		 if(list !=null &&list.size()>0   ){
			 Watthourmeter ww= list.get(0);
			 //项目名称
			 w.setEquipname(ww.getEquipname());
			 //峰值
			 w.setRecord(ww.getRecord());
			 //时间
			 
		
			 
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  Date date;
			try {
				date = sdf.parse(ww.getRecordtime());
				  sdf = new SimpleDateFormat("HH:mm");
					 w.setRecordtime(sdf.format(date));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				w.setRecordtime("");
			}
//			ww.setParamvalue("5000");
			 
			 if(ww.getParamvalue() !=null && !ww.getParamvalue().trim().equals("") && ww.getRecord() != 0){
				 Double  proportion=	 DroolsBusiness.div(ww.getRecord(), Double.valueOf(ww.getParamvalue()), 2);
				 NumberFormat nFromat = NumberFormat.getPercentInstance();
				 String rates = nFromat.format(proportion);
	           w.setProportion(rates);
			 } 
		 }
		return w;
	}
	
	public static Double div(Double v1, Double v2, int scale) {  
		   if (scale < 0) {  
		    throw new IllegalArgumentException(  
		      "The scale must be a positive integer or zero");  
		   }  
		   BigDecimal b1 = new BigDecimal(v1.toString());  
		   BigDecimal b2 = new BigDecimal(v2.toString());  
		   return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());  
		}  
		 
	
	
	
	/**
	 * 查询本规则是否发送过
	 * 
	 * @param apply
	 */
	public Boolean selectdroolssend(String rulename) {
		String s=this.sqlSession.selectOne("selectdroolssend",rulename);
		if(s!=null && !s.equals("")){
			return false; //发送过
			
		}else{
			return true;
		}
	}
	/**
	 * 当前规则下的员工列表
	 * 
	 * @param apply
	 */
	public List<String> selectmoble(String rulename) {
		return this.sqlSession.selectList("selectmoble",rulename);
	}
	
	
	/**
	 * 消息模板
	 * 
	 * @param apply
	 */
	public String selectsms(String control_point) {
		return this.sqlSession.selectOne("selectsms",control_point);
	}
	
	
	
	
	
	/**
	 * 插入规则
	 * 
	 * @param apply
	 */
	public void updaterule(String rulename) {

		sqlSession.update("updaterule", rulename);
	}

	
	
	
	/**
	 * 插入关系表
	 * 
	 * @param apply
	 */
	public void insertindex(String sle, String []dlId) {
		Map<String,Object> map=new HashMap<String,Object>();
		  map.put("ruleseq", sle);
		  
		  sqlSession.delete("deleteDbruleindex", map);    
		  
		  if(dlId != null && dlId.length>0){
		  
		if(sle!= null && Integer.valueOf(sle) !=0 && !sle.equals("")){
			
			for(int i=0;i<dlId.length;i++){
				   map.put("sermainseql", dlId[i]);
//				
				   Dbrule Dbrule= sqlSession.selectOne("selectDbruleindexone", map);
					 if(Dbrule == null){
						 sqlSession.insert("insertDbruleindex", map);    
					 }
		  
			}	
			
		}
		  }
		
	}

	
	/**
	 * 插入规则
	 * 
	 * @param apply
	 */
	public void insertdrools(Dbrule dbrule) {

		sqlSession.insert("insertDbrule", dbrule);
	}

	
	/**
	 * 查询规则
	 * 
	 * @param apply
	 */
	public List<String> selectList(Dbrule dbrule) {

		return this.sqlSession.selectList("finddbruleall",dbrule);
	}
	
	
	/**
	 * 查询规则关系表对应下的人员
	 * 
	 * @param apply
	 */
	public List<Dbrule> selectDbruleindex(Dbrule sermainseql) {

		return this.sqlSession.selectList("selectDbruleindex",sermainseql);
	}

	/**
	 * 查询规则关系表对应下的人员
	 * 
	 * @param apply
	 */
	public List<Dbrule> selectDbrulenotindex(Dbrule dbrule) {

		return this.sqlSession.selectList("selectDbrulenotindex",dbrule);
	}
	
	
	
	/**
	 * 查询规则
	 * 
	 * @param apply
	 */
	public List<Dbrule> finddbrulealldbrule(Dbrule dbrule) {

		return this.sqlSession.selectList("finddbrulealldbrule",dbrule);
	}
	
	
}
