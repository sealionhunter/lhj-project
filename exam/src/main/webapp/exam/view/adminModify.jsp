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
<script type="text/javascript">
function doLogout() {
	adminLogoutForm.submit();
}
</script>
<style type="text/css">
<!--
.STYLE1 {
	color: #666666;
	font-weight: bold;
}
-->
</style>
</head>
<body>

	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>


	<div class="midBox">
		<div class="midTitle">管理员密码修改</div>
		<div class="cnt">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
						style="padding-left: 10px;"><span class="STYLE1">管理员密码修改</span></td>
					<td height="32" align="right" bgcolor="#FFFFFF" valign="middle"
						style="padding-right: 10px;" width="30"><form:form
							name="adminLogoutForm" method="post"
							action="/exam/adminLogout.action" commandName="AdminLoginCommand">
							<a href="#" onclick="doLogout();">注销</a>
						</form:form></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center" height="448"
						style="padding-top: 30px;"><form:form name="adminModifyForm"
							method="post" action="/exam/modifyAdmin.action"
							commandName="AdminModifyCommand">
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right; width: 20%;">
										管理员帐号：</td>
									<td style="height: 10px; text-align: left;"><form:input
											path="adminId" id="adminId" maxlength="18" size="18"
											cssClass="bbsInput_short" /><font color="#ff0000">*&nbsp;&nbsp;<form:errors
												path="adminId" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right;">旧密码：</td>
									<td style="height: 10px; text-align: left;"><form:password
											path="oldPassword" id="oldPassword" maxlength="16" size="18"
											cssClass="bbsInput_short" /><font color="#ff0000">*&nbsp;&nbsp;<form:errors
												path="oldPassword" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right;">新密码：</td>
									<td style="height: 10px; text-align: left;"><form:password
											path="password" id="password" maxlength="16" size="18"
											cssClass="bbsInput_short" /><font color="#ff0000">*&nbsp;&nbsp;请务必牢记&nbsp;&nbsp;
											<form:errors path="password" />
									</font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right;">重复新密码：</td>
									<td style="height: 10px; text-align: left;"><form:password
											path="rePassword" id="rePassword" maxlength="16" size="18"
											cssClass="bbsInput_short" /><font color="#ff0000">*&nbsp;&nbsp;<form:errors
												path="rePassword" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td>&nbsp;</td>
									<td style="text-align: left;"><input type="submit"
										value="确定" style="width: 50px;" /></td>
								</tr>
							</table>
						</form:form></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
