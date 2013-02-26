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

function doVerify(obj) {
	document.getElementById('verifyUserId').value = obj;
	personForm.submit();
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
		<div class="midTitle">报名人员信息一览</div>
		<div class="cnt">

			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
						style="padding-left: 10px;"><b><span
							style="color: #666666">报名人员信息一览</span></b></td>
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
							method="post" action="/exam/signuppeopleinfo.action"
							commandName="SignUpPersonSearchCommand">
							<form:hidden path="verifyUserId" id="verifyUserId" />
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
									<td style="text-align: right; width: 10%;">审核状态：</td>
									<td style="text-align: left; width: 15%"><form:select
											path="state" id="state">
											<form:option value="-1" label=""></form:option>
											<form:option value="0">未审核</form:option>
											<form:option value="1">审核不通过</form:option>
											<form:option value="2">审核通过</form:option>
										</form:select></td>
									<td style="text-align: right; width: 10%;">性别：</td>
									<td style="text-align: left;"><form:select path="sex"
											id="sex">
											<form:option value="-1" label=""></form:option>
											<form:option value="1">男</form:option>
											<form:option value="2">女</form:option>
										</form:select></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;"></td>
									<td style="text-align: left; width: 15%"></td>
									<td style="text-align: right; width: 10%;"></td>
									<td style="text-align: left;"><input type="submit"
										value="筛选" style="width: 50px;" />&nbsp;&nbsp;<input
										type="button" value="返回" style="width: 50px;"
										onclick="javascript:document.location='/exam/adminInit.action'" /></td>
								</tr>
							</table>
						</form:form></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center" height="448"
						style="padding-top: 5px;">
						<table width="95%" border="0" cellpadding="8" cellspacing="1"
							bgcolor="#E1E1E1">
							<tr bgcolor="#f7f7f7">
								<th colspan="9" style="text-align: right; width: 10%;">总计：<span
									id="totalCount">${totalCount}</span>人&nbsp;&nbsp;未审核：<span
									id="totalUnVerify">${totalCount - totalPassed -
										totalUnPassed }</span>人&nbsp;&nbsp;<font color="green">审核通过：<span id="totalPassed">${totalPassed
										}</span>人</font>&nbsp;&nbsp;<font color="red">审核未通过：<span id="totalUnPassed">${totalUnPassed
										}</span>人</font>
								</th>
							</tr>
							<tr bgcolor="#f7f7f7">
								<th style="width: 8%;">姓名</th>
								<th style="width: 10%;">身份证号</th>
								<th style="width: 12%;">籍贯</th>
								<th style="width: 8%;">政治面貌</th>
								<th style="width: 14%;">报考部门</th>
								<th style="width: 12%;">岗位类别</th>
								<th style="width: 8%;">岗位编号</th>
								<th style="width: 10%;">审核状态</th>
								<th>&nbsp;</th>
							</tr>
							<c:set var="totalCount" value="0" />
							<c:set var="totalPassed" value="0" />
							<c:set var="totalUnPassed" value="0" />
							<c:forEach items="${applyUsers}" var="applyUser">
								<c:set var="totalCount" value="${totalCount + 1 }" />
								<tr bgcolor="#ffffff">
									<td>${applyUser.applyUserName}</td>
									<td>${applyUser.idCardNo}</td>
									<td>${applyUser.applyUserHomeTown}</td>
									<c:choose>
										<c:when test="${applyUser.aplyUserPolitical == '1'}">
											<td>共产党员</td>
										</c:when>
										<c:when test="${applyUser.aplyUserPolitical == '2'}">
											<td>共青团员</td>
										</c:when>
										<c:otherwise>
											<td>群众</td>
										</c:otherwise>
									</c:choose>
									<td>${applyUser.applyDepartName}</td>
									<td>${applyUser.applyOfficeName}</td>
									<td>${applyUser.applyOfficeCode}</td>
									<c:choose>
										<c:when test="${applyUser.state == 2 }">
											<c:set var="totalPassed" value="${totalPassed + 1 }" />
											<td>审核通过</td>
											<td><input type="button" value="详细" style="width: 50px;"
												onclick="doVerify(${applyUser.id.userid})" /></td>
										</c:when>
										<c:when test="${applyUser.state == 1 }">
											<c:set var="totalUnPassed" value="${totalUnPassed + 1 }" />
											<td>审核不通过</td>
											<td><input type="button" value="详细" style="width: 50px;"
												onclick="doVerify(${applyUser.id.userid})" /></td>
										</c:when>
										<c:otherwise>
											<td>未审核</td>
											<td><input type="button" value="审核" style="width: 50px;"
												onclick="doVerify(${applyUser.id.userid})" /></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
							<tr bgcolor="#f7f7f7">
								<th colspan="9" style="text-align: right; width: 10%;">总计：${totalCount}人&nbsp;&nbsp;未审核：${totalCount
									- totalPassed - totalUnPassed};&nbsp;&nbsp;<font color="green">审核通过：${totalPassed
									}人</font>&nbsp;&nbsp;<font color="red">审核未通过：${totalUnPassed}人</font></th>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
<!--
 changeDeart();
 if ('${SignUpPersonSearchCommand.postId}' != '') {
	 document.getElementById("postId").value='${SignUpPersonSearchCommand.postId}';
 }
 document.getElementById("totalCount").innerText='${totalCount}';
 document.getElementById("totalPassed").innerText='${totalPassed}';
 document.getElementById("totalUnPassed").innerText='${totalUnPassed}';
 document.getElementById("totalUnVerify").innerText='${totalCount - totalPassed - totalUnPassed}';
//-->
</script>
</html>