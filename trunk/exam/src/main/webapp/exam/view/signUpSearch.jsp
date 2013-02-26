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
		<div class="midTitle">报考情况一览</div>
		<div class="cnt">
			<form:form name="registForm" method="post"
				action="/exam/applyinfo.action" commandName="RegistCommand">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">职位报考情况一览</span></b></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" 
							style="padding-top: 5px;padding-bottom: 5px;">
							<table width="98%" border="0" cellpadding="8" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#f7f7f7">
									<th style="width: 8%;">地区</th>
									<th style="width: 12%;">部门</th>
									<th style="width: 8%;">岗位编号</th>
									<th style="width: 12%;">岗位类别</th>
									<th style="width: 8%;">计划数</th>
									<th style="width: 8%;">报名人数</th>
									<th style="width: 12%;">通过审核人数</th>
									<th style="width: 12%;">通过审核比例</th>
									<th >专业要求</th>
									<%--
									<th style="width: 15%;">岗位要求</th>
									 --%>
								</tr>
							<c:set var="totalRecruits" value="0" />
							<c:set var="totalApplyCount" value="0" />
							<c:set var="totalValidataCount" value="0" />
								<c:forEach items="${RegistCommand.offices}" var="office">
								<c:set var="totalRecruits" value="${totalRecruits + office.recruits }" />
								<c:set var="totalApplyCount" value="${totalApplyCount + office.applyCount }" />
								<c:set var="totalValidataCount" value="${totalValidataCount + office.validataCount }" />
									<tr bgcolor="#ffffff">
										<td>${office.cityName}</td>
										<td>${office.departName}</td>
										<td>${office.code}</td>
										<td>${office.name}</td>
										<td>${office.recruits }</td>
										<td>${office.applyCount }</td>
										<td>${office.validataCount }</td>
										<td>1:${office.scale }</td>
										<td align="left">${office.major }</td>
										<%--
										<td align="left">${office.description }</td>
										 --%>
									</tr>
								</c:forEach>
									<tr bgcolor="#f7f7f7">
										<td colspan="4" style="text-align: right">总计:</td>
										<td>${totalRecruits}</td>
										<td>${totalApplyCount}</td>
										<td>${totalValidataCount}</td>
										<td colspan="4" ></td>
										<%--
										<td align="left">${office.description }</td>
										 --%>
									</tr>
							</table>
						</td>
					</tr>
				</table>
			</form:form>
		</div>

	</div>

</body>
</html>
