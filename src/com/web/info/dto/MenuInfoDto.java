package com.web.info.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MenuInfoDto {
	private BigDecimal MENU_ID;
	private String P_MENU_ID;
	private String MENU_NAME;
	private String MENU_URL;
	private BigDecimal MENU_ORDER_BY;
	private String IS_DELETED;
	private Date CREATE_TIME;
	private String MENU_ICO_URL;
	private String FATHER_MENU_NAME;
	private Integer _parentId;
	private Integer id;
	private String name;
	private String state;

	public Integer get_parentId() {
		return _parentId;
	}

	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFATHER_MENU_NAME() {
		return FATHER_MENU_NAME;
	}

	public void setFATHER_MENU_NAME(String fATHER_MENU_NAME) {
		FATHER_MENU_NAME = fATHER_MENU_NAME;
	}

	public BigDecimal getMENU_ID() {
		return MENU_ID;
	}

	public void setMENU_ID(BigDecimal mENU_ID) {
		MENU_ID = mENU_ID;
	}

	public String getP_MENU_ID() {
		return P_MENU_ID;
	}

	public void setP_MENU_ID(String p_MENU_ID) {
		P_MENU_ID = p_MENU_ID;
	}

	public String getMENU_NAME() {
		return MENU_NAME;
	}

	public void setMENU_NAME(String mENU_NAME) {
		MENU_NAME = mENU_NAME;
	}

	public String getMENU_URL() {
		return MENU_URL;
	}

	public void setMENU_URL(String mENU_URL) {
		MENU_URL = mENU_URL;
	}

	public BigDecimal getMENU_ORDER_BY() {
		return MENU_ORDER_BY;
	}

	public void setMENU_ORDER_BY(BigDecimal mENU_ORDER_BY) {
		MENU_ORDER_BY = mENU_ORDER_BY;
	}

	public String getIS_DELETED() {
		return IS_DELETED;
	}

	public void setIS_DELETED(String iS_DELETED) {
		IS_DELETED = iS_DELETED;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getMENU_ICO_URL() {
		return MENU_ICO_URL;
	}

	public void setMENU_ICO_URL(String mENU_ICO_URL) {
		MENU_ICO_URL = mENU_ICO_URL;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
