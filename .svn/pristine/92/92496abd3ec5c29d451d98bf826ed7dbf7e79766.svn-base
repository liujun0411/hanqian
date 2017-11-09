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
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script src="manager/js/jquery-1.7.2.js" type="text/javascript">
</script>
		<script src="manager/javascript/previewPic/picShow.js"
			type="text/javascript">
</script>
		<script src="manager/config/config.js" type="text/javascript">
</script>
		<style type="text/css">
.toolbg {
	background: #aac9ea;
}
.btn11{width:50px; height:30px; margin-top:10px; cursor:pointer;}
.toolbgline {
	padding: 6px 0px 6px;
	background-image: none;
	border-bottom: 1px solid #aac9ea;
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
.selectUlLi{
	background-color: lightgreen;
	}
.noselectUlLi{
	background-color: none;
	}
/*去掉下拉框滚动条，内容超出时自动加滚动条*/
/*1.增加body-->style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;"*/
/*2.给slect对应外面的td上面的class=中添加lm_dlg*/
/*3.给slect的style=中添加overflow: auto;*/
.lm_dlg {
	overflow: hidden;
}
.button_css{width:49px; height:auto;font-size: 13pt;margin: 7px;}

</style>

		<script type="text/javascript">
var ff ;
		$(document).ready(function(){
			//appendClassForUlLi();
			//给复选框赋默认值
			// $("input[name='repairLevels']").attr("checked","checked");
			 gaojingjibies();
		});
	//移到右边(设备)
	function select_add(){
        $('#select1 option:selected').appendTo('#select2');
	}
	//全部移到右边(设备)
    function select_add_all(){
        $('#select1 option').appendTo('#select2');
	}
    //移到左边(设备)
     function select_remove(){
         $('#select2 option:selected').appendTo('#select1');
	}
    //全部移到左边(设备)
	function select_remove_all(){
        //获取全部的选项,删除并追加给对方
         $('#select2 option').appendTo('#select1');
	}
	//双击选项(设备)right
	function  select_dblclick_right(){
	   $("option:selected",$('#select1')).appendTo('#select2'); //追加给对方
	}
		//双击选项(设备)left
	function  select_dblclick_left(){
	   $("option:selected",$('#select2')).appendTo('#select1'); //追加给对方
	}
     //移到右边(人员)
    function user_select_add(){
        $('#select3 option:selected').appendTo('#select4');
    }
    //移到左边(人员)
    function user_select_remove(){
        $('#select4 option:selected').appendTo('#select3');
    }
    //全部移到右边(人员)
    function user_select_add_all(){
        //获取全部的选项,删除并追加给对方
        $('#select3 option').appendTo('#select4');
    }
        //全部移到左边(人员)
     function user_select_remove_all(){
        $('#select4 option').appendTo('#select3');
        //移动到左边需清空数组里面所用的值
    }
     //双击选项(人员)right
     function user_select_dblclick_right(){
       $("option:selected",$('#select3')).appendTo('#select4'); //追加给对方
     }
     //双击选项(人员)left
     function user_select_dblclick_left(){
       $("option:selected",$('#select4')).appendTo('#select3');
     }
function setEqDl() {
     var selectEquid="";
     var selectUserId="";
	//获取选中的设备编号
	  $('#select2 option').each(function(){
           selectEquid+=$(this).val()+",";
        })
	  $('#select4 option').each(function(){
           selectUserId+=$(this).val()+",";
        })
    //已有设备列表
    $("#equipId").val(selectEquid);
	//已有维修人员
	$("#detailId").val(selectUserId);
}
function addSubmit() {
    setEqDl();
	if ($('#groupName').val() == '') {
		alert('维修班组名不能为空');
		return ;
	}else{
	       //提示未选维修人员，发送告警未保存
	       //判断第4个option的值是否为空，为空的信息进行提示
	       var selEquip1Num=$("#select4 option").length;
			if(selEquip1Num==0){
			   alert("维修人员未选,发送告警级别未保存");
			}
		  tixingjibie();
		  var aArray = {};//定义一个数组
	      var repair;
	      var repairs="";
		  $("input[name='repairLevels']:checked").each(function(i){
	          if($(this).attr("checked")){
	              repair=$(this).attr("value")+",";
	              repairs+=repair;
	          }
	     });
	     $("#repairLevel").val(repairs);
		 $("#myform").submit();
	}
}
function tixingjibie(){
  if($("input[name='repairLevels']:checked").length<=0){
			   alert("发送告警级别未选,将无法收到告警短信!");
		    }
}
function updateSubmit() {
	setEqDl();
	if ($('#groupName').val() == '') {
			alert('维修班组名不能为空');
		return ;
	}else{
	   //提示未选维修人员，发送告警未保存
		var selEquip1Num=$("#select4 option").length;
		if(selEquip1Num==0){
		   alert("维修人员未选,发送告警级别未保存");
		}
		var updateFlag=$("#updateFlag").val();
		if("11"==updateFlag){
			tixingjibie();
		} 
	var aArray = {};//定义一个数组
	      var repair;
	      var repairs="";
		  $("input[name='repairLevels']:checked").each(function(i){
	          if($(this).attr("checked")){
	              repair=$(this).attr("value")+",";
	              repairs+=repair;
	          }
	     });
	     $("#repairLevel").val(repairs);
		var frm = $("#myform");
		frm.attr('action', 'serMain!updateSerMain.action');
		frm.submit();
	}
}

function gaojingjibies(){
//11==updateFlag判断是修改还是添加的功能
//告警级别那几个复选框
var ff = $("#gaojingjibie").val();
var updateFlag=$("#updateFlag").val();
if("11"==updateFlag){
 if(ff){
 $("input[name='repairLevels']").attr('checked',false);
	var myArray=new Array();
	myArray = ff.split(",");
	for(var i=0;i<myArray.length;i++){
	  $("input[name='repairLevels']").each(
				   function(){
				        if(myArray[i]==$(this).attr("value")){
						    $(this).attr('checked',true);
						}
						
				   }
				);
      }
     } else{
         $("input[name='repairLevels']").attr("checked",false);
     }
   }
}
//根据设备类型选择
//具体的设备列表
function getEquipList(obj) {
	//判断
	$.ajax( {
				type : "POST",
				url : "manager/equipGroupAction!showEquipList.action?eqtype="
						+ $(obj).val(),
				success : function(result) {
					if (result && result != '') {
						var dt = eval('(' + result + ')');
						var app = "";
						$('#select1').find("option").remove();
						for ( var k = 0; k < dt.length; k++) {
							app += "<option value='"
									+ dt[k].equipId
									+ "' >"
									+ dt[k].equipName + "</option>";
						}
						$('#select1').append(app);
					} else {
						$('#select1').find("option").remove();
					}
				},
				error : function() {
					alert("服务器异常，请稍微再试！");
				}
			});
}
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
							维修班组—— ${empty update?'添加班组':'维修班组'}&nbsp;
						</td>
					</tr>
				</table>
			</div>
			<s:form method="post" action="serMain!addSerMain.action"
				name="myform" id="myform" enctype="">
				<input type="hidden" id="equipId" name="equipId" value="${equipId }" />
				<input type="hidden" id="detailId" name="detailId" value="${detailId }" />
				<input type="hidden" id="groupId" name="groupId" value="${groupId }" />
				<input type="hidden" id="repairLevel" name="repairLevel" value="${repairLevel}" />
				<input type="hidden" id="gaojingjibie" name="gaojingjibie" value="${gaojingjibie.repairlevel}" />
				<input type="hidden" id="updateFlag" name="updateFlag" value="${updateFlag}" />
				<div class="settingtable">
					<div class="addr_line">
						<input type="hidden" id="eqlist_str" />
						<table width="100%" border="0" cellspacing="0" cellpadding="3"
							align="center" style="font-weight: lighter;">
							<tr>
								<td colspan="2" align="left" class="shebeigl_inp_zt"
									style="padding: 20px 0 0 21px; width: 30%;font-size: 14px;">
									组名:
									<input type="text" class="txt" id="groupName" name="groupName"
										size="36" maxlength="15" value="${groupName }" />
									<font class="txt_red">*</font>

								</td>
								<td colspan="2" align="left" class="shebeigl_inp_zt"
									style="padding: 20px 0 0 21px; font-size: 14px;">
									发送告警级别:
									<input type="checkbox" name="repairLevels"  value="1" checked="checked" />1级
									<input type="checkbox" name="repairLevels"  value="2" checked="checked" />2级
									<input type="checkbox" name="repairLevels"  value="3" checked="checked" />3级
								</td>
							</tr>
							<tr>
								<td align="left" class="shebeigl_inp_zt"
									style="padding: 20px 0 0 21px; width: 30%; font-size: 14px;">
									设备列表
								</td>
						        <td style="width:10%;"></td>
						        <td class="shebeigl_inp_zt" class="shebeigl_inp_zt" style="padding: 20px 0 0 21px; width: 30%; font-size: 14px;">已有设备</td>
						        <td></td>
							</tr>
							<tr>
							<td align="left" class="shebeigl_inp_zt" style="padding-left: 20px; width: 30%; font-size: 14px;">
										<!-- 下拉列表 -->
										<div
											style="width: 100%;background:#fff; line-height: 30px; border-bottom: 1px solid blue;">
											<span class="myfont">设备类型:</span>
											<select id="sle" class="myfont lm_dlg"
												onchange="getEquipList(this);">
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
										</div>
				                       <select multiple="multiple" class="lm_dlg" id="select1"  ondblclick='select_dblclick_right()' name="select1" style="width:325px;height:250px;float:left; border:0px; padding:4px;font-size: 14px;font-weight: bold;color: #0066FF;overflow: auto;">
								       </select>
							</td>
							<td align="center" style="width:10%;">
								          <div style="float:left;margin: 0 auto;width: 100%;"> 
								             <input type="button" class="button_css" onclick="select_add()"  value=">"/>
								           <br/>
								             <input type="button" class="button_css" onclick="select_add_all()"  value=">>"/>
								           <br/>
								              <input type="button" class="button_css" onclick="select_remove()"  value="<"/>
								           <br/>
								              <input type="button" class="button_css" onclick="select_remove_all()"  value="<<"/>
								           <br/>
						                  </div>
						                  <div style="clear:both"></div>
							</td>
							<td  align="left" class="shebeigl_inp_zt lm_dlg" style="padding: 0px 0 0 21px; width: 49%; font-size: 14px;">
							<!-- 这个是已有设备的 -->
								<select multiple="multiple" id="select2"  ondblclick="select_dblclick_left()" style="width:325px;height:280px; float:left; border:0px #A0A0A4 outset; padding:4px; font-size: 14px;font-weight: bold;color: #0066FF;overflow: auto;">
								           <c:if test="${! empty serMainEquipList}">
													<c:forEach items="${serMainEquipList}" var="equip">
													 <option value="${equip.equip_id}" >${equip.equip_name}</option>
													</c:forEach>
								           </c:if>
								</select>
							</td>
							<td></td>
							</tr>
							<tr>
								<td align="left" class="shebeigl_inp_zt"
									style="padding: 20px 0 0 21px; width: 30%; font-size: 14px;">
								维修人员列表</td>
						        <td style="width:10%;"></td>
						        <td align="left" class="shebeigl_inp_zt"
									style="padding: 20px 0 0 21px; width: 30%; font-size: 14px;">已有维修人员</td>
						        <td></td>
							</tr>
							<tr>
							<td align="left" class="shebeigl_inp_zt lm_dlg" style="padding-left: 20px; width: 30%; font-size: 14px;">
				                       <select multiple="multiple" id="select3"  ondblclick="user_select_dblclick_right()" name="select3" style="width:325px;height:250px;float:left; border:0px #A0A0A4 outset; padding:4px;font-size: 14px;font-weight: bold;color: #0066FF;overflow: auto;">
								         <c:if test="${! empty sermainDetailList}">
													<c:forEach items="${sermainDetailList}" var="e">
														<option value="${e.seq}">
															${e.mem_name}
														</option>
													</c:forEach>
												</c:if>
								       </select>
							</td>
							<td align="center" style="width:10%;">
								          <div style="float:left;margin: 0 auto;width: 100%;"> 
								             <input type="button" class="button_css"   onclick="user_select_add()" value=">"/>
								            <br/>
								             <input type="button" class="button_css" onclick="user_select_add_all()" value=">>"/>
								            <br/>
								              <input type="button" class="button_css" onclick="user_select_remove()"  value="< " />
								            <br/>
								              <input type="button" class="button_css" onclick="user_select_remove_all()" value="<< " />
								            <br/>
						                   </div>
							</td>
							<td align="left" class="shebeigl_inp_zt lm_dlg" style="padding-left: 20px; width: 30%; font-size: 14px;">
							<!-- 这个是已有的维修人员的 -->
								<select multiple="multiple" id="select4" ondblclick="user_select_dblclick_left()" style="width:325px;height:250px;float:left; border:0px #A0A0A4 outset; padding:4px;font-size: 14px;font-weight: bold;color: #0066FF;overflow: auto;">
								           <c:if test="${! empty repairList}">
													<c:forEach items="${repairList}" var="e">
													  <option value="${e.seq}">${e.mem_name}</option>
													</c:forEach>
								           </c:if>
								</select>
							</td>
							<td></td>
							</tr>
						</table>
					</div>
				</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="2"
					align="center">
					<tr>
					    <td colspan="3"></br></td>
					</tr>
					<tr>
						<td align="right" width="64%">
							<c:if test="${empty update}">
							    <a href="javascript:void(0);" onclick="addSubmit();" class="btn blue">保 存</a>	
							</c:if>
							<c:if test="${!empty update}">
							<a href="javascript:void(0);" onclick="updateSubmit();" class="btn blue">修 改</a>	
							</c:if> 
						</td>
						<td align="left" width="60">
						<a href="javascript:void(0);" onclick="javascript: window.location.href='serMain!findSerMain.action';" class="btn blue">取 消</a>	
						</td>
						<td></td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>

</html>


</script>
