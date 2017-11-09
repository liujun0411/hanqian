<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>My JSP 'lyxqJB.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
		<script type="text/javascript" src="manager/js/ceng.js"></script>
		<script type="text/javascript" src="manager/js/xialakuang.js"></script>
		<script type="text/javascript" src="manager/js/changeSelect.js"></script>
		<script type="text/javascript" src="manager/js/jsont.js"></script>
		<script type="text/javascript" src="manager/js/zxq.js"></script>
		
		<link href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/static/css/aries/jquery.aries.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/dropdown/jquery.dropdown.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script>
		<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script>
		<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script>
		<script src="<c:url value="/static/js/dropdown/jquery.dropdown.min.js"/>"></script>
		
		
<script type="text/javascript">
//任意提醒，通过标签ID给需要绑定数据的元素绑定数据
$(document).ready(function(){
	<c:if test="${empty remindJsonJB}">
		var remindJsonJB="";
	</c:if>
	<c:if test="${!empty remindJsonJB}">
		var remindJsonJB =${remindJsonJB};
	</c:if>
	 	if(remindJsonJB!=""){
		 	for(var obj in remindJsonJB){
	 	 				console.debug("   楼宇详情remindJsonJB=  " + remindJsonJB[obj].resultsql + "        sql中传的参数ID=    "+remindJsonJB[obj].sqlFieldKey.buildingId);
		 				$("#"+obj).data("aries",remindJsonJB[obj]);
		 		}
	 	}
	});
</script>
  </head>
  
  <body>
  <div style="margin-left: 15px;">
  	<input type="hidden" id="buildingId" name="buildingId"
					value="${buildingId }" />
   <table width="770"  class="louyujj_xiaxian_hui"> 

				<tr style="height: 30px;" id="divJB"  >
					<td width="160" valign="bottom" data-aries="#aries-1" id="totalArea" class="admin_bgclor_e3f aries-anchor-line" >
						总面积
					</td>
					<td width="225" valign="bottom" class="admin_clor_f00">
						<c:if test="${listBuild[0].buildingAreas !=null}">${listBuild[0].buildingAreas }&nbsp;</c:if>(平方米)
					</td>
					<td width="160" valign="bottom" data-aries="#aries-1" id="buildTotalArea" class="admin_bgclor_e3f aries-anchor-line">
						楼宇占医院总面积
					</td>
					<td width="225" valign="bottom"  class="admin_clor_f00">
					   <c:choose>
					       <c:when test="${listBuild[0].buildingAreas > 0 && hospitalArea[0].mj > 0 }">
					           <fmt:formatNumber value="${(listBuild[0].buildingAreas*100)/hospitalArea[0].mj}" pattern="###.##"/>&nbsp;%
					       </c:when>
					       <c:otherwise>0</c:otherwise>
					   </c:choose>
					    (平方米)
					</td>
				</tr>
				<tr style="height: 30px;" id="divJB"  >
					<td width="160" valign="bottom" data-aries="#aries-1" id="amount" class="admin_bgclor_e3f aries-anchor-line">
						投资额
					</td>
					<td width="235" valign="bottom" class="admin_clor_f00">
						<c:if test="${listBuild[0].amount !=null}"><fmt:formatNumber value="${listBuild[0].amount }" pattern="##,##0.00#"/>&nbsp;</c:if>(元)
					</td>
					<td width="160" valign="bottom" data-aries="#aries-1" id="completime" class="admin_bgclor_e3f aries-anchor-line">
						竣工时间
					</td>
					<td width="235" valign="bottom" class="admin_clor_f00">
						<c:if test="${listBuild[0].completime !=null}">${fn:substring(listBuild[0].completime,0,4)}年</c:if>
					</td>
				</tr>
				<tr style="height: 30px;" id="divJB"  >
					<td width="160" valign="bottom" data-aries="#aries-1" id="structure" class="admin_bgclor_e3f aries-anchor-line">
						建筑结构
					</td>
					<td width="235" valign="bottom" class="admin_clor_f00">
						${listBuild[0].structure}
					</td>
					<td width="160" valign="bottom" data-aries="#aries-1" id="storeyNum" class="admin_bgclor_e3f aries-anchor-line">
						楼层总数
					</td>
					<td width="235" valign="bottom" class="admin_clor_f00">
<%--						<c:if test="${listBuild[0].storeyNumDown+listBuild[0].storeyNumUp !='0'}">${listBuild[0].storeyNumDown+listBuild[0].storeyNumUp }&nbsp;</c:if>(层)--%>
					<c:if test="${listBuild[0].storeyNumUp !=null && listBuild[0].storeyNumDown !=null}">地上${listBuild[0].storeyNumUp}层<br/>
						地下${listBuild[0].storeyNumDown}层</c:if>
					</td>
				</tr>
				<tr style="height: 30px;" id="divJB"   >
					<td width="160" valign="bottom" data-aries="#aries-1" id="buildMaterByQuakeproof" class="admin_bgclor_e3f aries-anchor-line" >
						抗震烈度
					</td>
					<td width="235" valign="bottom" class="admin_clor_f00">
						<c:forEach var="s" items="${listMater}">
								<c:if test="${! empty listBuild[0].dbBuildMaterByQuakeproof }">
									<c:if test="${s.mater_Id == listBuild[0].dbBuildMaterByQuakeproof.materId}">
									${s.mater_name}
									</c:if>
								</c:if>
						</c:forEach>
					</td>
					<td width="160" valign="bottom" data-aries="#aries-1" id="buildHeight" class="admin_bgclor_e3f aries-anchor-line">
						高度
					</td>
					<td width="235" valign="bottom" class="admin_clor_f00">
						<c:if test="${listBuild[0].height !=null}">${listBuild[0].height }&nbsp;</c:if>(米)
					</td>
				</tr>
			</table>
			</div>
  </body>
</html>
