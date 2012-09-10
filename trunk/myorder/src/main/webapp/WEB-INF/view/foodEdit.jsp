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
<title>订餐系统管理--菜品信息</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#foodEditForm").submit(function() {

        var hname = $("#data\\.name").val();
        if (hname == '' || hname.length == 0) {
            alert("请输入菜名！");
            return false;
        }
        var category = $("#data\\.category").val();
        if (category == '' || category.length == 0) {
            alert("请选择菜系 ！");
            return false;
        }
        var category = $("#data\\.category").val();
        if (category == '' || category.length == 0) {
            alert("请选择分类 ！");
            return false;
        }
        var price = $("#data\\.price").val();
        if (price == '' || price.length == 0) {
            alert("请输入单价 ！");
            return false;
        }
        if (!jQuery.isNumeric(price)) {
            alert("单价输入不正确！请输入数字...");
            return false;
        }
        return true;
	});
    $("#imgOK").click(function() {
        $("#foodEditForm").submit();
    });
    $("#imgReset").click(function() {
        document.foodEditForm.reset();
    });
    $("#imgCancel").click(function() {
        document.location.href = "foodList.action";
    });
	
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>

    $("#data\\.category").val('${data.category}');
    $("#data\\.subCategory").val('${data.subCategory}');
})
</script>
</head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="15" height="30"><img src="images/tab_03.gif" width="15" height="30" /></td>
          <td background="images/tab_05.gif"><img src="images/311.gif" width="16" height="16" /><span class="titleText">新建餐厅</span></td>
          <td width="15"><img src="images/tab_07.gif" width="15" height="30" /></td>
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
	<form id="foodEditForm" name="foodEditForm" action="foodEditOk.action"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="data.uuid" id="data.uuid"
			value="${data.uuid }" /> <input type="hidden" name="data.hid"
			id="data.hid" value="${data.hid }" /> <input type="hidden"
			id="actionType" name="actionType" value="${actionType }" />

            <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1">
				<tr>
					<td class="newLabel">菜名:</td>
					<td class="newValue"><input type="text" name="data.name" value="${data.name }"
						id="data.name" maxlength="128" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="newLabel">分类 :</td>
					<td class="newValue"><select name="data.subCategory" id="data.subCategory">
												<option value="0">主菜</option>
												<option value="1">甜点</option>
												<option value="2">凉菜</option>
												<option value="3">酒水</option>
												<option value="4">饮料</option>
												<option value="5">其他</option>
					</select><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="newLabel">菜系 :</td>
					<td class="newValue"><select name="data.category" id="data.category">
												<option value="1">鲁菜</option>
												<option value="2">川菜</option>
												<option value="3">粤菜</option>
												<option value="4">苏菜</option>
												<option value="5">闽菜</option>
												<option value="6">浙菜</option>
												<option value="7">湘菜</option>
												<option value="8">徽菜</option>
												<option value="9">其他</option>
					</select></td>
				</tr>
				<tr>
					<td class="newLabel">计量单位 :</td>
					<td class="newValue"><input type="text" name="data.unit"
						value="${data.unit }" id="data.unit" maxlength="16" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td class="newLabel">单价 :</td>
					<td class="newValue"><input type="text" name="data.price"
						value="${data.price }" id="data.price" maxlength="16" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td class="newLabel">说明 :</td>
					<td class="newValue"><textarea rows="4" cols="20" name="data.description"
							maxlength="128" id="data.description">${data.description }</textarea></td>
				</tr>
				<c:if test="${data.imgUrl != null }">
					<tr>
						<td colspan="2"  class="newValue"><img src="${data.imgUrl }"
							height=240 width=320 /></td>
					</tr>
				</c:if>
				<tr>
					<td class="newLabel">上传图片 :</td>
					<td class="newValue"><input type="file" name="imgFile" /></td>
				</tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <img src="images/OK.gif" id="imgOK" />&nbsp;&nbsp;&nbsp;
                        <img src="images/cancel.gif"  id="imgCancel" />&nbsp;&nbsp;&nbsp;
                        <img src="images/clear.gif" id="imgReset" />
                    </td>
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
          <td width="15" height="29"><img src="images/tab_20.gif" width="15" height="29" /></td>
          <td background="images/tab_21.gif">&nbsp;</td>
          <td width="14"><img src="images/tab_22.gif" width="14" height="29" /></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>