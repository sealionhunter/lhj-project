<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Locale"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style/style.css" />
<link rel="stylesheet" type="text/css" href="style/top.css" />
<title>top</title>
<script type="text/javascript">
	function logout() {
		document.logoutForm.submit();
		return false;

	}
</script>
</head>
<body>
	<form id="logoutForm" name="logoutForm" action="logout" method="get"
		target="_parent"></form>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		style="table-layout: fixed;">
		<tr>
			<td class="topBack">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="topLeft">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="centerBack">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="centerLeft">&nbsp;</td>
						<td style="width: 60px;">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="welcome">&nbsp;</td>
								</tr>
								<tr>
									<td class="name">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											style="table-layout: fixed;">
											<tr>
												<td style="width: 1px;">&nbsp;</td>

												<td class="text">${SESSION_LOGIN_INFO.uname}</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td class="platform">&nbsp;</td>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="operation"><img src="images/main_12.gif"
										width="367" height="23" border="0" usemap="#Map" /></td>
								</tr>
							</table>
						</td>
						<td class="timeBack">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="time">&nbsp;</td>

									<%
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 EEE",
												Locale.CHINESE);
									%>
									<td class="timeValue text">日期：<%=sdf.format(new Date())%></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="bottomBack">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="bottomLeft">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<map name="Map" id="Map">
		<area shape="rect" coords="3,1,49,22" href="#" />
		<area shape="rect" coords="52,2,95,21" href="#" />
		<area shape="rect" coords="102,2,144,21" href="#" />
		<area shape="rect" coords="150,1,197,22" href="#" />
		<area shape="rect" coords="210,2,304,20" href="#"
			onclick="window.parent.rightFrame.location=userEdit.action?uid=&actionType=edit" />
		<area shape="rect" coords="314,-1,361,21" href="#"
			onclick="return logout();" />
	</map>
</body>
</html>
