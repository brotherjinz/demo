package com.web.info.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.info.args.UtilArgs;
import com.web.info.dto.UtilDto;
import com.web.info.service.FaultService;
import com.web.util.DateUtil;
import com.web.util.ExcelUtil;

@Controller
@RequestMapping("Excel/")
public class ExcelUtilController {
	@Autowired
	private FaultService faultService;

	/**
	 * 导出excel
	 * 
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "export", method = { RequestMethod.GET, RequestMethod.POST })
	public void export(UtilArgs args, HttpServletRequest request, HttpServletResponse response) {
		if (args.getStationcode().equals("All")) {
			args.setStationcode("");
		}
		// 取到数据后开始执行拼装，拼装完成后响应数据流
		// 获取数据
		List<UtilDto> listutil = faultService.expQueryFault(args);
		if (listutil != null && listutil.size() > 0) {
			// excel标题
			String[] title = new String[] { "探测站名称", "探测站编码", "车次", "车号", "车型", "过车时间", "目录号", "辆序" };
			// excel文件名
			// sdf.format(DateUtil.string2Date("yyyy/MM/dd HH:mm",aa))
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String fileName = sdf.format(DateUtil.string2Date("yyyy/MM/dd HH:mm:ss", args.getStartTime())) + "-" + sdf.format(DateUtil.string2Date("yyyy/MM/dd HH:mm:ss", args.getEndTime())) + "故障数据.xls";
				// "学生信息表"+System.currentTimeMillis()+".xls";
				// sheet名
				String sheetName = "故障数据";// "学生信息表";
				String[][] content = new String[listutil.size()][8];
				for (int i = 0; i < listutil.size(); i++) {
					content[i][0] = listutil.get(i).getSTATION_NAME();
					content[i][1] = listutil.get(i).getSTATION_ID();
					content[i][2] = listutil.get(i).getTRAIN_ID();
					content[i][3] = listutil.get(i).getVEHICLE_ID();
					content[i][4] = listutil.get(i).getVEHICLE_TYPE();
					content[i][5] = listutil.get(i).getPASS_TIME();
					content[i][6] = listutil.get(i).getINDEX_ID().toString();
					content[i][7] = listutil.get(i).getVEHICLE_ORDER().toString();
				}
				HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
				// 响应到客户端
				this.setResponseHeader(response, fileName);
				OutputStream os = response.getOutputStream();
				wb.write(os);
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
