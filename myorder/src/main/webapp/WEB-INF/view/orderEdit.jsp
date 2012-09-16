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
	$("#orderEditForm").submit(function() {
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
    	$("#orderEditForm").attr("action", "orderSelectFood.action");
    	$("#orderEditForm").submit();
    });
    $("#imgOK").click(function() {
        $("#orderEditForm").submit();
    });
    $("#imgReset").click(function() {
        document.orderEditForm.reset();
    });
    $("#imgCancel").click(function() {
        document.location.href = "tableList.action";
    });
    $("[name=btnRowDelete]").click(function() {
        var dids = new Array();
        var id = $(this).parent().parent().attr("id");
        dids.push(id);
        $("#orderEditForm #uuids").val(dids);
        $("#orderEditForm").attr('action', 'orderFoodSelectRemove');
        $("#orderEditForm").submit();
    });
    $("[name=btnDelete]").click(function() {
        var dids = new Array();
        var n = 0;
        $.each($("input[name=ids]:checked"), function() {
            dids.push($(this).parent().parent().attr("id"));
            n++;
        });
        if (n < 1) {
            alert("请选择要删除的菜品");
            return;
        }
        $("#orderEditForm #uuids").val(dids);
        $("#orderEditForm").attr('action', 'orderFoodSelectRemove');
        $("#orderEditForm").submit();
    });
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>
});
</script>
</head>
<body>
	<form id="orderEditForm" name="orderEditForm"
		action="orderEditOk.action" method="post"
		enctype="multipart/form-data">
		<input type="hidden" id="uuids" value="uuids" value="${uuids }">
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
								width="16" height="16" /><span class="titleText">新建订单-${sessionScope.SESSION_LOGIN_INFO.hname}-${table.name
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
										<td class="newLabel">客户名称:</td>
										<td class="newValue"><input type="text" name="data.cname"
											value="${data.cname }" id="data.cname" maxlength="128" /></td>
										<td class="newLabel">分类 :</td>
										<td class="newValue"><select name="data.category" id="data.category">
												<option value="1">前台订餐</option>
												<option value="2">外卖</option>
												<option value="3">电话预订</option>
												<option value="4">网络预订</option>
												<option value="5">其他</option>
										</select></td>
									</tr>
									<tr>
										<td class="newLabel">预约人数 :</td>
										<td class="newValue"><input type="text" name="data.ccount"
											value="${data.ccount }" id="data.ccount" maxlength="4" size=4 /></td>
										<td class="newLabel">联系电话 :</td>
										<td class="newValue"><input type="text" name="data.telNum"
											value="${data.telNum }" id="data.telNum" maxlength="16" /></td>
									</tr>
									<tr>
										<td class="newLabel">外送地址 :</td>
										<td colspan=3 class="newValue"><input type="text" name="data.address"
											value="${data.address }" id="data.address" maxlength="16"
											size="61" /></td>
									</tr>
									<tr>
										<td class="newLabel">备注 :</td>
										<td colspan=3 class="newValue"><textarea rows="4" cols="54"
												name="data.description" maxlength="128"
												id="data.description">${data.description }</textarea></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>
											<img src="images/selectFood.gif" id="btnSelectFood" /></td>
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
                            <td width="281" background="images/tab_05.gif"><table
                                    border="0" align="right" cellpadding="0" cellspacing="0">
                                    <tr>
                                        </td>
                                        <td width="52" style="cursor:pointer"><table width="88%" border="0"
                                                cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td><div align="center">
                                                            <img src="images/083.gif" width="14" height="14"
                                                                name="btnDelete" />
                                                        </div></td>
                                                    <td><div align="center" name="btnDelete">删除</div></td>
                                                </tr>
                                            </table></td>
                                    </tr>
                                </table></td>
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
                            <td bgcolor="#f3ffe3"><table width="99%" border="0"
                                    align="center" cellpadding="0" cellspacing="1"
                                    bgcolor="#c0de98" onmouseover="changeto()"
                                    onmouseout="changeback()">
                                    <tr>
                                        <th class="tabTitle"><input type="checkbox"
                                            id="cbxSelectAll" /></td>
                                        <td class="tabTitle">菜名</td>
                                        <td class="tabTitle">分类</td>
                                        <td class="tabTitle">菜系</td>
                                        <td class="tabTitle">单价</td>
                                        <td class="tabTitle">数量</td>
                                        <td class="tabTitle"></td>
                                    </tr>
                                    <c:forEach items="${details }" var="detail" varStatus="status">

                                        <tr id="${detail.uuid }"
                                            class="${status.index / 2 == 0 ? 'odd' : 'even' }">
                                            <td class="tabBody"><input type="checkbox" name="ids"
                                                value="${detail.uuid }" id="${detail.uuid }" /></td>
                                            <td class="tabBody"><span>${detail.food.name }</span></td>
                                            <td class="tabBody"><span><c:if
                                                        test="${detail.food.subCategory == '0'}">主菜</c:if> <c:if
                                                        test="${detail.food.subCategory == '1'}">甜点</c:if> <c:if
                                                        test="${detail.food.subCategory == '2'}">凉菜</c:if> <c:if
                                                        test="${detail.food.subCategory == '3'}">酒水</c:if> <c:if
                                                        test="${detail.food.subCategory == '4'}">饮料</c:if> <c:if
                                                        test="${detail.food.subCategory == '5'}">其他</c:if></span></td>
                                            <td class="tabBody"><span>${detail.food.category
                                                    } <c:if test="${detail.food.category == '1'}">鲁菜</c:if> <c:if
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
                                            <td class="tabBody"><input type="text"
                                                name="count${detail.uuid }" id="count${detail.uuid }"
                                                value="${detail.unit }" /><span>${detail.food.unit }</span></td>
                                            <td class="tabBody"><img src="images/010.gif" width="24"
                                                height="24" name="btnRowDelete" /></td>
                                        </tr>
                                    </c:forEach>
                                </table></td>
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