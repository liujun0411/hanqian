<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>

		<title>医院后勤智能化管理平台</title>

		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>     
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<meta http-equiv="description" content="This is my page"/>
		
		<link href="manager/css/neirong.css" rel="stylesheet"	type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />		
		<script src="manager/js/annu.js" type="text/javascript"></script>	
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript" ></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<style type="text/css">
		<!--
		div {
			/*border: 1px solid #0066FF;*/
		}
		-->
		</style>
		<script type="text/javascript">

			//表单提交
			goto_area= function(){	
				//医院ID
				try{
					var selUnits = window.top.frames["hospTree"].$("#selectIds").val();					
					if(selUnits.length>0){
						var units = selUnits.split(",");
						var unitType =units[0].substring(units[0].lastIndexOf("|")+1);					
						
						if ("yiyuan"==unitType) {
							var menuids = "";
							for (var i = 0; i < units.length; i++) {
								var tmp = units[i];
								if(i !=0 ){
									menuids +=",";
								}
								menuids += tmp.substring(0,tmp.indexOf("|"));					
							}	
							$("#selUnits").attr("value",menuids);	
						}
					}	
				}catch(e){}	
				window.formSelect.submit();				
			}
			
			//用户登录失效
			onload=function(){
				if("${isfail}" != ""){
					alert("请重新登陆！");
					window.top.location="user!closeUsers.action";
				}
			}	


			//更新比例
			submyfrom=function(){
				var mz=parseFloat($("#mz").val());
				var jz=parseFloat($("#jz").val());
				var zy=parseFloat($("#zy").val());
				var yj=parseFloat($("#yj").val());
				var bz=parseFloat($("#bz").val());
				var xz=parseFloat($("#xz").val());
				var ky=parseFloat($("#ky").val());
				var jy=parseFloat($("#jy").val());
				var sh=parseFloat($("#sh").val());
				var ck=parseFloat($("#ck").val());
				var qt=parseFloat($("#qt").val());
				var str='{"mz":"'+mz+'","jz":"'+jz+'","zy":"'+zy+'","yj":"'+yj+'","bz":"'+bz+'","xz":"'+xz+'","ky":"'+ky+'","jy":"'+jy+'","sh":"'+sh+'","ck":"'+ck+'","qt":"'+qt+'"}';
				if( (mz+jz+zy+yj+bz+xz+ky+jy+sh+ck+qt)!=100 ){
					alert("各区域标准比例总和必须等于100%!");
				}else{
					$.ajax({
						type: "POST",
						url: "rate_editBuildingrate.action",
						data: "obj="+str,
						error: function(mydate){    
							alert("数据连接失败,请联系管理员!");
							return;		 
						},
						success: function(mydate){ 
							//$("#name").attr("value",mydate);
						   if(mydate=="1"){
								alert("操作成功!");								
						   }else{
							   alert("操作失败!");
						   } 
						   return ;  
						}
					}); 
				}
			}

		</script>
	</head>
	
	
	
	<body>
	<!-- 查询 -->			
	<form method="post" action="rate_showBuildingrate.action" name="formSelect" id="formSelect">
		<input type="hidden" name="selUnits"  id="selUnits"  />
	</form>	
	<!--页面主内容开始 -->
    <div id="admin_nr_rightg">    
		<div class="canshusz_btbg_1">
			<table width="100%" border="0">
				<tr style="height:30px;" valign="middle">
					<td width="34" align="center">
						<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
					</td>
					<td width="679" class="biaoti_zt_canshusz">
						<c:if test="${roleMessage=='2' || roleMessage=='3' || roleMessage=='5'}">基准比例——区域比例设置</c:if>
						<c:if test="${roleMessage!='2' && roleMessage!='3' && roleMessage!='5'}">面积比例情况——区域比例</c:if>						
					</td>
				</tr>
			</table>
		</div>
		
	
	<div>
		<c:if test="${editFlag=='2'}">	
		<table width="780" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
			<tr style="height:40">
				<td></td>				
				<td width="60" >
				    <a  onclick="submyfrom()" class="btn blue">保存</a>				   
				</td>					
			</tr>
		</table>
		</c:if>
	</div>	
	
	
		
	<div>
	
	<table width="780" border="0" cellspacing="1" class="biaoge_bg1 gai_left_duiqi" align="center">	
		<caption><span class="admin_jbxx_bt1">标准比例</span> </caption>	
		<thead>
		<tr class="biaoge_tr0 biaog_kan1 biaog_zt1" align="center" style="height:26px;">
			<td >门诊</td>
			<td >急诊</td>
			<td >住院</td>
			<td >医技</td>
			<td >保障</td>
			<td >行政</td>
			<td >科研</td>
			<td >教育</td>
			<td >生活</td>
			<td >车库</td>
			<td >其它</td>
		</tr>
		</thead>
		<tr style="height:10px;background-color: #FFFFFF;" >
			<td align="center" bgcolor="#D99594"></td>
			<td align="center" bgcolor="#66FF66"></td>
			<td align="center" bgcolor="#FFFF00"></td>
			<td align="center" bgcolor="#66CCFF"></td>
			<td align="center" bgcolor="#FF6699"></td>
			<td align="center" bgcolor="#00FF99"></td>
			<td align="center" bgcolor="#990033"></td>		
			<td align="center" bgcolor="#FF0066"></td>
			<td align="center" bgcolor="#FF9933"></td>
			<td align="center" bgcolor="#3366FF"></td>
			<td align="center" bgcolor="#7030A0"></td>			
		</tr>
		<tbody id="stu-datas-tb2" style="background-color: #FFFFFF"> 

		<c:if test="${editFlag!='2'}">
		<tr align="left" valign="middle" class=" biaog_kan2 biaog_zt2" align="center">
			<td align="center">${obj.bdata.mz.datavalue }%</td>
			<td align="center">${obj.bdata.jz.datavalue }%</td>
			<td align="center">${obj.bdata.zy.datavalue }%</td>
			<td align="center">${obj.bdata.yj.datavalue }%</td>
			<td align="center">${obj.bdata.bz.datavalue }%</td>
			<td align="center">${obj.bdata.xz.datavalue }%</td>
			<td align="center">${obj.bdata.ky.datavalue }%</td>		
			<td align="center">${obj.bdata.jy.datavalue }%</td>
			<td align="center">${obj.bdata.sh.datavalue }%</td>
			<td align="center">${obj.bdata.ck.datavalue }%</td>
			<td align="center">${obj.bdata.qt.datavalue }%</td>			
		</tr> 
		</c:if>
		<c:if test="${editFlag=='2' }">
		<tr align="left" valign="middle" class=" biaog_kan2 biaog_zt2" align="center">
			<td align="center"><input id="mz" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.mz.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>
			<td align="center"><input id="jz" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.jz.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>
			<td align="center"><input id="zy" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.zy.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>
			<td align="center"><input id="yj" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.yj.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>
			<td align="center"><input id="bz" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.bz.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>
			<td align="center"><input id="xz" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.xz.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>
			<td align="center"><input id="ky" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.ky.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>		
			<td align="center"><input id="jy" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.jy.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>
			<td align="center"><input id="sh" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.sh.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>
			<td align="center"><input id="ck" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.ck.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>
			<td align="center"><input id="qt" type="text" class="yiyuanjbxx_inp_nr2" style="width:35px;" maxlength="5" value="${obj.bdata.qt.datavalue }" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" onfocus="this.select();"/>%</td>			
		</tr>
		</c:if>
		</tbody>
	</table>
	
		
	</div>
	
	
	
	<c:if test="${editFlag!='2'}">
    <div>
    <!-- 表格区 -->
	<table width="780" border="0" cellspacing="1" class="biaoge_bg1 gai_left_duiqi" align="center">
		<caption><span class="admin_jbxx_bt1">${obj.tdata.title }</span> </caption>
		<thead>
		<tr class="biaoge_tr0 biaog_kan1 biaog_zt1" align="center">
			<c:forEach items="${obj.tdata.keys}" var="key">
			<td>${key }</td>
			</c:forEach>
		</tr>
		</thead>
		<tbody id="stu-datas-tb">
		<c:forEach items="${obj.tdata.values}" var="obj">
			<tr class=" biaog_kan2 biaog_zt2" align="center">
				<c:forEach items="${obj}" var="rowData" varStatus="s">
				<td>${rowData }<c:if test="${!s.first}">%</c:if></td>
				</c:forEach>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
    </c:if>
    
    
  </div>
  
  
	</body>
</html>