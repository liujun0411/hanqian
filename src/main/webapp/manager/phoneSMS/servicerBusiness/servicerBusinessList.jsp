<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		 <title>服务商管理——服务商列表</title>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />

		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
		<script type="text/javascript" src="manager/js/ceng.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/xialakuang.js"></script>
		
		<script type="text/javascript">

//分页
function formCheck(currentPage) {
	if (currentPage != "") {
		$("#currentPage").val(currentPage);
	}
	document.subForm.submit();
}
//删除
var deleteService = function(id){
	var isdel=window.confirm('确认要删除吗？');

	if(isdel){
		$("#seq").val(id);
		$("#subForm").attr("action","serviceAction_deleteService.action");
		$("#subForm").submit();
	}

}
//修改or新增
var updateService = function(id){
	if(id!=""&&id&&id!=null&&id != undefined&&id != 'undefined' ){
		$("#editFlag").val("update");
	}else{
		$("#editFlag").val("add");
	}
	$("#seq").val(id);
	$("#subForm").attr("action","serviceAction_updateBeforeService.action");
	$("#subForm").submit();

}

</script>

	</head>

	<body >

		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="100%" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td class="biaoti_zt_canshusz">
							服务商管理——服务商列表
						</td>
					</tr>
				</table>
			</div>
			<form id="subForm" name="subForm" action="serviceAction_findServicerList.action">
				<input type="hidden" name="currentPage" value="${currentPage}"
					id="currentPage" />
					<input type="hidden" name="editFlag" value=""
					id="editFlag" />
					<input type="hidden" name="seq" value=""
					id="seq" />
					<span style="color:red">${message}</span>
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi"
					width="100%" align="right" style="float: right;">
					<tr>
						<td>
							<span style="font-weight: normal; font-size: 12px"
								class="admin_clor_f00"></span>
						</td>
						<td  width="90" class="shebeigl_inp_zt">
							<span>服务商名称：</span>
						</td>
						<td  width="100">
							<input id="serName" name="serName" class="myfont" value="${serName }"/>
						</td>
						<td  width="90" class="shebeigl_inp_zt">
							<span>服务商类型：</span>
						</td>
						<td  width="100">
							<select id="serType" name="serType" class="myfont" >
								<option value=""> --请选择服务商类型--</option>
								<c:forEach items="${serviceTypeList}" var="stype">
									<option value="${stype.seq }" ${serType== stype.seq?'selected':''}>${stype.content1 }</option>
								</c:forEach>
							</select>
						</td>
						<td  width="4">
							<img onclick="formCheck();"	src="manager/images/imgs/shebeigl_sousuo_an.png"
								style="cursor: pointer"/>
						</td>
						<td  width="50">
							<c:if test="${!empty menuIdMap && !empty menuIdMap['5007003001']}">
								<a href="javascript:void(0)" class="btn blue" id="addBtnA" onclick="updateService()">添加</a>
							</c:if>
							
						</td>
					</tr>
				</table>
			</form>
			
			
			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="5%" scope="col">
							序号
						</th>
						<th width="15%" scope="col">
							服务商名称
						</th>
						<th width="15%" scope="col">
							服务商类型
						</th>
						<th width="10%" scope="col">
							联系固话
						</th>
						<th width="10%" scope="col">
							联系手机
						</th>
						<th width="10%" scope="col">
							主要负责人
						</th>
						<th width="8%" scope="col">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<c:if test="${!empty servicerList}">
						<c:forEach items="${servicerList}" var="ser" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">
									${index.count}
								</td>
								<td align="center">
									${ser.servname}
								</td>
								<td align="center">
									${ser.content1}
								</td>
								<td align="center">
									${ser.tele}
								</td>
								<td align="center">
									${ser.mobitele}
								</td>
								<td align="center">
									${ser.princ}
								</td>
								<td align="center" class="biao_lianj_1">
									<c:if test="${!empty menuIdMap && !empty menuIdMap['5007003002']}">
										<a href="javascript:void(0)" onclick="javascript:updateService(${ser.seq});">修改</a>
									</c:if>
									<c:if test="${!empty menuIdMap && !empty menuIdMap['5007003003']}">
										<a href="javascript:void(0)" onclick="javascript:deleteService(${ser.seq});">删除</a>
									</c:if>
									
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
				<c:if test="${!empty servicerList}" >
				<table width="98%" border="0" class="gai_left_duiqi">
					<tr>
						<td class="biaoge_fanye" align="right">
							<%--从tag中获取翻页的信息,从这开始--%>
							<sktag:paginator showTotal="true" showAllPages="true"
								strUnit="条记录" />
							<%--从tag中获取翻页的信息,到这结束--%>
						</td>
					</tr>
				</table>
			</c:if>
		
		</div>
		<!--页面结束 -->
	</body>
</html>
