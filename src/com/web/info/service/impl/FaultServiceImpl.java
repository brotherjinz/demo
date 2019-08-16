package com.web.info.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.info.args.BpTypeArgs;
import com.web.info.args.ConfigArgs;
import com.web.info.args.FaultArgs;
import com.web.info.args.ScheduleJdArgs;
import com.web.info.args.ScheduleRecordArgs;
import com.web.info.args.TrainArgs;
import com.web.info.args.UtilArgs;
import com.web.info.args.VehicleArgs;
import com.web.info.dao.ConfigDao;
import com.web.info.dao.FaultBDao;
import com.web.info.dao.FaultDao;
import com.web.info.dao.ScheduleJdDao;
import com.web.info.dao.ScheduleRecordDao;
import com.web.info.dao.TrainDao;
import com.web.info.dao.VehicleDao;
import com.web.info.dto.BpTypeDto;
import com.web.info.dto.FaultTable;
import com.web.info.dto.ScheduleJdDto;
import com.web.info.dto.ScheduleRecordDto;
import com.web.info.dto.TrainDto;
import com.web.info.dto.UtilDto;
import com.web.info.dto.VehicleDto;
import com.web.info.model.Config;
import com.web.info.model.Fault;
import com.web.info.model.FaultB;
import com.web.info.model.ScheduleJD;
import com.web.info.model.ScheduleRecord;
import com.web.info.model.Train;
import com.web.info.model.Vehicle;
import com.web.info.service.FaultService;
import com.web.info.service.TrainService;
import com.web.info.service.VehicleService;
import com.web.info.system.PageResult;
import com.web.util.DataSourceConst;
import com.web.util.DataSourceContextHolder;
import com.web.util.DateUtil;
import com.web.util.FileUtil;
import com.web.util.ZipUtil;

import net.sf.json.JSONObject;

@Service
public class FaultServiceImpl implements FaultService {

	private static Logger log = Logger.getLogger(FaultServiceImpl.class);
	@Autowired
	private FaultDao faultDao;
	@Autowired
	private FaultBDao faultBDao;
	@Autowired
	private TrainService trainService;
	@Autowired
	private TrainDao trainDao;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private VehicleDao vehicleDao;
	@Autowired
	private ConfigDao configDao;
	@Autowired
	private ScheduleJdDao scheduleJdDao;
	@Autowired
	private ScheduleRecordDao scheduleRecordDao;

	@Override
	public boolean faultData(UtilArgs args, String stationcode) {
		ScheduleJdArgs scheduleJdArgs = new ScheduleJdArgs();
		ScheduleJD scjd = new ScheduleJD();
		if (args.isSZ()) {
			// 自动同步之前首先查询本最大节点
			scheduleJdArgs.setCOLUMNNAME("PASS_TIME");//
			scheduleJdArgs.setTABLENAME("TF_OP_FAULT");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			// 查询节点最大值
			ScheduleJdDto sd = scheduleJdDao.MaxJd(scheduleJdArgs);
			// 如果无节点数据则用默认值，则默认本月1日为节点
			scjd.setCOLUMNNAME(scheduleJdArgs.getCOLUMNNAME());
			scjd.setTABLENAME(scheduleJdArgs.getTABLENAME());
			scjd.setSTATIONCODE(stationcode);
			if (sd == null) {
				scjd.setCOLUMNVALUE_START(df.format(DateUtil.getFirstDayOfMonth(new Date())));// 本月第一天
				scjd.setCOLUMNVALUE_END(DateUtil.addHour(scjd.getCOLUMNVALUE_START(), 24));
				scjd.setSCHEDULEORDER(0);
				// 把时间赋值给参数
				args.setStartTime(scjd.getCOLUMNVALUE_START());
				args.setEndTime(scjd.getCOLUMNVALUE_END());

			} else {
				args.setStartTime(sd.getCOLUMNVALUE_END());
				args.setEndTime(DateUtil.addHour(sd.getCOLUMNVALUE_END(), 24));
				scjd.setCOLUMNVALUE_START(args.getStartTime());
				scjd.setCOLUMNVALUE_END(args.getEndTime());
				scjd.setSCHEDULEORDER(sd.getSCHEDULEORDER().intValue() + 1);
			}
			// 按时间查询本地库，获取已同步的数据id
			ScheduleRecordArgs arg = new ScheduleRecordArgs();
			arg.setStartTime(args.getStartTime());
			arg.setEndTime(args.getEndTime());
			List<ScheduleRecordDto> ls = scheduleRecordDao.queryList(arg);
			List<String> listStr = new ArrayList<String>();
			for (ScheduleRecordDto srd : ls) {
				listStr.add(srd.getFAULT_SERIAL());
			}
			args.setStrList(listStr);
			args.setTF(false);
		}
		// 切换数据源
		DataSourceContextHolder.setDataSourseType(stationcode);
		List<Fault> flt = faultDao.queryListDao(args);
		// 切回数据源
		DataSourceContextHolder.setDataSourseType(DataSourceConst.default_db);
		// 插入到本地数据库
		// 遍历对象插入到本地数据库
		try {
			if (flt != null && flt.size() > 0) {
				Set<String> trainSet = new HashSet<String>();
				Set<String> vehicleSet = new HashSet<String>();
				Set<String> faultSerial = new HashSet<String>();
				for (Fault fault : flt) {
					FaultB ftB = new FaultB();
					BeanUtils.copyProperties(fault, ftB);// 复制资源对象
					// 先将照片缓存到本地
					byte[] image = fault.getIMG_DATA();// 获取照片byte
					String dir = "C:/Tmp/";// System.getProperty("user.dir");
					FileImageOutputStream imageOutput = new FileImageOutputStream(new File(dir + fault.getFAULT_SERIAL() + "_TEMP.jpg"));
					// 打开输入流
					imageOutput.write(image, 0, image.length);// 将byte写入硬盘
					imageOutput.close();// 关闭输入流
					FileInputStream fis = new FileInputStream(dir + fault.getFAULT_SERIAL() + "_TEMP.jpg");// 定义文件读入流
					byte[] buffer = new byte[fis.available()];
					fis.read(buffer);
					fis.close();
					ftB.setFAULT_SERIAL(fault.getFAULT_SERIAL());
					ftB.setIMG_DATA(buffer);
					faultBDao.add(ftB);
					// faultDao.add(ft);
					// 插入成功后删除临时文件
					File file = new File(dir + fault.getFAULT_SERIAL() + "_TEMP.jpg");
					file.delete();// 删除临时文件
					ScheduleRecord scheduleRecord = new ScheduleRecord();
					trainSet.add(fault.getTRAIN_SERIAL());// 取出所属列车的唯一编号
					vehicleSet.add(fault.getVEHICLE_SERIAL());// 取出所属车辆唯一编号
					faultSerial.add(fault.getFAULT_SERIAL());
					// 插入记录表，记录同步数据状态
					scheduleRecord.setFAULT_SERIAL(fault.getFAULT_SERIAL());
					scheduleRecord.setDATA_STATE(0);
					scheduleRecord.setIMG_STATE(0);
					scheduleRecord.setVEHICLE_SERIAL(fault.getVEHICLE_SERIAL());
					scheduleRecord.setTRAIN_SERIAL(fault.getTRAIN_SERIAL());
					scheduleRecord.setSCHEDULE_TIME(new Date());
					if (args.isSZ()) {
						scheduleRecord.setSTATE(0);
					} else {
						scheduleRecord.setSTATE(1);
					}
					scheduleRecord.setSTATIONCODE(stationcode);
					scheduleRecordDao.add(scheduleRecord);
				}
				// 同步列车表,更新同步记录
				boolean trainState = trainService.insertTrainData(trainSet, stationcode);
				boolean vehicleState = vehicleService.insertVehicleData(vehicleSet, stationcode);
				if (trainState && vehicleState) {
					for (String string : faultSerial) {
						ScheduleRecord scr = scheduleRecordDao.get(string);
						scr.setDATA_STATE(1);
						scheduleRecordDao.update(scr);
					}
					log.info("列车数据同步成功");
				} else {
					log.error("列车数据同步失败");
				}
			}
			if (args.isSZ()) {
				// 保存节点
				scjd.setSCHEDULETIME(new Date());
				scheduleJdDao.addEntity(scjd);
			}
			return true;
		} catch (FileNotFoundException e) {
			log.error("文件未找到");
			// e.printStackTrace();
			return false;
		} catch (IOException e) {
			log.error("IO异常");
			// e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Fault> queryList(UtilArgs args) {
		return null;
	}

	@Override
	public Map<String, Object> queryPager(UtilArgs args, String stationcode) {
		DataSourceContextHolder.setDataSourseType(stationcode);
		PageResult result = faultDao.queryPager(args);
		DataSourceContextHolder.setDataSourseType(DataSourceConst.default_db);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (result.getPageList() != null) {
			for (int i = 0; i < result.getPageList().size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				Object[] obj = (Object[]) result.getPageList().get(i);
				map.put("FAULT_SERIAL", obj[0].toString());
				map.put("FAULT_NAME", obj[1] == null ? "" : obj[1].toString());
				map.put("DETECT_TIME", obj[2] == null ? "" : obj[2].toString());
				map.put("REMARK", obj[3] == null ? "" : obj[3].toString());
				map.put("PASS_TIME", obj[4] == null ? "" : obj[4].toString());
				map.put("TRAIN_ID", obj[5] == null ? "" : obj[5].toString());
				map.put("STATION_ID", obj[6] == null ? "" : obj[6].toString());
				map.put("STATION_NAME", obj[7] == null ? "" : obj[7].toString());
				map.put("FAULT_ID", obj[8] == null ? "" : obj[8].toString());
				map.put("VEHICLE_ID", obj[9] == null ? "" : obj[9].toString());
				map.put("DISPLAY_NAME", obj[10] == null ? "" : obj[10].toString());
				list.add(map);
			}
		}
		jsonMap.put("rows", list);
		jsonMap.put("total", result.getTotalRow());
		return jsonMap;
	}

	// 查询当前已经同步成功的数据
	// 此方法保留，待以后用，
	@Override
	public Object syncQueryPager(UtilArgs utilargs) {
		// 查询本地同步数据记录表得到所有同步数据的key集合，通过KEY分页查询所包含的数据，拼装json
		// 本地已经同步成功的数据
		/*
		 * ScheduleRecordArgs srargs = new ScheduleRecordArgs(); List<ScheduleRecordDto> listsr = scheduleRecordDao.queryList(srargs); List<String> liststr = new ArrayList<String>(); for (ScheduleRecordDto scheduleRecordDto : listsr) { liststr.add(scheduleRecordDto.getFAULT_SERIAL()); } utilargs.setStrList(liststr); utilargs.setTF(true);
		 */
		// 查询故障表，列车表，车辆表
		PageResult result = faultDao.syncQueryPager(utilargs);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (result.getPageList() != null) {
			for (int i = 0; i < result.getPageList().size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				Object[] obj = (Object[]) result.getPageList().get(i);
				map.put("VEHICLE_ID", obj[0].toString());
				map.put("VEHICLE_ORDER", obj[1] == null ? "" : obj[1].toString());
				map.put("TRAIN_ID", obj[2] == null ? "" : obj[2].toString());
				map.put("STATION_ID", obj[3] == null ? "" : obj[3].toString());
				map.put("STATION_NAME", obj[4] == null ? "" : obj[4].toString());
				map.put("VEHICLE_SERIAL", obj[5] == null ? "" : obj[5].toString());
				list.add(map);
			}
		}
		jsonMap.put("rows", list);
		jsonMap.put("total", result.getTotalRow());
		return jsonMap;
	}

	/**
	 * 查询已经同步成功的数据
	 */
	@Override
	public Object syncSuccessQueryPager(UtilArgs args) {
		PageResult result = faultDao.syncSuccessGetPager(args);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (result.getPageList() != null) {
			for (int i = 0; i < result.getPageList().size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				Object[] obj = (Object[]) result.getPageList().get(i);
				map.put("VEHICLE_SERIAL", obj[0].toString());
				map.put("DATA_STATE", obj[1] == null ? "" : obj[1].toString());
				map.put("STATIONCODE", obj[2] == null ? "" : obj[2].toString());
				map.put("SCHEDULE_TIME", obj[3] == null ? "" : obj[3].toString());
				map.put("IMG_STATE", obj[4] == null ? "" : obj[4].toString());
				map.put("VEHICLE_ID", obj[5] == null ? "" : obj[5].toString());
				map.put("STATION_NAME", obj[6] == null ? "" : obj[6].toString());
				list.add(map);
			}
		}
		jsonMap.put("rows", list);
		jsonMap.put("total", result.getTotalRow());
		return jsonMap;
	}

	@Override
	public Object queryDataPager(UtilArgs args) {
		PageResult result = faultDao.queryDataPager(args);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (result.getPageList() != null) {
			for (int i = 0; i < result.getPageList().size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				Object[] obj = (Object[]) result.getPageList().get(i);
				map.put("TRAIN_ID", obj[0].toString());
				map.put("TRAIN_SERIAL", obj[1] == null ? "" : obj[1].toString());
				map.put("VEHICLE_ID", obj[2] == null ? "" : obj[2].toString());
				map.put("VEHICLE_SERIAL", obj[3] == null ? "" : obj[3].toString());
				map.put("PASS_TIME", obj[4] == null ? "" : obj[4].toString());
				map.put("STATION_ID", obj[5] == null ? "" : obj[5].toString());
				map.put("VEHICLE_TYPE", obj[6] == null ? "" : obj[6].toString());
				map.put("INDEX_ID", obj[7] == null ? "" : obj[7].toString());
				map.put("VEHICLE_ORDER", obj[8] == null ? "" : obj[8].toString());
				map.put("STATION_NAME", obj[9] == null ? "" : obj[9].toString());
				list.add(map);
			}
		}
		jsonMap.put("rows", list);
		jsonMap.put("total", result.getTotalRow());
		return jsonMap;
	}

	@Override
	public List<UtilDto> queryFault(UtilArgs args) {
		return faultDao.queryFaultList(args);
	}

	@Override
	public List<UtilDto> expQueryFault(UtilArgs args) {
		// TODO Auto-generated method stub
		return faultDao.expQueryFault(args);
	}

	@Override
	public Object queryAll(UtilArgs args) {
		TrainArgs tra = new TrainArgs();
		tra.setTRAIN_SERIAL(args.getSTATION_ID());
		// 查询本探测站下全部数据车次
		List<TrainDto> listTrain = trainService.queryTrainDto(tra);
		for (TrainDto trainDto : listTrain) {
			// 根据探测站查到的数据为条件查询车号
			VehicleArgs Vargs = new VehicleArgs();
			Vargs.setTRAIN_SERIAL(trainDto.getTRAIN_SERIAL());
			List<VehicleDto> listVehicleDto = vehicleService.queryVehicleDto(Vargs);
			for (VehicleDto vehicleDto : listVehicleDto) {
				// 以车号数据为条件查询全部故障
				FaultArgs faultargs = new FaultArgs();
				faultargs.setVEHICLE_SERIAL(vehicleDto.getVEHICLE_SERIAL());
				List<FaultTable> listFaultTable = faultDao.queryFaultDto(faultargs);
			}
		}
		return listTrain;
	}

	@Override
	public boolean vEditing(List<String> list, String stationcode) {
		try {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
			SimpleDateFormat df2 = new SimpleDateFormat("yyMMddHHmm");// 设置日期格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			List<String> newVEHICLE_SERIAL = new ArrayList<String>();
			Train newTrain = new Train();
			int suiji = (int) ((Math.random() * 9 + 1) * 10000);
			newTrain.setTRAIN_ID("B" + suiji);// 车次,5位随机数
			newTrain.setSTATION_ID(stationcode);// 探测站编码
			// 获取车站配置
			ConfigArgs argscfg = new ConfigArgs();
			argscfg.setSTATIONCODE(stationcode);
			Config cfg = configDao.querycfg(argscfg);
			newTrain.setPASS_TIME(DateUtil.string2Date("yyyy-MM-dd HH:mm:ss", df.format(new Date())));// 设置虚拟过车时间，转化时间格式
			newTrain.setINDEX_ID(new BigDecimal(df2.format(newTrain.getPASS_TIME())));// 目录号，原图存放位置
			// 过车时间,取当前时间
			newTrain.setTRAIN_SERIAL(UUID.randomUUID().toString());// 利用java生成uuid
			newTrain.setVEHICLE_NUMBER(new BigDecimal(list.size()));// 车辆数
			newTrain.setFREIGHT_VEHICLE_NUMBER(new BigDecimal(list.size()));// 总量数
			trainDao.add(newTrain);
			String realPath = cfg.getPATHFOLDER() + "/" + stationcode + "/" + df1.format(newTrain.getPASS_TIME());// 定义目录号
			File fileML = new File(realPath);
			// 判断文件夹是否存在，不存在则创建
			if (!fileML.exists() && !fileML.isDirectory()) {
				fileML.mkdirs();
			}
			// 遍历原车号新编组的数组，轮训插入，遍历，迭代
			Integer j = 1;
			for (String str : list) {
				Vehicle oldVehicle = vehicleDao.get(str); // 查询原数据
				Vehicle newVehicle = new Vehicle();// 创建新数据
				BeanUtils.copyProperties(oldVehicle, newVehicle);// 复制数据对象
				// 此处调取原图,并指定存放位置
				newVehicle.setTRAIN_SERIAL(newTrain.getTRAIN_SERIAL());// 此处存放新编的车次id
				newVehicle.setVEHICLE_ORDER(j.toString());// 设置辆序从1开始
				// 操作图片,用原数据的条件去取原图，取到之后更名保存新的
				Train tr = trainDao.get(oldVehicle.getTRAIN_SERIAL());
				// 取图路径
				String rpath = cfg.getPATHFOLDER() + tr.getSTATION_ID() + "/" + df1.format(tr.getPASS_TIME()) + "_" + tr.getINDEX_ID();
				// 解压图片
				ZipUtil.unZip(rpath + "/" + df1.format(tr.getPASS_TIME()) + "_" + oldVehicle.getVEHICLE_ID() + ".zip", rpath + "/" + df1.format(tr.getPASS_TIME()) + "_" + oldVehicle.getVEHICLE_ID());
				// 得到解压完成后的路径，取出解压后的全部文件名,遍历文件名，依次拷贝图片到新的目录号里面
				List<String> oldFilename = FileUtil.getFileName(rpath + "/" + df1.format(tr.getPASS_TIME()) + "_" + oldVehicle.getVEHICLE_ID() + "/" + tr.getINDEX_ID() + "/");
				// 存图路径,解压完成后从解压后的路径复制，取图，更名，然后存放到新的路径
				// 定义变量获取上次截取后的辆序
				Integer oldIndex = 0;
				Integer i = 0;
				for (String strFilename : oldFilename) {
					String indexstr = strFilename.substring(0, strFilename.indexOf("_"));// 截取下划线之前的字符串
					if (oldIndex != 0) {
						if (Integer.parseInt(indexstr) > oldIndex) {
							i = j + 1;
						}
					}
					oldIndex = Integer.parseInt(indexstr);
					String laststr = strFilename.substring(strFilename.indexOf("_") + 1);// 指定字符之后的全部
					if (i == 0) {
						i = j;
					}
					FileUtil.copyFile(rpath + "/" + df1.format(tr.getPASS_TIME()) + "_" + oldVehicle.getVEHICLE_ID() + "/" + tr.getINDEX_ID() + "/" + strFilename, realPath + "/" + i.toString() + "_" + laststr);// 拷贝图片，并更名
				}
				newVehicle.setVEHICLE_SERIAL(UUID.randomUUID().toString());// 利用java生成uuid
				vehicleDao.add(newVehicle);// 保存到数据库
				newVEHICLE_SERIAL.add(newVehicle.getVEHICLE_SERIAL());// 保存完成后取新保存的数据id
				// 保存入库完成后删除原解压后的整个文件夹
				// rpath+"/"+df1.format(tr.getPASS_TIME())+"_"+oldVehicle.getVEHICLE_ID()
				boolean deleteFolder = FileUtil.deleteDir(new File(rpath + "/" + df1.format(tr.getPASS_TIME()) + "_" + oldVehicle.getVEHICLE_ID()));
				// 通过原车号id取原故障数据
				UtilArgs utilarg = new UtilArgs();// 实例化参数类
				utilarg.setVEHICLE_SERIAL(str);// 设置参数id字段
				List<Fault> oldfault = faultDao.getListFault(utilarg);// 查询表数据，反回list对象集合
				// 取到故障数据后遍历数据，转化blob二进制文件流，再存入新数据
				for (Fault old : oldfault) {
					FaultB ftB = new FaultB();
					BeanUtils.copyProperties(old, ftB);// 复制资源对象
					ftB.setTRAIN_SERIAL(newTrain.getTRAIN_SERIAL());
					ftB.setVEHICLE_SERIAL(newVehicle.getVEHICLE_SERIAL());
					// 先将照片缓存到本地
					byte[] image = old.getIMG_DATA();// 获取照片byte
					FileImageOutputStream imageOutput = new FileImageOutputStream(new File(System.getProperty("user.dir") + old.getFAULT_SERIAL() + "_TEMP.jpg"));
					// 打开输入流
					imageOutput.write(image, 0, image.length);// 将byte写入硬盘
					imageOutput.close();// 关闭输入流
					FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + old.getFAULT_SERIAL() + "_TEMP.jpg");// 定义文件读入流
					byte[] buffer;
					buffer = new byte[fis.available()];
					fis.read(buffer);
					fis.close();
					ftB.setFAULT_SERIAL(old.getFAULT_SERIAL());
					ftB.setIMG_DATA(buffer);
					ftB.setFAULT_SERIAL(UUID.randomUUID().toString());// 利用java生成uuid
					faultBDao.add(ftB);
					// 插入成功后删除临时文件
					File file = new File(System.getProperty("user.dir") + old.getFAULT_SERIAL() + "_TEMP.jpg");
					file.delete();// 删除临时文件
				}
				j++;
			}

			return true;
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Object queryDatavEditing(UtilArgs args) {
		PageResult result = faultDao.queryDatavEditing(args);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (result.getPageList() != null) {
			for (int i = 0; i < result.getPageList().size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				Object[] obj = (Object[]) result.getPageList().get(i);
				map.put("TRAIN_ID", obj[0].toString());
				map.put("TRAIN_SERIAL", obj[1] == null ? "" : obj[1].toString());
				map.put("VEHICLE_ID", obj[2] == null ? "" : obj[2].toString());
				map.put("VEHICLE_SERIAL", obj[3] == null ? "" : obj[3].toString());
				map.put("PASS_TIME", obj[4] == null ? "" : obj[4].toString());
				map.put("STATION_ID", obj[5] == null ? "" : obj[5].toString());
				map.put("VEHICLE_TYPE", obj[6] == null ? "" : obj[6].toString());
				map.put("INDEX_ID", obj[7] == null ? "" : obj[7].toString());
				map.put("VEHICLE_ORDER", obj[8] == null ? "" : obj[8].toString());
				map.put("STATION_NAME", obj[9] == null ? "" : obj[9].toString());
				map.put("FAULT_NAME", obj[10] == null ? "" : obj[10].toString());
				list.add(map);
			}
		}
		jsonMap.put("rows", list);
		jsonMap.put("total", result.getTotalRow());
		return jsonMap;
	}

	@Override
	public JSONObject querySelectType(BpTypeArgs args) {
		JSONObject jobj = new JSONObject();
		List<BpTypeDto> listrs = new ArrayList<BpTypeDto>();
		List<BpTypeDto> list = faultDao.queryDatavEditingType(args);
		// 遍历数据将条件一查询到的数据跟条件二查询得到的数据合并
		for (BpTypeDto bpTypeDto : list) {
			listrs.add(bpTypeDto);
		}
		jobj.put("data", listrs);
		return jobj;
	}

}
