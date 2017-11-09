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
		function initOnload(){
			if("${isfail}" != ""){
				alert("请重新登陆！");
				window.top.location="user!closeUsers.action";
			}


			//同步二级菜单
			var stypid="${voobj.styid}";
			if("" != stypid){
				getMyChild("${voobj.ftyid}",stypid);
				getCo(stypid);
			}
			else{
				getCo("${voobj.ftyid}");
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
			$("#eqids").val(eqIds);

			if(eqIds.length<1){
				alert("请选择设备!");
				return ;
			}

			
			myform.submit();			
		}
		
	</script>
	
	
	<script language="JavaScript">
		onover=function(obj){
			if("#000000" !=obj.style.color ){
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
			if("#000000" !=obj.style.color ){
				obj.style.color="#3399FF";
			}
		}
	</script>

	<style type="text/css">
	<!--
	div {
		/*border: 1px solid #3399FF;*/
	}
	-->
	</style>
	</head>

<body onload="initOnload();" style="overflow:scroll;overflow-x:hidden;overflow-y:hidden;">

		<!-- form 表单 -->
		<form method="post" action="eqrepair_showUI.action" name="myform"	id="myform">
			<input type="hidden" id="power"     name="voobj.power"			/>   	<!-- 能源类型 -->
			<input type="hidden" id="startDate" name="voobj.startDate"		/> 		<!-- 开始时间 -->
			<input type="hidden" id="endDate"   name="voobj.endDate"		/>		<!-- 结束时间 -->
			<input type="hidden" id="timeStep"  name="voobj.timeStep"		/>		<!-- 时间间隔 -->
			<input type="hidden" id="hospid" name="voobj.hospid" /><!-- 医院Id -->
			<input  type="hidden" id="ftyid"  name="voobj.ftyid"/> <!-- 首类型 -->
			<input  type="hidden" id="styid"  name="voobj.styid"/> <!-- 子类型 -->
			<input  type="hidden" id="eqids"  name="voobj.eqids"/><!-- 设备列表 -->
			<input  type="hidden" id="coname"  name="voobj.coname" /><!-- 厂商 -->			
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
						设备能效分析——同类设备维修比较
					</td>
				</tr>
			</table>
		</div>	
		
		<!-- 查询条件 -->
		<div>
		<table width="100%" border="0" align="right" class="louyujj_xiaxian_hui gai_left_duiqi" >
	       <tr class="shebeigl_inp_zt">
	       		<td width="300" >
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
	         	<span><input name="sDate1" type="text" id="sDate1"  value="${voobj.startDate }" size="8" maxlength="6"  class="Wdate"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'${'year'== voobj.timeStep?'yyyy':'yyyyMM' }'})" /></span>至 
	         	<span><input name="sDate2" type="text" id="sDate2"  value="${voobj.endDate }" size="8" maxlength="6"  class="Wdate"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'${'year'== voobj.timeStep?'yyyy':'yyyyMM' }'})" /></span>
	         	&nbsp;
	         </td>
	         <td  >时间隔:<select name="sstpe"  id="sstpe" onchange="changeStep(this.value)">
		           <option value="month" <c:if test="${'year'!=voobj.timeStep}">selected</c:if>>月</option>
		           <option value='year' <c:if test="${'year'==voobj.timeStep}">selected</c:if>>年</option>          
		        </select>         
		     </td>
	         <td align="right">
	         	<img style='cursor: pointer' src="manager/images/imgs/shebeigl_sousuo_an.png" onclick="submitform();"></img>
	         </td>
	      	</tr>
	      </table>
	      <span class="admin_clor_f00">${message}</span>
		</div>
  		


			

		<c:if test="${!(empty obj)}">			
			<!-- 图形展示区 -->
			<div style="height: 410px;width: 780px;">
				<div style="height: 350px;width: 270px;float: left;clear:left;z-index: 1;margin-top: 10px;">
					<table width="265px;"  border="0" style="height:350px;" >							
						<c:if test="${!(empty obj.myDrawF)}">
						<!-- 图形1 -->
						<tr valign="top">
							<td><img src="${obj.myDrawF }"  border="1"  width="260px;" onclick="showImgF('imgf');" style="cursor:url(manager/images/cursor/zoomin.cur);"/></td>
						</tr>
					</c:if>					
				</table>
			</div>
			
			<div style="height: 410px;width: 500px;float: right;z-index: 1;" >						
				<c:if test="${!(empty obj.mytable)}">
				<!-- 表格区 -->
				<table width="100%" border="0" cellspacing="1" class="biaoge_bg1 gai_left_duiqi" align="center">
					<caption><span class="admin_jbxx_bt1" >${obj.mytable.title }</span></caption>
					<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1" align="center">
					<c:forEach items="${obj.mytable.keys}" var="key">
							<td >${key }</td>
					</c:forEach>											
					</tr>
					</thead>
										
					<tbody id="stu-datas-tb" >
					<c:forEach items="${obj.mytable.values}"  var="obj">
					<tr>
						<c:forEach items="${obj}"  var="rowData" varStatus="at">
							<c:if test="${at.index==0}"><td>${rowData }</td></c:if>
							<c:if test="${at.index!=0}"><td>${rowData.rcount }</td></c:if>							
						</c:forEach>																										
					</tr>
					</c:forEach>
					</tbody>
				</table>
				</c:if>							
			</div>
			
			
			<!-- 大图展示 -->
			
			<c:if test="${!(empty obj.myDrawF) }">
			<!-- 大图1 -->
			<div style="display: none;" id="imgf" onclick="hideMe('imgf');" >
			<img src="${obj.myDrawF }" border="1" usemap="#useMapF" />
			</div>
			</c:if>
			
		
			<div style="display: none;cursor: pointer;border: 0px solid #FF0000;" id="outimg">
				<img src="manager/images/cursor/zoomout.png" width="24px;"  onclick="closeImg();" title="关闭"/>
			</div>
			
			
			<!-- map区 -->
			${obj.useMapF }				
			
						
			
			</div>
			</c:if>
		
		
		
	</div>
	
	
	
					
						
		<!-- 设备列表 -->
		<div id="xtype" style="z-index:100;position:absolute;left:310px;top:60px;border: 1px solid #3399FF;width: 360px;height:200px;background-color: #FFFFFF;display: none;" >
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
					'value="'+this.aNodes[i].id+'" id="ch'+i+'"/><label id="lb'+i+'" for="ch'+i+'" style="color:#3399FF;" onmouseover="onover(this);" onclick="oncli(this);" onmouseout="onout(this);">'+this.aNodes[i].name+'</label><br/>';
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
			co.add("${obj.eqid}","${obj.name}","${obj.eqtype}");
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

