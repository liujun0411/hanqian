<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<base href="<%=basePath%>" />
		<title>医院后勤智能化管理平台</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" href="manager/css/neirong.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/skdiv.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/ziti.css" type="text/css"></link>
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
		<link rel="stylesheet" href="manager/common/tab/ui.tabs.css" type="text/css" media="print, projection, screen"></link>
		<link href="manager/common/fullScreen/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>
		<script type="text/javascript" src="manager/common/fullScreen/tipswindown.js"></script>
<script type="text/javascript" src="manager/javascript/buildStorey/buildStorey.js"></script>
	   
	
		<script type="text/javascript">
		  doSubmit=function(){
		       //如果输入的楼层
		       if($('#inpStorey').css('display')=="inline"){
		          $('#storey').val($('#inpStorey').val());
		       }
		       document.myform.submit();
		    }
		
		showEquip = function(seq) {
			$("#seq").val(seq);
			$("#myform").attr( {
				action : 'control_showGroupEquipList.action'
			})
			$("#myform").submit();
		}

		$(function() {
			$(".ahref").click(function(event) {
				event.stopPropagation();
			});
		});

        //显示监控界面
        function showMonitor(groupId){
              $('#control_Info').slideToggle(1000);
              $('#pageList div').slideToggle(1000);
              $('#Control_page').attr({src:'control_findToControl.action?groupId='+groupId+"&eqTypeId="+$('#eqTypeId').val()+"&t="+Math.random()});
        }
        
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
				         $("#storey").val('');
					  },
					  error:function(textStatus){
					      alert('error'+textStatus);
					  }
				 });
             }else{
                 var cid=$(childId).attr('id');
				 $("'#"+cid+"' option").remove();
			     $(childId).append("<option value=''>--请选择楼层--</option>");
			     $("#"+cid).hide();
			     $('#inpStorey').val('');
			     $('#inpStorey').show();
             }
         }
         
         function selectChild(obj){
             $('#storey').val($(obj).val());
         }
         $(function(){
    		 initDataFun('buildId','storey','${buildId}');
     } );
     //点击所在楼宇“--请选择--”所在楼层是灰掉
	 $(document).ready(function(){
				  $("#buildId").change(function(){
				    if("0".equals($("#buildId").val())){
				        $("#storey").attr("disabled",true);
				     }else{
				        $("#storey").attr("disabled",false);
				     }
				  });
				});
</script>
	</head>

	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<div id="admin_nr_rightg">
			  <div class="canshusz_btbg_1">
					<table class="titleBg">
						<tr style="height: 20px">
							<td width="30" align="center">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td>
								配电系统
							</td>
						</tr>
					</table>
			  </div>
          <div id="pageList">
            <form action="controlPage_showEquipList.action" method="get" id="myform" name="myform">
				<input name="seq" value="${seq}" type="hidden" id="seq" />
				<input type="hidden" name="mpage" value="${currentgPage}" id="gpage" />
				<input name="eqTypeId" value="${eqTypeId }" type="hidden" id="eqTypeId" />
				<div id="serachDiv" class="serachDiv">
					<table width="100%" border="0"
						class="serachTable">
						<tr style="height: 50px;" valign="middle">
							<td width="70" align="left">
								所在楼宇：
							</td>
							<td width="160">
								<select name="buildId" id="buildId" style="width: 140px;" onchange="getStoreyByBuildId('buildId','storey','${storey }','');">
<%--									<option value="">--%>
<%--										--请选择所在楼宇----%>
<%--									</option>--%>
<%--									<c:forEach items="${buildList}" var="build">--%>
<%--										<option value="${build.buildingId }" ${buildId==build.buildingId?'selected':''}>--%>
<%--											${build.buildingName }--%>
<%--										</option>--%>
<%--									</c:forEach>--%>
								</select>
							</td>
							<td width="70" align="left">
								所在楼层：
							</td>
							<td width="160">
<%--								<select id="selStorey" name="selStorey" style=" width:155px; <c:if test="${empty buildId}">display: none;</c:if> <c:if test="${!empty buildId}">display: block;</c:if>" onchange="selectChild(this);">--%>
<%--								    <option value=''>--请选择楼层--</option>--%>
<%--								    <c:if test="${!empty storeyList}">--%>
<%--									    <c:forEach items="${storeyList}" var="st">--%>
<%--										   <option value="${st}" ${st==storey?'selected="selected"':''}>--%>
<%--		                                     ${fn:replace(st,'-','B')}--%>
<%--		                                   </option>--%>
<%--										</c:forEach>--%>
<%--								    </c:if>--%>
<%--							    </select>--%>
<%--							    <input type="text" id="inpStorey" name="inpStorey" <c:if test="${empty buildId}"> value="${storey}" style=" display:block;"</c:if><c:if test="${!empty buildId}"> style=" display:none;"</c:if> onkeyup="value=value.replace(/[^\d]/g,'') "  onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />--%>
							    <input type="text" id="storey" name="storey" value="${storey}"
							    disabled="disabled" maxlength="2" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"/>
							</td>
							<td align="center" width="90px">
								<img style='cursor: pointer'
									src="manager/images/imgs/shebeigl_sousuo_an.png"
									onclick="doSubmit()" />
							</td>
							<td>
							&nbsp;
							</td>
						</tr>
					</table>
			    </div>
			</form>
            <div class="itemList">
				<table width="100%" cellspacing="1"
					class="listTable">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<td align="center" width="10%">
								序号
							</td>
							<td align="center" width="20%">
								名称
							</td>
							<td align="center" width="15%">
								所在楼宇
							</td>
							<td align="center" width="15%">
								所在楼层
							</td>
							<td align="center" width="10%">
								配电柜数量
							</td>
							<td align="center" width="10%">
								备注
							</td>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:forEach items="${equipGroupList}" var="eqGroup"
							varStatus="idx">
							<tr class="${idx.index%2==0?'tr1':'tr2'}" id="trhref"
								onclick="showMonitor(${eqGroup.seq})">
								<td align="center">
									${idx.index+1 }
								</td>
								<td align="center">
								    ${eqGroup.group_name}
								</td>
								<td align="center">
									${eqGroup.building_name }
								</td>
								<td align="center">
								<c:if test="${!empty equipment.storey_num_up}">
							       <c:if test="${eqGroup.storey<= eqGroup.storey_num_up&&eqGroup.storey>0}">
							       <fmt:formatNumber type="number" maxFractionDigits="1" value="${eqGroup.storey}" />
							       </c:if>
							       <c:if test="${eqGroup.storey>eqGroup.storey_num_up}">
							                            屋顶
							       </c:if>
							    </c:if>
							    <c:if test="${eqGroup.storey<0}">
							    B<fmt:formatNumber type="number" maxFractionDigits="1" value="${eqGroup.storey<0?-eqGroup.storey:eqGroup.storey}" />
							 </c:if>
								</td>
								<td align="center">
									${eqGroup.totalcount }
								</td>
								<td align="center">
									${eqGroup.remark }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<c:if test="${!empty equipGroupList}">
				<!-- 分页从这里开始 -->
				<div class="PageingEquip">
				<table width="100%" border="0">
					<tr style="height: 50px;">
						<td align="center">
							<sktag:paginator showTotal="true" showAllPages="true"
								strUnit="条记录" />
						</td>
					</tr>
				</table>
				</div>
				<!-- 分页在这里结束 -->
			</c:if>
          </div>	
		</div>

        <!-- Control -->
        <div id="control_Info" style=" display: none;">
		    <iframe src="" id="Control_page" scrolling=no width="100%" height="800" marginwidth=0
			   marginheight=0 frameborder=0></iframe>
	    </div>
	</body>
</html>