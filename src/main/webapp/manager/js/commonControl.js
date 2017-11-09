//页面加载，调用设备报警模块
		 $(function(){
		    if($('#alarmCount').val()!="" && $('#alarmCount').val()>0){
		       if($('#groupId').val()!=""){
		    	   if(jQuery.trim($("#showFlag").val())=="" || jQuery.trim($("#showFlag").val())==undefined){
			   	       $(window.parent.parent.parent.document).find("#showFrames").attr({src:"alarm_findAllAlarmPoint.action?groupId="+$('#groupId').val()})+"&t="+Math.random();
			   	       $(window.parent.parent.parent.document).find("#isread-text").click();
			   	       $(window.parent.parent.parent.document).find("#control_warnDiv").show();
			   	       $(window.parent.parent.parent.document).find("#warn_sum").text($('#alarmCount').val());
			   	       $("#showFlag").val("show");
		    	   }else{
		    		   $(window.parent.parent.parent.document).find("#windown-close").click();
		    		   $(window.parent.parent.parent.document).find("#control_warnDiv").show();
			   	       $(window.parent.parent.parent.document).find("#warn_sum").text($('#alarmCount').val());   
		    	   }
		       }
			   if($('#equipId').val()!=""){
				   if($("#showFlag").val()==""){
					   $(window.parent.parent.parent.document).find("#showFrames").attr({src:"alarm_findAllAlarmPoint.action?equipId="+$('#equipId').val()})+"&t="+Math.random();
					   $(window.parent.parent.parent.document).find("#isread-text").click();
					   $(window.parent.parent.parent.document).find("#control_warnDiv").show();
			   	       $(window.parent.parent.parent.document).find("#warn_sum").text($('#alarmCount').val());
					   $("#showFlag").val("show");
				   }else{
					   $(window.parent.parent.parent.document).find("#windown-close").click();
					   $(window.parent.parent.parent.document).find("#control_warnDiv").show();
			   	       $(window.parent.parent.parent.document).find("#warn_sum").text($('#alarmCount').val());   
				   }
			   }
		    }else{
		    	//隐藏弹出层
		    	$(window.parent.parent.parent.document).find("#windown-close").click();
		    	$(window.parent.parent.parent.document).find("#control_warnDiv").hide();
		   	    $(window.parent.parent.parent.document).find("#warn_sum").val('');
		    }
		    $("#warn_sum").click(function(){
		    	  if($('#alarmCount').val()!="" && $('#alarmCount').val()>0){
				       if($('#groupId').val()!=""){
				   	       $(window.parent.parent.parent.document).find("#showFrames").attr({src:"alarm_findAllAlarmPoint.action?groupId="+$('#groupId').val()});
				   	       $(window.parent.parent.parent.document).find("#isread-text").click();
				   	       $("#control_warnDiv").show();
				       }
				       if($('#equipId').val()!=""){
						   $(window.parent.parent.parent.document).find("#showFrames").attr({src:"alarm_findAllAlarmPoint.action?equipId="+$('#equipId').val()});
						   $(window.parent.parent.parent.document).find("#isread-text").click();
						   $("#control_warnDiv").show();
					   }
				  }
		    });
		    
		    //点击Flash空白界面，隐藏所有的提示DIV
		    $('#mainDiv').click(function(){
		    	if($("#flashVersion").val()!="old"){
		    		$('div[name="showInfo"]').hide();
		    	}
		    });
		    //绘制热区(集水井和电梯没有注册事件)
//		    if($('#eqTypeId').val()!=4 && $('#eqTypeId').val()!=6){
//		    	 createHotDiv(0);
//		    }
		 });
		 
		 //自定义JS专家论证文档对象
		 function Argument(run,des,unit,colu,maxVa,minVa,remark,condition,seTitle,showFlag,isAbs,errText) {
			  this.run=run;
			  this.des=des;
			  this.unit=unit;
			  this.colu=colu;
			  this.maxVa=maxVa;
			  this.minVa=minVa;
			  this.remark=remark;
			  this.condition=condition;
			  this.seTitle=seTitle;
			  this.showFlag=showFlag;
			  this.isAbs=isAbs;
			  this.errText=errText;
		 }
		 
		 //自定义热区对象
		 function HotArea(title,wd,hg,marLeft,marTop,prop,initFlag,eqTypeId,eqId,eqName,positX,positY,eventType){
			 this.title=title;
			 this.wd=wd;
			 this.hg=hg;
			 this.marLeft=marLeft;
			 this.marTop=marTop;
		     this.prop=prop;
		     this.initFlag=initFlag;
		     this.eqTypeId=eqTypeId;
		     this.eqId=eqId;
		     this.eqName=eqName;
		     this.positX=positX;
		     this.positY=positY;
		     this.eventType=eventType;
		 }
		 
		 
		//自定义Map对象
		 function Map(){
		    this.keys = new Array();
		    this.data = new Array();
		    //存值
			this.put = function(key,value){
			   if(this.data[key] == null){
			      this.keys.push(value);
			   }
			   this.data[key] = value;
			};
		    //取值
		    this.get = function(key){
		       return this.data[key];
		    };
		    //移除元素
		    this.remove = function(key){
		       this.keys.remove(key);
		       this.data[key] = null;
		    };
		    //判断是否为空
		    this.isEmpty = function(){
		       return this.keys.length == 0;
		    };
		    //集合的长度
		    this.size = function(){
		       return this.keys.length;
		    };
		 }
		 
	    //自定义数组indexOf下表属性方法
		Array.prototype.indexOf = function(val) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == val) return i;
            }
            return -1;
        };
        //自定义移除数组中指定的元素
        Array.prototype.remove = function(val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };
        //定义标题
        var titlename;
		 //动态的创建热区
		 function createHotDiv(childIndex){
			 $('.controlDiv').remove();
			 var hotList=new Array();
			 var divPath=new Map();
			 var eqTypeId=$('#eqTypeId').val();
			 var equipIdList=$('#controlPoint').val();  //获得设备名称和设备ID
			 var hotInterface="hotSpotPath.xml";  //热区入口文件
			 var currentHospCode = $("#currentHospCode").val();
			 var eqList="";
			 var divHot="";
			 var divPt="";
			 $.get('manager/monitoring/control/hotAreaXml/'+currentHospCode+"/"+hotInterface,"fileUrl", function(d){
        	     $(d).find('equipPath').find('fileUrl').each(function(){
		            var path=$(this);
		            divPath.put(path.attr('typeId'),path.attr('url'));
		         });
        	     //如果水冷机组
        	     if(eqTypeId=="1004"){
        	    	 var keys=eqTypeId+"_"+$('#sysTm').val()+"_"+$('#groupCode').val();
        	    	 divPt=divPath.get(keys);
        	     }else if(eqTypeId!="1001" && eqTypeId!="1002"){
        	    	 var keys=eqTypeId+"_"+$('#groupCode').val();
        	    	 divPt=divPath.get(keys);
        	     }else{
        	    	 divPt=divPath.get(eqTypeId);
        	     }
        	     divPt="manager/monitoring/control/hotAreaXml/"+currentHospCode+"/"+divPt;
        	     $.get(divPt,"area", function(m){
        	    	 //如果是不分组页面
        	    	 if($("#equipId").val()!=""){
        	    		 if($(m).find('equipment').find('child').length>0){
        	    			 $(m).find('equipment').find('child').eq(childIndex).find('area').each(function(){
 	       			            var pro=$(this);
 	       			            var eqids=$("#equipId").val();
 	       			            var eqname=$('#eqTypeId').val();
 	       			            //实例化热区对象
 	       			            var hot=new HotArea(pro.attr('title'),pro.attr('wd'),pro.attr('hg'),pro.attr('marLeft'),pro.attr('marTop'),pro.attr('prop'),pro.attr('initFlag'),pro.attr('eqTypeId'),eqids,eqname,pro.attr('positionX'),pro.attr('postionY'),pro.attr('eventType'),pro.attr('errText'));
 	       			            hotList.push(hot);
 	         	    		 });  
        	    		 }else{
	        	    		 $(m).find('equipment').find('area').each(function(){
	       			            var pro=$(this);
	       			            var eqids=$("#equipId").val();
	       			            var eqname=$('#eqTypeId').val();
	       			            //实例化热区对象
	       			            var hot=new HotArea(pro.attr('title'),pro.attr('wd'),pro.attr('hg'),pro.attr('marLeft'),pro.attr('marTop'),pro.attr('prop'),pro.attr('initFlag'),pro.attr('eqTypeId'),eqids,eqname,pro.attr('positionX'),pro.attr('postionY'),pro.attr('eventType'),pro.attr('errText'));
	       			            hotList.push(hot);
	         	    		 }); 
        	    		 }
        	    	 }else{
        	    		 //分组页面
	        	    	 if(jQuery.trim(equipIdList)!="" && equipIdList!=undefined && equipIdList!=null){
	        	    		 eqList=eval(equipIdList); 
	        	    		 if($(m).find('equipment').find('child').length>0){
	        	    			 $(m).find('equipment').find('child').eq(childIndex).find('area').each(function(){
		     			            var pro=$(this);
		     			            var eqids="";
		     			            var eqname="";
		     			            for(var y=0;y<eqList.length;y++){
		     			            	if(eqList[y].eqtypeid==pro.attr('eqTypeId')){
		     			            		eqids=eqList[y].equipid;
		     			            		eqname=eqList[y].equipname;
		     			            		eqList.remove(eqList[y]);
		     			            		break;
		     			            	}
		     			            }
		     			            //实例化热区对象
		     			            var hot=new HotArea(pro.attr('title'),pro.attr('wd'),pro.attr('hg'),pro.attr('marLeft'),pro.attr('marTop'),pro.attr('prop'),pro.attr('initFlag'),pro.attr('eqTypeId'),eqids,eqname,pro.attr('positionX'),pro.attr('postionY'),pro.attr('eventType'),pro.attr('errText'));
		     			            hotList.push(hot);
		     			         }); 
	        	    		 }else{
		        	    		 $(m).find('equipment').find('area').each(function(){
		     			            var pro=$(this);
		     			            var eqids="";
		     			            var eqname="";
		     			            for(var y=0;y<eqList.length;y++){
		     			            	if(eqList[y].eqtypeid==pro.attr('eqTypeId')){
		     			            		eqids=eqList[y].equipid;
		     			            		eqname=eqList[y].equipname;
		     			            		eqList.remove(eqList[y]);
		     			            		break;
		     			            	}
		     			            }
		     			            //实例化热区对象
		     			            var hot=new HotArea(pro.attr('title'),pro.attr('wd'),pro.attr('hg'),pro.attr('marLeft'),pro.attr('marTop'),pro.attr('prop'),pro.attr('initFlag'),pro.attr('eqTypeId'),eqids,eqname,pro.attr('positionX'),pro.attr('postionY'),pro.attr('eventType'),pro.attr('errText'));
		     			            hotList.push(hot);
		     			         }); 
	        	    		 }
        	    	      }
        	    	   }
    				 //判断是否有数据,初始化展示热区
    				 if(hotList.length>0){
    					 for(var t=0;t<hotList.length;t++){
    						 divHot="";
    						 //判断设备ID是否为空，数据库有多少设备，显示多少热区，（最多不能多于标准版）
    						 if(hotList[t].eqId!=null && hotList[t].eqId!=undefined && hotList[t].eqId!=""){
    							 if($("#flashVersion").val()!="old"){
    							 divHot += "<div id='control"+t+"' class='controlDiv' title='"+hotList[t].title+"' style=' cursor: pointer; position: absolute;  height:"+hotList[t].hg+"px; width: "+hotList[t].wd+"px; margin-top: "+hotList[t].marTop+"px; margin-left: "+hotList[t].marLeft+"px; ' prop='"+hotList[t].prop+"' eqTypeId='"+hotList[t].eqTypeId+"' eqId='"+hotList[t].eqId+"' ><img src='manager/images/img/quan9.gif' style='width:"+25+"px;height:"+25+"px;' /></div>";
    							 }
    						 }else{
    							continue;
    						 }
    					     //<span style='background-color:white; filter:Alpha(opacity=0); -moz-opacity:0;-khtml-opacity: 0;opacity: 0; display: block; width:"+hotList[t].wd+"px; height:"+hotList[t].hg+"px; '></span>
    						 var x="";
    						 var y="";
    						 if(hotList[t].positX=="" && hotList[t].positY==""){
    							x=parseInt(hotList[t].marLeft)+parseInt(hotList[t].wd)-10;
        						y=parseInt(hotList[t].marTop)+parseInt(hotList[t].hg)-10;
    						 }else{
    							 x=hotList[t].positX;
    							 y=hotList[t].positY;
    						 }
    					     //默认展示监控DIV
    						if( $("#flashVersion").val()=="old"){
	    					     if(hotList[t].initFlag=="show"){
	    					    	 if(hotList[t].eqTypeId!="" && hotList[t].eqId!=""){
	    							    initShow(x,y,hotList[t].eqTypeId,hotList[t].eqId,hotList[t].prop,currentHospCode);
	    					    	 }
	    					     }
    						 }
    					     //如果是分气缸，默认展示支管
    					     if(hotList[t].eqTypeId=="2004" &&　hotList[t].initFlag=="hide"){
 							    initShow(-1000,-1000,hotList[t].eqTypeId,hotList[t].eqId,hotList[t].prop,currentHospCode);
 					    	 }
    					     
    					     //如果是分水器
    					     if(hotList[t].eqTypeId=="1004005" &&　hotList[t].initFlag=="hide"){
 							    initShow(0,0,hotList[t].eqTypeId,hotList[t].eqId,hotList[t].prop,currentHospCode);
 					    	 }
    					     //集水器
    					     if(hotList[t].eqTypeId=="1004006" &&　hotList[t].initFlag=="hide"){
  							    initShow(0,0,hotList[t].eqTypeId,hotList[t].eqId,hotList[t].prop,currentHospCode);
  					    	 }
    					     
    					     //如果是进水管道
    					     if(hotList[t].eqTypeId=="1005002" &&　hotList[t].initFlag=="hide"){
 							    initShow(0,0,hotList[t].eqTypeId,hotList[t].eqId,hotList[t].prop,currentHospCode);
 					    	 }
    					     
    					     //如果是出水管道
    					     if(hotList[t].eqTypeId=="1005003" &&　hotList[t].initFlag=="hide"){
 							    initShow(0,0,hotList[t].eqTypeId,hotList[t].eqId,hotList[t].prop,currentHospCode);
 					    	 }
    					     
    					     $('body').append(divHot);
    					     //热区展示监控详细(注册事件),根据事件类型，注册相应事件
    					     if($("#flashVersion").val()!="old"){
	    	    				 switch(hotList[t].eventType){
	    	    				    case 'click':
	    	    				    	$('#control'+t).click(function(e){
	    	    				    		//动态获取标题
	    	    				    		  titlename=	$(this).attr('title');
	    	    	    				       var x=e.pageX+10;  //鼠标点击的X坐标(+偏移量)
	    	    	    				       var y=e.pageY+10;  //鼠标点击的Y坐标(+偏移量)
	    	    	    				       initShow(x,y,$(this).attr('eqTypeId'),$(this).attr('eqId'),$(this).attr('prop'),currentHospCode);
	    	    	    				 }); 
	    	    				    	break;
	    	    				    case 'dblclick':
	    	    				    	$('#control'+t).dblclick(function(e){
	    	    				    		//动态获取标题
	    	    				    		  titlename=	$(this).attr('title');
	    	    				    		   var x=e.pageX+10;  //鼠标点击的X坐标(+偏移量)
	 	    	    				           var y=e.pageY+10;  //鼠标点击的Y坐标(+偏移量)
	    	    	    				       initShow(x,y,$(this).attr('eqTypeId'),$(this).attr('eqId'),$(this).attr('prop'),currentHospCode);
	    	    	    				 }); 
	    	    				    	break;
	    	    				    case 'mouseover':
	    	    				    	$('#control'+t).mouseover(function(e){
	    	    				    		//动态获取标题
	    	    				    		  titlename=	$(this).attr('title');
	    	    				    		   var x=e.pageX+10;  //鼠标点击的X坐标(+偏移量)
	 	    	    				           var y=e.pageY+10;  //鼠标点击的Y坐标(+偏移量)
	    	    	    				       initShow(x,y,$(this).attr('eqTypeId'),$(this).attr('eqId'),$(this).attr('prop'),currentHospCode);
	    	    	    				 }); 
	    	    				    	break;
	    	    				    default:
	    	    				    	 $('#control'+t).click(function(e){
	    	    				    		//动态获取标题
	    	    				    		  titlename=	$(this).attr('title');
	    	    				    		   var x=e.pageX+10;  //鼠标点击的X坐标(+偏移量)
	 	    	    				           var y=e.pageY+10;  //鼠标点击的Y坐标(+偏移量)
	    	    	    				       initShow(x,y,$(this).attr('eqTypeId'),$(this).attr('eqId'),$(this).attr('prop'),currentHospCode);
	    	    	    				 }); 	
	    	    				    	break;
	    	    				 }
    					     }
    					 }
    				 }
			      });
			 });
		 }
		 
		 //展示div监控界面
		function initShow(postionX,postionY,eqTypeId,eqId,prop,currentHospCode){
			//alert("postionX:"+postionX+"||postionY:"+postionY+"||eqTypeId："+eqTypeId+"||eqId:"+eqId+"||prop:"+prop);
			var arguPath="manager/monitoring/argumentXml/"+currentHospCode+"/";
			if($("#flashVersion").val()!="old"){
				$('div[name="showInfo"]').remove();
			}
	        var objList=new Array();  //专家论证对象集合
	        var childList=new Array();
	        var myparam=prop;
	        var equipId=eqId;
	        var eqTypeId=eqTypeId;
	        var innerHTML="";
			var title="";
			var hasChild=false;
			var topTitle="";
			var InnerHtml="";
			var remarkMap=new Map();
		    //实例化一个设备类型和专家论证XML文档路径
	        var pathItem=new Map();
			if(eqTypeId!=""){
		           //根据设备类型编号，获得专家论证文档存放路径
		           $.get('manager/monitoring/argumentXml/'+currentHospCode+'/argumentPath.xml',"fileUrl", function(m){
		        	     var filePaths=$(m).find('equipPath').find('fileUrl').each(function(){
				            var path=$(this);
				            var attGroupId = path.attr("groupCode");
				            if(attGroupId&&attGroupId!=null&&attGroupId!=undefined&&attGroupId!=""){
				            	pathItem.put(path.attr('typeId')+"_"+path.attr("groupCode"),path.attr('url'));
				            }else{
				            	pathItem.put(path.attr('typeId'),path.attr('url'));
				            }
				         }); 
		        	     //根据设备类型ID,获取专家论证文档URL地址
		        	     var filePath=pathItem.get(eqTypeId);
		        	     if(filePath==null||filePath==undefined||filePath==''){
		        	    	 filePath = pathItem.get(eqTypeId+"_"+$('#groupCode').val());
		        	     }
		        	     filePath=arguPath+filePath;
			             //读取专家论证的XML文档
					     $.get(filePath,myparam, function(d){
					    	    //获得是否为父节点的标识
					    	    $(d).find('equipment').each(function(){
					    	    	var equ=$(this);
					    	    	hasChild=equ.attr('hasParTitle');
					    	    	topTitle=titlename;
					    	    	//鼠标移动标题替代点击标题
					    	    	//topTitle=equ.attr('title');
					    	    });
					    	    if(hasChild){
					    	    	var groupId=$('#groupId').val();  //分组编号
							        var myPath="";
							        //判断是否分组
							        if(groupId!=""){
							           myPath="manager/monitoring/controlJson/"+$('#eqTypeId').val()+"/"+$('#eqTypeId').val()+"_"+groupId+".json?t="+Math.random();
							        }else{
							           myPath="manager/monitoring/controlJson/"+$('#eqTypeId').val()+"/"+$('#eqTypeId').val()+"_"+equipId+".json?t="+Math.random();
							        }
							        //alert('myPath:'+myPath);
							        if(equipId!=null && equipId!="" && equipId !=undefined){
								         //根据设备ID,查询当前设备ID对应的所有的监控点位的读数
								         $.get(myPath,function(data){
								        	 data=eval(data);
								        	 var pointMap=new Map();
								        	 var otherMap=new Map();
								        	 if(data.length>0){
								        		  for(var m=0;m<data.length;m++){
								 		             var projectPoint=data[m].project_point;
								 		             if(data[m].equipid==equipId){
								 		            	if(projectPoint.indexOf('_')>1){
								 		            		 projectPoint=projectPoint.substring(projectPoint.indexOf('.')+1,projectPoint.indexOf('_'));
								 		            	}else{
								 		            		 projectPoint=projectPoint.substring(projectPoint.indexOf('.')+1);
								 		            	}
								 		            	//如果工程点位值为null,当前点处于为监控状态
								 		            	if(data[m].controlpoint==null || data[m].controlpoint==undefined || data[m].controlpoint==""){
								 		            		pointMap.put(projectPoint,'ISNULL');
								 		            	}else{
								 		            		if((data[m].record!=null && data[m].record!=undefined && data[m].record!="") || (typeof data[m].record=='number' && data[m].record==0)){
								 		            			pointMap.put(projectPoint, data[m].record*data[m].rate);
								 		            		}else{
								 		            			pointMap.put(projectPoint, 'ISNULL');
								 		            		}		
								 		            	}
								 		             }
								 		          }
									        	  InnerHtml+="<tr align='center' bgcolor='#e0f3fc'><td colspan='10'><span style='font-weight:bold;'>"+topTitle+"</span></td></tr>";
									        	  var showPtitle="";
									        	  	//获得支管
									        	    $(d).find('equipment').find("other").each(function(){
									        	    	var zhuanjia=new Array();
									    	    		var index=0;
									    	    		showPtitle=$(this).attr('title');
									    	    		$(this).find('property').each(function(){
												            var pros=$(this);
												            //实例化专家论证文档对象
												            var argument=new Argument(pros.attr('run'),pros.attr('des'),pros.attr('unit'),pros.attr('colu'),pros.attr('maxVa'),pros.attr('minVa'),pros.attr('remark'),pros.attr('condition'),pros.attr('seTitle'),pros.attr('showFlag'),pros.attr('isAbs'),pros.attr('errText'));
												            if(pros.attr('showFlag')==1){
												            	zhuanjia.push(argument);
												            }
												            remarkMap.put(pros.attr('run'), pros.attr('remark'));
													    }); 
									    	    		var titleArray=new Array();
									    	    		//支管HTML
									    	    		if(eqTypeId=="2004"){
									    	    			var contextList=new Array();
										    	    		//offtakeHtml="<div style='left: 510px; top: 130px; position: absolute;' class='gulu_fqg'><center class='fengji_ziti_140' style='height: 25PX; margin-top: 25px;'>"+showPtitle+"</center><table width='97%' border='0' cellspacing='1' bgcolor='#FFFFFF' class='shishijiankong_guolu_zt13' align='center'><tr align='center' valign='top' bgcolor='#f1e3e6'><td width='52'>名称</td><td width='30'>单位</td>";
										        	    	offtakeHtml='<div id="zhiguan" style=" width:60%; margin-left:1%; position:absolute; top:150px;left:35%;"><b class="b1"></b><b class="b2 d1"></b><b class="b3 d1"></b><b class="b4 d1"></b><div class="b d1 k"><center class="fengji_ziti_140">'+showPtitle+'</center><table width="97%" border="0" cellspacing="1" bgcolor="#FFFFFF" class="shishijiankong_guolu_zt13" align="center"><tr align="center" valign="top" bgcolor="#f1e3e6"><td width="52">名称</td><td width="30">单位</td>';
									    	    			for(var k=0;k<data.length;k++){
										        	    		 var project=data[k].project_point;
										        	    		 //获得所有的标题栏
										        	    		 if(project.indexOf(",")>0 && project.substring(project.indexOf(".")+1,project.indexOf(","))==zhuanjia[0].run){
										        	    			 var title=data[k].descr.substring(data[k].descr.indexOf('[')+1,data[k].descr.indexOf(']'));
										        	    			 title.replace("&nbsp;","<br/>");
										        	    			 titleArray.push(title);
										        	    			 contextList.push(project.substring(project.indexOf(",")+1));
										        	    		 }
										        	    	}
										        	    	for(var y=0;y<titleArray.length;y++){
										        	    		offtakeHtml+="<td width='30'><span style='Writing-mode:lr-tb; text-align:left;'>"+titleArray[y]+"</span></td>";
										        	    	}
										        	    	offtakeHtml+="</tr>"
										        	    	//获取对应的读数
										        	    	for(var p=0;p<zhuanjia.length;p++){
										        	    		var myhtml="<tr bgcolor='#f1e3e6' align='center'><td style='height: 20PX;'>"+zhuanjia[p].des+"</td><td>("+zhuanjia[p].unit+")</td>";
										        	    		for(var j=0;j<contextList.length;j++){
										        	    			var record=0;
											        	    		for(var k=0;k<data.length;k++){
											        	    			var project=data[k].project_point;
												        	    		 //获得所有的标题栏
												        	    		 if(project.indexOf(",")>0 && project.substring(project.indexOf(".")+1,project.indexOf(","))==zhuanjia[p].run){
												        	    			if(data[k].record!=null && data[k].record!=undefined && data[k].record!="" && data[k].controlpoint!=null && data[k].controlpoint!=undefined && data[k].controlpoint!=""){
												        	    				record=data[k].record*data[k].rate;
												        	    			}else if(data[k].controlpoint==null || data[k].controlpoint==undefined || data[k].controlpoint==""){
												        	    			    record="";
												        	    			}else{
												        	    				record=data[k].record;
												        	    			}
										    	    						if(record!=null && record!=undefined && record!=""){
										    			   						record=record.toString();
										    			   						if(record.indexOf(".")>0){
										    			   							record=parseFloat(record);
										    			   							record=record.toFixed(2);  //四舍五入，保留两位小数   
										    			   						}
										    			   					}
										    	    						data.remove(data[k]);
										    	    						break;
												        	    		 }else if(project.indexOf(",")>0 && project.substring(project.indexOf(".")+1,project.indexOf(".")+3)==zhuanjia[p].run && project.substring(project.indexOf(",")+1)==contextList[j]){
												        	    			if(data[k].record!=null && data[k].record!=undefined && data[k].record!="" && data[k].controlpoint!=null && data[k].controlpoint!=undefined && data[k].controlpoint!=""){
												        	    				record+=parseFloat(data[k].record*data[k].rate);
												        	    			}else if(data[k].controlpoint==null || data[k].controlpoint==undefined || data[k].controlpoint==""){
												        	    			    record="";
												        	    			}else{
												        	    				record+=data[k].record;
												        	    			}
										    	    						if(record!=null && record!=undefined && record!=""){
										    			   						record=record.toString();
										    			   						if(record.indexOf(".")>0){
										    			   							record=parseFloat(record);
										    			   							record=record.toFixed(2);  //四舍五入，保留两位小数   
										    			   						}else{
										    			   							record=parseInt(record);
										    			   						}
										    			   					}
												        	    		 }
											        	    		 }
											        	    		myhtml+="<td>"+record+"</td>"; 
										        	    		}
										        	    		myhtml+="</tr>";
										        	    		offtakeHtml+=myhtml;
										        	    	}
										        	    	offtakeHtml+='</table></div><b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b></div>';
										        	    	$('#zhiguan').remove();
										        	    	$('body').append(offtakeHtml);
									    	    		}
									    	    		
									        	    });
									        	    //如果是集水器或者分水器
									        	    if(eqTypeId=="1004005" || eqTypeId=="1004006"){
									        	    	var left="";
									        	    	var classname="";
									        	    	var colors="";
									        	    	var titles="";
									        	    	if(eqTypeId=="1004006"){ //集水器
									        	    		var left="50%";
									        	    		colors="#e0f3fc";
									        	    		titles="集水器";
									        	    		classname="d2";
									        	    	}
									        	    	if(eqTypeId=="1004005"){//分水器
									        	    		classname="d1";
									        	    		left="1%";
									        	    		colors="#efe1e3";
									        	    		titles="分水器";
									        	    	}
									        	    	var html="<div style=' width:48%; height:120px;overflow:auto; margin-left:"+left+"; position:absolute;'><b class='b1'></b><b class='b2 "+classname+"'></b><b class='b3 "+classname+"'></b><b class='b4 "+classname+"'></b><div class='b "+classname+" k'><center class='fengji_ziti_140'>"+titles+"</center><table width='90%' border='0' cellspacing='1' bgcolor='#ffffff'  style=' margin-left:5%;'>";
									        	    	//获得所有的子项
									        	    	var index=0;
										    	    	$(d).find('equipment').find("child").each(function(){
										    	    		var myChild=new Array();
										    	    		var columArr=new Array(); //展示列的集合
										    	    		var pointList=new Array();
										    	    		$(this).find('property').each(function(){
													            var pros=$(this);
													            //实例化专家论证文档对象
													            var argument=new Argument(pros.attr('run'),pros.attr('des'),pros.attr('unit'),pros.attr('colu'),pros.attr('maxVa'),pros.attr('minVa'),pros.attr('remark'),pros.attr('condition'),pros.attr('seTitle'),pros.attr('showFlag'),pros.attr('isAbs'),pros.attr('errText'));
													            myChild.push(argument);
													            if(pros.attr('showFlag')==1){
													            	index++;
													            }
													            remarkMap.put(pros.attr('run'), pros.attr('remark'));
													            pointList.push(pros.attr('run'));
														    });
										    	    		
										    	    		//添加流向列
										    	    		columArr.push("流向");	
										    	    		for(var i=0;i<myChild.length;i++){
										    	    			if(myChild[i].unit==""){
										    	    				columArr.push(myChild[i].des+""+myChild[i].unit);
										    	    			 }else {
									    	    				    columArr.push(myChild[i].des+"("+myChild[i].unit+")");
										    	    			 }
									    	    			}
										    	    		
										    	    		//获得设备ID
										    	    		var eqid=new Array();
										    	    		if(myChild.length>0){
										    	    			for(var m=0;m<myChild.length;m++){
										    	    				for(var k=0;k<data.length;k++){
											    	    				if(eqTypeId==data[k].eqtypeid){
											    	    					eqid.push(data[k].equipid);	
											    	    				}
										    	    				}
										    	    			}
										    	    		}
										    	    		var eqArr=new Array();
									    	    			//去重复EquipId
									    	    			for(var m=0;m<eqid.length;m++){
									    	    				if(eqArr.indexOf(eqid[m])>=0){
									    	    				}else{
									    	    					eqArr.push(eqid[m]);
									    	    				}
									    	    			}
									    	    			var tempArr=new Array();
									    	    			//用来获得总共打印多少列
									    	    			for(var i=0;i<eqArr.length;i++){
									    	    				//获得展示的列，即最后的逗号后面的数值
									    	    				for(var k=0;k<data.length;k++){
										    	    				if(data[k].project_point.indexOf(',')>0){
											    	    				if(eqArr[i]+"."+pointList[0]==data[k].project_point.substring(0,data[k].project_point.indexOf(','))){
											    	    					tempArr.push(data[k].project_point.substring(data[k].project_point.indexOf(',')+1));
											    	    				}
										    	    				}
										    	    			}
									    	    			}
									    	    			html+="<tr align='center' bgcolor='"+colors+"'><td rowspan='"+(tempArr.length+2)+"' valign='middle'>"+$(this).attr('title')+"</td></tr>";
									    	    			html+="<tr align='center' bgcolor='"+colors+"'>";
									    	    			for(var m=0;m<columArr.length;m++){
									    	    				html+="<td valign='middle'>"+columArr[m]+"</td>";
									    	    			}
									    	    			html+="</tr>";
									    	    			for(var i=0;i<eqArr.length;i++){
									    	    				//获得展示的列，即最后的逗号后面的数值
										    	    			var myList=new Array();
									    	    				for(var k=0;k<data.length;k++){
										    	    				if(data[k].project_point.indexOf(',')>0){
											    	    				if(eqArr[i]+"."+pointList[0]==data[k].project_point.substring(0,data[k].project_point.indexOf(','))){
											    	    					myList.push(data[k].project_point.substring(data[k].project_point.indexOf(',')+1));
											    	    				}
										    	    				}
										    	    			}
									    	    				
										    	    			for(var r=0;r<myList.length;r++){
										    	    				html+="<tr align='center' bgcolor='"+colors+"'>";
										    	    				//填充流向
										    	    				var config=false;
										    	    				//填充读数
										    	    				for(var j=0;j<pointList.length;j++){
										    	    					for(var k=0;k<data.length;k++){
										    	    						if(eqTypeId==data[k].eqtypeid){
										    	    							if(eqArr[i]+"."+pointList[j]+","+myList[r]==data[k].project_point){
										    	    								var record="";
										    	    								if(data[k].record!=null && data[k].record!=undefined && data[k].record!=""){
										    	    									record=data[k].record*data[k].rate;
										    	    								}else{
										    	    									record=data[k].record;
										    	    								}
										    	    								if(record!=null && record!=undefined && record!=""){
										    	    										record=record.toString();
										    	    										if(record.indexOf(".")>0){
										    	    											record=parseFloat(record);
										    	    											record=record.toFixed(2);  //四舍五入，保留两位小数   
										    	   
										    	    											if(record <= 0){
										    	    												record = 0;   //add by jiangkj 2013/08/12
										    	    											}
										    	    										}
										    	    								}
										    	    								if(!config){
										    	    									if(data[k].descr.indexOf('[')>0 && data[k].descr.indexOf(']')>0){
										    	    										html+="<td height='18' bgcolor='"+colors+"'>"+data[k].descr.substring(data[k].descr.indexOf('[')+1,data[k].descr.indexOf(']'))+"</td>";
										    	    										config=true;
										    	    									}
										    	    								}
										    	    								if(config){
											    	    								//集水器分水器补零
											    	    								if(record==null || $.trim(record)=="null"){
											    	    									html+="<td height='18' bgcolor='"+colors+"'></td>";
											    	    								}else{
											    	    									html+="<td height='18' bgcolor='"+colors+"'>"+record+"</td>";	
											    	    								}
										    	    								}else{
										    	    									html+="<td height='18' bgcolor='"+colors+"' class='removeParentTr'></td>";
										    	    								}
										    	    							}
											    	    					}
										    	    					}
										    	    				}
										    	    				html+="</tr>";
										    	    			}
									    	    			}
										    	    	});
										    	    	html+="</table></div><b class='b4b "+classname+"'></b><b class='b3b "+classname+"'></b><b class='b2b "+classname+"'></b><b class='b1b'></b></div>";
									    	    		$('div[class="lengdong_jsq1"]').remove();
									        	    	$('#tbList').append(html);
									        	    	$('.removeParentTr').parent().remove();
									        	    }else if(eqTypeId=="1005002" || eqTypeId=="1005003"){  //如果是风冷机组(进水管道，出水管道)
									        	    	var left="";
									        	    	var classname="";
									        	    	var colors="";
									        	    	var titles="";
									        	    	if(eqTypeId=="1005002"){ //进水管
									        	    		left="50%";
									        	    		colors="#e0f3fc";
									        	    		titles="进水管";
									        	    		classname="d2";
									        	    	}
									        	    	if(eqTypeId=="1005003"){//出水管
									        	    		classname="d1";
									        	    		left="1%";
									        	    		colors="#efe1e3";
									        	    		titles="出水管";
									        	    	}
									        	    	var myChild = new Array();
									        	    	var pointList=new Array();
                                                                                                //2015-06-09   去掉titles显示的名称<center //class='fengji_ziti_140'>"+titles+"</center>
									        	    	var html="<div id='"+eqTypeId+"_guandao' style=' width:48%; margin-left:"+left+"; position:absolute;'><b class='b1'></b><b class='b2 "+classname+"'></b><b class='b3 "+classname+"'></b><b class='b4 "+classname+"'></b><div class='b "+classname+" k'><center class='fengji_ziti_140'></center><table width='90%' border='0' cellspacing='1' bgcolor='#ffffff'  style=' margin-left:5%;'><tr align='center' bgcolor='"+colors+"'>";
									        	    	//添加流向列
									        	    	html+="<td align='center' bgcolor='"+colors+"'>流向";
									        	    	$(d).find('equipment').find("child").each(function(){
									        	    		//循环专家论证节点，打印指定的列
									        	    		$(this).find('property').each(function(){
													            var pros=$(this);
													            //实例化专家论证文档对象
													            var argument=new Argument(pros.attr('run'),pros.attr('des'),pros.attr('unit'),pros.attr('colu'),pros.attr('maxVa'),pros.attr('minVa'),pros.attr('remark'),pros.attr('condition'),pros.attr('seTitle'),pros.attr('showFlag'),pros.attr('isAbs'),pros.attr('errText'));
													            myChild.push(argument);
													            //循环添加列
													            html+="<td align='center' bgcolor='"+colors+"'>"+pros.attr('des');
													            if($.trim(pros.attr('unit'))!=""){
													            	html+="("+pros.attr('unit')+")";
													            }
													            html+="</td>";
													            pointList.push(pros.attr('run'));
														    }); 
									        	    	});	
									        	    	//循环遍历数组元素
									        	    	html+="</tr>";
									        	    	var eqIdList=new Array();
									        	    	var desList = new Array();
								        	    		for(var k=0;k<data.length;k++){
								        	    			var eqtyppId=data[k].eqtypeid;
								        	    			var projectPoint=data[k].project_point;
									        	    		var eqId=data[k].equipid;
									        	    		var des=data[k].descr;
									        	    		if(des!=null && des!="" && des!=undefined){
									        	    			if(des.indexOf("[")>0 && des.indexOf("]")>0){
									        	    				des=des.substring(des.indexOf("[")+1,des.indexOf("]"));
									        	    			}
									        	    		}
								        	    			if(eqTypeId == eqtyppId){
								        	    				if(eqIdList.indexOf(eqId)<0){
								        	    					eqIdList.push(eqId);
								        	    				}
								        	    				//添加进出水管流向
								        	    				if(desList.indexOf(des)<0){
								        	    					desList.push(des);
								        	    				}
								        	    			}
								        	    		}
								        	    		for(var r=0;r<eqIdList.length;r++){
								        	    			html+="<tr align='center' bgcolor='"+colors+"'>";
								        	    			if(desList.length>0){
								        	    				html+="<td align='center' bgcolor='"+colors+"'>"+desList[r]+"</td>";
								        	    			}else{
								        	    				html+="<td align='center' bgcolor='"+colors+"'></td>";
								        	    			}
								        	    			for(var k=0;k<data.length;k++){
								        	    				var eqtyppId=data[k].eqtypeid;
									        	    			var projectPoint=data[k].project_point;
									        	    			if(eqTypeId == eqtyppId){
										        	    			for(var m=0;m<pointList.length;m++){
										        	    				if(data[k].equipid==eqIdList[r] && projectPoint.substring(projectPoint.indexOf('.')+1,projectPoint.indexOf('.')+3)==pointList[m]){
										        	    				   var record=data[k].record;
										        	    				   if(record!=null && record!=undefined && record!=""){
								    	    									record=parseFloat(record) * parseFloat(data[k].rate);
								    	    								}else if(record!=null){
								    	    									record=record;
								    	    								}else{
								    	    								    record="";	
								    	    								}
										        	    				   if(data[k].controlpoint==null || data[k].controlpoint=="" || data[k].controlpoint==undefined){
										        	    					   record="";
										        	    				   }else{
										        	    					   record=record.toString();
										        	    				   }
											        	    			   if($.trim(record)!=""){
											        	    				   record=record.toString();
											        	    				   if(record.indexOf(".")>0){
											        	    					   record=parseFloat(record);
											        	    					   record=record.toFixed(2);  //四舍五入，保留两位小数   
											        	    				   }
											        	    			   }
										        	    				   //html+="<td height='18' bgcolor='"+colors+"'>"+record+"</td>"; 
										        	    				   //进水管和出水管补零
								    	    							   if(record==null || $.trim(record)=="null" || record==''){
								    	    									html+="<td height='18' bgcolor='"+colors+"'>0</td>";
								    	    							   }else{
								    	    									html+="<td height='18' bgcolor='"+colors+"'>"+record+"</td>";	
								    	    							   }
										        	    				}
										        	    			}
									        	    			}
								        	    			}
								        	    			html+="</tr>";
								        	    		}
									        	    	html+="</table></div><b class='b4b "+classname+"'></b><b class='b3b "+classname+"'></b><b class='b2b "+classname+"'></b><b class='b1b'></b></div>";
									        	    	$('#'+eqTypeId+'_guandao').remove();
									        	    	$('#admin_nr_rightg').append(html);
									        	    }else{
								        	    		//获得所有的子项
									        	    	var flag=0;
										    	    	$(d).find('equipment').find("child").each(function(){
										    	    		var myChild=new Array();
										    	    		var index=0;
										    	    		$(this).find('property').each(function(){
													            var pros=$(this);
													            //实例化专家论证文档对象
													            var argument=new Argument(pros.attr('run'),pros.attr('des'),pros.attr('unit'),pros.attr('colu'),pros.attr('maxVa'),pros.attr('minVa'),pros.attr('remark'),pros.attr('condition'),pros.attr('seTitle'),pros.attr('showFlag'),pros.attr('isAbs'),pros.attr('errText'));
													            myChild.push(argument);
													            if(pros.attr('showFlag')==1){
													            	index++;
													            }
													            remarkMap.put(pros.attr('run'), pros.attr('remark'));
														    }); 
										    	    		
										    	    		if(myChild.length>1){
                                                                for(var i=0;i<myChild.length;i++){
										    	    				var remarkObj=new Array();
											    	    			var item=new Array();
											    	    			var p=myChild[i];
										    	    				if(p.showFlag==1){
										    	    					var result=getRecords(pointMap,remarkMap,p,false,myChild);
															        	item=result.split(',');
															        	if(item[1]=="conPointNull"){
															        		index--;
															        	}
										    	    				}
										    	    			}
                                                                flag+=index;
										    	    			if(index>0){
										    	    				index++;
										    	    			}
										    	    			//判断是否有子项，没有子项则隐藏，有子项则显示
										    	    			if(index<=0){
										    	    				InnerHtml+="<tr align='center' bgcolor='#e0f3fc' style=' display:none;'><td rowspan='"+index+"' valign='middle'>"+$(this).attr('title')+"</td></tr>";
										    	    			}else{
										    	    				InnerHtml+="<tr align='center' bgcolor='#e0f3fc'><td rowspan='"+index+"' valign='middle'>"+$(this).attr('title')+"</td></tr>";
										    	    			}
											    	    		for(var i=0;i<myChild.length;i++){
											    	    			var remarkObj=new Array();
											    	    			var item=new Array();
											    	    			var p=myChild[i];
											    	    			if(p.showFlag==1){
											    	    				var desc=p.des;
											    	    				//如果备注名不为空，优先显示备注名
											    	    				if(jQuery.trim(p.seTitle)!=""){
											    	    					desc=p.seTitle;
											    	    				}
											    	    				//获取读数
															        	var result=getRecords(pointMap,remarkMap,p,false,myChild);
															        	item=result.split(',');
											    	    			    InnerHtml+="<tr align='center' bgcolor='#e0f3fc' class='"+item[1]+"'><td>"+desc+"</td><td>"+item[0]+"</td><td>"+p.unit+"</td></tr>";
											    	    			}
											    	    		}
										    	    		}else{
										    	    			if(myChild[0].showFlag==1){
										    	    				var result=getRecords(pointMap,remarkMap,myChild[0],false,myChild);
														        	item=result.split(',');
														        	var childHtml="<td class='"+item[1]+"'>"+myChild[0].des+"</td class='"+item[1]+"'><td class='"+item[1]+"'>"+item[0]+"</td><td class='"+item[1]+"'>"+myChild[0].unit+"</td>";
														        	if(item[1]!="conPointNull"){
														        		flag++;
														        		InnerHtml+="<tr bgcolor='#e0f3fc' align='center' ><td >"+$(this).attr('title')+"</td>"+childHtml+"</tr>";
														        	}else{
														        		InnerHtml+="<tr bgcolor='#e0f3fc' align='center' class='conPointNull'><td >"+$(this).attr('title')+"</td>"+childHtml+"</tr>";
														        	}
														        }
										    	    		 }
										    	    		var de="<div name='showInfo' style='z-index:9999; left: "+postionX+"px; top: "+postionY+"px; position: absolute; float: right; clear: both;' title=''><div class='shssb_table3'></div><div class='shssb_table4'><table width='95%' border='0' cellspacing='1' bgcolor='#ffffff' class='diant_ziti_130' align='center'>"+InnerHtml+"</table><div style='height: 4px;'></div></div></div>";  
										    	    		if($("#flashVersion").val()!="old"){
										    	    			$("div[name='showInfo']").remove();
										    	    		}
										    	    		if(flag>0){
										    	    		   $('body').append(de);
										    	    		}else{
										    	    			//$('body').append("<div name='showInfo' style='z-index:9999; left: "+postionX+"px; top: "+postionY+"px; position: absolute; float: right; clear: both;' title=''>监控点为空</div>");
										    	    		   //alert("没有可用监控点");
										    	    		}
										    	    	});
									        	    }
								        	    }else{
								        	    	InnerHtml+="<tr align='center' bgcolor='#e0f3fc'><td colspan='10'><span style='font-weight:bold;'>"+topTitle+"</span></td></tr>";
									    	    	//获得所有的子项
									    	    	$(d).find('equipment').find("child").each(function(){
									    	    		var myChild=new Array();
									    	    		var index=0;
									    	    		$(this).find('property').each(function(){
												            var pros=$(this);
												            //实例化专家论证文档对象
												            var argument=new Argument(pros.attr('run'),pros.attr('des'),pros.attr('unit'),pros.attr('colu'),pros.attr('maxVa'),pros.attr('minVa'),pros.attr('remark'),pros.attr('condition'),pros.attr('seTitle'),pros.attr('showFlag'),pros.attr('isAbs'),pros.attr('errText'));
												            myChild.push(argument);
												            if(pros.attr('showFlag')==1){
												            	index++;
												            }
												            remarkMap.put(pros.attr('run'), pros.attr('remark'));
													    }); 
									    	    		if(myChild.length>1){
									    	    			index++;
									    	    			InnerHtml+="<tr align='center' bgcolor='#e0f3fc'><td rowspan='"+index+"' valign='middle'>"+$(this).attr('title')+"</td></tr>";
										    	    		for(var i=0;i<myChild.length;i++){
										    	    			var remarkObj=new Array();
										    	    			var p=myChild[i];
										    	    			if(p.showFlag==1){
										    	    				var desc=p.des;
										    	    				//如果备注名不为空，优先显示备注名
										    	    				if(jQuery.trim(p.seTitle)!=""){
										    	    					desc=p.seTitle;
										    	    				}
										    	    			    InnerHtml+="<tr align='center' bgcolor='#e0f3fc' class='noValue'><td>"+desc+"</td><td></td><td>"+p.unit+"</td></tr>";
										    	    			}
										    	    		}
									    	    		}else{
									    	    			if(myChild[0].showFlag==1){
									    	    				var result=getRecords(pointMap,remarkMap,myChild[0],false,myChild);
													        	item=result.split(',');
										    	    			var childHtml="<td class='noValue'>"+myChild[0].des+"</td class='"+item[1]+"'><td class='"+item[1]+"'>"+item[0]+"</td><td class='"+item[1]+"'>"+myChild[0].unit+"</td>";
										    	    			InnerHtml+="<tr bgcolor='#e0f3fc' align='center' ><td >"+$(this).attr('title')+"</td>"+childHtml+"</tr>";
									    	    			}
									    	    		 }
									    	    	});
									    	    	var de="<div name='showInfo' style='z-index:9999; left: "+postionX+"px; top: "+postionY+"px; position: absolute; float: right; clear: both;' title=''><div class='shssb_table3'></div><div class='shssb_table4'><table width='95%' border='1' cellspacing='0' bgcolor='#ffffff' class='diant_ziti_130' align='center'>"+InnerHtml+"</table><div style='height: 4px;'></div></div></div>";  
									    	    	if($("#flashVersion").val()!="old"){
									    	    		$("div[name='showInfo']").remove();
									    	    	}
									    	    	$('body').append(de);
								        	    }
								            });
							            }else{
							            	 //未监控
							            	 $.get(myPath,function(data){
									        	 data=eval(data);
									        	 var pointMap=new Map();
									        	 var otherMap=new Map();
									        	 if(data.length>0){
										        	 for(var m=0;m<data.length;m++){
											            var projectPoint=data[m].project_point;
											            if(data[m].equipid==equipId){
											            	if(projectPoint.indexOf('_')>1){
											            		 projectPoint=projectPoint.substring(projectPoint.indexOf('.')+1,projectPoint.indexOf('_'));
											            	}else{
											            		 projectPoint=projectPoint.substring(projectPoint.indexOf('.')+1);
											            	}
											            	//如果工程点位为空
											            	if(data[m].controlpoint==null || data[m].controlpoint==undefined || data[m].controlpoint==""){
											            		pointMap.put(projectPoint, "ISNULL");
											            	}else{
											            		if(data[m].record!=null && data[m].record!=undefined && data[m].record!=""){
											            			pointMap.put(projectPoint, data[m].record*data[m].rate);
											            		}else{
											            			pointMap.put(projectPoint, data[m].record);	
											            		}	
											            	}
											            }
											         }
									                //未监控	
									            	InnerHtml+="<tr align='center' bgcolor='#e0f3fc'><td colspan='10'><span style='font-weight:bold;'>"+topTitle+"</span></td></tr>";
									    	    	//获得所有的子项
									    	    	$(d).find('equipment').find("child").each(function(){
									    	    		var myChild=new Array();
									    	    		var index=0;
									    	    		$(this).find('property').each(function(){
												            var pros=$(this);
												            //实例化专家论证文档对象
												            var argument=new Argument(pros.attr('run'),pros.attr('des'),pros.attr('unit'),pros.attr('colu'),pros.attr('maxVa'),pros.attr('minVa'),pros.attr('remark'),pros.attr('condition'),pros.attr('seTitle'),pros.attr('showFlag'),pros.attr('isAbs'),pros.attr('errText'));
												            myChild.push(argument);
												            if(pros.attr('showFlag')==1){
												            	index++;
												            }
												            remarkMap.put(pros.attr('run'), pros.attr('remark'));
													    }); 
	                                                    var index=0;
									    	    		if(myChild.length>1){
									    	    			for(var i=0;i<myChild.length;i++){
									    	    				var remarkObj=new Array();
										    	    			var item=new Array();
										    	    			var p=myChild[i];
									    	    				if(p.showFlag==1){
									    	    					var result=getRecords(pointMap,remarkMap,p,false,myChild);
														        	item=result.split(',');
														        	if(item[1]=="conPointNull"){
														        		index--;
														        	}
									    	    				}
									    	    			}
									    	    			if(index>0){
									    	    				index++;
									    	    			}
									    	    			//判断是否有子项，没有子项则隐藏，有子项则显示
									    	    			if(index<=0){
									    	    				InnerHtml+="<tr align='center' bgcolor='#e0f3fc' style=' display:none;'><td rowspan='"+index+"' valign='middle'>"+$(this).attr('title')+"</td></tr>";
									    	    			}else{
									    	    				InnerHtml+="<tr align='center' bgcolor='#e0f3fc'><td rowspan='"+index+"' valign='middle'>"+$(this).attr('title')+"</td></tr>";
									    	    			}
										    	    		for(var i=0;i<myChild.length;i++){
										    	    			var remarkObj=new Array();
										    	    			var p=myChild[i];
										    	    			if(p.showFlag==1){
										    	    				var desc=p.des;
										    	    				//如果备注名不为空，优先显示备注名
										    	    				if(jQuery.trim(p.seTitle)!=""){
										    	    					desc=p.seTitle;
										    	    				}
										    	    			    InnerHtml+="<tr align='center' bgcolor='#e0f3fc' class='conPointNull'><td>"+desc+"</td><td></td><td>"+p.unit+"</td></tr>";
										    	    			}
										    	    		}
									    	    		}else{
									    	    			if(myChild[0].showFlag==1){
									    	    				var result=getRecords(pointMap,remarkMap,myChild[0],false,myChild);
													        	item=result.split(',');
										    	    			var childHtml="<td class='conPointNull'>"+myChild[0].des+"</td class='conPointNull'><td class='conPointNull'>"+item[0]+"</td><td class='conPointNull'>"+myChild[0].unit+"</td>";
										    	    			InnerHtml+="<tr bgcolor='#e0f3fc' align='center' ><td >"+$(this).attr('title')+"</td>"+childHtml+"</tr>";
									    	    			}
									    	    		 }
									    	    	});
									    	    	 var de="<div name='showInfo' style='z-index:9999; left: "+postionX+"px; top: "+postionY+"px; position: absolute; float: right; clear: both;' title=''><div class='shssb_table3'></div><div class='shssb_table4'><table width='95%' border='0' cellspacing='1' bgcolor='#ffffff' class='diant_ziti_130' align='center'>"+InnerHtml+"</table><div style='height: 4px;'></div></div></div>";  
									    	    	 if($("#flashVersion").val()!="old"){
									    	    		 $("div[name='showInfo']").remove();
									    	    	 }
									    	    	 //$('body').append(de);
									        	 }
							            	 });
							    	    	
									     }
							            	 
					    	    }else{
						    	    //获得专家论证的对象
							        $(d).find('equipment').find(myparam).find('property').each(function(){
							            var pro=$(this);
							            //实例化专家论证文档对象
							            var argument=new Argument(pro.attr('run'),pro.attr('des'),pro.attr('unit'),pro.attr('colu'),pro.attr('maxVa'),pro.attr('minVa'),pro.attr('remark'),pro.attr('condition'),pro.attr('seTitle'),pro.attr('showFlag'),pro.attr('isAbs'),pro.attr('errText'));
							            objList.push(argument);
							            remarkMap.put(pro.attr('run'), pro.attr('remark'));
							         }); 
							         //获得展示标题
							         $(d).find('equipment').find(myparam).each(function(){
							            var pro=$(this);
							            //鼠标移动标题替代点击标题
						                //title=$(pro).attr('title');
						            	title= titlename;
							         }); 
							         var groupId=$('#groupId').val();  //分组编号
							         var myPath="";
							         //判断是否分组
							         if(groupId!=""){
							        	myPath="manager/monitoring/controlJson/"+$('#eqTypeId').val()+"/"+$('#eqTypeId').val()+"_"+groupId+".json?t="+Math.random();
							         }else{
							            myPath="manager/monitoring/controlJson/"+$('#eqTypeId').val()+"/"+$('#eqTypeId').val()+"_"+equipId+".json?t="+Math.random();
							         }
							         if(equipId!=null && equipId!=""){
								         //根据设备ID,查询当前设备ID对应的所有的监控点位的读数
								         $.get(myPath,function(data){
								        	 data=eval(data);
								        	 var pointMap=new Map();
								        	 //华东独立要求， 新风机修改热区div的title
								        	 //2015-09-02
								        	 var pointMapDescr=new Map();
								        	 if(data.length>0){
									        	 for(var m=0;m<data.length;m++){
										            var projectPoint=data[m].project_point;
                                                                                            if(projectPoint.indexOf('_')>1){
									            		 projectPoint=projectPoint.substring(projectPoint.indexOf('.')+1,projectPoint.indexOf('_'));
									            	    }else{
									            		 projectPoint=projectPoint.substring(projectPoint.indexOf('.')+1);
									            	    }
										            if(data[m].equipid==equipId){
                                                                                                  //projectPoint=projectPoint.substring(projectPoint.indexOf('.')+1,projectPoint.indexOf('_'));
											             //如果工程点位为空
										            	 if(data[m].controlpoint==null || data[m].controlpoint==undefined || data[m].controlpoint==""){
										            		pointMap.put(projectPoint, "ISNULL");
										            	 }else{
										            		 if(data[m].record==null){
										            			 pointMap.put(projectPoint, null);	
										            		 }else{
											            		 if(data[m].record!=null && data[m].record!=undefined && data[m].record!=""){
											            			 pointMap.put(projectPoint, data[m].record*data[m].rate);
														        	 //华东独立要求， 新风机修改热区div的title
														        	 //2015-09-02
											            				  pointMapDescr.put(projectPoint, data[m].descr);
											            		 }else{
											            			 pointMap.put(projectPoint, data[m].record);	
														        	 //华东独立要求， 新风机修改热区div的title
														        	 //2015-09-02
                                                                    	  pointMapDescr.put(projectPoint, data[m].descr);
											            		 }	
										            		 }
										            	 }
										             }
										         }
									        	//循环遍历专家论证文档对象
										         var count=0;
									        	 var total=0;
										         for(var i=0;i<objList.length;i++){
										        	 var item=new Array();
										        	 //华东独立要求， 新风机修改热区div的title
										        	 //2015-09-02
										        	 var descr=new Array();
										        	 
										        	 var remarkObj=new Array(); //备注信息
										        	 var desc=objList[i].des; //展示标题
										        	 if(jQuery.trim(objList[i].seTitle)!=""){
										        		 desc=objList[i].seTitle;
										        	 }
										        	 var units=objList[i].unit;  //单位
										        	 //获取读数
										        	 var result=getRecords(pointMap,remarkMap,objList[i],false,objList);
										        	 /**
										        	  * 华东独立要求
										        	  * 
										        	  * 获取当前描述
										        	  */
										        	 var resultDescr=getRecords(pointMapDescr,remarkMap,objList[i],false,objList);
										        	
										        	 item=result.split(',');
										        	 /**
										        	  * 华东独立要求
										        	  */
										        	 descr=resultDescr.split(',');
										        	 var descrs= "";
										        	 if(descr[0].lastIndexOf("[")>0 &&descr[0].lastIndexOf("]")>0){
										        		   descrs= descr[0].substring(descr[0].lastIndexOf("[")+1,descr[0].lastIndexOf("]"));
										        	   }
										        	 
										        	 if(objList[i].showFlag=="1"){
 													 total++;
										        		 if(item[1]=="conPointNull"){
										        			 count++;
										        		 }
										        		 
										        		 /**
										        		  * 华东独立要求
										        		  */
										        		 if(("HD")==currentHospCode){
														        	if(descrs==""){
													        			    innerHTML+="<tr style='height: 18px;' bgcolor='#e0f3fc' align='left' class='"+item[1]+"'><td width='75px;'><span class='"+item[1]+"'>"+desc+":</span></td><td align='center' width='60'><span class='"+item[1]+"'>"+item[0]+"</span></td><td align='center'><span class='"+item[1]+"'>"+units+"</span></td></tr>";
													        	  	}else{
													        			    innerHTML+="<tr style='height: 18px;' bgcolor='#e0f3fc' align='left' class='"+item[1]+"'><td width='75px;'><span class='"+item[1]+"'>"+descrs+":</span></td><td align='center' width='60'><span class='"+item[1]+"'>"+item[0]+"</span></td><td align='center'><span class='"+item[1]+"'>"+units+"</span></td></tr>";
													        	  	}
										        		 }else{
										        			 innerHTML+="<tr style='height: 18px;' bgcolor='#e0f3fc' align='left' class='"+item[1]+"'><td width='75px;'><span class='"+item[1]+"'>"+desc+":</span></td><td align='center' width='60'><span class='"+item[1]+"'>"+item[0]+"</span></td><td align='center'><span class='"+item[1]+"'>"+units+"</span></td></tr>";
										        		 }
										        	 }
										         }
										         var de="<div id='showInfo' name='showInfo' style='z-index:9999; left: "+postionX+"px; top: "+postionY+"px; position: absolute; float: right; clear: both;' title='"+title+"'><div class='shssb_table3'></div><div class='shssb_table4'><table width='95%' border='0' cellspacing='0' bgcolor='#ffffff' class='diant_ziti_130' align='center'><tr style='hieght: 18px;' bgcolor='#e0f3fc' align='center'><td colspan='3'><span style='font-weight:bold;'>"+title+"</span></td></tr>"+innerHTML+"</table><div style='height: 4px;'></div></div></div>";  
										         if($("#flashVersion").val()!="old"){
										          $("div[name='showInfo']").remove();
										         }
 											 if(count==total){
 												
										     }else{
										       	 $('body').append(de);
											 }
								        	 }else{
								        		//没有取得监控读数
							        	    	for(var i=0;i<objList.length;i++){
										        	 var className="noValue";
										        	 if(objList[i].showFlag=="1"){
											        	 innerHTML+="<tr style='height: 18px;' bgcolor='#e0f3fc' align='left' class='diant_ziti_130_tr'><td width='75px;'><span class='"+className+"'>"+objList[i].des+":</span></td><td align='center' width='60'><span class='"+className+"'></span></td><td align='center'><span class='"+className+"'>"+objList[i].unit+"</span></td></tr>";
											         }
										         }
										         var de="<div name='showInfo' style='z-index:9999; left: "+postionX+"px; top: "+postionY+"px; position: absolute; float: right; clear: both;' title=''><div class='shssb_table3'></div><div class='shssb_table4'><table width='95%' border='0' cellspacing='0' bgcolor='#ffffff' class='diant_ziti_130' align='center'><tr style='hieght: 18px;' bgcolor='#e0f3fc' align='center'><td colspan='3'><span style='font-weight:bold; color:gray;'>"+title+"</span></td></tr>"+innerHTML+"</table><div style='height: 4px;'></div></div></div>";  
										         if($("#flashVersion").val()!="old"){
										          $("div[name='showInfo']").remove();
										         }
										         $('body').append(de); 
								        	 }
								         });
							       }else{
							    	 //循环遍历专家论证文档对象
								         for(var i=0;i<objList.length;i++){
								        	 var className="noControl";
								        	 if(objList[i].showFlag=="1"){
									        	 innerHTML+="<tr style='height: 18px;' bgcolor='#e0f3fc' align='left' class='diant_ziti_130_tr'><td width='75px;'><span class='"+className+"'>"+objList[i].des+":</span></td><td align='center' width='60'><span class='"+className+"'></span></td><td align='center'><span class='"+className+"'>"+objList[i].unit+"</span></td></tr>";
									         }
								         }
								         var de="<div name='showInfo' style='z-index:9999; left: "+postionX+"px; top: "+postionY+"px; position: absolute; float: right; clear: both;' title=''><div class='shssb_table3'></div><div class='shssb_table4'><table width='95%' border='0' cellspacing='0' bgcolor='#ffffff' class='diant_ziti_130' align='center'><tr style='hieght: 18px;' bgcolor='#e0f3fc' align='center'><td colspan='3'><span style='font-weight:bold; color:gray;'>"+title+"</span></td></tr>"+innerHTML+"</table><div style='height: 4px;'></div></div></div>";  
								         if($("#flashVersion").val()!="old"){
								        	 $("div[name='showInfo']").remove();
								         }
								         //$('body').append(de);
							       }
					    	   }
					       }); 
					    });
		          }
		     }
		
		
		//获得读数
		var getRecords=function (pointMap,remarkMap,obj,hasChild,objList){
			var record="";
			var className="";
			var remark="";
			var remarkObj=new Array();
			var childList=new Array();
			//如果工程点位不为空
			if(pointMap.get(obj.run)!='ISNULL' && pointMap.get(obj.run)!=undefined){
				//判断计算方式是否为空
				if(jQuery.trim(obj.colu)!=""){
				   //获取计算方式
				   var colu=obj.colu;
				   var sty=colu.substring(0,colu.indexOf('('));
				   childList=colu.substring(colu.indexOf('(')+1,colu.length-1).split(',');
				   record=0;
				   var myrecord=0;
				   switch(sty){
				      case "cut": //减法
				    	 for(var k=0;k<childList.length;k++){
							 if(k<childList.length-1){
								 var keysinfo=obj.run+childList[k];
								 var keynext=obj.run+childList[k+1];
								 if(pointMap.get(keysinfo)=="ISNULL"){
									 record-=9999;
								 }else{
									 if(pointMap.get(keysinfo).toString()!=undefined && pointMap.get(keysinfo).toString()!=null && pointMap.get(keysinfo).toString()!="" && pointMap.get(keynext).toString()!=null && pointMap.get(keynext).toString()!=undefined && pointMap.get(keynext).toString()!=""){
										 var p=parseFloat(pointMap.get(keysinfo).toString())-parseFloat(pointMap.get(keynext).toString());
										 record=parseFloat(record)-parseFloat(p);
									 }else{
										 record-=99999;
									 }
								 }
							 }
						 }
					     break;
				      case "add":  //加法
				    	  for(var k=0;k<childList.length;k++){
				    		    var keysinfo=obj.run+childList[k];
								var p=parseInt(pointMap.get(keysinfo).toString());
							    record=parseInt(record)+parseInt(p);
							 }
				    	  break;
				      case "para":  //并列
				    	  for(var k=0;k<childList.length;k++){
				    		 var keysinfo=obj.run+childList[k];
							 if(pointMap.get(keysinfo).toString()=="ISNULL"){
								 record=-9999;
							 }else{
								 record=pointMap.get(keysinfo);
								 if(record!=undefined && record!=null){
			       				     record=record.toString();
								 }
								 if(record!=undefined &&　record!=null && record!=""){
									 if(pointMap.get(keysinfo)!=0){
										 record="1";
										 break;
									 }else{
										 record=0;
									 }
								 }else{
									 myrecord-=9999; 
								 }
							 }
				    	  }
				    	  break;
				      default:
				    	  record="";
				    	  break;
				   }
				    className="hasValue"
//					if(record>0){
//					    record=1;
//					}
				    if(myrecord<=-29997){
						record="";
						className="noValue";
					}
				    if(record<=-9999){
						record="";
	                    if(hasChild){
						     className="controlPointNULL";
						}else{
						     className="conPointNull";
                                                }
					}
				    //判断备注是否为空
	   			    if(obj.remark!=""){
	   					 var remarkMap=new Array();
	   					 remarkMap=obj.remark.split(",");
	   					 for(var r=0;r<remarkMap.length;r++){
	   						 if(record==remarkMap[r].substring(0,1)){
	   							 record=remarkMap[r].substring(remarkMap[r].indexOf('|')+1);
	   							 if(record==obj.errText){
	   								className="errorValue"; 
	   							 }
	   						 }
	   					 }
	   			   }else{
		   				if(record!=null || record!=undefined || record!=""){
	   						record=record.toString();
	   						if(record.indexOf(".")>0){
	   							record=parseFloat(record);
	   							record=record.toFixed(2);  //四舍五入，保留两位小数   
	   						}
	   					}
	   			   }
				}else{ 
				   if(pointMap.get(obj.run)!=null){
		       		 //判断当前节点是否有前置条件
		       		 if(obj.condition!=""){
		       			 var condList=new Array();
		       			 //判断前置条件
		       			 if(obj.condition.indexOf(",")>0){
		       				condList=obj.condition.split(',');
		       			 }
		       			 var i=0;
		       			 if(condList.length>0){
			       			 for(var t=0;t<condList.length;t++){
			       				 //如果是异常情况(取绝对值)
			       				 if(Math.abs(pointMap.get(condList[t]))==1){
			       					record=Math.abs(pointMap.get(condList[t]));
		       						remark=remarkMap.get(condList[t]);
		       						if(remark!="" || remark!=null){
			       					   remarkObj=remark.split(",");
				       				   for(var r=0;r<remarkObj.length;r++){
			       						   if(record==remarkObj[r].substring(0,1)){
			       							 record=remarkObj[r].substring(remarkObj[r].indexOf('|')+1);
			       							 i++;
			       						   }
				       				   }
			       				    }
			       				   className="errorValue"; 
		       					   break;
			       				 }
			       			 }
		       			 }else{
			       			 //单个前置条件
		       				  if(pointMap.get(obj.condition)!=null || pointMap.get(obj.condition)!="" ||pointMap.get(obj.condition)!=undefined){
				       			 if(Math.abs(pointMap.get(obj.condition))==1){
				       				   i++;
				       				   record=Math.abs(pointMap.get(obj.condition));
				       				   remark=remarkMap.get(obj.condition);
				       				   if(remark!="" || remark!=null){
			       					       remarkObj=remark.split(",");
					       				   for(var r=0;r<remarkObj.length;r++){
				       						   if(record==remarkObj[r].substring(0,1)){
				       							 record=remarkObj[r].substring(remarkObj[r].indexOf('|')+1);
				       						   }
					       				   }
			       				      }
					                className="errorValue"; 
				       			 }else{
				       				 record=pointMap.get(obj.run);
				       				 record=record.toString();
					       			 if(record==undefined || record==null ||record=="" ){
					       				record="";
					       				className="noValue"
					       			 }else{
					       				if(obj.isAbs==1){
					       					record=Math.abs(pointMap.get(obj.run));
					       				}else{
					       					record=pointMap.get(obj.run);
					       				}
					   				    className="hasValue";
					       			 }
					       			 //判断备注是否为空
					   				 if(obj.remark!=""){
					   					 remarkObj=obj.remark.split(",");
					   					 for(var r=0;r<remarkObj.length;r++){
					   						 if(record==remarkObj[r].substring(0,1)){
					   							 record=remarkObj[r].substring(remarkObj[r].indexOf('|')+1);
					   							 if(record=="停止"){
					   								className="stopStatus";
					   							 }
					   							 if(record==obj.errText){
					   								className="errorValue"; 
					   							 }
					   						 }
					   					 }
					   				 }else{
					   					if(record!=null || record!=undefined || record!=""){
					   						record=record.toString();
					   						if(record.indexOf(".")>0){
					   							record=parseFloat(record);
					   							record=record.toFixed(2);  //四舍五入，保留两位小数   
					   						}
					   					}
					   				 }
				       			 }
		       			     }else{
		       			    	 record=pointMap.get(obj.run);
			       				 record=record.toString();
				       			 if(record==undefined || record==null ||record=="" ){
				       				record="";
				       				className="noValue";
				       			 }else{
				       				if(obj.isAbs==1){
				       					record=Math.abs(pointMap.get(obj.run));
				       				}else{
				       					record=pointMap.get(obj.run);
				       				}
				       				className="hasValue";
				       			 }
				       			//判断备注是否为空
				   				 if(obj.remark!=""){
				   					 var remarkMap=new Array();
				   					 remarkMap=obj.remark.split(",");
				   					 for(var r=0;r<remarkMap.length;r++){
				   						 if(record==remarkMap[r].substring(0,1)){
				   							 record=remarkMap[r].substring(remarkMap[r].indexOf('|')+1);
				   							 if(record=="停止"){
				   								className="stopStatus";
				   							 }
				   							 if(record==obj.errText){
				   								className="errorValue"; 
				   							 }
				   						 }
				   					 }
				   				 }else{
				   					if(record!=null || record!=undefined || record!=""){
				   						record=record.toString();
				   						if(record.indexOf(".")>0){
				   							record=parseFloat(record);
				   							record=record.toFixed(2);  //四舍五入，保留两位小数   
				   						}
				   					}
				   				 }
		       			     }
		       			 }
		       			 if(i<=0){
			       			 record=pointMap.get(obj.run);
			       			 record=record.toString();
			       			 if(record==undefined || record==null ||record==""){
			       				record="";
			       				className="noValue";
			       			 }else{
			       				if(obj.isAbs==1){
			       					record=Math.abs(pointMap.get(obj.run));
			       				}else{
			       					record=pointMap.get(obj.run);
			       				}
			       				className="hasValue";
			       			 }
			       			//判断备注是否为空
			   				 if(obj.remark!=""){
			   					var remarkMap=new Array();
			   					remarkMap=obj.remark.split(",");
			   					 for(var r=0;r<remarkMap.length;r++){
			   						 if(record==remarkMap[r].substring(0,1)){
			   							 record=remarkMap[r].substring(remarkMap[r].indexOf('|')+1);
			   							 if(record=="停止"){
			   								className="stopStatus";
			   							 }
			   							 if(record==obj.errText){
			   								className="errorValue"; 
			   							 }
			   						 }
			   					 }
			   				 }else{
			   					if(record!=null || record!=undefined || record!=""){
			   						record=record.toString();
			   						if(record.indexOf(".")>0){
			   							record=parseFloat(record);
			   							record=record.toFixed(2);  //四舍五入，保留两位小数   
			   						}
			   					}  
			   				 }
		       			 }
	       			 }else{
		       			 record=pointMap.get(obj.run);
		       			 record=record.toString();
		       			 if(record==undefined || record==null ||record==""){
		       				record="";
		       				className="noValue";
		       			 }else{
		       				if(obj.isAbs==1){
		       					record=Math.abs(pointMap.get(obj.run));
		       				}else{
		       					record=pointMap.get(obj.run);
		       				}
		       				className="hasValue";
		       			 }
		       			//判断备注是否为空
		   				 if(obj.remark!=""){
		   					var remarkMap=new Array();
		   					remarkMap=obj.remark.split(",");
		   					 for(var r=0;r<remarkMap.length;r++){
		   						 if(record==remarkMap[r].substring(0,1)){
		   							 record=remarkMap[r].substring(remarkMap[r].indexOf('|')+1);
		   							 if(record=="停止"){
		   								className="stopStatus";
		   							 }
		   							 if(record==obj.errText){
		   								className="errorValue"; 
		   							 }
		   						 }
		   					 }
		   				 }else{
		   					if(record!=null || record!=undefined || record!=""){
		   						record=record.toString();
		   						if(record.indexOf(".")>0){
		   							record=parseFloat(record);
		   							record=record.toFixed(2);  //四舍五入，保留两位小数   
		   							className="hasValue";
		   						}
		   					}
		   				 }
		       		 }
		       	 }else{
		       	     //如果有前置条件
		       		 if(jQuery.trim(obj.condition.toString())!=""){
		       			 //判断是否拥有多个前置条件
		       			 var condList=new Array();
		       			 //判断前置条件
		       			 if(obj.condition.indexOf(",")>0){
		       				condList=obj.condition.split(',');
		       			 }
		       			 var i=0;
		       			 //拥有多个前置条件
		       			 if(condList.length>0){
		       				for(var t=0;t<condList.length;t++){
			       				 //如果是异常情况(取绝对值)
			       				 if(Math.abs(pointMap.get(condList[t]))==1){
			       					record=Math.abs(pointMap.get(condList[t]));
		       						remark=remarkMap.get(condList[t]);
		       						if(remark!="" || remark!=null){
			       					   remarkObj=remark.split(",");
				       				   for(var r=0;r<remarkObj.length;r++){
			       						   if(record==remarkObj[r].substring(0,1)){
			       							 record=remarkObj[r].substring(remarkObj[r].indexOf('|')+1);
			       							 i++;
			       						   }
				       				   }
			       				    }
			       				   className="errorValue"; 
		       					   break;
			       				 }
			       			 }
		       				if(i==0){
		       					record="";
				       		    className="noValue";
		       				}
		       			 }else{
		       				 if(pointMap.get(obj.condition)!="ISNULL" && pointMap.get(obj.condition)!=null){
		       					if(Math.abs(pointMap.get(obj.condition))!=0){
					       			record=Math.abs(pointMap.get(obj.condition));
					       			remark=remarkMap.get(obj.condition);
					       			if(remark!="" && remark!=null && remark!=undefined){
					       				remarkObj=remark.split(',');
						       			for(var r=0;r<remarkObj.length;r++){
							       			if(record==remarkObj[r].substring(0,1)){
							       				record=remarkObj[r].substring(remarkObj[r].indexOf('|')+1);
							       			}
						       			}
					       			}
					       			className="errorValue"; 
					       		 }else{
					       			record="";
					       		    className="noValue";
					       		 }
		       				 }else{
		       					record="";
				       		    className="noValue";
		       				 }
		       		     }
		       		 }else{
		       			record="0";  //2013-07-15 14:09
		       		    className="hasValue";
		       		 }
		       	  }	
			  }
		}else if(pointMap.get(obj.run)==undefined){  //update start by jiangkj 2013 11/12
			 record="";
			 className="conPointNull"            //update end
		}else{
			if(hasChild){
		        className="controlPointNULL";
			}else{
		        className="conPointNull";
	        }
			if(obj.condition!=null && obj.condition!="" && obj.condition!=undefined){
				var objCondition=obj.condition;
				var objconArr=new Array();
				//如果有多个前置条件
				if(objCondition.indexOf(",")>0){
					objconArr=objCondition.split(',');
					var doFlag=0;
					for(var q=0;q<objconArr.length;q++){
                       if(q<objconArr.length-1 && doFlag==0){
                    	   //start add by lg 2013/6/13取绝对值
                    	   if(obj.isAbs==1){
		       					if(pointMap.get(objconArr[q])!="ISNULL"){
		       						record = Math.abs(pointMap.get(objconArr[q]));
		       					}else{
		       						record = pointMap.get(objconArr[q]);
		       					}
		       				}else{
		       					record=pointMap.get(objconArr[q]);
		       				}
                    	   //end
                    	  // record=pointMap.get(objconArr[q]); //del by lg 2013/6/13
                    	   if(record!=undefined && record!=null && record!=""){
        					   record=record.toString();
        				   }else if(record==0){
        					   record=record.toString();
        				   }
                    	   if(record=="ISNULL"){
       						   className="conPointNull";
       						   record="";
                    	   }else if(record==0){
                    		  className="conPointNull";
       					 	  record=record.toString();
       					   }else if(record==1){
       						   className="hasValue";
       						   record=record.toString();
       						   var condObj=null;
		       		  			 if(objList.length>0){
		       			  			 for(var k=0;k<objList.length;k++){
		       			  				 if(objList[k].run==objconArr[q]){
		       			  					condObj=objList[k];
		       			  					break;
		       			  				 }
		       			  			 }
		       		  			 }
		       		  			 //判断备注是否为空
		       					 if(condObj.remark!=""){
		       						 var remarkMap=new Array();
		       						 remarkMap=condObj.remark.split(",");
		       						 for(var r=0;r<remarkMap.length;r++){
		       							 if(record==remarkMap[r].substring(0,1)){
		       								 record=remarkMap[r].substring(remarkMap[r].indexOf('|')+1);
		       								 if(record=="停止"){
		       									className="stopStatus";
		       								 }
		       								 if(record==condObj.errText){
		       									className="errorValue"; 
		       								 }
		       							 }
		       						 }
		       					 }else{
		       						if(record!=null || record!=undefined || record!=""){
		       							record=record.toString();
		       							if(record.indexOf(".")>0){
		       								record=parseFloat(record);
		       								record=record.toFixed(2);  //四舍五入，保留两位小数   
		       							}
		       						}
		       					}
		       				  doFlag=1;
		       				  break;
       					   }
                       }else if(q==objconArr.length-1 && doFlag==0){
                    	   //start add by lg 2013/6/13取绝对值
                    	   if(obj.isAbs==1){
		       					if(pointMap.get(objconArr[q])!="ISNULL"){
		       						record = Math.abs(pointMap.get(objconArr[q]));
		       					}else{
		       						record = pointMap.get(objconArr[q]);
		       					}
		       				}else{
		       					record=pointMap.get(objconArr[q]);
		       				}
                    	   //end 
//                    	   record=pointMap.get(objconArr[q]);    //del by lg 2013/6/13
                    	   if(record=="ISNULL"){
       						   className="conPointNull";
       						   record="";
                    	   }else{
	                    	   className="hasValue";
	   						   record=record.toString();
	   						   var condObj=null;
		       		  			 if(objList.length>0){
		       			  			 for(var k=0;k<objList.length;k++){
		       			  				 if(objList[k].run==objconArr[q]){
		       			  					condObj=objList[k];
		       			  					break;
		       			  				 }
		       			  			 }
		       		  			 }
		       		  			 //判断备注是否为空
		       					 if(condObj.remark!=""){
		       						 var remarkMap=new Array();
		       						 remarkMap=condObj.remark.split(",");
		       						 for(var r=0;r<remarkMap.length;r++){
		       							 if(record==remarkMap[r].substring(0,1)){
		       								 record=remarkMap[r].substring(remarkMap[r].indexOf('|')+1);
		       								 if(record=="停止"){
		       									className="stopStatus";
		       								 }
		       								 if(record==condObj.errText){
		       									className="errorValue"; 
		       								 }
		       							 }
		       						 }
		       					 }else{
		       						if(record!=null || record!=undefined || record!=""){
		       							record=record.toString();
		       							if(record.indexOf(".")>0){
		       								record=parseFloat(record);
		       								record=record.toFixed(2);  //四舍五入，保留两位小数   
		       							}
		       						}
		       					}
                    	   }
                       }
					}
				}else{
					record=pointMap.get(objCondition);
					if(record!=undefined && record!=null && record!=""){
					   record=record.toString();
					}else if(record==0){
						record=record.toString();
					}
					if(record=="ISNULL"){
						className="conPointNull";
						record="";
					}else if(record==undefined || record==null ||record==""){
		  				record="";
		  				className="noValue";
		  			 }else{
		  				if(obj.isAbs==1){
		  					record=Math.abs(pointMap.get(objCondition));
		  				}else{
		  					record=pointMap.get(objCondition);
		  				}
		  				className="hasValue";
		  			 }
					 var condObj=null;
		  			 if(objList.length>0){
			  			 for(var k=0;k<objList.length;k++){
			  				 if(objList[k].run==objCondition){
			  					condObj=objList[k];
			  					break;
			  				 }
			  			 }
		  			 }
		  			 //判断备注是否为空
					 if(condObj.remark!=""){
						 var remarkMap=new Array();
						 remarkMap=condObj.remark.split(",");
						 for(var r=0;r<remarkMap.length;r++){
							 if(record==remarkMap[r].substring(0,1)){
								 record=remarkMap[r].substring(remarkMap[r].indexOf('|')+1);
								 if(record=="停止"){
									className="stopStatus";
								 }
								 if(record==condObj.errText){
									className="errorValue"; 
								 }
							 }
						 }
					 }else{
						if(record!=null || record!=undefined || record!=""){
							record=record.toString();
							if(record.indexOf(".")>0){
								record=parseFloat(record);
								record=record.toFixed(2);  //四舍五入，保留两位小数   
							}
						}
					}
				}
			  }
           }
	   return record+","+className
	}
		
		function checkArrayIsNull(array){
			var str = 0;
			for(var i = 0 ; i<array.length ; i++){
				if(array[i]==null){
					str += 1;
				}
			}
			if(str == array.length){
				return true;
			}else{
		        return false;
			}
		}