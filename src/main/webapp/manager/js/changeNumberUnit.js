// JavaScript Document
/**
* js 更新数据单位
* 需要jquery 支持
* 将界面中span标签内的所有数据约化为
* #0.00亿 #0.00万
* @author 陈志平 2011-11-29
* 
*/
	
	beginAt=function(){
		var objs_span = $("span");
		changNumber(objs_span);
		var objs_td = $("td");
		changNumber(objs_td);
		
	};
	
	beginChange=function(){
		var objs_td = $("td[class='tdValue']");
		changNumber(objs_td);
		
	};
	changNumber=function(objs){
		var value;		
		for(var i=0;i<objs.length;i+=1){
			value = $(objs[i]).text();
			if(!isNaN(value)){
				if(value>10000){
					value =  Math.round((Math.floor((value/10000)*1000)/10))/100;
					if(value>10000){
						$(objs[i]).text(Math.round((Math.floor((value/10000)*1000)/10))/100 + "亿");
					}else{
						$(objs[i]).text(value + "万");
					}
				}
			}else{
				$(objs[i]).text();
			}
		}
	};
	$(document).ready(function(){ 
		beginAt(); 
		beginChange();
	}); 	
