<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<title>My JSP 'eqPicUpload.jsp' starting page</title>
		<link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-1.3.2.js"></script>

		<script language="JavaScript">
		var allFiles = document.getElementsByName("image");
			function addappendChild() {
				if($("#addBtn").text() == "添加"){
					if (allFiles.length < 10) {
					   //找到tbody
                       var t=$("#tb");
                       //克隆
                       var tr=$("#tb>tr").eq(1).clone(true);
                      //添加
                       t.append(tr);
                       //清空文本框
                       tr.find("input").val("");
					}
					if (allFiles.length == 10) {
						$("#addBtn").text("最多一次上传10张图纸");
					}
				}
			}
			
			function uploadBegin() {
				if($("#equipPicType").val()==""||$("#equipPicType").val==null){
					alert("请选择图纸类型!");
					return; 
				}
				var imageName = $("input[name='image']");
				var a = 0;
				var alertStr = "";
				$("input[name='image']").each(function(i){ 
					var imageFile = $(this).val();
					if(imageFile==""||	imageFile==null){
						a++;
					}	
				});
				if(a!=0&&a===$("input[name='image']").length){
					alert("请选择需要上传的图纸!");
					return; 
				}
				
				/**判断是否选择图纸编号*/
				var imageName = $("input[name='picName']");
				var b = 0;
				var alertStr = "";
				$("input[name='picName']").each(function(i){ 
					var imageFileCode = $(this).val();
					if(imageFileCode==""||	imageFileCode==null){
						b++;
					}	
				});
				if(b!=0&&b===$("input[name='picName']").length){
					alert("请选择需要上传的图纸名称");
					return; 
				}
				
				$("#addBtn").text("图纸正在上传中......");
				//document.getElementById("addBtn").disabled = "disabled";
				//document.getElementById("addBtn").text = "图纸正在上传中......";
				document.myform.submit();
			}

			function checkPic(a,b){
				var extArray = new Array(".jpg",".png",".dwg",".pdf");
				var flagIsTrue = 0 ;
				var ext = a.substr(a.indexOf("."));
				var check=false;
				for (var i = 0; i < extArray.length; i++) {
					if(extArray[i].toLowerCase()==ext.toLowerCase()){
						break;
					}else{
						flagIsTrue++;
					}
				}
				if (flagIsTrue!=0&&flagIsTrue==extArray.length) {
					alert("请选择正确的文件!");	
					document.getElementById(b).outerHTML += '';//清空IE的
                    document.getElementById(b).value = "";//可以清空火狐的
					//alert(jQuery("#img").val());
					a.length=0;
					return;	
				}
			}
			
			function doRemove(obj){
			  var imgName= $("input[name='picName']")
			  if(imgName.length>1){
			    $(obj).parent().parent().remove();
			    $("#addBtn").text('添加');
			  }else{
			     alert('至少保留一个上传项');
			  }
			}
			
		 function changeBuild(obj,childId){
            var buildId=$(obj).val();
            if(jQuery.trim(buildId)!=""){
               var dt={"buildId":buildId};
               $.ajax({
				      type: 'get',
					  url: "equipment_findStoreyByBuildId.action",
					  data: dt,
					  cache:false,
					  dataType: 'json',
					  success: function(data){
					     var arrayObj = new Array();//创建一个数组
					     var storey=eval(data);
					     var cid=$(childId).attr('id');
					     $("'#"+cid+"' option").remove();
					     $(childId).append("<option value=''>--请选择--</option>");
					     for(var k=0;k<storey.length;k++){
					       $(childId).append("<option value='"+storey[k]+"'>"+storey[k]+"</option>");
					     }
					  },
					  error:function(textStatus){
					      alert('error'+textStatus);
					  }
				 });
             }else{
                 var cid=$(childId).attr('id');
				 $("'#"+cid+"' option").remove();
			     $(childId).append("<option value=''>--请选择--</option>");
             }
         }

		 </script>
	</head>
	<body>
		<div align="center">
			<div id="admin_nr_jbxx" >
					<div style="color:#7C777B;text-align: center; " >
			 		注：文件上传格式（.dwg\.jpg\.pdf\.png）	
					</div>
				<s:form action="equipPic_uploadEquipPic.action" method="post" enctype="multipart/form-data" name="myform">
				    <input type="hidden" id="equipId" name="equipId" value="${equipId}" />
					<input type="hidden" id="portal.attachment" name="portal.attachment" />
					<input type="hidden" name="eqid" value="${eqid }" />
					<div id="mydiv">
						<table>
							<thead></thead>
							<s:fielderror/>
							<tbody id="tb">
							<th style="text-align:left">
								图纸类型
								<select id="equipPicType" name="equipPicType" style="width:160px">
									<option value="">--请  选   择 --</option>
									<c:forEach items="${listPicTypeList}" var="picType">
										<option value="${picType.pic_type_id}">
											${picType.pic_type_name}
										</option>	
									</c:forEach>
								</select><span class="admin_clor_f00">*</span>
								<span id="subeqty"></span>
							</th>
								<tr><td>图纸编号：<input type="text" name="picCode" style="ime-mode:disabled"/>&nbsp;&nbsp;&nbsp;图纸名称：<input type="text" name="picName"/>&nbsp;&nbsp;&nbsp;<input type="file" id="img1" name="image" onchange="checkPic(this.value,'img1')" value=""/>&nbsp;&nbsp;&nbsp;<a onclick="doRemove(this);" class="btn blue">移除</a></td></tr>
								<tr><td>图纸编号：<input type="text" name="picCode" style="ime-mode:disabled"/>&nbsp;&nbsp;&nbsp;图纸名称：<input type="text" name="picName"/>&nbsp;&nbsp;&nbsp;<input type="file" id="img2" name="image" onchange="checkPic(this.value,'img2')" value=""/>&nbsp;&nbsp;&nbsp;<a onclick="doRemove(this);" class="btn blue">移除</a></td></tr>
								<tr><td>图纸编号：<input type="text" name="picCode" style="ime-mode:disabled"/>&nbsp;&nbsp;&nbsp;图纸名称：<input type="text" name="picName" />&nbsp;&nbsp;&nbsp;<input type="file" id="img3" name="image" onchange="checkPic(this.value,'img3')" value=""/>&nbsp;&nbsp;&nbsp;<a onclick="doRemove(this);" class="btn blue">移除</a></td></tr>
							</tbody>
						</table>
					</div>
					<table>
						<tr>
							<td width="160" align="right">
								<a onclick="addappendChild();" class="btn blue" id="addBtn">添加</a>
							</td>
							<td width="60">
								<a onclick="uploadBegin()" class="btn blue">上 传</a>
							</td>
							<td width="60">
								<a href="equipPic_findEquipPicList.action?equipId=${equipId}" class="btn blue">返回</a>
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</body>
</html>
