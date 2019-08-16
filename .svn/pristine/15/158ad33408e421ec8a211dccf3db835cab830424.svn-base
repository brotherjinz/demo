<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/resources/include/commonEasyUi.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查询数据</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body style="padding: 0px;">
	<div style="width: 100%; height: 100%;">
		<table id="queryDataGrid" style="width: 100%; height: 100%;" data-options="singleSelect:true,autoRowHeight:true,toolbar:tb">
		</table>
		<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<label style="margin-left: 5px; color: red;">按条件查询:</label>请选择过车时间范围: <input id="startTime" editable="false" class="easyui-datetimebox" style="width: 150px"> 至: <input id="endTime" editable="false" class="easyui-datetimebox" style="width: 150px"> 探测站: <select id="tcz" class="easyui-combobox" editable="false" onmousedown="if(this.options.length>3){this.size=4}" onblur="this.size=0" onchange="this.size=0" style="width: 200px">
					<option value="">请选择</option>
					<c:forEach items="${TCZ}" var="TCZ">
						<option value="${TCZ.STATIONCODE}">${TCZ.STATIONNAME}</option>
					</c:forEach>
				</select> 车号:<input id="VEHICLE_ID" class="easyui-textbox" type="text" name="name" style="width: 100px;"></input> <a id="btnsearch" class="easyui-linkbutton" iconCls="icon-search">查询</a> <a id="exportExcel" class="easyui-linkbutton" iconCls="icon-export-excel">导出</a>
			</div>
		</div>
		<div id="expdownload" class="easyui-dialog" style="width: 40%; height: 40%; padding: 0px" align="center">
			<br /> <label>请选择您要下载或者导出的数据:默认导出为xls格式,其他格式待开发</label><br />
			<table>
				<tr>
					<td>过车时间范围:</td>
					<td><input id="exp_startTime" editable="false" class="easyui-datetimebox" style="width: 150px"></td>
					<td>至:</td>
					<td><input id="exp_endTime" editable="false" class="easyui-datetimebox" style="width: 150px"></td>
				</tr>
				<tr>
					<td>探测站:</td>
					<td colspan="3"><select id="exp_tcz" class="easyui-combobox" editable="false" onmousedown="if(this.options.length>3){this.size=4}" onblur="this.size=0" onchange="this.size=0" style="width: 200px">
							<option value="All">全部探测站</option>
							<c:forEach items="${TCZ}" var="TCZ">
								<option value="${TCZ.STATIONCODE}">${TCZ.STATIONNAME}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<br />
			<div align="center">
				<a class="easyui-linkbutton" id="exp_Save" iconCls="icon-save" plain="false">保存/提交下载</a> <a class="easyui-linkbutton" id="exp_Cancel" iconCls="icon-cancel" plain="false">取消</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#queryDataGrid').datagrid({
							url : '${ctx}/FaultData/queryDataPager',
							rownumbers : true,
							fitColumns : true,
							striped : true,
							collapsible : false,// 右上角隐藏用的小箭头
							loadMsg : "数据加载中，请稍后...",
							pagination : true,
							pageList : [ 5, 10, 20, 30, 40 ],
							pageSize : 20,
							columns : [ [ {
								title : 'id',
								field : 'VEHICLE_SERIAL',
								checkbox : true
							}, {
								field : "STATION_NAME",
								title : "探测站名称",
								width : 100
							}, {
								field : "STATION_ID",
								title : "探测站编码",
								width : 100
							}, {
								field : "TRAIN_ID",
								title : "车次",
								width : 100
							}, {
								field : "VEHICLE_ID",
								title : "车号",
								width : 100
							}, {
								field : "VEHICLE_TYPE",
								title : "车型",
								width : 50
							}, {
								field : "PASS_TIME",
								title : "过车时间",
								width : 100
							}, {
								field : "INDEX_ID",
								title : "目录号",
								width : 100
							}, {
								field : "VEHICLE_ORDER",
								title : "辆序",
								width : 50,
								formatter : function(value, row, index) {
									return value;
								}
							} ] ],
							onLoadSuccess : function(data) {
							}
						});
		$("#btnsearch").on('click', function() {
			var startdate = $('#startTime').datetimebox('getValue');
			var enddate = $('#endTime').datetimebox('getValue');
			if (startdate != "" && enddate != "") {
				startdate = startdate.replace(/-/g, "/");
				enddate = enddate.replace(/-/g, "/");
				var start = new Date(startdate);
				var end = new Date(enddate);
				if (start != null && end != null) {
					if (end < start) {
						$.messager.alert("提示信息", '结束日期应大于开始时期！');
						return;
					}
				}
				$('#queryDataGrid').datagrid('load', {
					stationcode : $('#tcz').val(),
					VEHICLE_ID : $('#VEHICLE_ID').val(),
					startTime : $('#startTime').datetimebox('getValue'),
					endTime : $('#endTime').datetimebox('getValue'),
				});
			} else {
				$.messager.alert('提示消息', '时间范围，开始时间以及结束时间不可为空');
			}
		});
		//弹出框的属性设置
		$("#expdownload").dialog({
			closable : true,
			closed : true,
			modal : true,
			onClose : function() {
				//右上角关闭事件。清空文本框
				$('#exp_tcz').combobox('setValue', "All");
				startTime: $('#exp_startTime').datetimebox('setValue', "");
				endTime: $('#exp_endTime').datetimebox('setValue', "");
			}
		});
		$("#exportExcel").on('click', function() {
			$('#expdownload').dialog({
				title : "导出Excel/下载数据"
			});
			$('#expdownload').dialog('open');
		});

		$("#exp_Save")
				.on(
						'click',
						function() {
							var exp_startdate = $('#exp_startTime')
									.datetimebox('getValue');
							var exp_enddate = $('#exp_endTime').datetimebox(
									'getValue');
							if (exp_startdate != "" && exp_enddate != "") {
								exp_startdate = exp_startdate
										.replace(/-/g, "/");
								exp_enddate = exp_enddate.replace(/-/g, "/");
								var exp_start = new Date(exp_startdate);
								var exp_end = new Date(exp_enddate);
								if (exp_start != null && exp_end != null) {
									if (exp_end < exp_start) {
										$.messager
												.alert("提示信息", '结束日期应大于开始时期！');
										return;
									}
									window.location.href = "${ctx}/Excel/export?stationcode="
											+ $('#exp_tcz').val()
											+ "&startTime="
											+ exp_startdate
											+ "&endTime=" + exp_enddate;
									$('#expdownload').dialog('close');
								}
							} else {
								$.messager.alert('提示消息', '时间范围，开始时间以及结束时间不可为空');
							}
							;
						});
		$("#exp_Cancel").on('click', function() {
			$('#exp_tcz').combobox('setValue', "All");
			startTime: $('#exp_startTime').datetimebox('setValue', "");
			endTime: $('#exp_endTime').datetimebox('setValue', "");
			$('#expdownload').dialog('close');
		});
	</script>
</body>
</html>