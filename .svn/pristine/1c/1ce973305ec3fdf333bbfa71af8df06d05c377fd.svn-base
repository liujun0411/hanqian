<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<title>医院后勤智能化管理平台</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" href="manager/css/neirong.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/skdiv.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/ziti.css" type="text/css"></link>
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
        <script type="text/javascript" src="manager/js/tableTr.js"></script>
		<script type="text/javascript">
		    //分页控件
			function formCheck(currentPage) {
				if (currentPage != ""){
					$("#curpage").val(currentPage);
				}
				document.myform.submit();
			}
			
			function gotoControl(eqTypeId,equipId){
			  if(eqTypeId!="" && equipId!=""){
			     $("#ammeter_page").attr({src:"control_findToControl.action?equipId="+equipId+"&eqTypeId="+eqTypeId});
			     if($('#ammeter_control').css('display')=="none"){
			        $('#ammeter_control').slideToggle(800); 
			     }
			     
			  }
			}
			
			function doHideControl(){
			  $('#ammeter_control').slideToggle(800); 
			}
	   </script>
	</head>

	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
	    <form id="myform" name="myform" action="control_showChildEquipList.action">
	        <input type="hidden" id="curpage" name="curpage" value="${curpage}" />
	        <input type="hidden" id="seq" name="seq" value="${seq}" />
	        <input type="hidden" id="equipId" name="equipId" value="${equipId}" />
	        <input type="hidden" id="eqTypeId" name="eqTypeId" value="${eqTypeId}" />
	    </form>
		 <div id="admin_nr_rightgq">
		 <div class="itemList">
			<table width="100%" cellspacing="1"
					style="font-family: '华文细黑'; font-size: 13px; 
				 color: #404041; background-color: #999999; margin-top: 10px;">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<td align="center">
							序号
						</td>
						<td align="center">
							楼宇
						</td>
						<td align="center">
							设备名称
						</td>
						<td align="center">
							设备型号
						</td>
						<td align="center">
							设备类型
						</td>
						<td align="center">
							安装位置
						</td>
						<td align="center">
							所在楼层
						</td>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<c:forEach items="${childEquipList}" var="equip" varStatus="idx">
						<tr class="${idx.index%2==0?'tr1':'tr2'}">
							<td align="center">
								${idx.index+1 }
							</td>
							<td align="center">
								${equip.building_name}
							</td>
							<td align="center" class="biao_lianj_1">
								<a href="javascript:void(0)" onclick="gotoControl(${equip.eqtypeid},${equip.equipid });">${equip.equipname}</a>
							</td>
							<td align="center">
								${equip.unittype }
							</td>
							<td align="center">
								${equip.type_name}
							</td>
							<td align="center">
								${equip.place }
							</td>
							<td align="center">
								<c:if test="${equip.storey<0}">
								  ${fn:replace(equip.storey,'-','B')}
								</c:if>
								<c:if test="${equip.storey>=0}">
								  ${equip.storey }
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 分页从这里开始 -->
			<c:if test="${!empty childEquipList}">
			    <div class="PageingEquip">
					<table width="100%" border="0">
						<tr style="height: 50px;">
							<td>
								<sktag:paginator showTotal="true" showAllPages="true"
									strUnit="条记录" />
							</td>
						</tr>
					</table>
				</div>
			</c:if>
			<!-- 分页在这里结束 -->
			</div>
			<!-- 电表监控界面 -->
			<div class="PageingEquip">
	            <div id="ammeter_control" style=" border:0px solid red; display:none;">
	                <div style="height: 15px; border-top: 1px solid gray;">
						<img src="manager/images/control/closePage.png" alt="关闭监控界面" title="关闭监控界面" style="margin-left: 160px;  cursor: pointer;" onclick="doHideControl();"/>
					</div>
	                <iframe src="" id="ammeter_page" scrolling=no width="100%" height="300" marginwidth=0
				        marginheight=0 frameborder=0></iframe>
	            </div>
			</div>
		</div>
	</body>
</html>