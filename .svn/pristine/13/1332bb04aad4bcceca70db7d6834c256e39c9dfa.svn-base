<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>

			<title>楼宇基建 图纸上传</title>

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
	   
			<script type="text/javascript" 	src="manager/js/jquery-1.3.2.js"></script>
	       <script type="text/javascript" src="manager/js/jquery.validate.js"></script>

	        
<script language="JavaScript">
	
         
         //验证成功后事件
         function uploadCheck(){
         	var buildingId=document.getElementById('buildingId');
         	var storey=document.getElementById('storey');
         	var buildingPicType=document.getElementById('buildingPicType');
         	
         	if(buildingId.value==null || buildingId.value=='0'){
         		alert('请选择楼宇');
         		buildingId.focus();
         		return false;
         	}
         	if(storey.value==null || storey.value=='0'){
         		alert('请选择楼层');
         		storey.focus();
         		return false;
         	}
         	if(buildingPicType.value==null || buildingPicType.value=='0'){
         		alert('请选择图纸类型');
         		buildingPicType.focus();
         		return false;
         	}
         	var check=checkImg();
         	if(check==false){
         		return false;
         	}
         
         
         	document.addBuildingFrom.submit();
         }
         
         function checkImg(){
             var endFlag = false;
         	var img=$("input:file");
         	var imgName= $("input[name='picName']");
         	var a = 0;
         	var b = 0;
         	for(i=0;i<img.length;i++){
         		if(imgName.get(i).value!=''){
             		if(img.get(i).value==''){
             			alert('请选择图片');
             			return false;
             		}else{
             			var fileCheck= checkFileHz(img.get(i).value);
                 		if(!fileCheck){
                 			return false;
                 		}
             		}
         		}else{
             		b++;
         		}
         		if(img.get(i).value!=''){
             		if(imgName.get(i).value==''){
             			alert('请填写图纸名');
             			return false;
             		}else{
             			var fileCheck= checkFileHz(img.get(i).value);
                 		if(!fileCheck){
                 			return false;
                 		}
             		}
         		}else{
             		a++;
         		}
           		if(a==img.length&&a!=0){
           			alert('请填写图纸名');
         			img.get(0).focus();
         			return false;
           		}
           		if(b==img.length&&b!=0){
           			alert('请选择图片');
         			img.get(0).focus();
         			return false;
           		}
         		
         	}
         	return true;
         }
         
         //判断file后缀名
		function checkFileHz(imageFile){
			//
			var ext = imageFile.substring(imageFile.lastIndexOf('.'),imageFile.length);
			extArray = new Array(".jpg",".png",".dwg");
			var check=false;
			for (var i = 0; i < extArray.length; i++) {
					if(extArray[i].toLowerCase()==ext.toLowerCase()){
						check=true;
						break;
					}
			}
			if(!check){
				alert("对不起，只能上传以下格式的文件:  .dwg - .jpg - .png "+extArray.length+'种格式文件'
				+ "\n请重新选择符合条件的文件"
				+ "再上传.");
				return false;
			}				
			return true;
	}	
	
	function getStorey(buildingId){
		//ajax获取所有楼层
			$.ajax({
	  			type:"POST",
		  		url:"buildingPic!findStorey.action?buildingId="+buildingId+"&nd="+new Date().getTime(),
		  		success:function(result) {
		  			var json = eval('('+result+')');
		  			//清空楼层信息
		  			removeStorey();
		  			//添加负楼层
		  			addDownStorey(json[0].storey_num_down);
		  			//添加楼上
		  			addUpStorey(json[0].storey_num_up);
		  		},
		  		error:function() {
		  			alert("您点的太快了，请稍后再试！");
		  		}
	  		});
	}
	
	//将楼层信息清空
		function removeStorey(){
			var q=document.getElementById("storey");
		    q.options.length=0;
		    q.options[0]=new Option('--请  选   择 --',0);
		}
	
	//添加负楼层
		function addDownStorey(down){
			var q=document.getElementById("storey");
			var i=0;
			//负数层
			var count=down;
			var temp=count-(count*2);
			for(i;i<down;i++){
  	 			q.options[i]=new Option('B'+count+'层',temp);

  	 			count--;
  	 			temp++;
  	 		}
		}
		//添加正数楼层
		function addUpStorey(up){
			var q=document.getElementById("storey");
			var i=q.options.length;
			//楼上层数
  	 		for(a=1;a<=up;a++){
  	 			q.options[i]=new Option(a+'层',a);
  	 			i++;
  	 		}
		}
	
	//添加图片
	function addImg(obj){
	    var childHtml="<tr>"+$(obj).parent().parent().html()+"</tr>";
	    $(obj).parent().parent().parent().append(childHtml);
	}
	

	function removeTr(obj){
		if($("input:file").length==1){
			return false;
		}
		$(obj).parent().remove();
	}
	
 </script> 
	</head>
	<body>

			
			<s:form action="buildingPic!upload.action" enctype="multipart/form-data" method="post" id="addBuildingFrom" name="addBuildingFrom">
				
				<input type="hidden" name="dbBuildingPic.dbUsersByOper.seq" value="${empty sessionScope.users?'':sessionScope.users.seq}" />
			
			
				<table width="770" border="0" cellspacing="1" class="gai_left_duiqi">
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
					<td class="admin_bgclor_e3f admin_zt_14">
							楼宇选择<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">												
							
							<s:select list="dbBuildings" name="dbBuildingPic.dbBuilding.buildingId" theme="simple"
							listKey="building_id" listValue="building_name"
							headerKey="0" headerValue="--  请  选  择    --"
							onchange="getStorey(this.value)" id="buildingId" style="width:155px;"></s:select>
							<span id="subeqty"></span>
						</td>
						<td class="admin_bgclor_e3f admin_zt_14">
							楼层选择<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">												
							<select id="storey" name="dbBuildingPic.storey" style="width:155px;">
								<option value="0">--请  选   择 --</option>
							</select>
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
						<td class="admin_bgclor_e3f admin_zt_14">
							图纸类型<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">												
							<s:select list="dbBuildingPicTypes"  theme="simple"  name="dbBuildingPic.picType.seq"
							listValue="content1" listKey="seq"
							id="buildingPicType" headerKey="0" headerValue="--请选择--" style="width:155px;"></s:select>
							<span id="subeqty"></span>

						</td>
						<td class="admin_bgclor_e3f admin_zt_14">
							
						</td>
						<td class="admin_bgclor_f1f">
							
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff" >
						<td class="admin_bgclor_e3f admin_zt_14" >
							图片名称
						</td>
						<td class="admin_bgclor_f1f" >
							<input type="text" id="imageFileName" name="picName" maxlength="25"/>
						</td>
						<td class="admin_bgclor_e3f admin_zt_14"> 
							图片选择
						</td>
						<td class="admin_bgclor_f1f">
							<s:file theme="simple" name="image" id="imgFile"></s:file>
<%--							<a onclick="doRemove(this);" class="btn blue">移除</a>--%>
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff" >
						<td class="admin_bgclor_e3f admin_zt_14" >
							图片名称
						</td>
						<td class="admin_bgclor_f1f" >
							<input type="text" id="imageFileName" name="picName"/>
						</td>
						<td class="admin_bgclor_e3f admin_zt_14"> 
							图片选择
						</td>
						<td class="admin_bgclor_f1f">
							<s:file theme="simple" name="image" id="imgFile"></s:file>
<%--							<a onclick="doRemove(this);" class="btn blue">移除</a>--%>
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff" >
						<td class="admin_bgclor_e3f admin_zt_14" >
							图片名称
						</td>
						<td class="admin_bgclor_f1f" >
							<input type="text" id="imageFileName" name="picName"/>
						</td>
						<td class="admin_bgclor_e3f admin_zt_14"> 
							图片选择
						</td>
						<td class="admin_bgclor_f1f">
							<s:file theme="simple" name="image" id="imgFile"></s:file>
<%--							<a onclick="doRemove(this);" class="btn blue">移除</a>--%>
						</td>
					</tr>
				

				</table>
				<table width="770" border="0" class="gai_left_duiqi">
					<tr>
						<td></td>
						<td  width="65"><!--a href="javascript:f_check();" class="btn blue">下一步</a-->	
						<td width="60"><a onclick="uploadCheck()" class="btn blue">上  传</a>
						</td>									
						</td>
						<td  width="65"><a href="javascript:history.go(-1);" class="btn blue">取 消</a>																
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
