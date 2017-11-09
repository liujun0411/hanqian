//设备类型未选择时，选择设备名称，给予提示信息 
function showTypeInfo(obj){
			  var errorInfo = $(obj).parent().find('.error');
			  var typeInfo = $(obj).parent().parent().find("td").eq(1).find("input").eq(0).val();
			  if(typeInfo==null||typeInfo==""||typeInfo==undefined){
				  if(errorInfo.length<1){
				 	 $(obj).parent().append("<font color='red' class='error'>当前设备类型不能为空!</font>");
				  }
			  }else{
				  errorInfo.remove();
			  }
		  }
			
//设备类型改变事件
function changeEquipType(cobj,eqnameId){
	//设备名字
    /*var buildId=$('#build').val(); //楼宇
    var storey=$('#storey').val();  //楼层
    var place=$('#place').val();  //安装位置
    var unitType=$('#unitType').val();   //型号*/
    var equipType=$('#'+cobj).val();   //设备类型
    $("#"+eqnameId+" option").remove();
    //if(jQuery.trim(buildId)!=""){
       var dt={"eqTypeId":equipType};
       $.ajax({
		      type: 'post',
			  url: "equipment_findEquipListByParam.action",
			  data: dt,
			  cache:false,
			  dataType: 'json',
			  success: function(data){
			     $("#"+eqnameId+" option").remove();
			     $("#"+eqnameId).append("<option value=''>--请选择--</option>");
			     var dt=eval(data);
			     for(var i=0;i<dt.length;i++){
				     if(dt[i]!=null){
				      $("#"+eqnameId).append("<option value='"+dt[i].equipid+"'>"+dt[i].equipname+"</option>");
				     }
			     }
			  },
			  error:function(textStatus){
			      alert('error'+textStatus);
			  }
		 });
     //}else{
		 $("#"+eqnameId+" option").remove();
	     $("#"+eqnameId).append("<option value=''>--请选择--</option>");
     //}
   }


  //设备类型菜单回调函数
   function doClicks(note,obj,cobj,eqNameObj){
      $('#'+obj).val(note.data.text);
      $('#'+cobj).val(note.data.id);
      $("#listDiv_Lists"+obj).hide();
      changeEquipType(cobj,eqNameObj);
   }