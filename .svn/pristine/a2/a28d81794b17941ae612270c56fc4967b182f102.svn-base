<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>信息查看</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="imagetoolbar" content="no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- <link href="css4/axurerp_pagespecificstyles.css" type="text/css"
	rel="stylesheet"> -->
<link rel="shortcut icon" href="icon/favicon.ico" type="image/x-icon" />
<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
var  mid =	${contentMap.mid};
	//回复 
	$("#to_info").click(function(){
			//window.location("www.baidu.com");
			 window.location.href = "info_findInfo_byidreply.action?mid="+mid;
		})
		//返回
    $("#return").click(function(){

   	 window.location.href = "info_infoMain.action?pg_num=1&pageNumber=20";

        })
		
})


</script>

</head>
<body>
<input type="hidden" id="mid" name="mid" value="${contentMap.mid}">
		<div id="main_container"
			style="height:50%; position: relative;  width: 69%; left:90px">
	
			<div id="u0" class="u0">
				<div id="u0_rtf">
					<p style="text-align: center;">
						<span
							style="font-family: Arial; font-size: 27px; font-weight: normal; font-style: normal; text-decoration: none; color: #000000;">${contentMap.title}</span>
					</p>
				</div>
			</div>
			<div id="u1" class="u1" style="margin-top: -30px;">
				<div id="u1_rtf">
					<p style="text-align: right;" >
						<span
							style="font-family: Arial; font-size: 13px; font-weight: normal; font-style: normal; text-decoration: none; color: grey;">${contentMap.otime}</span>
					</p>&nbsp;
					<span style="display:block;">
					<input type="button" value="回复"  id="to_info">
					<input type="button" value="返回" id="return">
						
			</span>
				</div>
			</div>
			<hr style="color:red"/>
		
			<div id="u4" class="u4" style="height:100%;overflow:auto;">
				<div id="u4_rtf" >
					
					<p style="text-align: left;">
						<span
							style="font-family: Arial; font-size: 13px; font-weight: normal; font-style: normal; text-decoration: none; color: #333333;">&nbsp;
							&nbsp;
							${contentMap.content}</span>
					</p>
				</div>
			</div>
		<hr style="color:red"/>
		
		<div id="u5" class="u5">
			<div id="u5_rtf">
				<p style="text-align: left;">
				<%--<c:if test="${not empty contentMap.attach_file}">
					<span
						style="font-family: Arial; font-size: 13px; font-weight: normal; font-style: normal; text-decoration: none; color: #000000;">附件:</span>
				</c:if>
				--%></p>
			</div>
		</div>
		
		<div id="u6" class="u6" style="margin-top: -32px;margin-left: 33px;">
			<div id="u6_rtf">
				<p style="text-align: left;">
				
					
					
				</p>
			</div>
			
		</div>
		<p>

				</p>&nbsp;
				<p>
				<%--<a id="btn_print2" href="http://localhost:8080/logistics/info_infofileDownload.action?fileUrl=${ contentMap.ATTACH}"
						style="font-family: Arial;cursor: pointer;font-weight: normal; font-style: normal;color: #0000FF; font-size: 13px;text-decoration:none; font-weight: bold;float: left;">下载附件</a>
				--%>
				<c:if test="${contentMap.attach_file == null }">
			           	<a style="font-family: Arial;cursor: pointer;font-weight: normal; font-style: normal;color: #0000FF; font-size: 13px;text-decoration:none; font-weight: bold;float: left;" href="#"></a>
				</c:if>
				<c:if test="${contentMap.attach_file != null }">
				<a href="logistics/info_infofileDownload.action?fileUrl=${ contentMap.attach_file}"
						style="font-family: Arial; font-size: 20px; font-weight: normal; font-style: normal; text-decoration: none; color: #0000FF; cursor: pointer;">
						下载</a>
				</c:if>
				</p>
	</div>
 
      
      
      
</body>
</html>