<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/resources/include/commonEasyUi.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>车辆编排</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body style="padding: 0px;">
	<div style="width: 100%; height: 100%;">
		<div id="leftData" style="width: 50%; height: 100%; float: left;">
			<table id="faultData" style="width: 100%; height: 100%;"
				data-options="singleSelect:true,autoRowHeight:true,toolbar:tb">
			</table>
			<div id="tb" style="padding: 5px; height: auto">
				车时间范围: <input id="startTime" editable="false"
					class="easyui-datetimebox" style="width: 150px"> 至: <input
					id="endTime" editable="false" class="easyui-datetimebox"
					style="width: 150px"> <br />探测站: <select id="tcz"
					class="easyui-combobox" editable="false"
					onmousedown="if(this.options.length>3){this.size=4}"
					onblur="this.size=0" onchange="this.size=0" style="width: 200px">
					<option value="">请选择</option>
					<c:forEach items="${TCZ}" var="TCZ">
						<option value="${TCZ.STATIONCODE}">${TCZ.STATIONNAME}</option>
					</c:forEach>
				</select> <a id="btnsearch" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;<a
					id="addType" class="easyui-linkbutton" iconCls="icon-add">根据条件编排车辆</a>
			</div>
		</div>
		<div id="rightData" style="width: 50%; height: 100%; float: right;">
			<table id="tmpData" title="模拟编车" style="width: 100%; height: 100%;"
				data-options="singleSelect:true,autoRowHeight:true,toolbar:tbTemp">
			</table>
			<div id="tbTemp" style="padding: 5px; height: auto">
				<a id="TEMPADD" class="easyui-linkbutton" iconCls="icon-add">保存并提交编车数据</a>
			</div>
		</div>
		<div id="addtypedlg" class="easyui-dialog"
			style="width: 50%; height: 40%; padding: 0px" align="center">
			<table>
				<tr>
					<td>选择车型:</td>
					<td><select id="carType" class="easyui-combobox"
						editable="false" multiple="true" style="width: 200px; color: red;">
							<option value="P">P</option>
							<option value="C">C</option>
							<option value="N">N</option>
							<option value="X">X</option>
							<option value="G">G</option>
							<option value="L">L</option>
							<option value="K">K</option>
							<option value="U">U</option>
					</select></td>
					<!-- <td>辆数:</td>
					<td><input id="carTypenum" class="easyui-numberspinner"
						value="0" data-options="min:0,max:25,required:true"
						style="width: 120px;"></input></td> -->
				</tr>
				<tr>
					<td>选择处理方式:</td>
					<td><select id="TransactmodeType" class="easyui-combobox"
						multiple="true" editable="false" style="width: 200px">
							<option value="-1">全部</option>
							<c:forEach items="${TRANSACTMODE}" var="TRANSACTMODE">
								<option value="${TRANSACTMODE.CODE}">${TRANSACTMODE.DISPLAY_NAME}</option>
							</c:forEach>
					</select></td>
					<!-- <td>辆数:</td>
					<td><input id="TransactmodeTypenum"
						class="easyui-numberspinner" value="0"
						data-options="min:0,max:25,required:true" style="width: 120px;"></input></td> -->
				</tr>
				<tr>
					<td>辆数:</td>
					<td><input id="carnum" class="easyui-numberspinner" value="0"
						data-options="min:0,max:50,required:true" style="width: 120px;"></input></td>
				</tr>
				<%-- <tr>
					<td>故障类型:</td>
					<td><select id="faultType" class="easyui-combobox"
						editable="false"
						onmousedown="if(this.options.length>3){this.size=4}"
						onblur="this.size=0" onchange="this.size=0" style="width: 200px">
							<option value="0">请选择</option>
							<c:forEach items="${FAULTTYPE}" var="FAULTTYPE">
								<option value="${FAULTTYPE.CODE}">${FAULTTYPE.DISPLAY_NAME}</option>
							</c:forEach>
					</select></td>
					<td>辆数:</td>
					<td><input id="faultTypenum" class="easyui-numberspinner"
						value="0" data-options="min:0,max:50,required:true"
						style="width: 120px;"></input></td>
				</tr> --%>
			</table>
			<div style="margin-top: 15px;">
				<a id="tempTypeAdd" class="easyui-linkbutton" iconCls="icon-add">保存至模拟编车表格</a>
				<a id="tempTypeCancel" class="easyui-linkbutton"
					iconCls="icon-cancel">取消</a> <a id="tempTypeReset"
					class="easyui-linkbutton" iconCls="icon-remove">重置条件</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#tmpData').datagrid({
			//url : str,
			rownumbers : true,
			fitColumns : true,
			striped : true,
			collapsible : false,// 右上角隐藏用的小箭头
			loadMsg : "数据加载中，请稍后...",
			columns : [ [ {
				title : 'id',
				field : 'VEHICLE_SERIAL',
				checkbox : true
			}, {
				field : "VEHICLE_ID",
				title : "车号",
				width : 100
			}, {
				field : "VEHICLE_TYPE",
				title : "车型",
				width : 50
			}, {
				field : "FAULT_NAME",
				title : "故障名称",
				width : 100
			} ] ],
			onLoadSuccess : function(data) {
				//此处加载数据成功后自定义合并单元格操作
			},//双击事件
			onDblClickRow : function(index, row) {
				var row = $('#tmpData').datagrid('getSelected');
				if (row) {
					var rowIndex = $('#tmpData').datagrid('getRowIndex', row);
					$('#tmpData').datagrid('deleteRow', rowIndex);
				}
			}
		});
		$('#faultData').datagrid(
				{
					url : '${ctx}/FaultData/queryDatavEditing',
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
						field : "TRAIN_ID",
						title : "车次",
						width : 50
					}, {
						field : "VEHICLE_ID",
						title : "车号",
						width : 50
					}, {
						field : "VEHICLE_TYPE",
						title : "车型",
						width : 50
					}, {
						field : "PASS_TIME",
						title : "过车时间",
						width : 100
					}, {
						field : "FAULT_NAME",
						title : "故障名称",
						width : 100
					} ] ],
					onLoadSuccess : function(data) {
						//此处加载数据成功后自定义合并单元格操作
					},//双击事件
					onDblClickRow : function(index, row) {
						var rowstemp = $("#tmpData").datagrid("getRows"); //这段代码是获取当前页的所有行。
						if (rowstemp.length == '50') {
							$.messager.alert("提示信息", '当前待保存数据已经到达50条，达到了最大限制');
							return false;
						}
						if (rowstemp.length != '0') {
							var i = 0;
							$.each(rowstemp, function(index, val) {
								if (row.VEHICLE_ID == val.VEHICLE_ID) {
									$.messager.alert("提示信息", val.VEHICLE_ID
											+ '此车号数据已经添加过了,相同车号的数据只需添加一次即可 ');
									i++;
								}
							});
							if (i == 0) {
								$('#tmpData').datagrid('insertRow', {//在右边插入新行。
									index : index,
									row : {
										VEHICLE_SERIAL : row.VEHICLE_SERIAL,
										VEHICLE_ID : row.VEHICLE_ID,
										VEHICLE_TYPE : row.VEHICLE_TYPE,
										FAULT_NAME : row.FAULT_NAME
									}
								});
							}
						} else {
							$('#tmpData').datagrid('insertRow', {//在右边插入新行。
								index : index,
								row : {
									VEHICLE_SERIAL : row.VEHICLE_SERIAL,
									VEHICLE_ID : row.VEHICLE_ID,
									VEHICLE_TYPE : row.VEHICLE_TYPE,
									FAULT_NAME : row.FAULT_NAME
								}
							});
						}
						/* $('#tempV').append(row.VEHICLE_ID + "<br/>"); */
					}
				});
		//弹出框的属性设置
		$("#addtypedlg").dialog({
			closable : true,
			closed : true,
			modal : true,
			onClose : function() {
				$('#carType').combobox('clear')
				$('#carnum').numberbox('setValue', 0)
				$('#TransactmodeType').combobox('clear')
			}
		});
		$("#addType").click(function() {
			$('#tmpData').datagrid('loadData', {
				total : 0,
				rows : []
			});
			$('#addtypedlg').dialog({
				title : "根据条件编车"
			});
			$('#addtypedlg').dialog('open');
		});
		$("#tempTypeAdd")
				.click(
						function() {
							var type = $('#carType').combobox('getValues');
							var TransactmodeType = $('#TransactmodeType')
									.combobox('getValues');
							var num = $('#carnum').numberbox('getValue');
							if (type.length > 0) {
								if (TransactmodeType.length > 0) {
									if (num > 0) {
										//保存至编车表格
										$
												.ajax({
													type : "POST",
													url : "${ctx}/FaultData/selectType",
													traditional : true,//阻止深度序列化数组对象
													data : {
														typeCar : type,
														typeCarNum : num,
														TypeTransactmode : TransactmodeType,
													},
													success : function(data) {
														// 因为返回值为object类型，所以此处eval取值时应该转一下object
														var result = eval("("
																+ data + ")");
														if (result.data.length > 0) {
															$
																	.each(
																			result.data,
																			function(
																					key,
																					val) {
																				$(
																						'#tmpData')
																						.datagrid(
																								'insertRow',
																								{//在右边插入新行。
																									index : key,
																									row : {
																										VEHICLE_SERIAL : val.VEHICLE_SERIAL,
																										VEHICLE_ID : val.VEHICLE_ID,
																										VEHICLE_TYPE : val.VEHICLE_TYPE,
																										FAULT_NAME : val.FAULT_NAME
																									}
																								});
																				//console.log(val);
																			});
															$.messager
																	.alert(
																			'提示消息',
																			"根据您的条件查到了【"
																					+ result.data.length
																					+ "】条数据,已经添加到右侧编辑栏");
															$('#addtypedlg')
																	.dialog(
																			'close');
														} else {
															$.messager
																	.alert(
																			'提示消息',
																			"未查到数据，请检查条件或更换条件重新查询");
															return;
														}
													},
													error : function(result) {
														$.messager.alert(
																'提示消息', "系统异常");
														return;
													}
												});
									} else {
										$.messager.alert('提示消息', "编车数量不可为0");
										return;
									}
								} else {
									$.messager.alert('提示消息', "处理方式不可为空");
									return;
								}
							} else {
								$.messager.alert('提示消息', "车型不可为空");
								return;
							}
						});
		$("#tempTypeCancel").click(function() {
			$('#carType').combobox('clear')
			$('#carnum').numberbox('setValue', 0)
			$('#TransactmodeType').combobox('clear')
			$('#addtypedlg').dialog('close');
		});
		$("#tempTypeReset").click(function() {
			//重置条件
			$('#carType').combobox('clear')
			$('#carnum').numberbox('setValue', 0)
			$('#TransactmodeType').combobox('clear')
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
					stationcode : $('#tcz').val(),
					VEHICLE_ID : $('#VEHICLE_ID').val(),
					startTime : $('#startTime').datetimebox('getValue'),
					endTime : $('#endTime').datetimebox('getValue'),
				});
			} else {
				$.messager.alert('提示消息', '时间范围，开始时间以及结束时间不可为空');
			}
		});
		//保存待编辑数据
		$("#TEMPADD").on('click', function() {
			var rowstemp = $("#tmpData").datagrid("getRows"); //这段代码是获取当前页的所有行。
			var stationcode = $('#tcz').val();
			var arrData = [];
			if (rowstemp.length != '0') {
				$.each(rowstemp, function(key, val) {
					arrData.push(val.VEHICLE_SERIAL);
				});
				processBarOpen();
				$.ajax({
					type : "POST",
					url : "${ctx}/FaultData/vEditing",
					traditional : true,//阻止深度序列化数组对象
					data : {
						dataArr : arrData,
						stationcode : stationcode,
					},
					success : function(data) {
						var result = eval("(" + data + ")");
						// 因为返回值为object类型，所以此处eval取值时应该转一下object
						if (result.code == 200) {
							processBarClose();
							$.messager.alert('提示消息', "编排成功");
							$('#tmpData').datagrid('loadData', {
								total : 0,
								rows : []
							});
						} else {
							processBarClose();
							$.messager.alert('提示消息', "编排失败");
						}
					},
					error : function(result) {
						$.messager.alert('提示消息', "系统异常");
						return;
					}
				});
			} else {
				$.messager.alert('提示消息', '您还没有编排任何数据');
			}
		});
		//等待条
		function processBarOpen() {
			if (!$("div").is("#dialog_div")) {
				$(
						"<div id='dialog_div'><img src='${ctx}/resources/img/loadingAnimation.gif'/></div>")
						.hide().appendTo(document.body);
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