<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上报名-合肥猎头网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:SimSun;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:SimHei;
	panose-1:2 1 6 9 6 1 1 1 1 1;}
@font-face
	{font-family:"Cambria Math";
	panose-1:2 4 5 3 5 4 6 3 2 4;}
@font-face
	{font-family:FangSong_GB2312;
	panose-1:2 1 6 9 6 1 1 1 1 1;}
@font-face
	{font-family:"\@SimSun";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"\@SimHei";
	panose-1:2 1 6 9 6 1 1 1 1 1;}
@font-face
	{font-family:"\@FangSong_GB2312";}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0mm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Times New Roman","serif";}
 /* Page Definitions */
 @page WordSection1
	{size:595.3pt 841.9pt;
	margin:20.0mm 20.0mm 20.0mm 20.0mm;
	layout-grid:15.6pt;}
div.WordSection1
	{page:WordSection1;}
-->
</style>
<script language="JavaScript" type="text/javascript">
	var HKEY_Path = "HKEY_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	function PageSetup(name, value) {
		try {
			var Wsh = new ActiveXObject("WScript.Shell");
			Wsh.RegWrite(HKEY_Path + name, value);
		} catch (e) {
		}
	}
	PageSetup('header', '');
	PageSetup('footer', '');
</script>
</head>
<body lang=ZH-CN style='text-justify-trim:punctuation'>
<form:form name="admissionForm" method="post"
		action="/exam/admissionPrint.action" commandName="AdmissionPrintCommand">
	<div>
		<div>
			<table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#ffffff"><tr><td align="center">
			<table width="670" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#e1e1e1">
				<tr>
					<td valign="top" align="center" bgcolor="#FFFFFF" valign="middle"
						style="padding: 3px;"><p class=MsoNormal align=center style='text-align:center;line-height:16pt'><span
lang=ZH-CN style='font-size:14.0pt;font-family:SimSun'>阜阳合肥现代产业园区管委会招聘工作人员<br />笔试准考证</span></p>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td valign="top" align="center" style="padding: 3px;">
						<table width="100%" border="1" cellpadding="4" cellspacing="0"
							bgcolor="#000000">
							<tr bgcolor="ffffff" style="height:35px">
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>姓名</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.user.name}</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>准考证号</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.admission.code}</span></p></td>
								<td align="center" rowspan="5" width="120">
									<img src="/exam/imageDownload?userId=${AdmissionPrintCommand.idCardNo}" width="120" />
								</td>
							</tr>
							<tr bgcolor="ffffff" style="height:35px">
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>身份证号</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.idCardNo}</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>考点名称</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.admission.seat.room.name}</span></p></td>
							</tr>
							<tr bgcolor="ffffff" style="height:35px">
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>职位部门</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.apply.applyDepartName}</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>考点地址</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.admission.seat.room.position}</span></p></td>
							</tr>
							<tr bgcolor="ffffff" style="height:35px">
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>岗位类别 </span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.apply.applyOfficeName}</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>考场号</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.admission.seat.room.code}</span></p></td>
							</tr>
							<tr bgcolor="ffffff" style="height:35px">
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>岗位编号</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.apply.applyOfficeCode}</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>座位号</span></p></td>
								<td align="center"><p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>${AdmissionPrintCommand.admission.seat.code}</span></p></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td style="padding: 3px;text-align:left;">
						<p class=MsoNormal style='line-height:14.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>考试时间： <fmt:formatDate value="${AdmissionPrintCommand.exam.examDate}" pattern="yyyy-MM-dd"/>&nbsp;${AdmissionPrintCommand.exam.examTime}</span></p>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td style="padding: 3px;">
						
<div class=WordSection1 style='layout-grid:15.6pt'>

<p class=MsoNormal align=center style='text-align:center;line-height:16.0pt'><b><span
lang=ZH-CN style='font-size:15.0pt;font-family:SimSun'>考生注意事项</span></b></p>

<p class=MsoNormal align=center style='text-align:center;line-height:10.0pt'><b><span
lang=EN-US style='font-size:15.0pt;font-family:SimSun'>&nbsp;</span></b></p>

<p class=MsoNormal style='line-height:15.0pt'><span lang=EN-US
style='font-size:12.0pt;font-family:SimSun'>*********** </span><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>特别提示：考生于考场内请勿携带贵重物品，否则后果自负。</span><span
lang=EN-US style='font-size:12.0pt;font-family:SimSun'>************ </span></p>

<p class=MsoNormal style='line-height:12.0pt'><span lang=EN-US
style='font-size:12.0pt;font-family:SimSun'>&nbsp; </span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>请考生于考试前熟悉考试环境，必须阅知《考生注意事项》，在招考公告规定时间内及时打印并妥善保存本人准考证，过期不予补办，丢失责任自负。</span><span
lang=EN-US style='font-size:12.0pt;font-family:SimSun'> </span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>1.考前30分钟，考生凭《准考证》、《身份证》原件进入规定的考场。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>2.考生入场除必要的文具，如钢笔、签字笔、2B铅笔外，禁止携带任何书籍、笔记、资料、报刊、草稿纸以及各种无线通讯工具、电子记事本、计算器等考试无关的东西。考生应将手机关闭放入包中，未带包的放入手机袋中，并在袋上写上姓名、准考证号；同时将随身携带书籍、笔记本、手提包等统一放置在监考老师指定位置。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>3.考生入场后，要对号入座，将本人《准考证》、《身份证》放在桌面右上角，以便核验。</span><span
lang=EN-US style='font-size:12.0pt;font-family:SimSun'> </span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>4.统一开考信号发出后才能开始答题。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>5.开考30分钟后考生不得进入考场，考试结束前30分钟方准交卷出场。交卷出场后不得再进行续考。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>6.考生领到试卷、答题纸、答题卡后，应先在指定位置准确、清楚地填写（填涂）姓名、准考证号等栏目。凡漏填、错填或字迹不清，无法辨认的答题纸（卡）一律作废。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>7.考生在答题纸上作答时，必须按要求一律用蓝色、黑色钢笔或签字笔，答卷禁止使用涂改液；答题卡作答时，请按规定要求用2B铅笔填涂。不按上述规定作答的一律视为无效答卷。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>8.考生试卷分发错误及试题字迹不清等问题可举手询问，监考人员应当众答复。试卷及试题的疑问，不得向监考人员询问。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>9.考生在考场时必须保持安静，严格遵守考场纪律，不准交头接耳、左顾右盼；不准偷看、抄袭他人答卷；不准夹带、冒名替考或换卷；不准吸烟。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>10.考生离开考场必须交卷，不得携带试卷、答题纸、答题卡和草稿纸离开考场。离开考场后不准在考场附近逗留和交谈。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>11.考生应自觉服从监考人员管理，不得以任何理由妨碍监考人员进行正常工作。监考人员有权对考场内发生的问题按规定作出处理，对扰乱考场秩序、恐吓或威胁监考人员人身安全的考生将交公安机关追究责任。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:16.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimSun'>12.对违反考生须知，不服从监考人员管理的违纪舞弊考生，将根据情节轻重给予取消考试成绩处理。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:10.0pt'><span lang=EN-US style='font-size:12.0pt;font-family:SimSun'>&nbsp;
</span></p>

<p class=MsoNormal ><span
lang=ZH-CN style='font-size:12.0pt;font-family:SimHei'>-------------------------------------成绩公布-----------------------------------</span></p>

<p class=MsoNormal style='text-indent:24.0pt'><span
lang=ZH-CN style='font-size:12.0pt;font-family:FangSong_GB2312'>笔试结束后5天内在合肥猎头网（www.hfjyz.com）、阜阳合肥现代产业园区网（www.fhip.gov.cn）公布笔试成绩。</span></p>

</div>
					</td>
				</tr>
			</table>
			</td></tr></table>
		</div>
	</div>
</form:form>
</body>
</html>
