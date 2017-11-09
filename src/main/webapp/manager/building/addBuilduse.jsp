<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=utf-8");
response.setCharacterEncoding("utf-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String lc = request.getParameter("lc"); 
String qy = new String( request.getParameter("qy").getBytes("ISO-8859-1"),"UTF-8"); 
String mj = request.getParameter("mj");
String bz =  new String( request.getParameter("bz").getBytes("ISO-8859-1"),"UTF-8"); 
String seq =  new String( request.getParameter("seq").getBytes("ISO-8859-1"),"UTF-8"); 
String dt = request.getParameter("dt");
String areaId = request.getParameter("areaaId");
String buildId = request.getParameter("buildId");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>楼宇区域添加</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<script src="manager/js/My97DatePicker/WdatePicker.js"
			type="text/javascript"></script>
	<link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
	<script src="manager/js/annu.js" type="text/javascript"></script>
	<script src="manager/js/reqi.js" type="text/javascript"></script>
	<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
<style type="text/css">
 *{
   font-size: 12px;
 }
 
 .biaoti{
	font-size: 14px;
	font-weight: bolder;
	color: #0066CC;
	
}
</style>
<script type="text/javascript">
 
  f_close=function()
  {
     window.close();
  }
	function sumAmount() {
		if (objisNull("recodeDate", "请选择记录日期")) {
		} else {
			return;
		}
	}

  onload=function(){
  var lc =<%=lc%>;
   if(lc>0){
    	$("#cen").text("地上"+lc+"层");
   	}else {
   		 $("#cen").text("地下"+lc+"层");
   }  
  }
  
  $(function(){
    $('#date').focus(function(){ 
    	var dt=$("#date").val();
    	var year=dt.substring(0,4);
    	var month=dt.substring(4);
    	var dt=year+"-"+month;
      WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM',minDate:dt, maxDate:'%y-%M'})  
    });
  });
</script>
<script type="text/javascript">
	function updateInfo(){
		 if($("#date").val()==""){
        alert("请选择记录日期！");
        $("#date").focus();
        return;
   		}
    var mj=$("#mj").val();
    var bz=$("#bz").val();
    var dt=$("#date").val();
    var seq=$("#seq").val();
    var buildingId=$("#buildingId").val();
    var areaId=$("#qyid").val();
     var storey =<%=lc%>;
  //  window.returnValue=$("#mj").val()+"|"+jQuery.trim($("#bz").val())+"|"+$("#seq").val()+"|"+$("#cen").val()+"|"+dt+"|"+areaId;
    var params={"mj":mj,"bz":bz,"seq":seq,"dt":dt,"buildingId":buildingId,"areaId":areaId,"storey":storey};
    $.ajax({
       url:"buildStorey!updateStorey.action?t="+Math.random(),
       type:"post",
       dataType:"json",
       data: params,
       success: function (data) {
          parent.document.location.reload();
       },
       error:function (dt){
         alert("内部服务器错误，请稍后再试！");
       }
    });
    window.close();
	}
</script>
  </head>
  <body >
  <div id="admin_nr_jbxx">
  <form name=myform action="" method="post">
  <table width="" style="height: 70px;" border="0" cellpadding="0" cellspacing="0">
  <tr style="height: 30px;">
    <td colspan="2"  class="biaoti" align="center"><label id="buildingName" class="biaoti"></label>—<label id="cen" class="biaoti"></label></td>
  </tr>
  <tr>
   <td class="admin_bgclor_e3f"><label id="qy"></label></td>
   <td class="admin_bgclor_f1f"><input type="text" name="mj" value="<%=mj%>" id="mj" maxlength="7" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/>&nbsp;平方米</td>
  </tr>
  <tr>
     <td class="admin_bgclor_e3f"> 备注：</td>
   <td  class="admin_clor_f00"><textarea rows="6" cols="20" onkeydown= "if(this.value.length> 224)return false;" name="bz" id="bz" ><%=bz%></textarea>
    <input type="hidden" id="seq" name="seq" value="<%=seq%>"/>
    <input type="hidden" id="buildingId" name="buildingId" value="<%=buildId %>"/>
    <input type="hidden" id="qyid" name="qyid" value="<%=areaId %>"/>
   </td>
  </tr>
  
   <tr>
     <td class="admin_bgclor_e3f" width="120px"> 记录日期：<span class="admin_clor_f00">*</span></td>
     	<td align="left"  width="200">
     		<input type="text" name="recordDate" id="date" size="6"
								maxlength="6" class="Wdate" onkeyup="sumAmount();"
								value="<%=dt%>"/>			
   </td>
  </tr>
  <tr style="height: 30;">
  <td>
  </td>
  <td width="">
  <a href="javascript:f_close()" class="btn blue" >取消</a><a href="javascripts::" class="btn blue"  onclick="updateInfo()">确定</a>
  </td>
　</tr>
	</table>
	 </form>
	 </div>
  </body>
</html>
