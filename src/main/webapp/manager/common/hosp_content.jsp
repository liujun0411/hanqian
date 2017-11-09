<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>医院后勤智能化管理平台</title>
		<link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript">
</script>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript">
</script>
<script src="manager/js/jquery.easyui.min.js" type="text/javascript">
</script>
		<script type="text/javascript">
	var today = new Date();
	var y=today.getFullYear();
	var m=today.getMonth()+1;
	var d=today.getDate();	
$(function() {
	$('#menu_icon').mouseover(function() {
		$('#mu_icon').show();
	}).mouseout(function() {
		$('#mu_icon').hide();
	});
	$('#mu_icon')
			.click(
					function() {
						if ($('#mu_icon').attr('src') == "manager/images/control/main_right.gif") {
							$('#td_menu').show(500);
							$('#mu_icon').attr('src',
									'manager/images/control/main_left.gif');
						} else {
							$('#td_menu').hide(500);
							$('#mu_icon').attr('src',
									'manager/images/control/main_right.gif');
						}
					});
					loadScreen();
});
//y,m,d date  y1,m1,d1 otherDate 返回date是否大于otherDate
function judgeDateStr(menuDate,offset){
     menuDate = menuDate.toString();
     menuDate = menuDate.substring(0,menuDate.indexOf(" "));
     var now = new Date();
     var newDate= new Date(Date.parse(menuDate.replace(/-/g,"/"))); 
     var offsetDate = new Date();
     offsetDate.setDate(newDate.getDate() + parseInt(offset));
     //菜单未过期
	 if(offsetDate.getTime()>=now.getTime()){
	     return true;
	 }else{  //菜单已过期
	     return false;
	 }
}

//二级菜单点击事件
function secondaryMenu(id, isclose, menuUrl, editFlag,obj){
    var menuDate = $(obj).attr('menuDate');
	var menuOffset = $('#menuOffset').val();
	var menuTip = $('#menuTip').val();
	if(menuTip=="on"){
		if($.trim(menuDate)!=""){
	       if(judgeDateStr(menuDate,menuOffset)){
	          blocknoneMenu3(id, isclose, menuUrl, editFlag,obj);
	       }else{
	          //alert("【"+$.trim($(obj).text())+"】-功能过期,无法使用!");
	       }
	    }else{
	       blocknoneMenu3(id, isclose, menuUrl, editFlag,obj);
	    }
    }else{
        blocknoneMenu3(id, isclose, menuUrl, editFlag,obj);
    }
}


function blocknoneMenu3(id, isclose, menuUrl, editFlag,obj) {

	//如果是数据分析
	if($(window.parent.document).find("#top").contents().find("a.selected").attr("id")=="ahref_3"){
		if($('#olapTabStatus').val()=="on"){
	        $("#workflow").contents().find('#doOper').attr({showname:$(obj).text()});
	        $("#workflow").contents().find('#doOper').attr({links:$(obj).attr('hrefs')});
	        $("#workflow").contents().find('#doOper').attr({showid:id});
            $("#workflow").contents().find('#doOper').click();
		}else{
		    $('#workflow').attr({src:$(obj).attr('hrefs')});//---------------
		}
        //二级菜单 
		for (i = 0; i < $("div").length; i++) {
			var divClass = $("div").get(i).className;
			var daoh1 = "admin_nr_daoh";
			if (divClass.indexOf(daoh1) > -1) {
				if ($("div").get(i).id.indexOf("div2") > -1) {
					if ($("div").get(i).id == "div2" + id) {
						$("div").get(i).className = "admin_nr_daoh1";
					} else {
						$("div").get(i).className = "admin_nr_daoh2";
					}
				}
	
			}
		}
		//二级菜单字体样式
		for (i = 0; i < $("td").length; i++) {
			if ($("td").get(i).id.indexOf("sTd") != -1) {
				if ($("td").get(i).id == "sTd" + id) {
					$("td").get(i).className = "admin_rdhz_1";
				} else {
					$("td").get(i).className = "admin_rdhz_2";
				}
			}
		}
	}else{
		if (editFlag == "") {
			editFlag = "${fmenuR }";
		}
		var par=$(obj).parent().parent().parent().parent().parent();
		var className=par.next().css('display');
		$('#fenzhan_kj_left .childDiv').hide();
		//判断当前样式是否为展开
		if(par.next().find('.admin_nr_daoh3').length>0){
		   if(className=='block'){
		       par.next().hide();
		   }else{
		       par.next().show();
		       par.next().find('.menu_lines:last-child').hide();
		   }
		}
		//var tempHref = $(obj).attr('hrefs');
	//	if(tempHref.search("lyparam")>-1){
		//	window.open(tempHref,"","");
	//	}else{
	   	 $('#workflow').attr({src:$(obj).attr('hrefs')});
	//	}
		//二级菜单 
		for (i = 0; i < $("div").length; i++) {
			var divClass = $("div").get(i).className;
			var daoh1 = "admin_nr_daoh";
			if (divClass.indexOf(daoh1) > -1) {
				if ($("div").get(i).id.indexOf("div2") > -1) {
					if ($("div").get(i).id == "div2" + id) {
						$("div").get(i).className = "admin_nr_daoh1";
					} else {
						$("div").get(i).className = "admin_nr_daoh2";
					}
				}
	
			}
		}
		//二级菜单字体样式
		for (i = 0; i < $("td").length; i++) {
			if ($("td").get(i).id.indexOf("sTd") != -1) {
				if ($("td").get(i).id == "sTd" + id) {
					$("td").get(i).className = "admin_rdhz_1";
				} else {
					$("td").get(i).className = "admin_rdhz_2";
				}
			}
		}
		//隐藏告警信息
		$(window.parent.document).find('#control_warnDiv').hide();
		$(window.parent.document).find('#warn_sum').text('');
	}
	
	//更新样式
	$(obj).removeClass('');
}

function getWork(menuUrl, editFlag, menuid, pid) {
   
		var fmenuUrl = "${fmenuUrl}";
		var fmenuR = "${fmenuR}";
		var fmenuM = "${fmenuM}";
	
		if (menuUrl == "") {
			menuUrl = "${myfirstU }";
		}
		if (menuid == "") {
			menuid = "${myfirstT }";
		}
		if (pid == "") {
			pid = "${myfirstP }";
		}
		if (editFlag == "") {
			editFlag = "${myfirstR }";
		}
	
		//3级菜单样式
		for (i = 0; i < $("td").length; i++) {
			var divClass = $("td").get(i).className;
			if ($("td").get(i).id.indexOf("aa") > -1) {
				if ($("td").get(i).id == "aa" + pid) {
					$("td").get(i).className = "admin_rdhz_4";
				} else {
					$("td").get(i).className = "admin_rdhz_5";
				}
			}
		}
	
		if (menuUrl.length > 0) {
			if (menuUrl.indexOf("?") != -1) {
				menuUrl = menuUrl + "&editFlag=" + editFlag;
			} else {
				menuUrl = menuUrl + "?editFlag=" + editFlag;
			}
			window.parent.$("#workflow").attr("src", menuUrl);
			return;
		}
	
		if (menuUrl != "") {
			var url = window.parent.workflow.location.href;
			var hospid = "";
			if (pid != "10" && url.indexOf("hospid") != -1) {
				hospid = "&" + url.substring(url.indexOf("hospid"), url.length);
			}
			window.parent.$("#workflow").attr("src", menuUrl + hospid);
	
			return;
		}
	
		if (fmenuUrl.length > 0 && menuUrl.length <= 0) {
			blocknoneMenu3(fmenuM, true, fmenuUrl, fmenuR);
			return;
		} else {
			blocknoneMenu3(menuid, false);
		}
	
}

//默认展示
function getWorkStation(){
    //循环遍历所有菜单
    var menuOffset = $('#menuOffset').val();
    var menuTip = $('#menuTip').val(); 
    if(menuTip=="on"){
	    $('#fenzhan_kj_left div').each(function(){
	       if($(this).hasClass('admin_nr_daoh1')){
	           var obj = this;
	           $(obj).find('.topMenus').each(function(){
	                if(judgeDateStr($(this).attr('menudate'),menuOffset)){
	                  
	                }else{
	                   $(obj).next().remove();
	                   $(obj).remove();
	                }
	           });
	       }
	    });
	    $('#fenzhan_kj_left div').each(function(){
	       //二级菜单
	       if($(this).hasClass('admin_nr_daoh2')){
	           var obj = this;
	           $(obj).find('.topMenus').each(function(){
	                if(judgeDateStr($(this).attr('menudate'),menuOffset)){
	                  
	                }else{
	                   $(obj).next().remove();
	                   $(obj).remove();
	                }
	           });
	       }
	       //三级菜单
	       if($(this).hasClass('childDiv')){
	           var obj = this;
	           $(obj).find('.admin_nr_daoh3').each(function(){
	                var threeObj = this;
	                $(threeObj).find('.biao_lianj_2').each(function(){
		                if(!judgeDateStr($(this).attr('menudate'),menuOffset)){
		                   $(threeObj).remove();
		                   if($(obj).find('.admin_nr_daoh3').length<2 && $(obj).find('.admin_nr_daoh3').length>=1){
		                      $(obj).find('.menu_lines').hide();
		                   }else if($(obj).find('.admin_nr_daoh3').length<1){
		                      $(obj).remove();
		                   }
		                }
	                });
	           });
	        }
	    });
    }
    //如果设备类型Id不为空
    var childTypeId=$("#myOper",parent.document.body).attr('childTypeId');
    if($.trim($('#eqTypeId').val())!=""){
       $('#fenzhan_kj_left a').each(function(){
          var types=$(this).attr('hrefs');
          var actionName=types.substring(0,types.indexOf('_')); 
          types=types.substring(types.lastIndexOf('=')+1); 
          if(types==$('#eqTypeId').val() && (actionName == 'controlPage' || actionName == 'control')){
             $(this).click();
             var menuDate = $(this).attr('menuDate');
             var menuOffset = $('#menuOffset').val();
             if(menuTip=="on"){
				 if($.trim(menuDate)!=""){
			        if(judgeDateStr(menuDate,menuOffset)){
			          $(this).attr({"hrefs":$(this).attr('hrefs')+"&childTypeId="+childTypeId});
	                  $('#workflow').attr({src:$(this).attr('hrefs')});
			        }else{
			           //alert("【"+$.trim($(this).text())+"】-功能过期,无法使用!");
			        }
			     }else{
			        $(this).attr({"hrefs":$(this).attr('hrefs')+"&childTypeId="+childTypeId});
	                $('#workflow').attr({src:$(this).attr('hrefs')});
			     }
		     }else{
		         $(this).attr({"hrefs":$(this).attr('hrefs')+"&childTypeId="+childTypeId});
	             $('#workflow').attr({src:$(this).attr('hrefs')});
		     }
             
          }
       });
    }else{
    $('#fenzhan_kj_left div').removeClass('admin_rdhz_1');
        var menuDate = $('.admin_rdhz_1 a').eq(0).attr('menuDate');
        var menuOffset = $('#menuOffset').val();
        if(menuTip=="on"){
	        if($.trim(menuDate)!=""){
		        if(judgeDateStr(menuDate,menuOffset)){
		           loadMenuInfo();
		        }else{
		            if($('#fenzhan_kj_left .childDiv').eq(0).find('.admin_nr_daoh3').length<1){
		                 $('#fenzhan_kj_left .childDiv').eq(0).hide();
		            }
		            //alert("【"+$.trim($('.admin_rdhz_1 a').eq(0).text())+"】-功能过期,无法使用!");
		        }
		     }else{
		          loadMenuInfo();
		     }
	     }else{
	        loadMenuInfo();
	     }
    }
}


function loadMenuInfo(){
		if($('#fenzhan_kj_left div').eq(0).attr('class')=='admin_nr_daoh2'){
		    $('#fenzhan_kj_left div').eq(0).removeClass('admin_nr_daoh2');
		    $('#fenzhan_kj_left div').eq(0).addClass('admin_nr_daoh1');
		    $('#fenzhan_kj_left div').eq(0).find('table td:last-child').attr('class','');
		    $('#fenzhan_kj_left div').eq(0).find('table td:last-child').addClass('admin_rdhz_1');
		    //$('#fenzhan_kj_right iframe').attr({src:$('.admin_rdhz_1 a').eq(0).attr('href')});
		}
		if($('#olapTabStatus').val()=="on"){
			var menuDate = $('.admin_rdhz_1 a').eq(0).attr('menuDate');
		    if($('#fenzhan_kj_left div').eq(0).attr('class')=='admin_nr_daoh1'){
			   if($(window.parent.document).find("#top").contents().find("a.selected").attr("id")=="ahref_3"){
			      $('#workflow').attr({src:'manager/olapTabList.jsp'});
			   }else{
		       	  $('#workflow').attr({src:$('.admin_rdhz_1 a').eq(0).attr('hrefs')});//------------
		       }
		   }
		}else{
		   $('#workflow').attr({src:$('.admin_rdhz_1 a').eq(0).attr('hrefs')});//-----------------------
		}
		
	   //初始化加载样式
	   $('#fenzhan_kj_left .admin_nr_daoh3 td img').eq(0).attr({src:'manager/images/common/25-01.png'});
	   $('#fenzhan_kj_left .admin_nr_daoh3 .admin_rdhz_5').eq(0).addClass('admin_rdhz_6');
	   //消除菜单的line
	   $('#fenzhan_kj_left .childDiv').eq(0).find('.menu_lines:last-child').hide();
	   //隐藏空DIV
	   if($('#fenzhan_kj_left .childDiv').eq(0).find('.admin_nr_daoh3').length<1){
	     $('#fenzhan_kj_left .childDiv').eq(0).hide();
	   }
}

//三级菜单点击事件
function toTarget(obj){
	//menuDate
	var menuDate = $(obj).attr('menuDate');
	var menuOffset = $('#menuOffset').val();
	var menuTip = $('#menuTip').val(); 
	if(menuTip=="on"){
		if($.trim(menuDate)!=""){
	       if(judgeDateStr(menuDate,menuOffset)){
	          $('.admin_nr_daoh3 td').removeClass('admin_rdhz_6');
		      $('.admin_nr_daoh3 td').addClass('admin_rdhz_5');
		      $('.admin_rdhz_5 > img').attr({src:'manager/images/common/26-01.png'});
		      $(obj).parent().parent().find('img').attr({src:'manager/images/common/25-01.png'});
		      $(obj).parent().addClass('admin_rdhz_6');
	          $('#workflow').attr({src:$(obj).attr('hrefs')});
	       }else{
	          alert("【"+$.trim($(obj).text())+"】-功能过期,无法使用!");
	       }
	    }else{
	      $('.admin_nr_daoh3 td').removeClass('admin_rdhz_6');
	      $('.admin_nr_daoh3 td').addClass('admin_rdhz_5');
	      $('.admin_rdhz_5 > img').attr({src:'manager/images/common/26-01.png'});
	      $(obj).parent().parent().find('img').attr({src:'manager/images/common/25-01.png'});
	      $(obj).parent().addClass('admin_rdhz_6');
	      $('#workflow').attr({src:$(obj).attr('hrefs')});
	    }
    }else{
          $('.admin_nr_daoh3 td').removeClass('admin_rdhz_6');
	      $('.admin_nr_daoh3 td').addClass('admin_rdhz_5');
	      $('.admin_rdhz_5 > img').attr({src:'manager/images/common/26-01.png'});
	      $(obj).parent().parent().find('img').attr({src:'manager/images/common/25-01.png'});
	      $(obj).parent().addClass('admin_rdhz_6');
	      $('#workflow').attr({src:$(obj).attr('hrefs')});
    }
   //------------------------------------------
}

function toAction(url) {

}
window.onresize = function(){  
    loadScreen();  
}  
window.onscroll = function(){  
    loadScreen();    
}  
function loadScreen(){
    var screenWidth=screen.width;
    if(screenWidth<=1366){
       $('#td_menu').removeClass('tdMenus_1440').addClass('tdMenus_1366');
       $('#fenzhan_kj_left').removeClass('main_left_workbench_1440').addClass('main_left_workbench_1366');
    }
    if(screenWidth>1400){
      $('#td_menu').removeClass('tdMenus_1366').addClass('tdMenus_1440');
      $('#fenzhan_kj_left').removeClass('main_left_workbench_1366').addClass('main_left_workbench_1440');
    }
}
</script>
	</head>
	<body bgColor="#F1F1F1" onload="getWorkStation();">
		<input type="hidden" id="hi_link" name="hi_link" value="aaaaaaa" />
		<input type="hidden" id="olapTabStatus" name="olapTabStatus" value="${sessionScope.olapTabStatus}" />
		<input type="hidden" id="menuOffset" name="menuOffset" value="${sessionScope.menuOffset}" />
		<input type="hidden" id="menuTip" name="menuTip" value="${sessionScope.menuTip}" />
		<input type="hidden" id="eqTypeId" name="eqTypeId" value="${eqTypeId }" />
		<input type="hidden" id="reloadCss" name="reloadCss" onclick="loadScreen();" />
		<table style="width: 100%;">
			<tr>
				<td id="td_menu" name="td_menu">
					<!--左边导航 -->
					<div name="kjleft" id="fenzhan_kj_left">
								<c:forEach items="${menuList}" var="menu" varStatus="index">
								<!-- 2级菜单 -->
								<c:if test="${ menu.status==0 && (!empty menuIdMap[menu.menuCode]) }">
									<div class="admin_nr_daoh${index.index==0?'1':'2'}"
										id="div2${menu.menuId}" >
										<table width="100%" border="0" align="right">
											<tr style="height: 28px">
												<td width="5">
												  
												</td>
												<td id="sTd${menu.menuId}" valign="middle"
													class="admin_rdhz_${index.index==0?'1':'2'}">
													<c:if test="${!empty sessionScope.olapTabStatus}">
													  <c:if test="${sessionScope.olapTabStatus=='on'}">
													     <a style="cursor: pointer" class="topMenus"
															onclick="secondaryMenu('${menu.menuId}',true,'',1,this);"
															menuId="${menu.menuId}"
															hrefs="${!empty menu.menuUrl && menu.menuUrl!='' ?menu.menuUrl:'javascript:void(0);'}"  menuDate="${menu.dueDate }">
															${menu.name}</a>
													  </c:if>
													  <c:if test="${sessionScope.olapTabStatus=='off'}">
													     <a menuId="${menu.menuId}" style="cursor: pointer" class="topMenus"
																		onclick="secondaryMenu('${menu.menuId}',true,'',1,this);" target="workflow" hrefs="${!empty menu.menuUrl && menu.menuUrl!='' ?menu.menuUrl:'javascript:void(0);'}"  menuDate="${menu.dueDate }">
																		${menu.name}</a>
													  </c:if>
													</c:if>
												</td>
											</tr>
										</table>
									</div>
                                    <div class="childDiv" style=" margin-bottom:3px; width:129px; ${index.index==0?'display:block':'display:none'}; z-index:9999;"><b class="b1"></b><b class="b2 d3"></b><b class="b3 d3"></b><b class="b4 d3"></b><div class="b d3 k">
										<c:forEach items="${menu.dbMenuses}" var="menu2" varStatus="index2">
												<c:if test="${ menu2.status==0 && !empty menuIdMap[menu2.menuCode] }">
													<div id="msdtree${menu.menuId}" class="admin_nr_daoh3">
														<table width="100%" border="0">
															<tr>
																<td width="20" align="center"><img src="manager/images/common/26-01.png" /></td>
																<td id="aa${menu.menuId}" class="admin_rdhz_5">
																	<a class="biao_lianj_2" style="cursor: pointer"
																		onclick="toTarget(this);" target="workflow" hrefs="${menu2.menuUrl}" menuDate="${menu2.dueDate }">
																		${menu2.name}</a>
																</td>
															</tr>
														</table>
													</div>
													<div class="menu_lines"></div>
												</c:if>
										</c:forEach>
									</div><b class="b4b d3"></b><b class="b3b d3"></b><b class="b2b d3"></b><b class="b1b"></b></div>
								</c:if>
							</c:forEach>
						</div>
				</td>

			<!--右侧主页面显示内容-->
			<td width="1250" style=" vertical-align: top; padding-top:6px; ">
				<div id="fenzhan_kj_right" style=" z-index:9999; overflow: hidden;">
					<iframe src="" scrolling="no" width="1160" height="1000";
						marginwidth="0" allowTransparency="true" marginheight="0"
						frameborder="0" name="workflow"  id="workflow"></iframe>
				</div>
			</td>
			</tr>
		</table>
	</body>
</html>
