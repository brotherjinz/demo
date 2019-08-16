package com.web.info.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.web.info.args.VehicleArgs;
import com.web.info.dao.VehicleDao;
import com.web.info.dto.VehicleDto;
import com.web.info.model.Vehicle;
@Repository
public class VehicleDaoImpl extends BaseDao<Vehicle> implements VehicleDao {

	@Override
	public List<VehicleDto> queryVehicle(Set<String> vehicleSet) {
		StringBuffer sql = new StringBuffer();
		sql.append("select vehicle_serial, train_serial, vehicle_id, vehicle_sort, vehicle_type, vehicle_order, made_factory, made_date, conversion, vehicle_owner, axes_distance, midpart_pics_number, lock_version, fault_auto, fault_manul, car_axis_number, grab_info, cmspcar_pics_number, dmspcar_pics_number, pos_ab_flag, midpartcm_pics_number, cmctcar_pics_number, cmcar_pics_number, dmcar_pics_number from tf_op_vehicle t where 1=1 ");
		if(vehicleSet !=null && vehicleSet.size()>0){
			String ve = "";
			for (String str : vehicleSet) {
				if (ve == "") {
					ve += "'" + str + "'";
				} else {
					ve += ",'" + str + "'";
				}
			}
			sql.append(" and t.vehicle_serial in ("+ve+")");
		}
		return super.listBySql(sql.toString(),VehicleDto.class, false);
	}

	@Override
	public List<VehicleDto> queryVehicleDto(VehicleArgs args) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select vehicle_serial, train_serial, vehicle_id, vehicle_sort, vehicle_type, vehicle_order, made_factory, made_date, conversion, vehicle_owner, axes_distance, midpart_pics_number, lock_version, fault_auto, fault_manul, car_axis_number, grab_info, cmspcar_pics_number, dmspcar_pics_number, pos_ab_flag, midpartcm_pics_number, cmctcar_pics_number, cmcar_pics_number, dmcar_pics_number from tf_op_vehicle t where 1=1 ");
		if(StringUtils.isNotEmpty(args.getTRAIN_SERIAL())){
			sql.append(" and t.train_serial='"+args.getTRAIN_SERIAL()+"'");
		}
		return super.listBySql(sql.toString(),VehicleDto.class, false);
	}

}
