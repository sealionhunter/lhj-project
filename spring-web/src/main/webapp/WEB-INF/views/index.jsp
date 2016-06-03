<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>FX D-MAT Input System</title>
<link rel="stylesheet" href="<c:url value="/resources/css/css.css" />"
		type="text/css" />
<script language="javascript" type="text/javascript">
    function SetCwinHeight(){
        var iframeid=document.getElementById("maincontent"); //iframe id
        if (document.getElementById){
            if (iframeid && !window.opera){
                if (iframeid.contentDocument && iframeid.contentDocument.body.offsetHeight){
                    iframeid.height = iframeid.contentDocument.body.offsetHeight;
                }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
                    iframeid.height = iframeid.Document.body.scrollHeight;
                }
            }
        }
    }
 </script>
<script src="<c:url value="/resources/js/AC_RunActiveContent.js" />" type="text/javascript"></script>
</head>
<body>
<div id="mainbody">
  <%@include file="header.jsp" %>
  <%@include file="menu.jsp" %>
  <div>
	 <iframe frameborder="0" id="maincontent" name="maincontent" width="100%" scrolling="y"  src="<c:url value="/notice/noticeList" />"
               height="580px;" style="min-height:580px;" onload="Javascript:SetCwinHeight()"></iframe>
  </div>
  <%@include file="footer.jsp" %>
</div>
</body>
</html>
