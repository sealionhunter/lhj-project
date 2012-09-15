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
<title>订餐系统管理--菜品信息</title>
<script type="text/javascript">

$(document).ready(function() {
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

    $("#imgOK").click(function() {
        $("#orderFoodSearchForm").submit();
    });
    $('#imgFirst').click(function(){
        $("#orderFoodSearchForm #actionType").val('first');
        $("#orderFoodSearchForm").submit();
    }) ;

    $('#imgLast').click(function(){
        $("#orderFoodSearchForm #actionType").val('last');
        $("#orderFoodSearchForm").submit();
    }) ;

    $('#imgBack').click(function(){
        $("#orderFoodSearchForm #actionType").val('back');
        $("#orderFoodSearchForm").submit();
    }) ;

    $('#imgNext').click(function(){
        $("#orderFoodSearchForm #actionType").val('next');
        $("#orderFoodSearchForm").submit();
    }) ;

    $('#imgToPage').click(function(){
        var toPage = $("#toPage").val();
        if (toPage == null || toPage == '') {
            alert("请输入页数");
            $("#toPage").focus();
            return;
        }
        if (!jQuery.isNumeric(toPage)) {
            alert("请输入数字");
            $("#toPage").focus();
            return;
        }
        $("#orderFoodSearchForm #paging\\.toPage").val(toPage);
        $("#orderFoodSearchForm #actionType").val('toPage');
        $("#orderFoodSearchForm").submit();
    }) ;
    
    $("[name=btnNew]").click(function() {
        var fids = new Array();
        var n = 0;
        $.each($("input[name=ids]:checked"), function() {
            fids.push($(this).parent().parent().attr("id"));
            n++;
        });
        if (n < 1) {
            alert("请选择要追加的菜品");
            return;
        }
        $("#orderEditForm #fids").val(fids);
        $("#orderEditForm").submit();
    });

    $("#imgCancel").click(function() {
        $("#orderEditForm #fids").val('');
        $("#orderEditForm").submit();
    });
    
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>
    $("#condition\\.category").val('${condition.category}');
    $("#condition\\.subCategory").val('${condition.subCategory}');
    
});
</script>
</head>
<body>
	<form id="orderFoodSearchForm" name="orderFoodSearchForm"
		action="orderFoodSelect.action" method="post">
		<input type="hidden" name="uuids" id="uuids" /> <input type="hidden"
			name="fids" id="fids" value="${fids}" /> <input type="hidden"
			name="oid" id="oid" value="${oid}" /> <input type="hidden"
			id="paging.rcount" name="paging.rcount" value="${paging.rcount }" />
		<input type="hidden" id="paging.pcount" name="paging.pcount"
			value="${paging.pcount }" /> <input type="hidden"
			id="paging.pcurrent" name="paging.pcurrent"
			value="${paging.pcurrent }" /> <input type="hidden"
			id="paging.percount" name="paging.percount"
			value="${paging.percount }" /> <input type="hidden"
			id="paging.toPage" name="paging.toPage" /><input type="hidden"
			value="list" name="actionType" id="actionType" />
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="1%" height="30"><img src="images/tab_03.gif"
								width="100%" height="30" /></td>
							<td width="78%" background="images/tab_05.gif"><img
								src="images/311.gif" width="16" height="16" /><span
								class="titleText">菜品检索 -
									${sessionScope.SESSION_LOGIN_INFO.hname}</span></td>
							<td width="20%" background="images/tab_05.gif"></td>
							<td width="1%"><img src="images/tab_07.gif" width="100%"
								height="30" /></td>
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
										<td align="right">菜名:</td>
										<td><input type="text" name="condition.name"
											value="${condition.name }" id="condition.name" /></td>
										<td align="right">分类 :</td>
										<td><select name="condition.subCategory"
											id="condition.subCategory">
												<option value=""></option>
												<option value="0">主菜</option>
												<option value="1">甜点</option>
												<option value="2">凉菜</option>
												<option value="3">酒水</option>
												<option value="4">饮料</option>
												<option value="5">其他</option>
										</select></td>
									</tr>
									<tr>
										<td align="right">菜系 :</td>
										<td><select name="condition.category"
											id="condition.category">
												<option value=""></option>
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
										<td align="right">单价 :</td>
										<td><input type="text" name="condition.price"
											value="${condition.price }" id="condition.price" />以上</td>
									</tr>
									<tr>
										<td align="right">特色 :</td>
										<td colspan="3"><input type="text"
											name="condition.description"
											value="${condition.description }" id="condition.description" /></td>
									</tr>
									<tr>
										<td colspan="4" style="padding-left: 55px; padding-top: 10px;">
											<img src="images/search.gif" id="imgOK" />&nbsp;&nbsp;&nbsp;<img src="images/cancel.gif" id="imgCancel" />
										</td>
									</tr>
								</table>
							</td>
							<td width="11" background="images/tab_16.gif">&nbsp;</td>
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
							<td width="16"><img src="images/tab_22.gif" width="14"
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
								class="titleText">菜品一览 -
									${sessionScope.SESSION_LOGIN_INFO.hname}</span></td>
							<td width="281" background="images/tab_05.gif"><table
									border="0" align="right" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60" style="cursor:pointer">
											<table width="90%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td><div align="center">
															<img src="images/001.gif" width="14" height="14"
																name="btnNew" />
														</div></td>
													<td><div align="center" name="btnNew">追加</div></td>
												</tr>
											</table>
										</td>
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
											id="cbxSelectAll" /></th>
										<th class="tabTitle">菜名</th>
										<th class="tabTitle">分类</th>
										<th class="tabTitle">菜系</th>
										<th class="tabTitle">单价</th>
										<th class="tabTitle">特色</th>
									</tr>
									<c:forEach items="${datas }" var="food" varStatus="status">

										<tr id="${food.uuid }"
											class="${status.index / 2 == 0 ? 'odd' : 'even' }">
											<td class="tabBody"><input type="checkbox" name="ids"
												value="${food.uuid }" id="${table.uuid }" /></td>
											<td class="tabBody"><span><img
													src="${food.imgUrl }" height=60 width=80 />${food.name }</span></td>
											<td class="tabBody"><span> <c:if
														test="${food.subCategory == '0'}">主菜</c:if> <c:if
														test="${food.subCategory == '1'}">甜点</c:if> <c:if
														test="${food.subCategory == '2'}">凉菜</c:if> <c:if
														test="${food.subCategory == '3'}">酒水</c:if> <c:if
														test="${food.subCategory == '4'}">饮料</c:if> <c:if
														test="${food.subCategory == '5'}">其他</c:if></span></td>
											<td class="tabBody"><span><c:if
														test="${food.category == '1'}">鲁菜</c:if> <c:if
														test="${food.category == '2'}">川菜</c:if> <c:if
														test="${food.category == '3'}">粤菜</c:if> <c:if
														test="${food.category == '4'}">苏菜</c:if> <c:if
														test="${food.category == '5'}">闽菜</c:if> <c:if
														test="${food.category == '6'}">浙菜</c:if> <c:if
														test="${food.category == '7'}">湘菜</c:if> <c:if
														test="${food.category == '8'}">徽菜</c:if> <c:if
														test="${food.category == '9'}">其他</c:if></span></td>
											<td class="tabBody"><span>${food.price
													}/${food.unit }</span></td>
											<td class="tabBody"><span>${food.description }</span></td>
										</tr>
									</c:forEach>
								</table></td>
							<td width="11" background="images/tab_16.gif">&nbsp;</td>
						</tr>
					</table></td>
			</tr>

			<tr>
				<td height="29"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="15" height="29"><img src="images/tab_20.gif"
								width="15" height="29" /></td>
							<td background="images/tab_21.gif"><table width="100%"
									border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="25%" height="29" nowrap="nowrap"><span>共${paging.rcount
												}条纪录，当前第${paging.pcurrent }/${paging.pcount
												}页，每页${paging.percount }条纪录</span></td>
										<td width="75%" valign="top"><div align="right">
												<table width="352" height="20" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td width="62" height="22" valign="middle"><div
																align="right">
																<img src="images/first.gif" width="37" height="15"
																	id="imgFirst" />
															</div></td>
														<td width="50" height="22" valign="middle"><div
																align="right">
																<img src="images/back.gif" width="43" height="15"
																	id="imgBack" />
															</div></td>
														<td width="54" height="22" valign="middle"><div
																align="right">
																<img src="images/next.gif" width="43" height="15"
																	id="imgNext" />
															</div></td>
														<td width="49" height="22" valign="middle"><div
																align="right">
																<img src="images/last.gif" width="37" height="15"
																	id="imgLast" />
															</div></td>
														<td width="59" height="22" valign="middle"><div
																align="right">转到第</div></td>
														<td width="25" height="22" valign="middle"><span>
																<input id="toPage" name="toPage" type="text"
																style="height: 10px; width: 25px;" size="5" />
														</span></td>
														<td width="23" height="22" valign="middle">页</td>
														<td width="30" height="22" valign="middle"><img
															src="images/go.gif" width="37" height="15" id="imgToPage" /></td>
													</tr>
												</table>
											</div></td>
									</tr>
								</table></td>
							<td width="14"><img src="images/tab_22.gif" width="14"
								height="29" /></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form>
	<form id="orderEditForm" name="orderEditForm"
		action="orderFoodSelectOK.action" method="post">
		<input type="hidden" id="fids" name="fids">
	</form>
</body>
</html>