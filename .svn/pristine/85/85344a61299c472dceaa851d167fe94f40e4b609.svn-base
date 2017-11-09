<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
submyfrom = function(groupid, groupName, equipType, groupRemark,picName,buildId,groupCode) {
	document.getElementById("groupId").value = groupid;
	document.getElementById("groupName").value = groupName;
	document.getElementById("picName").value = picName;
	document.getElementById("equipType").value = equipType;
	document.getElementById("eBuildId").value = buildId;
	document.getElementById("groupCode").value = groupCode;
	//if (document.getElementById("groupRemark").value == "") {
		//document.getElementById("groupRemark").value = "";
	//} else {
		document.getElementById("groupRemark").value = groupRemark;
	//}
	
	document.getElementById("myform").action = "equipGroupAction!showGroupEdit.action";
	document.getElementById("myform").submit();
}

selgroupeq = function() {
	document.getElementById("wherestr").value = document.getElementById("mystr").value.replace(/(^\s*)|(\s*$)/g, "");
	document.getElementById("myform").action = "equipGroupAction!showEquipGroupList.action";
	document.getElementById("myform").submit();
}

//删除分组 
delgroup = function(groupId) {
	var myfrom = document.getElementById("myform");
	document.getElementById("groupId").value = groupId;
	myfrom.action = "equipGroupAction!deleteEquipGroup.action";
	document.getElementById("myform").submit();
}
function formCheck(currentPage) {
	if (currentPage != "" || currentPage != undefined) {
		$("#currentPage").val(currentPage);
	}
	document.myform.submit();
}
document.onkeydown=function(e){
    if(!e) e=window.event;
   if((e.keyCode || e.which) ==13){
	   selgroupeq();  
   }
}

</script>
		<style type="text/css">
.diveq {
	height: 30px;
	width: 120px;
	color: #3399FF;
	text-align: center;
	cursor: default;
	
}
</style>
	</head>
	<body style="overflow: auto; overflow-x: hidden; overflow-y: hidden;">


		<!-- form 表单 -->
		<form method="post" action="equipGroupAction!showEquipGroupList.action"
			name="myform" id="myform">
			<input type="hidden" id="groupId" name="groupId" />
			<input type="hidden" id="groupName" name="groupName" />
			<input type="hidden" id="picName" name="picName" />
			<input type="hidden" id="equipType" name="equipType" />
			<input type="hidden" id="wherestr" name="wherestr" />
			<input type="hidden" id="eBuildId" name="eBuildId" />
			<input type="hidden" id="groupCode" name="groupCode" />
			<input type="hidden" id="groupRemark" name="groupRemark" />
			<input type="hidden" id="flag" name="flag" value="select" />
			<input type="hidden" id="currentPage" name="currentPage"
				value="${page.currentPage}" />
		</form>
		<div id="admin_nr_rightg" style=" height:750px; overflow:auto; ">
			<!-- 标题 -->
			<div class="canshusz_btbg_1" style="width:98%;">
				<table width="780" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							设备分组——设备分组列表
						</td>
					</tr>
				</table>
			</div>

			<div>
				<table width="98%" border="0" align="right"
					class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr class="shebeigl_inp_zt">
						<td><span class="admin_clor_f00" style="font-weight: normal;font-size: 12px">${message}</span></td>
						<td width="330" align="right" >
							查询条件:
							<input type="text" id="mystr" name="mystr" size="20"
								maxlength="10" value="${wherestr }" />
						</td>
						<td width="60">
							<img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="selgroupeq();"></img>
						</td>
						<c:if test="${!empty menuMap && !empty menuMap['5006002']}">
							<td width="65">
								<a href="javascript:void(0);"
									onclick="submyfrom(null,null,null,null,null,'');" class="btn blue">添加</a>
							</td>
						</c:if>
					</tr>
				</table>
				
			</div>

			<!-- 设备组 -->
			<c:if test="${!empty equipGroupList}">
				<table width="98%" border="0" cellspacing="1" align="center"
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
								设备组中的设备
							</th>
							<th colspan="2">
								操作
							</th>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:forEach items="${equipGroupList}" var="obj" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center" width="30">
									${obj.index}
								</td>
								<td align="center" width="80">
									${obj.groupName }
								</td>
								<td align="center" valign="middle" class="biao_lianj_1">
									<span> <c:forEach items="${obj.equipList}" var="eqobj"
											varStatus="idx">
											<c:if test="${idx.index!=0}">、</c:if>
											<span id="${eqobj.equipName }" class="diveq">${eqobj.equipName}</span>
										</c:forEach> </span>
								</td>
								<c:if test="${!empty menuMap && !empty menuMap['5006003']}">
									<td width="50" align="center" class="biao_lianj_1">
										<a href="javascript:void(0);"
											onclick="submyfrom('${obj.seq}','${obj.groupName }','${obj.equipType }','${obj.groupRemark}','${obj.picName }','${obj.buildId }','${obj.groupCode}');">修
											改</a>
									</td>
								</c:if>
								<c:if test="${!empty menuMap && !empty menuMap['5006004']}">
									<td width="50" align="center" class="biao_lianj_1">
										<a href="javascript:void(0);"
											onclick="var isdel=window.confirm('确认要删除[${obj.groupName }]监控设备组吗？');if(isdel){delgroup('${obj.seq}');}">删
											除</a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
	</body>
</html>
