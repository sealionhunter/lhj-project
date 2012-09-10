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
<title>订餐系统管理--订餐信息</title>
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
    $('[name=btnRowEdit]').click(function() {
        var id = $(this).parent().parent().attr("id");
        $("#orderEditForm #uuid").val(id);
        $("#orderEditForm").submit();
    });
    $("[name=btnRowDelete]").click(function() {
        var uuids = new Array();
        var id = $(this).parent().parent().attr("id");
        uuids.push(id);
        $("#orderDeleteForm #uuids").val(uuids);
        $("#orderDeleteForm").submit();
    });

    $('#imgFirst').click(function(){
        $("#orderSearchForm #actionType").val('first');
        $("#orderSearchForm").submit();
    }) ;

    $('#imgLast').click(function(){
        $("#orderSearchForm #actionType").val('last');
        $("#orderSearchForm").submit();
    }) ;

    $('#imgBack').click(function(){
        $("#orderSearchForm #actionType").val('back');
        $("#orderSearchForm").submit();
    }) ;

    $('#imgNext').click(function(){
        $("#orderSearchForm #actionType").val('next');
        $("#orderSearchForm").submit();
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
        $("#orderSearchForm #paging\\.toPage").val(toPage);
        $("#orderSearchForm #actionType").val('toPage');
        $("#orderSearchForm").submit();
    }) ;
    $("[name=btnNew]").click(function() {
        $("#orderEditForm #actionType").val('new');
        $("#orderEditForm #uuid").val('');
        $("#orderEditForm").submit();
    });
    $('[name=btnEdit]').click(function() {
        var n = $("input[name=ids]:checked").length;

        if (n < 1) {
            alert("请选择要修改的订单");
            return;
        }
        if (n > 1) {
            alert("一次只能编辑一条订单");
            return;
        }
        $("#orderEditForm #uuid").val($("input[name=ids]:checked").parent().parent().attr("id"));
        $("#orderEditForm #actionType").val('edit');
        $("#orderEditForm").submit();
    });
    $("[name=btnDelete]").click(function() {
        var uuids = new Array();
        var n = 0;
        $.each($("input[name=ids]:checked"), function() {
            uuids.push($(this).parent().parent().attr("id"));
            n++;
        });
        if (n < 1) {
            alert("请选择要删除的订单");
            return;
        }
        $("#orderDeleteForm #uuids").val(uuids);
        $("#orderDeleteForm").submit();
    });

    $("#imgOK").click(function() {
        $("#orderSearchForm").submit();
    });
    
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>
    
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
							width="16" height="16" /><span class="titleText">点餐检索 -
								${sessionScope.SESSION_LOGIN_INFO.hname}-${table.name }</span></td>
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
							<table cellpadding="2">
								<form id="orderSearchForm" name="orderSearchForm"
									action="orderList.action" method="post">
									<input type="hidden" id="tid" value="tid" value="${tid }">
									<input type="hidden" id="paging.rcount" name="paging.rcount"
										value="${paging.rcount }" /> <input type="hidden"
										id="paging.pcount" name="paging.pcount"
										value="${paging.pcount }" /> <input type="hidden"
										id="paging.pcurrent" name="paging.pcurrent"
										value="${paging.pcurrent }" /> <input type="hidden"
										id="paging.percount" name="paging.percount"
										value="${paging.percount }" /> <input type="hidden"
										id="paging.toPage" name="paging.toPage" /><input
										type="hidden" value="list" name="actionType" id="actionType" />
									<table>
										<tr>
											<td>桌台编号:</td>
											<td><input type="text" name="condition.tid"
												value="${condition.tid }" id="condition.tid" maxlength="128" /></td>
											<td>客户名称:</td>
											<td><input type="text" name="condition.cname"
												value="${condition.cname }" id="condition.cname" /></td>
										</tr>
										<tr>
											<td>联系电话 :</td>
											<td><input type="text" name="condition.telNum"
												value="${condition.telNum }" id="condition.telNum" /></td>
											<td>外送地址 :</td>
											<td><input type="text" name="order.address"
												value="${condition.address }" id="order.address" /></td>
										</tr>
										<tr>
											<td>分类 :</td>
											<td><select name="condition.category"
												id="condition.category">
													<option value=""></option>
													<option value="前台订餐"
														${condition.category == '前台订餐' ? 'selected' : ''}>前台订餐</option>
													<option value="外卖"
														${condition.category == '外卖' ? 'selected' : ''}>外卖</option>
													<option value="电话预订"
														${condition.category == '电话预订' ? 'selected' : ''}>电话预订</option>
													<option value="网络预订"
														${condition.category == '网络预订' ? 'selected' : ''}>网络预订</option>
													<option value="其他"
														${condition.category == '其他' ? 'selected' : ''}>其他</option>
											</select></td>
											<td>预约人数 :</td>
											<td><input type="text" name="condition.ccount"
												value="${condition.ccount }" id="condition.ccount"
												maxlength="16" />以上</td>
										</tr>
										<tr>
											<td>说明 :</td>
											<td colspan="3"><input type="text"
												name="condition.description"
												value="${condition.description }" id="condition.description"
												size=32 /></td>
										</tr>
										<tr>
											<td colspan="4"
												style="padding-left: 55px; padding-top: 10px;"><img
												src="images/search.gif" id="imgOK" /></td>
										</tr>
									</table>
								</form>

								</td>
								<td width="11" background="images/tab_16.gif">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="29"><table width="100%" border="0"
								cellspacing="0" cellpadding="0">
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
						<td height="30"><table width="100%" border="0"
								cellspacing="0" cellpadding="0">
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
												<td width="60">
													<table width="90%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td><div align="center">
																	<img src="images/001.gif" width="14" height="14"
																		name="btnNew" />
																</div></td>
															<td><div align="center" name="btnNew">新增</div></td>
														</tr>
													</table>
												</td>
												<td width="60">
													<table width="90%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td><div align="center">
																	<img src="images/114.gif" width="14" height="14"
																		name="btnEdit" />
																</div></td>
															<td><div align="center" name="btnEdit">修改</div></td>
														</tr>
													</table>
												</td>
												<td width="52"><table width="88%" border="0"
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
												<td class="tabTitle"><input type="checkbox"
													id="cbxSelectAll" /></td>
												<td class="tabTitle">餐台名称</td>
												<td class="tabTitle">客户名称</td>
												<td class="tabTitle">分类</td>
												<td class="tabTitle">预约人数</td>
												<td class="tabTitle">联系电话</td>
												<td class="tabTitle">外送地址</td>
												<td class="tabTitle">说明</td>
												<td class="tabTitle">&nbsp;</td>
											</tr>
											<c:forEach items="${datas }" var="order" varStatus="status">

												<tr id="${order.uuid }"
													class="${status.index / 2 == 0 ? 'odd' : 'even' }">
													<td class="tabBody"><input type="checkbox" name="ids" /></td>
													<td class="tabBody"><span>${table.name }</span></td>
													<td class="tabBody"><span>${order.cname }</span></td>
													<td class="tabBody"><span>${order.category }</span></td>
													<td class="tabBody"><span>${order.ccount }</span></td>
													<td class="tabBody"><span>${order.telNum }</span></td>
													<td class="tabBody"><span>${order.address }</span></td>
													<td class="tabBody"><span>${order.description }</span></td>
													<td class="tabBody"><img src="images/037.gif"
														width="9" height="9" name="btnRowEdit" />&nbsp; <img
														src="images/010.gif" width="9" height="9"
														name="btnRowDelete" />&nbsp;</td>
												</tr>
											</c:forEach>
										</table></td>
									<td width="11" background="images/tab_16.gif">&nbsp;</td>
								</tr>
							</table></td>
					</tr>

					<tr>
						<td height="29"><table width="100%" border="0"
								cellspacing="0" cellpadding="0">
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
																	src="images/go.gif" width="37" height="15"
																	id="imgToPage" /></td>
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
				<form id="orderEditForm" name="orderEditForm"
					action="orderEdit.action" method="post">
					<input type="hidden" name="uuid" id="uuid" /><input type="hidden"
						value="edit" name="actionType" id="actionType" />
				</form>
				<form id="orderDeleteForm" name="orderDeleteForm"
					action="orderDelete.action" method="post">
					<input type="hidden" name="uuids" id="uuids" /><input type="hidden"
						value="delete" name="actionType" id="actionType" />
				</form>
</body>
</html>