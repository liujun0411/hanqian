<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>医院后勤智能化管理平台</title>

<link rel="stylesheet" href="manager/css/neirong.css" type="text/css"></link>
<link rel="stylesheet" href="manager/css/skdiv.css" type="text/css"></link>
<link rel="stylesheet" href="manager/css/ziti.css" type="text/css"></link>
<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
<link rel="stylesheet" href="manager/css/annu.css" type="text/css" />

<script src="manager/js/annu.js" type="text/javascript"></script>
<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
<script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
<script type="text/javascript"
	src="manager/javascript/buildStorey/buildStorey.js"></script>
<script type="text/javascript" src="manager/js/tableTr.js"></script>
</head>

<body>
	<div id="admin_nr_rightg">

		<div class="canshusz_btbg_1">
			<table width="100%" class="titleBg">
				<tbody>
					<tr style="height: 20px">
						<td width="30" align="center"><img
							src="manager/images/common/28-01.png"></td>
						<td>环境指标</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div id="ListInfo">
			<div class="itemList2">
				<table width="100%" border="0" cellspacing="1" class="listTable">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<td align="center" width="10%">序号</td>
							<td align="center" width="40%">描述</td>
							<td align="center" width="15%">值</td>
							<td align="center" width="35%">时间</td>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:forEach items="${illuminationList}" var="item" varStatus="idx">
							<tr class="tr${idx.index%2+1}" id="trhref">
								<td align="center">${idx.index+1}</td>
								<td align="center">
									<a href="javascript:void(0);" class="ahref">${item.DESCR == null ? "--" : item.DESCR}</a>
								</td>
								<td align="center">${item.RECORD == null ? "--" : item.RECORD}</td>
								<td align="center">
									<f:formatDate value="${item.RECORDTIME == null ? '--' : item.RECORDTIME}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>

