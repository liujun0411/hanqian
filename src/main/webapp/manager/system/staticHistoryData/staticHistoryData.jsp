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
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript">
		</script>
		<base href="<%=basePath%>" />
		<title>平台静态历史数据</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />
		

		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/ceng.js">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
		<script type="text/javascript">
		
function changeTableName(obj){
	$('#tableName').val($(obj).val());
	document.myform.submit();
}

$(function(){
	var cols = $('#columns').val();
	if($('#tableName').val()!=""){
		$('#left_select').val($('#tableName').val());
	}
	if(cols!=""){
		var colArr = new Array;
		var html = '<th width="12%" scope="col">序号</th>';
		colArr = cols.split(',');
		for(var i=0;i<colArr.length;i++ ){
			html+='<th width="12%" scope="col">'+colArr[i]+'</th>';
		}
		//html+='<th width="12%" scope="col">操作</th>';
		$('#dataList tr').eq(0).html(html);
	}

	/**$('#dataList tr td').each(function(){
	   if($.trim($(this).text())==""){
		   $(this).remove();
	   }	
	});**/
});
function formCheck(currentPage) {
    if (currentPage != "") {
        $("#currentPage").val(currentPage);
    }
    document.forms[0].submit();
}
function chakan(obj){
	$('#changeDiv').hide();
	$('#detailDiv').load(obj,function(){
		var name = $('#redName').val();
		var value=$('#redNameValue').val();
		var strs= new Array();
		var str=new Array();
		strs = name.split("!");
		str = value.split("!");
		$('#detailDiv').find('.admin_bgclor_e3f').each(function(){
			for(var i = 0 ;i<strs.length;i++){
				if($.trim($(this).text())==strs[i]){
					$(this).css('background','yellow');
					$(this).next().css('background','yellow');
					if($.trim(str[i])==''){
						$(this).next().text($(this).next().text()+" → ( )");
					}else{
						$(this).next().text($(this).next().text()+" → ("+str[i]+")");
					}
				}
			}
		});
	});
}
</script>

	</head>

	<body>
        
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="100%" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td class="biaoti_zt_canshusz">
							平台静态历史数据
						</td>
					</tr>
				</table>
			</div>
			
			<s:form action="staticHistory_StaticHistoryData.action"
				method="post" name="myform" id="myform">
				<input type="hidden" name="currentPage" value="${currentPage}" id="currentPage" />
				<input type="hidden" name="tableName" value="${tableName}" id="tableName" />
				<input type="hidden" id="columns" value="${columns}" />
				<select id="left_select" name="left_select" style="HEIGHT: 20px; WIDTH: 120px" onchange="changeTableName(this);">
			            <OPTION VALUE="DB_BUILDING_LOG">楼宇历史表</OPTION>
			            <OPTION VALUE="DB_BUILDING_PIC_LOG">楼宇图纸历史表</OPTION>
			            <OPTION VALUE="DB_BUILDING_REPAIR_LOG">楼宇维修历史表</OPTION>
			            <OPTION VALUE="DB_BUILDING_STOREY_LOG">楼宇楼层历史表</OPTION>
			            <OPTION VALUE="DB_BUILD_AREA_LOG">楼宇面积记录历史表</OPTION>
			            <OPTION VALUE="DB_BUILD_MATER_LOG">建筑数据历史表</OPTION>
			            <OPTION VALUE="DB_EQUIP_GROUP_LOG">设备分组历史表</OPTION>
			            <OPTION VALUE="DB_EQUIP_LIST_LOG">设备列表历史表</OPTION>
			            <OPTION VALUE="DB_EQUIP_PIC_LOG">设备图纸历史表</OPTION>
			            <OPTION VALUE="DB_EQUIP_TYPE_LOG">设备类型历史表</OPTION>
                 </select>
			</s:form>
			<div style="width:100%;" id="changeDiv">
			<table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi" id="dataList">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						
					</tr>
				</thead>
				<tbody id="stu-datas-tb">

					<c:if test="${!empty voStaticHistoryDataList}">
						<c:forEach items="${voStaticHistoryDataList}" var="staticData" varStatus="index">
							<tr class=" biaog_kan2 biaog_zt2" ondblclick="chakan('staticHistory_StaticHistoryDataById.action?seq=${staticData.log_seq}&tableName=${tableName}');">
								<td align="center">
									${index.count} 
								</td>
								<td align="center" >
								<c:choose>
									<c:when test='${staticData.col4=="update"}'>
									修改
									</c:when>
									<c:when test='${staticData.col4=="delete"}'>
									删除
									</c:when>
									<c:otherwise>
									${staticData.col4 }
									</c:otherwise>
								</c:choose>
								</td>
								<td align="center" >
									${staticData.col5 }
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
					</tr>
				</tbody>
			</table>

			<c:if test="${!empty voStaticHistoryDataList}">
				<div style=" width:100%; ">
					<div style="position: relative; float:left; width:88%;">
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
					</div>
			    </div>
		</c:if>
		</div>
		
		<div id="detailDiv">
		
		</div>
			<!--页面结束 -->
	</body>
</html>
