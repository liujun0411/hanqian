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
				     // var eqTypeId=$(this).attr('eid');
					  $('#rotate>div').addClass('ui-tabs-hide');
					  $('#rotate ul li').removeClass('ui-tabs-selected');
					  $(this).parent().addClass('ui-tabs-selected');
					  $(selhref).removeClass('ui-tabs-hide');
					  $("#typeTitle").text($(this).attr('title'));
					  $("#fragmentSDCDSRC").attr({src : ''});
					  $("#fragmentYeWuKuss").attr({src : ''});
					  if(selhref=='#fragmentYeWuKu'){
					      $("#fragmentYeWuKuss").attr({src : 'controlRed_selectfindDianweiInfoList.action?currentPage=1'});
					   }
					  if(selhref=='#fragmentSDCD'){
						  $("#fragmentSDCDSRC").attr({src : 'controlRed_sDCDfindDiwanweiInfoList.action?currentPage=1'});
					  }					  
					 
					  //判断当前页面是否为监控页面
					   /*    $(window.parent.parent.document).find("#control_warnDiv").hide();
			              $(window.parent.parent.document).find("#warn_sum").text('');   */
				 })		 
	 		 });
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
							      点位统计---<span id="typeTitle">平台</span>
						</td>
					</tr>
				</table>
			</div>
			              <div id="rotate">
				     		     <ul class="ui-tabs-nav">
				                   <li class="ui-tabs-selected"><a reHref="#fragmentYeWuKu"  title="平台" ><span>平台</span></a></li>
				                   <li ><a reHref="#fragmentSDCD" title="SDCD"><span>SDCD</span></a></li>
				      		     </ul>
						         <div  id="fragmentYeWuKu" class="ui-tabs-panel"> 
								    <!-- 业务库列表页面-->
								     <div id="#" style=" width:100%; height:auto; " >
									        <iframe id="fragmentYeWuKuss" src="controlRed_selectfindDianweiInfoList.action?currentPage=1" scrolling=no width="100%" height="700" marginwidth=0
									         marginheight=0 frameborder=0></iframe>
									 </div>
							    </div>
							    <div id="fragmentSDCD" class="ui-tabs-panel ui-tabs-hide"> 
							         <!-- SDCD对应的数据库的数据列表页面 -->
									 <div id="#" style=" width:100%; height:auto; " >
									        <iframe id="fragmentSDCDSRC" src="" scrolling=no width="100%" height="700" marginwidth=0
									         marginheight=0 frameborder=0></iframe>
									</div>
							  </div>
				         </div>
        </div>
			
	</body>
</html>