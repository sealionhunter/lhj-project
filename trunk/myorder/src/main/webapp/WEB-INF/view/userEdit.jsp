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
		var hname = $("#data\\.uname").val();
		if (hname == '' || hname.length == 0) {
			alert("请输入姓名！");
			return false;
		}
		var validTo = $("#data\\.validTo").val();
		if (validTo == '' || validTo.length == 0) {
			alert("请输入有效期限！");
			return false;
		}
		if (validTo.length >= 0 && !isDate(validTo)) {
			alert("有效期限输入不正确！请重新输入");
			return false;
		}
		var birthday = $("#data\\.birthday").val();
		if (birthday.length >= 0 && !isDate(birthday)) {
			alert("出生年月日输入不正确！请重新输入");
			return false;
		}
		return true;
	});
    $("#imgOK").click(function() {
        $("#userEditForm").submit();
    });
    $("#imgReset").click(function() {
        document.userEditForm.reset();
    });
    $("#imgCancel").click(function() {
    	document.location.href = "userList.action";
    });
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
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
								width="16" height="16" /><span class="titleText"><c:if test="${actionType=='new' }">新建用户</c:if><c:if test="${actionType != 'new'}">用户信息修改</c:if></span></td>
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
											</c:if>
											<c:if test="${actionType=='edit' }">
												<input type="hidden" name="data.uid" value="${data.uid }"
													id="data.uid">${data.uid }</c:if></td>
									</tr>
									<c:if test="${actionType == 'new' }">
										<tr>
											<td class="newLabel">密码</td>
											<td class="newValue"><input type="password"
												name="data.password" id="data.password" maxlength="128" /></td>
										</tr>
									</c:if>
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
											id="data.birthday" maxlength="16" />(yyyy/MM/dd)</td>
									</tr>
									<tr>
										<td class="newLabel">联系电话</td>
										<td class="newValue"><input type="text"
											value="${data.telNum }" name="data.telNum" maxlength="128"
											id="data.telNum"></td>
									</tr>
									<tr>
										<td class="newLabel">有效期限至 :</td>
										<td class="newValue"><input type="text"
											value="<fmt:formatDate
													value="${data.validTo}" type="date" pattern="yyyy/MM/dd" />"
											name="data.validTo" id="data.validTo" />(yyyy/MM/dd)</td>
									</tr>
									<tr>
										<td class="newLabel">管理员 :</td>
										<td class="newValue"><input type="checkbox"
											name="data.admin" id="data.admin" value="true" /></td>
									</tr>
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