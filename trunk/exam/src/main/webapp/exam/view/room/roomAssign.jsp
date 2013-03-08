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
	
var office = new Array();
<c:forEach items="${offices}" var="office" varStatus="status">
	office[${status.index }] = new Object();
    office[${status.index }].id = '${office.id}';
    <c:choose>
        <c:when test='${office.name == ""}'>
            office[${status.index }].name = '';
        </c:when>
        <c:otherwise>
            office[${status.index }].name = '${office.name}(${office.code})--(${office.totalUsers - office.totalSeats })';
        </c:otherwise>
    </c:choose>
    office[${status.index }].departId = '${office.departId}';
</c:forEach>

function changeDeart() {
	var departId = document.getElementById("departId").value;
	var officeEl = document.getElementById("officeId");
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
		<div class="midTitle">岗位考场分配</div>
		<div class="cnt">

			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
						style="padding-left: 10px;"><b><span
							style="color: #666666">岗位考场分配</span></b></td>
					<td height="32" align="right" bgcolor="#FFFFFF" valign="middle"
						style="padding-right: 10px;" width="30"><form:form
							name="adminLogoutForm" method="post"
							action="/exam/adminLogout.action" commandName="AdminLoginCommand">
							<a href="#" onclick="doLogout();">注销</a>
						</form:form></td>
				</tr>
				<form:form name="roomListForm" method="post"
					action="/exam/roomAssign.action" commandName="RoomAssignCommand">
					<tr bgcolor="#FFFFFF">
						<td colspan="2" valign="top" align="center" height="25"
							style="padding-top: 5px;">
							<table width="95%" border="0" cellpadding="4" cellspacing="0"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;">部门：</td>
									<td style="text-align: left; width: 15%"><form:select
											path="departId" items="${departs}" id="departId"
											itemLabel="name" itemValue="id" onchange="changeDeart();">
										</form:select></td>
									<td style="text-align: right; width: 10%;">岗位类别：</td>
									<td style="text-align: left; width: 30%"><form:select
											path="officeId" id="officeId" /></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;">考场：</td>
									<td style="text-align: left; width: 15%"><form:select
											path="roomId" id="roomId">
											<c:forEach items="${rooms }" var="r">
												<c:if test="${r.remainSeats > 0 }">
												<form:option value="${r.id }" label="${r.code }--${r.seats }--${r.remainSeats }"></form:option>
												</c:if>
											</c:forEach>
										</form:select><font color="#ff0000">&nbsp;&nbsp;<form:errors
												path="code" /></font></td>
									<td style="text-align: right; width: 10%;">分配座位数：</td>
									<td style="text-align: left; width: 30%"><form:input
											path="seats" id="seats" /><font color="#ff0000">&nbsp;&nbsp;<form:errors
												path="seats" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td></td>
									<td style="text-align: center;" colspan="2" nowrap="nowrap"><input
										type="submit" value="确定" style="width: 50px;" />&nbsp;&nbsp;<input
										type="button" value="返回" style="width: 50px;"
										onclick="javascript:document.location='/exam/roomList.action'" /></td>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
				</form:form>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
<!--
 changeDeart();
 if ('${RoomAssignCommand.officeId}' != '') {
	 document.getElementById("officeId").value='${RoomAssignCommand.officeId}';
 }
//-->
</script>
</html>
