/**
 * 
 */
package com.hanqian.message;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hanqian.util.MultiPropertiesUtils;
import com.hanqian.util.StringUtil;

/**
 * 调用短信接口进行信息发送.
 * 每条发送信息都必须记录在COMM_MSG_SEND表内.
 * 
 * @author eden.cui
 *
 */
@Component
public class SmsMessage  {
	
	public static void main(String[] args) {

		  ResourceBundle resource = ResourceBundle.getBundle("config");
		   String key = resource.getString("sms_url"); 
		   
		   System.out.println(key);
		   
		   SmsMessage.send("dddd", "15365132434");
	}
	

	

	public  static  void send(String msg, String mobile){
		try {
//			WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
//			MultiPropertiesUtils configMessage = context.getBean("configMessage", MultiPropertiesUtils.class);
			
//			if (log.isDebugEnabled()) log.debug("八爪鱼:开始对外发送短信,用户id:{}, 手机号码:{}, 发送内容;{}, 已有记录id:{}", userId, mobile, msg, msid);
			
			  ResourceBundle resource = ResourceBundle.getBundle("config");
			   String key = resource.getString("sms_url"); 

			
//			String url = configMessage.getString("sms_url");
			String url=key;
			if (StringUtil.isEmpty(url)) {
//				log.info("八爪鱼:对外发送短信时,出现从配置文件内取得的短信平台url为空的情况,本次短信发送无法继续.用户id:{}, 待发手机号码:{}, 待发发送内容;{}, 已有记录id:{}", userId, mobile, msg, msid);
				return;
			}
			
			CloseableHttpClient httpclient = HttpClients
					.custom().setDefaultRequestConfig(RequestConfig.custom()
							.setSocketTimeout(60000).setConnectTimeout(20000).setConnectionRequestTimeout(20000).build())
					.build();
			
			HttpPost httppost = new HttpPost(url);
	
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("phoneNumber", mobile));
			list.add(new BasicNameValuePair("content", msg));
			HttpEntity reqEntity = new UrlEncodedFormEntity(list, "UTF-8");
			httppost.setEntity(reqEntity);
			
			try {
				CloseableHttpResponse response = httpclient.execute(httppost);
				Header[] smsResult = response.getHeaders("SmsResult");
				System.out.println(smsResult.length +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+smsResult[0]);
				
				
				if (smsResult != null && smsResult.length > 0 && "1".equals(smsResult[0].getValue())) {
//					log.info("八爪鱼:对外发送短信成功,手机号码:{}, 发送内容;{}", mobile, msg);
//					this.sendOk(SEND_TYPE, userId, msg, key, msid);
				} else {
					HttpEntity entity = response.getEntity();
					StringBuilder builder = new StringBuilder(200);
					try {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
						String text;
						
						while ((text = bufferedReader.readLine()) != null) {
							builder.append(text);
						}
						bufferedReader.close();
	
					} finally {
						response.close();
					}
					
					if (builder.length() > 0) {
//						log.error("八爪鱼:短信发送失败,得到短信发送结果反馈:{}", builder.toString());
//						this.sendError(SEND_TYPE, userId, msg, key, msid);
					}
				}
			} catch (Exception e) {
//				if (log.isDebugEnabled()) log.debug("八爪鱼:向短信平台发送短信时出现异常,将向记录表内写入发送失败记录.", e); 
//				this.sendError(SEND_TYPE, userId, msg, key, msid);
			} finally {
				if (httpclient != null) {
					httpclient.close();
				}
			}
		} catch (Exception e1) {
//			if (log.isDebugEnabled()) log.debug("八爪鱼:向短信平台发送短信时出现异常,将向记录表内写入发送失败记录.", e1); 
//			this.sendError(SEND_TYPE, userId, msg, key, msid);
		}
	}











}
