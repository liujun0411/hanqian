<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<base href="<%=basePath%>" />

		<title>楼宇添加</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script src="manager/js/reqi.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script language="javascript" type="text/javascript"
			src="manager/js/My97DatePicker/WdatePicker.js">
</script>
		<script type="text/javascript" src="manager/js/zxq.js">
</script>

		<script type="text/javascript">
		function showBuildingPages(action){
            document.linkform.action=action;
            document.linkform.submit();
        }
 f_quedingup=function(){
		alert("是否确认，需要修改楼上层数!");
		jQuery("#UP").focus();
}
f_quedingdown=function(){
		alert("是否确认，需要修改楼下层数!");
		jQuery("#DOWN").focus();
}
f_tijiao = function() {
	if (jQuery.trim(jQuery("#LM").val()) == ""
			|| jQuery.trim(jQuery("#LM").val()) == null) {
		alert("请输入楼宇名称！");
		jQuery("#LM").focus();
		return;
	}
	if (jQuery.trim(jQuery("#UP").val()) == ""
			|| jQuery.trim(jQuery("#UP").val()) == null) {
		alert("请输入楼上层数！");
		jQuery("#UP").focus();
		return;
	}

	if (jQuery.trim(jQuery("#DOWN").val()) == ""
			|| jQuery.trim(jQuery("#DOWN").val()) == null) {
		alert("请输入楼下层数！");
		jQuery("#DOWN").focus();
		return;
	}
//判断总建筑面积时候有值
	if (jQuery.trim(jQuery("#area").val()) == ""
			|| jQuery.trim(jQuery("#area").val()) == null) {
		//提示
		alert("请输入楼宇总建筑面积！");
		jQuery("#area").focus();
		return false;
	}else{
	 var area = $('#area').val();
	//判断填的值是否符合规范
	//一共12位小数，整数占10为小数点和小数点后只有一位，一共2位。
	 if((area.indexOf(".")<0&&area.length>10)||(area.indexOf(".")>-1&&area.indexOf(".")>10)){
                    alert("总建筑面积整数只能输入10位");
                     jQuery("#area").focus();
                    return false;
                 }else if(area.indexOf(".")>-1&&area.indexOf(".")+3<area.length){
                    alert("总建筑面积小数只能输入2位");
                     jQuery("#area").focus();
                    return false;
       }
	}
	//投资额验证位数
	if (jQuery.trim(jQuery("#tou").val()) != ""
			|| jQuery.trim(jQuery("#tou").val()) != null) {
		//提示
		 var tou = $('#tou').val();
	           if((tou.indexOf(".")<0&&tou.length>10)||(tou.indexOf(".")>-1&&tou.indexOf(".")>10)){
                    alert("投资额整数只能输入10位.");
                    jQuery("#tou").focus();
                    return false;
                 }else if(tou.indexOf(".")>-1&&tou.indexOf(".")+3<tou.length){
                    alert("投资额小数只能输入2位.");
                    jQuery("#tou").focus();
                    return false;
                 }
	}
	
	//建安工程造价
	if (jQuery.trim(jQuery("#jian").val()) != ""
			|| jQuery.trim(jQuery("#jian").val()) != null) {
		//提示
		 var jian = $('#jian').val();
	           if((jian.indexOf(".")<0&&jian.length>10)||(jian.indexOf(".")>-1&&jian.indexOf(".")>10)){
                    alert("建安工程造价整数只能输入10位.");
                    jQuery("#jian").focus();
                    return false;
                 }else if(jian.indexOf(".")>-1&&jian.indexOf(".")+3<jian.length){
                    alert("建安工程造价小数只能输入2位.");
                    jQuery("#jian").focus();
                    return false;
                 }
	}
	if (jQuery("#TIME").val() == "" || jQuery("#TIME").val() == null) {
		alert("请选择竣工时间！");
		jQuery("#TIME").focus();
		return;
	}
	if (jQuery("#ZJ").val() == "" || jQuery("#ZJ").val() == null
			|| jQuery("#ZJ").val() == "--请选择--") {
		alert("请选择造价依据！");
		jQuery("#ZJ").focus();
		return;
	}

	if (jQuery("#YT").val() == "" || jQuery("#YT").val() == null
			|| jQuery("#YT").val() == "--请选择--") {
		alert("请选择用途！");
		jQuery("#YT").focus();
		return;
	}
	if (jQuery("#ZT").val() == "" || jQuery("#KZ").val() == null
			|| jQuery("#KZ").val() == "--请选择--") {
		alert("请选择抗震烈度！");
		jQuery("#KZ").focus();
		return;
	}

	if (jQuery("#ZT").val() == "" || jQuery("#WD").val() == null
			|| jQuery("#WD").val() == "--请选择--") {
		alert("请选择屋面防水等级！");
		jQuery("#WD").focus();
		return;
	}


	if($('#buildingId').val()!=""){
		if (jQuery("#ZT").val() == 15 && jQuery("#GZQ").val() == "--请选择--") {
			alert("请选择改造前主要问题！");
			jQuery("#GZQ").focus();
			return;
		}
	}else{
		if (jQuery("#ZT").val() != 15 && jQuery("#GZQ").val() != "--请选择--") {
			alert("请不要选择改造前主要问题！");
			jQuery("#GZQ").focus();
			return;
		}
	}
	
	if (jQuery("#ZT").val() == "" || jQuery("#ZT").val() == null
			|| jQuery("#ZT").val() == "--请选择--") {
		alert("请选择楼宇状态！");
		jQuery("#ZT").focus();
		return;
	}
	document.myform.submit();
}

onload = function() {
	if (jQuery("#TIME").val() == "" || jQuery("#TIME").val() == null) {
		var now = new Date();
		jQuery("#TIME").val(now.getFullYear());
	}
	//门用材料
	var str = "${listBuilding.doorMater}";
	var strs = new Array();
	strs = str.split(",");
	var my = document.getElementsByName("building.doorMater");
	for ( var j = 0; j < strs.length; j++) {
		for ( var i = 0; i < my.length; i++) {
			if (parseInt(strs[j]) == jQuery("#my" + i).val()) {
				jQuery("#my" + i).attr("checked", true);
			}
		}
	}
	//窗用材料 
	var str = "${listBuilding.windowMater}";
	var strs = new Array();
	strs = str.split(",");
	var my = document.getElementsByName("building.windowMater");
	for ( var j = 0; j < strs.length; j++) {
		for ( var i = 0; i < my.length; i++) {
			if (parseInt(strs[j]) == jQuery("#cy" + i).val()) {
				jQuery("#cy" + i).attr("checked", true);
			}
		}
	}

	//墙体材料
	var str = "${listBuilding.wallMater}";
	var strs = new Array();
	strs = str.split(",");
	var my = document.getElementsByName("building.wallMater");
	for ( var j = 0; j < strs.length; j++) {
		for ( var i = 0; i < my.length; i++) {
			if (parseInt(strs[j]) == jQuery("#qt" + i).val()) {
				jQuery("#qt" + i).attr("checked", true);
			}
		}
	}
	//地坪材料
	var str = "${listBuilding.floorMater}";
	var strs = new Array();
	strs = str.split(",");
	var my = document.getElementsByName("building.floorMater");
	for ( var j = 0; j < strs.length; j++) {
		for ( var i = 0; i < my.length; i++) {
			if (parseInt(strs[j]) == jQuery("#dp" + i).val()) {
				jQuery("#dp" + i).attr("checked", true);
			}
		}
	}

	//屋内顶材料
	var str = "${listBuilding.ceilingMater}";
	var strs = new Array();
	strs = str.split(",");
	var my = document.getElementsByName("building.ceilingMater");
	for ( var j = 0; j < strs.length; j++) {
		for ( var i = 0; i < my.length; i++) {
			if (parseInt(strs[j]) == jQuery("#wnd" + i).val()) {
				jQuery("#wnd" + i).attr("checked", true);
			}
		}
	}
	//外墙材料
	var str = "${listBuilding.outWall}";
	var strs = new Array();
	strs = str.split(",");
	var my = document.getElementsByName("building.outWall");
	for ( var j = 0; j < strs.length; j++) {
		for ( var i = 0; i < my.length; i++) {
			if (parseInt(strs[j]) == jQuery("#wq" + i).val()) {
				jQuery("#wq" + i).attr("checked", true);
			}
		}
	}

<%--  <c:if test="${addOredit==1}">
        jQuery("#tou").val("0");
        jQuery("#jian").val("0");
        jQuery("#hei").val("0");
     </c:if>--%>
}
</script>

	</head>
	<body>
		<!--<div id="fenzhan_right">-->
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="100%" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							楼宇基建——楼宇导航——楼宇
							<c:if test="${addOredit==1}">添加</c:if>
							<c:if test="${addOredit==2}">修改</c:if>
						</td>
					</tr>
				</table>
			</div>

			<br />
			<form name="buildingForm" action="" method="post">
            </form>
			<form action="building_showAddBuilding.action" name="myform" method="post">
                <input type="hidden" id="buildingId" name="building.buildingId" value="${listBuilding.buildingId }"/>
				<table width="780" border="0" cellspacing="1" class="gai_left_duiqi">

					<tr>
						<td width="100" class="admin_bgclor_e3f">
							楼宇编号
						</td>
						<td width="290" class="admin_bgclor_f1f">
							<input name="building.buildingCode" type="text"
								class="yiyuanjbxx_inp_nr4" value="${listBuilding.buildingCode }"
								maxlength="25" />
						</td>
						<td width="100" class="admin_bgclor_e3f">
							楼宇名称&nbsp;
							<span class="louyujj_biaozongji_zt1">*</span>
						</td>
						<td width="290" class="admin_bgclor_f1f">
							<input name="building.buildingName" type="text"
								class="yiyuanjbxx_inp_nr4" id="LM" value="${listBuilding.buildingName }"
								maxlength="25" />
							&nbsp;
						</td>

					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							地上层数&nbsp;
							<span class="louyujj_biaozongji_zt1">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input name="building.mendNum" value="${listBuilding.mendNum }"
								type="hidden" />
								<!-- 
							<input name="building.status" value="${listBuilding.status }"
								type="hidden" />
								 -->
							<input name="building.storeyNumUp" id="UP"
							    <c:if test='${addOredit==2}'>onchange="f_quedingup()"</c:if>
								onkeyup="this.value=this.value.replace(/\D/g,'')" type="text"
								class="yiyuanjbxx_inp_nr4" value="${listBuilding.storeyNumUp }"
								maxlength="2" />
							&nbsp;
						</td>
						<td class="admin_bgclor_e3f">
							地下层数
							<span class="louyujj_biaozongji_zt1">&nbsp;*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input name="building.storeyNumDown"
							<c:if test='${addOredit==2}'>onchange="f_quedingdown()"</c:if>
								 id="DOWN" 
								onkeyup="this.value=this.value.replace(/\D/g,'')" type="text"
								class="yiyuanjbxx_inp_nr4"
								value="${listBuilding.storeyNumDown }" maxlength="2" />
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							总建筑面积
							<span class="louyujj_biaozongji_zt1">*</span>
						</td>
						<td class="admin_bgclor_f1f">
								<input type="text" id="area" maxlength="12"  name="building.buildingAreas" value="<fmt:formatNumber value='${listBuilding.buildingAreas }'  pattern="0.00"/>"  class="yiyuanjbxx_inp_nr4"
                                onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
							平方米
						</td>
						<td class="admin_bgclor_e3f">
							建筑高度
						</td>
						<td class="admin_bgclor_f1f">
							<input name="building.height" id="hei"
								onkeyup="if(isNaN(value))execCommand('undo')"
								onfocus="OnEnter(this)" onblur="OnExit(this)"
								onafterpaste="if(isNaN(value))execCommand('undo')" type="text"
								class="yiyuanjbxx_inp_nr4"
								<c:if test="${listBuilding.height!=null}"> value="${listBuilding.height }"</c:if>
								<c:if test="${listBuilding.height==null}">value="0"</c:if>
								maxlength="4" />
							米
						</td>

					</tr>
					<tr>

						<td class="admin_bgclor_e3f">
							投资额
						</td>
						<td class="admin_bgclor_f1f">
								<input type="text" id="tou" maxlength="12"  name="building.amount" 
								<c:if test="${listBuilding.amount!=null}"> value="<fmt:formatNumber value='${listBuilding.amount }' pattern="0.00"/>"</c:if>
								<c:if test="${listBuilding.amount==null}">value="0"</c:if>
								class="yiyuanjbxx_inp_nr4"
                                onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
							元
						</td>
						<td class="admin_bgclor_e3f">
							竣工时间&nbsp;
							<span class="louyujj_biaozongji_zt1">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input class="Wdate" name="building.completime" id="TIME"
								type="text"
								onfocus="WdatePicker({skin:'whyGreen',maxDate:'%YYYY',dateFmt:'yyyy'})"
								value="${fn:substring(listBuilding.completime,0,4)}" readonly="readonly" />年
						</td>
					</tr>

					<tr>
						<td class="admin_bgclor_e3f">
							建安工程造价

						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="jian" maxlength="12"  name="building.proCost" 
							<c:if test="${listBuilding.proCost!=null}"> value="<fmt:formatNumber value='${listBuilding.proCost }'  pattern="0.00"/>"</c:if>
						    <c:if test="${listBuilding.proCost==null}">value="0"</c:if>
							class="yiyuanjbxx_inp_nr4"
                                onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
							元
						</td>
						<td class="admin_bgclor_e3f">
							产权拥有者
						</td>
						<td class="admin_bgclor_f1f">
							<input name="building.ower" type="text"
								class="yiyuanjbxx_inp_nr4" value="${listBuilding.ower }"
								maxlength="25" />
						</td>

					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							位置
						</td>
						<td class="admin_bgclor_f1f">
							<input name="building.place" type="text"
								class="yiyuanjbxx_inp_nr4" value="${listBuilding.place }"
								maxlength="25" />
						</td>
						<td class="admin_bgclor_e3f">
							曾用名&nbsp;
						</td>
						<td>
							<input name="building.hisName" type="text"
								class="yiyuanjbxx_inp_nr4" value="${listBuilding.hisName }"
								maxlength="25" />
						</td>
					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							造价依据&nbsp;
							<span class="louyujj_biaozongji_zt1">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<select name="building.dbBuildMaterByCostaccord.materId" class="yiyuanjbxx_inp_nr4"
								id="ZJ">
								<option>
									--请选择--
								</option>
								<c:forEach items="${listMater_ZJ}" var="zj">
									<option value="${zj.masterid }"
										${listBuilding.dbBuildMaterByCostaccord.materId==zj.masterid?'selected':'' }>
										${zj.mastername }
									</option>
								</c:forEach>
							</select>
						</td>
						<td class="admin_bgclor_e3f">
							用途&nbsp;
							<span class="louyujj_biaozongji_zt1">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<select name="building.dbBuildMaterByUsetype.materId" class="yiyuanjbxx_inp_nr4"
								id="YT">
								<option>
									--请选择--
								</option>
								<c:forEach items="${listMater_YT}" var="yt">
									<option value="${yt.masterid }"
										${listBuilding.dbBuildMaterByUsetype.materId==yt.masterid?'selected':'' }>
										${yt.mastername }
									</option>
								</c:forEach>
							</select>
						</td>

					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							抗震烈度&nbsp;
							<span class="louyujj_biaozongji_zt1">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<select name="building.dbBuildMaterByQuakeproof.materId" class="yiyuanjbxx_inp_nr4"
								id="KZ">
								<option>
									--请选择--
								</option>
								<c:forEach items="${listMater_KZ}" var="kz">
									<option value="${kz.masterid }"
										${listBuilding.dbBuildMaterByQuakeproof.materId==kz.masterid?'selected':'' }>
										${kz.mastername }
									</option>
								</c:forEach>
							</select>
						</td>

						<td class="admin_bgclor_e3f">
							屋面防水等级
							<span class="louyujj_biaozongji_zt1">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<select name="building.dbBuildMaterByWaterproof.materId" class="yiyuanjbxx_inp_nr4"
								id="WD">
								<option>
									--请选择--
								</option>
								<c:forEach items="${listMater_FS}" var="fs">
									<option value="${fs.masterid }"
										${listBuilding.dbBuildMaterByWaterproof.materId==fs.masterid?'selected':'' }>
										${fs.mastername }
									</option>
								</c:forEach>
							</select>
						</td>


					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							改造前主要问题${buildingInfo }
						</td>
						<td class="admin_bgclor_f1f">
							<select name="building.dbBuildMaterByProblem.materId" id="GZQ"
								class="yiyuanjbxx_inp_nr4">
								<option>
									--请选择--
								</option>
								<c:forEach items="${listMater_GZ}" var="gz">
									<option value="${gz.masterid }"
										${listBuilding.dbBuildMaterByProblem.materId==gz.masterid?'selected':'' }>
										${gz.mastername }
									</option>
								</c:forEach>
							</select>
						</td>
						<td class="admin_bgclor_e3f">
							楼宇状态&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="louyujj_biaozongji_zt1">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<select name="building.dbBaseComm.seq" id="ZT"
								class="yiyuanjbxx_inp_nr4">
								<option>
									--请选择--
								</option>
								<c:forEach items="${listBaseComm}" var="dic">
									<option value="${dic.seq }"
										${listBuilding.dbBaseComm.seq==dic.seq?'selected':'' } >
										${dic.content1 }
									</option>
								</c:forEach>
							</select>
						</td>
						
						
					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							门用材料
						</td>
						<td class="admin_bgclor_f1f">
							<c:forEach items="${listMater_MY}" var="my" varStatus="v">
								<input type="checkbox" name="building.doorMater"
									value="${my.masterid }" id="my${v.index}" />${my.mastername }</c:forEach>
						</td>
						<td class="admin_bgclor_e3f">
							窗用材料
						</td>
						<td class="admin_bgclor_f1f">
							<c:forEach items="${listMater_CY}" var="cy" varStatus="v">
								<input type="checkbox" name="building.windowMater"
									value="${cy.masterid }" id="cy${v.index }" />${cy.mastername }
							</c:forEach>
						</td>

					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							墙体材料
						</td>
						<td class="admin_bgclor_f1f">
							<c:forEach items="${listMater_QT}" var="qt" varStatus="v">
								<input type="checkbox" name="building.wallMater"
									value="${qt.masterid }" id="qt${v.index }" />${qt.mastername }
							</c:forEach>
						</td>
						<td class="admin_bgclor_e3f">
							地坪材料
						</td>
						<td class="admin_bgclor_f1f">
							<c:forEach items="${listMater_DP}" var="dp" varStatus="v">
								<input type="checkbox" name="building.floorMater"
									value="${dp.masterid }" id="dp${v.index }" />${dp.mastername }
							</c:forEach>
						</td>
					</tr>

					<tr>
						<td class="admin_bgclor_e3f">
							建筑结构
						</td>
						<td class="admin_bgclor_f1f">
							<select name="building.structure" class="yiyuanjbxx_inp_nr4"
								id="JZ">
								<option value="-1">
									--请选择--
								</option>
								<c:forEach items="${listMater_JZ}" var="jz">
									<option value="${jz.masterid }"  <c:if test="${listBuilding.structure==null?'-1':listBuilding.structure==jz.masterid}">selected="selected"</c:if>  >
										${jz.mastername }
									</option>
								</c:forEach>
							</select>
						</td>

						<td class="admin_bgclor_e3f">
							外墙材料
						</td>
						<td class="admin_bgclor_f1f">
							<c:forEach items="${listMater_WQ}" var="wq" varStatus="v">
								<input type="checkbox" name="building.outWall"
									value="${wq.masterid }" id="wq${v.index }" />${wq.mastername }
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							屋内顶材料
						</td>
						<td class="admin_bgclor_f1f">
							<c:forEach items="${listMater_WND}" var="wnd" varStatus="v">
								<input type="checkbox" name="building.ceilingMater"
									value="${wnd.masterid }" id="wnd${v.index }" />${wnd.mastername }
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="admin_bgclor_e3f">
							审计结果
						</td>
						<td class="admin_bgclor_f1f" colspan="3">
							<textarea rows="4" cols="80" name="building.audits"
								onkeydown="if(this.value.length> 224)return false;">${listBuilding.audits }</textarea>
						</td>
					</tr>
				</table>
				<input type="hidden" name="editFlag" value="${editFlag}"
					id="editFlag" />
				<input type="hidden" value="${buiiding }" name="buiiding" />
			</form>
			<table width="780" border="0" class="gai_left_duiqi">
				<tr style="height: 30;" valign="bottom">
					<td></td>
					<td></td>
					<td width="70">
						<a href="javascript:f_tijiao()" class="btn blue">保 存</a>
					</td>
					<td width="70">
						<a href="javascript:void(0);"  
						onclick="location.href='manager/building_findBuild.action'" 
						class="btn blue">返回</a>
					</td>
				</tr>
			</table>

		</div>

	</body>
</html>