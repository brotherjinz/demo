package com.web.info.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.info.args.ScheduleJobArgs;
import com.web.info.service.ScheduleJobService;
import com.web.info.system.ReturnStandard;

import net.sf.json.JSONObject;

/**
 * 任务管理
 */
@Controller
@RequestMapping("schedule/")
public class ScheduleJobController {

	private static Logger log = Logger.getLogger(ScheduleJobController.class);
	
	 @Autowired
	 private ScheduleJobService service;
	 
	/**
	 * 任务首页
	 */
	@RequestMapping("schedule_job")
	public String schedule_job(Model model) throws UnsupportedEncodingException {
		return "schedule/schedule_job";
	}

	
	/**
	 * 获取任务列表
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "getJob", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONObject getJob(ScheduleJobArgs args,HttpServletRequest req, HttpServletResponse resp,Model model) {
		resp.setCharacterEncoding("UTF-8");
		String page=req.getParameter("page");
		String limit=req.getParameter("rows");
		String START_TIME = req.getParameter("START_TIME");
		String END_TIME = req.getParameter("END_TIME");
		String DATE_NAME = req.getParameter("DATE_NAME");
		String sqlWhere = "";
		JSONObject strjson=service.getJob(sqlWhere, page, limit);
		log.info("定时任务查询");
		//System.out.println("strjson:" + strjson);
		return strjson;
	}
	/**
	 * 运行一次任务
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "runOnce", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ReturnStandard runOnce(ScheduleJobArgs o) {
		//o.setScheduleJobId("1");
		ReturnStandard rs = new ReturnStandard();
		try {
			int res = service.runOnce(o);
			if (res == 1) {
				rs.setCode(200);
				rs.setMsg("运行成功");
			} else if (res == 2) {
				rs.setCode(500);
				rs.setMsg("请先暂停任务");
			} else if (res == 3) {
				rs.setCode(500);
				rs.setMsg("此任务不适用于当前系统");
			} else {
				rs.setCode(500);
				rs.setMsg("运行失败");
			}
		} catch (Exception e) {
			rs.setCode(500);
			rs.setMsg("运行失败");
		}
		return rs;
	}
	
	@RequestMapping(value = "resumeJob", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ReturnStandard resumeJob(ScheduleJobArgs o) {
		ReturnStandard rs = new ReturnStandard();
		try {
			int res = service.resumeJob(o);
			if (res == 1){rs.setCode(200);rs.setMsg("启动成功");}
			else if (res == 2){rs.setCode(500);rs.setMsg("项目已启动");}
			else if (res == 3){rs.setCode(500);rs.setMsg("此任务不适用于当前系统");}
			else{rs.setCode(500);rs.setMsg("启动失败");}
		} catch (Exception e) {
			rs.setCode(500);rs.setMsg("运行失败");
		}
		return rs;
	}
	@RequestMapping(value = "pauseJob", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ReturnStandard pauseJob(ScheduleJobArgs o) {
		ReturnStandard rs = new ReturnStandard();
		try {
			int res = service.pauseJob(o);
			if (res == 1){rs.setCode(200);rs.setMsg("暂停成功");}
			else if (res == 2){rs.setCode(500);rs.setMsg("项目没启动，不用暂停");}
			else{rs.setCode(500);rs.setMsg("暂停失败");}
		} catch (Exception e) {
			rs.setCode(500);rs.setMsg("暂停失败");
		}
		return rs;
	}
	
	/*
		
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				List<ScheduleJob> list = service.find(o);
				ScheduleJob obj = list.get(0);
				ar.setSucceed(obj);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
		
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
			try {
				o.setScheduleJobId(get32UUID());
				int res = service.creatScheduleJob(o);
				if (res == 1)
					ar.setSucceedMsg(Const.SAVE_SUCCEED);
				else
					ar.setFailMsg(Const.SAVE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.updateScheduleJob(o);
				if (res == 1)ar.setSucceedMsg(Const.UPDATE_SUCCEED);
				else         ar.setFailMsg(Const.UPDATE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.deleteScheduleJob(o);
				if (res == 1)
					ar.setSucceedMsg(Const.DEL_SUCCEED);
				else
					ar.setFailMsg(Const.DEL_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value = "runOnce", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes runOnce(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.runOnce(o);
				if (res == 1)
					ar.setSucceedMsg("运行成功");
				if (res == 2)
					ar.setFailMsg("请先停用任务");
				else
					ar.setFailMsg("运行失败");
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg("运行失败");
			}
		}
		return ar;
	}
		
	@RequestMapping(value = "resumeJob", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes resumeJob(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.resumeJob(o);
				if (res == 1)
					ar.setSucceedMsg("启动成功");
				else if (res == 2)
					ar.setFailMsg("项目已启动");
				else
					ar.setFailMsg("启动失败");
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg("启动失败");
			}
		}
		return ar;
	}
		
	@RequestMapping(value = "pauseJob", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes pauseJob(ScheduleJob o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				int res = service.pauseJob(o);
				if (res == 1)
					ar.setSucceedMsg("暂停成功");
				else if (res == 2)
					ar.setFailMsg("项目没启动，不用暂停");
				else
					ar.setFailMsg("暂停失败");
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg("暂停失败");
			}
		}
		return ar;
	}*/
}
