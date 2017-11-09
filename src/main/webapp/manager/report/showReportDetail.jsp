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
		function submitForm(formName, urlName,currentPage) {
			var myformObj = document.getElementById(formName);
			myformObj.action = urlName;
			if(currentPage != ""){
				$("#currentPage").val(currentPage);
			}
			myformObj.submit();
		}
	function formCheck(currentPage) {
		submitForm("myform", "reportDetail_showReportDetail.action",currentPage);

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
							上报日志
						</td>
					</tr>
				</table>
			</div>
			
		<form id="myform" name="myform" action="" method="post">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
				<div>
					<table width="99%" border="0" class="gai_left_duiqi">
						<tr>
							<td width="11%" class="shebeigl_inp_zt">
								查询条件：
							</td>
							<td width="18%">
								<select name="strdate" id="strdate" class="louyu_xiala_kt">
									<option value="">
										上报时间
									</option>
									<option value='1' ${echoTime=='1'?'selected':'' }>
										近一天
									</option>
									<option value='2' ${echoTime=='2'?'selected':'' }>
										近一周
									</option>
									<option value='3' ${echoTime=='3'?'selected':'' }>
										近一月
									</option>
									<option value='4' ${echoTime=='4'?'selected':'' }>
										近一季
									</option>
									<option value='5' ${echoTime=='5'?'selected':'' }>
										近半年
									</option>
								</select>
							</td>
							<td width="18%">
								<select name="typeid" id="typeid" class="louyu_xiala_kt">
									<option value="" selected="selected">
										上报内容
									</option>
									<c:forEach items="${ReportType}" var="t">
										<option value="${t.typeId}" ${t.typeId==echoContent?'selected':'' }>
											${t.name}
										</option>
									</c:forEach>
								</select>
							</td>
							<td width="18%">
								<select name="status" id="status" class="louyu_xiala_kt">
									<option value="" selected="selected">
										是否上报成功
									</option>
									<option value='1' ${echoStatus=='1'?'selected':'' }>
										上报成功
									</option>
									<option value='2' ${echoStatus=='2'?'selected':'' }>
										上报失败
									</option>
								</select>
							</td>
							<td width="11%" align="right">
								<img src="manager/images/imgs/shebeigl_sousuo_an.png"
									onclick="formCheck('');" />
							</td>
							<td width="24%"></td>
						</tr>
					</table>
				</div>
				<table width="99%" border="0" cellspacing="1" class="biaoge_bg1 gai_left_duiqi">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1" align="center">
							<td scope="col" width="70" align="center">
								上报时间
							</td>
							<td scope="col" width="130">
								上报内容
							</td>
							<td scope="col">
								上报结果
							</td>
							
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:if test="${!empty reportDetailList}">
							<c:forEach items="${reportDetailList}" var="list">
								<tr class=" biaog_kan2 biaog_zt2">
									<td  align="center">
										<fmt:formatDate value="${list.reporttime}"
											pattern="yyyy-MM-dd" />
									</td>
									<td>${list.dbReportType.name}
									</td>
									<td>
										${list.statusdescr}
									</td>
									
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${!empty reportDetailList}">
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
