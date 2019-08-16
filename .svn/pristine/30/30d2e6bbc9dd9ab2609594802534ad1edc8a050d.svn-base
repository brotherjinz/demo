package com.web.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class HttpClientUtil {
	private static Logger logger = LogManager.getLogger(HttpClientUtil.class);
	
	//@SuppressWarnings("deprecation")
	public static String sendHttpRequest(String url,Map mapValues) {
		// 构造HttpClient的实例
		String responseBody = null;
		if(url!=null){
			HttpClient httpClient = new HttpClient();
//			httpClient.setConnectionTimeout(Constants.HCU_TIMEOUT);//Tap连接超时
//			httpClient.setTimeout(Constants.HCU_TIMEOUT);//读数据超时
//			httpClient.setHttpConnectionFactoryTimeout(Constants.HCU_TIMEOUT);//连接池超时
			
			// 创建POST方法的实例
			PostMethod postMethod = new PostMethod(url);
			NameValuePair[] postData =new NameValuePair [mapValues.size()];
			
			Iterator iter = mapValues.entrySet().iterator(); 
			int i=0;
			while (iter.hasNext()) { 
				 Map.Entry entry = (Map.Entry) iter.next(); 
				 String key = (String)entry.getKey(); 
				 String val = (String)entry.getValue(); 
				 postData[i]=new NameValuePair(key,val);
				 i=i+1;
			}
			postMethod.setRequestBody(postData);// 将表单的值放入postMethod中
			
			int statusCode = 0;
			try {
				logger.info("prepare send httppost---");
				statusCode = httpClient.executeMethod(postMethod);
				logger.info("httppost send end ----the statusCode="+statusCode);
				if (statusCode ==HttpStatus.SC_OK){
					byte[] resp=postMethod.getResponseBody();
					if(resp!=null && resp.length>0){
						responseBody = new String(resp);
					}else {
						responseBody="";
					}
			    }
			} catch (Exception e) {
				logger.error("Please check your httpClient ERROR-Exception!"+e.getMessage());
				e.printStackTrace();
				responseBody="0";
			} finally {
				// 释放连接
				postMethod.releaseConnection();
			}
		}
		logger.info("***********HTTP CLIENTUTIL"+responseBody);
		return responseBody;
	}
	
	
	/**
	 * 字典推送 通过对方servlet发送list对象
	 * yangyw
	 * @param urlStr 地址URL
	 * @param list 发送list对象
	 * return String //1推送成功 0失败
	 */
	public static String sendPostList(String urlStr,Map<String,List> map ) {
		OutputStream outStrm = null;
		ObjectOutputStream oos = null;
		String result ="0";//1推送成功 0失败
 		try {
			logger.info("sendPostList---"+urlStr);
			URL url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setReadTimeout(600000);//超时时间-两分钟
			urlConn.setConnectTimeout(600000);
			
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestProperty("Content-type",
					"application/x-java-serialized-object");
			urlConn.setRequestMethod("POST");
			urlConn.connect();

			outStrm = urlConn.getOutputStream();
			oos = new ObjectOutputStream(outStrm);

			oos.writeObject(map);

			oos.flush();
			InputStream inStrm = urlConn.getInputStream();

			result = InputStreamTOString(inStrm);
			
		} catch (Exception e) {
			
			logger.error("Please check your HttpClientUtil sendPostList ERROR-Exception!"+e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				outStrm.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public static String InputStreamTOString(InputStream in) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int count = -1;
		while ((count = in.read(data, 0, 1024)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return new String(outStream.toByteArray(), "utf-8");
	}
	
	/**
	 * HTTP get方法 连接测试。
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static boolean getHttpGetstatus(String url)throws Exception{
		HttpClient client = new HttpClient();
		client.setConnectionTimeout(10000);
		client.setTimeout(10000);
		HttpMethod method=new GetMethod(url);  
		int statusCode = client.executeMethod(method);  
		method.releaseConnection();  
		if (statusCode ==HttpStatus.SC_OK){
			return true;
		}else{
			return false;
		}

	}
	
	public static void main(String[] args){
		String url ="http://10.16.6.100:8086/TRANSPORT/servlet/BusinessServlet";
		try {
			System.out.println(getHttpGetstatus(url));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
