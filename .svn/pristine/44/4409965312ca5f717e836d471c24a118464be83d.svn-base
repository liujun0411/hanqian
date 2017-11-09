<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 



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
	<script src="manager/js/tablePage.js" type="text/javascript"></script>

	
	<script language="JavaScript">
	<!--
				
		

		

		setHospids=function(){
			//医院ID
			try{
				var selUnits = window.top.frames["hospTree"].$("#selectIds").val();					
				if(selUnits.length>0){
					var units = selUnits.split(",");
					var unitType =units[0].substring(units[0].lastIndexOf("|")+1);					


					if(units.length >1){
						alert("请选择单家医院!");

						return false;
					}else{
						if ("yiyuan"==unitType) {
							var tmp = units[0];
							var hospid = tmp.substring(0,tmp.indexOf("|"));					
							$("#hospid").attr("value",hospid);	
						}

						
					}
				}	
			}catch(e){}

			return true;
			
		}

		//提交表单
		function submitform(){			
			var myform=$("#myform");
			
			//获得查询条件
			$("#startDate").val($("#sDate1").val());
			$("#endDate").val($("#sDate2").val());
			$("#timeStep").val($("#sstpe").val());
			if(!setHospids()){
				return ;
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

<body style="overflow:scroll;overflow-x:hidden;overflow-y:hidden;">

		<!-- form 表单 -->
		<form method="post" action="feature_showFeatrueCUI.action" name="myform"	id="myform">
			<input type="hidden" id="power"     name="voobj.power"			/>   	<!-- 能源类型 -->
			<input type="hidden" id="startDate" name="voobj.startDate"		/> 		<!-- 开始时间 -->
			<input type="hidden" id="endDate"   name="voobj.endDate"		/>		<!-- 结束时间 -->
			<input type="hidden" id="timeStep"  name="voobj.timeStep"		/>		<!-- 时间间隔 -->
			
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
						特征区域能效分析——特征区域能耗价值
					</td>
				</tr>
			</table>
		</div>	
		
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
		         <td width="250" align="right" >起止年月:
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


			
		<div style="height: 410px;width: 780px;z-index: 1;" align="center">						
				<c:if test="${!(empty obj.mytable)}">
				<!-- 表格区 -->
				<table width="100%" border="0" cellspacing="1" class="biaoge_bg1 gai_left_duiqi" align="center" id="table1">
					<caption><span class="admin_jbxx_bt1" >${obj.mytable.title }</span></caption>
					<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1" align="center">
					<c:forEach items="${obj.mytable.keys}" var="key">
							<td>${key }</td>
					</c:forEach>											
					</tr>
					</thead>
										
					<tbody id="stu-datas-tb" >
					<c:forEach items="${obj.mytable.values}"  var="obj">
					<tr>
						<c:forEach items="${obj}"  var="rowData" varStatus="at">
						<c:if test="${at.index==0}">
							<td>${rowData }</td>
						</c:if>
						<c:if test="${at.index!=0}">
							<td>${rowData.convert }</td>							
						</c:if>
						</c:forEach>																										
					</tr>
					</c:forEach>
					</tbody>
				</table>
				</c:if>							
			</div>
			
			<div id="showtool"></div>
			<script type="text/javascript">
				var mytable=new Page(12,'table1','stu-datas-tb');
			</script>
		
	</div>
	
	
	

</body>
</html>

