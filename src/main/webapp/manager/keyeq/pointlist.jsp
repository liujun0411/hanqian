<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台--点位选择</title>
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
<script type="text/javascript"  src="manager/js/jquery-1.3.2.js"></script>
		<style type="text/css">
td,span {
	font-size: 12px;
}
</style>
		<script language="JavaScript">
		var points=",";

		//选 择点 位
		selpoint=function(obj){
			if(obj.checked){
				if($("input[type='checkbox']:checked").length>3){
					obj.checked=false;
					alert("最多只能选择3个点位!");
					return false;			
				}else{
					points +=obj.value+",";
				}
			}else{
				points=points.replace(","+obj.value+",",",");
			}
		}
	
		onover=function(obj){
			if("#000000" !=obj.style.color ){
				obj.style.color="#FF6600";
			}
		}

		

		//checkbox选中事件
		onsella=function(obj,laid){
			selpoint(obj);
			if(obj.checked){
				document.getElementById(laid).style.color="#000000";
			}else{
				document.getElementById(laid).style.color="#3399FF";
			}
		}
		onout=function(obj){
			if("#000000" !=obj.style.color ){
				obj.style.color="#3399FF";
			}
		}

		//确定  返回
		function sendTo(){
		    
			 if(points.length>1){
			 	//window.returnValue = points.substring(1,points.length-1);
			 	  var parent= window.opener; 
			      parent.vodereturnValue(points.substring(1,points.length-1));
			 }else{
			  var parent= window.opener; 
			  parent.vodereturnValue("");
				// window.returnValue = "";
			 }
			
			 window.close();
		}		
	//-->
	</script>
	</head>
	<body onload="window.resizeTO(230,380)";>
		<div
			style="width: 98%; text-align: left; font: bold 14px Verdana; padding: 10px 0 4px 2px; font-size: 12px; font-weight: bold; border-bottom: 2px solid #aac9ea;">
			关键设备点位选择
		</div>
		<center>
			<br />
 			<div
				style="border: 1px solid #aac9ea; width: 90%; height: 120px; overflow: auto;"
				align="left">
				<!-- 点位选择 -->
				<c:if test="${pointList !=null}">

					<table>
						<c:forEach items="${pointList}" var="obj" varStatus="at">
							<tr>
								<td class="biao_lianj_1">
								
									<input type="checkbox"
										onclick="onsella(this,'la${at.index }');"
										value="${obj.keyPoint}" id="ch${at.index }" />
									<label for="ch${at.index }" id="la${at.index }"
										style="color: #3399FF; cursor: pointer;"
										onmouseover="onover(this);" onmouseout="onout(this);">
										${obj.name}
									</label>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${pointList ==null}">
					<span class="admin_clor_f00 ">该设备暂无通过专家论证监控的点位！</span>
				</c:if>
			</div>
		</center>
		<div
			style="width: 98%; font-weight: bold; border-bottom: 2px solid #aac9ea;">
			&nbsp;&nbsp;
		</div>
		<table style="width: 98%">
			<tr>
				<td align="right">
					<a href="javascript:void(0);" onclick="sendTo();" class="btn blue">确定</a>
				</td>
			</tr>
		</table>
	</body>
</html>