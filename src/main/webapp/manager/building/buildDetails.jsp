<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@  taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<base href="<%=basePath%>" />
<title>My JSP 'buildFloor.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>

<link href="<c:url value="/static/css/font-awesome/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/static/css/aries/jquery.aries.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/css/jquery_ui/jquery-ui-themes.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/css/dropdown/jquery.dropdown.css"/>" rel="stylesheet" type="text/css"/>
<script src="<c:url value="/static/js/jquery/jquery-2.1.1.min.js"/>"></script>
<script src="<c:url value="/static/js/jquery_ui/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/static/js/aries/jquery.aries.js"/>"></script>
<script src="<c:url value="/static/js/dropdown/jquery.dropdown.min.js"/>"></script>

<script src="manager/js/annu.js" type="text/javascript"></script>
<script type="text/javascript" src="manager/js/biaoge_1.js"></script>
<script type="text/javascript" src="manager/js/ceng.js"></script>
<script type="text/javascript" src="manager/js/xialakuang.js"></script>
<script type="text/javascript" src="manager/js/changeSelect.js"></script>
<script type="text/javascript" src="manager/js/jsont.js"></script>
<script type="text/javascript" src="manager/js/tableTr.js"></script>
<script type="text/javascript" src="manager/js/zxq.js"></script>

<script type="text/javascript">

//添加楼宇大修
f_add = function() {
	document.myForm2.submit();
}
f_show = function() {
if($("#BJ").text()=="修改") {
       $("#bFH").show();
       $("#mydiv").show();
       $("#mydiv1").hide();
       $("#BC").show();
       $("#BJ").text("保存");
       $("#BJ").hide();
      }
      else if($("#BJ").text()=="保存"){
       var sum=0.0;
       
      <c:forEach items="${LIST.alist}" var="li" varStatus="i">
      for ( var i = 0; i < 11; i++) {
	   if(jQuery("#area").val()=="") {
	     jQuery("#area").val(0);
	   }
	}
       sum+=parseFloat(jQuery("#area").val());
      </c:forEach>
     
      if(sum>"${BUIAREA}")
      {
        alert("你填写的楼宇区域面积总和("+sum+"㎡)大于该楼宇总建筑面积(${BUIAREA}㎡)，请重新输入！");
        return;
      }else{
       $("#mydiv").hide();
       $("#mydiv1").show();
       $("#BJ").text("修改");
       $("#bFH").hide();
       $("#BJ").show();
       $("#BC").hide();
       document.myform.submit();
       }
      }
    }
  
function f_openWindow(lc,qy,obj,seq,buildId,areaId)
{
   var bz=$(obj).attr('remark');
   var mj=$(obj).val();
   var dt='';
   var params={"storey":lc,"areaId":areaId,"buildingId":buildId};
   $.ajax({
      url:"buildStorey!getHisStoreyDate.action?t="+Math.random(),
      type:"post",
      dataType:"json",
      data: params,
      success: function (data) {
           var res=data;
           //alert(JSON.stringify(data));
             if(res!="failed"){
                dt=res;
             }
        //如果是模式对话框，则是returnValue值  
          var mydata = new Array();
			mydata[0] = lc;
			mydata[1] = qy;
			mydata[2] = mj;
			mydata[3] = bz;
			mydata[4] = seq;
			mydata[5] = dt;
			mydata[6] = areaId;
			mydata[7] = buildId;   
			var returnValue=window.open("<%=path%>/manager/building/addBuilduse.jsp?lc="+lc+"&qy="+qy+"&mj="+mj+"&bz="+bz+"&seq="+seq+"&dt="+dt+"&areaaId="+areaId+"&buildId="+buildId,mydata,"height=400, width=400, toolbar =no, menubar=no, left=450,top=20,scrollbars=no, resizable=no, location=no, status=no");
      },
      error:function (dt){
        alert("内部服务器错误，请稍后再试！");
      }
   });
}
	
 //模糊查
f_change=function(){
   $('#buildingId').val($('#buildingIds').val());
//    alert($('#buildingIds').val());
   document.myForm.submit();
}


go_menu=function(){
	//菜单定位
	try{
	var obj = window.parent.frames["hopdtree"];
	var objs =obj.$("a");
	var hospid=$("#hospid");
			
	for(var i=0;i<objs.length;i++){
		if(null != objs[i]){
			if(objs[i].id.indexOf("aa") != -1){
				if(objs[i].innerText=="楼宇信息"){
					objs[i].className="biao_lianj_1";
				}else{
					objs[i].className="biao_lianj_2";
				}
			}
		}
	} 
	}catch(e){}
}

function loadpage(){
  go_menu();
  f_load('1',$('#buildingId').val(),'2','1','1')
}

//任意提醒，通过标签ID给需要绑定数据的元素绑定数据
$(document).ready(function(){
	<c:if test="${empty remindJson}">
		var remindJson="";
	</c:if>
	<c:if test="${!empty remindJson}">
		var remindJson=${remindJson};
	</c:if>
	 	if(remindJson!=""){
	 	for(var obj in remindJson){
 	 				console.debug("     buildingarea=  "+remindJson[obj].resultsql);
	 				$("#"+obj).data("aries",remindJson[obj]);
	 		}
	 	}
	});
     </script>
</head>
<body onload="loadpage()"
	style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
	<form id="hiddenForm" action="" method="post"></form>
	<div id="admin_nr_rightg">
		<div class="canshusz_btbg_1">
			<table class="titleBg">
				<tr style="height: 20px">
					<td width="30" align="center"><img
						src="manager/images/common/28-01.png" /></td>
					<td>楼宇基建——楼宇信息</td>
				</tr>
			</table>
		</div>
		<div class="admin_nr_right_g02">
			<table width="100%" border="0">
				<tr style="height: 25px;" valign="middle">
					<td width="2"></td>
					<td width="83" style="cursor: pointer"><img
						src="manager/images/imgs/gai_louyu_annu7.png"
						onclick="f_JB('${hospid}','${buildingId}','${editflag}','${myfirst}');	"
						id="img1" title="基本信息" /></td>
					<td width="4" valign="bottom"><img
						src="manager/images/imgs/gaixian.png" /></td>
					<td width="83" style="cursor: pointer"><img
						src="manager/images/imgs/gai_louyu_annu5.png"
						onclick="f_BLT('${hospid}','${buildingId}','${editflag}','${myfirst}');  "
						id="img2" title="面积比例" /></td>
					<td width="4" valign="bottom"><img
						src="manager/images/imgs/gaixian.png" /></td>
					<td width="83" style="cursor: pointer"><img
						src="manager/images/imgs/gai_louyu_annu3.png"
						onclick="f_YT('${hospid}','${buildingId}')" id="img3"
						style="cursor: hand" title="面积详情" /></td>
					<td width="4" valign="bottom"><img
						src="manager/images/imgs/gaixian.png" /></td>
					<td width="83" style="cursor: pointer"><img
						src="manager/images/imgs/gai_louyu_annu1.png"
						onclick="f_DXandJG('${hospid}','${buildingId}','${editflag}','${myfirst}');  "
						id="img4" title="大修信息" /></td>
					<td width="4" valign="bottom"><img
						src="manager/images/imgs/gaixian.png" /></td>
					<td width="83" style="cursor: pointer"><img
						src="manager/images/imgs/tuzhishagnchuang1.png"
						onclick="f_picUpload(${buildingId})" id="img5" title="楼宇图纸" /></td>
					<td></td>
				</tr>
			</table>
		</div>




		<!-- 用途所占面积信息 -->
		<div id="areaDiv">
			<div id="div1" style="margin-top: 10px;" class="gai_left_duiqi">
				<table width="770" border="0" align="center">
					<tr valign="middle" style="height: 25px;">
						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="outpatient">门诊</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_menz"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[0].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[0].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[0].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[0].mj!=0}">占总门诊面积：${buildarea[0].mj*100/builduseArea[0].mj}%</c:when>
									         <c:when test="${hospitalArea[0].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[0].mj!=0}">占医院总面积：${buildarea[0].mj*100/hospitalArea[0].mj}% </c:when>
									      </c:choose>">
									${buildarea[0].mj}${empty buildarea[0].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="emergency">急诊</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_jiz"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_ zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[1].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[1].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[1].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[1].mj!=0}">占总门诊面积：${buildarea[1].mj*100/builduseArea[1].mj}%</c:when>
									         <c:when test="${hospitalArea[1].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[1].mj!=0}">占医院总面积：${buildarea[1].mj*100/hospitalArea[1].mj}% </c:when>
									      </c:choose>">
									${buildarea[1].mj}${empty buildarea[1].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="hospitalization">住院</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_bingf"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[2].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[2].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[2].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[2].mj!=0}">占总门诊面积：${buildarea[2].mj*100/builduseArea[2].mj}%</c:when>
									         <c:when test="${hospitalArea[2].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[2].mj!=0}">占医院总面积：${buildarea[2].mj*100/hospitalArea[2].mj}% </c:when>
									      </c:choose>">
									${buildarea[2].mj}${empty buildarea[2].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="medical">医技</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_yij"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[3].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[3].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[3].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[3].mj!=0}">占总门诊面积：${buildarea[3].mj*100/builduseArea[3].mj}%</c:when>
									         <c:when test="${hospitalArea[3].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[3].mj!=0}">占医院总面积：${buildarea[3].mj*100/hospitalArea[3].mj}% </c:when>
									      </c:choose>">
									${buildarea[3].mj}${empty buildarea[3].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>
					</tr>

					<tr align="center" valign="middle" style="height: 5px;">
						<td colspan="15"></td>
					</tr>
					<tr valign="middle">
						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="security">保障</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_houq"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[4].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[4].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[4].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[4].mj!=0}">占总门诊面积：${buildarea[4].mj*100/builduseArea[4].mj}%</c:when>
									         <c:when test="${hospitalArea[4].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[4].mj!=0}">占医院总面积：${buildarea[4].mj*100/hospitalArea[4].mj}% </c:when>
									      </c:choose>">
									${buildarea[4].mj}${empty buildarea[4].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="administration">行政</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_xingz"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[5].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[5].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[5].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[5].mj!=0}">占总门诊面积：${buildarea[5].mj*100/builduseArea[5].mj}%</c:when>
									         <c:when test="${hospitalArea[5].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[5].mj!=0}">占医院总面积：${buildarea[5].mj*100/hospitalArea[5].mj}% </c:when>
									      </c:choose>">
									${buildarea[5].mj}${empty buildarea[5].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="scientific">科研</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_key"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[6].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[6].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[6].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[6].mj!=0}">占总门诊面积：${buildarea[6].mj*100/builduseArea[6].mj}%</c:when>
									         <c:when test="${hospitalArea[6].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[6].mj!=0}">占医院总面积：${buildarea[6].mj*100/hospitalArea[6].mj}% </c:when>
									      </c:choose>">
									${buildarea[6].mj}${empty buildarea[6].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="education">教育</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_jiaox"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[7].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[7].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[7].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[7].mj!=0}">占总门诊面积：${buildarea[7].mj*100/builduseArea[7].mj}%</c:when>
									         <c:when test="${hospitalArea[7].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[7].mj!=0}">占医院总面积：${buildarea[7].mj*100/hospitalArea[7].mj}% </c:when>
									      </c:choose>">
									${buildarea[7].mj}${empty buildarea[7].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>

					</tr>
					<tr align="center" valign="middle" style="height: 5px;">
						<td colspan="15"></td>
					</tr>
					<tr>
						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="life">生活</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_shengh"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[8].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[8].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[8].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[8].mj!=0}">占总门诊面积：${buildarea[8].mj*100/builduseArea[8].mj}%</c:when>
									         <c:when test="${hospitalArea[8].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[8].mj!=0}">占医院总面积：${buildarea[8].mj*100/hospitalArea[8].mj}% </c:when>
									      </c:choose>">
									${buildarea[8].mj}${empty buildarea[8].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="garage">车库</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_chek"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[9].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[9].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[9].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[9].mj!=0}">占总门诊面积：${buildarea[9].mj*100/builduseArea[9].mj}%</c:when>
									         <c:when test="${hospitalArea[9].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[9].mj!=0}">占医院总面积：${buildarea[9].mj*100/hospitalArea[9].mj}% </c:when>
									      </c:choose>">
									${buildarea[9].mj}${empty buildarea[9].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>

						<td align="center" class="cahshusz_qumc_zt1" width="60"><span class="aries-anchor-line"
							data-aries="#aries-1" id="others">其它</span></td>
						<td valign="middle" width="50">
							<div class="gongnengqu_ktf_1 quyuyanse_qit"></div>
						</td>
						<td valign="middle" class="cahshusz_bfb_zt1" width="85">
							<c:choose>
								  <c:when test="${buildarea[10].mj>0.0}">
									<a title="
									      <c:choose>
									         <c:when test="${BUIAREA > 0}">占楼宇面积：${(buildarea[10].mj*100)/BUIAREA}%</c:when>
									         <c:when test="${builduseArea[10].mj==0}">占总门诊面积：0%</c:when>
									         <c:when test="${builduseArea[10].mj!=0}">占总门诊面积：${buildarea[10].mj*100/builduseArea[10].mj}%</c:when>
									         <c:when test="${hospitalArea[10].mj==0}">占医院总面积：0%</c:when>
									         <c:when test="${hospitalArea[10].mj!=0}">占医院总面积：${buildarea[10].mj*100/hospitalArea[10].mj}% </c:when>
									      </c:choose>">
									${buildarea[10].mj}${empty buildarea[10].mj?'0':'' }&nbsp;㎡</a>
									</c:when>
									<c:otherwise>
									    0&nbsp;㎡
									</c:otherwise>
								</c:choose>

							</td>
					</tr>
				</table>
			</div>


			<form name="myForm" action="buildDetails!findBuild.action"
				method="post" id="myForm">
				<input type="hidden" id="buildingId" name="buildingId"
					value="${buildingId }" />
					 <input type="hidden" id="tabIndex"
					name="tabIndex" value="${tabIndex}" />
				<table width="100%" border="0"
					class="louyujj_xiaxian_hui gai_left_duiqi">
					<tr style="height: 35px;">
						<td>
							<!--<c:if test="${roleMessage == '1' ||roleMessage == '4' }">
					<span class="gai_clor_063">${listB[0].dbHospInfo.hospName}</span> 			
				</c:if>-->					
						</td>
						<td></td>
						<td <c:if test="${editFlag==1}"> width="90" </c:if> align="right"
							class="shebeigl_inp_zt">楼宇：</td>
						<td width="80" align="right"><select id="buildingIds"
							name="buildingIds">
								<c:forEach items="${listB}" var="build">
									<option value="${build.buildingId}" ${build.buildingId==buildingId ? 'selected':''}>
										${build.buildingName}
									</option>
								</c:forEach>
						</select></td>
						<td width="80" align="right">
							<img style='cursor: pointer'
								src="manager/images/imgs/shebeigl_sousuo_an.png"
								onclick="f_change()" />
						</td>
						<c:if test="${hideOrshow==1}">
							<td width="50" align="right" id="xFH"><a
								href="building_findBuild.action?editFlag=${editFlag
											}&hospid=${listB[0].dbHospInfo.seqHosp}&tag=${tag}"
								class="btn blue">返回 </a></td>
						</c:if>
					</tr>
				</table>
				
				
				
				<input type="hidden" name="editFlag" value="${editFlag}"
					id="editFlag" /> <input type="hidden" name="hosinfoid"
					value="${hosinfoid}" id="hosinfoid" /> <input type="hidden"
					name="myfirst" value="${myfirst}" id="myfirst" /> <input
					type="hidden" name="hideOrshow" value="${hideOrshow}"
					id="hideOrshow" /> <input type="hidden" name="tag" value="${tag}"
					id="tag" />
			</form>
		</div>
		<!-- 楼宇基本信息 -->
		<div id="div22" style="display: none;" class="gai_left_duiqi">
			<iframe src="" id="iframeJB" scrolling="no" width=780 height="520"
				marginwidth=0 marginheight=0 frameborder=0 name="jb"> </iframe>

		</div>
		<!-- 面积比例图 -->
		<div id="div33" style="display: none;" class="gai_left_duiqi">
			<iframe src="" id="iframeBlt" scrolling=no width=770 height="1000"
				marginwidth=0 marginheight=0 frameborder=0 name="Blt"></iframe>
		</div>

		<!-- 楼宇图纸 -->
		<div id="divpic" style="display: none;" class="gai_left_duiqi">
			<iframe src="" id="iframeTZ" scrolling=no width=1000 height="1000"
				marginwidth=0 marginheight=0 frameborder=0 name="tz"></iframe>
		</div>

		<!-- 楼层用途信息 -->
		<div id="div44" style="display: none;" class="gai_left_duiqi">
			<table width="770" border="0">
				<tr align="right">
					<td width="93%" align="right"><a href="javascript:f_show()"
						id="BJ" class="btn blue">修改</a></td>
					<td width="7%" align="right" id="bFH"><a
						href="buildDetails!findBuild.action?buildingId=${buildingId}&hospId=${hospId}&editFlag=${editFlag }&showOrhide=yt&hideOrshow=${hideOrshow}&tag=${tag}&tabIndex=3"
						class="btn blue">保存</a></td>
				</tr>
			</table>
			<input type="hidden" name="editFlag" value="${editFlag}"
				id="editFlag" /> <input type="hidden" name="hideOrshow"
				value="${hideOrshow}" id="hideOrshow" /> <input type="hidden"
				name="myfirst" value="${myfirst}" id="myfirst" /> <span
				class="admin_clor_f00"><c:if test="${messages=='1'}">操作成功</c:if>
				<c:if test="${messages=='2'}">操作失败</c:if> </span>
			<!--<a href="buildStoreys!buildingStorey.action?buildingId=12">面积详情</a>
				-->
			<div id="mydiv1" style="overflow: auto; height: 500px; width: 800px;">
				<table width="760" border="0" cellspacing="1" class="biaoge_bg1">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<th colspan="12" scope="col" align="left">
								<table width="760" border="0">
									<tr>
										<th width="624" align="left">实际用途面积，单位：平方米</th>
									</tr>
								</table>
							</th>
						</tr>
						<tr class="biaog_kan1 admin_bgclor_fff">
							<c:forEach items="${columnName}" var="area" step="1" varStatus="i">
								<th width="280" scope="col">${area }</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody id="stu-datas-tb2">
						<c:forEach items="${valueList}" var="list" step="1" varStatus="i">
							<tr>
								<c:forEach items="${list}" var="slist" varStatus="j">
									<th width="300" height="30" scope="col">
										<c:if test="${slist >0}">
											<a	onmouseover="wsug(event,'${remarksList[i.index][j.index-1]}');"	onmouseout="wsug(event, 0);"> ${slist } </a>
										</c:if> 
										<c:if test="${slist <0}">${fn:replace(slist,'-','B')}</c:if>
									</th>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div id="mydiv"
			style="display: none; overflow: auto; width: 800px; height: 500px; float: left;">
			<form name=myform
				action="buildUse_showBuildUseFloor.action?hosinfoid=${HOSPID}&buildingId=${buildingId}&editFlag=${editFlag}&showOrhide=yt&hideOrshow=${hideOrshow}&tag=${tag}&tabIndex=3"
				method="post">
				<table width="760" border="0" cellspacing="1"
					class="admin_bgclor_c6c">
					<thead>
						<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
							<th colspan="12" scope="col" align="left">
								<table width="760" border="0">
									<tr>
										<th width="624" align="left">实际用途面积，单位：平方米</th>

									</tr>
								</table>
							</th>
						</tr>
						<tr class="biaog_kan1 admin_bgclor_fff">
							<c:forEach items="${columnName}" var="area" step="1"
								varStatus="i">
								<th width="280" scope="col">${area }</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody id="stu-datas-tb3">
						<c:forEach items="${valueList}" var="list" varStatus="i">
							<tr>
								<c:forEach items="${list}" var="selist" varStatus="j">
									<c:choose>
										<c:when test="${j.index==0}">
											<td align="center">
												<div class="canshusz_input_1" style="border: 0px">
													${selist }</div>
											</td>
										</c:when>
										<c:otherwise>
											<td align="center"><input style="text-align: center"
												type="text" name="area" id="area"
												value="<c:if test="${selist>0}">${selist}</c:if> <c:if test="${selist<0}">${selist}</c:if>"
												onclick="f_openWindow(${list[0] },'${columnName[j.index]}',this,'${seqList[i.index][j.index-1]}','${buildingId }','${areaIdList[j.index-1]}')"
												remark="${remarksList[i.index][j.index-1]}"
												recordTime="${recorsDate}" class="canshusz_input_1"
												readonly="readonly" /></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>

		<!-- 楼宇结构和大修信息 -->
		<div id="div55" style="display: none;" class="gai_left_duiqi">
			<iframe src="" id="iframeJG" scrolling=no width=770 height="253"
				marginwidth=0 marginheight=0 frameborder=0 name="jb"></iframe>
			<form name="myForm2"
				action="buildRepair!showBuildRepairDic.action?buildingId=${buildingId }&addOredit=1&hospid=${hospid }&editFlag=${editFlag}"
				method="post">
				<table width="770" border="0" class="louyujj_xiaxian_hui">
					<tr align="right">
						<td width="100%" align="right"><a href="javascript:f_add()"
							class="btn blue">添加</a></td>
					</tr>
				</table>
			</form>
			<span class="admin_clor_f00"><c:if test="${message==1}">操作成功</c:if>
				<c:if test="${message==2}">操作失败</c:if> </span>
			<table width="100%" border="0" cellspacing="1" class="biaoge_bg1">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="13%" scope="col">大修次数</th>
						<th width="17%" scope="col">时间</th>
						<th width="25%" scope="col">费用（RMB：元）</th>
						<th width="28%" scope="col">维修内容</th>
						<th width="17%" scope="col">操作</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<c:forEach items="${listRepair}" var="repair" varStatus="s">
						<tr class=" biaog_kan2 biaog_zt2">
							<td align="center">第${s.count }次 <input type="hidden"
								id="seq" value="${repair.seq}" />
							</td>
							<td align="center">${repair.repairttime}</td>
							<td align="center"><fmt:formatNumber
									value="${repair.totalcost}" pattern="#,#00.00#" /></td>
							<td align="center">${repair.remark}</td>
							<td align="center" class="biao_lianj_1"><a
								href="buildRepair!updateShowRepair.action?repid=${repair.seq }&addOredit=2&buildingId=${buildingId }&hospid=${hospid}&editFlag=${editFlag}&showOrhide=dx&hideOrshow=${hideOrshow }&tag=${tag }">修改</a>
								<a
								href="javascript:if(window.confirm('是否确定删除?')){window.location='buildRepair!deleteBuildRepair.action?repid=${repair.seq  }&buildingId=${buildingId}&hospid=${hospid}';}">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
