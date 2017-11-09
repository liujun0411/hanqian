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

		<title>集水井-实时监控</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/commonControl.js"></script>
				<script type="text/javascript" src="manager/javascript/previewPic/picShow.js"></script>
		<script type="text/javascript" src="manager/config/config.js"></script>
		<script type="text/javascript">
			
<%--		   $(function(){--%>
<%--		      $("body").mousemove(function(e){--%>
<%--		        var x=e.originalEvent.x || e.originalEvent.layerX || 0;  //鼠标点击的X坐标--%>
<%--    			 var y=e.originalEvent.y || e.originalEvent.layerY || 0;  //鼠标点击的Y坐标--%>
<%--    			 $("#btnX").val(x+","+y);;--%>
<%--		    });--%>
<%--		  });--%>

		    function myrefresh(){
				 //判断当前页面是否为监控界面，自动刷新
		        if($(window.parent.document).find("#control_Info").css('display')=="block"){
				      document.myform.submit();
				   }
			}
			setTimeout('myrefresh()',30000); //指定30秒刷新一次*/
			
			
//为集水井 设备加载div状态
 
	  
		</script>
	</head>

	<body id="mainDiv" style="overflow: scroll; overflow-x: hidden; overflow-y: hidden; ">
 <!-- textHtml 为重要配置不能删除 -->
 ${textHtml} 
			<div class="admin_nr_rightg" style="position:fixed;  " border="1px">
			<table width="500" border="0" align="left" style=" font-size: 13px; color: #404041;">
					<tr>
						<td>
							<table width="100%" style=" height:100px;">
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
									<td align="center" class="diant_ziti_140" >
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
			 <input type="hidden" id="controlPoint" name="controlPoint" value='${controlPoint }' />
			<form id="myform" name="myform" action="control_findToControl.action">
			    <input type="hidden" id="equipId" name="equipId" value="${equipId }" />
			    <input type="hidden" id="groupId" name="groupId" value="${groupId}" />
			    <input type="hidden" id="eqTypeId" name="eqTypeId" value="${eqTypeId }" />
			    <input type="hidden" id="alarmCount" name="alarmCount" value="${alarmCount }"/>
			    
			    <input type="hidden" id="showFlag" name="showFlag" value='${showFlag }' />
		    </form>
		    <input type="hidden" id="controlPoint" name="controlPoint" value='${controlPoint }' />
		<div id="admin_nr_rightg">
			    <div style=" height: 20px; width: 100%; border:0px solid red;position:fixed;margin-top: 105px; ">
				    <table style=" width: 100%;">
				        <tr>
				           <td style="padding-left: 30%;"><span class="groupNameStyle">【${groupName}】</span></td>
				        </tr>
	 			    </table>
				 </div>
			<!-- 图片区 -->
			<div style=" border:0px solid red;" class="shishijiankong_zxq">
			
				 <div class="flashOverFlow" style="margin-top: 150px; margin-left: 10px;">
					
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
						codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0"
						width="1000px" height="500px">
						<param name="movie" value="manager/images/control/flashNew/jsjgbB2.swf" /> 
						<param name="quality" value="high"/>
						<param name="wmode" value="transparent"/>
						<embed src="manager/images/control/flashNew/jsjgbB2.swf" wmode="transparent" quality="high"
							pluginspage=" http://www.macromedia.com/shockwave/download/index.cgi?P1_Pr od_Version=ShockwaveFlash"
							type="application/x-shockwave-flash" width="1000px" height="500px">
						</embed>
					</object>
				</div>
			</div>
			
<%--	    </div>--%>
<%--	        <div style=" width:400px; height: 30px; border: 1px solid red;">--%>
<%--	       <input type="text" id="btnX" name="btnY" />--%>
<%--	    </div>--%>
	     
	</body>
</html>