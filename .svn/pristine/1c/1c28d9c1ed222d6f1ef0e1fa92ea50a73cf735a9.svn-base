package com.web.info.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.web.info.args.TrainArgs;
import com.web.info.dao.TrainDao;
import com.web.info.dto.TrainDto;
import com.web.info.model.Train;

@Repository
public class TrainDaoImpl extends BaseDao<Train> implements TrainDao {

	@Override
	public List<TrainDto> queryListTrain(TrainArgs args) {
		StringBuffer sql = new StringBuffer();
		sql.append("select train_serial, train_id, station_id, pass_time, direction,vehicle_number, freight_vehicle_number, car_number, speed, train_sort,engine_number, pass_kind, pass_state, view_state, pic_valid_state,highest_speed, lowest_speed, equ_detect_number, index_id, detect_number,day_night, team, remark, img_ready_status, lock_version, train_status,vehicle_exposure, photo_exposure, vehicle_black, photo_black, vehicle_lost,photo_lost, vehicle_disorder, station_track, bc, is_repeat, qcstation_id,qcpass_time from tf_op_train t where 1=1");
		if (args.getPASS_TIME() != null) {
			sql.append(" and t.pass_time > to_date('" + args.getPASS_TIME() + "','yyyy/MM/dd hh24:mi:ss')");
		}
		sql.append(" and rownum <= 100   order by t.pass_time desc");// 防止数据过多卡死，此处默认100条
		return super.listBySql(sql.toString(), TrainDto.class, false);
	}

	@Override
	public List<TrainDto> queryTrainSet(Set<String> trainSet) {

		StringBuffer sql = new StringBuffer();
		sql.append("select train_serial, train_id, station_id, to_char(pass_time,'yyyy-MM-dd hh24:mi:ss') pass_time, direction,vehicle_number, freight_vehicle_number, car_number, speed, train_sort,engine_number, pass_kind, pass_state, view_state, pic_valid_state,highest_speed, lowest_speed, equ_detect_number, index_id, detect_number,day_night, team, remark, img_ready_status, lock_version, train_status,vehicle_exposure, photo_exposure, vehicle_black, photo_black, vehicle_lost,photo_lost, vehicle_disorder from tf_op_train t where 1=1");
		if (trainSet != null && trainSet.size() > 0) {
			String tr = "";
			for (String str : trainSet) {
				if (tr == "") {
					tr += "'" + str + "'";
				} else {
					tr += ",'" + str + "'";
				}
			}
			sql.append(" and train_serial in (" + tr + ")");
		}
		return super.listBySql(sql.toString(), TrainDto.class, false);
	}

	@Override
	public List<TrainDto> queryList(TrainArgs args) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.train_serial, t.train_id, t.station_id, cfg.station_name, to_char(t.pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time, t.direction, t.vehicle_number, t.freight_vehicle_number, t.car_number, t.speed, t.train_sort, t.engine_number, t.pass_kind, t.pass_state, t.view_state, t.pic_valid_state, t.highest_speed, t.lowest_speed, t.equ_detect_number, t.index_id, t.detect_number, t.day_night, t.team, t.remark, t.img_ready_status, t.lock_version, t.train_status, t.vehicle_exposure, t.photo_exposure, t.vehicle_black, t.photo_black, t.vehicle_lost, t.photo_lost, t.vehicle_disorder, t.station_track, t.bc, t.is_repeat, t.qcstation_id from tf_op_train t,tf_cfg_detectstation cfg where 1 = 1 AND cfg.station_id = t.station_id ");
		//sql.append("select train_serial, train_id, station_id, to_char(pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time, direction,vehicle_number, freight_vehicle_number, car_number, speed, train_sort,engine_number, pass_kind, pass_state, view_state, pic_valid_state,highest_speed, lowest_speed, equ_detect_number, index_id, detect_number,day_night, team, remark, img_ready_status, lock_version, train_status,vehicle_exposure, photo_exposure, vehicle_black, photo_black, vehicle_lost,photo_lost, vehicle_disorder, station_track, bc, is_repeat, qcstation_id  from tf_op_train t where 1=1");
		if (StringUtils.isNotEmpty(args.getSTATION_ID())) {
			sql.append(" and t.station_id = '" + args.getSTATION_ID() + "'");
		}
		sql.append("  order by t.pass_time desc");
		return super.listBySql(sql.toString(), TrainDto.class, false);
	}

}
