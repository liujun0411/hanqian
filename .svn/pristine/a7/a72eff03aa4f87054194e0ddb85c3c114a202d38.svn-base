var br_cc = (function(){ 
	var setEffect = function(els,params){ 
		var settings = {"c1":"#fff","c2":"#CFE9EF","c3":"#A5E9E0"}; 
		$.extend(settings,params); 
		els.each(function(i){ 
			var self = $(this); 			
			if(i%2==0){ 
				this._bg = settings.c1; 
			}else{ 
				this._bg = settings.c2; 
			} 
			self.css({"background-color":this._bg}); 
			self.mouseover(function(){ 
				self.css("background-color",settings.c3); 
			}); 
			self.mouseout(function(){ 
				self.css("background-color",this._bg); 
			}); 
		}); 
	}; 
	var cc_table = function(params){ 
		params = params || {}; 
		var box = params.box?$("#"+params.box):$("body").eq(0); 
		setEffect(box.find("tr")); 
	}; 
	var cc_div = function(params){ 
		params = params || {}; 
		var box = params.box?$("#"+params.box):$("body").eq(0); 
		var els = params.tagClass?box.find("."+settings.tagClass):box.find("div"); 
		setEffect(els); 
	}; 
	return {"cc_tb":cc_table,"cc_div":cc_div}; 
	})(); 
	$(document).ready(function(){ 
		br_cc.cc_tb({"box":"stu-datas-tb"}); 

	}); 

	$(document).ready(function(){ 
		br_cc.cc_tb({"box":"stu-datas-tb2"}); 

	}); 
	$(document).ready(function(){ 
		br_cc.cc_tb({"box":"stu-datas-tb3"}); 

	}); 
	$(function(){
		  $('.canshusz_btbg_1 table').addClass('titleBg');
		  $('.canshusz_btbg_1 table td').eq(0).attr({width:'30'});
		  $('.canshusz_btbg_1 table tr').eq(0).attr({style:'20px'});
		  $('.canshusz_btbg_1 table td').eq(0).attr({align:'center'});
		  $('.canshusz_btbg_1 img').attr({src:"manager/images/common/28-01.png"});
		  $('.canshusz_btbg_1 table td:last-child').removeClass('biaoti_zt_canshusz');
  });