package com.web.info.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 同步数据记录表
 * <p>Title: ScheduleRecord</p>  
 * <p>Description: </p>  
 * @author 李亚洲  
 * @date 2018年8月13日
 */
@Entity
@Table(name = "SCHEDULE_RECORD")
public class ScheduleRecord {
	private String FAULT_SERIAL;//故障唯一号   可当id用
	private Date SCHEDULE_TIME;//同步时间
	private Integer DATA_STATE;//数据同步状态    0未同步，1已同步
	private Integer IMG_STATE;//图片数据同步状态 0未同步，1已同步
	private Integer STATE;//数据状态，是否删除，逻辑删除   0 正常，1删除
	private String STATIONCODE;//探测站编码
	private String VEHICLE_SERIAL;//车辆唯一号
	private String TRAIN_SERIAL;//车次唯一号
	@Id
	public String getFAULT_SERIAL() {
		return FAULT_SERIAL;
	}
	public void setFAULT_SERIAL(String fAULT_SERIAL) {
		FAULT_SERIAL = fAULT_SERIAL;
	}
	public Date getSCHEDULE_TIME() {
		return SCHEDULE_TIME;
	}
	public void setSCHEDULE_TIME(Date sCHEDULE_TIME) {
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
	public String getSTATIONCODE() {
		return STATIONCODE;
	}
	public void setSTATIONCODE(String sTATIONCODE) {
		STATIONCODE = sTATIONCODE;
	}
	public String getVEHICLE_SERIAL() {
		return VEHICLE_SERIAL;
	}
	public void setVEHICLE_SERIAL(String vEHICLE_SERIAL) {
		VEHICLE_SERIAL = vEHICLE_SERIAL;
	}
	public String getTRAIN_SERIAL() {
		return TRAIN_SERIAL;
	}
	public void setTRAIN_SERIAL(String tRAIN_SERIAL) {
		TRAIN_SERIAL = tRAIN_SERIAL;
	}
	
}
