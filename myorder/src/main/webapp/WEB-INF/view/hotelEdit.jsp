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
<title>订餐系统管理--餐厅信息</title>
<script type="text/javascript">

$(document).ready(function() {
	$("#hotelEditForm").submit(function() {
		var hname = $("#data\\.name").val();
		if (hname == '' || hname.length == 0) {
			alert("请输入餐厅名称！");
			return false;
		}
		var address = $("#data\\.address").val();
		if (address == '' || address.length == 0) {
			alert("请输入餐厅地址！");
			return false;
		}
		return true;
	});
    $("#imgOK").click(function() {
        $("#hotelEditForm").submit();
    });
    $("#imgReset").click(function() {
        document.hotelEditForm.reset();
    });
    $("#imgCancel").click(function() {
    	<c:if test="${SESSION_LOGIN_INFO.admin }">
    	document.location.href = "hotelList.action";
    	</c:if>
    	<c:if test="${not SESSION_LOGIN_INFO.admin }">
    	document.location.href = "hotelProperty.action";
    	</c:if>
    });
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>
});
</script>
</head>
<body>

	<form id="hotelEditForm" name="hotelEditForm"
		action="hotelEditOk.action" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="data.uuid" id="data.uuid"
			value="${data.uuid }" /> <input type="hidden"
			value="${actionType }" />
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="30"><img src="images/tab_03.gif"
								width="15" height="30" /></td>
							<td background="images/tab_05.gif"><img src="images/311.gif"
								width="16" height="16" /><span class="titleText">新建餐厅</span></td>
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
										<td class="newLabel">餐厅名称</td>
										<td class="newValue"><input type="text" name="data.name"
											value="${data.name }" id="data.name" maxlength="128" /></td>
									</tr>
									<tr>
										<td class="newLabel">餐厅地址</td>
										<td class="newValue"><input type="text"
											name="data.address" value="${data.address }"
											id="data.address" maxlength="128" /></td>
									</tr>
									<tr>
										<td class="newLabel">联系电话</td>
										<td class="newValue"><input type="text"
											name="data.telNum" value="${data.telNum }"
											id="data.telNum" maxlength="16" /></td>
									</tr>
									<tr>
										<td class="newLabel">说明</td>
										<td class="newValue"><textarea rows="4" cols="20"
												name="data.description" maxlength="128"
												id="data.description">${data.description }</textarea></td>
									</tr>
									<tr>
										<td class="newLabel">上传图片 :</td>
										<td class="newValue"><input type="file" name="imgFile" /></td>
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