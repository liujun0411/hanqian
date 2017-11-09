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
		</script>
		<base href="<%=basePath%>" />
		<title>权限管理-权限列表</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/ceng.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/xialakuang.js"></script>

		<script type="text/javascript">
		//分页
		function formCheck(currentPage) {
			if (currentPage != "") {
				$("#currentPage").val(currentPage);
			}
			document.forms[0].submit();
		}

    function deleteSubmit(menuId) {  
    	if(window.confirm("您确定删权限吗？")){
		    var url = "menu!deleteMenus.action"; 
		    $("#menuId").val(menuId); 
		    var form = $("#myform");  
		    form.attr("action",url)  
		    form.submit();  
	  	}
    }
     function updateSubmit(menuId) {  
		   var url = "menu!toUpdateMenus.action"; 
		   $("#menuId").val(menuId); 
		   var form = $("#myform");  
		   form.attr("action",url)  
		   form.submit();  
    }
</script>

	</head>

	<body>
			<div class="canshusz_btbg_1">
			<table width="780" border="0">
					<tr style="height:30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							权限管理-权限列表
						</td>
					</tr>
				</table>
			</div>
			<br><br>
			<s:form action="menu!findMenuList.action" theme="simple"
				method="post" name="myform" id="myform">

				<input type="hidden" name="currentPage" value="${currentPage}" id="currentPage" />
				<input type="hidden" name="dbMenus.menuId" value="" id="menuId" />
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						<td class="shebeigl_inp_zt" align="right" width="90">
							权限名称
						</td>
						<td width="80" align="left">
							<input type="text" name="dbMenus.name" id="name"  value="${dbMenus.name}"/>
						</td>
						<td class="shebeigl_inp_zt" align="right" width="90">
							菜单地址
						</td>
						<td width="80" align="right">
							<input type="text"  name="dbMenus.menuUrl" id="menuUrl" value="${dbMenus.menuUrl}" />
						</td>
						<td width="100px" align='right' class='shebeigl_inp_zt'>
							描述
						</td>
						<td align="left" width='80'>
							<input type="text" name="dbMenus.depict" id="depict"  value="${dbMenus.depict}"/>
						</td>
						<td class="shebeigl_inp_zt" align="right" width="90">
							备注
						</td>
						<td width="80" align="right">
							<input type="text" name="dbMenus.remarks" id="remarks" value="${dbMenus.remarks}"/>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="right">
							<img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="javascript:document.forms[0].submit();"></img>
							&nbsp;&nbsp;
							<a href="menu!toAddMenus.action" class="btn blue"
								align="left">添加</a>
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
						<th width="10%" scope="col">
							父级名称
						</th>
						<th width="10%" scope="col">
							菜单名称
						</th>
						<th width="13%" scope="col">
							菜单地址
						</th>
						<th width="13%" scope="col">
							菜单等级
						</th>
						<th width="10%" scope="col">
							备注
						</th>
						<th width="15%" scope="col">
							描述
						</th>
						<th width="8%" scope="col">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">

					<c:if test="${!empty menuList}">
						<c:forEach items="${menuList}" var="b" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">
									${index.count}
								</td>
								<td align="center">
									${b.parentname}
								</td>
								<td align="center">
									${b.name}
								</td>
								<td align="center" class="biao_lianj_1">
									${b.menu_url}
								</td>
								<td align="center">
									${b.menu_level}
								</td>
								<td align="center">
									${b.remarks}
								</td>
								<td align="center">
									${b.depict}									
								</td>

								<td align="center" class="biao_lianj_1">
									<a href="menu!toUpdateMenus.action?dbMenus.menuId=${b.menu_id}">修改</a>
									<a href="javascript:deleteSubmit(${b.menu_id});">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<c:if test="${!empty menuList}">
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
