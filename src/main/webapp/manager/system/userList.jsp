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
		<title>用户管理-用户列表</title>
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

function deleteSubmit(userId) {
	if (window.confirm("您确定删除该用户吗？")) {
		var url = "user!deleteUser.action";
		$("#userId").val(userId);
		var form = $("#myform");
		form.attr("action", url)
		form.submit();
	}
}

function updateSubmit(userId) {
		var url = "user!toUpdateUser.action";
		$("#userId").val(userId);
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
							用户管理——用户列表
						</td>
					</tr>
				</table>
			</div>
			<s:form action="user!findUsers.action" theme="simple"
				method="post" name="myform" id="myform">

				<input type="hidden" name="currentPage" value="${currentPage}"
					id="currentPage" />
				<input type="hidden" name="dbUsers.seq" value="" id="userId" />
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						<td class="shebeigl_inp_zt" align="right" width="90">
							
						</td>
						<td width="100%" align="left">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">

						<c:if test="${!empty menuMap && !empty menuMap['5001002']}">
							<a href="user!toAddUser.action" class="btn blue"
								align="left">添加用户</a>
								</c:if>
						</td>
					</tr>
				</table>
			</s:form>
			<span class="admin_clor_f00">${message}</span>
			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="4%" scope="col">
							序号
						</th>
						<th width="10%" scope="col">
							用户名
						</th>
						<th width="10%" scope="col">
							姓名
						</th>
						<th width="13%" scope="col">
							手机
						</th>
						<th width="13%" scope="col">
							电话
						</th>
						<th width="13%" scope="col">
							邮箱
						</th>
						<th width="13%" scope="col">
							添加时间
						</th>
						<th width="8%" scope="col">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">

					<c:if test="${!empty dbUserList}">
						<c:forEach items="${dbUserList}" var="b" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">
									${index.count}
								</td>
								<td align="center">
									${b.userid}
								</td>
								<td align="center">
									${b.username}
								</td>
								<td align="center" >
									${b.phone}
								</td>
								<td align="center">
									${b.tel1}
									<c:if test="${!empty b.tel2}">,</c:if>
									${b.tel2}
								</td>
								<td align="center">
									${b.email}
								</td>
								<td align="center">
									<fmt:formatDate value="${b.opertime}" pattern="yyyy-MM-dd" />
								</td>

								<td align="center" class="biao_lianj_1">
									<c:if test="${!empty menuMap && !empty menuMap['5001003'] }">
										<a href="javascript:updateSubmit(${b.seq});">修改</a>
									</c:if>
									<c:if test="${!empty menuMap && !empty menuMap['5001004'] }">
										<a href="javascript:deleteSubmit(${b.seq});">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<c:if test="${!empty dbUserList}">
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
