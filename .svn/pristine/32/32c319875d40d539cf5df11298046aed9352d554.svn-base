package com.hanqian.drools;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hanqian.business.DroolsBusiness;
import com.hanqian.message.SmsMessage;
import com.hanqian.pojo.Watthourmeter;

/**
 * @author lizhenhua
 *  规则类，》=35度时，读取所有总表的电流，取今日最大电流值
 */

public class WeatherSendsms {
	private static final Log log = LogFactory.getLog(WeatherSendsms.class);
	
	private SmsMessage smsMessage;
	
	@Autowired
	private DroolsBusiness droolsBusiness;
 
	/**
	 * @param rulename  规则名，规则分组名
	 */
	public void selectrecord(String rulename){
		System.out.println("进入发送消息");

		try{}catch(Exception e){
			
			log.error("初始化加载规则drl文件时出现异常", e);
		}
		
//		 1  查询 电表对应峰值  	
		Watthourmeter watthourmeter=droolsBusiness.selectrecord();
	
		//2查询本规则是否发送过
		Boolean flag=droolsBusiness.selectdroolssend(rulename);
		
		if(flag){//没发送过
		//   3   查询发送人员电话列表  已有方法	
		List<String>list=	droolsBusiness.selectmoble(rulename);
			if(list!=null &&list.size()>0){
				
				//4获取消息内容			
				String msg=droolsBusiness.selectsms("天气预警_35度");
		
		
//				高温天气安全用电提示：@贵院总表今日#电流峰值为[#]A()#。@请注意用电安全并合理调配用电负荷。   

				if(watthourmeter.getRecord() == null || watthourmeter.getRecord()  ==0){
					
					 StringBuffer buffer = new StringBuffer(msg);
					msg=buffer.replace(msg.indexOf("@"), msg.lastIndexOf("@")+1, "").toString();
				 
				}else{
					msg=msg.replace("@", "");
					StringBuffer buffer = new StringBuffer(msg);
					if(watthourmeter.getEquipname() !=null && !watthourmeter.getEquipname().trim().equals("")){
						msg=buffer.replace(msg.indexOf("(")+1, msg.lastIndexOf(")"), watthourmeter.getEquipname()).toString();
					}else{
						
						msg=buffer.replace(msg.indexOf("(")+1, msg.lastIndexOf(")"), "").toString();
					}
					if(watthourmeter.getRecordtime()!=null &&!watthourmeter.getRecordtime().trim().equals("")){
						msg=msg.replaceFirst("#", watthourmeter.getRecordtime()+"");	
					}else{
						msg=msg.replaceFirst("#", "");	
					}
					msg=msg.replace("[#]",watthourmeter.getRecord()+"");
					
					if(watthourmeter.getProportion() != null && !watthourmeter.getProportion().trim().equals("")){
//						msg=buffer.replace(msg.indexOf("["), msg.lastIndexOf("]")+1, ",占比"+watthourmeter.getProportion()).toString();
						
						msg= msg.replace("#",  ",占比"+watthourmeter.getProportion());
					}else{
						msg= msg.replace("#","");
					}
				}
			
				
				System.out.println(msg);
				//5发送消息接口
				for(int i=0;i<list.size();i++){
					
					smsMessage.send(msg, list.get(i));
				}
				//6修改本规则下的日期
				droolsBusiness.updaterule(rulename);
				
			}
		}
	}
	
	
	
	
	
	
	

}
