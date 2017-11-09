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
		var isfalse;
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

		//获得楼宇列表
		getBuilds=function(){
			var buildids ="";
			try{
				var selUnits = window.top.frames["hospTree"].$("#selectIds").val();					
				if(selUnits.length>0){
					var units = selUnits.split(",");
					var unitType =units[0].substring(units[0].lastIndexOf("|")+1);					
					

					if("louyu"==unitType){
						if (units.length >5) {
							alert("选择的楼宇数量,请不要超过5个!");
							isfalse = true;
						}else{
							var tmp ;
							for(var i=0;i<units.length;i +=1){
								tmp = units[i];
								if(i!=0){
									buildids +="','";
								}
								buildids += tmp.substring(0,tmp.indexOf("|"));
							}
						}
					}else{
						alert("请选择楼宇!");
						isfalse = true;
					}
				}	
			}catch(e){}

			return buildids;
		}
		//提交表单
		submitform=function(isPcha){			
			var myform=$("#myform");

			isfalse = false;
			var myPowerVal = document.getElementById('powerfrm').contentWindow.document.getElementById('mypwer').value;
			$("#power").val(myPowerVal);
			
			$("#startDate").val($("#sDate1").val());
			$("#endDate").val($("#sDate2").val());
			$("#timeStep").val($("#sstpe").val());
			$("#selUnits").val(getBuilds());				
			if(isfalse){
				return ;
			}
			
			//时间有效性
			var sDate1=$("#sDate1").val();
			var sDate2=$("#sDate2").val();
			if (sDate1.length >0 && sDate2.length>0) {
				if(sDate1>sDate2){
					alert("起始时间不能大于结束时间!");
					return false;
				}
			}

			//偏差
			if(isPcha){
				$("#baseLine").val($("#baseSel").val());
				$("#pcha").val($("#pchaVal").val());
				myform.attr("action",myform.attr("action")+"A");	
			}

			
			myform.submit();			
		}
		
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
		<form method="post" action="${param.action}" name="myform"	id="myform">
			<input type="hidden" id="power"     name="voobj.power"/>  	<!-- 能源类型 -->
			<input type="hidden" id="startDate" name="voobj.startDate"/><!-- 开始时间 -->
			<input type="hidden" id="endDate"   name="voobj.endDate"/>	<!-- 结束时间 -->
			<input type="hidden" id="timeStep"  name="voobj.timeStep"/>	<!-- 时间间隔 -->
			<input  type="hidden" id="hospid"  name="voobj.hospid"/>	<!-- 医院Id  -->	
			<input type="hidden" id="selUnits" name="voobj.selUnits"/>	<!-- 楼宇Id集 -->
			<input type="hidden" id="baseLine" name="voobj.baseLine"/>	<!-- 基准线 -->
			<input type="hidden" id="pcha" name="voobj.pcha"/>			<!-- 偏差 -->
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
						楼宇能效分析——${name }
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
			     <td >标准 <select id="baseSel" >
	      	  		<option >--请选择--</option>
	      	  		<option value="上海市" ${voobj.baseLine=='上海市'?'selected':'' }>上海市</option>
		      	  	</select>
		      	 </td>
			     <td >偏差<input type="text" id="pchaVal" value="${voobj.pcha }" size="5" maxlength="5" onkeyup="if(isNaN(value) || value>100){execCommand('undo');}" />%</td>
		      	 <td></td> 
		         <td  width="65"  align="right">
		         	<a href="javascript:void(0);" onclick="submitform(true);" class="btn blue">分析</a>
		         </td>
		         <td width="65" align="right">
		         	<img style='cursor: pointer' src="manager/images/imgs/shebeigl_sousuo_an.png" onclick="submitform(false);"></img>
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

