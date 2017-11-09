<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String action = request.getParameter("action"); 
	String sequence = request.getParameter("sequence");
	String startTime1 = request.getParameter("startTime");
	
	String updateDate = request.getParameter("updateDate"); 
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
			#divid{border:1px solid #000} 
			#divid{position:absolute; top:80%; left:10%; width:245px; height:50px;  
                background:#f3f4f8;display:none;align:center;padding-left: 0px;padding-top: 35px;padding-right: 35px;text-indent: 2em;border:1px #000 solid;border-radius:10px;} 
			</style>	
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>			
		<script type="text/javascript" src="manager/javascript/monitoring/alarmRemarks.js"></script>
	
	</head>

	<body >
		<form method=post action="" id="myRemarksform" name="myRemarksform">
	<input type="hidden" id="seq" name="seq"/>
	<input type="hidden" id="action" value="<%=action%>"/>
	<input type="hidden" id="sequence" value="<%=sequence%>"/>
	<input type="hidden" id="startTime1" value="<%=startTime1%>"/>
	
<%--			<input type="hidden" id="name" name="name"/>--%>
<%--			<input type="hidden" id="c_seq" name="c_seq"/>--%>
<%--			<input type="hidden" id="alertlevel" name="alertlevel"/>--%>
<%--			<input type="hidden" id="starttime" name="starttime"/>--%>
			
<%--			<table id="deleteTable" align="center" style="height:170px;width:90%;margin-left: 10px;" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">--%>
<%--				<caption ><span class="gai_clor_063">告警备注</span></caption>--%>
<%--				<tr>--%>
<%--					<td colspan="3" valign="top" >备注:<font  color="#FF0000">*</font></td>--%>
<%--				</tr>--%>
<%--				<tr>	--%>
<%--					<td colspan="3" height="140"><textarea style="width:100%;height:100%;overflow:auto;display:block;" id="remarks" name="remarks"></textarea></td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td></td>--%>
<%--					<td width="60" align="right" >--%>
<%--					<a href="javascript:void(0);" onclick="updateRe();" class="btn blue" id="btn">消除</a>--%>
<%--					</td>--%>
<%--					<td width="30" align="left">					--%>
<%--					<a href="javascript:void(0);" onclick="reBack();" class="btn blue">返回</a>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--			</table>--%>
			<table id="updateTable" align="center" style="height:170px;width:90%;margin-left: 10px;" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
				<caption ><span class="gai_clor_063" style="font-weight: bolder">告警备注</span></caption>
				<tr>
					<td>报警时间</td>
					<td><span class="spanOther" id="startTime"></span>
					</td>
				</tr>
				<tr>
					<td>安装位置</td>
					<td><span class="spanOther" id="place"></span></td>
				</tr>
				<tr>
					<td>设备</td>
					<td><span class="spanOther" id="equipName"></span></td>
				</tr>
				<tr>
					<td>报警点位</td>
					<td><span class="spanOther" id="pointName"></span></td>
				</tr>
				<tr>
					<td>监视人<font color="red">*</font></td>
					<td><input type="text" name="monitor" id="monitor"/></td>
				</tr>
				<tr>
					<td>通知维修人员</td>
					<td><input type="text" name="repairPerson" id="repairPerson"/></td>
				</tr>
				<tr>
					<td>通知时间</td>
					<td>
					<input class="Wdate" type="text" name="noteTime" id="noteTime" style="width: 150px;"
								 readonly="readonly"
								onfocus="WdatePicker({skin:'whyGreen',maxDate:CurentTime(),dateFmt:'yyyy/MM/dd HH:mm'})" />
<%--					<input type="text" name="noteTime" id="noteTime"/>--%></td>
				</tr>
				<tr>
					<td>维修人员反馈报警原因</td>
					<td>
					<textarea rows="4" cols="15"  maxlength="100"   name="reason" id="reason" style="border: 1px solid gray;resize:none;width:150px"></textarea>
					
<%--					<input type="text" name="reason"  id="reason"/>--%>
					</td>
				</tr>
				<tr>
					<td>反馈时间</td>
					<td><input  class="Wdate" type="text" name="feedBack" id="feedBack"   style="width: 150px;"  readonly="readonly"
								onfocus="WdatePicker({skin:'whyGreen',maxDate:CurentTime(),dateFmt:'yyyy/MM/dd HH:mm'})"/></td>
				</tr>
				<tr>
					<td >维修所用材料： </td>
					<td ><input type="text" name="material" id="material"/> </td>
				</tr>
				<tr>
					<td >维修所用费用： </td>
					<td><input type="text" name="cost" id="cost"/>（元）</td>
				</tr>
				<tr>
					<td>设备恢复正常时间： </td>
					<td><!-- readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:CurentTime(),dateFmt:'yyyy/MM/dd HH:mm'})"  -->
					<span id="deleteflagIDid" onfocus="WdatePicker({skin:'whyGreen',maxDate:CurentTime(),dateFmt:'yyyy/MM/dd HH:mm'})" ><%=updateDate%></span>
					<input   type="hidden" name="normalTime" id="normalTime"  />
					
					 </td>
				</tr>
				<tr id="alarmRemovePerson">
					<td>报警消除人： </td>
					<td><input type="text" name="alarmElimit" id="alarmElimit"/></td>
				</tr>
				<tr>
					<td>
						其他跟踪过程备注：
					</td>
					<td style="float:left;">
						<a href="javascript:void(0);" style="width:90px;"  onclick="removeOtherDes();" class="btn blue">删除跟踪过程</a>
						<a href="javascript:void(0);" style="width:90px;" onclick="addOtherDes();" class="btn blue">添加跟踪过程</a>
					</td>
				</tr>
				<tr>
					<td colspan="2" >
						<table id="otherDesTable" style="width: 100%;">	
							<tr>
								<td>
								跟踪时间：
								</td>
								<td>
									<input class="Wdate" type="text" id="trackTime1" name="trackTime1" style="width: 150px;" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:CurentTime(),dateFmt:'yyyy/MM/dd HH:mm'})" />
								</td>
							</tr>
							<tr>
								<td>
									  跟踪反馈情况：  
							 </td>
						  	<td>
						   		<input  type="text" id="trackFeedBack1" name="trackFeedBack1"/>
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
  				
				<tr>
					<td align="right" >
					<a href="javascript:void(0);" onclick="updateRe();" class="btn blue" id="btn" name="btns">保存</a>
					<div id="divid" >
					<span id="ansp"></span>
					</div>
					</td>
					<td align="left">					
					<a href="javascript:void(0);" onclick="reBack();" class="btn blue">返回</a>
					</td>
				</tr>
			</table>
			
			
		</form>
	</body>
</html>