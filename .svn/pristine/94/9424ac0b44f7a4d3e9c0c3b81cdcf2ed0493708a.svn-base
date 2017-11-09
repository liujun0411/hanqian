<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<script src="manager/js/annu.js" type="text/javascript">
</script>
<script type="text/javascript"
	src="manager/common/equip/js/jquery-1.3.2.min.js">
</script>


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
</style>
		<script type="text/javascript">
		 
		//移除设备 
		removeKeyEq=function(equipId){
			
			var obj =document.getElementById("eqlist_str");
			var str=obj.value;
			var eqList=obj.value.split(";");
			var eq;
			for(var i=0;i<eqList.length;i++){
				eq = eqList[i];
				if(equipId==eq.split("=")[0]){
					str = str.replace(eq+";","");
					str = str.replace(eq,"");
<%--					str=str.replace(eq,"");--%>
<%--					if(i==0){--%>
<%--						str=str.substring(1,str.length);--%>
<%--					}else if(i== eqList.length-1){--%>
<%--						str=str.substring(0,str.length-1);--%>
<%--					}else{--%>
<%--						str=str.replace(";;",";");--%>
<%--					}--%>
					obj.value=str;
					
					try{

						window.frames['eqtypeifr'].frames['eqiframe'].document.getElementById("eqstr").value=str;
					}catch(e){}
					break;
				}
			}
		}
		var judgeGroupName = function(){
			var groupName = document.getElementById("groupNameipt").value;
			var groupId = document.getElementById("groupId").value;
			var groupName2 = document.getElementById("groupName").value;
			var sqlStr = document.getElementById("eqlist_str").value;
			//添加
			if(groupId==null||groupId==""){
				 $.ajax({
					  type: "POST",
					  url: "keyEqAction_judgeIsUnique.action",
					  dataType: "json",
					  data:{
							groupName:groupName,
							groupId:groupId
							},
					  cache: false,
					  async:false,
					  error: function(jsonObj){  
					  		},
					  success: function(jsonObj) {
					  		var result = jsonObj.result;
					  		if(groupName2.trim()!=groupName.trim()){
					  			if(result==false){
							  		alert("该组名已经存在!");
							  		document.getElementById("groupNameipt").value="";
							  		document.getElementById("groupNameipt").focus();
					  			}else{
				  					document.getElementById("groupName").value=groupName;
				  					document.getElementById("sqlStr").value=document.getElementById("eqlist_str").value;
						        	document.getElementById("myform").submit();
					  			}
					        }else{
			  					document.getElementById("groupName").value=groupName;
			  					document.getElementById("sqlStr").value=document.getElementById("eqlist_str").value;
					        	document.getElementById("myform").submit();
					        }
					  	}
					 });
				
			}else{
				//修改
				 $.ajax({
						
					  type: "POST",
					  url: "keyEqAction_judgeIsUnique.action",
					  dataType: "json",
					  data:{
							groupName:groupName,
							groupId:groupId
							},
					  cache: false,
					  async:false,
					  error: function(jsonObj){  
					  		},
					  success: function(jsonObj) {
					  		var result = jsonObj.result;
					  		if(groupName2.trim()==groupName.trim()){
			  					document.getElementById("groupName").value=groupName;
			  					document.getElementById("sqlStr").value=sqlStr;
					        	document.getElementById("myform").submit();
					  			
					        }else{
					        	if(result==false){
					        		alert("该组名已经存在!");
							  		document.getElementById("groupNameipt").value="";
							  		document.getElementById("groupNameipt").focus();
					        	}else{
				  					document.getElementById("groupName").value=groupName;
				  					document.getElementById("sqlStr").value=sqlStr;
						        	document.getElementById("myform").submit();
					        		
					        	}
					        }
					  	}
					 });
			}
			 
			 
		};

		//提交表单
		saveKeyEq=function(){
			var groupName = document.getElementById("groupNameipt").value;
			var sqlStr = document.getElementById("eqlist_str").value;
			var groupId = document.getElementById("groupId").value;
			
			if(groupName == null || groupName == ""){
				alert("请输入组名！");
				document.getElementById("groupNameipt").focus();
				return;
			}
			if(sqlStr == null || sqlStr == ""){
				alert("请选择所要分组的设备！"); 
				return;
			}
			//判断组名称是否已经存在
			judgeGroupName();
		}
		
	</script>
	</head>

	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">

		<!-- form 表单 -->
		<form method="post" action="keyEqAction_updateGroupList.action" name="myform"
			id="myform">
			<input type="hidden" id="groupName" name="groupName" value="${groupName}" />
			<input type="hidden" id="sqlStr" name = "sqlStr" value = "" />
			<input type="hidden" id="groupId" name="groupId" value="${groupId}" />
		</form>

		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="780" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							关键设备——编辑关键设备组
						</td>
					</tr>
				</table>
			</div>

			<div class="settingtable">
				<div
					style="width: 96%; text-align: left; font: bold 14px Verdana; padding: 15px 0 4px 2px"
					class="addr_line">
					新建关键设备组&nbsp;
				</div>
				<div class="addr_line">
					<input type="hidden" id="eqlist_str" />
					<table width="96%" border="0" cellspacing="0" cellpadding="3"
						align="center" style="font-weight: lighter;">
						<tr>
							<td colspan="4" align="left"
								style="padding: 20px 0 0 21px; font-size: 14px;">
														组名
								<br />
								<c:if test="${empty groupName || groupName== 'null'}">
									<input type="text" class="txt" id="groupNameipt" name="groupName" size="36" maxlength="8" />
								</c:if>
								<c:if test="${!empty groupName && groupName != 'null'}">
									<input type="text" class="txt" id="groupNameipt" name="groupName" size="36" maxlength="8" value="${groupName}" />
								</c:if>
								<font class="txt_red">*</font>
							</td>
						</tr>
						<tr>
							<td align="left"
								style="padding: 0px 0 0 21px; width: 221px; font-size: 12px;font-weight: bold;">
								设备列表
								<div class="lm_dlg mydiv">
									<!-- 设备类型 -->
									<iframe src="manager/keyEqAction_showEqTypeList.action" name="eqtypeifr" id="eqtypeifr"
										scrolling="no" marginwidth="0" marginheight="0"
										class="myiframe" frameborder="0"></iframe>
								</div>
							</td>
							<td width="20" valign="middle" align="center">
								<img src="manager/images/imgs/arrow_left.gif" width="12"
									height="16" border=0 alt="" />
							</td>
							<td align="left" style="width: 221px; font-size: 12px;font-weight: bold;">
								已有设备
								<div id="mydiv" class="lm_dlg mydiv" style="font-weight:normal;">
							
								</div>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="height: 30px;"></td>
						</tr>
					</table>
				</div>
			</div>
			<table width="96%" border="0" cellspacing="0" cellpadding="2"
				align="center">
				<tr>
					<td align="right" width="65" style="padding: 0px 0px 0px 10px;">
						<a onclick="saveKeyEq();" href="javascript:void(0);"
							class="btn blue">保存</a>
					</td>
					<td align="left" width="60">
						<a onclick="location.href='manager/keyEqAction_showGroupList.action';"
							href="javascript:void(0);" class="btn blue">取消</a>
					</td>
					<td></td>
				</tr>
			</table>
		</div>





		<script type="text/javascript">
var eqlist_str = '';
var groupId = document.getElementById("groupId").value;
 if(groupId!=null){
	 eqlist_str = '${sqlStr}';
 }
if ('' != eqlist_str) {
	document.getElementById("eqlist_str").value = eqlist_str;
	var odiv = document.getElementById('mydiv');
	var str = '';
	var eqs = "${eqList}".split(";");
	var tmp;
	var equipId;
	var equipName;
	for ( var i = 0; i < eqs.length; i++) {
		tmp = eqs[i].split("=");
		equipId = tmp[0];
		equipName = tmp[1];
		if(equipId==''){
			str += "";
		}else{
			str += '<div id="v'
					+ equipId
					+ '" style="font-size: 12px;color:#3399FF;" onmouseover="this.style.color=\'#FF6600\';" '
					+ 'onmouseout="this.style.color=\'#3399FF\';">'
					+ '<table width="100%" border="0" ><tr><td>'
					+ equipName
					+ '</td><td width="30"><img src="manager/images/imgs/icon_del.gif" width="12" height="12"'
					+ ' border=0 title="删除" style="cursor: pointer;" onclick="try{document.getElementById(\'mydiv\')'
					+ '.removeChild(document.getElementById(\'v' + equipId
					+ '\'));removeKeyEq(\'' + equipId
					+ '\');}catch(e){}" /></td></tr></table></div>';
		}
	}
	odiv.innerHTML = str;
}
</script>


	</body>
</html>

