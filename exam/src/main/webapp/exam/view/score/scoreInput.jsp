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
function doScoreInput(flg) {
	var submitFlag = document.getElementById('submitFlag');
	if (flg) {
		submitFlag.value='true';
	} else {
		submitFlag.value='false';
	}
	scoreInputForm.submit();
}
function clear() {
	document.getElementById('idCardNo').value='';
	document.getElementById('admissionId').value='';
	document.getElementById('score').value='';
}

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
		<div class="midTitle">成绩录入</div>
		<div class="cnt">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="left" bgcolor="#FFFFFF" valign="middle" style="padding-left: 10px;">
						<span class="STYLE1">成绩录入</span>
					</td>
					<td height="32" align="right" bgcolor="#FFFFFF" valign="middle" style="padding-right: 10px;" width="30">
						<form:form name="adminLogoutForm" method="post" action="/exam/adminLogout.action" commandName="AdminLoginCommand">
							<a href="#" onclick="doLogout();">注销</a>
						</form:form>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center" height="448" style="padding-top: 30px;">
					<form:form name="scoreInputForm" method="post" action="/exam/scoreInput.action" commandName="ScoreInputCommand">
						<form:hidden path="submitFlag" id="submitFlag" />
						<table width="80%" border="0" cellpadding="4" cellspacing="1" bgcolor="#E1E1E1">
							<tr bgcolor="#ffffff">
								<td style="height: 10px; text-align: right; width: 20%;">身份证号：</td>
								<td style="height: 10px; text-align: left;">
									<form:input path="idCardNo" id="idCardNo" maxlength="18" size="18" cssClass="bbsInput_short" />
									<font color="#ff0000">
										*&nbsp;&nbsp;<form:errors path="idCardNo" />
									</font>
								</td>
							</tr>
							<tr bgcolor="#ffffff">
								<td style="height: 10px; text-align: right;">准考证号：</td>
								<td style="height: 10px; text-align: left;">
									<form:input path="admissionId" id="admissionId" maxlength="16" size="18" cssClass="bbsInput_short" />
									<font color="#ff0000">
										*&nbsp;&nbsp;<form:errors path="admissionId" />
									</font>
								</td>
							</tr>
							<tr bgcolor="#ffffff">
								<td style="height: 10px; text-align: right;">笔试成绩：</td>
								<td style="height: 10px; text-align: left;">
									<form:input path="score" id="score" maxlength="5" size="18" cssClass="bbsInput_short" />
									<font color="#ff0000">
										*&nbsp;&nbsp;<form:errors path="score" />
									</font>
								</td>
							</tr>
							<tr bgcolor="#ffffff">
								<td>&nbsp;</td>
								<td style="text-align: left;">
									<input type="button" value="登录" style="width: 50px;" onclick="doScoreInput(true);" />
									<input type="button" value="重置" style="width: 50px;" onclick="clear();" />
									<input type="button" value="取消" style="width: 50px;" onclick="doScoreInput(false);" />
								</td>
							</tr>
							<c:if test="${success == true}">
							<tr bgcolor="#ffffff">
								<td>&nbsp;</td>
								<td style="text-align: left;">
									登录成功，可以继续登录！
								</td>
							</tr>
							</c:if>
						</table>
					</form:form>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>