<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="swtag"   uri="/WEB-INF/swsoft-struts.tld" %>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String flags=request.getParameter("flags");
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
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>	
		
		<script type="text/javascript" src="static/js/jquery/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="static/js/fadeDelay/fadeDelay.js"></script>
			
		<script type="text/javascript">
		
		<!--淡入淡出特效-->
		jQuery('document').ready(function($) {
		    $('.list-group > tr').fadeDelay();
		});
		
			
			//页面dingsh
		//	setTimeout(
		//			function(){   
		//				var c = $("#eqcurrentPage").val();
		//				formCheck(c);   
		//			},60*1000);	
			
	       //定时后的提交
		var formCheck = function(currentPage){
				if(currentPage != ""){
					$("#eqcurrentPage").val(currentPage);
				}
				document.myform.submit();
			}
			//限制列表页面字数
			$(document).ready(function(){
				//限制字符个数
				$(".biao_lianj_1").each(function(){
				var maxwidth=82;
					if($(this).text().length>maxwidth){
						$(this).text($(this).text().substring(0,maxwidth));
						$(this).html($(this).html()+'…');
					}
				});
				
				
			});
			
			//显示任意提醒详情页面
			function showDetail(obj){
			var flags=$('#hisCurrentPage').val();
				$("#divShowDetail").fadeOut("slow",function(){
					$("#ifremeShowDetail").fadeIn("slow");
					$("#ifremeShowDetail").attr("src","alarm_ariesIdlist?seq="+obj+"&hisCurrentPage="+flags);
				});
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
	<body <c:if test="${flags==2}">style=" background-color: white;"</c:if>>
	      <div class="canshusz_btbg_1">
					<table class="titleBg">
						<tr style="height: 20px">
							<td width="30" align="center">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td>
								任意提醒
							</td>
						</tr>
					</table>
				</div>
          <form id="myform" name="myform" action="alarm_findCurrentAlarmInfoSuiYi.action">
			<span class="admin_clor_f00">${message}</span>
			<input type="hidden" id="eqcurrentPage" name="eqcurrentPage" value="${eqcurrentPage}"/>
			<input type="hidden" id="hisCurrentPage" name="hisCurrentPage" value="${flags}"/>
		  </form>
		  <div id="divShowDetail" style=" height: 800px;width:100%;" >
		 
		 
			<c:if test="${!empty listAlertsCurrent}">
			<div class="itemList">
			<table width="100%" border="0" style="font-family: '华文细黑'; font-size: 13px; 
				 color: #999999; background-color: #F7F7F7; margin-top: 10px;" cellspacing="1" class="biaoge_bg1 gai_left_duiqi"
				align="center" >
				<thead>
					<tr class="biaoge_tr0  biaog_kan1 biaog_zt1" style=" font-family: '黑体'; font-size: 13px; color: #00487A; background-color: #DCDDDD;">
						<th width="60" scope="col">
							序号
						</th>
						<th scope="col">
								内容
						</th>
						<th width="150" scope="col">
							操作时间
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb" class="list-group">					
						<c:forEach items="${listAlertsCurrent}" var="obj" varStatus="indx">
							<tr class=" biaog_kan2 biaog_zt2"  style="background-color: rgb(255, 255, 255);cursor: pointer;" 
							onclick="showDetail(${obj.seq});">
								<td align="center">
									${indx.index+1}
								</td>
								<td align="left" style="padding-left: 20px;" class="biao_lianj_1">
									${obj.content }
								</td>
								<td align="center">
									<f:formatDate value="${obj.opertime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>								
							</tr>
						</c:forEach>					
				</tbody>
			</table>
			</div>
			</c:if>
			<c:if test="${empty listAlertsCurrent}">
			<span span style="color:red;">暂无数据！</span>
			</c:if>
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
			</div>
	<!--页面结束 -->
				<iframe id="ifremeShowDetail" width="100%" frameborder="0" scrolling="no"  style="overflow-x:hidden; overflow-y:auto;border:none;" height="800px;"  src="" ></iframe>
			
	</body>
</html>