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
<title>订餐系统管理--餐台信息</title>
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
        $("#tableEditForm #uuid").val(id);
        $("#tableEditForm").submit();
    });
    $('[name=btnRowOrder]').click(function() {
        var id = $(this).parent().parent().attr("id");
        $("#tableOrderForm #tid").val(id);
        $("#tableOrderForm").submit();
    });
    $("[name=btnRowDelete]").click(function() {
    	if (!confirm("删除后不可恢复，确定删除吗！")) {
    		return;
    	}
        var uuids = new Array();
        var id = $(this).parent().parent().attr("id");
        uuids.push(id);
        $("#tableDeleteForm #uuids").val(uuids);
        $("#tableDeleteForm").submit();
    });

    $('#imgFirst').click(function(){
        $("#tableSearchForm #actionType").val('first');
        $("#tableSearchForm").submit();
    }) ;

    $('#imgLast').click(function(){
        $("#tableSearchForm #actionType").val('last');
        $("#tableSearchForm").submit();
    }) ;

    $('#imgBack').click(function(){
        $("#tableSearchForm #actionType").val('back');
        $("#tableSearchForm").submit();
    }) ;

    $('#imgNext').click(function(){
        $("#tableSearchForm #actionType").val('next');
        $("#tableSearchForm").submit();
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
        $("#tableSearchForm #paging\\.toPage").val(toPage);
        $("#tableSearchForm #actionType").val('toPage');
        $("#tableSearchForm").submit();
    }) ;
    $('[name=btnEdit]').click(function() {
        var n = $("input[name=ids]:checked").length;

        if (n < 1) {
            alert("请选择要修改的餐台");
            return;
        }
        if (n > 1) {
            alert("一次只能编辑一条信息");
            return;
        }
        $("#tableEditForm #uuid").val($("input[name=ids]:checked").parent().parent().attr("id"));
        $("#tableEditForm").submit();
    });
    $('[name=btnOrder]').click(function() {
        var n = $("input[name=ids]:checked").length;

        if (n < 1) {
            alert("请选择餐台");
            return;
        }
        if (n > 1) {
            alert("一次只能对一张餐台预定");
            return;
        }
        $("#tableOrderForm #tid").val($("input[name=ids]:checked").parent().parent().attr("id"));
        $("#tableOrderForm").submit();
    });
    $("[name=btnDelete]").click(function() {
        var uuids = new Array();
        var n = 0;
        $.each($("input[name=ids]:checked"), function() {
            uuids.push($(this).parent().parent().attr("id"));
            n++;
        });
        if (n < 1) {
            alert("请选择要删除的餐台");
            return;
        }
    	if (!confirm("删除后不可恢复，确定删除吗！")) {
    		return;
    	}
        $("#tableDeleteForm #uuids").val(uuids);
        $("#tableDeleteForm").submit();
    });
    $("#imgOK").click(function() {
        $("#tableSearchForm").submit();
    });
    $("[name=btnNew]").click(function() {
        $("#tableEditForm #uuid").val("");
        $("#tableEditForm #actionType").val("new");
        $("#tableEditForm").submit();
    });
    <c:if test="${errormsg != null}">
    alert("${errormsg}");
    </c:if>
    $("#condition\\.state").val('${condition.state}');
    $("#condition\\.category").val('${condition.category}');
    
});
</script>
</head>
<body>
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
							class="titleText">餐台检索 -
								${sessionScope.SESSION_LOGIN_INFO.hname}</span></td>
						<td width="20%" background="images/tab_05.gif"></td>
						<td width="1%"><img src="images/tab_07.gif" width="100%"
							height="30" /></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td>
				<form id="tableSearchForm" name="tableSearchForm"
					action="tableList.action" method="post">
					<input type="hidden" id="paging.rcount" name="paging.rcount"
						value="${paging.rcount }" /> <input type="hidden"
						id="paging.pcount" name="paging.pcount" value="${paging.pcount }" />
					<input type="hidden" id="paging.pcurrent" name="paging.pcurrent"
						value="${paging.pcurrent }" /> <input type="hidden"
						id="paging.percount" name="paging.percount"
						value="${paging.percount }" /> <input type="hidden"
						id="paging.toPage" name="paging.toPage" /><input type="hidden"
						value="list" name="actionType" id="actionType" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="9" background="images/tab_12.gif">&nbsp;</td>
							<td bgcolor="#f3ffe3">
								<table>
									<tr>
										<td>餐台名称:</td>
										<td><input type="text" name="condition.name"
											value="${condition.name }" id="condition.name" /></td>
										<td>状态:</td>
										<td><select name="condition.state" id="condition.state">
												<option value=""></option>
												<option value="0">使用中</option>
												<option value="1"}>空闲中</option>
												<option value="2"}>预订中</option>
										</select></td>
									</tr>
									<tr>
										<td>分类 :</td>
										<td><select name="condition.category"
											id="condition.category">
												<option value=""></option>
												<option value="1">大厅</option>
												<option value="2">普通包厢</option>
												<option value="3">雅座</option>
												<option value="4">豪华包厢</option>
												<option value="5">情侣包厢</option>
												<option value="6">其他</option>
										</select></td>
										<td>座位数 :</td>
										<td><input type="text" name="condition.maxService"
											value="${condition.maxService }" id="condition.maxService" />以上</td>
									</tr>
									<tr>
										<td>餐台位置:</td>
										<td colspan="3"><input type="text" name="condition.area"
											value="${condition.area }" id="condition.area" /></td>
									</tr>
									<tr>
										<td colspan="4" style="padding-left: 55px; padding-top: 10px;"><img
											src="images/search.gif" id="imgOK" /></td>
									</tr>
								</table>
							</td>
							<td width="11" background="images/tab_16.gif">&nbsp;</td>
						</tr>
					</table>

				</form>
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
							class="titleText">餐台一览 -
								${sessionScope.SESSION_LOGIN_INFO.hname}</span></td>
						<td width="281" background="images/tab_05.gif"><table
								border="0" align="right" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60">
										<table width="90%" border="0" cellpadding="0" cellspacing="0">
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
										<table width="90%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td><div align="center">
														<img src="images/114.gif" width="14" height="14"
															name="btnEdit" />
													</div></td>
												<td><div align="center" name="btnEdit">修改</div></td>
											</tr>
										</table>
									</td>
									<td width="52"><table width="88%"
											border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td><div align="center">
														<img src="images/083.gif" width="14" height="14"
															name="btnDelete" />
													</div></td>
												<td><div align="center" name="btnDelete">删除</div></td>
											</tr>
										</table></td>
									<td width="52"><table width="88%"
											border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td><div align="center">
														<img src="images/083.gif" width="14" height="14"
															name="btnOrder" />
													</div></td>
												<td><div align="center" name="btnOrder">订餐</div></td>
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
								align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98"
								onmouseover="changeto()" onmouseout="changeback()">
								<tr>
									<td width="3%" class="tabTitle"><input type="checkbox"
										id="cbxSelectAll" /></td>
									<td width="18%" class="tabTitle">餐台名称</td>
									<td width="23%" class="tabTitle">位置</td>
									<td width="5%" class="tabTitle">分类</v>
									<td width="3%" class="tabTitle">座位数</td>
									<td width="10%" class="tabTitle">状态</td>
									<td width="27%" class="tabTitle">说明</td>
									<td width="7%" class="tabTitle">&nbsp;</td>
								</tr>
								<c:forEach items="${datas }" var="table" varStatus="status">

									<tr id="${table.uuid }" class="tabBody">
										<td class="tabBody"><input type="checkbox" name="ids"
											value="${table.uuid }" id="${table.uuid }" /></td>
										<td class="tabBody"><img src="${table.imgUrl }" height=60
											width=80 />${table.name }</td>
										<td class="tabBody">${table.area }</td>
										<td class="tabBody"><c:if
												test="${table.category == '1' }">大厅</c:if> <c:if
												test="${table.category == '2' }">普通包厢</c:if> <c:if
												test="${table.category == '3' }">雅座</c:if> <c:if
												test="${table.category == '4' }">豪华包厢</c:if> <c:if
												test="${table.category == '5' }">情侣包厢</c:if> <c:if
												test="${table.category == '6' }">其他</c:if></td>
										<td class="tabBody" style="text-align: right;">${table.maxService
											}</td>
										<td class="tabBody"><c:if test="${table.state == '0' }">使用中</c:if>
											<c:if test="${table.state == '1' }">空闲中</c:if> <c:if
												test="${table.state == '2' }">预订中</c:if></td>
										<td class="tabBody">${table.description }</td>
										<td class="tabBody"><img src="images/037.gif" width="9"
											height="9" name="btnRowEdit" />&nbsp; <img
											src="images/010.gif" width="9" height="9" name="btnRowDelete" />&nbsp;
											<img src="images/002.gif" width="9" height="9"
											name="btnRowOrder" /></td>
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
	<form id="tableEditForm" name="tableEditForm" action="tableEdit.action"
		method="post">
		<input type="hidden" name="uuid" id="uuid" /><input type="hidden"
			value="edit" name="actionType" id="actionType" />
	</form>
	<form id="tableDeleteForm" name="tableDeleteForm"
		action="tableDelete.action" method="post">
		<input type="hidden" name="uuids" id="uuids" /><input type="hidden"
			value="delete" name="actionType" id="actionType" />
	</form>
	<form id="tableOrderForm" name="tableOrderForm"
		action="orderList.action" method="post">
		<input type="hidden" name="tid" id="tid" /><input type="hidden"
			value="delete" name="actionType" id="actionType" />
	</form>
</body>
</html>