var deleteflag;
$(document).ready(function(){
	initDataFun();
	});
function CurentTime() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分

    var clock = year + "-";

    if (month < 10)
        clock += "0";

    clock += month + "-";

    if (day < 10)
        clock += "0";

    clock += day + " ";

    if (hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm;
    return (clock);
}



	var  getJsonLength = function(json){
	        var cc = 0;
	        for(var i in json)
	        {
	            cc++;
	        }
	        return cc;
	}
	

	var initDataFun = function(){
		//var mydata = window.dialogArguments;
		try{
	   
		var seq = $("#sequence").val();
			if(!seq||seq==""){
				alert("程序异常!");
			return;
		        }
		$.ajax({
		  type: "POST",
		  url: "alarm_findAlarmAboutInfo.action?id=" + seq,
		  dataType: "json",
		  cache: false,
		  async:false,
		  error: function(jsonObj){  
			 alert("操作失败!数据异常!");
		  },
		  success: function(jsonObj){
			  if(jsonObj&&jsonObj!=""){
			deleteflag = jsonObj.deleteflag;
			}}})
			var action = $("#action").val();
			var seq = $("#sequence").val();
			var startTime1 = $("#startTime1").val();
 			$("#myRemarksform").attr("action",action);
		    $("#seq").val(seq);
		    $("#startTime").text(startTime1);
		    if(action=="alarm_removeAlarm"){
				//判断是否显示清除按钮
				if("1"==deleteflag){
					$("#deleteflagIDid").show();
					$("#remarks").val('');
					$("#btn").text("消除");
					$("#alarmRemovePerson").show();
				}
				if("0"==deleteflag){
					$("#deleteflagIDid").hide(); 
					$("#remarks").val('');
					$("#btn").text("消除");
					$("#btn").css("backgroundColor",'#ccc');
					$("#btn").removeAttr("onclick");
					$("#btn").mouseenter(function(){
					    //鼠标移入
						    var top = "68%";
						    var left ="12%";
					$("#divid").css( { position : 'absolute', 'top' : top , left : left } ).show();
					$("#ansp").text("本次告警设备未恢复正常不可消除!");
					}).mouseleave(function(){
					    //鼠标移出
					});
					
					$("#alarmRemovePerson").show();
				}
		}else{
			$("#alarmRemovePerson").hide();
			$("#btn").text("保存");
			$("#btndiv").hide();
		}
		getRemarks(seq,action);
			}catch(e){
			}
		}
		
	
		
	var getRemarks = function(seq,action){
	var normalTime = $("#normalTime").val();
		if(!seq||seq==""){
			alert("程序异常!");
		return;
	        }
	$.ajax({
	  type: "POST",
	  url: "alarm_findAlarmAboutInfo.action?id=" + seq,
	  dataType: "json",
	  cache: false,
	  async:false,
	  error: function(jsonObj){  
		 alert("操作失败!数据异常!");
	  },
	  success: function(jsonObj){
		  if(jsonObj&&jsonObj!=""){
		var equipName = jsonObj.equipName;
		var place= jsonObj.place;
		var updatDate = jsonObj.updateDate;
		var name= jsonObj.name;
	    var remarks = jsonObj.remarks;
	    var deleteflag = jsonObj.deleteflag;
	    remarks = eval(remarks);
		$("#place").text(place);
		$("#pointName").text(name);
		$("#equipName").text(equipName);
		$("#normalTime").val(updatDate);
		//$("#deleteflag").val(deleteflag);
		if(action!="alarm_removeAlarm"){
		    $("#monitor").val(remarks.monitor);
		    $("#repairPerson").val(remarks.repairPerson);
			$("#noteTime").val(remarks.noteTime);
			$("#alarmElimit").val(remarks.alarmElimit);
			$("#reason").val(remarks.reason);
			$("#feedBack").val(remarks.feedBack);
			$("#material").val(remarks.material);
			$("#cost").val(remarks.cost);
			$("#normalTime").val(remarks.normalTime);
			var otherDes = remarks.otherDes;
			var strArr = new Array();
			otherDes = eval(otherDes);
			var otherDesLength = getJsonLength(otherDes);
			otherDesLength = parseInt(otherDesLength/2)+parseInt(otherDesLength%2);
			if(otherDesLength>0){
				for(var a = 1;a<=otherDesLength;a++){
					var track = "trackTime"+a;
					var feedBack = "trackFeedBack"+a;
					var trackTime = remarks.otherDes[track];
					var feedBackDes = remarks.otherDes[feedBack];
					if(trackTime == undefined||trackTime == 'undefined'){
						trackTime ='';
					}
					if(feedBackDes == undefined||feedBackDes == 'undefined'){
						feedBackDes ='';
					}
					if(a==1){
							$("#"+feedBack).val(feedBackDes);
							$("#"+track).val(trackTime);
					}else{
						strArr.push('<tr>');
						strArr.push('	<td>');
						strArr.push('		跟踪时间：');
						strArr.push('	</td>');
						strArr.push('	<td>');
						strArr.push('	    <input class="Wdate" type="text" id="trackTime'+a+'" name="trackTime'+a+'" value="'+trackTime+'" style="width: 150px;" readonly="readonly" onfocus="WdatePicker({skin:\'whyGreen\',maxDate:CurentTime(),dateFmt:\'yyyy/MM/dd HH:mm\'})" />');
						strArr.push('	</td>');
						strArr.push('</tr>');
						strArr.push('<tr>');
						strArr.push('	<td>');
						strArr.push('		  跟踪反馈情况：  ');
						strArr.push('  </td>');
					    strArr.push('	<td>');
					    strArr.push('		<input name="trackFeedBack'+a+'" value="'+feedBackDes+'"/>');
						strArr.push('	</td>');
						strArr.push('</tr>');
					}
				}
				$("#otherDesTable").append(strArr.join(""));
				  }	
		}
			  }
		  }
		});
	};
		
    /**
	 * 更新备注/消除告警
	 */
	var updateRe=function(){
		console.debug("我进来的。。。。。"+deleteflag);
		
		/*if("0"==deleteflag){
			return false;
		}*/
		var action = $("#myRemarksform").attr("action");
		var arr = new Array();
		var inputArr = $("#updateTable  tr > td > input[type=text]");
		var flag = 0;
		var oper = "";
		var txtArea =  $("#updateTable  tr > td > textarea");
		var reasonTxt = $("#"+txtArea.attr("id")).val();
		
		if(reasonTxt!=''&&reasonTxt&&reasonTxt.length>100){
			alert("报警原因请限制在100字以内!");
			return ;
		}
		arr.push(txtArea.attr("name")+":\""+reasonTxt+"\"");
		for(var i = 0;i<inputArr.length;i++){
			var idVal = $("#"+$(inputArr[i]).attr("id")).val();
			if("monitor"==$(inputArr[i]).attr("id")&&idVal==''&&!idVal){
				alert("请输入监视人!");
				return ;
			}
			
			if(action=="alarm_removeAlarm"&&"alarmElimit"==$(inputArr[i]).attr("id")){
				oper = $("#"+$(inputArr[i]).attr("id")).val();
				break;
			}
			if(idVal&&idVal!=''){
				arr.push($(inputArr[i]).attr("name")+":\""+$("#"+$(inputArr[i]).attr("id")).val()+"\"");
			}
		}
		var desTableInputArr = $("#otherDesTable tr > td > input[type=text]");
		var desData = new Array();
		if(desTableInputArr.length>0){
			for(var i = 0;i<desTableInputArr.length;i=i+2){
				var idVal2 =$("#"+$(desTableInputArr[i]).attr("id")).val() ;
				desData.push($(desTableInputArr[i]).attr("name")+":\""+idVal2+"\"");
				var idVal1 =$("#"+$(desTableInputArr[i+1]).attr("id")).val() ;
				desData.push($(desTableInputArr[i+1]).attr("name")+":\""+idVal1+"\"");
			}
			arr.push("otherDes:"+"{"+desData.join(",")+"}");
		}
	
		var remarks = "{"+arr.join(",")+"}";
		
		var seq = $("#seq").val();
		 $.ajax({
			  type: "POST",
			  url: action,
			  data:{
			  		remarks:remarks,
			  		seq:seq,
			  		oper:oper
				 },
			  dataType: "text/html;charset=UTF-8",
			  aysnc:false,
			  error: function(mydate){ 
				 alert("操作失败!数据异常!");
			  },
			  success: function(){
				  
				  
					  //调用父窗口
					  var parent= window.opener;
			          // parent.location.reload(); 
			            parent.vodereturnValue();
					 //window.returnValue = "ok";
					 reBack();
				  
				      
			  }
		});
		};
	
	//添加跟踪过程备注
	var addOtherDes = function(){
		var otherDesLength = $("#otherDesTable tr").length;
		
		var strArr = new Array();
		strArr.push('<tr>');
		strArr.push('	<td>');
		strArr.push('		跟踪时间：');
		strArr.push('	</td>');
		strArr.push('	<td>');
		strArr.push('		<input class="Wdate" type="text" id="trackTime'+(otherDesLength/2+1)+'" name="trackTime'+(otherDesLength/2+1)+'" style="width: 150px;" readonly="readonly" onfocus="WdatePicker({skin:\'whyGreen\',maxDate:CurentTime(),dateFmt:\'yyyy/MM/dd HH:mm\'})" />');
		strArr.push('	</td>');
		strArr.push('</tr>');
		strArr.push('<tr>');
		strArr.push('	<td>');
		strArr.push('		  跟踪反馈情况：  ');
		strArr.push('  </td>');
	    strArr.push('	<td>');
	    strArr.push('		<input  type="text" id="trackFeedBack'+(otherDesLength/2+1)+'" name="trackFeedBack'+(otherDesLength/2+1)+'"/>');
		strArr.push('	</td>');
		strArr.push('</tr>');
		$("#otherDesTable").append(strArr.join(""));
	}
	//删除跟踪过程备注
	var removeOtherDes = function(){
		var otherDesChildLength = $("#otherDesTable tr").length;
		if(otherDesChildLength>2){
			$("#otherDesTable tr:last-child").remove(); 
			$("#otherDesTable tr:last-child").remove();
		}
	}
	var reBack=function(){
		window.close();
	}
			
