package com.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class DownLoadPic {
	Logger log = Logger.getLogger(DownLoadPic.class);

	// 请求下载图片链接来生成图片的zip包
	/**
	 * 补充注释,原先的true和false不适用于该场景，所以改为返回编码格式0,表示下载失败，1表示下载成功，2表示图片生成失败，即图片过期
	 * 
	 * @param stationId
	 * @param passTime
	 * @param vehicleOrder
	 * @param vehicleSerial
	 * @param ip
	 * @param port
	 * @param proName
	 * @param picPath
	 * @return
	 */
	public int downLoadPic(String stationId, String passTime, String vehicleOrder, String vehicleSerial, String ip, String port, String proName, String picPath) {
		System.out.println("vehicleOrder:" + vehicleOrder);
		try {
			String contextPath = "http://" + ip + ":" + port + "/" + proName;
			// 先判定文件是否存在，存在则不再请求相关路径(由于TFDS3.0的策略是有请求就会删除当前的压缩图片包并重新生成，
			// 所以在有图片包的情况下不进行请求，直接下载)
			String fileName = "TFDS_" + stationId + "_" + passTime + "_" + vehicleOrder + "_PIC.zip";
			String path = contextPath + "/pictmp/" + fileName;
			URL fileUrl = new URL(path);
			URLConnection fileConnection = fileUrl.openConnection();
			HttpURLConnection fileUrlConnection = (HttpURLConnection) fileConnection;
			int fileRtCode = fileUrlConnection.getResponseCode();
			int returnCode = 0;
			String result = "";
			if (fileRtCode == HttpURLConnection.HTTP_OK) {
				returnCode = 200;
				result = "PIC.zip";
			} else {
				modifyVehicleSort(contextPath, vehicleSerial, "0");
				String createPicPath = contextPath + "/show.do?method=thumbZip&passTime=" + passTime + "&order=" + vehicleOrder + "&detectStationId=" + stationId;
				log.info("图片生成连接:" + createPicPath);
				URL url = new URL(createPicPath);
				URLConnection rulConnection = url.openConnection();
				HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
				InputStream inStrm = httpUrlConnection.getInputStream();
				returnCode = httpUrlConnection.getResponseCode();
				result = inputStream2String(inStrm);
				log.info("图片生成结果:" + result);
				if (result.indexOf("取图失败") > -1) {
					return 2;
				}
			}

			if (returnCode == HttpURLConnection.HTTP_OK) {
				// 当返回内容包含完成字符时,表示压缩包已经做好
				if (result.indexOf("PIC.zip") > -1) {
					HttpDownloader downloader = new HttpDownloader(path, picPath + fileName);// 通过配置文件来判定所在位置
					boolean isFinish = downloader.download();
					modifyVehicleSort(contextPath, vehicleSerial, "4");
					return isFinish ? 1 : 0;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("下载图片出错,错误信息:" + e);
		}
		return 0;
	}

	// 返回数据流转换成文字
	public static String inputStream2String(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}

	/**
	 * 更改vehicleSort
	 * 
	 * @param vehicleSerial
	 * @param vehicleSort
	 */
	public void modifyVehicleSort(String contextPath, String vehicleSerial, String vehicleSort) {
		String url = contextPath + "/tfds/modifyVehicle.jsp?vehicleSerial=" + vehicleSerial + "&vehicleSort=" + vehicleSort;
		URL fileUrl;
		try {
			fileUrl = new URL(url);
			URLConnection fileConnection = fileUrl.openConnection();
			fileConnection.setConnectTimeout(30000);
			HttpURLConnection fileUrlConnection = (HttpURLConnection) fileConnection;
			int fileRtCode = fileUrlConnection.getResponseCode();
			log.info("车次车型修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("车次车型修改失败:" + e);
		}

	}

	public static void main(String[] args) throws Exception {
		String test = "http://10.97.133.82:8278";
		for (int i = 1; i < 55; i++) {
			URL fileUrl = new URL(test + "/tfjz/show.do?method=thumbZip&passTime=20181121225554&order=" + i + "&detectStationId=F38F09F01");
			URLConnection rulConnection = fileUrl.openConnection();
			rulConnection.setConnectTimeout(30000);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
			InputStream inStrm = httpUrlConnection.getInputStream();
			int returnCode = httpUrlConnection.getResponseCode();
			String result = inputStream2String(inStrm);
			test += result.substring(19, result.length() - 11);
			// System.out.println(test);
			HttpDownloader hd = new HttpDownloader(test, "C:\\Users\\Administrator\\Downloads\\JTVPIC\\" + i + ".zip");
			hd.download();
		}
		System.out.println("整列下载完成");
	}
}
