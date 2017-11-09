<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
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
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
		<script type="text/javascript">
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
							频率设置——频率列表
						</td>
					</tr>
				</table>
			</div>
			
			
		<form id="myform" name="myform" action="" method="post">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
				<table width="99%" border="0" cellspacing="1" class="biaoge_bg1 gai_left_duiqi">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1" align="center">
							<td width="198" scope="col">
								序号
							</td>
							<td width="398" scope="col">
								上报频率
							</td>
							<td width="174" scope="col">
								上报类型
							</td>
							<td width="174" scope="col">
								最后上报时间
							</td>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:if test="${!empty reportRateList}">
							<c:forEach items="${reportRateList}" var="list" varStatus="s">
								<tr class=" biaog_kan2 biaog_zt2">
									<td align="center">
										${s.count}
									</td>
									<td align="center">
									<c:if test="${list.rate==1}">每天一次</c:if>
									<c:if test="${list.rate==2}">每周一次</c:if>
									<c:if test="${list.rate==3}">每月一次</c:if>
									<c:if test="${list.rate==4}">每季度一次</c:if>
									<c:if test="${list.rate==5}">每年一次</c:if>
									<c:if test="${list.rate==6}">仅此一次</c:if>
									</td>
									<td align="center">
									${list.dbReportType.name}
									</td>
									<td align="center">
									<fmt:formatDate value="${list.reportdate}"
											pattern="yyyy-MM-dd" />
									
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				
				<!-- 分页从这里开始 -->
				<c:if test="${!empty reportRateList}" >
					<table width="780" border="0">
						<tr  style="height: 50px;">
						<td></td>
							<td>
								<sktag:paginator showTotal="true" showAllPages="true"  strUnit="条记录" />
							</td>
						</tr>
					</table>
				</c:if>
			
		</form>
		</div>
	</body>
</html>
