<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>特种设备安全指数</title>
		<meta name="Description" content="" />
		<meta name="Keywords" content="" />
		<script type="text/javascript" src="manager/js/ichart/ichart.1.2.min.js"></script>
		<script type="text/javascript" src="manager/js/ichart/ichart.gauge2d.js"></script>
		<script type="text/javascript" src="manager/js/jquery-1.7.2.js"></script>
	<style type="text/css">
		body{
			text-align: center;
			background-repeat: repeat;
		}
		#canvasDiv{
			margin:0px auto;
			text-align: center;
			height:250px;
		}
	</style>
	<script type="text/javascript">
      function drawGauge2D(target,title){
	     var chart = new iChart.Gauge2D({
			render : target,
			title : {
				text : title,
				height:14,
				fontsize : 13,
				color : '#366182',
				offsety:3
			},
			shadow:true,
			value:10,
			tickmarks_lower : 0,
			tickmarks_upper : 50,
			tickmarks_width:10,
			tickmarks_bg_color:0,
			tickmarks_small_color:["#FFFFFF",0,0,0,0,0,0,0,0,0],
			tickmarks_color:['#000000',0,0,0,0,0,0,0,0,0],
			tickmarks_size:2,
			tickmarks_count:10,
			tickmarks_radius:'84%',
			label_space:-17,
			label:{
				color:'#000000',
				fontsize:7,
				fontweight:85
			},
			start_angle:90,
			space_angle:2,
			panel_color:'#6694fb',
			gradient:true,
			gradient_mode:'RadialGradientOutIn',
			background_color:'',
			border:0,
			outer_border_width:22,
			outer_border_color:'#e3e4e9',//外边框
			inner_border_width:7,
			inner_border_color:'#b6c7fe',//内边框
			inner_border_radius:'98%',
			text:{text:'',color:'#ffffff'},
			//screen:{decimalsnum:1,unit_post:'pa',offsety:-50,height:0,color:'black'},
			needle_color:'red',
			needle_colorOne:'#ff6600',
			needle_colorTwo:'#ffcc66',
			needle_sizeOne:5,
			needle_sizeTwo:3,
			center_cap_sizeOne:5,
			center_cap_size:5,
			center_cap_sizeTwo:5,
			center_cap_color:'#b6c7fe',
			width : 195,
			height : 240,
			radius:'95%'
				
		});
		chart.on('beforedraw',function(e){
			chart.START_RUN_TIME = new Date().getTime();
			return true;
		});
		
		chart.on('draw',function(e){
			chart.END_RUN_TIME = new Date().getTime();
			chart.RUN_TIME_COST = chart.END_RUN_TIME - chart.START_RUN_TIME;
			//console.log(chart.RUN_TIME_COST);
		});
		chart.draw();
		//interval(chart,0,0,0);
		//chart.to(0,0,0);
		
		return chart;
	  }
	  

      var ktchart1 ;
      var ktchart2 ;
      var ktchart3;
      var ktchart4;
        		  
	$(function(){
		 ktchart1 = drawGauge2D('canvasDivKT','空调');
		 ktchart2 = drawGauge2D('canvasDivGL','锅炉');
		 ktchart3 = drawGauge2D('canvasDivDT','电梯');
		 ktchart4= drawGauge2D('canvasDivDL','电力');
		 //ajax
		 findSpecialAlarmCount();
		
	});
var gethtmlStr = function(one,two,three){//<br/>
	var htmlStr ="<label style=\"color:red;font-weight:bolder;\">一级:"+one+"&nbsp;</label>";
	htmlStr += "<label style=\"color:#ff6600;font-weight:bolder;\">二级:"+two+"&nbsp;</label>";
	htmlStr += "<label style=\"color:#ffcc66;font-weight:bolder;\">三级:"+three+"</label>";
	return htmlStr ;
}

var getShowValue  = function(val){
	return val>50?500:val
}

	function findSpecialAlarmCount (){
		$.ajax( {
			type : "POST",
			url : "statisticsSafe_findSpecialAlarmCountByHtml5.action",
			cache : false,
			async : false,
			dataType:"json",
			error : function(jsonObj) {
			 ktchart1.to(0,0,0);
			 ktchart2.to(0,0,0);
			 ktchart3.to(0,0,0);
			 ktchart4.to(0,0,0);
			},
			success : function(jsonObj) {
				var valArr = jsonObj;
				try{
				var chartValArr1 = valArr[0].split(",");
				var chartValArr2 = valArr[1].split(",");
				var chartValArr3 = valArr[2].split(",");
				var chartValArr4 = valArr[3].split(",");
				
				$("#div_kt").html(gethtmlStr(chartValArr1[0],chartValArr1[1],chartValArr1[2]));
				$("#div_gl").html(gethtmlStr(chartValArr2[0],chartValArr2[1],chartValArr2[2]));
				$("#div_dt").html(gethtmlStr(chartValArr3[0],chartValArr3[1],chartValArr3[2]));
				$("#div_dl").html(gethtmlStr(chartValArr4[0],chartValArr4[1],chartValArr4[2]));
				 ktchart1.to(getShowValue(chartValArr1[0]),getShowValue(chartValArr1[1]),getShowValue(chartValArr1[2]));
				 ktchart2.to(getShowValue(chartValArr2[0]),getShowValue(chartValArr2[1]),getShowValue(chartValArr2[2]));
				 ktchart3.to(getShowValue(chartValArr3[0]),getShowValue(chartValArr3[1]),getShowValue(chartValArr3[2]));
				 ktchart4.to(getShowValue(chartValArr4[0]),getShowValue(chartValArr4[1]),getShowValue(chartValArr4[2]));
				}catch(e){
					 ktchart1.to(0,0,0);
					 ktchart2.to(0,0,0);
					 ktchart3.to(0,0,0);
					 ktchart4.to(0,0,0);
				}
			}
		});
		 setTimeout(findSpecialAlarmCount,30000);  
	}
	
	
	</script>
	</head>
	<body>
	    <div style ="width:100%;height:129px;overflow: hidden;">
		   <div id='canvasDivKT' style=" float:left;"></div>
		   <div id='canvasDivGL' style=" float:left;"></div>
		   <div id='canvasDivDT' style=" float:left;"></div>
		   <div id='canvasDivDL' style=" float:left;"></div>
		</div>
		<div style ="width:100%;height:45px;overflow: hidden;font-size:13px;margin-top:22px">
		   <div id='div_kt' style=" float:left;width:25%">0</div>
		   <div id='div_gl' style=" float:left;width:25%">0</div>
		   <div id='div_dt' style=" float:left;width:25%">0</div>
		   <div id='div_dl' style=" float:left;width:25%">0</div>
		</div>
	</body>
</html>
