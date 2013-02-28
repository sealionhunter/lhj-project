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
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center" height="25"
						style="padding-top: 5px;"><form:form name="roomListForm"
							method="post" action="/exam/roomList.action"
							commandName="RoomListCommand">
							<table width="85%" border="0" cellpadding="4" cellspacing="0"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;">部门：</td>
									<td style="text-align: left; width: 15%"><form:select
											path="departId" items="${departs}" id="departId" itemLabel="name"
											itemValue="id" onchange="changeDeart();">
										</form:select></td>
									<td style="text-align: right; width: 10%;">岗位类别：</td>
									<td style="text-align: left; width: 20%"><form:select
											path="officeId" id="officeId" /></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td style="text-align: right; width: 10%;"></td>
									<td style="text-align: center;" colspan="2"><input
										type="submit" value="查询" style="width: 50px;" />&nbsp;&nbsp;<input
										type="submit" value="座位分配/准考证号生成" style="width: 150px;" name="generateAdmission"/>&nbsp;&nbsp;<input
										type="button" value="返回" style="width: 50px;"
										onclick="javascript:document.location='/exam/adminInit.action'" /></td>
									<td style="text-align: right; width: 10%;"></td>
								</tr>
							</table>
						</form:form></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="2" valign="top" align="center"
						style="padding-top: 5px; padding-bottom: 5px;">
						<table width="85%" border="0" cellpadding="4" cellspacing="1"
							bgcolor="#E1E1E1">
							<tr bgcolor="#f7f7f7">
								<th rowspan="2" style="width: 14%;" nowrap="nowrap">报考部门</th>
								<th rowspan="2" style="width: 16%;" nowrap="nowrap">岗位类别</th>
								<th rowspan="2" style="width: 6%;" nowrap="nowrap">报考人数</th>
								<th nowrap="nowrap" colspan="4">考场信息</th>
								<th rowspan="2" style="width: 8%;" nowrap="nowrap"></th>
							</tr>
							<tr bgcolor="#f7f7f7">
								<th style="width: 6%;">编号</th>
								<th style="width: 6%;">座位数</th>
								<th style="width: 12%;" nowrap="nowrap">说明</th>
								<th>位置</th>
							</tr>
							<c:set var="totalCount" value="0" />
							<c:set var="totalPassed" value="0" />
							<c:set var="totalUnPassed" value="0" />
							<c:forEach items="${RoomListCommand.offices}" var="office">
								<c:set var="totalSeats" value="0" />
								<c:set var="roomCount" value="${fn:length(office.rooms)}" />
								<c:choose>
									<c:when test="${roomCount == 0 }">
										<tr bgcolor="#ffffff">
											<td>${office.departName}</td>
											<td>${office.name}(${office.code})</td>
											<td>${office.totalUsers}</td>
											<td colspan="4">座位总计：${totalSeats}席<c:if test="${totalSeats - office.totalUsers < 0 }">&nbsp;&nbsp;<font color="red">座位数不够，还差${office.totalUsers -totalSeats}席</font></c:if></td>
											<td><input type="button" value="追加" onclick="javascript:document.location='/exam/roomEdit.action?officeId=${office.id}&departId=${office.departId}'"/></td>
										</tr>
									</c:when>
									<c:when test="${roomCount >= 1 }">

										<c:forEach items="${office.rooms }" var="room"
											varStatus="status">
											<c:set var="totalSeats" value="${totalSeats + room.seats }" />
											<tr bgcolor="#ffffff">
												<c:if test="${status.index == 0 }">
													<td rowspan="${roomCount + 1 }">${office.departName}</td>
													<td rowspan="${roomCount + 1 }">${office.name}(${office.code})</td>
													<td rowspan="${roomCount + 1 }">${office.totalUsers}</td>
												</c:if>
												<td>${room.code }</td>
												<td>${room.seats }</td>
												<td>${room.name }</td>
												<td>${room.position }</td>
												<td nowrap="nowrap"><input type="button" value="修改" onclick="javascript:document.location='/exam/roomEdit.action?roomId=${room.id}'"/></td>
											</tr>
										</c:forEach>
										<tr bgcolor="#ffffff">
											<td colspan="4">座位总计：${totalSeats}席<c:if test="${totalSeats - office.totalUsers < 0 }">&nbsp;&nbsp;<font color="red">座位数不够，还差${office.totalUsers -totalSeats}席</font></c:if></td>
											<td><input type="button" value="追加" onclick="javascript:document.location='/exam/roomEdit.action?officeId=${office.id}&departId=${office.departId}'"/></td>
										</tr>
									</c:when>

								</c:choose>
							</c:forEach>
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
 if ('${RoomListCommand.officeId}' != '') {
	 document.getElementById("officeId").value='${RoomListCommand.officeId}';
 }
//-->
</script>
</html>
