<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'findWeather.jsp' starting page</title>
    <script type="text/javascript">
		// JavaScript Document
		/**
		* 远程网页源代码读取
		* (存在乱码问题)
		* @param address  地名
		* @Version 1.0.0
		* @author 陈志平 2011-12-01
		* 调用方法 
		* getSource("地名"); 
		*/
		
		var xmlhttp; //XMLHttpRequest对象
		createXmlHttp=function() {
			/*
			if (window.XMLHttpRequest) {
			   xmlhttp = new XMLHttpRequest();  //FireFox、Opera等浏览器支持的创建方式
			} else {
			   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");//IE浏览器支持的创建方式
			}*/
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		};

		//获取远程网页源代码
		getSource=function(address) {
			var url = document.getElementById("url").value;

			//地址为空时提示用户输入
			if (url == "") {
				alert("请输入网页地址。");
				return;
			}
			
			createXmlHttp(); //创建XMLHttpRequest对象
			//设置回调函数
			xmlhttp.onreadystatechange = function(){
				writeSource(address);
			};  
			//alert("");             
			xmlhttp.open("GET", url, false);		
			xmlhttp.send(null);		
		};

		//解析远程网页源代码
		writeSource=function(address) {
			
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200 ) {
				var html =  xmlhttp.responseText;
				
				document.getElementById("mydiv").innerHTML = html;
				//7d  weatherYubao2
				var findthreeDays = document.getElementById("7d").innerHTML ;
				var findfourdays  = document.getElementById("weatherYubao2").innerHTML ;
				var allTbody = document.getElementsByTagName("tbody");			
				//获得日期地名
				var area = findDate(findthreeDays,address);
				//获得气温
				findAirTemperature(allTbody,area);			
			}
		};

		//日期运算
		operation=function(str,day,isadd){
			var result = str;
			var matchStr = /^\d{4}-\d{2}-\d{2}$/;
			if(str.length==10 && str.match(matchStr)!=null){
				var ndate = new Date();
				ndate.setYear(str.substr(0,4));
				ndate.setMonth(str.substr(5,2)-1);
				ndate.setDate(str.substr(8,2));
				
				if(isadd){
					ndate.setDate(ndate.getDate()+day);
				}else{
					ndate.setDate(ndate.getDate()-day);
				}

				var m = ndate.getMonth()+1;
				var d = ndate.getDate();
				result = ndate.getYear()+"-"+(m>9?m:"0"+m)+"-"+(d>9?d:"0"+d);
			}

			return result;
		};

		//日期加天
		addDay=function(str,day){
		   return operation(str,day,true);
		};
		
		//日期减天
		subDay=function(str,day){
			return operation(str,day,false);
		};


		//TR查找  Array(max,min)
		getTDs=function(trs){
			var vals = new Array();
			if(trs.length>0){
				for(var j=0;j<trs.length && j<2;j++){
					if(j==0){
						vals["max"] = getAir(trs[j].cells);
					}else{
						vals["min"] = getAir(trs[j].cells);
					}
				}			
			}

			return vals;
		};

		//TD查找 
		getAir=function(tds){
			var val="";
			if(tds.length>0){
				for(var k=0;k<tds.length;k+=1){
					if(tds[k].innerHTML.indexOf("℃")!=-1){
						val = tds[k].innerText;
						break;
					}
				}
			}
			return val;
		};
		
		
		//获得气温数据
		getNumber=function(str){		
			var matchStr = /^\d$/;
			var chr = str.split("");
			var val="";
			var start = -1;
			var end = -1;
			for(var i=0;i<chr.length;i+=1){
				if(chr[i].match(matchStr) !=null || chr[i] == "-"){
					if(start == -1){
						start = i;					
					}
				}

				if(start != -1 && chr[i] !="." && chr[i].match(matchStr) !=null){
					end = i;
				}
			}
			
			val = str.substring(start,end+1);
			if(isNaN(val)){
				val = null;
			}
			
			return val;
		};	
		
		//获得日期
		findDate=function(hString,address){
			var matchStr = /^\d{4}-\d{2}-\d{2}$/;
			var now_date ="";
			var title = address;
			var at = hString.indexOf(address);
			var tmp;
			if(at != -1 ){
				var tmpStr = hString.substr(at,35);
				var args = tmpStr.split("");
				for(var i=0;i<args.length;i+=1){
					if(!isNaN(args[i]) && args[i]>0){
						tmp = tmpStr.substr(i,10);
						tmp = tmp.match(matchStr);
						if(tmp != null){
							now_date = tmp
							break;
						}
					}
				}			
			}

			var area = new Array();
			area["name"] = title;
			area["stime"] = now_date;
			//document.getElementById("myaddress").innerText = address;

			return area;
		};

		//获得气温
		findAirTemperature=function(allTbody,area){
			if(area.stime =="" || area.stime == null){
				alert("未得到数据!");
				return ;
			}
			var str="";
			var data = new Array();
			var day = parseInt((area.stime+"").substr(8,2));
			var obj = null;
			var tmp = null;
			for(var i=0,j=-1;i<allTbody.length;i+=1){
				str = allTbody[i].innerHTML;
				if(str.indexOf(day+"日") !=-1){
					var vals = new Array();				
					vals["year"] = addDay(area.stime+"",j+=1);				
					tmp = getTDs(allTbody[i].rows);
					vals["max"] = getNumber(tmp.max+"");
					vals["min"] = getNumber(tmp.min+"");
					vals["unit"] = "℃";
					data[data.length] = vals;
					
					day += 1;
				}			
			}
			

		
			/**
			 * 数据异步更新区
			 */
			if(data.length>0){
				var show = document.getElementById("nowAir");
				var myshow ="<table border='1'><tr><td>时间</td><td>高温</td><td>低温</td><td>单位</td></tr>";
				for(var i=0;i<data.length;i++ ){
					obj = data[i];
					myshow += "<tr><td>"+(obj.year+"</td><td>"+obj.max+"</td><td>"+(obj.min==""?"&nbsp;":obj.min)+"</td><td>"+obj.unit) +"</td></tr>";
				}
				myshow +="</table>";
				
				show.innerHTML = myshow;			
			}
			
		};

	</script>
	<style type="text/css">
	/* 页面字体样式 */
	body, td, input, textarea {
		font-family:宋体;
		font-size:16px;
	}
	</style>
</head>






<body>
<center>
<h3>远程网页源代码读取</h3>
<div>
<input type="hidden" id="url" value="http://www.weather.com.cn/weather/101021200.shtml" >
</div>
<div id="mydiv" style="display:none;"></div>
<div style="display:block;"><span id="myaddress">徐家汇</span>未来七天气温情况</div>
<div id="nowAir" style="display:block;"></div>
</center>

<script language="JavaScript">
<!--
	getSource("徐家汇");
//-->
</script>

<!-- 
	说明:
	url 与 getSource()函数传入的地址必需一一对应! ;
-->
</body>
</html>
