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
		<table id="faultData" style="width:100%; height: 100%;" data-options="singleSelect:true,autoRowHeight:true,toolbar:tb">
		</table>
	</div>
	<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<label style="margin-left: 5px; color: red;">按条件查询:</label>
					探测站: <select id="tcz" class="easyui-combobox" editable="false" onmousedown="if(this.options.length>3){this.size=4}" onblur="this.size=0" onchange="this.size=0"  style="width:200px">
					<option value="">请选择</option>
						<c:forEach items="${TCZ}" var="TCZ">
					<option value="${TCZ.STATIONCODE}">${TCZ.STATIONNAME}</option>
					</c:forEach>
					</select>
					车号:<input id="VEHICLE_ID" class="easyui-textbox" type="text"	name="name" style="width: 100px;"></input>
				<a id="btnsearch" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			</div>
		</div>
	<script type="text/javascript">
		$('#faultData').datagrid({
			url : '${ctx}/FaultData/syncDataQuery',
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
				field : 'VEHICLE_SERIAL',
				checkbox : true
			}, {
				field : "STATION_NAME",
				title : "探测站",
				width : 100
			}, {
				field : "STATIONCODE",
				title : "探测站编码",
				width : 100//
			}, {
				field : "VEHICLE_ID",
				title : "车号",
				width : 100
			}, {
				field : "DATA_STATE",
				title : "数据同步状态",
				width : 100,
				formatter : function(value, row, index) {
					var str='<label style="color:#FF0000;"><b>未同步</b></label>';
					if(value =="1"){
						str='<label style="color:#00FF40;"><b>已同步</b></label>';
					}
					return str;
				}
			}, {
				field : "IMG_STATE",
				title : "图片Zip同步状态",
				width : 100,
				formatter : function(value, row, index) {
					var str='<label style="color:#FF0000;"><b>未同步</b></label>';
					if(value =="1"){
						str='<label style="color:#00FF40;"><b>已同步</b></label>';
					}
					return str;
				}
			}] ]
		});
		$("#btnsearch").on('click', function() {
			$('#faultData').datagrid('load', {
				stationcode:$('#tcz').val(),
				VEHICLE_ID:$('#VEHICLE_ID').val(),
			});
		});
	</script>
</body>
</html>