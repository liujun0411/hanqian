<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		String path1 = request.getContextPath();
		String basePath1 = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path1 + "/";
	%>
	<head>
		<base href="<%=basePath1%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>医院后勤智能化管理平台</title>
		<link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/common/tip/css/manhuaTip.1.0.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/Login.js"></script>
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script src="manager/js/jquery.easyui.min.js" type="text/javascript"></script>
		<style type="text/css">
		  .body_workbench{
		     margin:0 auto;
		     width:1440px;
		  }
		  
		  span.joyride-nub1.top {
		    /*border-bottom-color: rgba(0, 0, 0, 0.8);*/
		    border-bottom-color:#FFFFFF;
		    border-left-color: transparent !important;
		    border-right-color: transparent !important;
		    border-top-color: transparent !important;
		    top: -22px;
		}
		 span.joyride-nub1 {
		    border: 11px solid;
		    display: block;
		    height: 0;
		    left: 65px;
		    position: absolute;
		    width: 0;
		}
		
		
		.divLeft{
		       border-left:2px solid black;
		       border-right:2px solid black;
		       width:26px;
		       position:absolute;
		       height:8px; 
		       top:8px; 
		       left:0px;
		       filter:alpha(opacity=20);
		       -moz-opacity:0.2;
		       -khtml-opacity: 0.2; 
		       opacity: 0.2;  
		       
		    }
		    .divCenter{
		        border-left:2px solid red; 
		        width:0px;
		        position:absolute;
		        height:24px; 
		        top:0px; 
		        left:14px;
		        filter:alpha(opacity=100);
		        -moz-opacity:1;
		        -khtml-opacity: 1; 
		        opacity: 1;  
		        
		    }
		    .divMiddle{
		       border-left:2px solid blue;
		       border-right:2px solid blue; 
		       width:12px;
		       position:absolute;
		       height:16px; 
		       top:4px;
		       left:7px;
		       filter:alpha(opacity=40);
		       -moz-opacity:0.4;
		       -khtml-opacity: 0.4; 
		       opacity: 0.4;  
		       
		    }
		    .h3,h4{
		      text-align: center;
		      padding-right:10px;
		      font-weight: normal;
		    }
		    .sourceDate{
		       text-align:right;
		       font-size:8px;
		       color:gray;
		       padding-right:5px;
		    }
		</style>
		<script type="text/javascript" src="manager/common/tip/js/manhuaTip.1.0.js"></script>
		
			<script>
$(document).ready(function() {
	//$("#gai_daohangyiyuan").hide();
		//$("#hospDaohang").hide();
		$("#srm").click(function() {
			$("#gai_daohangyiyuan").show();
			$("#hospDaohang").hide();
		});
		$("#climg").click(function() {
			$("#gai_daohangyiyuan").hide();
			$("#hospDaohang").show();
		});

		$("#srm").hover(function() {
			/*
			if($("#ishide").attr("value")=="true"){
				$("#gai_daohangyiyuan").show();
				$("#hospDaohang").hide();
			}
			 */
		}, function() {

		});
		$("#gai_daohangyiyuan").hover(function() {

		}, function() {
			/*
			if($("#ishide").attr("value")=="true"){
				$("#gai_daohangyiyuan").hide();
				$("#hospDaohang").show();
			}
			 */
		});
	});
</script>
			<!-- 弹出层 -->
			<link href="manager/common/fullScreen/style.css" rel="stylesheet"
				type="text/css" />
			<script type="text/javascript"
				src="manager/common/fullScreen/tipswindown.js">
</script>
			<script type="text/javascript">
/* *弹出本页指定ID的内容于窗口 *id 指定的元素的id *title: window弹出窗的标题 *width: 窗口的宽,height:窗口的高 */
function showTipsWindown(title, id, width, height) {
	tipsWindown(title, "id:" + id, width, height, "true", "", "true", id);
}
function confirmTerm(s) {
	parent.closeWindown();
}
//弹出层调用
function popTips() {
	showTipsWindown("点位告警提示", 'simTestContent', 600, 255);
}
$(document).ready(function() {
	$("#isread-text").click(popTips);
	$('#warn_sum').click(popTips);
});

function aa() {
	$(".titleChange").manhuaTip( {
		Event : "click",//可选				       
		timeOut : 5000
	//停留时间
			});
}

window.onresize = function(){  
   $("#middle").contents().find("#reloadCss").click();  
   $("#top").contents().find("#reloadScreen").click();  
}  

$(function(){
  $('#div_change').mouseout(function(){
      //如果top页面的样式没有移除
      $(document.getElementById('top').contentWindow.document).find('#doChange').removeClass('change_hover');
      if(!$(document.getElementById('top').contentWindow.document).find('#doChange').hasClass('change_hover') && !($(document.getElementById('top').contentWindow.document).find('#doChange').hasClass('active')) ){
         $('#div_change').hide();
      }
  }).mouseover(function(){
      $(document.getElementById('top').contentWindow.document).find('#doChange').addClass('change_hover');
      $(document.getElementById('top').contentWindow.document).find('#doChange').addClass('active');
  });
  
});

		//自定义数组indexOf下表属性方法
		Array.prototype.indexOf = function(val) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == val) return i;
            }
            return -1;
        };
        //自定义移除数组中指定的元素
        Array.prototype.remove = function(val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };
 //支持切换的菜单类型
 var eqTypeList=new Array();
 eqTypeList.push('1');
 eqTypeList.push('2');
 eqTypeList.push('3');
 eqTypeList.push('4');
 eqTypeList.push('5');
 eqTypeList.push('6');
 eqTypeList.push('7');
 eqTypeList.push('8');
 eqTypeList.push('9');
 eqTypeList.push('10');
 eqTypeList.push('11');
 
function doOper(){
  var tpId=$('#myOper').attr('eqTypeId');
  if($.trim(tpId)!=""){
	  if(eqTypeList.indexOf(tpId)>0){
	     $(document.getElementById('top').contentWindow.document).find('#ahref_4').click();
	  }else{
	     $.ajax({
			type : "POST",
			url : "equipment_findEquipType.action",
			data:{
				eqTypeId:tpId
			},
			dataType : "json",
			cache : false,
			async : false,
			error : function(jsonObj) {
			},
			success : function(jsonObj) {
				if(jsonObj.types!='error'){
				   var typeList= jsonObj.types.split(',');
				   $('#myOper').attr({"eqTypeId":typeList[typeList.length-1]});
				   $('#myOper').attr({"childTypeId":typeList[typeList.length-2]});
				   $(document.getElementById('top').contentWindow.document).find('#ahref_4').click();
				}
			}
		});
	  }
   }
}
</script>

		</head>

		<body style=" background-color: white;">
		<input type="hidden" id="menuTipStatus" name="menuTipStatus" value="${sessionScope.menuTip}" />	
		<input type="hidden" id="myOper" onclick="doOper()" eqTypeId="" childTypeId=""/>
          <div id="body_workbench" class="body_workbench">
			<!--页面开始 -->
			<div class="agreesddd">
				<label id="isread-text" style="display: none;">
					弹出层特效
				</label>
			</div>
			<div style="display: none;">
				<div id="simTestContent" class="simScrollCont">
					<iframe src="" id="showFrames" scrolling=no width="600"
						height="255" marginwidth=0 marginheight=0 frameborder=0></iframe>
				</div>
			</div>

			<div id="admin_zong">
				<div id="fenzhan_top">
					<div style="display: none;">
						<a id="myloading" style="display: none;" class="titleChange"
							href="javascript:void(0)" onclick="aa()" title="loading"
							ty="loading" msg="正在提交您的请求，请稍候...">mmm</a>
					</div>
					<!--告警提示 -->
					<div class="control_warn" id="control_warnDiv"
						style="border: 0px solid red; display: none; z-index: 9999; margin-top: 230px;">
						<table class="control_table">
							<tr>
								<td style="width: 30px;" align="right" valign="middle">
									<img src="manager/images/control/warn.png" alt="warn"
										style="height: 30px; width: 30px;" />
								</td>
								<td width="60px" valign="middle">
									共
									<a id="warn_sum" href="javascript:void(0)"
										style="font-weight: bold; color: red;"></a> 条告警
								</td>
							</tr>
						</table>
					</div>
					
					<!-- 天气预报 -->
		            <div id="divWeatherMore" style="display:none;width: 150px; margin-top:160px; box-shadow: 2px 2px 3px  #888888;border:1px solid #BFBFBF;background: #FFFFFF; position: absolute; z-index:9999; margin-left: 1000px; border-radius:10px; ">
				  	    <span class="joyride-nub1 top"></span>
				  	    <div id="content" style="padding:0px 0px 0px 10px;">       
				 		</div>
				    </div>
					
<!--					<div id="div_change" class="control_change">-->
					   
<!--					</div>-->

					<!-- 顶部菜单 parentId:所属上级菜单Id level:菜单显示界面   showFlag:选中菜单项  -->
					<iframe src="user!findMyTopMenus.action" scrolling="no"
						width="1440" allowTransparency="true" marginwidth="0"
						marginheight="0" frameborder="0"  name="top" height="165" id="top"></iframe>
				</div>
				<div
					style="height: 2px; width: 100%; border: 0px solid red; margin-top: 5px;">
					&nbsp;
				</div>
				<!--正文内容   由[顶部菜单] 动态加载 -->
				<iframe src="" scrolling="no" width="100%;" height="1000"
					marginwidth="0" marginheight="0" allowTransparency="true"
					frameborder="0" name="middle" id="middle"></iframe>

				<script type="text/javascript">
					function reinitIframe(x,y) {
						var iframe = document.getElementById("middle");
						try {
							//iframe.height =  iframe.contentWindow.document.documentElement.scrollHeight;
							iframe.height =y;
							iframe.width=x;
						} catch (ex) {
					
						}
					}
					window.setInterval("reinitIframe(1440,1020)", 10);
					</script>
				<!-- 扩展区   底部内容  -->
				<!-- 底部的DIV -->
				<div id="bottomDiv">
					<iframe src="manager/common/bottom.jsp" scrolling=no width=100%
						height=80 marginwidth=0 marginheight=0 frameborder=0 name="buttom"></iframe>
				</div>

			</div>
          </div>
			<script type="text/javascript">
var lastScrollY = 0;
function heartBeat() {
	diffY = document.documentElement.scrollTop;
	percent = 0.1 * (diffY - lastScrollY);
	if (percent > 0)
		percent = Math.ceil(percent);
	else
		percent = Math.floor(percent);
	//document.getElementById("gaijiantou").style.top = parseInt(document
	//.getElementById("gaijiantou").style.top)
	//+ percent + "px";
	lastScrollY = lastScrollY + percent;
}
window.setInterval("heartBeat()", 1);
</script>
			<!--左边导航结束 -->
<%--			<c:if test="${!empty equipList}">--%>
<div id="div_cacu" style="display: none">
				<div class="smallImg"  style="display: block">
					<div class="smallClose"  style="cursor: pointer;">
						<table width="165" align="left" class="table1" style="margin-left:15px;">
							 <tbody>
							  	<tr style="height:20px;">
							    	<td width="20px" valign="top" >
							       	<img src="manager/images/common/28-01.png"/>  
							    	</td>
							    	<td  valign="top" style="font-family: '楷体';font-size:15px;" >告警
							    	</td>
							  </tr>
							</tbody>
						</table>
						&nbsp;
					</div>
					<div  class="contentssss" style="margin-top:5px;">
					  <c:if test="${num>0}">
						<table id="showxunjian" style='margin-left:20px;margin-top:4px;text-align: center;font-weight: bold;' width='85%' border='0'cellspacing='1' id='gaojing' bgcolor='#5483ae'  class='shishijiankong_guolu_zt13'>
							 <tr style="height:20px;background-color:#b1dcf3;">
						 		 <td colspan="2" style=" color:#333333; font-family:'楷体'; font-size: 14px;font-weight: bold;">巡检信息</td>
						 	 </tr>
						 	  <tr style="height:20px;background-color: #e1f2fa;">
						 		 <td style="width:50%; color:#737070; font-family: '楷体'; font-size: 13px;font-weight: bold;">需维修设备个数</td>
						 		 <td class="biao_lianj_1"><span class="ss_zhm_lh_zt" id="levelt"><a target="_blank" href="maintenance_findServeMaintenance.action" style="color:#737070;">${num}</a> </span></td>
						 	 </tr>
						</table>
						<div style="height: 4px;"></div>
						</c:if>
					</div>
				</div>
				
			     <div class="smImg"  style="display: none">
					<div class="smClose" >
						<table width="165" align="left" class="table2" style="margin-left:15px;">
							 <tbody>
							  	<tr style="height:20px;">
							    	<td width="20px" valign="top" >
							       	<img src="manager/images/common/28-01.png"/>  
							    	</td>
							    	<td  valign="top" style="font-family: '楷体';font-size:15px;" >告警
							    	</td>
							  </tr>
							</tbody>
						</table>
						&nbsp;
					</div>
				</div>
				
				</div>
<%--			</c:if>--%>

            
		</body>
</html>

<style type="text/css">
.smImg{
	background-position: -1px -3px; /*aa
       -webkit-box-shadow: #666 0px 0px 10px;
        -moz-box-shadow: #666 0px 0px 10px;
        box-shadow: #666 0px 0px 10px;
        border-radius: 5px 5px 0px 0px;*/
	width: 200px;
	height: 30px;
	bottom: 0px;
	position: fixed;
	right: 0px;
	margin: 0px 5px 5px 0px;
	_background-attachment: fixed;
	_position: absolute;
	_bottom: auto;
	_top: expression(eval(document.documentElement.scrollTop +       
		       document.documentElement.clientHeight-this.offsetHeight- ( 
		      
		      parseInt(this.currentStyle.marginTop, 10) ||                   0
		) -(   
		      
		      parseInt(this.currentStyle.marginBottom, 10) ||             0 )
		) );
	_right: auto;
	_left: expression(eval(document.documentElement.scrollLeft +    
	                  
		                 
		     document.documentElement.clientWidth-this.offsetWidth) -( 
		         
		     parseInt(this.currentStyle.marginLeft, 10) ||                   0
		) -(  
		   
		   
		        parseInt(this.currentStyle.marginRight, 10) ||
		                 
		 0 ) );
	z-index: 10000;
	_right: auto;
	_left: expression(eval(document.documentElement.scrollLeft +   
		                   
		                 
		     document.documentElement.clientWidth-this.offsetWidth) -( 
		         
		     parseInt(this.currentStyle.marginLeft, 10) ||                   0
		) -(  
		   
		   
		        parseInt(this.currentStyle.marginRight, 10) ||
		                 
		 0 ) );
}
.smallImg {
	background-image: url(manager/images/publicImg/flashplay.jpg);
	background-repeat: no-repeat;
	background-position: -1px -3px; /*aa
       -webkit-box-shadow: #666 0px 0px 10px;
        -moz-box-shadow: #666 0px 0px 10px;
        box-shadow: #666 0px 0px 10px;
        border-radius: 5px 5px 0px 0px;*/
	width: 261px;
	height: 220px;
	bottom: 0px;
	position: fixed;
	right: 0px;
	margin: 0px 5px 5px 0px;
	_background-attachment: fixed;
	/* 告警浮窗-告警提示、二级告警不可点击。
	_position: absolute;
	*/
	_bottom: auto;
	_top: expression(eval(document.documentElement.scrollTop +       
		       document.documentElement.clientHeight-this.offsetHeight- ( 
		      
		      parseInt(this.currentStyle.marginTop, 10) ||                   0
		) -(   
		      
		      parseInt(this.currentStyle.marginBottom, 10) ||             0 )
		) );
	_right: auto;
	_left: expression(eval(document.documentElement.scrollLeft +    
	                  
		                 
		     document.documentElement.clientWidth-this.offsetWidth) -( 
		         
		     parseInt(this.currentStyle.marginLeft, 10) ||                   0
		) -(  
		   
		   
		        parseInt(this.currentStyle.marginRight, 10) ||
		                 
		 0 ) );
	z-index: 10000;
	_right: auto;
	_left: expression(eval(document.documentElement.scrollLeft +   
		                   
		                 
		     document.documentElement.clientWidth-this.offsetWidth) -( 
		         
		     parseInt(this.currentStyle.marginLeft, 10) ||                   0
		) -(  
		   
		   
		        parseInt(this.currentStyle.marginRight, 10) ||
		                 
		 0 ) );
}

.smalls {
	position: absolute;
	height: 70px;
}

.contentssss {
	border-top: 1px dashed #ccc;
	font-size: 20px;
	text-align: center;
}

.fontDiv {
	font-size: 14px;
}

}
.smallClose { /* padding: 0px 5px 0px 25px;*/
	width: 98%;
	height: 18px;
	margin: 0px;
	font-size: 14px;
	font-weight: bold;
	float: right;
	cursor: pointer;
}
.smClose { /* padding: 0px 5px 0px 25px;*/
	width: 98%;
	height: 18px;
	margin: 0px;
	font-size: 14px;
	font-weight: bold;
	float: right;
	cursor: pointer;
}
</style>



<script type="text/javascript">
var colors = new Array("FFF","F00");
var iIndex=0;
var t;
//设定标题闪烁
function dbShanshuo(){
    //alert($('.table1 tr td').eq(1).text());
    $('.table1 tr td').eq(1).css('color','#'+colors[iIndex]);
    $('.table2 tr td').eq(1).css('color','#'+colors[iIndex]);
    if(iIndex==1){
       iIndex=0;
    }else{
       iIndex=1;
    }
    t=setTimeout("dbShanshuo()",400);
}



var showStr;
var spanStr;
var suiyistart =  false;
updateRcount = function() {
    clearTimeout(t);
	var jsonDoc = $
			.ajax( {
				type : "POST",
				url : "alarm_findCurrentAlarmCount.action",
				dataType : "json",
				cache : false,
				async : false,
				error : function(jsonObj) {
				},
				success : function(jsonObj) {
					var levelOne = jsonObj.levelOne;
					var levelTwo = jsonObj.levelTwo;
					var levelThree = jsonObj.levelThree;
					spanStr = "";
					var jinggaoObj = $(".contentssss table");
					//alert(jinggaoObj.length);
					for(var j = 0; j<jinggaoObj.length;j++){
						if($(jinggaoObj[j]).attr("id")=="showJinggao"){
							$(jinggaoObj[j]).next().remove();
							$(jinggaoObj[j]).remove();
						}
					}   
					    var checkCacu = $("#levelt a").html();
						if(levelOne==0&&levelTwo==0&&levelThree==0&&checkCacu==null){
							if(suiyistart==true){
								$("#div_cacu").show();
	                        }
							if(suiyistart==false){
							    $("#div_cacu").hide();
	                        }
						}else{
							$("#div_cacu").show();
						}
						var alarmTitle = "";
						var alarmVal = "";
						if(levelOne!=0||levelTwo!=0||levelThree!=0){
							dbShanshuo();
							//alert("gaojing");
							//<table id="showxunjian" style='margin-left:20px;text-align: center;font-weight: bold;' width='85%' border='0'cellspacing='1' id='gaojing' bgcolor='#5483ae'  class='shishijiankong_guolu_zt13'>
						    spanStr += "<table id='showJinggao' style='margin-left:20px;margin-top:4px;text-align: center;font-weight: bold;' width='85%' border='0'cellspacing='1' id='gaojing' bgcolor='#5483ae'  class='shishijiankong_guolu_zt13'>";
			            
							spanStr += '<tr name="trTip" style="height:20px;background-color:#b1dcf3;">';
							spanStr += ' <td colspan=3 style=" color:#333333; font-family: \'楷体\'; font-size: 14px;font-weight: bold;">告警提示</td>';
							spanStr += '</tr>';
							alarmTitle += '<tr id="titleAlarm" name="trTip" style="height:20px; background-color: #e1f2fa;">';
							alarmVal += '<tr id="alarmVal" name="trTip" style="height:20px; background-color: #e1f2fa;">';
//e60101
							alarmTitle += ' <td style=" color:#737070;font-weight: bold; font-family: \'楷体\'; font-size: 13px;width:33%">一级告警</td>';
							alarmVal += ' <td class="biao_lianj_1"><span class="ss_zhm_lh_zt" id="levelo"><a target="_blank" href="alarm_findCurrentAlarmInfo.action?eqcurrentPage=1&level=1&flag=false" style="color:#e60101;">'+ levelOne+'</a></span></td>';
//#f95d5d
							alarmTitle += '   <td style=" color:#737070; font-family: \'楷体\'; font-size: 13px;">二级告警</td>';
							alarmVal += '   <td class="biao_lianj_1"><span class="ss_zhm_lh_zt" id="levelt"><a target="_blank" href="alarm_findCurrentAlarmInfo.action?eqcurrentPage=1&level=2&flag=false" style="color:#e60101;">'+ levelTwo+'</a></span></td>';
//ff9999
							alarmTitle +=  '  <td style=" color:#737070; font-family: \'楷体\'; font-size: 13px;">三级告警</td>';
							alarmVal +=  '  <td class="biao_lianj_1"><span class="ss_zhm_lh_zt" id="levelth"><a target="_blank" href="alarm_findCurrentAlarmInfo.action?eqcurrentPage=1&level=3&flag=false" style="color:#e60101;">'+ levelThree+'</a></span></td>';
							alarmTitle += "</tr>";
							alarmVal += "</tr>";
							spanStr += alarmTitle;
							spanStr += alarmVal;
							spanStr +="</table>";
						}
						$(".contentssss").prepend(spanStr);
						
				}
			})
			setTimeout(updateRcount,60*1000);
};


//随意提醒功能

var showStr;
var spanStr1;
updateRcountSuiYi = function() {
    clearTimeout(t);
	var jsonDoc = $
			.ajax( {
				type : "POST",
				url : "alarm_findCurrentAlarmCountSuiYi.action",
				dataType : "json",
				cache : false,
				async : false,
				error : function(jsonObj) {
				},
				success : function(jsonObj) {
					dbShanshuo();
					var levelOne = jsonObj.suiyiCounts;
					spanStr1 = "";
					var jinggaoObj = $(".contentssss table");
					for(var j = 0; j<jinggaoObj.length;j++){
						if($(jinggaoObj[j]).attr("id")=="showSuiYi"||$(jinggaoObj[j]).attr("id")=="showJinggao"){
						//$(jinggaoObj[j]).next().remove();
							$(jinggaoObj[j]).remove();
						}
					}   
					    var checkCacu = $("#suiyi a").html();
						if(levelOne!=0){
						//	$("#div_cacu").hide();
						//}else{
							 suiyistart = true ;
							$("#div_cacu").show();
						}
						var alarmTitle = "";
						var alarmVal = "";
						if(levelOne!=0){
						    spanStr1 += "<table id='showSuiYi' style='margin-left:20px;margin-top:4px;text-align: center;font-weight: bold;' width='85%' border='0'cellspacing='1' id='gaojing' bgcolor='#5483ae'  class='shishijiankong_guolu_zt13'>";
							spanStr1 += '<tr name="trTip" style="height:20px;background-color:#b1dcf3;">';
							spanStr1 += ' <td colspan=2 style=" color:#333333; font-family: \'楷体\'; font-size: 14px;font-weight: bold;">任意提醒</td>';
							spanStr1 += ' <td class="biao_lianj_1">&nbsp;&nbsp;<span class="ss_zhm_lh_zt" id="suiyi"><a target="_blank" href="alarm_findCurrentAlarmInfoSuiYi.action" style="color:#e60101;">'+levelOne+'</a></span>&nbsp;&nbsp;</td>';
							spanStr1 += '</tr>';
							spanStr1 +="</table>";
							//alert(spanStr1);
							}
						$(".contentssss").prepend(spanStr1);
				}
			})
			setTimeout(updateRcountSuiYi,60*1000);
};


//获得提醒的菜单的个数

getReminders = function(){
	 clearTimeout(t);
    var menuValid = $.ajax( {
				type : "POST",
				url : "menu!getReminders.action",
				dataType : "json",
				cache : false,
				async : false,
				error : function(jsonObj) {
				},
				success : function(jsonObj) {
					dbShanshuo();
					var checkCacu = $("#levelt a").html();
					var  jinggao = $("#showJinggao").html();
					if(jsonObj.record<=0&&checkCacu==null&&jinggao==null){
						$("#div_cacu").hide();
					}else{
						$("#div_cacu").show();
					}
					var jinggaoObj = $(".contentssss table");
					for(var j = 0; j<jinggaoObj.length;j++){
						if($(jinggaoObj[j]).attr("id")=="showGongnneng"){
<%--							$(jinggaoObj[j]).next().remove(); --%>
							$(jinggaoObj[j]).remove();
						}
					}
					var RemindersHtml = "";
				    if(jsonObj.record > 0){
				    	RemindersHtml += "<table id='showGongnneng' style='margin-left:20px;margin-top:4px;text-align: center;font-weight: bold;' width='85%' border='0'cellspacing='1' id='gaojing' bgcolor='#5483ae'  class='shishijiankong_guolu_zt13'>";
			            RemindersHtml += '<tr name="trMenu" style="height:20px;background-color:#b1dcf3;">';
						RemindersHtml += ' <td colspan=2 style=" width:35%;color:#333333; font-family: \'楷体\'; font-size: 14px;font-weight: bold;">功能提示</td>';
						RemindersHtml += '</tr>';  
						RemindersHtml += '<tr name="trMenu" style="height:20px;background-color:#e1f2fa;">';
						RemindersHtml += ' <td  style="width:50%; color:#737070; font-family: \'楷体\'; font-size: 13px;font-weight: bold;">需续签合同功能</td>';
						RemindersHtml += ' <td class="biao_lianj_1"><span class="ss_zhm_lh_zt" id="levelt"><a target="_blank" href="menu!findOutDateMens.action" style="text-decoration: none;color:#737070;">'+jsonObj.record+'</a> </span></td>';
					//	RemindersHtml += ' <td colspan=3 style=" color:#333333; font-family: \'楷体\'; font-size: 14px;font-weight: bold;">'+ jsonObj.record + '</td>';
						RemindersHtml += '</tr>'; 
						RemindersHtml += '</table>';
						$(".contentssss").append(RemindersHtml);
				    }
				}
	     });
    setTimeout(getReminders,60*1000);		
}

$(function() {
	// /**updateRcountSuiYi();--任意提醒功能*/
	updateRcount();
	var menuTipStatus = $("#menuTipStatus").val();
	if(menuTipStatus == "on"){
		getReminders();
	}
	
	
	//大小
	$(".smallClose").click(function(){
		$(".smallImg").hide();
		$(".smImg").show();
	});
	$(".smClose").click(function(){
		$(".smallImg").show();
		$(".smImg").hide();
	});
	
<%--	$(".smallClose").toggle(function() {--%>
<%--		$(".smallImg").addClass("smalls");--%>
<%--		$(".contentssss").addClass("fontDiv");--%>
<%--	}, function() {--%>
<%--		$(".smallImg").removeClass("smalls");--%>
<%--		$(".contentssss").removeClass("fontDiv");--%>
<%--	});--%>
	//随下拉移动
	$('.smallImg').animate(
			{
				top : $(document).scrollTop() + $(window).height()
						- ($('.smallImg').height() )
			},
			{
				left : ($(document).scrollLeft() + $(window).width() - $(
						'.smallImg').width()) 
			}, 1);
	$('.smImg').animate(
			{
				top : $(document).scrollTop() + $(window).height()
						- ($('.smImg').height())
			},
			{
				left : ($(document).scrollLeft() + $(window).width() - $(
						'.smImg').width()) 
			}, 1);
	var loadAtOnePlace = function() {
		$(".smallImg").css(
				"left",
				($(document).scrollLeft() + $(window).width() - $('.smallImg')
						.width()) );
		$(".smallImg").css(
				"top",
				$(document).scrollTop() + $(window).height()
						- $('.smallImg').height() );
		$(".smImg").css(
				"left",
				($(document).scrollLeft() + $(window).width() - $('.smImg')
						.width()) );
		$(".smImg").css(
				"top",
				$(document).scrollTop() + $(window).height()
						- $('.smImg').height() );
	}
	loadAtOnePlace();
	$(window).resize(function() {
		$(".smallImg").css(
				"left",
				$(window).width() - $('.smallImg').width() );
		$(".smallImg").css(
				"top",
				$(window).height()- $('.smallImg').height()) ;
		$(".smImg").css(
				"left",
				 $(window).width() - $('.smImg').width());
		$(".smImg").css(
				"top",
			$(window).height()- $('.smImg').height() );
		}
	);
	$(window).scroll(function() {
		$(".smallImg").css(
				"left",
				$(window).width() - $('.smallImg').width() );
		$(".smallImg").css(
				"top",
				$(window).height()- $('.smallImg').height()) ;
		$(".smImg").css(
				"left",
				 $(window).width() - $('.smImg').width());
		$(".smImg").css(
				"top",
			$(window).height()- $('.smImg').height() );
	});

	//拖动
<%--	$('.smallImg').draggable();--%>
<%--	$('.smallClose').draggable().draggable();--%>
<%--	$('.smImg').draggable();--%>
<%--	$('.smClose').draggable().draggable();--%>
});
</script>