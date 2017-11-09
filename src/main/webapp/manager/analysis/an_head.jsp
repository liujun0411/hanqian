<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
	能源类型选择
	带参 power={1,2,3,4,5,all},默认为: power=2
	取值 Id=mypwer
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>医院后勤智能化管理平台</title>
	<script language="JavaScript">
		var oldobj = document.getElementById("dian");
		var imgpath = "manager/images/imgs/";
		var oldimg = "nengxiaofx_dian1.png";
	
		//选择能源
		chosepower = function(power) {
			if ("1" == power) {
				var obj = document.getElementById("shui");
				selectpower(obj, "1", "nengxiaofx_shui1.png", "nengxiaofx_shui2.png");
			} else if ("2" == power) {
				var obj = document.getElementById("dian");
				selectpower(obj, "2", "nengxiaofx_dian1.png", "nengxiaofx_dian2.png");
			} else if ("3" == power) {
				var obj = document.getElementById("you");
				selectpower(obj, "3", "nengxiaofx_you1.png", "nengxiaofx_you2.png");
			} else if ("4" == power) {
				var obj = document.getElementById("yyq");
				selectpower(obj, "4", "nengxiaofx_yyq1.png", "nengxiaofx_yyq2.png");
			} else if ("5" == power) {
				var obj = document.getElementById("gyq");
				selectpower(obj, "5", "nengxiaofx_gyq1.png", "nengxiaofx_gyq2.png");
			} else if("6" == power){
				var obj = document.getElementById("feng");
				selectpower(obj, "6", "nengxiaofx_feng1.png", "nengxiaofx_feng2.png");
			} else if ("0" == power) {
				var obj = document.getElementById("zh");
				selectpower(obj, "0", "nengxiaofx_zongh1.png", "nengxiaofx_zongh2.png");
			}
		}
		//点亮图标 
		selectpower =function(obj, key, img1, img2) {
			if (oldobj != null) {
				oldobj.src = imgpath + oldimg;//撤消原图片
			}
			obj.src = imgpath + img1; //更新当前图片			
			oldobj = obj; //设置当前对象
			oldimg = img2; //设置要撤消的图片
			
			document.getElementById("mypwer").value = key; //取值
			//alert(document.getElementById("mypwer").value);
		}
	
		//初始化
		onload = function() {
			var power = "${param.power}";
			if ("" == power || power.length < 1) {
				power = "2";
			}
			chosepower(power);
		}
		
	</script>
	</head>
	<body>
		<input type="hidden" id="mypwer"/>
		<!-- 能源类型 -->
		<div style="width: 100%;">
			<table width="100%" border="0" align="right" bgcolor="#EFEFEF">
				<tr valign="middle" style="height: 60" align="center" >
					<td>
						<img id="shui" src="manager/images/imgs/nengxiaofx_shui2.png"
							alt="水"
							onclick="selectpower(this,'1','nengxiaofx_shui1.png','nengxiaofx_shui2.png')"
							style='cursor: pointer' />
					</td>
					<td>
						<img id="dian" src="manager/images/imgs/nengxiaofx_dian2.png"
							alt="电"
							onclick="selectpower(this,'2','nengxiaofx_dian1.png','nengxiaofx_dian2.png')"
							style='cursor: pointer' />
					</td>
					<td>
						<img id="you" src="manager/images/imgs/nengxiaofx_you2.png"
							alt="石油"
							onclick="selectpower(this,'3','nengxiaofx_you1.png','nengxiaofx_you2.png');"
							style='cursor: pointer' />
					</td>
					<td>
						<img id="yyq" src="manager/images/imgs/nengxiaofx_yyq2.png"
							alt="医用气体"
							onclick="selectpower(this,'4','nengxiaofx_yyq1.png','nengxiaofx_yyq2.png')"
							style='cursor: pointer' />
					</td>
					<td>
						<img id="gyq" src="manager/images/imgs/nengxiaofx_gyq2.png"
							alt="天然气"
							onclick="selectpower(this,'5','nengxiaofx_gyq1.png','nengxiaofx_gyq2.png')"
							style='cursor: pointer' />
					</td>
					<td>
<%--						<img id="feng" src="manager/images/imgs/nengxiaofx_feng2.png"--%>
<%--							alt="风"--%>
<%--							onclick="selectpower(this,'6','nengxiaofx_feng1.png','nengxiaofx_feng2.png')"--%>
<%--							style='cursor: pointer' />--%>
					</td>
					<td>
<%--						<img id="zh" src="manager/images/imgs/nengxiaofx_zongh2.png" --%>
<%--							alt="综合"--%>
<%--							onclick="selectpower(this,'0','nengxiaofx_zongh1.png','nengxiaofx_zongh2.png')"--%>
<%--							style='cursor: pointer' />--%>
					</td>
				</tr>
			</table>
			<span class="admin_clor_f00">${message}</span>
		</div>

	</body>

</html>