/**
 * Filename：Time.java
 * Created by: Administrator
 * Created on: 2011-4-27 下午04:06:39
 * Last modified by：$Author$
 * Last modified on: $Date$
 * Revision: $Revision$
 */
package com.hanqian.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Systime {

	/**
	 * 获取当前系统时间+星期几	
	 * @param 2013-4-23  
	 * @param @return       
	 * @return String
	 */
	public static String thisSysDate(){
		return Systime.DateToString(new Date(), "yyyy年MM月dd日")+DateUtil.getWeekStr(DateUtil.getStringDate());
	}
	
	/**
	 * DESCR: 时间格式转换
	 * @param Date 时间
	 * @param String 时间格式
	 * @return String 
	 * */
    public static String DateToString(Date date,String str){
        SimpleDateFormat sdf=new SimpleDateFormat(str);
        String s=sdf.format(date);
        return s;
    }
    
    /**
	 * DESCR: 时间格式转换
	 * @param String 时间
	 * @param String 时间格式
	 * @return Date 
	 * */
    public static Date StringToDate(String str,String s){
        SimpleDateFormat sdf=new SimpleDateFormat(s);
        try {
            Date date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Descr:获取当前时间14位
     * */
    public static String date2str14() {

		SimpleDateFormat theFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return theFormat.format(new Date());
	}
    
}
