<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>		
		<script type="text/javascript">
		//分页
		function formCheck(currentPage) {
			if (currentPage != "") {

				$("#dateMenuPageCurrentPage").val(currentPage);
			}
			document.forms[0].submit();
		}
					
		</script>
		<style type="text/css">
			.remarks_top {
				width: 200px;
				height: 4px;
				background-image: url(manager/images/control/lh/zhaoming_fd1.png);
				background-position: center top;
			}
			.remarks_context {
				width: 200px;
				height: arto;
				background-image: url(manager/images/control/lh/zhaoming_fd1.png);
				background-position: center bottom;
			}
		</style>
	</head>

	<body style=" background-color: white;">
          <form id="myform" name="myform" action="menu!findOutDateMens.actions">
			<input type="hidden" name="dateMenuPageCurrentPage" value="${dateMenuPageCurrentPage}"
					id="dateMenuPageCurrentPage" />
		  </form>
			<c:if test="${!empty outDateMenuList}">
			<table width="100%" border="0" style="margin-top:0px;" cellspacing="1" class="shishijiankong_guolu_zt13"
				align="center" bgcolor="#CCCCCC" >
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1" style=" font-family: '黑体'; font-size: 13px; color: #00487A; background-color: #DCDDDD;">
						<th width="10%" scope="col">
							序号
						</th>
						<th scope="col" width="20%">
							功能名称
						</th>
						<th scope="col" width="25%">
							合同到期日期
						</th>
						<th width="150" width="25%">
							功能状态
						</th>
						<th width="60" width="20%">
							缓冲日期
						</th>
																	
					</tr>
				</thead>
				<tbody id="">					
						<c:forEach items="${outDateMenuList}" var="obj" varStatus="indx">
							<tr class=" biaog_kan2 biaog_zt2" style="background-color: rgb(255, 255, 255);">
								<td align="center">
									${indx.count}
								</td>
								<td align="center" class="biao_lianj_1">
									${obj.name }
								</td>	
								<td align="center">
<%--									<f:formatDate value="${obj.starttime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
									${obj.duedate }
								</td>		
								<td align="center" class="biao_lianj_1">
									<c:if test="${obj.outday >=0 }"> 即将到期 </c:if>
									<c:if test="${obj.outday <0 }" > 已过期 </c:if>
								</td>
												
								<td align="center" class="biao_lianj_1">
								    ${obj.vailddate }
								</td>
							</tr>
							
						</c:forEach>					
				</tbody>
			</table>
				</c:if>
			<!-- 分页从这里开始 -->
			<c:if test="${!empty outDateMenuList}" >
				<table width="100%" border="0">
					<tr  style="height: 50px;">
					<td></td>
						<td>
							<sktag:paginator showTotal="true" showAllPages="true"  strUnit="条记录" />
						</td>
					</tr>
				</table>
			</c:if>
		
		
	</body>
</html>