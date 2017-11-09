<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'building.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
	
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		


		<link href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/static/css/aries/jquery.aries.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/dropdown/jquery.dropdown.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script>
		<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script>
		<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script>
		<script src="<c:url value="/static/js/dropdown/jquery.dropdown.min.js"/>"></script>
<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/biaoge_1.js">
</script>
		<script type="text/javascript" src="manager/js/ceng.js">
</script>
		<script type="text/javascript" src="manager/js/tableTr.js">
</script>

		<script type="text/javascript">
//根据医院ID查询
function formCheck(currentPage) {
	if (currentPage != "")
		$("#page").val(currentPage);
	document.myForm.submit();
}

f_change = function() {
	document.myForm.submit();
}

/**获取楼宇热区*/
$(function() {
	$('#imgDiv').mousemove(function(e) {
			var x = e.originalEvent.x || e.originalEvent.layerX || 0; //鼠标点击的X坐标
			var y = e.originalEvent.y || e.originalEvent.layerY || 0; //鼠标点击的Y坐标
			y = y - 60;
			$("#txtXY").val(x + "," + y);
		});
	$('#imgDiv').click(function(e) {
		var x = e.originalEvent.x || e.originalEvent.layerX || 0; //鼠标点击的X坐标
			var y = e.originalEvent.y || e.originalEvent.layerY || 0; //鼠标点击的Y坐标
			y = y - 60;
			var point = x + "," + y;
			$("#hotXY").val($("#hotXY").val() + "," + point);
		});
	f_biao();
});

//根据状态进行模糊查
f_changeStatus = function() {
	location.href = "building_findBuild.action?statusSel="
			+ $("#statusSel").val()
			+ "&hospId=${hospId}&editFlag=${editFlag}&path=${PATH}&showOrhide=1";

}

f_biao = function() {
	//var Days = 1; //此 cookie 将被保存 1 天
	//var exp　　 = new Date();　　　　 //new Date("December 31, 9998");
	//exp.setTime(exp.getTime() + Days*24*60*60*1000);
	//document.cookie = "showbdiv" + "="+ escape("1") +";expire*="+ exp.toGMTString();

	document.getElementById("showTable").src = "manager/images/imgs/gailiebiao2.png";
	document.getElementById("showBilie").src = "manager/images/imgs/gai_louyu_yongtu_2.png";
	document.getElementById("showZhbi").src = "manager/images/imgs/gai_louyu_zhanbi_2.png";
	$("#biaoDiv").show();
	$("#myForm").show();
	$("#stuffid").show();
	$("#jiegou").show();
	$("#sousuo").show();
	$("#bilitu").show();
	$("#add").show();
	$("#bilieDiv").hide();
	$("#showZhbiDiv").hide();
}
f_bilie = function() {
	//var Days = 1; //此 cookie 将被保存 1 天
	///var exp　　 = new Date();　　　　 //new Date("December 31, 9998");
	//exp.setTime(exp.getTime() + Days*24*60*60*1000);
	//document.cookie = "showbdiv" + "="+ escape("2") +";expire*="+ exp.toGMTString();

	document.getElementById("showTable").src = "manager/images/imgs/gailiebiao.png";
	document.getElementById("showBilie").src = "manager/images/imgs/gai_louyu_yongtu_1.png";
	document.getElementById("showZhbi").src = "manager/images/imgs/gai_louyu_zhanbi_2.png";
	$("#biaoDiv").hide();
	$("#stuffid").hide();
	$("#jiegou").hide();
	$("#sousuo").hide();
	$("#bilitu").hide();
	$("#myForm").hide();
	$("#add").hide();
	$("#bilieDiv").show();
	$("#showZhbiDiv").hide();
	if ($("#showbilieDiviframe").attr("src").length < 1) {
		$("#showbilieDiviframe").attr("src", "buildUse_findBuildingUse.action");
	}
}

f_zhbi = function() {
	//var Days = 1; //此 cookie 将被保存 1 天
	//var exp　　 = new Date();　　　　 //new Date("December 31, 9998");
	//exp.setTime(exp.getTime() + Days*24*60*60*1000);
	//document.cookie = "showbdiv" + "="+ escape("3") +";expire*="+ exp.toGMTString();

	document.getElementById("showTable").src = "manager/images/imgs/gailiebiao.png";
	document.getElementById("showBilie").src = "manager/images/imgs/gai_louyu_yongtu_2.png";
	document.getElementById("showZhbi").src = "manager/images/imgs/gai_louyu_zhanbi_1.png";
	$("#biaoDiv").hide();
	$("#stuffid").hide();
	$("#jiegou").hide();
	$("#myForm").hide();
	$("#sousuo").hide();
	$("#bilitu").hide();
	$("#add").hide();
	$("#bilieDiv").hide();
	if ($("#showZhbiDiviframe").attr("src").length < 1) {
		$("#showZhbiDiviframe").attr("src",
				"drawing_showHospitalUse.action?hospId=${hosinfoid}");
	}
	$("#showZhbiDiv").show();

}

building_deleteBuilding = function(buildId) {
	if (confirm("拆除该楼宇后，和该楼宇相关的面积和设备将无法查看到，是否确定拆除?")) {
		var buildingId = buildId;
		var params = {
			"buildingId" : buildingId
		};
		$
				.ajax( {
					type : "POST",
					url : "building_findChildEquipByBuildId.action",
					dataType : "json",
					data : params,
					cache : false,
					async : false,
					error : function(data) {

					},
					success : function(data) {
						var dt = data;
						if (dt.countFlag&&dt.countFlag!=undefined&&dt.countFlag!=null&&dt.countFlag!='') {
							alert('该楼宇里面存在设备，不能删除，要先删除该楼宇的所有设备后才能删除该楼宇!');
						} else {
							window.location = 'building_deleteBuilding.action?buildingId=' + buildId;
						}
					}
				});
	}

}

//任意提醒，通过标签ID给需要绑定数据的元素绑定数据
$(document).ready(function(){
		<c:if test="${empty remindJson}">
			var remindJson="";
		</c:if>
		<c:if test="${!empty remindJson}">
			var remindJson=${remindJson};
		</c:if>
		if(remindJson!=""){
		 	for(var obj in remindJson){
	 	 				console.debug("楼宇导航--列表模式remindJson： ="+remindJson[obj].resultsql + ",obj=" + obj + ", $(obj)=" + $("#"+obj));
		 				$("#"+obj).data("aries",remindJson[obj]);
		 		}
		}
	});
</script>
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
	</head>
	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;"
		bgColor="transparent">
		<!--页面开始 -->
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table class="titleBg">
					<tr style="height: 20px">
						<td width="30" align="center">
							<img src="manager/images/common/28-01.png" />
						</td>
						<td>
							楼宇基建——楼宇导航
						</td>
					</tr>
				</table>
			</div>
			
			<!--        <div class="admin_nr_right_g01">后勤信息 &gt; 楼宇信息</div>-->
			<div class="admin_nr_right_g02">
				<table width="100%" border="0">
					<tr style="height: 25px;" valign="middle">
						<td width="2"></td>
						<td width="83" style="cursor: pointer">
							<img src="manager/images/imgs/gailiebiao.png " id="showTable"
								onclick="f_biao()" />
						</td>
						<td width="4" valign="bottom">
							<img src="manager/images/imgs/gaixian.png" />
						</td>
						<td width="83" style="cursor: pointer">
							<img src="manager/images/imgs/gai_louyu_zhanbi_2.png"
								id="showZhbi" onclick="f_zhbi()" />
						</td>
						<td width="4" valign="bottom">
							<img src="manager/images/imgs/gaixian.png" />
						</td>
						<td width="83" style="cursor: pointer">
							<img src="manager/images/imgs/gai_louyu_yongtu_2.png"
								id="showBilie" onclick="f_bilie()" />
						</td>
						<td></td>
					</tr>
				</table>
			</div>


			<form name="myForm" action="building_findBuild.action?showOrhide=1"
				method="post" id="myForm">

				<table width="780" border="0"
					class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						<td align="left" height="10px" style="margin-top: 5px;">

						</td>
						<td class='shebeigl_inp_zt'>
							楼名
						</td>
						<td width="30px">
							<input type="text" id="buildingName" name="building.buildingName"
								value="${building.buildingName}" style="width: 80px;" />
						</td>
						<td class='shebeigl_inp_zt'>
							曾用名
						</td>
						<td>
							<input type="text" id="hisName" name="building.hisName"
								value="${building.hisName}" style="width: 80px;" />
						</td>
						<td id='jiegou' <c:if test="${editFlag==1}"> width='90' </c:if>
							align='right' class='shebeigl_inp_zt'>
							建筑结构：
						</td>
						<td width="80" align="right">
							<select id="materId" name="buildMater.materId">
								<option value="">
									--请选择--
								</option>
								<c:forEach items="${listMater}" var="mater">
									<c:if test="${buildMater.materId == mater.masterid}">
										<option value="${mater.masterid }" selected="selected">
											${mater.mastername }
										</option>
									</c:if>
									<c:if test="${buildMater.materId != mater.masterid}">
										<option value="${mater.masterid }">
											${mater.mastername }
										</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<td class='shebeigl_inp_zt'>
							建筑状态
						</td>
						<td>
							<select id="statusSel" name="building.dbBaseComm.dbBaseType.seq">
								<option value="">
									--请选择--
								</option>
								<c:forEach items="${listBaseComm}" var="sta">
									<c:if test="${building.dbBaseComm.seq == sta.seq}">
										<option value=${sta.seq } selected="selected">
											${sta.content1 }
										</option>
									</c:if>
									<c:if test="${building.dbBaseComm.seq != sta.seq}">
										<option value=${sta.seq }>
											${sta.content1 }
										</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<td width="80" align="right">
							<img style='cursor: pointer; display: none;'
								src="manager/images/imgs/shebeigl_sousuo_an.png" id="sousuo"
								onclick="f_change()" />
						</td>
						<td width="50" align="right" id="add" style="display: none;">
							<c:if
								test="${!empty buildingMenuMap && !empty buildingMenuMap['2002002002002']}">
								<a
									href="building_showBuilding.action?addOredit=1&editFlag=${editFlag }"
									class="btn blue">添加</a>
							</c:if>
						</td>
					</tr>
				</table>
				<input type="hidden" name="editFlag" value="${editFlag}"
					id="editFlag" />
				<input type="hidden" name="page" value="${currentPage}" id="page" />
				<input type="hidden" name="showOrhide" value="${showOrhide}"
					id="showOrhide" />
				<input type="hidden" name="hospId" value="${hospId}" id="hospId" />
			</form>

			<div id="biaoDiv" class="gai_left_duiqi" style="display: none;">
				<span class="admin_clor_f00">
					<table width="780" border="0" cellspacing="1" class="biaoge_bg1">
						<thead>
							<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
								<th width="5%" scope="col">
									序号
								</th>
								<th width="14%" scope="col">
									楼名
								</th>
								<th width="10%" scope="col">
									曾用名
								</th>
								<th width="16%" scope="col">
									层数
								</th>
								<th width="12%" scope="col">
									总面积(㎡)
								</th>
								<th width="10%" scope="col">
									投资额(元)
								</th>
								<th width="8%" scope="col">
									大修次数
								</th>
								<th width="8%" scope="col">
									建筑状态
								</th>
								<th width="9%" scope="col">
									操作
								</th>
							</tr>
						</thead>


						<c:if test="${!empty listBuilding}">
							<tbody id="stu-datas-tb">
								<c:forEach items="${listBuilding}" var="build" varStatus="idx">
									<tr class="biaog_kan2 biaog_zt2">
										<td align="center">
											<c:out value='${idx.index+1}' />
										</td>
										<td align="center" class="biao_lianj_1">
											<a
												href="buildDetails!findBuild.action?buildingId=${ build.buildingId}&myfirst=building_findBuild&editFlag=${editFlag}&hospid=${build.dbHospInfo.seqHosp}&hideOrshow=1&tag=2">
												${build.buildingName }</a>
										</td>
										<td align="center" class="biao_lianj_1 aries-anchor-line" data-aries="#aries-1" id="hisName${idx.index}">
											${build.hisName}
										</td>
										<td class="biao_lianj_1 aries-anchor-line" align="center" data-aries="#aries-1" id="storeyNum${idx.index}">
											<!-- -->
											地上${build.storeyNumUp }层，地下${build.storeyNumDown}层
										</td>
										<td align="center" class="aries-anchor-line" data-aries="#aries-1" id="buildingAreas${idx.index}">
											${build.buildingAreas }
										</td>
										<td align="center" class="aries-anchor-line" data-aries="#aries-1" id="amount${idx.index}">
											<c:if test="${build.amount>0.0}">
												<fmt:formatNumber value="${build.amount }"
													pattern="#,#00.00#" />
											</c:if>
										</td>
										<td align="center" data-aries="#aries-1" class="aries-anchor-line" id="mendNum${idx.index}">
											${build.mendNum }
										</td>

										<td align="center" data-aries="#aries-1" class="aries-anchor-line" id="buildingStatus${idx.index}">
											${build.dbBaseComm.content1 }
										</td>
										<td align="center" class="biao_lianj_1">
											<c:if
												test="${!empty buildingMenuMap && !empty buildingMenuMap['2002002002003']}">
												<a
													href="building_updateBuilding.action?buildingId=${ build.buildingId}&addOredit=2&editFlag=${editFlag }">编辑</a>
											</c:if>
											<c:if
												test="${!empty buildingMenuMap && !empty buildingMenuMap['2002002002004']}">
												<a href="javascript:void(0)"
													onclick="building_deleteBuilding(${ build.buildingId})">拆除</a>

											</c:if>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${!empty message && message!='' && message=='0'}">
                                    <script type='text/javascript'>alert('修改成功');</script>
                                </c:if>
                                <c:if test="${!empty message && message!='' && message=='1'}">
                                    <script type='text/javascript'>alert('添加成功');</script>
                                </c:if>
							</tbody>
						</c:if>
						<c:if test="${empty listBuilding}">
							<script language="JavaScript">
								alert("没有您所查询的数据！");
							</script>
						</c:if>
					</table> 
					<c:if test="${!empty listBuilding}">
						<table width="780" border="0">
							<tr style="height: 50px;">
								<td></td>
								<td>
									<sktag:paginator showTotal="true" showAllPages="true"
										strUnit="条记录" />
								</td>
							</tr>
						</table>
					</c:if>
			</div>

			<!-- 楼宇占比 -->
			<div id="showZhbiDiv" style="display: none;">
				<iframe src="" allowTransparency="true" scrolling="no" width="100%"
					height=660 marginwidth=0 marginheight=0 frameborder=0
					id="showZhbiDiviframe"></iframe>
			</div>

			<!-- 比例信息 -->
			<div id="bilieDiv" style="display: none;">
				<iframe src="" style="overflow-y: auto;" allowTransparency="true"
					scrolling="no" width="100%" height=960 marginwidth=0 marginheight=0
					frameborder=0 id="showbilieDiviframe"></iframe>
			</div>
		</div>
	</body>
</html>
