<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>医院后勤智能化管理平台——后勤管理</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/ceng.js">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
		<script src="manager/js/jquery.easyui.min.js" type="text/javascript">
</script>
		<script type="text/javascript">
function formCheck(currentPage) {
	if (currentPage != "" || currentPage != undefined){
		$("#page").val(currentPage);
	}
	document.myform.submit();
}

//连接到设备信息
function trClick(eqId) {
	$('#myform').attr({action:"equipment_showDetail.action?equipId="+eqId});
	document.myform.submit();
	$('#equipId').val('eqId');
	
//  edit start 2013.5.6 by zhangdiannan
	//$('#myform').attr({action:"equipment_showDetail?equipId="+eqId});
	//document.myform.submit();
	window.location.href='equipment_showDetail?equipId='+eqId;
	//edit end 2013.5.6 by zhangdiannan
}
</script>
	</head>

	<body>
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="100%" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							维修巡检保养——巡检保养到期提醒
						</td>
					</tr>
				</table>
			</div>
			<form action="maintenance_findServeMaintenance.action" method="get" name="myform" id="myform">
				<input type="hidden" name="editFlag" value="${editFlag }" />
				<!-- edit start 2013.4.25 by zhangdiannan
				     隐藏域名为page 意义描述为 分页功能的第几页  
				     而后台 定义自定义类型 page 无法将此page（代表第几页） 转换成page类型 
				   修改 name 为page1 不与后台name重复   
				<input type="hidden" name="page" value="${currentPage}" id="page" />
				-->	
				<input type="hidden" name="page1" value="${currentPage}" id="page" />
				<!--  edit end 2013.4.25 by zhangdiannan -->
				<input type="hidden" value="${eqId}" name="eqId" id="eqid" />
			</form>
			<br/>
			<div style="overflow: auto; width: 100%; height: 100%;">
				<table width="98%" border="0" cellspacing="1" class="biaoge_bg1"
					align="center">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<th width="8%" scope="col">
								序号
							</th>
							<th width="13%" scope="col">
								设备名称
							</th>
							<th width="20%" scope="col">
								安装位置
							</th>
							<th width="13%" scope="col">
								资产编号
							</th>
							<th width="13%" scope="col">
								生产厂家
							</th>
							<th width="15%" scope="col">
								安装日期
							</th>
							<th width="10%" scope="col">
								使用年限
							</th>
							<th width="10%" scope="col">
								保养周期
							</th>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:if test="${!empty equipList}">
							<c:forEach items="${equipList}" var="equip" varStatus="i">
								<tr class=" biaog_kan2 biaog_zt2"  style="cursor: pointer;" onclick="trClick(${equip.equip_id})">
									<td align="center">
										${i.index+1 }
									</td>
									<td align="center">
										${equip.equip_name }
									</td>
									<td align="center">
										${equip.place }
									</td>
									<td align="center">
										${equip.assetscode }
									</td>
									<td align="center">
										${equip.production }
									</td>
									<td align="center">
										<fmt:formatDate value="${equip.install_date }" pattern="yyyy-MM-dd" />
									</td>
									<td align="center">
										${equip.service_life }
									</td>
									<td align="center">
										${equip.service_cycle }<c:if test="${!empty equip.service_cycle}">天</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>

				<table width="100%" border="0" align="center">
					<tr style="height: 10px">
						<td></td>
					</tr>
					<tr style="height: 30px">
						<td align="right" width="770">
							<c:if test="${!empty equipList}">
								<sktag:paginator showTotal="true" showAllPages="true"
									strUnit="条记录" />
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>