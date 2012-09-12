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
        var maxService = $("#data\\.maxService").val();
        if (maxService == '' || maxService.length == 0) {
            alert("请输入容纳人数 ！");
            return false;
        }
        if (!jQuery.isNumeric(maxService)) {
            alert("容纳人数输入不正确！请输入数字...");
            return false;
        }
        var serviceFrom = $("#data\\.serviceFrom").val();
        if (!checkTime(serviceFrom)) {
            alert("营业开始时间输入不正确！请按指定格式输入");
            return false;
        }
        var serviceTo = $("#data\\.serviceTo").val();
        if (!checkTime(serviceTo)) {
            alert("营业结束时间输入不正确！请按指定格式输入");
            return false;
        }
		var category = "";
		$.each($(":input[name*=cbx]"), function() {
			if ($(this).attr('checked') == 'checked') {
				category =category + $(this).val() + ",";
			}
		});
    	$("#data\\.foodCategoris").val(category);
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
    var category = $("#data\\.foodCategoris").val();
    if (category != undefined && category.length >= 1) {
    	var ids = category.split(",");
    	var i = 0;
    	for (; i < ids.length; i++) {
    		$("#cbx"+ids[i]).attr("checked", "checked");
    	}
    }
});
</script>
</head>
<body>

	<form id="hotelEditForm" name="hotelEditForm"
		action="hotelEditOk.action" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="data.uuid" id="data.uuid"
			value="${data.uuid }" /> <input type="hidden" value="${actionType }" />
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
										<td class="newLabel">容纳人数</td>
										<td class="newValue"><input type="text"
											name="data.maxService" value="${data.maxService }"
											id="data.maxService" maxlength="128" /></td>
									</tr>
									<tr>
										<td class="newLabel">营业时间段</td>
										<td class="newValue" nowrap><input type="text"
											name="data.serviceFrom" value="${data.serviceFrom }"
											id="data.serviceFrom" size="10" />&nbsp;&nbsp;~&nbsp;&nbsp;<input type="text"
											name="data.serviceTo" value="${data.serviceTo }"
											id="data.serviceTo" size="10" />&nbsp;&nbsp;hh:mm</td>
									</tr>
									<tr>
										<td class="newLabel">主要菜系</td>
										<td class="newValue"><input type="hidden"
											name="data.foodCategoris" value="${data.foodCategoris }"
											id="data.foodCategoris" /> <input type="checkbox" id="cbx1"
											name="cbx1" value="1">鲁菜&nbsp; <input type="checkbox"
											id="cbx2" name="cbx2" value="2">川菜&nbsp; <input
											type="checkbox" id="cbx3" name="cbx3" value="3">粤菜&nbsp;
											<input type="checkbox" id="cbx4" name="cbx4" value="4">苏菜&nbsp;
											<input type="checkbox" id="cbx5" name="cbx5" value="5">闽菜&nbsp;
											<input type="checkbox" id="cbx6" name="cbx6" value="6">浙菜&nbsp;
											<input type="checkbox" id="cbx7" name="cbx7" value="7">湘菜&nbsp;
											<input type="checkbox" id="cbx8" name="cbx8" value="8">徽菜&nbsp;
											<input type="checkbox" id="cbx9" name="cbx9" value="9">其他&nbsp;
										</td>
									</tr>
									<tr>
										<td class="newLabel">联系电话</td>
										<td class="newValue"><input type="text"
											name="data.telNum" value="${data.telNum }" id="data.telNum"
											maxlength="16" /></td>
									</tr>
									<tr>
										<td class="newLabel">说明</td>
										<td class="newValue"><textarea rows="4" cols="20"
												name="data.description" id="data.description">${data.description }</textarea></td>
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