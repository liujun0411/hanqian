<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			type="text/javascript"></script>
		<base href="<%=basePath%>" />
		<title>医院后勤智能化管理平台——后勤管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/ceng.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/xialakuang.js"></script>
		<!-- script src="manager/js/selectEL.js" type="text/javascript"></script> -->
		<!-- 引入公用控件 -->
        <script type="text/javascript" src="manager/common/equip/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
		<link  href="manager/common/equip/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerComboBox.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerResizable.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerTree.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/createTree.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery.validate.js"></script>
		<script type="text/javascript" src="manager/javascript/buildStorey/buildStorey.js"></script>
	   <!--<script type="text/javascript">
		    //回车事件添加到检索按钮
			document.onkeydown = function (e) {
				var theEvent = window.event || e;
				var code = theEvent.keyCode || theEvent.which;
				if (code == 13) {
					alert('aaaa');
				}else{
				  alert('bbbbb');
				}
		</script>
-->
		<script type="text/javascript">
			
			toAdd = function() {
				location.href = "equipment_toAddPage.action";
			}

			var handle;
			function start()
			{
				var obj = document.getElementById("tip");
				if (parseInt(obj.style.height)==0)
				{ 
					obj.style.display="block";
					handle = setInterval("changeH('up')",2);
				}else
				{
				     handle = setInterval("changeH('down')",2) 
				} 
			}
			function changeH(str)
			{
				var obj = document.getElementById("tip");
				if(str=="up")
				{
				if (parseInt(obj.style.height)>100)
				   clearInterval(handle);
				else
				   obj.style.height=(parseInt(obj.style.height)+8).toString()+"px";
				}
				if(str=="down")
				{
					if (parseInt(obj.style.height)<8)
					{ 
						clearInterval(handle);
					   obj.style.display="none";
					}
					else 
					   obj.style.height=(parseInt(obj.style.height)-8).toString()+"px"; 
					}
			}

			function recover()
			{
				document.getElementsByTagName("html")[0].style.overflow = "auto";
				document.getElementById("shadow").style.display="none";
				document.getElementById("detail").style.display="none"; 
			}
			//前提：db_equip_list中control_flag为“1”是监控点位不能报废“0”不是监控点位可以报废
			//设备报废
			function doDelete(obj,controlflag){
				if(controlflag==0){
				     if(confirm("确定要报废吗？")){
				         window.location.href="equipment_deleteEquipment.action?eqId="+obj;
				   }
				}else if(controlflag==1){
				   alert("有监控点不能被报废 !");
				  }
			}
			
			//显示设备详情
			function showDetail(obj){
			    //加判断，如果是报废就不要显示详情页面
				var status=$("input[name='status']").val();
				if(1==status){
				    return false;
				}else{
					$("#divShowDetail").fadeOut("slow",function(){
						$("#ifremeShowDetail").fadeIn("slow");
						$("#ifremeShowDetail").attr("src","equipment_showDetail?listToDetail=y&equipId="+obj);
					});
				}
			  // window.location.href="equipment_showDetail?history=history&equipId="+obj;

			}
			
		   //设备类型菜单回调函数
		   function doClick(note){
		      $('#equipTypeId').val(note.data.text);
		      $('#eqTypeId').val(note.data.id);
		      $("#listDiv_List").hide();
		   }
			   
		 function formCheck(currentPage) {
			if (currentPage != "") {
				$("#currentPage").val(currentPage);
			}
			if ($('#inpStorey').css('display') == 'block') {
		        $('#storey').val($('#inpStorey').val());
	        }
			document.myform.submit();
		}
		
		$(function() {
			$(".doOper").click(function(event) {
				event.stopPropagation();
			});
		});
		
		function changeBuild(obj, childId) {
			var buildId = $(obj).val();
			   if(jQuery.trim(buildId)!=""){
	                 var dt={"buildId":buildId};
	                 $.ajax({
					      type: 'get',
						  url: "equipment_findStoreyByBuildId.action",
						  data: dt,
						  cache:false,
						  dataType: 'json',
						  success: function(data){
						     $('#inpStorey').hide();
						     var arrayObj = new Array();　//创建一个数组
						     var storey=eval(data);
						     var cid=$(childId).attr('id');
						     $("'#"+cid+"' option").remove();
						     $(childId).append("<option value=''>--请选择所在楼层--</option>");
						     $(childId).append("<option value='"+(parseInt(storey[0])+parseInt(1))+"'>屋顶</option>");
						     for(var k=0;k<storey.length;k++){
						       var valu=String( storey[k]);
						       valu=valu.replace("-",'B');
						       $(childId).append("<option value='"+storey[k]+"'>"+valu+"</option>");
						     }
					         $("#"+cid).show();
						  },
						  error:function(textStatus){
						      alert('error'+textStatus);
						  }
					 });
	             }else{
	                 var cid=$(childId).attr('id');
					 $("'#"+cid+"' option").remove();
				     $(childId).append("<option value=''>--请选择所在楼层--</option>");
				     $("#"+cid).hide();
				     $('#inpStorey').show();
				     $('#storey').val('');
	             }
	         }
	         
	         function selectChild(obj){
	             $('#storey').val($(obj).val());
	         }

	         $(function(){
	        		 initDataFun('hospitalSel','astorey','${dbEquipList.dbBuilding.buildingId}');
	         } );
	       //点击所在楼宇“--请选择--”所在楼层是灰掉
	        $(document).ready(function(){
			  $("#hospitalSel").change(function(){
			    if("0".equals($("#hospitalSel").val())){
			        $("#astorey").attr("disabled",true);
			     }else{
			        $("#astorey").attr("disabled",false);
			     }
			  });
			});
             
		</script>
	</head>

	<body onload="">
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="100%" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td class="biaoti_zt_canshusz">
							设备管理——设备列表
						</td>
					</tr>
				</table>
			</div>
			<form action="equipment_findEquipmentByPage.action" method="post"
				name="myform" id="myform">
				<input type="hidden" name="currentPage" id="currentPage" value="${currentPage}" />
				<table  border="0"
					class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						<td class="shebeigl_inp_zt" align="right" width="90">
							所在楼宇：
						</td>
						<td width="80" align="left">
							<select name="dbEquipList.dbBuilding.buildingId" id="hospitalSel"   style="width: 174px;"  onchange="getStoreyByBuildId('hospitalSel','astorey','${dbEquipList.storey }','');" >
<%--								<option value="0">--请选择所在楼宇--</option>--%>
<%--								<c:forEach items="${listBuilding}" var="building">--%>
<%--									<option value="${building.buildingId}" ${building.buildingId==dbEquipList.dbBuilding.buildingId?'selected' : ''}>--%>
<%--										${building.buildingName}--%>
<%--									</option>	--%>
<%--								</c:forEach>--%>
							</select>
						</td>
						<td class="shebeigl_inp_zt" align="right" width="90">
							所在楼层：
						</td>
						<td width="80" align="right">
							<input type="text" id="astorey" name="astorey" maxlength="2" disabled="disabled"
							 onkeyup="value=value.replace(/[^\-?\d.]/g,'')"  
							 value='<fmt:formatNumber type="number" value="${dbEquipList.storey }"/>'/>
						</td>
						<td width='90' align='right' class='shebeigl_inp_zt'>
							安装位置：
						</td>
						<td width="80" align="left">
							<input type="text" name="dbEquipList.place" id="place" value="${dbEquipList.place }"/>
						</td>
						<td width="100px" align='right' class='shebeigl_inp_zt'>
							楼宇曾用名：
						</td>
						<td align="left" width='80'>
							<input type="text" name="dbEquipList.dbBuilding.hisName" id="hisName" value="${dbEquipList.dbBuilding.hisName}"/>
						</td>
					</tr>
					<tr>
						<td width='90' align='right' class='shebeigl_inp_zt'>
							设备名称：
						</td>
						<td width="80" align="left">
							<input type="text" name="dbEquipList.equipName" id="equipName" value="${dbEquipList.equipName }" />
						</td>
						<td class="shebeigl_inp_zt" align="right" width="90">
							设备编号：
						</td>
						<td width="80" align="right">
							<input type="text" name="dbEquipList.equipCode" id="equipCode" value="${dbEquipList.equipCode }" />
						</td>
                        <td width='90' align='right' class='shebeigl_inp_zt'>
							设备型号：
						</td>
						<td width="80" align="left">
							<input type="text" name="dbEquipList.unitType" id="unitType" value="${dbEquipList.unitType }"/>
						</td>
						<td width='90' align='right' class='shebeigl_inp_zt'>
							设备类型：
						</td>
						<td width="80" align="left">
							<input type="text" name="equipTypeId" id="equipTypeId" value="${dbEquipList.dbEquipType.typeName }" readonly="readonly" onclick="creatediv(this);"/>
							<input type="hidden" name="dbEquipList.dbEquipType.equipTypeId" id="eqTypeId" value="${dbEquipList.dbEquipType.equipTypeId }"/>
						</td>
					</tr>
					<tr>
					
					<td width='90' align='right' class='shebeigl_inp_zt'>
							设备状态：
						</td>
						<td width="80" align="left">
						   <input type="hidden" name="status" id="status" value="${dbEquipList.status}" />
							<select name="dbEquipList.status" id="hospitalSel" style="width: 174px;" >
										<option value="0"  ${0== dbEquipList.status?'selected':''}>正常</option>
										<option value="1"  ${1== dbEquipList.status?'selected':''}>报废</option>
							</select>
						</td>
					  <td colspan="6" align="right">
					      <img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="formCheck('1')"></img>&nbsp;&nbsp;
						   <c:if test="${!empty menuIdMap && !empty menuIdMap['2003001002']}">
						       <a href="javascript:toAdd()" class="btn blue" align="left">添加</a>
						   </c:if>
					  </td>
					</tr>
				</table>
			</form>
			<div id="divShowDetail" style=" height: 800px;width:100%;" >
			<c:if test="${!empty listEquipList}">
			
			<table  width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="4%" scope="col">
							序号
						</th>
						<th width="8%" scope="col">
							楼宇名称
						</th>
						<th width="13%" scope="col">
							设备名称
						</th>
						<th width="10%" scope="col">
						           品牌
						</th>
						<th width="8%" scope="col">
							设备型号
						</th>
						<th width="10%" scope="col">
							设备类型
						</th>
						<th width="13%" scope="col">
							安装位置
						</th>
						<th width="8%" scope="col">
							资产编号
						</th>
						<th width="10%" scope="col">
							所在楼层
						</th>
						<th width="8%" scope="col">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<c:if test="${!empty listEquipList}">
						<c:forEach items="${listEquipList}" var="equipment" varStatus="idx">
							<tr class="biaog_kan2 biaog_zt2" style="cursor: pointer;" onclick="showDetail(${equipment.equipid});">
								<td align="center">
									${idx.index+1 }
								</td>
								<td align="center">
									${equipment.building_name}
								</td>
								<td align="center" class="biao_lianj_1">
									${equipment.equip_name }
								</td>
								<td align="center">
									${equipment.brand }
								</td>
								<td align="center">
									${equipment.unittype }
								</td>
								<td align="center">
									${equipment.type_name }
								</td>
								<td align="center">
									${equipment.place }
								</td>
								<td align="center"> 
									${equipment.assetscode }
								</td>
								<td align="center"> 
								    <c:if test="${!empty equipment.storey_num_up}">
								       <c:if test="${equipment.storey<= equipment.storey_num_up&&equipment.storey>0}">
								           ${equipment.storey }
								       </c:if>
								       <c:if test="${equipment.storey>equipment.storey_num_up}">
								                                        屋顶
								       </c:if>
								    </c:if>
								    <c:if test="${equipment.storey<0}">
								   	B${equipment.storey<0?-equipment.storey:equipment.storey}
								    </c:if>
								     
								</td>
								<td align="center" class="biao_lianj_1">
									 <c:if test="${0==dbEquipList.status||null==dbEquipList.status}">
										<c:if test="${!empty menuIdMap && !empty menuIdMap['2003001003']}">
									       <a class="doOper" href="equipment_showEditUI.action?equipId=${equipment.equipid}">编辑</a>
									    </c:if>
										<c:if test="${!empty menuIdMap && !empty menuIdMap['2003001004']}">
									       <a class="doOper" href="javascript:do(0)" onclick="doDelete(${equipment.equipid},${equipment.controlflag})"}>报废</a>
									    </c:if>
								    </c:if>
								     <c:if test="${1==dbEquipList.status}">
										<span style="color:#c4b9b7;">编辑</span>
									    <span style="color:#c4b9b7;">报废</span>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			
			<c:if test="${!empty listEquipList}">
				<table width="100%" border="0" class="gai_left_duiqi">
					<tr style="height: 10px;">
						<td width="35%"></td>
						<td width="65%"></td>
					</tr>
					<tr style="height: 30px;">
						<td colspan="5">
							<sktag:paginator showTotal="true" showAllPages="true"
								strUnit="条记录" />
							
						</td>
					</tr>
				</table>
			</c:if>
			</c:if>
			<c:if test="${!empty msg}">
			   <table width="100%" style="height: 10px;float:left;">
				<tr>
					<td>
						<span class="admin_clor_f00">${ msg}</span>
					</td>
				</tr>
			  </table>
			</c:if>
			</div>
			<!--页面结束 -->
				<iframe id="ifremeShowDetail" width="100%" frameborder="0" scrolling="no"  style="overflow-x:hidden; overflow-y:auto;border:none;" height="800px;"  src="" ></iframe>
			<!-- 新短消息提示 -->
			<div id="tip">
				<h1>
					<a href="javascript:void(0)" onclick="start()">×</a>提示信息
				</h1>
				<div class="tipmsg">
					您有
					<font color="#FF0000">1</font>台设备将于7天后需要保养
					<br />
					<a href="maintenance_findServeMaintenance.action">马上查看</a>
				</div>
			</div>
		</div>

	<!--  	<script language="JavaScript">
			var eqobj = new Menu("_typesSel","subeqty","eqtypeSel");	
			<c:forEach items="${listDicEqType}" var="eqobj">
			eqobj.add("${eqobj.id }","${eqobj.name}","${eqobj.parentid }");
			</c:forEach>			
			eqobj.goTo("${eqtype}");
		</script>-->
	</body>
</html>
