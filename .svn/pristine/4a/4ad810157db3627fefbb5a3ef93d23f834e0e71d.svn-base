package com.web.info.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "task_schedule")
public class ScheduleJob implements Serializable {

	private static long serialVersionUID = 1L;

	/** 任务id */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	private String SCHEDULEJOBID;

	/** 任务名称 */
	private String JOBNAME;

	/** 任务分组 */
	private String JOBGROUP;

	/** 任务别名 */
	private String ALIASNAME;

	/** 指定执行类 */
	private String JOBCLASS;

	/** 任务状态 0停用 1启用 2删除 */
	private Integer STATUS;

	/** 任务运行时间表达式 */
	private String CRONEXPRESSION;

	/** 任务描述 */
	private String DESCRIPTION;

	/** 创建时间 */
	private Date CREATETIME;

	/** 修改时间 */
	private Date UPDATETIME;
	

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		ScheduleJob.serialVersionUID = serialVersionUID;
	}

	public String getSCHEDULEJOBID() {
		return SCHEDULEJOBID;
	}

	public void setSCHEDULEJOBID(String sCHEDULEJOBID) {
		SCHEDULEJOBID = sCHEDULEJOBID;
	}

	public String getJOBNAME() {
		return JOBNAME;
	}

	public void setJOBNAME(String jOBNAME) {
		JOBNAME = jOBNAME;
	}

	public String getJOBGROUP() {
		return JOBGROUP;
	}

	public void setJOBGROUP(String jOBGROUP) {
		JOBGROUP = jOBGROUP;
	}

	public String getALIASNAME() {
		return ALIASNAME;
	}

	public void setALIASNAME(String aLIASNAME) {
		ALIASNAME = aLIASNAME;
	}

	public String getJOBCLASS() {
		return JOBCLASS;
	}

	public void setJOBCLASS(String jOBCLASS) {
		JOBCLASS = jOBCLASS;
	}

	public Integer getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}

	public String getCRONEXPRESSION() {
		return CRONEXPRESSION;
	}

	public void setCRONEXPRESSION(String cRONEXPRESSION) {
		CRONEXPRESSION = cRONEXPRESSION;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public Date getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(Date cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public Date getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(Date uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}

}
