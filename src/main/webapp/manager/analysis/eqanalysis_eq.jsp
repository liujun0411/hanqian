<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 



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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>医院后勤智能化管理平台</title>
	<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
	<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
	<script src="manager/js/annu.js" type="text/javascript"></script>
	<script src="manager/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>	
	<script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
	<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
	

	
	<script language="JavaScript">
	<!--
		
		
		//更新时间隔
		changeStep=function(value){
			var spans=document.getElementById("sDate1");
			var spane=document.getElementById("sDate2");
			
			if(value=="year"){
				spans.onfocus=function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy'});  };
				spane.onfocus=function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy'});  };
				spans.value="";
				spane.value="";
			}else{
				spans.onfocus=function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'});  };
				spane.onfocus=function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'});  };
				spans.value="";
				spane.value="";
			}
		}

		
		
		
		//初始化
		function initOnload(power){
			if("${isfail}" != ""){
				alert("请重新登陆！");
				window.top.location="user!closeUsers.action";
			}

			//同步二级菜单
			var stypid="${voeqa.styid}";
			if("" != stypid){
				getMyChild("${voeqa.ftyid}",stypid);
				getCo(stypid);
			}
			else{
				getCo("${voeqa.ftyid}");
			}
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

		setHospids=function(){
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
							$("#hospid").attr("value",hospid);	
						}

						
					}
				}	
			}catch(e){}

			return true;
			
		}

		//提交表单
		function submitform(){			
			var myform=$("#myform");
			
			//获得查询条件
			$("#startDate").val($("#sDate1").val());
			$("#endDate").val($("#sDate2").val());
			$("#timeStep").val($("#sstpe").val());
			if(!setHospids()){
				return ;
			}		
			
			
			
			//时间有效性
			var sDate1=$("#sDate1").val();
			var sDate2=$("#sDate2").val();
			if (sDate1.length >0 && sDate2.length>0) {
				if(sDate1>sDate2){
					alert("起始时间不能大于结束时间");
					return false;
				}
			}
			var eqtype= $("#eqTypeP").val();
			var subeqtype=$("#subsel").val();
			var eqIds = $("#eqIds").val();
			if(null ==subeqtype){
				subeqtype="";
			}
			
			$("#ftyid").val(eqtype);
			$("#styid").val(subeqtype);
			$("#eqcodes").val(eqIds);

			if(eqIds.length<1){
				alert("请选择设备!");
				return ;
			}

			
			myform.submit();			
		}
		
	//-->
	</script>
	
	
	<script language="JavaScript">
	<!--
		onover=function(obj){
			if("#3399ff" !=obj.style.color && "#3399FF" !=obj.style.color){
				obj.style.color="#FF6600";
			}
		}
		oncli=function(obj){
			if(document.getElementById(obj.htmlFor).checked){
				obj.style.color="#3399FF";
			}else{
				obj.style.color="#000000";
			}
		}
		onout=function(obj){
			if("#3399ff" !=obj.style.color && "#3399FF" !=obj.style.color){
				obj.style.color="#000000";
			}
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
	</head>

<body onload="initOnload('${voeqa.power }');" style="overflow:scroll;overflow-x:hidden;overflow-y:hidden;">

		<!-- form 表单 -->
		<form method="post" action="eqanalyssis_showEqAnalysisUI.action" name="myform"	id="myform">
			<input type="hidden" id="power"     name="voeqa.power"			/>   	<!-- 能源类型 -->
			<input type="hidden" id="startDate" name="voeqa.startDate"		/> 		<!-- 开始时间 -->
			<input type="hidden" id="endDate"   name="voeqa.endDate"		/>		<!-- 结束时间 -->
			<input type="hidden" id="timeStep"  name="voeqa.timeStep"		/>		<!-- 时间间隔 -->
			<input type="hidden" id="hospid" name="voeqa.hospid" /><!-- 医院Id -->
			<input  type="hidden" id="ftyid"  name="voeqa.ftyid"/> <!-- 首类型 -->
			<input  type="hidden" id="styid"  name="voeqa.styid"/> <!-- 子类型 -->
			<input  type="hidden" id="eqcodes"  name="voeqa.eqcodes"/><!-- 设备列表 -->
			<input  type="hidden" id="coname"  name="voeqa.coname" /><!-- 厂商 -->			
		</form>




	<div id="admin_nr_rightg" >
		<!-- 标题 -->
		<div class="canshusz_btbg_1" >
			<table width="780" border="0">
				<tr style="height:30px">
					<td width="34" align="center">
						<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
					</td>
					<td width="679" class="biaoti_zt_canshusz">
						设备能效分析——同类各设备能耗
					</td>
				</tr>
			</table>
		</div>	
		
		<!-- 查询条件 -->
    	<div style="width: 780px;float: left;">		
		<!-- 能源选择 -->
		<iframe src="manager/analysis/an_head.jsp?power=${voeqa.power }"
				scrolling="no" width="100%" height="60" marginwidth="0"
				marginheight="0" frameborder="0" id="powerfrm"></iframe>
		<div >
	      <table width="100%" border="0" align="right" class="louyujj_xiaxian_hui gai_left_duiqi" style="background-color: #CEEFFF;">
	       <tr class="shebeigl_inp_zt">
	       		<td width="300">
				设备类型:
	         	<select id="eqTypeP" name="eqTypeP" onchange="getMyChild(this.value,'');">	         	   
	         		<option value="">--请选择--</option>
	         		<c:if test="${!(empty selobj.typelist)}">
	         		<c:forEach items="${selobj.typelist }" var="obj">
		         		<c:if test="${empty obj.dbEquipType.equipTypeId}">	         		
		         		<c:if test="${voeqa.ftyid==obj.equipTypeId }">
		         			<option value="${obj.equipTypeId }" selected="selected">${obj.typeName }</option>
		         		</c:if>         		
		         		<c:if test="${voeqa.ftyid!=obj.equipTypeId }">
		         			<option value="${obj.equipTypeId }" >${obj.typeName }</option>
		         		</c:if> 
		         		</c:if>
	         		</c:forEach>
	         		</c:if>
	         	</select>
	         	<span id="eqTypeChilds"></span>
	         	&nbsp;
	       		</td>
	       		<td class="biao_lianj_1"><a href="javascript:void(0);" onclick="$('#xtype').show();">设备列表</a></td>      
	       </tr>
	       
	       	       
	       <tr   class="shebeigl_inp_zt">       	
	         <td >起止年月:
	         	<span><input name="sDate1" type="text" id="sDate1"  value="${voeqa.startDate }" size="8" maxlength="6"  class="Wdate"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'${'year'== voeqa.timeStep?'yyyy':'yyyyMM' }'})" /></span>至 
	         	<span><input name="sDate2" type="text" id="sDate2"  value="${voeqa.endDate }" size="8" maxlength="6"  class="Wdate"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'${'year'== voeqa.timeStep?'yyyy':'yyyyMM' }'})" /></span>
	         	&nbsp;
	         </td>
	         <td  >时间隔:<select name="sstpe"  id="sstpe" onchange="changeStep(this.value)">
		           <option value="month" <c:if test="${'year'!=voeqa.timeStep}">selected</c:if>>月</option>
		           <option value='year' <c:if test="${'year'==voeqa.timeStep}">selected</c:if>>年</option>          
		        </select>         
		     </td>
	         <td  align="right">
	         	<img style='cursor: pointer' src="manager/images/imgs/shebeigl_sousuo_an.png" onclick="submitform();"></img>
	         </td>
	      	</tr>
	      </table>
	      <span class="admin_clor_f00">${message}</span>
		</div>
  		</div>


			

		<jsp:include page="an_data.jsp"></jsp:include>
		
		
	</div>
	
	
	
					
						
		<!-- 设备列表 -->
		<div id="xtype" style="z-index:100;position:absolute;left:310px;top:110px;border: 1px solid #3399FF;width: 360px;height:200px;background-color: #FFFFFF;display: none;" >
			<div class="biao_lianj_1" style="overflow:auto;width: 360px;height:180px;" id="coDiv">
			<label >请选择设备类型!</label>
			<c:if test="${!(empty selobj.prList)}">			
				<%--<c:forEach items="${selobj.prList}" var="obj" varStatus="at">					
					<input type="checkbox" onclick="checkComp(this);" value="${obj.name}" id="ch${at.index }"/><label for="ch${at.index }" >${obj.name}</label><br/>	<!-- <a href="javascript:void(0);" >${obj}</a> -->								
				</c:forEach>
			--%></c:if>
			</div>
			<input type="hidden" id="eqIds" name="eqIds"/>
			<div class="biao_lianj_1" style="visibility: inherit;border-top-width: 1px;border-top-style: solid;border-top-color: #3399FF;" align="right">
			<a href="javascript:void(0);" onclick="$('#xtype').hide();" >确&nbsp;定</a>&nbsp;
			</div>
		</div>	
		
		
		<script language="JavaScript">
		<!--
			
			checkComp=function(obj){
				var selComp = document.getElementById("eqIds");

				if(obj.checked){
					if(selComp.value.length==0){
						selComp.value = obj.value;
					}else{
						selComp.value +=","+obj.value;
					}
				}else{
					if(selComp.value.indexOf(","+obj.value) !=-1){
						selComp.value = selComp.value.replace(","+obj.value,"");
					}else if(selComp.value.indexOf(obj.value+",")!=-1){
						selComp.value = selComp.value.replace(obj.value+",","");
					}else if(selComp.value.indexOf(obj.value)!=-1){
						selComp.value = selComp.value.replace(obj.value,"");
					}
					
				}
				
			}
		//-->
		</script>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<script>

		//基类
		Node=function(id,name,pid){
			this.id=id;
			this.name=name;
			this.pid=pid;
		};

		//设备生成类
		myObject=function(){
			this.aNodes=[];
		
		};

		
		
		myObject.prototype.add=function(id,  name, pid) {
			this.aNodes[this.aNodes.length] = new Node(id, name, pid);
		};

		myObject.prototype.getCo=function(id){
			var str='';
			var count=0;
			for(var i=0;i<this.aNodes.length;i++){
				if(this.aNodes[i].pid==id){
					str +='<input type="checkbox" onclick="checkComp(this);oncli(document.getElementById(\'lb'+i+'\'));" '+
					'value="'+this.aNodes[i].id+'" id="ch'+i+'"/><label id="lb'+i+'" for="ch'+i+'" onmouseover="onover(this);" onClick="oncli(this);" onmouseout="onout(this);">'+this.aNodes[i].name+'</label><br/>';
					count +=1;
				}
			}

			if(count==0){
				str='<label>请选择设备类型!</label>';
			}

			return str;
		}
		
		myObject.prototype.getidSel=function(id){
			//eqTypeP  subsel
			
			var at;
			var str='';
			for(var i=0;i<this.aNodes.length;i++){
				if(this.aNodes[i].id==id){					
					if(this.aNodes[i].pid==""){
						at=id;
						str=this.getPidSel(id);	
					}else{
						at=this.aNodes[i].pid;
						str=this.getPidSel(this.aNodes[i].pid);
					}				
					break;		
				}
			}

			//一级菜单定位
			var sel=document.getElementById("eqTypeP");
			for(var i=0;i<sel.options.length;i++){
				if(sel.options[i].value==at){
					sel.selectedIndex =i;
					break;
				}
			}
						
									
			return str;
		};	
		myObject.prototype.getPidSel=function(pid,selid){
			var str='<select id="subsel" onchange="getSubEqId(this.value);">';
			var eqid="${eqType}";
			var count=0;
			for(var i=0;i<this.aNodes.length;i++){
				if(this.aNodes[i].pid==pid){
					//if(count==0){
					//	str+='<option value="0">--请选择--</option>';
					//}
					count +=1;
					if(this.aNodes[i].id==eqid && ""==selid){
						str+='<option value="'+this.aNodes[i].id+'" selected>'+this.aNodes[i].name+'</option>';
					}
					else if(this.aNodes[i].id == selid){
						str+='<option value="'+this.aNodes[i].id+'" selected>'+this.aNodes[i].name+'</option>';
					}
					else{
						str+='<option value="'+this.aNodes[i].id+'" >'+this.aNodes[i].name+'</option>';
					}
									
				}
			}
			str +='</select>';
			if(count==0){
				str='';
			}		
					
			return str;
		};
		
	</script>
	<script>
		var obj = new myObject();  	//设备类型
		var co = new myObject();	//设备列表
	</script>
	
	
	<c:forEach items="${selobj.typelist}" var="dic" >
		<script>
			obj.add("${dic.equipTypeId}","${dic.typeName}","${dic.dbEquipType.equipTypeId}");
		</script>
	</c:forEach>
	<c:forEach items="${selobj.eqList}" var="obj" >
		<script>
			co.add("${obj.eqcode}","${obj.name}","${obj.eqtype}");
		</script>
	</c:forEach>
	
	
	
	<script>

		//选择类型 
		getMyChild=function(eqtype,styid){
			//$("#eqType").val(eqtype);
			if(""!=eqtype){
				$("#eqTypeChilds").html(obj.getPidSel(eqtype,styid));
				if (null == $("#subsel").val()) {
					getCo(eqtype);
				}else{
					getCo($("#subsel").val());
				}
			}else{
				$("#eqTypeChilds").html("");
			}
		}

		//二级类别
		getSubEqId=function(value){
			$("#subsel").val(value);
			getCo(value);
		}

		//同步设备
		getCo=function(eqtype){
			$("#eqIds").val("");
			$("#coDiv").html(co.getCo(eqtype));
		}		
	</script>


</body>
</html>

