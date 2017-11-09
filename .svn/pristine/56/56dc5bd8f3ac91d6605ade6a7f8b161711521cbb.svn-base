package com.hanqian.quartz.jobs;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.hanqian.util.StringUtil;

/**
 * 定时清理saiku项目下的mondrian缓存
 * 因为mondrian自身无法判断缓存与数据库是否同步,
 * mondrian对外仅仅提供api可以清理缓存,所以此操作由本类执行.
 * 数据仓库中每天进行一次etl操作,所以缓存清理的操作也仅仅需要一天一次就可以.
 * 现在定位每天5点,配合医院上班前,进行缓存清理工作.
 * 
 * 通过http调用saiku提供的清理缓存接口.
 * 如果在配置文件中未制定saiku的ip和port,本类默认使用localhost:8080
 * 
 * saiku中必须含有admin用户,参考/saiku/WEB-INF/users.properties文件,否则本类调用将出错.
 * @author Eden.Cui
 * @version 1.4 2014年2月26日
 * @see
 */
public class SaikuMondrianClearCache {

	private static final Logger logg = Logger.getLogger(SaikuMondrianClearCache.class);
	
	/**
	 * 清理saiku缓存调用的url模版
	 */
	private static final String url = "http://?:@/saiku/rest/saiku/admin/discover/refresh";
	
	/**
	 * saiku项目所在ip,默认为localhost
	 */
	private String ip = "localhost";
	
	/**
	 * saiku项目所在端口,默认为8080
	 */
	private String port = "8080";
	
	public void run () {
		logg.debug("开始进行mondrian缓存清理.");
		
		try {
			
			//saiku需要认证
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
	        credsProvider.setCredentials(
	                new AuthScope(ip, StringUtil.getIntValue(port)),
	                new UsernamePasswordCredentials("admin", "admin"));
	        
			CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCredentialsProvider(credsProvider)
	                .build();
			
			String getUrl = url.replace("?", ip).replace("@", port);
			logg.debug("调用saiku的清理缓存URL:" + getUrl);
			logg.info("调用saiku的清理缓存URL:" + getUrl);
			HttpGet httpget = new HttpGet(getUrl);
			
			CloseableHttpResponse response = httpclient.execute(httpget);
			logg.info("调用saiku清理缓存机制成功.清理缓存的返回结果是:" + EntityUtils.toString(response.getEntity()));
			
		} catch (Exception e){
			logg.error("清理saiku mondrian缓存时出现异常.", e);
		}
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
}
