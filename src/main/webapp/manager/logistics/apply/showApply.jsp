<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.net.URLDecoder"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/swsoft-struts.tld" prefix="swtag"%>
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
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script language="javascript" src="manager/js/ceng.js"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>
		
		<script type="text/javascript">
		
	function checkApply(id) {
		document.getElementById("seq").value = id;
		submitForm("myform","ApplyAction!findById.action");
	}
	function deleteApply(id){
	if (window.confirm("确定要删除吗?")) {
	    document.getElementById("seq").value = id;
		submitForm("myform","ApplyAction!deleteMaterial.action");
		}
	}
	
	function wuzilingyong() {
	    document.getElementById("applyAdd").value = "1";
		submitForm("myform", "ApplyAction!toApplyJSP.action");
	}
	function submitForm(formName, urlName) {
		var myform = document.getElementById(formName);
		myform.action = urlName;
		myform.submit();
	}
</script>
	</head>
	<body>
	<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table class="titleBg">
					<tr style="height: 20px">
						<td width="30" align="center">
							<img src="manager/images/common/28-01.png" />
						</td>
						<td>
							资产物资管理——物资领用记录
						</td>
					</tr>
				</table>
			</div>
			  
		<form id="myform" name="myform" action="" method="post">
			<input id="seq" name="seq" type="hidden" value="" />
			
			<input id="applyAdd" name="applyAdd" type="hidden" value="" />
			<input id="hospid" name="hospid" type="hidden" value="${hospid}"/>
			
				<table width="100%" class="gai_left_duiqi">
					<tr>
						<td width="90%"></td>
						<td>
						<c:if test="${!empty menuMap && !empty menuMap['2005002001']}">
							<a href="javascript:void(0);" class="btn blue"
								onclick=
	wuzilingyong();
>领用</a>
</c:if>
						</td>
						
					</tr>
				</table>
				<span class="admin_clor_f00">${message}</span>
				<table width="100%" border="0" cellspacing="1"
					class="biaoju_tong_1 admin_bgclor_c6c gai_left_duiqi" align="center">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<th>
								领用部门
							</th>
							<th>
								领用人
							</th>
							<th>
								物资名称
							</th>
							<th>
								领用数量
							</th>
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:if test="${!empty applyList}">
							<c:forEach items="${applyList}" var="apply" varStatus="s">
								<tr class=" biaog_kan2 biaog_zt2" align="center">
									<td>
										${apply.applydept}
										<img id="u${s.count+1}" src="manager/images/imgs/sanjiao_xia.gif"
											align="middle"
											onclick="Yjbopen('t${s.count+2}','u${s.count+1}');" />
									</td>
									<td>
										${apply.applier}
									</td>
									<td>
										${apply.dbMaterial.name}
										<input id="materialSeq" name="materialSeq" value="${apply.dbMaterial.seq}" type="hidden" />
									</td>
									<td>
										${apply.acount}
									</td>
									<td align="center" class="biao_lianj_1">
									<c:if test="${!empty menuMap && !empty menuMap['2005002002']}">
									<a href="javascript:void(0);"
										onclick="checkApply('${apply.seq}');">修改</a>
									</c:if>
									<c:if test="${!empty menuMap && !empty menuMap['2005002003']}">
									<a href="javascript:void(0);"
										onclick="deleteApply('${apply.seq}');">删除</a>
									</c:if>
								</td>
								</tr>
								<tr id="t${s.count+2}" style="display: none; height: 30px;">
									<td colspan="7">
										${apply.reason}
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${!empty applyList}">
					<table width="99%" border="0" class="gai_left_duiqi">
						<tr height="10">
							<td width="35%"></td>
							<td width="65%"></td>
						</tr>
						<tr height="30">
							<td class="biaoge_fanye" align="center" colspan="2">
								<swtag:paginator
									url="${pageContext.request.contextPath}/ApplyAction!showApply.action"
									showTotal="true" showAllPages="true" strUnit="条记录" />
							</td>
						</tr>
					</table>
				</c:if>
			
		</form>
		</div>
	</body>
</html>
