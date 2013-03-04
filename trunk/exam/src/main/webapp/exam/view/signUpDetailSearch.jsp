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
var office = new Array();
<c:forEach items="${offices}" var="office" varStatus="status">
	office[${status.index }] = new Object();
    office[${status.index }].id = '${office.id}';
    <c:choose>
        <c:when test='${office.name == ""}'>
            office[${status.index }].name = '';
        </c:when>
        <c:otherwise>
            office[${status.index }].name = '${office.name}' + '(' +  '${office.code}' + ')';
        </c:otherwise>
    </c:choose>
    office[${status.index }].departId = '${office.departId}';
</c:forEach>

function changeDeart() {
	var departId = document.getElementById("deptId").value;
	var officeEl = document.getElementById("postId");
	officeEl.options.length=0;
	var i = 0;
	for (; i < office.length; i++) {
		if (departId == -1) {
			officeEl.options.add(new Option(office[i].name, office[i].id));
			continue;
		}
		if (office[i].departId == departId || office[i].id == -1) { 
			officeEl.options.add(new Option(office[i].name, office[i].id));
		}
	}
}

function doLogout() {
	adminLogoutForm.submit();
}

//-->
</script>
</head>
<body>
	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>

	<div class="midBox">
		<div class="midTitle">报名人员信息检索</div>
		<div class="cnt">

			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
						style="padding-left: 10px;"><b><span
							style="color: #666666">报名人员信息检索</span></b></td>
					<td height="32" align="right" bgcolor="#FFFFFF" valign="middle"
						style="padding-right: 10px;" width="30"><form:form
							name="adminLogoutForm" method="post"
							action="/exam/adminLogout.action" commandName="AdminLoginCommand">
							<a href="#" onclick="doLogout();">注销</a>
						</form:form></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center" height="25"
						style="padding-top: 5px;"><form:form name="personForm"
							method="post" action="/exam/signupdetailsearch.action"
							commandName="SignupDetailSearchCommand">
							<table width="95%" border="0" cellpadding="4" cellspacing="0"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;">部门：</td>
									<td style="text-align: left; width: 15%"><form:select
											path="deptId" items="${departs}" id="deptId" itemLabel="name"
											itemValue="id" onchange="changeDeart();">
										</form:select></td>
									<td style="text-align: right; width: 10%;">岗位类别：</td>
									<td style="text-align: left; width: 20%""><form:select
											path="postId" id="postId" /></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;">姓名：</td>
									<td style="text-align: left; width: 15%"><form:input
											path="name" maxlength="32" size="15"
											cssClass="bbsInput_short" /></td>
									<td style="text-align: right; width: 10%;">性别：</td>
									<td style="text-align: left; width: 20%""><form:select
											path="sex" id="sex">
											<form:option value="-1" label=""></form:option>
											<form:option value="1">男</form:option>
											<form:option value="2">女</form:option>
										</form:select></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;">年龄：</td>
									<td style="text-align: left; width: 20%""><form:select
											path="age" id="age">
											<form:option value="-1" label=""></form:option>
											<form:option value="0">18岁以下</form:option>
											<form:option value="1">18~25岁</form:option>
											<form:option value="2">25~30岁</form:option>
											<form:option value="3">30~35岁</form:option>
											<form:option value="4">35以上岁</form:option>
										</form:select></td>
									<td style="text-align: right; width: 10%;">学历：</td>
									<td style="text-align: left; width: 15%"><form:input
											path="degree" maxlength="32" size="15"
											cssClass="bbsInput_short" /></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;">籍贯：</td>
									<td style="text-align: left; width: 15%"><form:input
											path="homeTown" maxlength="32" size="15"
											cssClass="bbsInput_short" /></td>

									<td style="text-align: right; width: 10%;">审核状态：</td>
									<td style="text-align: left; width: 15%"><form:select
											path="state" id="state">
											<form:option value="-1" label=""></form:option>
											<form:option value="0">未审核</form:option>
											<form:option value="1">审核不通过</form:option>
											<form:option value="2">审核通过</form:option>
										</form:select></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;">身份：</td>
									<td style="text-align: left; width: 15%"><form:select
											path="identity" items="${identities }" itemLabel="name"
											itemValue="id.code">
										</form:select></td>

									<td style="text-align: right; width: 10%;"></td>
									<td style="text-align: left; width: 15%"><input
										type="submit" value="筛选" style="width: 50px;" />&nbsp;&nbsp;<input
										type="submit" value="导出" style="width: 50px;"
										name="excelExport" />&nbsp;&nbsp;<input type="button"
										value="返回" style="width: 50px;"
										onclick="javascript:document.location='/exam/adminInit.action'" /></td>
									</td>
								</tr>
							</table>
						</form:form></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center"
						style="padding-top: 5px; padding-bottom: 5px;">
						<table width="99%" border="0" cellpadding="4" cellspacing="1"
							bgcolor="#E1E1E1">
							<tr bgcolor="#f7f7f7">
								<th colspan="11" style="text-align: right; width: 10%;">总计：<span
									id="totalCount">${totalCount}</span>人&nbsp;&nbsp;未审核：<span
									id="totalUnVerify">${totalCount - totalPassed -
										totalUnPassed }</span>人&nbsp;&nbsp;<font color="green">审核通过：<span
										id="totalPassed">${totalPassed }</span>人
								</font>&nbsp;&nbsp;<font color="red">审核不通过：<span
										id="totalUnPassed">${totalUnPassed }</span>人
								</font>
								</th>
							</tr>
							<tr bgcolor="#f7f7f7">
								<th style="width: 7%;" nowrap="nowrap">姓名</th>
								<th style="width: 10%;" nowrap="nowrap">身份证号</th>
								<th style="width: 5%;" nowrap="nowrap">性别</th>
								<th style="width: 7%;" nowrap="nowrap">出生年月</th>
								<th>毕业院校</th>
								<th style="width: 11%;">专业</th>
								<th style="width: 7%;" nowrap="nowrap">毕业时间</th>
								<th style="width: 8%;">学历</th>
								<th style="width: 11%;">报考部门</th>
								<th style="width: 10%;">岗位类别</th>
								<th style="width: 8%;" nowrap="nowrap">审核状态</th>
							</tr>
							<c:set var="totalCount" value="0" />
							<c:set var="totalPassed" value="0" />
							<c:set var="totalUnPassed" value="0" />
							<c:forEach items="${applyUsers}" var="applyUser">
								<c:set var="totalCount" value="${totalCount + 1 }" />
								<tr bgcolor="#ffffff">
									<td>${applyUser.applyUserName}</td>
									<td>${applyUser.idCardNo}</td>
									<td style="text-align: center"><c:choose>
											<c:when test="${applyUser.user.sex == '1'}">男</c:when>
											<c:otherwise>女</c:otherwise>
										</c:choose></td>
									<td style="text-align: center">${applyUser.user.birthdayYear}/<fmt:formatNumber
											pattern="#00">${applyUser.user.birthdayMonth
									}</fmt:formatNumber></td>
									<td>${applyUser.user.graduateSchool}</td>
									<td>${applyUser.user.major}</td>
									<td style="text-align: center">${applyUser.user.graduateYear}/<fmt:formatNumber
											pattern="#00">${applyUser.user.graduateMonth
									}</fmt:formatNumber></td>
									<td>${applyUser.user.degree}</td>
									<td>${applyUser.applyDepartName}</td>
									<td>${applyUser.applyOfficeName}(${applyUser.applyOfficeCode})</td>
									<c:choose>
										<c:when test="${applyUser.state == 2 }">
											<c:set var="totalPassed" value="${totalPassed + 1 }" />
											<td style="text-align: center">审核通过</td>
										</c:when>
										<c:when test="${applyUser.state == 1 }">
											<c:set var="totalUnPassed" value="${totalUnPassed + 1 }" />
											<td style="text-align: center">审核不通过</td>
										</c:when>
										<c:otherwise>
											<td style="text-align: center">未审核</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
							<tr bgcolor="#f7f7f7">
								<th colspan="11" style="text-align: right; width: 10%;">总计：${totalCount}人&nbsp;&nbsp;未审核：${totalCount
									- totalPassed - totalUnPassed};&nbsp;&nbsp;<font color="green">审核通过：${totalPassed
										}人</font>&nbsp;&nbsp;<font color="red">审核不通过：${totalUnPassed}人</font>
								</th>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<c:if test="${downloadFile != null && downloadFile != '' }">
		<iframe id="downloadForm" name="downloadForm" style="display: none"
			src="/exam/excelExport?id=${downloadFile}"></iframe>
	</c:if>
</body>
<script type="text/javascript">
<!--
 changeDeart();
 if ('${SignupDetailSearchCommand.postId}' != '') {
	 document.getElementById("postId").value='${SignupDetailSearchCommand.postId}';
 }
 document.getElementById("totalCount").innerText='${totalCount}';
 document.getElementById("totalPassed").innerText='${totalPassed}';
 document.getElementById("totalUnPassed").innerText='${totalUnPassed}';
 document.getElementById("totalUnVerify").innerText='${totalCount - totalPassed - totalUnPassed}';
//-->
</script>
</html>
