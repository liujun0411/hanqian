<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>医院后勤智能化管理平台</title>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
		<link href="manager/css/login.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<script type="text/javascript">
	function login(e) {
		var evt = e ? e : (window.event ? window.event : null); //此方法为了在firefox中的兼容
		var node = evt.srcElement ? evt.srcElement : evt.target; //evt.target在火狐上才能识别用的。
		//selectNode = node.getAttribute("nodeId").toString(); 
		var name = document.getElementById("username").value;
		var pass = document.getElementById("password").value;
		var exs = "^\\w{2,10}$";
		var ex = new RegExp(exs);
		if (name.search(ex) == -1) {
			alert('用户名格式不正确!');
		} else if (pass.search(ex) == -1) {
			alert('密码格式不正确!');
		} else {
			$('#form1').attr({action:$('img.rdo.selected').eq(0).next().attr('path')});
			document.getElementById("form1").submit();
		}

	}
	function loadme() {
		var message = "${message }";
		if (message != "") {
			alert(message);
		}
		//加入了医院简编的判断，当是医院是仁济（东南西北）时，则可做选择。其他则直接登录
<%--		$.ajax( {
			type : "POST",
			url : "hospInfoAction!judgeCurrentHosp.action",
			cache : false,
			async : false,
			dataType : "json",
			error : function(jsonObj) {
			},
			success : function(jsonObj) {
				var currentHospCode = jsonObj.currentHospCode;
				if(currentHospCode!='RJ'&&currentHospCode!='RJB'&&currentHospCode!='RJX'&&currentHospCode!='RJN'){
					$("#tr_hosp_login td:eq(0)").hide();
					$("#tr_hosp_login td:eq(1)").hide();
				}else{
					$("#tr_hosp_login td:eq(0)").show();
					$("#tr_hosp_login td:eq(1)").show();
				}
			}
		});--%>
		$.ajax( {
			type : "POST",
			url : "hospInfoAction!judgeCurrentHosp.action",
			cache : false,
			async : false,
			dataType : "json",
			error : function(jsonObj) {
			},
			success : function(jsonObj) {
				var currentHospCode = jsonObj.currentHospCode;
				var imgSrc = "manager/images/common/hospLogo/"+currentHospCode+"_logo.png";
				$("#hosp_img").attr("src",imgSrc);
			}
		});
	}

	document.onkeydown = function(event) {
		e = event ? event : (window.event ? window.event : null);
		if (e.keyCode == 13) {
			login(event);
		}
	}
	
	$(function(){
	   $('img.rdo').click(function(){
	      $('img.rdo').removeClass('selected');
	      $('img.rdo').attr({src:"manager/images/common/radio.png"});
	      $(this).attr({src:"manager/images/common/radioSel.png"});
	      $(this).addClass('selected');
	   });
	   var high=window.screen.availHeight;
	   $('#div_main_body').css({height:high-130});
	   $('#div_main_body').css({overflow:'hidden'});
	});

	window.onresize = function () {
	   var high=window.screen.availHeight;
	   $('#div_main_body').css({height:high-130});
	   $('#div_main_body').css({overflow:'hidden'});
	}	
	
</script>
	</head>
	<body onload="loadme();">
		<div id="div_main_body" class="div_main_body">
			<div id="login_body">
				<div id="login_Logo">
					<table>
						<tr>
							<td valign="top" class="td_logo">
								<img id="hosp_img" src="" alt="" />
							</td>
							<td class="td_title">
								<img src="manager/images/common/projectLogo.png" alt="" />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--登陆框-->
			<div id="login_entrance" class="main">
				<div id="login" class="login_bg">
					<s:form id="form1" name="form1" method="post"
						action="userLogin.action">
						<table class="login_table" align="center" width="435">
							<tr align="center">
								<td colspan="2" class="login_top">
									<img src="manager/images/common/14.png" />
								</td>
							</tr>
							<tr>
								<td width="25%" align="right">
									<img src="manager/images/common/12.png" />
								</td>
								<td align="left" class="login_td">
									<input class="login_input" type="text" id="username"
										name="dbUsers.userid" value="" maxlength="20" />
								</td>
							</tr>
							<tr>
								<td width="25%" align="right">
									<img src="manager/images/common/13.png" />
								</td>
								<td align="left" class="login_td">
									<input class="login_input" type="password" id="password"
										name="dbUsers.password" value="" maxlength="20" />
								</td>
							</tr>
							<tr>
								<td align="right" class="login_btntd" colspan="2" valign="top">
								    <table border="0" style=" width:70%;">
								       <tr id="tr_hosp_login">
								          <td align="right"><img src="manager/images/common/10.png" onclick="login(event);"/></td>
								       </tr>
								    </table>
								</td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
			<!--脚注部分-->
			<div id="login_bottom" class="footer">
				<iframe src="manager/common/bottom.jsp" scrolling=no width=100%
						height=80 marginwidth=0 marginheight=0 frameborder=0 name="buttom"></iframe>
			</div>
		</div>
	</body>
	
</html>
