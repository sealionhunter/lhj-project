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
<title>订餐系统管理--订餐</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#orderChargeForm").submit(function() {
        var category = $("#data\\.category").val();
        if (category == '' || category.length == 0) {
            alert("请输入分类！");
            return false;
        }
		return true;
	});
    $("#cbxSelectAll").click(function() {
        var checked = $(this).attr("checked");
        $.each($("input[name=ids]"), function() {
            if (checked) {
            $(this).attr('checked',checked);
            } else {
                $(this).removeAttr('checked');  
            }
        });
    });
    $("#btnSelectFood").click(function() {
    	$("#orderChargeForm").attr("action", "orderSelectFood.action");
    	$("#orderChargeForm").submit();
    });
    $("#imgOK").click(function() {
        $("#orderChargeForm").submit();
    });
    $("#imgReset").click(function() {
        document.orderChargeForm.reset();
    });
    $("#imgCancel").click(function() {
        document.location.href = "orderList.action";
    });
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>
});
</script>
</head>
<body>
	<form id="orderChargeForm" name="orderChargeForm"
		action="orderChargeOk.action" method="post">
		<input type="hidden" id="tid" value="tid" value="${tid }"> <input
			type="hidden" name="data.uuid" id="data.uuid" value="${data.uuid }" />
		<input type="hidden" name="data.hid" id="data.hid"
			value="${data.hid }" /> <input type="hidden" id="actionType"
			name="actionType" value="${actionType }" />
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="30"><img src="images/tab_03.gif"
								width="15" height="30" /></td>
							<td background="images/tab_05.gif"><img src="images/311.gif"
								width="16" height="16" /><span class="titleText">订单付款-${sessionScope.SESSION_LOGIN_INFO.hname}-${table.name
									}</span></td>
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
										<td class="newLabel">总金额:</td>
										<td class="newValue">${data.oprice }</td>
										<td class="newLabel">付款金额:</td>
										<td class="newValue"><input type="text" name="data.rprice"
											value="${data.rprice }" id="data.rprice" maxlength="16" /><font color="red">*</font></td>
									</tr>
									<tr>
										<td class="newLabel">说明 :</td>
										<td colspan=3 class="newValue"><textarea rows="4" cols="54"
												name="data.description" maxlength="128"
												id="data.description">${data.description }</textarea></td>
									</tr>
								</table>
							</td>
							<td width="8" background="images/tab_16.gif">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="29"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="15" height="29"><img src="images/tab_20.gif"
								width="15" height="29" /></td>
							<td background="images/tab_21.gif">&nbsp;</td>
							<td width="16"><img src="images/tab_22.gif" width="17"
								height="29" /></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="30"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="15" height="30"><img src="images/tab_03.gif"
								width="15" height="30" /></td>
							<td width="1101" background="images/tab_05.gif"><img
								src="images/311.gif" width="16" height="16" /><span
								class="titleText">菜品选择一览 -
									${sessionScope.SESSION_LOGIN_INFO.hname}</span></td>
							<td width="281" background="images/tab_05.gif"></td>
							<td width="15"><img src="images/tab_07.gif" width="15"
								height="30" /></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="9" background="images/tab_12.gif">&nbsp;</td>
							<td bgcolor="#f3ffe3">
								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"
									onmouseout="changeback()">
									<tr>
										<td class="tabTitle">菜名</td>
										<td class="tabTitle">分类</td>
										<td class="tabTitle">菜系</td>
										<td class="tabTitle">单价</td>
										<td class="tabTitle">数量</td>
									</tr>
									<c:forEach items="${details }" var="detail" varStatus="status">

										<tr id="${detail.uuid }">
											<td class="tabBody"><span>${detail.food.name }</span></td>
											<td class="tabBody"><span><c:if
														test="${detail.food.subCategory == '0'}">主菜</c:if> <c:if
														test="${detail.food.subCategory == '1'}">甜点</c:if> <c:if
														test="${detail.food.subCategory == '2'}">凉菜</c:if> <c:if
														test="${detail.food.subCategory == '3'}">酒水</c:if> <c:if
														test="${detail.food.subCategory == '4'}">饮料</c:if> <c:if
														test="${detail.food.subCategory == '5'}">其他</c:if></span></td>
											<td class="tabBody"><span><c:if test="${detail.food.category == '1'}">鲁菜</c:if> <c:if
														test="${detail.food.category == '2'}">川菜</c:if> <c:if
														test="${detail.food.category == '3'}">粤菜</c:if> <c:if
														test="${detail.food.category == '4'}">苏菜</c:if> <c:if
														test="${detail.food.category == '5'}">闽菜</c:if> <c:if
														test="${detail.food.category == '6'}">浙菜</c:if> <c:if
														test="${detail.food.category == '7'}">湘菜</c:if> <c:if
														test="${detail.food.category == '8'}">徽菜</c:if> <c:if
														test="${detail.food.category == '9'}">其他</c:if>
											</span></td>
											<td class="tabBody"><span>${detail.food.price }</span></td>
											<td class="tabBody"><span>${detail.unit }</span><span>${detail.food.unit
													}</span></td>
										</tr>
									</c:forEach>
								</table>
							</td>
							<td width="11" background="images/tab_16.gif">&nbsp;</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="9" background="images/tab_12.gif">&nbsp;</td>
							<td bgcolor="#f3ffe3">
								<table>
									<tr>
										<td>&nbsp;</td>
										<td><img src="images/OK.gif" id="imgOK" />&nbsp;&nbsp;&nbsp;
											<img src="images/cancel.gif" id="imgCancel" />&nbsp;&nbsp;&nbsp;
											<img src="images/clear.gif" id="imgReset" /></td>
									</tr>
								</table>
							</td>
							<td width="14"><img src="images/tab_22.gif" width="14"
								height="29" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>