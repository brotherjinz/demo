package com.web.info.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.web.info.args.BpTypeArgs;
import com.web.info.args.FaultArgs;
import com.web.info.args.UtilArgs;
import com.web.info.dao.FaultDao;
import com.web.info.dto.BpTypeDto;
import com.web.info.dto.FaultTable;
import com.web.info.dto.UtilDto;
import com.web.info.model.Fault;
import com.web.info.system.PageResult;

@Repository
public class FaultDaoImpl extends BaseDao<Fault> implements FaultDao {

	@Override
	public List<Fault> getListFault(UtilArgs args) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from tf_op_fault where 1=1  ");
		if (StringUtils.isNotEmpty(args.getVEHICLE_SERIAL())) {
			sb.append(" and VEHICLE_SERIAL='" + args.getVEHICLE_SERIAL() + "'");
		}
		return super.listBySql(sb.toString(), Fault.class, true);
	}

	@Override
	public List<Fault> queryListDao(UtilArgs args) {
		StringBuilder sb = new StringBuilder();
		List<Object> arr = new ArrayList<Object>();
		// sb.append("select FAULT_SERIAL,TRAIN_SERIAL,VEHICLE_SERIAL,FAULT_NUMBER,FAULT_PLACE,FAULT_ID,DETECTOR,DETECT_TIME,FOUND_MODE,TRANSACTOR,TRANSACT_TIME,TRANSACT_MODE,AFFIRMANT,VERIFY_TIME,PONDERANCE,LAST_REPAIR_DATE,POSITION_LEFT,POSITION_RIGHT,POSITION_TOP,POSITION_BOTTOM,IMG_LENGTH,IMG_TYPE , REMARK , LOCK_VERSION , IMG_INDEX , DEL_FLAG , DEL_TIME , DEL_USER , VIEW_FLAG , VIEW_TIME , VIEW_USER , CXINFO , DXINFO , FXINFO , DOWN_TIME , FK_TIME , O_ID_DOWN , DOWN_RECEIVE_TIME , DOWN_RECEIVE_USER , FK_USER , FK_STATUS , TJZBY_FLAG , TJZBY_TIME , TJZBY_USER , IDENTIFY_ID , FK_LJ , ZWXX , IMAGE_NAME , IDENTIFY_RESULT , O_ID_CHECK , CHECK_TIME , CHECK_RESULT , ADDTIME , CONFIDENCE , INFO_CREATED_RESULT , IMAGE_CREATED_RESULT , INFO_CREATED_REMARK , IMAGE_CREATED_REMARK , FAULT_NAME from tf_op_fault where 1=1 ");
		sb.append("select tf.* from tf_op_fault tf, tf_op_train tt where 1 = 1 and tf.train_serial = tt.train_serial ");
		if (StringUtils.isNotEmpty(args.getStartTime())) {
			sb.append(" and tt.pass_time >= to_date('" + args.getStartTime() + "', 'yyyy-MM-dd hh24:mi:ss') ");
			// arr.add(args.getStartTime());
		}
		if (StringUtils.isNotEmpty(args.getEndTime())) {
			sb.append("and tt.pass_time <= to_date('" + args.getEndTime() + "', 'yyyy-MM-dd hh24:mi:ss')");
			// arr.add(args.getEndTime());
		}
		if (args.isTF() && args.getStrList() != null && args.getStrList().size() > 0) {
			String tr = "";
			for (String strArgs : args.getStrList()) {
				if (tr == "") {
					tr += "'" + strArgs + "'";
				} else {
					tr += ",'" + strArgs + "'";
				}
			}
			// 此处加包含在内的
			sb.append(" and tf.fault_serial in (" + tr + ")");
		} else if (args.getStrList() != null && args.getStrList().size() > 0) {
			String tr = "";
			for (String strArgs : args.getStrList()) {
				if (tr == "") {
					tr += "'" + strArgs + "'";
				} else {
					tr += ",'" + strArgs + "'";
				}
			}
			// 此处加不包含在内的
			sb.append(" and tf.fault_serial not in (" + tr + ")");
		}
		List<Fault> ftlist = super.listBySql(sb.toString(), arr.toArray(), Fault.class, true);
		return ftlist != null && ftlist.size() > 0 ? ftlist : null;
	}

	@Override
	public PageResult queryPager(UtilArgs args) {
		StringBuffer sql = new StringBuffer();
		PageResult result = null;
		try {
			sql.append(" select tf.FAULT_SERIAL, tf.FAULT_NAME, to_char(tf.DETECT_TIME, 'yyyy-MM-dd hh24:mi:ss') as DETECT_TIME, tf.REMARK, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as PASS_TIME, tt.TRAIN_ID, ff.STATION_ID, ff.STATION_NAME, tf.FAULT_ID, vv.VEHICLE_ID, tdm.DISPLAY_NAME from tf_op_fault tf, tf_op_train tt, tf_cfg_detectstation ff, tf_op_vehicle vv, tf_dz_transact_mode tdm, tf_dz_fault tdf where 1 = 1 and tdf.code = tf.fault_id and tf.train_serial = tt.train_serial and ff.station_id = tt.station_id and vv.vehicle_serial = tf.vehicle_serial(+) and tdm.code(+) = tf.transact_mode ");
			// sql.append("select tf.FAULT_SERIAL, tf.FAULT_NAME, to_char(tf.DETECT_TIME, 'yyyy-MM-dd hh24:mi:ss') as DETECT_TIME, tf.REMARK, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as PASS_TIME, tt.TRAIN_ID, ff.STATION_ID, ff.STATION_NAME,vv.VEHICLE_ID,tdm.DISPLAY_NAME from tf_op_fault tf, tf_op_train tt, tf_cfg_detectstation ff,tf_op_vehicle vv,tf_dz_transact_mode tdm where 1 = 1 and tf.train_serial = tt.train_serial and ff.station_id = tt.station_id and vv.vehicle_serial = tf.vehicle_serial(+) and tdm.code(+) = tf.transact_mode ");
			// and tf.fault_id='07TF6117489116'
			if (StringUtils.isNotEmpty(args.getFAULT_ID())) {
				sql.append(" and tf.fault_id='" + args.getFAULT_ID() + "'");
			}
			if (StringUtils.isNotEmpty(args.getStartTime())) {
				sql.append(" and tt.pass_time >= to_date('" + args.getStartTime() + "', 'yyyy-MM-dd hh24:mi:ss') ");
			}
			if (StringUtils.isNotEmpty(args.getEndTime())) {
				sql.append(" and tt.pass_time <= to_date('" + args.getEndTime() + "', 'yyyy-MM-dd hh24:mi:ss')");
			}
			if (StringUtils.isNotEmpty(args.getSTATION_ID())) {
				sql.append(" and ff.station_id = '" + args.getSTATION_ID() + "'");
			}
			if (StringUtils.isNotEmpty(args.getKXcode())) {
				sql.append("   and tf.transact_mode = '" + args.getKXcode() + "'");
			}
			if (args.isTF() && args.getStrList() != null && args.getStrList().size() > 0) {
				String tr = "";
				for (String strArgs : args.getStrList()) {
					if (tr == "") {
						tr += "'" + strArgs + "'";
					} else {
						tr += ",'" + strArgs + "'";
					}
				}
				// 此处加包含在内的
				sql.append(" and tf.fault_serial in (" + tr + ")");
			} else if (args.getStrList() != null && args.getStrList().size() > 0) {
				String tr = "";
				for (String strArgs : args.getStrList()) {
					if (tr == "") {
						tr += "'" + strArgs + "'";
					} else {
						tr += ",'" + strArgs + "'";
					}
				}
				// 此处加不包含在内的
				sql.append(" and tf.fault_serial not in (" + tr + ")");
			}
			sql.append("  order by tt.pass_time desc");
			result = super.getPageListSql(args.getPage(), args.getRows(), sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<UtilDto> queryFaultData(UtilArgs args) {
		StringBuilder sb = new StringBuilder();
		List<Object> arr = new ArrayList<Object>();
		sb.append("select DISTINCT vv.vehicle_id,to_number(vv.vehicle_order) vehicle_order, tt.train_id, to_char(tt.pass_time, 'yyyyMMddhh24miss') PASS_TIME, tt.station_id,tt.index_id, cf.stationname as station_name, vv.vehicle_serial from tf_op_vehicle vv, tf_op_train tt, config cf  where 1 = 1 and  cf.stationcode = tt.station_id and tt.train_serial = vv.train_serial and vv.vehicle_serial in (select DISTINCT tf.vehicle_serial from tf_op_fault tf where 1=1 ");
		if (args.isTF() && args.getStrList() != null && args.getStrList().size() > 0) {
			String tr = "";
			for (String strArgs : args.getStrList()) {
				if (tr == "") {
					tr += "'" + strArgs + "'";
				} else {
					tr += ",'" + strArgs + "'";
				}
			}
			// 此处加包含在内的
			sb.append("and tf.fault_serial in (" + tr + ")) order by tt.train_id desc");
			// sb.append(" and tf.fault_serial in ("+tr+")");
		} else if (args.getStrList() != null && args.getStrList().size() > 0) {
			String tr = "";
			for (String strArgs : args.getStrList()) {
				if (tr == "") {
					tr += "'" + strArgs + "'";
				} else {
					tr += ",'" + strArgs + "'";
				}
			}
			// 此处加不包含在内的
			sb.append("and tf.fault_serial not in (" + tr + ")) order by tt.train_id desc");
			// sb.append(" and tf.fault_serial not in ("+tr+")");
		}
		List<UtilDto> ftlist = super.listBySql(sb.toString(), arr.toArray(), UtilDto.class, false);
		return ftlist != null && ftlist.size() > 0 ? ftlist : null;
	}

	@Override
	public PageResult syncQueryPager(UtilArgs args) {
		StringBuffer sql = new StringBuffer();
		try {
			PageResult result = null;
			sql.append("select DISTINCT vv.vehicle_id, vv.vehicle_order, tt.train_id, tt.station_id,ff.station_name,vv.vehicle_serial from tf_op_vehicle vv, tf_op_train tt, tf_op_fault tf,tf_cfg_detectstation ff where 1 = 1 and ff.station_id = tt.station_id and tt.train_serial = vv.train_serial order by tt.train_id desc");// 此sql是查询不重复车号数据
			result = super.getPageListSql(args.getPage(), args.getRows(), sql.toString());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PageResult syncSuccessGetPager(UtilArgs args) {
		StringBuffer sql = new StringBuffer();
		try {
			PageResult result = null;
			sql.append("select sr.vehicle_serial, sr.DATA_STATE, sr.STATIONCODE, to_char(sr.schedule_time, 'yyyy-MM-dd hh24:mi:ss') as SCHEDULE_TIME, sr.IMG_STATE, vv.vehicle_id, tcd.station_name from schedule_record sr,tf_op_vehicle vv,tf_cfg_detectstation tcd where 1 = 1 and vv.vehicle_serial = sr.vehicle_serial and tcd.station_id = sr.stationcode");
			// sql.append(" select DISTINCT T.TRAIN_SERIAL, T.DATA_STATE, T.STATIONCODE, to_char(t.schedule_time, 'yyyy-MM-dd hh24:mi:ss') as SCHEDULE_TIME, TT.TRAIN_ID, TCD.STATION_NAME,T.IMG_STATE from schedule_record t, tf_op_train tt,tf_cfg_detectstation tcd where 1 = 1 and tt.train_serial = t.train_serial and tcd.station_id = tt.station_id");
			if (StringUtils.isNotEmpty(args.getStationcode())) {
				sql.append(" and sr.stationcode='" + args.getStationcode() + "'");
			}
			if (StringUtils.isNotEmpty(args.getVEHICLE_ID())) {
				sql.append("   and vv.vehicle_id='" + args.getVEHICLE_ID() + "' ");
			}
			sql.append("  order by sr.stationcode");
			result = super.getPageListSql(args.getPage(), args.getRows(), sql.toString());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PageResult queryDataPager(UtilArgs args) {
		StringBuffer sql = new StringBuffer();
		try {
			PageResult result = null;
			sql.append("select tt.train_id, tt.train_serial, vv.vehicle_id, vv.vehicle_serial, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time, tt.station_id, vv.vehicle_type, tt.index_id, vv.vehicle_order, tcf.station_name from tf_op_vehicle vv, tf_op_train tt, tf_cfg_detectstation tcf where vv.train_serial = tt.train_serial and tcf.station_id = tt.station_id");
			// sql.append(" select tt.train_id , tt.train_serial, vv.vehicle_id, vv.vehicle_serial, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time , tt.station_id, vv.vehicle_type, tt.index_id, vv.vehicle_order from tf_op_vehicle vv, tf_op_train tt where vv.train_serial = tt.train_serial ");
			if (StringUtils.isNotEmpty(args.getStartTime())) {
				sql.append(" and tt.pass_time >= to_date('" + args.getStartTime() + "', 'yyyy-MM-dd hh24:mi:ss') ");
			}
			if (StringUtils.isNotEmpty(args.getEndTime())) {
				sql.append(" and tt.pass_time <= to_date('" + args.getEndTime() + "', 'yyyy-MM-dd hh24:mi:ss')");
			}
			if (StringUtils.isNotEmpty(args.getStationcode())) {
				sql.append(" and tt.station_id='" + args.getStationcode() + "'");
			}
			if (StringUtils.isNotEmpty(args.getVEHICLE_ID())) {
				sql.append("   and vv.vehicle_id='" + args.getVEHICLE_ID() + "' ");
			}
			sql.append(" order by tt.train_id desc");
			result = super.getPageListSql(args.getPage(), args.getRows(), sql.toString());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UtilDto> queryFaultList(UtilArgs args) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select tf.fault_serial,tcd.station_id, tcd.station_name, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time, tt.train_id, tv.vehicle_id,tv.vehicle_type, tf.fault_name, tf.remark from tf_op_fault tf, tf_op_train tt, tf_op_vehicle tv, tf_cfg_detectstation tcd where 1 = 1 and tt.train_serial = tf.train_serial and tv.vehicle_serial = tf.vehicle_serial and tcd.station_id = tt.station_id ");
		if (StringUtils.isNotEmpty(args.getStationcode())) {
			sql.append(" and tt.station_id='" + args.getStationcode() + "'");
		}
		sql.append(" order by tt.train_id,tt.pass_time desc");
		return listBySql(sql.toString(), UtilDto.class, false);
	}

	@Override
	public List<UtilDto> expQueryFault(UtilArgs args) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("select tt.train_id, tt.train_serial, vv.vehicle_id, vv.vehicle_serial, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time, tt.station_id, vv.vehicle_type, to_number(tt.index_id) as index_id, to_number(vv.vehicle_order) as vehicle_order, tcf.station_name from tf_op_vehicle vv, tf_op_train tt, tf_cfg_detectstation tcf where vv.train_serial = tt.train_serial and tcf.station_id = tt.station_id");
		// sql.append(" select tt.train_id , tt.train_serial, vv.vehicle_id, vv.vehicle_serial, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time , tt.station_id, vv.vehicle_type, tt.index_id, vv.vehicle_order from tf_op_vehicle vv, tf_op_train tt where vv.train_serial = tt.train_serial ");
		if (StringUtils.isNotEmpty(args.getStartTime())) {
			sql.append(" and tt.pass_time >= to_date('" + args.getStartTime() + "', 'yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotEmpty(args.getEndTime())) {
			sql.append(" and tt.pass_time <= to_date('" + args.getEndTime() + "', 'yyyy-MM-dd hh24:mi:ss')");
		}
		if (StringUtils.isNotEmpty(args.getStationcode())) {
			sql.append(" and tt.station_id='" + args.getStationcode() + "'");
		}
		sql.append(" order by tt.train_id desc");
		return listBySql(sql.toString(), UtilDto.class, false);
	}

	@Override
	public List<FaultTable> queryFaultDto(FaultArgs args) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select fault_serial, train_serial, vehicle_serial, fault_number, fault_place, fault_id, detector, detect_time, found_mode, transactor, transact_time, transact_mode, affirmant, verify_time, ponderance, last_repair_date, position_left, position_right, position_top, position_bottom, img_length, img_type, remark, lock_version, img_index, del_flag, del_time, del_user, view_flag, view_time, view_user, cxinfo, dxinfo, fxinfo, down_time, fk_time, o_id_down, down_receive_time, down_receive_user, fk_user, fk_status, tjzby_flag, tjzby_time, tjzby_user, identify_id, fk_lj, zwxx, image_name, identify_result, o_id_check, check_time, check_result, addtime, confidence, info_created_result, image_created_result, info_created_remark, image_created_remark, fault_name from tf_op_fault  t where 1=1 ");
		if (StringUtils.isNotEmpty(args.getVEHICLE_SERIAL())) {
			sql.append(" and t.vehicle_serial='" + args.getVEHICLE_SERIAL() + "'");
		}
		return listBySql(sql.toString(), FaultTable.class, false);
	}

	@Override
	public PageResult queryDatavEditing(UtilArgs args) {
		StringBuffer sql = new StringBuffer();
		try {
			PageResult result = null;
			sql.append(" select tt.train_id, tt.train_serial, vv.vehicle_id, vv.vehicle_serial, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time, tt.station_id, vv.vehicle_type, tt.index_id, vv.vehicle_order, tcf.station_name,tf.fault_name from tf_op_vehicle vv, tf_op_train tt, tf_cfg_detectstation tcf,schedule_record sr,tf_op_fault tf where vv.train_serial = tt.train_serial and tcf.station_id = tt.station_id and sr.vehicle_serial = vv.vehicle_serial and sr.img_state =1 and tf.vehicle_serial = vv.vehicle_serial and vv.vehicle_id is not null ");
			if (StringUtils.isNotEmpty(args.getStartTime())) {
				sql.append(" and tt.pass_time >= to_date('" + args.getStartTime() + "', 'yyyy-MM-dd hh24:mi:ss') ");
			}
			if (StringUtils.isNotEmpty(args.getEndTime())) {
				sql.append(" and tt.pass_time <= to_date('" + args.getEndTime() + "', 'yyyy-MM-dd hh24:mi:ss')");
			}
			if (StringUtils.isNotEmpty(args.getStationcode())) {
				sql.append(" and tt.station_id='" + args.getStationcode() + "'");
			}
			sql.append(" order by tt.train_id desc");
			result = super.getPageListSql(args.getPage(), args.getRows(), sql.toString());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BpTypeDto> queryDatavEditingType(BpTypeArgs args) {
		StringBuffer sql = new StringBuffer();
		sql.append("  select distinct vv.vehicle_id, vv.vehicle_serial, tt.train_id, tt.train_serial, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time, tt.station_id, vv.vehicle_type, tt.index_id, vv.vehicle_order, tcf.station_name from tf_op_vehicle vv, tf_op_train tt, tf_cfg_detectstation tcf, schedule_record sr, tf_op_fault tf where vv.train_serial = tt.train_serial and tcf.station_id = tt.station_id and sr.vehicle_serial = vv.vehicle_serial and sr.img_state = 1  and tf.vehicle_serial = vv.vehicle_serial and vv.vehicle_id is not null ");
		// sql.append(" select tt.train_id, tt.train_serial, vv.vehicle_id, vv.vehicle_serial, to_char(tt.pass_time, 'yyyy-MM-dd hh24:mi:ss') as pass_time, tt.station_id, vv.vehicle_type, tt.index_id, vv.vehicle_order, tcf.station_name,tf.fault_name from tf_op_vehicle vv, tf_op_train tt, tf_cfg_detectstation tcf,schedule_record sr,tf_op_fault tf where vv.train_serial = tt.train_serial and tcf.station_id = tt.station_id and sr.vehicle_serial = vv.vehicle_serial and sr.img_state =1 and tf.vehicle_serial = vv.vehicle_serial and vv.vehicle_id is not null ");
		if (args.getTypeCar() != null && args.getTypeCar().length > 0) {
			String str = "";
			for (String strArgs : args.getTypeCar()) {
				if (str == "") {
					str += "'" + strArgs;
				} else {
					str += "|" + strArgs;
				}
			}
			str = str + "'";
			sql.append(" and REGEXP_LIKE(vv.vehicle_type, " + str + ") ");
			/*
			 * select * from table where REGEXP_LIKE(字段名, '(匹配串1|匹配串2|...)') ;//全模糊匹配
			 * 
			 * select * from table where REGEXP_LIKE(字段名, '^(匹配串1|匹配串2|...)') ;//右模糊匹配
			 * 
			 * select * from table where REGEXP_LIKE(字段名, '(匹配串1|匹配串2|...)$') ;//左模糊匹配
			 */ // sql.append(" and vv.vehicle_type like '"+args.getTypeCar()+"%' ");
		}
		if (args.getTypeTransactmode() != null && args.getTypeTransactmode().length > 0) {
			String str = "";
			for (String strArgs : args.getTypeTransactmode()) {
				if (str == "") {
					str += "'" + strArgs + "'";
				} else {
					str += ",'" + strArgs + "'";
				}
			}
			sql.append(" and tf.transact_mode in (" + str + ") ");
		}
		if (args.getTypeCarNum() > 0) {
			sql.append("  and rownum<=" + args.getTypeCarNum() + " ");
		}
		/*
		 * if(args.getTypeTransactmodeNum()>0) { sql.append("  and rownum<="+args.getTypeTransactmodeNum()+" "); }
		 */
		sql.append(" order by tt.train_id desc");
		return super.listBySql(sql.toString(), BpTypeDto.class, false);
	}

}
