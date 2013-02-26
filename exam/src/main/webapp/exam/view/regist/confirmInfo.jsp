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
</head>
<body>
	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>

	<div class="midBox">
		<div class="midTitle">网上报名</div>
		<div class="cnt">
			<form:form name="registForm" method="post"
				action="/exam/regist.action" commandName="RegistCommand">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="right" bgcolor="#FFFFFF" valign="middle"><b><span
								style="color: #666666">步骤：1.填写个人信息并设置密码-2.开始报名-3.阅读报名须知并签订诚信承诺书-4.填写资格审查表-5.选择报考职位-6.上传照片-<span
									style="color: #ff3300">7.确认填写信息</span></span></b>&nbsp;&nbsp;</td>
					</tr>

					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="300"><span
							style="color: #666666; font-size: 14px; font-weight: 600"><br />
								7.确认填写信息 </span><br />
						<br />
						<span style="color: #222222; font-size: 16px; font-weight: 600">${RegistCommand.registExamName}&nbsp;资格审查表</span>
							<br />报考序号:${RegistCommand.registExamCode}
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td width="20%" align="right">身份证号：</td>
									<td width="40%" align="left">${RegistCommand.idCardNo}</td>
									<td width="20%" align="right">姓名：</td>
									<td width="20%" align="left">${RegistCommand.name}</td>
									<td rowspan="5"><img
										src="/exam/imageDownload?userId=${RegistCommand.idCardNo}"
										height="140px" width="102px" /></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">性别：</td>
									<c:choose>
										<c:when test="${RegistCommand.sexCode == '1'}">
											<td align="left">男</td>
										</c:when>
										<c:otherwise>
											<td align="left">女</td>
										</c:otherwise>
									</c:choose>
									<td align="right">民族：</td>
									<c:choose>
										<c:when test="${RegistCommand.nationCode == '1'}">
											<td align="left">汉族</td>
										</c:when>
										<c:otherwise>
											<td align="left">少数民族</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">出生年月：</td>
									<td align="left">${RegistCommand.birthdayYear}年${RegistCommand.birthdayMonth}月</td>
									<td align="right">毕业时间：</td>
									<td align="left">${RegistCommand.graduateYear}年${RegistCommand.graduateMonth}月</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">籍贯：</td>
									<td align="left">${RegistCommand.homeTown}</td>
									<td align="right">身份：</td>
									<c:choose>
										<c:when test="${RegistCommand.identity == '1'}">
											<td align="left">应届毕业生</td>
										</c:when>
										<c:when test="${RegistCommand.identity == '2'}">
											<td align="left">无业人员</td>
										</c:when>
										<c:otherwise>
											<td align="left">在职人员</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">婚姻状况：</td>
									<c:if test='${RegistCommand.isMarried == "2"}'>
										<td align="left">未婚</td>
									</c:if>
									<c:if test='${RegistCommand.isMarried == "1"}'>
										<td align="left">已婚</td>
									</c:if>
									<td align="right">政治面貌：</td>
									<c:choose>
										<c:when test="${RegistCommand.politicalCode == '1'}">
											<td align="left">共产党员</td>
										</c:when>
										<c:when test="${RegistCommand.politicalCode == '2'}">
											<td align="left">共青团员</td>
										</c:when>
										<c:otherwise>
											<td align="left">群众</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">最高学历：</td>
									<td align="left">${RegistCommand.degree}</td>
									<td align="right">工作年限：</td>
									<td align="left" colspan="2">${RegistCommand.workYears}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">毕业院校：</td>
									<td align="left">${RegistCommand.userSchool}</td>
									<td align="right">专业：</td>
									<td align="left" colspan="2">${RegistCommand.userMajor}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">计算机程度：</td>
									<td align="left">${RegistCommand.computerSkill}</td>
									<td align="right">外语程度：</td>
									<td align="left" colspan="2">${RegistCommand.languageSkill}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">身高：</td>
									<td align="left">${RegistCommand.height}cm</td>
									<td align="right">联系电话：</td>
									<td align="left" colspan="2">${RegistCommand.telephone}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">报考部门：</td>
									<td align="left">${RegistCommand.applyDeptName}</td>
									<td align="right">岗位类别：</td>
									<td align="left" colspan="2">${RegistCommand.applyPostName}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">岗位编号：</td>
									<td align="left">${RegistCommand.applyPostCode}</td>
									<td align="right">考试所在地：</td>
									<td align="left" colspan="2">${RegistCommand.registExamLocation}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right" nowrap="nowrap">学习/培训经历：</td>
									<td align="left" colspan="4"><div style="width: 612px;overflow: auto"><pre>${RegistCommand.trainingExp}</pre></div></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">工作经历：</td>
									<td align="left" colspan="4"><div style="width: 612px;overflow: auto"><pre>${RegistCommand.workExp}</pre></div></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">社会关系：</td>
									<td align="left" colspan="4"><div style="width: 612px;overflow: auto"><pre>${RegistCommand.socialRel}</pre></div></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td height="22" colspan="5">
										<div align="center">
											<input type="submit" value="上一步" name="_target5" />
											&nbsp;&nbsp; <input type="submit" value="确认无误，退出等待审核"
												name="_finish" />
										</div>
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>
