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
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/biaoge_1.js">
</script>
		<link rel="stylesheet" href="manager/common/tab/ui.tabs.css"
			type="text/css" media="print, projection, screen"></link>
		<script type="text/javascript" src="manager/common/tab/ui.core.js">
</script>
		<script type="text/javascript" src="manager/common/tab/ui.tabs.js">
</script>

		<script type="text/javascript">
function formCheck(currentPage) {
	if (currentPage != "")
		$("#currentPage").val(currentPage);
	document.myForm.submit();
}f_goLHControl = function(eqId) {
	$("#eqId").val(eqId);
	document.myForm.action = "control_toLHControl.action";
	document.myForm.submit();
}f_goAir = function(eqId,buildid){
				$("#eqId").val(eqId);
				$("#buildid").val(buildid);
				document.myForm.action = "buildEq_ShowEqListByBuilding.action";
				document.myForm.submit();	
			}
		<%--
		f_goLHControl = function(eqType,eqCode ,buildName,buildid){
			$("#eqType").val(eqType);
			$("#eqCode").val(eqCode);
			$("#buildName").val(buildName);
			$("#buildid").val(buildid);
			document.myform.action = "control_toLHControl.action";
			document.myform.submit();	
		}
		--%>
		f_goRJControl = function(eqType,eqCode ,buildName,buildid){
			$("#eqType").val(eqType);
			$("#eqCode").val(eqCode);
			$("#buildName").val(buildName);
			$("#buildid").val(buildid);
			document.myForm.action = "rjControl_toRJControl.action";
			document.myForm.submit();	
		}
		f_goXKControl = function(eqType,eqCode ,buildName,buildid){
			$("#eqType").val(eqType);
			$("#eqCode").val(eqCode);
			$("#buildName").val(buildName);
			$("#buildid").val(buildid);
			
			document.myForm.action = "xkControl_showControlUI.action";//   showControlUI  toXKControl
			document.myForm.submit();	
		}
		f_goFKControl = function(eqType,eqCode ,buildName,buildid){
			
			$("#eqType").val(eqType);
			$("#eqCode").val(eqCode);
			$("#buildName").val(buildName);
			document.myForm.action = "control_toFKControl.action";
			document.myForm.submit();	
			
		}
   
        function showControlPage(buildId,equipTypeId){
           $('#commonShow').slideToggle(1000);
           var selectDiv=$('.ui-tabs-selected').children().attr('href');
           $(selectDiv).slideToggle(1000);
           $('#controlDescript').attr({src:'control_findEquipByParam.action?buildId='+buildId+"&eqTypeId="+equipTypeId});
           $('#controlPage').attr({src:'control_findToControl.action'});
        }
	</script>
	</head>

	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<div id="admin_nr_rightg">
			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
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
							设备品牌
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
					<c:forEach items="${equipList}" var="equip" varStatus="index">
						<tr class=" biaog_kan2 biaog_zt2">
							<td align="center">
								${index.index+1 }
							</td>
							<td align="center">
								${equip.building_name}
							</td>
							<td align="center" class="biao_lianj_1">
								${equip.equipname}
							</td>
							<td align="center">
								${equip.brand }
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
								${equip.storey }
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 分页从这里开始 -->
			<c:if test="${!empty eqList}">
				<table width="100%" border="0">
					<tr style="height: 50px;">
						<td></td>
						<td>
							<sktag:paginator showTotal="true" showAllPages="true"
								strUnit="条记录" />
						</td>
					</tr>
				</table>
			</c:if>
			<!-- 分页在这里结束 -->
		</div>
	</body>
</html>