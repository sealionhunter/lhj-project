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
</head>
<body>

	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>

	<div class="midBox">
		<div class="midTitle">报名人员信息一览</div>
		<div class="cnt">
			<form:form name="registForm" method="post"
				action="/exam/applyinfo.action" commandName="RegistCommand">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">报名人员信息一览</span></b></td>
					</tr>
					<%--
	<tr bgcolor="#FFFFFF"> 
        <td valign="top" align="center" height="45" style="padding-top:10px;">
            <table width="95%"  border="0" cellpadding="4" cellspacing="0" bgcolor="#E1E1E1">
                <tr bgcolor="#ffffff">
                    <td style="height:10px;text-align:right;width:10%;">
                        请选择部门：</td>
					<td style="height:10px;text-align:left;">
                        <form:select path="applyDeptId"  items="${RegistCommand.departs }" itemLabel="name" itemValue="id">
                        </form:select>
					</td>
                </tr>
            </table>
        </td>
    </tr> --%>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="448"
							style="padding-top: 5px;">
							<table width="95%" border="0" cellpadding="8" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#f7f7f7">
									<th style="width: 10%;">姓名</th>
									<th style="width: 8%;">身份证号</th>
									<th style="width: 8%;">籍贯</th>
									<th style="width: 8%;">政治面貌</th>
									<th style="width: 20%;">部门</th>
									<th style="width: 8%;">报考岗位类别</th>
									<th style="width: 8%;">报考岗位编号</th>
									<th style="width: 8%;">报名岗位要求</th>
									<th style="width: 12%;">审核状态</th>
									<th></th>
								</tr>
								<c:forEach items="${RegistCommand.applyUsers }" var="applyUser">
									<tr bgcolor="#ffffff">
										<td>${applyUser.applyUserName}</td>
										<td>${applyUser.idCardNo}</td>
										<td>${applyUser.applyDepartName}</td>
										<td>${applyUser.applyOfficeName}</td>
										<td>${applyUser.applyOfficeCode}</td>
										<c:choose>
											<c:when test="${applyUser.state != '2' }">
												<td>已审核</td>
											</c:when>
											<c:otherwise>
												<td>未审核</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
			</form:form>
		</div>

	</div>

</body>
</html>
