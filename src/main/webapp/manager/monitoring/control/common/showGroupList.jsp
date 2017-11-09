<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
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
		<base href="<%=basePath%>" />
		<title>医院后勤智能化管理平台</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" href="manager/css/neirong.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/skdiv.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/ziti.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/tableTr.js"></script>
		<link rel="stylesheet" href="manager/common/tab/ui.tabs.css" type="text/css" media="print, projection, screen"></link>
		<link href="manager/common/fullScreen/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/common/fullScreen/tipswindown.js"></script>
        <!-- 弹出层 -->
        <link href="manager/common/fullScreen/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/common/fullScreen/tipswindown.js"></script>
		<script type="text/javascript"> 
			/* *弹出本页指定ID的内容于窗口 *id 指定的元素的id *title: window弹出窗的标题 *width: 窗口的宽,height:窗口的高 */
			function showTipsWindown(title,id,width,height){
				 tipsWindown(title,"id:"+id,width,height,"true","","true",id,1); 
			} 
			function confirmTerm(s) { 
				 parent.closeWindown();
			 }
			//弹出层调用
			function popTips(){ 
				 showTipsWindown($("#groupName").val(), 'simTestContent', 780, 285);
			} 
			$(document).ready(function(){
				 $("#isread-text").click(popTips); 
			});
		</script> 

		<script type="text/javascript">
		submit = function() {
			$("form:first").submit();
		}
		
		showEquip = function(seq) {
			$("#seq").val(seq);
			$("#myform").attr( {
				action : 'control_showGroupEquipList.action'
			})
			$("#myform").submit();
		}
		//control_showGroupEquip.action
		function showChild(groupId, groupName) {
		   $("#groupName").val(groupName);
           $("#listItem").attr({src:"control_showGroupEquip.action?seq="+groupId});
	       $("#isread-text").click();
		}
		
		function hidediv() {
			document.getElementById("bg").style.display = 'none';
			document.getElementById("show").style.display = 'none';
		}
		
		$(function() {
			$(".ahref").click(function(event) {
				event.stopPropagation();
			});
		});

        //显示监控界面
        function showMonitor(equipid,equipTypeId,buildId,groupName,groupCode){
           //有选项卡的情况下
           if(jQuery.trim($('#HasTab').val())!=""){
	           $(window.parent.document).find('#equipList_'+equipTypeId).slideToggle(1000);
	           $(window.parent.document).find('#controlPage_'+equipTypeId).attr({src:'control_findToControl.action?groupId='+equipid+"&eqTypeId="+equipTypeId+"&buildId="+buildId+"&groupName="+groupName+"&groupCode="+groupCode+"&t="+Math.random()});
               $(window.parent.document).find('#commonShow_'+equipTypeId).slideToggle(1000);
	           $(window.parent.document).find('#controlDescript_'+equipTypeId).attr({width:0});
           }else{
               //无选项卡
              $('#ListInfo div').slideToggle();
               $("#control_Info").slideToggle(1000);
               $("#Control_page").attr({src:"control_findToControl.action?groupId="+equipid+"&groupCode="+groupCode+"&eqTypeId="+equipTypeId+"&buildId="+buildId+"&groupName="+encodeURIComponent(encodeURIComponent(groupName))+"&t="+Math.random()});
           }
        }
        
		function formCheck(currentPage) {
			if (currentPage != "" || currentPage != undefined) {
				$("#unGrcurrentPage").val(currentPage);
			}
			document.myform.submit();
		}
		
		//关闭监控界面
		function doHideControl(){
		    $("#ListInfo div").slideToggle(1000);
            $("#control_Info").slideToggle(1000);
            $(window.parent.parent.parent.document).find('#control_warnDiv').hide(500);
            $(window.parent.parent.parent.document).find('#warn_sum').text('');
		}
</script>
	</head>
	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<div id="admin_nr_rightg">
		  <c:if test="${empty(HasTab)}">
			  <div class="canshusz_btbg_1">
					<table width="100%" class="titleBg">
						<tr style="height:20px">
							<td width="30" align="center">
								<img src="manager/images/common/28-01.png" />
							</td>
							<td>
								${title}
							</td>
						</tr>
					</table>
			  </div>
		  </c:if>
          <div id="ListInfo">
            <form action="controlPage_showEquipList.action" method="get" id="myform" name="myform">
				<input name="seq" value="${seq }" type="hidden" id="seq" />
				<input type="hidden" name="mpage" value="${currentgPage}" id="gpage" />
				<input name="eqTypeId" value="${eqTypeId }" type="hidden" id="eqTypeId" />
				<input name="unGrcurrentPage" value="" type="hidden" id="unGrcurrentPage" />
				<input type="hidden" id="HasTab" name="HasTab" value="${HasTab }" />
				<input type='hidden' id="groupName"/>
				<div id="serachDiv" class="serachDiv">
					<table width="800px;" class="serachTable">
						<tr style="height: 50px;" valign="middle">
							<td width="50" align="left">
								组名：
							</td>
							<td width="160">
								<select name="groupId" id="groupId" style="width: 140px;">
									<option value="">
										--请选择组名--
									</option>
									<c:forEach items="${groupList}" var="eqGroup">
										<option value="${eqGroup.seq }" ${groupId==eqGroup.seq?'selected':''}>
											${eqGroup.group_name }
										</option>
									</c:forEach>
								</select>
							</td>
							<td align="center" width="90px">
								<img style='cursor: pointer'
									src="manager/images/imgs/shebeigl_sousuo_an.png"
									onclick="submit()" />
							</td>
							<td>
							  &nbsp;
							</td>
						</tr>
					</table>
				</div>
			</form>
            <div class="itemList2">
				<table width="100%" border="0" cellspacing="1" 
					class="listTable">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<td align="center" width="10%">
								序号
							</td>
							<td align="center" width="40%">
								组名
							</td>
							<td align="center" width="15%">
								包含设备数量
							</td>
							<td align="center" width="35%">
								备注
							</td>
						</tr>
					</thead>
					<tbody id="stu-datas-tb">
						<c:forEach items="${equipGroupList}" var="eqGroup"
							varStatus="idx">
							<tr class="${idx.index%2==0?'tr1':'tr2'}" id="trhref"
								onclick="showMonitor(${eqGroup.seq},${eqGroup.equipType},${eqGroup.buildId},'${eqGroup.groupName}','${eqGroup.groupCode}')">
								<td align="center">
									${eqGroup.index }
								</td>
								<td align="center">
									<a href="javascript:void(0);" class="ahref" 
										onclick="showChild('${eqGroup.seq}','${eqGroup.groupName}')">${eqGroup.groupName}</a>
								</td>
								<td align="center">
									<c:if test="${(eqGroup.equipList)!= null && fn:length(eqGroup.equipList) > 0}">
										${fn:length(eqGroup.equipList)}
								    </c:if>
								</td>
								
							<!-- update start 2013.4.11 by lee  -->
							<!-- .groupRemark 若数据库中为空时，后台查询出为 字符串"null" 而不是 null 现加入 三目运算符   -->
								<td align="center">
									${!empty eqGroup.groupRemark && eqGroup.groupRemark!='null'?eqGroup.groupRemark: ''}
								</td>
							<!-- update end 2013.4.11 by lee -->

<%--							<c:if test="${empty eqGroup.groupRemark}">--%>
<%--								<td align="center">--%>
<%--									""--%>
<%--								</td>--%>
<%--							</c:if>--%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<c:if test="${!empty equipGroupList}">
			  <div class="PageingGroup">
				<!-- 分页从这里开始 -->
				<table width="100%" align="right">
					<tr style="height: 50px;">
						<td align="center">
							<sktag:paginator showTotal="true" showAllPages="true"
								strUnit="条记录" />
						</td>
					</tr>
				</table>
			  </div>
				<!-- 分页在这里结束 -->
			</c:if>
          </div>
		</div>

		<!-- 遮罩层 -->
		<div class="agreesddd"> 
			<label id="isread-text" style=" display: none;"></label>
	    </div>
	    <div style="display:none;"> 
		    <div id="simTestContent" class="simScrollCont"> 
				<iframe src="" id="listItem" scrolling=no width="780" height="285" marginwidth=0 
					marginheight=0 frameborder=0></iframe>
		   </div>
        </div> 
        
        <!-- Control -->
        <div id="control_Info" style=" display: none;">
	        <div style="height: 14px;" title="关闭监控界面">
		       <img src="manager/images/control/closePage.png" alt="关闭监控界面" title="关闭监控界面" style="margin-left: 200px;  cursor: pointer;" onclick="doHideControl();"/>
		    </div>
		    <div style="margin-top: 31px;">
			   <iframe src="" id="Control_page" scrolling=no width="100%" height="1000" marginwidth=0
				   marginheight=0 frameborder=0></iframe>
		    </div>
	    </div>
	</body>
</html>