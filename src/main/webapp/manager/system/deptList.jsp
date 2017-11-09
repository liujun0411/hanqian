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
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript">
		</script>
		<base href="<%=basePath%>" />
		<title>组织管理-组织列表</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />
		

		<script src="manager/js/annu.js" type="text/javascript"></script>
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

function deleteSubmit(deptId) {
	if (window.confirm("您确定删除该组织吗？")) {
		var url = "dept!deleteDept.action";
		$("#deptId").val(deptId);
		var form = $("#myform");
		form.attr("action", url)
		form.submit();
	}
}

function updateSubmit(deptId) {
		var url = "dept!toUpdateDept.action";
		$("#deptId").val(deptId);
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
							组织管理——组织列表
						</td>
					</tr>
				</table>
			</div>
			
			<s:form action="dept!findDepts.action" theme="simple"
				method="post" name="myform" id="myform">

				<input type="hidden" name="currentPage" value="${currentPage}" id="currentPage" />
				<input type="hidden" name="dbDept.seq" value="" id="deptId" />
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						<td class="shebeigl_inp_zt" align="right" width="90">
							组织名称
						</td>
						<td width="80" align="left">
							<input type="text" name="dbDept.name" value="${dbDept.name}"/>
						</td>
						<td width="100px" align='right' class='shebeigl_inp_zt'>
							负责人
						</td>
						<td align="left" width='80'>
							<input type="text" name="dbDept.principal" value="${dbDept.principal}"/>
						</td>
						<td width='100'align="right">
							<img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="javascript:document.forms[0].submit();">
							&nbsp;&nbsp;
						</td>
						<td width='80'>
						<c:if test="${!empty menuMap && !empty menuMap['5002002']}">
							<a href="manager/system/deptAddOrUpdate.jsp" class="btn blue"
								align="left">添加</a>
								</c:if>
						</td>
					</tr>
				</table>
			</s:form>
			<br />
			<span class="admin_clor_f00">${message}</span>
			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="5%" scope="col">
							序号
						</th>
						<th width="10%" scope="col">
							组织名称
						</th>
						<th width="10%" scope="col">
							组织负责人
						</th>
						<th width="13%" scope="col">
							创建人
						</th>
						<th width="8%" scope="col">
							创建时间
						</th>
						<th width="8%" scope="col">
							更改时间
						</th>
						<th width="15%" scope="col">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">

					<c:if test="${!empty dbDepts}">
						<c:forEach items="${dbDepts}" var="b" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">
									${index.count}
								</td>
								<td align="center">
									${b.name}
								</td>
								<td align="center">
									${b.principal}
								</td>
								<td align="center" >
									${b.dbUsersByOper.username}
								</td>
								<td align="center" >
									<fmt:formatDate value="${b.opertime}" pattern="yyyy-MM-dd" />
								</td>
								<td align="center" >
									<fmt:formatDate value="${b.updatetime}" pattern="yyyy-MM-dd" />
								</td>

								<td align="center" >
								<c:if test="${!empty menuMap && !empty  menuMap['5002005'] }">
									<a href="dept!toUserDept.action?dbDept.seq=${b.seq}">用户组织关系</a>
								</c:if>
								<c:if test="${!empty menuMap && !empty  menuMap['5002003'] }">
									<a href="javascript:updateSubmit(${b.seq});">修改</a>
								</c:if>
								<c:if test="${!empty menuMap && !empty  menuMap['5002004'] }">
									<a href="javascript:deleteSubmit(${b.seq});">删除</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
					</tr>
				</tbody>
			</table>
			<c:if test="${!empty dbDepts}">
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
