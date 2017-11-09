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
		<meta http-equiv="Refresh" content="30"/>
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet" type="text/css" />
		<style type="text/css"> 
			*{margin:0;padding:0;list-style-type:none;}
			a,img{border:0;}
			body{font-size:0.8em;letter-spacing:1px;font-family:"微软雅黑";line-height:1.8em;}
			h1{font-size:1em;font-weight:normal;}
			h1 a{background:#CFF;padding:2px 3px;text-decoration:none;}
			h1 a:hover{background:#eee;text-decoration:underline;}
			h2,h3{font-size:1em;font-weight:normal;color:#a40000;margin:1em auto;position:relative;}
			h3{color:#888;font-weight:bold;}
			/* ibox */
			.ibox{width:588px;height:180px;position:relative;overflow:hidden; margin-left: 57px; margin-top: 10px; }
			.ibox .showbox{position:absolute;left:0;width:2550px;height:140px; margin-top: 29px;}
			.ibox .showbox ul li{float:left;margin:7px;display:inline; }
			.ibox .showbox ul li img{border:1px solid #999999; cursor: pointer;}
			.ibox .showbox ul li a{display:block;width:156px;height:156px;overflow:hidden;}
			.ibox .showbox ul li a img{border:3px solid #a40000;}
			.ibox .showbox ul li a:hover img{border:solid 3px #3366cc;}
			.ibox .btnbox{height:34px;overflow:hidden;float:right;}
			.ibox span{padding:2px 5px;background:#fff;cursor:pointer;float:left;margin:0 5px;display:inline;height:20px;line-height:20px;}
			.ibox span.num{background:#ddd;line-height:18px;}
			.ibox span.num li{float:left;display:inline;margin:0 5px;padding:1px 5px;line-height:18px;}
			.ibox span.num .numcur{background:#a40000;color:#fff;}
		</style>
		<script type="text/javascript">
		  $(function(){
			    var $cur = 1;//初始化显示的版面
			    var $i = 3;//每版显示数
			    var $len = $('.showbox>ul>li').length;//计算列表总长度(个数)
			    var $pages = Math.ceil($len / $i);//计算展示版面数量
			    var $w = $('.ibox').width();
			    var $showbox = $('.showbox');
			    var $num = $('span.num li')
			    var $pre = $('div.pre')
			    var $next = $('div.next');
			    var $autoFun;
			    //autoSlide();
			    $pre.click(function(){
			        if (!$showbox.is(':animated')) {  
			            if ($cur == 1) {   
			                $showbox.animate({
			                    left: '-=' + $w * ($pages - 1)
			                }, 1000); 
			                $cur = $pages; 
			            }
			            else {
			                $showbox.animate({
			                    left: '+=' + $w
			                }, 1000); 
			                $cur--; 
			            }
			            $num.eq($cur - 1).addClass('numcur').siblings().removeClass('numcur'); //为对应的版面数字加上高亮样式,并移除同级元素的高亮样式
			        }
			    });
			    $next.click(function(){
			        if (!$showbox.is(':animated')) { 
			            if ($cur == $pages) { 
			                $showbox.animate({
			                    left: 0
			                }, 1000); 
			                $cur = 1; 
			            }
			            else {
			                $showbox.animate({
			                    left: '-=' + $w
			                }, 1000);
			                $cur++; 
			            }
			            $num.eq($cur - 1).addClass('numcur').siblings().removeClass('numcur'); //为对应的版面数字加上高亮样式,并移除同级元素的高亮样式
			        }
			    });
			    clearFun($showbox);
			    clearFun($pre);
			    clearFun($next);
			    function clearFun(elem){
			        elem.hover(function(){
			            clearAuto();
			        });
			    }
				/**
				,  function(){
			            autoSlide();
			        }
				*/
			    function autoSlide(){
			        $next.trigger('click');
			        $autoFun = setTimeout(autoSlide, 5000);//此处不可使用setInterval,setInterval是重复执行传入函数,这会引起第二次划入时停止失效
			    }
			    function clearAuto(){
			        clearTimeout($autoFun);
			    }
			    
			    $('#btnPrev img').mouseover(function(){
			       $(this).attr({src:"manager/images/common/left_hover.png"});
			    }).mouseout(function(){
			       $(this).attr({src:"manager/images/common/left_page.png"});
			    });
			    $('#btnNext img').mouseover(function(){
			       $(this).attr({src:"manager/images/common/right_hover.png"});
			    }).mouseout(function(){
			       $(this).attr({src:"manager/images/common/right_page.png"});
			    });
			    
			});
			
			function showBigPic(obj){
			  $(window.parent.document).find("#imgPath").attr({showTitle:$(obj).attr('alt')});
			  $(window.parent.document).find("#imgPath").val($(obj).attr('src'));
			  $(window.parent.document).find("#imgPath").click();
			}
			
			function divIN(obj){
				$(window.parent.document).find('#zhaq').css({"left":$(obj).attr('marleft')});
				$(window.parent.document).find('#zhaq').css({"top":$(obj).attr('martop')});
				$(window.parent.document).find('#ptitleDiv').html($(obj).attr('tishi'));
				$(window.parent.document).find('#zhaq').show();
			}
			
			function divOut(obj){
				$(window.parent.document).find('#zhaq').hide();
			}
		</script>
		
	</head>

	<body id="mainDiv" style="overflow: scroll; overflow-x: hidden; overflow-y: hidden; background-color: white; ">
		<div id="admin_nr_rightg" style=" background: url(manager/images/common/lan_bg.png) no-repeat; margin-left: 50px; height: 210px; border:1px solid WHITE; margin-top: 10px;" >
		   <div id="btnPrev" class="pre" style=" position: absolute; margin-left: 32px; margin-top: 100px;">
                <img src="manager/images/common/left_page.png" alt="" style=" cursor: pointer; <c:if test='${ empty resultList}'> display:none;</c:if>" />  
           </div>
           <div id="btnNext" class="next" style=" position: absolute; margin-left: 655px; margin-top: 100px;">
                <img src="manager/images/common/right_page.png" alt="" style=" cursor: pointer; <c:if test='${ empty resultList}'> display:none;</c:if>"  />  
           </div>
		   <div class="ibox" onmouseover="divIN(this)" onmouseout="divOut(this)" tishi="<p>变压器电流峰值(综合电流)是指变压器在某一时刻的三相电流综合变化趋势。</p> 
<p>综合电流展示每个变压器的三相电流（即：A、B、C三相）的数值之和，在某一段时间内的变化趋势。时间段是最近一小时，每五分钟的变化趋势。 </p>
<p>红色区域为危险，三相电流数值之和≥变压器的额定电流*90%;</p>
<p>橙色区域为警告，三相电流数值之和≥变压器的额定电流*80%;</p>
<p>灰色区域为安全，三相电流数值之和＜变压器的额定电流*80%;</p>
	
<p>变压器电流峰值（三相电流）是指变压器在某一时刻的三相电流的变化趋势。 </p>
<p>三相电流展示每个变压器的三相电流（即：A、B、C三相）在某一段时间内的变化趋势。时间段是最近一小时，每五分钟的变化趋势。</P>" martop="450px" marleft="600px">
        		<div style="clear:both;height:0;overflow:hidden;"></div>
		        <div class="showbox">
		            <c:if test="${! empty resultList}">
			            <ul>
				            <c:forEach items="${resultList}" var="res">
				               <li title="${res.title}"><img width="180" height="120"  src="data:image/png;base64,${res.path}" alt="${res.title}" onclick="showBigPic(this);" /></li>
				            </c:forEach>
			            </ul>
		            </c:if>
		            <c:if test="${ empty resultList}">
		                <span style=" margin-top:20px; color:red; cursor:default;">暂未获取到数据</span>
		            </c:if>
		        </div>
    		</div>
	    </div>
	</body>
</html>