<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title></title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/reqi.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/jquery-tmpl-min.js">
</script>
		<script type="text/javascript" src="manager/js/ceng.js">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/xialakuang.js">
</script>
		<script src="manager/js/selectEL.js" type="text/javascript">
</script>
		<!-- 引入公用控件 -->
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
		<script type="text/javascript"
			src="manager/javascript/buildStorey/buildStorey.js">
</script>
		<script type="text/javascript">
function formCheck(currentPage) {
	if (currentPage != "" || currentPage != undefined) {
		$("#mpage").val(currentPage);
	}
	document.myform.submit();
}
formSubmit = function() {
	$("#myform").submit();
}

//设备类型菜单回调函数
function doClick(note) {
	$('#equipTypeId').val(note.data.text);
	$('#eqTypeId').val(note.data.id);
	$("#listDiv_List").hide();
}

$(function() {
	initDataFun('buildId', 'storey', '${voMaintenance.buildName}', '1');
	
	$(".deleM").click(function(event){
		var langstr = $(this).attr("lang");
		if(window.confirm('是否删除维护信息?')){
			window.location='maintenance_deleteMaintenance?mid='+langstr;
		}
        event.stopPropagation();
   });
	
});
//连接到设备信息
trClick = function(eqId) {
	$('#myform').attr( {
		action : "equipment_showDetail?equipId=" + eqId
	});
	document.myform.submit();
}

onload = function() {
	var showbdiv = "1";
	if (1 == showbdiv) {
		f_jilu();
	} else if (2 == showbdiv) {
		f_hetong;
	}
}

f_jilu=function() 
	{
	  document.getElementById("showJiLu").src="manager/images/imgs/weixiujilucai.png";
	  document.getElementById("showHeTong").src="manager/images/imgs/weixiuhetong.png";
	    $("#showWeiXiuDIV").show();
	    $("#showHeTongDIV").hide();
	}

f_hetong=function()
	{
	  document.getElementById("showJiLu").src="manager/images/imgs/weixiujilu.png";
	  document.getElementById("showHeTong").src="manager/images/imgs/weixiuhetongcai.png";
	     $("#showWeiXiuDIV").hide();
	    $("#showHeTongDIV").show();
	}

</script>
	</head>
	<body>
		<div id="admin_nr_jbxx">
			<c:if test="${empty headTop}">
				<div class="canshusz_btbg_1">
					<table width="100%" border="0">
						<tr style="height: 30px">
							<td width="34" align="center">
								<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
							</td>
							<td width="679" class="biaoti_zt_canshusz">
								维修巡检保养——设备维修记录查询
							</td>
						</tr>
					</table>
				</div>
			</c:if>
			
			<div class="admin_nr_right_g02">
				<table width="100%" border="0">
					<tr style="height: 25px;" valign="middle">
						<td width="2"></td>
						<td width="83" style="cursor: pointer">
							<img src="manager/images/imgs/weixiujilu.png " id="showJiLu"
								onclick="f_jilu()" />
						</td>
						<td width="4" valign="bottom">
							<img src="manager/images/imgs/gaixian.png" />
						</td>
						<td width="83" style="cursor: pointer">
							<img src="manager/images/imgs/weixiuhetong.png"
								id="showHeTong" onclick="f_hetong()" />
						</td>
						<td></td>
					</tr>
				</table>
			</div>
			
			
			<div id="showWeiXiuDIV">
				<form action="maintenance_findMaintenance" method="post"
					name="myform" id="myform">
					<input type="hidden" name="mpage" value="${page.currentPage}"
						id="mpage" />
					<input type="hidden" name="editFlag" value="${editFlag}"
						id="editFlag" />
					<c:if test="${roleMessage == '1' || roleMessage == '4' }">
						<table align="left">
							<tr height="25" valign="bottom">
								<td align="left">
									<span class="gai_clor_063">${hospName }</span>
								</td>
							</tr>
						</table>
					</c:if>
					<table width="100%" border="0"
						class="louyujj_xiaxian_hui gai_left_duiqi">
						<tr style="height: 30px" valign="middle">
							<td width="100" class="shebeigl_inp_zt" align="center">
								所在楼宇：
							</td>
							<td width="155">
								<select name="voMaintenance.buildName" id="buildId"
									style="width: 125px;"
									onchange="getStoreyByBuildId('buildId','storey','${voMaintenance.storey }','','1');">
									<option value="">
										--请选择楼宇--
									</option>
								</select>
								<%--							<input id="" name="voMaintenance.buildName"--%>
								<%--								value="${voMaintenance.buildName }" type="text"--%>
								<%--								style="width: 120px;" />--%>
							</td>
							<td width="100" class="shebeigl_inp_zt" align="center">
								所在楼层：
							</td>
							<td width="150">
								<input id="storey" name="storey" value="${voMaintenance.storey }"
									type="text" style="width: 120px;" />
							</td>
							<td width="130" class="shebeigl_inp_zt" align="center"
								style="width: 120px;">
								楼宇曾用名：
							</td>
							<td width="80">
								<input name="voMaintenance.hisName"
									value="${voMaintenance.hisName }" type="text"
									style="width: 120px;" />
							</td>
							<td width="100" class="shebeigl_inp_zt" align="center">
								维修时间：
							</td>
							<td width="250" colspan="2">
								<input id="d4311" size="12"
									value="${voMaintenance.serviceDateStart }"
									name="voMaintenance.serviceDateStart" class="Wdate" type="text"
									style="width: 85px;"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'%y-%M-%d\'}',onpicked:function(){d4312.focus();}})" />
								至
								<input id="d4312" size="12"
									value="${voMaintenance.serviceDateEnd}"
									name="voMaintenance.serviceDateEnd" class="Wdate" type="text"
									style="width: 85px;"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'%y-%M-%d'})" />
	
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr style="height: 30px" valign="middle">
							<td class="shebeigl_inp_zt" align="center" width="100">
								设备名称：
							</td>
							<td width="80">
								<input name="voMaintenance.eqName"
									value="${voMaintenance.eqName }" type="text"
									style="width: 120px;" />
							</td>
							<td class="shebeigl_inp_zt" align="center" width="100">
								安装位置：
							</td>
							<td>
								<input name="voMaintenance.place" value="${voMaintenance.place }"
									type="text" style="width: 120px;" />
							</td>
							<td class="shebeigl_inp_zt" align="center" width="100">
								设备类型：
							</td>
							<td>
								<input type="text" name="voMaintenance.eqTypeName" id="equipTypeId"
									value="${voMaintenance.eqTypeName }" readonly="readonly"
									onclick="creatediv(this);" style="width: 120px;" />
								<input type="hidden" name="voMaintenance.eqTypeId" id="eqTypeId"
									value="${voMaintenance.eqTypeId }" />
							</td>
							<td class="shebeigl_inp_zt" align="center" width="100">
								设备型号：
							</td>
							<td width="80">
								<input name="voMaintenance.unitType"
									value="${voMaintenance.unitType }" type="text"
									style="width: 120px;" />
							</td>
							<td align="center" width="90">
								<img style='cursor: pointer'
									src="manager/images/imgs/shebeigl_sousuo_an.png"
									onclick="formSubmit()" />
							</td>
						</tr>
					</table>
				</form>
				<br />
				<!-- 显示提示信息 -->
				<span class="admin_clor_f00">${message}</span>
				<table width="100%" border="0" cellspacing="1"
					class="biaoge_bg1 gai_left_duiqi" align="center">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<th width="8%" scope="col">
								序号
							</th>
							<th width="15%" scope="col">
								设备名称
							</th>
							<th width="20%" scope="col">
								安装位置
							</th>
							<th width="10%" scope="col">
								维护类型
							</th>
							<th width="15%" scope="col">
								维护日期
							</th>
							<th width="10%" scope="col">
								维护人员
							</th>
							<c:if
								test="${!empty menuMap && !empty menuMap['2004001002'] && !empty menuMap['2004001003']}">
								<th width="15%" scope="col">
									操作
								</th>
							</c:if>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:if test="${!empty mainList}">
							<c:forEach items="${mainList}" var="maint" varStatus="i">
								<tr class=" biaog_kan2 biaog_zt2"
									onclick="trClick(${maint.equip_id});">
									<td align="center">
										${i.index+1 }
									</td>
									<td align="center">
										${maint.equip_name }
									</td>
									<td align="center">
										${maint.place }
									</td>
									<td align="center">
										<c:if test="${maint.maintenancetype==1}">
										维修
										</c:if>
										<c:if test="${maint.maintenancetype==2}">
										巡检
										</c:if>
										<c:if test="${maint.maintenancetype==3}">
										保养
										</c:if>
									</td>
									<td align="center">
										<fmt:formatDate value="${maint.servicetime }"
											pattern="yyyy-MM-dd" />
									</td>
									<td align="center">
										${maint.serviceman }
									</td>
									<c:if
										test="${!empty menuMap && !empty menuMap['2004001002'] && !empty menuMap['2004001003']}">
										<td align="center" class="biao_lianj_1">
											<c:if test="${!empty menuMap && !empty menuMap['2004001002']}">
												<a href="maintenance_showEditUI?mid=${maint.seq }">修改</a>
											</c:if>
											<c:if test="${!empty menuMap && !empty menuMap['2004001003']}">
												<a href="javascript:void(0);" class="deleM" lang="${maint.seq}">删除</a>
											</c:if>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${!empty mainList}">
					<table width="100%" border="0" class="gai_left_duiqi">
						<tr style="height: 30px">
							<td colspan="5">
								<sktag:paginator showTotal="true" showAllPages="true"
									strUnit="条记录" />
							</td>
						</tr>
					</table>
				</c:if>
			</div>
			
			<!-- 维修合同 -->
			<div id="showHeTongDIV" style="display: none;">
				<iframe src="manager/logistics/equipment/mContract.jsp"
					allowTransparency="true" scrolling="no" width="100%" height=760
					marginwidth=0 marginheight=0 frameborder=0></iframe>
			</div>
			
			
			
			
			
		</div>
	</body>
</html>