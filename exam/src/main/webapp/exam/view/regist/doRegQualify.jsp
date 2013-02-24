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
	<form:form name="registForm" method="post" action="/exam/regist.action"
		commandName="RegistCommand">
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
								style="color: #666666">步骤：1.填写个人信息并设置密码-2.开始报名-3.阅读报名须知并签订诚信承诺书-<span
									style="color: #ff3300">4.填写资格审查表</span>-5.选择报考职位-6.上传照片-7.确认填写信息
							</span></b></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="170"><span
							style="color: #666666; font-size: 14px; font-weight: 600"><br />
								4.填写资格审查表 </span><br /> <br />
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
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
							</table></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td><b>以下信息需考生认真填写（<font color="#ff0000">*</font>为必填项）
						</b></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td valign="top" align="center" height="300"
							style="padding-top: 15px;">
							<table width="80%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#E1E1E1">
								<tr bgcolor="#f7f7f7">
									<td width="21%" align="right"><font color="#ff0000">*</font>性别：</td>
									<td align="left"><form:select path="sexCode"
											items="${RegistCommand.sexs }" itemLabel="name"
											itemValue="id.code">

										</form:select></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>民族：</td>
									<td align="left"><form:select path="nationCode"
											items="${RegistCommand.nationCodes }" itemLabel="name"
											itemValue="id.code">
										</form:select></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right"><font color="#ff0000">*</font>籍贯：</td>
									<td align="left"><form:input path="homeTown"
											maxlength="32" size="15" cssClass="bbsInput_short" /> <font
										color="#ff0000">&nbsp;&nbsp;<form:errors
												path="homeTown" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>出生年月：</td>
									<td align="left"><form:select path="birthdayYear">
											<form:option value="1983" label="1983" />
											<form:option value="1984" label="1984" />
											<form:option value="1985" label="1985" />
											<form:option value="1986" label="1986" />
											<form:option value="1987" label="1987" />
											<form:option value="1988" label="1988" />
											<form:option value="1989" label="1989" />
											<form:option value="1990" label="1990" />
											<form:option value="1991" label="1991" />
											<form:option value="1992" label="1992" />
											<form:option value="1993" label="1993" />
											<form:option value="1994" label="1994" />
											<form:option value="1995" label="1995" />
											<form:option value="1996" label="1996" />
											<form:option value="1997" label="1997" />
											<form:option value="1998" label="1998" />
											<form:option value="1999" label="1999" />
											<form:option value="2000" label="2000" />
											<form:option value="2001" label="2001" />
											<form:option value="2002" label="2002" />
											<form:option value="2003" label="2003" />
											<form:option value="2004" label="2004" />
											<form:option value="2005" label="2005" />
											<form:option value="2006" label="2006" />
											<form:option value="2007" label="2007" />
											<form:option value="2008" label="2008" />
											<form:option value="2009" label="2009" />
											<form:option value="2010" label="2010" />
											<form:option value="2011" label="2011" />
											<form:option value="2012" label="2012" />
											<form:option value="2013" label="2013" />
										</form:select>年 <form:select path="birthdayMonth">
											<form:option value="1" label="01" />
											<form:option value="2" label="02" />
											<form:option value="3" label="03" />
											<form:option value="4" label="04" />
											<form:option value="5" label="05" />
											<form:option value="6" label="06" />
											<form:option value="7" label="07" />
											<form:option value="8" label="08" />
											<form:option value="9" label="09" />
											<form:option value="10" label="10" />
											<form:option value="11" label="11" />
											<form:option value="12" label="12" />
										</form:select>月</td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right"><font color="#ff0000">*</font>政治面貌：</td>
									<td align="left"><form:select path="politicalCode"
											items="${RegistCommand.politicalCodes }" itemLabel="name"
											itemValue="id.code">
										</form:select></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>婚姻状况：</td>
									<td align="left"><form:select path="isMarried"
											items="${RegistCommand.marriedStates }" itemLabel="name"
											itemValue="id.code">

										</form:select></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right"><font color="#ff0000">*</font>最高学历：</td>
									<td align="left"><form:input path="degree" maxlength="16"
											size="15" cssClass="bbsInput_short" /> <font color="#ff0000">&nbsp;&nbsp;<form:errors
												path="degree" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>身份：</td>
									<td align="left"><form:select path="identity"
											items="${RegistCommand.identities }" itemLabel="name"
											itemValue="id.code">

										</form:select></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right"><font color="#ff0000">*</font>毕业院校：</td>
									<td align="left"><form:input path="userSchool"
											maxlength="32" size="15" cssClass="bbsInput_short" /> <font
										color="#ff0000">&nbsp;&nbsp;<form:errors
												path="userSchool" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>毕业年月：</td>
									<td align="left"><form:select path="graduateYear">
											<form:option value="1983" label="1983" />
											<form:option value="1984" label="1984" />
											<form:option value="1985" label="1985" />
											<form:option value="1986" label="1986" />
											<form:option value="1987" label="1987" />
											<form:option value="1988" label="1988" />
											<form:option value="1989" label="1989" />
											<form:option value="1990" label="1990" />
											<form:option value="1991" label="1991" />
											<form:option value="1992" label="1992" />
											<form:option value="1993" label="1993" />
											<form:option value="1994" label="1994" />
											<form:option value="1995" label="1995" />
											<form:option value="1996" label="1996" />
											<form:option value="1997" label="1997" />
											<form:option value="1998" label="1998" />
											<form:option value="1999" label="1999" />
											<form:option value="2000" label="2000" />
											<form:option value="2001" label="2001" />
											<form:option value="2002" label="2002" />
											<form:option value="2003" label="2003" />
											<form:option value="2004" label="2004" />
											<form:option value="2005" label="2005" />
											<form:option value="2006" label="2006" />
											<form:option value="2007" label="2007" />
											<form:option value="2008" label="2008" />
											<form:option value="2009" label="2009" />
											<form:option value="2010" label="2010" />
											<form:option value="2011" label="2011" />
											<form:option value="2012" label="2012" />
											<form:option value="2013" label="2013" />
										</form:select>年 <form:select path="graduateMonth">
											<form:option value="1" label="01" />
											<form:option value="2" label="02" />
											<form:option value="3" label="03" />
											<form:option value="4" label="04" />
											<form:option value="5" label="05" />
											<form:option value="6" label="06" />
											<form:option value="7" label="07" />
											<form:option value="8" label="08" />
											<form:option value="9" label="09" />
											<form:option value="10" label="10" />
											<form:option value="11" label="11" />
											<form:option value="12" label="12" />
										</form:select>月</td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right"><font color="#ff0000">*</font>工作年限：</td>
									<td align="left"><form:input path="workYears"
											maxlength="3" size="15" cssClass="bbsInput_short" />年 <font
										color="#ff0000">&nbsp;<form:errors path="workYears" /></font>
									</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>专业：</td>
									<td align="left"><form:input path="userMajor"
											maxlength="32" size="15" cssClass="bbsInput_short" /> <font
										color="#ff0000">&nbsp;&nbsp;<form:errors
												path="userMajor" /></font></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right"><font color="#ff0000">*</font>计算机程度：</td>
									<td align="left"><form:input path="computerSkill"
											maxlength="32" size="15" cssClass="bbsInput_short" /> <font
										color="#ff0000">&nbsp;&nbsp;<form:errors
												path="computerSkill" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>外语程度：</td>
									<td align="left"><form:input path="languageSkill"
											maxlength="32" size="15" cssClass="bbsInput_short" /> <font
										color="#ff0000">&nbsp;&nbsp;<form:errors
												path="languageSkill" /></font></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right"><font color="#ff0000">*</font>联系电话：</td>
									<td align="left"><form:input path="telephone"
											maxlength="12" size="15" cssClass="bbsInput_short" /> <font
										color="#ff0000">&nbsp;&nbsp;<form:errors
												path="telephone" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right"><font color="#ff0000">*</font>身高：</td>
									<td align="left"><form:input path="height" maxlength="4"
											size="14" cssClass="bbsInput_short" />cm <font
										color="#ff0000">&nbsp;&nbsp;<form:errors path="height" /></font></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">学习/培训经历：</td>
									<td align="left"><form:textarea path="trainingExp"
											cols="50" rows="4" /></td>
								</tr>
								<tr bgcolor="#f7f7f7">
									<td align="right">工作经历：</td>
									<td align="left"><form:textarea path="workExp" cols="50"
											rows="4" /></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td align="right">社会关系：</td>
									<td align="left">
										<p>
											<form:textarea path="socialRel" cols="50" rows="4" />
										</p>
										<p>
											<font color="#FF0000">格式:称谓 姓名 年龄 工作单位及任职</font><br /> 例：<br />
											&nbsp;&nbsp;父亲 王某某 54岁 某市某机关单位<br /> &nbsp;&nbsp;母亲 张某某 52岁
											某市某公司
										</p>
									</td>
								</tr>

								<tr bgcolor="#f7f7f7">
									<td height="22" colspan="4">
										<div align="center">
											<input type="submit" value="确定" name="_target4" />
											&nbsp;&nbsp; 
											<input type="reset" value="清除" /> &nbsp;&nbsp;
											<c:choose>
												<c:when test="${RegistCommand.editFlg != '1' }">
													<input type="submit" value="返回" style="width: 50px;"
														name="_target2" />
												</c:when>
												<c:otherwise>
													<input type="submit" value="返回" style="width: 50px;"
														name="_target0" />
												</c:otherwise>
											</c:choose>
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
</html>