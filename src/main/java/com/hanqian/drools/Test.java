package com.hanqian.drools;

import java.util.Date;

import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.MillisecondInstantPrinter;

import com.hanqian.business.OctopusInitBusibess;

import com.hanqian.pojo.WeatherRt;
import com.hanqian.util.DateUtil;

public class Test {



	public void test(){
	

		
		Date now = DateUtil.getNowDate();

		KnowledgeBase knowBase = OctopusInitBusibess.Base;
		KieSession kSession = knowBase.newKieSession();
		kSession.getAgenda().getAgendaGroup("天气预警").setFocus();

		WeatherRt wr=new WeatherRt();
		wr.setAlarm("");
		wr.setAlarmurl("");
		wr.setInfo("");
		wr.setDt(DateUtil.dateToString(now, "yyyy/MM/dd"));
		wr.setCityId(Integer.parseInt("101010100"));
		wr.setCity("上海");
		wr.setTemp(Double.parseDouble("35.2"));
		wr.setSd("");
		wr.setWd("");
		wr.setWs("");
	
  		wr.setOpTime(now);

		kSession.insert(wr);
        kSession.fireAllRules();
		
		System.out.println("222222222222222222222222222222222222222222222222222");
		
	}
	
	
}
