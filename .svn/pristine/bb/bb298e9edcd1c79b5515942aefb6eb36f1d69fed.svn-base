<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js"
			type="text/javascript">
		</script>
		<base href="<%=basePath%>" />
		<title>班组管理-班组列表</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />

		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/ceng.js">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/xialakuang.js">
</script>
		<script type="text/javascript">

//分页
function formCheck(currentPage) {
	if (currentPage != "") {

		$("#currentPage").val(currentPage);
	}
	document.forms[0].submit();
}

//删除提交
function deleteSubmit(seq) {
	if(confirm("您确定删除该班组吗？")){
		$('#groupId').val(seq);
		var frm = $('#myform');
		frm.attr('action', 'serMain!deleteSerMain.action');
		frm.submit();
	}
}

//修改提交
function updateSubmit(seq, groupName) {

	$('#groupId').val(seq);
	$('#groupName').val(groupName);
	var frm = $('#myform');
	frm.attr('action', 'serMain!toUpdateSerMain.action');
	frm.submit();

}
</script>

	</head>

	<body>

		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="100%" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td class="biaoti_zt_canshusz">
							班组管理——班组列表 
						</td>
					</tr>
				</table>
			</div>
			<s:form action="serMain!findSerMain.action" theme="simple"
				method="post" name="myform" id="myform">

				<input type="hidden" name="currentPage" value="${currentPage}"
					id="currentPage" />
				<input type="hidden" name="groupId" value="" id="groupId" />
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi"
					width="95%">

					<tr>
						<td colspan="" align="right" width="8%" class="shebeigl_inp_zt">
							<span>班组名称：</span>
						</td>
						<td colspan="" align="left" width="70%">
							<input type="text" name="groupName" id="groupName"
								value="${groupName}" />
						</td>
						<td colspan="" align="right" width="10%">
							<!--  <a href="javascript:document.forms[0].submit();" class="btn blue"
								align="left">查询</a>-->
						<a href="javascript:document.forms[0].submit();" 
								align="left"><img 	src="manager/images/imgs/shebeigl_sousuo_an.png"
								style="cursor: pointer"/></a>
								
						</td>
						<td colspan="" align="right" width="10%">
							<c:if test="${!empty menuMap && !empty menuMap['5007001002']}">
								<a href="serMain!toAddSerMain.action" class="btn blue"
									align="right">添加</a>
							</c:if>
						</td>
					</tr>
				</table>
			</s:form>

			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="5%" scope="col">
							序号
						</th>
						<th width="15%" scope="col">
							班组名称
						</th>
						<th width="10%" scope="col">
							班组设备数
						</th>
						<th width="10%" scope="col">
							班组人数
						</th>

						<th width="8%" scope="col">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">

					<c:if test="${!empty groupList}">
						<c:forEach items="${groupList}" var="b" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">
									${index.count}
								</td>
								<td align="center">
									${b.name}
								</td>
								<td align="center">
									${b.equipcount}
								</td>
								<td align="center">
									${b.servicecount}
								</td>

								<td align="center" class="biao_lianj_1">
									<c:if test="${!empty menuMap && !empty menuMap['5007001003']}">
										<a href="javascript:updateSubmit(${b.seq},'${b.name}');">修改</a>
									</c:if>
									<c:if test="${!empty menuMap && !empty menuMap['5007001004']}">
										<a href="javascript:deleteSubmit('${b.seq}');">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<c:if test="${!empty groupList}">
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
			</c:if>
			<!--页面结束 -->
	</body>
</html>
