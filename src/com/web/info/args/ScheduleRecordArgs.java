package com.web.info.args;


public class ScheduleRecordArgs {
	private String FAULT_SERIAL;//故障唯一号   可当id用
	private String SCHEDULE_TIME;//同步时间
	private Integer DATA_STATE;//数据同步状态    0未同步，1已同步
	private Integer IMG_STATE;//图片数据同步状态 0未同步，1已同步
	private Integer STATE;//数据状态，是否删除，逻辑删除   0 正常，1删除
	public String startTime;//开始时间
	public String endTime;//结束时间
	private String stationcode;//探测站编码
	public String getFAULT_SERIAL() {
		return FAULT_SERIAL;
	}
	public void setFAULT_SERIAL(String fAULT_SERIAL) {
		FAULT_SERIAL = fAULT_SERIAL;
	}
	public String getSCHEDULE_TIME() {
		return SCHEDULE_TIME;
	}
	public void setSCHEDULE_TIME(String sCHEDULE_TIME) {
		SCHEDULE_TIME = sCHEDULE_TIME;
	}
	public Integer getDATA_STATE() {
		return DATA_STATE;
	}
	public void setDATA_STATE(Integer dATA_STATE) {
		DATA_STATE = dATA_STATE;
	}
	public Integer getIMG_STATE() {
		return IMG_STATE;
	}
	public void setIMG_STATE(Integer iMG_STATE) {
		IMG_STATE = iMG_STATE;
	}
	public Integer getSTATE() {
		return STATE;
	}
	public void setSTATE(Integer sTATE) {
		STATE = sTATE;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStationcode() {
		return stationcode;
	}
	public void setStationcode(String stationcode) {
		this.stationcode = stationcode;
	}
	
}
