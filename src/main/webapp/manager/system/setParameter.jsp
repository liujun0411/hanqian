<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>设置首页参数</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	    <link rel="stylesheet" type="text/css" href="styles.css">
	    -->
		<script src="manager/js/My97DatePicker/WdatePicker.js"
			type="text/javascript">
</script>
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="manager/common/tab/ui.tabs.css"
			type="text/css"></link>
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
		<script type="text/javascript">

function doOnload() {
	$.get('manager/monitoring/control/electricity/config/targetConfig.xml?t=' + Math.random(),function(d){
		$(d).find('electConfig').find('energysave').each(function() {
			$('#energyYears').val($(this).find('statTime').text());
		})
	  
	   $(d).find('electConfig').find('transformer').each(function() {
			var vals=$(this).find('quantum').text();
			var list=new Array();
			list=vals.split(',');	
			$('#valJiange').val(list[0]);	
			$('#valUnit').val(list[1]);
			if(list[1]=="day"){
			   $('#valJiange').addClass('inputRead');	
			}
	   })
	   
	   $(d).find('electConfig').find('safesave').each(function() {
		    //$('#criterionval').val($(this).find('criterionval').text());
			/* edit start 2013.4.16 by zhangdiannan
			       将参数以百分比展现*/
			$('#criterionval').val($(this).find('criterionval').text()*100);
			/* edit end 2013.4.16 by zhangdiannan */
		    $('#safetyStartTime').val($(this).find('startTime').text());
			$('#safetyEndTime').val($(this).find('endTime').text());
	   })
	   
	   $(d).find('electConfig').find('history').each(function() {
			$('#safetyHisStartTime').val($(this).find('startTime').text());
			$('#safetyHisEndTime').val($(this).find('endTime').text());
	   })
	   
	   $(d).find('electConfig').find('special').each(function() {
			$('#specialStartTime').val($(this).find('startTime').text());
			$('#specialEndTime').val($(this).find('endTime').text());
	   })
   });
}
//表单验证
function validForm(){

}


function saveValue() {
	/* add start 2013.4.16 by zhangdiannan
	 * 验证 参数百分比只能输入0至100之间 */
	if($('#criterionval').val()>100 || $('#criterionval').val()<0){
		alert('参数百分比只能输入0至100之间');
		$('#criterionval').fous();
		return;
	}
	/* add end 2013.4.16 by zhangdiannan*/
    var errorCount=0;
    errorCount = totalCount("input[type='text']",errorCount);
    if(errorCount==0){
		var param = {
			"energyYears" : $('#energyYears').val(),
			"valJiange" : $('#valJiange').val(),
			"valUnit" : $('#valUnit').val(),
			//"criterionval" : $('#criterionval').val(),
			/* edit start 2013.4.16 by zhangdiannan
			    将参数以百分的方式传递给后台*/
			"criterionval" : $('#criterionval').val()/100,
			/* edit end 2013.4.16 by zhangdiannan  */
			"safetyStartTime" : $('#safetyStartTime').val(),
			"safetyEndTime" : $('#safetyEndTime').val(),
			"safetyHisStartTime" : $('#safetyHisStartTime').val(),
			"safetyHisEndTime" : $('#safetyHisEndTime').val()
		};

		$.ajax( {
			type : 'post',
			url : "controlConfig!updateTargetConfigXml.action",
			data : param,
			cache : false,
			dataType : 'json',
			success : function(data) {
				var objdt = eval(data);
				if (objdt[0].msg == "yes") {
					alert("修改成功");
				}
			},
			error : function(textStatus) {
	
			}
		});
  }
}

function changeJiange(obj){
   if($(obj).val()>24){
       $(obj).val(24);
   }
}

$(function(){
   $('#valUnit').change(function(){
      if($(this).val()=="day"){
         $('#valJiange').val(1);
         $('#valJiange').attr({readonly:'readonly'});
         $('#valJiange').addClass('inputRead');
      }else if($(this).val()=="hour"){
         $('#valJiange').removeClass('inputRead');
         $('#valJiange').attr({readonly:''});
      }
   })
   getElem("input[type='text']");
   getElem("select");
});
         
		  var getElem = function(ele){
		   $('.admin_clor_f00').parent().next().find(ele).blur(function(){
		         if($.trim($(this).val())==""){
		            $(this).parent().find('.error').remove();
		            var tip=$(this).parent().prev().text();
		            if(tip.indexOf("*")>0){
		               tip=tip.substring(0,tip.indexOf("*")-1);
		            }
		            $(this).parent().append("<font color='red' class='error'>"+tip+"不能为空!</span>");
		         }else{
		            $(this).parent().find('.error').remove();
		         }
		      });
		  }
		  var totalCount = function(ele,errorCount){
		 	 for(var i=0;i<$('.admin_clor_f00').parent().next().find(ele).length;i++){
		         if(jQuery.trim($('.admin_clor_f00').parent().next().find(ele).eq(i).val())==""){
		            $('.admin_clor_f00').parent().next().find(ele).eq(i).parent().find('.error').remove();
		            var tip=$('.admin_clor_f00').parent().next().find(ele).eq(i).parent().prev().text();
		            if(tip.indexOf("*")>0){
		               tip=tip.substring(0,tip.indexOf("*")-1);
		            }
		           $('.admin_clor_f00').parent().next().find(ele).eq(i).parent().append("<font color='red' class='error'>"+tip+"不能为空!</span>");
		            errorCount++;
		         }
		     }
		     return errorCount;
		  }

</script>
		<style type="text/css">
fieldset {
	padding-top: 5px;
	margin-top: 10px;
}

legend {
	color: #00487A;
}

.inputRead{
  background-color: gray;
}
</style>

	</head>

	<body onload="doOnload();">

		<!-- 标题 -->
		<div class="canshusz_btbg_1" style="width: 100%;">
			<table width="780" border="0">
				<tr style="height: 30px">
					<td width="34" align="center">
						<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
					</td>
					<td width="679" class="biaoti_zt_canshusz">
						参数设置——首页参数设置
					</td>
				</tr>
			</table>
		</div>
		<div style="width: 100%; height: 400px;">
			<div id="rotate">
				<div id="fragment-1" class="ui-tabs-panel">
					<fieldset>
						<legend>
							综合节能指数参数配置
						</legend>
						<table width="900px;" cellpadding="0"
							cellspacing="0" style="font-family: '黑体'; font-size: 13px;">
							<tr>
								<td align="center" width="150px;">
									统计年份：<span class="admin_clor_f00">*</span>
								</td>
								<td>
									<span></span>
									<input type="text" id="energyYears" name="energyYears"
										class="Wdate"
										onfocus="WdatePicker({dateFmt:'yyyy',minDate:'2008',maxDate:'{%y}'})" />
								</td>
								<td align="center">

								</td>
								<td>
									<span></span>
								</td>
							</tr>
						</table>
					</fieldset>
					<fieldset>
						<legend>
							变压器电流峰值
						</legend>
						<table width="900px;" class="energysave" cellpadding="1"
							cellspacing="1" style="font-family: '黑体'; font-size: 13px;">
							<tr>
								<td align="center">
									取值界限：<span class="admin_clor_f00">*</span>
								</td>
								<td width="300px;">
									<select style=" width:155px;" id="valUnit" name="valUnit">
									  <option value="hour">时</option>
									  <option value="day">天</option>
									</select>
								</td>
								<td align="center">
									时间间隔：<span class="admin_clor_f00">*</span>
								</td>
								<td width="300px;">
									<input type="text" id="valJiange" name="valJiange" onchange="changeJiange(this);" onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
						</table>
					</fieldset>
					<fieldset>
						<legend>
							综合安全指数--实时
						</legend>
						<table width="900px;" class="energysave" cellpadding="1"
							cellspacing="1" style="font-family: '黑体'; font-size: 13px;">
							<tr>
								<td align="center">
									基准参数：<span class="admin_clor_f00">*</span>
								</td>
								<td>
									<input type="text" id="criterionval" name="criterionval"
										value=""  onkeyup="value=value.replace(/[^\?\d]/g,'')"/>%
								</td>
								<td colspan="2">
									参数为0-100之间的小百分比
								</td>
							</tr>
							<tr>
								<td align="center">
									开始日期：<span class="admin_clor_f00">*</span>
								</td>
								<td width="300px;">
									<input type="text" id="safetyStartTime" name="safetyStartTime"
										class="Wdate"
										onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'safetyEndTime\')||\'2020-10-01\'}'})" />
								</td>
								<td align="center">
									截止日期：<span class="admin_clor_f00">*</span>
								</td>
								<td width="300px;">
									<input type="text" id="safetyEndTime" name="safetyEndTime"
										class="Wdate"
										onfocus="WdatePicker({minDate:'#F{$dp.$D(\'safetyStartTime\')}',maxDate:'2020-10-01'})" />
								</td>
							</tr>
						</table>
					</fieldset>
					<fieldset>
						<legend>
							综合安全指数--历史
						</legend>
						<table width="900px;" class="energysave" cellpadding="1"
							cellspacing="1" style="font-family: '黑体'; font-size: 13px;">
							<tr>
								<td align="center">
									开始日期：<span class="admin_clor_f00">*</span>
								</td>
								<td width="300px;">
									<input type="text" id="safetyHisStartTime"
										name="safetyHisStartTime" class="Wdate"
										onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'safetyHisEndTime\')||\'2020-10-01\'}'})" />
								</td>
								<td align="center">
									截止日期：<span class="admin_clor_f00">*</span>
								</td>
								<td width="300px;">
									<input type="text" id="safetyHisEndTime"
										name="safetyHisEndTime" class="Wdate"
										onfocus="WdatePicker({minDate:'#F{$dp.$D(\'safetyHisStartTime\')}',maxDate:'2020-10-01'})" />
								</td>
							</tr>
						</table>
					</fieldset>
					<div style="width: 100%;">
					   <table style=" width:100%;">
					     <tr>
					        <td>
					            <a class="btn blue" onclick="saveValue();" id="btnEdit">修  改</a>&nbsp;&nbsp;
					        </td>
					        
					        <!-- 参数设置只有一个页面，不存在首页与副页的概念。所以无需取消按钮 -->
					        <td style=" width:80px;">
					        <!-- 
					          <a class="btn blue" id="btnEdit">取  消</a>
					         -->
					        </td>
					         
					     </tr>
					   </table>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
