<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<title>参数设置——短信时间告警间隔设置</title>
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

 

function toSubmit() {
 	if($("#level1").val()==''){
 		alert('告警等级1时间设置不能为空!');
 		$("#level1").foucus();
 		return ;
 	}
 	if($("#level2").val()==''){
 		alert('告警等级2时间设置不能为空!');
 		$("#level2").focus();
 		return ;
 	}
 	if($("#level3").val()==''){
 		alert('告警等级3时间设置不能为空!');
 		$("#level3").focus();
 		return ;
 	}
 
 	//判断是否位数字
 	if(isNaN($("#level1").val())){
 		alert('告警等级1时间设置只能为数字!');
 		return ;
 	}
 	if(isNaN($("#level2").val())){
 		alert('告警等级2时间设置只能为数字!');
 		$("#level2").focus();
 		return ;
 	}
 	if(isNaN($("#level3").val())){
 		alert('告警等级3时间设置只能为数字!');
 		$("#level3").focus();
 		return ;
 	}
 	
 	//告警时间 只能为1-168
 	if($("#level1").val()<=0 ||$("#level1").val()>=169){
 		alert('告警等级1时间只能设置为1-168小时!');
 		$("#level1").focus();
 		return ;
 	}
 	if($("#level2").val()<=0 ||$("#level2").val()>=169){
 		alert('告警等级2时间只能设置为1-168小时!');
 		$("#level2").focus();
 		return ;
 	}
 	if($("#level3").val()<=0 ||$("#level3").val()>=169){
 		alert('告警等级3时间只能设置为1-168小时!');
 		$("#level3").focus();
 		return ;
 	}
 	
	//$("#deptId").val(deptId);
	var form = $("#myform");
	form.submit();
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
							参数设置——短信时间告警间隔设置
						</td>
					</tr>
				</table>
			</div>

			<s:form action="alarmTime!updateAlarmTime.action" theme="simple" method="post"
				name="myform" id="myform">

				<table width="70%" border="0" cellspacing="1"
					class="biaoge_bg1 gai_left_duiqi">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<th width="2%" scope="col">
								序号
							</th>
							<th width="10%" scope="col">
								告警等级
							</th>
							<th width="20%" scope="col">
								告警时间间隔(小时)
							</th>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">

						<c:if test="${!empty alarmList}">
							<c:forEach items="${alarmList	}" var="b" varStatus="index">
								<tr class=" biaog_kan2 biaog_zt2">
									<td align="center">
										${index.count}
									</td>
									<td align="center">
										${b.alertlevel}
									</td>
									<td align="center">
										<input  type="text" name="level${index.count}" id="level${index.count}" value="${b.interval_date}"/>
										
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<tr class=" biaog_kan2 biaog_zt2">
							
							<td colspan="3" align="left" >
							<small><font color=red>*</font>最小1小时，最大168小时</small> 
								<a href="javascript:void(0);" class="btn blue" align="left" >取消</a>
							 
								<a href="javascript:toSubmit();" class="btn blue" align="left" >修改</a>
								 
							</td>
							 
						</tr>
					</tbody>

				</table>
			</s:form>
			<!--页面结束 -->
	</body>
</html>
