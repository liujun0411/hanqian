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
		<link href="manager/css/annu.css" rel="stylesheet"
			type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/ceng.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/xialakuang.js"></script>
		<script type="text/javascript">
		    //分页控件方法
			 function formCheck(currentPage) {
				if (currentPage != "") {
					$("#currentPage").val(currentPage);
				}
				document.myform.submit();
			}
		</script>
	</head>

	<body>
		<div id="admin_nr_rightg">
		  <form id="myform" name="myform" action="maintenance_findEquipmentByPage.action">
		     <input type="hidden" id="equipId" name="equipId" value="${equipId }" />
		     <input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
		  </form>
				<table width="100%" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr style="height: 25px" valign="bottom">
						<td width="250" class="gai_clor_063">&nbsp;
						</td>
						<td width="500">&nbsp;
							
						</td>
						<td width="80">
							<a href="maintenance_addMaintenanceUI.action?equipId=${equipId}" class="btn blue">添加</a>
						</td>
				    </tr>
				</table>
				<table width="100%" border="0" cellspacing="1" class="biaoge_bg1 gai_left_duiqi">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						    <th width="5%" scope="col">
								序号
							</th>
							<th width="16%" scope="col">
								设备名称
							</th>
							<th width="13%" scope="col">
								设备编号
							</th>
							<th width="28%" scope="col">
								维护内容
							</th>
							<th width="10%" scope="col">
								维护类型
							</th>
							<th width="10%" scope="col">
								维护人员
							</th>
							<th width="13%" scope="col">
								维护日期
							</th>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:if test="${!empty mainTenanceList}">
							<c:forEach items="${mainTenanceList}" var="maintenance" varStatus="idx">
								<tr class=" biaog_kan2 biaog_zt2">
								    <td align="center">
								        ${idx.index+1 }
								    </td>
									<td align="center">
										${dbEquipList.equipName}
									</td>
									<td align="center">
									    ${dbEquipList.equipCode}
									</td>
									<td align="center">
										${maintenance.content}
									</td>
									<td align="center">
										<c:if test="${maintenance.maintenancetype==1}">维修</c:if>
										<c:if test="${maintenance.maintenancetype==2}">巡检</c:if>
										<c:if test="${maintenance.maintenancetype==3}">保养</c:if>
									</td>
									<td align="center">
										${maintenance.serviceman}
									</td>
									<td align="center">
									    <fmt:formatDate value="${maintenance.servicetime }" pattern="yyyy-MM-dd" />
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<table width="100%" border="0" class="gai_left_duiqi">
					<tr style="height:10px">
						<td></td>
					</tr>
					<tr style="height:30px">
						<td align="right" width="770">
							<c:if test="${!empty mainTenanceList}">
								<sktag:paginator showTotal="true" showAllPages="true"
									strUnit="条记录" />
							</c:if>
						</td>
					</tr>
				</table>
			</div>
	</body>
</html>

