<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台——后勤管理</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<!-- 引用实时监控专用JS文件 -->
		<script type="text/javascript" src="manager/js/commonControl.js">
</script>
		<script type="text/javascript" src="manager/config/config.js">
</script>
		<script type="text/javascript"
			src="manager/javascript/previewPic/picShow.js">
</script>
		<script type="text/javascript" src="manager/js/swfobject.js">
</script>
		<script type="text/javascript">
function changeFlashLayer(obj) {
	if (obj == null) {
		if ($("#eqTypeId").val() != 4 && $("#eqTypeId").val() != 6) {
			createHotDiv(0);
		}
	} else {
		if ($("#eqTypeId").val() != 4 && $("#eqTypeId").val() != 6) {
			createHotDiv(obj);
		}
	}
}

function yeYang() {
	var a = $("#dataYeYang").val();
	return a;
}

function flashStatus() {
	var a = $("#dataYeYang").val();
	return a;
}



var doAjax = function(){
	var reVal;
	var jsonDoc = $.ajax({
		type:"post",
		url:"control_reFreshAjax.action?t=" + Math.random(),
		data:{
			eqTypeId:$("#eqTypeId").val(),
			groupId:$("#groupId").val(),
			groupCode:$("#groupCode").val(),
			groupName:$("#groupName").val(),
		},
		cache:false,
		async:false,
		error:function(jsonObj){
		},
		success:function(jsonObj){
			reVal = jsonObj.toString();
			$('#dataYeYang').val(reVal);
		}
	})
	return reVal;
}
function callExternalInterface() {
try {  doAjax();
	   var currentHospCode = $("#currentHospCode").val();
		if (thisMovie("ExternalInterfaceExample") && thisMovie("ExternalInterfaceExample") != null) {
			thisMovie("ExternalInterfaceExample").Refresh($("#dataYeYang").val());
		}
	} catch (e) {
	}  
		setTimeout(callExternalInterface, 30000); //指定30秒刷新一次*/
}

function thisMovie(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		return window[movieName];
	} else {
		return document[movieName];
	}
}
$(function(){
				var currentHospCode = $("#currentHospCode").val();
				var loadingFlag = $("#loadingFlag").val();
				var groupCode = $('#groupCode').val();
				var so = new SWFObject("manager/images/control/flashNew/loading.swf?loadSwf=manager/images/control/flashNew/yiYongQi/"+currentHospCode+"/"+groupCode+".swf&loadingFlag="+loadingFlag+"&loading_x=200&loading_y=50&flash_x=-50","mymovie","1000","700","10","write");
				so.addParam("swliveconnect","true");
				so.addParam("wmode","transparent");
				so.write("flashcontentYY");
				createHotDiv(0);
	            callExternalInterface();
			});
		</script>
	</head>

	<body id="mainDiv"
		style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<!--实时监控开始 -->
		<div id="admin_nr_rightg">
			<form id="myform" name="myform" action="control_findToControl">
				<input type="hidden" id="currentHospCode" name="currentHospCode"
				value="${sessionScope.currentHospCode}" />
				<input type="hidden" id="equipId" name="equipId" value="${equipId }" />
				<input type="hidden" id="groupId" name="groupId" value="${groupId}" />
				<input type="hidden" id="eqTypeId" name="eqTypeId"
					value="${eqTypeId }" />
				<input type="hidden" id="alarmCount" name="alarmCount"
					value="${alarmCount }" />
				<input type="hidden" id="controlPoint" name="controlPoint"
					value='${controlPoint }' />
				<input type="hidden" id="showFlag" name="showFlag"
					value='${showFlag }' />
				<input type="hidden" id="groupCode" name="groupCode"
					value="${groupCode }" />
				<input type="hidden" id="dataYeYang" name="dataYeYang" value='${dataYeYang }' />
				<input type="hidden" id="loadingFlag" name="loadingFlag"
					value="${sessionScope.loadingFlag}" />
				<input type="hidden" id="flashVersion" name="flashVersion"
				value="${sessionScope.flashVersion}" />
			</form>
			<div class="shishijiankong_jishuijing_111">
				<table width="100%" border="0" align="left">
					<tr>
						<td>
							<table width="100%">
								<tr>
									<td class="diant_ziti_140f" align="center">
										运行状态
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%">
								<tr>
									<td align="center">
										<div class="shishijiankong_yxzt1"></div>
									</td>
									<td align="center">
										<div class="shishijiankong_yxzt2"></div>
									</td>
									<td align="center">
										<div class="shishijiankong_yxzt5"></div>
									</td>
									<td align="center">
										<div class="shishijiankong_yxzt3"></div>
									</td>
									<td align="center">
										<div class="shishijiankong_yxzt6"></div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" style="height: 30px;">
								<tr>
									<td align="center" class="diant_ziti_140">
										运行
									</td>
									<td align="center" class="diant_ziti_140">
										停止
									</td>
									<td align="center" class="diant_ziti_140">
										采集失败
									</td>
									<td align="center" class="diant_ziti_140">
										故障
									</td>
									<td align="center" class="diant_ziti_140">
										未监控
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>

			<div class="shishijiankong_kuan22">
				<table>
					<tr>
						<td>
							<input name="" type="button" value="设备安装位置图" class="inp_shi_zt"
								onclick="getPicNameById(${groupId});" />
						</td>
					</tr>
				</table>

				<div
					style="left: 350px; top: 40px; position: absolute; width: 400px;"
					id="equip_descs">
					<table class="equip_desc">
						<tr>
							<td>
								<img
									src="manager/images/control/eqtype_icon/yaliguan,xiaotubiao.png"
									class="equip_img" />
								液氧罐
							</td>
						</tr>
					</table>
				</div>

			</div>
			<div class="shishijiankong_zxq" style="border: 1px red; margin-top: 100px; margin-left: -50px;">
				<div style="height: 20px; width: 100%; border: 0px solid red;">
					<table style="width: 100%;">
						<tr>
							<td align="center">
								<span class="groupNameStyle">【${groupName}】</span>
							</td>
						</tr>
					</table>
				</div>
				<div class="flashOverFlow" id="flashcontentYY" style="border: 1px;">
<%--					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"--%>
<%--						id="ExternalInterfaceExample" width="640" height="480"--%>
<%--						codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">--%>
<%--						<param name="movie"--%>
<%--							value="manager/images/control/flashOld/yaliguan.png" />--%>
<%--						<param name="quality" value="high" />--%>
<%--						<param name="menu" value="false" />--%>
<%--						<param name="wmode" value="transparent" />--%>
<%--						<param name="allowScriptAccess" value="sameDomain" />--%>
<%--						<embed src="manager/images/control/flashOld/yaliguan.png"--%>
<%--							quality="high" width="640" height="480"--%>
<%--							name="ExternalInterfaceExample" align="middle" play="true"--%>
<%--							loop="false" quality="high" allowScriptAccess="sameDomain"--%>
<%--							type="application/x-shockwave-flash"--%>
<%--							pluginspage="http://www.macromedia.com/go/getflashplayer"--%>
<%--							wmode="transparent">--%>
<%--						</embed>--%>
<%--					</object>--%>
				</div>
			</div>
		</div>
		<!--实时监控结束 -->
	</body>
</html>
