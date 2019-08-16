package com.web.info.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import com.web.info.args.MenuInfoArgs;
import com.web.info.dao.MenuInfoDao;
import com.web.info.dto.MenuInfoDto;
import com.web.info.model.MenuInfo;
import com.web.info.service.MenuInfoService;

@Service
public class MenuInfoServiceImpl implements MenuInfoService {

	@Resource
	private MenuInfoDao menuInfoDao;

	@Override
	public List<MenuInfoDto> getAllMenu(MenuInfoArgs args) {
		List<MenuInfoDto> allMenuList = menuInfoDao.getAllMenu(args);
		List<MenuInfoDto> allMenuListResult = new ArrayList<MenuInfoDto>();
		for (MenuInfoDto menuInfoDto : allMenuList) {
			if (!menuInfoDto.getP_MENU_ID().equals("0")) {
				menuInfoDto.set_parentId(Integer.valueOf(menuInfoDto.getP_MENU_ID()));
			} else {
				menuInfoDto.setState("closed");
			}
			menuInfoDto.setId(menuInfoDto.getMENU_ID().intValue());
			menuInfoDto.setName(menuInfoDto.getMENU_NAME());
			allMenuListResult.add(menuInfoDto);
		}
		return allMenuListResult;
	}

	@Override
	public List<MenuInfoDto> queryMenu(MenuInfoArgs args) {
		List<MenuInfoDto> listMenu = menuInfoDao.getMenuInfoDtos(args);
		return listMenu;
	}

	@Override
	public boolean addMenu(MenuInfoArgs args) {
		MenuInfo menu = new MenuInfo();
		menu.setCreate_time(new Date());
		menu.setIs_deleted("N");
		menu.setMenu_name(args.getMenu_name());
		List<MenuInfoDto> list = menuInfoDao.getMenuOrderBy();
		List<Integer> orderBy = new ArrayList<Integer>();
		for (MenuInfoDto menuInfoDto : list) {
			orderBy.add(menuInfoDto.getMENU_ORDER_BY().intValue());
		}
		menu.setMenu_order_by(Collections.max(orderBy) + 1);
		if (StringUtils.isNotEmpty(args.getMenu_url())) {
			menu.setMenu_url(args.getMenu_url());
		} else {
			menu.setMenu_url("0");
		}
		if (StringUtils.isNotEmpty(args.getP_menu_id())) {
			menu.setP_menu_id(args.getP_menu_id());
		} else {
			menu.setP_menu_id("0");
		}
		if (StringUtils.isNotEmpty(args.getMenu_ico_url())) {
			menu.setMenu_ico_url(args.getMenu_ico_url());
		}
		try {
			menuInfoDao.add(menu);
			return true;
		} catch (BeansException e) {
			return false;
		}
	}

	@Override
	public List<MenuInfoDto> getAllParentMenu() {
		List<MenuInfoDto> list = menuInfoDao.getAllParentMenu();
		MenuInfoDto menu = new MenuInfoDto();
		menu.setMENU_ID(new BigDecimal(0));
		menu.setMENU_NAME("无");
		list.add(menu);
		return list;
	}

	@Override
	public boolean editMenu(MenuInfoArgs args) {
		MenuInfo menu = new MenuInfo();
		BeanUtils.copyProperties(args, menu);
		System.out.println();
		try {
			menuInfoDao.update(menu);
			return true;
		} catch (BeansException e) {
			return false;
		}
	}
}
