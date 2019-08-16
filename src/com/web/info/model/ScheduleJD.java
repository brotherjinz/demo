package com.web.info.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 同步节点记录表
 * <p>Title: ScheduleJD</p>  
 * <p>Description: </p>  
 * @author 李亚洲  
 * @date 2018年8月13日
 */
@Entity
@Table(name = "TASK_SCHEDULE_JD")
public class ScheduleJD {
	private String JD_ID;//节点id
	private String TABLENAME;//同步表名
	private String COLUMNNAME;//节点列名标识
	private String COLUMNVALUE_START;//节点列的值
	private String COLUMNVALUE_END;//节点结束值
	private Date SCHEDULETIME;//同步时间
	private Integer SCHEDULEORDER;//排序
	private String STATIONCODE;//探测站编码
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	public String getJD_ID() {
		return JD_ID;
	}
	public void setJD_ID(String jD_ID) {
		JD_ID = jD_ID;
	}
	public String getTABLENAME() {
		return TABLENAME;
	}
	public void setTABLENAME(String tABLENAME) {
		TABLENAME = tABLENAME;
	}
	public String getCOLUMNNAME() {
		return COLUMNNAME;
	}
	public void setCOLUMNNAME(String cOLUMNNAME) {
		COLUMNNAME = cOLUMNNAME;
	}
	public String getCOLUMNVALUE_START() {
		return COLUMNVALUE_START;
	}
	public void setCOLUMNVALUE_START(String cOLUMNVALUE_START) {
		COLUMNVALUE_START = cOLUMNVALUE_START;
	}
	public String getCOLUMNVALUE_END() {
		return COLUMNVALUE_END;
	}
	public void setCOLUMNVALUE_END(String cOLUMNVALUE_END) {
		COLUMNVALUE_END = cOLUMNVALUE_END;
	}
	public Date getSCHEDULETIME() {
		return SCHEDULETIME;
	}
	public void setSCHEDULETIME(Date sCHEDULETIME) {
		SCHEDULETIME = sCHEDULETIME;
	}
	public Integer getSCHEDULEORDER() {
		return SCHEDULEORDER;
	}
	public void setSCHEDULEORDER(Integer sCHEDULEORDER) {
		SCHEDULEORDER = sCHEDULEORDER;
	}
	public String getSTATIONCODE() {
		return STATIONCODE;
	}
	public void setSTATIONCODE(String sTATIONCODE) {
		STATIONCODE = sTATIONCODE;
	}
}
