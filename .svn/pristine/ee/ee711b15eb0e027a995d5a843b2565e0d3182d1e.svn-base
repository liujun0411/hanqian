<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link href="manager/css/neirong.css" rel="stylesheet"	type="text/css" />
	<style type="text/css">
		td{font-size: 12px;}
	</style>
	<script language="JavaScript">
		var equipId="";
		var eqname="";
		onload=function(){
			try{
				document.getElementById("eqstr").value=window.parent.parent.document.getElementById("eqlist_str").value;
			}catch(e){}
		}
		onover=function(obj){
			if("#3399ff" !=obj.style.color && "#3399FF" !=obj.style.color){
				obj.style.color="#FF6600";
			}
		}
		oncli=function(obj){
			if(document.getElementById(obj.htmlFor).checked){
				obj.style.color="#3399FF";
			}else{
				obj.style.color="#000000";
			}
		}
		onout=function(obj){
			if("#3399ff" !=obj.style.color && "#3399FF" !=obj.style.color){
				obj.style.color="#000000";
			}
		}


		//设备关键设备点位
		selEqPoint=function(equipId,eqname){
		this.equipId=equipId;
		this.eqname=eqname;
			//var returnValue=window.showModalDialog('manager/keyEqAction_showPointList.action?equipId='+equipId,null,'center:yes;dialogHeight:230px;dialogWidth:380px;status:no;');
			var returnValue=window.open("manager/keyEqAction_showPointList.action?equipId="+equipId,'',"height=320, width=380, toolbar =no, menubar=no, left=450,top=20,scrollbars=no, resizable=no, location=no, status=no");
			//window.location.href="actionName.action?id=parm&password=parm";
		}
	  function vodereturnValue(returnValue){
					if(returnValue !=null && returnValue.length>0){
									//更新已有设备
									updateKeyEq(equipId,returnValue,eqname);
								}
			}
		//更新已有设备
		updateKeyEq=function(equipId,keypoints,eqname){
			var eqlist=document.getElementById("eqstr").value;
			var eqs = eqlist.split(";");
			var roweq;
			var temp;
			var isadd=true;
			for(var i=0;i<eqs.length;i++){
                roweq=eqs[i];
                temp=roweq.split("=");

                //更新
                if(equipId==temp[0]){
                	eqlist=eqlist.replace(roweq,equipId+"="+keypoints);
                	isadd =false;
                	document.getElementById("eqstr").value =eqlist;				
                	break;
                }
			}
			if(isadd){
				if(eqs.length>5){
					alert("最多只能添加6个设备");
					return ;
				}
				
				//新增
				if(eqlist!=""){
					eqlist +=";";
				}
				eqlist+=equipId+"="+keypoints;

				try{
					var odiv=window.parent.parent.document.getElementById("mydiv");
					var str='<div id="v'+equipId+'" style="font-size: 12;color:#3399FF;" onmouseover="this.style.color=\'#FF6600\';" onmouseout="this.style.color=\'#3399FF\';"><table width="100%" border="0" ><tr><td>'+eqname
						+'</td><td width="30"><img src="manager/images/imgs/icon_del.gif" width="12" height="12" border=0 title="删除" style="cursor: pointer;" onclick="try{document.getElementById(\'mydiv\').removeChild(document.getElementById(\'v'+equipId+'\'));removeKeyEq(\''+equipId+'\');}catch(e){}" /></td></tr></table></div>';
	            		odiv.innerHTML +=str;            		
				}catch(e){};
			}

			document.getElementById("eqstr").value =eqlist;	
			//保存已有设备
			try{
				window.parent.parent.document.getElementById("eqlist_str").value=eqlist;
			}catch(e){}
		}
	//-->
	</script>
</head>
<body>	
	
	<!-- 设备选择 -->	
	<input type="hidden" id="eqstr"/>
	
	<c:if test="${eqList !=null}">
		<div style="font-size: 12;width: 100%;height: 246px;overflow:auto;">
		<table  width="100%" border="0" cellspacing="1"  align="center" >
			<tbody >	
			<c:forEach items="${eqList}" var="obj" varStatus="at">
				<tr><td width="8">${at.index }</td>
				<td class="biao_lianj_1"  >
				<a href="javascript:void(0);" 
					onclick="selEqPoint('${obj.equipId}','${obj.name }')">${obj.name }</a>
				</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
	</c:if>
	
	
	
</body>
</html>