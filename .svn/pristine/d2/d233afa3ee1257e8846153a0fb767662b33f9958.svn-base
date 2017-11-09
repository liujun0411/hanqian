<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript">
	onload = function() {
		if (${applyAdd=="1"}) {
			document.getElementById("addTitle").innerHTML = "领用";
		} else {
			document.getElementById("addTitle").innerHTML = "修改";
		}
	}
		
	function objisNull(element, resultContent) {
		var obj = document.getElementById(element);
		var copyobj = jQuery.trim(obj.value);
		if (copyobj == null || copyobj == "") {
			obj.value = "";
			obj.focus();
			return false;
		}
		return true;
	}

	function addApply() {
		
		if (objisNull("acount", "请填写领用数量")) {
		} else {
			return;
		}
		if (objisNull("applier", "请填写领用人")) {
		} else {
			return;
		}
		if (objisNull("materialName", "请选择物资名称")) {
		} else {
			return;
		}
		
		var materialNames;
		$("select option:selected").each(function(){
			materialNames=$(this).text();
		});
		
		var materialNumber;
		<c:forEach items="${materialList}" var="material" >
			if("${material.name}"==$.trim(materialNames)){
				materialNumber="${material.acount}";
			}
		</c:forEach>
		var eqNumber=document.getElementById("acount");
		if(materialNumber<eqNumber.value || materialNumber==0){
			alert("库存不足,库存量为："+materialNumber);
			return;
		}
			
		//此处作为增加修改的判断
		var add = document.getElementById("addTitle").innerHTML;
		if (add == "领用") { 
			var seq = $("#materialName").val();
			document.getElementById("myform").action = "ApplyAction!addApply.action?seq="+seq+"&materialName="+materialNames;
		}
		var applylId = document.getElementById("seq").value;
		if (add == "修改" && applylId != null && applylId != "") {
			document.getElementById("myform").action = "ApplyAction!updateApply.action";
		}
		document.getElementById("myform").submit();
	}
	
	function refApplyshow() {
		submitForm("myform", "ApplyAction!showApply.action?editFlag=2");
	}

	function submitForm(formName, urlName) {
		var myform = document.getElementById(formName);
		myform.action = urlName;
		myform.submit();
	}
	
	
</script>
	</head>
	<body>
	
	<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
			<table width="780" border="0">
					<tr style="height:30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							资产物资管理——物资领用记录——<span id="addTitle"></span>
						</td>
					</tr>
				</table>
			</div>
		<form id="myform" name="myform" action="" method="post">
		<input id="seq" name="dbApply.seq" value="${dbApply.seq}" type="hidden" />
		<input type="hidden" id="editFlag" name="editFlag" value="${editFlag}"/>
			<!--<div id="fenzhan_right">-->
			<span class="admin_clor_f00">注：领用数量不能超过库存数量</span>
				<table width="100%" border="0" cellspacing="1" class="gai_left_duiqi">

					<tr>
						<td  class="admin_bgclor_e3f" align="left">
							领用部门
							
						</td>
						<td class="admin_bgclor_f1f">
							<input id="applydept" name="dbApply.applydept" type="text"
								class="yiyuanjbxx_inp_nr4" maxlength="25" value="${dbApply.applydept}"/>
						</td>
						<td class="admin_bgclor_e3f" align="left">
							领用数量
							<span class="admin_clor_f00">*</span>
						</td>

						<td class="admin_bgclor_f1f">
							<input id="acount" name="dbApply.acount" type="text" maxlength="25"
								class="yiyuanjbxx_inp_nr4" value="${dbApply.acount}"/>
						</td>
						<td class="admin_bgclor_e3f" align="left">
							领用人
							<span class="admin_clor_f00">*</span>
						</td>
						<td  class="admin_bgclor_f1f">
							<input id="applier" name="dbApply.applier" type="text" maxlength="25"
								class="yiyuanjbxx_inp_nr4" value="${dbApply.applier}" />
						</td>
					</tr>
					<tr>
						<td width="10%" class="admin_bgclor_e3f" align="left">
							物资类别
						</td>
						<td class="admin_bgclor_f1f">
							<input id="usetype" name="dbApply.usetype" type="text" maxlength="25"
								class="yiyuanjbxx_inp_nr4" value="${dbApply.usetype}"/>
						</td>
						<td width="10%" class="admin_bgclor_e3f" align="left">
							物资名称
							<span class="admin_clor_f00">*</span>
						</td>
						<td colspan="3" class="admin_bgclor_f1f">
							<select id="materialName" name="dbApply.dbMaterial.seq" style="width:155px;">
								<c:forEach items="${materialList}" var="material" varStatus="vs">
									<option value="${material.seq}" ${dbApply.dbMaterial.seq==material.seq?'selected':'' }>
										${material.name}
									</option>
								</c:forEach>
									
							</select>
						</td>
					</tr>
					<tr>
						<td class="admin_bgclor_e3f" align="left">
							领用原因
						</td>
						<td colspan="5" class="admin_bgclor_f1f">
							<textarea onpropertychange="if(value.length>50) value=value.substr(0,50)" id="reason" name="dbApply.reason" cols="60" rows="3">${dbApply.reason}</textarea>
						</td>
					</tr>
				</table>

				<table width="780" border="0" class="gai_left_duiqi">
					<tr height="30" valign="bottom">
						<td></td>
						<td></td>
						<td width="70">
							<a href="javascript:void(0);" class="btn blue"
								onclick="addApply();"
>保存</a>
						</td>
						<td width="70">
							<a href="javascript:void(0);" class="btn blue"
								onclick=
	refApplyshow();
>返回</a>
						</td>
					</tr>
				</table>
			
			<!--</div>-->
		</form>
		</div>
	</body>
</html>
