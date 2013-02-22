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
		<div class="midTitle">报考情况一览</div>
		<div class="cnt">
			<form:form name="registForm" method="post"
				action="/exam/applyinfo.action" commandName="RegistCommand">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#E1E1E1">
					<tr>
						<td height="32" align="left" bgcolor="#FFFFFF" valign="middle"
							style="padding-left: 10px;"><b><span
								style="color: #666666">职位报考情况一览</span></b></td>
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
							<table width="98%" border="0" cellpadding="8" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#f7f7f7">
									<th style="width: 8%;">地区</th>
									<th style="width: 10%;">部门</th>
									<th style="width: 8%;">岗位编号</th>
									<th style="width: 12%;">岗位类别</th>
									<th style="width: 8%;">计划数</th>
									<th style="width: 8%;">报名人数</th>
									<th style="width: 11%;">通过审核人数</th>
									<th style="width: 8%;">报考比例</th>
									<th style="width: 12%;">专业要求</th>
									<th style="width: 15%;">岗位要求</th>
								</tr>
								<c:forEach items="${RegistCommand.offices}" var="office">
									<tr bgcolor="#ffffff">
										<td>${office.cityName}</td>
										<td>${office.departName}</td>
										<td>${office.code}</td>
										<td>${office.name}</td>
										<td>${office.recruits }</td>
										<td>${office.applyCount }</td>
										<td>${office.validataCount }</td>
										<td>1:${office.scale }</td>
										<td align="left">${office.major }</td>
										<td align="left">${office.description }</td>
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
