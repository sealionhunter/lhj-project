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
	
<%-- 
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
--%>
	function generateR() {
		document.getElementById("actionType").value = 'generateRoom';
		return true; //confirm("如果以前分配过座位，点击确定将重新分配座位，生成新的准考证号。确定吗?");
	}
	function generateAd() {
		document.getElementById("actionType").value = 'generateAdmission';
		return confirm("如果以前分配过座位，点击确定将重新分配座位，生成新的准考证号。确定吗?");
	}

	function addR() {
		document.getElementById("actionType").value = 'addRoom';
		return true;
	}

	function editR(roomId) {
		document.getElementById("actionType").value = 'editRoom';
		document.getElementById("roomId").value = roomId;
		return true;
	}

	function deleteR(roomId) {
		if (!confirm("删除考场也会删除已分配给岗位的座位。确定吗?")) {
			return false;
		}
		document.getElementById("actionType").value = 'deleteRoom';
		document.getElementById("roomId").value = roomId;
		return true;
	}

	function removeA(departId, officeId, roomId, roomOfficeId) {

		if (!confirm("确定要取消考场的分配吗？")) {
			return false;
		}
		document.getElementById("actionType").value = 'removeAssign';
		document.getElementById("departId").value = departId;
		document.getElementById("officeId").value = officeId;
		document.getElementById("roomId").value = roomId;
		document.getElementById("roomOfficeId").value = roomOfficeId;
		return true;
	}

	function assignR(departId, officeId) {
		document.getElementById("actionType").value = 'assign';
		document.getElementById("departId").value = departId;
		document.getElementById("officeId").value = officeId;
		return true;
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
		<div class="midTitle">考场信息一览</div>
		<div class="cnt">

			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
						style="padding-left: 10px;"><b><span
							style="color: #666666">考场信息检索</span></b></td>
					<td height="32" align="right" bgcolor="#FFFFFF" valign="middle"
						style="padding-right: 10px;" width="30"><form:form
							name="adminLogoutForm" method="post"
							action="/exam/adminLogout.action" commandName="AdminLoginCommand">
							<a href="#" onclick="doLogout();">注销</a>
						</form:form></td>
				</tr><form:form name="roomListForm"
							method="post" action="/exam/roomList.action"
							commandName="RoomListCommand">
							<form:hidden path="officeId" />
							<form:hidden path="departId" />
							<form:hidden path="roomId" />
							<form:hidden path="roomOfficeId" />
							<form:hidden path="actionType" />
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center" height="25"
						style="padding-top: 5px;">
							<table width="95%" border="0" cellpadding="4" cellspacing="0"
								bgcolor="#E1E1E1">
								<%-- <tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;">部门：</td>
									<td style="text-align: left; width: 15%"><form:select
											path="departId" items="${departs}" id="departId"
											itemLabel="name" itemValue="id" onchange="changeDeart();">
										</form:select></td>
									<td style="text-align: right; width: 10%;">岗位类别：</td>
									<td style="text-align: left; width: 20%"><form:select
											path="officeId" id="officeId" /></td>
								</tr> --%>
								<tr bgcolor="#ffffff">
									<td></td>
									<td style="text-align: center;" colspan="2" nowrap="nowrap"><input
										type="submit" value="查询" style="width: 50px;"/><span id="gr">&nbsp;&nbsp;<input
										type="submit" value="考场自动生成" style="width: 100px;"
										name="generateRoom" onclick="return generateR();" />&nbsp;&nbsp;</span> <input
										type="submit" value="考场添加" style="width: 77px;"
										name="addRoom" onclick="return addR();" />&nbsp;&nbsp;<input
										type="submit" value="座位分配/准考证号生成" style="width: 150px;"
										name="generateAdmission" onclick="return generateAd();" />&nbsp;&nbsp;<input
										type="button" value="返回" style="width: 50px;"
										onclick="javascript:document.location='/exam/adminInit.action'" /></td>
									<td></td>
								</tr>
							</table></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center"
						style="padding-top: 5px; padding-bottom: 5px;">
						<table width="95%" border="0" cellpadding="4" cellspacing="1"
							bgcolor="#E1E1E1">
							<tr bgcolor="#f7f7f7">
								<th rowspan="2" style="width: 12%;" nowrap="nowrap">报考部门</th>
								<th rowspan="2" style="width: 14%;" nowrap="nowrap">岗位类别</th>
								<th rowspan="2" style="width: 6%;" nowrap="nowrap">报考人数</th>
								<th nowrap="nowrap" colspan="4">考场信息</th>
								<th rowspan="2" style="width: 7%;" nowrap="nowrap"></th>
							</tr>
							<tr bgcolor="#f7f7f7">
								<th style="width: 5%;">编号</th>
								<th style="width: 7%;">座位数</th>
								<th style="width: 9%;">分配座位数</th>
								<th style="width: 9%;">剩余座位数</th>
							</tr>
							<c:set var="totalCount" value="0" />
							<c:forEach items="${RoomListCommand.offices}" var="office">
								<c:set var="totalSeats" value="0" />
								<c:set var="totalAssignSeats" value="0" />
								<c:set var="roomCount" value="${fn:length(office.roomOffices)}" />
								<c:set var="totalCount" value="${totalCount + roomCount }" />
								<c:choose>
									<c:when test="${roomCount == 0 }">
										<tr bgcolor="#ffffff">
											<td>${office.departName}</td>
											<td>${office.name}(${office.code})</td>
											<td>${office.totalUsers}</td>
											<td colspan="4" nowrap="nowrap">已分配考场：${roomCount}间&nbsp;&nbsp;分配座位：${totalAssignSeats}席<c:if
													test="${totalAssignSeats - office.totalUsers < 0 }">&nbsp;&nbsp;<font
														color="red">座位数不够，还差${office.totalUsers
														-totalAssignSeats}席</font>
												</c:if></td>
											<td><input type="submit" value="分配考场" name="assign"
												onclick="return assignR('${office.departId }','${office.id }')" style="width: 77px;"/></td>
										</tr>
									</c:when>
									<c:when test="${roomCount >= 1 }">

										<c:forEach items="${office.roomOffices }" var="ro"
											varStatus="status">
											<c:set var="totalSeats" value="${totalSeats + ro.room.seats}" />
											<c:set var="totalAssignSeats"
												value="${totalAssignSeats + ro.assignSeats}" />
											<tr bgcolor="#ffffff">
												<c:if test="${status.index == 0 }">
													<td rowspan="${roomCount + 1 }">${office.departName}</td>
													<td rowspan="${roomCount + 1 }">${office.name}(${office.code})</td>
													<td rowspan="${roomCount + 1 }">${office.totalUsers}</td>
												</c:if>
												<td>${ro.room.code }</td>
												<td>${ro.room.seats }</td>
												<td><c:if test="${ro.assignSeats < ro.room.seats }"><font color="red"></c:if>${ro.assignSeats }<c:if test="${ro.assignSeats < ro.room.seats }"></font></c:if></td>
												<td><c:if test="${ro.room.remainSeats > 0}"><font color="green"></c:if>${ro.room.remainSeats }<c:if test="${ro.room.remainSeats > 0}"></font></c:if></td>
												<td nowrap="nowrap"><input type="submit" value="修改"
													name="editRoom" onclick="return editR('${ro.room.id}')" style="width: 50px;"/>&nbsp;<input
													type="submit" value="删除考场" name="deleteRoom"
													onclick="return deleteR('${ro.room.id}');"style="width: 77px;" />&nbsp;<input
													type="submit" value="取消分配" name="removeAssign"
													onclick="return removeA('${office.departId }','${office.id }','${ro.room.id}','${ro.id }')" style="width: 77px;"/></td>
											</tr>
										</c:forEach>
										<tr bgcolor="#ffffff">
											<td colspan="4" nowrap="nowrap">已分配考场：${roomCount}间&nbsp;&nbsp;分配座位：${totalAssignSeats}席<c:if
													test="${totalAssignSeats - office.totalUsers < 0 }">&nbsp;&nbsp;<font
														color="red">座位数不够，还差${office.totalUsers
														-totalAssignSeats}席</font>
												</c:if></td>
											<td><input type="submit" value="分配考场" name="assign"
												onclick="return assignR('${office.departId }','${office.id }')" style="width: 77px;"/></td>
										</tr>
									</c:when>

								</c:choose>
							</c:forEach>
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
<%-- 
 changeDeart();
 if ('${RoomListCommand.officeId}' != '') {
	 document.getElementById("officeId").value='${RoomListCommand.officeId}';
 }
 
--%>
<c:if test="${totalCount > 0}">
document.getElementById("gr").style.display='none';
</c:if>
//-->
</script>
--%>
</html>
