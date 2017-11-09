<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>
		<script type="text/javascript">
 	
	onload = function() {
		if (${materialAdd=="1"}) {
			document.getElementById("addTitle").innerHTML = "添加";
		} else {
			document.getElementById("addTitle").innerHTML = "修改";
		}
	}
	function sumAmount() {
		var price=document.getElementById("price");
		if (price.vlaue == "" || price.value == null) {
			price.value = 0;
		}else if(isNaN(price.value)){
			alert("输入格式不正确");
			price.value="";
			return;
			}
		var acount = document.getElementById("acount");
		if (acount == "" || acount == null) {
			acount = 0;
		}else if(isNaN(acount.value)){
			alert("输入格式不正确");
			acount.value="";
			return;
			}
			
		document.getElementById("amount").value = (price.value * acount.value);
		return;
	}
	function objisNull(element,resultContent){
		var obj=document.getElementById(element);
		var copyobj=jQuery.trim(obj.value);
		if(copyobj==null || copyobj==""){
			alert(resultContent);
			obj.value="";
			obj.focus();
			return false;
			}
		return true;
		}
	
	function addMaterial() {
		 if(objisNull("apparatusno","请输入物资编号")){}
			 else{
			return;
			 }
		 if(objisNull("name","请输入物资名称")){}
		 else{
		return;
		 }
		 if(objisNull("model","请输入物资型号")){}
		 else{
		return;
		 }
		 if(objisNull("tisrp","请输入生产厂家")){}
		 else{
		return;
		 }
		 if(objisNull("price","请输入单价")){}
		 else{
			return;
		 }
		 if(objisNull("acount","请输入数量")){}
		 else{
			return;
		 }
		 if(objisNull("supplier","请输入供货商")){}
		 else{
			return;
		 }
		 if(objisNull("buydate","请输入购进日期")){}
		 else{
			return;
		 }
		 if(objisNull("buydept","请输入购进部门")){}
			else{
			return;
		 }
		 if(objisNull("source","请输入资产来源")){}
			else{
			return;
		 }
		 if(objisNull("entrydate","请输入入帐日期")){}
			else{
			return;
		 }
		 if(objisNull("prodate","请输入出厂日期")){}
			else{
			return;
		 }
		
		//此处作为增加修改的判断
		var add = document.getElementById("addTitle").innerHTML
		if (add == "添加") {
			document.getElementById("materialForm").action = "MaterialAction!insertMaterial.action?editFlag=${editFlag}";
		}
		var materialId = document.getElementById("id").value;
		if (add == "修改" && materialId != null && materialId != "") {
			document.getElementById("materialForm").action = "MaterialAction!updateMaterial.action?editFlag=${editFlag}";
		}
		document.getElementById("materialForm").submit();
		return;
	}
	function retMaterial() {
		submitForm("materialForm","MaterialAction!showMaterial.action?editFlag=2");
	}
	function submitForm(formName,urlName){
		var myform=document.getElementById(formName);
		myform.action=urlName;
		myform.submit();
		}
</script>
	</head>

	<body>
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table class="titleBg">
					<tr style="height: 20px">
						<td width="30" align="center">
							<img src="manager/images/common/28-01.png" />
						</td>
						<td>
							资产物资管理——资产物资列表——<span id="addTitle"></span>
						</td>
					</tr>
				</table>
			</div>
	
		<form id="materialForm" name="materialForm" action="" method="post">
			<input id="id" name="material.seq" value="${material.seq}"
				type="hidden" />
			
				<div id="quchu_left">
					<table width="780" border="0" cellspacing="1" class="gai_left_duiqi">
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								物资编号
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="apparatusno" name="material.apparatusno" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.apparatusno}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								物资名称
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="name" name="material.name" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.name}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								分类号
							</td>
							<td class="admin_bgclor_f1f">
								<input id="classno" name="material.classno" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.classno}" />
							</td>
						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								同类序号
							</td>
							<td class="admin_bgclor_f1f">
								<input id="equalno" name="material.equalno" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.equalno}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								型号
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="model" name="material.model" type="text" maxlength="50"
									class="yiyuanjbxx_inp_nr4" value="${material.model}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								规格
							</td>
							<td class="admin_bgclor_f1f">
								<input id="standard" name="material.standard" type="text" maxlength="50"
									class="yiyuanjbxx_inp_nr4" value="${material.standard}" />
							</td>
						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								单价
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="price" name="material.price" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.price}"
									onkeyup="sumAmount(this);" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								数量
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="acount" name="material.acount" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.acount}"
									onkeyup="sumAmount(this);" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								供货商
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="supplier" name="material.supplier" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.supplier}" />
							</td>
						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								购进日期
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="buydate" name="material.buydate" class="Wdate"
									type="text" readonly="readonly"
									value="<f:formatDate value="${material.buydate}" pattern="yyyy-MM-dd"/>"
									onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								购进部门
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="buydept" name="material.buydept" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.buydept}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								使用部门
							</td>
							<td class="admin_bgclor_f1f">
								<input id="usedept" name="material.usedept" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.usedept}" />
							</td>
						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								物资总价
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="amount" name="material.amount" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.amount}"
									readonly="readonly" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								保养周期
							</td>
							<td class="admin_bgclor_f1f">
								<input id="keeprate" name="material.keeprate" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.keeprate}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								国名
							</td>
							<td class="admin_bgclor_f1f">
								<input id="bnrpd" name="material.bnrpd" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.bnrpd}" />
							</td>
						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								项目代码
							</td>
							<td class="admin_bgclor_f1f">
								<input id="itemno" name="material.itemno" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.itemno}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								项目名称
							</td>
							<td class="admin_bgclor_f1f">
								<input id="itemname" name="material.itemname" type="text" maxlength="50"
									class="yiyuanjbxx_inp_nr4" value="${material.itemname}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								经费科目
							</td>
							<td class="admin_bgclor_f1f">
								<input id="funds" name="material.funds" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.funds}" />
							</td>
						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								物资来源
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="source" name="material.source" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.source}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								管理属性
							</td>
							<td class="admin_bgclor_f1f">
								<input id="nature" name="material.nature" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.nature}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								备注
							</td>
							<td class="admin_bgclor_f1f">
								<input id="remark1" name="material.remark1" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.remark1}" />
							</td>

						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								使用方向
							</td>
							<td class="admin_bgclor_f1f">
								<input id="direction" name="material.direction" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.direction}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								入账日期
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="entrydate" name="material.entrydate" class="Wdate"
									type="text" readonly="readonly"
									value="<f:formatDate value="${material.entrydate}" pattern="yyyy-MM-dd" />"
									onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								入账凭证
							</td>
							<td class="admin_bgclor_f1f">
								<input id="proof" name="material.proof" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.proof}" />
							</td>
						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								经手人
							</td>
							<td class="admin_bgclor_f1f">
								<input id="issuedby" name="material.issuedby" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.issuedby}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								原编号
							</td>
							<td class="admin_bgclor_f1f">
								<input id="originalno" name="material.originalno" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.originalno}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								出厂日期
								<span class="admin_clor_f00">*</span>
							</td>
							<td class="admin_bgclor_f1f">
								<input id="prodate" name="material.prodate" class="Wdate"
									type="text" readonly="readonly"
									value="<f:formatDate value="${material.prodate}" pattern="yyyy-MM-dd"/>"
									onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" />
							</td>
						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								安置地点
							</td>
							<td class="admin_bgclor_f1f">
								<input id="instate" name="material.instate" type="text" maxlength="50"
									class="yiyuanjbxx_inp_nr4" value="${material.instate}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								物资现状
							</td>
							<td class="admin_bgclor_f1f">
								<input id="status" name="material.status" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.status}" />
							</td>
							<td class="admin_bgclor_e3f" align="left">
								保管人
							</td>
							<td class="admin_bgclor_f1f">
								<input id="custodian" name="material.custodian" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.custodian}" />
							</td>
						</tr>
						<tr>
							<td class="admin_bgclor_e3f" align="left">
								生产厂家
								<span class="admin_clor_f00">*</span>
							</td>
							<td colspan="5" class="admin_bgclor_f1f">
								<input id="tisrp" name="material.tisrp" type="text" maxlength="25"
									class="yiyuanjbxx_inp_nr4" value="${material.tisrp}" />
							</td>
						</tr>
					</table>
				</div>
				<table width="780" border="0" class="gai_left_duiqi">
					<tr style="height:30" valign="bottom">
						<td></td>
						<td></td>
						<td width="70">
							<a href="javascript:void(0);" class="btn blue"
								onclick="addMaterial();">保存</a>
						</td>
						<td width="70">
							<a href="javascript:void(0);" class="btn blue"
								onclick="retMaterial();">返回</a>
						</td>
					</tr>
				</table>

			
			<!--</div>-->
		</form>
		</div>
	</body>
</html>
