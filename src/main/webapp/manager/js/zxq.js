function wsug(e, str){ // http://www.hansir.cn
 var oThis = arguments.callee;
 if(!str) {
  oThis.sug.style.visibility = 'hidden';
  document.onmousemove = null;
  return;
 }  
 if(!oThis.sug){
  var div = document.createElement('div'), css = 'top:0; left:0; position:absolute; z-index:100; visibility:hidden';
   div.style.cssText = css;
   div.setAttribute('style',css);
  var sug = document.createElement('div'), css= 'font:normal 12px/16px "宋体"; white-space:nowrap; color:#666; padding:3px; position:absolute; left:0; top:0; z-index:10; background:#f9fdfd; border:1px solid #0aa';
   sug.style.cssText = css;
   sug.setAttribute('style',css);
  var dr = document.createElement('div'), css = 'position:absolute; top:3px; left:3px; background:#333; filter:alpha(opacity=50); opacity:0.5; z-index:9';
   dr.style.cssText = css;
   dr.setAttribute('style',css);
  var ifr = document.createElement('iframe'), css='position:absolute; left:0; top:0; z-index:8; filter:alpha(opacity=0); opacity:0';
   ifr.style.cssText = css;
   ifr.setAttribute('style',css);
  div.appendChild(ifr);
  div.appendChild(dr);
  div.appendChild(sug);
  div.sug = sug;
  document.body.appendChild(div);
  oThis.sug = div;
  oThis.dr = dr;
  oThis.ifr = ifr;
  div = dr = ifr = sug = null;
 }
 var e = e || window.event, obj = oThis.sug, dr = oThis.dr, ifr = oThis.ifr;
 if(str.length>12&&str.length<24){
   obj.sug.innerHTML = str.substring(0,12)+"</br>"+str.substring(12,24);
 }else if(str.length>24&&str.length<48)
 {
	 obj.sug.innerHTML = str.substring(0,12)+"</br>"+str.substring(12,24)+"</br>"+str.substring(24,36)+"</br>"+str.substring(36,48);
 }else if(str.length>48&&str.length<72){
	 obj.sug.innerHTML = str.substring(0,12)+"</br>"+str.substring(12,24)+"</br>"+str.substring(24,36)+"</br>"+str.substring(36,48)+"</br>"+str.substring(48,60)+"</br>"+str.substring(60,72);
 }else if(str.length>72&&str.length<96){
	 obj.sug.innerHTML = str.substring(0,12)+"</br>"+str.substring(12,24)+"</br>"+str.substring(24,36)+"</br>"+str.substring(36,48)+"</br>"+str.substring(48,60)+"</br>"+str.substring(60,72)+"</br>"+str.substring(72,84)+"</br>"+str.substring(84,96);
 }else if(str.length>96&&str.length<120){
	 obj.sug.innerHTML = str.substring(0,12)+"</br>"+str.substring(12,24)+"</br>"+str.substring(24,36)+"</br>"+str.substring(36,48)+"</br>"+str.substring(48,60)+"</br>"+str.substring(60,72)+"</br>"+str.substring(72,84)+"</br>"+str.substring(84,96)+"</br>"+str.substring(96,108)+"</br>"+str.substring(108,120);
 }else{
	 obj.sug.innerHTML = str;
 }
 var w = obj.sug.offsetWidth, h = obj.sug.offsetHeight, dw = document.documentElement.clientWidth||document.body.clientWidth; dh = document.documentElement.clientHeight || document.body.clientHeight;
 var st = document.documentElement.scrollTop || document.body.scrollTop, sl = document.documentElement.scrollLeft || document.body.scrollLeft;
 var left = e.clientX +sl +17 + w < dw + sl  &&  e.clientX + sl + 15 || e.clientX +sl-8 - w, top = e.clientY + st +17 + h < dh + st  &&  e.clientY + st + 17 || e.clientY + st - 5 - h;
 obj.style.left = left+ 10 + 'px';
 obj.style.top = top + 10 + 'px';
 dr.style.width = w + 'px';
 dr.style.height = h + 'px';
 ifr.style.width = w + 3 + 'px';
 ifr.style.height = h + 3 + 'px';
 obj.style.visibility = 'visible';
 document.onmousemove = function(e){
  var e = e || window.event, st = document.documentElement.scrollTop || document.body.scrollTop, sl = document.documentElement.scrollLeft || document.body.scrollLeft;
  var left = e.clientX +sl +17 + w < dw + sl  &&  e.clientX + sl + 15 || e.clientX +sl-8 - w, top = e.clientY + st +17 + h < dh + st  &&  e.clientY + st + 17 || e.clientY + st - 5 - h;
  obj.style.left = left + 'px';
  obj.style.top = top + 'px';
 }
}

	function f_JB(hospid,buildingId,editflag,myfirst){
		  $('#tabIndex').val("1");
	   	  $("#iframeJB").attr({ src: "build!showBuildingbasic.action?hospid="+hospid+"&buildingId="+buildingId+"&editFlag="+editflag+"&myfirst="+myfirst+"&tabIndex=1"});
	   	  $("#div22").show();
	   	  $("#mydiv").hide();
	   	  $("#img5").attr({ src: "manager/images/imgs/buildPicblack.png"});
	   	  $("#divpic").hide();
	   	  $("#img1").attr({ src: "manager/images/imgs/gai_louyu_annu8.png"});
          $("#div44").hide();
	   	  $("#img3").attr({ src: "manager/images/imgs/gai_louyu_annu3.png"}); 
          $("#div33").hide();
	   	  $("#img2").attr({ src: "manager/images/imgs/gai_louyu_annu5.png"});
          $("#div55").hide();
	   	  $("#img4").attr({ src: "manager/images/imgs/gai_louyu_annu1.png"});
	   	  
	   	  $("#areaDiv").show();
     }

	function f_BLT(hospid,buildingId,editflag,myfirst){
		  $('#tabIndex').val("2");
		
		  $("#iframeBlt").attr({ src: "drawing!showStoreyUse.action?buildingId="+buildingId+"&hospid="+hospid+"&editFlag="+editflag+"&myfirst="+myfirst+"&showOrhide=1"+"&tabIndex=2"});
	   	  $("#div33").show();
	   	  //$("#mydiv").hide();
	   	  $("#img5").attr({ src: "manager/images/imgs/buildPicblack.png"});
	   	  $("#divpic").hide();
	   	  $("#img2").attr({ src: "manager/images/imgs/gai_louyu_annu6.png"});
	    $("#div44").hide();
	   	  $("#img3").attr({ src: "manager/images/imgs/gai_louyu_annu3.png"});
	    $("#div22").hide();
	   	  $("#img1").attr({ src: "manager/images/imgs/gai_louyu_annu7.png"});
	    $("#div55").hide();
	   	  $("#img4").attr({ src: "manager/images/imgs/gai_louyu_annu1.png"});
	
	   	$("#areaDiv").hide();
	  }

    function f_DXandJG(hospid,buildingId,editflag,myfirst,shows){
    	$("#mydiv").hide();
    	$("#mydiv1").hide();
    	if($("#xiangxi") && $("#liebiao")){
    		$("#xiangxi").show();
    		$("#liebiao").hide();
    	}
    	$('#tabIndex').val("4");
    	
	      $("#iframeJG").attr({ src:"buildDetails!showBuildStruct.action?hospid="+hospid+"&buildingId="+buildingId+"&editFlag="+editflag+"&myfirst="+myfirst+"&shows="+shows+"&tabIndex=4"});
	   	  $("#div55").show();
	   	  $("#img5").attr({ src: "manager/images/imgs/buildPicblack.png"});
	   	  $("#divpic").hide();
	   	  $("#img4").attr({ src: "manager/images/imgs/gai_louyu_annu2.png"});
          $("#div44").hide();
	   	  $("#img3").attr({ src: "manager/images/imgs/gai_louyu_annu3.png"});
          $("#div22").hide();
	   	  $("#img1").attr({ src: "manager/images/imgs/gai_louyu_annu7.png"});
          $("#div33").hide();
	   	  $("#img2").attr({ src: "manager/images/imgs/gai_louyu_annu5.png"});
	   	 $("#areaDiv").show();
	}
	
    
    function f_picUpload(buildingId){
    	if($("#xiangxi") && $("#liebiao")){
    		$("#xiangxi").show();
    		$("#liebiao").hide();
    	}
    	
	      $("#iframeTZ").attr({ src:"buildingPic!findBuilbingPic.action"});
	      $("#divpic").show();
	      $("#div55").hide();
	   	  $("#img4").attr({ src: "manager/images/imgs/gai_louyu_annu1.png"});
          $("#div44").hide();
	   	  $("#img3").attr({ src: "manager/images/imgs/gai_louyu_annu3.png"});
          $("#div22").hide();
	   	  $("#img1").attr({ src: "manager/images/imgs/gai_louyu_annu7.png"});
          $("#div33").hide();
	   	  $("#img2").attr({ src: "manager/images/imgs/gai_louyu_annu5.png"});
	   	  $("#img5").attr({ src: "manager/images/imgs/buildpicBlue.png"});
	   	 $("#areaDiv").hide();
	}
    f_YT=function(hospitalId,build){
    	$("#BJ").text("修改");
    	 $("#bFH").hide();
         $("#BJ").show();
         $("#BC").hide();
    	  
    	$('#tabIndex').val("3");
		$("#mydiv").hide();
	   	  $("#div44").show();
	   	  $("#mydiv1").show();
	   	  $("#img5").attr({ src: "manager/images/imgs/buildPicblack.png"});
	   	  $("#divpic").hide();
	   	  $("#img3").attr({ src: "manager/images/imgs/gai_louyu_annu4.png"});
          $("#div22").hide();
	   	  $("#img1").attr({ src: "manager/images/imgs/gai_louyu_annu7.png"});
          $("#div33").hide();
	   	  $("#img2").attr({ src: "manager/images/imgs/gai_louyu_annu5.png"});
          $("#div55").hide();
	   	  $("#img4").attr({ src: "manager/images/imgs/gai_louyu_annu1.png"});
	   	  
	   	 $("#areaDiv").show();
	   
	   	 
	}
	function f_load(hospid,buildingId,editflag,myfirst,desc)
    {
		$("#BC").hide();
		$("#bFH").hide();
		var showXXid=$('#tabIndex').val();
		if(showXXid==2){
			f_BLT(hospid,buildingId,editflag,myfirst);
		}else if(showXXid ==3){
			f_YT(hospid,buildingId);
		}else if(showXXid == 4){
			 f_DXandJG(hospid,buildingId,editflag,myfirst);
		}else{
			f_JB(hospid,buildingId,editflag,myfirst)			
		}				
    }
	
	function OnEnter( field ) {
		 if( field.value == field.defaultValue ) { 
			 field.value = ""; 
	    }
    }
	function OnExit( field ) { 
		if( field.value == "" ) { 
			field.value = field.defaultValue; 
		} 
	}