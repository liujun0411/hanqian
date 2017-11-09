<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js"
			type="text/javascript">
		</script>
		<base href="<%=basePath%>" />
		<title>楼宇基建 图纸列表</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />

		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
		<script type="text/javascript" src="manager/js/ceng.js">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/xialakuang.js">
</script>


<%-- 		<link href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/> --%>
<%-- 		<link href="<c:url value="/static/css/aries/jquery.aries.css"/>" rel="stylesheet" type="text/css" /> --%>
<%-- 		<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>" rel="stylesheet" type="text/css" /> --%>
<%-- 		<link href="<c:url value="/static/css/dropdown/jquery.dropdown.css"/>" rel="stylesheet" type="text/css"/> --%>
<%-- 		<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script> --%>
<%-- 		<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script> --%>
<%-- 		<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script> --%>
<%-- 		<script src="<c:url value="/static/js/dropdown/jquery.dropdown.min.js"/>"></script> --%>


<script type="text/javascript" src="manager/javascript/buildStorey/buildStorey.js"></script>
		<!-- script src="manager/js/selectEL.js" type="text/javascript"></script> -->

		<!--<script type="text/javascript">
		    //回车事件添加到检索按钮
			document.onkeydown = function (e) {
				var theEvent = window.event || e;
				var code = theEvent.keyCode || theEvent.which;
				if (code == 13) {
					alert('aaaa');
				}else{
				  alert('bbbbb');
				}
		</script>

		-->
		<script type="text/javascript">

toAdd = function() {
	location.href = "equipment_toAddPage.action";
}

var handle;
function start() {
	var obj = document.getElementById("tip");
	if (parseInt(obj.style.height) == 0) {
		obj.style.display = "block";
		handle = setInterval("changeH('up')", 2);
	} else {
		handle = setInterval("changeH('down')", 2)
	}
}
function changeH(str) {
	var obj = document.getElementById("tip");
	if (str == "up") {
		if (parseInt(obj.style.height) > 100)
			clearInterval(handle);
		else
			obj.style.height = (parseInt(obj.style.height) + 8).toString()
					+ "px";
	}
	if (str == "down") {
		if (parseInt(obj.style.height) < 8) {
			clearInterval(handle);
			obj.style.display = "none";
		} else
			obj.style.height = (parseInt(obj.style.height) - 8).toString()
					+ "px";
	}
}

function recover() {
	document.getElementsByTagName("html")[0].style.overflow = "auto";
	document.getElementById("shadow").style.display = "none";
	document.getElementById("detail").style.display = "none";
}

//分页
function formCheck(currentPage) {
	if (currentPage != "") {

		$("#currentPage").val(currentPage);
	}
	document.forms[0].submit();
}

    function updateSubmit(picId) {  
    	if(window.confirm("您确定删除该张图纸吗？")){
		    var url = "buildingPic!updatePic.action"; 
		    $("#picId").val(picId); 
		    var form = $("#myform");  
		    form.attr("action",url)  
		    form.submit();  
	  	}
    }
    
    $(function(){
    		 initDataFun('buildingName','storey','${dbBuildingPic.dbBuilding.buildingName}',1);
     });
     
     //1.js验证只能输入数字.
	 function NumOnly(e) { 
            var key; 
            if (window.event) { 
                key = event.keyCode; 
            } 
            else { 
                key = e.which; 
            } 
            if (key > 47 && key < 58 || key == 8 || key == 45) { 
                return; 
            } 
            else if (window.event) { 
                window.event.returnValue = null; 
            } 
            else { 
                if (e && e.preventDefault) 
                    e.preventDefault(); 
            } 
        } 
        
     //点击所在楼宇“--请选择--”所在楼层是灰掉
	 $(document).ready(function(){
				  $("#buildingName").change(function(){
				    if("0".equals($("#buildingName").val())){
				        $("#storey").attr("disabled",true);
				     }else{
				        $("#storey").attr("disabled",false);
				     }
				  });
				});
     
     
	//任意提醒，通过标签ID给需要绑定数据的元素绑定数据
// 	 $(document).ready(function(){
// 	 	 	var remindJson =${remindJson};
// 	 	 	for(var obj in remindJson){
// 	  	 				console.debug("   remindJson[obj]=     "+remindJson[obj].resultsql + ",obj=" + obj + ", $(obj)=" + $("#"+obj));
// 	 	 				$("#"+obj).data("aries",remindJson[obj]);
// 	 	 		}
// 	 	});
</script>
	</head>
	<body>
	<br/>
			<s:form action="buildingPic!findBuilbingPic.action" theme="simple"
				method="post" name="myform" id="myform">

				<input type="hidden" name="currentPage" value="${currentPage}" id="currentPage" />
				<input type="hidden" name="dbBuildingPic.picId" value="" id="picId" />
				<table border="0" class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr align="right">
						<td width="100px" align='right' class='shebeigl_inp_zt'>
							楼宇：
						</td>
						<td align="left" width='80'>
								<select name="dbBuildingPic.dbBuilding.buildingName" id="buildingName" style="width: 160px;" onchange="getStoreyByBuildId('buildingName','storey','${dbBuildingPic.storey }','',1);">
<%--									<option value="">--%>
<%--										--请选择所在楼宇----%>
<%--									</option>--%>
<%--									<c:forEach items="${buildList}" var="build">--%>
<%--										<option value="${build.buildingId }" ${buildId==build.buildingId?'selected':''}>--%>
<%--											${build.buildingName }--%>
<%--										</option>--%>
<%--									</c:forEach>--%>
								</select>
							
							
<!--							<input type="text" name="dbBuildingPic.dbBuilding.buildingName"-->
<!--								id="buildingName"  value="${!empty dbBuildingPic.dbBuilding?dbBuildingPic.dbBuilding.buildingName:''}" onchange="getStoreyByBuildId('buildId','storey','${storey }','');"/>-->
						</td>
						<td class="shebeigl_inp_zt" align="right" width="90">
							楼层：
						</td>
						<td width="80" align="right">
						<!-- edit start 2013.4.12 by zhangdiannan
						              限制楼层只能输入数字，且只能为整形 -->
							<input type="text"  name="storey" id="storey" value="${dbBuildingPic.storey}"  
								disabled="disabled" onkeyup="value=value.replace(/[^\-?\d]/g,'')"/>
						<!-- edit end 2013.4.12 by zhangdiannan -->
						</td>
						<td class="shebeigl_inp_zt" align="right" width="90">
							图纸类型：
						</td>
						<td width="80" align="left">
							<s:select name="dbBuildingPic.picType.seq"
								id="hospitalSel" cssStyle="width: 150px;" headerValue="--请选择--" headerKey=""
								listKey="seq" listValue="content1"
								list="dbBuildingPicTypes"
								value="dbBuildingPic.picType.seq">

							</s:select>
						</td>
						<td class="shebeigl_inp_zt" align="right" width="90">
							图纸名称：
						</td>
						<td width="80" align="right">
							<input type="text" name="dbBuildingPic.picName" id="unitType" value="${dbBuildingPic.picName}"/>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="right">
							<img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="javascript:document.forms[0].submit();"></img>
							&nbsp;&nbsp;
							<c:if test="${!empty menuMap && !empty menuMap['2002003005002']}">
							<a href="buildingPic!toUpload.action" class="btn blue"
								align="left">添加</a>
								</c:if>
						</td>
					</tr>
				</table>
			</s:form>
			<c:if test="${empty dbBuildingPics}">
				<span style="color:red">${msg}</span>
			</c:if>
		<c:if test="${!empty errorMsg}">
			<span style="color:red">${errorMsg}</span>
		</c:if>
<c:if test="${!empty dbBuildingPics}">
			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="5%" scope="col">
							序号
						</th>
						<th width="10%" scope="col">
							楼宇名称
						</th>
						<th width="10%" scope="col">
							图片名称
						</th>
						<th width="13%" scope="col">
							图片类型
						</th>
						<th width="10%" scope="col">
							楼层
						</th>
						<th width="15%" scope="col">
							上传人
						</th>
						<th width="13%" scope="col">
							上传时间
						</th>
						<th width="8%" scope="col">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">

					
						<c:forEach items="${dbBuildingPics}" var="b" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2">
								<td align="center">
									${index.count}
								</td>
								<td align="center">
									${b.building_name}
								</td>
								<td align="center">
									${b.pic_name}
								</td>
								<td align="center">
									${b.pic_type_name}
								</td>
								<td align="center">
								    <c:if test="${!empty b.storey_up}">
								       <c:if test="${b.storey<= b.storey_up&&b.storey>0}">
								           ${b.storey }
								       </c:if>
								       <c:if test="${b.storey>b.storey_up}">
								                            屋顶
								       </c:if>
								    </c:if>
								    <c:if test="${b.storey<0}">
								   	 B${b.storey<0?-b.storey:b.storey}
								    </c:if>
								     
								</td>
								</td>
								<td align="center">
									${b.oper}
								</td>
								<td align="center">
									<fmt:formatDate value="${b.opertime}" pattern="yyyy-MM-dd" />
								</td>

								<td align="center" class="biao_lianj_1">
<%--									<c:if test="${!empty menuMap && !empty menuMap['2002003005003']}">--%>
<%--									<a href="buildingPic!buildingPicDownload.action?--%>
<%--									fileName=${b.pic_name}--%>
<%--											${fn:substring(b.pic_address, --%>
<%--											fn:indexOf(b.pic_address,'.'), --%>
<%--											fn:length(b.pic_address))}&--%>
<%--									fileUrl=${b.pic_address}">下载</a>--%>
<%--									</c:if>--%>
									<c:if test="${!empty menuMap && !empty menuMap['2002003005003']}">
									<a href="buildingPic!buildingPicDownload.action?
									fileName=${b.pic_name}
											${fn:substring(b.pic_address, 
											fn:indexOf(b.pic_address,'.'), 
											fn:length(b.pic_address))}&
									fileUrl=${b.pic_address}">下载</a>
									</c:if>
									
									<c:if test="${!empty menuMap && !empty menuMap['2002003005004']}">
									<a href="javascript:updateSubmit(${b.pic_id});">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					
				</tbody>
			</table>
			<c:if test="${!empty dbBuildingPics}">
				<table width="100%" border="0" class="gai_left_duiqi">
					<tr style="height: 10px;">
						<td width="35%"></td>
						<td width="65%"></td>
					</tr>
					<tr style="height: 30px;">
						<td colspan="5">
							<sktag:paginator showTotal="true" showAllPages="true"
								strUnit="条记录" />

						</td>
					</tr>
				</table>
			</c:if>
		</c:if>
		
			<!--页面结束 -->
	</body>
</html>
