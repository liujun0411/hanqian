<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<title>电梯设备实时监控</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript"
			src="manager/javascript/previewPic/picShow.js">
</script>
		<script type="text/javascript" src="manager/config/config.js">
</script>
		<link href="manager/css/shishijiankong.css" rel="stylesheet"
			type="text/css" />
		<link href="manager/common/tip/css/manhuaTip.1.0.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript"
			src="manager/common/tip/js/manhuaTip.1.0.js">
</script>
		<script type="text/javascript" src="manager/js/swfobject.js">
</script>
	    <!-- 引用实时监控专用JS文件 -->
		<script type="text/javascript" src="manager/js/commonControl.js"></script>
		<script type="text/javascript">
function filePathjs() {
	var path = "manager/monitoring/realtimeXMl/4/4001.xml?r=" + Math.random();
	return path;
}

function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
			return;
	}
}
$(function() {
	$(".titleChange").manhuaTip( {
		Event : "click",//可选				       
		timeOut : 2500
	//停留时间
			});
	if ($('#flag').val() == "") {
		$('#aa').click();
	}

});

//分页控件方法
function formCheck(currentPage) {
	$('#aa').click();
	sleep(2000);
	if (currentPage != "") {
		$("#gPage").val(currentPage);
	}
	document.myform.submit();
}

// 设置自动刷新时间为30s 
//setTimeout(
//function(){   
//var c = $("#gPage").val();
//formCheck(c);   
//},10*1000);

<%--function myrefresh() {--%>
<%--	$('#flag').val('sss');--%>
<%--	document.myform.submit();--%>
<%--}--%>
<%--setTimeout('myrefresh()', 5000); //指定  秒刷新一次*/--%>


var elevator = function() {
	var reVal;
	var path="control_findElevatorControl.action";
<%--	if($("#groupCode").val()==8){--%>
<%--		path="control_find8ElevatorControl.action";--%>
<%--	}--%>
	<%--2套规则方式灵活改变-->
<%--	if($("#buildId").val()==8){--%>
<%--		path="control_find8ElevatorControl";--%>
<%--	}--%>
	var jsonDoc = $.ajax( {
		type : "POST",
		url : path,
		data:{
			eqTypeId:$("#eqTypeId").val(),
			groupId:$("#groupId").val(),
			groupCode:$("#groupCode").val(),
			groupName:$("#groupName").val(),
			gPage:$("#gPage").val()
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
function myrefresh() {
	$('#flag').val('sss');
	document.myform.submit();
}

function callExternalInterface() {
	try{
	    if(thisMovie("mymovie")&&thisMovie("mymovie")!=null){
				thisMovie("mymovie").Refresh();
	    }
	}catch(e){
	}
}

setInterval(callExternalInterface,13000);

function thisMovie(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		return window[movieName];
	} else {
		return document[movieName];
	}
}

function refresh() {
	var currentHospCode = '${sessionScope.currentHospCode}';
	if(currentHospCode!='RJ'&&currentHospCode!='RJB'&&currentHospCode!='RJX'&&currentHospCode!='RJN'){
		$("#content").prepend("<div id='flashcontent'></div>");
		$("#tab").show();
	}else{
		//当前页为第一页，没有上一页的按钮
		$("#content").html("<table><tr><td id='td1'><img src='manager/images/control/rj/01.png' onclick='nextPage(1)'>" +
		"</img></td><td><div id='flashcontent'></div></td><td id='td2'><img src='manager/images/control/rj/02.png' onclick='nextPage(2)'>" +
		"</img></td></tr></table>");
		if($("#gPage").val()==1){
			$("#td1").hide();
		}
		if($("#gPage").val()==$("#currentPage").val()){
			$("#td2").hide();
		}
		$("#tab").hide();
	}
	var currentHospCode = $("#currentHospCode").val();
	var groupCode = $("#groupCode").val();
	var so = new SWFObject("manager/images/control/flashNew/elevator/"+currentHospCode+"/"+groupCode+".swf", "mymovie", "800", "550", "10", "write");
	so.addParam("wmode", "transparent");
	so.write("flashcontent");
}

//分页控件方法
function formCheck(currentPage) {
	if (currentPage != "" || currentPage != undefined) {
		$("#gPage").val(currentPage);
	}
	document.myform.submit();
}

function nextPage(num){
	var page = $("#gPage").val();
	var currentPage = $("#currentPage").val();
	if(num==1){
		//上一页
		page--;
		if(page<=0){
			return;
		}
	}else if(num==2){
		//下一页
		page++;
		if(page>currentPage){
			return;
		}
	}
	formCheck(page);
}

</script>
	</head>
	<body onload="refresh()">
		<form method="post" action="control_findToControl.action" name="myform"
			id="myform">
			 <input type="hidden" id="currentHospCode" name="currentHospCode"
    				value="${sessionScope.currentHospCode}" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId}" />
			<input type="hidden" id="eqTypeId" name="eqTypeId"
				value="${eqTypeId}" />
					<input type="hidden" id="groupCode" name="groupCode" value="${groupCode }" />
			<input type="hidden" id="groupName" name="groupName"
				value="${groupName}" />
			<input type="hidden" id="alarmCount" name="alarmCount"
				value="${alarmCount }" />
			<input type="hidden" id="showFlag" name="showFlag" value='${showFlag }' />
			<input type="hidden" id="flag" name="flag" value="${flag}" />
			<input type="hidden" id="gPage" name="gPage" value="${gPage}" />
			<input type="hidden" id="currentPage" name="currentPage"
				value="${currentPage}" />
			<input type="hidden" id="returnVal" name="returnVal"
				value="${returnVal}" />
			<input type="hidden" id="buildId" name="buildId" value='${buildId }' />
		</form>
		<a style="display: none;" class="titleChange" id="aa" ty="loading"
			msg="正在提交您的请求，请稍候..."></a>
		<!--实时监控开始 -->
		<div class="shishijiankong_kuan1"  style="z-index: 9999;">
			<div
				style="height: 20px; width: 100%; border: 0px solid red; margin-bottom: 10px; margin-top: 10px;">
				<table style="width: 100%;">
					<tr>
						<td align="center">
							<span class="groupNameStyle">【${groupName}】</span>
						</td>
					</tr>
				</table>
			</div>
			<div class="cold" style="left: 888px; top: 2px; position: absolute;"
				id="">
				<input name="eqPic" type="button" value="设备安装位置图" class="inp_shi_zt"
					onclick="getPicNameById(${groupId });" />
			</div>
		</div>
		<br />
		<br />
		<div class="flashOverFlow" id="content"
			style="width: 1000px; padding-top: 100px; text-align: center;">
			
			<table width='850px' border='0' id="tab" style="display: none;">
				<tr style='height: 50px;'>
					<td></td>
					<td align='center'>
						<sktag:paginator showTotal='true' showAllPages="true"
							strUnit='条记录' />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>