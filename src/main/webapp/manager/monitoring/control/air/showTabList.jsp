<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
		<link rel="stylesheet" href="manager/common/tab/ui.tabs.css" type="text/css"></link>
	
		<script type="text/javascript">
			 $(function(){
			     //动态获得标题
			     $('#typeTitle').text($('.ui-tabs-selected').find('a').attr('title'));
			 
				 $(".ui-tabs-nav li a").click(function(){
				      var selhref=$(this).attr('reHref');	
				      var eqTypeId=$(this).attr('eid');
					  $('#rotate>div').addClass('ui-tabs-hide');
					  $('#rotate ul li').removeClass('ui-tabs-selected');
					  $(this).parent().addClass('ui-tabs-selected');
					  $(selhref).removeClass('ui-tabs-hide');
					  $("#typeTitle").text($(this).attr('title'));
					  //判断 是否为新选项卡
					  if(jQuery.trim($("#"+eqTypeId+"_Frm").attr('src'))==""){
					      if(eqTypeId=="1004" || eqTypeId=="1005"){
					        $("#"+eqTypeId+"_Frm").attr({src:"controlPage_showEquipList.action?eqTypeId="+eqTypeId+"&HasTab=1"});
					      }else{
					        $("#"+eqTypeId+"_Frm").attr({src:"controlPage_showEquipList.action?eqTypeId="+eqTypeId});
					      }
					  }
					  //判断当前页面是否为监控页面
					  if($("#commonShow_"+eqTypeId).css('display')=="none"){//不是监控界面
					      $(window.parent.parent.document).find("#control_warnDiv").hide();
			              $(window.parent.parent.document).find("#warn_sum").text('');  
					  }else{
					      //显示告警数量
					      if($("#controlPage_"+eqTypeId).contents().find("#alarmCount").val()>0){
						      $(window.parent.parent.document).find("#control_warnDiv").hide();
				              $(window.parent.parent.document).find("#warn_sum").text($("#controlPage_"+eqTypeId).contents().find("#alarmCount").val());
				              $(window.parent.parent.document).find("#control_warnDiv").show(); 
			              }
					  }
				 })		 
	 		 });
	 		 
	 		 function doHideControl(obj){
	 		   $(obj).parent().parent().slideToggle(600);
	 		   $(obj).parent().parent().prev().slideToggle(600);
	 		   $(window.parent.parent.document).find("#control_warnDiv").hide();
			   $(window.parent.parent.document).find("#warn_sum").text('');
	 		 }
      
	</script>
	</head>
	
	<body style="overflow-x:hidden;overflow-y:hidden;">
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
			    <table border="0" class="titleBg">
					<tr style="height:20px">
						<td width="30" align="center">
							<img src="manager/images/common/28-01.png" />
						</td>
						<td>
							<c:if test="${eqTypeId==1}"> 
							      空调系统---<span id="typeTitle">新风机</span>
							</c:if>
							<c:if test="${eqTypeId==10}">
								<c:if test="${falg != 1 && falg != 2}">
							    		能源计量---<span id="typeTitle">电表</span>
							    </c:if>
							    <c:if test="${falg == 1}">
									             电力安全---<span id="typeTitle">电力安全</span>
								</c:if>
								<c:if test="${falg == 2}">
							      		太平间监控---<span id="typeTitle">太平间监控</span>
								</c:if>
							</c:if>
							<c:if test="${eqTypeId==12}">
							     天然气系统---<span id="typeTitle">天然气</span>
							</c:if>
							<c:if test="${eqTypeId==13}">
							     <span id="typeTitle">第三方点位</span>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
			  <c:if test="${!empty menuTabList}">
			    <c:if test="${eqTypeId==1}"> 
			      <div id="rotate">
				      <ul class="ui-tabs-nav">
				        <%boolean bool=true;%>
						  <c:forEach items="${menuTabList}" var="menus" varStatus="i">
						      <c:if test="${!empty menuIdMap}">
						         <c:if test="${!empty menuIdMap[menus.menu_code] }">
						           <c:if test="${ !empty childTypeId}">
						               <li <c:if test="${menus.eqtypeid==childTypeId}"> class="ui-tabs-selected" </c:if>><a reHref="#fragment-${i.index+1}" eid="${menus.eqtypeid }" title="${menus.name }"><span>${menus.name }</span></a></li>
						           </c:if>
						           <c:if test="${ empty childTypeId}">
						              <li <%if(bool){%><c:if  test="${menuIdMap[menus.menu_code]==menus.menu_code}" ><% bool=false;%>class="ui-tabs-selected" </c:if><%} %>><a reHref="#fragment-${i.index+1}" eid="${menus.eqtypeid }" title="${menus.name }"><span>${menus.name }</span></a></li>
						           </c:if>
						         </c:if>
						      </c:if>
						  </c:forEach>
				      </ul>
				       <%boolean bool2=true;%>
				      <c:forEach items="${menuTabList}" var="menus" varStatus="i">
				        <c:if test="${!empty menuIdMap}">
						         <c:if test="${!empty menuIdMap[menus.menu_code] }">
				           <c:if test="${ !empty childTypeId}">
						       <div id="fragment-${i.index+1 }" <c:if test="${menus.eqtypeid==childTypeId}">class="ui-tabs-panel"</c:if> <c:if test="${menus.eqtypeid!=childTypeId }">class="ui-tabs-panel ui-tabs-hide"</c:if>> 
								    <!-- 列表 -->
								    <div id="equipList_${menus.eqtypeid }" style=" width:100%; height:auto; " >
									    <iframe <c:if test="${menus.eqtypeid==childTypeId }">src="controlPage_showEquipList.action?eqTypeId=${menus.eqtypeid }&HasTab=1"</c:if> <c:if test="${menus.eqtypeid!=childTypeId }">src=""</c:if>  id="${menus.eqtypeid }_Frm" scrolling=no width="100%" height="700" marginwidth=0
									         marginheight=0 frameborder=0></iframe>
									</div>
									<div id="commonShow_${menus.eqtypeid }" style=" width:100%; height:auto; display: none;" >
									    <div style="height: 15px;">
									      <img src="manager/images/control/closePage.png" alt="关闭监控界面" title="关闭监控界面" style="margin-left: 200px;  cursor: pointer;" onclick="doHideControl(this);"/>
									    </div>
									    <!-- 监控页面备注信息 -->
									    <iframe src="" id="controlDescript_${menus.eqtypeid }" scrolling=no width="99%" height="0" marginwidth=0
											marginheight=0 frameborder=0></iframe>
									    <!-- 监控页面 -->
										<iframe src="" id="controlPage_${menus.eqtypeid }" scrolling=no width="99%" height="900" marginwidth=0
										marginheight=0 frameborder=0></iframe>
									</div>
							  </div>
						  </c:if>
						  <c:if test="${ empty childTypeId}">
							  <div id="fragment-${i.index+1 }" <c:if test="${menuIdMap[menus.menu_code]==menus.menu_code}">class="ui-tabs-panel"</c:if> <c:if test="${i.index>0 }">class="ui-tabs-panel ui-tabs-hide"</c:if>> 
								   <div id="equipList_${menus.eqtypeid }" style=" width:100%; height:auto; " >
								    <!-- 列表 -->
									    <iframe <% if(bool2){  %> 
									    <c:if test="${menuIdMap[menus.menu_code]==menus.menu_code }"><% bool2=false; %>
										     src="controlPage_showEquipList.action?eqTypeId=${menus.eqtypeid }&HasTab=1"
									    </c:if>
									    <%} %>  <c:if test="${i.index>0 }">src=""</c:if> id="${menus.eqtypeid }_Frm" scrolling=no width="100%" height="700" marginwidth=0
									         marginheight=0 frameborder=0></iframe>
									</div>
									<div id="commonShow_${menus.eqtypeid }" style=" width:100%; height:auto; display: none;" >
									    <div style="height: 15px;">
									      <img src="manager/images/control/closePage.png" alt="关闭监控界面" title="关闭监控界面" style="margin-left: 200px;  cursor: pointer;" onclick="doHideControl(this);"/>
									    </div>
									    <!-- 监控页面备注信息 -->
									    <iframe src="" id="controlDescript_${menus.eqtypeid }" scrolling=no width="99%" height="0" marginwidth=0
											marginheight=0 frameborder=0></iframe>
									    <!-- 监控页面 -->
										<iframe src="" id="controlPage_${menus.eqtypeid }" scrolling=no width="99%" height="900" marginwidth=0
										marginheight=0 frameborder=0></iframe>
									</div>
							  </div>
						  </c:if>
						  </c:if></c:if>
					  </c:forEach>
				  </div>
			    </c:if>
			    <c:if test="${eqTypeId==10||eqTypeId==12}"> 
			       <div id="rotate">
				      <ul class="ui-tabs-nav">
				      <% boolean bool3=true;%>
						  <c:forEach items="${menuTabList}" var="menus" varStatus="i">
						    <c:if test="${!empty menuIdMap}">
						    <c:if test="${!empty menuIdMap[menus.menu_code] }">
						  <c:if test="${sessionScope.currentHospCode!='RUJ'}">
						      <c:if test="${ !empty childTypeId}">
						      	<li <c:if test="${menus.eqtypeid==childTypeId}"> class="ui-tabs-selected" </c:if>><a reHref="#fragment-${i.index+1}" eid="${menus.eqtypeid }" title="${menus.name }"><span>${menus.name }</span></a></li>
						      </c:if>
						      <c:if test="${ empty childTypeId}">
						      	<!-- 控制能源计量中电表和水表选项卡 ‘不等于1、2就显示 等于1、2就不显示’-->
						      	  <c:choose>
						      	  	<c:when test="${falg==1 || falg==2}">
						      	  	</c:when>
						      	  	<c:otherwise>
						      	  		<li <% if(bool3){%><c:if test="${menuIdMap[menus.menu_code]==menus.menu_code}"><% bool3=false; %> class="ui-tabs-selected" </c:if><%} %>><a reHref="#fragment-${i.index+1}" eid="${menus.eqtypeid }" title="${menus.name }"><span>${menus.name }</span></a></li>
						      	  	</c:otherwise>
						      	  </c:choose>
						      </c:if>
						      
						   </c:if>
						  	<c:if test="${menus.eqtypeid != 10002 && sessionScope.currentHospCode=='RUJ'}">
						      <c:if test="${ !empty childTypeId}">
						      	<li <c:if test="${menus.eqtypeid==childTypeId}"> class="ui-tabs-selected" </c:if>><a reHref="#fragment-${i.index+1}" eid="${menus.eqtypeid }" title="${menus.name }"><span>${menus.name }</span></a></li>
						      </c:if>
						      <c:if test="${ empty childTypeId}">
						        <li <c:if test="${i.index==0}"> class="ui-tabs-selected" </c:if>><a reHref="#fragment-${i.index+1}" eid="${menus.eqtypeid }" title="${menus.name }"><span>${menus.name }</span></a></li>
						      </c:if> 
						      </c:if>
						      </c:if> 
						      </c:if>
						  </c:forEach>
				      </ul>
				      <% boolean bool2=true; %>
				      <c:forEach items="${menuTabList}" var="menus" varStatus="i">
				       <c:if test="${!empty menuIdMap}">
						  <c:if test="${!empty menuIdMap[menus.menu_code] }">
				      	   <c:if test="${ !empty childTypeId}">
						       <div id="fragment-${i.index+1 }" <c:if test="${menus.eqtypeid==childTypeId}">class="ui-tabs-panel"</c:if> <c:if test="${menus.eqtypeid!=childTypeId }">class="ui-tabs-panel ui-tabs-hide"</c:if>> 
								    <!-- 列表 -->
								    <div id="equipList_${menus.eqtypeid }" style=" width:100%; height:auto; " >
									    <iframe <c:if test="${menus.eqtypeid==childTypeId }">src="controlPage_showEquipList.action?eqTypeId=${menus.eqtypeid }"</c:if> <c:if test="${menus.eqtypeid!=childTypeId }">src=""</c:if>  id="${menus.eqtypeid }_Frm" scrolling=no width="100%" height="700" marginwidth=0
									         marginheight=0 frameborder=0></iframe>
									</div>
									<div id="commonShow_${menus.eqtypeid }" style=" width:100%; height:auto; display: none;" >
									    <div style="height: 15px;">
									      <img src="manager/images/control/closePage.png" alt="关闭监控界面" title="关闭监控界面" style="margin-left: 200px;  cursor: pointer;" onclick="doHideControl(this);"/>
									    </div>
									    <!-- 监控页面备注信息 -->
									    <iframe src="" id="controlDescript_${menus.eqtypeid }" scrolling=no width="99%" height="230" marginwidth=0
											marginheight=0 frameborder=0></iframe>
									    <!-- 监控页面 -->
										<iframe src="" id="controlPage_${menus.eqtypeid }" scrolling=no width="99%" height="800" marginwidth=0
										marginheight=0 frameborder=0></iframe>
									</div>
							  </div>
						  </c:if>
						  <c:if test="${ empty childTypeId}">
						       <div id="fragment-${i.index+1 }" <c:if test="${menuIdMap[menus.menu_code]==menus.menu_code }">class="ui-tabs-panel"</c:if> <c:if test="${i.index>0 }">class="ui-tabs-panel ui-tabs-hide"</c:if>> 
								    <!-- 列表 -->
								    <div id="equipList_${menus.eqtypeid }" style=" width:100%; height:auto; " >
										<iframe <%if(bool2){%><c:if test="${menuIdMap[menus.menu_code]==menus.menu_code }"> <% bool2=false; %> src="controlPage_showEquipList.action?eqTypeId=${menus.eqtypeid }&falg=${falg }"</c:if><%} %>  <c:if test="${i.index>0 }">src=""</c:if> id="${menus.eqtypeid}_Frm" scrolling=no width="100%" height="700" marginwidth=0
									         marginheight=0 frameborder=0></iframe>
									</div>
									<div id="commonShow_${menus.eqtypeid }" style=" width:100%; height:auto; display: none;" >
									    <div style="height: 15px;">
									      <img src="manager/images/control/closePage.png" alt="关闭监控界面" title="关闭监控界面" style="margin-left: 200px;  cursor: pointer;" onclick="doHideControl(this);"/>
									    </div>
									    <!-- 监控页面备注信息 -->
									    <iframe src="" id="controlDescript_${menus.eqtypeid }" scrolling=no width="99%" height="230" marginwidth=0
											marginheight=0 frameborder=0></iframe>
									    <!-- 监控页面 -->
										<iframe src="" id="controlPage_${menus.eqtypeid }" scrolling=no width="99%" height="800" marginwidth=0
										marginheight=0 frameborder=0></iframe>
									</div>
							  </div>
						  </c:if>
						 </c:if>
						</c:if>
					  </c:forEach>
				   </div>
			    </c:if>
			  </c:if>
			  <!-- 第三方点位信息查询 -->
			  <c:if test="${!empty threePoint}">
			     <iframe src="controlPage_threePoint.action" id="" scrolling=no width="99%" height="400" marginwidth=0
											marginheight=0 frameborder=0></iframe>
			  </c:if>
        </div>
	</body>
</html>