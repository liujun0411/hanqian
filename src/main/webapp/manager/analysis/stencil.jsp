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
	
	


	<style type="text/css">
	<!--
	div {
		/*border: 1px solid #3399FF;*/
	}
	-->
	</style>
	</head>

	<body  style="overflow:scroll;overflow-x:hidden;overflow-y:hidden;">

		



	<div id="admin_nr_rightg" >
		<!-- 标题 -->
		<div class="canshusz_btbg_1" >
			<table width="780" border="0">
				<tr style="height:30px">
					<td width="34" align="center">
						<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
					</td>
					<td width="679" class="biaoti_zt_canshusz">
						数据分析
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
		         	<img style='cursor: pointer' src="manager/images/imgs/shebeigl_sousuo_an.png" ></img>
		         </td>
		      	</tr>
		      </table>
		      <span class="admin_clor_f00">${message}</span>
			</div>
    	</div>
				
			
		
	</div>	
			
</body>
</html>

