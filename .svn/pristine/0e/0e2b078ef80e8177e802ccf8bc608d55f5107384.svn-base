<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<title>权限管理- ${empty update?'权限添加':'权限修改'}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/My97DatePicker/WdatePicker.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/jquery.validate.js">
</script>

		<script type="text/javascript"
			src="manager/common/equip/js/jquery-1.3.2.min.js">
</script>
		<link href="manager/common/equip/css/ligerui-all.css" rel="stylesheet"
			type="text/css" />
		<script src="manager/common/equip/js/base.js" type="text/javascript">
</script>
		<script src="manager/common/equip/js/ligerComboBox.js"
			type="text/javascript">
</script>
		<script src="manager/common/equip/js/ligerResizable.js"
			type="text/javascript">
</script>
		<script src="manager/common/equip/js/ligerTree.js"
			type="text/javascript">
</script>
		<script src="manager/common/equip/js/createTree.js"
			type="text/javascript">
</script>

		<script type="text/javascript">

function checkSubmit() {

	if ($("#parentId").val() == '') {
		alert('请选择父级权限名称!');
		$("#name").focus();
		return false;
	}
	if ($("#name").val() == '') {
		alert('请填写权限名称!');
		$("#name").focus();
		return false;
	}

	return true;
}

function updateSubmit() {
	var check = checkSubmit();
	if (check == false) {
		return false;
	}
	var form = $("#myform");
	form.attr("action", "role!updateRole.action");
	form.submit();
}

function addUserCheck() {
	var check = checkSubmit();
	if (check == true) {
		//提交表单
		document.forms[0].submit();
	}
}

function updateDeptCheck() {
	var check = checkSubmit();
	if (check == true) {
		var form = $("#myform");
		form.attr("action", "menu!updateMenus.action");
		form.submit();
	}
}

function creatediv2(obj) {
	if ($("#listDiv_List").length == 0) {
		var _left = parseInt($(obj).offset().left - 75);
		var _top = parseInt($(obj).offset().top + 5);
		var _width = parseInt($(obj).width()) / 2;
		var _height = parseInt($(obj).height());
		var str = new Array();
		var _divWidth = _width * 2 + 5;
		str.push('<div id="listDiv_List"');
		str
				.push('style="overflow-x:auto;overflow-y:auto;background-color:white; border:1px solid #0193ce;width:'
						+ _divWidth
						+ 'px;height:300px;position:absolute;left:'
						+ (_left + _width + 1)
						+ 'px;top:'
						+ (_top + _height + 2) + 'px;">');
		str.push('<ul id="tree1">');
		str.push($('#aa').html());
		str.push('</ul>');
		str.push('</div>');
		$("body").append(str.join(""));
		$("#tree1").ligerTree( {
			checkbox : false,
			parentIcon : null,
			childIcon : null
		});
		//$("#tree1").ligerTree({ url: 'manager/common/equip/json/prov4.json',checkbox: false,onClick:doClick});
		$('#tree1 li span').click(function() {
			$("#parentName").val($(this).text());
			$("#parentId").val($(this).parent().parent().attr('id'));
			$("#listDiv_List").hide();
		});
	} else {
		$("#listDiv_List").show();
	}
}
</script>
	</head>

	<body>
		<div id="aa" style="display: none;">
			${menuParent}
		</div>
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="780" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							权限管理—— ${empty update?'权限添加':'权限修改'}
						</td>
					</tr>
				</table>
			</div>
			<s:form action="menu!addMenus.action" method="post" id="myform"
				theme="simple" name="">
				<input type="hidden" name="dbMenus.${empty update?'oper':'updater'}"
					value="${empty sessionScope.users?'':sessionScope.users.seq}" />
				<input type="hidden" name="dbMenus.menuId" value="${dbMenus.menuId}" />


				<table width="770" border="0" cellspacing="1" class="gai_left_duiqi">
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f admin_zt_14">
							父级权限
							<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="parentName" name="test"
								value="${!empty dbMenus.dbMenus?dbMenus.dbMenus.name:''}"
								onclick="creatediv2(this)" readonly="readonly" />
							<input type="hidden" id="parentId" name="dbMenus.dbMenus.menuId"
								value="${!empty dbMenus.dbMenus?dbMenus.dbMenus.menuId:''}" />
						</td>
						<td class="admin_bgclor_e3f admin_zt_14">
							权限名称
							<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="name" name="dbMenus.name"
								value="${dbMenus.name}" />
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f admin_zt_14">
							权限URL
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="menuUrl" name="dbMenus.menuUrl"
								value="${dbMenus.menuUrl}" />

						</td>
						<td class="admin_bgclor_e3f admin_zt_14">
							权限等级
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="principal" name="dbMenus.principal"
								value="${dbMenus.menuLevel}" />
						</td>
					</tr>
					<tr>
						<td class="admin_bgclor_e3f admin_zt_14">
							使用期限
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="dueDate" name="dbMenus.dueDate"
								readonly="readonly"
								value='<fmt:formatDate value="${dbMenus.dueDate}" pattern="yyyy-MM-dd"/>'
								class="Wdate"
								onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" />

						</td>
						<td class="admin_bgclor_e3f admin_zt_14">
							描述
						</td>
											<td class="admin_bgclor_f1f">
							<input type="text" id="depict" name="dbMenus.depict"
								value="${dbMenus.depict}" />

						</td>
					</tr>
					<tr>
						<td class="admin_bgclor_e3f admin_zt_14">
							备注
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="remarks" name="dbMenus.remarks"
								value="${dbMenus.remarks}" />
						</td>

						<td class="admin_bgclor_e3f admin_zt_14">
				
						</td>
						<td class="admin_bgclor_f1f">

						</td>
					</tr>
				</table>
				<table width="770" border="0" class="gai_left_duiqi">
					<tr>
						<td></td>
						<td width="60">
							<!--a href="javascript:f_check();" class="btn blue">下一步</a-->
							<td width="60" align="left">
								<c:if test="${empty update}">
									<a onclick="addUserCheck();" class="btn blue">添 加</a>
								</c:if>
								<c:if test="${!empty update}">
									<a onclick="updateDeptCheck();" class="btn blue">修 改</a>
								</c:if>
							</td>
						</td>
						<td width="65">
							<a href="javascript:history.go(-1);" class="btn blue">取 消</a>
						</td>
					</tr>
				</table>
			</s:form>
	</body>
</html>
