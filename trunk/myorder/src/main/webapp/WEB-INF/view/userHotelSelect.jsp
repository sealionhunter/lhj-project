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

	$('#imgFirst').click(function(){
		$("#hotelSearchForm #actionType").val('first');
		$("#hotelSearchForm").submit();
	}) ;

    $('#imgLast').click(function(){
        $("#userHotelSearchForm #actionType").val('last');
        $("#userHotelSearchForm").submit();
    }) ;

    $('#imgBack').click(function(){
        $("#userHotelSearchForm #actionType").val('back');
        $("#userHotelSearchForm").submit();
    }) ;

    $('#imgNext').click(function(){
        $("#userHotelSearchForm #actionType").val('next');
        $("#userHotelSearchForm").submit();
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
        $("#userHotelSearchForm #paging\\.toPage").val(toPage);
        $("#userHotelSearchForm #actionType").val('toPage');
        $("#userHotelSearchForm").submit();
    }) ;
    $('[name=btnEdit]').click(function() {
		var n = $("input[name=ids]:checked").length;

		if (n < 1) {
			alert("请选择餐厅");
			return;
		}
		if (n > 1) {
			alert("只能选择一条记录");
			return;
		}
		$("#userHotelSearchOKForm #uuid").val($("input[name=ids]:checked").parent().parent().attr("id"));
		$("#userHotelSearchOKForm").submit();
	});
    $("#imgOK").click(function() {
        $("#userHotelSearchForm").submit();
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
							class="titleText">餐厅检索 -
								${user.uname}</span></td>
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
							<form id="userHotelSearchForm" name="userHotelSearchForm"
								action="userHotelSelect.action" method="post">
								<input type="hidden" name="uid" id="uid" value="${uid }" /> <input
									type="hidden" id="paging.rcount" name="paging.rcount"
									value="${paging.rcount }" /> <input type="hidden"
									id="paging.pcount" name="paging.pcount"
									value="${paging.pcount }" /> <input type="hidden"
									id="paging.pcurrent" name="paging.pcurrent"
									value="${paging.pcurrent }" /> <input type="hidden"
									id="paging.percount" name="paging.percount"
									value="${paging.percount }" /> <input type="hidden"
									id="paging.toPage" name="paging.toPage" /><input type="hidden"
									value="list" name="actionType" id="actionType" />

								<table>
									<tr>
										<td>餐厅名称</td>
										<td><input type="text" name="condition.name"
											value="${condition.name }" id="condition.name" /></td>
										<td>餐厅地址</td>
										<td><input type="text" name="condition.address"
											value="${condition.address }" id="condition.address"
											maxlength="128" /></td>
									</tr>
									<tr>
										<td>联系电话</td>
										<td><input type="text" name="condition.telNum"
											value="${condition.telNum }" id="condition.telNum"
											maxlength="16" /></td>
										<td>说明</td>
										<td><input type="text" name="condition.description"
											value="${condition.description }" id="condition.description" /></td>
									</tr>
									<tr>
										<td colspan="4" style="padding-left: 55px; padding-top: 10px;">
											<img src="images/search.gif" id="imgOK" />
										</td>
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
							class="titleText">餐厅一览</span></td>
						<td width="281" background="images/tab_05.gif"><table
								border="0" align="right" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60" style="cursor:pointer">
										<table width="90%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td><div align="center">
														<img src="images/114.gif" width="14" height="14"
															name="btnEdit" />
													</div></td>
												<td><div align="center" name="btnEdit">选择</div></td>
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
								align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98"
								onmouseover="changeto()" onmouseout="changeback()">
								<tr>
									<td class="tabTitle"><input type="checkbox"
										id="cbxSelectAll" /></td>
									<td class="tabTitle">餐厅名称</td>
									<td class="tabTitle">餐厅地址</tD>
									<td class="tabTitle">联系人</td>
									<td class="tabTitle">电话</td>
									<td class="tabTitle">说明</td>
									<td class="tabTitle">&nbsp;</td>
								</tr>
								<c:forEach items="${datas }" var="hotel" varStatus="status">

									<tr id="${hotel.uuid }" class="tabBody">
										<td class="tabBody"><input type="checkbox" name="ids"
											value="${hotel.uuid }" id="${table.uuid }" /></td>
										<td class="tabBody"><img src="${hotel.imgUrl }" height=60
											width=80 /><span>${hotel.name }</span></td>
										<td class="tabBody"><span> ${hotel.address }</span></td>
										<td class="tabBody"><span> ${hotel.telNum }</span></td>
										<td class="tabBody"><span>${hotel.description }</span></td>
										<td class="tabBody"><img src="images/037.gif" width="24"
											height="24" name="btnRowEdit" style="cursor:pointer" />&nbsp; <img
											src="images/010.gif" width="24" height="24" name="btnRowDelete" style="cursor:pointer" />&nbsp;</td>
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
	<form id="userHotelSearchOKForm" name="userHotelSearchOKForm"
		action="userHotelSearchOK.action" method="post">
		<input type="hidden" name="uuid" id="uuid" /> <input type="hidden"
			name="uid" id="uid" value="${uid }" /><input type="hidden"
			value="edit" name="actionType" id="actionType" />
	</form>
</body>
</html>