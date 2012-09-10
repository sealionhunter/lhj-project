<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/style.css" />
<link rel="stylesheet" type="text/css" href="style/list.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<title>订餐系统管理</title>
<script type="text/javascript">
function init() {
	<c:if test="${errormsg != null}">
	alert("${errormsg}");
	</c:if>
}

function valid() {
		var uid = $("#userId").val();
		var pwd = $("#password").val();
		if (uid == '' || uid.length == 0) {
			alert("请输入用户名！");
			return false;
		}
		if (pwd == '' || pwd.length == 0) {
			alert("请输入密码！");
			return false;
		}
		return true;
}
</script>
</head>
<body onload="init();">
	<form id="loginForm" name="loginForm" action="login.action"
		onsubmit="return valid();" method="post">
		<div id="login">
			<h1>请登录：</h1>
			<table>
				<tr>
					<td align="right">用户名:</td>
					<td><input type="text" name="userId" value="${userId }"
						id="userId" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td align="right">密码 :</td>
					<td><input type="password" name="password"
						value="${password }" id="password" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td><input type="submit" id="login_0" value="登录" /></td>
					<td><input type="reset" id="login_0" value="重置" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>