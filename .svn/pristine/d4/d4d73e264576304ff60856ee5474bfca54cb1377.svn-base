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
	<div style="margin-bottom: 5px">
		按条件查询:</label> 请选择过车时间范围: <input id="startTime" editable="false"
			class="easyui-datetimebox" style="width: 150px"> 至: <input
			id="endTime" editable="false" class="easyui-datetimebox"
			style="width: 150px">
		<%-- <label>探测站:</label> <select
			id="stationId" class="easyui-combobox" editable="false"
			panelHeight="auto" style="width: 200px">
			<option value="">全部</option>
			<c:forEach items="${TCZ}" var="TCZ">
				<option value="${TCZ.STATION_ID}">${TCZ.STATION_NAME}</option>
			</c:forEach>
		</select> --%>
		<a id="btnsearch" class="easyui-linkbutton" iconCls="icon-search">查询</a>
	</div>
	<!-- <div id="txtj" style="width: 100%;" align="center">
		<h1 id="noData" style="margin-top: 200px;">暂无数据</h1>
		<div style="width: 90%; height: 500px;" id="pic1"></div>
	</div> -->
	<div id="pic1" style="width: 90%; height: 500px;"></div>
	<div id="tczdatadlg" class="easyui-dialog" style="width: 90%; height: 90%; padding: 0px" align="center">
		<table id="faultDatagrid" style="width: 100%; height: 100%;" data-options="singleSelect:true,autoRowHeight:true">
		</table>
	</div>
</body>
<script type="text/javascript">
$('#faultDatagrid').datagrid({
	url : '${ctx}/echarts/getfault',
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
		field : 'fault_SERIAL',
		checkbox : true
	}, {
		field : "station_NAME",
		title : "探测站名称",
		width : 100
	}, {
		field : "station_ID",
		title : "探测站编码",
		width : 70
	}, {
		field : "train_ID",
		title : "车次",
		width : 50
	}, {
		field : "vehicle_ID",
		title : "车号",
		width : 50
	}, {
		field : "vehicle_TYPE",
		title : "车型",
		width : 50
	}, {
		field : "pass_TIME",
		title : "过车时间",
		width : 100
	}, {
		field : "fault_NAME",
		title : "故障名称",
		width : 200,
		formatter : function(value, row, index) {
			return value;
		}
	} ] ]
});
</script>
<script type="text/javascript" src="${ctx}/resources/js/echarts.min.js"></script>
<script type="text/javascript">
	$(function() {
		var myChart = echarts.init(document.getElementById('pic1'));
		// 显示标题，图例和空的坐标轴
		myChart.setOption({
			// color: ['#ff7d27', '#47b73d', '#fcc36e', '#57a2fd', "#228b22"],//饼图颜色
			title : {
				text : '故障数据展示',
				subtext : '各探测站故障数据统计图',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'left',
				data : []
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'pie', 'funnel' ],
						option : {
							funnel : {
								x : '25%',
								width : '50%',
								funnelAlign : 'left',
								max : 1548
							}
						}
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			series : [ {
				name : '探测站名称/故障条数/百分比',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : []
			} ]
		});
		myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
		//从服务器端获取数据
		$.ajax({
			type : "POST",
			async : true, //同步执行
			url : "${ctx}/echarts/echarts_data",
			dataType : "json", //返回数据形式为json
			success : function(data) {
				var legendData = [];//类别数组（用于存放饼图的类别）
				var seriesData = [];
				//请求成功时执行该函数内容，data即为服务器返回的json对象
				$.each(data, function(index, item) {
					legendData.push(item.station_NAME); //挨个取出类别并填入类别数组 
					seriesData.push({
						name : item.station_NAME,
						station_id : item.station_ID,
						value : item.countnumber,
					});
				});
				myChart.hideLoading(); //隐藏加载动画
				myChart.setOption({ //加载数据图表                
					legend : {
						data : legendData
					},
					series : [ {
						data : seriesData
					} ]
				});
				myChart.on('click', function(param) {
					/* 1、直接替换panel属性

					例如：$('#dr_auth').panel({title: "新title"});

					所有的窗体都继承了panel中的方法，所以可以直接用。

					2、通过具体窗体替换属性

					例如：$('#dr_auth').dialog({title: "新title"});

					不推荐使用此种用法，因为会导致页面重新加载。 */
					 var data = param.data;
					$('#tczdatadlg').dialog({title: data.name+"故障列表"});
					$('#tczdatadlg').dialog('open');
					//打开弹层后加载table表数据
					$('#faultDatagrid').datagrid('load', {
						stationcode : data.station_id
					});
				});
			},
			error : function(errorMsg) {
				$.messager.alert("提示信息", '图表请求数据失败啦!');
			}
		});
		//弹出框的属性设置
		$("#tczdatadlg").dialog({
			closable : true,
			closed : true,
			modal: true,
			onClose: function () {
				//右上角关闭事件。清空文本框
			}
		});
		$("#btnsearch").on('click',function() {
					var startdate = $('#startTime').datetimebox('getValue');
					var enddate = $('#endTime').datetimebox('getValue');
					if (startdate != "" && enddate != "") {
						startdate = startdate.replace(/-/g, "/");
						enddate = enddate.replace(/-/g, "/");
						var start = new Date(startdate);
						var end = new Date(enddate);
						if (start != null && end != null) {
							if (end > start) {
								myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
								//从服务器端获取数据
								$.ajax({
									type : "POST",
									async : true, //同步执行
									data : {
										startTime : $('#startTime').datetimebox('getValue'),
										endTime : $('#endTime').datetimebox('getValue'),
									},
									url : "${ctx}/echarts/echarts_data",
									dataType : "json", //返回数据形式为json
									success : function(data) {
										var legendData = [];//类别数组（用于存放饼图的类别）
										var seriesData = [];
										//请求成功时执行该函数内容，data即为服务器返回的json对象
										$.each(data, function(index, item) {
											legendData.push(item.station_NAME); //挨个取出类别并填入类别数组 
											seriesData.push({
												name : item.station_NAME,
												station_id : item.station_ID,
												value : item.countnumber,
											});
										});
										myChart.hideLoading(); //隐藏加载动画
										myChart.setOption({ //加载数据图表                
											legend : {
												data : legendData
											},
											series : [ {
												data : seriesData
											} ]
										});
										myChart.on('click', function(param) {
											$('#tczdatadlg').dialog({title: data.name+"故障列表"});
											$('#tczdatadlg').dialog('open');
											//打开弹层后加载table表数据
											$('#faultDatagrid').datagrid('load', {
												stationcode : data.station_id
											});
										});
									},
									error : function(errorMsg) {
										$.messager.alert("提示信息", '图表请求数据失败啦!');
									}
								});
							} else {
								$.messager.alert("提示信息", '结束日期应大于开始时期！');
								return;
							};
						};
					} else {
						$.messager.alert('提示消息', '时间范围，开始时间以及结束时间不可为空');
					};
				});
	});
	
</script>
</html>