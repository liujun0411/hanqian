function creatediv(obj){
	if($("#listDiv_List").length==0){
	    var _left = parseInt($(obj).offset().left - 75);
		var _top = parseInt($(obj).offset().top + 5);
		var _width = parseInt($(obj).width())/2;
		var _height = parseInt($(obj).height());
	    var str = new Array();
		var _divWidth = _width*2+5;
	    str.push('<div id="listDiv_List"');
		str.push('style="background-color:white; border:1px solid #0193ce;width:'+_divWidth+'px;height:300px;position:absolute;left:'+(_left+_width+1)+'px;top:'+(_top+_height+2)+'px;">');
	    str.push('<div style="height:275px; overflow:auto;"><ul id="tree1">');
	    str.push('</ul></div>');
	    //str.push('<div style="height:22px; width:100%; text-align:right;position:absolute; float:right;"><img src="manager/common/equip/images/pic/cz.gif" onclick="doReset();"  style=" margin-top:2px; cursor:pointer;" />&nbsp;<img src="manager/common/equip/images/pic/Close.jpg" style=" margin-top:2px; margin-right:3px; cursor:pointer;" onclick="doClose();" /></div></div>');
	    str.push('<div style="height:22px; width:100%; text-align:right;position:absolute; float:right;vertical-align:middle;"><input type="button" id="btnReset" onclick="doReset()" value="重置" style="border:1px solid #0193ce; height:20px; color:#0193ce; margin-right:2px; margin-top:2px; " /><input type="button" id="btnReset" onclick="doClose()" value="关闭" style="border:1px solid #0193ce; height:20px; color:#0193ce; margin-right:2px; margin-top:2px;" /></div></div>');
	    $("body").append(str.join(""));
	    $("#tree1").ligerTree({ url: 'manager/common/equip/json/prov4.json',checkbox: false,onClick:doClick});
	   }else{
		 if($("#listDiv_List").is(":hidden")){
			 $("#listDiv_List").show();
		 }else{
			 $("#listDiv_List").hide();
		 }
     }
}

//重置按钮
function doReset(){
	$('#equipTypeId').val('');
    $('#eqTypeId').val('');
    $("#listDiv_List").hide();
}

//关闭按钮
function doClose(){
	$("#listDiv_List").hide();
}