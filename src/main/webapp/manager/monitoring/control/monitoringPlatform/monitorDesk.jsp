<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UFT-8" />
		<title>医院后勤智能化管理平台——后勤管理</title>
		<link href="manager/css/shishijiankong.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="manager/js/swfobject.js">
</script>
		<script type="text/javascript" src="manager/js/monist.js">
</script>
		<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style: none;
}

img {
	border: 0;
}

body {
	font-size: 12px;
	font-family: Tahoma, Arial, Verdana, Helvetica, sans-serif;
	color: #353535;
}

/* 弹出层 */
.dialog-box {
	position: absolute;
	z-index: 1001
}

.dialog-popup { /*background-color:#333333\9;*/
	filter: progid :                 DXImageTransform.Microsoft.gradient (  
		        
		     enabled =      
		  
		       'true', startColorstr =                 '#7F333333',
		endColorstr = 
		 
		      
		      '#7F333333' );
	position: relative;
	z-index: 29;
	zoom: 1;
	background: rgba(51, 51, 51, 0.5);
	padding: 10px;
}

.dialog-popup .dialog-title-bar {
	height: 30px;
	background-color: #CDCDCD;
	position: relative;
}

.dialog-popup .dialog-title-bar h2 {
	line-height: 30px;
	padding-left: 10px;
	font-size: 14px;
	font-weight: 700
}

.dialog-popup .dialog-title-bar a.close-dialog {
	position: absolute;
	top: 5px;
	right: 10px;
	background-color: #FFFFFF;
	border: 0px solid #FF3300;
	font-size: 14px;
	font-weight: 700;
	display: block;
	height: 18px;
	width: 18px;
	line-height: 18px;
	cursor: pointer;
}

.dialog-popup .dialog-title-bar a.close-dialog {
	text-decoration: none;
	color: #555555;
	text-align: center;
}

.dialog-popup .dialog-title-bar a.close-dialog:hover {
	text-decoration: none;
	color: #333333
}

.dialog-content {
	background-color: #FFFFFF;
	padding: 10px
}

.dialog-iframe-mask {
	position: absolute;
	left: 0;
	top: 0;
	border: none;
}

.boxy-modal-blackout {
	position: absolute;
	left: 0;
	top: 0;
	border: none;
	background-color: #333333;
	overflow: hidden;
	z-index: 999
}
#ptitleDiv p{
  text-indent:2em;
}
</style>
		<script type="text/javascript" src="manager/js/jquery.dialog.js">
</script>
		<script type="text/javascript">
getTree = function() {
	var d = new Date();
	var sd = new Date();
	sd.setYear(2011);
	sd.setMonth(9);
	sd.setDate(1);
	var v
= Math.round(((d.getTime()-sd.getTime())/1000/60/12/1000*0.123*2.6/1.83*1.2)*100)/100;
				v = Math.floor(v);
				return v;
			}
			
			Date.prototype.format = function(fmt) 
			{ //author: meizz 
			  var o = { 
			    "M+" : this.getMonth()+1,                 //月份 
			    "d+" : this.getDate(),                    //日 
			    "h+" : this.getHours(),                   //小时 
			    "m+" : this.getMinutes(),                 //分 
			    "s+" : this.getSeconds(),                 //秒 
			    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
			    "S"  : this.getMilliseconds()             //毫秒 
			  }; 
			  if(/(y+)/.test(fmt)) 
			    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
			  for(var k in o) 
			    if(new RegExp("("+ k +")").test(fmt)) 
			  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
			  return fmt; 
			} 
			
			
			var theDate = new Date().format("yyyy年MM月dd日");
			var jieneng = Math.round(Math.random() * 100);
		</script>
		<script type="text/javascript">
function show(obj) {
	var title = $(obj).attr('showTitle');
	var content = "<img src='" + $(obj).val()
			+ "' style='width:650px; height:380px;'>";
	var dialogBox1 = new Dialog(content, {
		"width" : "680px",
		"title" : title
	});
}

$(function() {
	$('.tab_table td').click(
			function() {
				$(this).parent().find('td').removeClass('selTd');
				$(this).addClass('selTd');
				$("#" + $(this).attr('pHref')).parent().find('div').hide();
				if ($("#" + $(this).attr('pHref')).find('iframe').eq(0).attr(
						'src') == "") {
					$("#" + $(this).attr('pHref')).find('iframe').attr( {
						src : $(this).attr('path') + "&t=" + Math.random()
					});
				}
				$("#" + $(this).attr('pHref')).show();
			});

	$('.tab_table1 td').click(function() {
		$(this).parent().find('td').removeClass('selTd');
		$(this).addClass('selTd');
		$("#" + $(this).attr('pHref')).parent().find('div').hide();
		$('#' + $(this).attr('pHref')).show();
	});
});

</script>
	</head>

	<body onload="swfLoad()">
		<div class="shishijiankongtai_admin">
			<input type="hidden" id="imgPath" value="" showTitle=""
				onclick="show(this);" />
			<input type="hidden" id="speciaEqFlash" name="speciaEqFlash" value="${sessionScope.speciaEqFlash}" />
			<!-- 告警第一排 -->
			<div class="shishijiankongtai_4">
				<div class="shishijiankongtai_51">
					<table width="165" align="left" class="table1">
						<tr style="height: 20px;">
							<td width="25px" align="center" valign="top">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td valign="top">
								特种设备安全指数
							</td>
						</tr>
					</table>
				</div>
				<div class="shishijiankongtai_54">
					<table width="165" align="left" class="table1">
						<tr style="height: 20px;">
							<td width="25px" align="center" valign="top">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td valign="top">
								综合能耗指数
							</td>
						</tr>
					</table>
				</div>
		
				<div class="shishijiankongtai_52"
					style="float: right; margin-bottom: 15px;">
					<div class="b d3 k" style="height: 185px;" id="specia" tishi="<p>特种设备包括空调、锅炉、电梯、电力，安全指数是各类设备当前各等级报警发生个数。</p>
<p>颜色定义分别为：一级（红色）；二级（橙色）；三级（黄色）。</p>" martop="200px" marleft="650px">
					<iframe width="100%" height="180" scrolling="no" frameborder="0"
						name="gauge" id="gauge" ></iframe>
				
					</div>
					<b class="b4b d3"></b><b class="b3b d3"></b><b class="b2b d3"></b><b
						class="b1b"></b>
				</div>
				<div class="shishijiankongtai_53" style="float: left">
					<div class="b d3 k" style="height: 185px;">
						<iframe width="100%" height="185" scrolling="no" frameborder="0"
							name="showElectSave" id="showElectSave"
							src="electricityAction_getEnergySaveing.action"></iframe>
					</div>
					<b class="b4b d3"></b><b class="b3b d3"></b><b class="b2b d3"></b><b
						class="b1b"></b>
				</div>
			</div>

			<!-- 告警第二排 -->
			<div class="shishijiankongtai_4">
				<div class="shishijiankongtai_51">
					<table width="165" align="left" class="table1">
						<tr style="height: 20px;">
							<td width="25px" align="center" valign="top">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td valign="top">
								变压器电流峰值
							</td>
						</tr>
					</table>
					<table width="128px" align="right" class="tab_table">
						<tr style="height: 20px;">
							<td width="64px" align="center" class="norTd selTd"
								pHref="totalCount"
								path="electricityAction_createAndShowElectZHdianliu.action?eqTypeId=9002&flag=total">
								综合电流
							</td>
							<td width="64px" align="center" class="norTd" pHref="singleCount"
								path="electricityAction_createAndShowElect3Adianliu.action?eqTypeId=9002&flag=singin">
								三相电流
							</td>
						</tr>
					</table>
				</div>
				<div class="shishijiankongtai_54">
					<table width="165" align="left" class="table1">
						<tr style="height: 20px;">
							<td width="25px" align="center" valign="top">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td valign="top">
								综合安全指数
							</td>
						</tr>
					</table>
					<table width="128px" align="right" class="tab_table1">
						<tr style="height: 20px;">
							<td width="64px" align="center" class="norTd selTd"
								pHref="realTime">
								实时
							</td>
							<td width="64px" align="center" class="norTd" pHref="history">
								历史
							</td>
						</tr>
					</table>
				</div>
				<div class="shishijiankongtai_52"
					style="float: right; margin-bottom: 15px;">
					<div class="b d3 k" style="height: 220px;">
						<div id="totalCount">
							<iframe width="100%" height="220" scrolling="no" frameborder="0"
								name="showElectPic" id="showElectPic"
								src="electricityAction_createAndShowElectZHdianliu.action?eqTypeId=9002&flag=total"></iframe>
						</div>
						<div id="singleCount" style="display: none;">
							<iframe width="100%" height="220" scrolling="no" frameborder="0"
								name="showElectPic" id="showElectPic" src=""></iframe>
						</div>
					</div>
					<b class="b4b d3"></b><b class="b3b d3"></b><b class="b2b d3"></b><b
						class="b1b"></b>
				</div>
				<div class="shishijiankongtai_53" style="float: left">
					<div class="b d3 k" style="height: 220px;">
						<div id="realTime" name="realTime" tishi="<p>综合安全指数（实时）是指监控平台内所有监控设备当前监控点位的发生比例。
			分为四个级别。分别是无告警、一级告警、二级告警、三级告警。</p>
			各级别的划分公式为： <br/>
			<p>无告警:暂无告警，显示绿色。</p> 
			<p>一级告警:一级告警个数，显示红色。</p> 
			<p>二级告警:二级告警个数，显示橘色。</p> 
			<p>三级告警:三级告警个数，显示黄色。 </p>
			" martop="450px" marleft="120px" >		
			<!-- 修改成指盘形式 -->
			<iframe width="100%" height="170" scrolling="no" frameborder="0"
						name="zhipanzhishu" id="zhipanzhishu" ></iframe>					
						</div>
						<div id="history" name="history" style="display: none;" tishi="<p>综合安全指数（历史）是最近一个月内，各等级报警发生比例，即各等级报警次数除以该等级点位总数。规则：一级（红色），二级（橙色），三级（黄色），无任何告警（绿色）。</p>
<p>例如：三级报警在10月份发生了10次，监控的点位中三级报警的点位数为100个，则报警发生比例= 10%，罗盘指针根据各告警等级发生比例，偏向比例位置。</p>" martop="400px" marleft="120px">
						</div>
					</div>
					<b class="b4b d3"></b><b class="b3b d3"></b><b class="b2b d3"></b><b
						class="b1b"></b>
				</div>
			</div>


			<!--汇总信息开始-->
			<div class="shishijiankongtai_4">
				<div class="shishijiankongtai_51">
					<table width="165" align="left" class="table1">
						<tr style="height: 20px;">
							<td width="25px" align="center" valign="top">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td valign="top">
								当前告警列表
							</td>
						</tr>
					</table>
				</div>
				<div class="shishijiankongtai_54">
					<table width="165" align="left" class="table1">
						<tr style="height: 20px;">
							<td width="25px" align="center" valign="top">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td valign="top">
								当前告警汇总
							</td>
						</tr>
					</table>
				</div>
				<div class="shishijiankongtai_52"
					style="float: right; margin-bottom: 15px;">
					<div class="b d3 k">
						<iframe width="100%" height="187" scrolling="no" frameborder="0"
							name="deployCollect" id="deployCollect"
							src="alarm_findCurrentAlarmInfoLess.action?eqcurrentPageLess=1"></iframe>
					</div>
					<b class="b4b d3"></b><b class="b3b d3"></b><b class="b2b d3"></b><b
						class="b1b"></b>
				</div>
				<div class="shishijiankongtai_53" style="float: left">
					<div class="b d3 k">
						<iframe width="100%" height="187" scrolling="no" frameborder="0"
							name="alertCollect" id="alertCollect"
							src="manager/monitoring/control/monitoringPlatform/alarmCollect.jsp"></iframe>
					</div>
					<b class="b4b d3"></b><b class="b3b d3"></b><b class="b2b d3"></b><b
						class="b1b"></b>
				</div>
			</div>
			<!--汇总信息结束-->
		</div>
		<!--关键设备开始-->
		<div class="shishijiankongtai_4" style="background-color: white;">
			<div class="shishijiankongtai_41">
				<!-- 关键设备组选择 -->
				<iframe width="100%" height="36" scrolling="no" frameborder="0"
					name="selGroup" id="selGroup" src="keyEqAction_showSelGroup.action"></iframe>
			</div>
			<div class="shishijiankongtai_42" style="background-color: white;">
				<div class="b d3 k">
					<iframe width="100%" height="160" scrolling="no" frameborder="0"
						name="keyEqCollect" id="keyEqCollect" src="manager/monitoring/control/monitoringPlatform/keyEqCollect.jsp"></iframe>
				</div>
				<b class="b4b d3"></b><b class="b3b d3"></b><b class="b2b d3"></b><b
					class="b1b"></b>
			</div>
		</div>
		<!--关键设备结束-->
		
		
		<!--监控提示div -->
		<div id="zhaq" style=" position: absolute; width: 500px; background-color: white; color: black; display: none;"> 
			<b class="b1"></b>
			<b class="b2 d4"></b>
			<b class="b3 d4"></b>
			<b class="b4 d4"></b>
			<div id="ptitleDiv" class="b d4 k" style=" padding-left: 4px; padding-bottom: 5px;">
			
			</div>
			<b class="b4b d4"></b>
			<b class="b3b d4"></b>
			<b class="b2b d4"></b>
			<b class="b1b"></b>
		</div>
	</body>
</html>
<script type="text/javascript">
$(function(){
   $('#realTime').mouseover(function(){
   	  $('#ptitleDiv').html($(this).attr('tishi'));
   	  $('#zhaq').css({"left":$(this).attr('marleft')});
   	  $('#zhaq').css({"top":$(this).attr('martop')});
      $('#zhaq').show();
   }).mouseout(function(){
      $('#zhaq').hide();
   });
   $('#history').mouseover(function(){
   	  $('#ptitleDiv').html($(this).attr('tishi'));
   	  $('#zhaq').css({"left":$(this).attr('marleft')});
   	  $('#zhaq').css({"top":$(this).attr('martop')});
      $('#zhaq').show();
   }).mouseout(function(){
      $('#zhaq').hide();
   });
   $('#specia').mouseover(function(){
   	  $('#ptitleDiv').html($(this).attr('tishi'));
   	  $('#zhaq').css({"left":$(this).attr('marleft')});
   	  $('#zhaq').css({"top":$(this).attr('martop')});
      $('#zhaq').show();
   }).mouseout(function(){
      $('#zhaq').hide();
   });
});

function swfLoad() {
	if($("#speciaEqFlash").val()=="y"){
		var so = new SWFObject("manager/images/control/flashNew/realTime.swf?num="
				+ new Date(), "real", "320", "230", "10", "write");
		so.addParam("wmode", "transparent");
		so.write("realTime");
	}else{
		$("#zhipanzhishu").attr("src","manager/monitoring/control/monitoringPlatform/highchartsView.jsp");
	}
	if($("#speciaEqFlash").val()=="y"){
		var special = new SWFObject(
				"manager/images/control/flashNew/specialEq.swf?num=" + new Date(),
				"special", "700", "200", "10", "write");
		special.addParam("wmode", "transparent");
		special.write("specia");
	}else{
		$("#gauge").attr("src","manager/monitoring/control/monitoringPlatform/specialEq.jsp");
	}
	var history = new SWFObject(
			"manager/images/control/flashNew/history.swf?num=" + new Date(),
			"special", "320", "230", "10", "write");
	history.addParam("wmode", "transparent");
	history.write("history");
}


function jsRefresh() {
	try{
	    if(thisMovie("real")&&thisMovie("real")!=null){
		thisMovie("real").Refresh();
		}
	}catch(e){
	}
}

setInterval(jsRefresh, 30000);

function thisMovie(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		return window[movieName];
	} else {
		return document[movieName];
	}
}
</script>
