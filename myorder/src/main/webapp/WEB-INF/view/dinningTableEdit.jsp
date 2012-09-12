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
<title>订餐系统管理--餐台信息</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#tableEditForm").submit(function() {

        var hname = $("#data\\.name").val();
        if (hname == '' || hname.length == 0) {
            alert("请输入餐台编号！");
            return false;
        }
        var area = $("#data\\.area").val();
        if (area == '' || area.length == 0) {
            alert("请输入位置 ！");
            return false;
        }
        var category = $("#data\\.category").val();
        if (category == '' || category.length == 0) {
            alert("请选择分类 ！");
            return false;
        }
        var maxService = $("#data\\.maxService").val();
        if (maxService == '' || maxService.length == 0) {
            alert("请输入座位数 ！");
            return false;
        }
        if (isNaN( parseInt(maxService) )) {
            alert("座位数输入不正确！请输入整数...");
            return false;
        }
        return true;
	});
    $("#imgOK").click(function() {
        $("#tableEditForm").submit();
    });
    $("#imgReset").click(function() {
        document.tableEditForm.reset();
    });
    $("#imgCancel").click(function() {
    	document.location.href = "tableList.action";
    });
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>

    $("#data\\.category").val('${data.category}');
});

</script>
</head>
<body>
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
									test="${actionType=='edit' }"> 修改餐台</c:if>
								<c:if test="${actionType=='new' }">新建餐台</c:if> - ${sessionScope.SESSION_LOGIN_INFO.hname}</span></td>
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
							<form id="tableEditForm" name="tableEditForm"
								action="tableEditOk.action" method="post"
								enctype="multipart/form-data">
								<input type="hidden" name="data.uuid" id="data.uuid"
									value="${data.uuid }" /> <input type="hidden" name="data.hid"
									id="data.hid" value="${data.hid }" /> <input type="hidden"
									value="${actionType }" name="actionType" id="actionType" />

								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="1">
									<c:if test="${data.imgUrl != null }">
										<tr>
											<td colspan="2" class="newValue" style="text-align: center;"><img
												src="${data.imgUrl }" height=240 width=320 /></td>
										</tr>
									</c:if>
									<tr>
										<td class="newLabel">餐台编号:</td>
										<td class="newValue"><input type="text" name="data.name"
											value="${data.name }" id="data.name" maxlength="128"
											size="40" /></td>
									</tr>
									<tr>
										<td class="newLabel">位置</td>
										<td class="newValue"><input type="text" name="data.area"
											value="${data.area }" id="data.area" maxlength="128"
											size="40" /></td>
									</tr>
									<tr>
										<td class="newLabel">分类</td>
										<td class="newValue"><select name="data.category"
											id="data.category">
												<option value="1">大厅</option>
												<option value="2">普通包厢</option>
												<option value="3">雅座</option>
												<option value="4">豪华包厢</option>
												<option value="5">情侣包厢</option>
												<option value="6">其他</option>
										</select></td>
									</tr>
									<tr>
										<td class="newLabel">座位数</td>
										<td class="newValue"><input type="text"
											name="data.maxService" value="${data.maxService }"
											id="data.maxService" maxlength="16" /></td>
									</tr>
									<tr>
										<td class="newLabel">说明</td>
										<td class="newValue"><textarea rows="4" cols="40"
												name="data.description" maxlength="128"
												id="data.description">${data.description }</textarea></td>
									</tr>
									<tr>
										<td class="newLabel">上传图片</td>
										<td class="newValue"><input type="file" name="imgFile" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><img src="images/OK.gif" id="imgOK" />&nbsp;&nbsp;&nbsp;
											<img src="images/cancel.gif" id="imgCancel" />&nbsp;&nbsp;&nbsp;
											<img src="images/clear.gif" id="imgReset" /></td>
									</tr>
								</table>
							</form>
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
							width="15" height="29" /></td>
						<td background="images/tab_21.gif">&nbsp;</td>
						<td width="14"><img src="images/tab_22.gif" width="14"
							height="29" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>