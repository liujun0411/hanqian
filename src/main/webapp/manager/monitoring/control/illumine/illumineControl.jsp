<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>照明设备实时监控</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript"
			src="manager/javascript/previewPic/picShow.js">
</script>
		<script type="text/javascript" src="manager/config/config.js">
</script>
		<script type="text/javascript" src="manager/js/swfobject.js">
</script>

		<script type="text/javascript">
<%--		var reVal;	--%>
//var illumine = function() {
var flashStatus = function(){
	var reVal;
	var jsonDoc = $.ajax( {
		type : "POST",
		url : "control_findIllumineControl.action",
		data:{
			eqTypeId:$("#eqTypeId").val(),
			groupId:$("#groupId").val(),
			groupCode:$("#groupCode").val(),
			buildId:$("#buildId").val()
		},
		cache : false,
		async : false,
		error : function(jsonObj) {
		},
		success : function(jsonObj) {
			reVal = jsonObj.toString();
		}
	});
	return reVal;
}

var illumine = function(){
	var reVal;
	var jsonDoc = $.ajax( {
		type : "POST",
		url : "control_findIllumineControl.action",
		data:{
			eqTypeId:$("#eqTypeId").val(),
			groupId:$("#groupId").val(),
			groupCode:$("#groupCode").val(),
			buildId:$("#buildId").val()
		},
		cache : false,
		async : false,
		error : function(jsonObj) {
		},
		success : function(jsonObj) {
			reVal = jsonObj.toString();
		}
	});
	return reVal;
}
function callExternalInterface() {
	try{
		var currentHospCode = $("#currentHospCode").val();
	    if(thisMovie("mymovie")&&thisMovie("mymovie")!=null){
	    	var reVal = flashStatus();
				thisMovie("mymovie").Refresh(reVal);
	    }
	}catch(e){
		console.info(e);
	}
}

setInterval(callExternalInterface,30000);

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
	var groupCode=$("#groupCode").val();
	var srcSwf ;
		srcSwf ="manager/images/control/flashNew/loading.swf?loadSwf=manager/images/control/flashNew/zhaoMing/"+currentHospCode+"/"+groupCode+".swf&loadingFlag="+loadingFlag;           
	var so=new SWFObject(srcSwf,"mymovie","1000","700","10","write");
			so.addParam("wmode","transparent");
			so.write("flashcontent");
			callExternalInterface();
});

</script>
	</head>

	<body>
		<form id="myform" name="myform" action="control_findToControl.action"
			method="post">
			<input type="hidden" id="currentHospCode" name="currentHospCode"
				value="${sessionScope.currentHospCode}" />
			<input type="hidden" id="loadingFlag" name="loadingFlag"
				value="${sessionScope.loadingFlag}" />
			<input type="hidden" id="buildId" name="buildId" value="${buildId}" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId}" />
			<input type="hidden" id="groupCode" name="groupCode"
				value="${groupCode }" />
			<input type="hidden" id="groupName" name="groupName"
				value="${groupName}" />
			<input type="hidden" id="eqTypeId" name="eqTypeId"
				value="${eqTypeId}" />
		</form>
		<!--实时监控开始 -->
		<div class="shishijiankong_kuan1" style="z-index: 9999;">
			<div style="left: 888px; top: 2px; position: absolute;" id="">
				<input name="" type="button" value="设备安装位置图" class="inp_shi_zt"
					onclick="getPicNameById(${groupId });" />
			</div>
		</div>
		<br />
		<div style="left: 20px;">
			<table width="300" border="0" align="left"
				style="font-size: 13px; color: #404041;">
				<tr>
					<td>
						<table width="100%" style="height: 100px;">
							<tr>
								<td class="diant_ziti_140f" align="center" colspan="4">
									运行状态
								</td>
							</tr>
							<tr>
								<td align="center" width="40">
									<div class="shishijiankong_yxzt1"
										style="background-color: #9fd8f3;"></div>
								</td>
								<td align="center" width="40">
									<div class="shishijiankong_yxzt5"
										style="background-color: #e1d123;"></div>
								</td>
								<td align="center" width="40">
									<div class="shishijiankong_yxzt5"></div>
								</td>
								<td align="center" width="40">
									<div class="shishijiankong_yxzt6"></div>
								</td>
							</tr>
							<tr>
								<td align="center" class="diant_ziti_140">
									关灯
								</td>
								<td align="center" class="diant_ziti_140">
									开灯
								</td>
								<td align="center" class="diant_ziti_140">
									采集失败
								</td>
								<td align="center" class="diant_ziti_140">
									未监控
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<div id="tbList" style="clear: both;"></div>
			<div style="clear: both;"></div>
		</div>
		<div style="position: absolute; left: 60px; top: 200px; z-index: 99;">
			<c:forEach var="outdoor" items="${outdoorRt}">
					<c:if test="${not empty outdoor.record}">
						${outdoor.point_name}:<fmt:formatNumber maxFractionDigits="2"
						pattern="#0.00" value="${outdoor.record}" /> ${outdoor.unit_type}
						&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>					
			</c:forEach>
		</div>
		<div
			style="height: 20px; width: 80%; border: 0px solid red; margin-top: 10px;">
			<table style="width: 100%;">
				<tr>
					<td align="center">
						<span class="groupNameStyle">【${groupName}】</span>
					</td>
				</tr>
			</table>
		</div>
		<div id="flashcontent" class="flashOverFlow">
			<!-- 
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
				id="ExternalInterfaceExample" width="910" height="546"
				codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
				<param name="mymovie"
					value="manager/images/control/flashNew/${buildId}.swf?num= 1" />
				<param name="quality" value="high" />
				<param name="wmode" value="transparent" />
				<param name="allowScriptAccess" value="sameDomain" />
				<embed src="manager/images/control/flashNew/${buildId}.swf?num= 2"
					quality="high" width="910" height="546" name="mymovie" align="middle"
					play="true" loop="false" quality="high"
					allowScriptAccess="sameDomain" type="application/x-shockwave-flash"
					pluginspage="http://www.macromedia.com/go/getflashplayer"
					wmode="transparent">
				</embed>
			</object>
			 -->
		</div>
		<br>
	</body>
</html>
