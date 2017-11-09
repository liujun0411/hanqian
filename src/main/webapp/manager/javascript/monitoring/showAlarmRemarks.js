	
$(document).ready(function(){
	initDataFun();
	});

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
			//var seq = mydata[0];
			//var startTime = mydata[1];
			var seq = $("#action").val();
			var startTime = $("#sequence").val();
			//var startTime = $("#startTime1").val();
		$("#seq").val(seq);
//		$("#startTime").text(startTime);
		$("#startTime").val(startTime);
		getRemarks(seq);
		}catch(e){
		}
	}
		
	
		
	var getRemarks = function(seq){
		if(!seq||seq==""){
			alert("程序异常!");
		return;
	}
	$.ajax({
	  type: "POST",
	  url: "alarm_findHisAlarmAboutInfo.action?id=" + seq,
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
		var name= jsonObj.name;
	    var remarks = jsonObj.remarks;
	    remarks = eval(remarks);
		$("#place").text(place);
		$("#pointName").text(name);
		$("#equipName").text(equipName);
//	    $("#place").val(place);
//		$("#pointName").val(name);
//		$("#equipName").val(equipName);
	    $("#monitor").val(remarks.monitor);
	    $("#repairPerson").val(remarks.repairPerson);
		$("#noteTime").val(remarks.noteTime);
//		$("#alarmElimit").val(remarks.alarmElimit);
		$("#alarmElimit").val(jsonObj.oper);
		$("#reason").val(remarks.reason);
		$("#feedBack").val(remarks.feedBack);
		$("#material").val(remarks.material);
		$("#cost").val(remarks.cost);
		$("#normalTime").val(remarks.normalTime);
//		  $("#monitor").text(remarks.monitor);
//		    $("#repairPerson").text(remarks.repairPerson);
//			$("#noteTime").text(remarks.noteTime);
//			$("#alarmElimit").text(remarks.alarmElimit);
//			$("#reason").text(remarks.reason);
//			$("#feedBack").text(remarks.feedBack);
//			$("#material").text(remarks.material);
//			$("#cost").text(remarks.cost);
//			$("#normalTime").text(remarks.normalTime);
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
//						$("#"+feedBack).text(feedBackDes);
//						$("#"+track).text(trackTime);
				}else{
					strArr.push('<tr>');
					strArr.push('	<td>');
					strArr.push('		跟踪时间：');
					strArr.push('	</td>');
					strArr.push('	<td>');
					strArr.push('		<input readonly="readonly" onfocus="this.blur()" class="spanOther" name="trackTime'+a+'" value="'+trackTime+'"/>');
//					strArr.push('		<span class="spanOther" id="trackTime'+a+'" name="trackTime'+a+'" value="'+trackTime+'"/>');
					strArr.push('	</td>');
					strArr.push('</tr>');
					strArr.push('<tr>');
					strArr.push('	<td>');
					strArr.push('		  跟踪反馈情况：  ');
					strArr.push('  </td>');
				    strArr.push('	<td>');
				    strArr.push('		<input readonly="readonly" onfocus="this.blur()" class="spanOther" name="trackFeedBack'+a+'" value="'+feedBackDes+'"/>');
//				    strArr.push('		<span class="spanOther" name="trackFeedBack'+a+'" value="'+feedBackDes+'"/>');
					strArr.push('	</td>');
					strArr.push('</tr>');
				}
			}
			$("#otherDesTable").append(strArr.join(""));
				  }	   
			  }
		  }
		});
	};
		
	
	var reBack=function(){
		window.close();
	}
			
