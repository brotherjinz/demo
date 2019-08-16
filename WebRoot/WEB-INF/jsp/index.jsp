<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	pageContext.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>数据同步</title>
<link rel="bookmark" type="image/x-icon" href="${ctx}/resources/img/logo.ico"/>
<link rel="shortcut icon" href="${ctx}/resources/img/logo.ico">
<link rel="icon" href="${ctx}/resources/img/logo.ico">
<meta name="description" content="数据同步" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" href="${ctx}/resources/assets/css/bootstrap.css" />
<link rel="stylesheet"
	href="${ctx}/resources/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/easyui/themes/icon.css">

<!-- text fonts -->
<link rel="stylesheet" href="${ctx}/resources/assets/css/ace-fonts.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/resources/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="${ctx}/resources/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${ctx}/resources/assets/css/ace-ie.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="${ctx}/resources/assets/js/ace-extra.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="${ctx}/resources/js/html5shiv.min.js"></script>
		<script src="${ctx}/resources/js/respond.min.js"></script>
		<![endif]-->
<script type="text/javascript">
	//获得当前时间,刻度为一千分一秒
	var initializationTime = (new Date()).getTime();
	function showLeftTime() {
		var now = new Date();
		var year = now.getYear() + 1900;
		var month = now.getMonth() + 1;
		var day = now.getDate();
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds();
		document.all.show.innerHTML = "" + year + "年" + month + "月" + day
				+ "日 " + hours + ":" + minutes + ":" + seconds + "";
		//一秒刷新一次显示时间
		var timeID = setTimeout(showLeftTime, 1000);
		Cheight = $(document).outerHeight() - 45 - 41 - 19;
		$("#content-frame").height(Cheight + "px");
		$("#sidebar").height(Cheight + "px");
	}
	function createiFrame(menuid, menuName1, menuName2, menuName3, menuUrl) {

		if ($('#main-menu li').hasClass('active')) {
			$('#main-menu li').remove(".active");
		}
		//添加页导航面包屑
		$("#main-menu").append('<li class="active">' + menuName1 + '</li>');
		$("#main-menu").append('<li class="active">' + menuName2 + '</li>');
		if (menuName3 != "") {
			$("#main-menu").append('<li class="active">' + menuName3 + '</li>');
		}
		$("#" + menuid).addClass("active").siblings().removeClass("active");
		$("#" + menuid).parents("li").siblings().find("ul li.active")
				.removeClass("active");
		//创建iFrame
		if (menuUrl != "") {
			$('#content-frame').attr('src', "${ctx}/" + menuUrl);//动态改变src
		} else {
			$('#content-frame').attr('src', menuUrl);//动态改变src
		}
	};
</script>
<style type="text/css">
#editActiveUserDlg table tr {
	height: 40px;
}
</style>
</head>
<body class="no-skin" onload="showLeftTime();">
	<div id="navbar" class="navbar navbar-default  ace-save-state">
		<div class="navbar-container ace-save-state" id="navbar-container">
			<!-- #section:basics/sidebar.mobile.toggle -->
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<!-- /section:basics/sidebar.mobile.toggle -->
			<div class="navbar-header pull-left">
				<!-- #section:basics/navbar.layout.brand -->
				<a href="${ctx}/Init/init_Index" class="navbar-brand"> <small>
						<i class="fa fa-hdd-o"></i> <i class="fa fa-exchange"></i> <i
						class="fa fa-hdd-o"></i> 数据同步
				</small>
				</a>
			</div>
			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue dropdown-modal"><label id="show"></label></li>
				</ul>
			</div>
			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<!-- 此处存放用户名 -->
					<span class="pull-right" id="sysdate"></span>
				</ul>
			</div>
		</div>
	</div>
	<!-- /section:basics/navbar.layout -->
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container');
			} catch (e) {
			}
		</script>
		<div id="sidebar" class="sidebar responsive  ace-save-state">
			<script type="text/javascript">
				try {
					ace.settings.loadState('sidebar');
				} catch (e) {
				}
			</script>
			<ul class="nav nav-list">
				<!-- 菜单首行固定   active-->
				<li class=""><a href="${ctx}/Init/init_Index"> <i
						class="menu-icon fa fa-home"></i> <span class="menu-text">
							首页 </span>
				</a> <b class="arrow"></b></li>
				<c:forEach items="${listMenu}" var="listMenu1">
					<c:if test="${listMenu1.p_MENU_ID == '0'}">
						<!-- 一级菜单 -->
						<li id="${listMenu1.MENU_ID}" class=""><a href="#"
							class="dropdown-toggle"> <i class="${listMenu1.MENU_ICO_URL}"></i>
								<span class="menu-text">${listMenu1.MENU_NAME}</span> <b
								class="arrow fa fa-angle-down"></b>
						</a> <b class="arrow"></b> <!-- 二级菜单 -->
							<ul id="leftMenu" class="submenu">
								<c:forEach items="${listMenu}" var="listMenu2">
									<c:if test="${listMenu2.p_MENU_ID == listMenu1.MENU_ID}">
										<c:if test="${listMenu2.MENU_URL != '0'}">
											<li id="${listMenu2.MENU_ID}" style="cursor: pointer;"
												onclick="createiFrame('${listMenu2.MENU_ID}','${listMenu1.MENU_NAME}','${listMenu2.MENU_NAME}','','${listMenu2.MENU_URL}');"><a>
													<i class="menu-icon fa fa-caret-right"></i>${listMenu2.MENU_NAME}
											</a> <b class="arrow"></b></li>
										</c:if>
										<c:if test="${listMenu2.MENU_URL == '0'}">
											<li class=""><a href="#" class="dropdown-toggle"> <i
													class="menu-icon fa fa-caret-right"></i>${listMenu2.MENU_NAME}<b
													class="arrow fa fa-angle-down"></b>
											</a> <b class="arrow"></b>
												<ul class="submenu">
													<c:forEach items="${listMenu}" var="listMenu3">
														<c:if test="${listMenu3.p_MENU_ID == listMenu2.MENU_ID}">
															<!-- 三级菜单 -->
															<li id="${listMenu3.MENU_ID}" style="cursor: pointer;"
																onclick="createiFrame('${listMenu3.MENU_ID}','${listMenu1.MENU_NAME}','${listMenu2.MENU_NAME}','${listMenu3.MENU_NAME}','${listMenu3.MENU_URL}');"><a>
																	<i class="menu-icon fa fa-caret-right"></i>
																	${listMenu3.MENU_NAME}
															</a> <b class="arrow"></b></li>
														</c:if>
													</c:forEach>
												</ul></li>
										</c:if>
									</c:if>
								</c:forEach>
							</ul></li>
					</c:if>
				</c:forEach>
			</ul>
			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i id="sidebar-toggle-icon"
					class="ace-icon fa fa-angle-double-left ace-save-state"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>
		</div>
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul id="main-menu" class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a
							href="${ctx}/Init/init_Index">首页</a></li>


						<!-- <li class="active"></li> -->
					</ul>
					<!-- <span style="padding-left: 900px;" id="sysdate" >aaaa</span> -->
					<!-- 搜索框 -->
					<!-- <div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text" placeholder="搜索 ..." class="nav-search-input" id="nav-search-input" autocomplete="off"> <i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div> -->
					<div class="page-content" style="padding: 0px;">
						<div class="row">
							<div class="col-xs-12">
								<iframe class="main-content" id="content-frame"
									style="width: 100%; height: 100%; padding: 0px;" scrolling="no"
									frameborder="0" src="${ctx}/Init/Welcome"> </iframe>
							</div>
						</div>
					</div>
				</div>
				<!-- /.main-content -->
			</div>
			<!-- 回到顶部 -->
			<a href="#" id="btn-scroll-up"
				class="btn-scroll-up btn btn-sm btn-inverse"> <i
				class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
			<!-- <div class="footer-content" align="center">
				<span class="bigger-120" style="margin-bottom: 10px;"> <span class="blue bolder">JTV</span>
					Application © 2018-2019
				</span>
			</div> -->
		</div>

		<script src="${ctx}/resources/js/jquery/jquery.js"></script>

		<!--[if IE]>
			<script src="${ctx}/resources/js/jquery.1x/jquery.js"></script>
		<![endif]-->

		<script src="${ctx}/resources/bootstrap/dist/js/bootstrap.js"></script>
		<script src="${ctx}/resources/assets/js/ace.js"></script>


		<!-- ace scripts -->
		<script src="${ctx}/resources/assets/js/src/elements.scroller.js"></script>
		<script type="text/javascript"
			src="${ctx}/resources/easyui/jquery.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/resources/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/resources/easyui/easyloader.js"></script>
		<script type="text/javascript"
			src="${ctx}/resources/js/jquery.validate.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript"
			src="${ctx}/resources/js/easyuiDateboxCleanButton.js"></script>
		<script type="text/javascript">
			easyloader.locale = "zh_CN";
		</script>
		<script type="text/javascript" src="${ctx}/resources/js/MD5-min.js"></script>
</body>
</html>
