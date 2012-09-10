<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<title>订餐系统管理</title>
</head>
<body>
	<div id="menu">
		<ul>
			<li><a
				href="hotelProperty.action?actionType=property" target="mainContent">餐厅信息</a></li>
			<li><a
				href="hotelEdit.action?actionType=edit" target="mainContent">餐厅信息修改</a></li>
			<li><a
				href="tableEdit.action?uuid=" target="mainContent">餐台追加</a></li>
			<li><a
				href="tableList.action" target="mainContent">餐台一览</a></li>
			<li><a
				href="foodEdit.action?uuid=" target="mainContent">菜品追加</a></li>
			<li><a
				href="foodList.action" target="mainContent">菜品一览</a></li>
			<li><a
				href="orderEdit.action?uuid=" target="mainContent">订餐</a></li>
			<li><a
				href="orderList.action" target="mainContent">订餐一览</a></li>
		</ul>
	</div>
	<iframe name="mainContent" id="mainContent" src="loginForm.action"
		scrolling="auto" style="border: 0" height="750" width="100%"></iframe>
</body>
</html>