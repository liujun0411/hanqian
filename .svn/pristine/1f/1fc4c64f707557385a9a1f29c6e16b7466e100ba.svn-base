<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
	<script type="text/javascript">
	   var initiVal=function(){
				try{
					$("#levelo").html("<font color='blue' id='alarmOne'>0</font>");
					$("#levelt").html("<font color='blue' id='alarmTwo'>0</font>");
					$("#levelth").html("<font color='blue' id='alarmThree'>0</font>");
					$("#marcount").html("<font color = 'blue'>0</font>");
				}catch(e){}
			}
		
			var updateRcount=function(obj){
				initiVal();
				var jsonDoc=$.ajax({
					  type: "POST",
					  url: obj,
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
				               countStrEnd ='<a  style="cursor:pointer" href="javascript:void(0);" onclick="submitHiddenForm(\'alarm_findCurrentAlarmInfo.action?eqcurrentPage=1&level=1&flag=true\');" >'+countStr+'</a>';
				               $("#levelo").html(countStrEnd);
				               if(levelTwo!=0){
				            	   countStr="<font color='red' id='alarmTwo'>"+levelTwo+"</font>";
							   }else{
								   countStr="<font color='blue' id='alarmTwo'>"+levelTwo+"</font>";
							   }
				               countStrEnd ='<a  style="cursor:pointer" href="javascript:void(0);" onclick="submitHiddenForm(\'alarm_findCurrentAlarmInfo.action?eqcurrentPage=1&level=2&flag=true\')" >'+countStr+'</a>';
					           $("#levelt").html(countStrEnd);
				               if(levelThree!=0){
				            	   countStr="<font color='red' id='alarmThree'>"+levelThree+"</font>";
							   }else{
								   countStr="<font color='blue' id='alarmThree'>"+levelThree+"</font>";
							   }
				               countStrEnd ='<a  style="cursor:pointer" href="javascript:void(0);" onclick="submitHiddenForm(\'alarm_findCurrentAlarmInfo.action?eqcurrentPage=1&level=3&flag=true\')" >'+countStr+'</a>';
					           $("#levelth").html(countStrEnd);

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
					           
					           //theAlert();  
					        }
									  
						
					        });  
					       
				setTimeout(updateRcount,30*1000);
			}
			
			function doOper(){
			  window.location.href="userLogin!IntoMainPage.action";
			}
			
		   $(function(){
		      $('.div_bottom img').mouseover(function(){
		         $('.ss_zhm_lh_zt a').text('');
		         if($(this).css('display')=="block"){
		            $('.tip').text($(this).attr('desc'));
		            if($.trim($(this).attr('link'))!=""){
		              updateRcount($(this).attr('link'));
		            }
		         }
		      });
		      if($.trim($('.div_bottom img').eq(0).attr('link'))!=""){
		         updateRcount($('.div_bottom img').eq(0).attr('link'));
		         $('.tip').text($('.div_bottom img').eq(0).attr('desc'));
		      }
		   });
		   
		   
	</script>
	<style type="text/css">
	   body{
		  margin-left: 0px;
		  margin-top: 0px;
		  margin-right: 0px;
		  margin-bottom: 0px;
		  margin:0 auto;
		  padding:0;
		 
		  overflow:hidden;
		  margin: auto 0;
	  }
	  .div_main_body{
		  margin: 0 auto;
		  border:1px solid white;
		  /*background:url(manager/images/common/bg.jpg) no-repeat;
		  width:1440px;
		  height: 1200px;
		  /*background-color: #F1F1F1;*/
	  }
	  .context{
	    margin: 0 auto;
	    width:1166px;
	    height:778px;
	    background: url(manager/images/change/bg.gif) no-repeat;
	  }
	  .divContains{
	    margin: 0 auto;
	    width:1000px;
	    height: 253px;
	    margin-top: 250px;
	    position: absolute;
	    margin-left: 85px;
	  }
	  .left{
	   width:301px;
	   height:263px;
	   position: relative;
	   float: left;
	  }
	  .left img{
	   margin-top:40px; 
	  }
	  .mains{
	    height:263px;
	    width:380px;
	    position: relative;
	    float: left;
	  }
	  .right{
	    width:315px;
	    height:263px;
	    position: relative;
	    float: right;
	  }
	  .right img{
	   margin-top:40px; 
	  }
	  .main_left{
	     background: url(manager/images/change/roller.png) no-repeat;
	     width:36px;
	     margin-left:-5px;
	     height:263px;
	     position: relative;
	     float: left;
	  }
	  .main_context{
	     margin-top:10px;
	     background: url(manager/images/change/alarm_bg.gif) repeat-x;
	     width:310px;
	     height: 250px;
	     position: relative;
	     float: left;
	  }
	  .main_right{
	     background: url(manager/images/change/roller.png) no-repeat;
	     width:36px;
	     height:263px;
	     position: relative;
	     float: right;
	     margin-right: -10px;
	  }
	  
	  .con_main{
	     background: url(manager/images/change/alarm_bg.gif) repeat-x;
	     position: absolute;
	     width:334.6px;
	     height: 230px;
	     margin-top: 267px;
	     margin-left: 411px;
	  }
	  .div_bottom{
	     position: absolute;
	     width:1000px;
	     height:50px;
	     margin-left: 90px;
	     margin-top: 550px;
	  }
	  .img_left{
	     position: absolute;
	     margin-left: 170px;
	     cursor: pointer;
	  }
	  .img_right{
	     position: absolute;
	     margin-left: 670px;
	     cursor: pointer;
	  }
	  .bg_lanmu_top{
	     width:100%;
	     height: 4px;
	     background: url(manager/images/change/line.gif) no-repeat;
	     margin-top: 20px;
	  }
	  .bg_lanmu_bottom{
	     width:100%;
	     height: 4px;
	     background: url(manager/images/change/line.gif) no-repeat;
	     margin-top: 10px;
	  }
	  .bg_title{
	    width:100%;
	    line-height: 40px;
	    text-align: center;
	  }
	  .bg_title span{
	     font-weight: bold;
	  }
	  .bg_title img{
	    margin-bottom: -10px;
	  }
	  .main_contexts_ALARM{
	     margin-top:12px;
	     height: 115px;
	  }
	  .tip{
	     color:blue;
	  }
	  .ss_zhm_lh_zt{
	    text-decoration: none;
	    font-size: 14px;
	  }
	  .ss_zhm_lh_zt a{
	   text-decoration: none;
	  }
	</style>
  </head>
  
  <body>
   <div class="div_main_body">
      <div class="context">
         <div class="divContains">
           <div class="left">
             <img src="manager/images/change/map.png" />
           </div>
           <div class="mains">
             <div class="main_left">
             </div>
             <div class="">
             </div>
             <div class="main_right">
             </div>
           </div>
           <div class="right">
              <img src="manager/images/change/map2.png" />
           </div>
         </div>
         <!-- 主体展示内容 -->
         <div class="con_main">
            <div class="bg_lanmu_top"></div>
            <div class="bg_title">
               <img src="manager/images/change/dot.gif"/>
               <span class="tip"></span><span>当前告警汇总</span>
            </div>
            <div class="main_contexts_ALARM">
            <table width="100%" border="0" cellspacing="1" bgcolor="#999999"  class="shishijiankong_guolu_zt13"  style="text-align: center; border: none;">
			  <tr bgcolor="#DCDDDD" style=" background-color: #DCDDDD; font-size: 15px; font-family: '黑体'; color:#00487A;"  valign="bottom">
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
			  </table>
            </div>
            <div class="bg_lanmu_bottom"></div>
         </div>
         <div class="div_bottom">
            <img src="manager/images/change/button_east.png"  class="img_left" onclick="doOper()" desc="东院" link="alarm_findCurrentAlarmCount.action"/>
            <img src="manager/images/change/button_north.png" class="img_right" desc="北院" link=""/>
         </div>
      </div>
   </div>
  </body>
</html>
