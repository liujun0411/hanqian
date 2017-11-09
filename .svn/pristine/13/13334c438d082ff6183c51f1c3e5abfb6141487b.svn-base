<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String action = request.getParameter("action"); 
	String sequence = request.getParameter("sequence");
	String recovertime = request.getParameter("recovertime");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台</title>
		<link href="manager/css/neirong.css" rel="stylesheet"	type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.spanOther{
				border:1px solid gray;
				padding:3px 0px 3px 0px;
				width:150px;
				display: block;
			}
			input{
				border:1px solid gray;
				height:20px;
				width:150px;
			}
			</style>	
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>			
		<script type="text/javascript" src="manager/javascript/monitoring/showAlarmRemarks.js"></script>
	</head>

	<body >
		<form method=post action="" id="myRemarksform" name="myRemarksform">
			<input type="hidden" id="seq" name="seq"/>
	<input type="hidden" id="action" value="<%=action%>"/>
	<input type="hidden" id="sequence" value="<%=sequence%>"/>
	
			<table id="updateTable" align="center" style="height:170px;width:90%;margin-left: 10px;" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
				<caption ><span class="gai_clor_063" style="font-weight: bolder">告警备注</span></caption>
				<tr>
					<td>报警时间</td>
<%--					<td><span class="spanOther" id="startTime" style="background-color:white;"></span></td>--%>
						<td><input readonly="readonly" onfocus="this.blur()"  id="startTime"/></td>
				</tr>
				<tr>
					<td>安装位置</td>
					<td><span class="spanOther" id="place" style="background-color:white;"></span></td>
				</tr>
				<tr>
					<td>设备</td>
					<td><span class="spanOther" id="equipName" style="background-color:white;"></span></td>
<%--					<td><input class="spanOther" id="equipName"/></td>--%>
				</tr>
				<tr>
					<td>报警点位</td>
					<td><span class="spanOther" id="pointName"style="background-color:white;"></span></td>
				</tr>
				<tr>
					<td>监视人</td>
					<td><input readonly="readonly" onfocus="this.blur()" id="monitor"/></td>
				</tr>
				<tr>
					<td>维修人员</td>
					<td><input readonly="readonly"  onfocus="this.blur()"  id="repairPerson"/></td>
				</tr>
		<!-- 		<tr>
					<td>反馈时间</td>
					<td>
					<input readonly="readonly"  onfocus="this.blur()" id="noteTime" style="width: 150px;"/>
 					</td>
				</tr>
				
				 -->
				<tr>
					<td>维修人员反馈报警原因</td>
					<td>
					<textarea  rows="4" cols="15" onfocus="this.blur()" readonly="readonly" id="reason"  style="border: 1px solid gray;resize:none;width:150px; "></textarea>
<%--					<input readonly="readonly"   />--%></td>
				</tr>
				
				<!-- 
				<tr>
					<td>办结时间</td>
					<td><input readonly="readonly"  onfocus="this.blur()" id="feedBack" /></td>
				</tr>
				 
				<tr>
					<td >维修所用材料： </td>
					<td ><input readonly="readonly" onfocus="this.blur()" type="text" name="material" id="material"/> </td>
				</tr>
				
				<tr>
					<td >维修所用费用： </td>
					<td><input readonly="readonly" onfocus="this.blur()" type="text" name="cost" id="cost"/>（元）</td>
				</tr>
				-->
				<tr>
					<td>设备恢复正常时间 </td>
					<td><%=recovertime %>
					<!-- <input readonly="readonly" onfocus="this.blur()" id="normalTime" style="width: 150px;" />  --></td>
				</tr>
				
				<!-- 
				<tr id="alarmRemovePerson">
					<td>报警消除人： </td>
					<td><input readonly="readonly" onfocus="this.blur()"  id="alarmElimit"/></td>
				</tr>
				 
				<tr>
					<td colspan="2" align="center" style="font-weight: bolder">
						其他跟踪过程备注
					</td>
				</tr>
				<tr>
					<td colspan="2" >
						<table id="otherDesTable" style="width: 100%;">	
							<tr>
								<td>
								跟踪时间
								</td>
								<td>
									<input readonly="readonly" onfocus="this.blur()"  id="trackTime1" name="trackTime1"/>
								</td>
							</tr>
							<tr>
								<td>
									  跟踪反馈情况
							 </td>
						  	<td>
						   		<input  readonly="readonly" onfocus="this.blur()" id="trackFeedBack1" name="trackFeedBack1"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
  				-->
				<tr>
					<td align="right" >
<%--					<a href="javascript:void(0);" onclick="updateRe();" class="btn blue" id="btn">保存</a>--%>
					</td>
					<td align="left">					
					<a href="javascript:void(0);" onclick="reBack();" class="btn blue">返回</a>
					</td>
				</tr>
			</table>
			
			
		</form>
	</body>
</html>