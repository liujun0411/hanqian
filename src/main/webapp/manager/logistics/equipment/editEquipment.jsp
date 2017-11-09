<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>医院后勤智能化管理平台——后勤管理</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet"
			type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<!-- 引入公用控件 -->
        <script type="text/javascript" src="manager/common/equip/js/jquery-1.3.2.min.js"></script>
		<link  href="manager/common/equip/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerComboBox.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerResizable.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerTree.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/createTree.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/equipType.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery.validate.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script src="manager/javascript/equipment/equipment.js" type="text/javascript"></script>
	  
		
	     <script type="text/javascript">
          /*验证方法*/
		  $(function(){
				getElem("input[type='text']");
				getElem("select");
		  });
		  var getElem = function(ele){
		   $('.admin_clor_f00').parent().next().find(ele).blur(function(){
		         if($.trim($(this).val())==""){
		            $(this).parent().find('.error').remove();
		            var tip=$(this).parent().prev().text();
		            if(tip.indexOf("*")>0){
		               tip=tip.substring(0,tip.indexOf("*"));
		            }
		            $(this).parent().append("<span  class='error'><font color='red'>"+tip+"不能为空!</font></span>");
		         }else{
		            $(this).parent().find('.error').remove();
		         }
		      });
		  }
		  

		  
		  //采购价和服务面积之只能为数字
		  function isNumber(){
			  var flag=true;
			  var purchase =$("#purchase").val();
			  var serviceSquare = $("#serviceSquare").val();
			  //最多可以输入小数点后面2位
			  var p =/^[1-9](\d+(\.\d{1,2})?)?$/; 
			  var p1=/^[0-9](\.\d{1,2})?$/;
			  if(purchase){
				  //var parnt = /^[0-9]*$/; 
				  //if(!parnt.test(purchase)){ 
					 if(!p.test(purchase)&&!p1.test(purchase)){
					  $("#purchase").attr("value","");
					  flag=false;
					  //return false; 
				   }
			  }
			  if(serviceSquare){
				  if(!p.test(serviceSquare)&&!p1.test(serviceSquare)){
					  $("#serviceSquare").attr("value","");
					  flag=false;
					  //return false; 
				   }
			  }
			  return flag;
		  }
		  function bbsd(){
			  var flag=true;
			  for(var b=1;b <= 5;b++){
				  var serviceSquarebb = $("#serviceSquare"+b).val();
				  if(serviceSquarebb){
					     var p =/^[1-9](\d+(\.\d{1,2})?)?$/; 
						 var p1=/^[0-9](\.\d{1,2})?$/;
						if(!p.test(serviceSquarebb)&&!p1.test(serviceSquarebb)){
							$("#serviceSquare"+b).attr("value","");
						   return false;
						}
				  }
			  }
			  return flag; 
		  }
		  var totalCount = function(ele,errorCount){
		 	 for(var i=0;i<$('.admin_clor_f00').parent().next().find(ele).length;i++){
		         if(jQuery.trim($('.admin_clor_f00').parent().next().find(ele).eq(i).val())==""){
		            $('.admin_clor_f00').parent().next().find(ele).eq(i).parent().find('.error').remove();
		            var tip=$('.admin_clor_f00').parent().next().find(ele).eq(i).parent().prev().text();
		            if(tip.indexOf("*")>0){
		               tip=tip.substring(0,tip.indexOf("*"));
		            }
		           $('.admin_clor_f00').parent().next().find(ele).eq(i).parent().append("<span class='error'><font color='red'>"+tip+"不能为空!</font></span>");
		            errorCount++;
		         }
		     }
		     return errorCount;
		  }
		  function valid(){
		     var errorCount=0;
		     errorCount = totalCount("input[type='text']",errorCount);
			 errorCount = totalCount("select",errorCount);
//			 if($('input[name="controlFlag"]:checked').length<1){
//		      	errorCount++;
//		      	$('input[name="controlFlag"]').append("<span class='error'><font color='red'>监控标识不能为空!</font></span>");
//		     }
		     if(errorCount==0){
			      return true;
		     }else{
		       return false;
		     }
		  }
         
         function saveButtonClick(){
//        	 var flag = false;
//				for(var i = 0; i < document.addEquipFrom.controlFlag.length; i++){
//					 if(document.addEquipFrom.controlFlag[i].checked){
//					 	   flag = true; 
//					 	    break; 
//					 	    }
//					 	  }
//					 	  if(!flag){
//					 	   		 $("#radioCheck").html("<span class='error'>" +
//					 	   		 "监控标识不能为空!" +
//					 	   		 "</span>"); 
//					 	   		}else{
//					 	   			$("#radioCheck").html("<span class='error'></span>"); 
//					 	   		}
            if(valid()&&isNumber()&&bbsd()){
               $('#addEquipFrom').submit();
            }
         }
         
         $(function(){
			//鼠标离开设备编号输入框，验证设备编号是否已存在
			$('#equipCode').blur(function(){
			   var equipCode=$('#equipCode').val();
			   var equipId=$('#equipId').val();
			   if(jQuery.trim(equipCode)!=''){
				   var dt={"equipCode":equipCode,"equipId":equipId};
				   $.ajax({
				      type: 'get',
					  url: "equipment_checkEquipCode.action",
					  data: dt,
					  cache:false,
					  dataType: 'json',
					  success: function(data){
					     var objdt=eval(data);
					     var result=objdt[0].res;
					     $('#equipCode').next().remove();
					     if(result=='has'){
					         $('#equipCode').parent().append('<span class="error"><font color="red">该设备编号已存在！</font></span>');
					     }else{
					        $('#equipCode').parent().append('<span  class="error" >设备编号可用！</span>');
					     }
					  },
					  error:function(textStatus){
					  }
				    });
				  }
			   });
			
				//新增电表参数
			if($("#eqTypeId").val()!=null&&$("#eqTypeId").val()!=''){
				if($("#eqTypeId").val()=='10001'){
					$("#elecOption").removeAttr("hidden");
				}
			}
           });
         
          //改变楼宇，加载对应的楼层
         function changeBuild(obj,childId){
            var buildId=$(obj).val();
            if(jQuery.trim(buildId)!=""){
               var dt={"buildId":buildId};
               $.ajax({
				      type: 'get',
					  url: "equipment_findStoreyByBuildId.action",
					  data: dt,
					  cache:false,
					  dataType: 'json',
					  success: function(data){
					     var arrayObj = new Array();//创建一个数组
					     var storey=eval(data);
					     var cid=$(childId).attr('id');
					     $("'#"+cid+"' option").remove();
					     $(childId).append("<option value=''>--请选择所在楼层--</option>");
					     for(var k=0;k<storey.length;k++){
					       $(childId).append("<option value='"+storey[k]+"'>"+String(storey[k]).replace("-",'B')+"</option>");
					     }
					  },
					  error:function(textStatus){
					      alert('error'+textStatus);
					  }
				 });
             }else{
                 var cid=$(childId).attr('id');
				 $("'#"+cid+"' option").remove();
			     $(childId).append("<option value=''>--请选择所在楼层--</option>");
             }
         }
         
          //设备类型菜单回调函数
		   function doClick(note){
		      $('#equipTypeId').val(note.data.text);
		      $('#eqTypeId').val(note.data.id);
		      $("#listDiv_List").hide();
		      //电表新增参数
				if($("#eqTypeId").val()!=null&&$("#eqTypeId").val()!=''){
					if($("#eqTypeId").val()=='10001'){
						$("#elecOption").removeAttr("hidden");
					}else{
						$("#elecOption").attr("hidden",true);
					}
				}
		   }
		    	function addappendChild(obj) {
				if($("#addfwqyBtn").text() == "添加"){
				    var tabtrL = $("#serAreatable tr").length/2;
					if (tabtrL<5) {
						var tab = $("#serAreatable");
						var trStr = new Array();
						trStr.push('<tr>');
						trStr.push('			<td>');
						trStr.push('			楼宇');
						trStr.push('			<select id="serviceBuild'+tabtrL+'" name="serviceBuild'+tabtrL+'" style="width: 150px;"  onchange="changeBuild(this,serviceStorey'+tabtrL+')">');
						trStr.push('				<option value="">--请选择--</option>');
						trStr.push('				<c:forEach items="${listBuilding}" var="building">');
						trStr.push('					<option value="${building.buildingId}" >');
						trStr.push('                       ${building.buildingName}');
						trStr.push('					</option>');
						trStr.push('				</c:forEach>');
						trStr.push('			</select>');
						trStr.push('			</td>');
						trStr.push('			<td>');
						trStr.push('			楼层');
						trStr.push('			<select id="serviceStorey'+tabtrL+'" name="serviceStorey'+tabtrL+'" style="width: 150px;">');
						trStr.push('				<option value="">--请选择--</option>');
						trStr.push('			</select>');
						trStr.push('			</td>');
						trStr.push('			<td>');
						trStr.push('			区域');
						trStr.push('			<select id="serviceAreas'+tabtrL+'" name="serviceAreas'+tabtrL+'" style="width: 150px;">');
						trStr.push('					<option value="">--请选择--</option>');
						trStr.push('					<c:forEach items="${listArea}" var="area">');
						trStr.push('					<option value="${area.seq}" >');
						trStr.push('						${area.content1}');
						trStr.push('					</option>');
						trStr.push('				    </c:forEach>');
						trStr.push('				</select>');
						trStr.push('			</td>');
									
						trStr.push('		</tr>');
						trStr.push('		<tr>');
						trStr.push('			<td>');
						trStr.push('				服务面积<input type="text"  onblur="bbsd();" id="serviceSquare'+tabtrL+'" name="serviceSquare'+tabtrL+'" />');
						trStr.push('			</td>');
						trStr.push('			<td >能源类型：');
						trStr.push('				<select id="serEnercyType'+tabtrL+'" name="serEnercyType'+tabtrL+'" style="width: 150px;">');
						trStr.push('					<option value="">--请选择--</option>');
						trStr.push('					<c:forEach items="${listEnergy}" var="energyType">');
						trStr.push('					<option value="${energyType.seq}" >');
						trStr.push('					${energyType.energy}');
						trStr.push('					</option>');
						trStr.push('				</c:forEach>');
						trStr.push('				</select>');
						trStr.push('			</td>');
						trStr.push('			<td>');
						trStr.push('				服务备注<input id="serviceComments'+tabtrL+'" name="serviceComments'+tabtrL+'"/>');
						trStr.push('			</td>');
						trStr.push('		</tr>');
						
						tab.append(trStr.join(''));
					   //找到tbody
                      // var t=$("#tb");
                       //克隆
                      // var tr=$("#tb>tr:first").clone(true);
                      //添加
                       //t.append(tr);
                      // var html=$(obj).parent().parent().parent().parent().html();
                      // alert(html);
                     //  $(obj).parent().parent().parent().parent().parent().append("<table width='100%' class='fwqy'>"+html+"</table>");
                       //清空文本框
                     //  tr.find("select").val("");
                     
                     
					}else{
					   alert('最多支持5项');
					}
				}
			}
			
			
			function addappendfwsb(obj) {
				if($("#addfwsbBtn").text() == "添加"){
				var QL = $("#serEuiptable tr").length;
					if (QL<5) {
						var Qtable = $("#serEuiptable");
						var trStr = new Array();
						trStr.push('<tr>');
						trStr.push('			<td class="admin_bgclor_f1f">');
						trStr.push('				设备类型');
						trStr.push('			</td>');
						trStr.push('			<td>');
						trStr.push('				<input type="text" name="equipTypeIds'+QL+'" id="equipTypeIds'+QL+'" value="${searchEquipList.dbEquipType.typeName}" readonly="readonly" onclick="createdivs(\'equipTypeIds'+QL+'\',\'eqTypeIds'+QL+'\',\'equipNames'+QL+'\');"/>');
						trStr.push('			    <input type="hidden" name="eqTypeIds'+QL+'" id="eqTypeIds'+QL+'" value="${searchEquipList.dbEquipType.equipTypeId}" />');
						trStr.push('			</td>');
						trStr.push('			<td colspan="2" class="admin_bgclor_f1f">');
						trStr.push('		          设备名称');
						trStr.push('			</td>');
						trStr.push('			<td>');
						trStr.push('			<select style="width:150px" id="equipNames'+QL+'" name="equipNames'+QL+'"  onclick = "showTypeInfo(this)">');
						trStr.push('				   <option value="">--请选择--</option>');
						trStr.push('				</select> ');
						trStr.push('	        </td>');
						trStr.push('       </tr>');
						Qtable.append(trStr.join(""));
					   //找到tbody
                      // var t=$(".fwsb");
                       //克隆
                      // var tr=$(".fwsb>tr:first").clone(true);
                      //添加
                     // t.append(tr);
                       //清空文本框
                      // tr.find("select").val("");
					}else{
					   alert('最多支持5项');
					}
					
				}
			}
			
		function doRemove(obj){
			var QL = $("#serAreatable tr").length;
			  //var imgName= $("td");
			  //alert(imgName.length);
			  //if(imgName.length>1){
			   // $(obj).parent().parent().remove();
			   // $("#addBtn").text('添加');
			  //}else{
			    // alert('至少保留一个上传项');
			  //}
			  if(QL>2){
			     $("#serAreatable tr:last-child").remove();
			     $("#serAreatable tr:last-child").remove();
			  }else{
			     alert('至少保留一项!');
			  }
			}
			
			function doRemoves(obj){
			var QL = $("#serEuiptable tr").length;
			//alert($('.fwsb').length);
			if(QL>1){
				$("#serEuiptable tr:last-child").remove();
			  }else{
			     alert('至少保留一项!');
			  }
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
							设备管理——设备列表——设备修改
						</td>
					</tr>
				</table>
			</div>
			
			<form action="equipment_editEquipment.action" method="post" id="addEquipFrom" name="addEquipFrom">
			<input type="hidden" id="equipId" name="dbEquipList.equipId" value="${dbEquipListDetail.equipId}"/>
			<%--<input type="hidden" id="serviceId" name="equipService.equipId" value="${dbEquipListDetail.equipId}" />--%>
			<input type="hidden" id="controlFlag" name="controlFlag" value="${dbEquipListDetail.controlFlag}" />
				<table width="1000" border="0" cellspacing="1" class="gai_left_duiqi">
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
					    <td class="admin_bgclor_e3f">
							设备编号<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="equipCode" name="dbEquipList.equipCode" value="${dbEquipListDetail.equipCode}"/>
						</td>
						<td class="admin_bgclor_e3f">
							设备名称<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">												
							<input type="text" id="equipName"  name="dbEquipList.equipName" value="${dbEquipListDetail.equipName}"/>
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
						<td class="admin_bgclor_e3f">
							所在楼宇<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<select id="build" name="dbEquipList.dbBuilding.buildingId" style="width: 150px;" value="${dbEquipListDetail.dbBuilding.buildingId}" onchange="changeBuild(this,storey);">
								<option value="">--请选择--</option>
								<c:forEach items="${listBuilding}" var="building">
									<option value="${building.buildingId}" ${building.buildingId==dbEquipListDetail.dbBuilding.buildingId?'selected="selected"':''}>
										${building.buildingName}
									</option>
								</c:forEach>
							</select>
						</td>
						<td class="admin_bgclor_e3f">
							所在楼层<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<select id="storey" name="dbEquipList.storey" style="width: 150px;">
								<option value="">--请选择--</option>
								<c:forEach items="${storeyList}" var="st" varStatus="count">
									<!-- add start 2013.4.26 by zhangdiannan 
									             楼层添加屋顶选项 -->
									<c:if test="${count.index==0}">
										<option value="${st+1}" ${st+1==dbEquipListDetail.storey?'selected="selected"':''}>
                                    	屋顶
                                   </option>
									</c:if>
									<!-- add end 2013.4.26 by zhangdiannan -->
								   <option value="${st}" ${st==dbEquipListDetail.storey?'selected="selected"':''}>
                                    	${fn:replace(st,'-','B')}
                                   </option>
                                   
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
							安装位置<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="place" name="dbEquipList.place" value="${dbEquipListDetail.place}"/>
						</td>
						<td class="admin_bgclor_e3f">
							能源类型<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<select id="enercyType" name="dbEquipList.dbEnergyType.seq" style="width: 150px;">
							    <option value="">--请选择--</option>
								<c:forEach items="${listEnergy}" var="energy">
									<option value="${energy.seq}" ${energy.seq==dbEquipListDetail.dbEnergyType.seq?'selected':''}>
										${energy.energy}
									</option>
								</c:forEach>
							</select>
						</td>
					 </tr>
					 <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
						设备品牌	
						</td>
						<td class="admin_bgclor_f1f">
						<input type="text" id="brand" name="dbEquipList.brand" value="${dbEquipListDetail.brand}"/>
						</td>
						<td class="admin_bgclor_e3f">
							设备类型<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" name="equipTypeId" id="equipTypeId" value="${dbEquipListDetail.dbEquipType.typeName }" readonly="readonly" onclick="creatediv(this);"/>
							<input type="hidden" name="dbEquipList.dbEquipType.equipTypeId" id="eqTypeId" value="${dbEquipListDetail.dbEquipType.equipTypeId }"/>
						</td>
					 </tr>
					 
					 <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff" id="elecOption" hidden="true">
							<td class="admin_bgclor_e3f">电表等级</td>
							<td class="admin_bgclor_f1f"><select
								style="width:150px;" id="elecLevel" name="elecLevel" value="${dbEquipListDetail.node_level}">
									<option value="">--请选择--</option>
									<option value="1" <c:if test="${dbEquipListDetail.node_level=='1'}">selected</c:if>>1级</option>
									<option value="2" <c:if test="${dbEquipListDetail.node_level=='2'}">selected</c:if>>2级</option>
									<option value="3" <c:if test="${dbEquipListDetail.node_level=='3'}">selected</c:if>>3级</option>
							</select></td>
							<td class="admin_bgclor_e3f">上级电表</td>
							<td class="admin_bgclor_f1f"><select
								style="width:150px;" id="parentElec" name="parentElec">
									<option value="">--请选择--</option>
									<c:if test="${allElec!=null}">
										<c:forEach items="${allElec }" var="elec">
											<option value="${elec.get('equip_id') }"
												<c:if test="${dbEquipListDetail.node_parent!=null&& dbEquipListDetail.node_parent==elec.get('equip_id') }">selected="selected"</c:if>>${elec.get('equip_name') }</option>
										</c:forEach>
									</c:if>
							</select></td>
						</tr>
					 
					 <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
							资产编号
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="assetscode" name="dbEquipList.assetscode" value="${dbEquipListDetail.assetscode}"/>
						</td>
						<td class="admin_bgclor_e3f">
							设备型号
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="unitType" name="dbEquipList.unitType" value="${dbEquipListDetail.unitType}"/>
						</td>
					 </tr>
					 <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
							产地
						</td>
						<td class="admin_bgclor_f1f">
						<input type="text" id="field" name="dbEquipList.field" value="${dbEquipListDetail.field}"/>
						</td>
						<td class="admin_bgclor_e3f">
							生产单位
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="production" name="dbEquipList.production" value="${dbEquipListDetail.production}"/>
						</td>
					 </tr>
					 
					 <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
							质保期
						</td>
						<td class="admin_bgclor_f1f">
							<input name="dbEquipList.limited" id="limited" value="<fmt:formatDate value="${dbEquipListDetail.limited }" pattern="yyyy-MM-dd"/>"
								readonly="readonly"  class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'%y-%M-%d'})"/>
						</td>
						<td class="admin_bgclor_e3f">
							生产日期
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="productionDate" name="dbEquipList.productionDate" readonly="readonly" value="<fmt:formatDate value="${dbEquipListDetail.productionDate }" pattern="yyyy-MM-dd"/>"
								class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
						</td>
					 </tr>

					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
					    <td class="admin_bgclor_e3f">
							安装日期
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="installDate" name="dbEquipList.installDate" readonly="readonly" value="<fmt:formatDate value="${dbEquipListDetail.installDate }" pattern="yyyy-MM-dd"/>"
							 class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
						</td>
						<td class="admin_bgclor_e3f">
							使用日期
						</td>
						<td class="admin_bgclor_f1f">
							<input id="sDate2" name="dbEquipList.useDate" id="useDate" value="<fmt:formatDate value="${dbEquipListDetail.useDate }" pattern="yyyy-MM-dd"/>"
								readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
						</td>
					</tr>

					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
							保养周期
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="serviceCycle" name="dbEquipList.serviceCycle" value="${dbEquipListDetail.serviceCycle}"
								onkeyup="if(isNaN(value))execCommand('undo')"/>（天）
					   </td>
						<td class="admin_bgclor_e3f">
							使用年限
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" name="dbEquipList.serviceLife" id="serviceLife" value="${dbEquipListDetail.serviceLife}" onkeyup="if(isNaN(value))execCommand('undo')"/>（年）
						</td>
					</tr>
	
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
							采购价
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" onblur="isNumber()" id="purchase" name="dbEquipList.purchase" value="${dbEquipListDetail.purchase}"/>
					    </td>
						<td class="admin_bgclor_f1f">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td class="admin_bgclor_f1f">
						    &nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tbody id="tb">
						
						<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						    <td class="admin_bgclor_e3f">
								服务区域
							</td>
						<td class="admin_bgclor_f1f" colspan="3" style="">
							<table width="100%" class='fwqy' style="border: 1px solid white;" id="serAreatable">
								<c:if test="${empty equipServiceList }">
									<tr>
									<td>
									楼宇
									<select id="serviceBuild" name="serviceBuild" style="width: 150px;"  onchange="changeBuild(this,serviceStorey)">
										<option value="">--请选择--</option>
										<c:forEach items="${listBuilding}" var="building">
											<option value="${building.buildingId}" >
												${building.buildingName}
											</option>
										</c:forEach>
									</select>
									</td>
									<td>
									楼层
									<select id="serviceStorey" name="serviceStorey" style="width: 150px;">
										<option value="">--请选择--</option>
									</select>
									</td>
									<td>
									区域
									<select id="serviceAreas" name="serviceAreas" style="width: 150px;">
											<option value="">--请选择--</option>
											<c:forEach items="${listArea}" var="area">
											<option value="${area.seq}" >
												${area.content1}
											</option>
										    </c:forEach>
										</select>
									</td>
									
								</tr>
								<tr>
									<td>
										服务面积22<input type="text" id="serviceSquare" name="serviceSquare" />
									</td>
									<td >能源类型：
										<select id="serEnercyType" name="serEnercyType" style="width: 150px;">
											<option value="">--请选择--</option>
											<c:forEach items="${listEnergy}" var="energyType">
												<option value="${energyType.seq}" >
													${energyType.energy}
												</option>
											</c:forEach>
										</select>
									</td>
									<td>服务备注
										<input id="serviceComments" name="serviceComments" />
									</td>
								</tr>
							</c:if>
								<c:forEach items="${equipServiceList}" var="equipServicevar" varStatus="st" >
									<c:if test="${st.index==0}">
									<tr>
									<td>
									楼宇
									<select id="serviceBuild" name="serviceBuild" style="width: 150px;"  onchange="changeBuild(this,serviceStorey)">
										<option value="">--请选择--</option>
										<c:forEach items="${listBuilding}" var="building">
											<option value="${building.buildingId}" ${building.buildingId==equipServicevar.buildId?'selected':''}>
												${building.buildingName}
											</option>
										</c:forEach>
									</select>
									</td>
									<td>
									楼层
									<select id="serviceStorey" name="serviceStorey" style="width: 150px;">
										<option value="">--请选择--</option>
										<c:set var="tempvar" value="sstoreyList${st.index}"/>
										<c:forEach items="${sstoreyList}" var="stroList" varStatus="stStatus">
											<c:if test="${stStatus.index==st.index}">
												<c:forEach items="${stroList}" var="stro">
													<option value="${stro}" ${stro==equipServicevar.storey?'selected':''}>
														${fn:replace(stro,'-','B')}
													</option>
												</c:forEach>
											</c:if>
										 </c:forEach>
									</select>
									</td>
									<td>
									区域
									<select id="serviceAreas" name="serviceAreas" style="width: 150px;">
											<option value="">--请选择--</option>
											<c:forEach items="${listArea}" var="area">
											<option value="${area.seq}" ${area.seq==equipServicevar.dbBaseComm.seq?'selected':''} >
												${area.content1}
											</option>
										    </c:forEach>
										</select>
									</td>
									
								</tr>
								<tr>
									<td>
										服务面积11 <input type="text" id="serviceSquare" onblur="isNumber()" name="serviceSquare" value="${equipServicevar.square}"/>
									</td>
									<td >能源类型：
										<select id="serEnercyType${st.index}" name="serEnercyType" style="width: 150px;">
											<option value="">--请选择--</option>
											<c:forEach items="${listEnergy}" var="energyType">
											<option value="${energyType.seq}" ${energyType.seq==equipServicevar.dbEnergyType.seq?'selected':''} >
												${energyType.energy}
											</option>
										</c:forEach>
										</select>
									</td>
									<td>服务备注
										<input id="serviceComments" name="serviceComments" value="${equipServicevar.comments}"/>
									</td>
								</tr>
								</c:if>
								<c:if test = "${st.index>0 }">
									<tr>
									<td>
									楼宇
									<select id="serviceBuild${st.index}" name="serviceBuild${st.index}" style="width: 150px;"  onchange="changeBuild(this,serviceStorey${st.index})">
										<option value="">--请选择--</option>
										<c:forEach items="${listBuilding}" var="building">
											<option value="${building.buildingId}" ${building.buildingId==equipServicevar.buildId?'selected':''}>
												${building.buildingName}
											</option>
										</c:forEach>
									</select>
									</td>
									<td>
									楼层
									<select id="serviceStorey${st.index}" name="serviceStorey${st.index}" style="width: 150px;">
										<option value="">--请选择--</option>
										<c:forEach items="${sstoreyList}" var="stroList" varStatus="stStatus">
											<c:if test="${stStatus.index==st.index}">
												<c:forEach items="${stroList}" var="stro">
													<option value="${stro}" ${stro==equipServicevar.storey?'selected':''}>
														${fn:replace(stro,'-','B')}
													</option>
												</c:forEach>
											</c:if>
										 </c:forEach>
									</select>
									</td>
									<td>
									区域
									<select id="serviceAreas${st.index}" name="serviceAreas${st.index}" style="width: 150px;">
											<option value="">--请选择--</option>
											<c:forEach items="${listArea}" var="area">
											<option value="${area.seq}" ${area.seq==equipServicevar.dbBaseComm.seq?'selected':''}>
												${area.content1}
											</option>
										    </c:forEach>
										</select>
									</td>
									
								</tr>
								<tr>
									<td>
										服务面积<input type="text" id="serviceSquare${st.index}" name="serviceSquare${st.index}" value="${equipServicevar.square}"/>
									</td>
									<td >能源类型：
										<select id="serEnercyType${st.index}" name="serEnercyType${st.index}" style="width: 150px;">
											<option value="">--请选择--</option>
											<c:forEach items="${listEnergy}" var="energyType">
											<option value="${energyType.seq}" ${energyType.seq==equipServicevar.dbEnergyType.seq?'selected':''} >
												${energyType.energy}
											</option>
										</c:forEach>
										</select>
									</td>
									<td>服务备注
										<input id="serviceComments${st.index}" name="serviceComments${st.index}" value="${equipServicevar.comments}"/>
									</td>
								</tr>
								</c:if>
									
								</c:forEach>
							</table>
								<a onclick="addappendChild(this);" class="btn blue" id="addfwqyBtn">添加</a>
						                <a onclick="doRemove(this);" class="btn blue">移除</a>
						</td>
					</tr>
					
					</tbody>
					<tbody class="fwsb">
					
					<tr class=" biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
							服务设备
						</td>
						<td class="admin_bgclor_f1f" colspan="3" style="">
							
							<table width="100%"  id="serEuiptable"style="border: 1px solid white;" >
								<c:if test="${empty eqSerEquipList}">
									<tr >
										<td class="admin_bgclor_f1f">
											设备类型
										</td>
										<td>
											<input type="text" name="equipTypeIds" id="equipTypeIds" value="${searchEquipList.dbEquipType.typeName}" readonly="readonly" onclick="createdivs('equipTypeIds','eqTypeIds','equipNames');"/>
										    <input type="hidden" name="eqTypeIds" id="eqTypeIds" value="${searchEquipList.dbEquipType.equipTypeId}" />
										</td>
										<td colspan="2" class="admin_bgclor_f1f">
									        	  设备名称
									      </td>
										<td>
											<select id="equipNames" name="equipNames" style="width:150px;" onclick = "showTypeInfo(this)" >
											   <option value="">--请选择--</option>
											</select> 
								        </td>
								       </tr>
							       </c:if>
							       <c:if test="${!empty eqSerEquipList}">
							       	<c:forEach var="eqSerEquip" items="${eqSerEquipList}" varStatus="eqSt">
								       <c:if test="${eqSt.index==0}">
								        <tr >
											<td class="admin_bgclor_f1f">
												设备类型
											</td>
											<td>
												<input type="text" name="equipTypeIds" id="equipTypeIds" value="${eqSerEquip.dbEquipListBySerEquipId.dbEquipType.typeName}" readonly="readonly" onclick="createdivs('equipTypeIds','eqTypeIds','equipNames');"/>
											    <input type="hidden" name="eqTypeIds" id="eqTypeIds" value="${eqSerEquip.dbEquipListBySerEquipId.dbEquipType.equipTypeId}" />
											</td>
											<td colspan="2" class="admin_bgclor_f1f">
										        	  设备名称
										      </td>
											<td>
												<select id="equipNames" name="equipNames" style="width:150px;"  onclick = "showTypeInfo(this)">
												   <option value="">--请选择--</option>
												    <c:forEach items="${eequipList}" var="tempEquip" varStatus="tmpEqSt">
											   			<c:if test="${eqSt.index==tmpEqSt.index}">
											   				<c:forEach items="${tempEquip}" var="tempEquipDb">
											   				<option value='${tempEquipDb.equip_Id }' ${eqSerEquip.dbEquipListBySerEquipId.equipId==tempEquipDb.equip_Id?'selected':''}>${ tempEquipDb.equip_name} </option>
											   				</c:forEach>
											   			</c:if>
											   		</c:forEach>
												</select> 
									        </td>
									       </tr>
								       </c:if>
								        <c:if test="${eqSt.index>0}">
								       		 <tr >
											<td class="admin_bgclor_f1f">
												设备类型
											</td>
											<td>
												<input type="text" name="equipTypeIds${eqSt.index}" id="equipTypeIds${eqSt.index}" value="${eqSerEquip.dbEquipListBySerEquipId.dbEquipType.typeName}" readonly="readonly" onclick="createdivs('equipTypeIds${eqSt.index}','eqTypeIds${eqSt.index}','equipNames${eqSt.index}');"/>
											    <input type="hidden" name="eqTypeIds${eqSt.index}" id="eqTypeIds${eqSt.index}" value="${eqSerEquip.dbEquipListBySerEquipId.dbEquipType.equipTypeId}" />
											</td>
											<td colspan="2" class="admin_bgclor_f1f">
										        	  设备名称
										      </td>
											<td>
												<select id="equipNames${eqSt.index}" name="equipNames${eqSt.index}" style="width:150px;"  onclick = "showTypeInfo(this)">
												   <option value="">--请选择--</option>
												    <c:forEach items="${eequipList}" var="tempEquip" varStatus="tmpEqSt">
											   			<c:if test="${eqSt.index==tmpEqSt.index}">
											   				${tempEquip }
											   				<c:forEach items="${tempEquip}" var="tempEquipDb">
											   				<option value='${tempEquipDb.equip_id }' ${eqSerEquip.dbEquipListBySerEquipId.equipId==tempEquipDb.equip_id?'selected':''}>${ tempEquipDb.equip_name} </option>
											   				</c:forEach>
											   			</c:if>
											   		</c:forEach>
												</select> 
									        </td>
									       </tr>
								       </c:if>
								      </c:forEach>
								      
							       
							       </c:if>
						        </table>
						        	<a onclick="addappendfwsb(this);" class="btn blue" id="addfwsbBtn">添加</a>
								        <a onclick="doRemoves(this);" class="btn blue">移除</a>	
							</td>
						</tr>
					</tbody>
					<tr style="height:80px" class=" biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_e3f">
							主要附件<br/>(其他备注)
						</td>
						<td colspan="3" class="admin_bgclor_f1f">
							<textarea rows="4" cols="93" id="accessory" name="dbEquipList.accessory">${dbEquipListDetail.accessory}</textarea>
						</td>
					</tr>
				</table>
				<table width="770" border="0" class="gai_left_duiqi">
					<tr>
						<td></td>
						<td  width="65">
						    <a href="javascript:void(0);" onclick="saveButtonClick();" class="btn blue">下一步</a>										
						</td>
						<td  width="65"><a href="javascript:history.go(-1);" class="btn blue">取 消</a>																
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>