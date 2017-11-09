package com.hanqian.dataInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hanqian.business.DbControldataBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.util.OSUtil;
import com.hanqian.util.StringUtil;

/**
 * 向sdcd系统读取数据. 本类使用https的双向认证方式连接sdcd,本类需要读取本地的认证文件以取得加密方式. 认证文件读取路径是:
 * {CLASSPATH}/certificate目录. 目录下需要含有client.truststore和client.p12这2个认证文件.
 * <p>
 * 本类使用apache httpClient组件发起http请求.因为httpClient不同版本间api变化较大,现使用版本是4.3.1
 * <p>
 * 一旦sdcd系统瘫痪或者响应不及时,本类会进行超时处理. SocketTimeout=60秒 ConnectTimeout=20秒
 * ConnectionRequestTimeout=20秒
 * <p>
 * sdcd是一个集群型系统,为了不将读取数据的压力集中在一台sdcd机器上, 所以将会以轮询的方式向分别向不同的sdcd读取数据.
 * 本类的构造函数接收sdcd的ip地址集.每一个ip代表一台sdcd运行机器.
 * 如果向某台sdcd机器读取数据出现超时或者其他异常时,本次查询会跳转至下一个sdcd ip进行再次查询,
 * 以避免一台sdcd机器宕机而造成的数据无法读取问题.
 * <p>
 * 每次向sdcd方式需要读取的点位字段,以json数组的形式组成. ["aaa","bbb"]
 * <p>
 * sdcd也会以json的格式返回读取数据, [{"OTIME":"2013-10-16 22:09:18"
 * ,"PROJECT_POINT":"aaa","REAL_DATA":1.000,"CALC_DATA":1.000},{"OTIME":
 * "2013-10-16 22:09:15"
 * ,"PROJECT_POINT":"bbb","REAL_DATA":2.000,"CALC_DATA":2.000}]
 * REAL_DATA代表实际值(设备实际返回值),CALC_DATA代表计算值(经过倍率转换后的值),OTIME代表这条数据产生的时间.
 * <p>
 * sdcd会保证查询多少个点位,一定会返回多少个点位字段. 因为有可能某些设备无法连接,或者查询点位在sdcd中根本不存在,使得点位sdcd无法取到值.
 * 对于这类字段,sdcd的返回数据中,REAL_DATA和CALC_DATA会等于NaN
 * <p>
 * 如果sdcd在处理返回数据过程中出现异常时,会返回异常信息. [{"error":"exception message"}]
 * <p>
 * 在和sdcd的通信过程中,为了减少传输数据量,本类和sdcd都会对发送数据进行压缩 使用StringUtil的
 * {@link com.hanqian.util.StringUtil#toBase64ZipString}和
 * {@link com.hanqian.util.StringUtil#getBase64UnzipString}.
 * 
 * @author Eden.Cui
 */
public class SdcdHttpsCollectData {
	private static final Logger logg = Logger.getLogger(SdcdHttpsCollectData.class);
	private SSLConnectionSocketFactory sslsf;
	private final static String url = "https://#/SDCD/service/realData";
	private final static String sdcdips = ConfigCST.SDCD_IPS;
	private StringBuilder builder = new StringBuilder(1000);

	public SdcdHttpsCollectData() throws Exception {
		logg.info("sdcd取数据开始创建https实现类.");
		
		if (logg.isDebugEnabled())
			logg.debug("sdcd取数据https实现类,sdcd开关:" + ConfigCST.IS_SDCD_OPEN);
		if (!"on".equalsIgnoreCase(ConfigCST.IS_SDCD_OPEN)) {
			logg.info("本系统不需要从SDCD取得点位数据.请参考config.properties");
			return;
		}

		if (logg.isDebugEnabled())
			logg.debug("sdcd取数据https实现类,开始载入数字证书.");

		try {
			// 创建数字证书keystore
			String classpath = StringUtil.getClasspath().getPath();
			if (logg.isDebugEnabled())
				logg.debug("sdcd取数据https实现类,载入数字证书classpath:" + classpath);

			KeyStore trustStore = KeyStore.getInstance("jks");
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			if (logg.isDebugEnabled())
				logg.debug("sdcd取数据https实现类,载入数字证书KeyStore创建结束.");

			// windows的classpath会在前面加一个/符号,需要去掉,linux的时候不需要去这个符号
			if (OSUtil.isWindows()) {
				if (logg.isDebugEnabled())
					logg.debug("sdcd取数据https实现类,本系统是windows.");
				classpath = classpath.substring(1, classpath.length());
			}

			if (logg.isDebugEnabled())
				logg.debug("sdcd取数据https实现类,载入数字证书最终classpath:" + classpath);

			InputStream tsIn = new FileInputStream(new File(classpath + "certificate/client.truststore"));
			InputStream keyIn = new FileInputStream(new File(classpath + "certificate/client.p12"));
			trustStore.load(tsIn, "SH@hanqian".toCharArray());
			keyStore.load(keyIn, "SH@hanqian".toCharArray());

			if (logg.isDebugEnabled())
				logg.debug("sdcd取数据https实现类,载入数字证书完成,开始创建通信类");
			SSLContext sslContexts = SSLContexts.custom().useSSL().loadKeyMaterial(keyStore, "SH@hanqian".toCharArray())
					.loadTrustMaterial(trustStore).build();

			sslsf = new SSLConnectionSocketFactory(sslContexts, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			if (logg.isDebugEnabled())
				logg.debug("sdcd取数据https实现类,创建通信类结束.");
			logg.info("sdcd取数据https实现类创建结束.");
		} catch (Exception e) {
			logg.error("SdcdHttpsCollectData-->SdcdHttpsCollectData", e);
			throw new Exception("创建数字证书时出现异常", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void getData(String value) throws Exception {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		DbControldataBusiness dbControldataBusiness = context.getBean("controlDataBusiness",
				DbControldataBusiness.class);
		// 允许SDCD花费15秒的时候返回数据,主要考虑点位数据较大.
		CloseableHttpClient httpclient = HttpClients
				.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(RequestConfig.custom()
						.setSocketTimeout(60000).setConnectTimeout(20000).setConnectionRequestTimeout(20000).build())
				.build();

		String[] ips = StringUtil.str2array(sdcdips, ",");
		for (int i = 0; i < ips.length; i++) {
			try {

				// 清空builder内的数据
				builder.delete(0, builder.length());

				String finalUrl = url.replace("#", ips[i]);
				logg.debug("向SDCD请求指令为:" + finalUrl + ",查询点位:" + value);

				HttpPost httppost = new HttpPost(finalUrl);

				List<NameValuePair> list = new ArrayList<NameValuePair>();
				list.add(new BasicNameValuePair("field", StringUtil.toBase64ZipString(value)));
				HttpEntity reqEntity = new UrlEncodedFormEntity(list, "UTF-8");
				httppost.setEntity(reqEntity);

				try {
					CloseableHttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					try {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
						String text;

						while ((text = bufferedReader.readLine()) != null) {
							builder.append(text);
						}
						bufferedReader.close();

						// 退出循环,只要向一个sdcd取得数据就可以退出循环,同样如果向第一个ip取得数据出错,则向第二个ip取数据.
						break;
					} finally {
						response.close();
					}
				} catch (Exception e) {
					logg.error("SdcdHttpsCollectData-->getData", e);
					logg.error("向" + httppost.getURI() + "发送指令时出现异常.", e);
				}

			} catch (Exception e) {
				logg.error("向SDCD取得点位数据时出现异常.", e);
			} finally {
				if (httpclient != null) {
					try {
						httpclient.close();
					} catch (IOException e) {
						logg.error("SdcdHttpsCollectData-->getData", e);
					}
				}
			}
		}

		if (builder.length() > 0) {
			try {

				JSONArray array = JSONArray.fromObject(StringUtil.getBase64UnzipString(builder.toString()));
				builder.delete(0, builder.length());

				for (int i = 0; i < array.size(); i++) {
					JSONObject JSONObject = array.getJSONObject(i);
					Map map = new HashMap();

					if (StringUtil.isEmpty(JSONObject.get("PROJECT_POINT"))) {
						logg.error("sdcd取回数据后,出现PROJECT_POINT为空的情况.本条抛弃." + JSONObject);
						continue;
					}

					map.put("PROJECT_POINT", JSONObject.get("PROJECT_POINT"));
					map.put("RECORD", JSONObject.get("CALC_DATA"));
					map.put("RECORDTIME", JSONObject.get("OTIME"));
					logg.debug("从SDCD返回数据写入controldata,本条记录:" + map);

					if ("NAN".equalsIgnoreCase(StringUtil.toString(JSONObject.get("CALC_DATA"))) || "OUT".equalsIgnoreCase(StringUtil.toString(JSONObject.get("CALC_DATA")))) {
						logg.error("从sdcd取回数据为NAN或者此点位在sdcd中不存在,不写入controlldata,点位数据:" + map);
						continue;
					}

					if (dbControldataBusiness.updateData(map) == 0) {
						logg.error("从sdcd取回数据写入controlldata时,写入不成功.点位数据:" + map);
					}
				}

				logg.info("从SDCD返回数据写入controldata完成.写入个数:" + array.size());
				array.clear();
				array = null;
				dbControldataBusiness = null;
			} catch (Exception e) {
				logg.error("从sdcd取回数据写入数据库时出现异常.读入数据:" + builder.toString(), e);
			}
		} else {
			logg.error("向SDCD取数据,但是未得到返回数据,取数据IP:" + StringUtil.arrayToString(ips, ","));
		}
	}

}
