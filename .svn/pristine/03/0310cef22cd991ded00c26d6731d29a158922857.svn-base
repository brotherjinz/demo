package com.web.info.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.web.info.args.MenuInfoArgs;
import com.web.info.dao.MenuInfoDao;
import com.web.info.dto.MenuInfoDto;
import com.web.info.model.MenuInfo;

@Repository
public class MenuInfoDaoImpl extends BaseDao<MenuInfo> implements MenuInfoDao {

	@Override
	public List<MenuInfoDto> getMenuInfoDtos(MenuInfoArgs args) {
		StringBuffer sql = new StringBuffer();
		List<Object> arg = new ArrayList<Object>();
		//sql.append("select menu_id, create_time, is_deleted, menu_ico_url, menu_name, menu_order_by, menu_url, p_menu_id from td_menu tm where tm.menu_id in (select t.menu_id from td_role_menu t where t.role_id in (select tur.role_id from td_user_role tur where 1=1 ");
		sql.append("select menu_id, create_time, is_deleted, menu_ico_url, menu_name, menu_order_by, menu_url, p_menu_id from td_menu t");
		/*if (args.getUser_id() != 0 && args.getUser_id() != null) {
			sql.append("and tur.user_id = ?");
			arg.add(args.getUser_id());
		}*/
		//sql.append("))order by tm.menu_name desc");
		return super.listBySql(sql.toString(), arg.toArray(), MenuInfoDto.class, false);
	}

	@Override
	public List<MenuInfoDto> getAllMenu(MenuInfoArgs args) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, (SELECT MENU_NAME FROM TD_MENU WHERE MENU_ID=M.P_MENU_ID) AS FATHER_MENU_NAME,MENU_ORDER_BY, MENU_URL, P_MENU_ID FROM TD_MENU M WHERE 1=1 ");
		if (StringUtils.isNotEmpty(args.getMenu_name())) {
			sql.append(" AND MENU_NAME LIKE '%" + args.getMenu_name() + "%'");
		}

		if (StringUtils.isNotEmpty(args.getStartDate())) {
			sql.append(" AND CREATE_TIME >= to_date('" + args.getStartDate() + "', 'yyyy-MM-dd') ");
			// arg.add(args.getStartDate());
		}
		if (StringUtils.isNotEmpty(args.getEndDate())) {
			sql.append(" AND CREATE_TIME <= to_date('" + args.getEndDate() + "', 'yyyy-MM-dd') ");
			// arg.add(args.getEndDate());
		}
		sql.append(" AND IS_DELETED='N'");
		return super.listBySql(sql.toString(), MenuInfoDto.class, false);
	}

	@Override
	public List<MenuInfoDto> getAllParentMenu() {
		return super.listBySql("SELECT MENU_ID,MENU_NAME FROM TD_MENU WHERE P_MENU_ID=0 AND IS_DELETED='N'", MenuInfoDto.class, false);
	}

	@Override
	public List<MenuInfoDto> getMenuOrderBy() {
		return super.listBySql("SELECT MENU_ID,MENU_NAME,MENU_ORDER_BY FROM TD_MENU WHERE IS_DELETED='N'", MenuInfoDto.class, false);
	}
}
