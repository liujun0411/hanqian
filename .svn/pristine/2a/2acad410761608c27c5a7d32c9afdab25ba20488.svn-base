<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>olapList</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
        <link href="manager/common/tab/ui.tabs.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		body {
			font-family: Lucida Sans, Lucida Sans Unicode, Arial, Sans-Serif;
			font-size: 13px;
			margin: 0px auto;
		}
		
		#content {
			background-color: #F1F1F1;
			width: 100%;
			border-top:1px solid #97A5B0;
		}
		
		#main {
			width: 1158px;
			margin: 0px auto;
			background-color: #F1F1F1;
			margin-top: 0px;
			-moz-border-radius: 10px;
			-webkit-border-radius: 10px;
			height: 950px;;
		}
		
		#wrapper,#doclist {
			float: left;
		}
		
		#doclist {
			width: 150px;
			border-right: solid 1px #dcdcdc;
		}
		
		#doclist ul {
			margin: 0;
			list-style: none;
		}
		
		#doclist li {
			margin: 10px 0;
			padding: 0;
		}
		
		#documents {
			margin: 0;
			padding: 0;
		}
		
		#wrapper {
			width: 100%;
			background-color: #F1F1F1;
		}
		label.normal{
		   margin-left:10px;
		   cursor:pointer;
		   color:red;
		   a:hover {} 
		}
		label.move{
		   cursor:pointer;
		   color:red;
		}
		.test{
		 color:red;
		 background-color: red;
		}
</style>
		<script type="text/javascript" src="manager/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
        $(document).ready(function() {
            //默认显示第一项
            $('#doOper').attr({showid:$(window.parent.document).find(".admin_nr_daoh1").eq(0).find('a').attr('menuId')});
            $('#doOper').attr({showname:$(window.parent.document).find(".admin_nr_daoh1").eq(0).find('a').text()});
            $('#doOper').attr({links:$(window.parent.document).find(".admin_nr_daoh1").eq(0).find('a').attr('hrefs')});
            $('#doOper').click();
            
            $('#ui-tabs-nav a.tab').live('click', function() {
                var contentname = $(this).attr("id") + "_content";
                //隐藏所有选项卡
                $("#content div").hide();
                $("#ui-tabs-nav li").removeClass("ui-tabs-selected");
                // 显示当前选项卡
                $("#" + contentname).show();
                $(this).parent().addClass("ui-tabs-selected");
                $('#doOper').attr({showname:$(this).find('span').text().substring(0,$(this).find('span').text().length-1)});
            });
 
            $('#ui-tabs-nav label.normal').live('click', function(event) {
                if($('#olapTabStatus').val()=="on"){
	                 //选项卡移动
		            var width=$(this).parent().parent().parent().css('width');
		            width=width.substring(0,width.length-2);
	            }
                if($('label.normal').length>1){
	                // 获得选项卡
	                var tabid = $(this).parent().parent().parent().find(".tab").attr("id");
	                // 移除选项卡
	                var contentname = tabid + "_content";
	                $("#" + contentname).remove();
		            var otherObj =  $(this).parent().parent().parent().prev();
		            if(otherObj.length<1){
		           		 otherObj =  $(this).parent().parent().parent().next();
		            }
	              	$(this).parent().parent().parent().remove();
	              	if ($("#ui-tabs-nav li.ui-tabs-selected").length == 0 && $("#ui-tabs-nav li").length > 0) {
	                 	otherObj.find("a.tab").click();
	                }
	             }
	             //如果
	             if($('#olapTabStatus').val()=="on"){
		             var leftSide=$('#pageList').css('left');
	                 leftSide=leftSide.substring(0,leftSide.length-2);
		             if(Math.abs(width)>=leftSide){
		                $('#pageList').animate({left:"20px"});
		             }else if(Math.abs(width)<leftSide){
		                leftSide=leftSide+width;
		                $('#pageList').animate({left:leftSide+"px"});
		             }
		             
		             var length=0;
		             $('#pageList li').each(function(){
		               var t= $(this).css('width');
		               length+=parseInt(t);
		             });
		             if(length<=1024){
		                $('#pagePrev').hide();
	                    $('#pageNext').hide();
		             }
	             }
	             return false;
	         });
        });
        function addTab(id,name,url) {
        	var close_html="<label class='normal' title='关闭'>x</label>";
            // 如果tab存在,不做操作
            if ($("#" + id+"_content").length != 0){
            	var contentname = id + "_content";
                //隐藏所有选项卡
                $("#content div").hide();
                $("#ui-tabs-nav li").removeClass("ui-tabs-selected");
 
                // 显示当前选项卡
                $("#" + contentname).show();
                $('#'+id).parent().addClass("ui-tabs-selected");
                return;
            }
            // 隐藏其他的选项卡
            $("#ui-tabs-nav li").removeClass("ui-tabs-selected");
            $("#content div").hide();
            // 添加一个新的选项卡，并处于选中状态
            $("#ui-tabs-nav").append("<li class='ui-tabs-selected'><a class='tab' id='" +
                id + "' href='javascript:void(0)' style='text-align:left;'><span>"+name+close_html+"</span></a></li>");
 
            $("#content").append("<div id='"+id+"_content' class='showContent'><iframe width='1085' scrolling='no' height='900' frameborder='0' name='workflow' marginheight='0' allowtransparency='true' marginwidth='0' src='"+url+"'></iframe></div>");
            //让新添加的选项卡处于选中状态
            $("#" + id + "_content").show();
            if($('#olapTabStatus').val()=="on"){
	            //如果超过长度，出滚动按钮
	            var length=0;
	            $('#pageList li').each(function(){
	               var t= $(this).css('width');
	               length+=parseInt(t);
	            });
	            if(length>1024){
	               $('#pagePrev').show();
	               $('#pageNext').show();
	               //var leftPos=$('#pageList').css('left');
	           	   //leftPos=leftPos.substring(0,leftPos.length-2);
	               $('#pageList').animate({left:20-(length-1013)+"px"});
	            }
            }
        }
        function doOper(obj){
           addTab($(obj).attr('showid'),$(obj).attr('showname'),$(obj).attr('links'));
        }
        
        function pagePrev(){
           var left=$('#pageList').css('left');
           left=left.substring(0,left.length-2);
           if(parseInt(left)<19){
	           $('#pageList').animate({left:"20px"});
           }
        }
        
        function pageNext(){
            var length=0;
            $('#pageList li').each(function(){
               var t= $(this).css('width');
               length+=parseInt(t);
            });
            if(length>1024){
               $('#pageList').animate({left:20-(length-1013)+"px"});
            }
        }
        
    </script>
	</head>

	<body>
		<div id="main">
			<input type="hidden" id="doOper" name="doOper" showname="" links=""
				showid="" onclick="doOper(this);" />
			<input type="hidden" id="olapTabStatus" name="olapTabStatus" value="${sessionScope.olapTabStatus}" />
			<div id="wrapper">
			    <div id="pagePrev" style=" left:-1px; position: absolute; top:1px;z-index:9999; display: none;"><img alt="" src="manager/images/cursor/tab-left.gif" style="height: 26px; width:26px; cursor: pointer;" onclick="pagePrev()"></div>
			    <div id="pageList" style=" border:0px solid red; width:3000px; height:26px; border-bottom:0px solid #97A5B0; position: relative; float:left; left:20px; overflow:auto;">
					<ul id="ui-tabs-nav" class="ui-tabs-nav" style=" height: 26px;">
	
					</ul>
				</div>
				<div id="pageNext" style=" position: absolute; top:1px;z-index:9999; left:1050px; width:120px; background-color:#F1F1F1;display: none;"><img alt="" src="manager/images/cursor/tab-right.gif" style="height: 26px; width:26px;cursor: pointer;" onclick="pageNext()"></div>
				<!-- 内容部分 -->
				<div id="content" style="border-top:1px solid #97A5B0; margin-top:25px;">

				</div>
			</div>
		</div>
	</body>
</html>
