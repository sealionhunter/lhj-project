<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>FX D-MAT Input System</title>
<link href="<c:url value="/resources/css/css.css" />" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div id="mainbody">
		<div id="content">
			<div class="titleblock">消息管理</div>
			<div id="mainContent" class="contentblock">
				<div id="tableDiv">
					<table id="tblProj">
						<thead>
							<tr>
								<th><span>序号</span></th>
								<th><span>消息主题</span></th>
								<th><span>消息内容</span></th>
								<th><span>发布人</span></th>
								<th><span>发布日期</span></th>
							</tr>
						</thead>

						<c:forEach items="${noticeList}" var="notice"
							varStatus="noticeStatus">
							<tr>
								<td>${noticeStatus.index }</td> 
								<td>${notice.title }</td>
								<td>${notice.comment }</td>
								<td>${notice.updateUserName }</td>
								<td><fmt:formatDate value="${notice.updateDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="btnbar"><input type="button" class="btnNew" value="新增" onclick="doAdd()"></div>
			</div>
			<p class="clearfloat" />
		</div>
	</div>
<form name="formNotice" action="<c:url value="notice/noticeAdd"/> ">
	<input type="hidden" name="noticeId" />
</form>
</body>
</html>