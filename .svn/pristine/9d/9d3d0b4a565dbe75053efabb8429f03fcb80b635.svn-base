<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js"
			type="text/javascript">
		</script>
		<base href="<%=basePath%>" />
		<title>角色管理-角色列表</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />

		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/ceng.js">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/xialakuang.js">
</script>
		<script type="text/javascript">

//分页
function formCheck(currentPage) {
	if (currentPage != "") {

		$("#currentPage").val(currentPage);
	}
	document.forms[0].submit();
}

function deleteSubmit(roleId) {
	if (window.confirm("您确定删除该角色吗？")) {
		var url = "role!deleteRole.action";
		$("#roleId").val(roleId);
		var form = $("#myform");
		form.attr("action", url)
		form.submit();
	}
}

function updateSubmit(roleId) {
		var url = "role!toUpdateRole.action";
		$("#roleId").val(roleId);
		var form = $("#myform");
		form.attr("action", url)
		form.submit();
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
						<td class="biaoti_zt_canshusz">
							角色管理-角色列表
						</td>
					</tr>
				</table>
			</div>
			<s:form action="role!findRoles.action" theme="simple"
				method="post" name="myform" id="myform">

				<input type="hidden" name="currentPage" value="${currentPage}"
					id="currentPage" />
				<input type="hidden" name="dbRoles.roleId" value="" id="roleId" />
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						<td class="shebeigl_inp_zt" align="right" width="90">
							
						</td>
						<td width="100%" align="left">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right" >
						
							<c:if test="${!empty menuMap && !empty menuMap['5003002']}">
							<a href="role!toAddRole.action"  class="btn blue"
								align="left">添加角色</a>
							</c:if>
						</td>
					</tr>
				</table>
			</s:form>

			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="5%" scope="col">
							序号
						</th>
						<th width="10%" scope="col">
							角色名
						</th>
						<th width="10%" scope="col">
							角色描述
						</th>

						<th width="8%" scope="col">
							创建人
						</th>
						<th width="15%" scope="col">
							创建时间
						</th>
						<th width="8%" scope="col">
							更新人
						</th>
						<th width="13%" scope="col">
							更新时间
						</th>
						<th width="15%" scope="col">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">

					<c:if test="${!empty roleList}">
						<c:forEach items="${roleList}" var="b" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">
									${index.count}
								</td>
								<td align="center">
									${b.name}
								</td>
								<td align="center">
									${b.depict}
								</td>
								<td align="center">
									${b.oper}
								</td>
								<td align="center">
									<fmt:formatDate value="${b.opertime}" pattern="yyyy-MM-dd" />
								</td>
								<td align="center">
									${b.updater}
								</td>
								<td align="center">
									<fmt:formatDate value="${b.updatertime}" pattern="yyyy-MM-dd" />
								</td>
								<td align="center" class="biao_lianj_1">
								<c:if test="${!empty menuMap && !empty menuMap['5003005']}">
									<a href="role!toRoleUser.action?dbRoles.roleId=${b.role_id}">角色用户关系</a>
								</c:if>
								<c:if test="${!empty menuMap && !empty menuMap['5003003']}">	
									<a href="javascript:updateSubmit('${b.role_id}');">修改</a>
								</c:if>
								<c:if test="${!empty menuMap && !empty menuMap['5003004']}">
									<a href="javascript:deleteSubmit('${b.role_id}');">删除</a>
									
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<c:if test="${!empty roleList}">
				<table width="100%" border="0" class="gai_left_duiqi">
					<tr style="height: 10px;">
						<td width="35%"></td>
						<td width="65%"></td>
					</tr>
					<tr style="height: 30px;">
						<td colspan="5">
							<sktag:paginator showTotal="true" showAllPages="true"
								strUnit="条记录" />

						</td>
					</tr>
				</table>
			</c:if>
			<!--页面结束 -->
	</body>
</html>
