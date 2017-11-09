	
//初始化楼宇
//楼宇combobox
//定义楼宇下拉菜单模板
var buildList_tmpl = function() {
	return '<option value="${buildId}">${buildName}</option>';
}

//定义楼宇下拉菜单模版（以name为值）
var buildNameList_tmpl = function() {
	return '<option value="${buildName}">${buildName}</option>';
}

var json_buildList = null;
/**
 * buildObjId楼宇combobox的id
 * storeyObjId楼层文本框的id
 */
	var initDataFun = function(buildObjId,storeyObjId,selectBuildId,nameOrId){
		var buildObj =  $("#"+buildObjId);
		$.ajax({
		      type: "POST",
			  url: "equipment_getBuildingInfoList.action",
			  cache:false,
			  dataType: 'json',
			  success: function(jsonObj){
				json_buildList = jsonObj.builds;
			    buildObj.empty();
			    buildObj.html('<option value="0"> --请选择楼宇--</option>');
			    if(nameOrId==""||nameOrId==undefined||!nameOrId||nameOrId==null||nameOrId=='undefined'){
			    	$.tmpl(buildList_tmpl(), json_buildList).appendTo("#"+buildObjId);
			    	$("#"+buildObjId).val(selectBuildId);
					getStoreyByBuildId(buildObjId,storeyObjId,$("#"+storeyObjId).val(),'','',true);
			    }else{
			    	$.tmpl(buildNameList_tmpl(), json_buildList).appendTo("#"+buildObjId);
			    	$("#"+buildObjId).val(selectBuildId);
					getStoreyByBuildId(buildObjId,storeyObjId,$("#"+storeyObjId).val(),'',nameOrId,true);
			    }
				
			  },
			  error:function(textStatus){
			      alert('操作过于频繁,请稍后再试!');
			  }
		 });
		
	}
	
    //楼层combobox
	/**
	 * buildObjId楼宇combobox的id
	 * storeyObjId楼层文本框的id
	 */
	var getStoreyByBuildId = function(buildObjId,storeyObjId,selectStorey,onchangefunc,nameOrId,isLoad){
		
		var buildObj =  $("#"+buildObjId);
		var storeyObj = $("#"+storeyObjId);
		if(!storeyObj||storeyObj==null||storeyObj.length<1){
			return;
		}
		var tagName = storeyObj.attr("tagName").toLowerCase();
		var inputStoreyObj = null;
		if(tagName=="input"){
			storeyObj.attr("name","input"+storeyObjId);
			inputStoreyObj = storeyObj.attr("id","input"+storeyObjId);
			inputStoreyObj.val('');
			inputStoreyObj.hide();
		}else{
			selectStorey =  "";
			var ipt = $("#input"+storeyObjId);
    	    if(ipt&&ipt!=null&&ipt!=''){
    	       ipt.val('');
    		   ipt.hide();
    	    }
			storeyObj.hide();
		}
		if(isLoad!=true){
			selectStorey = "";
		}
		if(selectStorey&&selectStorey!=""&&selectStorey!=null&&selectStorey!=undefined&&selectStorey!='undefined'&&$.trim(selectStorey)!=''){
			selectStorey = parseInt(selectStorey);
		}
		var buildId = buildObj.val();
		
		if($.trim(buildId)!=""&&$.trim(buildId)!="0"){
   			//创建楼层的combobox
    	   var storeyArr = new Array();
    	   if(onchangefunc&&onchangefunc!=null){
    		   if(nameOrId==""||nameOrId==undefined||!nameOrId||nameOrId==null||nameOrId=='undefined'){
    			   storeyArr.push("<select id='"+storeyObjId+"' name='"+storeyObjId+"' style='width:155px;' onchange='"+onchangefunc+"()'>");
    		   }else{
    			   storeyArr.push("<select id='"+storeyObjId+"' name='"+storeyObjId+"' style='width:125px;' onchange='"+onchangefunc+"()'>");
    		   }
    		}else{
    		   if(nameOrId==""||nameOrId==undefined||!nameOrId||nameOrId==null||nameOrId=='undefined'){
    			   storeyArr.push("<select id='"+storeyObjId+"' name='"+storeyObjId+"' style='width:155px;'>");
    		   }else{
    			   storeyArr.push("<select id='"+storeyObjId+"' name='"+storeyObjId+"' style='width:125px;'>");
    		   }
    	   }
    	   storeyArr.push("  <option value='0'>");
    	   storeyArr.push("  --请选择楼层--");
    	   storeyArr.push("   </option>");
        	   
    	   
    	   for(var i = 0;i<json_buildList.length;i++){
    		   var buildName = json_buildList[i].buildName;
    		   var build_id = json_buildList[i].buildId;
    		   var storeyDown = json_buildList[i].storeyDown;
    		   var storeyUp = json_buildList[i].storeyUp;
    		   var downUpArr = new Array();
    		   if(nameOrId==""||nameOrId==undefined||!nameOrId||nameOrId==null||nameOrId=='undefined'){
    			     if(build_id==buildId){
        			   var wd = parseInt(parseInt(storeyUp)+1);
        			   if(selectStorey&&selectStorey!=null&&selectStorey!=''&&selectStorey==wd){
        				   downUpArr.push("<option selected='selected' value='"+wd+"'>屋顶</option>");
        			   }else{
        				   downUpArr.push("<option value='"+wd+"'>屋顶</option>");
        			   }
        			   for(var up = storeyUp;up>=1;up--){
        				   if(selectStorey&&selectStorey!=null&&selectStorey!=''&&selectStorey==up){
        					   downUpArr.push("<option selected='selected' value='"+up+"'>"+up+"</option>");
        				   }else{
        					   downUpArr.push("<option  value='"+up+"'>"+up+"</option>");
        				   }
        			   }
        			   for(var down = 1;down<=storeyDown;down++){
        				   var downVal = parseInt("-"+down);
        				   if(selectStorey&&selectStorey!=null&&selectStorey!=''&&selectStorey==downVal){
        					   downUpArr.push("<option selected='selected' value='"+downVal+"'>B"+down+"</option>");
        				   }else{
        					   downUpArr.push("<option value='"+downVal+"'>B"+down+"</option>");
        				   }
        			   }
        			   storeyArr.push(downUpArr.join(""));
        			   break;
        		   }
    			 }else{
    				 if(buildName==buildId){
    	    			   var wd = parseInt(parseInt(storeyUp)+1);
    	    			   if(selectStorey&&selectStorey!=null&&selectStorey!=''&&selectStorey==wd){
    	    				   downUpArr.push("<option selected='selected' value='"+wd+"'>屋顶</option>");
    	    			   }else{
    	    				   downUpArr.push("<option value='"+wd+"'>屋顶</option>");
    	    			   }
    	    			   for(var up = storeyUp;up>=1;up--){
    	    				   if(selectStorey&&selectStorey!=null&&selectStorey!=''&&selectStorey==up){
    	    					   downUpArr.push("<option selected='selected' value='"+up+"'>"+up+"</option>");
    	    				   }else{
    	    					   downUpArr.push("<option  value='"+up+"'>"+up+"</option>");
    	    				   }
    	    			   }
    	    			   for(var down = 1;down<=storeyDown;down++){
    	    				   var downVal = parseInt("-"+down);
    	    				   if(selectStorey&&selectStorey!=null&&selectStorey!=''&&selectStorey==downVal){
    	    					   downUpArr.push("<option selected='selected' value='"+downVal+"'>B"+down+"</option>");
    	    				   }else{
    	    					   downUpArr.push("<option value='"+downVal+"'>B"+down+"</option>");
    	    				   }
    	    			   }
    	    			   storeyArr.push(downUpArr.join(""));
    	    			   break;
    	    		   }
    			 }
    		  
    	   }
    	   storeyArr.push("</select>");
    	   if(inputStoreyObj==null||inputStoreyObj==''){
    		   storeyObj.after(storeyArr.join(""));
    		   storeyObj.remove();
    	   }else{
	    	   inputStoreyObj.after(storeyArr.join(""));
	    	   inputStoreyObj.hide();
    	   }
    	   $("#"+storeyObjId).show();
       }else{
    	   if(tagName=="input"){
    		   storeyObj.val('');
    		   storeyObj.hide();
	   		}else{
	   			storeyObj.remove();
	   		}
    	   
    	   var ipt = $("#input"+storeyObjId);
    	   if(ipt&&ipt!=null&&ipt!=''){
    		   ipt.show();
    		   ipt.attr("name",storeyObjId);
    		   ipt.attr("id",storeyObjId);
    		   ipt.val(selectStorey);
    	   }else{
    		   $("#"+storeyObjId).val('');
    		   $("#"+storeyObjId).hide();
    	   }
    	   if(inputStoreyObj&&inputStoreyObj!=null&&inputStoreyObj!=''){
    		   inputStoreyObj.show();
    	   }
    	  
       }
	}
	
