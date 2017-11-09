<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<title>医院后勤智能化管理平台</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link rel="stylesheet" href="manager/css/neirong.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/skdiv.css" type="text/css"></link>
		<link rel="stylesheet" href="manager/css/ziti.css" type="text/css"></link>
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript">
</script>
		<script src="manager/js/jquery-1.3.2.js" type="text/javascript">
</script>
		<script src="manager/js/My97DatePicker/WdatePicker.js"
			type="text/javascript">
</script>
		<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="manager/js/tableTr.js">
</script>
		<script type="text/javascript"><!--
//判断是否是数字
function isNumber() {
	var istrue = true;
	
		  
	var names = new Array("buildareas", "rent", "innerareas", "outareas",
			"leaseareas", "setupareas", "demolishareas", "buildamount",
			"landareas", "landamount", "plotratio", "afforestation",
			"hireareas", "hire", "rentareas", "medicalareas", "bedspace",
			"carplace_up", "carplace_down");
	var values = new Array("年末实际占有使用房屋建筑面积", "出租租金", "账内建筑面积", "账外建筑面积",
			"租贷建筑面积", "在建建筑面积", "待拆建筑面积", "房屋账面金额", "土地占用面积", "土址账面金额", "容积率",
			"绿地率", "租赁面积", "租赁租金", "出租面积", "医疗用房面积", "现有床位数", "机动车位数---地上",
			"机动车位数---地下");
	var msg = "";
	var today = new Date();
	var year = ((today.getYear() > 200) ? today.getYear() : 1900 + today
			.getYear());
	var builYear = document.getElementById("builYear").value;
	
	//医院总面积
	var sumarea = document.getElementById("sumarea").value - 0;
	
	var buildareas = document.getElementById("buildareas").value - 0;
	var innerareas = document.getElementById("innerareas").value - 0;
	var leaseareas = document.getElementById("leaseareas").value - 0;
	var outareas = document.getElementById("outareas").value - 0;
	var landareas = document.getElementById("landareas").value - 0;
	var hireareas = document.getElementById("hireareas").value - 0;

	for ( var i = 0; i < names.length; i++) {
		var temp = null;
		try {
			temp = document.getElementById(names[i]).value;
		} catch (e) {
			continue;
		}

		if (temp.length > 0 && isNaN(temp)) {
			istrue = false;
			msg += values[i] + ",";
		}
	}
	if (!istrue) {
		msg += "!不是有效的数!";
	}

	if (buildareas < (innerareas + leaseareas + outareas)) {
		istrue = false;
		msg += " [账内房屋建筑面积] 与 [租赁房屋建筑物面积 ]及[帐外房屋建筑物面积（不含租赁）] 不能大于 [年末实际占有使用房屋建筑物面积] ! ";
	}

<%--if(buildareas  < landareas){
			istrue = false;
			msg += "  [年末实际占有使用房屋建筑物面积] 不能小于  [年末土地占用面积数]! ";
		}
		--%>
		if(hireareas < leaseareas){
			istrue = false;
			msg += "租赁[年末面积数] 不能小于 [租赁房屋建筑物面积]! ";
		}
	
		//判断年份是否有效
		if(builYear.length != 4 || builYear> year){
			istrue =false;
			msg += "你输入的[数据时间]无效 !";
		}
		
		if(buildareas<1){
			istrue = false;
			msg +="[年末实际占有使用房屋建筑物面积]不能为0!";
		}
		if(landareas<1){
			istrue = false;
			msg +="土地占用面积[年末面积数]不能为0!";
		}
		
		if(sumarea != (buildareas-0)){			
			var isMax=">";			
			if(buildareas<sumarea){
				isMax = "<" ;
			}			
			if(!confirm("[年末实际占有使用房屋建筑物面积] "+isMax+"  [楼宇总面积 ],[楼宇总面积]为:"+sumarea+" 平方米,是否继续?")){
				istrue = false;
				msg ="";
			}
		}else{
			var isMax="=";	
			if(!confirm("[年末实际占有使用房屋建筑物面积] "+isMax+"  [楼宇总面积 ],[楼宇总面积]为:"+sumarea+" 平方米,是否继续?")){
				istrue = false;
				msg ="";
			}
		}
		var updateNo=$('#updateNo').val();
		if(updateNo!="updateNo"){
			if(dateTime()){
				istrue = false;
				msg +="数据时间不能重复";
			}
		}
		if(istrue){	
			document.getElementById("form1").submit();		
		}else{
			if(msg.length>0){
				alert(msg);
			}
		}
			
	}
	/**
	 * 确定时间唯一性的
	 */
	dateTime=function(){
		   var dateTimes=false;
		   //获取日期
		   var builYear=$('#builYear').val();
		   if(jQuery.trim(builYear)!=''){
			 $.ajax({
				      type: 'get',
					  url: "hospDetailAction_dateTimeById.action",
					  data: 'builYear='+builYear,
					  async:false, 
					  cache:false,
					  dataType: 'json',
					  success: function(resultJSON){
					     var objdt=eval(resultJSON);
					     var result=objdt[0].res;
					     if(result=='yes'){
					    	 dateTimes =  true;
					     }else{
					    	 dateTimes = false;
					     }
					  },
					  error:function(resultJSON){
					      alert('error');
					  }
				    });
		        }
		   return dateTimes;
	}
	
	
	onload=function(){
		try {
			
		} catch (e) {
			// TODO: handle exception
		}
	}
	
	function inputSomething(obj){
	var value = obj.value;
	if(value.length == 3){
		if(value.length == 2){
			if(value != "1." && value != "0."){
				obj.value="";
				return;
			}
	}else if(value.length > 2){
		var sVal = value.substring(2,value.length);
		var reg = /^\d{1,2}$/;
		if(!reg.test(sVal)){
			obj.value="";
			return;
			}
		}
	}
}

function verifyData(obj){
		var value = obj.value;
		if(value != ""&&value.length==2){
			obj.value="";
			obj.focus();
			return;
		
	}
}
/**
限制文本框 只能输入
整型与double类型
*/
function clearNoNum(obj) { 
//先把非数字的都替换掉，除了数字和. 
obj.value = obj.value.replace(/[^\d.]/g,""); 
//必须保证第一个为数字而不是. 
obj.value = obj.value.replace(/^\./g,""); 
//保证只有出现一个.而没有多个. 
obj.value = obj.value.replace(/\.{2,}/g,"."); 
//保证.只出现一次，而不能出现两次以上 
obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
//obj.value=obj.value.replace(/^[0-9]*[1-9][0-9]*$/,"");
} 
function bedspaceYZ(){
	var bedspace = document.getElementById("bedspace").value ;
    var zhengze=/^[1-9]([0-9])*$/;
    var other=zhengze.test(bedspace);
    if(other==false){
    alert("输入错误");
    document.getElementById("bedspace").value="";
    }
}
function bedcheckYZ(){
	var bedcheck = document.getElementById("bedcheck").value ;
    var zhengze=/^[1-9]([0-9])*$/;
    var other=zhengze.test(bedcheck);
    if(other==false){
    alert("输入错误");
    document.getElementById("bedcheck").value="";
    }
}
	
</script>

	</head>
	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<div id="admin_nr_rightg">

			<div class="canshusz_btbg_1">
				<table class="titleBg">
					<tr style="height: 20px">
						<td width="30" align="center">
							<img src="manager/images/common/28-01.png" />
						</td>
						<td>
							楼宇基建 ——历史数据 ——
							<c:if test="${!empty detail}">修改</c:if>
							<c:if test="${empty detail}">添加</c:if>
						</td>
					</tr>
				</table>
			</div>
			<form name="form1" id="form1" method="post" action="${action }">
			    <input type="hidden" id="updateNo" name="updateNo" value="${updateNo }" />
				<input type="hidden" name="page" value="${page }" />
				<input type="hidden" name="startDate" value="${startDate }" />
				<input type="hidden" name="editFlag" value="${editFlag }" />
				<input type="hidden" name="hospid" value="${hospid }" />
				<input type="hidden" name="seqHosps" value="${seqHosps}" />
				<input type="hidden" name="hospIdSeq" id="hospIdSeq"
					value="${hospIdSeq}" />

				<input type="hidden" name="detail.seq" value="${detail.seq}" />
				<c:if test="${!(empty hospId)}">
					<input type="hidden" name="detail.dbHospInfo.seqHosp"
						value="${hospId }" />
				</c:if>
				<c:if test="${empty hospId}">
					<input type="hidden" name="detail.dbHospInfo.seqHosp"
						value="${detail.dbHospInfo.seqHosp }" />
				</c:if>

				<input type="hidden" name="sumarea" id="sumarea"
					value="${hospArea.hosparea }" />
				<center>
					<table width="770" border="0" cellspacing="1"
						class="gai_left_duiqi">
						<tr class="admin_bgclor_8ff">
							<th colspan="4" align="left" class="admin_dh_zt3">
								一、房屋情况
							</th>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								年末实际占有使用房屋建筑物面积
								<font size="" color="#FF0000">*</font>
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.buildareas" type="text" id="buildareas"
									value="<f:formatNumber value="${(empty detail.buildareas)?0:(detail.buildareas)  }" pattern="#00.0#"/>"
									class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)"/>
								平方米
							</td>
							<td align="left" class="admin_bgclor_e3f">
								账内房屋建筑面积
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.innerareas" type="text" id="innerareas"
									value="<f:formatNumber value="${detail.innerareas }" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								平方米
							</td>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								帐外房屋建筑物面积（不含租赁）
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.outareas" type="text" id="outareas"
									value="<f:formatNumber value="${detail.outareas}" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								平方米
							</td>
							<td align="left" class="admin_bgclor_e3f">
								租赁房屋建筑物面积
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.leaseareas" type="text" id="leaseareas"
									value="<f:formatNumber value="${detail.leaseareas }" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								平方米
							</td>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								年末房屋账面金额数
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.buildamount" type="text" id="buildamount"
									value="<f:formatNumber value="${detail.buildamount }" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								元
							</td>
							<td align="left" class="admin_bgclor_e3f">
								在建建筑面积
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.setupareas" type="text" id="setupareas"
									value="<f:formatNumber value="${detail.setupareas}" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								平方米
							</td>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								待拆建筑面积
							</td>
							<td colspan="3" align="left" class="admin_bgclor_f1f">
								<input name="detail.demolishareas" type="text"
									id="demolishareas" value="<f:formatNumber value="${detail.demolishareas}" pattern="#00.0#"/>"
									class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								平方米
							</td>
						</tr>
						<tr class="admin_bgclor_8ff">
							<th colspan="4" align="left" class="admin_dh_zt3">
								二、土地占用面积情况
							</th>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								年末土地占用面积数
								<font size="" color="#FF0000">*</font>
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.landareas" type="text" id="landareas"
									value="<f:formatNumber value="${(empty detail.landareas)?0:(detail.landareas)}" pattern="#00.0#"/>"
									class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								平方米
							</td>
							<td align="left" class="admin_bgclor_e3f">
								年末土地占用帐面金额数
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.landamount" type="text" id="landamount"
									value="<f:formatNumber value="${detail.landamount }" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								元
							</td>
						</tr>
						<tr class="admin_bgclor_8ff">
							<th colspan="4" align="left" class="admin_dh_zt3">
								三、租赁情况
							</th>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								年末租赁面积数
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.hireareas" type="text" id="hireareas"
									value="<f:formatNumber value="${detail.hireareas }" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								平方米
							</td>
							<td align="left" class="admin_bgclor_e3f">
								租金
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.hire" type="text" id="hire"
									value="<f:formatNumber value="${detail.hire }" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								元/年
							</td>
						</tr>
						<tr class="admin_bgclor_8ff">
							<th colspan="4" align="left" class="admin_dh_zt3">
								四、出租情况
							</th>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								年末出租面积数
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.rentareas" type="text" id="rentareas"
									value="<f:formatNumber value="${detail.rentareas }" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								平方米
							</td>
							<td align="left" class="admin_bgclor_e3f">
								收入
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.rent" type="text" id="rent"
									value="<f:formatNumber value="${detail.rent }" pattern="#00.0#"/>" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								元/年
							</td>
						</tr>
						<tr class="admin_bgclor_8ff">
							<td colspan="4" align="left" class="admin_dh_zt3">
								五、管理情况
							</td>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								总院负责人
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.principal" type="text" id="principal1"
									maxlength="10" value="${detail.principal }"
									class="yiyuanjbxx_inp_nr3" />
							</td>
							<td align="left" class="admin_bgclor_e3f">
								总院土地管理局
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.landdept" type="text" id="landdept1"
									maxlength="50" value="${detail.landdept }"
									class="yiyuanjbxx_inp_nr3" />
							</td>
						</tr>
						<tr class="admin_bgclor_8ff">
							<th colspan="4" align="left" class="admin_dh_zt3">
								六、其它信息
							</th>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								数据时间
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<c:if test="${empty nowDate}">
								<f:formatDate value='${detail.inputtime }' pattern='yyyy' />
								<input type="hidden" value="<f:formatDate value='${detail.inputtime }' pattern='yyyy' />" id="builYear" name="builYear"/>
								<!-- <input type="text" id="builYear" name="builYear" size="13"
										maxlength="6" class="Wdate" readonly="readonly"
										onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"
										value="<f:formatDate value='${detail.inputtime }' pattern='yyyy' />" /> -->	
								</c:if>
								<c:if test="${!(empty nowDate)}">
									<input type="text" id="builYear" name="builYear" size="13"
										maxlength="6" class="Wdate" readonly="readonly"
										onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"
										value="<f:formatDate value='${nowDate }' pattern='yyyy' />" />
								</c:if>
								年
							</td>
							<td align="left" class="admin_bgclor_e3f">
								医疗用房面积
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.medicalareas" type="text" id="medicalareas"
									value="${detail.medicalareas }" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" />
								平方米
							</td>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								容积率
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.plotratio" type="text" id="plotratio"
									onkeyup="clearNoNum(this);" maxlength="4"
									value="<c:if test="${!empty detail.plotratio }"><f:formatNumber value="${detail.plotratio * 100 }" pattern="#.##" type="number"/></c:if>"
									<%--								onBlur="verifyData(this)" onpropertychange="inputSomething(this)" onpaste="return false"--%>
									 class="yiyuanjbxx_inp_nr3" />

								%
							</td>

							<td align="left" class="admin_bgclor_e3f">
								绿地率
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.afforestation" type="text"
									id="afforestation"
									value="<c:if test="${!empty detail.afforestation }"><f:formatNumber value="${detail.afforestation * 100 }" pattern="#.##" type="number"/></c:if>"
									onkeyup="clearNoNum(this);" maxlength="4"
									<%--									onBlur="verifyData(this)" onpropertychange="inputSomething(this)" onpaste="return false"--%>
									class="yiyuanjbxx_inp_nr3" />
								%
							</td>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								现有床位数
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.bedspace" type="text" id="bedspace"
									value="${detail.bedspace }" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this);bedspaceYZ();" maxlength="5"/>
							</td>
							<td align="left" class="admin_bgclor_e3f">
								核定床位数
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.bedcheck" type="text" id="bedcheck"
									onkeyup="clearNoNum(this);bedcheckYZ();"
									value="${detail.bedcheck}" class="yiyuanjbxx_inp_nr3"
									maxlength="4" />
							</td>
						</tr>
						<tr>
							<td align="left" class="admin_bgclor_e3f">
								机动车位数——地上
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.carplaceUp" type="text" id="carplaceUp"
									value="${detail.carplaceUp }" class="yiyuanjbxx_inp_nr3"
									onkeyup="clearNoNum(this)" maxlength="5" />
							</td>

							<td align="left" class="admin_bgclor_e3f">
								机动车位数——地下
							</td>
							<td align="left" class="admin_bgclor_f1f">
								<input name="detail.carplaceDown" type="text" id="carplaceDown"
									onkeyup="clearNoNum(this)"
									value="${detail.carplaceDown}" class="yiyuanjbxx_inp_nr3"  maxlength="5"/>
							</td>
						</tr>
					</table>
					<table width="770" border="0" class="gai_left_duiqi">
						<tr class="louyujj_xiaxian_hui">
							<td></td>
							<td width="65">
								<a href="javascript:void(0);" onclick="isNumber();"
									class="btn blue">保 存</a>
							</td>
							<td width="65">
								<a href="javascript:void(0);" onclick="history.back();"
									class="btn blue">取 消</a>
							</td>
						</tr>
					</table>
				</center>
			</form>
		</div>
	</body>
</html>

