<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>XX报名-XXXX信息网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="/exam/view/css/user.css" type="text/css"
		media="all" />
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

	<form:form name="modifyPasswordForm" method="post"
		action="/exam/modifyPassword.action"
		commandName="ModifyPasswordCommand">
		<div class="midBox">
			<div class="midTitle">修改密码</div>
			<div class="cnt">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><span class="STYLE1">修改密码</span></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="448"
							style="padding-top: 30px;">
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right; width: 20%;">
										身份证号：</td>
									<td style="height: 10px; text-align: left;"><form:input
											path="idCardNo" id="idCardNo" maxlength="18" size="18"
											cssClass="bbsInput_short" /><font color="#ff0000">*&nbsp;&nbsp;<form:errors
												path="idCardNoError" /></font></td>
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
												path="rePasswordError" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td>&nbsp;</td>
									<td style="text-align: left;"><input type="submit"
										value="确定" style="width: 50px;" name="_target0" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>

		</div>
	</form:form>
</body>
</html>
