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

		<title>My JSP 'show.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<!-- 设置自动刷新时间为30s 
		<meta http-equiv="Refresh" content="30"/>-->
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		
		
		</style>
		<script type="text/javascript">
		   function divIN(obj){
				$(window.parent.document).find('#zhaq').css({"left":$(obj).attr('marleft')});
				$(window.parent.document).find('#zhaq').css({"top":$(obj).attr('martop')});
				$(window.parent.document).find('#ptitleDiv').html($(obj).attr('tishi'));
				$(window.parent.document).find('#zhaq').show();
			}
			
			function divOut(obj){
				$(window.parent.document).find('#zhaq').hide();
			}
		</script>
		
	</head>

	<body id="mainDiv" style="overflow: scroll; overflow-x: hidden; overflow-y: hidden; background-color: white; ">
		<div id="admin_nr_rightg" onmouseover="divIN(this)" onmouseout="divOut(this)" style=" border:0px solid red;" overflow:hidden; tishi="市级医疗机构建筑综合能耗节能指数划分为三个层次：
<p>1.在合理值范围之内，符合标准，展现形式为：绿色%；</p>
<p>2.大于合理值且小于等于合理值乘以基准参数，污染，展现形式为：橙色%；</p>
<p>3.大于合理值乘以基准参数，严重污染，展现形式为：红色%。</p>
计算公式：
<p>f(%)=(e-Rv)/Rv*100</P>
公式中：
<P>f:市级医疗机构单位建筑面积综合能耗节能指数值，单位为%;</P>
<p>e:市级医疗机构单位面积综合能耗，单位为实物量每平米每年;</p>
<p>Rv:合理值，引用上海市卫生局文件印发《市级医疗机构合理用能指南》沪卫规财[2012]30号 （DB31 / 553-2012）;</P>
<p>基准参数，参考“十二五”规划及《企业节能标准体系编制通则》（GB/T 22336-2008）</P>" martop="180px" marleft="180px">
		   <table width="100%">
				<tr>
					<td rowspan="3">
					<img src="manager/images/common/<c:if test="${empty resultVoElect.resultEnergy }">gloden-1.png</c:if><c:if test="${! empty resultVoElect }"><c:if test='${resultVoElect.resultEnergy=="A"}'>gloden-1.png</c:if><c:if test='${resultVoElect.resultEnergy=="B"}'>gloden-2.png</c:if><c:if test='${resultVoElect.resultEnergy=="C"}'>gloden-3.png</c:if></c:if>" style="width: 170px; margin-left: 10px; margin-top: 10px; margin-bottom: 10px; height: 170px;" />
					</td>
				</tr>
				<tr>
				<td valign="top" style="padding-top: 30px; padding-right: 5px;"
						align="right">
						<div style="position: absolute; width: 15px; height: 15px; <c:if test="${! empty resultVoElect}"> <c:if test="${resultVoElect.resultEnergy=='A'}">background-color:#009900;</c:if><c:if test="${resultVoElect.resultEnergy=='B'}">background-color:#ffcc00;'></c:if><c:if test="${resultVoElect.resultEnergy=='C'}">background-color: #cc0000;</c:if></c:if>"></div>
						<span
							style="color: #404041;  font-family: '黑体'; margin-left:20px; font-size: 13px; font-weight: bold; margin-right: 50px;">能耗指数：</span>
						<br/>
						<br/>
						<span
							style="font-size: 20px;font-weight: bold; margin-right: 10px; <c:if test="${! empty resultVoElect}"> <c:if test="${resultVoElect.resultEnergy=='A'}">color:green;</c:if><c:if test="${resultVoElect.resultEnergy=='B'}">color: #ffcc00;</c:if><c:if test="${resultVoElect.resultEnergy=='C'}">color: red;</c:if></c:if> font-family: Arial, Helvetica, sans-serif;  font-style: 宋体 ;"><c:if test="${! empty resultVoElect }">${resultVoElect.resultSaveVal}% </c:if><span style="font-size: 14px;"><span style=" color: black;font-size: 12px;">(</span><c:if test="${resultVoElect.resultEnergy=='A'}">节能</c:if><c:if test="${resultVoElect.resultEnergy=='B'}">耗能</c:if><c:if test="${resultVoElect.resultEnergy=='C'}">污染</c:if><span style=" color: black; font-size: 12px;">)</span></span></span>
				</td>
				</tr>
			</table>
	    </div>
	    
	</body>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#div_cksm",window.parent.document).hide();
		$("#cksm").mouseover(function(){
			$("#div_cksm",window.parent.document).show();
		})
		$("#cksm").mouseout(function(){
			$("#div_cksm",window.parent.document).hide();
		})
		});
		</script>
</html>