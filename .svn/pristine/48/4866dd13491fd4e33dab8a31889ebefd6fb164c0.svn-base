<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
		<title>医院后勤智能化管理平台</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
<%--		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />--%>
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script src="manager/js/My97DatePicker/WdatePicker.js"
			type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/jquery.validate.js">
</script>

		<script charset="utf-8" src="manager/js/kindeditor/kindeditor-min.js">
</script>
		<script charset="utf-8" src="manager/js/kindeditor/lang/zh_CN.js">
</script>

<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>
		<script type="text/javascript">
function subimtForm() {
	var istrue = true;
	var mgs = "";
	var hospName = document.getElementById("hospName").value;
	var shortName = document.getElementById("shortName").value;
	var buildtime = document.getElementById("buildtime").value;
	var principal = document.getElementById("principal").value;
	var landAreas = document.getElementById("landAreas").value;
	var buildAreas = document.getElementById("buildAreas").value;
	var address = document.getElementById("address").value;
	var zipcode = document.getElementById("zipcode").value;
	var today = new Date();
	var year = today.getFullYear();
	if (hospName.length == 0) {
		istrue = false;
		mgs += "[医院全称] 不能为空! ";
	} else if (hospName.length > 50) {
		istrue = false;
		mgs += "[医院全称] 超出字数限制! ";
	} else if (shortName.length == 0) {
		istrue = false;
		mgs += "[医院简称] 不能为空! ";
	} else if (shortName.length > 25) {
		istrue = false;
		mgs += "[医院简称] 超出字数限制! ";
	} else if (buildtime.length == 0) {
		istrue = false;
		mgs += "[创建时间] 不能为空! ";
	} else if (buildtime > year) {
		istrue = false;
		mgs += "你输入的[创建日期 ]大于当前日期,请重新输入 !";
	} else if (principal.length == 0) {
		istrue = false;
		mgs += "[院长] 不能为空! ";
	} else if (principal.length > 25) {
		istrue = false;
		mgs += "[院长] 超出字数限制! ";
	} else if (landAreas.length == 0) {
		istrue = false;
		mgs += "[占地面积] 不能为空! ";
	} else if (landAreas.length > 10) {
		istrue = false;
		mgs += "[占地面积] 超出限制! ";
	} else if (buildAreas.length == 0) {
		istrue = false;
		mgs += "[总建筑面积] 不能为空! ";
	} else if (buildAreas.length > 10) {
		istrue = false;
		mgs += "[总建筑面积] 超出限制! ";
	} else if (address.length == 0) {
		istrue = false;
		mgs += "[医院地址] 不能为空! ";
	} 
// 	else if (lens < 500 || lens > 1000) {
// 		istrue = false;
// 		mgs += "医院简介请输入500~1000个字!";
// 	} 
	else if (zipcode.length > 6) {
		istrue = false;
		mgs += "[邮编] 过长! ";
	} else if(!document.getElementById("fullView").value.length==0
			<%--||
			!document.getElementById("images1").value.length==0 ||
			!document.getElementById("images2").value.length==0 ||
			!document.getElementById("images3").value.length==0 ||
			!document.getElementById("images4").value.length==0--%>
			){
		var isdel = window.confirm('确认要修改图纸吗？此操作会覆盖原本的图纸');
		if(isdel){
			istrue = true;
		}else{
			istrue = false;
		}
	}
	if (istrue) {
		$("#levelName").val($("#levels").find("option:selected").text().trim());
		$("#distInfoName").val(
				$("#distinfo").find("option:selected").text().trim());
		$("#hospInfo").val(editor.html());
		$("#updateFrom").submit();
	} else {
		alert(mgs);
	}
}

function Preview(obj, val) {
	var images = obj.value;
	var objim = $("#" + val);
	var value = images.substr(images.lastIndexOf("\\") + 1);
	var ext = images.substr(images.indexOf(".") + 1);
	if (images != "" && ext != "jpg" && ext != "png" && ext != "jpeg"
			&& ext != "gif") {
		alert("请选择正确的图片!错误图片文件可能导致上传失败");
	} else {
		objim.value = value;
	}
}

//超链接表单提交
function saveButtonClick() {
	$("#levelName").val($("#levels").find("option:selected").text().trim());
	$("#distInfoName")
			.val($("#distinfo").find("option:selected").text().trim());
	if (checkForm()) {
		$('#updateFrom').submit();
	}
}

function submitLinkform(action) {
	document.linkform.action = action;
	document.linkform.submit();
}
var lens = "";
var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="content"]', {
		afterChange : function() {
			K('.word_count').html(this.count('text'));
			lens = this.count('text');
		}
	});

});
//-->
</script>

	</head>
	<body>

		<form method="post" action="" name="linkform">
			<input type="hidden" name="editFlag" value="${editFlag }" />
		</form>

		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table class="titleBg">
					<tr style="height: 20px">
						<td width="30" align="center">
							<img src="manager/images/common/28-01.png" />
						</td>
						<td>
							医院信息——医院概况——修改
						</td>
					</tr>
				</table>
			</div>
			<form name="updateFrom" id="updateFrom" method="post"
				action="hospInfoAction!updateHospInfo.action"
				enctype="multipart/form-data">
				<br/>
				<table border="0" class="shebeigl_inp_zt gai_left_duiqi"
					width="100%">
					<tr>
						<td align="center">
							<input name="hospName" id="hospName" type="text"
								value="${hospInfo.hospName }" class="yiyuanjbxx_inp_bt1" />
							<input type="hidden" name="seqHosp" id="seqHosp"
								value="${hospInfo.seqHosp }" />
							<input type="hidden" name="editFlag" value="${editFlag }" />
							<input type="hidden" id="levelName" name="levelName" value="" />
							<input type="hidden" id="distInfoName" name="distInfoName"
								value="" />
						</td>
					</tr>
				</table>
				<table border="0" cellspacing="1" bgcolor="#666666" width="98%">
					<tr>
						<td width="10%" bgcolor="#b8d4ec" class="admin_biaoge_zt1"
							align="center">
							医院简称
							<label style="color: red;">
								*
							</label>
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<input name="shortName" id="shortName" type="text"
								value="${hospInfo.shortName }" class="yiyuanjbxx_inp_nr1" />
						</td>
						<td width="10%" bgcolor="#b8d4ec" class="admin_biaoge_zt1"
							align="center">
							医院级别
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<select name="hospLevel" id="levels" style="width: 96px;">
								<c:forEach items="${levels}" var="level">
									<option value="${level.seq }"
										${hospInfo.dbBaseCommByHosplevel.seq==level.seq?'selected':''}>
										${level.content1 }
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td bgcolor="#b8d4ec" class="admin_biaoge_zt1" align="center">
							创建年份
							<label style="color: red;">
								*
							</label>
						</td>
						<td bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<input type="text" name="buildtime" id="buildtime" size="12"
								readonly="readonly" maxlength="10" class="Wdate"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"
								value="<f:formatDate value="${hospInfo.buildtime}"
								pattern="yyyy" />"
								onchange="checkForm()" />
							年
						</td>
						<td width="10%" bgcolor="#b8d4ec" class="admin_biaoge_zt1"
							align="center">
							医院类型
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<select name="hosptype" id="hospType" style="width: 96px;">
								<c:forEach items="${hospType}" var="hosptype">
									<option value="${hosptype.seq }"
										${hospInfo.dbBaseCommByKind.seq==hosptype.seq?'selected':''}>
										${hosptype.content1 }
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td bgcolor="#b8d4ec" class="admin_biaoge_zt1" align="center">
							院&nbsp;&nbsp;长
							<label style="color: red;">
								*
							</label>
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<input name="principal" id="principal" type="text"
								value="${hospInfo.principal }" class="yiyuanjbxx_inp_nr1" />
						</td>
						<td width="10%" bgcolor="#b8d4ec" class="admin_biaoge_zt1"
							align="center">
							邮&nbsp;&nbsp;编
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<input name="zipcode" id="zipcode" type="text"
								value="${hospInfo.zipcode }" class="yiyuanjbxx_inp_nr1"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" />
						</td>
					</tr>
					<tr>
						<td bgcolor="#b8d4ec" class="admin_biaoge_zt1" align="center">
							占地面积
							<label style="color: red;">
								*
							</label>
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
						<!-- edit start 2013.5.15 by zhangdiannan 
						            修改文本框的限制 -->
									<input type=text t_value="" id="landAreas" value="${hospInfo.landAreas }" class="yiyuanjbxx_inp_nr1" name="landAreas" o_value="" 
							onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value}"/>
 							<!-- edit end 2013.5.15 by zhangdiannan -->
							平方米
						</td>
						<td width="10%" bgcolor="#b8d4ec" class="admin_biaoge_zt1"
							align="center">
							总建筑面积
							<label style="color: red;">
								*
							</label>
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<input name="buildAreas" id="buildAreas" type="text"
								value="${hospInfo.buildAreas }" class="yiyuanjbxx_inp_nr1"
								onkeyup="value=value.replace(/[^\d{10}.\d{2}]/g, '')   "
								onbeforepaste="clipboardData.setData( 'text ',clipboardData.getData( 'text ').replace(/[^\d{10}.\d{2}]/g, '')) " />
							平方米
						</td>
					</tr>
					<tr>
						<td bgcolor="#b8d4ec" class="admin_biaoge_zt1" align="center">
							电&nbsp;&nbsp;话
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<input name="tel1" id="tel1" type="text"
								value="${hospInfo.tel1 }" class="yiyuanjbxx_inp_nr1"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" />
						</td>
						<td width="10%" bgcolor="#b8d4ec" class="admin_biaoge_zt1"
							align="center">
							传&nbsp;&nbsp;真
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<input name="fax1" id="fax1" type="text"
								value="${hospInfo.fax1 }" class="yiyuanjbxx_inp_nr1"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" />
						</td>
					</tr>
					<tr>
						<td width="10%" bgcolor="#b8d4ec" class="admin_biaoge_zt1"
							align="center">
							医院地址
							<label style="color: red;">
								*
							</label>
						</td>
						<td width="40%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							<select name="distinfo" id="distinfo" style="width: 96px;">
								<c:forEach items="${distInfo}" var="distinfo">
									<option value="${distinfo.seq }"
										${hospInfo.dbBaseCommByHospdist.seq==distinfo.seq?'selected':''}>
										${distinfo.content1 }
									</option>
								</c:forEach>
							</select>
							<input name="address" id="address" type="text"
								value="${hospInfo.address}" class="yiyuanjbxx_inp_nr1" />
						</td>
						<td bgcolor="#b8d4ec" class="admin_biaoge_zt1" align="center">

						</td>
						<td bgcolor="#FFFFFF" class="admin_biaoge_zt2">

						</td>
					</tr>
				</table>
				<br />
				<table width="98%" border="0" style="height: 20px;">
					<tr>
						<td>
							<div class="admin_zt_14" style="width: 100%;">
								<input type="hidden" name="fullView" id="images"
									value="${hospInfo.fullView }" />
<%--								<input type="hidden" name="images1" id="image1"--%>
<%--									value="${hospInfo.images1}" />--%>
<%--								<input type="hidden" name="images2" id="image2"--%>
<%--									value="${hospInfo.images2 }" />--%>
<%--								<input type="hidden" name="images3" id="image3"--%>
<%--									value="${hospInfo.images3 }" />--%>
<%--								<input type="hidden" name="images4" id="image4"--%>
<%--									value="${hospInfo.images4 }" />--%>
								<table width="100%">
									<tr>
										<td>
											<span id="clearfile">医院全貌图&nbsp;&nbsp;&nbsp;<input
													type="file" name="fullView" id="fullView"
													onchange="Preview(this,'images');" /> </span>
											<br />
											<!-- edit start 2013.4.12 by zhangdiannan
												  V1.4暂无宣传图展示界面 隐藏掉4个宣传图上传功能  注释掉-->
											<!-- 医院宣传图1
											<input type="file" name="images1" id="images1"
												onchange="Preview(this,'image1');" />
											医院宣传图2
											<input type="file" name="images2" id="images2"
												onchange="Preview(this,'image2');" />
											<br />
											医院宣传图3
											<input type="file" name="images3" id="images3"
												onchange="Preview(this,'image3');" />
											医院宣传图4
											<input type="file" name="images4" id="images4"
												onchange="Preview(this,'image4');" />
												 -->
										<!-- edit end 2013.4.12 by zhangdiannan -->		 
										</td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="hospInfo" id="hospInfo" />
											<!-- 文本编辑器 -->
											<textarea name="content" id="editor"
												style="width: 1000px; height: 200px;">${hospInfo.hospInfo }</textarea>
											<p>
												您当前输入了
												<span class="word_count" id="word_count">0</span> 个文字。
											</p>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<table width="630" border="0" class="gai_left_duiqi">
					<tr class="louyujj_xiaxian_hui">
						<td></td>
						<td width="60">
							<a href="javascript:void(0);" onclick="subimtForm();"
								name="getHtml" class="btn blue">保 存</a>
						</td>
						<td width="60">
							<a href="javascript:void(0);"
								onclick="submitLinkform('hospInfoAction!showHospInfo.action');"
								class="btn blue">取 消</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>