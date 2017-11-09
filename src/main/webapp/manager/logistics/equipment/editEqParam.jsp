<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>

		<title>My JSP 'addEquipment.jsp' starting page</title>

		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
        
		 <script type="text/javascript">
		   function postDbEqParam(){
		     document.myform.submit();
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
							设备管理——设备列表——设备——参数修改
						</td>
					</tr>
				</table>
			</div>
			<c:if test="${!(empty equipParamList)}">
				<form action="dbEquipParam_updateDbEqParam.action" name="myform" id="myform"
					method="post">
				<input type="hidden" name="doRedirect" value="update" />	
				<input type="hidden" name="equipId" value="${equipId}" />
				<input type="hidden" name="vLength" value="${vLength}" />
				<table width="920" border="0" cellspacing="1" style="float:left">
					<c:forEach var="p" items="${equipParamList}" varStatus="i" step="2">
						<tbody id="mytboy">
							<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
								<td class="admin_bgclor_e3f">
								    <c:if test="${equipParamList[i.index].param_name !=null }">
									${equipParamList[i.index].param_name }<c:if test="${equipParamList[i.index].param_unit != null}">(${equipParamList[i.index].param_unit })</c:if>
									<input type="hidden" id="k${i.index}" name="k${i.index}" value="${equipParamList[i.index].seq }" />
									</c:if>
								</td>
								<td class="admin_bgclor_f1f">
									<%-- <input type="text" id="v${i.index }" name="v${i.index }" value="${equipParamList[i.index].param_value}" /> --%>
									<c:if test="${empty equipParamList[i.index].param_value}">
										<c:if test="${equipParamList[i.index].input_type==1}">
											<input type="text" name="v${i.index}" value="${equipParamList[i.index].default_value }" class="inputnone"/>
									    </c:if>    
									    <c:if test="${equipParamList[i.index].input_type==2}">
										      <select id="v${i.index}" name="v${i.index}" >
										       <option value="-1">--请选择--</option>
										          <c:forEach items="${baseCommList}"   varStatus="b">
										              <c:if test="${equipParamList[i.index].comm_code == baseCommList[b.index].typeseq}">
										                <c:if test="${baseCommList[b.index].commseq!=null}">
												           <option value="${baseCommList[b.index].commseq}" >${baseCommList[b.index].content1}</option>
												        </c:if>
												      </c:if>
												  </c:forEach>
										     </select>
									   </c:if>    
								   </c:if>
								   
								   <c:if test="${!empty equipParamList[i.index].param_value}">
										<c:if test="${equipParamList[i.index].input_type==1}">
											<input type="text" name="v${i.index}" value="${equipParamList[i.index].param_value }" class="inputnone"/>
										</c:if>  
											   <c:if test="${equipParamList[i.index].input_type == 2}">
													       <select id="v${i.index}" name="v${i.index}">
													          <option value="-1">--请选择--</option>
													          <c:forEach items="${baseCommList}"   varStatus="b">
													             <c:if test="${equipParamList[i.index].dic_id == baseCommList[b.index].dseq}">
													               <c:if test="${equipParamList[i.index].param_value!=null}">
															         <option value="${baseCommList[b.index].commseq}" 
															         <c:if test="${equipParamList[i.index].param_value==null?(-1==baseCommList[b.index].commseq):(equipParamList[i.index].param_value==baseCommList[b.index].commseq)}">selected="selected"</c:if>>${baseCommList[b.index].content1}</option>
															       </c:if>
															     </c:if>
															  </c:forEach>
															</select>
											   </c:if>    
						            </c:if>
								   
								</td>
								<td class="admin_bgclor_e3f">
									<c:if test="${equipParamList[i.index+1].param_name !=null }">
									${equipParamList[i.index+1].param_name }<c:if test="${equipParamList[i.index+1].param_unit != null}">(${equipParamList[i.index+1].param_unit })</c:if>
									<input type="hidden" id="k${i.index+1}" name="k${i.index+1}" value="${equipParamList[i.index+1].seq }" />
									</c:if>
								</td>
								<td class="admin_bgclor_f1f">
								<%-- <c:if test="${equipParamList[i.index+1].param_name != null}">
									   <input type="text" id="v${i.index+1 }" name="v${i.index+1 }" value="${equipParamList[i.index+1].param_value }"  />
								</c:if> --%>
									<c:if test="${empty equipParamList[i.index+1].param_value}">
									    <c:if test="${equipParamList[i.index+1].input_type==1}">
										  <input type="text" name="v${i.index+1}" value="${equipParamList[i.index+1].default_value }" class="inputnone"  readOnly="readOnly"/>
									    </c:if>
									   <c:if test="${equipParamList[i.index+1].input_type==2}">
										        <select id="v${i.index+1}" name="v${i.index+1}">
										        <option value="-1">--请选择--</option>
										          <c:forEach items="${baseCommList}"   varStatus="b">
										                <c:if test="${equipParamList[i.index+1].comm_code == baseCommList[b.index].typeseq}">
										                     <c:if test="${baseCommList[b.index].commseq!=null}">
												              <option value="${baseCommList[b.index].commseq}">${baseCommList[b.index].content1}</option>
												             </c:if>
												        </c:if>
												  </c:forEach>
												</select>
									   </c:if> 
									</c:if>
									<c:if test="${!empty equipParamList[i.index+1].param_value}">
									<c:if test="${equipParamList[i.index+1].input_type==1}">
										<input type="text" name="v${i.index+1}" value="${equipParamList[i.index+1].param_value }" class="inputnone"  readOnly="readOnly"/>
									</c:if>
									<c:if test="${equipParamList[i.index+1].input_type==2}">
										     <select id="v${i.index+1}" name="v${i.index+1}">
										        <option value="-1">--请选择--</option>
										          <c:forEach items="${baseCommList}"   varStatus="b">
										             <c:if test="${equipParamList[i.index+1].dic_id == baseCommList[b.index].dseq}">
												         <c:if test="${baseCommList[b.index].commseq!=null}">
													         <option value="${baseCommList[b.index].commseq}"
														              <c:if test="${equipParamList[i.index+1].param_value==null?(-1==baseCommList[b.index].commseq):(equipParamList[i.index+1].param_value==baseCommList[b.index].commseq)}">selected="selected"</c:if>
														              >${baseCommList[b.index].content1}</option>
												         </c:if>
												      </c:if>
												  </c:forEach>
											</select>
									   </c:if> 
									</c:if>
											
								</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
	
				<table width="920" border="0"  style="float:left">
					<tr>
						<td></td>
						<td width="65">
							<a href="javascript:postDbEqParam();" class="btn blue">保存</a>
						</td>
						<td width="65" id="son">
							<a href="equipment_showDetail.action?equipId=${equipId}" class="btn blue">取 消</a>
						</td>
					</tr>
				</table>
				</form>
			</c:if>
		</div>
		${msg}
	</body>
</html>
