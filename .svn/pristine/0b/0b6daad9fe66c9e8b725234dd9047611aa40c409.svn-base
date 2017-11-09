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

function ondblclickId(dwbm){
	var rotate_all_li = $(window.parent.document).contents().find("#rotate");
	var service_li = $(rotate_all_li).find("li").eq(0);
	var sdcd_li = $(rotate_all_li).find("li").eq(1);
	$(service_li).removeClass("ui-tabs-selected");
	$(sdcd_li).addClass("ui-tabs-selected"); 
  location.href='controlRed_sDCDfindDiwanweiInfoList.action?currentPagee=1&dwbm1='+dwbm;
}

//导出Excel
function daochu(){
	//动态生成一个form表单和5个文本框
	var pointName1 = $('#pointName').val();
	var projectPoint1 = $('#projectPoint').val();
	var equipName1 = $('#equipName').val();
	var startTime1 = $('#d4311').val();
	var endTime1 = $('#d4312').val();
	var form  =$("<form>");//动态生成form
	form.attr('style','display:none');
	form.attr('target','');
	form.attr('method','post');
	form.attr('action','controlRed_export.action');
	var input1 = $("<input>");
	input1.attr('type','hidden');
	input1.attr('name','pointName');
	input1.attr('value',pointName1);
	var input2 = $("<input>");
	input2.attr('type','hidden');
	input2.attr('name','projectPoint');
	input2.attr('value',projectPoint1);
	var input3 = $("<input>");
	input3.attr('type','hidden');
	input3.attr('name','equipName');
	input3.attr('value',equipName1);
	var input4 = $("<input>");
	input4.attr('type','hidden');
	input4.attr('name','startTime');
	input4.attr('value',startTime1);
	var input5 = $("<input>");
	input5.attr('type','hidden');
	input5.attr('name','endTime');
	input5.attr('value',endTime1);
	$('body').append(form);
	form.append(input1);
	form.append(input2);
	form.append(input3);
	form.append(input4);
	form.append(input5);
	form.submit();
}
</script>
	</head>
	<body>
		<div id="admin_nr_rightg">
			<s:form action="controlRed_selectfindDianweiInfoList.action" theme="simple"
				method="post" name="myform" id="myform">
				<input type="hidden" name="type" value="${type}" id="type" />
				<input type="hidden" name="currentPage" value="${currentPage}" id="currentPage" />
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi"
					width="100%" align="right" style="float: right;">
					<tr>
						<td  width="90" align="center" class="shebeigl_inp_zt">
							<span>点位名称：</span>
						</td>
						<td  width="80">
							<input id="pointName" name="pointName" class="myfont" value="${pointName }"/>
						</td>
						<td  width="90" align="center" class="shebeigl_inp_zt">
							<span>点位编码：</span>
						</td>
						<td  width="80">
                            <input id="dwbmSDCD" name="dwbmSDCD" class="myfont" value="${dwbmSDCD }"/>
						</td>
						<td  width="90" align="center" class="shebeigl_inp_zt">
							<span>设备名称：</span>
						</td>
						<td  width="80">
							 <input id="equipName" name="equipName" class="myfont" value="${equipName }"/>
						</td>
						<td  width="90" class="shebeigl_inp_zt">
						</td>
						<td  width="80">
						</td>
					</tr>
					<tr>
					<td  width="90" align="center" class="shebeigl_inp_zt">
							<span>日期查询：</span>
					</td>
					<td  colspan="3"  width="170" class="shebeigl_inp_zt">
					<input class="Wdate" id="d4311" name="startTime"  value="${startTime }" size="12" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'d4312\')||\'%y-%M-%d\'}'})"/>&nbsp;<span>~</span>&nbsp;<input class="Wdate" id="d4312" name="endTime" size="12" type="text"  value="${endTime }" onclick="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'%y-%M-%d'})"/>
					</td>
						<td>

						</td>
                        <td align="right">
                        	<img onclick="formCheck();"	src="manager/images/imgs/shebeigl_sousuo_an.png"
								style="cursor: pointer"/>
                        </td>
						<td>
						</td>
                        <td>
                          	<c:if test="${!empty menuIdMap && !empty menuIdMap['5009003']}">
								<a href="javascript:void(0)" class="btn blue" onclick="daochu()" id="addBtnA" >导出Excel</a>
							</c:if>
                        </td>
					</tr>
				</table>
			</s:form>
			<div style="width:100%;overflow-x: scroll;">
			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th nowrap="nowrap" scope="col">
							序号
						</th>
						<th nowrap="nowrap" scope="col">
							工程点位
						</th>
						<th nowrap="nowrap"  scope="col">
							点位编码
						</th>
						<th nowrap="nowrap" scope="col">
							点位名称
						</th>
						<th nowrap="nowrap"  scope="col">
							读数
						</th>
						<th  nowrap="nowrap" scope="col">
							采集时间
						</th>
						<th  nowrap="nowrap" scope="col">
							记录时间
						</th>
						<th  nowrap="nowrap" scope="col">
							设备大类编码 
						</th>
					    <th  nowrap="nowrap" scope="col">
							设备大类
						</th>
				       <th  nowrap="nowrap" scope="col">
							设备类型
						</th>
					    <th nowrap="nowrap" scope="col">
							设备型号
						</th>
				       <th nowrap="nowrap" scope="col">
							设备名称
						</th>	
					    <th nowrap="nowrap" scope="col">
							设备编码 
						</th>
				       <th nowrap="nowrap" scope="col">
							安装楼宇
						</th>
					    <th nowrap="nowrap" scope="col">
							安装楼层
						</th>
				       <th nowrap="nowrap" scope="col">
							服务楼宇
						</th>	
				       <th nowrap="nowrap" scope="col">
							服务楼层 
						</th>
					    <th nowrap="nowrap" scope="col">
							备注
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">

					<c:if test="${!empty selectDianweiIfosList1}">
						<c:forEach items="${selectDianweiIfosList1}" var="indexSelect" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2" style=" cursor: pointer;" ondblclick="ondblclickId('${indexSelect.dwbm }');">
								<td nowrap="nowrap" align="center">
									&nbsp;${index.count}&nbsp;
								</td>
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.gcdw }&nbsp;
								</td>
								<td nowrap="nowrap" align="center">
									&nbsp;${indexSelect.dwbm }&nbsp;
								</td>
								<td nowrap="nowrap" align="center">
                                    &nbsp;${indexSelect.dwmc }&nbsp;
								</td>
								<td nowrap="nowrap" align="center" >
									&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2" pattern="0.00" value="${indexSelect.ds }"/>&nbsp;
								</td>
								<td nowrap="nowrap" align="center" >
									&nbsp;<fmt:formatDate value="${indexSelect.cjsj }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;
								</td>
								<td nowrap="nowrap" align="center" >
									&nbsp;<fmt:formatDate value="${indexSelect.jlsj }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;
								</td>
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.sbdlbm }&nbsp;
								</td>
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.sbdl}&nbsp;
								</td>	
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.sblx}&nbsp;
								</td>			
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.sbxh}&nbsp;
								</td>
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.sbmc}&nbsp;
								</td>	
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.sbbm}&nbsp;
								</td>	
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.azly}&nbsp;
								</td>		
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.azlc}&nbsp;
								</td>	
								<td  nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.fwly}&nbsp;
								</td>		
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.fwlc}&nbsp;
								</td>	
								<td nowrap="nowrap" align="center" >
									&nbsp;${indexSelect.bz}&nbsp;
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
					</tr>
				</tbody>
			</table>
			</div>
			<c:if test="${empty selectDianweiIfosList1}">
			  <span style="color:red;">暂无数据！</span>
			</c:if>
			<c:if test="${!empty selectDianweiIfosList1}">
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
