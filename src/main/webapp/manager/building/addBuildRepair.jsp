<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>My JSP 'addBuildRepair.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
<script src="manager/js/annu.js" type="text/javascript"></script>
<script src="manager/js/reqi.js" type="text/javascript"></script> 
<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="manager/js/zxq.js"></script>

<script language="javascript" type="text/javascript" src="manager/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

   f_tijiao=function(){
     
     if(jQuery.trim(jQuery("#TIME").val())==""||jQuery.trim(jQuery("#TIME").val())==null)
     {
        alert("请选择大修年月！");
        jQuery("#TIME").focus();
        return;
     }
     if(isNaN(jQuery("#TJ").val())||isNaN(jQuery("#AZ").val())||isNaN(jQuery("#TJAZ").val())){
        alert("【土建】或【安装】或【土建安装】不是数字，请重新输入！");
        return;
     }
     document.myform.submit();
   }
  
  f_blur=function(a)
  {
    if(jQuery(a).val()==""){
      jQuery(a).val("0.0");
    }
   f_JS();
  }
  
  f_JS=function()
  {
    //土建
    var sum1=parseFloat(jQuery("#ZS").val())+parseFloat(jQuery("#JG").val())+parseFloat(jQuery("#CC").val())+parseFloat(jQuery("#TJQT").val());
    jQuery("#TJ").val(sum1);
  
     //安装
    var sum2=parseFloat(jQuery("#RD").val())+parseFloat(jQuery("#PS").val())+parseFloat(jQuery("#DT").val())+parseFloat(jQuery("#KT").val())+parseFloat(jQuery("#GL").val())+parseFloat(jQuery("#YYQT").val())+parseFloat(jQuery("#AZQT").val());
    jQuery("#AZ").val(sum2);

   //土建总价
   var sum3=parseFloat(jQuery("#TJ").val())+parseFloat(jQuery("#AZ").val())+parseFloat(jQuery("#QT").val());
   jQuery("#TJAZ").val(sum3);

  }
  
  onload=function()
 {

    f_JS(); 
    
    <c:if test="${ADDorEDIT==2}">
    //外墙材料
     var str="${REPAIR.outWall}";
     var strs=new Array();
     strs=str.split(",");
     var my=document.getElementsByName("dbBuildingRepair.outWall");
     for ( var j = 0; j < strs.length; j++) {
         for ( var i = 0; i < my.length; i++) {
        	if(parseInt(strs[j])==jQuery("#wq"+i).val()){
    		    jQuery("#wq"+i).attr("checked",true);
			}
		}
	}
   </c:if>
   
  <c:if test="${ADDorEDIT==1}">
    var str="${BUILD.outWall}";
     var strs=new Array();
     strs=str.split(",");
     var my=document.getElementsByName("dbBuildingRepair.outWall");
     for ( var j = 0; j < strs.length; j++) {
         for ( var i = 0; i < my.length; i++) {
        	if(parseInt(strs[j])==jQuery("#wq"+i).val()){
    		    jQuery("#wq"+i).attr("checked",true);
			}
		}
	}
  </c:if>
  }
  function OnEnter( field ) {
		 if( field.value == field.defaultValue ) { 
			 field.value = ""; 
	    }
    }
	function OnExit( field ) { 
		if( field.value == "" ) { 
			field.value = field.defaultValue; 
		} 
	}
</script>

</head>

<body style="overflow:scroll;overflow-x:hidden;overflow-y:hidden;">
<!--<div id="fenzhan_right">-->
  <div id="admin_nr_jbxx">
           <div class="canshusz_btbg_1">
				<table width="100%" border="0">
					<tr style="height: 30px;">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							   楼宇基建——楼宇信息——${BUILD.buildingName }大修情况记录——<c:if test="${ADDorEDIT==1}">添加</c:if><c:if test="${ADDorEDIT==2}">修改</c:if>
						</td>
					</tr>
				</table>
			</div>
  <form action="buildRepair!showAddBuildRepair.action?hospinfopid=${hospinfopid}&editFlag=${editFlag}&showOrhide=dx&hideOrshow=${hideOrshow}&tag=${tag}&tabIndex=4" name="myform" method="post">
    <table width="780" border="0" cellspacing="1" align="left">
      <tr >
        <td  width="280" class="admin_bgclor_e3f">楼宇编号</td>
        <td  width="290" class="admin_bgclor_f1f"><input name="buildingCode_" type="text" readonly="readonly" value="${BUILD.buildingId }" maxlength="24"/></td>
        <td  width="150" class="admin_bgclor_e3f">大修年月&nbsp;<span class="louyujj_biaozongji_zt1">*</span></td>
        <td  width="290" class="admin_bgclor_f1f"><input class="Wdate" readonly="readonly" name="dbBuildingRepair.repairttime" id="TIME" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d',dateFmt:'yyyyMM'})"   value="${REPAIR.repairttime}"  /></td> 
      </tr>
      <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
        <td  width="150" class="admin_bgclor_e3f">屋面防水等级&nbsp;<span class="louyujj_biaozongji_zt1">*</span></td>
        <td  width="290" class="admin_bgclor_f1f">
        <select name="dbBuildingRepair.dbBuildMaterByWaterProof.materId"  class="yiyuanjbxx_inp_nr4" >
                      <c:forEach items="${listMater_FS}" var="fs">
                                 <c:if test="${ADDorEDIT==1}">
									<option value="${fs.masterid }" ${BUILD.dbBuildMaterByWaterproof.materId==fs.masterid?'selected':'' } >
										${fs.mastername }
									</option>
								 </c:if>
								 <c:if test="${ADDorEDIT==2}">
									<option value="${fs.masterid }" ${REPAIR.dbBuildMaterByWaterProof.materId==fs.masterid?'selected':'' } >
										${fs.mastername }
									</option>
								 </c:if>
								</c:forEach>
							</select>      </td>
        <td  width="150" class="admin_bgclor_e3f">抗震烈度&nbsp;<span class="louyujj_biaozongji_zt1">*</span></td>
        <td width="290" class="admin_bgclor_f1f">
        <select name="dbBuildingRepair.dbBuildMaterByQuakeProof.materId" class="yiyuanjbxx_inp_nr4">
								<c:forEach items="${listMater_KZ}" var="kz">
								    <c:if test="${ADDorEDIT==1}">
									<option value="${kz.masterid }"  ${BUILD.dbBuildMaterByQuakeproof.materId==kz.masterid?'selected':'' }>
										${kz.mastername }
									</option>
								    </c:if>
								 <c:if test="${ADDorEDIT==2}">
									<option value="${kz.masterid }"  ${REPAIR.dbBuildMaterByQuakeProof.materId==kz.masterid?'selected':'' }>
										${kz.mastername }
									</option>
								 </c:if>
								</c:forEach>
							</select>        </td>
      </tr> 
      <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
         <td  width="150" class="admin_bgclor_e3f">造价依据&nbsp;<span class="louyujj_biaozongji_zt1">*</span></td>
        <td  width="290" class="admin_bgclor_f1f">
        <select name="dbBuildingRepair.dbBuildMaterByCostAccord.materId" class="yiyuanjbxx_inp_nr4">
								<c:forEach items="${listMater_ZJ}" var="zj">
								<c:if test="${ADDorEDIT==1}">
									<option value="${zj.masterid }"  ${BUILD.dbBuildMaterByCostaccord.materId==zj.masterid?'selected':'' }>
										${zj.mastername }
									</option>
								  </c:if>
								  <c:if test="${ADDorEDIT==2}">
									<option value="${zj.masterid }"  ${REPAIR.dbBuildMaterByCostAccord.materId==zj.masterid?'selected':'' }>
										${zj.mastername }
									</option>
							    </c:if>
								</c:forEach>
							</select></td>
        <td  width="150" class="admin_bgclor_e3f">改造前问题&nbsp;<span class="louyujj_biaozongji_zt1">*</span></td>
        <td width="290" class="admin_bgclor_f1f">
       <select name="dbBuildingRepair.dbBuildMaterByProblem.materId" class="yiyuanjbxx_inp_nr4">
								<c:forEach items="${listMater_GZ}" var="gz">
								<c:if test="${ADDorEDIT==1}">
									<option value="${gz.masterid }" ${BUILD.dbBuildMaterByProblem.materId==gz.masterid?'selected':'' }>
										${gz.mastername }
									</option>
								  </c:if>
								  <c:if test="${ADDorEDIT==2}">
									<option value="${gz.masterid }" ${REPAIR.dbBuildMaterByProblem.materId==gz.masterid?'selected':'' }>
										${gz.mastername }
									</option>
									</c:if>
								</c:forEach>
							</select>        </td>
      </tr>  
      <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
							<td width="150" class="admin_bgclor_e3f">外墙材料</td>
        <td width="290" class="admin_bgclor_f1f"  colspan="3">
								<c:forEach items="${listMater_WQ}" var="wq" varStatus="v">
									<input type="checkbox" name="dbBuildingRepair.outWall"
									value="${wq.masterid }"  id="wq${v.index }"/>${wq.mastername }
								</c:forEach></td>
      </tr>  
      <tr>
        <td width="150" class="admin_bgclor_e3f">土建安装总造价</td>
        <td  width="290" class="admin_bgclor_f1f"  colspan="3">
        	<input name="dbBuildingRepair.totalcost" id="TJAZ" type="text" readonly="readonly" value=""/>&nbsp;元
        </td>
        
      </tr>       
      <tr>
        <td colspan="4" class="admin_bgclor_e3f">
        
        <table width="780" border="0" cellspacing="1">
          <tr>
            <td width="150" class="admin_bgclor_e3f">土建</td>
            <td width="22%"  class="admin_bgclor_f1f"><input name="dbBuildingRepair.tujianCost" id="TJ"  type="text" value="" readonly="readonly"  onkeyup="f_JS()" size="15"/>&nbsp;元</td>
            <td width="150" class="admin_bgclor_e3f">安装</td>
            <td width="23%"  class="admin_bgclor_f1f">
            	<input name="dbBuildingRepair.installCost" id="AZ" type="text" value="" readonly="readonly" onkeyup="f_JS()"  size="15"/>&nbsp;元
            </td>
            <td width="150" class="admin_bgclor_e3f">其它</td>
            <td width="20%"  class="admin_bgclor_f1f"><input name="dbBuildingRepair.anothersCost" id="QT" type="text" <c:if test="${REPAIR.anothersCost!=null}"> value="${REPAIR.anothersCost}"</c:if> <c:if test="${REPAIR.anothersCost==null}">value="0"</c:if>
               onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')" onchange="f_JS()" maxlength="15" 
                onfocus="OnEnter(this)" onblur="OnExit(this)"   size="15"/>&nbsp;元</td>
          </tr>
          <tr class="admin_bgclor_fff">
            <td colspan="2" align="left" class="admin_bgclor_fff" valign="top">
            <table width="100%" border="0" cellspacing="1">
              <tr>
                <td width="150" class="admin_bgclor_e3f">装饰</td><!-- dbBuildingRepair.adorn -->
                <td width="66%"><input name="dbBuildingRepair.adornCost" id="ZS" type="text" <c:if test="${REPAIR.anothersCost!=null}"> value="${REPAIR.adornCost}"</c:if> <c:if test="${REPAIR.adornCost==null}">value="0"</c:if>  maxlength="15" size="15" onblur="f_blur(this)" onfocus="OnEnter(this)" onblur="OnExit(this)"   onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"  />&nbsp;元</td>
              </tr>
              <tr>
                <td width="150" class="admin_bgclor_e3f">加固 </td>
                <td> <input name="dbBuildingRepair.pinupCost" id="JG" type="text" <c:if test="${REPAIR.pinupCost!=null}"> value="${REPAIR.pinupCost }"</c:if> <c:if test="${REPAIR.pinupCost==null}">value="0"</c:if> maxlength="15" size="15" onblur="f_blur(this)" onfocus="OnEnter(this)" onblur="OnExit(this)"    onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"  />&nbsp;元</td>
              </tr>
              <tr>
                <td width="150" class="admin_bgclor_e3f">拆除</td>
                <td ><input name="dbBuildingRepair.removeCost" id="CC" type="text" <c:if test="${REPAIR.removeCost!=null}">value="${REPAIR.removeCost }"</c:if> <c:if test="${REPAIR.removeCost==null}">value="0"</c:if> maxlength="15" size="15" onblur="f_blur(this)" onfocus="OnEnter(this)" onblur="OnExit(this)"    onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"  />&nbsp;元</td>
              </tr>
              <tr>
                <td width="150" class="admin_bgclor_e3f">土建其他</td>
                <td>
                	<input  name="dbBuildingRepair.tujianAnCost" id="TJQT" type="text" 
                	<c:if test="${REPAIR.tujianAnCost!=null}">value="${REPAIR.tujianAnCost }"</c:if> 
                	<c:if test="${REPAIR.tujianAnCost==null}">value="0"</c:if> 
                	maxlength="15" size="15" 
                	onfocus="OnEnter(this)" 
                	onblur="OnExit(this)"   
                	onchange="f_JS()"   
                	onkeyup="if(isNaN(value))execCommand('undo')" 
                   	onafterpaste="if(isNaN(value))execCommand('undo')"  />&nbsp;元</td>
              </tr>
            </table>
            </td>
            <td colspan="2" align="left" class="admin_bgclor_fff">
            <table width="100%" border="0"  cellspacing="1">
              <tr>
                <td width="100" class="admin_bgclor_e3f">弱电</td>
                <td width="66%"><input name="dbBuildingRepair.ruodianCost" id="RD" type="text" onfocus="OnEnter(this)" onblur="OnExit(this)"   maxlength="15"  <c:if test="${REPAIR.ruodianCost!=null}"> value="${REPAIR.ruodianCost }"</c:if> <c:if test="${REPAIR.ruodianCost==null}">value="0"</c:if> size="15"  onchange="f_JS()"  onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"  />&nbsp;元</td>
              </tr>
             
              <tr>
                <td width="100" class="admin_bgclor_e3f">给排水</td>                                                                                                                                                                                                                                                      
                <td><input  name="dbBuildingRepair.jipaishuiCost" id="PS" type="text" <c:if test="${REPAIR.jipaishuiCost!=null}"> value="${REPAIR.jipaishuiCost }"</c:if> <c:if test="${REPAIR.jipaishuiCost==null}">value="0"</c:if> onfocus="OnEnter(this)" onblur="OnExit(this)"  maxlength="15"  onchange="f_JS()"   onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"   size="15"/>&nbsp;元</td>
              </tr>
              <tr>
                <td width="100" class="admin_bgclor_e3f">电梯</td>
                <td><input name="dbBuildingRepair.liftCost" id="DT" type="text" <c:if test="${REPAIR.liftCost!=null}"> value="${REPAIR.liftCost }"</c:if> <c:if test="${REPAIR.liftCost==null}">value="0"</c:if> onfocus="OnEnter(this)" onblur="OnExit(this)"   maxlength="15"  onchange="f_JS()"  onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"    size="15"/>&nbsp;元</td>
              </tr>
              <tr>
                <td width="100" class="admin_bgclor_e3f">空调通风</td>
                <td><input  name="dbBuildingRepair.kongtiaoCost" id="KT" type="text" <c:if test="${REPAIR.kongtiaoCost!=null}">value="${REPAIR.kongtiaoCost }"</c:if> <c:if test="${REPAIR.kongtiaoCost==null}">value="0"</c:if> maxlength="15" onfocus="OnEnter(this)" onblur="OnExit(this)"    onchange="f_JS()"  onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"    size="15"/>&nbsp;元</td>
              </tr>
              <tr>
                <td width="100" class="admin_bgclor_e3f">锅炉房</td>
                <td ><input  name="dbBuildingRepair.guoluCost" id="GL" type="text" <c:if test="${REPAIR.guoluCost!=null}"> value="${REPAIR.guoluCost }"</c:if> <c:if test="${REPAIR.guoluCost==null}">value="0"</c:if> maxlength="15" onfocus="OnEnter(this)" onblur="OnExit(this)"   onchange="f_JS()"   onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"    size="15"/>&nbsp;元</td>
              </tr>
              <tr>
                <td width="100" class="admin_bgclor_e3f">医用气体系统</td>
                <td><input name="dbBuildingRepair.medicalgasCost" id="YYQT" type="text" <c:if test="${REPAIR.medicalgasCost!=null}">value="${REPAIR.medicalgasCost }"</c:if> <c:if test="${REPAIR.medicalgasCost==null}">value="0"</c:if> maxlength="15" onfocus="OnEnter(this)" onblur="OnExit(this)"   onchange="f_JS()"   onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"    size="15"/>&nbsp;元</td>
              </tr>
              <tr>
                <td width="100" class="admin_bgclor_e3f">安装其他</td>
                <td><input name="dbBuildingRepair.installAnCost" id="AZQT" type="text" <c:if test="${REPAIR.installAnCost!=null}">value="${REPAIR.installAnCost }"</c:if> <c:if test="${REPAIR.installAnCost==null}">value="0"</c:if> maxlength="15" onfocus="OnEnter(this)" onblur="OnExit(this)"   onchange="f_JS()"   onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"    size="15"/>&nbsp;元</td>
              </tr>
            </table>
             </td>
            <td colspan="2" align="center" class="admin_bgclor_fff">
             </td>
            </tr>
        </table>        
        </td>
      </tr>               
      <tr>
        <td width="150" class="admin_bgclor_e3f">审计价格</td>
        <td width="290" class="admin_bgclor_f1f"><input name="dbBuildingRepair.auditPrices" id="SJJG" type="text" maxlength="15"  onfocus="OnEnter(this)" onblur="OnExit(this)"   onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"   <c:if test="${REPAIR.anothersCost!=null}"> value="${REPAIR.auditPrices}"</c:if> <c:if test="${REPAIR.auditPrices==null}">value="0"</c:if> />&nbsp;元</td>
        <td width="150" class="admin_bgclor_e3f">结算价格</td>
        <td width="290" class="admin_bgclor_f1f"><input name="dbBuildingRepair.upPrices" type="text" id="JSJG" maxlength="15" onfocus="OnEnter(this)" onblur="OnExit(this)"    onkeyup="if(isNaN(value))execCommand('undo')"  onafterpaste="if(isNaN(value))execCommand('undo')"   <c:if test="${REPAIR.anothersCost!=null}"> value="${REPAIR.upPrices }"</c:if> <c:if test="${REPAIR.upPrices==null}">value="0"</c:if> />&nbsp;元</td>
      </tr>                                             
      <tr>
        <td width="100" class="admin_bgclor_e3f">备注</td>
        <td colspan="3" class="admin_clor_f00"><textarea name="dbBuildingRepair.remark" class="yiyuanjbxx_inp_nr6" onkeydown= "if(this.value.length> 500)return false;">${REPAIR.remark }</textarea>
        </td>
      </tr>                                                
    </table>
        <input type="hidden" value="${repid}" name="repid"/>
        <input type="hidden" value="${buildingId}" name="buildingId"/>
        <input type="hidden" value="${HOSID}" name="dbBuildingRepair.dbHospitalinfo"/>
      <!--   <input type="hidden" name="buildingId" value="${BUILD.buildingId }"/>-->
        <input type="hidden" name="editFlag" value="${editFlag}" id="editFlag" />
        <input type="hidden" name="showOrhide" value="${showOrhide}" id="showOrhide" />
    </form>
    <div style=" border:0px solid white; position: absolute; margin-top: 550px; margin-left: 630px;">
	    <table width="100%" border="0">
					<tr style="height: 30;" valign="bottom">
						<td></td>
						<td></td>
						<td width="70">
							<a href="javascript:f_tijiao()" class="btn blue" >保存</a>
						</td>
						<td width="70" >
						 <a href="javascript:history.go(-1)"  class="btn blue" >返回</a>
						</td>
						<td width="200">
						  &nbsp;
						</td>
					</tr>
		</table>
    </div>
  </div>
</body>
</html>
