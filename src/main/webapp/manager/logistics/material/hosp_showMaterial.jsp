<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/swsoft-struts.tld" prefix="swtag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
	<base href="<%=basePath%>" />
	<meta content="text/html;charset=GBK" http-equiv="Content-Type" />
	<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
	<script src="manager/js/annu.js" type="text/javascript"></script>
	<script src="manager/js/reqi.js" type="text/javascript"></script>
	<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
	<script type="text/javascript" src="manager/js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
	<script type="text/javascript" src="manager/js/tableTr.js"></script>
	<script type="text/javascript">
	function material() {
		var checks = document.getElementById("checks").value;
		var materialName = document.getElementById("materialName").value;
		var beginDate = document.getElementById("beginDate").value;
		var endDate = document.getElementById("endDate").value;
		if (beginDate != null && beginDate != "" && endDate != null
				&& endDate != "") {
			if (endDate < beginDate) {
				alert('结束时间不能小于开始时间');
				return;
			}
		}
		submitForm("myform", "MaterialAction!showMaterial.action");
	}
	function materialid(id) {
		document.getElementById("seq").value = id;
		submitForm("myform", "MaterialAction!findMaterialId.action");
	}
	function deleteMaterial(id) {
		if (window.confirm("确定要报废吗?")) {
			document.getElementById("seq").value = id;
			submitForm("myform", "MaterialAction!deleteMaterial.action");
		}
	}
	function wuziadd() {
		document.getElementById("materialAdd").value = "1";
		submitForm("myform", "MaterialAction!toMaterialAdd.action?editFlag=${editFlag}");
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
							资产物资管理——资产物资列表
						</td>
					</tr>
				</table>
			</div>
	<form id="myform" name="myform" action="" method="post">
		<input id="materialAdd" name="materialAdd" type="hidden" value="" />
		<input id="hospid" name="hospid" type="hidden" value="${hospid}" />
		<input type="hidden" id="editFlag" name="editFlag" value="${editFlag}" />
		<input id="seq" name="seq" type="hidden" value="" />
		
			<div>
			<span class="admin_clor_f00">${message}</span>
			<table width="100%" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="left">
						<td class="shebeigl_inp_zt" align="left" width="100px;">
							物资编号：
							</td>
							<td>
							<input name="checks" id="checks" type="text"
								style="width: 120px;" class="shebeigl_input_kt"
								value="${echoCheck}" />
						</td>
						<td  class="shebeigl_inp_zt" align="left" width="80px;">
							物资名称：
							</td>
							<td>
							<input name="materialName" id="materialName"
								style="width: 120px;" class="shebeigl_input_kt" type="text"
								value="${echoName}" />
						</td>
						<td class="shebeigl_inp_zt" width="120px;">
							购进日期：
							</td>
							<td width="300px;">
							<input class="Wdate" type="text" name="beginDate" id="beginDate" style="width: 120px;"
								value="${echoBeginDate}" size="12" readonly="readonly"
								onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" />
							至
							<input class="Wdate" type="text" name="endDate" id="endDate" style="width: 120px;"
								value="${echoEndDate}" size="12" readonly="readonly"
								onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" />
						</td>
						<td width="80" align="right">
							<img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png" onclick="material()" />
						</td>
						<td width="80">
						<c:if test="${!empty menuMap && !empty menuMap['2005001002']}">
							<a href="javascript:void(0);" class="btn blue"
								onclick="wuziadd();">添加</a>
					    </c:if>
						</td>
					</tr>
				</table>
			</div>
			<!--<span class="admin_clor_f00">${message}</span>
			
			--><table width="100%" cellspacing="1" class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th>
							序号
						</th>
						<th>
							物资编号
						</th>
						<th> 
							物资名称      
						</th>
						<th>
							生产厂家
						</th>
						<th>
							库存数量
						</th>
						<th>
							购进日期
						</th>
						<th>
							购进部门
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<c:if test="${!empty materialList}">
						<c:forEach items="${materialList}" var="material" varStatus="s">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">
									${s.count}
								</td>
								<td align="center">
									${material.apparatusno}
								</td>
								<td align="center">
									${material.name}
								</td>
								<td align="center">
									${material.tisrp}
								</td>
								<td align="center">
									${material.acount}
								</td>
								<td align="center">
									<f:formatDate value="${material.buydate}" pattern="yyyy-MM-dd" />
								</td>
								<td align="center">
									${material.buydept}
								</td>
								
								<td align="center" class="biao_lianj_1">
								<c:if test="${!empty menuMap && !empty menuMap['2005001003']}">
									<a href="javascript:void(0);"
										onclick="materialid('${material.seq}');">修改</a>
								</c:if>
								<c:if test="${!empty menuMap && !empty menuMap['2005001004']}">
									<a href="javascript:void(0);"
										onclick="deleteMaterial('${material.seq}');">报废</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<c:if test="${!empty materialList}">
				<table width="99%" border="0" class="gai_left_duiqi">
					<tr height="10">
						<td width="35%"></td>
						<td width="65%"></td>
					</tr>
					<tr height="30">
						<td class="biaoge_fanye" align="center" colspan="2">
							<swtag:paginator
								url="${pageContext.request.contextPath}/MaterialAction!showMaterial.action"
								showTotal="true" showAllPages="true" strUnit="条记录" />
						</td>
					</tr>
				</table>
			</c:if>
		
	</form>
	</div>
</body>
