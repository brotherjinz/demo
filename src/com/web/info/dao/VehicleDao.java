package com.web.info.dao;

import java.util.List;
import java.util.Set;

import com.web.info.args.VehicleArgs;
import com.web.info.dto.VehicleDto;
import com.web.info.model.Vehicle;

public interface VehicleDao extends IBase<Vehicle> {
	
	List<VehicleDto> queryVehicle(Set<String> vehicleSet);
	List<VehicleDto> queryVehicleDto(VehicleArgs args);
}
