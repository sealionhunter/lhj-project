<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上报名-合肥猎头网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/exam/view/css/user.css" type="text/css"
	media="all" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
</head>
<body>
	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>
	<div class="midBox">
		<div class="midTitle">管理员登陆</div>

		<div class="cnt">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
						style="padding-left: 10px;">管理员登陆</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td valign="top" align="center" height="448"
						style="padding-top: 30px;"><form:form name="adminLoginForm"
							method="post" action="/exam/adminLogin.action"
							commandName="AdminLoginCommand">
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right; width: 20%;">
										管理员帐号：</td>
									<td style="height: 10px; text-align: left;"><form:input
											path="adminId" id="adminId" maxlength="18"
											cssClass="bbsInput_short" /> <font color="#ff0000">*&nbsp;&nbsp;<form:errors
												path="adminId" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right;">管理员密码：</td>
									<td style="height: 10px; text-align: left;"><form:password
											path="adminPassword" id="adminPassword" maxlength="18"
											cssClass="bbsInput_short" /> <font color="#ff0000">*&nbsp;&nbsp;<form:errors
												path="adminPassword" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td>&nbsp;</td>
									<td style="text-align: left;"><input type="submit"
										value="登 录" style="width: 50px;" />
										&nbsp;&nbsp;<input
										type="button" value="返回" style="width: 50px;" onclick="javascript:document.location='/exam/'"/></td>
								</tr>
							</table>
						</form:form></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>