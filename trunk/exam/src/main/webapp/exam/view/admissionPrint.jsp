<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上报名-合肥猎头网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
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
<body>
<form:form name="admissionForm" method="post"
		action="/exam/admissionPrint.action" commandName="AdmissionPrintCommand">
	<div>
		<div>
			<table width="800" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#e1e1e1">
				<tr>
					<td height="32" align="center" bgcolor="#FFFFFF" valign="middle"
						style="padding: 30px;"><b>${AdmissionPrintCommand.exam.name}<br />
							准考证
					</b></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td valign="top" align="center" style="padding: 10px;">
						<table width="100%" border="0" cellpadding="4" cellspacing="1"
							bgcolor="#000000">
							<tr bgcolor="ffffff">
								<td align="center">姓名</td>
								<td align="center">${AdmissionPrintCommand.user.name}</td>
								<td align="center">准考<br />证号</td>
								<td align="center">${AdmissionPrintCommand.admission.code}</td>
								<td align="center" rowspan="5" width="120">
									<img src="/exam/imageDownload?userId=${AdmissionPrintCommand.idCardNo}" width="120" />
								</td>
							</tr>
							<tr bgcolor="ffffff">
								<td align="center">身份<br />证号</td>
								<td align="center">${AdmissionPrintCommand.idCardNo}</td>
								<td align="center">考点<br />名称</td>
								<td align="center">${AdmissionPrintCommand.admission.seat.room.name}</td>
							</tr>
							<tr bgcolor="ffffff">
								<td align="center">职位<br />部门</td>
								<td align="center">${AdmissionPrintCommand.apply.applyDepartName}</td>
								<td align="center">考点<br />地址</td>
								<td align="center">${AdmissionPrintCommand.admission.seat.room.position}</td>
							</tr>
							<tr bgcolor="ffffff">
								<td align="center">岗位<br />类别 </td>
								<td align="center">${AdmissionPrintCommand.apply.applyOfficeName}</td>
								<td align="center">考场</td>
								<td align="center">${AdmissionPrintCommand.admission.seat.room.code}</td>
							</tr>
							<tr bgcolor="ffffff">
								<td align="center">岗位<br />编号</td>
								<td align="center">${AdmissionPrintCommand.apply.applyOfficeCode}</td>
								<td align="center">座位</td>
								<td align="center">${AdmissionPrintCommand.admission.seat.code}</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td style="padding: 10px;text-align:left;">
						考试安排：${AdmissionPrintCommand.exam.examDate}<br />
						时间：${AdmissionPrintCommand.exam.examTime}
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td style="padding: 10px;">
						<table width="100%" border="0" cellpadding="4" cellspacing="0">
							<tr><td align="center">考生注意事项</td></tr>
							<tr><td align="center">*****************特别提示：考生于考场内请勿携带贵重物品，否则后果自负。*****************</td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr><td align="left">请考生于考试前熟悉考试环境，必须阅知《考生注意事项》，在招考公告规定时间内及时打印并妥善保存本人准考证，过期不予补办，丢失责任自负。</td></tr>
							<tr><td align="left">1.考前20分钟，考生凭《准考证》、《身份证》原件进入规定的考场。</td></tr>
							<tr><td align="left">2.考生入场除表的文具，如钢笔、圆珠笔、2B铅笔外，禁止携带任何书籍、笔记、资料、报刊、草稿纸以及各种无线通讯工具、电子记事本等考试无关的东西。</td></tr>
							<tr><td align="left">3.考生入场后，要按号入座，将本人《准考证》、《身份证》放在桌面右上角，以便核验。</td></tr>
							<tr><td align="left">4.统一开考信号发出后才能开始答题。</td></tr>
							<tr><td align="left">5.开考30分钟后考生不得进入考场，考试结束前30分钟方准交卷出场。交卷出场后不得再进行续考。</td></tr>
							<tr><td align="left">6.考生领到试卷后，应先到指定位置准确、清楚地填涂姓名、准考证号等栏目。凡漏填、错填或字迹不清，无法辨认的试卷一律作废。</td></tr>
							<tr><td align="left">7.考生必须严格按试卷要求一律用蓝色、黑色钢笔或签字笔，答卷禁止使用涂改液，不按上述规定要求作答的其他卷无效（答题卡请按规定要求填涂）。</td></tr>
							<tr><td align="left">8.考生试卷分发错误及试题字迹不清等问题可举手询问，监考人员应当众答复。试卷及试题的疑问，不得向监考人员询问。</td></tr>
							<tr><td align="left">9.考生在考场时必须保持安静，严格遵守考场纪律，不准交头接耳、左顾右盼；不准偷看、抄袭他人答卷；不准夹带、冒名替考或换卷；不准吸烟。</td></tr>
							<tr><td align="left">10.考生离开考场必须交卷，不得携带试卷和草稿纸离开考场。离开考场后不准在考场附近逗留和交谈。</td></tr>
							<tr><td align="left">11.考生应自觉服从监考人员管理，不得以任何理由妨碍监考人员进行正常工作。监考人员有权对考场内发生的问题按规定作出处理，对扰乱考场秩序、恐吓或威胁监考人员人身安全的考生将交公安机关追究责任。</td></tr>
							<tr><td align="left">12.对违反考生须知，不服从监考人员管理的违纪舞弊考生，将根据情节轻重给予取消考试成绩处理。</td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr><td align="center">---------------------------------------------------成绩公布---------------------------------------------------</td></tr>
							<tr><td align="center">按公告笔试第三点进行成绩公布。</td></tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form:form>
</body>
</html>