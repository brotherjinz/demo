package com.web.info.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.web.info.args.EchartsArgs;
import com.web.info.args.ScheduleRecordArgs;
import com.web.info.dao.ScheduleRecordDao;
import com.web.info.dto.EchartsDto;
import com.web.info.dto.ScheduleRecordDto;
import com.web.info.model.ScheduleRecord;

@Repository
public class ScheduleRecordDaoImpl extends BaseDao<ScheduleRecord> implements ScheduleRecordDao {

	@Override
	public List<ScheduleRecordDto> queryList(ScheduleRecordArgs args) {
		StringBuffer sql = new StringBuffer();
		//sql.append("select FAULT_SERIAL, DATA_STATE, IMG_STATE, to_char(schedule_time,'yyyy-MM-dd hh24:mi:ss') as SCHEDULE_TIME,STATE from schedule_record t where 1=1");
		 sql.append("select sr.FAULT_SERIAL, sr.DATA_STATE, sr.IMG_STATE, to_char(sr.schedule_time, 'yyyy-MM-dd hh24:mi:ss') as SCHEDULE_TIME, sr.STATE from schedule_record sr, tf_op_fault tf, tf_op_train tt where 1 = 1 and tf.fault_serial = sr.fault_serial and tt.train_serial = tf.train_serial ");
		if (args.getIMG_STATE() !=null && args.getIMG_STATE() == 0) {
			sql.append(" and IMG_STATE = 0");
		}
		if(StringUtils.isNotEmpty(args.getStartTime())&&StringUtils.isNotEmpty(args.getEndTime())){
			sql.append("and tt.pass_time >= to_date('"+args.getStartTime()+"', 'yyyy-MM-dd hh24:mi:ss') and tt.pass_time <= to_date('"+args.getEndTime()+"', 'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotEmpty(args.getStationcode())){
			sql.append(" and tt.station_id='"+args.getStationcode()+"'");
		}
		return super.listBySql(sql.toString(), ScheduleRecordDto.class, false);
	}

	@Override
	public List<ScheduleRecordDto> imgQueryList(String vs) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sr.FAULT_SERIAL, sr.DATA_STATE, sr.IMG_STATE, to_char(schedule_time,'yyyy-MM-dd hh24:mi:ss') as SCHEDULE_TIME,sr.STATE,STATIONCODE,sr.TRAIN_SERIAL,sr.VEHICLE_SERIAL from schedule_record sr, tf_op_fault tf where 1=1 and sr.fault_serial = tf.fault_serial");
		if (StringUtils.isNotEmpty(vs)) {
			sql.append(" and tf.vehicle_serial = '"+vs+"'");
		}
		return super.listBySql(sql.toString(), ScheduleRecordDto.class, false);
	}

	/**
	 * 查询图形统计图
	 */
	@Override
	public List<EchartsDto> queryEcharts(EchartsArgs args) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select tcd.station_name as STATION_NAME,tcd.station_id as STATION_ID,count(*) as COUNTNUMBER from schedule_record sr, tf_op_fault tf, tf_op_train tt,tf_cfg_detectstation tcd where 1 = 1 and tf.fault_serial = sr.fault_serial and tt.train_serial = tf.train_serial and tcd.station_id = tt.station_id ");
		if(StringUtils.isNotEmpty(args.getStartTime())&&StringUtils.isNotEmpty(args.getEndTime())){
			sql.append(" and tt.pass_time >= to_date('"+args.getStartTime()+"', 'yyyy-MM-dd hh24:mi:ss') and tt.pass_time <= to_date('"+args.getEndTime()+"', 'yyyy-MM-dd hh24:mi:ss')");
		}
		sql.append(" group by tcd.station_id,tcd.station_name order by tcd.station_id desc");
		return super.listBySql(sql.toString(), EchartsDto.class, false);
	}
}
