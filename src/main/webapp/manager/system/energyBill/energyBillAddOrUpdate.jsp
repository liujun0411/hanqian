<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=utf-8");
response.setCharacterEncoding("utf-8");
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <base href="<%=basePath%>"/>
        
        

            <title>能源账单- ${update==1?'能源账单添加':'能源账单修改'}</title>

            <meta http-equiv="pragma" content="no-cache"/>
            <meta http-equiv="cache-control" content="no-cache"/>
            <meta http-equiv="expires" content="0"/>
            <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
            <meta http-equiv="description" content="This is my page"/>
            <link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
            <link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
            <link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
            <link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
            <link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
             <link href="manager/css/message.css" rel="stylesheet" type="text/css" />
        
            <script type="text/javascript"  src="manager/js/jquery-1.7.2.js"></script>
           <script type="text/javascript" src="manager/js/jquery.validate.js"></script>
           <script src="manager/js/biaoge_1.js" type="text/javascript">
</script>
            <script src="manager/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
       
<script language="JavaScript">
         var defaultHtml="";
<%--         //验证成功后事件 添加--%>
         function addBillCheck(){
             if(checkIsRules()){
                document.forms[0].submit();
             }
         }
<%--          //验证成功后事件--%>
         function updateBillCheck(){
            var url = "energyBill_updateEnergyBill.action";
            //var url = "energyBill_updateBill.action";
            if(checkIsRules()){
                var form = $("#myform");
                form.attr("action", url);
                form.submit();
            }
            
            
         }
          function submitSum(){
            var unitPrice = $('#unitPrice').val();//单价
            var totalSum = $('#totalSum').val();//总额
            var consumption = $('#consumption').val();//使用量
            //判断不为空
             if(unitPrice!=''&&totalSum!=''&&consumption!=''){
             //判断是否 单价*使用量=总额
               if(unitPrice*consumption!=totalSum){
                 $("#SumPrompt").html("单价ｘ使用量≠总额").css({"color":"red","font-size":"14px",
                 "font-family":"微软雅黑","width":"200px",
                 "height":"26px","position":"relative","top":"36px","right":"-782px","text-align":"center"});
               }else{
                 $("#SumPrompt").empty();             
               }
             }else{
                 $("#SumPrompt").empty();             
               }
            }
        function checkIsRules(){
            var unitPrice = $('#unitPrice').val();
            var totalSum = $('#totalSum').val();
            var billMonth = $('#billMonth').val();
            var enerytype = $('#enerytype').val();
            var consumption = $('#consumption').val();
            if(enerytype=='' || enerytype==undefined){
                alert("请选择账单类型");
                return false;
            }else if(billMonth=='' || billMonth==undefined){
                alert("请选择账单月份");
                return false;
            }else  if(unitPrice==''||unitPrice == undefined){
                alert("请输入单价");
                return false;
            }else{
                 if((unitPrice.indexOf(".")<0&&unitPrice.length>6)||(unitPrice.indexOf(".")>-1&&unitPrice.indexOf(".")>6)){
                    alert("单价整数只能输入6位小数");
                    return false;
                 }else if(unitPrice.indexOf(".")>-1&&unitPrice.indexOf(".")+3<unitPrice.length){
                    alert("单价只能输入两位小数");
                    return false;
              	}else if(totalSum==''||totalSum == undefined){
                        alert("请输入总额");
                        return false;
                    }else{
                        if((totalSum.indexOf(".")<0&&totalSum.length>8)||(totalSum.indexOf(".")>-1&&totalSum.indexOf(".")>8)){
                            alert("总额整数只能输入8位小数");
                            return false;
                        }else if(totalSum.indexOf(".")>-1&&totalSum.indexOf(".")+3<totalSum.length){
                            alert("总额只能输入两位小数");
                            return false;
                        }else if(consumption==''||consumption ==undefined){
            	  				 alert("请输入使用量");
               					 return false;
          				}else{
          					 if((consumption.indexOf(".")<0&&consumption.length>16)||(consumption.indexOf(".")>-1&&consumption.indexOf(".")>16)){
                            alert("使用量只能输入16位小数");
                            return false;
                        }else if(consumption.indexOf(".")>-1&&consumption.indexOf(".")+4<consumption.length){
                            alert("使用量只能输入三位小数");
                            return false;
          				}else{
                            return true;
                        }
                    }
                    }
                }
            }
        function doUpdateMonth(obj){
            if($('#FlagId').val()==1){
               //添加操作
               var dt={"typeId":$(obj).val()};
               $.ajax({
                      type: 'get',
                      url: "energyBill_getUsedBillMonth.action",
                      data: dt,
                      cache:false,
                      dataType: 'json',
                      success: function(resData){
                      var data;
                      var upDatePrice;
                       if(resData.length>0){
                          data = resData[0];
                          if(resData.length>1){
                              upDatePrice = resData[1];
                              $("#unitPrice").val(upDatePrice[0].unit_price);
                          }
                        
                          //如果账单类型未修改
                           if(data.length>0){
                               var oper = 'disabledDates:['; 
                               if(data.length>0){
                                   for(var t =0;t<data.length;t++){
                                       oper+="'"+data[t].bill_month+"',";
                                   }
                                   oper = oper.substring(0,oper.lastIndexOf(","));
                               }
                               oper+=']';
                               var newHtml = '<input class="Wdate" type="text" name="insertEnergyBill.billMonth" id="billMonth"  style="width: 150px;"   value = "" onfocus="WdatePicker({skin:\'whyGreen\',dateFmt:\'yyyy-MM\',value:\'\','+oper+'})" maxlength="7"/>';
                                   $('#td_billMonth').html(newHtml);
                           }else{
                               var newHtml = '<input class="Wdate" type="text" name="insertEnergyBill.billMonth" id="billMonth" style="width: 150px;"  readonly="readonly" value = "" onfocus="WdatePicker({skin:\'whyGreen\',dateFmt:\'yyyy-MM\',value:\'\'})" maxlength="7"/>';
                                   $('#td_billMonth').html(newHtml);
                           }
                         }else{
                             $("#unitPrice").val('');
                             var newHtml = '<input class="Wdate" type="text" name="insertEnergyBill.billMonth" id="billMonth"  style="width: 150px;"   value = "" onfocus="WdatePicker({skin:\'whyGreen\',dateFmt:\'yyyy-MM\',value:\'\'})" maxlength="7"/>';
                               $('#td_billMonth').html(newHtml);
                         }
                     
                          
                      },
                      error:function(textStatus){
                          alert(textStatus.statusText);
                      }
                  });
            }else{
                if($.trim($(obj).val())!=""){
                   var dt={"typeId":$(obj).val()};
                   //修改操作
                   $.ajax({
                      type: 'get',
                      url: "energyBill_changeMonthByTypeId.action",
                      data: dt,
                      cache:false,
                      dataType: 'json',
                      success: function(data){
                           //如果账单类型未修改
                           if($('#beforeEnergyType').val()==$(obj).val()){
                               $('#td_billMonth').html(defaultHtml);
                           }else{
                               //设置禁止更新的月份
                               var oper = 'disabledDates:['; 
                               if(data.length>0){
                                   for(var t =0;t<data.length;t++){
                                       oper+="'"+data[t].bill_month+"',";
                                   }
                                   oper = oper.substring(0,oper.lastIndexOf(","));
                               }
                               oper+=']';
                               var newHtml = '<input class="Wdate" type="text" name="insertEnergyBill.billMonth" id="billMonth" style="width: 150px;"  readonly="readonly" value = "" onfocus="WdatePicker({skin:\'whyGreen\',dateFmt:\'yyyy-MM\',value:\'\','+oper+'})" maxlength="7"/>';
                                   $('#td_billMonth').html(newHtml);
                           }
                      },
                      error:function(textStatus){
                          alert('error'+textStatus);
                      }
                  });
                }
            }
        }
        
        $(function(){
            var oper ='opposite:true,disabledDates:[';
            <c:forEach items="${monthRc}" var="item">
                oper+="'${item.bill_month}',";
            </c:forEach>
            oper = oper.substring(0,oper.lastIndexOf(","));
            oper+="]"; 
            if($('#FlagId').val()==1){
                oper='';
                defaultHtml = '<input  class="Wdate" type="text" name="insertEnergyBill.billMonth" id="billMonth" style="width: 150px;"   value = ""  onfocus="WdatePicker({skin:\'whyGreen\',dateFmt:\'yyyy-MM\',value:\'\','+oper+'})" maxlength="7"/>';
                $('#td_billMonth').html(defaultHtml);
            }else{
                defaultHtml = '<input  disabled="disabled" class="Wdate" type="text" name="insertEnergyBill.billMonth" id="billMonth" style="width: 150px;"  readonly="readonly" value = "${energyBillList[0].bill_month}" onfocus="WdatePicker({skin:\'whyGreen\',dateFmt:\'yyyy-MM\',value:\'${energyBillList[0].bill_month}\','+oper+'})" maxlength="7"/>';
                $('#td_billMonth').html(defaultHtml);
            }
        });
        
       //2及联动
      function call(){
     	 var tableId = $("#enerytype").val();
			  if(tableId == null || tableId ==""){
					$("#s2").val("-----------");
				  alert("请选择类型");
				  return;
			  } else {
				$.ajax({
			  		 type: 'get',
                       url: "energyBill_getAjaxType.action",
                       data:"tableId="+tableId,
                       cache:false,
                      dataType: 'json',
		 		 success:function(data){
		 			// alert(data[0].unit);
		 			$("#s2").html("("+data[0].unit+")");
					//$("#s2").val(data[0].unit);
			}
		}); 
	 }
      }
 </script> 
    </head>
    <body>
    <div id="admin_nr_rightg">
            <div class="canshusz_btbg_1">
            <table width="780" border="0">
                    <tr style="height:30px">
                        <td width="34" align="center">
                            <img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
                        </td>
                        <td width="679" class="biaoti_zt_canshusz">
                            能源账单——    ${update==1?'添加':'修改'}
                        </td>
                    </tr>
                </table>
            </div>
            
            <form action="energyBill_insertEnergyBill.action"  method="post" id="myform" theme="simple" name="addBuildingFrom">
                <c:if test="${!empty energyBillList[0].id}">
                    <input type="hidden" name="energyId" value="${energyBillList[0].id}" />
                </c:if>
                <input type="hidden" id="FlagId" value="${update}" />
                <input type="hidden" id="beforeEnergyType" name="beforeEnergyType" value="${energyBillList[0].type}" />
                <input type="hidden" name="beforeUnitPrice" value="${energyBillList[0].unitprice}"/>
                <input type="hidden" name="beforeBillMonth" value="${sessionScope.billMonth}"/>  
                <input type="hidden" name="beforeBillMonth2" value="${energyBillList[0].bill_month}"/>   
                  <input type="hidden" name="typesss" value="${sessionScope.typesss}"/>
                <input type="hidden" id="unitPriceId" name="insertEnergyBill.dbUnitPrice.id" value="${energyBillList[0].unit_price_id}"/>
                <input type="hidden" id="customCode" name="insertEnergyBill.customerCode" value="${energyBillList[0].customer_code}"/>
                <div id="SumPrompt"></div>
                <table width="820"  border="0"  cellspacing="1" class="gai_left_duiqi">
                        <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
                            <td class="admin_bgclor_f1f admin_zt_14">
                                账单类型<span class="admin_clor_f00">*</span>
                            </td>
                            <td class="admin_bgclor_f1f">                                               
                                <select <c:if test="${update==2}"> disabled="disabled"</c:if> style="width:150px" id="enerytype" name="insertEnergyBill.type" onclick="doUpdateMonth(this);"  onchange="call();" runat="server">
                                 <option value="" >--请选择--</option>
                                    <c:forEach items="${energyTypeList2}" var="energyType" varStatus="ind">
                                        <c:if test="${energyBillList[0].type==energyType.seq}">
                                            <option value="${energyType.seq}" selected="selected">${energyType.energy }</option>
                                        </c:if>
                                        <c:if test="${energyBillList[0].type!=energyType.seq}">
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
                            <td class="admin_bgclor_f1f admin_zt_14"    >
                                账单月份<span class="admin_clor_f00">*</span>
                            </td>
                            <td class="admin_bgclor_f1f" id="td_billMonth"> 
                              
                            </td>
                            
                        </tr>
                        
                        <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
                            <td class="admin_bgclor_e3f admin_zt_14">
                                单价<span class="admin_clor_f00" >*</span>
                            </td>
                            <td class="admin_bgclor_f1f">   
                           <%--  <c:if test="${update==2}">  --%>
                            <!-- 修改 -->
                            	<input type="text" id="unitPrice" maxlength="9"  name="insertEnergyBill.dbUnitPrice.unitPrice" value="${energyBillList[0].unitprice}" 
                                onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />(元)
<%--                             </c:if>
                            <c:if test="${update==1}">  --%>
                            <!-- 增加 -->
                           <%--  <input type="text" id="unitPrice" maxlength="9" onchange="submitSum();" name="insertEnergyBill.dbUnitPrice.unitPrice" value="${energyBillList[0].unitprice}" 
                                onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />(元)
                            </c:if> --%>
                            </td>
                            <td class="admin_bgclor_e3f admin_zt_14">
                                总额<span class="admin_clor_f00">*</span>
                            </td>
                            <td class="admin_bgclor_f1f">
                          <input type="text" id="totalSum"  onchange="submitSum();" name="insertEnergyBill.sum" maxlength="11" value="${energyBillList[0].sum}"
                                onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>(元)
                            </td>
                            
                        </tr>
                        <tr>
                            <td class="admin_bgclor_f1f admin_zt_14">
                                使用量<span class="admin_clor_f00">*</span>
                            </td>
                            <td class="admin_bgclor_f1f" >
                                <input type="text" id="consumption" onchange="submitSum();" maxlength="9" name="insertEnergyBill.consumption"  value="${energyBillList[0].consumption}"  onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
                                onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
                                <span id="s2"></span>
                            </td>
                          <td class="admin_bgclor_f1f admin_zt_14">
                                条形码
                            </td>
                            <td class="admin_bgclor_f1f" >
                                <input type="text" id="barCode" maxlength="30" name="insertEnergyBill.barCode" value="${energyBillList[0].bar_code}"/>
                            </td>
                        </tr>
                        <tr>
							<td class="admin_bgclor_f1f admin_zt_14">
                              		 备注
                            </td>
                            <td class="admin_bgclor_f1f"  colspan="2">
                            	<textarea rows="2" cols="68"  name="insertEnergyBill.des" maxlength="50" >${energyBillList[0].des}</textarea>
                            </td>  
                        </tr>
                </table>
                <table width="770" border="0" class="gai_left_duiqi">
                    <tr>
                        <td></td>
                        <td width="60">
                            <c:if test="${update==1}">
                                <a onclick="addBillCheck();" class="btn blue">确认</a>
                            </c:if>
                            <c:if test="${update==2}">
                                <a onclick="updateBillCheck();" class="btn blue">修改</a>
                            </c:if>
                                                                
                        <td  width="65"><a href="energyBill_findEnergyBill.action?currentPage=1&correctMsg=1&bil=${energyBillList[0].bill_month }&bill=${sessionScope.billMonth}&types=${sessionScope.typesss}" class="btn blue">取消</a>                                                              
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
