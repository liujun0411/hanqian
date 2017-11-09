<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台——后勤管理</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet"
			type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="manager/js/ceng.js"></script>
		<script type="text/javascript" src="manager/js/xialakuang.js"></script>
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>
		<script type="text/javascript">
		    //分页控件方法
			 function formCheck(currentPage) {
				if (currentPage != "") {
					$("#alarmcurrentPage").val(currentPage);
				}
				document.myform.submit();
			}
			
			//消除告警
			function doCannelAlarm(){
			  if(jQuery.trim($('#equipId').val())!=""){
			     var nw=window.open("about:blank","");
			     nw.location="alarm_findCurrentAlarmInfo.action?equipId="+$('#equipId').val()+"&flag=false";
			  }else if(jQuery.trim($('#groupId').val())!=""){
			     var nw=window.open("about:blank","");
			     nw.location="alarm_findCurrentAlarmInfo.action?groupId="+$('#groupId').val()+"&flag=false";
			  }
			}
		</script>
	</head>

	<body>
		<div id="admin_nr_rightg">
		  <form id="myform" name="myform" action="alarm_findAllAlarmPoint.action">
		     <input type="hidden" id="equipId" name="equipId" value="${equipId }" />
		     <input type="hidden" id="groupId" name="groupId" value="${groupId }" />
		     <input type="hidden" id="alarmcurrentPage" name="alarmcurrentPage" value="" />
		  </form>
		        <div class="itemList">
					<table width="100%" border="0" cellspacing="1" class="listTable">
						<thead>
							<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							    <th width="5%" scope="col">
									序号
								</th>
								<th width="28%" scope="col">
									监测点名称
								</th>
								<th width="10%" scope="col">
									报警等级
								</th>
								<th width="10%" scope="col">
									产生时间
								</th>
								<th width="13%" scope="col">
									告警提示
								</th>
							</tr>
						</thead>
						<tbody id="stu-datas-tb">
							<c:if test="${!empty alarmList}">
								<c:forEach items="${alarmList}" var="alarm" varStatus="idx">
									<tr class="${idx.index%2==0?'tr1':'tr2'}" onclick="doCannelAlarm();">
									    <td align="center">
									        ${idx.index+1 }
									    </td>
										<td align="center">
										    ${alarm.name}
										</td>
										<td align="center">
											${alarm.alertlevel}级
										</td>
										<td align="center">
											${alarm.starttime}
										</td>
										<td align="left">
										   ${alarm.alarm_desc}
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
				<div class="PageingEquip">
					<table width="100%">
						<tr style="height:30px">
							<td align="right" >
								<c:if test="${!empty alarmList}">
									<sktag:paginator showTotal="true" showAllPages="true"
										strUnit="条记录" />
								</c:if>
							</td>
						</tr>
					</table>
				</div>
			</div>
	</body>
</html>

