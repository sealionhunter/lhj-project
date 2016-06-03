<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="x-ua-compatible" content="ie=7" />
	<title>FX D-MAT Input System</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/css.css" />"
		type="text/css" />
</head>
<body>
	<div id="mainbody">
		<c:if test="${not empty signinErrorMessage}">
			<div class="error">
				<c:out value="${signinErrorMessage}" />
			</div>
		</c:if>
		<div id="login">
			<form id="openid_form"
				action="<c:url value="/users/signin/authenticate" />" method="post">
				<div class="username">
					域账号&nbsp;&nbsp;<input type="text" class="middle" value="" name="j_username" />
				</div>
				<div class="password">
					密&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;<input type="password"
						class="middle" value="" name="j_password"/>
				</div>
				<p class="clearfloat" />
				<div class="btnbar">
					<input id="openid_submit" type="submit" class="btnNew" value="登录" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
