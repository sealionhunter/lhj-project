<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style/style.css" />
<link rel="stylesheet" type="text/css" href="style/left.css" />
<title>left</title>
<script type="text/JavaScript">
<!--
	function MM_preloadImages() { //v3.0
		var d = document;
		if (d.images) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
//-->
</script>
</head>
<body
	onload="MM_preloadImages('images/main_26_1.gif','images/main_29_1.gif','images/main_31_1.gif')">
	<table width="177" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					style="table-layout: fixed">
					<tr>
						<td height="26" background="images/main_21.gif">&nbsp;</td>
					</tr>
					<tr>
						<td height="80"
							style="background-image: url(images/main_23.gif); background-repeat: repeat-x;">
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="70" width="70">
										<div align="right">
											<a href="#"><img src="images/main_26.gif" name="Image1"
												width="40" height="40" border="0" id="Image1"
												onmouseover="MM_swapImage('Image1','','images/main_26_1.gif',1)"
												onmouseout="MM_swapImgRestore()"
												onclick="window.parent.rightFrame.location='<c:if test="${SESSION_LOGIN_INFO.admin }">hotelList.action</c:if><c:if test="${not SESSION_LOGIN_INFO.admin }">hotelEdit.action?uuid=${SESSION_LOGIN_INFO.hid }</c:if>'" /></a>
										</div>
									</td>
									<td>
										<div align="left" class="text">
											<a href="#"
												onclick="window.parent.rightFrame.location='<c:if test="${SESSION_LOGIN_INFO.admin }">hotelList.action</c:if><c:if test="${not SESSION_LOGIN_INFO.admin }">hotelEdit.action?uuid=${SESSION_LOGIN_INFO.hid }</c:if>'">餐厅<br />管理
											</a>
										</div>
									</td>
								</tr>
								<c:if test="${SESSION_LOGIN_INFO.admin }">
									<tr>
										<td height="70">
											<div align="right">
												<a href="#"><img src="images/main_28.gif" name="Image2"
													width="40" height="40" border="0" id="Image2"
													onmouseover="MM_swapImage('Image2','','images/main_29_1.gif',1)"
													onmouseout="MM_swapImgRestore()"
													onclick="window.parent.rightFrame.location='userList.action'" /></a>
											</div>
										</td>
										<td>
											<div align="left" class="text">
												<a href="#"
													onclick="window.parent.rightFrame.location='userList.action'">用户<br />管理
												</a>
											</div>
										</td>
									</tr>
								</c:if>
								<tr>
									<td height="70">
										<div align="right">
											<a href="#"><img src="images/main_28.gif" name="Image2"
												width="40" height="40" border="0" id="Image2"
												onmouseover="MM_swapImage('Image2','','images/main_29_1.gif',1)"
												onmouseout="MM_swapImgRestore()"
												onclick="window.parent.rightFrame.location='tableList.action'" /></a>
										</div>
									</td>
									<td>
										<div align="left" class="text">
											<a href="#"
												onclick="window.parent.rightFrame.location='tableList.action'">餐台<br />管理
											</a>
										</div>
									</td>
								</tr>
								<tr>
									<td height="70s">
										<div align="right">
											<a href="#"><img src="images/main_31.gif" name="Image3"
												width="40" height="40" border="0" id="Image3"
												onmouseover="MM_swapImage('Image3','','images/main_31_1.gif',1)"
												onmouseout="MM_swapImgRestore()"
												onclick="window.parent.rightFrame.location='foodList.action'" /></a>
										</div>
									</td>
									<td>
										<div align="left" class="text">
											<a href="#"
												onclick="window.parent.rightFrame.location='foodList.action'">菜品<br />管理
											</a>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td style="line-height: 4px; background: url(images/main_38.gif)">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
