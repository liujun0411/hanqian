<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'show.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<!-- 设置自动刷新时间为30s -->
		<!-- 
		<meta http-equiv="Refresh" content="30"/>-->
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet" type="text/css" />
		<!-- 弹出层 -->
        <link href="manager/common/fullScreen/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/common/fullScreen/tipswindown.js"></script>
		<script type="text/javascript"> 
			/* *弹出本页指定ID的内容于窗口 *id 指定的元素的id *title: window弹出窗的标题 *width: 窗口的宽,height:窗口的高 */
			function showTipsWindown(title,id,width,height){
				 tipsWindown(title,"id:"+id,width,height,"true","","true",id); 
			} 
			function confirmTerm(s) { 
				 parent.closeWindown();
			 }
			//弹出层调用
			function popTips(){ 
				 showTipsWindown($("#title").val(), 'simTestContent', 900, 600);
			} 
			$(document).ready(function(){
				 $("#isread-text").click(popTips); 
			});
		</script> 
		<script type="text/javascript">
		   $(function(){
		     $(".peidianPic").click(function(){
		         showChild($(this).attr('id'),$(this).parent().next().text());
		     });
		   });
		   
		   function showChild(groupId,title) {
		      $("#title").val(title);
		      $('#childList').attr({src:"control_showChildEquipList.action?seq="+groupId});
		      $("#isread-text").click();
		   }
		   function hidediv() {
			  $("#bg").hide();
			  $(".eqList").hide();
		   }
		   
		   function closeControl(){
		     $(window.parent.document).find("#pageList div").slideToggle(1000);
		     $(window.parent.document).find("#control_Info").slideToggle(1000);
		   }
		</script>
	</head>

	<body id="mainDiv" style="overflow: scroll; overflow-x: hidden; overflow-y: hidden; ">
	   <input type="hidden" id="equipId" name="equipId" value="${equipId }" />
	   <input type="hidden" id="groupId" name="groupId" value="${groupId}" />
	   <input type="hidden" id="eqTypeId" name="eqTypeId" value="${eqTypeId }" />
	   <input type="hidden" id="title" value=""/>
		<div id="admin_nr_rightg">
			<!-- 图片区 -->
			<div style="height: 7px;">
			    <img src="manager/images/control/closePage.png" alt="关闭监控界面" title="关闭监控界面" style="margin-left: 200px;  cursor: pointer;" onclick="closeControl();"/>
			</div>
			<div style=" border:0px solid red;" class="shishijiankong_zxq">
				 <div style=" height: 20px; width: 100%; border:0px solid red;">
				    <table style=" width: 100%;">
				        <tr>
				           <td align="center"><span style=" font-size: 14px; color:#F90; font-weight: bold;">【${groupName}】</span></td>
				        </tr>
	 			    </table>
				 </div>
				 <div style=" width:100%; height: 225px; border:0px solid red; background: url(manager/images/control/shishijiankong_beidian1.png ) no-repeat;">
					<!-- 上部图片 -->
				 </div>
				 <!-- 电柜列表 -->
				 <div style=" width: 100%; border:0px solid blue; height:500px; overflow:auto; background: url(manager/images/control/shishijiankong_beidian2.png) no-repeat; background-position: center top;">
				   <table style=" width: 100%;" cellpadding="3" border="0">
				      <c:forEach items="${childItemList}" varStatus="i" step="2">
					      <tr align="center">
					         <td width="50%">
					           <c:if test="${childItemList[i.index].seq!=null}">
					               <a href="javascript:void(0)" title="${childItemList[i.index].group_name }"><img src="manager/images/control/diangui.png" class="peidianPic" id ="${childItemList[i.index].seq}" alt="${childItemList[i.index].group_name }"/></a><div>${childItemList[i.index].group_name }</div>
					           </c:if>
					         </td>
					         <td width="50%">
					           <c:if test="${childItemList[i.index+1].seq!=null}">
					               <a href="javascript:void(0)" title="${childItemList[i.index+1].group_name }"><img src="manager/images/control/diangui.png" class="peidianPic" id ="${childItemList[i.index+1].seq}" alt="${childItemList[i.index].group_name }"/></a><div>${childItemList[i.index+1].group_name }</div>
					           </c:if>
					         </td>
					      </tr>
				      </c:forEach> 
				   </table>
				 </div>
			</div>
	    </div>
	    <!-- 遮罩层 -->
		<div class="agreesddd"> 
			<label id="isread-text" style=" display: none;"></label>
	    </div>
	    <div style="display:none;"> 
		    <div id="simTestContent" class="simScrollCont"> 
				<iframe src="" id="childList" scrolling=no width="900" height="520" marginwidth=0
					marginheight=0 frameborder=0></iframe>
		   </div>
        </div> 
	    
	</body>
</html>