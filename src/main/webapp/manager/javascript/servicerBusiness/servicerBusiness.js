	
$(document).ready(function(){
	});

//定义下拉菜单模板
var typeList_tmpl = function() {
	return '<option value="${seq }">${content1 }</option>';
}
		
//	var getTypeList = function(serTy){
//		$.ajax({
//		  type: "POST",
//		  url: "serviceAction_findServiceTypeList.action",
//		  dataType: "json",
//		  cache: false,
//		  async:false,
//		  error: function(jsonObj){  
//			 alert("操作失败!数据异常!");
//		  },
//		  success: function(jsonObj){
//			  var json_typeList = jsonObj.typeList;
//			  $("#serType").empty();
//			  $("#serType").html('<option value=""> --请选择服务商类型--</option>');
//			  $.tmpl(typeList_tmpl(), json_typeList).appendTo("#serType");
//			  $("#serType").val(serTy);
//			}
//		});
//	};
//	
	
	
	
	var validateIsUnique = function(){
		var serName = $("#serName").val();
		if(!serName||$.trim(serName)==''||serName==null){
			alert("请填写服务商名称");
			return;
		}
		$.ajax({
		  type: "POST",
		  url: "serviceAction_validateIsUnique.action",
		  dataType: "json",
		  data:{
			serName:serName
			},
		  cache: false,
		  async:false,
		  error: function(jsonObj){  
			 alert("验证失败!");
		  },
		  success: function(jsonObj){
			  var isUnique = jsonObj.isUnique;
			  if(isUnique=="true"||isUnique==true){
			  }else{
				  alert("该服务商名字已存在!");
				 $("#serName").val('');
				 $("#serName").focus();
			  }
			}
		});
	};
	
	
	//更新
	var editService = function(){
		var serName = $("#serName").val();
		var serType = $("#serType").val();
		var mobitele = $("#mobitele").val();//服务手机
		var tele = $("#tele").val();//服务固话
		if(!serName||$.trim(serName)==''||serName==null){
			alert("请填写服务商名称");
			return;
		}
		if(!serType||serType==''||serType==null){
			alert("请选择服务商类型");
			return;
		}
		if(mobitele&&$.trim(mobitele)!=''&&mobitele!=null){
			var regPhone=/[1][3-9][0-9]{9,9}/;//验证手机号码
			if(!regPhone.test(mobitele)){
				alert("请输入正确的手机号码");
				 $("#mobitele").val('');
				 $("#mobitele").focus();
				return;
			}
			
		}
		if(tele&&$.trim(tele)!=''&&tele!=null){
			var regTel = /^(\d){3,4}(\d){7,8}$/;//验证固话
			if(!regTel.test(tele)){
				alert("请输入正确的固话号");
				 $("#tele").val('');
				 $("#tele").focus();
				return;
			}
			
		}
		var editFlag = $("#editFlag").val();
		if(editFlag=="update"){
			$("#subForm").attr("action","serviceAction_updateService.action");
		}else{
			$("#subForm").attr("action","serviceAction_addService.action");
		}
		$("#subForm").submit();
	}
