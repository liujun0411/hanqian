<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<title>医院后勤智能化管理平台</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>		
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script src="manager/js/changeNumberUnit.js" type="text/javascript"></script>
		<script src="manager/js/tablePage.js" type="text/javascript"></script>
		<script type="text/javascript">
			var showobj;
			
			goto_area= function(){

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
								$("#selUnits").attr("value",hospid);	
							}							
						}						
					}	
				}catch(e){}	

				window.formSelect.submit();				
			}

			//展示大图
			showImgF = function(imgId){
				var obj=$("#"+imgId);				
				obj.css("position","absolute");
				obj.css("top",100+"px");
				obj.css("left",100+"px");

				var objout=$("#outimg");
				objout.css("position","absolute");
				objout.css("top",102+"px");
				objout.css("left",722+"px");
				
				try{
					showobj.hide();
				}catch(e){}
				obj.show();
				objout.show();			
				showobj=obj;			
			}

			closeImg =function(){
				try{
					showobj.hide();
					$("#outimg").hide();
				}catch(e){}
			}

			onload = function(){
				if("${isfail}" != ""){
					alert("请重新登陆！");
					window.top.location="user!closeUsers.action";
				}
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
	
	<body style="overflow:scroll;overflow-x:hidden;overflow-y:hidden;cursor:default;">

		<div id="admin_nr_rightg" >
		
		   <div class="canshusz_btbg_1">
			<table width="780" border="0">
					<tr style="height:30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							面积比例情况——区域面积比例
						</td>
					</tr>
				</table>
			</div>						
			
			
			
			<!-- 查询 -->			
			<form method="post" action="area_showAreaOf.action" name="formSelect" id="formSelect">
				<input type="hidden" name="selObject" id="selObject" value="${selObject}"/>	<!-- 分析对象 -->
				<input type="hidden" name="selUnits"  id="selUnits"  />						<!-- 分析单元 -->
				<c:if test="${roleMessage!='2' && roleMessage!='3' && roleMessage!='5'}">
				<table width="780" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr style="height:40">
						<td align="left" ></td>					
						<td width="60">
							<img style='cursor: pointer' src="manager/images/imgs/shebeigl_sousuo_an.png" onclick="goto_area();"></img>										
						</td>							
					</tr>
				</table>
				</c:if>
				<span class="admin_clor_f00">${message}</span>							
			</form>
			
			
		
			
				<!-- 数据展示区 -->
				<div style="height: 410px;width: 780px;">
					<div style="height: 350px;width: 270px;float: left;clear:left;z-index: 1;margin-top: 10px;">
						<table width="265px;"  border="0" style="height:350px;" >							
							<c:if test="${!(empty obj.myDrawF)}">
							<!-- 图形1 -->
							<tr valign="top">
								<td><img src="${obj.myDrawF }"  border="1"  width="260px;" onclick="showImgF('imgf');" style="cursor:url(manager/images/cursor/zoomin.cur);"/></td>
							</tr>
							</c:if>
							<c:if test="${!(empty obj.myDrawS)}">
							<!-- 图形2 -->
							<tr valign="top">
								<td><img src="${obj.myDrawS }"  border="1"  width="260px;" onclick="showImgF('imgs');" style="cursor:url(manager/images/cursor/zoomin.cur);"/></td>
							</tr>
							</c:if>
						</table>
					</div>
					
					
					
					<div style="height: 410px;width: 500px;float: right;z-index: 1;" >						
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
										<tr class=" biaog_kan2 biaog_zt2" align="center">
											<c:forEach items="${obj}"  var="rowData" varStatus="in">
											     <td class='${in.index>0?"tdValue":""}'>${rowData }</td>
											</c:forEach>																										
										</tr>
										</c:forEach>
										</tbody>
									</table>
									<table width="90%" align="center"><tr><td><span id="showtool"></span></td></tr></table>
								</c:if>							
					</div>
				</div>
				
				
				
				
				
			    
			   		<c:if test="${!(empty obj.myDrawF)}">
					<!-- 大图1 -->
					 <div style="display: none;" id="imgf"  >
				      <img src="${obj.myDrawF }" border="1" usemap="#useMapF" />
					</div>
					</c:if>
					<c:if test="${!(empty obj.myDrawS)}">
					<!-- 大图2 -->
					  <div style="display: none;" id="imgs"  >
					      <img src="${obj.myDrawS }" border="1" usemap="#useMapS" />
					  </div>
					</c:if>
					
					<div style="display: none;cursor: pointer;border: 0px solid #FF0000;" id="outimg">
						<img src="manager/images/cursor/zoomout.png" width="24px;"  onclick="closeImg();" title="关闭"/>
					</div>
								
					<!-- map 区 -->
					${obj.useMapF}
					${obj.useMapS}
					
			
			
		</div>
		<script type="text/javascript">
			beginAt(); 
			var mytable=new Page(12,'table1','stu-datas-tb','showtool','mytable'); 	
		</script>

		
	</body>
</html>