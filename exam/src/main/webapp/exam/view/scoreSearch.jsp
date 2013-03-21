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
		document.getElementById('submitType').value = type;
		scoreSearchForm.submit();
	}
</script>
</head>
<body>

	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>
	<form:form name="scoreSearchForm" method="post"
		action="/exam/scoreSearch.action" commandName="ScoreSearchCommand">
		<form:hidden path="submitType" id="submitType" />
		<div class="midBox">
			<div class="midTitle">笔试成绩</div>
			<div class="cnt">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">笔试成绩查询</span></b></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center"
							style="padding-top: 15px;padding-bottom: 15px">
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
											<input type="button" value="查  询" style="width: 50px;"
												onclick="goSubmit('search');" />
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<c:if test="${ScoreSearchCommand.showDetail}">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">笔试成绩查询结果</span></b></td>
					</tr>
						<tr bgcolor="#FFFFFF">
							<td valign="top" align="center"
								style="padding-top: 15px; padding-bottom: 10px;">
								<table width="80%" border="0" cellpadding="4" cellspacing="1"
									bgcolor="#E1E1E1">
									<tr bgcolor="#ffffff">
										<td width="20%" align="right">身份证号：</td>
										<td width="30%" align="left">${ScoreSearchCommand.idCardNo}</td>
										<td width="20%" align="right">姓名：</td>
										<td width="30%" align="left">${ScoreSearchCommand.user.name}</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">报考部门：</td>
										<td align="left">${ScoreSearchCommand.apply.applyDepartName}</td>
										<td align="right">岗位类别：</td>
										<td align="left">${ScoreSearchCommand.apply.applyOfficeName}(${ScoreSearchCommand.apply.applyOfficeCode})</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">准考证号：</td>
										<td align="left">${ScoreSearchCommand.apply.admission.code}</td>
										<td align="right">笔试成绩：</td>
										<td align="left">${ScoreSearchCommand.apply.admission.score}</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:if>
				</table>
			</div>
		</div>
	</form:form>
</body>
</html>
