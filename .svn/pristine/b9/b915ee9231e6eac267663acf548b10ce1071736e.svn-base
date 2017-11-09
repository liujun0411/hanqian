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
		<script type="text/javascript" src="manager/js/commonControl.js">
</script>
		<script type="text/javascript" src="manager/js/swfobject.js">
</script>
		<script type="text/javascript" src="manager/config/config.js"></script>
        <script type="text/javascript" src="manager/javascript/previewPic/picShow.js"></script>
		<script type="text/javascript">
		function downloadPic(){
		       getPicNameByEquip($("#equipId").val());
		   }
		
		function changeBuild(obj) {
			$('#buildId').val($(obj).val());
			document.myform.submit();
		}
		
		function f_GOTOControl(equipId) {
			$('#equipId').val(equipId);
			if (jQuery.trim(equipId) != "") {
				document.myform.submit();
			}
		}
			//返回 状态读数
		var jsreturnval = "";
		function jsReturnVal() {
			jsreturnval = $("#ktjStatus").val();
			return jsreturnval;
		}
		function flashStatus() {
			jsreturnval = $("#ktjStatus").val();
			return jsreturnval;
		}
		//ajax 动态获取 读数
		var illumine = function() {
			var reVal;
			var jsonDoc = $.ajax( {
				type : "POST",
				url : "control_reFreshAjax.action",
				data : {
					equipId : $("#equipId").val(),
					groupId : $("#groupId").val(),
					groupCode:$("#groupCode").val(),
					eqTypeId : $("#eqTypeId").val()
				},
				cache : false,
				async : false,
				error : function(jsonObj) {
				},
				success : function(jsonObj) {
					reVal = jsonObj.toString();
					$("#ktjStatus").val(reVal);
				}
			});
			return reVal;
		}
			//调用swf中的Refresh方法
		function callExternalInterface() {
			            illumine();
			var currentHospCode = $("#currentHospCode").val();
			try{  
	             if(thisMovie("ExternalInterfaceExample")&&thisMovie("ExternalInterfaceExample")!=null){
    			 thisMovie("ExternalInterfaceExample").Refresh($("#ktjStatus").val());
	              }
		    }catch(e){ 
		    }finally{ 
				setTimeout(callExternalInterface, 30000); //指定30秒刷新一次*/
		    }
		}
		function thisMovie(movieName) {
			if (navigator.appName.indexOf("Microsoft") != -1) {
				return window[movieName];
			} else {
				return document[movieName];
			}
		}
		$(function(){
			var currentHospCode = $("#currentHospCode").val();
			var loadingFlag = $("#loadingFlag").val();
			var so=new SWFObject("manager/images/control/flashNew/loading.swf?"
			                     +"loadSwf=manager/images/control/flashNew/kongTiaoJi/"
			                     +currentHospCode
			                     +"/kongtiaoji.swf&loadingFlag="
			                     +loadingFlag+
			                     "&loading_x=250&loading_y=50&flash_x=-10&flash_y=0","ExternalInterfaceExample","1000","700","10","write");
		    so.addParam("swliveconnect","true");
		    so.addParam("wmode","transparent");
			so.write("flashktj");
			createHotDiv(0);
			callExternalInterface();
		});
		</script>
	</head>
	<body id="mainDiv" 
		style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<form id="myform" name="myform" action="control_findToControl.action">
			<input type="hidden" id="ktjStatus" name="ktjStatus" value='${ktjStatus}' />
				 <input type="hidden" id="currentHospCode" name="currentHospCode"
    				value="${sessionScope.currentHospCode}" />
			<input type="hidden" id="equipId" name="equipId" value="${equipId }" />
			<input type="hidden" id="eqTypeId" name="eqTypeId"
				value="${eqTypeId }" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId }" />
			<input type="hidden" id="controlPoint" name="controlPoint" value="" />
			<input type="hidden" id="alarmCount" name="alarmCount"
				value="${alarmCount }" />
			<input type="hidden" id="showFlag" name="showFlag"
				value='${showFlag }' />
				<input type="hidden" id="loadingFlag" name="loadingFlag"
				value="${sessionScope.loadingFlag}" />
					<input type="hidden" id="flashVersion" name="flashVersion"
				value="${sessionScope.flashVersion}" />
			<input type="hidden" id="buildId" name="buildId" value='${buildId }' />
		</form>
			<div id="admin_nr_rightg">
			<div id="header"
				style="float: left; height: 230px; border: 0px solid red; margin-top: 0px;">
				<div
					style="width: 600px; float: left; height: 200px; overflow: auto;">
					<table width="100%" style="BORDER-COLLAPSE: collapse"
						borderColor=#cccccc cellSpacing=0 align=center bgColor=white
						border=1>
						<tr>
							<td style="width: 70px">
								所在楼宇：
							</td>
							<td colspan="3">
								<select name="dbEquipList.dbBuilding.buildingId" id="buildingId"
									style="width: 150px;" onchange="changeBuild(this);">
									<c:forEach items="${listBuilding}" var="building">
										<option value="${building.buildingid}"
											${building.buildingid==buildId?'selected' : ''}>
											${building.buildingname}
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<c:if test="${!(empty floorList)}"></c:if>
						<c:forEach var="floor" items="${floorList}" varStatus="i" step="2">
							<tr bgcolor="#FFFFFF">
								<td style="width: 40px">
									<c:if test="${floor != 0}">
										<c:if test="${i.index == 0}">屋顶</c:if>
										<c:if test="${i.index > 0}">${fn:replace(floor, '-', 'B')} F</c:if>
									</c:if>
								</td>

								<td width="200" height="22">
									<c:forEach items="${listEquipList}" var="equip">
										<c:if test="${equip.storey==floor}">
											<img style="cursor: pointer;"
												src="manager/images/control/xinfengji.png" height="20"
												width="20" alt="${equip.equipname}"
												title="${equip.equipname}"
												onclick="f_GOTOControl('${equip.equipid}','${equip.equip_type_id}','${equip.building_id}')" />
										</c:if>
									</c:forEach>
								</td>

								<td style="width: 40px">
									<c:if test="${floor-1 != 0}">
										${fn:replace(floor-1, '-', 'B')} F
									</c:if>
								</td>
								<td width="200" height="22">
									<c:forEach items="${listEquipList}" var="equip">
										<c:if test="${equip.storey==floor-1}">
											<img style="cursor: pointer;"
												src="manager/images/control/xinfengji.png" height="20"
												width="20" alt="${equip.equipname}"
												title="${equip.equipname}"
												onclick="f_GOTOControl('${equip.equipid}','${equip.equip_type_id}','${equip.building_id}')" />
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="shishijiankong_kuan22"
					style="margin-left: 600px; margin-top: 0px; position: absolute;">
					<input name="" type="button" value="设备安装位置图" class="inp_shi_zt"
						style="margin-left: 150px;" onclick="downloadPic()" />
					<table width="350" border="0" align="left"
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
			</div>
			<!-- 图片区 -->
			<div style="border: 0px solid red;" class="shishijiankong_zxq">
				<div style="height: 20px; width: 100%; border: 0px solid red;">
					<table style="width: 100%;">
						<tr>
							<td align="center">
								<span class="groupNameStyle">【${equipName}】</span>
							</td>
						</tr>
					</table>
				</div>
				<div class="flashOverFlow" id="flashktj">
				</div>
			</div>
	</body>
</html>