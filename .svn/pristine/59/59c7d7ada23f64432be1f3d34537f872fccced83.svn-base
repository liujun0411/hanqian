<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>医院后勤智能化管理平台</title>
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script src="manager/js/My97DatePicker/WdatePicker.js"
			type="text/javascript">
</script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
        <link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>



		<script type="text/javascript">
function business() {
	var smonth = document.getElementById("smonth").value;
	var emonth = document.getElementById("emonth").value;

	//判断日期是否有效
	var today = new Date();
	var year = ((today.getYear() > 200) ? today.getYear() : 1900 + today
			.getYear());
	var month = (today.getMonth() + 1) < 10 ? "0" + (today.getMonth() + 1)
			: (today.getMonth() + 1);
	var todayMonth = year + "" + month;
	if (todayMonth < smonth || todayMonth < emonth) {
		alert("你输入的[查询日期 ]无效,已超过当前日期  !");
	} else if (smonth > emonth && smonth.length > 0 && emonth.length > 0) {
		alert("你输入的[查询日期 ]无效,开始日期不能不能大于结束日期  !");
	} else {
		document.getElementById("formSelect").submit();
	}

}

function submitform(formName) {
	var thisform = document.getElementById(formName);
	var hospId = document.getElementById("hospId").value
	var today = new Date();
	var year = ((today.getYear() > 200) ? today.getYear() : 1900 + today
			.getYear());
	var monthly = (today.getMonth() + 1) < 10 ? "0" + (today.getMonth() + 1)
			: (today.getMonth() + 1);
	var todayMonth = year + "" + monthly;
	var keys = new Array("outPatient", "emergency", "inPatient");
	var names = new Array("门诊量", "急诊量", "住院量");
	var mgs = "";
	var submitOk = true;
	var obj = null;
	var oper = document.getElementById("oper").value;

	//判断不是否为数字 
	for ( var i = 0; i < keys.length; i++) {
		try {
			obj = document.getElementById(keys[i]).value;
			if (isNaN(obj)) {
				submitOk = false;
				alert("提示：[" + names[i] + "],必需是数字!");
				return;
			}

			if (obj.length < 1) {
				submitOk = false;
				alert("提示：[" + names[i] + "],不能为空!");
				return;
			}
		} catch (e) {

		}
	}
	//判断日期是否有效
	obj = document.getElementById("monthly").value
	if (todayMonth < obj) {
		submitOk = false;
		alert("提示：你输入的[月份]无效,已超过当前日期  !");
		return;
	}
	if (obj.length < 1) {
		submitOk = false;
		alert("提示：[日期]不能为空!");
		return;
	}

	//判断登录用户是否存在
	//if (oper.length < 1) {
	//	submitOk = false;
	//	alert("提示：登录已失效，请重新登录！");
	//	return;
	//}

	if (submitOk) {
		thisform.submit();
	}

}

//超链接表单提交
function submitLinkform(action, editsequence) {
	if(action=="businessAction!showBusinessEdit.action" && ${! empty editsequence }){
		alert("提示：请完成上次操作！");
		return ;
	}
		document.getElementById("editsequence").value=editsequence;
		document.formSelect.action= action;
		document.formSelect.submit();
}

//分页控件方法
function formCheck(currentPage) {
	if (currentPage != "" || currentPage != undefined) {
		$("#page").val(currentPage);
	}
	document.formSelect.submit();
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
						<td width="679" class="biaoti_zt_canshusz">
							医院信息——医疗业务量
						</td>
					</tr>
				</table>
			</div>


			<!-- 查询 -->
			<form method="post" action="businessAction!showBusiness.action"
				name="formSelect" id="formSelect">
				<input type="hidden" name="hospId" id="hospId" value="${hospId}" />
				<input type="hidden" name="editFlag" value="${editFlag}" />
				<input type="hidden" name="editsequence" id="editsequence" value="" />
				<input type="hidden" name="page" id="page" value="${page.currentPage}" />

				<table width="100%" border="0"
					class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr style="height: 40">
						<td align="left" height="10px" style="margin-top: 5px;">
							<c:if test="${roleMessage!=2 && roleMessage!=3}">
								<span class="gai_clor_063">${title }</span>
							</c:if>
						</td>
						<td width="380" class="shebeigl_inp_zt" nowrap="nowrap">
							查询日期：
							<input type="text" name="smonth" id="smonth" size="8"
								maxlength="6" class="Wdate" readonly="readonly"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"
								value="${smonth }" />
							至
							<input type="text" name="emonth" id="emonth" size="8"
								maxlength="6" class="Wdate" readonly="readonly"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"
								value="${emonth }" />
						</td>
						<td width="60">
							<img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="business();"></img>
						</td>
						<c:if test="${!empty menuMap && !empty menuMap['2001002002']}">
							<td width="60">
								<a href="javascript:void(0);"
									onclick="submitLinkform('businessAction!showBusinessAdd.action','0');"
									class="btn blue">添加</a>
							</td>
						</c:if>
					</tr>
				</table>
				<span class="admin_clor_f00">${message}</span>
			</form>

			<table width="100%" border="0" cellspacing="1" align="center"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="20%" scope="col">
							医院名称
						</th>
						<th width="17%" scope="col">
							门诊量(人次)
						</th>
						<th width="17%" scope="col">
							急诊量(人次)
						</th>
						<th width="17%" scope="col">
							住院量(床日)
						</th>
						<th width="9%" scope="col">
							月份
						</th>
						<c:if test="${!empty menuMap && !empty menuMap['2001002003']}">
							<th width="10%" scope="col" colspan="2">
								操作
							</th>
						</c:if>
					</tr>
				</thead>

				<tbody id="stu-datas-tb">
					<c:if test="${!empty businessList }">
						<c:forEach items="${businessList}" var="buslist" varStatus="s">
							<c:if test="${editsequence != buslist.seq}">
								<tr class=" biaog_kan2 biaog_zt2">
									<td align="center">
										${buslist.dbHospInfo.hospName}
									</td>
									<td align="center">
										${buslist.outPatient}&nbsp;
									</td>
									<td align="center">
										${buslist.emergency}&nbsp;
									</td>
									<td align="center">
										${buslist.inPatient}&nbsp;
									</td>
									<td align="center">
										${buslist.monthly}&nbsp;
									</td>
									<c:if test="${!empty menuMap && !empty menuMap['2001002003']}">
										<td colspan="2" align="center" class="biao_lianj_1">
											<a href="javascript:void(0);"
												onclick="submitLinkform('businessAction!showBusinessEdit.action',${buslist.seq });">
												修 改 </a>
										</td>
									</c:if>
								</tr>
							</c:if>

							<!-- 修改 -->
							<c:if test="${editsequence == buslist.seq}">
								<form method="post"
									action="businessAction!editBusiness.action?page=${page.currentPage }&editFlag=${editFlag}&smonth=${smonth}&emonth=${emonth}"
									name="buform${s.count }" id="buform${s.count }">
									<tr class=" biaog_kan2 biaog_zt2">
										<!-- 业务量ID -->
										<input type="hidden" name="seq" id="seq"
											value="${buslist.seq}" />
										<!-- 医院ID -->
										<input type="hidden" name="hospId" id="hospId"
											value="${buslist.dbHospInfo.seqHosp}" />
										<!-- 操作员 -->
										<input type="hidden" name="oper" id="oper"
											value="${user.username  }" />
										<td align="center">
											${buslist.dbHospInfo.hospName}
										</td>
										<td align="center">
										<!-- edit start 2013.5.15 by zhangdiannan 
											<input name="outPatient" id="outPatient" size="12"
												maxlength="9" type="text" align="center"
												class="yiyuanjbxx_inp_nr2" value="${buslist.outPatient}"
												onkeyup="this.value=this.value.replace(/\D/g,'')" />
											-->	
												<input type=text
												name="outPatient" id="outPatient" size="12"
												maxlength="9" type="text" align="center"
												class="yiyuanjbxx_inp_nr2" value="${buslist.outPatient}"
												t_value=""  o_value="" 
												onkeypress="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\d+)?|\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\d+$/))this.value=0+this.value}"/>
										<!-- edit end 2013.5.15 by zhangdiannan -->
										</td>
										<td align="center">
										<!-- edit start 2013.5.15 by zhangdiannan
											<input name="emergency" id="emergency" size="12"
												maxlength="9" type="text" align="center"
												class="yiyuanjbxx_inp_nr2" value="${buslist.emergency}"
												onkeyup="this.value=this.value.replace(/\D/g,'')" />
										-->
												<input type=text
												name="emergency" id="emergency" size="12"
												maxlength="9" type="text" align="center"
												class="yiyuanjbxx_inp_nr2" value="${buslist.emergency}"
												t_value=""  o_value="" 
												onkeypress="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\d+)?|\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\d+$/))this.value=0+this.value}"/>
										<!-- edit end 2013.5.15 by zhangdiannan -->
										</td>
										<td align="center">
										<!-- edit start 2013.5.15 by zhangdiannan
											<input name="inPatient" id="inPatient" size="12"
												maxlength="9" type="text" align="center"
												class="yiyuanjbxx_inp_nr2" value="${buslist.inPatient}"
												onkeyup="this.value=this.value.replace(/\D/g,'')" />
										  -->	
												<input type=text
												name="inPatient" id="inPatient" size="12"
												maxlength="9" type="text" align="center"
												class="yiyuanjbxx_inp_nr2" value="${buslist.inPatient}"
												t_value=""  o_value="" 
												onkeypress="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\d+)?|\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\d+$/))this.value=0+this.value}"/>
										<!-- edit end 2013.5.15 by zhangdiannan -->
										</td>
										<td align="center">
											<!-- 记录修改前的月份，修改时判断数据库是否存在 -->
											<input type="hidden" name="startDate" id="startDate"
												value="${buslist.monthly}" />
											<input type="text" name="monthly" id="monthly" size="10"
												maxlength="6" class="Wdate" readonly="readonly"
												onfocus="WdatePicker({dateFmt:'yyyyMM'})"
												value="${buslist.monthly}" />
										</td>
										<td align="center" class="biao_lianj_1">
											<a href="javascript:void(0);"
												onclick="submitform('buform${s.count}');">保存</a>
										</td>
										<td align="center" class="biao_lianj_1">
											<a href="javascript:history.go(-1);">取消</a>
										</td>
									</tr>
								</form>
							</c:if>

						</c:forEach>
					</c:if>


					<!-- 新增 -->
					<c:if test="${isAdd }">
						<form method="post"
							action="businessAction!addBusiness.action?page=${page.currentPage }&editFlag=${editFlag}&smonth=${smonth}&emonth=${emonth}"
							name="buaddform" id="buaddform">
							<tr class=" biaog_kan2 biaog_zt2">
								<!-- 医院ID -->
								<input type="hidden" name="hospId" id="hospId" value="1" />
								<input type="hidden" name="hospName" id="hospName"
									value="${hospInfo.hospName }" />
								<!-- 操作员 -->
								<input type="hidden" name="oper" id="oper"
									value="${user.username  }" />
								<td align="center">
									${hospInfo.hospName}
								</td>
								<td align="center">
									<input name="outPatient" id="outPatient" type="text"
										class="yiyuanjbxx_inp_nr2" value="" size="12" maxlength="9"
										onkeyup="this.value=this.value.replace(/\D/g,'')" />
								</td>
								<td align="center">
									<input name="emergency" id="emergency" type="text"
										class="yiyuanjbxx_inp_nr2" value="" size="12" maxlength="9"
										onkeyup="this.value=this.value.replace(/\D/g,'')" />
								</td>
								<td align="center">
									<input name="inPatient" id="inPatient" type="text"
										class="yiyuanjbxx_inp_nr2" value="" size="12" maxlength="9"
										onkeyup="this.value=this.value.replace(/\D/g,'')" />
								</td>
								<td align="center">
									<input type="text" name="monthly" id="monthly" size="10"
										maxlength="6" class="Wdate" readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyyMM'})" />
								</td>
								<td align="center" class="biao_lianj_1">
									<a href="javascript:void(0);"
										onclick="submitform('buaddform');">保存</a>
								</td>
								<td align="center" class="biao_lianj_1">
									<a href="javascript:history.go(-1);">取消</a>
								</td>
							</tr>
						</form>
					</c:if>

				</tbody>
			</table>
			<c:if test="${!empty businessList }">
				<table width="100%" border="0" class="gai_left_duiqi">
					<tr>
						<td class="biaoge_fanye" align="center">
							<sktag:paginator showTotal="true" showAllPages="true"
								strUnit="条记录" />
							<%--从tag中获取翻页的信息,到这结束--%>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
		<!--</div>-->
		<!--页面主内容结束 -->
	</body>
</html>