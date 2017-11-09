<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript">
		</script>
		<base href="<%=basePath%>" />
		<title>平台静态历史数据1111</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet"
			type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		
		<!-- 引入公用控件 -->
        <script type="text/javascript" src="manager/common/equip/js/jquery-1.3.2.min.js"></script>
		<link  href="manager/common/equip/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerComboBox.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerResizable.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerTree.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/createTree.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/equipType.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery.validate.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script type="text/javascript">
				
		function fanhui(obj){
			$('#changeDiv').show();
			$('#detailDiv').html("");
		}
		</script>

	</head>

	<body>
			<input type="hidden" id="redName" name="redName" value="${cn_name}" />
			<input type="hidden" id="redNameValue" name="redNameValue" value="${cn_value}" />
			<div style="width:100%;">
			   <table width="1000" id="deteilDiv_info"  cellspacing="1" class="biaoju_tong_1 admin_bgclor_c6c gai_left_duiqi">
				   <c:forEach items="${voList}" step="2" varStatus="i" >
				       <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
				          <td class="admin_bgclor_e3f" id="cname" width="150px;">${voList[i.index].CNName}</td>
				          <td class="admin_bgclor_f1f"  width="300px;">
				              <c:if test="${!empty voList[i.index].CNValue}">
				              <c:if test="${tableName=='DB_BUILDING_PIC_LOG'}"><c:if test="${voList[i.index].CNName=='图片'}"><a href="manager/images/buildPic/${voList[i.index].CNValue}" target="_blank">查看或者下载图片</a></c:if><c:if test="${voList[i.index].CNName!='图片'}">${voList[i.index].CNValue}</c:if></c:if>
				              <c:if test="${tableName=='DB_EQUIP_GROUP_LOG'}"><c:if test="${voList[i.index].CNName=='图片'}"><a href="manager/images/groupPic/${voList[i.index].CNValue}" target="_blank">查看或者下载图片</a></c:if><c:if test="${voList[i.index].CNName!='图片'}">${voList[i.index].CNValue}</c:if></c:if>
				              <c:if test="${tableName=='DB_EQUIP_PIC_LOG'}"><c:if test="${voList[i.index].CNName=='图片'}"><a href="manager/images/equipPic/${voList[i.index].CNValue}" target="_blank">查看或者下载图片</a></c:if><c:if test="${voList[i.index].CNName!='图片'}">${voList[i.index].CNValue}</c:if></c:if>
				              <c:if test="${tableName!='DB_BUILDING_PIC_LOG' && tableName!='DB_EQUIP_GROUP_LOG' && tableName!='DB_EQUIP_PIC_LOG'}"> ${voList[i.index].CNValue}</c:if></c:if>
				              <c:if test="${ empty voList[i.index].CNValue}"></c:if>
				          </td>
				          <td class="admin_bgclor_e3f"  width="150px;">${voList[i.index+1].CNName}</td>
				          <td class="admin_bgclor_f1f"  width="300px;">
				              <c:if test="${!empty voList[i.index+1].CNValue}">
				              <c:if test="${tableName=='DB_BUILDING_PIC_LOG'}"><c:if test="${voList[i.index+1].CNName=='图片'}"><a href="manager/images/buildPic/${voList[i.index+1].CNValue}" target="_blank">查看或者下载图片</a></c:if><c:if test="${voList[i.index+1].CNName!='图片'}">${voList[i.index+1].CNValue}</c:if></c:if>
				              <c:if test="${tableName=='DB_EQUIP_GROUP_LOG'}"><c:if test="${voList[i.index+1].CNName=='图片'}"><a href="manager/images/groupPic/${voList[i.index+1].CNValue}" target="_blank">查看或者下载图片</a></c:if><c:if test="${voList[i.index+1].CNName!='图片'}">${voList[i.index+1].CNValue}</c:if></c:if>
				              <c:if test="${tableName=='DB_EQUIP_PIC_LOG'}"><c:if test="${voList[i.index+1].CNName=='图片'}"><a href="manager/images/equipPic/${voList[i.index+1].CNValue}" target="_blank">查看或者下载图片</a></c:if><c:if test="${voList[i.index+1].CNName!='图片'}">${voList[i.index+1].CNValue}</c:if></c:if>
				              <c:if test="${tableName!='DB_BUILDING_PIC_LOG' && tableName!='DB_EQUIP_GROUP_LOG' && tableName!='DB_EQUIP_PIC_LOG'}">${voList[i.index+1].CNValue}</c:if></c:if>
				              <c:if test="${ empty voList[i.index+1].CNValue}"></c:if>
				          </td>
				       </tr>
				   </c:forEach>
				   <tr><td align="right" style="border: 0px;border-style: none" colspan="4" class="admin_bgclor_f1f"><input type="button" id="fanhui" name="fanhui" value="返回" onclick="fanhui('staticHistory_StaticHistoryData.action?currentPage=1')"/></td></tr>
			   </table>
			</div>
		</div>
	</body>
</html>
