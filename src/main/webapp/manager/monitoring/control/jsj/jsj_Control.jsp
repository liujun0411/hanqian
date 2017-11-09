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
   //这个更新页面把热区变大了一点点。
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>集水井-实时监控</title>

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
		<script type="text/javascript"
			src="manager/javascript/previewPic/picShow.js">
</script>
		<script type="text/javascript" src="manager/config/config.js">
</script>
		<script type="text/javascript" src="manager/js/swfobject.js">
</script>
		<script type="text/javascript" src="manager/js/commonControl.js">
</script>
		<script type="text/javascript"
			src="manager/javascript/previewPic/picShow.js">
</script>
		<script type="text/javascript">

$(function() {
	$("body").mousemove(function(e) {
		var x = e.originalEvent.x || e.originalEvent.layerX || 0; //鼠标点击的X坐标
			var y = e.originalEvent.y || e.originalEvent.layerY || 0; //鼠标点击的Y坐标
			$("#btnX").val(x + "," + y);
			;
		});
});

function myrefresh() {
	//判断当前页面是否为监控界面，自动刷新
	if ($(window.parent.document).find("#control_Info").css('display') == "block") {
		document.myform.submit();
	}
}

$(function() {
	var currentHospCode = $("#currentHospCode").val();
	//添加代码  将集水井falsh图片 通过js方式加载  解决 ie9 浏览器不显示flash 图片不显示问题
	var groupCode = $("#groupCode").val();
	var loadingFlag = $("#loadingFlag").val();
	var height = $("#flashHeight").val();
	var width = $("#flashWidth").val();
	var so;
		so = new SWFObject(
				"manager/images/control/flashNew/loading.swf?loadSwf=manager/images/control/flashNew/jsj/"
						+ currentHospCode
						+ "/"
						+ groupCode
						+ ".swf&loadingFlag="
						+ loadingFlag
						+ "&loading_x=250&loading_y=50", "mymovie", "1000",
				"700", "10", "write");
				
	so.addParam("swliveconnect", "true");
	so.addParam("wmode", "transparent");
	so.write("flashJsj");
	callExternalInterface();
});
	$(function (){
					createHotDiv(0);
				jsreturnval = $("#jsjStatus").val();
				setTimeout(callExternalInterface, 30000); //指定30秒刷新一次*/
			});
//返回 状态读数
var jsreturnval = "";
function jsReturnVal() {
	jsreturnval = $("#jsjStatus").val();
	//alert("状态"+jsreturnval);
	return jsreturnval;
}

function flashStatus() {
	jsreturnval = $("#jsjStatus").val();
	//alert("zhuangt2"+jsreturnavl);
	return jsreturnval;
}

//ajax 动态获取 读数
var illumine = function() {

	var reVal;
	var htmlDiv;
	var jsonDoc = $.ajax( {
		type : "POST",
		url : "control_reFreshAjax.action",
		data : {
			equipId : $("#equipId").val(),
			groupId : $("#groupId").val(),
			groupCode : $("#groupCode").val(),
			eqTypeId : $("#eqTypeId").val()
		},
		cache : false,
		async : false,
		error : function(jsonObj) {
		},
		success : function(jsonObj) {
			reVal = jsonObj.toString();
			htmlDiv = reVal.split("%jsj%");
			$("#jsjStatus").val(htmlDiv[0]);
		doChange(htmlDiv[1]);
	}
	});
	return htmlDiv[0];
}

//调用swf中的Refresh方法
function callExternalInterface() {
	try {
		jsreturnval = illumine();
		
		var currentHospCode = $("#currentHospCode").val();
		if (thisMovie("mymovie") && thisMovie("mymovie") != null) {
				thisMovie("mymovie").Refresh(jsreturnval);
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

//自定义热区对象
function HotArea(x,y,bengIndex){
			 this.x=x;
			 this.y=y;
			 this.bengIndex=bengIndex;
		}
		$(function(){
			createHotDiv();
		});
		
		function createHotDiv(){
		    var hotList=new Array();
		    var currentHospCode = $("#currentHospCode").val();
		    var path="manager/monitoring/control/jsj/"+currentHospCode+"/jsjXY.xml";
		   // alert(path);
		     $.get(path,"equip", function(m){
		        $(m).find('equipment').find('equip').each(function(c){
		           var pro=$(this);
		           if($.trim(pro.attr('groupCode'))==$.trim($('#groupCode').val())){
		          // alert($('#groupId').val());
		              pro.find('xy').each(function(){
		                  var hot=new HotArea($(this).attr('x'),$(this).attr('y'),$(this).attr('bengIndex'));
		                  hotList.push(hot);
		              });
		          //  alert("长度："+hotList.length); 
		             if(hotList.length>0){
		                 var divHot="";
		                 
		                 
		                 //循环遍历生成热区
    					 for(var t=0;t<hotList.length;t++){
    					 	divHot+="<div showId='jsj_"+hotList[t].bengIndex+"' class='jsj_control' title='集水井"+hotList[t].bengIndex+"#' style=' border: 0px solid red ; cursor: pointer; position: absolute;  height:40px; width: 40px; margin-top: "+hotList[t].x+"px; margin-left: "+hotList[t].y+"px; '><span style='height:30px; width: 30px; line-height:30px; position: absolute; background-color:white;filter:alpha(opacity=0);-moz-opacity:0;-khtml-opacity: 0;opacity: 0; border:1px solid blue;'></span></div>";
		               // alert(divHot);jishuijing_bg 。jsj_control
		                 }
		                 $('body').append(divHot);
		                 $('.jsj_control').click(function(e){
		                // alert("cccc"+$(this).attr('showId'));
			                $('#'+$(this).attr('showId')).css("left",e.pageX+10);
			                $('#'+$(this).attr('showId')).css("top",e.pageY+10);
		                    $('#'+$(this).attr('showId')).show(200);
		                 });
		              }
		           }
		        });
		     });
		}
	  
	  
	  function divHide(obj){
	  	$(obj).hide();
	  }
	  
	   function doChange(str){
	      $("#demo").html(str);
	   }
		</script>
	</head>

	<body id="mainDiv"
		style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<div id="demo">
			${textHtml}
		</div>
		<div class="shishijiankong_kuan22">
			<input name="" type="button" value="设备安装位置图" class="inp_shi_zt"
				onclick="getPicNameById(${groupId})" />
		</div>
		<!-- textHtml 为重要配置不能删除 -->
		<div class="admin_nr_rightg" style="position: fixed;" border="1px">
			<table width="500" border="0" align="left"
				style="font-size: 13px; color: #404041;">
				<tr>
					<td>
						<table width="100%" style="height: 100px;">
							<tr>
								<td class="diant_ziti_140f" align="center" colspan="5">
									运行状态
								</td>
								<td class="diant_ziti_140f" align="center" colspan="2">
									溢水状态
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
								<td align="center" width="40">
									<div class="shishijiankong_yxzt4"></div>
								</td>
								<td align="center" width="40">
									<div class="shishijiankong_yxzt7"></div>
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
								<td align="center" class="diant_ziti_140">
									正常
								</td>
								<td align="center" class="diant_ziti_140">
									报警
								</td>

							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>

		<form id="myform" name="myform" action="control_findToControl.action">
			<input type="hidden" id="currentHospCode" name="currentHospCode"
				value="${sessionScope.currentHospCode}" />
			<input type="hidden" id="loadingFlag" name="loadingFlag"
				value="${sessionScope.loadingFlag}" />
			<input type="hidden" id="equipId" name="equipId" value="${equipId }" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId}" />
			<input type="hidden" id="groupCode" name="groupCode"
				value="${groupCode }" />
			<input type="hidden" id="eqTypeId" name="eqTypeId"
				value="${eqTypeId }" />
			<input type="hidden" id="alarmCount" name="alarmCount"
				value="${alarmCount }" />
			<input type="hidden" id="jsjStatus" name="jsjStatus"
				value='${jsjStatus}' />
			<input type="hidden" id="showFlag" name="showFlag"
				value='${showFlag }' />
			<!-- 加入影藏属性 widht height -->
			<input type="hidden" id="flashWidth" name="flashWidth"
				value="${flashWidth}" />
			<input type="hidden" id="flashHeight" name="flashHeight"
				value='${flashHeight}' />
		</form>
		<input type="hidden" id="controlPoint" name="controlPoint"
			value='${controlPoint }' />
		<div id="admin_nr_rightg">
			<div
				style="height: 20px; width: 100%; border: 0px solid red; position: fixed; margin-top: 105px;">
				<table style="width: 100%;">
					<tr>
						<td style="padding-left: 30%;">
							<span class="groupNameStyle">【${groupName}】</span>
						</td>
					</tr>
				</table>
			</div>
			<!-- 图片区 -->
			<div style="border: 0px solid red; position: absolute;"
				class="shishijiankong_zxq">

				<div class="flashJsj" id="flashJsj"
					style="margin-top: 150px; margin-left: 50px;">

					<!-- 通过js 方式 load时 加载 flash图片
					 <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
						codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0"
						width="${empty flashWidth?1000:flashWidth}" height="${empty flashHeight?500:flashHeight}">
						<param name="mymovie" value="manager/images/control/flashNew/jsj/jsj${groupId}.swf" /> 
						<param name="quality" value="high"/>
						<param name="wmode" value="transparent"/>
						<embed name="mymovie" src="manager/images/control/flashNew/jsj/jsj${groupId}.swf" wmode="transparent" quality="high"
							pluginspage=" http://www.macromedia.com/shockwave/download/index.cgi?P1_Pr od_Version=ShockwaveFlash"
							type="application/x-shockwave-flash" width="${empty flashWidth?1000:flashWidth}" height="${empty flashHeight?500:flashHeight}">
						</embed>
					</object> -->
				</div>
			</div>
		</div>

		<%--	    <div style=" width:400px; height: 30px; border: 1px solid red;">--%>
		<%--	       <input type="text" id="btnX" name="btnY" />--%>
		<%--	    </div> --%>

	</body>
</html>