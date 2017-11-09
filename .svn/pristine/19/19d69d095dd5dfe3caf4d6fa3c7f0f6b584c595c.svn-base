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
		<script type="text/javascript"
			src="manager/javascript/previewPic/picShow.js">
</script>
		<script type="text/javascript" src="manager/config/config.js">
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

//给flash用的
function sljStatus() {
	//页面初次加载，读取request传递过来的字符串
	var dt = $('#datajson').val();
	//alert(dt);
	return dt;
}

function flashStatus() {
	var dt = $('#datajson').val();
	return dt;
}

//后台ajax刷新的方法
function doAjax() {
	var reVal = "";
	$.ajax( {
		type : "POST",
		url : "control_reFreshAjax.action?t=" + Math.random(),
		data : {
			eqTypeId : $("#eqTypeId").val(),
			groupId : $("#groupId").val(),
			groupCode : $("#groupCode").val(),
			sysTm : $('#sysTm').val()
		},
		cache : false,
		async : false,
		error : function(jsonObj) {
		},
		success : function(jsonObj) {
			reVal = jsonObj.toString();
			$('#datajson').val(reVal);
			var groupCode = $('#groupCode').val();
			callExternalInterface();
		}
	});

}

function callExternalInterface() {
	try {
		var currentHospCode = $("#currentHospCode").val();
		if (thisMovie("mymovie") && thisMovie("mymovie") != null) {
			var dt = $('#datajson').val();
			//alert(dt);
			thisMovie("mymovie").Refresh(dt);
		}
	} catch (e) {

	}
}

function thisMovie(movieName) {
<%--	if (navigator.appName.indexOf("Microsoft") != -1) {--%>
<%--		return window[movieName];--%>
<%--	} else {--%>
<%--		return document[movieName];--%>
<%--	}--%>
	return document[movieName] || window[movieName];
}

$(function(){
	var currentHospCode = $("#currentHospCode").val();
				changeFlashLayer(0);
        		refresh();
        		myrefresh();
        	});

        	function refresh() {
            	var sysTm=$('#sysTm').val();
            	var vgroupId = $('#groupId').val(); 
            	var loadingFlag = $("#loadingFlag").val();
            	var flashName="";
            	var currentHospCode = $("#currentHospCode").val();
            	var so;
            		if(sysTm==1){  //夏天
	                	//判断该展现哪张flash图，不同的组，图纸不同
	            		flashName=$('#groupCode').val()+"_xt.swf?t="+Math.random();
	            	}else{
	            		flashName=$('#groupCode').val()+"_dt.swf?t="+Math.random();
	            	}
            	so = new SWFObject("manager/images/control/flashNew/loading.swf?"
            	                  +"loadSwf=manager/images/control/flashNew/shuiLengJi/"
            	                  +currentHospCode+"/"+flashName+"&loadingFlag="
            	                  +loadingFlag+"&loading_x=250&loading_y=50&flash_y=0","mymovie","1000","700","10","write");
        		so.addParam("swliveconnect","true");
        		so.addParam("allowScriptAccess","always");
        		so.addParam("wmode", "transparent");
        		so.write("sljz_flashContent");
        	}
		   
			function dochange(){
			   sleep(1000);
			   if($("#sysTm").val()==1){
			      $("#sysTm").val("2");
			   }else{
			       $("#sysTm").val("1");
			   }
			   document.myform.submit();
			}
			//js停留（参数毫秒）
			function sleep(numberMillis) { 
			   var now = new Date();
			   var exitTime = now.getTime() + numberMillis;  
			   while (true) { 
			       now = new Date(); 
			       if (now.getTime() > exitTime)    return;
			    }
	        }
			
			function myrefresh(){
				 //判断当前页面的选项卡是否选中，选中才自动刷新
		        if($(window.parent.document).find("#rotate ul li[class='ui-tabs-selected']").find('a').attr("eid")==$("#eqTypeId").val()){
				   //如果监控页面隐藏状态
				   if($(window.parent.document).find("#commonShow_"+$("#eqTypeId").val()).css("display")=="block"){
					  // alert('ccc');
					   doAjax();
				   }
				}
				 //alert(sljStatus());
		        setTimeout(myrefresh,30000); //指定30秒刷新一次*/
			}
		</script>
		<script type="text/javascript">
$(function() {
	$(".titleChange").manhuaTip( {
		Event : "click",//可选				       
		timeOut : 2000
	});
});
</script>
	</head>

	<body id="mainDiv"
		style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<input type="hidden" id="datajson" name="datajson"
			value='${dataJson }' />
		<input type="hidden" id="controlPoint" name="controlPoint"
			value='${controlPoint }' />
		<form id="myform" name="myform" action="control_findToControl.action">
			<input type="hidden" id="currentHospCode" name="currentHospCode"
				value="${sessionScope.currentHospCode}" />
			<input type="hidden" id="equipId" name="equipId" value="${equipId }" />
			<input type="hidden" id="groupCode" name="groupCode"
				value="${groupCode }" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId}" />
			<input type="hidden" id="eqTypeId" name="eqTypeId"
				value="${eqTypeId }" />
			<input type="hidden" id="alarmCount" name="alarmCount"
				value="${alarmCount }" />
			<input type="hidden" id="showFlag" name="showFlag"
				value='${showFlag }' />
			<input type="hidden" id="loadingFlag" name="loadingFlag"
				value="${sessionScope.loadingFlag}" />
			<input type="hidden" id="flashVersion" name="flashVersion"
				value="${sessionScope.flashVersion}" />
			<!-- 系统季节 -->
			<input type="hidden" id="sysTm" name="sysTm" value="${sysTm}" />

			<div id="admin_nr_rightg">
				<div style="width: 300px; position: absolute; top: 0px;">
					<table width="300" border="0" align="left"
						style="font-size: 13px; color: #404041;">
						<tr>
							<td>
								<table width="100%" style="height: 100px;">
									<tr>
										<td class="diant_ziti_140f" align="center" colspan="5">
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
				<div class="cold" style="left: 888px; top: 2px; position: absolute;"
					id="">
					<input name="" type="button" value="设备安装位置图" class="inp_shi_zt"
						onclick="getPicNameById(${groupId });" />
				</div>
				<div
					style="left: 350px; top: 40px; position: absolute; width: 400px;"
					id="equip_descs">
					<table class="equip_desc">

						<tr>
							<td>
								<img
									src="manager/images/control/equippic/shuileng/shuileng142/lengquebeng01.png"
									class="equip_img" />
								冷却泵
							</td>
							<c:if test="${groupCode!='slj_01'}">
								<td>
									<img
										src="manager/images/control/equippic/shuileng/shuileng142/rejiaohuan01.png"
										class="equip_img" />
									换热器
								</td>
								<td>
									<img
										src="manager/images/control/equippic/shuileng/shuileng142/shuixiang.png"
										class="equip_img" />
									水箱
								</td>
							</c:if>
							<c:if test="${groupId=='slj_01'}">
								<td>
									<img
										src="manager/images/control/equippic/shuileng/shuileng/rejiaohuan01.png"
										class="equip_img" />
									换热器
								</td>
							</c:if>
							<td>
								<img
									src="manager/images/control/equippic/shuileng/shuileng142/lengdongbeng01.png"
									class="equip_img" />
								冷冻泵
							</td>
							<td>
								<img
									src="manager/images/control/equippic/shuileng/shuileng142/lengqueta01.png"
									class="equip_img" />
								冷却塔
							</td>

						</tr>
						<tr>
							<td>
								<img
									src="manager/images/control/equippic/shuileng/shuileng142/jishuiqi01.png"
									class="equip_img" />
								集水器
							</td>
							<td>
								<img
									src="manager/images/control/equippic/shuileng/shuileng142/fenshuiqi01.png"
									class="equip_img" />
								分水器
							</td>
							<td>
								<img
									src="manager/images/control/equippic/shuileng/shuileng142/reshuibeng01.png"
									class="equip_img" />
								热水泵
							</td>
							<td>
								<img
									src="manager/images/control/equippic/shuileng/shuileng142/lengdongji01.png"
									class="equip_img" />
								冷冻机
							</td>
							<c:if test="${groupId!='slj_01'}">
								<td>
									<img
										src="manager/images/control/equippic/shuileng/shuileng142/lengdongji02.png"
										class="equip_img" />
									冷冻机
								</td>
							</c:if>

						</tr>
					</table>
				</div>
				<div style="margin-top: 100px;">
					<div id="tbList" style="clear: both;"></div>
					<div style="clear: both;"></div>
				</div>
                <div style="position: absolute; left: 60px; top: 200px; z-index: 99;">
					<c:forEach var="airflsl" items="${AirFLSL}">
							${airflsl.point_name}:<fmt:formatNumber maxFractionDigits="2"
							pattern="#0.00" value="${airflsl.record}" /> ${airflsl.unit_type}
							&nbsp;&nbsp;
					</c:forEach>
				</div>
				<!-- 图片区 -->
				<div
					style="border: 0px solid red; margin-top: 140px; height: 620px; float: left; width: 1000px; overflow: auto;">
					<div
						style=" height: 20px; width: 100%; border:0px solid red;
					 <c:if test='${groupCode==\'slj_02\' && sessionScope.currentHospCode == \'RJ\' }'>margin-top:80px;</c:if>
					 <c:if test='${groupCode!=\'slj_02\' && sessionScope.currentHospCode == \'RJ\' }'>margin-top:10px;</c:if>
					 <c:if test='${ sessionScope.currentHospCode == \'LH\' }'>margin-top:160px;</c:if>
					 <c:if test='${ sessionScope.currentHospCode == \'FK\' }'>margin-top:10px;</c:if>
					 <c:if test='${ sessionScope.currentHospCode == \'XK\' }'>margin-top:80px;</c:if>
					 <c:if test='${ sessionScope.currentHospCode == \'RUJ\' }'>margin-top:50px;</c:if>
					 <c:if test='${ sessionScope.currentHospCode == \'HD\' }'>margin-top:150px;</c:if>
					 <c:if test='${ sessionScope.currentHospCode == \'DLRM\' }'>margin-top:190px;</c:if>
					 <c:if test='${ sessionScope.currentHospCode == \'DYRM\' }'>margin-top:0px;</c:if>
					 <c:if test='${ sessionScope.currentHospCode == \'DLRM\' }'>margin-top:60px;</c:if>
					 ">

						<table style="width: 100%;">
							<tr>
								<td align="center">
									<span class="groupNameStyle"> 【${groupName}<c:if
											test="${sysTm==1}">--夏季</c:if> <c:if test="${sysTm==2}">--冬季</c:if>
										[<a class="titleChange" href="javascript:void(0)"
										onclick="dochange();" title="点击切换季节" ty="loading"
										msg="正在提交您的请求，请稍候...">切换</a>]】 </span>
								</td>
							</tr>
						</table>
					</div>
					<div id="sljz_flashContent" class="flashOverFlow">
						<!-- Flash图片展示区 -->
					</div>
				</div>
			</div>
			</div>
		</form>
	</body>
</html>