<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="swtag" uri="/WEB-INF/swsoft-struts.tld"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>医院后勤智能化管理平台</title>
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
		<script language="JavaScript">
//表单提交
submyfrom = function(groupId, groupName) {
	document.getElementById("groupId").value = groupId;
	document.getElementById("groupName").value = groupName;
	document.getElementById("myform").action = "keyEqAction_showGroup.action";
	document.getElementById("myform").submit();
}

selgroupeq = function() {
	document.getElementById("wherestr").value = document
			.getElementById("mystr").value;
	document.getElementById("myform").action = "keyEqAction_showGroupList.action";
	document.getElementById("myform").submit();
}

//删除分组 
delgroup = function(groupid) {
	var myfrom = document.getElementById("myform");
	myfrom.action = "keyEqAction_deleteGroup.action";
	document.getElementById("groupId").value = groupid;

	document.getElementById("myform").submit();
}
</script>
		<style type="text/css">
<!--
.diveq {
	height: 30px;
	width: 120px;
	color: #3399FF;
	text-align: center;
	cursor: default;
}
-->
</style>
	</head>
	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">

		<!-- form 表单 -->
		<form method="post" action="keyEqAction_showGroup.action" name="myform"
			id="myform">
			<input type="hidden" id="groupName" name="groupName"
				value="${groupName}" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId}" />
			<input type="hidden" id="wherestr" name="wherestr"
				value="${wherestr}" />
		</form>
		<div id="admin_nr_rightg">
			<!-- 标题 -->
			<div class="canshusz_btbg_1">
				<table width="780" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							关键设备——关键设备组列表
						</td>
					</tr>
				</table>
			</div>
			<div>
				<table width="100%" border="0" align="right"
					class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr class="shebeigl_inp_zt">
						<td></td>
						<td width="330" align="right">
							查询条件:
							<input type="text" id="mystr" name="mystr" size="20"
								maxlength="10" value="${wherestr }" />
						</td>
						<td width="60">
							<img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="selgroupeq();"></img>
						</td>
						<td width="65">
							<c:if
								test="${!empty keyEqMenuMap && !empty keyEqMenuMap['5005002']}">
								<a href="javascript:void(0);" onclick="submyfrom(null,null);"
									class="btn blue">添加</a>
							</c:if>
						</td>
					</tr>
				</table>
				<span class="admin_clor_f00">${message}</span>
			</div>

			<!-- 设备组-->

			<c:if test="${!empty groupList}">
				<table width="100%" border="0" cellspacing="1" align="center"
					class="biaoge_bg1 gai_left_duiqi">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<th scope="col">
								序号
							</th>
							<th scope="col">
								组名称
							</th>
							<th scope="col">
								关键设备
							</th>
							<th colspan="2">
								操作
							</th>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:forEach items="${groupList}" var="obj" varStatus="at">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center" width="30">
									${obj.at}
								</td>
								<td align="center" width="80">
									${obj.groupName }
								</td>
								<td align="center" valign="middle" class="biao_lianj_1">
									<span> <c:forEach items="${obj.equipList}" var="eqobj"
											varStatus="idx">
											<c:if test="${idx.index!=0}">
											、
											</c:if>
											<c:set var="pPointName" value=""></c:set>
											<c:forEach items='${eqobj.points }' var='pointn'>
												<c:if test="${!empty pPointName}">
													<c:set var="pPointName"
														value="${pPointName}、${pointn.name} " />
												</c:if>
												<c:if test="${empty pPointName}">
													<c:set var="pPointName" value="${pointn.name} " />
												</c:if>
											</c:forEach>
											<span id="${eqobj.equipId }" class="diveq"
												title="	${pPointName}"> ${eqobj.name } </span>
										</c:forEach> </span>
								</td>
								<td width="50" align="center" class="biao_lianj_1">
									<c:if
										test="${!empty keyEqMenuMap && !empty keyEqMenuMap['5005003']}">
										<a href="javascript:void(0);"
											onclick="submyfrom('${obj.groupId}','${obj.groupName }');">
											修改</a>
									</c:if>
								</td>
								<td width="50" align="center" class="biao_lianj_1">
									<c:if
										test="${!empty keyEqMenuMap && !empty keyEqMenuMap['5005004']}">
										<a href="javascript:void(0);"
											onclick="var isdel=window.confirm('确认要删除[${obj.groupName }]关键设备组吗？');
										if(isdel){delgroup('${obj.groupId}');}">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<table width="100%" border="0" class="gai_left_duiqi">
					<tr>
						<td class="biaoge_fanye" align="right">
							<%--从tag中获取翻页的信息,从这开始--%>
							<swtag:paginator url="keyEqAction_showGroupList.action" showTotal="true"
								showAllPages="true" strUnit="条记录" />
							<%--从tag中获取翻页的信息,到这结束--%>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${empty groupList}">
				<script language="JavaScript">
alert("没有您所查询的数据！");<%--				<span class="admin_clor_f00 ">暂无数据！</span>--%>
				</script>
			</c:if>
		</div>
	</body>
</html>
