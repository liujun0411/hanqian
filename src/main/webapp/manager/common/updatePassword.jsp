<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String useridNusem = request.getParameter("useridNusem"); 
	useridNusem = new String(useridNusem.trim().getBytes("ISO-8859-1"),"UTF-8");  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>用户密码修改</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/jquery.validate.js">
</script>

		<script language="JavaScript">

onload=function(){
   var str=$("#useridNusem").val();
   var strs= new Array(); 
   strs=str.split("|"); //字符分割
   $('#userid').val(strs[0]);
   $('.user_name').eq(0).text(strs[1]);
 }

 function updatePassword(){
	 
	 //密码和确认密码不为空
	 if($.trim($('#oldPassword').val())=="" || $.trim($('#newPassword').val())==""  || $.trim($('#newSurePwd').val())==""){
			alert('密码不能为空！');
		 return;
	 }
 
	 //新密码和确认密码是否一致
	 if($.trim($('#newSurePwd').val())!=$.trim($('#newPassword').val())){
		 alert('新密码输入不一致！ ');
		 return ;
	  } 
	 //判断原密码是否正确
	 $.ajax({
	       url:"user!checkPassword.action?userid="+$("#userid").val()+
	                                      "&oldPassword="+$("#oldPassword").val()+"&newPassword="+$("#newPassword").val()+"&t="+Math.random(),
	       type:"post",
	       dataType:"text/html",
	       success: function (data) {
		 	 var res=data;
		 		if(res == "y"){
	       			alert("密码修改成功，请重新登录！");
					  //调用父窗口
					  var parent= window.opener;
			          parent.windowOpen();
			          window.close();
	       			//window.returnValue = res;
	       			//window.close();
	       			
		 		}else{
		 			alert("修改密码失败，请确认原密码是否正确！");
		 		}
		   },     
	       error:function(dt){
	       }
	     });
	
 }

</script>
	</head>
	<body  style="overflow-x: hidden">
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1" style="float: none">
				<table width="300" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="300" class="biaoti_zt_canshusz">
							用户密码修改
						</td>
					</tr>
				</table>
			</div>
			<div style="height: 100px; width: 300px;">
			</div>

			<s:form action="user!addUser.action" method="post" id="myform"
				theme="simple" name="addBuildingFrom">

					<input type="hidden" name="userid" id="userid"  />
                    <input type="hidden" name="useridNusem" id="useridNusem"  value="<%=useridNusem%>"/>
<%--				<input type="hidden" name="dbUsers.seq" value="${dbUsers.seq}" />--%>
				<table width="100%" border="0" cellspacing="1" >


					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
						<td class="admin_bgclor_f1f" colspan="2" align="center">
								<span class="user_name"></span>
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
						<td class="admin_bgclor_e3f admin_zt_14" align="right">
							原密码
							<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="password" id="oldPassword" name="oldPassword"  />
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
						<td class="admin_bgclor_e3f admin_zt_14" align="right">
							新密码
							<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="password" id="newPassword" name="newPassword"/>
						</td>
					</tr>
					<tr>
						<td class="admin_bgclor_e3f admin_zt_14" align="right">
							新密码确认
							<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="password" id="newSurePwd" name="newSurePwd"/>
						</td>
					</tr>
					<tr >
						<td colspan="1" />
						<td align="center" colspan="1">
							<a class="btn blue"  onclick="updatePassword();" style="float:left;margin:5px 5px 5px 5px;">修 改</a>
							<a onclick="window.close()" style="float:left;margin:5px 5px 5px 15px;" class="btn blue">取 消</a>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
