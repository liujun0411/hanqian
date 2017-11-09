<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

			<title>用户信息- ${empty update?'用户添加':'用户修改'}</title>

			<meta http-equiv="pragma" content="no-cache"/>
			<meta http-equiv="cache-control" content="no-cache"/>
			<meta http-equiv="expires" content="0"/>
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
			<meta http-equiv="description" content="This is my page"/>
			<link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
			<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
			<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
			<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
			<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
	   
			<script type="text/javascript" 	src="manager/js/jquery-1.3.2.js"></script>
	       <script type="text/javascript" src="manager/js/jquery.validate.js"></script>

	        
<script language="JavaScript">
	
         
         //验证成功后事件 添加
         function addUserCheck(){
         	var hospId=document.getElementById('hospId');
         	var userid=document.getElementById('userid');
         	var username=document.getElementById('username');
         	var pwd1=document.getElementById('pwd1');
         	var pwd2=document.getElementById('pwd2');
         	
         	if(hospId.value==null ||hospId.value==''){
         		hospId.value=1;
         	}
         	if(userid.value==null || userid.value==''){
         		alert('请填写“用户名”');
         		userid.focus();
         		return false;
         	}
         	if(!checkUserid(userid.value)){
         		return false;
         	}
         	if(username.value==null ||username.value==''){
         		alert('请填写“用户姓名”');
         		username.focus();
         		return false;
         	}
         	if(pwd1.value=='' ){
         		alert('请填写“密码”');
         		pwd1.focus();
         		return false;
         	}
         	if(pwd2.value=='' ){
         		alert('请填写“确认密码”');
         		pwd2.focus();
         		return false;
         	}
         	if(pwd1.value!=pwd2.value){
         		alert('两次密码填写不一样');
         		pwd2.focus();
         		return false;
         	}

 
         	document.forms[0].submit();
         }
         
         
          //验证成功后事件
         function updateUserCheck(){
 
			var hospId=document.getElementById('hospId');
         	var username=document.getElementById('username');
         	var pwd1=document.getElementById('pwd1');
         	var pwd2=document.getElementById('pwd2');
         	
         	
         	 
         	if(username.value==null ||username.value==''){
         		alert('请填写“用户姓名”');
         		username.focus();
         		return false;
         	}
         	if(pwd1.value=='' ){
         		alert('请填写“密码”');
         		pwd1.focus();
         		return false;
         	}
         	if(pwd2.value=='' ){
         		alert('请填写“确认密码”');
         		pwd2.focus();
         		return false;
         	}
			if(pwd1.value!=pwd2.value){
         		alert('两次密码填写不一样');
         		pwd2.focus();
         		return false;
         	}

 
			var url = "user!updateUser.action";

			var form = $("#myform");
			form.attr("action", url)
			form.submit();

         }

         
 		function checkUserid(userid){
 			$.ajax({
	  			type:"POST",
		  		url:"user!checkUserid.action?userid="+userid+"&nd="+new Date().getTime(),
		  		success:function(result) {
		  			var json = eval('('+result+')');
		  			if(json==true){
		  				alert('该用户名无法使用!');
		  				document.getElementById('userid').focus();
		  				return false;
		  			}
		  		},
		  		error:function() {
		  			//alert("您点的太快了，请稍后再试！");
		  		}
	  		});
	  		return true;
 		}
	
		
 </script> 
	</head>
	<body>
	<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
			<table width="780" border="0">
					<tr style="height:30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							用户管理——    ${empty update?'用户添加':'用户修改'}
						</td>
					</tr>
				</table>
			</div>
			
			<s:form action="user!addUser.action"  method="post" id="myform" theme="simple" name="addBuildingFrom">
				
				<input type="hidden" name="dbUsers.oper" value="${empty sessionScope.users?'':sessionScope.users.seq}" />
				<input type="hidden" name="dbUsers.seq" value="${dbUsers.seq}"/>		
				<table width="770" border="0" cellspacing="1" class="gai_left_duiqi">
					
					<c:if test="${empty update}">
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f" style="display: none;">
						<td class="admin_bgclor_e3f admin_zt_14">
							医院<span class="admin_clor_f00" >*</span>
						</td>
						<td class="admin_bgclor_f1f">												
							<select name="dbUsers.dbHospInfo.seqHosp"  id="hospId";>
								<c:forEach items="${hospAll}" var="h">
									<option value="${h.seq_hosp}" > ${h.hosp_name}     </option>
								</c:forEach>
							</select>
						</td>
						<td class="admin_bgclor_f1f">
							 
						</td>
						<td class="admin_bgclor_f1f">
							 
						</td>
					</tr>
					</c:if>
					
					<c:if test="${!empty update}">
						<input type="hidden" name="dbUsers.dbHospInfo.seqHosp" value="${dbUsers.dbHospInfo.seqHosp}"/>
					</c:if>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
					<td class="admin_bgclor_e3f admin_zt_14">
							用户名<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">												
							<input type="text" id="userid" name="dbUsers.userid" value="${dbUsers.userid}"
							onkeyup="value=value.replace(/[\W]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
							onblur="checkUserid(this.value)"  maxlength="50"
							 ${!empty update?'disabled="disabled"':''}/>
							
						</td>
						<td class="admin_bgclor_e3f admin_zt_14">
							姓名<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">												
							<input type="text" id="username" name="dbUsers.username" 
							 value="${!empty update?dbUsers.username:''}"/>
	
						</td>
					</tr>
					
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
						<td class="admin_bgclor_e3f admin_zt_14">
							密码<span class="admin_clor_f00" >*</span>
						</td>
						<td class="admin_bgclor_f1f">												
							<input type="password" id="pwd1" name="dbUsers.password" value=""/>
						</td>
						<td class="admin_bgclor_e3f admin_zt_14">
							密码确认<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="password" id="pwd2" name="" value=""/>
						</td>
					</tr>
					
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff" >
						<td class="admin_bgclor_e3f admin_zt_14" >
							手机
						</td>
						<td class="admin_bgclor_f1f" >
							<input type="text" id="phone" name="dbUsers.phone" value="${dbUsers.phone}"/>
						</td>
						<td class="admin_bgclor_e3f admin_zt_14" > 
							邮箱
						</td>
						<td class="admin_bgclor_f1f" >
							<input type="text" id="email" name="dbUsers.email" value="${dbUsers.email}"/>
						</td>
					</tr>
						<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff" >
						<td class="admin_bgclor_e3f admin_zt_14" >
							电话1
						</td>
						<td class="admin_bgclor_f1f" >
							<input type="text" id="tel1" name="dbUsers.tel1" value="${dbUsers.tel1}"/>
						</td>
						<td class="admin_bgclor_e3f admin_zt_14" > 
							电话2
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="tel2" name="dbUsers.tel2" value="${dbUsers.tel2}"/>
						</td>
					</tr>
					</tr>
						<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff" >
						<td class="admin_bgclor_e3f admin_zt_14" >
							家庭地址
						</td>
						<td class="admin_bgclor_f1f" >
							<input type="text" id="address1" name="dbUsers.address1" value="${dbUsers.address1}"/>
						</td>
						<td class="admin_bgclor_e3f admin_zt_14" > 
							公司地址
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" id="address2" name="dbUsers.address2" value="${dbUsers.address2}"/>
						</td>
					</tr>
				
				

				</table>
				<table width="770" border="0" class="gai_left_duiqi">
					<tr>
						<td></td>
						<td  width="65"><!--a href="javascript:f_check();" class="btn blue">下一步</a-->	
						<td width="60">
						<c:if test="${empty update}">
						<a onclick="addUserCheck();" class="btn blue">添  加</a>
						</c:if>
						<c:if test="${!empty update}">
						<a onclick="updateUserCheck();" class="btn blue">修  改</a>
						</c:if>
						</td>									
						</td>
						<td  width="65"><a href="javascript:history.go(-1);" class="btn blue">取 消</a>																
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
