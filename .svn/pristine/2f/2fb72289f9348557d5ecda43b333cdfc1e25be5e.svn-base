<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>楼宇用途</title>
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>

		<link href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/static/css/aries/jquery.aries.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/dropdown/jquery.dropdown.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script>
		<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script>
		<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script>
		<script src="<c:url value="/static/js/dropdown/jquery.dropdown.min.js"/>"></script>

		<script type="text/javascript">
//保留两位小数
var getFloatPer = function(firstNum, secondNum) {
	var floatNum = (firstNum / secondNum).toFixed(2);
	return floatNum * 100;
}

//任意提醒，通过标签ID给需要绑定数据的元素绑定数据
$(document).ready(function(){
	<c:if test="${empty remindJson}">
		var remindJson="";
	</c:if>
	<c:if test="${!empty remindJson}">
		var remindJson=${remindJson};
	</c:if>
	 	if(remindJson!=""){
		 	for(var obj in remindJson){
	 	 				console.debug("   emindJson[obj].resultsql=     "+remindJson[obj].resultsql);
		 				$("#"+obj).data("aries",remindJson[obj]);
		 		}
	 	}
	});
</script>
	</head>
	<body style="overflow: scroll; overflow-x: hidden;text-align: left">
		<div id="louyuDiv">
			<center>
				<div id="div1" style="margin-top: 10px;" class="gai_left_duiqi">
					<table width="100%" border="0" align="center">
						<c:forEach items="${buildUseList}" var="buildUse" begin="0"
							step="4" varStatus="i">
							<tr valign="middle" style="height: 25px;">
								<c:if test="${!empty buildUseList[i.index].seq}">
									<td align="center" class="cahshusz_qumc_zt1 aries-anchor-line" data-aries="#aries-1" width="80">
										${buildUseList[i.index].content1}
									</td>
									<c:forEach items="${colorMap}" var="colorEn">
										<c:if test="${colorEn.key==buildUseList[i.index].seq}">
											<td valign="middle" width="30">
												<div class="gongnengqu_ktf_1"
													style="background-color: ${colorEn.value }"></div>
											</td>
										</c:if>
									</c:forEach>
									<td valign="middle" class="cahshusz_bfb_zt1" width="85">
										${buildUseList[i.index].a_area}㎡
									</td>
								</c:if>
								<c:if test="${!empty buildUseList[i.index+1].seq}">
									<td align="center" class="cahshusz_qumc_zt1" width="80">
										${buildUseList[i.index+1].content1}
									</td>
									<c:forEach items="${colorMap}" var="colorEn">
										<c:if test="${colorEn.key==buildUseList[i.index+1].seq}">
											<td valign="middle" width="30">
												<div class="gongnengqu_ktf_1"
													style="background-color: ${colorEn.value }"></div>
											</td>
										</c:if>
									</c:forEach>
									<td valign="middle" class="cahshusz_bfb_zt1" width="85">
										${buildUseList[i.index+1].a_area}㎡
									</td>
								</c:if>
								<c:if test="${!empty buildUseList[i.index+2].seq}">
									<td align="center" class="cahshusz_qumc_zt1" width="80">
										${buildUseList[i.index+2].content1}
									</td>
									<c:forEach items="${colorMap}" var="colorEn">
										<c:if test="${colorEn.key==buildUseList[i.index+2].seq}">
											<td valign="middle" width="30">
												<div class="gongnengqu_ktf_1"
													style="background-color: ${colorEn.value }"></div>
											</td>
										</c:if>
									</c:forEach>
									<td valign="middle" class="cahshusz_bfb_zt1" width="85">
										${buildUseList[i.index+2].a_area}㎡
									</td>
								</c:if>
								<c:if test="${!empty buildUseList[i.index+3].seq}">
									<td align="center" class="cahshusz_qumc_zt1" width="80">
										${buildUseList[i.index+3].content1}
									</td>
									<c:forEach items="${colorMap}" var="colorEn">
										<c:if test="${colorEn.key==buildUseList[i.index+3].seq}">
											<td valign="middle" width="30">
												<div class="gongnengqu_ktf_1"
													style="background-color: ${colorEn.value }"></div>
											</td>
										</c:if>
									</c:forEach>
									<td valign="middle" class="cahshusz_bfb_zt1" width="85">
										${buildUseList[i.index+3].a_area}㎡
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>

				<div id="admin_nr_jbxx">
					

					<c:if test="${!(empty buildUseList) }">
						<table width="100%" border="0" align="left">
							<tr class=" biaog_kan2 biaog_zt2" valign="bottom">
								<td width="10%" align="center">
									医院用途比例
								</td>
								<td width="50%" align="center" valign="bottom">

									<table border="0" cellspacing="0" width="500" align="center"
										style="height: 14px">
										<tr>
											<c:forEach items="${buildUseList}" var="obj">
												<c:forEach items="${colorMap}" var="colorEn">
													<c:if test="${colorEn.key==obj.seq&&obj.buildUsePer>0}">
														<td bgcolor="${colorEn.value }"
															style="width:${(obj.buildUsePer)/100*500}px;"
															title="${obj.content1 }&#13面积: ${obj.a_area}㎡   比例:${obj.buildUsePer }%"></td>
													</c:if>
												</c:forEach>
											</c:forEach>
										</tr>
									</table>

								</td>
								<td align="left">
									${buildAreaTotal}㎡
								</td>
							</tr>
						</table>
					</c:if>
				<div style="overflow-x: auto; overflow-y: auto; 
				height: 600px; width: 100%;  float:left;">
					<table width="100%" border="0" cellspacing="1" class="biaoge_bg1"
						align="left">
						<thead>
							<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
								<th width="20%" scope="col">
									楼宇名称
								</th>
								<th width="50%" scope="col">
									面积比
								</th>
								<th width="15%" scope="col">
									总面积㎡
								</th>
								<th scope="15%">
									层数
								</th>
							</tr>
						</thead>
						<tbody id="stu-datas-tb">
							<c:forEach items="${buildArea }" var="build" varStatus="idx">
								<tr class=" biaog_kan2 biaog_zt2">
									<td align="center">
										${build.building_name }
									</td>
									<td align="center" valign="bottom">
										<c:if test="${!(empty buildUseArea) }">
											<table border="0" cellspacing="0" width="95%" align="center"
												style="height: 14px">
												<tr>
													<c:forEach items="${buildUseArea}" var="bua">
														<c:forEach items="${colorMap}" var="colorEn">
															<c:if
																test="${colorEn.key==bua.area_id&&build.building_id==bua.building_id }">
																<c:if test="${bua.useAreaPer>0}">
					          				   		${build.content1 }
					          				   			<td bgcolor="${colorEn.value }"
																		style="width:${770*bua.useAreaPer*0.95/100}px;"
																		title="${bua.content1 }&#13面积: ${bua.sa}㎡   比例:${bua.useAreaPer }%"></td>
																</c:if>

															</c:if>
														</c:forEach>
													</c:forEach>
												</tr>
											</table>
										</c:if>
									</td>
									<td align="center" data-aries="#aries-1" id="total${idx.index}" class="aries-anchor-line">
										${build.total }
									</td>
									<td align="center">
										楼下${build.storey_num_down }层，楼上${build.storey_num_up }层
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					<br />
				</div>
				<!--</div>-->
			</center>
		</div>
	</body>
</html>