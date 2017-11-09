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
    
    <title>My JSP 'lyxqJGandDX.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />

		
		
		<link href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/static/css/aries/jquery.aries.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/dropdown/jquery.dropdown.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script>
		<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script>
		<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script>
		<script src="<c:url value="/static/js/dropdown/jquery.dropdown.min.js"/>"></script>

		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
		<script type="text/javascript" src="manager/js/ceng.js"></script>
		<script type="text/javascript" src="manager/js/xialakuang.js"></script>
		<script type="text/javascript" src="manager/js/changeSelect.js"></script>
		<script type="text/javascript" src="manager/js/jsont.js"></script>
		<script type="text/javascript" src="manager/js/zxq.js"></script>
  <script type="text/javascript">
   	
  	onload=function(){
  		if("${shows}"=="1" || "${shows}"=="2" ){
  			$("#div1").show();
  			$("#div2").show();
  		}else{
  			$("#div1").hide();
  			$("#div2").hide();
  		}
  	}
  	
  	function goBack(){
  		 parent.location.href="buildRepair!findBuildingRepair.action";
  	}
  	
  	function formCheck(currentPage){
  				$("#buildid").val($("#sequenc").val());
  				if(currentPage != "")
					$("#page").val(currentPage);
					document.myform.submit();			
	}
  	
  //任意提醒，通过标签ID给需要绑定数据的元素绑定数据
  	$(document).ready(function(){
  		<c:if test="${empty remindJsonJG}">
			var remindJsonJG="";
		</c:if>
		<c:if test="${!empty remindJsonJG}">
			var remindJsonJG =${remindJsonJG};
		</c:if>
  		 	if(remindJsonJG!=""){
  		 	for(var obj in remindJsonJG){
 	  	 				console.debug("    remindJsonJG:    ="+remindJsonJG[obj].resultsql);
  		 				$("#"+obj).data("aries",remindJsonJG[obj]);
  		 		}
  		 	}
  		});
  
  </script>
  </head>
  <body>
    <!-- 用途所占面积信息 -->
			<div id="div1" style="margin-top: 0px;width: 760px; " class="gai_left_duiqi">
				<table width="100%" border="0" align="center">
					<tr valign="middle" style="height:25px;">
						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							 data-aries="#aries-1" id="outpatient">门诊</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_menz"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[0].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[0].mj/BUIAREA}" type="percent"  maxFractionDigits="2"/></c:if>
占总门诊面积：<fmt:formatNumber value="${buildarea[0].mj/builduseArea[0].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[0].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[0].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[0].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="emergency">急诊</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_jiz"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[1].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[1].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总急诊面积：<fmt:formatNumber value="${buildarea[1].mj/builduseArea[1].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[1].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[1].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[1].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>
						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="hospitalization">住院</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_bingf"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[2].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[2].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总住院面积：<fmt:formatNumber value="${buildarea[2].mj/builduseArea[2].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[2].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[2].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[2].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="medical">医技</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_yij"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[3].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[3].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总医技面积：<fmt:formatNumber value="${buildarea[3].mj/builduseArea[3].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[3].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[3].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[3].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>
					</tr>
					
					<tr align="center" valign="middle" style="height: 5px;"><td colspan="15"></td></tr>
					<tr valign="middle">
						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="security">保障</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_houq"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[4].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[4].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总保障面积：<fmt:formatNumber value="${buildarea[4].mj/builduseArea[4].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[4].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[4].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[4].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="administration">行政</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_xingz"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[5].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[5].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总行政面积：<fmt:formatNumber value="${buildarea[5].mj/builduseArea[5].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[5].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[5].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[5].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="scientific">科研</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_key"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[6].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[6].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总科研面积：<fmt:formatNumber value="${buildarea[6].mj/builduseArea[6].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[6].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[6].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[6].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="education">教育</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_jiaox"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[7].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[7].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总教育面积：<fmt:formatNumber value="${buildarea[7].mj/builduseArea[7].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[7].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[7].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[7].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>

					</tr>
					<tr align="center" valign="middle" style="height: 5px;"><td colspan="15"></td></tr>
					<tr>
						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="life">生活</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_shengh"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[8].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[8].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总生活面积：<fmt:formatNumber value="${buildarea[8].mj/builduseArea[8].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[8].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[8].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[8].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="garage">车库</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_chek"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[9].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[9].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总车库面积：<fmt:formatNumber value="${buildarea[9].mj/builduseArea[9].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[9].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[9].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[9].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span
							data-aries="#aries-1" id="others">其它</span></td>
						<td valign="middle" width="50"><div class="gongnengqu_ktf_1 quyuyanse_qit"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:if test="${buildarea[10].mj>0.0}">
								<a title="<c:if test="${BUIAREA > 0}">占楼宇面积：<fmt:formatNumber value="${buildarea[10].mj/BUIAREA}" type="percent" maxFractionDigits="2"/></c:if>
占总其他面积：<fmt:formatNumber value="${buildarea[10].mj/builduseArea[10].mj}" type="percent" maxFractionDigits="2"/>
占医院总面积：<fmt:formatNumber value="${buildarea[10].mj/hospitalArea[0].mj}" type="percent" maxFractionDigits="2"/> ">${buildarea[10].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
							</c:if>
							<c:if test="${!(buildarea[10].mj>0.0)}">
								0&nbsp;㎡
							</c:if>
						</td>
					</tr>
				</table>
				
				<form name="myform" method="post" action="buildRepair!findBuildingRepair.action">
				<input type="hidden" name="shows" value="2" id="shows" />
				<table width="760px" border="0" align="center" class="louyujj_xiaxian_hui gai_left_duiqi">
			    <tr style="height:35px;">
				<td></td>
				<td  align="right" class="shebeigl_inp_zt">楼宇：</td>
						<td width="80" align="right">
							<select id="buildingId" name="buildingId">
							<c:forEach items="${builds}" var="build">
							<option value="${build.building_id}"  ${build.building_id ==buildingId ? 'selected' : ''}>${build.building_name}</option>
							</c:forEach> 
							</select> 
						</td>
						<td width="80" align="right"> 
							<img src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="formCheck('')" style="cursor: pointer;"/></td>
						<td width="50" align="right">
							<a onclick="goBack();" class="btn blue">返回</a>
						</td>
					</tr>
				</table>  
					<input type="hidden" name="page" value="${currentPage}" id="page" />
					<input type="hidden" name="hospitalid" value="${hospitalid}"/>
					<input type="hidden" name="xX" value="1" id="xX"/>  
					<input type="hidden" name="buildid" value="${buildid}" id="buildid"/> 
			 </form> 
			</div>
			
  <div style="margin-left: 15px; margin-top: 0px;width: 770px; "  class="gai_left_duiqi">
    <table border="0" align="center" width="100%">
                <tr style="height: 30px;" id="divJB">
					<td width="180" align="left">
						<span data-aries="#aries-1" id="buildMaterByCostaccord" class="aries-anchor-line">造价依据</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					  <c:forTokens items="${listBuild[0].dbBuildMaterByCostaccord.materId}"  delims=", " var="val">
						<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_id == val}"> ${s.mater_name}</c:if>
						</c:forEach>
					  </c:forTokens>
					</td>
					<td width="180" align="left"> 
						<span data-aries="#aries-1" id="proCost" class="aries-anchor-line">建安工程造价 </span>
					</td> 
					<td width="235" align="left" class="admin_clor_f00">
					<fmt:formatNumber value="${listBuild[0].proCost}" pattern="#,#00.00#"/>&nbsp;(元)
					</td> 
				</tr> 
				<tr style="height: 30px;" id="divJB">
					<td width="180" align="left" >
						<span data-aries="#aries-1" id="audits" class="aries-anchor-line">审计结果 </span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					${listBuild[0].audits} 
					</td>
					 <td width="175" align="left">
						<span data-aries="#aries-1" id="mendNum" class="aries-anchor-line">大修次数 </span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
				       ${listBuild[0].mendNum}
					</td>
				</tr>
				<tr style="height: 30px;" id="divJB">
			        
					<td width="180" align="left">
						<span data-aries="#aries-1" id="buildMaterByProblem" class="aries-anchor-line">改造前主要问题</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					<c:forTokens items="${listBuild[0].dbBuildMaterByProblem.materId}"  delims=", " var="val">
						<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_Id == val}">${s.mater_Name}</c:if>
						</c:forEach> 
						</c:forTokens>
					</td>
					<td width="180" align="left">
						<span data-aries="#aries-1" id="structures" class="aries-anchor-line">建筑结构</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
                 <c:forTokens items="${listBuild[0].structure}"  delims=", " var="val">
					<c:forEach var="s" items="${listMater}">
						<c:if test="${s.mater_Name == val}">${s.mater_Name}</c:if>
					</c:forEach>
				</c:forTokens>
					</td>
				    
				</tr>
				
    
				<tr style="height: 30px;" id="divJB">
				
				
					<td width="180" align="left">
						<span data-aries="#aries-1" id="outWall" class="aries-anchor-line">外墙材料</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
                 <c:forTokens items="${listBuild[0].outWall}"  delims=", " var="val">
					<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_Id == val}">${s.mater_Name}</c:if>
					</c:forEach>
				</c:forTokens>
					</td>
					<td width="180" align="left">
						<span data-aries="#aries-1" id="doorMater" class="aries-anchor-line">门用材料</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					<c:forTokens items="${listBuild[0].doorMater}"  delims=", " var="val">
						<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_Id == val}">${s.mater_Name}</c:if>
						</c:forEach>
						</c:forTokens>
					</td>
				</tr>
				<tr style="height: 30px;" id="divJB">
				    
					<td width="180" align="left">
						<span data-aries="#aries-1" id="windowMater" class="aries-anchor-line">窗用材料</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					<c:forTokens items="${listBuild[0].windowMater}"  delims=", " var="val">
						<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_Id == val}">${s.mater_Name}</c:if>
						</c:forEach>
						</c:forTokens>
					</td>
					<td width="180" align="left">
						<span data-aries="#aries-1" id="ceilingMater" class="aries-anchor-line">屋内顶材料</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					<c:forTokens items="${listBuild[0].ceilingMater}"  delims=", " var="val">
						<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_Name == val}">${s.mater_Name}</c:if>
						</c:forEach>
						</c:forTokens>
					</td>
				</tr>
				<tr style="height: 30px;" id="divJB">
					
					<td width="180" align="left">
						<span data-aries="#aries-1" id="wallMater" class="aries-anchor-line">墙体材料</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					<c:forTokens items="${listBuild[0].wallMater}"  delims=", " var="val">
						<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_Id == val}">${s.mater_Name}</c:if>
						</c:forEach>
					</c:forTokens>
					</td>
					<td width="180" align="left">
						<span data-aries="#aries-1" id="floorMater" class="aries-anchor-line">地板材料</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					<c:forTokens items="${listBuild[0].floorMater}"  delims=", " var="val">
						<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_Id == val}">${s.mater_Name}</c:if>
						</c:forEach>
						</c:forTokens>
					</td>
				</tr>
				<tr>
				  
					<td width="180" align="left">
						<span data-aries="#aries-1" id="buildMaterByWaterproof" class="aries-anchor-line">屋面防水等级</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					<c:forTokens items="${listBuild[0].dbBuildMaterByWaterproof.materId}"  delims=", " var="val">
						<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_Id == val}">${s.mater_Name}</c:if>
						</c:forEach>
					</c:forTokens>
					</td>
					
					<td width="160" align="left">
						<span data-aries="#aries-1" id="buildMaterByQuakeproofs" class="aries-anchor-line">抗震烈度</span>
					</td>
					<td width="235" align="left" class="admin_clor_f00">
					<c:forTokens items="${listBuild[0].dbBuildMaterByQuakeproof.materId}"  delims=", " var="val">
						<c:forEach var="s" items="${listMater}">
										<c:if test="${s.mater_Id == val}">${s.mater_Name}</c:if>
						</c:forEach>
						</c:forTokens>
					</td>
				</tr>
			</table>
			</div>
			<div id="div2" style=" width: 770px;margin-left: 10px; "  class="gai_left_duiqi">
			<center>
			   <table width="100%" border="0" cellspacing="1" align="center" class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<th width="13%" scope="col">大修次数</th>
							<th width="17%" scope="col">时间</th>
							<th width="25%" scope="col">费用（RMB：元）</th>
							<th width="28%" scope="col">维修内容</th>
						</tr>
					</thead>
					<tbody id="stu-datas-tb2">
						<c:forEach items="${listRepair}" var="repair" varStatus="s">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">第${s.count }次<input type="hidden" id="sequence" value="${repair.seq}" /></td>
								<td align="center">${repair.repairttime}</td>
								<td align="center"><fmt:formatNumber value="${repair.totalcost}" pattern="#,#00.00#"/></td>
								<td align="center">${repair.remark}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</center>
			</div>
			</body>
</html>
