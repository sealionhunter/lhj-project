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
function goSubmit(type) {
	document.getElementById('submitType').value=type;
	admissionForm.submit();
}
</script>
</head>
<body>

	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>
	<form:form name="admissionForm" method="post"
		action="/exam/admissionPrint.action" commandName="AdmissionPrintCommand">
		<form:hidden path="submitType" id="submitType" />
		<div class="midBox">
			<div class="midTitle">准考证打印</div>
			<div class="cnt">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">准考证打印</span></b></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="140"
							style="padding-top: 20px;">
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right; width: 20%;">
										身份证号：</td>
									<td style="height: 10px; text-align: left;"><form:input
											path="idCardNo" id="idCardNo" cssClass="bbsInput_short" /> <font
										color="#ff0000"> *&nbsp;&nbsp;<form:errors
												path="idCardNo" />
									</font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right;">密码：</td>
									<td style="height: 10px; text-align: left;"><form:password
											path="password" id="password" maxlength="16" size="18"
											cssClass="bbsInput_short" /> <font color="#ff0000">*&nbsp;&nbsp;
											<form:errors path="password" />
									</font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td>&nbsp;</td>
									<td height="22">
										<div style="text-align: left;">
											<input type="button" value="检  索" style="width: 50px;"
												onclick="goSubmit('search');" />
										</div>
									</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right;">审核状态：</td>
									<c:choose>
										<c:when test="${AdmissionPrintCommand.apply.state == '0'}">
											<td style="height: 10px; text-align: left; color: #ff0000">
												未审核，请等待审核通过后再打印准考证。
											</td>
										</c:when>
										<c:when test="${AdmissionPrintCommand.apply.state == '1'}">
											<td style="height: 10px; text-align: left; color: #ff0000">
												审核未通过，请仔细核对岗位要求与自身资料</td>
										</c:when>
										<c:when test="${AdmissionPrintCommand.apply.state == '2'}">
											<td style="height: 10px; text-align: left; color: #ff0000">
												审核通过
												<input type="button" value="生成准考证" onclick="goSubmit('print');" />
											</td>
										</c:when>
										<c:otherwise>
											<td style="height: 10px; text-align: left; color: #ff0000">
												&nbsp;
											</td>
										</c:otherwise>
									</c:choose>
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
