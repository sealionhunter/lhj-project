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
</head>
<body>
	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>
	<div class="midBox">
		<div class="midTitle">网上报名</div>
		<div class="cnt">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="right" bgcolor="#FFFFFF" valign="middle"><b><span
							style="color: #666666">步骤：1.填写个人信息并设置密码-2.开始报名-3.阅读报名须知并签订诚信承诺书-4.填写资格审查表-
								5.选择报考职位-<span style="color: #ff3300">6.上传照片</span>-7.确认填写信息
						</span></b>&nbsp;&nbsp;</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td valign="top" align="center" height="140"><span
						style="color: #666666; font-size: 14px; font-weight: 600"><br />
							6.上传照片 </span><br /> <br />
						<table width="80%" border="0" cellpadding="4" cellspacing="1"
							bgcolor="#E1E1E1">
							<tr bgcolor="#ffffff">
								<td width="21%" align="right">特别提示：</td>
								<td align="left" width="79%">1.照片必须为jpg格式，宽度1-2厘米，高度2-3厘米，分辨率约为300-500像素，大小为100kb以下；</td>
							</tr>
							<tr bgcolor="#ffffff">
								<td align="right">&nbsp;</td>
								<td align="left" width="79%">2.请保证照片长宽比例同二寸证件照长宽之比，打印效果清晰；</td>
							</tr>
							<tr bgcolor="#ffffff">
								<td align="right">&nbsp;</td>
								<td align="left">3.考生点击浏览按钮找到所需照片并选中，再点击下一步按钮即可。</td>
							</tr>
						</table></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td valign="top" align="center" height="300"
						style="padding-top: 15px;">
						<table width="80%" border="0" cellpadding="4" cellspacing="1"
							bgcolor="#E1E1E1">
							<tr bgcolor="#f7f7f7">
								<td colspan="4" align="center"><font color="#CC3300">照片示例</font></td>
							</tr>
							<tr bgcolor="#f7f7f7">
								<td align="center"><img src="/exam/view/images/photo1.jpg"
									width="102" height="140" /></td>
								<td align="center"><img src="/exam/view/images/photo2.jpg"
									width="102" /></td>
								<td align="center"><img src="/exam/view/images/photo3.jpg"
									width="102" /></td>
								<td align="center"><img src="/exam/view/images/photo4.jpg"
									height="140" /></td>
							</tr>
							<tr bgcolor="#f7f7f7">
								<td align="center">合格</td>
								<td align="center">不合格</td>
								<td align="center">不合格</td>
								<td align="center">不合格</td>
							</tr>
							<form:form name="registForm" method="post"
								action="/exam/regist.action" commandName="RegistCommand"
								enctype="multipart/form-data">
								<tr bgcolor="#ffffff">
									<td colspan="4" align="center"><input size="50"
										type="file" name="uploadPhoto" id="uploadPhoto" /> <font
										color="#ff0000">&nbsp;&nbsp;<form:errors /></font></td>

								</tr>
								<tr bgcolor="#ffffff">
									<td height="22" colspan="4">
										<div align="center">
											<input type="submit" value="上一步" style="width: 50px;" name="_target4" />
											&nbsp;&nbsp; 
											<input type="submit" value="下一步" style="width: 50px;" name="_target6" />
										</div>
									</td>
								</tr>
							</form:form>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
