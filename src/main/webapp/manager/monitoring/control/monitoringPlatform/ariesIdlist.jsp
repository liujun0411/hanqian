<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
		
		<title>医院后勤智能化管理平台——后勤管理</title>

		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/reqi.js" type="text/javascript"></script>
		<!-- 引入公用控件 -->
		<link  href="manager/common/equip/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/common/equip/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerComboBox.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerResizable.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerTree.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/createTree.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery.validate.js"></script>
		<script type="text/javascript" src="manager/javascript/buildStorey/buildStorey.js"></script>
		
		<script type="text/javascript">
			//返回列表页面
			function showEquipList(){
				$(window.parent.document).find("#ifremeShowDetail").fadeOut("slow",function(){
					$(window.parent.document).find("#divShowDetail").show();
					$(window.parent.document).find("#ifremeShowDetail").attr("src","");
				});
			}
		</script>
		<style type="text/css">
			.divborder{
					border:1px solid #c4b9b7;
			}
			.divborder .divborderText{
			        line-height: 26px;
			        margin-left: 10px;
			        margin-top: 10px;
			        margin-right: 10px;
			}
		</style>
	</head>
	<body>
		<div id="admin_nr_rightg">
			<!-- 设备详情 -->
		    <div id="equipdetail"  >
			<!-- 加线条的  class="louyujj_xiaxian_hui gai_left_duiqi" -->
				<table style="height: 100%;width: 100%;"  >
				<tr>
				 <td align="left" style="width:103px;"><samp style="color:#c4b9b7;">内容</samp> </td>
				  <td> <input type="hidden" id="hisCurrentPage" name="hisCurrentPage" value="${hisCurrentPage}"/></td>
				  <td align="right" style="width:103px;">
                       <c:if test="${!empty content}">
					      <a class="btn blue" onclick="showEquipList();" style="float: right;"><i></i><span><i></i><span></span>返回</span></a>
				       </c:if>
                   </td>
				</tr>
				</table>
				<table border="0" style="height: 100%;width: 100%;">
				<tr>
				  <td>
				  <div class="divborder" <c:if test="${hisCurrentPage==1}">style="height:350px ;width:1153px;"</c:if>
				                         <c:if test="${hisCurrentPage==2}">style="height:350px ;width:100%;"</c:if>><!-- 加边框 border:1px solid #c4b9b7;    style="height:350px ;width:1341px;"-->
					   <div class="divborderText"  <c:if test="${hisCurrentPage==1}">style="height:330px ;width:1130px;"</c:if>
				                                   <c:if test="${hisCurrentPage==2}">style="height:330px;width:98%;"</c:if>><!-- 加边框 border:1px solid #c4b9b7; -->
					   <c:if test="${!empty content}">
					   <c:forEach items="${content}" var="obj" varStatus="indx">
					        ${obj.content}
					   	</c:forEach>	
					   	</c:if>
					   </div>
				   </div>
				</td>
				</tr>
				</table>
	        </div>
	    </div>
	</body>
	
</html>
