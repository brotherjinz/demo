package com.web.info.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 菜单
 * 
 * @author 李亚洲 2017年6月6日上午10:30:50
 */
@Entity
@Table(name = "TD_MENU")
public class MenuInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "td_menu_id_seq")
	@SequenceGenerator(name = "td_menu_id_seq", sequenceName = "td_menu_id_seq", allocationSize = 1)
	private Integer menu_id;
	private String p_menu_id;
	private String menu_name;
	private String menu_url;
	private Integer menu_order_by;
	private String is_deleted;
	private Date create_time;
	private String menu_ico_url;

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public String getP_menu_id() {
		return p_menu_id;
	}

	public void setP_menu_id(String p_menu_id) {
		this.p_menu_id = p_menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public Integer getMenu_order_by() {
		return menu_order_by;
	}

	public void setMenu_order_by(Integer menu_order_by) {
		this.menu_order_by = menu_order_by;
	}

	public String getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getMenu_ico_url() {
		return menu_ico_url;
	}

	public void setMenu_ico_url(String menu_ico_url) {
		this.menu_ico_url = menu_ico_url;
	}

}
