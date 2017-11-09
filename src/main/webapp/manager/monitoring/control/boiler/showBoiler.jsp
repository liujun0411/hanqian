<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>医院后勤智能化管理平台——后勤管理</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/swfobject.js">
</script>
		<!-- 引用实时监控专用JS文件 -->
		<script type="text/javascript" src="manager/js/commonControl.js">
</script>
		<script type="text/javascript"
			src="manager/javascript/previewPic/picShow.js">
</script>
		<script type="text/javascript" src="manager/config/config.js">
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
//flash接受所用到的参数
var dtGLsss="";
function boiler() {
	 dtGLsss = $("#dataGL").val();
	return dtGLsss;
}

function flashStatus() {
	 dtGLsss = $("#dataGL").val();
	 console.log("flashStatus=" + dtGLsss);
	return dtGLsss;
}

function callExternalInterface() {
	try { 
	    dtGLsss=doAjax();
		var currentHospCode = $("#currentHospCode").val();
		if (thisMovie("ExternalInterfaceExample")&& thisMovie("ExternalInterfaceExample") != null) {
				thisMovie("ExternalInterfaceExample").Refresh(dtGLsss);
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
var doAjax = function(){
	var reVal = "";
	var jsonDoc = $.ajax({
		type:"post",
		url:"control_reFreshAjax.action",
		data:{
			eqTypeId:$("#eqTypeId").val(),
			groupId:$("#groupId").val(),
			groupName:$("#groupName").val(),
			groupCode:$("#groupCode").val(),
			gPage:$("#gPage").val()
		},
		cache:false,
		async:false,
		error:function(jsonObj){
		},
		success:function(jsonObj){
			reVal = jsonObj.toString();
			$('#dataGL').val(reVal);
		}
	})
	return reVal;
}
$(function(){
	var currentHospCode = $("#currentHospCode").val();
	var groupCode = $("#groupCode").val();
	var loadingFlag = $("#loadingFlag").val();
	var so = new SWFObject("manager/images/control/flashNew/loading.swf?"
	                      +"loadSwf=manager/images/control/flashNew/boiler/"
	                      +currentHospCode
	                      +"/"+groupCode
	                      +".swf&loadingFlag="
	                      +loadingFlag
	                      +"&loading_x=250&loading_y=50","ExternalInterfaceExample","1000","770","10","write");
	so.addParam("swliveconnect","true");
	so.addParam("wmode","transparent");
	so.write("boilerDiv");
    createHotDiv(0);
	callExternalInterface();
});
		</script>
	</head>
	<body id="mainDiv"
		style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<form id="myform" name="myform" action="control_findToControl.action">
			<input type="hidden" id="currentHospCode" name="currentHospCode"
				value="${sessionScope.currentHospCode}" />
			<input type="hidden" id="equipId" name="equipId" value="${equipId }" />
			<input type="hidden" id="groupCode" name="groupCode"
				value="${groupCode }" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId}" />
			<input type="hidden" id="eqTypeId" name="eqTypeId"
				value="${eqTypeId }" />
			<input type="hidden" id="dataGL" name="dataGL" value='${dataGL}'/>
			<input type="hidden" id="alarmCount" name="alarmCount"
				value="${alarmCount }" />
			<input type="hidden" id="controlPoint" name="controlPoint"
				value='${controlPoint }' />
			<input type="hidden" id="showFlag" name="showFlag"
				value='${showFlag }' />
			<input type="hidden" id="loadingFlag" name="loadingFlag"
				value="${sessionScope.loadingFlag}" />
				<input type="hidden" id="flashVersion" name="flashVersion"
				value="${sessionScope.flashVersion}" />
		</form>
		<!--实时监控开始 -->

		<div id="admin_nr_rightg">
			<div class="shishijiankong_jishuijing_111">
				<table width="320" border="0" align="left"
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
										<div class="shishijiankong_yxzt1"></div>
									</td>
									<td align="center" width="40">
										<div class="shishijiankong_yxzt2"></div>
									</td>
									<td align="center" width="40">
										<div class="shishijiankong_yxzt5"></div>
									</td>
									<td align="center" width="40">
										<div class="shishijiankong_yxzt3"></div>
									</td>
									<td align="center" width="40">
										<div class="shishijiankong_yxzt6"></div>
									</td>
								</tr>
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

			<div class="shishijiankong_kuan_guolu" align="left">
				<table>
					<%--					<div style=" width:400px; height: 30px; border: 0px solid red;">--%>
					<%--				     	  <input type="text" id="btnX" name="btnY" />--%>
					<%--				    </div> --%>
					<tr>
						<td>
							<input name="" type="button" value="设备安装位置图" class="inp_shi_zt"
								onclick="getPicNameById(${groupId});" />
						</td>
					</tr>
				</table>
			</div>
			<div
				style="left: 350px; top: 40px; position: absolute; width: 400px;"
				id="equip_descs">
				<table class="equip_desc">
					<tr>
						<td>
							<img src="manager/images/control/equippic/boilerBody.png"
								class="equip_img" />
							锅炉本体
						</td>
						<td>
							<img src="manager/images/control/equippic/jishuibeng.png"
								class="equip_img" />
							给水泵
						</td>
					</tr>
					<tr>
						<td>
							<img src="manager/images/control/equippic/fengqigang.png"
								class="equip_img" />
							分汽缸
						</td>
						<td>
							<img src="manager/images/control/equippic/jishuixiang.png"
								class="equip_img" />
							给水箱
						</td>
					</tr>
				</table>
			</div>
			<div
				style="top: 120px; position: absolute; height: 20px; width: 100%; border: 0px solid red;">
				<table style="width: 100%;">
					<tr>
						<td align="center">
							<span class="groupNameStyle">【${groupName}】</span>
						</td>
					</tr>

				</table>
				<div id="boilerDiv" class="flashOverFlow" style="margin-top: 120px;">
				</div>

			</div>
		</div>
		<!--实时监控结束 -->
		<!--页面结束 -->
		</div>
	</body>
</html>
