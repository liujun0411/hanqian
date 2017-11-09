<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<title>医院后勤智能化管理平台——后勤管理</title>

		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/reqi.js" type="text/javascript"></script>
		<!-- 引入公用控件 -->
		<link  href="manager/common/equip/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/common/equip/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerComboBox.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerResizable.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerTree.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/createTree.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery.validate.js"></script>
		<script type="text/javascript" src="manager/javascript/buildStorey/buildStorey.js"></script>
		
		
		<link href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/static/css/aries/jquery.aries.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/dropdown/jquery.dropdown.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script>
		<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script>
		<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script>
		<script src="<c:url value="/static/js/dropdown/jquery.dropdown.min.js"/>"></script>
		
		<script type="text/javascript">
		$(function(){
			$("body").fadeIn("slow");
		});
		 //设备类型菜单回调函数
		   function doClick(note){
		      $('#equipTypeId').val(note.data.text);
		      $('#eqTypeId').val(note.data.id);
		      $("#listDiv_List").hide();
		      changeEquipType();  //查询设备数据
		   }
		   
			function changeEqType() {	
				var url = "equipment_equipmentDetail.action?editFlag=${editFlag}&hospid=${hosid}&eqType="
						+ jQuery("#eqtype").val();
				location.href = url;
			}
			
				var isNull = ${equipmentDetail==null};
				if(isNull){
					jQuery("#img1").replaceWith("<img src='manager/images/imgs/gai_shebei_canshu_2.png' id='img1' style='cursor:hand' title='设备参数' />"); 
					jQuery("#img2").replaceWith("<img src='manager/images/imgs/gai_shebei_weihu_2.png' id='img2' style='cursor:hand' title='维护信息' />"); 
					jQuery("#img3").replaceWith("<img src='manager/images/imgs/gai_shebei_tuzhi_2.png' id='img3' style='cursor:hand' title='设备图纸' />"); 
					jQuery("#img4").replaceWith("<img src='manager/images/imgs/gai_shebei_xiangqing_1.png' id='img4' style='cursor:hand' title='设备详情' />");
				}
			
			f_param = function(){
			   if(jQuery.trim($('#equipId').val())!=""){
			        $('#listDiv_List').hide();
			        $("#paramIfream").attr({src:"equipParam_findEquipByEquipId.action?equipId="+$('#equipId').val()});
					$("#img1").attr({ src: "manager/images/imgs/gai_shebei_canshu_1.png"});
					$("#img2").attr({ src: "manager/images/imgs/gai_shebei_weihu_2.png"});
					$("#img3").attr({ src: "manager/images/imgs/gai_shebei_tuzhi_2.png"});
					$("#img4").attr({ src: "manager/images/imgs/gai_shebei_xiangqing_2.png"});
					$("#pic").hide();
					$("#equipdetail").show();
					$("#equipMaintenance").hide();
					$("#equipPic").hide();
					$("#equipParam").show();
				}
			}
			
			f_maint = function (){
				if(jQuery.trim($('#equipId').val())!=""){
				    $('#listDiv_List').hide();
					$("#maintIfream").attr({src:"maintenance_findEquipmentByPage.action?equipId="+$('#equipId').val()});
					$("#img1").attr({ src: "manager/images/imgs/gai_shebei_canshu_2.png"});
					$("#img2").attr({ src: "manager/images/imgs/gai_shebei_weihu_1.png"});
					$("#img3").attr({ src: "manager/images/imgs/gai_shebei_tuzhi_2.png"});
					$("#img4").attr({ src: "manager/images/imgs/gai_shebei_xiangqing_2.png"});
					$("#pic").hide();
					$("#equipdetail").show();
					$("#equipMaintenance").show();
					$("#equipPic").hide();
					$("#equipParam").hide();
				 }
			}
			
			f_pic = function (){
			    if(jQuery.trim($('#equipId').val())!=""){
			        $('#listDiv_List').hide();
			        $("#picIfream").attr({src:"equipPic_findEquipPicList.action?equipId="+$('#equipId').val()});
					$("#img1").attr({ src: "manager/images/imgs/gai_shebei_canshu_2.png"});
					$("#img2").attr({ src: "manager/images/imgs/gai_shebei_weihu_2.png"});
					$("#img3").attr({ src: "manager/images/imgs/gai_shebei_tuzhi_1.png"});
					$("#img4").attr({ src: "manager/images/imgs/gai_shebei_xiangqing_2.png"});
					$("#pic").show();
					$("#equipdetail").show();
					$("#equipMaintenance").hide();
					$("#equipPic").show();
					$("#equipParam").hide();
			    }
			}
			f_detail = function(){
				$("#img1").attr({ src: "manager/images/imgs/gai_shebei_canshu_2.png"});
				$("#img2").attr({ src: "manager/images/imgs/gai_shebei_weihu_2.png"});
				$("#img3").attr({ src: "manager/images/imgs/gai_shebei_tuzhi_2.png"});
				$("#img4").attr({ src: "manager/images/imgs/gai_shebei_xiangqing_1.png"});
				$("#pic").hide();
				$("#equipdetail").show();
				$("#equipMaintenance").hide();
				$("#equipPic").hide();
				$("#equipParam").hide();
			}
			
			//设备类型改变事件
			function changeEquipType(){
				//设备名字
	            var buildId=$('#buildId').val(); //楼宇
	            var storey=$('#storey').val();  //楼层
	            var hisName=$('#hisName').val(); //楼名
	            var place=$('#place').val();  //安装位置
	            var unitType=$('#unitType').val();   //型号
	            var equipType=$('#eqTypeId').val();   //设备类型
	            $("#equipName option").remove();
	            if(jQuery.trim(buildId)!=""){
	               var dt={"buildId":buildId,"storey":storey,"hisName":hisName,"place":place,"unitType":unitType,"eqTypeId":equipType};
	               $.ajax({
					      type: 'post',
						  url: "equipment_findEquipListByParam.action",
						  data: dt,
						  cache:false,
						  dataType: 'json',
						  success: function(data){
						     $("#equipName option").remove();
						     $("#equipName").append("<option value=''>--请选择--</option>");
						     var dt=eval(data);
						     for(var i=0;i<dt.length;i++){
							     if(dt[i]!=null){
							       $("#equipName").append("<option value='"+dt[i].equipid+"'>"+dt[i].equipname+"</option>");
							     }
						     }
						  },
						  error:function(textStatus){
						      alert('error'+textStatus);
						  }
					 });
	             }else{
					 $("#equipName option").remove();
				     $("#equipName").append("<option value=''>--请选择--</option>");
	             }
         }
         
         toAdd = function() {
				location.href = "equipment_toAddPage.action";
		 }
		 
		 function doSerach(){
		 if($('#equipTypeId').val()==""||$('#equipTypeId').val()==null){
		     alert('请选择设备类型!');
		     return;
		   }
		   if($('#equipName').val()!=""){
		      $('#equipId').val($('#equipName').val());
		      document.serachform.submit();
		   }else{
		     alert('请选择设备名称!');
		   }
		 }

			//显示设备详情
			function showEquipList(){
				$(window.parent.document).find("#ifremeShowDetail").fadeOut("slow",function(){
					$(window.parent.document).find("#divShowDetail").show();
					$(window.parent.document).find("#ifremeShowDetail").attr("src","");
				});
				
			  // window.location.href="equipment_showDetail?history=history&equipId="+obj;

			}
			
			
	//任意提醒，通过标签ID给需要绑定数据的元素绑定数据
	$(document).ready(function(){
		<c:if test="${empty remindJson}">
			var remindJson="";
		</c:if>
		<c:if test="${!empty remindJson}">
			var remindJson =${remindJson};
		</c:if>
			if(remindJson!=""){
		 	for(var obj in remindJson){
	 	 				console.debug("     eqRemindJson=    "+remindJson[obj].resultsql);
		 				$("#"+obj).data("aries",remindJson[obj]);
		 		}
			}
		});
		</script>
		
	</head>
	<body>
		<div id="admin_nr_rightg">
 			<div class="canshusz_btbg_1">
				<table width="100%" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							设备管理——设备信息
						</td>
					</tr>
				</table>
			</div>
			<div class="admin_nr_right_g02">
				<table width="100%" border="0">
					<tr style="height: 25px;" valign="middle">
						<td width="2"></td>
						<td width="83">
							<img src="manager/images/imgs/gai_shebei_xiangqing_1.png"
								id="img4" onclick="f_detail()" style="cursor: hand" title="设备详情" />
						</td>
						<td width="4" valign="bottom">
							<img src="manager/images/imgs/gaixian.png" />
						</td>						
							<td width="83">
								<img src="manager/images/imgs/gai_shebei_canshu_2.png" id="img1"
									onclick="f_param()" style="cursor: hand" title="设备参数" />
							</td>
							<td width="4" valign="bottom">
								<img src="manager/images/imgs/gaixian.png" />
							</td>
						<td width="83">
							<img src="manager/images/imgs/gai_shebei_weihu_2.png" id="img2"
								onclick="f_maint()" style="cursor: hand" title="维护信息" />
						</td>
						<td width="4" valign="bottom">
							<img src="manager/images/imgs/gaixian.png" />
						</td>
						<td width="83">
							<img src="manager/images/imgs/gai_shebei_tuzhi_2.png" id="img3"
								onclick="f_pic()" style="cursor: hand" title="设备图纸" />
						</td>
						<td></td>
					</tr>
				</table>
				 <!-- 设备信息 -->	
<%--				<form action="equipment_showDetail.action" method="post"--%>
<%--					name="serachform">--%>
<%--				 <input type="hidden" id="equipId" name="equipId" value="${searchEquipList.equipId}" />--%>
<%--				</form>--%>
			</div>
			<!-- 设备详情 -->
		    <div id="equipdetail">
				<form action="equipment_showDetail.action" method="post"
					name="serachform">
					<input type="hidden" id="equipId" name="equipId" value="${equipId}" />
					<table class="louyujj_xiaxian_hui gai_left_duiqi" style=" margin-top: 10px;display:none">
						<tr align="right">
							<td class="shebeigl_inp_zt" align="right" width="90">
								所在楼宇：
							</td>
							<td width="80" align="left">
								<select name="buildId" id="buildId" style="width: 155px;" onchange="getStoreyByBuildId('buildId','storey','${searchEquipList.storey }','changeEquipType');changeEquipType();">
<%--									<option value="">--请选择--</option>--%>
<%--									<c:forEach items="${listBuilding}" var="building">--%>
<%--										<option value="${building.buildingId}" ${building.buildingId==dbEquipListDetail.dbBuilding.buildingId?'selected':''}>--%>
<%--											${building.buildingName}--%>
<%--										</option>	--%>
<%--									</c:forEach>--%>
								</select>
							</td>
							<td class="shebeigl_inp_zt" align="right" width="90">
								所在楼层：
							</td>
							<td width="80" align="left">
								<input type="text" name="storey" id="storey" maxlength="2" 
								onblur="changeEquipType();" value='<fmt:formatNumber type="number" value="${searchEquipList.storey}"/>'
								 onkeyup="value=value.replace(/[^\-?\d.]/g,'')"/>
							</td>
							<td width="100px" align='right' class='shebeigl_inp_zt'>
								楼宇曾用名：
							</td>
							<td align="left" width='80'>
								<input type="text" name="hisName" id="hisName" value="${searchEquipList.dbBuilding.hisName }" maxlength="25" onblur="changeEquipType();"/>
							</td>
							
							<td width='90' align='right' class='shebeigl_inp_zt'>
								安装位置：
							</td>
							<td width="80" align="left">
								<input type="text" name="place" id="place" value="${searchEquipList.place }" maxlength="100" onblur="changeEquipType();"/>
							</td>
						</tr>
						<tr>
					   	    <td width='50' align='right' class='shebeigl_inp_zt'>
								设备型号：
							</td>
							<td width="80" align="left">
								<input type="text" name="unitType" id="unitType" value="${searchEquipList.unitType }" maxlength="50" onblur="changeEquipType();"/>
							</td>
							<td width='90' align='right' class='shebeigl_inp_zt' >
								设备类型：
							</td>
							<td width="80" align="left"  style="white-space: nowrap">
								<input type="text" name="equipTypeId" id="equipTypeId" value="${searchEquipList.dbEquipType.typeName}" readonly="readonly" onclick="creatediv(this);"/>
							    <input type="hidden" name="eqTypeId" id="eqTypeId" value="${searchEquipList.dbEquipType.equipTypeId}" />
								<font color="red" >*</font>
							</td>
							<td width='90' align='right' class='shebeigl_inp_zt'>
							设备名称：
							</td>
							<td width="80" align="left" style="white-space: nowrap">
								<select id="equipName" name="equipName" style="width: 155px;" >
								   <option value="">--请选择--</option>
								</select> <font color="red" >*</font>	
							</td>
							<td colspan="2" align="right">
						      <img style='cursor: pointer'
									src="manager/images/imgs/shebeigl_sousuo_an.png"
									onclick="doSerach();"></img>&nbsp;&nbsp;
							  <a href="javascript:toAdd()" class="btn blue" align="left">添加</a>
						  </td>
						</tr>
					</table>
				</form>
				<div id="equipDetail">
				<br /><br />
				<c:if test="${!empty listToDetail}">
					<a class="btn blue" onclick="showEquipList();" style="float:left"><i></i><span><i></i><span></span>返回设备列表</span></a>
				</c:if>
				<div>
						<c:if test="${empty dbEquipListDetail}">
						<span style="color:red">${msg }</span>
						
						</c:if><c:if test="${!empty dbEquipListDetail}">
						    <table width="950" border="0" cellspacing="1"
									class="biaoju_tong_1 admin_bgclor_c6c gai_left_duiqi">
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="equipCode">设备编号</span>
										</td>
										<td width="350">
											${dbEquipListDetail.equipCode }
										</td>
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="equipNameTow">设备名称</span>
										</td>
										<td width="350">
											${dbEquipListDetail.equipName }
										</td>
			
									</tr>
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="typeName">设备类型</span>
										</td>
										<td width="220">
											${dbEquipListDetail.dbEquipType.typeName}
										</td>
										
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="unitTypeTwo">设备型号</span>
										</td>
										<td width="220">
										   ${dbEquipListDetail.unitType}
										</td>
									</tr>
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="buildingNamTwo">所在楼宇</span>
										</td>
										<td width="220">
											${dbEquipListDetail.dbBuilding.buildingName}
										</td>
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="storeyTwo">所在楼层</span>
										</td>
										<td width="220">
											<c:if test="${dbEquipListDetail.storey <0}">
												B<fmt:formatNumber type='number' value ='${dbEquipListDetail.storey<0?-dbEquipListDetail.storey:dbEquipListDetail.storey}'></fmt:formatNumber>
											</c:if>
											<c:if test="${dbEquipListDetail.storey >0&&dbEquipListDetail.storey <= dbEquipListDetail.dbBuilding.storeyNumUp}">
												<fmt:formatNumber type="number" value ='${dbEquipListDetail.storey}'></fmt:formatNumber>
											</c:if>
											<c:if test="${dbEquipListDetail.storey >0&&dbEquipListDetail.storey>dbEquipListDetail.dbBuilding.storeyNumUp}">
												屋顶
											</c:if>
										</td>
									</tr>
			
			
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="placeTwo">安装位置</span>
										</td>
										<td width="220">
										    ${dbEquipListDetail.place}
										</td>
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="controlFlag">是否监控</span>
										</td>
										<td width="220">
										   ${dbEquipListDetail.controlFlag==1?'是':'否'}
										</td>
									</tr>
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="equipServiceEquip">服务设备</span>
										</td>
										<td width="220">
										<c:if test="${!empty dbEquipListDetail.equipService}">
											<table>
											 <c:forEach items="${dbEquipListDetail.equipService}" var="equipServiceEquip">
												<tr>
													<td>										
														${equipServiceEquip.equipName }&nbsp;
														<c:if test="${!empty equipServiceEquip.equipCode}">
															[${equipServiceEquip.equipCode}]
														</c:if>
													</td>
												</tr>	
											 </c:forEach>
											 </table>
										 </c:if>
										</td>
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="equipServices">服务区域</span>
										</td>
										<td width="220">
										   <c:if test="${! empty dbEquipListDetail.equipServices}">
										      <c:forEach items="${dbEquipListDetail.equipServices}" var="equipService">
										      <table>
										      <tr>
										      	<td>
										      		<c:forEach items="${listBuilding}" var = "tempBuild">
										      			<c:if test="${equipService.buildId==tempBuild.buildingId}">
										      				${tempBuild. buildingName}
										      			</c:if>
										      		</c:forEach>
										      	</td>
										      	<td>
										      	<c:if test="${equipService.storey <0}">
												  B<fmt:formatNumber type="number" value ='${equipService.storey<0?-equipService.storey:equipService.storey}'></fmt:formatNumber>
											    </c:if>
										      	<c:if test="${equipService.storey >0}">
												  <fmt:formatNumber type="number" value ='${equipService.storey}'></fmt:formatNumber>
											    </c:if>
										      	<c:if test="${!empty equipService.storey}">
									      			层
									      		</c:if>
										      	</td>
										      	<td>${equipService.dbBaseComm.content1}</td>
										      	<td>
										      			${equipService.dbEnergyType.energy}
									      		</td>
									      		<td>
										      			${equipService.comments}
									      		</td>
									      		<td>
									      		<c:if test="${!empty equipService.square}">
									      			${equipService.square}㎡
									      		</c:if>
									      		</td>
										      </tr>
										      </table>
											  </c:forEach>
										   </c:if>
										</td>
									</tr>
								
			
									
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="assetscode">资产编号</span>
										</td>
										<td width="220">
										   ${dbEquipListDetail.assetscode}
										</td>
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="production">生产单位</span>
										</td>
										<td width="220">
										   ${dbEquipListDetail.production}
										</td>
									</tr>
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="field">产地</span>
										</td>
										<td width="220">
										    ${dbEquipListDetail.field}
										</td>
			
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="productionDate">生产日期</span>
										</td>
										<td width="220">
										   <c:if test="${!empty dbEquipListDetail.productionDate}">
										      <fmt:formatDate value="${dbEquipListDetail.productionDate}"
												pattern="yyyy-MM-dd" />
										   </c:if>
										</td>
									</tr>
									
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="installDate">安装日期</span>
										</td>
										<td width="220">
										   <c:if test="${!empty dbEquipListDetail.installDate}">
										      <fmt:formatDate value="${dbEquipListDetail.installDate}"
												pattern="yyyy-MM-dd" />
										   </c:if>
										</td>
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="useDate">使用日期</span>
										</td>
										<td width="220">
										  <c:if test="${!empty dbEquipListDetail.useDate}">
										    <fmt:formatDate value="${dbEquipListDetail.useDate}"
												pattern="yyyy-MM-dd" />
										  </c:if>
										</td>
									</tr>
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="limited">质保期</span>
										</td>
										<td width="220">
										  <c:if test="${!empty dbEquipListDetail.limited}">
										    <fmt:formatDate value="${dbEquipListDetail.limited}"
												pattern="yyyy-MM-dd" />
										  </c:if>
										<br /></td>
			
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="serviceLife">使用年限</span>
										</td>
										<td width="220">
										   ${dbEquipListDetail.serviceLife}
										</td>
									</tr>
									
									<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="serviceCycle">保养周期（天）</span>
										</td>
										<td width="220">
										   ${dbEquipListDetail.serviceCycle}
										</td>
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="purchase">采购价</span>
										</td>
										<td width="220">
										   ${dbEquipListDetail.purchase}
										</td>
									</tr>
									
									<tr style="height: 80px" class=" biaog_zt2 admin_bgclor_fff">
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="brand">设备品牌</span>
										</td>
										<td width="220">
											${dbEquipListDetail.brand}
										</td>
										<td class="admin_bgclor_e3f">
											<span class="aries-anchor-right" data-aries="#aries-1" id="accessory">主要附件</span>
										</td>
										<td >
										    ${dbEquipListDetail.accessory}
										</td>
									</tr>
									<c:if test="${!empty js}">
<%--									<tr>--%>
<%--										<td align="right" colspan="4">${js}</td>--%>
<%--									</tr>--%>
									</c:if>
					        </table>
				  </c:if>	
						</div>	
			    </div>
	        </div>
	        <!-- 设备参数 -->
	        <div id="equipParam" style=" display: none;">
	           <iframe src="" id="paramIfream" scrolling="no" width=970 height="300" marginwidth=0
					marginheight=0 frameborder=0></iframe>
	        </div>
	        <!-- 设备维护信息 -->
	        <div id="equipMaintenance" style="display: none;">
	           <iframe src="" id="maintIfream" scrolling="auto" width=970 height="280" marginwidth=0
					marginheight=0 frameborder=0></iframe>
	        </div>
	        <!-- 设备图纸 -->
	        <div id="equipPic" style="display: none;">
	           <iframe src="" id="picIfream" scrolling="auto"  style="overflow-x:hidden;overflow-y:auto;" width=950 height="520" marginwidth=0
					marginheight=0 frameborder=0></iframe>
	        </div>
	    </div>
	</body>
	<script  type="text/javascript">
	$(function(){
	  initDataFun('buildId','storey','${searchEquipList.dbBuilding.buildingId}');
	  changeEquipType();
	});
	</script>
</html>
