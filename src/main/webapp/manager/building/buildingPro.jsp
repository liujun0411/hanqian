<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台</title>			
	</head>
<body>

<center>
<c:if test="${!(empty useImage)}">
<div id="admin_nr_jbxx">  
 <table width="100%" border="0">      
      <tr>
         <td ><img src='${useImage}' width=600 height=400 border=0 /></td>
      </tr>        
   </table>
</div>
</c:if>

</center>
</body>
</html>

