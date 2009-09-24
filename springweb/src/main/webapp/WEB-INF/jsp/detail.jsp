<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
    <head>
        <title>Produce Detail</title>
    </head>
    <body>
        <h1>Product Detail: <c:out value="${product.description}"/></h1>
        <br/>
        <c:out value="${product.id }" />
        <i><c:out value="${product.description }" /></i>
        <i>$<c:out value="${product.price }"/></i>
        
        <br/>
        <br/>
        
    <a href="<c:url value="hello.htm"/>">Home</a>
    </body>
</html>