<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hanqian.util.Systime"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>医院后勤智能化管理平台</title>
		<link href="manager/css/indexTop.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
		<script type="text/javascript">
		//加载天气预报
		function loadWeather(){
			$.ajax( {
				type : "POST",
				url : "weatherUtil_findWeatherInfo.action",
				cache : false,
				async : false,
				dataType : "json",
				error : function(jsonObj) {
				},
				success : function(jsonObj) {
				 if(jsonObj!=null&&jsonObj!=""){
					 var myDate = new Date();
<%--					 var dt = jsonObj.weatherRtDt;--%>
<%--				     var dependedDate=new Date();--%>
<%--					 var regEx = new RegExp("\\-","gi");--%>
<%--					 dt=dt.replace(regEx,"/");--%>
<%--					 dependedDate.setTime(Date.parse(dt));--%>
<%--					 var difDays = ((myDate.getDate()-dependedDate.getDate())/1000/60/60/24).toFixed(0);--%>
					var difDays =  jsonObj.diffDate;
					if(difDays<1){
						$("#span_cityName").html(jsonObj.city);
						
			            
						 if(jsonObj.temp != null ){

								$("#span_wther").html(jsonObj.temp+"℃");
							}
						
						
						
						if(jsonObj.alarm != null ){
							

							$("#span_alarm").css('color','#FF9900');

							
							$("#span_alarm").html(jsonObj.alarm);
						
						
						}
						
						
						
					
						var imgStr = new Array();
						//var ptime = jsonObj.weatherPtime;
						
						var hour = myDate.getHours();
						var minutes = myDate.getMinutes();
						var img1 = jsonObj.img1;
						var img2 = jsonObj.img2;
						if(img1==null||img1==""){
							 $("#span_img").html("");
							 $("#span_cityName").html(jsonObj.city);
						     
							 if(jsonObj.temp != null ){

									$("#span_wther").html(jsonObj.temp+"℃");
								}
							if(jsonObj.alarm != null ){
								
								$("#span_alarm").html(jsonObj.alarm);
							}
						}else{
						<%--var realDimg = img1;
						var realNimg = img2;
						if(img1.search("d")>-1){//白天
							realDimg = img1;
							realNimg = img2;
						}else{
							realDimg = img2;
							realNimg = img1;
						}
						if(hour<18&&hour>6){
							imgStr.push("<img src='manager/images/tqimg/"+realDimg+"' >");//白天
						}else if(hour==18||hour==6){
							if(hour==6&&minutes>0){
								imgStr.push("<img src='manager/images/tqimg/"+realDimg+"' >");//白天
							}else if(hour==18&&minutes>0){
								imgStr.push("<img src='manager/images/tqimg/"+realNimg+"' >");//夜间
							}
						}else{
							imgStr.push("<img src='manager/images/tqimg/"+realNimg+"' >");//夜间
						}
						--%>
	<%--					imgStr.push("<img src='manager/images/tqimg/"+jsonObj.img1+"' >");--%>
	<%--					imgStr.push("<img src='manager/images/tqimg/"+jsonObj.img2+"' >");--%>
						 <%-- imgStr.push(jsonObj.temp+"℃");
					
	
						  var divContent = $("#divWeatherMore>#content",parent.document.body);
	
						  var contentStr = new Array();
	
							contentStr.push('<div id="cj" style="font-family:Verdana,Arial,Helvetica,sans-serif;font-weight:normal;line-height:180%;list-style:none outside none;padding:0">');
							contentStr.push(' <div id="left" style="padding-top:5px;">');
							contentStr.push(' <h3 class="h3"><span id="city" style="font-weight:bold;">'+jsonObj.city+'</span></h3>');
							if(hour<18&&hour>6){
								contentStr.push(' <img alt="" src="manager/images/tqimg/big/'+realDimg+'" id="big1">');
								contentStr.push('<div style="position:absolute;top:32px; left:61px;"><div class="divCenter"></div><div class="divMiddle"></div><div class="divLeft"></div></div>');
								contentStr.push('<img alt="" src="manager/images/tqimg/big/'+realNimg+'" id="big2" style="margin-left:50px;">');
								contentStr.push(' <div><span style="margin-left:10px;">日间</span><span span style="margin-left:66px;">夜间</span></div>');
							}else if(hour==18||hour==6){
								if(hour==6&&minutes>0){
									contentStr.push(' <img alt="" src="manager/images/tqimg/big/'+realDimg+'" id="big1">');
									contentStr.push('<div style="position:absolute;top:32px; left:61px;"><div class="divCenter"></div><div class="divMiddle"></div><div class="divLeft"></div></div>');
									contentStr.push('<img alt="" src="manager/images/tqimg/big/'+realNimg+'" id="big2" style="margin-left:50px;">');
									contentStr.push(' <div><span style="margin-left:10px;">日间</span><span span style="margin-left:66px;">夜间</span></div>');
								}else if(hour==18&&minutes>0){
									contentStr.push(' <img alt="" src="manager/images/tqimg/big/'+realNimg+'" id="big1">');
									contentStr.push('<div style="position:absolute;top:32px; left:61px;"><div class="divCenter"></div><div class="divMiddle"></div><div class="divLeft"></div></div>');
									contentStr.push('<img alt="" src="manager/images/tqimg/big/'+realDimg+'" id="big2" style="margin-left:50px;">');
									contentStr.push(' <div><span style="margin-left:10px;">夜间</span><span span style="margin-left:66px;">日间</span></div>');
								}
							}else{
								contentStr.push(' <img alt="" src="manager/images/tqimg/big/'+realNimg+'" id="big1">');
								contentStr.push('<div style="position:absolute;top:32px; left:61px;"><div class="divCenter"></div><div class="divMiddle"></div><div class="divLeft"></div></div>');
								contentStr.push('<img alt="" src="manager/images/tqimg/big/'+realDimg+'" id="big2" style="margin-left:50px;">');
								contentStr.push(' <div><span style="margin-left:10px;">夜间</span><span span style="margin-left:66px;">日间</span></div>');
							}
							contentStr.push('  <h4 class="h4"><strong id="weather1">'+jsonObj.temp1+'℃~'+jsonObj.temp2+'℃'+'</strong></h4>');
							contentStr.push('</div>');
							contentStr.push('<div id="right" style="text-align:left;">');
							contentStr.push('  <h4><span id="temp1">相对湿度   '+jsonObj.SD+'</span> </h4>');
							contentStr.push('  <h4><span id="wd1">'+jsonObj.WD+jsonObj.WS+'</span> </h4>');
							contentStr.push('  <h4> <em id="index_uv"></em> </h4>');
							contentStr.push('  <h4 class="more"> </h4>');
							
							//var dt = jsonObj.weatherRtDT;
								
							contentStr.push('<div class="sourceDate">今天'+jsonObj.weatherRtPtime+'发布</div>');
							contentStr.push(' </div>');
							contentStr.push('</div>');
						 	 divContent.html( contentStr.join(""));
							$("#span_img").html(imgStr.join(""));
							 $('#spanWeather').mouseover(function(){
							      $("#divWeatherMore",parent.document.body).show();
							   }).mouseout(function(){
							      $("#divWeatherMore",parent.document.body).hide();
							   });
							 --%>
						}
					 }else{
						 $("#span_img").html("");
						 $("#span_cityName").html(jsonObj.city);
					     
						 if(jsonObj.temp != null ){

								$("#span_wther").html(jsonObj.temp+"℃");
							}
						 
						if(jsonObj.alarm != null ){
							$("#span_alarm").html(jsonObj.alarm);
						}
					 }
				 }
				}
			});
			setTimeout(loadWeather,60*60*1000);
<%--				setTimeout(loadWeather,10*1000);--%>
		}
		
		function loadScreen(){
		    var screenWidth=screen.width;
		    if(screenWidth<=1366){
		       $('#top_td').removeClass('toptd_1440').addClass('toptd_1366');
		    }
		    if(screenWidth>1400){
		      $('#top_td').removeClass('toptd_1366').addClass('toptd_1440');
		    }   
		 }
		 $(function(){
		    loadScreen();
		    var weatherShowFlag = $("#weatherShowFlag").val();
		    if(weatherShowFlag=="on"){
			 loadWeather();
		   
		    }
		 });
		 
		 function to(obj,userid){
			$('#middle').contents().find('#workflow').attr({src:'user!toUpdateUser.action?dbUsers.seq='+userid});
		 }
			
			var toPassword=function(obj,userid,userName){
				var user = new Array();
				user[0] = userid;
				user[1] = userName;
				 window.open("manager/common/updatePassword.jsp?useridNusem="+userid+"|"+userName,user,"height=413, width=435, toolbar =no, menubar=no, scrollbars=no, left=818,top=115,resizable=no, location=no, status=no, z-look=yes");
				/* var obj=showModalDialog('manager/common/updatePassword.jsp',userid+"|"+userName,"dialogHeight:400px;dialogWidth:500px;location:no;center:yes;scroll:no;help:no;status:no;");
					*/
			}
			function windowOpen(){
				//if(obj=="y"){
				//alert("密码修改成功，请重新登录！");
			    window.top.location.href="user!closeUsers.action?dbLogin.seq="+$("#userid").val();
			//}
			}
			//仁济医院
			$(function(){
			   $('#doChange').mouseover(function(){
			      $(this).addClass('change_hover');
			      //$(window.parent.document).find('#div_change').show();
			      $('ul.items').slideDown(200);
			   }).mouseout(function(){
			      //$('ul.items').hide();
			   });
			   //加入了医院简编的判断，当是医院是仁济（东南西北）时，则可做选择。其他则直接登录
				$.ajax( {
					type : "POST",
					url : "hospInfoAction!judgeCurrentHosp.action",
					cache : false,
					async : false,
					dataType : "json",
					error : function(jsonObj) {
					},
					success : function(jsonObj) {
						var currentHospCode = jsonObj.currentHospCode;
						if(currentHospCode!='RJ'&&currentHospCode!='RJB'&&currentHospCode!='RJX'&&currentHospCode!='RJN'){
							$("#div_rj").hide();
						}else{
							$("#doChange").html($("#li_"+currentHospCode+" a").html());
							$("#li_"+currentHospCode).hide();
							$("#div_rj").show();
						}
					}
				});
			});
			//曙光医院
			$(function(){
				   $('#doChange_sg').mouseover(function(){
				      $(this).addClass('change_hover');
				      //$(window.parent.document).find('#div_change').show();
				      $('ul.items').slideDown(200);
				   }).mouseout(function(){
				      //$('ul.items').hide();
				   });
				   //加入了医院简编的判断，当是医院是曙光（东南西北）时，则可做选择。其他则直接登录
					$.ajax( {
						type : "POST",
						url : "hospInfoAction!judgeCurrentHosp.action",
						cache : false,
						async : false,
						dataType : "json",
						error : function(jsonObj) {
						},
						success : function(jsonObj) {
							var currentHospCode = jsonObj.currentHospCode;
							if(currentHospCode!='SG'&&currentHospCode!='SGB'&&currentHospCode!='SGX'&&currentHospCode!='SGN'){
								$("#div_sg").hide();
							}else{
								$("#doChange_sg").html($("#li_"+currentHospCode+" a").html());
								$("#li_"+currentHospCode).hide();
								$("#div_sg").show();
							}
						}
					});
				});
			$(function(){
				$('ul.select_change li').eq(0).hover(
					function(){
						$(this).find('ul').show();
						$(this).find('a').eq(0).addClass('change_hover');
						//$(this).find('span').addClass('active');
					},
					function(){
						$(this).find('ul').hide();
						$(this).find('a').eq(0).removeClass('change_hover');
						//$(this).find('span').removeClass('active');
					}
				);
			});
			function changeHosp(userid,userpwd,path){  
				var name = 'otherHosp';
			     var tempForm = document.createElement("form");  
			     tempForm.id="tempForm1";  
			     tempForm.method="post";  
			     tempForm.action=path;  
			     tempForm.target=name;  

			     var toSubInput = document.createElement("input");  
			     toSubInput.type="hidden";  
			     toSubInput.name= "toSub";
			     toSubInput.value= 1;
			     var userIdInput = document.createElement("input");  
			     userIdInput.type="hidden";  
			     userIdInput.name= "uid";
			     userIdInput.value= userid;
			     var userPwdInput = document.createElement("input");  
			     userPwdInput.type="hidden";  
			     userPwdInput.name= "pwd";
			     userPwdInput.value= userpwd;
			     tempForm.appendChild(toSubInput); 
			     tempForm.appendChild(userIdInput);
			     tempForm.appendChild(userPwdInput);
			     if(window.addEventListener){ // Mozilla, Netscape, Firefox
			    	 tempForm.addEventListener("onsubmit",function(){ openWindow(name); });
			     } else { // IE
			    	 tempForm.attachEvent("onsubmit",function(){ openWindow(name); });
			     }
			     document.body.appendChild(tempForm);  
			    
			     tempForm.submit();
			     document.body.removeChild(tempForm);
			}

			function openWindow(name) {  
			     window.open('about:blank',name,'status=yes,toolbar=yes,fullscreen=1,menubar=yes,location=yes');   
			}  

		</script>
		<style type="text/css">
				#cj h4 {
			    font-size: 12px;
			    margin-bottom: 5px;
			}
			ul{list-style-type:none;}
			ul li{width:140px; height:20px; }
			.change_select{
		      color:white;
			  font-size:14px;
			  font-family:'黑体';
			  margin-right:20px;
			  text-decoration:none;
			  padding: 2px 4px;
			  line-height: 12px;
		   }
		   .change_hover{
		      cursor:pointer;
		      line-height: 12px;
    		  padding: 2px 3px;
		      border-color: transparent;
   			  border-radius: 3px 3px 0 0;
              border-style: solid;
              border-width: 1px 1px 0;
		      color:black;
			  font-size:14px;
			  font-family:'黑体';
			  margin-right:20px;
			  background: none repeat scroll 0 0 #FFFFFF;
    		  border-color: #98B2C9 #98B2C9 #FFFFFF;
              box-shadow: 0 0 4px rgba(0, 0, 0, 0.4);
              border-bottom: none;
		   }
			ul.select_change{
				padding-top:1px;
				padding-left:10px;
				margin: 0 auto;
				list-style:none;
				width:100px;
			    font-size:14px;
			    font-family:"黑体";
			    text-align: left;
			    color:white;
			}
			ul.select_change > li{
			   float:left;
			}
			ul.items{
			  display: none;
			  padding-top: 1.49px;
			  margin-left: 64.5px;
			  float:left;
			  border-color: transparent;
			  box-shadow: 0 0 4px rgba(0, 0, 0, 0.4);
			  border-color: #98B2C9 #98B2C9 #FFFFFF #98B2C9;
			  
			  border-radius: 3px 3px 3px 3px;
			  border-style: solid;
			  border-width: 0px 1px 1px 1px;
			  border-top: none;
			}
			
			ul.items > li {
			  height: 25px;
			  margin-left: -42px;
			  float:left;
			}
			
			ul.items > li > a{
			  font-size:14px;
			  font-family:"黑体";
			  color: green;
			  text-decoration: none;
			  display: block;
			  line-height: 25px;
			  padding-left: 8px;
			}
			 .b1{width:207px;border-left:1px solid #8dd126;border-right:2px solid #8dd126;height:1px;overflow:hidden;display:block;}
			ul.items > li > a:hover{
			  font-size:14px;
			  font-family:"黑体";
			  color: green;
			  padding-left: 10px;
			  text-decoration:underline;
			  background-color: #EBF4FB;
			}
			
			ul.select_change ul > li{
			    list-style:none;
			    font-size:14px;
			    font-family:"黑体";
			    background-color: white;
			    padding-left: 0px;
			}
		
		    
		
		</style>
	</head>

	<body>
		<div id="body_warpper">
		    <input type="hidden" id="menuOffset" name="menuOffset" value="${sessionScope.menuOffset}" />
		    <input type="hidden" id="menuTip" name="menuTip" value="${sessionScope.menuTip}" />
		     <input type="hidden" id="weatherShowFlag" name="weatherShowFlag" value="${sessionScope.weatherShowFlag}" />
		    <input type="hidden" id="reloadScreen" name="reloadScreen" onclick="loadScreen()" />
			<!--头部LOGo-->
			<div id="top_index" class="index_title" style=" width:1425px;">
				<table width="1370">
					<tr>
						<td valign="top" class="td_logo" align="right">
							<img src="manager/images/common/hospLogo/${sessionScope.currentHospCode }_logo.png" class="hosp_logo"
								alt="" />
						</td>
						<td class="td_title">
							<img src="manager/images/common/index_top_title.png" alt="" />
						</td>
						<td align="left" valign="top" width="700">
							 <div style="display:inline; float: left; position: relative;" id="div_rj">
								<ul type="disc" class="select_change">
								   <li>仁济医院-<a id="doChange" class="change_select">东院</a>
								       <ul class="items">
								       	  <li id="li_RJ"><a href="javascript:void(0)" onclick="changeHosp('${sessionScope.users.userid}','${sessionScope.users.password}','http://192.168.0.15:8099/logisticsCSS/userLogin.action');">东院</a></li>
									      <li id="li_RJB"><a href="javascript:void(0)" onclick="changeHosp('${sessionScope.users.userid}','${sessionScope.users.password}','http://renjibei.vicp.cc:8080/logisticsCSS/userLogin.action');">北院</a></li>
									    </ul>
								   </li>
								</ul>
						     </div>
						     	<div style="display:inline; float: left; position: relative;" id="div_sg">
								<ul type="disc" class="select_change">
								   <li>曙光医院-<a id="doChange_sg" class="change_select">总部</a>
								       <ul class="items">
								       	  <li id="li_SG"><a href="javascript:void(0)" onclick="changeHosp('${sessionScope.users.userid}','${sessionScope.users.password}','http://192.168.3.121:8080/logistics/userLogin.action');">总部</a></li>
									      <li id="li_SGX"><a href="javascript:void(0)" onclick="changeHosp('${sessionScope.users.userid}','${sessionScope.users.password}','http://192.168.11.121:8080/logistics/userLogin.action');">西部</a></li>
									    </ul>
								   </li>
								</ul>
						     </div>
						    <div style=" width:470px; position: relative; float: left; margin-left: 30px;">
								<span class="top_desc">总访问量：${sessionScope.dbLogin.systemCount }</span>
								<span class="top_desc" style="margin-right: 0px;">用户：
								</span>
								<a href="javascript:void(0);" style="text-decoration:none;cursor: pointer;"  class="top_desc"
									onclick="toPassword(this,'${sessionScope.users.seq}','${sessionScope.users.username}');">
									${sessionScope.users.username }
								</a>
								<a href="javascript:void(0)" onclick="if(window.confirm('您确定注销登录吗？')){
									window.top.location.href=
	    								'user!closeUsers.action?dbLogin.seq=${sessionScope.dbLogin.seq}';}" class="aHref">注销</a>
								<a href="manager/common/hosp_main.jsp" target="_parent" class="aHref">首页</a>
							</div>
						</td>
					</tr>
				</table>
				
			</div>
			<!--一级菜单-->
			<div id="top_menus" class="top_menus">
				<table cellpadding="0" cellspacing="0" width="100%" border="0"
					style="height: 40px;">
					<tr align="center">
						<td id="top_td">

						</td>
						<c:forEach items="${topMenus}" var="m"  varStatus="idx">
						<td class="menus_td">
							<a id="ahref_${m.menu_code }" onclick="showSec('4','${m.menu_url}',this);" links="${m.menu_url}" eqTypeId="" class="${idx.index==0?'selected':''}" dueDate="${m.due_date}" >
							${m.name} ${m.dueDate}
							</a>
						</td>
						</c:forEach>
					
							
						
						<td class="last_td" align="right">
							<span id="spanWeather" >
							    <span id="span_cityName"></span>
							    <span id="span_wther"></span>
							    
							    
							     <span id="span_alarm"  style="border:1px;background:#0066FF"></span>
								
								
								<span id="span_img"></span>
							</span>
							<img src="manager/images/common/32.png" alt="" />
							<span><%=Systime.thisSysDate()%></span>
						</td>
					</tr>
				</table>
			</div>
		</div>
		 <script type="text/javascript">
		    var offset = $('#menuOffset').val();
		    var menuTip= $('#menuTip').val();
		    $(function(){
		       if(menuTip=="on"){
			       $('#top_menus a').each(function(){
			           if(!judgeDateStr($(this).attr('dueDate'),offset)){
			               $(this).parent().remove();
			           }
			       });
		       }
		    });
 		  
		    
		    
		    function judgeDateStr(menuDate,offset){
			     menuDate = menuDate.toString();
			     if (menuDate.indexOf(" ")>0) {
			     	menuDate = menuDate.substring(0,menuDate.indexOf(" "));
			     }
			     var now = new Date();
			     var newDate= new Date(Date.parse(menuDate.replace(/-/g,"/"))); 
			     var offsetDate = new Date();
			     offsetDate.setDate(newDate.getDate() + parseInt(offset));
			     //菜单未过期
				 if(offsetDate.getTime()>=now.getTime()){
				     return true;
				 }else{  //菜单已过期
				     return false;
				 }
			}
		 
			onload=function(){
			   var first=$('#top_menus .menus_td').eq(0).find('a');
			   var menuDate = first.attr('dueDate');
			   if(menuTip=="on"){
				   if($.trim(menuDate)!=""){
					  showSec("4",$(first).attr('links'),first);	
				   }else{
					   showSec("4",$(first).attr('links'),first);
				   }
			   }else{
			       showSec("4",$(first).attr('links'),first);
			   }
			}
			
			$(function(){
			   $('.menus_td a').mouseover(function(){
				 $(this).addClass('ahover');
			   }).mouseout(function(){
			     $(this).removeClass('ahover');
			   });
			});
		
			//加载正文内容
			showSec = function(parentId, menuUrl,obj) {
		        var menuDate = $(obj).attr('dueDate');
		        if(menuTip=="on"){
			        if($.trim(menuDate)!=""){
				       if(judgeDateStr(menuDate,offset)){
						    //移除所有选中状态
					        $('.menus_td a').removeClass('selected');
					        //给当前添加选中样式
					        $(obj).addClass("selected");
					        
							//加亮选中项	
							if ($(".fenzhan_daohan_lei_2") != null) {
								$(".fenzhan_daohan_lei_2").attr("class", "fenzhan_daohan_lei_1");
							}
							$("#first" + parentId).attr("class", "fenzhan_daohan_lei_2");
					       if (menuUrl.length > 0) {
								var eqTypeId=$("#myOper",parent.document.body).attr('eqTypeId');
								if($.trim(eqTypeId)!="" && $.trim($(obj).attr('id'))=="ahref_4" ){
								   menuUrl+="&eqTypeId="+eqTypeId;
								}else{
								   $("#myOper",parent.document.body).attr('eqTypeId',"");
								}
								$("#myOper",parent.document.body).attr({'eqTypeId':''});
								window.parent.$("#middle").attr("src", menuUrl);
							} else {
								//jsp 传参方式    获取数据 方法  < %=request.getParament("") % >
								window.parent.$("#middle").attr(
										"src",
										"hosp_content.jsp?parentId=" + parentId + "&menuUrl="
												+ menuUrl);
							}	
					   }else{
					       alert("【"+$.trim($(obj).text())+"】-功能过期,无法使用!");
					   }	
				    }else{
					    //移除所有选中状态
				        $('.menus_td a').removeClass('selected');
				        //给当前添加选中样式
				        $(obj).addClass("selected");
				        
						//加亮选中项	
						if ($(".fenzhan_daohan_lei_2") != null) {
							$(".fenzhan_daohan_lei_2").attr("class", "fenzhan_daohan_lei_1");
						}
						$("#first" + parentId).attr("class", "fenzhan_daohan_lei_2");
					   if (menuUrl.length > 0) {
						    var eqTypeId=$("#myOper",parent.document.body).attr('eqTypeId');
						    if($.trim(eqTypeId)!="" && $.trim($(obj).attr('id'))=="ahref_4" ){
						       menuUrl+="&eqTypeId="+eqTypeId;
							}else{
							   $("#myOper",parent.document.body).attr('eqTypeId',"");
							}
							$("#myOper",parent.document.body).attr({'eqTypeId':''});
							window.parent.$("#middle").attr("src", menuUrl);
					   } else {
							//jsp 传参方式    获取数据 方法  < %=request.getParament("") % >
							window.parent.$("#middle").attr(
									"src",
									"hosp_content.jsp?parentId=" + parentId + "&menuUrl="
											+ menuUrl);
						}
				    }
				}else{
			        //移除所有选中状态
			        $('.menus_td a').removeClass('selected');
			        //给当前添加选中样式
			        $(obj).addClass("selected");
			        
					//加亮选中项	
					if ($(".fenzhan_daohan_lei_2") != null) {
						$(".fenzhan_daohan_lei_2").attr("class", "fenzhan_daohan_lei_1");
					}
					$("#first" + parentId).attr("class", "fenzhan_daohan_lei_2");
				   if (menuUrl.length > 0) {
					    var eqTypeId=$("#myOper",parent.document.body).attr('eqTypeId');
					    if($.trim(eqTypeId)!="" && $.trim($(obj).attr('id'))=="ahref_4" ){
					       menuUrl+="&eqTypeId="+eqTypeId;
						}else{
						   $("#myOper",parent.document.body).attr('eqTypeId',"");
						}
						$("#myOper",parent.document.body).attr({'eqTypeId':''});
						window.parent.$("#middle").attr("src", menuUrl);
				   } else {
						//jsp 传参方式    获取数据 方法  < %=request.getParament("") % >
						window.parent.$("#middle").attr(
								"src",
								"hosp_content.jsp?parentId=" + parentId + "&menuUrl="
										+ menuUrl);
					}
				}
				//界面跳转 "showFrame_showMenus.action?parentId="+parentId+"&level=hospsecond"
				
				
		
				//隐藏告警提示（jkj）
				$(window.parent.document).find("#control_warnDiv").hide();
				$(window.parent.document).find("#warn_sum").text('');
			}
		</script>
	</body>
</html>