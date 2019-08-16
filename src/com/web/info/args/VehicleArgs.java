package com.web.info.args;


public class VehicleArgs {
	private String VEHICLE_SERIAL; // 车辆唯一号 (VARCHAR2(40) )
	private String TRAIN_SERIAL; // 列车唯一号 (VARCHAR2(40) )
	private String VEHICLE_ID; // 车号 (VARCHAR2(7) )
	private String VEHICLE_SORT; // 车辆类型 (VARCHAR2(1) )
	private String VEHICLE_TYPE; // 车型 (VARCHAR2(10) )
	private String VEHICLE_ORDER; // 辆序 (VARCHAR2(3) )
	private String MADE_FACTORY; // 制造厂 (VARCHAR2(1) )
	private String MADE_DATE; // 制造日期 (VARCHAR2(10) )
	private String CONVERSION; // 换长 (VARCHAR2(5) )
	private String VEHICLE_OWNER; // 车辆属性 (VARCHAR2(1) )
	private String AXES_DISTANCE; // 轴距 (VARCHAR2(160) )
	private Integer MIDPART_PICS_NUMBER; // 中间部张数 (NUMBER )
	private Integer LOCK_VERSION; // 乐观锁定标识 (NUMBER )
	private Integer FAULT_AUTO; // 自动识别故障数 (NUMBER )
	private Integer FAULT_MANUL; // 人工识别故障数 (NUMBER )
	private Integer CAR_AXIS_NUMBER; // 车辆轴数 (NUMBER(2) )
	private String GRAB_INFO; // 拍摄图像定位信息 (VARCHAR2(100) )
	private Integer CMSPCAR_PICS_NUMBER; // 存储车辆侧部单通道相机采集的图像数量 (NUMBER(5) )
	private Integer DMSPCAR_PICS_NUMBER; // 存储车辆底部单通道相机采集的图像数量 (NUMBER(5) )
	private String POS_AB_FLAG; // (VARCHAR2(1) )
	private Integer MIDPARTCM_PICS_NUMBER; // 存储车中间部侧部单通道相机采集的图像数量,车辆种类为0时填写 (NUMBER(3) )
	private Integer CMCTCAR_PICS_NUMBER; // 存储车辆侧面定检单通道相机采集的图像数量 (NUMBER(3) )
	private Integer CMCAR_PICS_NUMBER; // 存储侧部线扫描相机单通道相机采集的图像数量 (NUMBER(3) )
	private Integer DMCAR_PICS_NUMBER; // 存储底部线扫描相机单通道相机采集的图像数量 (NUMBER(3) )
	private String IS_TRACK; // 是否追踪 (VARCHAR2(10) )
	private String WORK_MODE; // 作业模式 (VARCHAR2(10) )
	private String WORK_STATE; // 作业状态 (VARCHAR2(10) )
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
	public String getVEHICLE_ID() {
		return VEHICLE_ID;
	}
	public void setVEHICLE_ID(String vEHICLE_ID) {
		VEHICLE_ID = vEHICLE_ID;
	}
	public String getVEHICLE_SORT() {
		return VEHICLE_SORT;
	}
	public void setVEHICLE_SORT(String vEHICLE_SORT) {
		VEHICLE_SORT = vEHICLE_SORT;
	}
	public String getVEHICLE_TYPE() {
		return VEHICLE_TYPE;
	}
	public void setVEHICLE_TYPE(String vEHICLE_TYPE) {
		VEHICLE_TYPE = vEHICLE_TYPE;
	}
	public String getVEHICLE_ORDER() {
		return VEHICLE_ORDER;
	}
	public void setVEHICLE_ORDER(String vEHICLE_ORDER) {
		VEHICLE_ORDER = vEHICLE_ORDER;
	}
	public String getMADE_FACTORY() {
		return MADE_FACTORY;
	}
	public void setMADE_FACTORY(String mADE_FACTORY) {
		MADE_FACTORY = mADE_FACTORY;
	}
	public String getMADE_DATE() {
		return MADE_DATE;
	}
	public void setMADE_DATE(String mADE_DATE) {
		MADE_DATE = mADE_DATE;
	}
	public String getCONVERSION() {
		return CONVERSION;
	}
	public void setCONVERSION(String cONVERSION) {
		CONVERSION = cONVERSION;
	}
	public String getVEHICLE_OWNER() {
		return VEHICLE_OWNER;
	}
	public void setVEHICLE_OWNER(String vEHICLE_OWNER) {
		VEHICLE_OWNER = vEHICLE_OWNER;
	}
	public String getAXES_DISTANCE() {
		return AXES_DISTANCE;
	}
	public void setAXES_DISTANCE(String aXES_DISTANCE) {
		AXES_DISTANCE = aXES_DISTANCE;
	}
	public Integer getMIDPART_PICS_NUMBER() {
		return MIDPART_PICS_NUMBER;
	}
	public void setMIDPART_PICS_NUMBER(Integer mIDPART_PICS_NUMBER) {
		MIDPART_PICS_NUMBER = mIDPART_PICS_NUMBER;
	}
	public Integer getLOCK_VERSION() {
		return LOCK_VERSION;
	}
	public void setLOCK_VERSION(Integer lOCK_VERSION) {
		LOCK_VERSION = lOCK_VERSION;
	}
	public Integer getFAULT_AUTO() {
		return FAULT_AUTO;
	}
	public void setFAULT_AUTO(Integer fAULT_AUTO) {
		FAULT_AUTO = fAULT_AUTO;
	}
	public Integer getFAULT_MANUL() {
		return FAULT_MANUL;
	}
	public void setFAULT_MANUL(Integer fAULT_MANUL) {
		FAULT_MANUL = fAULT_MANUL;
	}
	public Integer getCAR_AXIS_NUMBER() {
		return CAR_AXIS_NUMBER;
	}
	public void setCAR_AXIS_NUMBER(Integer cAR_AXIS_NUMBER) {
		CAR_AXIS_NUMBER = cAR_AXIS_NUMBER;
	}
	public String getGRAB_INFO() {
		return GRAB_INFO;
	}
	public void setGRAB_INFO(String gRAB_INFO) {
		GRAB_INFO = gRAB_INFO;
	}
	public Integer getCMSPCAR_PICS_NUMBER() {
		return CMSPCAR_PICS_NUMBER;
	}
	public void setCMSPCAR_PICS_NUMBER(Integer cMSPCAR_PICS_NUMBER) {
		CMSPCAR_PICS_NUMBER = cMSPCAR_PICS_NUMBER;
	}
	public Integer getDMSPCAR_PICS_NUMBER() {
		return DMSPCAR_PICS_NUMBER;
	}
	public void setDMSPCAR_PICS_NUMBER(Integer dMSPCAR_PICS_NUMBER) {
		DMSPCAR_PICS_NUMBER = dMSPCAR_PICS_NUMBER;
	}
	public String getPOS_AB_FLAG() {
		return POS_AB_FLAG;
	}
	public void setPOS_AB_FLAG(String pOS_AB_FLAG) {
		POS_AB_FLAG = pOS_AB_FLAG;
	}
	public Integer getMIDPARTCM_PICS_NUMBER() {
		return MIDPARTCM_PICS_NUMBER;
	}
	public void setMIDPARTCM_PICS_NUMBER(Integer mIDPARTCM_PICS_NUMBER) {
		MIDPARTCM_PICS_NUMBER = mIDPARTCM_PICS_NUMBER;
	}
	public Integer getCMCTCAR_PICS_NUMBER() {
		return CMCTCAR_PICS_NUMBER;
	}
	public void setCMCTCAR_PICS_NUMBER(Integer cMCTCAR_PICS_NUMBER) {
		CMCTCAR_PICS_NUMBER = cMCTCAR_PICS_NUMBER;
	}
	public Integer getCMCAR_PICS_NUMBER() {
		return CMCAR_PICS_NUMBER;
	}
	public void setCMCAR_PICS_NUMBER(Integer cMCAR_PICS_NUMBER) {
		CMCAR_PICS_NUMBER = cMCAR_PICS_NUMBER;
	}
	public Integer getDMCAR_PICS_NUMBER() {
		return DMCAR_PICS_NUMBER;
	}
	public void setDMCAR_PICS_NUMBER(Integer dMCAR_PICS_NUMBER) {
		DMCAR_PICS_NUMBER = dMCAR_PICS_NUMBER;
	}
	public String getIS_TRACK() {
		return IS_TRACK;
	}
	public void setIS_TRACK(String iS_TRACK) {
		IS_TRACK = iS_TRACK;
	}
	public String getWORK_MODE() {
		return WORK_MODE;
	}
	public void setWORK_MODE(String wORK_MODE) {
		WORK_MODE = wORK_MODE;
	}
	public String getWORK_STATE() {
		return WORK_STATE;
	}
	public void setWORK_STATE(String wORK_STATE) {
		WORK_STATE = wORK_STATE;
	}
	
}
