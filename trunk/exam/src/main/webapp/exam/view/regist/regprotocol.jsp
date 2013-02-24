<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/view/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="/exam/view/css/user.css" type="text/css"
	media="all" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网上报名-合肥猎头网</title>
</head>
<body>
	<div class="whiteBG">
		<div class="header_logo"></div>
	</div>

	<div class="midBox">
		<div class="midTitle">用户报名</div>
		<div class="cnt">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" bgcolor="#E1E1E1">
				<tr>
					<td height="32" align="right" bgcolor="#FFFFFF" valign="middle"><b><span
							style="color: #666666">步骤：1.填写个人信息并设置密码 - 2.开始报名 - <span
								style="color: #ff3300">3.阅读报名须知并签订诚信承诺书</span> - 4.填写资格审查表 -
								5.选择报考职位 - 6.上传照片 - 7.确认填写信息
						</span></b>&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFFFFF" style="padding-top: 30px;">
						<span style="color: #666666; font-size: 14px; font-weight: 600">3.阅读报名须知并签订诚信承诺书</span><br />
						<textarea name="textarea" cols="100" rows="22" readonly
							style="padding-top: 15px; font-size: 12px">
                                                       网上报名协议

１、考生必须完全同意所有条款，方可进行网上报名。

２、考生本人已经认真阅读、理解并认同本网站提供的关于本项考试全部文件的内容，确信本人符合本项考试报名条件，并且自愿遵守《考场规则》和考试纪律。考生不符合报名条件而报名的，一经发现即取消报考资格,考试成绩无效；考生考试时如发生违纪违规行为，按有关规定严肃处理。

３、考生保证本人提交的个人信息资料真实、正确，并将对个人信息不真实或不正确而导致无法参加考试以及其他直接或间接后果负责。

４、考生本人在提交个人信息并确认报名后，即作出该考生对所提交信息内容真实性、正确性的承诺，不得再作更改。

５、考生本人报名成功后，将使用自己设定的用户名和密码作为登录本网站的依据。考生本人应妥善保管用户名和密码并保证不向任何他人透露。否则，后果自负。

６、考生本人理解并同意，在报名过程中出现不可抗力所引起的无法正常报名的情况，本网站不承担任何直接或间接的责任。

７、考生本人理解并同意，在报名成功后，由于本网站系统出现问题影响考生参加考试，考生提出赔偿的，本网站可能提供的补偿仅限于在规定时限内帮助安排重新报名，不承担任何其他责任。

                </textarea> <br /> <br /> <a href="#"
						onclick="javascript:window.open('view/promise.html')">诚信承诺书</a><br />
						<span style="color: #CC3300">点击同意，表示您已阅读并同意遵守本网站用户服务协议。<br />
							<br />
					</span> <form:form name="registForm" method="post"
							action="/exam/regist.action" commandName="RegistCommand">
							<input type="submit" value="同意" name="_target3" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" name="_cancel" value="不同意" />
						</form:form> <br />
					<br /> <br />
					</td>
				</tr>
			</table>

		</div>
	</div>
	<div class="whiteBG"></div>
</body>
</html>
