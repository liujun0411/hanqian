<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<base href="<%=basePath%>" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
		
		<script type="text/javascript">
	function setReportRate() {
		var rate = document.getElementById("rate").value;
		var typeid = document.getElementById("typeid").value;
		var reportDate = document.getElementById("reportDate").value;
		if (reportDate == null || reportDate == "") {
			alert('请选择上报时间');
			return;
		}
		var date = reportDate.substr(0, 5);
		reportDate = date.substr(0, 2);
		reportDate += date.substr(3, 5);
		var reportip = document.getElementById("reportip").value;
		var json = '{"rate":"' + rate + '","reporttime":"' + reportDate
				+ '","reportip1":"' + reportip + '"}';
		jQuery.post('reportRate_updateReportRate.action', {
			json : json,
			typeid : typeid
		}, function(x) {
			if (x == "true") {
				document.getElementById("showmessage").innerHTML = "操作成功";
			} else {
				document.getElementById("showmessage").innerHTML = "操作失败";
			}
		});
	}
</script>
	</head>
	<body>
		
		<div id="admin_nr_rightg">
		
		   <div class="canshusz_btbg_1">
			<table width="780" border="0">
					<tr style="height:30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							频率设置——设置频率
						</td>
					</tr>
				</table>
			</div>
		
		<span id="showmessage" class="admin_bgclor_e3f admin_zt_14 admin_clor_f00"></span>
				<table width="600" border="0" class="gai_left_duiqi">
					<tr height="30">
						<td></td>
						<td></td>
					</tr>
					<tr height="50">
						<td>
							<div class="shujujk_cansu_bg_4">
								<table width="100%" border="0">
									<tr height="10">
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td width="65"></td>
										<td class="shujujk_cansu_zt">
											设定上报频率
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td align="right">
							<div class="shujujk_cansu_bg_3">
								<table width="100%" border="0">
									<tr height="10">
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td width="65"></td>
										<td class="shujujk_cansu_zt" align="left">
											设定上报时间
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr height="150">
						<td class="shebeigl_inp_zt" valign="top">
							<br />
							上报频率:
							<select name="rate" id="rate" class="louyu_xiala_kt">
								<option value="1" selected="selected">
									每日一次
								</option>
								<option value='2'>
									每周一次
								</option>
								<option value='3'>
									每月一次
								</option>
								<option value='4'>
									每季度一次
								</option>
								<option value='5'>
									每年一次
								</option>
							</select>

						</td>
						<td class="shebeigl_inp_zt" valign="top" align="right">
							<br />
							上报时间:
							<input name="reportDate" id="reportDate" type="text"
								class="Wdate" value=""
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" />
						</td>
					</tr>
					<tr height="50">
						<td>
							<div class="shujujk_cansu_bg_2">
								<table width="100%" border="0">
									<tr height="10">
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td width="65"></td>
										<td class="shujujk_cansu_zt">
											设定上报地址
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td align="right">
							<div class="shujujk_cansu_bg_1">
								<table width="100%" border="0">
									<tr height="10">
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td width="65"></td>
										<td class="shujujk_cansu_zt" align="left">
											设定上报内容
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr height="50">
						<td class="shebeigl_inp_zt" valign="top">
							<br />
							上报地址:
							<select name="reportip" id="reportip" class="louyu_xiala_kt">
								<c:forEach items="${reportIp}" var="ip">
									<option value="${ip}">
										${ip}
									</option>
								</c:forEach>
							</select>

						</td>
						<td class="shebeigl_inp_zt" valign="top" align="right">
							<br />
							上报类型:
							<select name="typeid" id="typeid" class="louyu_xiala_kt">
								<c:forEach items="${reportTypes}" var="r">
									<option value="${r.typeId}">
										${r.name}
									</option>
								</c:forEach>
							</select>

						</td>
					</tr>
				</table>
				<table width="780" border="0" class="gai_left_duiqi">
					<tr>
						<td width="232"></td>
						<td width="371"></td>
						<td>
							<a href="javascript:void(0);" class="btn blue" onclick="setReportRate();">保存</a>
						</td>
					</tr>
				</table>
			</div>
	</body>
</html>
