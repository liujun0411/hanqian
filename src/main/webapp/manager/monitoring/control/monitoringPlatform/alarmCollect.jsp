<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>医院后勤智能化管理平台——告警</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/voice/js/swfobject.js"></script>

		<script type="text/javascript" >
			var timerS = 0;
			var timerT = 0;
			
			var initiVal=function(){
				try{
					$("#levelo").html("<font color='blue' id='alarmOne'>0</font>");
					$("#levelt").html("<font color='blue' id='alarmTwo'>0</font>");
					$("#levelth").html("<font color='blue' id='alarmThree'>0</font>");
					$("#marcount").html("<font color = 'blue'>0</font>");
				}catch(e){}
			}
			
			var updateRcount=function(){
				initiVal();
				var jsonDoc=$.ajax({
					  type: "POST",
					  url: "alarm_findCurrentAlarmCount.action",
					  dataType: "json",
					  cache: false,
					  async:false,
					  error: function(jsonObj){  
					  },
					  success: function(jsonObj) {
					  		var levelOne = jsonObj.levelOne;
					  		var levelTwo = jsonObj.levelTwo;
					  		var levelThree = jsonObj.levelThree;
					  		//var repairNum = jsonObj.repairNum;
					  	   	var countStr = "";
					  	  	var countStrEnd = "";
				               if(levelOne!=0){
				            	   countStr="<font color='red' id='alarmOne'>"+levelOne+"</font>";
							   }else{
								   countStr="<font color='blue' id='alarmOne'>"+levelOne+"</font>";
							   }
				               countStrEnd ='<a  style="cursor:pointer" href="javascript:void(0);" onclick="submitHiddenForm(\'alarm_findCurrentAlarmInfoLess.action?eqcurrentPageLess=1&level=1&flag=true\');" >'+countStr+'</a>';
				               $("#levelo").html(countStrEnd);
				               if(levelTwo!=0){
				            	   countStr="<font color='red' id='alarmTwo'>"+levelTwo+"</font>";
							   }else{
								   countStr="<font color='blue' id='alarmTwo'>"+levelTwo+"</font>";
							   }
				               countStrEnd ='<a  style="cursor:pointer" href="javascript:void(0);" onclick="submitHiddenForm(\'alarm_findCurrentAlarmInfoLess.action?eqcurrentPageLess=1&level=2&flag=true\')" >'+countStr+'</a>';
					           $("#levelt").html(countStrEnd);
				               if(levelThree!=0){
				            	   countStr="<font color='red' id='alarmThree'>"+levelThree+"</font>";
							   }else{
								   countStr="<font color='blue' id='alarmThree'>"+levelThree+"</font>";
							   }
				               countStrEnd ='<a  style="cursor:pointer" href="javascript:void(0);" onclick="submitHiddenForm(\'alarm_findCurrentAlarmInfoLess.action?eqcurrentPageLess=1&level=3&flag=true\')" >'+countStr+'</a>';
					           $("#levelth").html(countStrEnd);
					        	theAlert();
					           //需要维护的设备数
					        //   var repairStr = "";
					        //  var repairStrEnd = "";
					        //  if(repairNum>0){
					        //	   repairStr="<font color='red'>"+repairNum+"</font>";
							//  }else{
							//	   repairStr="<font color='blue'>"+repairNum+"</font>";
							//   }
					        //   repairStrEnd ='<a  style="cursor:pointer;text-decoration:none" href="javascript:void(0);" onclick="javascript:window.open(\'maintenance_findServeMaintenance.action?type=alert\',\'\',\'height=430,width=780,top=\'+(screen.height-430)/2+\',left=\'+(screen.width-780-10)/2+\',status=no,toolbar=no,menubar=no,location=no\');" >'+repairStr+'</a>';
							  	
					        //   $("#marcount").html(repairStrEnd);
					          
					             
					        }
									  
						
					        });  
					       
				setTimeout(updateRcount,30*1000);
			}
			var submitHiddenForm = function(url){
				$(window.parent.document).find("#deployCollect").attr("src",url);
			}
			
			//告警音乐
			var theAlert=function(){
					$("#emDivId").html('');
					var fobj = $("#alarmOne").html();
					var sobj = $("#alarmTwo").html();
					var tobj = $("#alarmThree").html();
					var divId = "emDivId";
					try{
						if(fobj>0){
							voiceUrl= "images/muisc/alarn/1.mp3";
					    }else if(sobj>0){
					    	voiceUrl= "images/muisc/alarn/2.mp3";
					    }else if(tobj>0){
					    	voiceUrl= "images/muisc/alarn/3.mp3";
					    }
						var cacheBuster = "?t=" + Date.parse(new Date());
			            // swf path
			            var swfPath = "manager/voice/preview.swf";
						var stageW = 1;//560;//"100%";
						var stageH = 1;//300;//"100%";
						
						// ATTRIBUTES
					    var attributes = {};
					    attributes.id = divId;
					    attributes.name = attributes.id;
					    
						// PARAMS
						var params = {};
						params.allowfullscreen = "true";
						params.allowScriptAccess = "always";
						params.bgcolor = "#ffffff";
						params.wmode = "transparent";
			
					    /* FLASH VARS */
						var flashvars = {};				
						
						/// if commented / delete these lines, the component will take the stage dimensions defined 
						/// above in "JAVASCRIPT SECTIONS" section or those defined in the settings xml			
						flashvars.componentWidth = stageW-1;//281; // define these dimensions different then the stage dimension only for preview because the component is samller then the stage
						flashvars.componentHeight = stageH-1;//78;
						
						/// path to the content folder(where the xml files, images or video are nested)
						/// if you want to use absolute paths(like "http://domain.com/images/....") then leave it empty("")
					 	flashvars.pathToFiles = "manager/";
						flashvars.xmlPath = "voice/player/xml/settings.xml";
						
						// other vars
						flashvars.songURL = voiceUrl;
						/** EMBED THE SWF**/
						swfobject.embedSWF(swfPath, attributes.id, stageW, stageH, "9.0.124", "manager/voice/js/expressInstall.swf", flashvars, params);
					}catch (e1){
						
					}
					setTimeout(theAlert,8*1000);
			}
			
			//告警播放
			var palySalter=function(obj){
				obj.play();
			}
			
			//停止三级告警播放
			var stopTimerTPlay=function(){
				clearInterval(timerT);
				timerT = 0;
			}
			
			//停止二级告警播放
			var stopTimerSPlay=function(){
				clearInterval(timerS);
				timerS = 0;
			}
<%--			if(window.XMLHttpRequest)//判断浏览器是否属于Mozilla,Sofari   --%>
<%--			{   --%>
<%--				$("#emDivId").hide();--%>
<%--			}else{--%>
				$("#emDivId").show();
<%--			}--%>
		</script>
		
</head>

<body onload="updateRcount();" style=" background-color: white;">
<form action="" method="get" id="hiddenForm" name = "hiddenForm">
</form>
<table width="100%" border="0" cellspacing="1" bgcolor="#999999"  class="shishijiankong_guolu_zt13"  style="text-align: center; border: none;">
  <tr bgcolor="#DCDDDD" class="shishijiankongtai_313_zt" style=" background-color: #DCDDDD; font-size: 13px; font-family: '黑体'; color:#00487A;"  valign="bottom">
    <td>告警等级</td>
    <td>告警数量</td>
    <td>提&nbsp;&nbsp;示</td>
  </tr>
  <tr style="height:25px; background-color: #F1F1F1;">
    <td style=" color:#404041; font-family: '华文细黑'; font-size: 13px;">一级告警</td>
    <td class="biao_lianj_1"><span class="ss_zhm_lh_zt" id="levelo">&nbsp;</span></td>
    <td style=" color:#404041; font-family: '华文细黑'; font-size: 13px;">紧急处理</td>
  </tr>
  <tr style="height:25px; background-color: #F7F8F8;">
    <td style=" color:#404041; font-family: '华文细黑'; font-size: 13px;">二级告警</td>
    <td class="biao_lianj_1"><span class="ss_zhm_lh_zt" id="levelt">&nbsp;</span></td>
    <td style=" color:#404041; font-family: '华文细黑'; font-size: 13px;">尽快处理</td>
  </tr>
  <tr style="height:25px; background-color: #F1F1F1;">
    <td style=" color:#404041; font-family: '华文细黑'; font-size: 13px;">三级告警</td>
    <td class="biao_lianj_1"><span class="ss_zhm_lh_zt" id="levelth">&nbsp;</span></td>
    <td style=" color:#404041; font-family: '华文细黑'; font-size: 13px;">安排处理</td>
  </tr>
<%--  <tr bgcolor="#FFFFFF" style="height:75px">--%>
<%--    <td colspan="3" align="center">本月保养到期设备 <span class="ss_zhm_lh_zt" id="marcount" >&nbsp;</span> 台</td>--%>
<%--    </tr>--%>
</table>
<%--		--%>
 <div id="emDivId" style="display:none">
</div>
</body>
</html>
