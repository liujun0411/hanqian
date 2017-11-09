	
$(document).ready(function(){
	
	appendClassForDivTr();
});


var appendClassForDivTr = function(){
	$("#div_all tr").click(function(){
		$("#div_all tr").addClass("noSelectTr").removeClass("selectTr");
		$(this).addClass("selectTr").removeClass("noSelectTr");
	});
	$("#div_already tr").click(function(){
		$("#div_already tr").addClass("noSelectTr").removeClass("selectTr");
		$(this).addClass("selectTr").removeClass("noSelectTr");
	});
}
var alreayDivText = function(alreayValues){
	var mains = alreayValues.split(",");
	var divAllTr = $("#div_all tr");
	var allMains = new Array();
	var divAlreadyStr = new Array();
	divAlreadyStr.push("<table width='100%'>")
	for(var i = 0 ;i<divAllTr.length;i++){
		for(var j = 0;j<mains.length;j++){
			if($(divAllTr[i]).attr("id")=="mid_"+mains[j]){
				divAlreadyStr.push("<tr id='al_"+mains[j]+"' style='cursor: pointer;color:#3399FF;' onmouseout='javascript:this.style.color=\"#3399FF\";' onmouseover='javascript:this.style.color=\"#FF6600\";'  ondblclick='fromRightToLeft("+mains[j]+")'>");
				divAlreadyStr.push(" <td>");
				divAlreadyStr.push($("#"+$(divAllTr[i]).attr("id")).text());
				divAlreadyStr.push(" </td>");
				divAlreadyStr.push("</tr>");
				$(divAllTr[i]).remove();
			}
		}
		
	}
	divAlreadyStr.push("</table>");
	$("#div_already").append(divAlreadyStr.join(""));
	appendClassForDivTr();
}
	//更新
	var editMen = function(){
		var userSeq = new Array(); 
		//获取班组ID
		//获取选择的ID
		   $('#roleId1 option').each(function(i){
	           userSeq[i]=$(this).val();
	        })
		$("#classId").val(userSeq);
		var memName = $("#memName").val();
		var memSer = $("#memSer").val();
		var mobitele = $("#mobitele").val();//服务手机
		var tele = $("#tele").val();//服务固话
		if(!memName||$.trim(memName)==''||memName==null){
			alert("请维修人员名称");
			 $("#memName").focus();
			return;
		}
		if(!memSer||memSer==''||memSer==null){
			alert("请选择服务商");
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
			
		}else{
			alert("请输入手机号码");
			 $("#mobitele").val('');
			 $("#mobitele").focus();
			return;
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
			$("#subForm").attr("action","sermainDetail_updateMen.action");
		}else{
			$("#subForm").attr("action","sermainDetail_insertMen.action");
		}
		$("#subForm").submit();
	}

	
	
	var fromLeftToRight = function(id){
		var leftId = "mid_"+id;
		var rigthId = "al_"+id;
		
		var allMains = new Array();
		var trAlreadyStr = new Array();
		trAlreadyStr.push("<tr id='"+rigthId+"' style='cursor: pointer;color:#3399FF;' onmouseover='this.style.color=\"#FF6600\";' onmouseout='this.style.color=\"#3399FF\";' ondblclick='fromRightToLeft("+id+")' >");
		trAlreadyStr.push(" <td>");
		trAlreadyStr.push($("#"+leftId).text());
		trAlreadyStr.push(" </td>");
		trAlreadyStr.push("</tr>");
		$("#"+leftId).remove();
		$("#div_already table").append(trAlreadyStr.join(""));
		appendClassForDivTr();
		
	}
	var fromRightToLeft = function(id){
		var leftId = "mid_"+id;
		var rigthId = "al_"+id;
		
		var allMains = new Array();
		var trAlreadyStr = new Array();
		trAlreadyStr.push("<tr id='"+leftId+"' style='cursor: pointer;color:#3399FF;' onmouseover='this.style.color=\"#FF6600\";' onmouseout='this.style.color=\"#3399FF\";' ondblclick='fromLeftToRight("+id+")'>");
		trAlreadyStr.push(" <td>");
		trAlreadyStr.push($("#"+rigthId).text());
		trAlreadyStr.push(" </td>");
		trAlreadyStr.push("</tr>");
		$("#"+rigthId).remove();
		$("#div_all table").append(trAlreadyStr.join(""));
		appendClassForDivTr();
	}
	
	
	var changeLeftToRight = function(){
		var allMainTr =	$("#div_all table tr");
		var flag = 0;
		for(var i = 0 ;i<allMainTr.length;i++){
			if($(allMainTr[i]).attr("class")=="selectTr"){
				fromLeftToRight($(allMainTr[i]).attr("id").split("_")[1]);
				break;
			}else{
				flag++;
				if(flag==allMainTr.length){
					alert("请选中需分配的班组");
					break;
				}
			}
		}
	}
	var changeRightToLeft = function(){
		var alreadyTr =	$("#div_already table tr");
		var flag = 0;
		if(alreadyTr&&alreadyTr.length>0){
			for(var i = 0 ;i<alreadyTr.length;i++){
				if($(alreadyTr[i]).attr("class")=="selectTr"){
					fromRightToLeft($(alreadyTr[i]).attr("id").split("_")[1]);
					break;
				}else{
					flag++;
					if(flag==alreadyTr.length){
						alert("请选中需移出的班组");
						break;
					}
				}
			}
		}else{
			alert("请选中需移出的班组");
		}
		
	}