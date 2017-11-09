<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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

		<title>生活水-实时监控</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="manager/js/commonControl.js">
</script>
		<script type="text/javascript"
			src="manager/javascript/previewPic/picShow.js">
</script>
		<script type="text/javascript" src="manager/js/swfobject.js">
</script>
		<script type="text/javascript" src="manager/config/config.js">
</script>
		<script type="text/javascript">

$(function() {
	$().mousemove(function(e) {
		var x = e.pageX; //鼠标点击的X坐标
			var y = e.pageY; //鼠标点击的Y坐标
			$("#btnX").val(x + "," + y);
			;
		});

});

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

function shengHuoShui() {
	var a = $("#df").val();
	return a;
}

function flashStatus() {
	var a = $("#df").val();
	return a;
}

function shsStatus1() {
	var reVal = "";
	var jsonDoc = $.ajax( {
		type : "POST",
		url : "control_reFreshAjax.action?t=" + Math.random(),
		data : {
			eqTypeId : $("#eqTypeId").val(),
			groupId : $("#groupId").val(),
			groupCode : $("#groupCode").val(),
			groupName : $("#groupName").val()
		},
		cache : false,
		async : false,
		error : function(jsonObj) {
		},
		success : function(jsonObj) {
			reVal = jsonObj.toString();
			$("#df").val(reVal);
			var groupCode = $('#groupCode').val();
			callExternalInterface();
		}
	});
}

function callExternalInterface() {
	try {
		var currentHospCode = $("#currentHospCode").val();
		if (thisMovie("mymovie") && thisMovie("mymovie") != null) {
			var a = $("#df").val();
			//alert(a);
			thisMovie("mymovie").Refresh(a);
		}
	} catch (e) {
	}
}

//setInterval(callExternalInterface,5000);

function thisMovie(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		return window[movieName];
	} else {
		return document[movieName];
	}
}

function refresh(){
				var currentHospCode = $("#currentHospCode").val();
				var loadingFlag = $("#loadingFlag").val();
				var groupCode = $('#groupCode').val();
				var flashName ="";
				var so;
					flashName=$('#groupCode').val()+".swf?t="+Math.random();
					so = new SWFObject("manager/images/control/flashNew/loading.swf?"
					                  +"loadSwf=manager/images/control/flashNew/shengHuoShui/"
					                  +currentHospCode
					                  +"/"
					                  +groupCode
					                  +".swf&flash_y=50&loadingFlag="
					                  +loadingFlag
					                  +"&loading_x=250&loading_y=50","mymovie","1000","700","10","write");
				so.addParam("swliveconnect","true");
				so.addParam("wmode","transparent");
				so.write("flashcontentshs");
			}
		    
		    var count=0;
		    function myrefresh(){
				 //判断当前页面是否为监控界面，自动刷新
		        if($(window.parent.document).find("#control_Info").css('display')=="block" && count>0){
				      shsStatus1();
				   }
				 setTimeout(myrefresh,30000); //指定30秒刷新一次*/
				 count++;
			}
			 $(function(){
			   var currentHospCode = $("#currentHospCode").val();
					changeFlashLayer(0);
			   refresh();
			   myrefresh();
			   }
		   );
		</script>
	</head>

	<body id="mainDiv"
		style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<div class="shishijiankong_kuan22">
			<input name="" type="button" value="设备安装位置图" class="inp_shi_zt"
				onclick="getPicNameById(${groupId})" />
		</div>
		<div class="admin_nr_rightg" style="position: absolute; top: 10px"
			border="1px">
			<table width="400" border="0" align="left"
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
		<div style="left: 450px; top: 40px; position: absolute; width: 400px;"
			id="equip_descs">
			<table class="equip_desc">
				<tr>
					<td>
						<img src="manager/images/control/equippic/shs/beng.png"
							class="equip_img" />
						热水泵
					</td>
					<td>
						<img src="manager/images/control/equippic/shs/rejiaohuan.png"
							class="equip_img" />
						换热器
					</td>
					<td>
						<img src="manager/images/control/equippic/shs/fenshuiqi.png"
							class="equip_img" />
						分水器
					</td>
					<td>
						<img src="manager/images/control/equippic/shs/jishuiqi.png"
							class="equip_img" />
						集水器
					</td>
				</tr>
				<tr>
					<td>
						<img src="manager/images/control/equippic/shs/xiaobeng.png"
							class="equip_img" />
						冷水泵
					</td>
					<td>
						<img src="manager/images/control/equippic/shs/shuixiang.png"
							class="equip_img" />
						水箱
					</td>
					<td>
						<img src="manager/images/control/equippic/shs/guolu.png"
							class="equip_img" />
						锅炉
					</td>
					<td>
						<img src="manager/images/control/equippic/shs/shubeng.png"
							class="equip_img" />
						冷水泵(高区 )
					</td>
				</tr>
			</table>
		</div>
		<form id="myform" name="myform" action="control_findToControl.action">
			<input type="hidden" id="currentHospCode" name="currentHospCode"
				value="${sessionScope.currentHospCode}" />
			<input type="hidden" id="equipId" name="equipId" value="${equipId }" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId }" />
			<input type="hidden" id="groupCode" name="groupCode"
				value="${groupCode }" />
			<input type="hidden" id="eqTypeId" name="eqTypeId"
				value="${eqTypeId }" />
			<input type="hidden" id="alarmCount" name="alarmCount"
				value="${alarmCount }" />
			<input type="hidden" id="controlPoint" name="controlPoint"
				value='${controlPoint }' />
			<input type="hidden" id="showFlag" name="showFlag"
				value='${showFlag }' />
			<input type="hidden" id="df" name="df" value='${df }' />
			<input type="hidden" id="loadingFlag" name="loadingFlag"
				value="${sessionScope.loadingFlag}" />
		</form>
		<div id="admin_nr_rightg">
			<div
				style="height: 20px; width: 100%; border: 0px solid red; margin-top: 105px;">
				<table style="width: 100%;">
					<tr>
						<td align="center">
							<span class="groupNameStyle"> </span>
						</td>
					</tr>
				</table>
			</div>
			<!-- 图片区 -->
			<div style="border: 0px solid red;" class="shishijiankong_zxq">
				<div style="height: 20px; width: 100%; border: 0px solid red;">
					<table style="width: 100%;">
						<tr>
							<td align="center">
								<span class="groupNameStyle">【${groupName}】</span>
								<%-- <input type="text" id="btnX" name="btnY" /> --%>

							</td>
						</tr>
					</table>
				</div>
				<div class="flashOverFlow" id="flashcontentshs" >

				</div>
			</div>
		</div>
		</div>
	</body>
</html>