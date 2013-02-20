<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上报名-合肥猎头网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/exam/view/css/user.css" type="text/css" media="all" />
<script text="text/javascript">
<!--

var office = new Array();
<c:forEach items="${RegistCommand.offices }" var="office" varStatus="status">
office[${status.index }] = new Object();
    office[${status.index }].id = '${office.id}';
    office[${status.index }].name = '${office.name}' + '(' +  '${office.code}' + ')';
    office[${status.index }].departId = '${office.departId}';
</c:forEach>

function changeDeart() {
	var departId = document.getElementById("applyDeptId").value;
	var officeEl = document.getElementById("applyPostId");
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

<div class="whiteBG">
  <div class="header_logo">
  </div>
</div>

<div class="midBox">
<div class="midTitle">
    网上报名
</div>
<div class="cnt">
<form:form name="registForm" method="post" action="/exam/regist.action" commandName="RegistCommand">

	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#E1E1E1">
	    <tr> 
	        <td height="32" align="right" bgcolor="#FFFFFF" valign="middle"><b><span style="color:#666666">步骤：1.填写个人信息并设置密码 - 2.开始报名 - 3.阅读报名须知并签订诚信承诺书 - 4.填写资格审查表 - <span style="color:#ff3300">5.选择报考职位</span> - 6.上传照片 - 7.确认填写信息</span></b>&nbsp;&nbsp;</td>
	    </tr>
	    <tr bgcolor="#FFFFFF">
	        <td valign="top" align="center" height="300">
	        <span align="center" style="color:#666666;font-size:14px;font-weight:600"><br />
	            5.选择报考岗位
	        </span><br /><br />
	            <table width="80%"  border="0" cellpadding="4" cellspacing="1" bgcolor="#E1E1E1">
	                <tr bgcolor="#ffffff"> 
	                    <td width="21%" align="right">报考人姓名：</td>
	                    <td align="left" width="79%">${RegistCommand.name}</td>
	                </tr>
	                <tr bgcolor="#ffffff"> 
	                    <td align="right">证件号码：</td>
	                    <td align="left" width="79%">${RegistCommand.idCardNo}</td>
	                </tr>
	                <tr bgcolor="#ffffff"> 
	                    <td align="right">考试名称：</td>
	                    <td align="left" width="79%">${RegistCommand.registExamName}</td>
	                </tr>
	                <tr bgcolor="#f7f7f7"> 
	                    <td align="right">报考所在市：</td>
	                    <td align="left">
	                    	<form:select path="applyCityId"  items="${RegistCommand.cities }" itemLabel="name" itemValue="id">
	                        </form:select>
	                    </td>
	                </tr>
	                <tr bgcolor="#f7f7f7"> 
	                    <td align="right">报考部门：</td>
	                    <td align="left">
	                        <form:select path="applyDeptId" id="applyDeptId"  items="${RegistCommand.departs }" itemLabel="name" itemValue="id" onchange="changeDeart();">
	                        </form:select>
	                    </td>
	                </tr>
	                <tr bgcolor="#f7f7f7"> 
	                    <td align="right">岗位类别：</td>
	                    <td align="left">
	                        <form:select path="applyPostId" id="applyPostId">
	                        </form:select>
	                        <%-- <input type="button" value="查看此职位报名状况" />--%>
	                    </td>
	                </tr>
	                <tr bgcolor="#ffffff"> 
		                    <td height="22" colspan="4"><div align="center">
		                        <input type="submit" value="上一步" name="_target3">
		                          &nbsp;&nbsp; 
			                    <input type="submit" value="下一步" name="_target5">
		                        </div>
		                    </td>
	                </tr>
	            </table>
	        </td>
	    </tr>
	    <tr bgcolor="#FFFFFF">
	        <td valign="top" align="center" height="300" style="padding-top:15px;">
	            <table width="80%"  border="0" cellpadding="4" cellspacing="1" bgcolor="#E1E1E1">
	                    <%--
	                    <tr bgcolor="#f7f7f7"> 
	                        <th rowspan="2" align="center">招聘部门</th>
	                        <th rowspan="2" align="center">岗位类别</th>
	                        <th rowspan="2" align="center">岗位编号</th>
	                        <th rowspan="2" align="center">招聘职位数</th>
	                        <th colspan="4" align="center">职位资格条件</th>
	                    </tr>
	                    <tr bgcolor="#f7f7f7"> 
	                        <th>专业</th>
	                        <th>学历</th>
	                        <th>年龄</th>
	                        <th>其他</th>
	                    </tr>
	            <c:forEach items="${RegistCommand.offices }" var="office">
	                    <tr bgcolor="#ffffff">
	                        <td>${office.departName}</td>
	                        <td>${office.name}</td>
	                        <td>${office.code}</td>
	                        <td>${office.recruits }</td>
	                        <td>${office.major }</td>
	                        <td>${office.degree }</td>
	                        <td>${office.limitAge }</td>
	                        <td>${office.description }</td>
	                    </tr>
	            
	            </c:forEach>
	             --%>
	              <tr bgcolor="#f7f7f7"> 
                        <th align="center">招聘部门</th>
                        <th align="center">岗位类别</th>
                        <th align="center">岗位编号</th>
                        <th align="center">招聘职位数</th>
						<th align="center">专业要求</th>
						<th align="center">岗位要求</th>
                    </tr>
                    <tr bgcolor="#ffffff">
                        <td rowspan="3">办公室</td>
                        <td>管理岗位</td>
                        <td>101</td>
                        <td>1</td>
                        <td>中文及相关专业</td>
                        <td></td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td>管理岗位</td>
                        <td>102</td>
                        <td>1</td>
                        <td>中文及相关专业</td>
                        <td>应届毕业生</td>
                    </tr>
                    <tr bgcolor="#ffffff"> 
                        <td>专业技术岗位</td>
                        <td>103</td>
                        <td>1</td>
                        <td>计算机、动漫、文案策划</td>
                        <td>有较强的计算机专业知识</td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td rowspan="2">财政局</td>
                        <td>专业技术岗位</td>
                        <td>201</td>
                        <td>2</td>
                        <td>财务管理、金融等相关专业</td>
                        <td>有较强的金融专业知识 </td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td>专业技术岗位</td>
                        <td>202</td>
                        <td>2</td>
                        <td>会计、审计等相关专业 </td>
                        <td>有较强的财会专业知识</td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td rowspan="4">规划建设局</td>
                        <td>专业技术岗位</td>
                        <td>301</td>
                        <td>3</td>
                        <td>建设工程类等相关专业</td>
                        <td>有较强的建设工程专业知识</td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td>专业技术岗位</td>
                        <td>302</td>
                        <td>1</td>
                        <td>土地管理专业</td>
                        <td>有较强的土地管理专业知识</td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td>专业技术岗位</td>
                        <td>303</td>
                        <td>1</td>
                        <td>规划专业</td>
                        <td>有较强的城市规划专业知识</td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td>专业技术岗位</td>
                        <td>304</td>
                        <td>1</td>
                        <td>环保专业</td>
                        <td>有较强的环境保护专业知识</td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td rowspan="1">社会事业局</td>
                        <td>专业技术岗位</td>
                        <td>401</td>
                        <td>4</td>
                        <td>社会管理等相关专业</td>
                        <td>社区管理工作经验</td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td rowspan="4">经贸（招商）局</td>
                        <td>管理岗位</td>
                        <td>501</td>
                        <td>1</td>
                        <td>专业不限</td>
                        <td>具有招商引资经验，有较强的社交沟通能力</td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td>管理岗位 </td>
                        <td>502</td>
                        <td>2</td>
                        <td>专业不限</td>
                        <td>应届毕业生，有较强的社交沟通能力 </td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td>专业技术岗位 </td>
                        <td>503</td>
                        <td>1</td>
                        <td>统计</td>
                        <td>有较强的统计专业知识 </td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td>专业技术岗位 </td>
                        <td>504</td>
                        <td>1</td>
                        <td>英语</td>
                        <td>全日制本科及以上、具有相应学位，<br />英语专业具有专业八级证书</td>
                    </tr>
					<tr bgcolor="#ffffff"> 
                        <td>企业服务中心</td>
                        <td>管理岗位 </td>
                        <td>601</td>
                        <td>3</td>
                        <td>专业不限</td>
                        <td>&nbsp;</td>
                    </tr>
	            </table>
	        </td>
	    </tr>
	</table>
</form:form>
</div>

</div>

</body>
<script text="text/javascript">
<!--
 changeDeart();
document.getElementById("applyPostId").value='${RegistCommand.applyPostId}';
//-->
</script>
</html>
