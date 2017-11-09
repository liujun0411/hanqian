<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<title>医院后勤智能化管理平台</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" href="manager/css/neirong.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/skdiv.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/ziti.css" type="text/css"></link>
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript">
</script>
<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>
		
		<script type="text/javascript">

//超链接表单提交
function submitLinkform(action) {
	document.linkform.action = action;
	document.linkform.submit();
}

</script>
	</head>

	<body>
		<!-- 超链接表单提交区 -->
		<form method="post" action="" name="linkform">
			<input type="hidden" name="editFlag" value="${editFlag }" />
			<input type="hidden" name="seqHosp" value="${hospInfo.seqHosp }" />
			<input type="hidden" name="hospName" value="${hospInfo.hospName }" />
		</form>

		<div id="admin_nr_rightg">
			<div id="jibenDiv">
				<div class="canshusz_btbg_1">
					<table class="titleBg">
						<tr style="height: 20px">
							<td width="30" align="center">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td>
								医院信息——医院概况
							</td>
						</tr>
					</table>
				</div>
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi"
					style="width: 100%">
						<tr>
							<td><span class="admin_clor_f00">${message}</span></td>
							<td class="admin_jbxx_bt1" style="height: 40px" align="center" >
								${hospInfo.hospName}
							</td>
							<%--判断是否有权限--%>
							<c:if test="${!empty menuMap && !empty menuMap['2001001001']}">
								<td width="60" align="left">
									<a href="javascript:void(0);"
										onclick="submitLinkform('hospInfoAction!showHospInfoEdit.action');"
										class="btn blue">修 改</a>
								</td>
							</c:if>
						</tr>
				</table>

				<table border="0" cellspacing="1" bgcolor="#666666"
					class="gai_left_duiqi" style="width: 100%">
					<tr style="height: 30px;">
						<td bgcolor="#ddeefc" class="admin_biaoge_zt1" align="center">
							医院简称
						</td>
						<td bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							&nbsp; ${hospInfo.shortName}
						</td>
						<td width="13%" bgcolor="#ddeefc" class="admin_biaoge_zt1"
							align="center">
							医院级别
						</td>
						<td width="32%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							&nbsp; ${hospInfo.dbBaseCommByHosplevel.content1}
						</td>
					</tr>
					<tr style="height: 30px;">
						<td width="13%" bgcolor="#ddeefc" class="admin_biaoge_zt1"
							align="center">
							创建日期
						</td>
						<td width="33%" bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							&nbsp;
							<f:formatDate value="${hospInfo.buildtime}" pattern="yyyy年" />
						</td>
						<td bgcolor="#ddeefc" class="admin_biaoge_zt1" align="center">
							医院地址
						</td>
						<td bgcolor="#FFFFFF" class="admin_biaoge_zt2">
							&nbsp; ${hospInfo.dbBaseCommByHospdist.content1}${hospInfo.address}
						</td>
					</tr>
				</table>
				<div class="admin_nr_right_g02" style="width: 100%">
				</div>

				<div class="gai_yyxinxi" align="left">
					<!-- style="width:100%;overflow:auto;height:450px" -->
					<!-- add start 2013.4.11 by zhangdiannan
						  图片修改后后台已经改变但是html 还是现实修改前图片
						 img src 添加 随机数    src 后加上 "?randomNum=${randomNum}" -->
					<%-- <img src="${picUrl}${hospInfo.fullView }?randomNum=${randomNum}" --%>
					<img src="hospInfoAction!showHospInfoPic.action?randomNum=${randomNum}&urls=${hospInfo.fullView }"
						alt="${hospInfo.shortName}"
						style="margin-right: 10px; margin-bottom: 10px;" width="400"
						height="300" align="left" />
					<span>${hospInfo.hospInfo}</span>
				</div>
			</div>
		</div>
	</body>
</html>