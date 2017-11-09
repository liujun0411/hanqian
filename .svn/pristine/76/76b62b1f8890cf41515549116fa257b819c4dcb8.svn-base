<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

		<title>电表</title>
		<meta http-equiv="Refresh" content="30" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet"
			type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript"
			src="manager/javascript/previewPic/picShow.js">
</script>
		<script type="text/javascript" src="manager/config/config.js">
</script>

<%--		<script type="text/javascript" src="manager/js/refresh.js">--%>
<%--</script>已删除--%>
		<script type="text/javascript">
window
		.setInterval(
				"refresh('${dbEqList.eqType}','${equip_id}','${dbEqList.dbBuilding.sequence}','${equip_code}')",
				300000);</script>

	</head>

	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<div id="admin_nr_rightg">
			<!--实时监控开始 -->
			<div style="height: 20px; width: 100%; border: 0px solid red;">
				<table style="width: 80%;">
					<tr>
						<td align="center">
							<span style="font-size: 14px; color: #F90; font-weight: bold;">【<% boolean name_null=false;%>
							<c:forEach items="${equipServices}" var="equipService" varStatus="ind">(
								<c:forEach items="${listEnergyBuilding}" var="tempBuild">
									<c:if test="${equipService.buildId==tempBuild.buildingId}">
										<c:if test="${!empty tempBuild.buildingName}">${tempBuild.buildingName}<%name_null=true; %></c:if>
					      			</c:if>
								</c:forEach>
								<c:if test="${!empty equipService.storey}">${equipService.storey}<%name_null=true; %></c:if>
					      		<c:if test="${!empty equipService.storey}">层</c:if>
					      			${equipService.dbBaseComm.content1}<!-- 区域 -->
								<c:if test="${!empty equipService.comments}">[</c:if>
								<c:if test="${!empty equipService.comments}">
									${equipService.comments}<!-- 备注 --><%name_null=true; %>
								</c:if>
								<c:if test="${!empty equipService.comments}">]</c:if>
								)
							</c:forEach>
							<% if(name_null==false){%>暂无服务区域<%}%>】</span>
						</td>
					</tr>
				</table>
			</div>
			<div class="shishijiankong_kuan_guolu" style="margin-right: 30px;">
				<input name="" type="button" value="设备安装位置图" class="inp_shi_zt"
					onclick="getPicNameByEquip('${powerList[0].equip_id}')" />
			</div>
			<table width="80%" border="0" cellspacing="1" bgcolor="#83c5d7"
				class="diant_ziti_130">
				<input type="hidden" id="eqTypeId" name="eqTypeId"
					value="${eqTypeId }" />
				<input type="hidden" id="equipId" name="equipId" value="" />
				<input type="hidden" id="buildingId" name="buildingId" value="" />
				<c:forEach items="${powerList}" var="p" varStatus="st" begin="0"
					step="2">
					<c:choose>
						<c:when test="${st.index==0}">
							<tr height="25" bgcolor="#ffffff">
								<td bgcolor="#cfe9ef" class="diant_ziti_140" align="center">
									${powerList[st.index].point_name}
								</td>
								<td width="15%" align="center">
								<c:choose>
								   <c:when test="${!empty powerList[st.index].da}">
								      <span class="diant_ziti_sh130"> <fmt:formatNumber
											type="number" maxFractionDigits="2"
											value="${powerList[st.index].da}" />
										${powerList[st.index].unit_type}</span>
								   </c:when>
								   <c:otherwise></c:otherwise>
								 </c:choose>
								</td>
								<td bgcolor="#cfe9ef" class="diant_ziti_140" align="center">
									${powerList[st.index+1].point_name}
								</td>
								<td width="15%" align="center">
								<c:choose>
								  <c:when test="${!empty powerList[st.index+1].da}">
									<span class="diant_ziti_sh130"> <fmt:formatNumber
											type="number" maxFractionDigits="2"
											value="${powerList[st.index+1].da}" />
										${powerList[st.index+1].unit_type} </span>
								   </c:when>
								   <c:otherwise></c:otherwise>
								</c:choose>
								</td>
								<td rowspan="3" width="15%" class="diant_ziti_140"
									bgcolor="#cfe9ef" align="center">
							<c:choose>
								  <c:when test="${!empty powerList[st.index+1].equip_code}">
									${powerList[st.index+1].equip_code}
									<br />
									${powerList[st.index+1].place}
							      </c:when>
								  <c:otherwise></c:otherwise>
						   </c:choose>		
								</td>
								<td rowspan="3" width="15%" align="center">
									<img src="manager/images/control/dianb_nyjl.png" />
								</td>
							</tr>
						</c:when>
						<c:when test="${st.index>0&&st.index<6}">
							<tr height="25" bgcolor="#ffffff">
								<td bgcolor="#cfe9ef" class="diant_ziti_140" align="center">
									${powerList[st.index].point_name}
								</td>
								<td width="15%" align="center">
								<c:choose>
								   <c:when test="${!empty powerList[st.index].da}">
										<span class="diant_ziti_sh130"> <fmt:formatNumber
												type="number" maxFractionDigits="2"
												value="${powerList[st.index].da}" />
											${powerList[st.index].unit_type} </span>
								   </c:when>
								   <c:otherwise></c:otherwise>
						       </c:choose>		
								</td>
								<td bgcolor="#cfe9ef" class="diant_ziti_140" align="center">
									${powerList[st.index+1].point_name}
								</td>
								<td width="15%" align="center">
								<c:choose>
								   <c:when test="${!empty powerList[st.index+1].da}">
										<span class="diant_ziti_sh130"> <fmt:formatNumber
												type="number" maxFractionDigits="2"
												value="${powerList[st.index+1].da}" />
											${powerList[st.index+1].unit_type} </span>
								   </c:when>
								   <c:otherwise></c:otherwise>
						        </c:choose>	
								</td>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:forEach items="${powerList}" var="p" varStatus="st" begin="6"
					step="3">
					<tr height="25" bgcolor="#ffffff">
						<td bgcolor="#cfe9ef" class="diant_ziti_140" align="center">
							${powerList[st.index].point_name}
						</td>
						<td width="15%" align="center">
						<c:choose>
								   <c:when test="${!empty powerList[st.index].da}">
										<span class="diant_ziti_sh130"> 
										        <c:set var="point" value="${powerList[st.index].control_point}"></c:set>
											   <c:if test="${fn:endsWith(point, '.19')}">
											     <!-- 点表中开关状态显示问题：由于SDCD取的数据是 ：null[页面显示：--]、0[页面显示：开]、1[页面显示：关]，-->
											     <!-- 由于和数据库有冲突(具体冲突：v_equip_control_point_record中da字段把null和'0'都处理为'0'，-->
											     <!-- 与需求不符(需求：null[页面显示：--]、0[页面显示：开]、1[页面显示：关]))特此在0和1的基础上面加1 -->
											     <!-- 变成nul或0[页面显示：--]1[页面显示：开]2[页面显示：关] -->
											     <!-- 胸科医院项目显示nul或0[--]1[开]2[关] -->
											     <!-- 以后按照胸科+1的显示为标准 -->
														<c:if test="${powerList[st.index].da==0}">--</c:if>
														<c:if test="${powerList[st.index].da==1}">开</c:if>
														<c:if test="${powerList[st.index].da==2}">关</c:if>
														<c:if test="${powerList[st.index].da==3}">不确定</c:if>
														<c:if test="${powerList[st.index].da >=4 || powerList[st.index].da <0}">--</c:if>
											   </c:if>
											    <c:if test="${!fn:endsWith(point, '.19')}">
												   <fmt:formatNumber type="number" maxFractionDigits="2"
														value="${powerList[st.index].da}" />
										                ${powerList[st.index].unit_type}
										        </c:if>
								         </span>
								  </c:when>
							     <c:otherwise></c:otherwise>
						 </c:choose>	
						</td>
						<td bgcolor="#cfe9ef" class="diant_ziti_140" align="center">
							<c:choose>
								   <c:when test="${!empty powerList[st.index+1].point_name}">
							            ${powerList[st.index+1].point_name}
							       </c:when>
							  <c:otherwise></c:otherwise>
						    </c:choose>
						</td>
						<td width="15%" align="center">
							<c:choose>
								   <c:when test="${!empty powerList[st.index+1].da}">
											<span class="diant_ziti_sh130">
											   <c:set var="point" value="${powerList[st.index+1].control_point}"></c:set>
											   <c:if test="${fn:endsWith(point, '.19')}">
														<c:if test="${powerList[st.index+1].da==0}">--</c:if>
														<c:if test="${powerList[st.index+1].da==1}">开</c:if>
														<c:if test="${powerList[st.index+1].da==2}">关</c:if>
														<c:if test="${powerList[st.index+1].da==3}">不确定</c:if>
														<c:if test="${powerList[st.index+1].da >=4 || powerList[st.index+1].da <0}">--</c:if>
											   </c:if>
											    <c:if test="${!fn:endsWith(point, '.19')}">
												   <fmt:formatNumber type="number" maxFractionDigits="2"
														value="${powerList[st.index+1].da}" />
										                ${powerList[st.index+1].unit_type}
										        </c:if>
								  </c:when> 
							 <c:otherwise></c:otherwise>
						    </c:choose>		
						</td>
						<td bgcolor="#cfe9ef" class="diant_ziti_140" align="center">
						     <c:choose>
								  <c:when test="${!empty powerList[st.index+2].point_name}">
							            ${powerList[st.index+2].point_name}
							      </c:when>
							  <c:otherwise></c:otherwise>
						    </c:choose>	 
						</td>
						<td width="15%" align="center">
                                 <c:choose>
								   <c:when test="${!empty powerList[st.index+2].da}">
											<span class="diant_ziti_sh130">
											   <c:set var="point" value="${powerList[st.index+2].control_point}"></c:set>
											   <c:if test="${fn:endsWith(point, '.19')}">
														<c:if test="${powerList[st.index+2].da==0}">--</c:if>
														<c:if test="${powerList[st.index+2].da==1}">开</c:if>
														<c:if test="${powerList[st.index+2].da==2}">关</c:if>
														<c:if test="${powerList[st.index+2].da==3}">不确定</c:if>
														<c:if test="${powerList[st.index+2].da >=4 || powerList[st.index+2].da <0}">--</c:if>
											   </c:if>
											    <c:if test="${!fn:endsWith(point, '.19')}">
												   <fmt:formatNumber type="number" maxFractionDigits="2"
														value="${powerList[st.index+2].da}" />
										                ${powerList[st.index+2].unit_type}
										        </c:if>
								  </c:when> 
							 <c:otherwise></c:otherwise>
						    </c:choose>	
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>
