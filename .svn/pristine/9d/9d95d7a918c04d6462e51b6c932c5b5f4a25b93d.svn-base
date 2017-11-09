<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<style type="text/css">	

	.fileDiv{
		margin-left: 45px;
	}
	.fileDiv input{
		margin-left: 45px;
	}
	#contentDiv{
		margin-left: 130px;
		margin-top: 2px;
		margin-bottom: 10px;
	}
	
	.contentP{
		position:absolute;
		top:90px;
		margin-left: 60px;
	}
	
	#editor{
		position: absolute;
		margin-top: 200px;
	}
	#tb_info span {
		color: red;
		display: none;
		margin-left: 10px;
	}
	
	.smsContent .content span {
		color: red;
		display: none;
		margin-left: 120px;
	}
	
	.
	#tb_info .left {
		text-align: right;
		padding-right: 10px;
		font-size: 14px;
	}
	
	#tb_info input.text {
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
	
	.zhezhaoceng {
		position: absolute;
		background: none repeat scroll 0% 0% rgb(0, 0, 0);
		padding: 0px;
		height: 434px;
		top: 45px;
		opacity: 0.3;
		left: 0px;
		width: 1080px;
		display: none;
	}
	
	.smsDiv {
		background: none repeat scroll 0 0 red;
		border-radius: 10px 10px 10px 10px;
		height: 300px;
		left: 300px;
		position: absolute;
		top: 100px;
		width: 500px;
		z-index: 999;
		display: none;
	}
	
	.smsDivTitle {
		background: #A4D7FF;
		border-top-left-radius: 10px;
		border-top-right-radius: 10px;
		color: rgb(49, 126, 172);
		font-size: 14px;
		font-weight: bold;
		height: 35px;
		border-top-left-radius: 10px;
	}
	
	.smsDivTitle>span {
		line-height: 35px;
		margin-left: 10px;
	}
	
	.smsContent {
		height: 265px;
		background: #F5F5F5;
		border-bottom-left-radius: 10px;
		border-bottom-right-radius: 10px;
	}
	
	.smsContent .content {
		background: #FFFFFF;
		padding: 20px;
		height: 215px;
	}
	
	.replaceContent {
		height: 180px;
		border: 0px solid red;
		overflow-x: hidden;
	}
	
	a.closeCheck {
		float: right;
		margin-right: 15px;
		font-size: 20px;
		color: rgb(255, 255, 255);
		margin-top: 8px;
		cursor: pointer;
	}
	
	.btn_oper {
		border: 0px solid blue;
		height: 35px;
		padding-top: 5px;
	}
	
	.btn_save {
		background: #43A1DA;
		border-radius: 5px 5px 5px 5px;
		color: rgb(255, 255, 255);
		font-size: 14px;
		padding: 6px 20px;
		float: right;
		margin-right: 10px;
		cursor: pointer;
	}
	
	.btn_transfer {
		background: #43A1DA;
		border-radius: 5px 5px 5px 5px;
		color: rgb(255, 255, 255);
		font-size: 14px;
		width: 50px;
		height: 20px;
		margin-top: 10px;
		cursor: pointer;
	}
	
	.checkmargin {
		margin-left: 15px;
	}
	
	.emailContent {
		height: 170px;
		width: 160px;
		margin-left: 20px;
		float: left;
		position: relative;
		margin-top: 5px;
		overflow: auto;
		border: 1px solid #43A1DA;
		border-radius: 5px;
	}
	
	.email_oper {
		height: 170px;
		width: 50px;
		margin-top: 5px;
		float: left;
		margin-left: 18px;
		text-align: center;
	}
	
	.email_changed {
		height: 170px;
		margin-left: 270px;
		width: 160px;
		margin-top: -172px;
		float: left;
		border: 1px solid #43A1DA;
		border-radius: 5px;
	}
	
	.selectUL>li>a:hover {
		color: #43A1DA;
		cursor: pointer;
	}
	
	.selectUL li {
		line-height: 18px;
	}
	
	.liClick {
		background: #43A1DA;
		color: #FFFFFF;
		cursor: pointer;
	}
	
	.liClicka {
		color: #FFFFFF;
	}
	
	.btnOPer {
		width: 40px;
	}
	
	 #alertMsg {
            display: none;
            width: 400px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 1px 1px 10px black;
            padding: 10px;
            font-size: 12px;
            position: absolute;
            text-align: center;
            background: #fff;
            z-index: 100000;
        }

        #alertMsg_info {
            padding: 2px 15px;
            line-height: 1.6em;
            text-align: left;
        }

        #alertMsg_btn1, #alertMsg_btn2 {
            display: inline-block;
            background: url(images/gray_btn.png) no-repeat left top;
            padding-left: 3px;
            color: #000000;
            font-size: 12px;
            text-decoration: none;
            margin-right: 10px;
            cursor: pointer;
        }

        #alertMsg_btn1 cite, #alertMsg_btn2 cite {
            line-height: 24px;
            display: inline-block;
            padding: 0 13px 0 10px;
            background: url(images/gray_btn.png) no-repeat right top;
            font-style: normal;
        }
	
</style>

</head>
<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
<script type="text/javascript" src="manager/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="manager/js/ckeditor/ckeditor.js"></script>
	<body style="z-index: 10000">
		
	<!-- 这个是提示在点击信封时判断是不是这个页面 -->
	<input type="hidden" id="personInfo" value="1">
	<form id="infoForm" action="info_addInfo.action" method="post"  enctype="multipart/form-data">
	<input type="hidden" name="info_from" value= "${contentMap.info_from}">
	<input type="hidden" name="info_to" value= "${contentMap.info_to}">
	<input type="hidden" name="from_name" value= "${contentMap.from_name}">
	<input type="hidden" name="to_name" value= "${contentMap.to_name}">
	
	
		<table id="tb_info" width="90%" align="center" border="0"
			height="20px" style="margin-top: 5px;">
			<tr height="20px;">
				<td width="70"><input type="submit" value="发送" > </td>
			    <td><input type="button" id="return" onclick="javascript:history.back(-1); " value="返回"></td>
			</tr>
			
			<tr>
			<td colspan=2><hr style="color:red;"/>
			</tr>
			
			<tr height="20px;"><%--
			class="left"
				--%><td >标题:</td>
				<td><input type="text" id="txtTitle" class="text" name="txtTitle"  style="width: 400px;" value="回复 :${contentMap.title}"/> 
				</td>
			</tr>
		</table>
		<p class="contentP">内容:</p><br/>
		<div id="contentDiv">
			<textarea id="editor" name="Content"  ></textarea>
		</div>
		<div class="fileDiv">附件:<input type="file" id="file" name="file" /></div>
		
		<div class="zhezhaoceng"></div>
	
		
	</form>
	

	
	
	<script type="text/javascript">
	var dataInfo =<%=request.getAttribute("data")%>;
	//console.debug(data.content);
	var data;
	if(dataInfo.content == null  || dataInfo.content == "undefined"){
          data=" ";
		}else{
			data=dataInfo.content;
			}
	//+$("#img").html(data.content)+"
	$("#editor").text("<br><br><br><br><b ><div color=red>"+"---  --  -- --  -- -- --  --  -- --  -- --  -- -- --"+"</div><br><br><br><br></b>"+data);
	//页面加载不出编辑器的时候，重新加载出来
	if (CKEDITOR.instances['editor']) {
		CKEDITOR.remove(CKEDITOR.instances['editor']);
	}
//工具栏为自定义类型
   var editor = CKEDITOR.replace( 'editor',
         {
             toolbar :
             [
			//加粗     斜体，     下划线      穿过线      下标字        上标字
                ['Bold','Italic','Underline','Strike','Subscript','Superscript'],
			//数字列表          实体列表            减小缩进    增大缩进
                ['NumberedList','BulletedList','-','Outdent','Indent'],
			//左对齐             居中对齐          右对齐          两端对齐
                ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			//超链接 取消超链接 锚点
                ['Link','Unlink','Anchor'],
			//图片    flash    表格       水平线            表情       特殊字符        分页符
                ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
					'/',
			//样式       格式      字体    字体大小
                ['Styles','Format','Font','FontSize'],
			//文本颜色     背景颜色
                ['TextColor','BGColor'],
			//全屏           显示区块
                ['Maximize', 'ShowBlocks','-']
             ]
         }
    );
    
		var usersId = "";
		var sendMode = "";
		var infoType = "";
		var txtTitle = "";
		var text_to = "";
	    var fileName = ""; 
	    var text_from = "";
		$(function(){
			//隐藏下面的页脚
			$("#all-Footer").hide();
			$("#info-Footer").show();
			//给元素绑定改变函数，去掉提示
			$("#infoType").change(function(){
				$("#hintInfoType").css("display","none");
			});
			$("#txtTitle").change(function(){
				$("#hintTxtTitle").css("display","none");
			});
			
			
		});
		
		
		function validContent(){
			
			text_to = $("#text_to").val();
			
			
			txtTitle = $("#txtTitle").val();
			ftyContent = editor.document.getBody().getHtml(); //取得html文本
			
		    fileName = $("#file").val(); 
			var sendModeTemp = "";
		    $("#sendMode input:checked").each(function(){
		    	sendModeTemp+= $(this).val()+";";
			});
		    sendMode = sendModeTemp;
		    text_from = $("#text_from").val();
		    
		 
		    if(txtTitle == ""){
		    	$("#hintTxtTitle").css("display","inline");
		    	return false;
		    }
		    
	
		    
		    return true;
		}
		
		function submitForm(){
			if(validContent()){
			     $.ajax({ 
			    	   type:"POST",
					   data:{"txtTitle":txtTitle,"fileName":fileName,
				             "text_from":text_from,"text_to":text_to,"Content":ftyContent},
		               url:'info_addInfo.action',//用于文件上传的服务器端请求地址  
		               success: function (data){

		                       jQuery.ajaxFileUpload({
	        					   url: "info_sendFile.action",
	        					   secureuri:false,
	        					   fileElementId : 'file',
	        					   success: function(data, status){
	        					   		document.getElementById("infoForm").reset();
	        						   //关闭上传提示框
	        						   document.body.removeChild(alertBox);
	        				           document.body.removeChild(shadowDiv);
	        						  
	        					   }
	        				}); 
		                       
		               }
		         });
			}
		}
		
		//点击选择后
		function radioChange(obj) {
			
			$('.zhezhaoceng').show();
			$('.smsDiv').show();
			registerLiHover();
		}
		
		//添加点击事件
		function registerLiHover() {
			$('.replaceContent ul li').click(function() {
				$('.replaceContent ul li').removeClass('liClick');
				$(this).addClass('liClick');
			});
			$('#ulList li').dblclick(function() {
				$("#hintUser").css("display","none");
				$(this).unbind('dblclick');
				$('#ulChanged').append($(this));
				reloadRightEvents();
			});
		}

		//从新加载左侧事件
		function reloadLeftEvents() {
			$('#ulList li').unbind('dblclick');
			$('#ulList li').dblclick(function() {
				$("#hintUser").css("display","none");
				$(this).unbind('dblclick');
				$('#ulChanged').append($(this));
				reloadRightEvents();
			});
		}
		//重新加载右侧事件
		function reloadRightEvents() {
			$('#ulChanged li').unbind('dblclick');
			$('#ulChanged li').dblclick(function() {
				$("#hintUser").css("display","none");
				$(this).unbind('dblclick');
				$('#ulList').append($(this));
				reloadLeftEvents();
			});
		}
		
		
		function removeToRight() {
			$("#hintUser").css("display","none");
			 $('#ulList li.liClick').unbind('dblclick');
			$('#ulChanged').append($('#ulList li.liClick'));
			reloadRightEvents(); 
		}

		function removeToLeft() {
			$("#hintUser").css("display","none");
			$('#ulChanged li.liClick').unbind('dblclick');
			$('#ulList').append($('#ulChanged li.liClick'));
			reloadLeftEvents();
		}

		function removeAllToRight() {
			$("#hintUser").css("display","none");
			$('#ulList li').each(function() {
				$('#ulChanged').append($(this));
			});
			reloadRightEvents();
		}

		function removeAllToLeft() {
			$('#ulChanged li').each(function() {
				$('#ulList').append($(this));
			});
			reloadLeftEvents();
		}

		function doHideChange() {
			$('.zhezhaoceng').hide();
			$('.smsDiv').hide();
		}

		//全选全不选
		function checkAll(obj) {
			if ($(obj).attr("checked")) {
				$("input[name='smsPhone']").attr("checked", true);
			} else {
				$("input[name='smsPhone']").attr("checked", false);
			}
		}

		function getSMSInfo() {
			var res = '';
			$("input[name='smsPhone']:checked").each(function() {
				res += $(this).attr('value') + ";";
			});
			if (res.length > 80) {
				res = res.substring(0, 80);
			}
			return res;
		}
		function hideHint(){
			$("#hintSendMode").css("display","none");
			$("#hintSendModeCommon").css("display","none");
		}
		function getEmailInfo() {
			var res = '';
			$('#ulChanged li').each(function() {
				res += $(this).find('a').text() + ";";
			});
			if (res.length > 80) {
				res = res.substring(0, 80) + "...";
			}
			return res;
		}
		
		function validInfo() {
			var usersIdTemp = "";
			$('#ulChanged li').each(function() {
				usersIdTemp += $(this).val() + ";";
			});
			usersId = usersIdTemp;
			
			$("#sendMode input:checked").each(function(){
				sendMode+= $(this).val()+";";
			});
			
			if ($('#ulChanged li').length < 1) {
				$("#hintUser").css("display","inline");
				
				return;
			} 
			if($("#sendMode input:checked").val() == null){
				$("#hintSendMode").css("display","inline");
				return;
			} 
			return true;
		}

		function Save() {
			 
			if (validInfo()) {
				$('#hasChange').html("已选接收者：" + getEmailInfo());
				$('.zhezhaoceng').hide();
				$('.smsDiv').hide();
			}
			
		}
		
		//上传附件弹出的等待框
		var alertBox;
		var shadowDiv;
		function alertMsg(msg, mode) { //mode为空，即只有一个确认按钮，mode为1时有确认和取消两个按钮
	        msg = msg || '';
	        mode = mode || 0;
	        var top = document.body.scrollTop || document.documentElement.scrollTop;
	        var isIe = (document.all) ? true : false;
	        var isIE6 = isIe && !window.XMLHttpRequest;
	        var sTop = document.documentElement.scrollTop || document.body.scrollTop;
	        var sLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
	        var winSize = function(){
	            var xScroll, yScroll, windowWidth, windowHeight, pageWidth, pageHeight;
	            // innerHeight获取的是可视窗口的高度，IE不支持此属性
	            if (window.innerHeight && window.scrollMaxY) {
	                xScroll = document.body.scrollWidth;
	                yScroll = window.innerHeight + window.scrollMaxY;
	            } else if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac
	                xScroll = document.body.scrollWidth;
	                yScroll = document.body.scrollHeight;
	            } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
	                xScroll = document.body.offsetWidth;
	                yScroll = document.body.offsetHeight;
	            }

	            if (self.innerHeight) {    // all except Explorer
	                windowWidth = self.innerWidth;
	                windowHeight = self.innerHeight;
	            } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
	                windowWidth = document.documentElement.clientWidth;
	                windowHeight = document.documentElement.clientHeight;
	            } else if (document.body) { // other Explorers
	                windowWidth = document.body.clientWidth;
	                windowHeight = document.body.clientHeight;
	            }

	            // for small pages with total height less then height of the viewport
	            if (yScroll < windowHeight) {
	                pageHeight = windowHeight;
	            } else {
	                pageHeight = yScroll;
	            }

	            // for small pages with total width less then width of the viewport
	            if (xScroll < windowWidth) {
	                pageWidth = windowWidth;
	            } else {
	                pageWidth = xScroll;
	            }

	            return{
	                'pageWidth':pageWidth,
	                'pageHeight':pageHeight,
	                'windowWidth':windowWidth,
	                'windowHeight':windowHeight
	            }
	        }();
	        //alert(winSize.pageWidth);
	        //遮罩层
	        var styleStr = 'top:0;left:0;position:absolute;z-index:10000;background:#666;width:' + winSize.pageWidth + 'px;height:' +  (winSize.pageHeight + 30) + 'px;';
	        styleStr += (isIe) ? "filter:alpha(opacity=80);" : "opacity:0.8;"; //遮罩层DIV
	        shadowDiv = document.createElement('div'); //添加阴影DIV
	        shadowDiv.style.cssText = styleStr; //添加样式
	        $(shadowDiv).css("z-index","100000");
	        shadowDiv.id = "shadowDiv";
	        //如果是IE6则创建IFRAME遮罩SELECT
	        if (isIE6) {
	            var maskIframe = document.createElement('iframe');
	            maskIframe.style.cssText = 'width:' + winSize.pageWidth + 'px;height:' + (winSize.pageHeight + 30) + 'px;position:absolute;visibility:inherit;z-index:-1;filter:alpha(opacity=0);';
	            maskIframe.frameborder = 0;
	            maskIframe.src = "about:blank";
	            shadowDiv.appendChild(maskIframe);
	        }
	        document.body.insertBefore(shadowDiv, document.body.firstChild); //遮罩层加入文档
	        //弹出框
	        var styleStr1 = 'display:block;position:fixed;_position:absolute;left:' + (winSize.windowWidth / 2 - 200) + 'px;top:' + (winSize.windowHeight / 2 - 150) + 'px;_top:' + (winSize.windowHeight / 2 + top - 150)+ 'px;'; //弹出框的位置
	        alertBox = document.createElement('div');
	        $(alertBox).css("z-index","120000");
	        
	        alertBox.id = 'alertMsg';
	        alertBox.style.cssText = styleStr1;
	        //创建弹出框里面的内容P标签
	        var alertMsg_info = document.createElement('P');
	        alertMsg_info.id = 'alertMsg_info';
	        alertMsg_info.innerHTML = msg;
	        alertBox.appendChild(alertMsg_info);
	        //创建按钮
	        var btn1 = document.createElement('a');
	       
	        btn1.id = 'alertMsg_btn1';
	        btn1.href = 'javas' + 'cript:void(0)';
	        btn1.innerHTML = '<cite>附件上传中...</cite>';
	        btn1.onclick = function () {
	            document.body.removeChild(alertBox);
	            document.body.removeChild(shadowDiv);
	            return true;
	        };
	        alertBox.appendChild(btn1);
	        
	        document.body.appendChild(alertBox);

	    }
	</script>
</body>
</html>