<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="titleblock">消息通知</div>
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
					<td><fmt:formatDate value="${notice.updateDate}"
							pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<p class="clearfloat" />
