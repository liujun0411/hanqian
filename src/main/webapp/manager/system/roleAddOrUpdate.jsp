<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>角色管理- ${empty update?'角色添加':'角色修改'}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="manager/system/style.css" rel="stylesheet" type="text/css">
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script src="manager/js/My97DatePicker/WdatePicker.js"
			type="text/javascript">
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


		<style type="text/css">
/*查询表单样式*/
.q_table {
	width: 100%;
	border-collapse: collapse;
	border-top: solid 1px #666;
	border-bottom: solid 0px #666;
}

.q_table th {
	font-size: 12px;
	font-weight: normal;
	border-bottom: solid 1px #8c8c8c;
	background-color: #6EC2FD;
	font-weight: 400;
	font-size: 13px;
	color: #000;
	height: 30px;
	width: 15%;
}

.q_table td {
	background-color: #eeeeff;
	border-bottom: solid 1px #8c8c8c;
	border-left: solid 1px #fff;
	width: 18%;
}
</style>


		<script type="text/javascript">
function allch(code) {
	var temp = document.getElementById(code);
	var rs = document.getElementsByName("sysRight");
	if (temp.checked) {
		for ( var i = 0; i < rs.length; i++) {
			if (rs[i].id.substring(0, 3) == code) {
				rs[i].checked = true;
			}
		}
	} else {
		for ( var i = 0; i < rs.length; i++) {
			if (rs[i].id.substring(0, 3) == code) {
				rs[i].checked = false;
			}
		}
	}
}
function selSup1(code, parentId, id) {
	var temps = document.getElementById(code);
	if (temps.checked) {
		var sup = document.getElementById(parentId);
		sup.checked = true;
		var sup1 = document.getElementById(id);

		sup1.checked = true;
	}
}
function selSup(code, parentId) {
	var temps = document.getElementById(code);
	if (temps.checked) {
		var sup = document.getElementById(parentId);
		sup.checked = true;
	}
}

function checkSubmit() {
	var name = document.getElementById("name");
	var roleLevel = document.getElementById("roleLevel");
	var depict = document.getElementById("depict");

	if (name.value == '') {
		alert('请填写角色名称!');
		name.focus();
		return false;
	}

	if ($(":radio[name=dbRoles.status]:checked").length == 0) {
		alert('请选择是否显示!');
		status.focus();
		return false;
	}

	if (depict.value == null || depict.value == '') {
		alert('请填写角色描述!');
		depict.focus();
		return false;
	}
	if ($(":checkbox[name=menuId]:checked").length == 0) {
		//alert('请至少选中一个菜单权限!');
		//return false;
	}
	return true;
}

function insertSubmit() {
	var check = checkSubmit();
	if (check == false) {
		return false;
	}
	var form = $("#myform");
	form.attr("action", "role!addRole.action");
	form.submit();
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

function checkAll(my) {
	$('input[type=checkbox]').attr("checked", my.checked);
}
$(function() {
	$("#tree1").ligerTree( {
		checkbox : true,
		parentIcon : null,
		childIcon : null
	});
});

function test1(obj) {

	var id = $(obj).attr("id");
	var ids = id.split("-");

	if (ids.length >= 2) {
		var temp = '';
		for ( var i = 0; i < ids.length - 1; i++) {
			if (i > 0) {
				temp = temp + '-' + ids[i];
			} else {
				temp = temp + ids[i];
			}
			$("input:checkbox[id=" + temp + "]").attr("checked", true);
		}
	}

	var box = $("input:checkbox[id^=" + id + "]").attr("checked", obj.checked);

}
</script>
	</head>

	<body>
		<div id="admin_nr_rightg"
			style="height: 980px; overflow: auto; overflow-x: hidden;">
			<div class="canshusz_btbg_1">
				<table width="780" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							角色权限—— ${empty update?'角色添加':'角色修改'}
						</td>
					</tr>
				</table>
			</div>
			<br>
			<br>
			<div style="border: solid 1px #666; widht: 100%; height: 58px;">
				<form action="role!addRole.action" method="post" id="myform">
					<input type="hidden" name="sign" value="RightUpdate" />
					<input type="hidden" name="dbRoles.roleId" id="roleId"
						value="${dbRoles.roleId}" />

					<table class="q_table">
						<tr>

							<th>
								角色名
							</th>
							<td>
								<input type="text" id="name" name="dbRoles.name"
									value="${dbRoles.name}" />
							</td>

							<th>
								角色描述
							</th>
							<td>
								<input type="text" id="depict" name="dbRoles.depict"
									value="${dbRoles.depict}" />
							</td>
							<th>
								显示
							</th>
							<td>
								<input type="radio" id="status" name="dbRoles.status"
									${dbRoles.status==0? 'checked':''} value="0" />
								显示
								<input type="radio" id="status" name="dbRoles.status"
									${dbRoles.status==1? 'checked':''} value="1" />
								不显示
							</td>
						</tr>
						<tr>

							<td colspan="5">
							</td>
							<td align="center">
								<c:if test="${empty update}">
									<input type="button" onclick="insertSubmit();"
										styleClass="common_button" value="保存" />
								</c:if>
								<c:if test="${!empty update}">
									<input type="button" onclick="updateSubmit();"
										styleClass="common_button" value="修改" />
								</c:if>


								<input type=reset styleClass="common_button"
									onclick="javascript:history.go(-1);" value="取消">
							</td>

						</tr>
					</table>
			</div>
			<br />
			<!-- 
				<div id="aa" style="width: 500px ">
					<ul id="tree1" style="width: 300px">
						${menuParent}
	                </ul>
				</div>
				 -->
				 
			<!-- add start  2013.4.10 by  zhangdiannan
				    将确认按钮固定在最上方 将内容固定显示在固定区域加入滚动条-->
		<div
			style="overflow-x: auto; overflow-y: auto; height: 100px; width: 200px; width: 100%; height: 80%; border: solid 1px #666">
			<!-- add end 2013.4.10 by zhangdiannan -->
				<table class="query_form_table">
					<div style="background-color: #CCCCCC;" align="right">
						<font>全选</font>
						<input type="checkbox" onclick="checkAll(this);">
					</div>
					<c:forEach items="${menuList}" var="m" varStatus="count">
						<c:if test="${m.status!=2 and m.name!=null}">
							<tr style="border-collapse: collapse; right: 300px">
								<td>
									<div style="background-color: #CCCCCC;">
										<font style="font-size: 20px;"> ${m.name}</font>
										<input type="checkbox" name="menuCode" value="${m.menuCode}"
											id="${m.menuCode}" onclick="test1(this);"${!empty menIdMap[m.menuCode]?'checked':''}>
									</div>

									<c:forEach items="${m.dbMenuses}" var="m1" varStatus="count1">
										<c:if test="${m1.status!=2 and m1.name!=null}">
											<div>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<font style="font-size: 15px;">${m1.name}</font>
												<input type="checkbox" id="${m.menuCode}-${m1.menuCode}"
													value="${m1.menuCode}" name="menuCode"
													onclick="test1(this);"${!empty menIdMap[m1.menuCode]?'checked':''}>
											</div>
											<div>
												<table border="0">
													<tr>
														<c:forEach items="${m1.dbMenuses}" var="m2"
															varStatus="count1">
															<c:if test="${m2.status!=2 and m2.name!=null}">
																<td style="width: 160"></td>	
											${(count1.count-1)%3==0?'</tr><tr> <td style="width: 160;"  ></td>':''} 
											
											<td style="width: 160;" align="left">
																	${m2.name} <input type="checkbox" name="menuCode"
																	id="${m.menuCode}-${m1.menuCode}-${m2.menuCode}"
																	value="${m2.menuCode}" onclick="test1(this);" ${!empty
																	menIdMap[m2.menuCode]?'checked':''} >
																</td>


																<td style="width: 160;"></td>
																<td style="width: 160;"></td>
																<c:forEach items="${m2.dbMenuses}" var="m3"
																	varStatus="count2">
																	<c:if test="${m3.status!=2 and m3.name!=null}">
													</tr>
													<tr>
														<td style="width: 160;"></td>
														<td style="width: 180;"></td>

														<td style="width: 160;" align="left">
															${m3.name} <input type="checkbox" name="menuCode"
															id="${m.menuCode}-${m1.menuCode}-${m2.menuCode}-${m3.menuCode}"
															value="${m3.menuCode}" onclick="test1(this);" ${!empty
															menIdMap[m3.menuCode]?'checked':''} >
														</td>

														<c:forEach items="${m3.dbMenuses}" var="m4"
															varStatus="count3">
															<c:if test="${m4.status!=2 and m4.name!=null}">	
															${(count3.count-1)%3==0?'</tr><tr> <td style="width: 160;"  ></td><td style="width: 160;" ></td><td style="width: 160;" ></td>':''} 
															
															
													
													</tr>
													<tr>
														<td style="width: 160;"></td>
														<td style="width: 160;"></td>
														<td style="width: 160;"></td>
														<td style="width: 160;" align="left">
															${m4.name} <input type="checkbox" name="menuCode"
															id="${m.menuCode}-${m1.menuCode}-${m2.menuCode}-${m3.menuCode}-${m4.menuCode}"
															value="${m4.menuCode}" onclick="test1(this);" ${!empty
															menIdMap[m4.menuCode]?'checked':''} >
														</td>
														</c:if>
														</c:forEach>
													</tr>
													<tr>
														</c:if>
														</c:forEach>
													</tr>
													</c:if>
													</c:forEach>
													</tr>
												</table>
											</div>
											<hr>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			<!-- add start  2013.4.10 by  zhangdiannan
				    将确认按钮固定在最上方 将内容固定显示在固定区域加入滚动条-->	
			</div>
			<!-- add start  2013.4.10 by  zhangdiannan
			     标签收尾-->
			</form>
	</body>
</html>
