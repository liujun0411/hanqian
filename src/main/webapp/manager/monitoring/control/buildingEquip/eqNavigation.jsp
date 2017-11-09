<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>My JSP 'eq_Navigation.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		
	<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<!-- 引入公用控件 -->
    <script type="text/javascript" src="manager/common/equip/js/jquery-1.3.2.min.js"></script>
	<link  href="manager/common/equip/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
	<script src="manager/common/equip/js/ligerComboBox.js" type="text/javascript"></script>
	<script src="manager/common/equip/js/ligerResizable.js" type="text/javascript"></script>
	<script src="manager/common/equip/js/ligerTree.js" type="text/javascript"></script>
	<script src="manager/common/equip/js/createTree.js" type="text/javascript"></script>
	
	<script src="manager/js/annu.js" type="text/javascript"></script>
	<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
	<script type="text/javascript" src="manager/js/ceng.js"></script>
	<style type="text/css">
	   .eqList {
			display: none;
			position: absolute;
			width: 80%;
			margin-top:-7px;
			padding: 8px;
			border: 8px solid #E8E9F7;
			background-color: white;
			z-index: 1002;
			overflow: auto;
		}
		#bg {
			display: none;
			position: absolute;
			top: 0%;
			left: 0%;
			width: 100%;
			height: 100%;
			background-color: black;
			z-index: 1001;
			-moz-opacity: 0.2;
			opacity: .20;
			filter: alpha(opacity =20);
		}
		.close {
			float: right;
			font-size: 24px;
			color: #FF0000;
			text-decoration:none;
			font-weight: bold;
		}
		#title {
			font-size: 16px;
			color: blue;
			float: left;
			height:20px;	
		}
		.onetr {
			background-color: #CFE9EF;
		}
	</style>
	<script type="text/javascript">
		
		function formCheck(currentPage) {
				$("#page").val(currentPage);
				document.myForm.submit();
		}

		 //设备类型菜单回调函数
		   function doClick(note){
		      $('#equipTypeId').val(note.data.text);
		      $('#eqTypeId').val(note.data.id);
		      $("#listDiv_List").hide();
		   }
			
		f_biao=function()
		{
		 $("#biaoDiv").show();
		 $("#imgDiv").hide();
		 $("#showEqType").show();
		 $("#showImg").attr({ src: "manager/images/imgs/gaiditu1.png"});
		 $("#showTable").attr({ src: "manager/images/imgs/gailiebiao2.png"});
		}
		
		onload = function (){
			 $("#biaoDiv").show();
			 $("#showEqType").show();
			 $("#showTable").attr({ src: "manager/images/imgs/gailiebiao2.png"});
		}
		
		doControl=function(groupId,eqTypeId){
		    $("#eqTypeIdInfo").val(eqTypeId);
	        $("#groupId").val(groupId);
	        $("#goControlForm").attr("action","control_findToControl.action");
	        $("#goControlForm").submit();
		}
		
		f_change=function()
		{
		
		}
		var gotoControl = function(equipId,equipTypeId){
		    //如果是新风机，空调机直接进入监控页面
		    if(equipTypeId=="1001" || equipTypeId=="1002"){
		       if(jQuery.trim(equipId)!=""){
			       $("#goControlForm").attr("action","control_findToControl.action");
			       $("#equipid").val(equipId);
			       $("#eqTypeIdInfo").val(equipTypeId);
			       $("#goControlForm").submit();
		       }
		    }else{
			    //异步请求调用，查看设备所属组
			    if(jQuery.trim(equipId)!="" && jQuery.trim(equipTypeId)!=""){
				    $.ajax({
					      type: 'get',
						  url: "equipGroupAction!findGroupByEquip.action?equipId="+equipId,
						  cache:false,
						  dataType: 'json',
						  success: function(data){
						     var dt=eval(data);
						     if(dt.length>0){
							     //展示列表
							     var app = "<table width='100%' style=' margin-bottom:50px;' border='0' cellspacing='1' class='biaoge_bg1 gai_left_duiqi'><tr class='biaoge_tr0 biaog_kan1 biaog_zt1'><td align='center'>"
									+ "序号</td><td align='center'>组名称</td><td align='center'>系统类型</td><td align='center'>备注"
									+ "</td></tr>";
								 for ( var k = 0; k < dt.length; k++) {
									if (k % 2 == 0) {
										app += "<tr onclick='doControl("+dt[k].seq+",4);' class=' biaog_kan2 biaog_zt2' style='background-color: rgb(255, 255, 255);' id='"
									} else {
										app += "<tr onclick='doControl("+dt[k].seq+",4);' class='biaog_kan2 biaog_zt2'  style='background-color: rgb(207, 233, 239);' id='"
									}
									app += dt[k].seq
										+ "'><td align='center'>"
										+ (k + 1)
										+ "</td>"
										+ "<td align='center'>"
										+ dt[k].group_name
										+ "</td><td align='center'>"
										+ dt[k].type_name
										+ "</td><td align='center'>"
										+ (dt[k].remark == undefined ? '' : dt[k].remark)
										+ "</td></tr>";
								   }
								   app += "</table>";
								   $(".eqList").append(app);
								   $('#bg').show();
				                   $('#title').text("所属分组列表");
				                   $('.eqList').show();
						     }else{
						        //单设备且为新风机，空调机，电表（不分组）
						        if(equipTypeId=="1001" ||equipTypeId=="1002" ||equipTypeId=="10001"){
							        if(equipTypeId=="10001"){
									     $("#goControlForm").attr("action","control_showPowerContorl.action");
							        }else{
							        	 $("#goControlForm").attr("action","control_findToControl.action");
							        }
							        $("#equipid").val(equipId);
							        $("#goControlForm").submit();
						        }else{
						           alert("该设备未提供监控界面！");
						        }
						     }
						  },
						  error:function(textStatus){
						      alert('error'+textStatus);
						  }
					 });
			    }
		    }
		}

		
		</script>
  </head>
  
  <body style="overflow:scroll;overflow-x:hidden;overflow-y:hidden; ">
  	<form id ="goControlForm" action="">
  	    <input type="hidden"  name="eqTypeIdInfo" id="eqTypeIdInfo" value=""/>
	    <input type="hidden" id="equipid" name="equipId" value=""/>
	    <input type="hidden" id="groupId" name="groupId" value=""/>
  	</form>
     <div id="admin_nr_rightg">
     <div class="canshusz_btbg_1">  
		<table width="100%" border="0">
			<tr style="height: 30px">
				<td width="34" align="center">
					<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
				</td>
				<td width="679" class="biaoti_zt_canshusz">
					楼宇设备
				</td>
			</tr>
		</table>
     </div>
     
<%--     <div class="admin_nr_right_g02">--%>
<%--        <table width="100%" border="0">--%>
<%--          <tr style="height:25px;" valign="middle">--%>
<%--            <td width="83" style="cursor: pointer"><img src="manager/images/imgs/gailiebiao2.png " id="showTable" onclick="f_biao()"  /></td>--%>
<%--           <td></td>--%>
<%--          </tr>--%>
<%--        </table>--%>
<%--        </div>--%>
        
        <form name="myForm" action="navigation_showEquipList.action" method="post" id="myForm">
				<input type="hidden" name="showType" value="biao"/>
				<input type="hidden" name="currentPage" value="${currentPage}" id="page" />
				<input type="hidden" value="${voEquipBuild.eqTypeId }" name="eqTypeId " id="eqType"/>
				
				<div id="showEqType">
				<table width="100%" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						
			   			<td id='jiegou' width='90' align='right' class='shebeigl_inp_zt' >所在楼宇：</td>
						<td width="80" align="left">
				 		<select id="buildId" name="buildId" style="width: 155px;" >
						<option value="0">
							--请选择--
						</option>
						<c:forEach items="${buildList}" var="builds">
							<option value="${builds.building_id }" ${builds.building_id==voEquipBuild.buildId?'selected' : ''}>
								${builds.building_name }
								
							</option>
						</c:forEach>
						</select>
						</td>
				  		<td id='jiegou' width='90' align='right' class='shebeigl_inp_zt' >所在楼层：</td>
					  		<td width="80" align="left" id="td_buildStorey">
					  			<input type="text"  name="storey" id="storey" value="${voEquipBuild.storey}" onkeyup="if(isNaN(value)&&value!='-')execCommand('undo')"/>
					  		
					  		</td>
				  		<td id='jiegou' width='90' align='right' class='shebeigl_inp_zt' >设备型号：</td>
				  		<td align="left">
				  			<input type="text" name="unitType" value="${voEquipBuild.unitType}"/>
				  		</td>
			   			
				  		<td width="80">
				  			<div id="eqTypeChilds"></div>
				  		</td>
				  		
				  		</tr>
				  		<tr>
					  		<td id='jiegou' width='90' align='right' class='shebeigl_inp_zt' >设备类型：</td>
							<td width="80" align="left">
								<input type="text" name="typeName" id="equipTypeId" value="${voEquipBuild.typeName }" readonly="readonly" onclick="creatediv(this);"/>
	<%--				  					<input type="text" name="typeName" id="equipTypeId" value="${voEquipBuild.typeName }" readonly="readonly" />--%>
					  			<input type="hidden" name="eqTypeId" id="eqTypeId" value="${voEquipBuild.eqTypeId }"/>
					  		</td>
					  		<td id='jiegou' width='90' align='right' class='shebeigl_inp_zt' >安装位置：</td>
					  		<td  width="80" align="left">
					  			<input type="text" name="place" value="${voEquipBuild.place}"/>
					  		</td>
				  			
					  		<td id='jiegou' width='90' align='right' class='shebeigl_inp_zt' >服务楼宇：</td>
					  		<td width="80" align="left">
					  		<select id="serviceBuildId" name="serviceBuildId" style="width: 155px;">
								<option value="0">
									--请选择--
								</option>
								<c:forEach items="${buildList}" var="builds">
									<option value="${builds.building_id }" ${builds.building_id==voEquipBuild.serviceBuildId?'selected' : ''}>
										${builds.building_name }
									</option>
								</c:forEach>
							</select>
					  		</td>
					  		<td></td>
				  		</tr>
				        <tr>
					  		<td id='jiegou' width='90' align='right' class='shebeigl_inp_zt' >服务楼层：</td>
					  		<td  width="80" align="left">
					  			<input type="text" name="serviceStorey" value="${voEquipBuild.serviceStorey}"/>
					  		</td>
				  			<td id='jiegou' width='90' align='right' class='shebeigl_inp_zt' >服务区域：</td>
					  		<td width="80" align="left">
					  			<input type="text"  name="square" id="square" value="${voEquipBuild.square}" onkeyup="if(isNaN(value)&&value!='-')execCommand('undo')"/>
					  		
					  		</td>
					  		<td id='jiegou' width='90' align='right' class='shebeigl_inp_zt' >服务面积：</td>
					  		<td width="80" align="left">
					  			<input type="text"  name="areas" id="areas" value="${voEquipBuild.areas}"/>
					  		
					  		</td>
					  		<td  width="80" align="right"><img style='cursor: pointer' src="manager/images/imgs/shebeigl_sousuo_an.png" id="sousuo" onclick="formCheck('')"/></td>
					  		
				  		</tr>
				</table>
				</div>
		  </form>
        
        
        		 <div  id="biaoDiv" class="gai_left_duiqi" style="display: none; width:100%;"> 
			<table width="100%" border="0" cellspacing="1" class="biaoge_bg1">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="4%" scope="col">
							序号
						</th>
						<th width="10%" scope="col">
							楼名
						</th>
						<th width="15%" scope="col">
							设备名称
						</th>
						<th width="15%" scope="col">
							设备类型
						</th>
						<th width="10%" scope="col">
							设备型号
						</th>
						<th width="10%" scope="col">
							所在楼层
						</th>
						<th width="15%" scope="col">
							安装位置
						</th>
												
						<th  scope="col">
							服务楼宇 
						</th>
						<th  scope="col">
							服务楼层
						</th>
						<th  scope="col">
							服务面积
						</th>
						<th  scope="col">
							服务区域
						</th>
						
<%--						<th  scope="col" colspan="4">--%>
<%--							服务区域--%>
<%--						</th>--%>
					</tr>
				</thead>

				<c:if test="${!empty equipBuildlist}">
					<tbody id="stu-datas-tb">
						<c:forEach items="${equipBuildlist}" var="eqBuildlist" varStatus="idx">
							<tr class=" biaog_kan2 biaog_zt2" onclick="javascript:gotoControl('${eqBuildlist.equipId}','${eqBuildlist.eqTypeId }');">
								<td align="center">
									<c:out value='${idx.index+1}' />
								</td>
								<td align="center" class="biao_lianj_1">
									${eqBuildlist.buildName }
								</td>
								<td align="center">
									${eqBuildlist.equipName }
								</td>
								<td align="center">
								    ${eqBuildlist.typeName}
								</td>
								<td align="center">
									${eqBuildlist.unitType}
								</td>
								<td align="center">
									${eqBuildlist.storey}
								</td>
								<td align="center">
									${eqBuildlist.place}
								</td>
								<td align="center">
								<c:forEach items="${buildList}" var="builds">
									<c:if test="${builds.building_id==eqBuildlist.serviceBuildId}">
										${builds.building_name }
									</c:if>
								</c:forEach>
								</td>
								<td align="center">
								  ${eqBuildlist.serviceStorey }
								</td>
								<td align="center">
								  ${eqBuildlist.areas }
								</td>
								<td align="center">
								  ${eqBuildlist.square }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${!empty equipBuildlist}">
				<table width="100%" border="0">
					<tr  style="height: 50px;">
					<td></td>
						<td>
							<sktag:paginator showTotal="true" showAllPages="true"  strUnit="条记录" />
						</td>
					</tr>
				</table>
			</c:if>
		</div>	
     </div>
	<div class="ss_yiyuan_tuk_1" id="mydiv" style="display: none;position: absolute;">
  
</div>
        <!-- 遮罩层 -->
		<div id="bg"></div>
		<div class="eqList">
			<span id="title"></span>
			<a href="javascript:void(0);" class="close" onclick="hidediv()" title="关闭列表">×</a>
		</div>
  </body>
</html>
