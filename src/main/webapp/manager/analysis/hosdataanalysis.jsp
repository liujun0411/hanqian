<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
	<script src="manager/js/annu.js" type="text/javascript"></script>
	<script src="manager/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>	
	<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
	<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
	<script src="manager/js/changeNumberUnit.js" type="text/javascript"></script>
	<script src="manager/js/tablePage.js" type="text/javascript"></script>
	<script language="JavaScript">
		var isNo;
		//更新时间隔
		changeStep=function(value){
			var spans=document.getElementById("sDate1");
			var spane=document.getElementById("sDate2");
			
			if(value=="year"){
				spans.onfocus=function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy'});  };
				spane.onfocus=function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy'});  };
				spans.value="";
				spane.value="";
			}else{
				spans.onfocus=function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'});  };
				spane.onfocus=function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'});  };
				spans.value="";
				spane.value="";
			}
		}

		//医院选择
		setHospids=function(){
			//医院ID
			var hospid="";
			try{
				var selUnits = window.top.frames["hospTree"].$("#selectIds").val();					
				if(selUnits.length>0){
					var units = selUnits.split(",");
					var unitType =units[0].substring(units[0].lastIndexOf("|")+1);					
					

					if("yiyuan"==unitType){
						if (units.length >1) {
							alert("请选择单家医院!");
							isNo = true;
						}else{
							var tmp = units[0];
							hospid = tmp.substring(0,tmp.indexOf("|"));	
						}
					}else{
						alert("请选择医院");
						isNo = true;
					}
				}	
			}catch(e){}

			return hospid;
		}

		//提交表单
		submitform=function(){			
			var myform=$("#myform");

			isNo = false;
			//获得查询条件
			var _Frame = null;
			$("#power").val(document.getElementById('powerfrm').contentWindow.document.getElementById('mypwer').value);
			$("#startDate").val($("#sDate1").val());
			$("#endDate").val($("#sDate2").val());
			$("#timeStep").val($("#sstpe").val());
			$("#hospid").attr("value",setHospids());	
			if(isNo){
				return;
			}
			
			
			//时间有效性
			var sDate1=$("#sDate1").val();
			var sDate2=$("#sDate2").val();
			if (sDate1.length >0 && sDate2.length>0) {
				if(sDate1>sDate2){
					alert("起始时间不能大于结束时间");
					return false;
				}
			}
			
			myform.submit();			
		}
		
	//-->
	</script>
	
	


	<style type="text/css">
	<!--
	div {
		/*border: 1px solid #3399FF;*/
	}
	-->
	</style>
	</head>

	<body  style="overflow:scroll;overflow-x:hidden;overflow-y:hidden;">

		<!-- form 表单 -->
		<form method="post" action="hosdataanalysis_showUI.action" name="myform"	id="myform">
			<input type="hidden" id="power"     name="voobj.power"			/>  <!-- 能源类型 -->
			<input type="hidden" id="startDate" name="voobj.startDate"		/> 	<!-- 开始时间 -->
			<input type="hidden" id="endDate"   name="voobj.endDate"		/>	<!-- 结束时间 -->
			<input type="hidden" id="timeStep"  name="voobj.timeStep"		/>	<!-- 时间间隔 -->
			<input  type="hidden" id="hospid"  name="voobj.hospid"/>
			
		</form>




	<div id="admin_nr_rightg" >
		<!-- 标题 -->
		<div class="canshusz_btbg_1" >
			<table width="780" border="0">
				<tr style="height:30px">
					<td width="34" align="center">
						<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
					</td>
					<td width="679" class="biaoti_zt_canshusz">
						医院能效分析——医院总体能耗量
					</td>
				</tr>
			</table>
		</div>	
		
		<div style="width: 780px;float: left;">	
		
			<!-- 能源选择 -->
			<iframe src="manager/analysis/an_head.jsp?power=${voobj.power }"
				scrolling="no" width="100%" height="60" marginwidth="0"
				marginheight="0" frameborder="0" id="powerfrm"></iframe>


				<!-- 条件区 -->
    		<div >
		      <table width="100%" border="0" align="right" class="louyujj_xiaxian_hui gai_left_duiqi" 
		      			style="background-color: #CEEFFF;" >
		       	       
		       <tr   class="shebeigl_inp_zt" >        	
		         <td></td>
		         <td width="270" align="right" >起止年月:
		         	<span>
		         	<input name="sDate1" type="text" id="sDate1"  
		         	value="${voobj.startDate }" size="8" maxlength="6"  
		         	class="Wdate"  readonly="readonly" 
		         	onfocus="WdatePicker({skin:'whyGreen',dateFmt:'${'year'== voobj.timeStep?'yyyy':'yyyyMM' }'})" />
		         	</span>至 
		         	<span>
		         	<input name="sDate2" type="text" id="sDate2"  
		         	value="${voobj.endDate }" size="8" maxlength="6"  
		         	class="Wdate"  readonly="readonly" 
		         	onfocus="WdatePicker({skin:'whyGreen',dateFmt:'${'year'== voobj.timeStep?'yyyy':'yyyyMM' }'})" />
		         	</span>
		         </td>
		         <td width="110" >时间隔:<select name="sstpe"  id="sstpe" onchange="changeStep(this.value)">
			           <option value="month" <c:if test="${'year'!=voobj.timeStep}">selected</c:if>>月</option>
			           <option value='year' <c:if test="${'year'==voobj.timeStep}">selected</c:if>>年</option>          
			        </select>         
			     </td>
		         <td width="65" align="right">
		         	<img style='cursor: pointer' src="manager/images/imgs/shebeigl_sousuo_an.png" onclick="submitform();"></img>
		         </td>
		      	</tr>
		      </table>
		      <span class="admin_clor_f00">${message}</span>
			</div>
    	</div>
				
			
		<jsp:include page="an_data.jsp"></jsp:include>
	</div>
	
	
	
					
	<script type="text/javascript">
		try{
			beginAt(); 
			var mytable=new Page(10,'table1','stu-datas-tb','showtool','mytable'); 	
		}catch(eui){}
	</script>				
			
</body>
</html>

