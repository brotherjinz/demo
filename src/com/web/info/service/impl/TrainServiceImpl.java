package com.web.info.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.info.args.ScheduleJdArgs;
import com.web.info.args.TrainArgs;
import com.web.info.dao.ScheduleJdDao;
import com.web.info.dao.TrainDao;
import com.web.info.dto.ScheduleJdDto;
import com.web.info.dto.TrainDto;
import com.web.info.model.ScheduleJD;
import com.web.info.model.Train;
import com.web.info.service.TrainService;
import com.web.util.DataSourceConst;
import com.web.util.DataSourceContextHolder;
import com.web.util.DateUtil;

@Service
public class TrainServiceImpl implements TrainService {

	private static Logger log = Logger.getLogger(TrainServiceImpl.class);

	@Autowired
	private ScheduleJdDao scheduleJdDao;
	@Autowired
	private TrainDao trainDao;

	@Override
	public int TrainData(String tableName, String ColumnName) {
		int rt = 0;
		ScheduleJdArgs args = new ScheduleJdArgs();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		// args.setTableName(tableName);
		// 查询节点最大值
		ScheduleJdDto sd = scheduleJdDao.MaxJd(args);
		TrainArgs trainArgs = new TrainArgs();
		// 如果无节点数据则用默认值，默认当前系统时间为节点
		ScheduleJD scjd = new ScheduleJD();
		if (sd == null) {
			trainArgs.setPASS_TIME(df.format(new Date()));
			// scjd.setScheduleOrder(0);
			// scjd.setColumnValue("2016-01-01 00:00:00");
		} else {
			// trainArgs.setPASS_TIME(sd.getCOLUMNVALUE());
			// scjd.setScheduleOrder(sd.getSCHEDULEORDER().intValue() + 1);
		}
		// 切换数据源查询
		//DataSourceContextHolder.setDataSourseType(DataSourceConst.tfds);
		List<TrainDto> trainDtos = trainDao.queryListTrain(trainArgs);
		// 切回数据源插入
		//DataSourceContextHolder.setDataSourseType(DataSourceConst.default_db);
		if (trainDtos.size() > 0 && trainDtos != null) {
			// StringBuffer sql = new StringBuffer();
			rt = trainDtos.size();
			// List<String> lst = new ArrayList<String>();
			for (TrainDto trainDto : trainDtos) {
				Train train = new Train();
				BeanUtils.copyProperties(trainDto, train);
				trainDao.addEntity(train);// 单条插入
			}
			// lst.add("insert into TF_OP_TRAIN (TRAIN_SERIAL, TRAIN_ID, STATION_ID, PASS_TIME, DIRECTION, VEHICLE_NUMBER, FREIGHT_VEHICLE_NUMBER, CAR_NUMBER, SPEED, TRAIN_SORT, ENGINE_NUMBER, PASS_KIND, PASS_STATE, VIEW_STATE, PIC_VALID_STATE, HIGHEST_SPEED, LOWEST_SPEED, EQU_DETECT_NUMBER, INDEX_ID, DETECT_NUMBER, DAY_NIGHT, TEAM, REMARK, IMG_READY_STATUS, LOCK_VERSION, TRAIN_STATUS, VEHICLE_EXPOSURE, PHOTO_EXPOSURE, VEHICLE_BLACK, PHOTO_BLACK, VEHICLE_LOST, PHOTO_LOST, VEHICLE_DISORDER, STATION_TRACK, BC, IS_REPEAT, QCSTATION_ID, QCPASS_TIME) values ('"+train.getTRAIN_SERIAL()+"', '"+train.getTRAIN_ID()+"', '"+train.getSTATION_ID()+"', to_date( '"+train.getPASS_TIME()+"', 'dd-mm-yyyy hh24:mi:ss'), '"+train.getDIRECTION()+"', "+train.getVEHICLE_NUMBER()+", "+train.getFREIGHT_VEHICLE_NUMBER()+", "+train.getCAR_NUMBER()+", "+train.getSPEED()+", '"+train.getTRAIN_SORT()+"', "+train.getENGINE_NUMBER()+", '"+train.getPASS_KIND()+"', '"+train.getPASS_STATE()+"', '"+train.getVIEW_STATE()+"', '"+train.getPIC_VALID_STATE()+"', "+train.getHIGHEST_SPEED()+", "+train.getLOWEST_SPEED()+", "+train.getEQU_DETECT_NUMBER()+", "+train.getINDEX_ID()+", "+train.getDETECT_NUMBER()+", '"+train.getDAY_NIGHT()+"', "+train.getTEAM()+", "+train.getREMARK()+", '"+train.getIMG_READY_STATUS()+"', "+train.getLOCK_VERSION()+", "+train.getTRAIN_STATUS()+", "+train.getVEHICLE_EXPOSURE()+", "+train.getPHOTO_EXPOSURE()+", "+train.getVEHICLE_BLACK()+", "+train.getPHOTO_BLACK()+", "+train.getVEHICLE_LOST()+", "+train.getPHOTO_LOST()+","+train.getVEHICLE_DISORDER()+", "+train.getSTATION_TRACK()+", "+train.getBC()+", "+train.getIS_REPEAT()+", "+train.getQCSTATION_ID()+", "+train.getQCPASS_TIME()+");");
			// sql.append(lst.toString().substring(1, lst.toString().length()-1));
			// insert into student_info(stuName,stuAge) // values('zhanghua',13),('zhanghua',14),('zhanghua',15);
			// trainDao.excuteBySql(sql.toString());//传入批量插入sql语句执行
			// System.out.println(sql);
		}
		// scjd.setColumnValue(trainArgs.getQueryEnd_Time());
		// scjd.setTableName(tableName);
		// scjd.setColumnName(ColumnName);
		// scjd.setScheduleTime(new Date());
		scheduleJdDao.addEntity(scjd);
		return rt;
	}

	/**
	 * 传入列车唯一编号，查询列车数据
	 */
	@Override
	public boolean insertTrainData(Set<String> trainSet,String stationcode) {
		try {
			// 切换数据源查询远程数据库车列车数据
			DataSourceContextHolder.setDataSourseType(stationcode);
			List<TrainDto> trainData = trainDao.queryTrainSet(trainSet);
			DataSourceContextHolder.setDataSourseType(DataSourceConst.default_db);
			//SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			// 查到数据后执行插入本地表
			for (TrainDto trainDto : trainData) {
				Train train = new Train();
				BeanUtils.copyProperties(trainDto, train);
				train.setPASS_TIME(DateUtil.string2Date("yyyy-MM-dd HH:mm:ss", trainDto.getPASS_TIME()));
				trainDao.add(train);// 执行插入数据
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<TrainDto> queryTrain(TrainArgs args) {
		return trainDao.queryListTrain(args);
	}

	@Override
	public List<TrainDto> queryTrainDto(TrainArgs args) {
		return trainDao.queryList(args);
	}
}
