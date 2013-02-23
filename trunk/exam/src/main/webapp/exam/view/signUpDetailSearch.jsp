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
		if (office[i].departId == departId) {
			officeEl.options.add(new Option(office[i].name, office[i].id));
		}
	}
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
			<form:form name="personForm" method="post"
				action="/exam/signupdetailsearch.action"
				commandName="SignupDetailSearchCommand">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">报名人员信息检索</span></b></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="25"
							style="padding-top: 5px;">
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
									<td style="text-align: right; width: 10%;">籍贯：</td>
									<td style="text-align: left; width: 15%"><form:input
											path="homeTown" maxlength="32" size="15"
											cssClass="bbsInput_short" /></td>

									<td align="right"><font color="#ff0000">*</font>政治面貌：</td>
									<td align="left"><form:select path="politicalCode"
											items="${politicalCodes }" itemLabel="name"
											itemValue="id.code">
										</form:select></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="text-align: right;" colspan="4"><input
										type="submit" value="筛选" style="width: 50px;" /></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="448"
							style="padding-top: 5px;">
							<table width="95%" border="0" cellpadding="8" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#f7f7f7">
									<th style="width: 8%;">姓名</th>
									<th style="width: 10%;">身份证号</th>
									<th >籍贯</th>
									<th style="width: 8%;">政治面貌</th>
									<th style="width: 14%;">报考部门</th>
									<th style="width: 12%;">岗位类别</th>
									<th style="width: 8%;">岗位编号</th>
									<th style="width: 8%;">审核状态</th>
								</tr>
								<c:set var="totalCount" value="0" />
								<c:set var="totalPassed" value="0" />
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
											</c:when>
											<c:when test="${applyUser.state == 1 }">
												<td>审核不通过</td>
											</c:when>
											<c:otherwise>
												<td>未审核</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
<script type="text/javascript">
<!--
 changeDeart();
 if ('${SignupDetailSearchCommand.postId}' != '') {
	 document.getElementById("postId").value='${SignupDetailSearchCommand.postId}';
 }
//-->
</script>
</html>
