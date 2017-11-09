function createdivs(obj,cobj,eqNameObj){
	if($("#listDiv_Lists"+obj).length==0){
	    var _left = parseInt($("#"+obj).offset().left - 75);
		var _top = parseInt($("#"+obj).offset().top + 5);
		var _width = parseInt($("#"+obj).width())/2;
		var _height = parseInt($("#"+obj).height());
	    var str = new Array();
		var _divWidth = _width*2+5;
	    str.push('<div id="listDiv_Lists'+obj+'"');
		str.push('style="background-color:white; border:1px solid #0193ce;width:'+_divWidth+'px;height:300px;position:absolute;left:'+(_left+_width+1)+'px;top:'+(_top+_height+2)+'px;">');
	    str.push('<div style="height:275px; overflow:auto;"><ul id="tree2'+obj+'">');
	    str.push('</ul></div>');
	    //str.push('<div style="height:22px; width:100%; text-align:right;position:absolute; float:right;"><img src="manager/common/equip/images/pic/cz.gif" onclick="doReset();"  style=" margin-top:2px; cursor:pointer;" />&nbsp;<img src="manager/common/equip/images/pic/Close.jpg" style=" margin-top:2px; margin-right:3px; cursor:pointer;" onclick="doClose();" /></div></div>');
	    str.push('<div style="height:22px; width:100%; text-align:right;position:absolute; float:right;vertical-align:middle;"><input type="button" id="btnReset" onclick="doResets(\''+obj+'\',\''+cobj+'\')" value="重置" style="border:1px solid #0193ce; height:20px; color:#0193ce; margin-right:2px; margin-top:2px; " /><input type="button" id="btnReset" onclick="doCloses(\''+obj+'\')" value="关闭" style="border:1px solid #0193ce; height:20px; color:#0193ce; margin-right:2px; margin-top:2px;" /></div></div>');
	    $("body").append(str.join(""));
	    $("#tree2"+obj).ligerTree({ url: 'manager/common/equip/json/prov4.json',checkbox: false,onClick:function(n){doClicks(n,obj,cobj,eqNameObj);}});
	   }else{
		 if($("#listDiv_Lists"+obj).is(":hidden" )){
			 $("#listDiv_Lists"+obj).show();
		 }else{
			 $("#listDiv_Lists"+obj).hide();
		 }
     }
}

//重置按钮
function doResets(obj,cobj){
	$('#'+obj).val('');
    $('#'+cobj).val('');
    $("#listDiv_Lists"+obj).hide();
}

//关闭按钮
function doCloses(obj){
	$("#listDiv_Lists"+obj).hide();
}