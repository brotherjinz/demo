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
		<table id="configGrid" style="width: 100%; height: 100%;"
			data-options="singleSelect:true,autoRowHeight:true,toolbar:tb">
		</table>
		<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a id="add" class="easyui-linkbutton" iconCls="icon-add">新增</a> <a
					id="edit" class="easyui-linkbutton" iconCls="icon-edit">修改</a> <a
					id="delete" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
			</div>
		</div>
	</div>
	<div id="configdlg" class="easyui-dialog"
		style="width: 50%; height: 85%; padding: 0px" align="center">
		<input id="CONFIGID" type="hidden" class="easyui-textbox"
			disabled="disabled" name="name" style="width: 300px;"></input>
		<table cellpadding="5">
			<tr>
				<td style="float: right;">探测站编码:</td>
				<td><input id="STATIONCODE" class="easyui-textbox" type="text"
					name="name" style="width: 300px;"></input></td>
			</tr>
			<tr>
				<td style="float: right;">探测站名称:</td>
				<td><input id="STATION_NAME" class="easyui-textbox" type="text"
					name="name" style="width: 300px;"></input></td>
			</tr>
			<tr>
				<td style="float: right;">临时文件目录:</td>
				<td><input id="PATHTEMP" class="easyui-textbox" type="text"
					name="name" style="width: 300px;"></input></td>
			</tr>
			<tr>
				<td style="float: right;">图片存放文件夹:</td>
				<td><input id="PATHFOLDER" class="easyui-textbox" type="text"
					name="name" style="width: 300px;"></input></td>
			</tr>
			<tr>
				<td style="float: right;">取图路径:</td>
				<td><input id="PATHURL" class="easyui-textbox" type="text"
					name="name" style="width: 300px;"></input></td>
			</tr>
			<tr>
				<td style="float: right;">数据库类型:</td>
				<td><select id="dbtype" class="easyui-combobox" name="language"
					editable="false" panelHeight="auto" style="width: 300px;">
						<option value="1">Oracle</option>
						<!-- <option value="2">MySql</option> -->
				</select></td>
			</tr>
			<tr>
				<td style="float: right;">数据库IP:</td>
				<td><input id="dburl" class="easyui-textbox" type="text"
					name="name" style="width: 300px;"></input></td>
			</tr>
			<tr>
				<td style="float: right;">数据库SID:</td>
				<td><input id="dbsid" class="easyui-textbox" type="text"
					name="name" style="width: 300px;"></input></td>
			</tr>
			<!-- <tr>
				<td style="float: right;">示例:</td>
				<td><label style="color: red; font-size: 10px;">jdbc:oracle:thin:@数据库访问IP:端口号/实例名(sid)</label></td>
			</tr> -->
			<tr>
				<td style="float: right;">用户名:</td>
				<td><input id="dbusername" class="easyui-textbox" type="text"
					name="name" style="width: 300px;"></input></td>
			</tr>
			<tr>
				<td style="float: right;">密 码:</td>
				<td><input id="dbpwd" class="easyui-textbox" type="text"
					name="name" style="width: 300px;"></input></td>
			</tr>
		</table>
		<div align="center">
			<a class="easyui-linkbutton" id="Save" iconCls="icon-save"
				plain="false">保存</a> <a class="easyui-linkbutton" id="Cancel"
				iconCls="icon-cancel" plain="false">取消</a>
		</div>
	</div>
	<script type="text/javascript">
		$('#configGrid').datagrid({
			url : '${ctx}/config/queryPager',
			rownumbers : true,
			fitColumns : false,
			striped : true,
			collapsible : false,// 右上角隐藏用的小箭头
			loadMsg : "数据加载中，请稍后...",
			pagination : true,
			pageList : [ 5, 10, 20, 30, 40 ],
			pageSize : 20,
			columns : [ [ {
				title : 'CONFIGID',
				field : 'CONFIGID',
				checkbox : true
			}, {
				field : 'STATIONNAME',
				title : "探测站名称",
				width : 100
			}, {
				field : 'STATIONCODE',
				title : "探测站编码",
				width : 100
			}, {
				field : "PATHURL",
				title : "取图路径",
				width : 200
			}, {
				field : "STATION_DB_TYPE",
				title : "数据库类型",
				width : 100,
				formatter : function(value, row, index) {
					if (value == '1') {
						return "oracle";
					} else if (value == '2') {
						return "mysql";
					}
				}
			}, {
				field : "STATION_DB_URL",
				title : "数据库IP",
				width : 100
			}, {
				field : "STATION_DB_USERNAME",
				title : "数据库登录名",
				width : 100
			}, {
				field : "STATION_DB_PASSWORD",
				title : "数据库登陆密码",
				width : 100
			}, {
				field : "STATION_DB_SID",
				title : "SID",
				width : 100
			}, {
				field : "PATHTEMP",
				title : "临时文件目录",
				width : 300
			}, {
				field : "PATHFOLDER",
				title : "图片存储文件夹",
				width : 300
			} ] ]
		});
		//弹出框的属性设置
		$("#configdlg").dialog({
			closable : true,
			closed : true,
			modal : true,
			onClose : function() {
				//右上角关闭事件。清空文本框
				$('#STATIONCODE').textbox('setValue', "");
				$('#STATION_NAME').textbox('setValue', "");
				$('#PATHTEMP').textbox('setValue', "");
				$('#PATHFOLDER').textbox('setValue', "");
				$('#PATHURL').textbox('setValue', "");
				$('#dbtype').combobox('setValue', "");
				$('#dburl').textbox('setValue', "");
				$('#dbusername').textbox('setValue', "");
				$('#dbpwd').textbox('setValue', "");
				$('#dbsid').textbox('setValue', "");

			}
		});
		var operation = "";
		$("#delete").click(function() {
			operation = "delete";
			var row = $("#configGrid").datagrid('getSelected');
			if (row) {
				$.ajax({
					type : "POST",
					url : "${ctx}/config/cinfig",
					traditional : true,//阻止深度序列化数组对象
					data : {
						CONFIGID : row.CONFIGID,
						OPERATION : operation
					},
					success : function(result) {
						if (result) {
							$.messager.alert('提示消息', "删除成功");
							$('#configGrid').datagrid('load', {});
						} else {
							$.messager.alert('提示消息', "删除失败");
						}
					},
					error : function(result) {
						$.messager.alert('提示消息', "系统异常");
						return;
					}
				});
			} else {
				$.messager.alert('提示消息', "请先选中您要删除的数据");
			}
		});
		$("#btnsearch").on('click', function() {
			$('#configGrid').datagrid('load', {});
		});
		$("#add").click(function() {
			operation = "add";
			$('#configdlg').dialog('open');
		});
		$("#edit").click(function() {
			operation = "edit";
			var row = $("#configGrid").datagrid('getSelected');
			if (row) {
				$('#CONFIGID').textbox('setValue', row.CONFIGID);
				$('#STATIONCODE').textbox('setValue', row.STATIONCODE);
				$('#PATHTEMP').textbox('setValue', row.PATHTEMP);
				$('#PATHFOLDER').textbox('setValue', row.PATHFOLDER);
				$('#PATHURL').textbox('setValue', row.PATHURL);
				$('#STATION_NAME').textbox('setValue', row.STATIONNAME);
				$('#dbtype').combobox('setValue', row.STATION_DB_TYPE);
				$('#dburl').textbox('setValue', row.STATION_DB_URL);
				$('#dbusername').textbox('setValue', row.STATION_DB_USERNAME);
				$('#dbpwd').textbox('setValue', row.STATION_DB_PASSWORD);
				$('#dbsid').textbox('setValue', row.STATION_DB_SID);
				$('#configdlg').dialog('open');
			} else {
				$.messager.alert('提示消息', "请先选中您要编辑的数据");
			}
		});
		$("#Cancel").click(function() {
			operation = "edit";
			$('#configdlg').dialog('close');
			$('#STATIONCODE').textbox('setValue', "");
			$('#STATION_NAME').textbox('setValue', "");
			$('#PATHTEMP').textbox('setValue', "");
			$('#PATHFOLDER').textbox('setValue', "");
			$('#PATHURL').textbox('setValue', "");
			$('#dbtype').combobox('setValue', "");
			$('#dburl').textbox('setValue', "");
			$('#dbusername').textbox('setValue', "");
			$('#dbpwd').textbox('setValue', "");
			$('#dbsid').textbox('setValue', "");

		});
		$("#Save").click(function() {
			$.ajax({
				type : "POST",
				url : "${ctx}/config/cinfig",
				traditional : true,//阻止深度序列化数组对象
				data : {
					CONFIGID : $('#CONFIGID').val(),
					STATIONCODE : $('#STATIONCODE').val(),
					PATHTEMP : $('#PATHTEMP').val(),
					PATHFOLDER : $('#PATHFOLDER').val(),
					PATHURL : $('#PATHURL').val(),
					STATIONNAME : $('#STATION_NAME').val(),// 探测站名称
					STATION_DB_TYPE : $('#dbtype').val(),// 数据库类型 1/2 1 oracle，2 mysql
					STATION_DB_URL : $('#dburl').val(),// 数据库链接url
					STATION_DB_USERNAME : $('#dbusername').val(),// 数据库登陆用户名
					STATION_DB_PASSWORD : $('#dbpwd').val(),
					STATION_DB_SID : $('#dbsid').val(),
					OPERATION : operation
				},
				success : function(result) {
					if (result == "true") {
						if (operation == "edit") {
							$.messager.alert('提示消息', "修改成功");
						} else if (operation == "add") {
							$.messager.alert('提示消息', "新增成功");
						} else if (operation == "delete") {
							$.messager.alert('提示消息', "删除成功");
						}
						$('#configdlg').dialog('close');
						$('#STATIONCODE').textbox('setValue', "");
						$('#STATION_NAME').textbox('setValue', "");
						$('#PATHTEMP').textbox('setValue', "");
						$('#PATHFOLDER').textbox('setValue', "");
						$('#PATHURL').textbox('setValue', "");
						$('#dbtype').combobox('setValue', "");
						$('#dburl').textbox('setValue', "");
						$('#dbusername').textbox('setValue', "");
						$('#dbpwd').textbox('setValue', "");
						$('#dbsid').textbox('setValue', "");
						$('#configGrid').datagrid('load', {});
					} else if (result == "false") {
						$('#configdlg').dialog('close');
						$('#STATIONCODE').textbox('setValue', "");
						$('#STATION_NAME').textbox('setValue', "");
						$('#PATHTEMP').textbox('setValue', "");
						$('#PATHFOLDER').textbox('setValue', "");
						$('#PATHURL').textbox('setValue', "");
						$('#dbtype').combobox('setValue', "");
						$('#dburl').textbox('setValue', "");
						$('#dbusername').textbox('setValue', "");
						$('#dbpwd').textbox('setValue', "");
						$('#dbsid').textbox('setValue', "");
						$('#configGrid').datagrid('load', {});
						if (operation == "edit") {
							$.messager.alert('提示消息', "修改失败");
						} else if (operation == "add") {
							$.messager.alert('提示消息', "新增失败");
						} else if (operation == "delete") {
							$.messager.alert('提示消息', "删除失败");
						}
					}
				},
				error : function(result) {
					$.messager.alert('提示消息', "系统异常");
					return;
				}
			});
		});
	</script>
</body>
</html>