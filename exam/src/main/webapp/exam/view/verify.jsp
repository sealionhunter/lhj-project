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
<script type="text/javascript">
<!--
function submitVerify(obj) {
	document.getElementById('submitFlag').value = obj;
	document.getElementById('verifyForm').submit();
}
//-->
</script>
</head>
<body>
	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>
	<form:form id="verifyForm" name="verifyForm" method="post" action="/exam/verify.action"
		commandName="VerifyCommand">
		<form:hidden path="userId" />
		<div class="midBox">
			<div class="midTitle">报名审核</div>
			<div class="cnt">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">报名审核</span></b></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="200"
							style="padding-top: 20px;">
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td width="20%" align="right">身份证号：</td>
									<td width="40%" align="left">${VerifyCommand.user.idCardNo}</td>
									<td width="20%" align="right">姓名：</td>
									<td width="20%" align="left">${VerifyCommand.user.name}</td>
									<td rowspan="5"><img
										src="/exam/imageDownload?userId=${VerifyCommand.user.idCardNo}"
										height="140px" width="102px" /></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">性别：</td>
									<c:choose>
										<c:when test="${VerifyCommand.user.sex == '1'}">
											<td align="left">男</td>
										</c:when>
										<c:otherwise>
											<td align="left">女</td>
										</c:otherwise>
									</c:choose>
									<td align="right">民族：</td>
									<c:choose>
										<c:when test="${VerifyCommand.user.nationCode == '1'}">
											<td align="left">汉族</td>
										</c:when>
										<c:otherwise>
											<td align="left">少数民族</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">出生年月：</td>
									<td align="left">${VerifyCommand.user.birthdayYear}年${VerifyCommand.user.birthdayMonth}月</td>
									<td align="right">毕业时间：</td>
									<td align="left">${VerifyCommand.user.graduateYear}年${VerifyCommand.user.graduateMonth}月</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">籍贯：</td>
									<td align="left">${VerifyCommand.user.homeTown}</td>
									<td align="right">身份：</td>
									<c:choose>
										<c:when test="${VerifyCommand.user.identity == '1'}">
											<td align="left">应届毕业生</td>
										</c:when>
										<c:when test="${VerifyCommand.user.identity == '2'}">
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
										<c:when test='${VerifyCommand.user.married == "2"}'>
											<td align="left">未婚</td>
										</c:when>
										<c:otherwise>
											<td align="left">已婚</td>
										</c:otherwise>
									</c:choose>
									<td align="right">政治面貌：</td>
									<c:choose>
										<c:when test="${VerifyCommand.user.politicalCode == '1'}">
											<td align="left">共产党员</td>
										</c:when>
										<c:when test="${VerifyCommand.user.politicalCode == '2'}">
											<td align="left">共青团员</td>
										</c:when>
										<c:otherwise>
											<td align="left">群众</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">最高学历：</td>
									<td align="left">${VerifyCommand.user.degree}</td>
									<td align="right">工作年限：</td>
									<td align="left" colspan="2">${VerifyCommand.user.workyears}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">毕业院校：</td>
									<td align="left">${VerifyCommand.user.graduateSchool}</td>
									<td align="right">专业：</td>
									<td align="left" colspan="2">${VerifyCommand.user.major}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">计算机程度：</td>
									<td align="left">${VerifyCommand.user.computerSkill}</td>
									<td align="right">外语程度：</td>
									<td align="left" colspan="2">${VerifyCommand.user.languageSkill}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">身高：</td>
									<td align="left">${VerifyCommand.user.height}cm</td>
									<td align="right">联系电话</td>
									<td align="left" colspan="2">${VerifyCommand.user.telephone}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">报考部门：</td>
									<td align="left">${VerifyCommand.apply.applyDepartName}</td>
									<td align="right">岗位类别：</td>
									<td align="left" colspan="2">${VerifyCommand.apply.applyOfficeName}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">岗位编号：</td>
									<td align="left">${VerifyCommand.apply.applyOfficeCode}</td>
									<td align="right">考试所在地：</td>
									<td align="left" colspan="2">${VerifyCommand.apply.applyExamPosition}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">学习/培训经历：</td>
									<td align="left" colspan="4">${VerifyCommand.user.trainingExp}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">工作经历：</td>
									<td align="left" colspan="4">${VerifyCommand.user.workExp}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">社会关系：</td>
									<td align="left" colspan="4">${VerifyCommand.user.socialRel}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">审核：</td>
									<td align="left" colspan="4"><form:select
											path="verifyState">
											<form:option value="1">不通过</form:option>
											<form:option value="2">通过</form:option>
										</form:select></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">原因：</td>
									<td align="left" colspan="4">
										<form:textarea path="verifyReason" cols="50" rows="4" />
										<form:errors path="verifyReason" />
									</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="center" colspan="5">
										<form:hidden path="submitFlag" id="submitFlag" />
										<input type="button" value="确 定" style="width:50px;" onclick="submitVerify(true);" />&nbsp;&nbsp;&nbsp;
										<input type="button" value="返 回" style="width:50px;" onclick="submitVerify(false);"/>
									</td>
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