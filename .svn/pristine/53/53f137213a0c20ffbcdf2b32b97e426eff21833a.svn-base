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
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript">
		</script>
		<base href="<%=basePath%>" />
		<title>点位统计——类型分类列表</title>
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
		$("#typecurrentPage").val(currentPage);
	}
	document.forms[0].submit();
}

function changeAction(type){
	$("#type").val(type);
	$("#typecurrentPage").val(1);
	document.forms[0].submit();
}

function doOper(eqTypeId){
   //alert(window.top.document.body.innerHTML);
   if($.trim(eqTypeId)!=""){
	   $(window.top.document).find('#myOper').attr({"eqTypeId":eqTypeId});
	   $(window.top.document).find('#myOper').click();
   }
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
							点位统计——类型分类列表
						</td>
					</tr>
				</table>
			</div>
			
			<s:form action="controlRed_findControlDataListByEquipType.action" theme="simple"
				method="post" name="myform" id="myform">
				<input type="hidden" name="type" value="${type}" id="type" />
				<input type="hidden" name="typecurrentPage" value="${typecurrentPage}" id="typecurrentPage" />
				<table align="right" border="0" class="louyujj_xiaxian_hui gai_left_duiqi" style="float:right;width:80%;color: #004A7F;font-size: 13px; font-weight: bold;">
					<tr align="right">
						<td align="right" >
							锅炉点位个数:
							<a <c:if test='${boilderCount >0}'> onclick = "changeAction(2)"</c:if> href="javascript:void(0)"  style="color:#0066FF;cursor: pointer;text-decoration: none;" title="工程点位个数(不包括未监控点位)">
							${boilderCount}</a>
						</td>
						<td align="right" >
							水冷机组点位个数:<a <c:if test='${shuilengCount >0}'>onclick = "changeAction(1004)"</c:if>  href="javascript:void(0)"  style="color:#0066FF;cursor: pointer;text-decoration: none;" title="计量点位个数(不包括未监控点位)">
							${shuilengCount}</a>
						</td>
						<td align="right" >
							水表点位个数:<a <c:if test='${waterCount >0}'> onclick = "changeAction(10002)"</c:if>  href="javascript:void(0)"  style="color:#0066FF;cursor: pointer;text-decoration: none;" title="状态点位个数(不包括未监控点位)">
							${waterCount}</a>
						</td>
						<td align="right" >
							电表点位个数:<a <c:if test='${eleCount >0}'>onclick = "changeAction(10001)" </c:if>   href="javascript:void(0)" style="color:#0066FF;cursor: pointer;text-decoration: none;" title="报警点位个数(不包括未监控点位)">
							${eleCount}</a>
						</td>
						<td align="right" >
							<a align="left" class="btn blue" href="javascript:formCheck(${typecurrentPage})";><i></i><span><i></i></span><span></span>刷新</a>
						</td>
					</tr>
				</table>
			</s:form>
			<div style="width:100%;  ">
			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="5%" scope="col">
							序号
						</th>
						<th width="15%" scope="col">
							设备名称
						</th>
						<th width="15%" scope="col">
							设备类型
						</th>
						<th width="20%" scope="col">
							安装位置
						</th>
						<th width="25%" scope="col">
							设备监控点
						</th>
						<th width="8%" scope="col">
							读数
						</th>
						<th width="12%" scope="col">
							录入时间
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">

					<c:if test="${!empty controlDataByEquipTypeList}">
						<c:forEach items="${controlDataByEquipTypeList}" var="controlDate" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2" style=" cursor: pointer;<c:if test="${empty controlDate.controlpoint}">color:#f95d5d;</c:if>"  onclick="doOper('${controlDate.equip_type_id }')">
								<td align="center">
									${index.count} 
								</td>
								<td align="center">
									${controlDate.equip_name }
								</td>
								<td align="center">
									${controlDate.type_name }
								</td>
								<td align="center" >
									${controlDate.place }
								</td>
								<td align="center" >
									${controlDate.descr }
								</td>
								<td align="center" >
									${controlDate.record}
								</td>
								<td align="center" >
									${controlDate.recordtime }
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
					</tr>
				</tbody>
			</table>
			</div>
			<c:if test="${!empty controlDataByEquipTypeList}">
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
					
<%--					<c:if test="${ empty type||type==5||type==1}">--%>
<%--					<div style=" position: relative;float:right; width:12%;">--%>
<%--						<table width="100%"  style="height: 30px;">--%>
<%--							<tr>--%>
<%--								<td><span style="background-color:#333333;width: 16px;height: 16px;display: inline-block;"></span><a href="javascript:void(0)" onclick="changeAction(1)" style="text-decoration: none">已监控</a></td>--%>
<%--								<td><span style="background-color:#FF9999;width: 16px;height: 16px;display: inline-block; line-height: 20px;"></span><span style="line-height: 14px;"><a href="javascript:void(0)" onclick="changeAction(5)" style="text-decoration: none">未监控</a></span></td>--%>
<%--							</tr>--%>
<%--						</table>--%>
<%--					</div>--%>
<%--					</c:if>--%>
			    </div>
		</c:if>
			<!--页面结束 -->
	</body>
</html>
