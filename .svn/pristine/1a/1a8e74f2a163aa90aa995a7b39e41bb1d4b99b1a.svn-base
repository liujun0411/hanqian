<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		<title></title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link  href="manager/common/equip/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/jquery.validate.js"></script>
		<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerTree.js" type="text/javascript"></script>
		<script type="text/javascript">
$(function() {
	//验证是否为带有html标签
	jQuery.validator.addMethod("isHTML", function(value, element) {
		var tel = /^.*<[^>]*>.*$/;
		return this.optional(element) || (!tel.test(value));
	}, "不能输入'&lt;&gt;'等特殊字符");

	$('#saveForm').validate( {
		submitHandler : saveButtonClick,//通过验证后运行函数
		rules : { //表单元素验证规则
			serviceman : {
				required : true,
				isHTML : true
			},
			sDate1 : {
				required : true,
				isHTML : true
			},
			company : {
				required : true,
				isHTML : true
			}
		},
		messages : { //表单元素验证不通过的提示信息
			serviceman : {
				required : '维护人员不能为空！'
			},
			sDate1 : {
				required : '维护日期不能为空！'
			},
			company : {
				required : '维护单位不能为空！'
			}
		},
		onchange : true
	});
});

function saveButtonClick() {
	//必填项
	//维修人
	var repairPerson = $("#serviceman").val();
	//维修保养单位
	var repairCompany = $("#company").val();
	
	var equipId = $("#equipId").val();
	
	//维修时间
	var repairTime = $("#sDate1").val();
	if(!repairPerson||repairPerson==''||repairPerson==null){
		alert("请填写维修人!");
		return;
	}
	if(!repairCompany||repairCompany==''||repairCompany==null){
		alert("请填写维修保养单位!");
		return;
	}
	if(!repairTime||repairTime==''||repairTime==null){
		alert("请选择维修日期!");
		return;
	}
	if(!equipId||equipId==''||equipId==null){
		alert("请选择维修设备!");
		return;
	}
	document.saveForm.submit();
}

function creatediv(obj){
	if($("#listDiv_List").length==0){
	    var _left = parseInt($(obj).offset().left - 75);
		var _top = parseInt($(obj).offset().top + 5);
		var _width = parseInt($(obj).width())/2;
		var _height = parseInt($(obj).height());
	    var str = new Array();
		var _divWidth = _width*2+5;
	    str.push('<div id="listDiv_List"');
		str.push('style="background-color:white; border:1px solid #0193ce;width:'+_divWidth+'px;height:300px;position:absolute;left:'+(_left+_width+1)+'px;top:'+(_top+_height+2)+'px;">');
	    str.push('<div style="height:275px; overflow:auto;"><ul id="tree1">');
	    str.push('</ul></div>');
	    //str.push('<div style="height:22px; width:100%; text-align:right;position:absolute; float:right;"><img src="manager/common/equip/images/pic/cz.gif" onclick="doReset();"  style=" margin-top:2px; cursor:pointer;" />&nbsp;<img src="manager/common/equip/images/pic/Close.jpg" style=" margin-top:2px; margin-right:3px; cursor:pointer;" onclick="doClose();" /></div></div>');
	    str.push('<div style="height:22px; width:100%; text-align:right;position:absolute; float:right;vertical-align:middle;"><input type="button" id="btnReset" onclick="doReset()" value="重置" style="border:1px solid #0193ce; height:20px; color:#0193ce; margin-right:2px; margin-top:2px; " /><input type="button" id="btnReset" onclick="doClose()" value="关闭" style="border:1px solid #0193ce; height:20px; color:#0193ce; margin-right:2px; margin-top:2px;" /></div></div>');
	    $("body").append(str.join(""));
	    $("#tree1").ligerTree({ url: 'manager/common/equip/json/prov4.json',checkbox: false,onClick:doClick});
	   }else{
		 if($("#listDiv_List").is(":hidden")){
			 $("#listDiv_List").show();
		 }else{
			 $("#listDiv_List").hide();
		 }
     }
}

//重置按钮
function doReset(){
	$('#equipTypeId').val('');
    $('#eqTypeId').val('');
    $("#listDiv_List").hide();
}

//关闭按钮
function doClose(){
	$("#listDiv_List").hide();
}

//设备类型菜单回调函数
   function doClick(note){
      $('#equipTypeId').val(note.data.text);
      $('#eqTypeId').val(note.data.id);
      $("#listDiv_List").hide();
      var type_id=$('#eqTypeId').val();
      $.ajax({
	      type: 'get',
		  url: "maintenance_getEpuipByTypeId.action",
		  data: {"type_id":type_id},
		  cache:false,
		  dataType: 'json',
		  success: function(data){
		     /* var objdt=eval(data);
		     var result=objdt[0].res;
		     $('#equipCode').next().remove();
		     if(result=='has'){
		         $('#equipCode').parent().append('<span class="error"><font color="red">该设备编号已存在！</font></span>');
		     }else{
		        $('#equipCode').parent().append('<span  class="error" >设备编号可用！</span>');
		     } */
		     var equipList=eval(data);
		     $("#equipId option").remove();
			 $("#equipId").append("<option value=''>--请选择--</option>");
		     if(equipList!=null&&equipList!=''&&equipList.length>0){
			 	console.debug("equipList.length:"+equipList.length);
		    	 
		     }
			  for(var i=0;i<equipList.length;i++){
				  console.debug(i+":"+equipList[i].equip_name);
				  $("#equipId").append("<option value='"+equipList[i].equip_id+"'>"+equipList[i].equip_name+"</option>");
			  }
		  },
		  error:function(textStatus){
			  $("#equipId option").remove();
			  $("#equipId").append("<option value=''>--请选择--</option>");
		  }
	    });
   }
</script>
	</head>

	<body>
		<div id="admin_nr_jbxx">
			<form action="maintenance_updateMaintenance.action" method="post"
				id="saveForm" name="saveForm">
				<input type="hidden" name="oper"
					value="${empty sessionScope.users?'':sessionScope.users.seq}" />
				<input type="hidden" value="${equipId }" name="equipId" />
				<input type="hidden" value="${mark }" id="mark" name="mark"/>
				<input type="hidden" value="${dbMaintenance.seq }" name="dbMaintenance.seq" />
				<table width="970" border="0" cellspacing="1" class="gai_left_duiqi">
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
							设备类型<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" name="equipTypeId" id="equipTypeId" readonly="readonly" onclick="creatediv(this);" value="${equipList[0].type_name }"/>
							<input type="hidden" name="dbEquipList.dbEquipType.equipTypeId" id="eqTypeId" value="${equipList[0].equip_type_id }"/>
						</td>
						<td class="admin_bgclor_e3f">
							设备名称<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<select
								style="width:150px;" id="equipId" name="dbEquipList.equipId" value="${dbMaintenance.dbEquipList.equipId }">
									<option value="">--请选择--</option>
									<c:forEach items="${equipListOpt}" var="equipOpt" varStatus="index">
										<option value="${equipOpt.equip_id }" <c:if test="${equipList[0].equip_id==equipOpt.equip_id }">selected</c:if>>${equipOpt.equip_name}</option>
									</c:forEach>
							</select>
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f ">
							维护人员
							<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input name="dbMaintenance.serviceman" type="text"
								id="serviceman"  value="${dbMaintenance.serviceman }"/>
						</td>
						<td class="admin_bgclor_e3f ">
							维护类型
						</td>
						<td class="admin_bgclor_f1f" >
							<select name="dbMaintenance.maintenancetype"
								class="louyu_xiala_kt2" id="maintenancetype"
								style="width: 150px">
								<option value="1" ${dbMaintenance.maintenancetype==1?'selected':''}>
									维修
								</option>
								<option value='2'  ${dbMaintenance.maintenancetype==2?'selected':''}>
									巡检
								</option>
								<option value='3' ${dbMaintenance.maintenancetype==3?'selected':''}>
									保养
								</option>
							</select>
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
						<td class="admin_bgclor_e3f ">
							维修合同编号
						</td>
						<td class="admin_bgclor_f1f">
							<input name="dbMaintenance.pact" type="text" id="pact"  value="${dbMaintenance.pact }"/>
						</td>
						<td class="admin_bgclor_e3f ">
							维护日期
							<span class="admin_clor_f00">*</span>
						</td>

						<td class="admin_bgclor_f1f">
							<input type="text" readonly="readonly" class="Wdate" name="dbMaintenance.servicetime" id="sDate1" size="14"
								style="width:148px" value="<fmt:formatDate value="${dbMaintenance.servicetime }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" />
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">

						<td class="admin_bgclor_e3f ">
							维护保养单位
							<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input name="dbMaintenance.company" type="text" id="company"  value="${dbMaintenance.company }"/>
						</td>
						<td class="admin_bgclor_e3f">
							备注
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" name="dbMaintenance.remark" id="remark"  value="${dbMaintenance.remark }"/>
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
						<td class="admin_bgclor_e3f">
							维护内容
						</td>
						<td colspan="3" class="admin_bgclor_f1f">
							<textarea rows="5" cols="70" name="dbMaintenance.content" id="content">${dbMaintenance.content}</textarea>
						</td>
					</tr>
				</table>

				<table width="970" border="0" class="gai_left_duiqi">
					<tr>
					    <td width="90%">
					
				    	</td>
						<td width="65">
						    <a onclick="saveButtonClick();" class="btn blue">保存</a>
						</td>

						<td width="65">
						    <a href="javascript:history.go(-1);" class="btn blue">取 消</a>																
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
