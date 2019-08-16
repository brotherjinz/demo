package com.web.info.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.info.args.BpTypeArgs;
import com.web.info.args.ScheduleRecordArgs;
import com.web.info.args.UtilArgs;
import com.web.info.dao.ScheduleRecordDao;
import com.web.info.dto.ScheduleRecordDto;
import com.web.info.service.FaultService;
import com.web.info.system.ReturnStandard;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("FaultData/")
public class FaultDataController {
	@Autowired
	private FaultService faultService;
	@Autowired
	private ScheduleRecordDao scheduleRecordDao;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "faultDataQuery", method = { RequestMethod.GET, RequestMethod.POST })
	public Object queryFault(UtilArgs args) {
		if (StringUtils.isNotEmpty(args.getStationcode())) {
			// 按照时间查询本地库，获取已同步的数据id
			ScheduleRecordArgs arg = new ScheduleRecordArgs();
			arg.setStartTime(args.getStartTime());
			arg.setEndTime(args.getEndTime());
			arg.setStationcode(args.getStationcode());
			List<ScheduleRecordDto> ls = scheduleRecordDao.queryList(arg);
			List<String> listStr = new ArrayList<String>();
			for (ScheduleRecordDto srd : ls) {
				listStr.add(srd.getFAULT_SERIAL());
			}
			args.setStrList(listStr);
			args.setTF(false);
			return faultService.queryPager(args, args.getStationcode());
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping("syncDataQuery")
	public Object syncDataQuery(UtilArgs args) {
		// 查询全部已同步过来的数据，按照同步时间排序
		return faultService.syncSuccessQueryPager(args);
	}
	/**
	 * 查询统计报表
	 * @param args
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryDataPager")
	public Object queryDataPager(UtilArgs args) {
		return faultService.queryDataPager(args);
	}

	/**
	 * 手动同步
	 * @param req
	 * @param resp
	 * @param model
	 * @param args
	 * @param dataArr
	 * @param stationcode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "synchronization", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean synchronization(HttpServletRequest req, HttpServletResponse resp, Model model, UtilArgs args, String[] dataArr, String stationcode) {
		List<String> strList = Arrays.asList(dataArr);
		args.setTF(true);
		args.setStrList(strList);
		args.setSZ(false);
		boolean sync = faultService.faultData(args, stationcode);
		if (sync) {
			return true;
		} else {
			return false;
		}
	}
	@ResponseBody
	@RequestMapping(value = "queryAll", method = { RequestMethod.GET, RequestMethod.POST })
	public Object queryAll(HttpServletRequest req, HttpServletResponse resp, Model model, UtilArgs args){
		return faultService.queryAll(args);
	}
	//查询可编排数据
	@ResponseBody
	@RequestMapping("queryDatavEditing")
	public Object queryDatavEditing(UtilArgs args) {
		return faultService.queryDatavEditing(args);
	}
	/**
	 * 编排数据
	 * @param req
	 * @param resp
	 * @param model
	 */
	@ResponseBody
	@RequestMapping(value = "vEditing", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnStandard vEditing(HttpServletRequest req, HttpServletResponse resp, Model model,String[] dataArr, String stationcode){
		List<String> strList = Arrays.asList(dataArr);
		boolean res = faultService.vEditing(strList, stationcode);
		ReturnStandard rs = new ReturnStandard();
		if(res){
			rs.setCode(200);
			rs.setMsg("成功");
		}else{
			rs.setCode(500);
			rs.setMsg("失败");
		}
		return rs;
	}
	/**
	 * 根据指定条件编排数据
	 * @author 李亚洲 
	 */
	@ResponseBody
	@RequestMapping(value = "selectType", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnStandard selectType(HttpServletRequest req, HttpServletResponse resp, Model model,BpTypeArgs args) {
		for (String str : args.getTypeTransactmode()) {
			if(str.equals("-1")) {
				args.setTypeTransactmode(null);
			}
		}
		//根据条件查询要同步的故障数据
		JSONObject jobj = faultService.querySelectType(args);
		ReturnStandard rs = new ReturnStandard();
		if(jobj!=null &&jobj.size()>0){
			rs.setCode(200);
			rs.setMsg("成功");
			rs.setData(jobj.get("data"));
		}else{
			rs.setCode(500);
			rs.setMsg("失败");
		}
		return rs;
	}
}
