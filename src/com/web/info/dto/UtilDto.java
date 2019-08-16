package com.web.info.dto;

import java.math.BigDecimal;

public class UtilDto {
	private String PASS_TIME;//过车时间
	private BigDecimal VEHICLE_ORDER;//辆序
	private String STATION_ID;//探测站编码
	private String VEHICLE_SERIAL;//车辆唯一号
	private String VEHICLE_ID;//车号
	private String TRAIN_ID;//车次
	private String VEHICLE_TYPE;//车型
	private String STATION_NAME;//探测站名称
	private String FAULT_NAME;//故障名称
	private String REMARK;//描述
	private String FAULT_SERIAL;//故障id
	private String TRAIN_SERIAL;//车次id
	private BigDecimal INDEX_ID;//目录号
	
	public String getPASS_TIME() {
		return PASS_TIME;
	}
	public void setPASS_TIME(String pASS_TIME) {
		PASS_TIME = pASS_TIME;
	}
	public BigDecimal getVEHICLE_ORDER() {
		return VEHICLE_ORDER;
	}
	public void setVEHICLE_ORDER(BigDecimal vEHICLE_ORDER) {
		VEHICLE_ORDER = vEHICLE_ORDER;
	}
	public String getSTATION_ID() {
		return STATION_ID;
	}
	public void setSTATION_ID(String sTATION_ID) {
		STATION_ID = sTATION_ID;
	}
	public String getVEHICLE_SERIAL() {
		return VEHICLE_SERIAL;
	}
	public void setVEHICLE_SERIAL(String vEHICLE_SERIAL) {
		VEHICLE_SERIAL = vEHICLE_SERIAL;
	}
	public String getVEHICLE_ID() {
		return VEHICLE_ID;
	}
	public void setVEHICLE_ID(String vEHICLE_ID) {
		VEHICLE_ID = vEHICLE_ID;
	}
	public String getTRAIN_ID() {
		return TRAIN_ID;
	}
	public void setTRAIN_ID(String tRAIN_ID) {
		TRAIN_ID = tRAIN_ID;
	}
	public String getSTATION_NAME() {
		return STATION_NAME;
	}
	public void setSTATION_NAME(String sTATION_NAME) {
		STATION_NAME = sTATION_NAME;
	}
	public String getFAULT_NAME() {
		return FAULT_NAME;
	}
	public void setFAULT_NAME(String fAULT_NAME) {
		FAULT_NAME = fAULT_NAME;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getFAULT_SERIAL() {
		return FAULT_SERIAL;
	}
	public void setFAULT_SERIAL(String fAULT_SERIAL) {
		FAULT_SERIAL = fAULT_SERIAL;
	}
	public String getVEHICLE_TYPE() {
		return VEHICLE_TYPE;
	}
	public void setVEHICLE_TYPE(String vEHICLE_TYPE) {
		VEHICLE_TYPE = vEHICLE_TYPE;
	}
	public String getTRAIN_SERIAL() {
		return TRAIN_SERIAL;
	}
	public void setTRAIN_SERIAL(String tRAIN_SERIAL) {
		TRAIN_SERIAL = tRAIN_SERIAL;
	}
	public BigDecimal getINDEX_ID() {
		return INDEX_ID;
	}
	public void setINDEX_ID(BigDecimal iNDEX_ID) {
		INDEX_ID = iNDEX_ID;
	}
	
}
