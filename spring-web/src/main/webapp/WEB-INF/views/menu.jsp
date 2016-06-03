<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<div id="menu">
	<div class="text">
		<ul>
			<spring:eval expression="T(com.ustcsoft.generalsolution.dmat.webui.menu.MenuParser).getInstance().getMenu().item" var="menus" />
			
			<c:forEach items="${menus}" var="menu">
					<spring:eval expression="menu.type.value()" var="menuType" />
					<c:url value="${item.href }" var="href"></c:url>
					<li><a href="${menuType == 'item' ? href : '#' }"><spring:message code="app.menu.${menu.name}" /></a>
					<c:if test="${menuType == 'folder' }">
						<ul>
							<c:forEach items="${menu.item}" var="item">
									<li><a href="${item.href }"><spring:message
												code="app.menu.${item.name}" /></a></li>
							</c:forEach>
						</ul>
					</c:if>
					</li>
			</c:forEach>
		</ul>
	</div>
	<div class="line"></div>
</div>