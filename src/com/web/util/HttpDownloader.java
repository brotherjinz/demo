package com.web.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;


public class HttpDownloader  {
	private Logger log = Logger.getLogger(HttpDownloader.class);
	private String destUrl;
	private String savePath;

	public HttpDownloader(String destUrl, String savePath) {
		this.destUrl = destUrl;
		this.savePath = savePath;
	}

	/**
	 * 开始下载
	 * @throws Exception 
	 */
	public boolean download() throws Exception {
		log.info("下载文件" + destUrl + ",保存路径=" + savePath);
		long beginTime = System.currentTimeMillis();
		boolean ok = true;
		HttpClient client = new HttpClient();
		GetMethod httpGet = new GetMethod(destUrl);
		
		File file = new File(savePath);
		if(!file.exists()) file.createNewFile();
		long position = file.length();
		httpGet.addRequestHeader("Range", "bytes="+position+"-");
		try {
			int result = client.executeMethod(httpGet);
			InputStream in = httpGet.getResponseBodyAsStream();
			//文件指针读取写入的类,可以参考jdk_api
			RandomAccessFile writeFile = new RandomAccessFile(file, "rw");
			writeFile.seek(position);

			byte[] b = new byte[1024];
			int len = 0;
			long total = 0;
			while ((len = in.read(b)) != -1) {
				writeFile.write(b, 0, len);
				total += len;
				log.info("已下载:" + total / 1024 + "KB");
			}
			in.close();
			writeFile.close();

		} catch (HttpException e) {
			e.printStackTrace();
			ok = false;
		} catch (IOException e) {
			e.printStackTrace();
			ok = false;
		} finally {
			httpGet.releaseConnection();
		}
		log.info("下载完成，耗时:" + (System.currentTimeMillis() - beginTime) / 1000
				+ "秒");
		return ok;
	}
	public static void main(String[] args) throws Exception {
		HttpDownloader downLoader = new HttpDownloader("http://dxcy.cr173.com/xl/jdk_api_1_6_zh_cn.zip", "D:\\jdk_api_1_6_zh_cn.zip");
		downLoader.download();
	}
}
