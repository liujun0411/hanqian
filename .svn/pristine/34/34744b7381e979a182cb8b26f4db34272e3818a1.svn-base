<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<base href="<%=basePath%>"/>
		<title>My JSP 'addEquipment.jsp' starting page</title>
		<link rel="stylesheet" href="manager/css/neirong.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/skdiv.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/ziti.css" type="text/css"></link>
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script type="text/javascript">
			function postDbEqParam(){
				document.getElementById("myform").submit();	
			}
			
			//goBack　＝　function(){
				//window.parent.$("#son").html("");
				//location.href="equipment_equipmentDetail.action?eqId=${eqid }&myfirst=equipment_findEquipmentByPage&editFlag=2&hospid=${hospitalid}";
			//}
		</script>
	</head>

	<body>
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
			<table width="780" border="0">
					<tr style="height:30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							设备管理——设备列表——设备——参数添加
						</td>
					</tr>
				</table>
			</div>
			<form action="dbEquipParam_addDbEqParam.action" name="myform" id="myform"
				method="post">
			<input type="hidden" name="equipId" value="${equipId}" />
			<input type="hidden" name="vLength" value="${vLength}" />
			<table width="920" border="0" cellspacing="1" style="float: left;">
				<c:forEach var="p" items="${equipParamList}" varStatus="i" step="2">
					<tbody id="mytboy">
						<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
							<td class="admin_bgclor_e3f">
							    <c:if test="${equipParamList[i.index].param_name !=null }">
								${equipParamList[i.index].param_name }<c:if test="${equipParamList[i.index].param_unit != null}">(${equipParamList[i.index].param_unit })</c:if>
								<input type="hidden" id="k${i.index}" name="k${i.index}" value="${equipParamList[i.index].seq }" />
								</c:if>
							</td>
							<td class="admin_bgclor_f1f">
								<input type="text" id="v${i.index }" name="v${i.index }" value="${equipParamList[i.index].default_value }" />
							</td>
							<td class="admin_bgclor_e3f">
								<c:if test="${equipParamList[i.index+1].param_name !=null }">
								${equipParamList[i.index+1].param_name }<c:if test="${equipParamList[i.index+1].param_unit != null}">(${equipParamList[i.index+1].param_unit })</c:if>
								<input type="hidden" id="k${i.index+1}" name="k${i.index+1}" value="${equipParamList[i.index+1].seq }" />
								</c:if>
							</td>
							<td class="admin_bgclor_f1f">
							<c:if test="${equipParamList[i.index+1].param_name != null}">
								<input type="text" id="v${i.index+1 }" name="v${i.index+1 }" value="${equipParamList[i.index].default_value }"  />
							</c:if>
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
            <c:if test="${!(empty equipParamList)}">
				<table width="920" border="0" style="float: left;">
					<tr>
						<td></td>
						<td width="65">
							<a href="javascript:postDbEqParam();" class="btn blue">保存</a>
						</td>
						<td width="65" id="son">
							<a href="equipment_showDetail.action?equipId=${equipId}" class="btn blue">取 消</a>
						</td>
					</tr>
				</table>
			</c:if>
			</form>
			${msg }
		</div>
	</body>
</html>
