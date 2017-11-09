<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C// DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
		<script src="manager/js/xuanze.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
		<script type="text/javascript">
			
			function submitForm(formName,urlName){
				var str="";
				jQuery("input:checked").each(function(){
					if(this.checked){
					str+=this.value+",";
				}});
				
				if(str==""){ 
					alert('请选择要上报的信息');return;
				}
				
				str=str.substring(0,str.length-1);
				
				alert('稍后请查看上报日志');
				var myform=document.getElementById("myform");
				
				myform.action=urlName+"?typeid="+str;
				myform.submit();
				
				}
		</script>
	</head>
	<body style="overflow:scroll;overflow-x:hidden;overflow-y:hidden;">

		<div id="admin_nr_rightg">
		
		   <div class="canshusz_btbg_1">
			<table width="780" border="0">
					<tr style="height:30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="600" class="biaoti_zt_canshusz">
							临时上报
						</td>		
					</tr>
				</table>
			</div>
		<form id="myform" action="" method="post" name="reportForm">
			<div class="shujusb_xinxi">
				<div class="shujusb_xinxi_2 shujujk_xx_zt1"><input name="typeid" type="checkbox"  value="1" />医院的静态信息</div>
				<div class="shujusb_xinxi_2 shujujk_xx_zt1"><input name="typeid" type="checkbox"  value="2" />动态信息的统计值</div>
				<div class="shujusb_xinxi_2 shujujk_xx_zt1"><input name="typeid" type="checkbox"  value="3" />医院总体能效使用信息</div>
				<div class="shujusb_xinxi_2 shujujk_xx_zt1"><input name="typeid" type="checkbox"  value="4" />特征区域能效使用信息</div>
			</div>
		</form>
<%--	<table width="95%" border="0" align="left" >--%>
<%--	    <tr height="30" >--%>
<%--	      <td width="85%"  ></td>--%>
<%--	      <td width="10%" align="left"><a href="javascript:void(0);" class="btn blue"--%>
<%--	       onclick="submitForm('reportForm','reportTemporary_addTempReport.action');" >保存</a></td>--%>
<%--	    </tr>--%>
<%--    </table>--%>
    <table width="780" border="0" class="gai_left_duiqi">
		<tr>
			<td width="232"></td>
			<td width="371"></td>
			<td>
				<a href="javascript:void(0);" class="btn blue"  onclick="submitForm('reportForm','reportTemporary_addTempReport.action');">保存</a>
			</td>
		</tr>
	</table>


	</div>
	</body>
</html>
