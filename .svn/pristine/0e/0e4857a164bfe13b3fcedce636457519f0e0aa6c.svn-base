package com.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取数据配置文件
 * 
 * @author yangyw
 * 
 */
public class ParseProperties {

	public static String getLocalProperty(String str) {
		String result = null;
		InputStream ins = ParseProperties.class
				.getResourceAsStream("../../../../config/db_config.properties");
		Properties p = new Properties();

		try {
			p.load(ins);
			result = p.getProperty(str);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String getTransportUrl(String str) {
		String result = null;
		InputStream ins = ParseProperties.class
				.getResourceAsStream("../../../../config/KyblInvokeTransport.properties");
		Properties p = new Properties();

		try {
			p.load(ins);
			result = p.getProperty(str);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String getLocalDatamergeProperty(String str) {
		String result = null;
		InputStream ins = ParseProperties.class
				.getResourceAsStream("../../../../config/data_merge.properties");
		Properties p = new Properties();

		try {
			p.load(ins);
			result = p.getProperty(str);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getLocalProperty(String proname,String str) {
		String result = null;
		InputStream ins = ParseProperties.class
				.getResourceAsStream("../../../../config/"+proname);
		Properties p = new Properties();

		try {
			p.load(ins);
			result = p.getProperty(str);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
