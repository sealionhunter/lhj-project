<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
<!--
	function selectExam(id, name) {
		document.getElementById('registExamCode').value = id;
		document.getElementById('registExamName').value = name;
		// document.getElementById('registForm').submit();
		return true;
	}
//-->
</script>
</head>
<body>

	<form:form name="registForm" id="registForm" method="post"
		action="/exam/regist.action" commandName="RegistCommand">
		<form:hidden path="registExamCode" id="registExamCode" />
		<form:hidden path="registExamName" id="registExamName" />
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
								style="color: #666666">步骤：1.填写个人信息并设置密码-<span
									style="color: #ff3300">2.开始报名</span>-3.阅读报名须知并签订诚信承诺书-4.填写资格审查表-5.选择报考职位-6.上传照片-7.确认填写信息
							</span></b>&nbsp;&nbsp;</td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="140"><span
							style="color: #666666; font-size: 14px; font-weight: 600"><br />
								2.开始报名 </span><br /> <br />
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td width="21%" align="right">报考人姓名：</td>
									<td align="left" width="79%">${RegistCommand.name}</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">证件号码：</td>
									<td align="left" width="79%">${RegistCommand.idCardNo}</td>
								</tr>
							</table></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="300"
							style="padding-top: 15px;">
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<c:forEach items="${RegistCommand.exams }" var="exam">
									<tr bgcolor="#f7f7f7">
										<td width="50%" rowspan="2" align="center">${exam.name }</td>
										<td width="35%" align="center">报名时间</td>
										<td width="15%" rowspan="2" align="center"><input
											type="submit" value="开始报名"
											onclick="return selectExam('${exam.id }', '${exam.name }');"
											name="_target2" /></td>
									</tr>
									<tr bgcolor="#f7f7f7">
										<td width="35%" align="center"><fmt:formatDate
												pattern="yyyy/MM/dd HH:mm" value="${exam.applyBeginDate}" />
											~ <fmt:formatDate pattern="yyyy/MM/dd HH:mm"
												value="${exam.applyDeadDate }" /></td>
									</tr>
								</c:forEach>
								<tr bgcolor="#ffffff">
									<td height="45" colspan="5">
										<table border="0" cellpadding="0" cellspacing="0"
											align="center" style="text-align: left">
											<tr>
												<td height="24"><font color="#CC3300">特别提示： </font></td>
											</tr>
											<tr>
												<td height="24">1. 请考生在报名期间尽早报名，以便审核不通过及早改报</td>
											</tr>
											<tr>
												<td height="24">2. 为保证报名顺利进行，请及时报名，以防止后期网络拥堵，影响报名。</td>
											</tr>
										</table>
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
