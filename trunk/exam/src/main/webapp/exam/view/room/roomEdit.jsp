<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上报名-合肥猎头网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/exam/view/css/user.css" type="text/css"
	media="all" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<script type="text/javascript">
<!--

var office = new Array();
<c:forEach items="${RoomEditCommand.offices }" var="office" varStatus="status">
office[${status.index }] = new Object();
    office[${status.index }].id = '${office.id}';
    office[${status.index }].name = '${office.name}' + '(' +  '${office.code}' + ')';
    office[${status.index }].departId = '${office.departId}';
</c:forEach>

function changeDeart() {
	var departId = document.getElementById("departId").value;
	var officeEl = document.getElementById("officeId");
	officeEl.options.length=0;
	var i = 0;
	for (; i < office.length; i++) {
		if (office[i].departId == departId) {
			officeEl.options.add(new Option(office[i].name, office[i].id)); //这个兼容IE与firefox
		}
	}
}
//-->
</script>
</head>
<body>
	<form:form name="roomEditForm" method="post"
		action="/exam/roomEdit.action" commandName="RoomEditCommand">
		<form:hidden path="id" />
		<div class="whiteBG">
			<div class="header_logo"></div>
		</div>
		<div class="midBox">
			<div class="midTitle">考场追加/编辑</div>
			<div class="cnt">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="300"
							style="padding-top: 15px;">
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#f7f7f7">
									<td align="right"><font color="#ff0000">*</font>编号：</td>
									<td align="left"><form:input path="code" maxlength="2"
											size="15" cssClass="bbsInput_short" /> <font color="#ff0000">&nbsp;&nbsp;<form:errors
												path="code" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>座位数：</td>
									<td align="left"><form:input path="seats" maxlength="2"
											size="15" cssClass="bbsInput_short" /><font color="#ff0000">&nbsp;&nbsp;<form:errors
												path="seats" /></font></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right"><font color="#ff0000">*</font>说明：</td>
									<td align="left"><form:input path="name" maxlength="32"
											size="15" cssClass="bbsInput_short" /><font color="#ff0000">&nbsp;&nbsp;<form:errors
												path="name" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>位置：</td>
									<td align="left"><form:input path="position"
											maxlength="128" size="15" cssClass="bbsInput_short" /> <font
										color="#ff0000">&nbsp;&nbsp;<form:errors
												path="position" /></font></td>
								</tr>

								<tr bgcolor="#f7f7f7">
									<td align="right">报考部门：</td>
									<td align="left"><form:select path="departId"
											id="departId" items="${RoomEditCommand.departs }"
											itemLabel="name" itemValue="id" onchange="changeDeart();">
										</form:select></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right">岗位类别：</td>
									<td align="left"><form:select path="officeId"
											id="officeId">
										</form:select></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td height="22" colspan="4">
										<div align="center">
											<input type="submit" value="确定" name="_target4" />&nbsp;&nbsp;
											<input type="reset" value="清除" /> &nbsp;&nbsp;<input
												type="button" value="返回" style="width: 50px;" onclick="javascript:document.location='/exam/roomList.action'" />
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form:form>
</body>
<script type="text/javascript">
<!--
 changeDeart();
 if ('${RoomEditCommand.officeId}' != '') {
	 document.getElementById("officeId").value='${RoomEditCommand.officeId}'; 
 }
//-->
</script>
</html>