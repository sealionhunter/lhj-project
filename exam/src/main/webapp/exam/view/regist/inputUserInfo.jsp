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
</head>
<body>

	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>

	<div class="midBox">
		<div class="midTitle">网上报名</div>
		<div class="cnt">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="right" bgcolor="#FFFFFF" valign="middle"><b><span
							style="color: #666666"><span style="color: #ff3300">步骤：1.填写个人信息并设置密码</span>-2.开始报名-3.阅读报名须知并签订诚信承诺书-4.填写资格审查表-5.选择报考职位-6.上传照片-7.确认填写信息</span></b></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td valign="top" align="center" height="448"><span
						style="color: #666666; font-size: 14px; font-weight: 600"><br />
							1.填写个人信息并设置密码 </span><br /> <br />
						<table width="80%" border="0" cellpadding="4" cellspacing="1"
							bgcolor="#E1E1E1">
							<form:form name="registeForm" method="post"
								action="/exam/regist.action" commandName="RegistCommand"
								onsubmit="return CheckForm();">
								<c:if test="${RegistCommand.editFlg != '1' }">
									<tr bgcolor="#f7f7f7">
										<td width="21%" align="right">姓名：</td>
										<td align="left" width="79%"><form:input path="name"
												id="name" maxlength="32" size="30" cssClass="bbsInput_short" /><font
											color="#ff0000">*&nbsp;&nbsp;请务必填写正式姓名&nbsp;&nbsp;<form:errors
													path="name" /></font></td>
									</tr>
								</c:if>
								<tr bgcolor="#ffffff">
									<td align="right">身份证号：</td>
									<td align="left" width="79%"><form:input path="idCardNo"
											id="idCardNo" maxlength="18" size="18"
											cssClass="bbsInput_short" /><font color="#ff0000">*&nbsp;&nbsp;<form:errors
												path="idCardNoError" /></font></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right">密码：</td>
									<td align="left"><form:password path="password"
											id="password" maxlength="16" size="18"
											cssClass="bbsInput_short" /><font color="#ff0000">*&nbsp;&nbsp;<c:if test="${RegistCommand.editFlg != '1' }">请务必牢记&nbsp;&nbsp;</c:if><form:errors
												path="password" /></font></td>
								</tr>
								<c:if test="${RegistCommand.editFlg != '1' }">
									<tr bgcolor="#ffffff">
										<td align="right">重复密码：</td>
										<td align="left"><form:password path="rePassword"
												id="rePassword" maxlength="16" size="18"
												cssClass="bbsInput_short" /><font color="#ff0000">*&nbsp;&nbsp;<form:errors
													path="rePasswordError" /></font></td>
									</tr>
								</c:if>
								<tr bgcolor="#ffffff">
									<td height="22" colspan="4">
										<div align="center">
										<c:choose>
											<c:when test="${RegistCommand.editFlg != '1' }"><input type="submit" value="下一步" style="width: 50px;"
												name="_target1" /></c:when>
												<c:otherwise><input type="submit" value="下一步" style="width: 50px;"
												name="_target3" />
												</c:otherwise>
										</c:choose>&nbsp;&nbsp; <input type="reset"
												value="清  除" style="width: 50px;" /> &nbsp;&nbsp; <input
												type="submit" value="返  回" name="_cancel"
												style="width: 50px;" />
										</div>
									</td>
								</tr>
							</form:form>
						</table> <br />
					<br /></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
