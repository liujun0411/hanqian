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

		<title></title>

		<meta http-equiv="pragma" content="no-cache" />
		<!-- 设置自动刷新时间为30s 
		<meta http-equiv="Refresh" content="30"/>-->
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
		<!-- 引用实时监控专用JS文件 -->
		<script type="text/javascript" src="manager/js/commonControl.js">
</script>
		<link href="manager/common/tip/css/manhuaTip.1.0.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript"
			src="manager/common/tip/js/manhuaTip.1.0.js">
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
function fengLeng() {
	var a = $("#dataFL").val();
	//alert(a);
	return a;
}

function flashStatus() {
	var a = $("#dataFL").val();
	return a;
}

function flStatus1() {
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
			$("#dataFL").val(reVal);
			var groupCode = $('#groupCode').val();
			callExternalInterface();
		}
	});
}

function callExternalInterface() {
	try {
		if (thisMovie("ExternalInterfaceExample")
				&& thisMovie("ExternalInterfaceExample") != null) {
			var a = $("#dataFL").val();
			thisMovie("ExternalInterfaceExample").Refresh(a);
		}
	} catch (e) {
	}
}

function thisMovie(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		return window[movieName];
	} else {
		return document[movieName];
	}
}

function refresh(){
				var currentHospCode = $("#currentHospCode").val();
				/** 第一人民医院北院 风冷机组的图太大了，调整了位置，导致 */
				/** 以前的图点位不能正常显示，特加次判断 */
				/** 和注释*/
				/** 这个只是[第一人民医院北院]适用*/

				var widthFlash='700';
				if(currentHospCode=='DYRMB'){
				   widthFlash='930';
				}
				var groupCode = $('#groupCode').val();
				var loadingFlag = $("#loadingFlag").val();
				var flashName ="";
				var so;
					flashName=$('#groupCode').val()+".swf?t="+Math.random();
					so = new SWFObject("manager/images/control/flashNew/loading.swf?"
					                  +"loadSwf=manager/images/control/flashNew/fengLeng/"
					                  +currentHospCode
					                  +"/"
					                  +groupCode
					                  +".swf&loadingFlag="
					                  +loadingFlag
					                  +"&loading_x=250&loading_y=50","ExternalInterfaceExample","1000",widthFlash,"10","write");
					so.addParam("swliveconnect","true");
					so.addParam("wmode","transparent");
					so.addParam("allowScriptAccess","always");
					so.write("flashcontentFL");
			}
			
		    var count=0;
		    function myrefresh(){
		    	 //判断当前页面的选项卡是否选中，选中才自动刷新
		        if($(window.parent.document).find("#rotate ul li[class='ui-tabs-selected']").find('a').attr("eid")==$("#eqTypeId").val()){
				   //如果监控页面隐藏状态
				   if($(window.parent.document).find("#commonShow_"+$("#eqTypeId").val()).css("display")=="block"){
					  flStatus1();
				   }
				}
				setTimeout(myrefresh,30000); //指定30秒刷新一次*/
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
		<form id="myform" name="myform" action="control_findToControl.action">
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
			<input type="hidden" id="dataFL" name="dataFL" value='${dataFL }' />
			<input type="hidden" id="loadingFlag" name="loadingFlag"
				value="${sessionScope.loadingFlag}" />

			<input type="hidden" id="flashVersion" name="flashVersion"
				value="${sessionScope.flashVersion}" />
			<div id="admin_nr_rightg">
				<div class="shishijiankong_jishuijing_111"
					style="height: 100px; margin-bottom: 20px;">
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
				<div class="cold" style="left: 688px; top: 2px; position: absolute;"
					id="">
					<input name="" type="button" value="设备安装位置图" class="inp_shi_zt"
						onclick="getPicNameById(${groupId});" />
				</div>
				<div
					style="left: 350px; top: 40px; position: absolute; width: 400px;"
					id="equip_descs">
					<table class="equip_desc">
						<tr>
							<td>
								<img src="manager/images/control/equippic/fenglengrebeng.png"
									class="equip_img" />
								风冷热泵
							</td>
							<td>
								<img src="manager/images/control/equippic/shuibeng.png"
									class="equip_img" />
								循环泵
							</td>
						</tr>
					</table>
				</div>
				<!--			    <div id="tbList" style=" clear:both;"></div>-->
				<div style="clear: both;"></div>
			</div>
			<!-- 增加室外温湿度 -->
			<div style="position: absolute; left: 60px; top: 200px; z-index: 99;">
			<c:forEach var="airflsl" items="${AirFLSL}">
					${airflsl.point_name}:<fmt:formatNumber maxFractionDigits="2"
					pattern="#0.00" value="${airflsl.record}" /> ${airflsl.unit_type}
					&nbsp;&nbsp;
			</c:forEach>
		    </div>
			<!-- 图片区 -->
			<div
				style=" border:0px solid red; 
			<c:if test='${groupCode==\'fljz_03\' && sessionScope.currentHospCode == \'FK\' }'>margin-top: 140px;</c:if>
			<c:if test='${groupCode!=\'fljz_03\' && sessionScope.currentHospCode == \'FK\' }'>margin-top: 10px;</c:if>"
				"
			 class="shishijiankong_zxq">
				<div style="height: 20px; width: 100%; border: 0px solid red;">
					<table style="width: 100%;">
						<tr>
							<td align="center">
								<span class="groupNameStyle">【${groupName}】</span>
							</td>
						</tr>
					</table>
				</div>
				<div class="flashOverFlow" id="flashcontentFL"></div>
			</div>
		</form>
	</body>
</html>