<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
	<head>
		<title><fmt:message key="title"/></title>
	</head>
	<body>
		<h1><fmt:message key="heading" /> </h1>
		<p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
		<h3><fmt:message key="products-title"/></h3>
		<c:forEach items="${model.products }" var="prod">
			<c:url var="detail" value="detail.htm">
			    <c:param name="id" value="${prod.id}" />
			</c:url>
			<a href="${detail}"><c:out value="${prod.description }" /></a>
		  	<i>$<c:out value="${prod.price }"/></i><br/><br/>
		</c:forEach>
		<br/>
		<a href="<c:url value="priceincrease.htm"/>"/>Increase Prices</a>
		
		
		<br/>
		
		<table>
		  <tr>
		      <td rowspan="2"><input type="text" /></td>
		      <td><img src="" onclick=""/></td>
		  </tr>
		  <tr>
		      <td><img src="" onclick=""/></td>
		  </tr>
		</table>
	</body>
</html>