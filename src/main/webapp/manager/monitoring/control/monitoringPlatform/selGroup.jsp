<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>医院后勤智能化管理平台</title>
	<link href="manager/css/shishijiankong.css" rel="stylesheet" type="text/css" />
	<script src="manager/js/tdPage.js" type="text/javascript"></script>	
	<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
	</head>
	<body style="margin-top: 0px;margin-left: 0px;">
	<div class="shishijiankongtai_41" style="height: 29px;">
	<table width="165"  align="left" border="0" style=" background: url(manager/images/common/29-01.png) no-repeat; height: 29px; margin-top: 8px;">
		  <tr >
		    <td style=" padding-left:5px; width:20px;" valign="top">
		       <img src="manager/images/common/28-01.png" />
		    </td>
		    <td valign="top" style="font-family: '黑体'; font-size: 13px; color: white; ">关键设备</td>
		  </tr>
		</table>
	
	<table border="0" align="right" id="table1">
	<tbody>
	<tr id="tr1">	
	<c:if test="${groupList != null}">		
		<c:forEach items="${groupList}" var="obj"  varStatus="idx">
		 	<c:if test="${idx.first}">
		 		<c:set var="firstgroup" value="${obj.groupId }" />
		 	</c:if>
		 	<td>
			 	<div id="${obj.groupId }" class="shishijiankongtai_313 shishijiankongtai_313_zt" style="cursor: pointer;" onclick="selMy(this,'${obj.groupId }');" title="${obj.groupName }">
			 		<c:if test="${fn:length(obj.groupName)>4}">
								${fn:substring(obj.groupName,0,2) }...
					</c:if>
					<c:if test="${fn:length(obj.groupName)<=4}">
						${obj.groupName }
					</c:if>
			 	</div>
		 	</td>
		</c:forEach>		
	</c:if>	
	</tr>
	</tbody>
	</table>
	</div>
	
	
	
	<script type="text/javascript">
		var mytr = new Page(10,"table1","tr1","mytr","hideDiv");

		hideDiv=function(){
			var divs=$("div");
			for(var i=0;i<divs.length;i++){
				if(divs.get(i).className.indexOf("shishijiankongtai_314")!=-1){
					divs.get(i).className="shishijiankongtai_313 shishijiankongtai_313_zt";
				}
			}
		}
		selMy=function(obj,groupid){
			var className = obj.className ;
			
			if(className.indexOf("shishijiankongtai_314") == -1){
				hideDiv();			
				obj.className ="shishijiankongtai_314 shishijiankongtai_313_zt";				
                $(obj).addClass("currented");
				//更新当前组关键设备
				try {
					window.parent.document.getElementById("keyEqCollect").src="keyEqAction_showKeyEqCollect.action?groupId="+groupid;
				} catch (e) {
					
				}				
			}
		}

		
		onload=function(){
			try{
				var groupid="${firstgroup}";
				oldobj = document.getElementById(groupid);							
				selMy(oldobj,groupid);					
			}catch(e){}
		}
	</script>
</body>
</html>