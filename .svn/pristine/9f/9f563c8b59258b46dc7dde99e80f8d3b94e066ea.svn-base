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
		<table id="faultData" style="width: 100%; height: 100%;"
			data-options="singleSelect:false,autoRowHeight:true,toolbar:tb">
		</table>
		<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				请选择过车时间: <input id="startTime" editable="false"
					class="easyui-datetimebox" style="width: 150px"> 至: <input
					id="endTime" editable="false" class="easyui-datetimebox"
					style="width: 150px"> 探测站: <select id="tcz"
					class="easyui-combobox" editable="false"
					onmousedown="if(this.options.length>3){this.size=4}"
					onblur="this.size=0" onchange="this.size=0" style="width: 200px">
					<option value="">请选择</option>
					<c:forEach items="${TCZ}" var="TCZ">
						<option value="${TCZ.STATIONCODE}">${TCZ.STATIONNAME}</option>
					</c:forEach>
				</select><br> 处理方式: <select id="kx" class="easyui-combobox"
					editable="false" panelHeight="auto" style="width: 200px">
					<option value="">请选择</option>
					<option value="803">扣修</option>
				</select> 故障代码: <input id="fault_code" class="easyui-textbox" type="text"
					name="name" style="width: 200px;"></input> <a id="btnsearch"
					class="easyui-linkbutton" iconCls="icon-search">查询</a> <a
					id="synchronizationbtn" class="easyui-linkbutton"
					iconCls="icon-upload">同步</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#faultData').datagrid({
			url : '${ctx}/FaultData/faultDataQuery',
			rownumbers : false,
			fitColumns : true,
			striped : true,
			collapsible : false,// 右上角隐藏用的小箭头
			loadMsg : "数据加载中，请稍后...",
			pagination : true,
			pageList : [ 5, 10, 20, 30, 40 ],
			pageSize : 20,
			columns : [ [ {
				title : 'id',
				field : 'FAULT_SERIAL',
				checkbox : true
			}, {
				field : "STATION_NAME",
				title : "探测站",
				width : 100
			}, {
				field : "TRAIN_ID",
				title : "车次",
				width : 100
			//
			}, {
				field : "VEHICLE_ID",
				title : "车号",
				width : 100
			}, {
				field : "FAULT_ID",
				title : "故障代码",
				width : 100
			}, {
				field : "FAULT_NAME",
				title : "故障名称",
				width : 100
			}, {
				field : "DISPLAY_NAME",
				title : "处理方式",
				width : 100
			}, {
				field : "PASS_TIME",
				title : "过车时间",
				width : 100,
				formatter : function(value, row, index) {
					return value;
				}
			}, {
				field : "DETECT_TIME",
				title : "发现时间",
				width : 100,
				formatter : function(value, row, index) {
					return value;
				}
			}, {
				title : "备注",
				field : "REMARK",
				width : 100
			} ] ]
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
				$('#faultData').datagrid('load', {
					startTime : $('#startTime').datetimebox('getValue'),
					endTime : $('#endTime').datetimebox('getValue'),
					STATION_ID : $('#tcz').val(),
					KXcode : $('#kx').val(),
					stationcode : $('#tcz').val(),
					FAULT_ID : $('#fault_code').val(),
				});
			} else {
				$.messager.alert('提示消息', '时间范围，开始时间以及结束时间不可为空');
			}
		});
		$("#synchronizationbtn").on('click', function() {
			/* getSelected：取得第一个选中行数据，如果没有选中行，则返回 null，否则返回记录。
			getSelections：取得所有选中行数据，返回元素记录的数组数据。 */
			//stationcode同步时将探测站编码传入后台
			var rowInfo = $("#faultData").datagrid('getSelections');
			if (rowInfo && rowInfo.length > 0) {
				var stationcode = "";
				var arrData = [];
				$.each(rowInfo, function(key, val) {
					arrData.push(val.FAULT_SERIAL);
					stationcode = val.STATION_ID;
				});
				processBarOpen();
				$.ajax({
					type : "POST",
					url : "${ctx}/FaultData/synchronization",
					traditional : true,//阻止深度序列化数组对象
					data : {
						dataArr : arrData,
						startTime : $('#startTime').datetimebox('getValue'),
						endTime : $('#endTime').datetimebox('getValue'),
						stationcode : stationcode,
					},
					success : function(result) {
						if (result) {
							processBarClose();
							$.messager.alert('提示消息', "手动同步成功");
							$('#faultData').datagrid('load', {
								startTime : $('#startTime').datetimebox('getValue'),
								endTime : $('#endTime').datetimebox('getValue'),
								STATION_ID : $('#tcz').val(),
								KXcode : $('#kx').val(),
								stationcode : $('#tcz').val(),
							});
						} else {
							processBarClose();
							$.messager.alert('提示消息', "手动同步失败");
						}
					},
					error : function(result) {
						$.messager.alert('提示消息', "系统异常");
						return;
					}
				});
			} else {
				$.messager.alert('提示消息', '请选择您要同步的数据');
				return null;
			}
		});
		//等待条
		function processBarOpen() {
			if (!$("div").is("#dialog_div")) {
				$("<div id='dialog_div'><img src='${ctx}/resources/img/loadingAnimation.gif'/></div>").hide().appendTo(document.body);
			}
			$("#dialog_div").dialog({
				autoOpen : true,
				modal : true,
				resizable : false,
				height : 55,
				width : 230,
				title : '同步数据中,请稍等...',
				open : function(event, ui) {
					//$(".ui-dialog-titlebar").hide();
					//$(".ui-dialog").removeClass("ui-widget ui-widget-content ui-corner-all ui-draggable");
				}
			});
		}
		/**
		 * close processBar
		 */
		function processBarClose() {
			$("#dialog_div").dialog('close');
		}
	</script>
</body>
</html>