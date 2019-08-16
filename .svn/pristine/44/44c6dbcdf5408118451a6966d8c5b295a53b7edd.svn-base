package com.web.info.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.info.args.ConfigArgs;
import com.web.info.args.ScheduleRecordArgs;
import com.web.info.args.UtilArgs;
import com.web.info.dao.ConfigDao;
import com.web.info.dao.FaultDao;
import com.web.info.dao.ScheduleJobDao;
import com.web.info.dao.ScheduleRecordDao;
import com.web.info.dto.ScheduleRecordDto;
import com.web.info.dto.UtilDto;
import com.web.info.model.Config;
import com.web.info.model.ScheduleRecord;
import com.web.info.service.ImageDataService;
import com.web.info.service.TrainService;
import com.web.util.DateUtil;
import com.web.util.HttpDownloader;

@Service
public class ImageDataServiceImpl implements ImageDataService {

	@Autowired
	private FaultDao faultDao;
	@Autowired
	private ScheduleRecordDao scheduleRecordDao;
	@Autowired
	private TrainService trainService;
	@Autowired
	private ConfigDao configDao;
	@Autowired
	private ScheduleJobDao jobDao;
	private static Logger log = Logger.getLogger(ImageDataServiceImpl.class);

	@Override
	public boolean imageData() {
		boolean rt = false;
		String path = "";
		ScheduleRecordArgs args = new ScheduleRecordArgs();
		UtilArgs utilargs = new UtilArgs();
		// 查询记录表，去其中IMG_STATE状态为0的数据
		args.setIMG_STATE(0);
		List<ScheduleRecordDto> sr = scheduleRecordDao.queryList(args);
		if (sr != null && sr.size() != 0) {
			List<String> list = new ArrayList<String>();
			for (ScheduleRecordDto scheduleRecordDto : sr) {
				list.add(scheduleRecordDto.getFAULT_SERIAL());
			}
			utilargs.setStrList(list);
			utilargs.setTF(true);
			// 取到的故障唯一号用来查询完整数据
			List<UtilDto> flist = faultDao.queryFaultData(utilargs);
			try {
				ConfigArgs argscfg = new ConfigArgs();
				for (UtilDto utilDto : flist) {
					argscfg.setSTATIONCODE(utilDto.getSTATION_ID());
					Config cfg = configDao.querycfg(argscfg);
					String url = cfg.getPATHURL();// "http://10.97.134.132:8278/";
					path = cfg.getPATHFOLDER();
					String uri = url + "/tfjz/show.do?method=thumbZip&passTime=" + utilDto.getPASS_TIME() + "&order=" + utilDto.getVEHICLE_ORDER() + "&detectStationId=" + utilDto.getSTATION_ID() + "";
					URL fileUrl = new URL(uri);
					URLConnection rulConnection = fileUrl.openConnection();
					rulConnection.setConnectTimeout(30000);
					HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
					InputStream inStrm = httpUrlConnection.getInputStream();
					int returnCode = httpUrlConnection.getResponseCode();
					String result = inputStream2String(inStrm);
					String resultstr = result.substring(0, 4);
					if (resultstr.equals("取图失败")) {
						log.info(utilDto.getVEHICLE_SERIAL() + "取图失败,请求字符串【" + uri + "】,返回字符串【" + result + "】");
						continue;
					}
					url += result.substring(19, result.length() - 11);
					// System.out.println(url);
					String realPath = path + utilDto.getSTATION_ID() + "/" + utilDto.getPASS_TIME() + "_" + utilDto.getINDEX_ID();
					File file = new File(realPath);
					if (!file.exists() && !file.isDirectory()) {
						file.mkdirs();
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
					HttpDownloader hd = new HttpDownloader(url, realPath + "/" + utilDto.getPASS_TIME() + "_" + utilDto.getVEHICLE_ID() + ".zip");
					boolean res = hd.download();
					if (res) {
						// 下载成功后修改数据库同步记录状态
						List<ScheduleRecordDto> imgquery = scheduleRecordDao.imgQueryList(utilDto.getVEHICLE_SERIAL());
						for (ScheduleRecordDto scheduleRecordDto : imgquery) {
							ScheduleRecord scr = new ScheduleRecord();
							scr.setDATA_STATE(scheduleRecordDto.getDATA_STATE().intValue());
							scr.setFAULT_SERIAL(scheduleRecordDto.getFAULT_SERIAL());
							scr.setIMG_STATE(1);
							scr.setSCHEDULE_TIME(DateUtil.StringToDate(scheduleRecordDto.getSCHEDULE_TIME(), "yyyy-MM-dd HH:mm:ss"));
							scr.setSTATE(scheduleRecordDto.getSTATE().intValue());
							scr.setTRAIN_SERIAL(scheduleRecordDto.getTRAIN_SERIAL());
							scr.setVEHICLE_SERIAL(scheduleRecordDto.getVEHICLE_SERIAL());
							BeanUtils.copyProperties(scheduleRecordDto, scr);
							scheduleRecordDao.update(scr);
						}
						rt = true;
					} else {
						log.info("下载失败");
						rt = false;
					}
					url = "";
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return rt;
			} catch (Exception e) {
				e.printStackTrace();
				return rt;
			}
		} else {
			return rt;
		}
		return rt;
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
}
