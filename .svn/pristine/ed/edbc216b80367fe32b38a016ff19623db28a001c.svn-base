<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>医院后勤智能化管理平台</title>
		   
		<link href="manager/css/neirong.css" rel="stylesheet"	type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />		 	
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />		
		<script src="manager/js/annu.js" type="text/javascript"></script>		
		
		
		
		<link href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/static/css/aries/jquery.aries.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/static/css/dropdown/jquery.dropdown.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script>
		<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script>
		<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script>
		<script src="<c:url value="/static/js/dropdown/jquery.dropdown.min.js"/>"></script>
		
		
		<script type="text/javascript">	
			
			//是否激活消隐功能
		    var allhide=false;	
		    
		    
			//比例图
		    function showUseAtcion(ationc){
		   		document.getElementById("buildingId").value=document.getElementById("buildingId").value;
		    	document.form1.action=ationc;
		   		document.form1.submit();
		    }
		
			function getPic(storey){
				var buildingId = document.getElementById("buildingId").value;
				location.href="buildUsePicUpload_showBuildUsePicList.action?buildingId="+buildingId+"&storey="+storey;
			}

			function selectYongTu(bg){
				var yongtu = $("td");
				for(var i=0;i<yongtu.length;i++){
					if(yongtu[i].id.indexOf("bgcolor") !=-1){
						if(yongtu[i].id.indexOf(bg) != -1){						
							yongtu[i].bgColor=bg;
						}else{
							yongtu[i].bgColor="";
						}
					}
				}
			}
			//drawing_showStoreyUse.action
			 f_change=function(){
			   $("#buildingId").val($('#buildIds').val());
		       document.form1.submit();
		    }
		    
		    //全显
			function showAll(){
				var yongtu = $("td");
				
				for(var i=0;i<yongtu.length;i++){
					if(yongtu[i].id.indexOf("bgcolor") !=-1){
						yongtu[i].bgColor=yongtu[i].id.substr(7,yongtu[i].id.length);						
					}
				}
				allhide=false;
			}

			//全隐
			function hiddenAll(){
				var yongtu = $("td");
				for(var i=0;i<yongtu.length;i++){
					if(yongtu[i].id.indexOf("bgcolor") !=-1){
						yongtu[i].bgColor="";
					}
				}
				allhide=true;
			}
			
			//消隐
			function showMe(obj){
				obj.bgColor = obj.id.substr(7,obj.id.length);
			}
			
			//独显 
			function showThisColor(bgcolor){
				var yongtu = $("td");
				for(var i=0;i<yongtu.length;i++){
					if(yongtu[i].id.indexOf("bgcolor") !=-1){
						if(yongtu[i].id.indexOf(bgcolor) !=-1){
							yongtu[i].bgColor=bgcolor;
						}else{
							yongtu[i].bgColor="";
						}
					}
				}
				
				allhide=false;
			}
			
			
			
			//任意提醒，通过标签ID给需要绑定数据的元素绑定数据
		  	$(document).ready(function(){
		  		<c:if test="${empty remindJson}">
					var remindJson="";
				</c:if>
				<c:if test="${!empty remindJson}">
					var remindJson =${remindJson};
				</c:if>
		  		 	if(remindJson!=""){
			  		 	for(var obj in remindJson){
			 	  	 				console.debug("面积详情remindJson="+remindJson[obj].resultsql);
			  		 				$("#"+obj).data("aries",remindJson[obj]);
			  		 		}
		  		 	}
		  		});
		</script>	

		
		
		</head>
<body >
<form action="drawing!showStoreyUseById.action" method="post" name="form1" id="form1">
	<input id="hospid" name="hospid" type="hidden" value="${hospid }" />	
	<input id="buildingId" name="buildingId" type="hidden" value="${buildingId }" />	
</form>

<div id="admin_nr_jbxx">

	<div id="div1" style="margin-top: 10px;" class="gai_left_duiqi">
				<table width="780" border="0" align="center">
					<tr valign="middle" style="height:25px;">
						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="outpatient" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['门诊'].color}')">门诊</td>
						<td valign="middle" width="50"  style="cursor:pointer;"; onclick="showThisColor('${yongtu['门诊'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_menz"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["门诊"].area}${(empty yongtu["门诊"].area)?'0':'' }&nbsp;㎡</td>

						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="emergency" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['急诊'].color}')">急诊</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['急诊'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_jiz"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["急诊"].area}${(empty yongtu["急诊"].area)?'0':'' }&nbsp;㎡</td>

						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="hospitalization" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['住院'].color}')">住院</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['住院'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_bingf"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["住院"].area}${(empty yongtu["住院"].area)?'0':'' }&nbsp;㎡</td>

						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="medical" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['医技'].color}')">医技</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['医技'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_yij"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["医技"].area}${(empty yongtu["医技"].area)?'0':'' }&nbsp;㎡</td>
					</tr>
					
					<tr align="center" valign="middle" style="height: 5px;"><td colspan="15"></td></tr>
					<tr valign="middle">
						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="security" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['保障'].color}')">保障</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['保障'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_houq"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["保障"].area}${(empty yongtu["保障"].area)?'0':'' }&nbsp;㎡ </td>
						
						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="administration" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['行政'].color}')">行政</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['行政'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_xingz"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["行政"].area}${(empty yongtu["行政"].area)?'0':'' }&nbsp;㎡</td>

						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="scientific" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['科研'].color}')">科研</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['科研'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_key"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["科研"].area}${(empty yongtu["科研"].area)?'0':'' }&nbsp;㎡</td>

						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="education" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['教育'].color}')">教育</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['教育'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_jiaox"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["教育"].area}${(empty yongtu["教育"].area)?'0':'' }&nbsp;㎡</td>

					</tr>
					<tr align="center" valign="middle" style="height: 5px;"><td colspan="15"></td></tr>
					<tr>
						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="life" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['生活'].color}')">生活</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['生活'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_shengh"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["生活"].area}${(empty yongtu["生活"].area)?'0':'' }&nbsp;㎡</td>

						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="garage" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['车库'].color}')">车库</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['车库'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_chek"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["车库"].area}${(empty yongtu["车库"].area)?'0':'' }&nbsp;㎡</td>

						<td align="center" class="cahshusz_qumc_zt1  aries-anchor-right aries-anchor-line"  data-aries="#aries-1" id="others" width="60" style="cursor:pointer;"; onclick="showThisColor('${yongtu['其它'].color}')">其它</td>
						<td valign="middle" width="50" style="cursor:pointer;"; onclick="showThisColor('${yongtu['其它'].color}')"><div class="gongnengqu_ktf_1 quyuyanse_qit"></div></td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">${yongtu["其它"].area}${(empty yongtu["其它"].area)?'0':'' }&nbsp;㎡</td>
					</tr>
				</table>
			</div>

    <table width="780" border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
      <tr>
      	<td align="left"><span class="gai_clor_063">
        <c:if test="${(roleMessage!=2 && roleMessage!=3)}">${title}</c:if></span></td> 
		
		<td width="330" align="right" class="shebeigl_inp_zt"> 楼宇:
			<select id="buildIds" name="buildIds">
			<c:forEach items="${listB}" var="build">
				<option value="${build.buildingId }" <c:if test="${build.buildingId==buildingId}">selected</c:if> >${build.buildingName }</option> 
			 </c:forEach>
			<c:if test="${empty listB}"> 
				<option>
						查无数据
				</option>
			</c:if>
			</select>
		</td>
		<td width="80" align="right">
			<img style='cursor: pointer' src="manager/images/imgs/shebeigl_sousuo_an.png" onclick="f_change()"/>
		</td>
      	<td  width="50" align="right" >
		     <a href="javascript:void(0);" onclick="showAll();" class="btn blue">全显</a>
		 </td>
		 <td  width="50" align="left" >
		     <a href="javascript:void(0);" onclick="hiddenAll();" class="btn blue">全隐</a>
		 </td>
		 <td width="10"></td>
      </tr>
    </table> 



			<c:if test="${!empty buildinguse}">
				<table width="100%" border="0" align="center"
					class="louyujj_xiaxian_hui">
					<tr class=" biaog_kan2 biaog_zt2" valign="bottom">
						<td width="120" align="center">
							楼宇用途比例
						</td>
						<td width="500" align="center" valign="bottom">
							<c:if test="${!(empty buildinguse.obj) }">
								<table border="0" cellspacing="0" width="95%" align="center"
									style="height: 14px">
									<tr>
										<c:forEach items="${buildinguse.obj}" var="obj">
											<td bgcolor="${obj.color }" style="width:${obj.bili }%;"
												title="${obj.content1 }&#13面积: ${obj.area}㎡   比例:${obj.bili }%"></td>
										</c:forEach>
									</tr>
								</table>
							</c:if>
						</td>
						<td width="120" align="left">
							${buildinguse.sumarea }㎡ 
						</td>
					</tr>
				</table>
			</c:if>




<div style="overflow: scroll; height: 500px;">
	<c:if test="${!(empty storeylist) }">
	<table width="100%" bgcolor="#DADADA"   border="0" cellspacing="0">
	<tr >
	 <td></td>
	 <td >
	 <table  border="0" cellspacing="0"  align="center">	
	 <!---  树 -->
      <tr >
	      <td rowspan="${(storeylist[0].storey+1)}" valign="bottom" align="right"><img src="manager/images/imgbg/louyushu.png"/></td>
      	  <c:if test="${storeylist[0].storey>0}">
      	   	<td width="374"  align="left"  style="height:47px;background-image:url(manager/images/imgbg/louyubili01.png);background-repeat: no-repeat;background-position: center bottom;"> </td>
	        <td style="width:33px;height:31px;background-image:url(manager/images/imgbg/louyubili07.png);background-repeat: no-repeat;background-position: left bottom;"></td> </td>
	      </c:if>
	      <c:if test="${storeylist[0].storey<=0}">
	      	<td></td>
	      	<td></td>
	      </c:if>
	    <td rowspan="${(storeylist[0].storey+1)}" valign="bottom" align="left"><img src="manager/images/imgbg/louyushu.png"/>
	      
      </tr>
      
    
     <c:forEach items="${storeylist }" var="list"  varStatus="ms">
     	<tr title="${list.name }层">
     		<!-- 地上 -->
     		<c:if test="${list.storey>0}">    
     		
     		  <!-- 1F以上层 --> 				 
          	   <c:if test="${!(!ms.last && !ms.first && list.storey==1)}">
          	   <td align="center" valign="bottom"  style="width:374px;height:28px;background-image:url(manager/images/imgbg/louyubili02.png);background-repeat: no-repeat;background-position: center bottom;">
          			<c:if test="${!(empty list.obj) }">
	          			<table border="0" cellspacing="0" width="95%" align="center" style="height:14px"> 
	          				<tr>
	          				   <c:forEach items="${list.obj}" var="obj">
	          				   		<td id="bgcolor${obj.color }" bgcolor="${obj.color }" style="width:${obj.bili}%;" title="${list.name}层&#13${obj.content1 }: 面积: ${obj.acreage}㎡   比例:${obj.bili}%" onmouseover="if(allhide){showMe(this);}" onmouseout="if(allhide){this.bgColor='';}"></td>	          				   	
	          				   </c:forEach>	          					        					
	          				</tr>
						</table>
          			</c:if>     			
          		</td>
          	   </c:if>
          	   
          	     <!-- 1F层 --> 	
          	   <c:if test="${(!ms.last && !ms.first && list.storey==1)}">
          	   <td align="center" valign="bottom"  style="width:374px;height:28px;background-image:url(manager/images/imgbg/louyubili03.png);background-repeat: no-repeat;background-position: center bottom;">
          			<c:if test="${!(empty list.obj) }">
	          			<table border="0" cellspacing="0" width="95%" align="center" style="height:14px"> 
	          				<tr>
	          				   <c:forEach items="${list.obj}" var="obj">
	          				   		<td id="bgcolor${obj.color }" bgcolor="${obj.color }" style="width:${obj.bili}%;" title="${list.name}层&#13${obj.content1 }: 面积: ${obj.acreage}㎡   比例:${obj.bili}%" onmouseover="if(allhide){showMe(this);}" onmouseout="if(allhide){this.bgColor='';}"></td>	          				   	
	          				   </c:forEach>	          					        					
	          				</tr>
						</table>
          			</c:if>
          		</td>
          	   </c:if>
          		
          		
          		
          		<c:if test="${ms.index==0}">
          		<td  style="width:33px;height:31px;background-image:url(manager/images/imgbg/louyubili06.png);background-repeat: no-repeat;background-position: left bottom;"></td>
          		</c:if>
	          	<c:if test="${ms.index!=0}">
	          	<td  style="width:33px;height:31px;background-image:url(manager/images/imgbg/louyubili06.png);background-repeat:no-repeat;background-position: left bottom;" ></td>
	          	</c:if>  
	             	
          	</c:if>
          <!-- 地上 end -->
          	
          	<!-- 地下 -->
          	<c:if test="${list.storey<0}">
          		<td></td>
          		<td align="center" valign="bottom"  style="width:374px;height:28px;background-image:url(manager/images/imgbg/louyubili04.png);background-repeat: no-repeat;background-position: center bottom;">
          			<c:if test="${!(empty list.obj) }">
	          			<table border="0" cellspacing="0" width="95%" align="center" style="height:14px"> 
	          				<tr>
	          				   <c:forEach items="${list.obj}" var="obj">
	          				   		<td id="bgcolor${obj.color }" bgcolor="${obj.color }" style="width:${obj.bili}%;" title="${list.name}层&#13${obj.content1 }: 面积: ${obj.acreage}㎡   比例:${obj.bili}%" onmouseover="if(allhide){showMe(this);}"   onmouseout="if(allhide){this.bgColor='';}"></td>
	          				   	</c:forEach>	          					        					
	          				</tr>
	          			</table>
          			</c:if>
          		</td>
          		
          		<td  style="width:33px;height:31px;background-image:url(manager/images/imgbg/louyubili05.png);background-repeat:no-repeat;background-position: left bottom;"></td>
          		<td></td>
          </c:if>  
	      </tr> 
	      
	      
	       
	       <!--  地平线   --> 
		    <c:if test="${list.storey==0}">
		    	<tr>
		          <td colspan="4" ><img src="manager/images/imgbg/louyubili08.png" width="100%"  style="height:12px"/></td>
		        </tr>
		    </c:if>     
      </c:forEach>  
      
                
    
    </table></td>
	 <td></td>
	</tr>
	
	<tr>
		<td char="4" style="height: 50px;"></td>
	</tr>
</table>
</c:if>
</div>
  </div>

</body>
</html>