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
		<title></title>
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
<%--		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />--%>
	
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		
		<script type="text/javascript" src="manager/js/tableTr.js"></script>
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
			//分页
			function formCheck(currentPage){
				if(currentPage != "")
					$("#hisCurrentPage").val(currentPage);
				document.myform.submit();			
			}

			/**
			 *更新备注/消除告警
			 */
			var showRemarks=function(action,sequence,recovertime){
		
				
				var startTime="";
		
				var mydata = new Array();
				mydata[0] = action;
				mydata[1] = sequence;
				mydata[2] = startTime;
				
				
				var returnValue = window.open("<%=path%>/manager/monitoring/control/monitoringPlatform/showAlarmRemarks.jsp?action="+action+"&sequence="+sequence+"&startTime="+startTime+"&recovertime="+recovertime,mydata,"height=430, width=380, toolbar =no, menubar=no,left=450,top=20, scrollbars=no, resizable=no, location=no, status=no");
				
				//var returnValue = window.showModalDialog("/manager/monitoring/control/monitoringPlatform/showAlarmRemarks.jsp",mydata,"center:yes;dialogHeight:580px;dialogWidth:450px;status:no;");
			}
			
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

	<body>
		<div id="admin_nr_jbxx">		
			<div class="canshusz_btbg_1">
					<table class="titleBg">
						<tr style="height: 20px">
							<td width="30" align="center">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td>
								监控平台——历史告警
							</td>
						</tr>
					</table>
				</div>
			<form action="alarm_findAllHisAlarm.action" method="post" name="myform" id="myform">
			    <input type="hidden" id="hisCurrentPage" name="hisCurrentPage" value="${hisCurrentPage}"/>
				<table  width="100%" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr>
						<td class="shebeigl_inp_zt" width="160" align="right">关键字:</td>
						<td  width="80" align="left">
							<input type="text" name="name"  value="${name }"/>
						</td>
						<td  width="80" class="shebeigl_inp_zt" align="right">告警等级：</td>
						<td  width="60" align="left">
							<select name="level" >
								<option value="0">--请选择--</option>
								<option value="1" <c:if test="${level == 1 }">selected</c:if>>1 级</option>
								<option value="2" <c:if test="${level == 2 }">selected</c:if>>2 级</option>
								<option value="3" <c:if test="${level == 3 }">selected</c:if>>3 级</option>
							</select>
						</td>
					
						<td width="60" class="shebeigl_inp_zt" align="right">日期:</td>
						<td class="shebeigl_inp_zt" width="300" nowrap="nowrap">
							<input class="Wdate" id="d4311" value="${startTime}" name="startTime" size="12" type="text"  onclick="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'d4312\')||\'%y-%M-%d\'}'})"/>&nbsp;至
							<input class="Wdate" id="d4312" value="${endTime }" name="endTime" size="12" type="text" onclick="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'%y-%M-%d'})"/>
							<select name="timeType" >
								<option value="0" <c:if test="${timeType == 0 }">selected</c:if>>全部</option>
								<option value="1"  <c:if test="${timeType == 1 }">selected</c:if>>告警时间</option>
								<option value="2" <c:if test="${timeType == 2 }">selected</c:if>>消除时间</option>
							</select>					
						</td>
						<td width="60" align="left">
							<img style='cursor: pointer'
							
									src="manager/images/imgs/shebeigl_sousuo_an.png"
									onclick="formCheck('')"></img>					
						</td>
					</tr>
				</table>
			</form>
				<span class="admin_clor_f00">${message}</span>			
			<div class="itemList">
			<table width="100%" border="0" cellspacing="1"
				style="font-family: '华文细黑'; font-size: 13px; 
				 color: #404041; background-color: #999999; margin-top: 10px;"
				align="center">
				<thead>
					<tr class="biaoge_tr0  biaog_kan1 biaog_zt1">
						<th width="60" scope="col">
							告警等级
						</th>
						<th scope="col">
							告警点
						</th>
						<th width="150" scope="col">
							告警时间
						</th>
						<th width="150" scope="col">
							消除时间
						</th>
						<th width="60" scope="col">
<%--							消除人--%>
							操作者

						</th>						
					</tr>
				</thead>
				<c:if test="${!empty listAlerts}">
					<tbody id="stu-datas-tb">
						<c:forEach items="${listAlerts}" var="obj" varStatus="indx">
							<tr class="${indx.index%2==0?'tr1':'tr2'}">
								<td align="center">
									${obj.alertlevel }级
								</td>
								<td align="center">
								<!-- <input  type="text" name="recovertime" value="${obj.recovertime}"/> -->
									<a href="javascript:void(0);" style="text-decoration: none;" onclick="showRemarks('${obj.seq }','${obj.starttime}','${obj.recovertime}')">
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
									${obj.name }</a>
								</td>
								<td align="center">
<%-- 									<f:formatDate value="${obj.starttime}" pattern="yyyy-MM-dd HH:mm:ss" /> --%>
									${obj.starttime}
								</td>
								<td align="center">
<%-- 									<f:formatDate value="${obj.endtime }" pattern="yyyy-MM-dd HH:mm:ss" />  --%>
									${obj.endtime }
								</td>
								<td align="center">
									${obj.oper }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			</div>
			<c:if test="${!empty listAlerts}" >
				<div style="float: left; width:100%;" align="center">
					<table width="100%" border="0" >
						<tr style="height: 50px;">
							<td>
							</td>
							<td>
								<sktag:paginator showTotal="true" showAllPages="true"  strUnit="条记录" />
							</td>
						</tr>
					</table>
				</div>
			</c:if>
			<div style="display: none; width: 220px; position: absolute;" id="reDiv">
				<div class="remarks_top"></div>
				<div class="remarks_context">
					<table width="98%"  border="0" align="center" style="filter:Alpha(opacity=100);">
						<tr>
							<td>备注:</td>
						</tr>
						<tr>						
							<td id="myValue"  valign="top" align="left"></td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>