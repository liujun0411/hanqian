<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>


	
	<script language="JavaScript">
	<!--
		//展示大图32
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

		//关闭大图 
		closeImg =function(){
			try{
				showobj.hide();
				$("#outimg").hide();
			}catch(e){}
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
		${weather.myDrawF }
		<c:if test="${!(empty obj)}">			
			<!-- 图形展示区 -->
			<div style="height: 410px;width: 780px;">
				<div style="height: 350px;width: 250px;float: left;clear:left;z-index: 1;margin-top: 10px;">
					<table width="245px;"  border="0" style="height:350px;" >							
						<c:if test="${!(empty obj.myDrawF)}">
						<!-- 图形1 -->
						<tr valign="top">
							<td><img src="${obj.myDrawF }"  border="1"  width="240px;" onclick="showImgF('imgf');" style="cursor:url(manager/images/cursor/zoomin.cur);"/></td>
						</tr>
					</c:if>
					<c:if test="${!(empty obj.myDrawS)}">
					<!-- 图形2 -->
					<tr valign="top">
						<td><img src="${obj.myDrawS }"  border="1"  width="240px;" onclick="showImgF('imgs');" style="cursor:url(manager/images/cursor/zoomin.cur);"/></td>
					</tr>
					</c:if>
					<c:if test="${!(empty obj.myDrawT)}">
					<!-- 图形3 -->
					<tr valign="top">
						<td><img src="${obj.myDrawT }"  border="1"  width="240px;" onclick="showImgF('imgt');" style="cursor:url(manager/images/cursor/zoomin.cur);"/></td>
					</tr>
					</c:if>
					<c:if test="${!(empty vowe)}">
					<!-- 天气情况 -->
					<tr valign="top">
						<td><img src="${vowe.imgURL }"  border="1"  width="240px;" onclick="showImgF('imgwe');" style="cursor:url(manager/images/cursor/zoomin.cur);"/></td>
					</tr>
					</c:if>
				</table>
			</div>
			
			<div style="height: 410px;width: 520px;float: right;z-index: 1;" >						
				<c:if test="${!(empty obj.mytable)}">
				<!-- 表格区 -->
				<table width="100%" border="0" cellspacing="1" class="biaoge_bg1 gai_left_duiqi" align="center" id="table1">
					<caption><span class="admin_jbxx_bt1" >${obj.mytable.title }</span></caption>
					<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1" align="center">
					<c:forEach items="${obj.mytable.keys}" var="key" varStatus="idx">
						<c:if test="${idx.first}">
							<td width="80">${key }</td>
						</c:if>
						<c:if test="${!idx.first}">
							<td >${key }</td>
						</c:if>
					</c:forEach>											
					</tr>
					</thead>
										
					<tbody id="stu-datas-tb" >
					<c:forEach items="${obj.mytable.values}"  var="obj">
					<tr align="center" class=" biaog_kan2 biaog_zt2">
						<c:forEach items="${obj}"  var="rowData">
							<td>${rowData }</td>
						</c:forEach>																										
					</tr>
					</c:forEach>
					</tbody>
				</table>
				</c:if>	
				<table width="90%" align="center"><tr><td><span id="showtool"></span></td></tr></table>
									
			</div>
			
			
			<!-- 大图展示 -->
			
			<c:if test="${!(empty obj.myDrawF) }">
			<!-- 大图1 -->
			<div style="display: none;" id="imgf" onclick="hideMe('imgf');" >
			<img src="${obj.myDrawF }" border="1" usemap="#useMapF" />
			</div>
			</c:if>
			
			<c:if test="${!(empty obj.myDrawS) }">
			<!-- 大图2 -->
			<div style="display: none;" id="imgs" onclick="hideMe('imgs');" >
				<img src="${obj.myDrawS }" border="1" usemap="#useMapS" />
			</div>
			</c:if>
			
			<c:if test="${!(empty obj.myDrawT) }">
			<!-- 大图3 -->
			<div style="display: none;" id="imgt" onclick="hideMe('imgt');" >
				<img src="${obj.myDrawT }" border="1" usemap="#useMapT" />
			</div>
			</c:if>
			<c:if test="${!(empty vowe)}">
			<!-- 天气情况 -->
			<div style="display: none;" id="imgwe" onclick="hideMe('imgwe');" >
				<img src="${vowe.imgURL }" border="1" usemap="#imgMap" />
			</div>
			</c:if>
			
			<div style="display: none;cursor: pointer;border: 0px solid #FF0000;" id="outimg">
				<img src="manager/images/cursor/zoomout.png" width="24px;"  onclick="closeImg();" title="关闭"/>
			</div>
			
			
			<!-- map区 -->
			${obj.useMapF }				
			${obj.useMapS }
			${obj.useMapT }	
			<c:if test="${!(empty vowe)}">
				${vowe.imgMap }
			</c:if>
			</div>
			</c:if>	


