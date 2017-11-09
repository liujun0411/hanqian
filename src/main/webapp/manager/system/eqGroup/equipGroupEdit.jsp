<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript" ></script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
		<script src="manager/javascript/previewPic/picShow.js" type="text/javascript"> </script>
		<script src="manager/config/config.js" type="text/javascript"> </script>
		<style type="text/css">
.toolbg {
	background: #aac9ea;
}

.toolbgline {
	padding: 6px 0px 6px;
	background-image: none;
	border-bottom: 1px solid #aac9ea;
}

.lm_dlg {
	background: #fff;
	overflow: hidden;
	width: 100px;
	height: 340px;
}

.txt_red {
	color: #C00;
}

.txt {
	border: 1px solid #718da6;
	height: 20px;
	padding-top: 2px;
}

.addr_line {
	font-size: 14px;
	font-weight: bold;
	border-bottom: 2px solid #aac9ea;
}

.myiframe {
	width: 290px;
	height: 280px;
}

.mydiv {
	border: 1px solid #aac9ea;
	width: 290px;
	height: 280px;
}

.settingtable {
	padding: 30px 9px 7px;
	border-top: 1px solid #fff;
}

ul {
	list-style: none;
}

ul,li {
	margin: 0px;
	padding: 0px;
	width: 98%;
}

li {
	margin-left: 10px;
	cursor: pointer;
}
</style>

		<script type="text/javascript">
		 //判断file后缀名
		function checkFileHz(imageFile){
			if($("#groupPic").val()&&$("#group_pic").val()!=''&&$("#group_pic").val()!=null&&$("#groupPic").val()&&$("#groupPic").val()!=''){
				var isdel=window.confirm("上传当前图纸会替换之前图纸，确定做此操作");
				if(!isdel){
					$("#groupPic").val('');
					return;
				}
			}
			if(imageFile&&imageFile!=''){
				var ext = imageFile.substring(imageFile.lastIndexOf('.'),imageFile.length);
				extArray = new Array(".jpg",".png",".dwg",".pdf");
				var check=false;
				for (var i = 0; i < extArray.length; i++) {
						if(extArray[i].toLowerCase()==ext.toLowerCase()){
							check=true;
							break;
						}
				}
				if(!check){
					alert("对不起，只能上传以下格式的文件:  .dwg - .jpg - .png "+extArray.length+'种格式文件'
					+ "\n请重新选择符合条件的文件"
					+ "再上传.");
					return false;
				}
			}				
			return true;
	}	
	
		var selType = "";//记录选择的设备类型
		var eqType="";
		//记录之前点击的Id
		var zhiqian;
		//移除设备 
		removeEquip=function(obj){
			$(obj).parent().remove();
			var equipId =  $(obj).parent().attr("id");
			var equipName =  $(obj).parent().text();
			movelist = "<li id='"+equipId+"' onclick='chenge(this.id)' ondblclick='moveList()'>"
	  			       +equipName+"</li>";
				//在列表中不显示
			$('#eqList').append(movelist);
			gotoValue();
		}

		//提交表单
		saveGroupEq=function(){
			gotoValue();
			$("#build_id").val($("#buildSel").val());
			var grname = $('#grName').val();
			
			if(grname==null||grname==""||$.trim(grname)==''){
				alert("请输入\"组名\"!");
				$("#grName").val('');
				$("#grName").focus();
				return;
			}
			if($('#selEquip li').length==0){
				alert("请选择设备!");				
				return;
			}
			
			$("#groupName").val($('#grName').val());
			if($("#resultStr").val()=="add"){
				if(checkFileHz($("#groupPic").val())){
					$("#myform").submit();
				}
			}else{
				if(checkFileHz($("#groupPic").val())){
					if($("#groupPic").val()==''){
						$("#isChangeFlag").val(1);
					}
					$("#myform").attr({action:'equipGroupAction!updateGroupEquip.action'})
					$("#myform").submit();
				}
			}
			
		}
		
		//赋值
		gotoValue = function(){
			var lis = $('#selEquip li');
			//得到所有的已选择设备Id
			var equip = "";
			$('#selEquip li').each(function(i,obj){
				equip+=$(obj).attr("id");
				if(i<$('#selEquip li').length-1){
					equip+=","
				}
			});
			//shebeiID
			$('#equipId').val(equip);
			if(equip==""){
				eqType = "";
			}
		}
	
		//添加到设备列表中l
		var beforeSle = '';
		getEquipList = function(type){
			var id = $("#sle").val();
			
			//selType 设备类型
			//eqType 设备类型
			selType=id;
			var buildId = $("#buildSel").val();
			//判断是否改变了设备类型
			$("#equipType").val(id);
			if(type == 'type'){
				if(id != 3&&id != 4 &&id != 5&&id != 6){
					buildId = '';
					$("#buildSel").get(0).selectedIndex = 0;
					$("#buildSel").hide();
					$("#buildSel").prev().hide();
					$("#buildSel").next().hide();
				}else{
					$("#buildSel").get(0).selectedIndex = 0;
					$("#buildSel").show();
					$("#buildSel").prev().show();
					$("#buildSel").next().show();
				}
			}
			if(eqType!="" ){
				if(eqType!=selType){
					var alertStr = "";
					if(type=='type'){
						alertStr = '只能在一个设备类型中分组，当前操作会删除已选择的设备！';
					}else if(type=='build'){
						alertStr = '只能在一个楼宇中分组，当前操作会删除已选择的设备！';
					}
					var isdel=window.confirm(alertStr);
					if(isdel){ 
						$('#equipId').val('');
						eqType='';
						$('#selEquip li').remove();
					}else{
						$("#sle").val(beforeSle);
						$("#sle option").each(function(){
							if($(this).val()==beforeSle){
								$(this).selected = true;
							}
						});
						return;
					}
				}else{
					var alertStr = '';
					if(type=='build'){
						 alertStr = '只能在一个楼宇中分组，当前操作会删除已选择的设备！';
					}else{
						 alertStr = '只能在一个设备类型中分组，当前操作会删除已选择的设备！';
					}
					var isdel=window.confirm(alertStr);
					if(isdel){
						$('#equipId').val('');
						eqType='';
						$('#selEquip li').remove();
					}else{
						$("#sle").val(beforeSle);
						$("#sle option").each(function(){
							if($(this).val()==beforeSle){
								$(this).selected = true;
							}
						});
						
						return;
					}
				}
			}
			//判断
			$.ajax({
  				type:"POST",
	  			url:"manager/equipGroupAction!showEquipList.action?eqtype="+id,
	  			data:{
	  				build_id:buildId
				},
	  			success:function(result) {
		  			if(result&&result!=''){
		  			    var dt=eval('('+result+')');
		  			    var app="";
		  			    $('#eqList li').remove();
	  			    	for(var k=0;k<dt.length;k++){
		  			       app+="<li id="+dt[k].equipId+" onclick='chenge(this.id)' ondblclick='moveList()'>"
		  			       +dt[k].equipName+"</li>";
	  			   		}
	  			    	$('#eqList').append(app);
		  			}else{
		  				 $('#eqList li').remove();
		  			}
	  			},
	  			error:function() {
	  				alert("服务器异常，请稍微再试！");
	  			}
  			});
			beforeSle = id;
		}

		chenge = function(id){
			if(zhiqian!=''){
				$('#'+zhiqian).css("background-color","white");
			}
			$('#'+id).css("background-color","#A5E9E0"); 
			zhiqian=id;
		}
		
		var movelist;
		//把左边选中的设备，在右边添加一个
		moveList = function(){
			var buildId = $("#buildSel").val();
			if($("#buildSel").css("display")!='none'&&(buildId==0||buildId=='')){
				alert("请先选择楼宇");
				return;
			}
			eqType = zhiqian;
		//判断是否选
			if(zhiqian!=null&&zhiqian!=''){
				//判断当前选择的设备，在已选设备中有没有
				if(eqIsExist(zhiqian)){
					alert("同一个设备不能添加两次");
					return;
				}
				movelist = "<li id="+zhiqian+">"
				+$('#'+zhiqian).text()+"<img src='manager/images/imgs/icon_del.gif' width='16' height='16' border=0 title='删除'"+
				" style='cursor: pointer;float:right;' onclick='removeEquip(this)' /></li>";
				//在列表中不显示
				$("#"+zhiqian).remove();
				$('#selEquip').append(movelist);
				gotoValue();
				zhiqian = '';
			}else{
				alert('请选择设备');
			}
		}
		
		
		//判断该设备，是否已经被选 
		eqIsExist=function(eqid){
			var str=$("#equipId").val();
			var eqlist=str.split(",");
			var eq = false;
			for(var i=0;i<eqlist.length;i++){
				if(eqid==eqlist[i]){
					eq = true;
				}
			}
			return eq;
		}
		
	var	showOrHide = function(){
		var id = $("#equipType").val();
		if(id==''||(id != 3&&id != 4 &&id != 5&&id != 6)){
			$("#buildSel").prev().hide();
			$("#buildSel").next().hide();
			$("#buildSel").hide();
		}
	}
	$(document).ready(function(){
		showOrHide();
		});
	</script>

	</head>

	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<!-- form 表单 -->
		 <div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="100%" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							设备分组—— ${resultStr == "add" ? '新增' : '修改' }设备分组&nbsp;
						</td>
					</tr>
				</table>
			</div>
		<s:form method="post" action="equipGroupAction!addGroupEquip.action"
			name="myform" id="myform" enctype="multipart/form-data" >
			<input type="hidden" id="build_id" name="build_id" />
			<input type="hidden" id="groupName" name="groupName" value="${groupName }" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId }" />
			<input type="hidden" id="equipId" name="equipId" value="${equipId }" />
			<input type="hidden" id="equipType" name="equipType" value="${equipType }" />
			<input type="hidden" id="picName" name="picName" value="${picName }" />
			<input type="hidden" id="resultStr" name="resultStr" value="${resultStr }" />
			<input type="hidden" id="isChangeFlag" name="isChangeFlag"  />
			<input type="hidden" id="groupCode" name="groupCode" value="${groupCode }" />
			<div class="settingtable">
	
				<div class="addr_line">
					<input type="hidden" id="eqlist_str" />
					<table width="100%" border="0" cellspacing="0" cellpadding="3"
						align="center" style="font-weight: lighter;">
						<tr>
							<td colspan="4" align="left"
								style="padding: 20px 0 0 21px; font-size: 14px;">
								组名
								<br/>
								<input type="text" class="txt" id="grName" name="grName" 
									size="36" maxlength="15" value="${groupName }" />
								<font class="txt_red">*</font>
									<br/>
									<br/>
								备注
								<br/>
								<!--  update start 2013.4.11 by lg-->
								<input type="text" class="txt" id="groupRemark" name="groupRemark" 
								size="36" maxlength="200"
										 value="${!empty groupRemark && groupRemark != 'null'?groupRemark:'' }" />
								<!-- update start 2013.4.11 by lee -->	
								<!-- 修改备注无法保存，无法显示的问题 -->							
<%--									<input type="text" class="txt" id="grRemark" name="grRemark" 
										size="36" maxlength="200"
										 value="${!empty groupRemark && groupRemark != 'null'?groupRemark:'' }" />--%>
								<!-- update end 2013.4.11 by lee -->
									<br/>
									<br/>
								组图纸
								<s:file theme="simple" name="groupPic" id="groupPic"></s:file>
								<c:if test="${!empty picName}">
									<a href="javascript:void(0);" onclick="javascript:showPic('${picName}');">查看图纸</a>
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="left"
								style="padding: 20px 0 0 21px; width: 221px; font-size: 14px;">
								设备列表
								<div class="lm_dlg mydiv" style="width: 450px;">
									<!-- 下拉列表 -->
									<div
										style="width: 100%; line-height: 30px; border-bottom: 1px solid blue;">
										<span class="myfont">设备类型:</span>
										<select id="sle" class="myfont"
											onchange="getEquipList('type');">
											<option value="0">
												--请选择--
											</option>
											<c:forEach items="${eqtypelist}" var="eqList">
												<option value="${eqList.equiptypeid }"
													${equipType==eqList.equiptypeid?'selected':''}>
													${eqList.equiptypename }
												</option>
											</c:forEach>
										</select>
<%--										<br/>--%>
										<span class="myfont">楼宇名称:</span>
										<select id="buildSel" class="myfont" onchange="getEquipList('build');">
											<option value="0">
												--请选择--
											</option>
											<c:forEach items="${buildList}" var="build">
												<option value="${build.building_id }" ${eBuildId == build.building_id?'selected':''}>
													${build.building_name}
												</option>
											</c:forEach>
										</select>&nbsp;<font class="txt_red">*</font>
									</div>
									<!-- 设备列表-->
									<div style="width: 100%; height: 250px; overflow: auto;">
										<ul id="eqList">
											<c:if test="${! empty equipType}">
												<c:forEach items="${eqList}" var="equip">
													<li id="${equip.equipId}" onclick='chenge(this.id)'
														ondblclick='moveList()'>
														${equip.equipName}
													</li>
												</c:forEach>
											</c:if>
										</ul>
									</div>
								</div>
							</td>
							<td width="20" valign="middle" align="center">
								<img src="manager/images/imgs/arrow_left.gif" width="12"
									height="20" border=0 alt="添加" style="cursor: pointer;"
									onclick="moveList()" />
							</td>
							<td align="left" style="width: 221px;padding: 20px 0 0 21px; font-size: 14px;">
								已有设备
								<div id="mydiv" class="lm_dlg mydiv" style="overflow: auto;width: 350px;" >
									<ul id="selEquip">
									</ul>
								</div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="6" style="height: 30px;"></td>
						</tr>
					</table>
				</div>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="2"
				align="center">
				<tr>
					<td align="right" width="65" style="padding: 0px 0px 0px 10px;">
						<a onclick="saveGroupEq();" href="javascript:void(0);"
							class="btn blue">保存</a>
					</td>
					<td align="left" width="60">
						<a onclick="location.href='equipGroupAction!showEquipGroupList.action';"
							href="javascript:void(0);" class="btn blue">取消</a>
					</td>
					<td></td>
				</tr>
			</table>
			</s:form>
		</div>
	</body>
	
</html>

<script type="text/javascript">
//修改时，显示已有的设备
if(${equiplist!=null}){
	var str = '';
	var eqs = "${equiplist}".split(",");
	eqType = ${equipType}+'';
	$("#equipType").val(eqType);
	var tmp;
	var eqid;
	var eqname;
	for ( var i = 0; i < eqs.length; i++) {
		tmp = eqs[i].split("=");
		eqid = tmp[0];
		eqname = tmp[1];
		str += "<li id="
				+ eqid
				+ ">"
				+ eqname
				+ "<img src='manager/images/imgs/icon_del.gif' width='16' height='16' border=0 title='删除'"
				+ " style='cursor: pointer;float:right;' onclick='removeEquip(this)' /></li>";
	}
	$('#selEquip').append(str);
}
</script>
