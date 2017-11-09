	function paping(pg_num,totalRows,maxPageCount){ //当前页，总条数，每页多少条
			//总页数
			var pageCount= parseInt((totalRows+maxPageCount-1)/maxPageCount); 
			//最大显示页数
			var maxPageCount = 10;
  			//如果当前页大于总页数 则设置当前页=总页数
  			if(pg_num==pageCount){
  				pg_num=pageCount;
  			}
  			var option="";
			//小于最大显示页数 直接循环
  			option+="<li><a href='javascript:papingSubmit(1)'>首页</a></li>";
  			option+="<li><a href='javascript:papingSubmit("+(pg_num - 1 > 1 ? pg_num-1 : 1)+")'>上一页</a></li>";
  			if(pageCount<maxPageCount+1){
  				for(var i = 1;i<pageCount+1;i++){
  					if(i==pg_num){
		  				option+="<li  class='active'><a>"+i+"&nbsp;</a></li>";
  					}else{
  						option+="<li><a href='javascript:papingSubmit("+i+")'>"+i+"&nbsp;</a></li>";
  					}
	  			}
  				option+="<li><a href='javascript:papingSubmit("+(pg_num+1<pageCount?pg_num+1:pageCount)+")'>下一页</a></li>";
  			}else{
				/**
				 * 当前页-5 当前页+4 
				 */
				 var begin=0;
				 var end = 0;
				 if(pg_num-5>0){
					 /**
					  * 限制每次最多展示10页 算法 展示不同的页数算法也不一样
					  */
					 begin = pg_num-5;
					 end = pg_num+4;
					 if(pg_num>=pageCount-3){
						 begin=pageCount-maxPageCount+1;
						 end=pageCount;
					 }
					 for(var i = begin;i<end+1;i++){
						 if(i==pg_num){
							 option+="<li  class='active'><a>"+i+"&nbsp;</a></li>";
	  					}else{
	  						option+="<li><a href='javascript:papingSubmit("+i+")'>"+i+"&nbsp;</a></li>";
	  					}
					 }
					 option+="<li><a href='javascript:papingSubmit("+(pg_num+1<pageCount?pg_num+1:pageCount)+")'>下一页</a></li>";
				 }else{
					 for(var i = 1;i<maxPageCount+1;i++){
		  				if(i==pg_num){
		  					option+="<li  class='active'><a>"+i+"&nbsp;</a></li>";
	  					}else{
	  						option+="<li><a href='javascript:papingSubmit("+i+")'>"+i+"&nbsp;</a></li>";
	  					}
		  			 }
					 option+="<li><a href='javascript:papingSubmit("+(pg_num+1<pageCount?pg_num+1:pageCount)+")'>下一页</a></li>";
				 }
  			}
  			option+="<li><a href='javascript:papingSubmit("+pageCount+")'>末页</a></li>";
  			return option;
  		}
	