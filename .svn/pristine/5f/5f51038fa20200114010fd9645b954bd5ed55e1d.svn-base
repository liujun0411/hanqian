<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>底部的页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="manager/css/footer.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/dropDownSelect.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="manager/js/menuChange.js"></script>
	    <style type="text/css">
		.joyride-tip-guide {
		    background: none repeat scroll 0 0 rgba(0, 0, 0, 0.8);
		    border-radius: 4px 4px 4px 4px;
		    color: #FFFFFF;
		    font-family: "HelveticaNeue","Helvetica Neue","Helvetica",Helvetica,Arial,Lucida,sans-serif;
		    font-weight: normal;
		    position: absolute;
		    width: 250px;
		    z-index: 999;
		    line-height: 18px;
		    text-shadow: 0 0 1px rgba(0, 0, 0, 0.01);
		}
		
		.joyride-tip-guide .joyride-next-tip {
		    background: -moz-linear-gradient(center top , #0063FF 0%, #0055D6 100%) repeat scroll 0 0 transparent;
		    border: 1px solid #003CB4;
		    border-radius: 2px 2px 2px 2px;
		    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.3) inset;
		    color: #FFFFFF;
		    font-size: 13px;
		    padding: 6px 18px 4px;
		    text-decoration: none;
		    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.5);
		    width: auto;
		}
		.joyride-tip-guide a {
		    border-bottom: 1px dotted rgba(255, 255, 255, 0.6);
		    color: #FFFFFF;
		    text-decoration: none;
		}
		.joyride-close-tip {
		    border-bottom: medium none !important;
		    color: rgba(255, 255, 255, 0.4) !important;
		    font-family: Verdana,sans-serif;
		    font-size: 10px;
		    font-weight: bold;
		    position: absolute;
		    right: 10px;
		    text-decoration: none;
		    top: 10px;
		}
		.joyride-next-tip{
			margin-left:10px;
			padding-bottom:10px;
			margin-bottom:10px;
		}
		a {
		    color: #666666;
		    outline: medium none;
		    text-decoration: none;
		}
		.joyride-content-wrapper h2{
			padding-left:10px;
		}
		.joyride-content-wrapper p{
		     font-size: 12px;
			 padding-left:10px;
			 line-height:10px;
		}
		.joyride-tip-guide span.joyride-nub.top {
		    border-right-color: rgba(0, 0, 0, 0.8);
		    border-left-color: transparent !important;
		    border-bottom-color: transparent !important;
		    border-top-color: transparent !important;
		    left: -24px;
			top:7px;
		}
		.joyride-tip-guide span.joyride-nub {
		    border: 10px solid;
		    display: block;
		    height: 0;
		    left: 22px;
		    position: absolute;
		    width: 0;
		}
		.joyride-tip-guide span.joyride-nub1.top {
		    border-right-color: rgba(0, 0, 0, 0.8);
		    border-left-color: transparent !important;
		    border-bottom-color: transparent !important;
		    border-top-color: transparent !important;
		    left: -22px;
			top:17px;
		}
		.joyride-tip-guide span.joyride-nub1 {
		    border: 11px solid;
		    display: block;
		    height: 0;
		    left: 22px;
		    position: absolute;
		    width: 0;
		}
		.mleft{
		  margin-left: 10px;
		}
	</style>
    <script type="text/javascript">
      function showTip(operId){
        $('#'+operId).show();
      }
      
      function hideTip(operId){
        $('#'+operId).hide();
      }
      $(function(){
         var date = new Date() 
         $('#info table tr').eq(1).find('span').eq(0).text(date.getFullYear());
         $('#info table tr').eq(1).find('span').eq(1).addClass('mleft');
      });
    </script>
	</head>

	<body>
	   
		
		<div id="login_bottom" class="footer">
			<div class="footers">
			<!--div展现开始 -->
		    <div id="joyRidePopup1" class="joyride-tip-guide " style="display:none; top: 2.5px; left: 731.5px;">
			    <span class="joyride-nub1 top"></span>
				<div class="joyride-content-wrapper">
					<p>电话：021-60319736</p>
					<p>地址：上海市宝山区长逸路15号复旦软件园</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B栋1401室 </p>
				</div>
			 </div>
			<!--div展现结束-->
			<!--div展现开始 -->
		    <div id="joyRidePopup2" class="joyride-tip-guide " style="display: none; top: 2.5px; left: 903.5px;">
			    <span class="joyride-nub1 top"></span>
				<div class="joyride-content-wrapper">
					<p>浏览器：谷歌浏览器(Chrome)24+</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以上版本浏览器</p>
					<p>分辨率：1440*900及以上分辨率</p>
				</div>
			 </div>
			<!--div展现结束-->
				<div id="info">
					<table align="center" border="0">
						<tr align="center">
							<td class="td_company" align="right">
								<img src="manager/images/common/company_logo.png" alt="" />
							</td>
							<td align="left" class="td_infos">
							    <a href="http://www.shhanqian.com" target="_blank" class="info">访问汉乾</a><span class="seperator"><b>|</b></span>
							    <a class="info" onmouseover="showTip('joyRidePopup1');" onmouseout="hideTip('joyRidePopup1');">联系汉乾</a><span class="seperator"><b>|</b></span>
							    <a href="mailto:hanqian@shhanqian.com" class="info">MAIL汉乾</a><span class="seperator"><b>|</b></span>
							    <a class="info" onmouseover="showTip('joyRidePopup2');" onmouseout="hideTip('joyRidePopup2');">最佳体验</a><span class="seperator"><b>|</b></span>
							    <a href="manager/common/Contract.jsp" target="_blank" class="info">责任声明</a>
							</td>
						</tr>
						<tr align="center" style=" width:480px; font:12px arial; color:#6B6B6B;">
						    <td colspan="2">
								©<span>2013</span><span>汉乾中国</span>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
