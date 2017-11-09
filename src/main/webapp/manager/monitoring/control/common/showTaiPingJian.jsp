<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

        <title>My JSP 'showUnControlList.jsp' starting page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="stylesheet" href="manager/css/neirong.css" type="text/css"></link>
        <link rel="stylesheet" href="manager/css/skdiv.css" type="text/css"></link>
        <link rel="stylesheet" href="manager/css/ziti.css" type="text/css"></link>
        <link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
        <link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
        <script src="manager/js/annu.js" type="text/javascript"></script>
        <script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
         <script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
<!--        <script type="text/javascript" src="manager/js/tableTr.js"></script>-->
        <script type="text/javascript" src="manager/javascript/buildStorey/buildStorey.js"></script>
        <script type="text/javascript" src="manager/js/tableTr.js"></script>
       
        <script type="text/javascript">
            function formCheck(currentPage) {
                if (currentPage != "") {
                    $("#unGrcurrentPage").val(currentPage);
                }
                if ($('#inpStorey').css('display') == 'block') {
                    $('#storey').val($('#inpStorey').val());
                }
                document.myForm.submit();
            }
            //js停留（参数毫秒）
            function sleep(numberMillis) {
                var now = new Date();
                var exitTime = now.getTime() + numberMillis;
                while (true) {
                    now = new Date();
                    if (now.getTime() > exitTime)
                        return;
                }
            }
            //显示监控界面
            function showControlPage(buildId, equipTypeId, equipid) {
                $(window.parent.document).find("#commonShow_" + equipTypeId).slideToggle(
                        1000);
                $(window.parent.document).find("#equipList_" + equipTypeId).slideToggle(
                        1000);
                //$(window.parent.document).find("#controlDescript_" + equipTypeId).attr(
                    //  {
                            //src : 'control_findEquipByParam.action?buildId=' + buildId
                                //  + "&eqTypeId=" + equipTypeId + "&t=" + Math.random()
                        //});
                //sleep(1000);
                $(window.parent.document).find('#controlDescript_'+equipTypeId).attr({width:0});
                $(window.parent.document).find("#controlPage_" + equipTypeId).attr(
                        {
                            src : 'control_findToControl.action?equipId=' + equipid + "&eqTypeId="
                                    + equipTypeId + "&buildId=" + buildId+"&t=" + Math.random()
                        });
            }
            
            goPowerControl = function(equipId, equipTypeId) {
                $("#equipId").val(equipId);
                //document.myForm.action = "";
                //document.myForm.submit(); 
                $(window.parent.document).find("#commonShow_" + equipTypeId).slideToggle(
                        1000);
                $(window.parent.document).find("#equipList_" + equipTypeId).slideToggle(
                        1000);
                $(window.parent.document).find("#controlDescript_" + equipTypeId).attr( {
                    width : 0
                });
                $(window.parent.document).find("#controlPage_" + equipTypeId).attr(
                        {
                            src : 'control_showPowerContorl.action?equipId=' + equipId
                                    + "&eqTypeId=" + equipTypeId + "&t=" + Math.random()
                        });
            }
            noControl = function() {
                alert('水表暂无监控页面！')
            }
            var noOtherControl = function() {
                alert('天然气暂无监控页面！')
            }

            function changeBuild(obj, childId) {
                var buildId = $(obj).val();
                if(jQuery.trim(buildId)!=""){
                     var dt={"buildId":buildId};
                     $.ajax({
                          type: 'get',
                          url: "equipment_findStoreyByBuildId.action",
                          data: dt,
                          cache:false,
                          dataType: 'json',
                          success: function(data){
                             $('#inpStorey').hide();
                             var arrayObj = new Array();　//创建一个数组
                             var storey=eval(data);
                             var cid=$(childId).attr('id');
                             $("'#"+cid+"' option").remove();
                             $(childId).append("<option value=''>--请选择所在楼层--</option>");
                             $(childId).append("<option value='"+(parseInt(storey[0])+parseInt(1))+"'>屋顶</option>");
                             for(var k=0;k<storey.length;k++){
                               var valu=String( storey[k]);
                               valu=valu.replace("-",'B');
                               $(childId).append("<option value='"+storey[k]+"'>"+valu+"</option>");
                             }
                             $("#"+cid).show();
                          },
                          error:function(textStatus){
                              alert('error'+textStatus);
                          }
                     });
                 }else{
                     var cid=$(childId).attr('id');
                     $("'#"+cid+"' option").remove();
                     $(childId).append("<option value=''>--请选择楼层--</option>");
                     $("#"+cid).hide();
                     $('#inpStorey').show();
                 }
             }
             function selectChild(obj){
                 $('#storey').val($(obj).val());
             }
             $(function(){
                 initDataFun('buildId','storey','${buildId}');
                 initDataFun('serviceBuildId','serviceStorey','${serviceBuildId}');
         } );
         
     //点击所在楼宇“--请选择--”所在楼层是灰掉
	 $(document).ready(function(){
				  $("#buildId").change(function(){
				    if("0".equals($("#buildId").val())){
				        $("#storey").attr("disabled",true);
				     }else{
				        $("#storey").attr("disabled",false);
				     }
				  });
				});
	//点击服务楼宇“--请选择--”服务楼层是灰掉
	 $(document).ready(function(){
				  $("#serviceBuildId").change(function(){
				    if("0".equals($("#serviceBuildId").val())){
				        $("#serviceStorey").attr("disabled",true);
				     }else{
				        $("#serviceStorey").attr("disabled",false);
				     }
				  });
				});			
            </script>
    </head>

    <body>
        <form id="myForm" name="myForm" action="controlPage_showEquipList.action"
            method="post">
            <input type="hidden" id="eqTypeId" name="eqTypeId"
                value="${eqTypeId }" />
            <input type="hidden" id="unGrcurrentPage" name="unGrcurrentPage"
                value="" />
            <input type="hidden" id="equipId" name="equipId" value="" />
            <input type="hidden" id="buildingId" name="buildingId" value="" />
            <input type="hidden" id="flag" name="flag" value="flag" />
            <input type="hidden" id="falg" name="falg" value="${falg }" />
            <div id="serachDiv" style="float: left;" class="serachDiv">
                <table width="95%" class="serachTable" border="0" >
                    <tr>
                        <td height="10px" width="12%"
                            style="margin-top: 0px;" align="right" >
                            所在楼宇：
                        </td>
                        <td width="12%">
                            <select name="buildId" id="buildId" style="width: 150px;"
                                onchange="getStoreyByBuildId('buildId','storey','${storey }','');" >
                                <option value="">
                                    --请选择楼宇--
                                </option>
<%--                                <c:forEach items="${listBuilding}" var="building">--%>
<%--                                    <option value="${building.buildingid}"--%>
<%--                                        ${building.buildingid==buildId?'selected' : ''}>--%>
<%--                                        ${building.buildingname}--%>
<%--                                    </option>--%>
<%--                                </c:forEach>--%>
                            </select>
                        </td>
                        <td width="12%" align="right">
                            所在楼层：
                        </td>
                        <td width="12%">
                            <input type="text" id="storey" name="storey" value="${storey} "  disabled="disabled"
                            onkeyup="value=value.replace(/[^\-?\d.]/g,'')" maxlength="2"/>
                        </td>
                            <td width="12%" align="right">
                                 安装位置：
                            </td>
                            <td>
                                <input type="text" name="place" id="place" value="${place }" />
                            </td>
                            <td colspan="2" align="right">
                                <img style='cursor: pointer; display: block;'
                                    src="manager/images/imgs/shebeigl_sousuo_an.png" id="sousuo"
                                    onclick="formCheck(1)" />
                            </td>
                    </tr>
             
                </table>
            </div>
        </form>
        <div class="itemList">
            <table width="100%" border="0" cellspacing="1"
                class="listTable">
                <thead>
                    <tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
                        <td align="center">
                            序号
                        </td>
                        <td align="center">
                           故障状态
                        </td>
                        <td align="center">
                            设备名称
                        </td>
                        <td align="center">
                            设备品牌
                        </td>
                        <td align="center">
                            设备型号
                        </td>
                      
                        <td align="center">
                            安装位置
                        </td>
                        <td align="center">
                            所在楼层
                        </td>
                       
                    </tr>
                </thead>
                <tbody id="stu-datas-tb">
                    <c:forEach items="${listEquipList}" var="equipment" varStatus="idx">
                        <c:choose>
                            <c:when test="${eqTypeId == 10001}"> 
							    <tr class="${idx.index%2==0?'tr1':'tr2'}" 
                                      <c:if test="${1==equipment.da || -1==equipment.da || null==equipment.da}">
                                         style="color: red"
                                      </c:if> 
                                    >
                            </c:when>
                            <c:when test="${eqTypeId == 10002}">
                                <tr class="${idx.index%2==0?'tr1':'tr2'}" onclick="noControl();">
                            </c:when>
                            <c:when test="${eqTypeId == 12001}">
                                <tr class="${idx.index%2==0?'tr1':'tr2'}" onclick="noOtherControl();">
                            </c:when>
                            <c:otherwise>
                                <tr class="${idx.index%2==0?'tr1':'tr2'}"
                                    onclick="showControlPage(${equipment.building_id},${equipment.equip_type_id},${equipment.equipid})">
                            </c:otherwise>
                        </c:choose>
                        <td align="center">
                            ${idx.index+1 }
                        </td>
                        <td align="center">
                                                    <c:if test="${equipment.da==-1 || equipment.da==null}">采集失败</c:if>
                                                    <c:if test="${equipment.da==0}">正常</c:if>
													<c:if test="${equipment.da==1}">故障</c:if>
                        </td>
                        <td align="center" class="biao_lianj_1">
                            ${equipment.equip_name }
                        </td>
                        <td align="center">
                            ${equipment.brand }
                        </td>
                        <td align="center">
                            ${equipment.unittype }
                        </td>
                       
                        <td align="center">
                            ${equipment.place }
                        </td>
                        <td align="center">
                            <c:if test="${!empty equipment.storey_num_up}">
                                   <c:if test="${equipment.storey<= equipment.storey_num_up&&equipment.storey>0}">
                                       ${equipment.storey }
                                   </c:if>
                                   <c:if test="${equipment.storey>equipment.storey_num_up}">
                                                        屋顶
                                   </c:if>
                                </c:if>
                                <c:if test="${equipment.storey<0}">
                                 B${equipment.storey<0?-equipment.storey:equipment.storey}
                             </c:if>
                             </td>
                       
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- 分页从这里开始 -->
        <c:if test="${!empty listEquipList}">
            <div class="PageingEquip">
                <table width="100%">
                    <tr style="height: 50px;">
                        <td align="right">
                            <sktag:paginator showTotal="true" showAllPages="true"
                                strUnit="条记录" />
                        </td>
                    </tr>
                </table>
            </div>
        </c:if>
        <c:if test="${empty listEquipList}">
            <script type="text/javascript">
if (!${listEquipList}||${listEquipList} == null) {
    $("#stu-datas-tb").text('您所查询的相关数据为空，请重新输入“查询条件”！')
}
</script>
        </c:if>
    </body>
</html>
