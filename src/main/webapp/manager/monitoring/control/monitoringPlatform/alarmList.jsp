<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"  %>
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
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>		
		<script type="text/javascript">
			var showReDiv=function(value){
				var obj=getReDiv(value);
				obj.show();
			}
			hideReDiv=function(value){
				var obj=getReDiv(value);
				obj.hide();
			}
			getReDiv=function(value){
				var obj =$('#reDiv');
				var show = obj.css('display');
				var myValue = $("#myValue");
				myValue.html('&nbsp;&nbsp;'+value);
				obj.css('left',(event.clientX+16) + 'px');
				obj.css('top',(event.clientY+8) + 'px');
				return obj;				
			}

			/**
			 *更新备注/消除告警
			 */
			var updataRemarks=function(action,sequence,startTime){
			var deleteflag =$('#deleteflag').val();
			var updateDate =$('#updateDate').val();
				var mydata = new Array();
				mydata[0] = action;
				mydata[1] = sequence;
				mydata[2] = startTime;
				var returnValue = window.open("<%=path%>/manager/monitoring/control/monitoringPlatform/alarmRemarks.jsp?action="+action+"&deleteflag="+deleteflag+"&sequence="+sequence+"&startTime="+startTime+"&updateDate="+updateDate,mydata,"height=600, width=400, toolbar =no, menubar=no, left=450,top=20,scrollbars=no, resizable=no, location=no, status=no");
				//var returnValue = window.showModalDialog("//manager/monitoring/control/monitoringPlatform/alarmRemarks.jsp",mydata,"center:yes;dialogHeight:580px;dialogWidth:450px;status:no;");
				}
			function vodereturnValue(){
						var urlstr = "alarm_findCurrentAlarmInfo.action?eqcurrentPage=1";
						urlstr += "&equipId="+$("#equipId").val();
						urlstr += "&groupId="+$("#groupId").val();
						var level = "${level}";
						if(level !=""){
							urlstr += "&level="+level;
						}
						location.href = urlstr;
						$(window.parent.document).find("#alertCollect").attr("src","manager/monitoring/control/monitoringPlatform/alarmCollect.jsp");
			}
            //分页
			var formCheck = function(currentPage){
				if(currentPage != ""){
					$("#eqcurrentPage").val(currentPage);
				}
				//alert($("#alarmForm").attr('action'));
				document.myform.submit();
			}
			setTimeout(
					function(){   
						var c = $("#eqcurrentPage").val();
						formCheck(c);   
					},60*1000);	
			
			
		</script>
		<style type="text/css">
			.remarks_top {
				width: 200px;
				height: 4px;
				background-image: url(manager/images/control/lh/zhaoming_fd1.png);
				background-position: center top;
			}
			.remarks_context {
				width: 200px;
				height: arto;
				background-image: url(manager/images/control/lh/zhaoming_fd1.png);
				background-position: center bottom;
			}
		</style>
	</head>

	<body style=" background-color: white;">
          <form id="myform" name="myform" action="alarm_findCurrentAlarmInfo.action">
			<span class="admin_clor_f00">${message}</span>
			<input type="hidden" id="level" name="level" value="${level}"/>
			<input type="hidden" id="equipId" name="equipId" value="${equipId}"/>
			<input type="hidden" id="groupId" name="groupId" value="${groupId}"/>
			<input type="hidden" id="eqcurrentPage" name="eqcurrentPage" value="${eqcurrentPage}"/>
			<input type="hidden" id="flag" name="flag" value="${flag }"/>
		  </form>
			<c:if test="${!empty listAlertsCurrent}">
			<table width="100%" border="0" style="margin-top:0px;" cellspacing="1" class="shishijiankong_guolu_zt13"
				align="center" bgcolor="#CCCCCC" >
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1" style=" font-family: '黑体'; font-size: 13px; color: #00487A; background-color: #DCDDDD;">
						<th width="60" scope="col">
							告警等级
						</th>
						<th scope="col">
							告警点
						</th>
							<th scope="col">
								告警提示
							</th>
						<th width="150" scope="col">
							告警时间
						</th>
<%--						<th width="60" scope="col">--%>
<%--							备注--%>
<%--						</th>--%>
						<th width="90" scope="col">
							操作
						</th>												
					</tr>
				</thead>
				<tbody id="">					
						<c:forEach items="${listAlertsCurrent}" var="obj" varStatus="indx">
							<tr class=" biaog_kan2 biaog_zt2" style="background-color: rgb(255, 255, 255);">
								<td align="center">
									${obj.alertlevel }级
									<input type="hidden" id="deleteflag" name="deleteflag" value="${obj.deleteflag}"/>
									<input type="hidden" id="updateDate" name="updateDate" value="<f:formatDate value='${obj.updateDate}' pattern='yyyy/MM/dd HH:mm:ss' />"/>
								</td>
<%--								<td align="center" class="biao_lianj_1">--%>
<%--									<a href="javascript:void(0)"  >${obj.name }</a>--%>
<%--								</td>--%>

							<!-- add start 2013/04/01 by Lijinag -->
								<!-- 取消告警点，点名称上的点击效果 -->
									<td align="center" class="biao_lianj_1">
										${obj.building_name}
										 <c:if test="${!empty obj.storeynumup}">
									         <c:if test="${obj.storey<= obj.storeynumup&&obj.storey>0}">
									           ${obj.storey }
									       </c:if>
									       <c:if test="${obj.storey>obj.storeynumup}">
									                                        屋顶
									       </c:if>
								          </c:if>
								       <c:if test="${obj.storey<0}">
								   	        B${obj.storey<0?-obj.storey:obj.storey}
								       </c:if>层
										${obj.name }
									</td>	
							<!-- add end 2013/04/01 by Lijinag -->	
							
									<td align="center" class="biao_lianj_1">
										${obj.alarmDesc }
									</td>
								<td align="center">
									<f:formatDate value="${obj.starttime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>								
								<td align="center" class="biao_lianj_1">
									<c:if test="${!empty menuIdMap && !empty menuIdMap['4001001001']}">
										<a href="javascript:void(0);" onclick="updataRemarks('alarm_updateRemarks','${obj.seq }','<f:formatDate value='${obj.starttime}' pattern='yyyy/MM/dd HH:mm:ss' />');">查看</a>
								    </c:if>
<%--								</td>--%>
<%--								<td align="center" class="biao_lianj_1">--%>
									<c:if test="${!empty menuIdMap && !empty menuIdMap['4001001002']}">
										<a href="javascript:void(0);" onclick="updataRemarks('alarm_removeAlarm','${obj.seq }','<f:formatDate value='${obj.starttime}' pattern='yyyy/MM/dd HH:mm:ss' />');">消除</a>
									</c:if>
								</td>								
							</tr>
							
						</c:forEach>					
				</tbody>
			</table>
				</c:if>
			<!-- 分页从这里开始 -->
			<c:if test="${!empty listAlertsCurrent}" >
				<table width="100%" border="0">
					<tr  style="height: 50px;">
					<td></td>
						<td>
							<sktag:paginator showTotal="true" showAllPages="true"  strUnit="条记录" />
						</td>
					</tr>
				</table>
			</c:if>
		
		
	</body>
</html>