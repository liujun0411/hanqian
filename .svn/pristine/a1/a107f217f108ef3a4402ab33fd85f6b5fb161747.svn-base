<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			type="text/javascript"></script>
		<base href="<%=basePath%>" />
<!-- 引入公用控件 -->
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

<c:if test="${!empty eqtypeList}">
	<script language="JavaScript">

//菜单项
node = function(id, name, pid) {
	this.id = id;
	this.name = name;
	this.pid = pid;
};

//菜单
menu = function() {
	this.nodes = [];
}

//添加菜单项
menu.prototype.add = function(id, name, pid) {
	this.nodes[this.nodes.length] = new node(id, name, pid);
}

//查询子菜单
<%--menu.prototype.findmenu = function(pid) {--%>
<%--	var rnodes = [];--%>
<%--	for ( var i = 0; i < this.nodes.length; i++) {--%>
<%--		if (this.nodes[i].pid == pid) {--%>
<%--			rnodes[rnodes.length] = this.nodes[i];--%>
<%--		}--%>
<%--	}--%>
<%--	return rnodes;--%>
<%--}--%>
//取对象
g = function(id) {
	return document.getElementById(id);
}

//更新二级菜单
<%--getsubmenu = function(pid) {--%>
<%--	alert("更新二级菜单")--%>
<%--	g('subtype').innerHTML = '';--%>
<%--	seteqtypeid(pid);--%>
<%----%>
<%--	if (pid != null && pid != '') {--%>
<%--		alert("更新二级菜单------")--%>
<%--		var rnodes = eqtypes.findmenu(pid);--%>
<%--		if (rnodes.length > 0) {--%>
<%--			alert("更新二级菜单=======")--%>
<%--			var str = '<select id="stype" class="myfont" onchange="seteqtypeid(this.value,\'' + pid + '\');">';--%>
<%--			str += '<option >--请选择--</option>';--%>
<%--			for ( var i = 0; i < rnodes.length; i++) {--%>
<%--				str += '<option value="' + rnodes[i].id + '">' + rnodes[i].name--%>
<%--						+ '</option>';--%>
<%--			}--%>
<%--			str += '</select>';--%>
<%--			g('subtype').innerHTML = str;--%>
<%--		}--%>
<%--	}--%>
<%--}--%>

//选中类型
seteqtypeid = function(id, pid) {
	if (pid != '' && pid != null && (id == '' || id == null)) {
		id = pid
	}
	g('eqtypeid').value = id;

	gotoEqList(id);
}

//更新设备列表
gotoEqList = function(typeid) {
	if (typeid != null && typeid != '') {
		g("eqiframe").src = "manager/keyEqAction_showEqList.action?eqtype=" + typeid;
	} else {
		g("eqiframe").src = '';
	}
}
var eqtypes = new menu();

 //设备类型菜单回调函数
		   function doClick(note){
		      $('#equipTypeId').val(note.data.text);
		      $('#eqTypeId').val(note.data.id); 
		      $("#listDiv_List").hide();
		      gotoEqList(note.data.id);
		   }
</script>
	<style type="text/css">
.myfont {
	font-size: 12px;
}
</style>
</head>
	<body style="font-size:12px;">
		<c:forEach items="${eqtypeList}" var="obj">
			<script type="text/javascript">
eqtypes.add('${obj.equiptypeid}', '${obj.equiptypename}',
		'${obj.equiptypeparentid }');
</script>
		</c:forEach>




		<!-- 设备类型选择 -->
		<input type="hidden" id="eqtypeid" />
		<div
			style="padding: 6px 0px 6px 2px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #aac9ea; height: 20px;">
			<span class="myfont">设备类型:</span>

						<td width="80" align="left">
							<input type="text" name="equipTypeId" id="equipTypeId"
								value="${dbEquipList.dbEquipType.typeName }" readonly="readonly"
								onclick="creatediv(this);" />
							<input type="hidden" name="dbEquipList.dbEquipType.equipTypeId"
								id="eqTypeId" value="${dbEquipList.dbEquipType.equipTypeId }" />
						</td>



<%--			<select id="sle" class="myfont" onchange="getsubmenu(this.value);">--%>
<%--				<option>--%>
<%--					--请选择----%>
<%--				</option>--%>
<%--				<script type="text/javascript">--%>
<%--var rnodes = eqtypes.findmenu('');--%>
<%----%>
<%--for ( var i = 0; i < rnodes.length; i++) {--%>
<%--	document.write('<option value="' + rnodes[i].id + '">' + rnodes[i].name--%>
<%--			+ '</option>');--%>
<%--}--%>
<%--</script>--%>
<%--			</select>--%>





			<span id="subtype"></span>
		</div>

		<!-- 设备列表 -->
		<div>
			<iframe src="" scrolling="no" marginwidth="0" marginheight="0"
				width="100%" height="246" frameborder="0" name="eqiframe" id="eqiframe"></iframe>
		</div>
	</body>
</c:if>
</html>
