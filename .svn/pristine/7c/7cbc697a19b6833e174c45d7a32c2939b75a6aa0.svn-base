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

		<script language="JavaScript">
			
			//修改界面 
			changeParam = function(){
				$("#message").html("");
				$('#savebtn').show();
				var itxt = $("input:text");
				var itxt2 = $("#myform select");
				for(var i=0;i<itxt.length;i+=1){
					itxt[i].readOnly="";
					itxt[i].className="";
					if(i==0){
						itxt[i].focus();
						itxt[i].select();
					}
				}
				for(var i=0;i<itxt2.length;i+=1){
					itxt2[i].disabled="";
					if(i==0){
						itxt2[i].focus();
					}
				}
			}


			//保存
			saveParam = function(){
// 				$('#savebtn').hide();
				$('#savebtn').hide();
				var itxt = $("input:text");
				for(var i=0;i<itxt.length;i+=1){
					itxt[i].readOnly="readOnly";
					itxt[i].className="inputnone";
				}
				document.myform.submit();
			}

			//取消
			resetParam = function(){
				document.myform.reset();
				$('#savebtn').hide();
				var itxt = $("input:text");
				var itxt2 = $("#myform select");
				for(var i=0;i<itxt.length;i+=1){
					itxt[i].readOnly="readOnly";
					itxt[i].className="inputnone";
				}
				for(var i=0;i<itxt2.length;i+=1){
					itxt2[i].disabled="disabled";
				}
			}
		</script>
	</head>
	<body>
	
	<c:if test="${!(empty equipParamList)}">
		<form action="dbEquipParam_updateDbEqParam.action" 
			name="myform" id="myform" method="post">
			<input type="hidden" id="typeId" name="typeId" value="${typeId }" />
			<input type="hidden" name="equipId" value="${equipId }" />
			<input type="hidden" name="vLength" value="${vLength }" />
			<input type="hidden" id="flag" name="flag" value="${flag}"/>
				<table width="920" border="0" cellspacing="1" 
					class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						<td width="82" colspan="4" align="right">
							<a onclick="changeParam();" class="btn blue">修改参数</a>
						</td>
					</tr>
				</table>
				<span id="message" class="admin_clor_f00">${message}</span>
		 <div style="overflow:auto;width:920px; float:left; height:600px;">	
			<table width="100%" border="0" cellspacing="1" align="center">
				<tr class="biaog_kan1 admin_bgclor_2cb">
					<th colspan="4" class="admin_dh_zt2" align="left">
						&nbsp;主要技术参数
					</th>
				</tr>
				<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
					<th class="admin_bgclor_e3f admin_zt_14" width="160">
						参数名称（参数单位）
					</th>
					<th class="admin_bgclor_e3f admin_zt_14">
						参数值
					</th>
					<th class="admin_bgclor_e3f admin_zt_14" width="160">
						参数名称（参数单位）
					</th>
					<th class="admin_bgclor_e3f admin_zt_14">
						参数值
					</th>
				</tr>
				
				<c:forEach items="${equipParamList}"  step="2" varStatus="i">
					<tr class="biaog_kan2 biaog_zt2 admin_bgclor_f1f">
						<td>${equipParamList[i.index].param_name }
						     <c:if test="${!(empty equipParamList[i.index].param_unit )}">(${equipParamList[i.index].param_unit })</c:if></td>
						<td>
							<input type="hidden" name="k${i.index}" value="${equipParamList[i.index].seq }"/>
							<c:if test="${empty equipParamList[i.index].param_value}">
								<c:if test="${equipParamList[i.index].input_type==1}">
									<input type="text" name="v${i.index}" value="${equipParamList[i.index].default_value }" class="inputnone" readOnly="readOnly"/>
							    </c:if>    
							    <c:if test="${equipParamList[i.index].input_type==2}">
								      <select id="v${i.index}" name="v${i.index}" disabled="disabled" >
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
								<input type="text" name="v${i.index}" value="${equipParamList[i.index].param_value }" class="inputnone" readOnly="readOnly"/>
							</c:if>  
									   <c:if test="${equipParamList[i.index].input_type == 2}">
											       <select id="v${i.index}" name="v${i.index}" disabled="disabled" >
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
						<c:if test="${empty equipParamList[i.index+1]}">
							<td></td>
							<td></td>
						</c:if>
						<c:if test="${!(empty equipParamList[i.index+1])}">
						<td>${equipParamList[i.index+1].param_name }<c:if test="${!(empty equipParamList[i.index+1].param_unit )}">(${equipParamList[i.index+1].param_unit })</c:if></td>
						<td>
							<input type="hidden" name="k${i.index+1}" value="${equipParamList[i.index+1].seq }" />
							<c:if test="${empty equipParamList[i.index+1].param_value}">
							    <c:if test="${equipParamList[i.index+1].input_type==1}">
								  <input type="text" name="v${i.index+1}" value="${equipParamList[i.index+1].default_value }" class="inputnone"  readOnly="readOnly"/>
							    </c:if>
							   <c:if test="${equipParamList[i.index+1].input_type==2}">
								        <select id="v${i.index+1}" name="v${i.index+1}" disabled="disabled">
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
								     <select id="v${i.index+1}" name="v${i.index+1}" disabled="disabled">
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
					  </c:if>
					</tr>					
				</c:forEach>				
			</table>
			<table width="98%" id="savebtn" style="display:none;" align="center">
				<tr>
					<td></td>
					<td width="65"><a onclick="saveParam();" class="btn blue">保存</a></td>
					<td width="65"><a onclick="resetParam();" class="btn blue">取消</a></td>
				</tr>
			</table>
		</div>
		
		
		</form>
	</c:if>
	${msg }
	</body>
</html>
