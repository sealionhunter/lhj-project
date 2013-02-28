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
	userPasswordResetForm.submit();
}
<c:if test="${alert == true}">
alert('考生密码重置成功！');
</c:if>
</script>
</head>
<body>
	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>
	<form:form name="userPasswordResetForm" method="post"
		action="/exam/userPasswordReset.action"
		commandName="UserPasswordResetCommand">
		<form:hidden path="submitType" />
		<div class="midBox">
			<div class="midTitle">重置考生密码</div>
			<div class="cnt">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">重置考生密码</span></b></td>
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
									<td>&nbsp;</td>
									<td height="22">
										<div style="text-align: left;">
											<input type="button" value="检  索" style="width: 50px;" onclick="goSubmit('search');" />
											<c:if test="${UserPasswordResetCommand.showDetail}">
												<input type="button" value="重置密码" style="width: 70px;" onclick="goSubmit('reset');" />
											</c:if>
											<input type="button" value="返  回" style="width: 50px;" onclick="goSubmit('back');" />
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<c:if test="${UserPasswordResetCommand.showDetail}">
						<tr>
							<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
								style="padding-left: 10px;"><b><span
									style="color: #666666">详细信息</span></b></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td valign="top" align="center" height="200"
								style="padding-top: 20px; padding-bottom: 10px;">
								<table width="80%" border="0" cellpadding="4" cellspacing="1"
									bgcolor="#E1E1E1">
									<tr bgcolor="#ffffff">
										<td width="20%" align="right">身份证号：</td>
										<td width="40%" align="left">${UserPasswordResetCommand.idCardNo}</td>
										<td width="20%" align="right">姓名：</td>
										<td width="20%" align="left">${UserPasswordResetCommand.user.name}</td>
										<td rowspan="5"><img
											src="/exam/imageDownload?userId=${UserPasswordResetCommand.idCardNo}"
											height="140px" width="102px" /></td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">性别：</td>
										<c:choose>
											<c:when test="${UserPasswordResetCommand.user.sex == '1'}">
												<td align="left">男</td>
											</c:when>
											<c:otherwise>
												<td align="left">女</td>
											</c:otherwise>
										</c:choose>
										<td align="right">民族：</td>
										<c:choose>
											<c:when test="${UserPasswordResetCommand.user.nationCode == '1'}">
												<td align="left">汉族</td>
											</c:when>
											<c:otherwise>
												<td align="left">少数民族</td>
											</c:otherwise>
										</c:choose>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">出生年月：</td>
										<td align="left">${UserPasswordResetCommand.user.birthdayYear}年${UserPasswordResetCommand.user.birthdayMonth}月</td>
										<td align="right">毕业时间：</td>
										<td align="left">${UserPasswordResetCommand.user.graduateYear}年${UserPasswordResetCommand.user.graduateMonth}月</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">籍贯：</td>
										<td align="left">${UserPasswordResetCommand.user.homeTown}</td>
										<td align="right">身份：</td>
										<c:choose>
											<c:when test="${UserPasswordResetCommand.user.identity == '1'}">
												<td align="left">应届毕业生</td>
											</c:when>
											<c:when test="${UserPasswordResetCommand.user.identity == '2'}">
												<td align="left">无业人员</td>
											</c:when>
											<c:otherwise>
												<td align="left">在职人员</td>
											</c:otherwise>
										</c:choose>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">婚姻状况：</td>
										<c:choose>
											<c:when test='${UserPasswordResetCommand.user.married == "2"}'>
												<td align="left">未婚</td>
											</c:when>
											<c:otherwise>
												<td align="left">已婚</td>
											</c:otherwise>
										</c:choose>
										<td align="right">政治面貌：</td>
										<c:choose>
											<c:when
												test="${UserPasswordResetCommand.user.politicalCode == '1'}">
												<td align="left">共产党员</td>
											</c:when>
											<c:when
												test="${UserPasswordResetCommand.user.politicalCode == '2'}">
												<td align="left">共青团员</td>
											</c:when>
											<c:otherwise>
												<td align="left">群众</td>
											</c:otherwise>
										</c:choose>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">最高学历：</td>
										<td align="left">${UserPasswordResetCommand.user.degree}</td>
										<td align="right">工作年限：</td>
										<td align="left" colspan="2">${UserPasswordResetCommand.user.workyears}</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">毕业院校：</td>
										<td align="left">${UserPasswordResetCommand.user.graduateSchool}</td>
										<td align="right">专业：</td>
										<td align="left" colspan="2">${UserPasswordResetCommand.user.major}</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">计算机程度：</td>
										<td align="left">${UserPasswordResetCommand.user.computerSkill}</td>
										<td align="right">外语程度：</td>
										<td align="left" colspan="2">${UserPasswordResetCommand.user.languageSkill}</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">身高：</td>
										<td align="left">${UserPasswordResetCommand.user.height}cm</td>
										<td align="right">联系电话：</td>
										<td align="left" colspan="2">${UserPasswordResetCommand.user.telephone}</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">报考部门：</td>
										<td align="left">${UserPasswordResetCommand.apply.applyDepartName}</td>
										<td align="right">岗位类别：</td>
										<td align="left" colspan="2">${UserPasswordResetCommand.apply.applyOfficeName}</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">岗位编号：</td>
										<td align="left">${UserPasswordResetCommand.apply.applyOfficeCode}</td>
										<td align="right">考试所在地：</td>
										<td align="left" colspan="2">${UserPasswordResetCommand.apply.applyExamPosition}</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right" nowrap="nowrap">学习/培训经历：</td>
										<td align="left" colspan="4"><div
												style="width: 612px; overflow: auto">
												<pre>${UserPasswordResetCommand.user.trainingExp}</pre>
											</div></td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">工作经历：</td>
										<td align="left" colspan="4"><div
												style="width: 612px; overflow: auto">
												<pre>${UserPasswordResetCommand.user.workExp}</pre>
											</div></td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">社会关系：</td>
										<td align="left" colspan="4"><div
												style="width: 612px; overflow: auto">
												<pre>${UserPasswordResetCommand.user.socialRel}</pre>
											</div></td>
									</tr>
									<tr bgcolor="#ffffff">
										<td style="height: 10px; text-align: right;">审核状态：</td>
										<td style="height: 10px; text-align: left; color: #ff0000"
											colspan="4"><b> <c:choose>
													<c:when test="${UserPasswordResetCommand.apply.state == '0'}">
												未审核
											</c:when>
													<c:when test="${UserPasswordResetCommand.apply.state == '1'}">
												审核未通过
											</c:when>
													<c:when test="${UserPasswordResetCommand.apply.state == '2'}">
												审核通过
											</c:when>
													<c:otherwise>
												&nbsp;
											</c:otherwise>
												</c:choose>
										</b></td>
									</tr>
									<tr bgcolor="#ffffff">
										<td style="height: 10px; text-align: right;">原因：</td>
										<td style="height: 10px; text-align: left;" colspan="4"><pre>${UserPasswordResetCommand.apply.reason}</pre></td>
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