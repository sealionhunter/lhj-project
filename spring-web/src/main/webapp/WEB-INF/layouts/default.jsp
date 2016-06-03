<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<base href="<c:url value="/" />" />
<title>FX D-MAT Input System</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/css.css" />" ></link>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css" />" ></link>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/AC_RunActiveContent.js" />" ></script>
</head>
<body >
	<div id="mainbody">
		<tiles:insertAttribute name="header" ignore="true" />
		<tiles:insertAttribute name="menu" ignore="true" />
		<div id="content">
			<tiles:insertAttribute name="body" ignore="true" />
		</div>
		<tiles:insertAttribute name="footer" ignore="true" />
	</div>
</body>
</html>