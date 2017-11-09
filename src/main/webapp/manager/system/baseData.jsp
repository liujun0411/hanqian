<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>基础数据</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="manager/ui/assets/css/bootstrap.min.css">
		<link rel="stylesheet"
			href="manager/ui/css/custom-theme/jquery-ui-1.10.0.custom.css"
			type="text/css">
		<link rel="stylesheet"
			href="manager/ui/assets/css/font-awesome.min.css" type="text/css">
		<!--[if IE 7]>
    <link rel="stylesheet" href="manager/ui/assets/css/font-awesome-ie7.min.css">
<![endif]-->
		<!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="manager/ui/css/custom-theme/jquery.ui.1.10.0.ie.css"/>
<![endif]-->
		<link rel="stylesheet" href="manager/ui/assets/css/docs.css">
		<link rel="stylesheet"
			href="manager/ui/assets/js/google-code-prettify/prettify.css">
		<link href="manager/ui/css/pagination.css" type="text/css"
			rel="stylesheet" />
		<link href="manager/ui/css/datepickter.css" type="text/css"
			rel="stylesheet" />
		<link href="manager/ui/css/style.css" type="text/css" rel="stylesheet" />

		<script type="text/javascript"
			src="manager/ui/assets/js/jquery-1.9.0.min.js"></script>
		<script type="text/javascript"
			src="manager/ui/assets/js/jquery-ui-1.10.0.custom.min.js"></script>
		<script type="text/javascript"
			src="manager/ui/assets/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="manager/ui/js/bootstrap-modal.js"></script>

		<script type="text/javascript"
			src="manager/ui/assets/js/google-code-prettify/prettify.js"></script>
		<script type="text/javascript" src="manager/ui/assets/js/docs.js"></script>

		<script type="text/javascript" src="manager/ui/assets/js/demo.js"></script>
		<script type="text/javascript"
			src="manager/ui/js/jquery.pagination.js"></script>
		<script type="text/javascript" src="manager/ui/js/editform_basic.js"></script>
		<style type="text/css">
.biaog_kan2.biaog_zt2 img {
	width: 14px;
	cursor: pointer;
}

textarea {
	width: 450px;
	height: 50px;
	resize: none;
	font-size: 13px;
}

.active {
	background: #28649A;
	color: #FFFFFF;
}
</style>
	</head>

	<body>
		<div id="admin_nr_rightg">
			<form id="myform" name="myform"
				action="baseCommAction_getAllDataBase.action" method="post">
				<input type="hidden" id="qx" value="${menuIdMap['5012003'] }" />
				<input type="hidden" id="pageSum" value="${pageSum}" />
				<input type="hidden" id="typeId" name="typeId" value="${typeId }" />
				<input type="hidden" id="currentPage" name="currentPage"
					value="${currentPage }" />
			</form>
			<div id="com_info" class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						信息管理
						<span class="separative">></span>基础数据
					</h3>
				</div>
				<div class="panel-body">
					<div id="rootDiv" class="box_top">
						<input type="hidden" id="scid" name="scid" value="${scid }" />
						<div class="control-group">
							<label for="selectError3" class="control-label">
								数据类型：
							</label>
							<div class="controls">
								<select id="baseType_select" name="baseType_select"
									class="myselect" onchange="changeType(this);">
									<c:forEach items="${typeList}" var="type">
										<option value="${type.seq}"
											<c:if test="${type.seq==typeId}">selected="selected"</c:if>>
											${type.oper_table}
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="box_middle">
						<div>
							<table id="table_base"
								class="table table-bordered table-hover table-striped">
								<thead>
									<tr>
										<th align="center" style="text-align: center; width: 80px;">
											#
										</th>
										<th id="col_one" style="width: 350px; color: #428BCA;">
											${title1 }
										</th>
										<th id="col_two" style="color: #428BCA;">
											${title2 }
										</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dataList}" var="data" varStatus="idx">
										<tr>
											<td style="text-align: center;">
												<img src="manager/images/common/open.png" id="${data.seq }"
													class="comimg" />
											</td>
											<td>
												${data.content1}
											</td>
											<td colspan="2">
												<c:if test="${! empty data.content2}">
												   ${data.content2 }
												</c:if>
												<c:if test="${empty data.content2}">
												  &nbsp;
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="table_page">
							<div id="oper_div" class="table_oper">
								<c:if
									test="${!empty menuIdMap && !empty menuIdMap['5012001']}">
									<button id="btn-com-add" aria-disabled="false" role="button"
										class="ui-button-primary ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
										<span class="ui-button-text"> <span
											class="ui-button-text">添 加</span> </span>
									</button>
								</c:if>
								<c:if
									test="${!empty menuIdMap && !empty menuIdMap['5012002']}">
									<button id="btn-com-delete" aria-disabled="false" role="button"
										class="ui-button-primary ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
										<span class="ui-button-text"> <span
											class="ui-button-text">删 除</span> </span>
									</button>
								</c:if>
								<div id="PaginationCom" class="paginationDropins"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
