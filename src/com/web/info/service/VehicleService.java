package com.web.info.service;

import java.util.List;
import java.util.Set;

import com.web.info.args.VehicleArgs;
import com.web.info.dto.VehicleDto;

public interface VehicleService {
	boolean insertVehicleData(Set<String> vehicleSet,String stationcode);
	
	List<VehicleDto> queryVehicleDto(VehicleArgs args);
}
