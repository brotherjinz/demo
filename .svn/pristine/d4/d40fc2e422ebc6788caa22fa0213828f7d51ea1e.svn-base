package com.web.info.dao;

import java.util.List;

import com.web.info.args.BpTypeArgs;
import com.web.info.args.FaultArgs;
import com.web.info.args.UtilArgs;
import com.web.info.dto.BpTypeDto;
import com.web.info.dto.FaultTable;
import com.web.info.dto.UtilDto;
import com.web.info.model.Fault;
import com.web.info.system.PageResult;

public interface FaultDao extends IBase<Fault> {
	
	List<FaultTable> queryFaultDto(FaultArgs args);
	
	List<Fault> queryListDao(UtilArgs args);
	List<Fault> getListFault(UtilArgs args);
	//根据条件查询完整信息
	List<UtilDto> queryFaultData(UtilArgs args);
	List<UtilDto> queryFaultList(UtilArgs args);
	List<UtilDto> expQueryFault(UtilArgs args);
	PageResult queryPager(UtilArgs args);
	PageResult syncQueryPager(UtilArgs args);
	PageResult syncSuccessGetPager(UtilArgs args);
	PageResult queryDataPager(UtilArgs args);
	PageResult queryDatavEditing(UtilArgs args);
	List<BpTypeDto> queryDatavEditingType(BpTypeArgs args);
}
