<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="refresh" content="30"> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>综合安全指数</title>
		<meta name="Description" content="" />
		<meta name="Keywords" content="" />
		<script type="text/javascript" src="manager/js/highchartsJs/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="manager/js/highchartsJs/highcharts-js.js"></script>
</head>
	<body>
	<script type="text/javascript" src="manager/js/highchartsJs/highcharts.js"></script>
	<script type="text/javascript" src="manager/js/highchartsJs/highcharts-more.js"></script>
	<script type="text/javascript" src="manager/js/highchartsJs/solid-gauge.js"></script>
		<div style="width: 300px; height: 210px; margin: 0 auto">
			<div id="container-speed" style="width: 300px; height: 200px; float: left"></div>
		</div>
	</body>
</html>
