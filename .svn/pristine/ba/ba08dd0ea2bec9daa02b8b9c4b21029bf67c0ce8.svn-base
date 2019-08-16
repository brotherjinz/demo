package com.web.info.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.info.args.MenuInfoArgs;
import com.web.info.dto.MenuInfoDto;
import com.web.info.service.ConfigService;
import com.web.info.service.DzFaultService;
import com.web.info.service.MenuInfoService;
//import com.web.info.service.TczService;
import com.web.info.service.TransactModeService;

@Controller
@RequestMapping(value = "/Init")
public class InitJspController {
	@Autowired
	private ConfigService configService;
	@Autowired
	private MenuInfoService menuInfoService;
	@Autowired
	private TransactModeService transactModeService;
	@Autowired
	private DzFaultService dzFaultService;

	@RequestMapping(value = "init_Index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model, MenuInfoArgs args, HttpServletRequest request, HttpServletResponse response) {
		List<MenuInfoDto> listMenu = menuInfoService.queryMenu(args);
		model.addAttribute("listMenu", listMenu);
		return "index";
	}

	@RequestMapping(value = "initScheduleJob", method = { RequestMethod.GET, RequestMethod.POST })
	public String scheduleJob() {

		return "scheduleJob";
	}

	@RequestMapping(value = "faultData", method = { RequestMethod.GET, RequestMethod.POST })
	public String queryFault(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// 查询全部探测站
		req.setAttribute("TCZ", configService.selectListCfg());
		return "faultData";
	}

	@RequestMapping(value = "syncData", method = { RequestMethod.GET, RequestMethod.POST })
	public String syncData(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// 查询全部探测站
		req.setAttribute("TCZ", configService.selectListCfg());
		return "synchronizingData";
	}

	@RequestMapping(value = "Welcome", method = { RequestMethod.GET, RequestMethod.POST })
	public String Welcome(HttpServletRequest req, HttpServletResponse resp, Model model) {
		return "Welcome";
	}

	@RequestMapping(value = "imgconfig", method = { RequestMethod.GET, RequestMethod.POST })
	public String imgconfig(HttpServletRequest req, HttpServletResponse resp, Model model) {
		return "imgconfig";
	}

	@RequestMapping(value = "queryData", method = { RequestMethod.GET, RequestMethod.POST })
	public String queryData(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// 查询全部探测站
		req.setAttribute("TCZ", configService.selectListCfg());
		return "queryData";
	}

	// 初始化图形统计图
	@RequestMapping(value = "echart", method = { RequestMethod.GET, RequestMethod.POST })
	public String echart(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// 查询全部探测站
		req.setAttribute("TCZ", configService.selectListCfg());
		return "echart";
	}

	/**
	 * 车辆编排
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "vehicleEditing", method = { RequestMethod.GET, RequestMethod.POST })
	public String vehicleEditing(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// 查询全部探测站
		req.setAttribute("TCZ", configService.selectListCfg());
		req.setAttribute("FAULTTYPE", dzFaultService.queryList());
		req.setAttribute("TRANSACTMODE", transactModeService.queryList());
		return "vehicleEditing";
	}
}
