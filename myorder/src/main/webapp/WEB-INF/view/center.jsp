<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>center</title>
</head>
<frameset cols="177,*" rows="*" framespacing="0" frameborder="no"
	border="0">
	<frame src="left.action" name="leftFrame" scrolling="no"
		noresize="noresize" id="leftFrame" />

	<c:if test="${SESSION_LOGIN_INFO.admin }">
		<frame src="userEdit.action?uuid=&actionType=new" name="rightFrame" id="rightFrame" />
	</c:if>
	<c:if test="${not SESSION_LOGIN_INFO.admin }">
		<frame src="hotelProperty.action" name="rightFrame" id="rightFrame" />
	</c:if>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
