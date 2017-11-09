<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@page import="java.net.URLEncoder"%>
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
<title>My JSP 'eqPicList.jsp' starting page</title>

<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
<script src="manager/js/annu.js" type="text/javascript"></script>
<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
<script src="manager/config/config.js" type="text/javascript"></script>



<link
	href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/css/aries/jquery.aries.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/css/jsPanel/jquery.jspanel.min.css"/>"
	rel="stylesheet" type="text/css" />
<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script>
<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script>
<script src="<c:url value="/static/js/jsPanel/mobile-detect.min.js"/>"></script>
<script src="<c:url value="/static/js/jsPanel/jquery.jspanel.min.js"/>"></script>



<script type="text/javascript">
	//分页控件方法
	function formCheck(currentPage) {
		if (currentPage != "") {
			$("#currentPage").val(currentPage);
		}
		document.myform.submit();
	}

	//任意提醒，通过标签ID给需要绑定数据的元素绑定数据
// 	$(document).ready(
// 			function() {
// 				var remindJson = $
// 				{
// 					remindJson
// 				}
// 				;
// 				for ( var obj in remindJson) {
// 					console.debug("       equipPicList=     " + remindJson[obj].resultsql
// 							+ ",obj=" + obj + ", $(obj)=" + $("#" + obj));
// 					$("#" + obj).data("aries", remindJson[obj]);
// 				}
// 			});
</script>
</head>
<body>
	<div id="admin_nr_rightg">

		<form action="equipPic_findEquipPicList.action" name="myform"
			id="myform">
			<input type="hidden" name="equipId" value="${equipId}" /> <input
				type="hidden" id="currentPage" name="currentPage"
				value="${currentPage }" />
		</form>

		<table width="100%" border="0"
			class="louyujj_xiaxian_hui gai_left_duiqi">
			<tr>
				<td><a href="equipPic_showAddPicUI.action?equipId=${equipId}"
					class="btn blue">上传图纸</a></td>
				<td width="10" align="left"></td>
			</tr>
		</table>
		<div style="">
			<table width="100%" border="0" cellspacing="1"
				class="biaoju_tong_1 admin_bgclor_c6c" align="left">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="5%" scope="col">序号</th>
						<th width="20%" scope="col">图纸编号</th>
						<th width="20%" scope="col">设备名称</th>
						<th width="15%" scope="col">图纸类型</th>
						<th width="20%" scope="col">图纸名称</th>
						<th width="20%" scope="col">操作</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<c:if test="${!empty equipPicList}">
						<c:forEach items="${equipPicList}" var="eqpic" varStatus="i">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">${i.index+1 }</td>
								<td align="center" >
									${eqpic.pic_code }</td>
								<td align="center">
									${eqpic.equip_name}</td>
								<td align="center" >
									${eqpic.pic_type_name }</td>
								<td align="center" >
									${eqpic.equip_pic_name}</td>
								<td align="center" class="biao_lianj_1"><a
									href="javascript:window.location='equipPic_equipPicDownload.action?fileName=${eqpic.equip_pic_name}
													${fn:substring(eqpic.pic_address, 
													fn:indexOf(eqpic.pic_address,'.'), 
													fn:length(eqpic.pic_address ))}&
											fileUrl=${eqpic.pic_address}'">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
									<a
									href="javascript:if(window.confirm('是否确定删除?')){window.location='equipPic_deletePic.action?equipId=${equipId}&picId=${eqpic.equip_pic_id }&seq=${eqpic.seq}';}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<c:if test="${!empty equipPicList}">
				<table width="100%" border="0" align="center">
					<tr style="height: 10px">
						<td></td>
					</tr>
					<tr style="height: 30px">
						<td colspan="5"><sktag:paginator showTotal="true"
								showAllPages="true" strUnit="条记录" /></td>
					</tr>
				</table>
			</c:if>
			<div>&nbsp;</div>
		</div>
	</div>
</body>
</html>
