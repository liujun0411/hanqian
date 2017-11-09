package com.hanqian.drools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.hanqian.business.DroolsBusiness;
import com.hanqian.message.SmsMessage;
import com.hanqian.pojo.Dbrule;

public class FileUtil implements InitializingBean{

	@Value("classpath*:rules/*.drl")
	private Resource[] ruleDrlResources;
	
	@Autowired
	private DroolsBusiness droolsservice;
//	@Autowired
//	private SmsMessage s;
	
	
	public  void test() throws IOException {
		
		
		
		
		
		if (ruleDrlResources != null) {
			
			//存储数据，放入数据库
			List<String> list=new ArrayList<>();
			
			//调取已经存入数据库中的规则名
			Dbrule dbrule=new Dbrule();
			dbrule.setRuletype("weather");
		

			for (int i = 0; i < ruleDrlResources.length; i++) {
		
				System.out.println("第"+i+"个文件");
		
				InputStream	fiel=	ruleDrlResources[i].getInputStream();	
				
				 InputStreamReader reade = new InputStreamReader(fiel);  
							
			//				BufferedReader reader = new BufferedReader(reader);
							//读取一个文本的字符流		
					BufferedReader in = new BufferedReader(reade);
					String line = null;
					//定义一个空字符串来接受读到的字符串
					String str="";
					//循环把读取到的字符赋给str
					while((line = in.readLine())!=null)
					{
					 str+=line;
					}
						//判断str中是否有EFG子串，为true则说明有。进去if语句
						if(str.contains("agenda-group")){
						
				             String[] group= str.split("agenda-group");
				             
				               for(String ss:group){
				//            	   System.out.println(ss); 
				            		if(ss.contains("when")){
				            			
				            			String agenda =ss.substring(0, ss.indexOf("when")).trim();
				            			String agendas= agenda.substring(1, agenda.length()-1);
				            			
				            							            			List<String> list1=droolsservice.selectList(dbrule);
 				            			
				            				
				            			if(!list1.contains(agendas)){
				            				dbrule.setRulename(agendas);
				            				droolsservice.insertdrools(dbrule);
				            			}
				            			
				            			
				            		}   
				            		
				               }
						}
			}			
			
		}
		
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		  test();
		  
	
	}
	
	
}
