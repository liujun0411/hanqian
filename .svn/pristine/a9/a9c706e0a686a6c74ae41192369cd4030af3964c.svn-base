<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
			type="text/javascript"></script>
		<base href="<%=basePath%>" />
		<title>My JSP 'buildArea.jsp' starting page</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
		<script type="text/javascript" src="manager/js/ceng.js"></script>
		<script type="text/javascript" src="manager/js/xialakuang.js"></script>
		<script type="text/javascript" src="manager/js/changeSelect.js"></script>
		<script type="text/javascript" src="manager/js/jsont.js"></script>
		<script type="text/javascript" src="manager/js/zxq.js"></script>
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>
		
		<script type="text/javascript"><!--
		function Yjbopen(divID,divTU)
		{ 
			var mytr = document.getElementById(divID);
			var mytr2 = document.getElementById(divTU);
			if (mytr.style.display=="")  {
				mytr.style.display="none"; 
				mytr2.src='manager/images/imgs/sanjiao_xia.gif';
			 }else {  
				mytr.style.display="";
				mytr2.src='manager/images/imgs/sanjiao_shang.gif';
			 }
		   
		};
		 function formCheck(currentPage){
			if(currentPage != "")
				$("#page").val(currentPage);
			document.myform.submit();			
		}

			
			
			function getBuilds(hospObj){
				var o = null;
				//o=${buildMap};
				if(hospObj != null){
					hosp = hospObj.value;				
				}else
					hosp = "${user.dbHospitalinfo.seqHosp}";
				var os = document.getElementById("sequenc");
				//删除
				deleteAllOptions(os);
				//加载
				//jsAddItemToSelect(os,"","");
				//动态加载
				var item = jsonT(o, getRule(hosp));
				if(item.length>0){
					var itemTVs = item.split(";");
					for(i=0;i<itemTVs.length;i++){
						var tv = itemTVs[i].split("#");
						jsAddItemToSelect(os,tv[1],tv[0]);
					}
					if(jsSelectIsExitItem(os,"${buildingId}"))
						os.value = "${buildingId}"; 
					if(jsSelectIsExitItem(os,"")){
						jsRemoveItemFromSelect(os,"");
					}
				} else {
					jsAddItemToSelect(os,"暂无楼宇","");
				}	
			  }
			function f_show(a,b){
				//document.myFrom2.submit();
				location.href="buildRepair_updateShowRepair.action?hospid=${hospid}&details=details&repid="+a+"&buiId="+b;
			}
			
			
		--></script>
	</head>

	<body>
		<!--页面开始 -->
		  <div id="admin_nr_rightg">

			<div class="canshusz_btbg_1">
				<table class="titleBg">
						<tr style="height: 20px">
							<td width="30" align="center">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td>
								大修库——楼宇大修
							</td>
						</tr>
					</table>
			</div>
<div id="liebiao">
			<form name="myform" method="post" action="buildRepair!findBuildingRepair.action">
				<input type="hidden" name="page" value="${currentPage}" id="page" />
				<input type="hidden" name="hospid" value="${hospid }"/>
				<table width="100%" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr style="height: 35px">
					 	<td></td>
						<td align="left" class="shebeigl_inp_zt" width="50">
							楼宇：
						</td>
						<td align="left" width="100">
							<select id="buildingId" name="buildingId">
							<option value="0">--请选择--</option>
							<c:forEach items="${buildings}" var="build">
								<option  value="${build.buildingId }" ${build.buildingId==buildingId?'selected' : ''}>
									${build.buildingName }
								</option>
							</c:forEach>
							</select>
						</td>
						<td align="right" class="shebeigl_inp_zt" width="80">
							大修年月:
						</td>
						<td align="left"  width="280">
							<input type="text" name="beginDate" id="smonth" size="8"
								maxlength="6" class="Wdate"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM',maxDate:'#F{$dp.$D(\'emonth\')||\'2020-10-01\'}'})"
								value="${beginDate}" />
							&nbsp;至
							<input type="text" name="endDate" id="emonth" size="8"
								maxlength="6" class="Wdate"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM',minDate:'#F{$dp.$D(\'smonth\')||\'2020-10-01\'}'})"
								value="${endDate}" />
						</td>
						<td align="center" width="65">
							<img src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="formCheck('');" style="cursor: pointer;" />
						</td>
					</tr>
				</table>
			</form>	
			<span class="admin_clor_f00">${messages}</span>
	 <form action="buildRepair_updateShowRepair.action?hospid=${hospid}&details=details" name="myFrom2" method="post">
			<table width="100%" border="0" cellspacing="1" class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="6%" scope="col">
							序号
						</th>
						<th width="16%" scope="col">
							楼宇名称
						</th>
						<th width="18%" scope="col">
							造价依据
						</th>
						<th width="10%" scope="col">
							大修年月
						</th>
						<th width="20%" scope="col">
							结算价格（RMB/元）
						</th>
						<th width="14%" scope="col">
							改造前问题
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<c:if test="${!empty listRepair}">
						<c:forEach var="rp" varStatus="i" items="${listRepair}">
							<tr class="biaog_kan2 biaog_zt2">
								<td align="center">
									${i.index+1 }
								</td> 
								<td align="center" style="cursor: pointer;color:#3399FF;"  class="biao_lianj_1" id="u${i.count+1 }" onclick="">
									<a onclick="f_DXandJG('${hospid }','${rp.building_id}','1','buildDetails!findBuild.action','1');">${rp.building_name }</a>
								<td align="center">
									${rp.costaccord}
								</td>
								<td align="center">
									${rp.repairttime }
								</td>
								<td align="center">
									${rp.upprices }
								</td>
								<td align="center">
								    ${rp.problem}
								</td>
							</tr>
							<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff zhiti_hangju"
								id="t${i.count+2 }" style="display: none;">
								<td colspan="6" id="">
									&nbsp;&nbsp;&nbsp;&nbsp;${rp.remark }
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
</form>
			<c:if test="${!empty listRepair}">
				<table width="100%" border="0">
					<tr style="height: 50px" align="center">
						<td></td>
						<td>
							<sktag:paginator showTotal="true" showAllPages="true"
										strUnit="条记录" />
						</td>
					</tr>
				</table>
			</c:if>
		</div>
		</div>
		
		
	<div id="xiangxi" style="display: none;width:780px;">
		
		<!-- 楼宇结构和大修信息 -->
			<div style="display: block;margin-top: 15px;width: 780px;" class="gai_left_duiqi">
				<iframe src="" id="iframeJG" scrolling=no width=780 height="720" marginwidth=0 marginheight=0 frameborder=0 name="jb"></iframe>				
			</div>
	</div>
	</body>
</html>

