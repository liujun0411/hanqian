<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台——后勤管理</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/shishijiankong.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		
</head>

<body onload="onload();" style=" background-color: white;">
<c:if test="${empty obj}">
<font color="red">暂无数据</font>
</c:if>
<c:if test="${obj !=null }">
   <div style="overflow:auto; height:160px">
   	<table width="100%" class="shishijiankong_guolu_zt13" border="0" >
		<tr  style="height:117">
			<c:forEach items="${obj.equipList}" var="eqobj">
				<td width="158" valign="top" align="left">
					<table width="150" border="0">
						<tr style="height:60px;">
							<td width="79" align="center">
								<img id="${eqobj.typeId }" src="manager/images/control/eqtype_icon/${eqobj.typeId }.jpg" />
							</td>
							<td width="79" title="${eqobj.name }">
								<c:if test="${fn:length(eqobj.name)>15}">
									${fn:substring(eqobj.name,0,13) }...
								</c:if>
								<c:if test="${fn:length(eqobj.name)<=15}">
									${eqobj.name }
								</c:if>
							</td>
						</tr>							
						<c:if test="${eqobj.points !=null}" >
							<c:forEach items="${eqobj.points}" var="point" varStatus="ind">
						
							<c:if test="${point.des==points[ind+1].des}">
											
							</c:if>
							<c:if test="${point.des!=points[ind+1].des}">
								<tr style="height: 26px;" valign="middle" >
									<td align="right" title="${point.des }" style="font-size: 12px;">
										<c:if test="${fn:length(point.des)>7}">
											${fn:substring(point.des,0,6) }...:
										</c:if>
										<c:if test="${fn:length(point.des)<=7}">
											${point.des }
										</c:if>
									</td>
									<td class="shishijiankongtai_clorg" align="left"><span id="${fn:replace(point.keyPoint,'.','__') }" ></span><label style=" display:none;">${eqobj.typeId }</label>${point.unit }</td>
								</tr>
							</c:if>
								
							</c:forEach>
						</c:if>						
					</table>
				</td>
			</c:forEach>
			<td></td>
		</tr>
	</table>	
	</div>
	
	<div id="error" style="position:absolute;top:1px;left: 6px;color:#FF0000;"></div>
</c:if>



<script  type="text/javascript">
       function Map(){
		    this.keys = new Array();
		    this.data = new Array();
		    //存值
			this.put = function(key,value){
			   if(this.data[key] == null){
			      this.keys.push(value);
			   }
			   this.data[key] = value;
			};
		    //取值
		    this.get = function(key){
		       return this.data[key];
		    };
		    //移除元素
		    this.remove = function(key){
		       this.keys.remove(key);
		       this.data[key] = null;
		    };
		    //判断是否为空
		    this.isEmpty = function(){
		       return this.keys.length == 0;
		    };
		    //集合的长度
		    this.size = function(){
		       return this.keys.length;
		    };
		 }

	var points ="";
    var eqTypeId="";
    var map=new Map();
	//获得当前点
	var getPoints=function(){
		var pSpan=$("span");
		for(var i=0;i<pSpan.length;i++){
			if("" != points){	
				points +=","
			}
			points += pSpan.get(i).id;
		}
		return points;
	}

	
	var onload=function(){
		points = getPoints();
		updatePointValue();
	}

	//同步数据 
	var updatePointValue=function(){
		var xmlDoc=$.ajax({
			  type: "POST",
			  url: "keyEqAction_showKeyPointValue.action?points="+points,
			  dataType: "json",
			  aysnc:false,
			  error: function(mydate){ 
				  $("#error").html("数据同步失败");
				  var pointArr = points.split(",");
				  for(var i = 0 ;i<pointArr.length;i++){	
					  for(var j = i+1 ;j<pointArr.length;j++){
						  var jt = $("#"+pointArr[j]).parent().prev().attr("title");
						  var it = $("#"+pointArr[i]).parent().prev().attr("title");
						
						  var jmid = $("td:last",$("tr:first",$("#"+pointArr[j]).parent().parent().parent())).attr("title");
						  var imid =  $("td:last",$("tr:first",$("#"+pointArr[i]).parent().parent().parent())).attr("title");
						  	if(jt==it&&jmid==imid){
						  		$("#"+pointArr[i]).parent().parent().remove();
						  	}	        	
				        } 		        	
			        } 
			  },
			  success: function(mydate){    
			       var points = mydate.points;
			       for(var i = 0 ;i<points.length;i++){			        	
				        var key = points[i].projectPoint;
				        var value = points[i].pointValue;
				        if(value=="FLAG"){
							$("#"+key).parent().parent().remove();
						}else{
							$("#"+key).html(value);
						}
				        $("#error").html("");		              
			          } 
			       }
			    });
		       setTimeout(updatePointValue,30000);
			};
	//	setTimeout(updatePointValue,5000);
</script>
</body>
</html>
