<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/sksoftmysql-struts.tld" prefix="sktag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript">
		</script>
		<base href="<%=basePath%>" />
		<title>点位统计——点位查询统计</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />
		

		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/ceng.js">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
		<script type="text/javascript">

//分页
function formCheck(currentPage) {
	if (currentPage != "") {
		$("#currentPage").val(currentPage);
	}
	document.forms[0].submit();
}
function ondblclickId(dwbm1){
	var rotate_all_li = $(window.parent.document).contents().find("#rotate");
	var service_li = $(rotate_all_li).find("li").eq(0);
	var sdcd_li = $(rotate_all_li).find("li").eq(1);
	$(sdcd_li).removeClass("ui-tabs-selected");
	$(service_li).addClass("ui-tabs-selected"); 
	location.href='controlRed_selectfindDianweiInfoList.action?currentPage=1&dwbmSDCD='+dwbm1;
	}
</script>
	</head>
	<body>
		<div id="admin_nr_rightg">
			<s:form action="controlRed_sDCDfindDiwanweiInfoList.action" theme="simple"
				method="post" name="myform" id="myform">
				<input type="hidden" name="currentPage" value="${currentPage}" id="currentPage" />
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi"
					width="100%" align="right" style="float: right;">
					<tr>
						<td  width="90" align="center" class="shebeigl_inp_zt">
							<span>点位编码：</span>
						</td>
						<td  width="80">
							<input id="dwbm1" name="dwbm1" class="myfont" value="${dwbm1 }"/>
						</td>
						<td  width="90" align="center" class="shebeigl_inp_zt">
							<span>日期查询：</span>
						</td>
						<td  width="80">
						<input class="Wdate" id="d4311" value="${dateTimeSDCD}" name="dateTimeSDCD" size="12" type="text" 
						 onclick="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd',maxDate:'#F{\'%y-%M-%d\'}'})"/>
						</td>
						<td  width="90" align="center" class="shebeigl_inp_zt">
							<span></span>
						</td>
						<td  width="80">
						</td>
						<td  width="90" class="shebeigl_inp_zt">
						</td>
						<td  width="80">
						  <img onclick="formCheck();"	src="manager/images/imgs/shebeigl_sousuo_an.png"
								style="cursor: pointer"/>
						</td>
					</tr>
				</table>
			</s:form>
			<div style="width:100%;">
			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th nowrap="nowrap" scope="col">
							序号
						</th>
						<th nowrap="nowrap" scope="col">
							点位编码
						</th>
						<th nowrap="nowrap"  scope="col">
							采集值
						</th>
						<th nowrap="nowrap"  scope="col">
							采集日期
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<c:if test="${!empty sDCDfindDiwanweiInfoList}">
						<c:forEach items="${sDCDfindDiwanweiInfoList}" var="indexSelect" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2" style=" cursor: pointer;"  ondblclick="ondblclickId('${indexSelect.project_point }');">
								<td nowrap="nowrap" align="center">
									&nbsp;${index.count}&nbsp;
								</td>
								<td nowrap="nowrap" align="center">
									&nbsp;${indexSelect.project_point }&nbsp;
								</td>
								<td nowrap="nowrap" align="center">
									&nbsp;${indexSelect.real_data }&nbsp;
								</td>
								<td nowrap="nowrap" align="center" >
									&nbsp;<fmt:formatDate value="${indexSelect.date_time }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
					</tr>
				</tbody>
			</table>
			</div>
			<c:if test="${empty sDCDfindDiwanweiInfoList}">
			<span style="color:red;">暂无数据！</span>
			</c:if>
			<c:if test="${!empty sDCDfindDiwanweiInfoList}">
				<div style=" width:100%; ">
					<div style="position: relative; float:left; width:88%;">
						<table width="100%" border="0" class="gai_left_duiqi">
							<tr style="height: 10px;">
								<td width="35%"></td>
								<td width="65%"></td>
							</tr>
							<tr style="height: 30px;">
								<td colspan="5">
									<sktag:paginator showTotal="true" showAllPages="true"
										strUnit="条记录" />
								</td>
							</tr>
						</table>
					</div>
			    </div>
		</c:if>
			<!--页面结束 -->
	</body>
</html>
