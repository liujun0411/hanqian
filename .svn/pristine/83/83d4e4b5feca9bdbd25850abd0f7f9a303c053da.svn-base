<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
<script type="text/javascript" src="manager/js/util.js"></script>
<script type="text/javascript">
   var data =<%=request.getAttribute("data")%>;
   var pagenum  =<%=request.getAttribute("pageallnumber")%>;
	console.debug(data);
	var pageNumber = 20;//每页20条
	var pg_num = 1;     //默认第一页
	var pageTotalCount=0;
	
	$(function(){
		var fanException1=$("#fanException").val();
		if(fanException1=='fanException'){
			alert("消息发送失败，请重新发送!");
		}
		//隐藏下面的页脚
		$("#all-Footer").hide();
		$("#info-Footer").show();
		
		papingSubmit2(pg_num);
        var    pg_num=1;
        //定时从后台获取最新的信息数据
		setInterval("papingSubmit(1)",60000)  //单位毫秒
		function getinfobytime()
		{
			
		}
	   
	});
	
	function changeFont(node){
		
		$(node).parent().css({"font-style":"italic","font-weight":"normal"});
	}
	function papingSubmit2(pg_num){
	        	  
	        	  var jsonInfo = data;
	        	  var li = "";
	        	  if(jsonInfo[0]==null){
	        	  	$('.news_wrapper ul').html("<div><font color =red>未发现新信息</div>");
	        	  	  
		        	  }else{
			        	  
	        	  for(var i=0 ; i<jsonInfo.length; i++){
	        	 	  console.debug(jsonInfo[0]+"..-------------..");
	        		  var fontWeight = "";
	        		  if(jsonInfo[i].readed == "1"){
	        			  fontWeight = '<li>';
	        		  }else{
	        			  fontWeight = '<li  style="font-weight: bold;" >';   //font-style:normal;
	        		  }//<img src="manager/images/info.JPG"> target="_blank" 
	        		  li+= fontWeight + '<a  href="info_findInfo_byid.action?mid='+jsonInfo[i].mid+'">'+  
	        		  ' '+jsonInfo[i].title+'</a>'+"         "+'<font  size="2" color= "grey ">  '+	jsonInfo[i].otime+'</font></li>';
	        	  }
		      
	        	  pageTotalCount =pagenum;//jsonInfo.length;// jsonInfo.length;
                  
	        //	  $('.news_wrapper ul').insertAfter("#divline");
	        	  $('.news_wrapper ul').html(li);
	            // $('.news_wrapper').html("<hr>");
	        	  	
	        	  if(jsonInfo.length <=1){
	        		  $("#hindInfo").css("display","inline");
	        		  $('.row-fluid ul').html(paping(pg_num,pageTotalCount,pageNumber));
	        	  }else{
	        	 	//  alert(jsonInfo.length);
		        	  //生成分页条
					  $('.row-fluid ul').html(paping(pg_num,pageTotalCount,pageNumber));
			      }
		  }
			 
	     
	}
	function papingSubmit(pg_num){
	//	alert("定时器启动。。。。。。"+pg_num+pageNumber);
		$.ajax({
			  data:{"pg_num":pg_num,"pageNumber":pageNumber},
	          url:'info_listPageInfo.action',//用于文件上传的服务器端请求地址  
	          success: function (msg){
	           var li = "";
	     	   var dtainfo =  eval('(' + msg + ')');
	     	   
	        	  console.debug(msg);
	        	  var jsonInfo = dtainfo;
		        
	        	  if(jsonInfo[0]==null){
		       
	        	  	  $('.news_wrapper ul').html("<div><font color =red>未发现新信息</div>");
	        	  	  
		        	  }else{
			        	  
	        	  for(var i=0 ; i<jsonInfo.length; i++){
	        		  var fontWeight = "";
	        		  if(jsonInfo[i].readed == "1"){
	        			  fontWeight = '<li>';
	        		  }else{
	        			  fontWeight = '<li  style="font-weight: bold;" >';   //font-style:normal;
	        		  }
	        		  li+= fontWeight + '<a href="info_findInfo_byid.action?mid='+jsonInfo[i].mid+'">'+  
	        		           ' '+jsonInfo[i].title+'</a>'+"         "+'<font    size="2" color= "grey ">  '+	jsonInfo[i].otime+'</font></li>';
	        	  }
		    
	        	 pageTotalCount =pagenum;//jsonInfo.length;
	        	 
	       
	        	 $('.news_wrapper ul').html(li);
	        
	        	  if(jsonInfo.length <=1){
		        	  
	        		  $("#hindInfo").css("display","inline");
	        		  $('.row-fluid ul').html(paping(pg_num,pageTotalCount,pageNumber));
	        	  }else{
		        	  
		        	  //生成分页条
					  $('.row-fluid ul').html(paping(pg_num,pageTotalCount,pageNumber));
	        	         }
	                }
			   }
			}); 
	 
	}

</script>
</head>

<style type="text/css">

#tb_info .left {
	text-align: right;
	padding-right: 10px;
	font-size: 14px;
}

#tb_info input {
	height: 24px;
	width: 250px;
}

#tb_info tr {
	margin-top: 5px;
	margin-bottom: 5px;
}

#tb_info select {
	height: 20px;
	width: 100px;
}

#tb_info {
	margin-top: -10px;
}

.news_wrapper span {
	float: right;
	font-style: italic;
	color: #333333;
}

.news_wrapper>ul>li {
	padding-top: 6px;
	padding-bottom: 6px;

}

.news_wrapper>ul>li>a {
	color: #333333;
	font-size: 16px;
	
}

.news_wrapper>ul>li>a:hover {
	text-decoration: underline;
	color: #43A1DA;
}

.news_wrapper {
	width: 87%;
	margin: 0 auto;
	margin-top: 10px;
}

.news_wrapper>ul>li>a>img {
	border: none;
	padding-right: 5px;
	padding-left: 5px;
	margin-bottom: -3px;
}

.page_wrapper {
	margin-bottom: 10px;
	margin-top: 10px;
	padding: 0;
}

.page {
	float: right;
	margin-right: 0;
	padding: 2px 0;
	width: auto;
}

.page span.pre {
	border: 1px solid #000000;
	padding-left: 16px;
	padding-right: 8px;
	position: relative;
}

.page span {
	display: block;
	float: left;
	font-size: 12px;
	height: 12px;
	line-height: 12px;
	margin-left: 5px;
	padding: 2px 0;
	width: auto;
	cursor: pointer;
}

.page .no {
	border: 1px solid #666666;
	padding: 2px 4px;
}

.page .selected {
	background-color: #000000;
	color: #FFFFFF;
}

.page span.next {
	border: 1px solid #000000;
	padding-left: 8px;
	padding-right: 16px;
	position: relative;
}

.row-fluid:before, .row-fluid:after {
    content: "";
    display: table;
}
.row-fluid:before, .row-fluid:after {
    content: "";
    display: table;
}
.row-fluid:after {
    clear: both;
}
.row-fluid:before, .row-fluid:after {
    content: "";
    display: table;
}
.row-fluid:after {
    clear: both;
}
.row-fluid:before, .row-fluid:after {
    content: "";
    display: table;
}


.row-fluid [class*="span"]:first-child {
    margin-left: 0;
}
.row-fluid [class*="span"]:first-child {
    margin-left: 0;
}
.row-fluid .span12 {
    width: 100%;
}
.row-fluid [class*="span"] {
    -moz-box-sizing: border-box;
    display: block;
    float: left;
    margin-left: 2.5641%;
    min-height: 28px;
    width: 100%;
}
.row-fluid .span12 {
    width: 100%;
}
.row-fluid [class*="span"] {
    -moz-box-sizing: border-box;
    display: block;
    float: left;
    margin-left: 2.12766%;
    min-height: 28px;
    width: 100%;
}
div.center, p.center, img.center {
    display: block;
    float: none !important;
    margin-left: auto !important;
    margin-right: auto !important;
    text-align: center;
}
.span12 {
    width: 1170px;
}
[class*="span"] {
    float: left;
    margin-left: 30px;
}
.span12 {
    width: 940px;
}
[class*="span"] {
    float: left;
    margin-left: 20px;
}

.pagination {
    height: 36px;
    margin: 18px 0;
}
div.center, p.center, img.center {
    text-align: center;
}
.pagination ul {
    border-radius: 3px 3px 3px 3px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
    display: inline-block;
    margin-bottom: 0;
    margin-left: 0;
}
ul {
    list-style: disc outside none;
}

.pagination li {
    display: inline;
}
li {
    line-height: 18px;
    width:50;
}
.pagination li:first-child a {
    border-left-width: 1px;
    border-radius: 3px 0 0 3px;
}
.pagination .disabled span, .pagination .disabled a, .pagination .disabled a:hover {
    background-color: rgba(0, 0, 0, 0);
    color: #999999;
    cursor: default;
}
.pagination a {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: #DDDDDD;
    border-image: none;
    border-style: solid;
    border-width: 1px 1px 1px 0;
    float: left;
    line-height: 34px;
    padding: 0 14px;
    text-decoration: none;
}
a {
    color: #369BD7;
    text-decoration: none;
}
.pagination li:last-child a {
    border-radius: 0 3px 3px 0;
}
.pagination a {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: #DDDDDD;
    border-image: none;
    border-style: solid;
    border-width: 1px 1px 1px 0;
    float: left;
    line-height: 34px;
    padding: 0 14px;
    text-decoration: none;
}
a {
    color: #369BD7;
    text-decoration: none;
}
.dataTables_paginate>ul>li>a{
  color:#2082BD;
}
.dataTables_paginate>ul>li.active>a{
  /*background:#F5F5F5;*/
  background: #2082BD;
  color: #FFFFFF;
  border-bottom: 1px sloid #DDDDDD;
  border-top: 1px sloid #DDDDDD;
  cursor:text;
}

.btn { margin-left: 96%; }


</style>
<body>

<!-- 这个是提示在点击信封时判断是不是这个页面 -->
	<input type="hidden" id="personInfo" value="1">
	<input type="hidden" id="fanException" value="${fanException}">
<div class="news_wrapper"  >
     <div id="div2"  ><!-- manager/info/infoCreateReply.jsp -->
         <h2 align="left">申康中心信息一览</h2>
         <a href="info_createView.action"  class="btn blue" ><input type="button" value="新建" ></a>
     </div>
	  <hr  style="color:red	 "/>
	   <ul>
	   </ul>
	<hr style="color:red"/>
</div>
<div class="row-fluid">
	<div class="span12 center">
		<div class="dataTables_paginate paging_bootstrap pagination" style="text-align: left;">
			<ul style="margin-left: 30px;bottom:0px; position:relative;">
				
			</ul>
		</div>
	</div>
	
</div>



<input id="messageType" type="hidden" value="${messageType}"/>
</body>
</html>