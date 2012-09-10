<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/style.css" />
<link rel="stylesheet" type="text/css" href="style/list.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<title>订餐系统管理</title>
<script type="text/javascript">

$(document).ready(function() {
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>
});
</script>
</head>
<body>

	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="30">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="15" height="30"><img src="images/tab_03.gif"
							width="15" height="30" /></td>
						<td background="images/tab_05.gif"><img src="images/311.gif"
							width="16" height="16" /><span class="titleText">餐厅信息</span></td>
						<td width="15"><img src="images/tab_07.gif" width="15"
							height="30" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="9" background="images/tab_12.gif">&nbsp;</td>
						<td bgcolor="#f3ffe3">
							<div>
								<h1>${data.name }</h1>
								<img alt="${data.name }" src="${data.imgUrl }" height="240"
									width="320" />
								<p>${data.description }</p>
							</div>
							<div>
								地址：&nbsp;<span>${data.address }</span><br> 联系电话：&nbsp;<span>${data.telNum
									}</span>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>