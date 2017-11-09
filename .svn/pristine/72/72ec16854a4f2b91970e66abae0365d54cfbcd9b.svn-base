	
var showPic = function(picname,flag){
	//picUrl
	var url = picname;
	$.ajax({
		  type: "POST",
		  url: "equipGroupAction!validPicIsExist?url=" + url,
		  dataType: "json",
		  cache: false,
		  async:false,
		  error: function(jsonObj){  
			 alert("操作失败!数据异常!");
		  },
		  success: function(jsonObj){
			  if(jsonObj.resStatus){
				 var sheight = screen.height-70;
			     var swidth = screen.width-10;
			     var winoption="left=0,top=0,height="+sheight+",width="+swidth+",toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
			     //var tmp=window.open("manager/images/groupPic/"+url,'',winoption);
			     var tmp=window.open("equipGroupAction!pic?pic="+url,'',winoption);
			  }else{
				  alert('该组未提供设备安装图!');
			  }
		  }
	});
}

var showEquipPic = function(picname){
	var url = equipUrl+picname;
	$.ajax({
		  type: "POST",
		  url: "equipGroupAction!validPicIsExist?url=" + url,
		  dataType: "json",
		  cache: false,
		  async:false,
		  error: function(jsonObj){  
			 alert("操作失败!数据异常!");
		  },
		  success: function(jsonObj){
			  if(jsonObj.resStatus){
				 var sheight = screen.height-70;
			     var swidth = screen.width-10;
			     var winoption="left=0,top=0,height="+sheight+",width="+swidth+",toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
			     var tmp=window.open(url,'',winoption);
			  }else{
				  alert('该组未提供设备安装图!');
			  }
		  }
	});
	
	
}
var getPicNameById = function(groupId){
	if(groupId==''||groupId==null){
		return;
	}
	$.ajax({
		  type: "POST",
		  url: "equipGroupAction!findPicNameByGroupId.action?groupId=" + groupId,
		  dataType: "json",
		  cache: false,
		  async:false,
		  error: function(jsonObj){  
			 alert("操作失败!数据异常!");
		  },
		  success: function(jsonObj){
			  var picName = jsonObj.picName;
			  if(picName==''||picName==null){
				  alert('该组未提供设备安装图');
			  }else{
				  showPic(picName);
			  }
			 
		  }
	});
}

function getPicNameByEquip(equipId){
	if(equipId==''||equipId==null){
		return;
	}
	$.ajax({
		  type: "POST",
		  url: "equipGroupAction!findPicNameByEquipId.action?equipId=" + equipId,
		  dataType: "json",
		  cache: false,
		  async:false,
		  error: function(jsonObj){  
			 alert("操作失败!数据异常!");
		  },
		  success: function(jsonObj){
			  var picName = jsonObj.picName;
			  if(picName==''||picName==null){
				  alert('该设备未提供设备安装图');
			  }else{
				  showEquipPic(picName);
			  }
		  }
	});
}
			
