<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上报名-合肥猎头网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/exam/view/css/user.css" type="text/css" media="all" />
</head> 
<body>

<div class="whiteBG">
  <div class="header_logo">
  </div>
</div>

<div class="midBox">
<div class="midTitle">
    考试一览
</div>
<div class="cnt">
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#E1E1E1">
    <tr> 
        <td height="32" align="left" bgcolor="#FFFFFF" valign="middle" style="padding-left:10px;"><b><span style="color:#666666">考试选择</span></b></td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
        <td valign="top" align="center" height="448" style="padding-top:30px;">
            <table width="80%"  border="0" cellpadding="4" cellspacing="0" bgcolor="#E1E1E1">
                <tr bgcolor="#ffffff">
                    <td style="height:10px;text-align:left;">
                        <a href="view/Regflow.html">报名流程</a>
                    </td>
                </tr>
                <tr bgcolor="#ffffff">
                    <td style="height:10px;text-align:left;">
                        <a href="/exam/regist.action">报名登陆</a>
                    </td>
                </tr>
                <tr bgcolor="#ffffff">
                    <td style="height:10px;text-align:left;">
                        <a href="/exam/statusSearch.action">查询/打印资审结果</a>
                    </td>
                </tr>
				<tr bgcolor="#ffffff">
                    <td style="height:10px;text-align:left;">
                        <a href="/exam/regist.action?editFlg=1">信息修改</a>
                    </td>
                </tr>
				<tr bgcolor="#ffffff">
                    <td style="height:10px;text-align:left;">
                        <a href="/exam//modifyPassword.action">修改密码</a>
                    </td>
                </tr>
				<tr bgcolor="#ffffff">
                    <td style="height:10px;text-align:left;">
                        <a href="/exam/applyinfo.action">报名情况浏览</a>
                    </td>
                </tr>
				<tr bgcolor="#ffffff">
                    <td style="height:10px;text-align:left;">
                        <a href="view/admissionPrint.html">打印准考证</a>
                    </td>
                <%-- 
				<tr bgcolor="#ffffff">
                    <td style="height:10px;text-align:left;">
                        <a href="/exam/signuppeopleinfo.action">审核</a>
                    </td>
                </tr>
                 --%>
            </table>
        </td>
    </tr>
</table>
</div>

</div>

</body>
</html>
