package com.web.info.args;

public class ConfigArgs {
	private String CONFIGID;// 配置主键ID
	private String PATHTEMP;// 临时缓存文件夹路径
	private String PATHFOLDER;// 图片存储文件夹
	private String PATHURL;// 远程取图地址
	private String STATIONCODE;// 探测站编码
	private String STATIONNAME;// 探测站名称
	private String STATION_DB_TYPE;// 数据库类型 1/2 1 oracle，2 mysql
	private String STATION_DB_URL;// 数据库链接url
	private String STATION_DB_DRIVER_CLASS_NAME;// 数据库驱动
	private String STATION_DB_USERNAME;// 数据库登陆用户名
	private String STATION_DB_PASSWORD;// 数据库登陆密码
	private String STATION_DB_SID;// SID
	private String rows;
	private String page;
	private String OPERATION;

	public String getCONFIGID() {
		return CONFIGID;
	}

	public void setCONFIGID(String cONFIGID) {
		CONFIGID = cONFIGID;
	}

	public String getPATHTEMP() {
		return PATHTEMP;
	}

	public void setPATHTEMP(String pATHTEMP) {
		PATHTEMP = pATHTEMP;
	}

	public String getPATHFOLDER() {
		return PATHFOLDER;
	}

	public void setPATHFOLDER(String pATHFOLDER) {
		PATHFOLDER = pATHFOLDER;
	}

	public String getPATHURL() {
		return PATHURL;
	}

	public void setPATHURL(String pATHURL) {
		PATHURL = pATHURL;
	}

	public String getSTATIONCODE() {
		return STATIONCODE;
	}

	public void setSTATIONCODE(String sTATIONCODE) {
		STATIONCODE = sTATIONCODE;
	}

	public String getSTATIONNAME() {
		return STATIONNAME;
	}

	public void setSTATIONNAME(String sTATIONNAME) {
		STATIONNAME = sTATIONNAME;
	}

	public String getSTATION_DB_TYPE() {
		return STATION_DB_TYPE;
	}

	public void setSTATION_DB_TYPE(String sTATION_DB_TYPE) {
		STATION_DB_TYPE = sTATION_DB_TYPE;
	}

	public String getSTATION_DB_URL() {
		return STATION_DB_URL;
	}

	public void setSTATION_DB_URL(String sTATION_DB_URL) {
		STATION_DB_URL = sTATION_DB_URL;
	}

	public String getSTATION_DB_DRIVER_CLASS_NAME() {
		return STATION_DB_DRIVER_CLASS_NAME;
	}

	public void setSTATION_DB_DRIVER_CLASS_NAME(String sTATION_DB_DRIVER_CLASS_NAME) {
		STATION_DB_DRIVER_CLASS_NAME = sTATION_DB_DRIVER_CLASS_NAME;
	}

	public String getSTATION_DB_USERNAME() {
		return STATION_DB_USERNAME;
	}

	public void setSTATION_DB_USERNAME(String sTATION_DB_USERNAME) {
		STATION_DB_USERNAME = sTATION_DB_USERNAME;
	}

	public String getSTATION_DB_PASSWORD() {
		return STATION_DB_PASSWORD;
	}

	public void setSTATION_DB_PASSWORD(String sTATION_DB_PASSWORD) {
		STATION_DB_PASSWORD = sTATION_DB_PASSWORD;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getOPERATION() {
		return OPERATION;
	}

	public void setOPERATION(String oPERATION) {
		OPERATION = oPERATION;
	}

	public String getSTATION_DB_SID() {
		return STATION_DB_SID;
	}

	public void setSTATION_DB_SID(String sTATION_DB_SID) {
		STATION_DB_SID = sTATION_DB_SID;
	}

}
