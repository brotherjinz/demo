package com.web.info.service;

import java.util.List;

import com.web.info.args.BpTypeArgs;
import com.web.info.args.UtilArgs;
import com.web.info.dto.TrainDto;
import com.web.info.dto.UtilDto;
import com.web.info.model.Fault;

import net.sf.json.JSONObject;


public interface FaultService {
	
	List<Fault> queryList(UtilArgs args);
	
	Object queryPager(UtilArgs args,String stationcode);
	
	Object syncQueryPager(UtilArgs args);
	
	Object syncSuccessQueryPager(UtilArgs args);
	
	Object queryDataPager(UtilArgs args);
	
	Object queryDatavEditing(UtilArgs args);
	
	JSONObject querySelectType(BpTypeArgs args);
	
	boolean faultData(UtilArgs args,String stationcode);//定时任务同步数据
	//根据条件查询全部故障数据
	List<UtilDto> queryFault(UtilArgs args);
	
	List<UtilDto> expQueryFault(UtilArgs args);
	
	Object  queryAll(UtilArgs args);
	
	boolean vEditing(List<String> list,String stationcode); 
}
