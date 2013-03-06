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
var room = new Array();

<c:forEach items="${UserSeatResetCommand.rooms}" var="room" varStatus="status">
    room[${status.index }] = new Object();
    room[${status.index }].id = '${room.id}';
    room[${status.index }].code = '${room.code}';
</c:forEach>

function changeRoom() {
	var roomId = document.getElementById("roomId").value;
	var i = 0;
	for (; i < room.length; i++) {
		if (room[i].id == roomId) {
			document.getElementById("roomCode").value = room[i].code;
			break;
		}
	}
}

function doLogout() {
	adminLogoutForm.submit();
}

// -->
</script>
</head>
<body>
	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>
	<form:form name="userSeatResetForm" method="post"
		action="/exam/userSeatReset.action" commandName="UserSeatResetCommand">
		<form:hidden path="userId" />
		<form:hidden path="roomCode" />
		<div class="midBox">
			<div class="midTitle">重置考生座位</div>
			<div class="cnt">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">考生座位信息</span></b></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center"
							style="padding-top: 20px;">
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#ffffff">
									<td style="height: 10px; text-align: right; width: 20%;">
										身份证号：</td>
									<td style="height: 10px; text-align: left;"><form:input
											path="idCardNo" id="idCardNo" cssClass="bbsInput_short" /> <font
										color="#ff0000"> *&nbsp;&nbsp;<form:errors
												path="idCardNo" />
									</font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td>&nbsp;</td>
									<td height="22">
										<div style="text-align: left;">
											<input type="submit" value="检  索" style="width: 50px;"
												name="search" /> <input type="button" value="返  回"
												style="width: 50px;"
												onclick="javascript:document.location='/exam/adminInit.action'" />
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<c:if test="${UserSeatResetCommand.showDetail}">
					<form:hidden path="seatId" />
						<tr>
							<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
								style="padding-left: 10px;"><b><span
									style="color: #666666">座位详细信息</span></b></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td valign="top" align="center"
								style="padding-top: 20px; padding-bottom: 10px;">
								<table width="80%" border="0" cellpadding="4" cellspacing="1"
									bgcolor="#E1E1E1">
									<tr bgcolor="#ffffff">
										<td width="20%" align="right">姓名：</td>
										<td width="30%" align="left">${UserSeatResetCommand.user.name}</td>
										<td width="20%" align="right">身份证号：</td>
										<td width="30%" align="left">${UserSeatResetCommand.user.idCardNo}</td>
										<%--<td rowspan="5"><img
											src="/exam/imageDownload?userId=${UserSeatResetCommand.idCardNo}"
											height="140px" width="102px" /></td> --%>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">报考部门：</td>
										<td align="left">${UserSeatResetCommand.apply.applyDepartName}</td>
										<td align="right">岗位类别：</td>
										<td align="left">${UserSeatResetCommand.apply.applyOfficeName}(${UserSeatResetCommand.apply.applyOfficeCode})</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">审核状态：</td>
										<td align="left"><c:choose>
												<c:when test="${UserSeatResetCommand.apply.state == '1'}">
												审核未通过
											</c:when>
												<c:when test="${UserSeatResetCommand.apply.state == '2'}">
												审核通过
											</c:when>
												<c:otherwise>
												未审核
											</c:otherwise>
											</c:choose></td>
										<td align="right">准考证号：</td>
										<td align="left">${UserSeatResetCommand.admission.code}</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="right">考场编号：</td>
										<td align="left">${UserSeatResetCommand.seat.room.code}</td>
										<td align="right">座位号：</td>
										<td align="left">${UserSeatResetCommand.seat.code}</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
								style="padding-left: 10px;"><b><span
									style="color: #666666">重置考生座位</span></b></td>
						</tr>
						<c:if test="${fn:length(UserSeatResetCommand.rooms) > 0 }">
						<tr bgcolor="#FFFFFF">
							<td valign="top" align="center" height="200"
								style="padding-top: 5px; padding-bottom: 5px;">
								<table width="80%" border="0" cellpadding="4" cellspacing="1"
									bgcolor="#E1E1E1">
									<tr bgcolor="#ffffff">
										<td width="20%" align="right">考场编号：</td>
										<td width="30%" align="left"><form:select path="roomId"
												id="roomId" onchange="changeRoom();">
													<form:option value="-1" label=""></form:option>
												<c:forEach items="${UserSeatResetCommand.rooms}" var="room">
													<form:option value="${room.id }"
														label="${room.code }" />
												</c:forEach>
											</form:select></td>
										<td width="20%" align="right">座位号：</td>
										<td width="30%" align="left"><form:select path="seatCode"
												id="seatCode">
													<option value=""></option>
												<c:forEach begin="1" end="30" var="code">
												<fmt:formatNumber var="strCode" minIntegerDigits="2" maxIntegerDigits="2" maxFractionDigits="0" value="${code }" />
													<form:option value="${strCode}" label="${strCode}"></form:option>
												</c:forEach>
												</form:select></td>
									</tr>
									<tr bgcolor="#ffffff">
										<td style="height: 10px; text-align: center;" colspan="4">
											<input type="submit" value="重置" name="reset"
											style="width: 50px;" />
										</td>
									</tr>
								</table>
							</td>
						</tr></c:if>
						</tr>
					</c:if>
				</table>
			</div>
		</div>
	</form:form>
</body>

</html>