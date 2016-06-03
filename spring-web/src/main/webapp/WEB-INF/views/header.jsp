<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="header">
	<div class="logo">
		<img src="<c:url value="/resources/img/logo.gif" />" align="middle" />
	</div>
	<div class="logo">
		<script type="text/javascript">
			AC_FL_RunContent(
					'codebase',
					'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0',
					'width',
					'517',
					'height',
					'42',
					'src',
					'<c:url value="/resources/img/banner2" />',
					'quality',
					'high',
					'pluginspage',
					'http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash',
					'movie', '<c:url value="/resources/img/banner2" />'); //end AC code
		</script>
		<noscript>
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
				codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0"
				width="517" height="42">
				<param name="movie"
					value="<c:url value="/resources/img/banner2.swf" />" />
				<param name="quality" value="high" />
				<embed src="<c:url value="/resources/img/banner2.swf" />"
					quality="high"
					pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash"
					type="application/x-shockwave-flash" width="517" height="42"></embed>
			</object>
		</noscript>
	</div>
	<div class="userinfo">
		<div>
			<span>组员：</span> <span style="color: red;"><sec:authentication
					property="principal.username" /></span>&nbsp;&nbsp;&nbsp;&nbsp;<span><a
				href="<c:url value="/users/signout" />">退出</a></span>
		</div>
		<div style="margin-top: 5px;">
			<spring:eval expression="new java.util.Date()" var="today" />
			<span><fmt:formatDate value="${today }" pattern="yyyy-MM-dd HH:mm:ss"/> </span>
		</div>
	</div>
	<p class="clearfloat" />
</div>