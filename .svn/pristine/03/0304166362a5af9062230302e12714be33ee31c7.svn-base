<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <title>点位统计——告警分类列表</title>
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
//分页
function formCheck(currentPage) {
    if (currentPage != "") {
        $("#currentPage").val(currentPage);
    }
    //获取日期和类型
    //如果两者都不为空的话，默认给分页赋值为'1'
    var billMonth = $("#billMonth").val();
    var seType = $("#seType").val();
    if((billMonth!=null && billMonth!='')&&(seType!=null && seType!='')){
    	 $("#currentPage").attr("value","1");
    }
    document.forms[0].submit();
}
function callss(){
var energyType =$("#seType").val();
 	 if(energyType == null || energyType ==""){
			alert("请选择类型");
			return;
	}else{
    	$.ajax({
			  	type: 'get',
                 url: "energyBill_getAjaxEnergy.action",
                  data:"energyType="+energyType,
                   cache:false,
                   dataType: 'json',
		 		 success:function(data){
		 		 	alert(data[0].seq);
		 			alert(data[0].energy);
		 		if(data[0].seq !="1" && data[0].seq !="2" &&data[0].seq !="3" &&data[0].seq !="4" &&data[0].seq !="5" ){
		 			$("#seType").val(data[0].energy);
		 		}else{
		 			 $("#seType").val(data[0].energy); 
		 		}
			}
		}); 
	}
}
function changeAction(type){
    $("#type").val(type);
    $("#currentPage").val(1);
    document.forms[0].submit();
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
                            能源账单——能源账单列表
                        </td>
                    </tr>
                </table>
            </div>
            
            <s:form action="energyBill_findEnergyBill.action" theme="simple"
                method="post" name="myform" id="myform">
                <input type="hidden" name="correctMsg" value="1" id="correctMsg" />
                <input type="hidden" name="typesss"  id="typesss" value="${sessionScope.typesss}" />
                <input type="hidden" name="currentPage" value="${currentPage}" id="currentPage" />
                <c:if test="${messeage !=  '1'}">
                	<span  class="admin_clor_f00" >${messeage}</span>
                </c:if>
            
            <div style="width:100%;float: right">
                <table  width="100%" border="0" class="louyujj_xiaxian_hui gai_left_duiqi" style="float: right">
                    <tr>
                        <td width="80px"></td>
                        <td width="60px" class="shebeigl_inp_zt" align="right">账单月份</td>
                        <td width="150px">
                           <input  class="Wdate" type="text" name="billMonth" id="billMonth" 
                                     style="width: 150px;" value ="${billMonth}"
                                  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" maxlength="7" />
                            </td>
                        <td  width="60px" class="shebeigl_inp_zt" align="right">能源类型：</td>
                        <td  width="150px" >
                            <select style="width:150px;cursor: default;" name="energyBill.type"  id="seType" >
                             <option value="" selected="selected">--全部--</option>
                            <c:forEach items="${energyTypeList}" var="energyType" varStatus="ind" >
                                 <c:if test="${energyBill.type==energyType.seq}">
                                 	<c:if test="${energyType.seq ==  '1'}">
                                 		<option value="${energyType.seq}"  selected="selected">${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.seq ==  '2'}">
                                 		<option value="${energyType.seq}"  selected="selected">${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.parent_id ==  '2'}">
                                 		<option value="${energyType.seq}"  selected="selected">&nbsp;&nbsp;&nbsp;&nbsp;${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.seq ==  '3'}">
                                 		<option value="${energyType.seq}" selected="selected" >${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.parent_id ==  '3'}">
                                 		<option value="${energyType.seq}"  selected="selected">&nbsp;&nbsp;&nbsp;&nbsp;${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.seq ==  '4'}">
                                 		<option value="${energyType.seq}"  selected="selected">${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.parent_id ==  '4'}">
                                 		<option value="${energyType.seq}"  selected="selected">&nbsp;&nbsp;&nbsp;&nbsp;${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.seq ==  '5'}">
                                 		<option value="${energyType.seq}"  selected="selected">${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.parent_id ==  '5'}">
                                 		<option value="${energyType.seq}"  selected="selected">&nbsp;&nbsp;&nbsp;&nbsp;${energyType.energy}</option>
                                 	</c:if>
                                </c:if>
                                 <c:if test="${energyBill.type!=energyType.seq}">
                                 	<c:if test="${energyType.seq ==  '1'}">
                                 		<option value="${energyType.seq}" >${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.seq ==  '2'}">
                                 		<option value="${energyType.seq}" >${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.parent_id ==  '2'}">
                                 		<option value="${energyType.seq}" >&nbsp;&nbsp;&nbsp;&nbsp;${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.seq ==  '3'}">
                                 		<option value="${energyType.seq}" >${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.parent_id ==  '3'}">
                                 		<option value="${energyType.seq}" >&nbsp;&nbsp;&nbsp;&nbsp;${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.seq ==  '4'}">
                                 		<option value="${energyType.seq}" >${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.parent_id ==  '4'}">
                                 		<option value="${energyType.seq}" >&nbsp;&nbsp;&nbsp;&nbsp;${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.seq ==  '5'}">
                                 		<option value="${energyType.seq}" >${energyType.energy}</option>
                                 	</c:if>
                                 	<c:if test="${energyType.parent_id ==  '5'}">
                                 		<option value="${energyType.seq}" >&nbsp;&nbsp;&nbsp;&nbsp;${energyType.energy}</option>
                                 	</c:if>
                                </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td width="20px">
                            <img style='cursor: pointer'
                                    src="manager/images/imgs/shebeigl_sousuo_an.png"
                                    onclick="formCheck('')"></img>  
                        </td>
                        
                        
                        <td  align="right" width="20px">
                               <a href="energyBill_insertEnergyBillBefore.action?type=1" class="btn blue" align="left">添加</a>
                        </td>
                </tr>
                </table>
            
            <table width="100%" border="0" cellspacing="1"
                class="biaoge_bg1 gai_left_duiqi" >
                <thead>
                    <tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
                        <th width="5%" scope="col">
                            序号
                        </th>
                        <th width="8%" scope="col">
                            能源类型
                        </th>
                        <th width="10%" scope="col">
                            能源账单月份
                        </th>
                        <th width="8%" scope="col">
                            能源单价
                        </th>
                        <th width="14%" scope="col">
                            能源账单金额
                        </th>
<%--                        <th width="20%" scope="col">--%>
<%--                            条形码--%>
<%--                        </th>--%>
                        
                     <!--  <th width="10%" scope="col"> --> 
                     <!--        使用量--> 
                     <!--   </th>--> 
                     <!--     <th width="20%" scope="col"> --> 
                     <!--   备注  --> 
                     <!--    </th> --> 
                      <th width="12%" scope="col">
                          使用量
                        </th>
                        <th width="12%" scope="col">
                            录入时间
                        </th>
                        <th width="12%" scope="col">
                            操作
                        </th>
                    </tr>
                </thead>
                <tbody id="stu-datas-tb">

                    <c:if test="${!empty energyBillList}">
                        <c:forEach items="${energyBillList}" var="energyBill" varStatus="index">
                            <tr class=" biaog_kan2 biaog_zt2"  >
                                <td align="center">
                                    ${index.count} 
                                </td>
                                <td align="center">
                                    <c:forEach items="${energyTypeList}" var="energyType" varStatus="ind">
                                       <c:if test="${energyType.seq==energyBill.type}">${energyType.energy}</c:if>
                                    </c:forEach>
                                </td>
                                <td align="center">
                                    ${energyBill.bill_month }
                                </td>
                     <c:choose>
					<c:when test="${energyBill.unitprice !=null}">
					  <td align="center">
						<fmt:formatNumber type="number" 
           								 maxFractionDigits="3" value="${energyBill.unitprice}"/>
                                	<small> (元)</small> 
                       </td>
						</c:when>
					<c:otherwise>
							<td></td>
					</c:otherwise>
					</c:choose>
                      <c:choose>
					<c:when test="${energyBill.sum !=null}">
					<td align="center" >
						<fmt:formatNumber type="number" 
           								 maxFractionDigits="3" value="${energyBill.sum }"/>
                                 <small> (元)</small>
                      </td>
						</c:when>
					<c:otherwise>
							<td></td>
					</c:otherwise>
					</c:choose>
		
				<c:choose>
					<c:when test="${energyBill.consumption !=null}">
					<td align="center" >
						<fmt:formatNumber type="number" 
           								 maxFractionDigits="3" value="${energyBill.consumption}"/>
                              <c:forEach items="${energyTypeList}" var="energyType" varStatus="ind"><c:if test="${energyType.seq==energyBill.type}"><small>（${energyType.unit}）</small> </c:if></c:forEach>
						</td>
						</c:when>
					<c:otherwise>
							<td></td>
					</c:otherwise>
					</c:choose>
                                <td align="center" >
                                    ${energyBill.optime }
                                </td>
                                <td align="center" >
                                    <a href="energyBill_insertEnergyBillBefore.action?type=2&seq=${energyBill.id}">修改</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <tr>
                    </tr>
                </tbody>
            </table>
            </div>
        </s:form>
            <c:if test="${!empty energyBillList}">
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
            <!--页面结束 -->
    </body>
</html>
