package com.hanqian.common;
import java.util.List;



/**
 * 气温倍数化
 * @author clczp
 *
 */
public class WeatherRate {
	
	/**
	 * 倍率
	 * @param list
	 * @return
	 */
	public static float findReate(List<ChartData> list){
		float rate = 1;
		float sum = 0 ;
		if (list != null) {
			for (ChartData obj:list) {
				if(obj!=null && obj.getValue()!=null)
					sum += obj.getValue().floatValue();
			}
			
			rate = sum/list.size();
		}
		
		return rate;
	}
}
