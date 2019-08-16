package com.web.info.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.info.args.VehicleArgs;
import com.web.info.dao.VehicleDao;
import com.web.info.dto.VehicleDto;
import com.web.info.model.Vehicle;
import com.web.info.service.VehicleService;
import com.web.util.DataSourceConst;
import com.web.util.DataSourceContextHolder;

@Service
public class VehicleServiceImpl implements VehicleService {

	private static Logger log = Logger.getLogger(VehicleServiceImpl.class);
	
	@Autowired
	private VehicleDao vehicleDao;
	@Override
	public boolean insertVehicleData(Set<String> vehicleSet,String stationcode) {
		try {
			DataSourceContextHolder.setDataSourseType(stationcode);
			List<VehicleDto> listVehicle = vehicleDao.queryVehicle(vehicleSet);
			DataSourceContextHolder.setDataSourseType(DataSourceConst.default_db);
			for (VehicleDto vehicleDto : listVehicle) {
				Vehicle vehicle = new Vehicle();
				BeanUtils.copyProperties(vehicleDto, vehicle);
				vehicleDao.add(vehicle);
			}
			return true;
		} catch (BeansException e) {
			return false;
		}
	}
	@Override
	public List<VehicleDto> queryVehicleDto(VehicleArgs args) {
		return vehicleDao.queryVehicleDto(args);
	}

}
