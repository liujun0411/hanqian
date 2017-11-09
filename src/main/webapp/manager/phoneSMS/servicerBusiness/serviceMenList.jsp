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
var deleteMen = function(id){
	var isdel=window.confirm('确认要删除吗？');

	if(isdel){
		$("#seq").val(id);
		$("#subForm").attr("action","sermainDetail_deleteMen.action");
		$("#subForm").submit();
	}

}


//修改or新增
var updateMen = function(id){
	if(id!=""&&id&&id!=null&&id != undefined&&id != 'undefined' ){
		$("#editFlag").val("update");
	}else{
		$("#editFlag").val("add");
	}
	$("#seq").val(id);
	$("#subForm").attr("action","sermainDetail_updateBeforeMen.action");
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
							维修人员管理——维修人员列表
						</td>
					</tr>
				</table>
			</div>
			<form id="subForm" name="subForm" action="sermainDetail_findMenList.action">
				<input type="hidden" name="currentPage" value="${currentPage}"
					id="currentPage" />
					<input type="hidden" name="editFlag" value=""
					id="editFlag" />
					<input type="hidden" name="yesOrNo"  value="${yesOrNo }"
					id="yesOrNo" />
					<input type="hidden" name="seq" value="${tempId}"
					id="seq" />
					<span style="color:red">
						${message}
					</span>
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi"
					width="100%" align="right" style="float: right;">
					<tr>
						<td>
							<span style="font-weight: normal; font-size: 12px"
								class="admin_clor_f00"></span>
						</td>
						<td  width="110" class="shebeigl_inp_zt">
							<span>维修人员名称：</span>
						</td>
						<td  width="100">
							<input id="menName" name="menName" class="myfont" value="${menName }"/>
						</td>
						<td  width="90" class="shebeigl_inp_zt">
							<span>所属服务商：</span>
						</td>
						<td  width="100">
							<select id="serId" name="serId" class="myfont" >
								<option value=""> --请选择服务商--</option>
								<c:forEach items="${serviceList}" var="sl">
									<option value="${sl.seq }" ${serId== sl.seq?'selected':''}>${sl.servname }</option>
								</c:forEach>
							</select>
						</td>
						<td  width="90" class="shebeigl_inp_zt">
							<span>所属班组：</span>
						</td>
						<td  width="100">
							<select id="mainId" name="mainId" class="myfont" >
								<option value=""> --请选择班组--</option>
								<c:forEach items="${classList}" var="cl">
									<option value="${cl.seq }" ${mainId== cl.seq?'selected':''}>${cl.content1 }</option>
								</c:forEach>
							</select>
						</td>
						<td  width="4">
							<img onclick="formCheck();"	src="manager/images/imgs/shebeigl_sousuo_an.png"
								style="cursor: pointer"/>
						</td>
						<td >
							<c:if test="${!empty menuIdMap && !empty menuIdMap['5007002001']}">
								<a href="javascript:void(0)" class="btn blue" id="addBtnA" onclick="updateMen()">添加</a>
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
						<th width="10%" scope="col">
							维修人员名称
						</th>
						<th width="15%" scope="col">
							所属服务商
						</th>
						<th width="15%" scope="col">
							所属班组
						</th>
						<th width="10%" scope="col">
							联系手机
						</th>
						<th width="10%" scope="col">
							联系固话
						</th>
						<th width="8%" scope="col">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<c:if test="${!empty menList}">
						<c:forEach items="${menList}" var="men" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">
									${index.count}
								</td>
								<td align="center">
									${men.mem_name}
								</td>
								<td align="center">
									${men.servname}
								</td>
								<td align="center">
									${men.content1}
								</td>
								<td align="center">
									${men.mem_mobtele}
								</td>
								<td align="center">
									${men.mem_tele}
								</td>
								<td align="center" class="biao_lianj_1">
									<c:if test="${!empty menuIdMap && !empty menuIdMap['5007002002']}">
										<a href="javascript:void(0)" onclick="javascript:updateMen(${men.seq});">修改</a>
									</c:if>
									<c:if test="${!empty menuIdMap && !empty menuIdMap['5007002003']}">
										<a href="javascript:void(0)" onclick="javascript:deleteMen(${men.seq});">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
				<c:if test="${!empty menList}" >
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
	<script type="text/javascript">
			$(document).ready(function(){
			var tempStatus = $("#yesOrNo").val();
			if(tempStatus=="no"){
				var isdel=window.confirm('删除后不再下方短信至该人员，确认要删除吗？');
				if(isdel){
					$("#yesOrNo").val("1");
					$("#subForm").attr("action","sermainDetail_deleteMen.action");
					$("#subForm").submit();
				}
			}
		});
	</script>
</html>
