<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<base href="<%=basePath%>" />
		<title>医院后勤智能化管理平台</title>
		
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
        <link href="manager/css/common.css" rel="stylesheet" type="text/css"></link>
        		
		<link href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/static/css/aries/jquery.aries.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/dropdown/jquery.dropdown.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script>
		<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script>
		<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script>
		<script src="<c:url value="/static/js/dropdown/jquery.dropdown.min.js"/>"></script>
						
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script src="manager/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script src="manager/js/changeNumberUnit.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>

		<script type="text/javascript">
function hospDetail() {
	var myform = document.getElementById("hospDetailForm");
	myform.action = "hospDetailAction_showHospDetail.action";
	myform.submit();
}

function subform(opr) {
	var isOK = true;
	if (opr == "select") {
		var startDate = document.getElementById("startDate").value;

		//判断年份是否有效 
		var today = new Date();
		var year = ((today.getYear() > 200) ? today.getYear() : 1900 + today
				.getYear());
		//判断年份是否有效
		if (startDate != "") {
			if (startDate.length != 4 || startDate > year) {
				alert("你输入的[信息年份]无效 !不能大超过当前日期!");
				isOK = false;
			}
		}
		document.getElementById("hospDetailForm").action = "hospDetailAction_showHospDetail.action";

	} else if (opr == "add") {
		document.getElementById("hospDetailForm").action = "hospDetailAction_showByAdd.action";
	}

	if (isOK) {
		document.getElementById("hospDetailForm").submit();
	}
}

function formCheck(currentPage) {
	if (currentPage != "")
		$("#page").val(currentPage);
	document.hospDetailForm.submit();
}

//超链接表单提交
function submitLinkform(action, sequence) {
	document.getElementById("sequence").value = sequence;
	document.hospDetailForm.action = action;
	document.hospDetailForm.submit();
}

//任意提醒，通过标签ID给需要绑定数据的元素绑定数据
 $(document).ready(function(){
	 	<c:if test="${empty remindJson}">
			var remindJson="";
		</c:if>
		<c:if test="${!empty remindJson}">
			var remindJson =${remindJson};
		</c:if>
		if(remindJson!=""){
	 	for(var obj in remindJson){
// 	 				console.debug("111111="+remindJson[obj].resultsql);
	 				$("#"+obj).data("aries",remindJson[obj]);
	 		}
		}
	});
	
</script>
	</head>
	<body style="overflow: hidden; overflow-x: hidden; overflow-y: hidden;">
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table class="titleBg">
					<tr style="height: 20px">
						<td width="30" align="center">
							<img src="manager/images/common/28-01.png" />
						</td>
						<td>
							楼宇基建——历史数据
						</td>
					</tr>
				</table>
			</div>

			<form method="post" action="hospDetailAction_showHospDetail.action"
				name="hospDetailForm" id="hospDetailForm">
				<input type="hidden" id="page" name="page"
					value="${(empty currentPage)?(page.currentPage):currentPage }" />
				<!-- 
				<input type="hidden" name="editFlag" value="${editFlag}" />
					 -->

				<input type="hidden" name="sequence" id="sequence" />

				<input type="hidden" id="seq" name="seq" value="${seq}" />

				<input type="hidden" id="hospIdSeq" name="hospIdSeq"
					value="${hospIdSeq}" />
				
				<input type="hidden" id="hospId" name="hospId"
				value="${hospId}" />
			
				<table border="0" width="780" border="0"
					class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr style="height: 40">

						<!-- 
						<td align="left" height="10px" style="margin-top: 5px;">
							<c:if test="${roleMessage!=2 && roleMessage!=3}">
								<span class="gai_clor_063">${title }</span>
							</c:if>
						</td>
					 -->
						<td align="right" class="shebeigl_inp_zt">
						<span class="admin_clor_f00" style="font-weight: normal;font-size: 12px;">${message}</span>
							查询时间：
							<input type="text" name="startDate" id="startDate" size="8"
								maxlength="6" class="Wdate" readonly="readonly"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"
								value="${startDate}" />
							年
						</td>
						<td align="right">
							<img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="hospDetail();"></img>
						</td>
						<c:if test="${editFlag == 2}">
						</c:if>
						<td align="right">
							<c:if
								test="${!empty hospDetailMenuMap && !empty hospDetailMenuMap['2002001002']}">
								<a href="javascript:void(0);" onclick="subform('add');"
									class="btn blue">添 加</a>
							</c:if>
						</td>
					</tr>
				</table>

				<c:if test="${!empty detailList}">
					<table border="0" width="780" border="0" cellspacing="1"
						align="center" class="biaoge_bg1 gai_left_duiqi">
						<thead>
							<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
								<th width="20%" scope="col">
									医院名称
								</th>
								<th scope="col">
									医院详情
								</th>
								<th width="10%" scope="col">
									年份
								</th>
								<c:if test="${editFlag == 2}">
								</c:if>
								<th width="12%" scope="col">
									操作
								</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${detailList}" var="delist">
								<tr class=" biaog_kan2 biaog_zt2 gai_yyxinxi"
									style="background-color: #FFFFFF;">
									<td align="center">
										${delist.dbHospInfo.hospName}
									</td>
									<td>
										一、房屋情况
										<br />
										<span data-aries="#aries-1" id="buildareas">
											1、年末实际占有使用房屋建筑物面积
											<span class="admin_clor_f00">${delist.buildareas}</span>（平方米）
										</span>
										<br />
										<span data-aries="#aries-1" id="innerareas">
											①账内房屋建筑
											<span class="admin_clor_f00">${delist.innerareas}</span>(平方米)
										</span>
										<br />
										<span data-aries="#aries-1" id="outareas">
											②帐外房屋建筑物
											<span class="admin_clor_f00">${delist.outareas}</span>（平方米）（不含租赁）
										</span>
										<br />
										<span data-aries="#aries-1" id="leaseareas">
											③租赁房屋建筑物
											<span class="admin_clor_f00">${delist.leaseareas }</span>（平方米）
										</span>
										<br />
										<span data-aries="#aries-1" id="buildamount">
											④年末房屋账面金额数
											<span class="admin_clor_f00">${delist.buildamount}</span>（元）
										</span>
										<br />
										<span data-aries="#aries-1" id="setupareas">
											⑤在建建筑面积
											<span class="admin_clor_f00">${delist.setupareas}</span>（平方米）
										</span>
										<br />
										<span data-aries="#aries-1" id="demolishareas">
											⑥待拆建筑面积
											<span class="admin_clor_f00">${delist.demolishareas}</span>（平方米）
										</span>
										<br />

										二、土地占用面积情况
										<br />
										<span data-aries="#aries-1" id="landareas">
											①年末土地占用面积数
											<span class="admin_clor_f00">${delist.landareas}</span>（平方米）
										</span>
										<br />
										<span data-aries="#aries-1" id="landamount">
											②年末土地占用帐面金额数
											<span class="admin_clor_f00">${delist.landamount}</span>（元）
										</span>
										<br />
										三、租赁情况
										<br />
										<span data-aries="#aries-1" id="hireareas">
											①年末面积数
											<span class="admin_clor_f00">${delist.hireareas}</span>（平方米）
										</span>
										<span data-aries="#aries-1" id="hire">
											②租金
											<span class="admin_clor_f00">${delist.hire }</span>（元/年）
										</span>
										<br />
										四、出租情况
										<br />
										<span data-aries="#aries-1" id="rentareas">
											①年末面积数
											<span class="admin_clor_f00">${delist.rentareas}</span>（平方米）
										</span>
										<span data-aries="#aries-1" id="rent">
											②收入
											<span class="admin_clor_f00">${delist.rent}</span>（元/年）
										</span>
										<br />
										五、管理情况
										<br />
										<span data-aries="#aries-1" id="principal">
											①总院负责人 :
											<span class="admin_clor_f00">${delist.principal}</span>
										</span>
										<br />
										<span data-aries="#aries-1" id="landdept">
											②总院土地管理局:
											<span class="admin_clor_f00">${delist.landdept} </span>
										</span>
										<br />
										六、其他信息
										<br />
										<span data-aries="#aries-1" id="medicalareas">
											①医疗用房面积:
											<span class="admin_clor_f00">${delist.medicalareas }</span>
										</span>
										<br />
										<span data-aries="#aries-1" id="plotratio">
											②容积率:
											<span class="admin_clor_f00">
												<c:if test="${!empty delist.plotratio}">
												   <f:formatNumber value="${delist.plotratio * 100}" pattern="#.##" type="number"/>
												</c:if>
											</span>（%）
										</span>
										<br />
										<span data-aries="#aries-1" id="afforestation">
											③绿地率:
											<span class="admin_clor_f00">
												<c:if test="${!empty delist.afforestation}">
												  <f:formatNumber value="${delist.afforestation * 100}" pattern="#.##" type="number"/>
												</c:if>
											</span>（%）
										</span>
										<br />
										<span data-aries="#aries-1" id="bedspace">
											④现有床位数:
											<span class="admin_clor_f00">${delist.bedspace } </span>
										</span>
										<br />
										<span data-aries="#aries-1" id="bedcheck">
											⑤核定床位数:
											<span class="admin_clor_f00">${delist.bedcheck} </span>
										</span>
										<br />
										<span data-aries="#aries-1" id="carplaceUp">
											⑥机动车位数——地上:
											<span class="admin_clor_f00">${delist.carplaceUp } </span>
										</span>
										<br />
										<span data-aries="#aries-1" id="carplaceDown">
											⑦机动车位数——地下:
											<span class="admin_clor_f00">${delist.carplaceDown}</span>
										</span>
										<br />
										<span data-aries="#aries-1" id="inputtime">
											七、
											<f:formatDate value="${delist.inputtime }" pattern="yyyy年" />
											业务量
										</span>
										<br />
										<span data-aries="#aries-1" id="outpatient">
											①门诊量
											<span class="admin_clor_f00">${business.outpatient}</span>(人次)
										</span>
										<br />
										<span data-aries="#aries-1" id="emergency">
											②急诊量
											<span class="admin_clor_f00">${business.emergency}</span>(人次)
										</span>
										<br />
										<span data-aries="#aries-1" id="inpatient">
											③住院量
											<span class="admin_clor_f00">${business.inpatient}</span>(人次)
										</span>
									</td>
									<td align="center">
										<f:formatDate value="${delist.inputtime}" pattern="yyyy" />
									</td>
									<c:if test="${editFlag==2}">
									</c:if>
									<td align="center" class="biao_lianj_1">
										<c:if
											test="${!empty hospDetailMenuMap && !empty hospDetailMenuMap['2002001003']}">
											<a href="javascript:void(0);" 
												onclick="submitLinkform('hospDetailAction_showByEdit.action','${delist.seq }');">修&nbsp;改</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${detailList ==null}">
					<script language="JavaScript">
alert("没有您所查询的数据！");
</script>
				</c:if>
				<table width="780" border="0" class="gai_left_duiqi"
					style="width: 100%">
					<tr>
						<td width="35%"></td>
						<td width="65%"></td>
					</tr>
					<tr>
						<c:if test="${!empty detailList}">
							<td colspan="3">
								<sktag:paginator showTotal="true" showAllPages="true"
									strUnit="条记录" />
							</td>
						</c:if>
					</tr>
				</table>

			</form>
			<!--  </div>
  </div>-->
		</div>

		<!--页面主内容结束 -->

	</body>
</html>