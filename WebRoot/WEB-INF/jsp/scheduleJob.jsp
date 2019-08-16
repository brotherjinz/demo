<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/resources/include/commonEasyUi.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>任务列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body style="padding: 0px;">
	<div style="width: 100%; height: 100%;">
		<table id="scheduleListDj" style="width: 100%; height: 100%;"
			data-options="singleSelect:true,autoRowHeight:true,toolbar:tb">
		</table>
		<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<!-- | <a id="addUserBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>| <a id="editUserBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> -->
				<!--  <label style="margin-left: 5px;color: red;">按条件查询:</label>
			  用户名:<input id="uName" class="easyui-textbox" style="width:100px"> 
			  日期: <input id="startDate" class="easyui-datebox" editable="false" style="width:150px"> 至: <input id="endDate" editable="false" class="easyui-datebox" style="width:150px"> 删除状态: <select id="isDelete" class="easyui-combobox" editable="false" panelHeight="auto" style="width:70px">
					<option value="">请选择</option>
					<option value="N">未删除</option>
					<option value="Y">已删除</option>
				</select> <a id="btnsearch" class="easyui-linkbutton" iconCls="icon-search">搜索</a> -->
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#scheduleListDj')
				.datagrid(
						{
							url : '${ctx}/schedule/getJob',
							rownumbers : false,
							fitColumns : true,
							striped : true,
							collapsible : false,// 右上角隐藏用的小箭头
							loadMsg : "数据加载中，请稍后...",
							pagination : true,
							pageList : [ 5, 10, 20, 30, 40 ],
							pageSize : 20,
							columns : [ [
									{
										title : 'scheduleJobId',
										field : 'ck',
										checkbox : true
									},
									{
										field : "jobName",
										title : "任务名称",
									},
									{
										field : "aliasName",
										title : "任务别名",
									},
									{
										field : "jobGroup",
										title : "任务分组",
									},
									{
										title : "任务类Class",
										field : "jobClass",
										hidden : 'true'
									},
									{
										field : "status",
										title : "任务状态",
										formatter : function(value, row, index) {
											if (row.status == 0) {
												return '<label style="color:#FF0000;"><b>未启动</b></label>';
											} else if (row.status == 1) {
												return '<label style="color:#00FF40;"><b>已启动</b></label>';
											} else if(row.status == 3){
												return '<label style="color:#00FF40;"><b>运行中</b></label>';
											}
										}
									},
									{
										field : "cronExpression",
										title : "执行表达式",
									},
									{
										field : "createTime",
										title : "创建时间",
									},
									{
										field : "scheduleJobId",
										title : "操作",
										align : 'center',
										width : 100,
										formatter : function(value, row, index) {
											var str = ""
													+ "<a id='' class='onestarBtn' onclick='runOnce(\""													+ value
													+ "\")' href='javascript:void(0)'></a>"
													+ "<a id='' class='startbtn' onclick='startTask(\""
													+ value
													+ "\")' href='javascript:void(0)'></a>"
													+ "|<a id='' class='endbtn' onclick='endTask(\""
													+ value
													+ "\")' href='javascript:void(0)'></a>";
											return str;
										}
									}, {
										field : "description",
										title : "任务描述",

									} ] ],
							onLoadSuccess : function(data) {
								$('.onestarBtn').linkbutton({
									text : '运行一次',
									plain : true,
									iconCls : 'icon-ok'
								});
								$('.startbtn').linkbutton({
									text : '启动',
									plain : true,
									iconCls : 'icon-ok'
								});
								$('.endbtn').linkbutton({
									text : '停止',
									plain : true,
									iconCls : 'icon-remove'
								});
							}
						});
		function runOnce(scheduleJobId) {
			job_public("确定要运行一次此任务吗？", "${ctx}/schedule/runOnce", scheduleJobId);
		}
		function startTask(scheduleJobId) {
			//$.messager.alert('提示消息', 'startTask');
			job_public("确定要启动任务吗？", "${ctx}/schedule/resumeJob", scheduleJobId);
		}
		function endTask(scheduleJobId) {
			//$.messager.alert('提示消息', 'endTask');
			job_public("确定要关闭任务吗？", "${ctx}/schedule/pauseJob", scheduleJobId);
		}
		$("#ael").click(function() {
			$('#aeldlg').dialog('open');
		});

		function job_public(msg, url, scheduleJobId) {
			$.messager.confirm({
				title : '提示',
				msg : msg,
				icon : 'info',
				width : 400,
				top : $(document).scrollTop() + 200,
				fn : function(r) {
					if (r) {
						$.ajax({
							type : "POST",
							url : url,
							data : {
								"scheduleJobId" : scheduleJobId
							},
							dataType : "json",
							success : function(result) {
								// 因为返回值为object类型，所以此处eval取值时应该转一下object
								//var json = eval("(" + result + ")");
								if (result.code == 200) {
									$.messager.alert('提示消息', result.msg);
								} else {
									$.messager.alert('提示消息', result.msg);
								}
								$('#scheduleListDj').datagrid('load');
							},
							error : function(result) {
								$.messager.alert('提示消息', "操作失败");
								return;
							}
						});
					}
				}
			});
		}
	</script>
</body>
</html>
