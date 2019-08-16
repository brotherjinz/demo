$(function() {
	
	$("#login").click(function() {
		var userName = $("#login_name").val();
		var userPwd = $("#login_pwd").val();
		if (userName != "" && userPwd != "") {
			$.ajax({
				type : "POST",
				url : "user/login",
				data : {
					USER_NAME : userName,
					USER_PWD : userPwd
				},
				success : function(result) {
					// 因为返回值为object类型，所以此处eval取值时应该转一下object
					if (result.code == 200) {
						window.location.href = "user/init_Index"
					} else {
						$.messager.alert('提示消息', result.msg);
						return;
					}
				},
				error : function(result) {
					$.messager.alert('提示消息', "系统异常");
					return;
				}
			});
		} else {
			$.messager.alert('提示消息', '用户名或密码不可为空');
			return;
		}
	});
	$("#register").click(function() {
		var email = $("#register_Email").val();
		var user_name = $("#register_name").val();
		var user_pwd = $("#register_pwd").val();
		var user_pwd1 = $("#register_pwd1").val();
		if (user_pwd == user_pwd1) {
			if (email != "" && user_name != "" && user_pwd != "") {
				$.ajax({
					type : "POST",
					url : "user/register",
					data : {
						EMAIL : email,
						USER_NAME : user_name,
						USER_PWD : user_pwd
					},
					success : function(result) {
						// 因为返回值为object类型，所以此处eval取值时应该转一下object
						// var json = eval("(" + result + ")");
						if (result.code == 200) {
							// alert(result.msg);
							$.messager.alert('提示消息', result.msg);
							window.location.href = "/XJXT/user/init_Index"
						} else {
							$.messager.alert('提示消息', result.msg);
							// alert(result.msg);
							return;
						}
					},
					error : function(result) {
						$.messager.alert('提示消息', "系统异常");
						// alert(result.msg);
						return;
					}
				});
			} else {
				$.messager.alert('提示消息', '用户名,密码,邮箱不可为空');
				return;
			}
		} else {
			$.messager.alert('提示消息', '两次密码输入不一致');
			return;
		}
	});
});

document.body.onselectstart = document.body.oncontextmenu = function() {
	return false;
}