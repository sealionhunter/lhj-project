<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/style.css" />
<link rel="stylesheet" type="text/css" href="style/list.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/myhotel.js"></script>
<title>订餐系统管理--餐厅信息</title>
<script type="text/javascript">

$(document).ready(function() {
	$("#userEditForm").submit(function() {
		var uid = $("#data\\.uid").val();
		if (uid == '' || uid.length == 0) {
			alert("请输入用户名！");
			return false;
		}

		<c:if
			test="${(actionType == 'new') }">
		var plainPass = $("#data\\.plainPass").val();
		if (plainPass != undefined) {
		if (plainPass == '' || plainPass.length == 0) {
			alert("请输入密码！");
			return false;
		}}
		</c:if>
		var uname = $("#data\\.uname").val();
		if (uname == '' || uname.length == 0) {
			alert("请输入姓名！");
			return false;
		}
		var birthday = $("#data\\.birthday").val();
		if (!jQuery.isEmptyObject(birthday) && !isDate(birthday)) {
			alert("出生年月日输入不正确！请重新输入");
			return false;
		}
		var validToDate = $("#data\\.validToDate").val();
		if (validToDate != undefined) {
		if (validToDate == '' || validToDate.length == 0) {
			alert("请输入有效期限！");
			return false;
		}
		if (validToDate.length >= 0 && !isDate(validToDate)) {
			alert("有效期限输入不正确！请重新输入");
			return false;
		}}
		<c:if test="${actionType == 'new' }">
		var createHotel = $("#cbxCreateHotel").attr('checked') == 'checked' ? 'true' : 'false';
		if (createHotel) {
			var hname = $("#hname").val();
			if (hname == '' || hname.length == 0) {
				alert("请输入餐厅名称！");
				return false;
			}
			$("#createHotel").val(createHotel);	
			
		}
		</c:if>
		return true;
	});
    $("#imgOK").click(function() {
        $("#userEditForm").submit();
    });
    $("#imgReset").click(function() {
        document.userEditForm.reset();
    });
    $("#imgCancel").click(function() {
    	<c:if test="${SESSION_LOGIN_INFO.admin }">
    	document.location.href = "userList.action";
    	</c:if>
    	<c:if test="${not SESSION_LOGIN_INFO.admin }">
    	document.location.href = "hotelProperty.action";
    	</c:if>
    });
    
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>

	<c:if test="${not (actionType == 'profile') }">
    <c:if test="${data.admin}">
    $("#data\\.admin").attr('checked', 'checked');
    </c:if>
    <c:if test="${createHotel}">
    $("#cbxCreateHotel").attr('checked', 'checked');
    </c:if>
	</c:if>
});
</script>
</head>
<body>

	<form id="userEditForm" name="userEditForm" action="userEditOk.action"
		method="post" enctype="multipart/form-data">
		<input type="hidden" value="${actionType }" />
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="30"><img src="images/tab_03.gif"
								width="15" height="30" /></td>
							<td background="images/tab_05.gif"><img src="images/311.gif"
								width="16" height="16" /><span class="titleText"><c:if
										test="${actionType=='new' }">新建用户</c:if> <c:if
										test="${actionType != 'new'}">用户信息修改</c:if></span></td>
							<td width="15"><img src="images/tab_07.gif" width="15"
								height="30" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>

				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="9" background="images/tab_12.gif">&nbsp;</td>
							<td bgcolor="#f3ffe3">
								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="1">
									<tr>
										<td class="newLabel">用户名</td>
										<td class="newValue"><c:if test="${actionType == 'new' }">
												<input type="text" name="data.uid" value="${data.uid }"
													id="data.uid" maxlength="128" />
											</c:if> <c:if test="${not (actionType=='new') }">
												<input type="hidden" name="data.uid" value="${data.uid }"
													id="data.uid">${data.uid }</c:if></td>
									</tr>
									<tr>
										<td class="newLabel">密码</td>
										<td class="newValue"><input type="password"
											name="data.plainPass" id="data.plainPass" maxlength="128" /></td>
									</tr>
									<tr>
										<td class="newLabel">姓名</td>
										<td class="newValue"><input type="text" name="data.uname"
											value="${data.uname }" id="data.uname" maxlength="128" /></td>
									</tr>
									<tr>
										<td class="newLabel">性别</td>
										<td class="newValue"><select id="data.sex"
											name="data.sex">
												<option value="1">男</option>
												<option value="2">女</option>
										</select></td>
									</tr>
									<tr>
										<td class="newLabel">出生年月日</td>
										<td class="newValue"><input type="text"
											name="data.birthday"
											value="<fmt:formatDate
													value="${data.birthday}" type="date" pattern="yyyy/MM/dd" />"
											id="data.birthday" maxlength="16" />&nbsp;&nbsp;(yyyy/MM/dd)</td>
									</tr>
									<tr>
										<td class="newLabel">联系电话</td>
										<td class="newValue"><input type="text"
											value="${data.telNum }" name="data.telNum" maxlength="128"
											id="data.telNum"></td>
									</tr>

									<c:if test="${not (actionType == 'profile') }">
										<tr>
											<td class="newLabel">有效期限至</td>
											<td class="newValue"><input type="text"
												value="<fmt:formatDate
													value="${data.validToDate}" type="date" pattern="yyyy/MM/dd" />"
												name="data.validToDate" id="data.validToDate" />&nbsp;&nbsp;(yyyy/MM/dd)</td>
										</tr>
										<tr>
											<td class="newLabel">管理员</td>
											<td class="newValue"><input type="checkbox"
												name="data.admin" id="data.admin" value="true" /></td>
										</tr>
										<c:if test="${actionType == 'new' }">
											<tr>
												<td class="newLabel">创建餐厅</td>
												<td class="newValue"><input type="checkbox"
													name="cbxCreateHotel" id="cbxCreateHotel" value="true" />
													<input type="hidden" name="createHotel" id="createHotel" /></td>
											</tr>
											<tr>
												<td class="newLabel">餐厅名称</td>
												<td class="newValue"><input type="text" name="hname"
													id="hname" value="${hname}" /></td>
											</tr>
										</c:if>
									</c:if>
									<tr>
										<td>&nbsp;</td>
										<td><img src="images/OK.gif" id="imgOK" />&nbsp;&nbsp;&nbsp;
											<img src="images/cancel.gif" id="imgCancel" />&nbsp;&nbsp;&nbsp;
											<img src="images/clear.gif" id="imgReset" /></td>
									</tr>
								</table>
							</td>
							<td width="9" background="images/tab_16.gif">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="29">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="29"><img src="images/tab_20.gif"
								width="15" height="29" id="imgOK" /></td>
							<td background="images/tab_21.gif" id="imgCancel">&nbsp;</td>
							<td width="14"><img src="images/tab_22.gif" width="14"
								height="29" id="imgReset" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>