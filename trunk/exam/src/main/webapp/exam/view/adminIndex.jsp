<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上报名-合肥猎头网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/exam/view/css/user.css" type="text/css"
	media="all" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<script type="text/javascript">
	function doLogout() {
		adminLogoutForm.submit();
	}
</script>
</head>
<body>

	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>

	<div class="midBox">
		<div class="midTitle">管理员首页</div>
		<div class="cnt">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
						style="padding-left: 10px;"><b><span
							style="color: #666666">管理员首页</span></b></td>
					<td height="32" align="right" bgcolor="#FFFFFF" valign="middle"
						style="padding-right: 10px;" width="30"><form:form
							name="adminLogoutForm" method="post"
							action="/exam/adminLogout.action" commandName="AdminLoginCommand">
							<a href="#" onclick="doLogout();">注销</a>
						</form:form></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center" height="448"
						style="padding-top: 30px;">
						<table width="80%" border="0" cellpadding="4" cellspacing="0"
							bgcolor="#E1E1E1">
							<tr bgcolor="#ffffff">
								<td style="height: 10px; text-align: left;"><a
									href="/exam/signuppeopleinfo.action">考生资格审核</a></td>
							</tr>
							<tr bgcolor="#ffffff">
								<td style="height: 10px; text-align: left;"><a
									href="/exam/modifyAdmin.action">管理员密码修改</a></td>
							</tr>
							<tr bgcolor="#ffffff">
								<td style="height: 10px; text-align: left;"><a
									href="/exam/signupdetailsearch.action">报名信息检索</a></td>
							</tr>
							<tr bgcolor="#ffffff">
								<td style="height: 10px; text-align: left;"><a
									href="/exam/userPasswordReset.action">考生密码重置</a></td>
							</tr>
							<tr bgcolor="#ffffff">
								<td style="height: 10px; text-align: left;"><a
									href="/exam/roomList.action">考场登陆</a></td>
							</tr>
							<tr bgcolor="#ffffff">
								<td style="height: 10px; text-align: left;"><a
									href="/exam/userSeatReset.action">考生座位重置</a></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>

	</div>

</body>
</html>
