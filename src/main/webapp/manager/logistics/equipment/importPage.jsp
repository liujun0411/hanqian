<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>导入</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
<link href="manager/css/message.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="manager/js/ceng.js"></script>
<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
<script type="text/javascript" src="manager/js/xialakuang.js"></script>
<!-- script src="manager/js/selectEL.js" type="text/javascript"></script> -->
<!-- 引入公用控件 -->
<script type="text/javascript"
	src="manager/common/equip/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
<link href="manager/common/equip/css/ligerui-all.css" rel="stylesheet"
	type="text/css" />
<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
<script src="manager/common/equip/js/ligerComboBox.js"
	type="text/javascript"></script>
<script src="manager/common/equip/js/ligerResizable.js"
	type="text/javascript"></script>
<script src="manager/common/equip/js/ligerTree.js"
	type="text/javascript"></script>
<script type="text/javascript" src="manager/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="manager/javascript/buildStorey/buildStorey.js"></script>
<script src="manager/common/equip/js/createTree.js"
	type="text/javascript"></script>

<script type="text/javascript">
	function deletePoint(eqid, projectPoint) {
		if (confirm("删除后不可恢复，确定删除此条信息？")) {
			location.href = "importExcle_deletePoint.action?eqid=" + eqid
					+ "&projectPoint=" + projectPoint;
		}
	}

	//设备类型菜单回调函数
	function doClick(note) {
		$('#equipTypeId').val(note.data.text);
		$('#eqTypeId').val(note.data.id);
		$("#listDiv_List").hide();
	}
	function formCheck(currentPage) {
		var form1 = document.getElementById("myForm001");
		form1.action = "importExcle_toImportExcle.action"; //设置提交路径
		if (currentPage != "") {
			$("#currentPage").val(currentPage);
		}
		form1.submit();
	}
	function outPutExcle(sta) {
		var form1 = document.getElementById("myForm001");
		if (sta == "excle") {
			form1.action = "importExcle_outPutExcle.action"; //设置提交路径
		} else {
			form1.action = "importExcle_outPutSDCDExcle.action"; //设置提交路径
		}
		form1.submit();
	}
	function toUpload() {
		var filepath = $("#file001").val();
		if (filepath.length > 0) {
			var checkName = filepath.split(".");
			if (checkName[checkName.length - 1] == "xls") {
				showdiv();
				var form1 = document.getElementById("myForm001");
				form1.action = "importExcle_uploadFile.action"; //设置提交路径
				form1.submit();
			} else {
				hidediv();
				alert("请选择正确的EXCLE文件！");
			}
		} else {
			alert("文件未选择！");
		}
	}
	function showdiv() {
		document.getElementById("bg").style.display = "block";
		document.getElementById("show").style.display = "block";
	}
	function hidediv() {
		document.getElementById("bg").style.display = 'none';
		document.getElementById("show").style.display = 'none';
	}
	function toAdd() {
		alert("添加功能，敬请期待！");
	}
</script>
<style type="text/css">
#bg {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.7;
	opacity: .70;
	filter: alpha(opacity = 70);
}

#show {
	display: none;
	position: absolute;
	top: 20%;
	left: 45%;
	padding: 8px;
	border: 0px solid #E8E9F7;
	z-index: 1002;
	overflow: auto;
}
</style>
</head>
<body>
	<div id="bg"></div>
	<div id="show">
		<h1>文件上传中。。。</h1>
	</div>
	<div id="admin_nr_rightg">
		<div class="canshusz_btbg_1">
			<table width="100%" border="0">
				<tr style="height: 30px">
					<td width="34" align="center"><img
						src="manager/images/imgs/canshushezhi_dianntu_1.png" /></td>
					<td class="biaoti_zt_canshusz">初始信息</td>
				</tr>
			</table>
		</div>
		<form action="importExcle_uploadFile.action" method="POST"
			name="myForm001" id="myForm001" enctype="multipart/form-data">
			<form action="equipment_findEquipmentByPage.action" method="post"
				name="myform" id="myform">
				<input type="hidden" name="currentPage" id="currentPage"
					value="${currentPage}" />
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						<td class="shebeigl_inp_zt" align="right" width="90">楼宇名称：</td>
						<td width="80" align="left"><input type="text"
							name="buildingName" value="${buildingName}" /></td>
						<td width='90' align='right' class='shebeigl_inp_zt'>设备名称：</td>
						<td width="80" align="left"><input type="text"
							name="equipName" id="equipName" value="${equipName}" /></td>
						<td width='90' align='right' class='shebeigl_inp_zt'>设备分级：</td>
						<td width="80" align="left"><select name="nodeLevel"
							id="nodeLevel" style="width: 174px;">
								<option value="" <c:if test="${nodeLevel eq ''}">selected</c:if>>--请选择--</option>
								<option <c:if test="${nodeLevel eq '1'}">selected</c:if>
									value="1">一级</option>
								<option <c:if test="${nodeLevel eq '2'}">selected</c:if>
									value="2">二级</option>
								<option <c:if test="${nodeLevel eq '3'}">selected</c:if>
									value="3">三级</option>
						</select></td>
						<td width='90' align='right' class='shebeigl_inp_zt'>设备类型：</td>
						<td width="80" align="left"><input type="text"
							name="equipTypeName" id="equipTypeId" value="${equipTypeName }"
							readonly="readonly" onclick="creatediv(this);" /> <input
							type="hidden" name="equipTypeId" id="eqTypeId"
							value="${equipTypeId}" /></td>
					</tr>
					<tr>
						<td class="shebeigl_inp_zt" align="right" width="90">点位名称：</td>
						<td width="80" align="right"><input type="text"
							name="pointName" id="pointName" value="${pointName}" /></td>
						<td width='90' align='right' class='shebeigl_inp_zt'>点位编码：</td>
						<td width="80" align="left"><input type="text"
							name="pointCode" id="pointCode" value="${pointCode }" /></td>
						<td width='90' align='right' class='shebeigl_inp_zt'>是否监控：</td>
						<td width="80" align="left"><select name="controlFlag"
							id="controlFlag" style="width: 174px;">
								<option value=""
									<c:if test="${controlFlag eq ''}">selected</c:if>>--请选择--</option>
								<option <c:if test="${controlFlag eq '1'}">selected</c:if>
									value="1">是</option>
								<option <c:if test="${controlFlag eq '0'}">selected</c:if>
									value="0">否</option>
						</select></td>
						<td width='90' align='right' class='shebeigl_inp_zt'>报警级别：</td>
						<td width="80" align="left"><select name="alertLevel"
							id="alertLevel" style="width: 174px;">
								<option <c:if test="${alertLevel eq ''}">selected</c:if>
									value="">--请选择--</option>
								<option <c:if test="${alertLevel eq '0'}">selected</c:if>
									value="0">非报警</option>
								<option <c:if test="${alertLevel eq '1'}">selected</c:if>
									value="1">一级</option>
								<option <c:if test="${alertLevel eq '2'}">selected</c:if>
									value="2">二级</option>
								<option <c:if test="${alertLevel eq '3'}">selected</c:if>
									value="3">三级</option>
						</select></td>

					</tr>
					<tr>
						<td colspan="8" align="right"><c:if
								test="${!empty menuIdMap && !empty menuIdMap['5012001']}">
								<span class="admin_clor_f00">${improtMsg}${improtMessage}</span>
								<input type="file" name="fileupload" id="file001" />
								<input type="button" onclick="toUpload()" value="导入初始信息" />&nbsp;&nbsp;&nbsp;&nbsp;<input
									type="button" value="导出初始信息" onclick="outPutExcle('excle')"
									id="emportExcle" />&nbsp;&nbsp;&nbsp;&nbsp;<!-- <input type="button" value="导出SDCD"
							onclick="outPutExcle('sdcd')" id="emportExcle1" /> -->
							</c:if><img style='cursor: pointer'
							src="manager/images/imgs/shebeigl_sousuo_an.png"
							onclick="javascript:formCheck('1')"></img>
						<!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:toAdd()" class="btn blue" align="left">添加</a> -->
						</td>
					</tr>
				</table>
			</form>
			<!-- </form> -->
			<div id="divShowDetail" style="height: 800px; width: 100%;">
				<table width="100%" border="0" cellspacing="1"
					class="biaoge_bg1 gai_left_duiqi">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<th width="8%" scope="col">楼宇名称</th>
							<th width="8%" scope="col">设备名称</th>
							<th width="8%" scope="col">设备编号</th>
							<th width="8%" scope="col">设备类型</th>
							<th width="8%" scope="col">安装位置</th>
							<th width="6%" scope="col">是否监控</th>
							<th width="6%" scope="col">设备分级</th>
							<th width="8%" scope="col">上级设备</th>
							<th width="8%" scope="col">设备分组</th>
							<th width="8%" scope="col">服务区域</th>
							<th width="6%" scope="col">点位编码</th>
							<th width="6%" scope="col">点位名称</th>
							<th width="6%" scope="col">报警级别</th>
							<th width="6%" scope="col">操作</th>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:if test="${!empty pointList}">
							<c:forEach items="${pointList}" var="pointList" varStatus="idx">
								<tr class="biaog_kan2 biaog_zt2">
									<td align="center">${pointList.BUILDING_NAME}</td>
									<td align="center">${pointList.EQUIP_NAME}</td>
									<td align="center" class="biao_lianj_1">
										${pointList.EQUIP_CODE }</td>
									<td align="center">${pointList.eqTypeName }</td>
									<td align="center">${pointList.PLACE }</td>
									<td align="center"><c:choose>
											<c:when test="${pointList.CONTROL_FLAG eq '1' }">是</c:when>
											<c:otherwise>否</c:otherwise>
										</c:choose></td>
									<td align="center"><c:choose>
											<c:when test="${pointList.NODE_LEVEL eq '1' }">一级</c:when>
											<c:when test="${pointList.NODE_LEVEL eq '2' }">二级</c:when>
											<c:when test="${pointList.NODE_LEVEL eq '3' }">三级</c:when>
											<c:otherwise></c:otherwise>
										</c:choose></td>
									<td align="center">${pointList.eqpName  }</td>
									<td align="center">${pointList.GROUP_NAME  }</td>
									<td align="center">${pointList.COMMENTS  }</td>
									<td align="center">${pointList.PROJECT_POINT  }</td>
									<td align="center">${pointList.POINT_NAME  }</td>
									<td align="center">${pointList.ALERT_LEVEL  }</td>
									<td align="center"><c:if
											test="${!empty menuIdMap && !empty menuIdMap['5012001']}">
											<a
												href="javascript:deletePoint('${pointList.EQUIP_ID}','${pointList.PROJECT_POINT}')">删除</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${!empty pointList}">
					<table width="100%" border="0" class="gai_left_duiqi">
						<tr style="height: 10px;">
							<td width="35%"></td>
							<td width="65%"></td>
						</tr>
						<tr style="height: 30px;">
							<td colspan="5"><sktag:paginator showTotal="true"
									showAllPages="true" strUnit="条记录" /></td>
						</tr>
					</table>
				</c:if>
				<c:if test="${!empty mesg}">
					<table width="100%" style="height: 10px; float: left;">
						<tr>
							<td><span class="admin_clor_f00">${ mesg}</span></td>
						</tr>
					</table>
				</c:if>
			</div>
		</form>
		<div id="divShowDetail" style="height: 800px; width: 100%;"></div>
		<!--页面结束 -->
		<iframe id="ifremeShowDetail" width="100%" frameborder="0"
			scrolling="no"
			style="overflow-x: hidden; overflow-y: auto; border: none;"
			height="800px;" src=""></iframe>
		<!-- 新短消息提示 -->
		<div id="tip">
			<h1>
				<a href="javascript:void(0)" onclick="start()">×</a>提示信息
			</h1>
			<div class="tipmsg">
				您有 <font color="#FF0000">1</font>台设备将于7天后需要保养 <br /> <a
					href="maintenance_findServeMaintenance.action">马上查看</a>
			</div>
		</div>
	</div>

</body>

</html>