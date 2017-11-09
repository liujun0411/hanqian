
// JavaScript Document
/**
* js分页类
* @param pageSize 每页显示记录数
* @param tableId  表格 Id
* @param tbodyId  表格<tbody> Id,此项为要分页的主体内容
* @param toolId   分页工具Id
* @param objName  实例化对象名,String型
* @Version 1.0.0
* @author 陈志平 2011-11-11
* 调用方法 
* var mytable=new Page(10,'table1','stu-datas-tb','showtool','mytable'); 
*/
function Page(pageSize,tableId,tbodyId,toolId,objName){
	this.pageSize = pageSize;	//每页记录数
	this.tableId = tableId;		//表格Id
	this.tbodyId = tbodyId;		//tbody Id

	this.rowCount = 0;			//总记录数
	this.totalPages = 0;		//总页数
	this.currentPage = 0;		//当前页
	this.table = null;//表格引用
	this.tbody = null;//要分页内容
	this.dataRows = 0;//记录行引用
	
	this.toolId=toolId;	 //分页条Id
	this.objName=objName;//实例化对象名
	
	try{
	this.init(); //初始化;	
	}catch(e){}
};
/**
初始化
*/
Page.prototype.init = function(){
	this.table = document.getElementById(this.tableId);//获取table引用
	this.tbody = this.table.tBodies[this.tbodyId];	   //获取tBody引用
	this.dataRows = this.tbody.rows;
	this.rowCount = this.dataRows.length;
	try{
		this.pageSize = (this.pageSize <= 0) || (this.pageSize>this.rowCount) ? this.rowCount : this.pageSize; 
		this.totalPages = parseInt(this.rowCount%this.pageSize==0?this.rowCount/this.pageSize:this.rowCount/this.pageSize+1);
	}catch(exception){}

	this.updateTableRows();
};
/**
 * 更新分页工具
 */
Page.prototype.updateTableTool=function(){
	var tool  = document.getElementById(this.toolId);
	var str ="";
	if(tool !=null && this.objName !=null){
		
		str ="<table align='left'>"+
		"	<tr>"+
		"		<td class='biaoge_fanye'>共<b>"+this.rowCount+"</b> 条记录&nbsp;";
		if(this.currentPage>0){
		   str+="<a href='javascript:void(0);' onclick='"+this.objName+".firstPage()' title='首页'>首页</a>&nbsp;";
		   str+="<a href='javascript:void(0);' onclick='"+this.objName+".prePage()' title='上一页'>上一页</a>&nbsp;";
		}else{
		   str+="首页&nbsp;上一页&nbsp;";
		}
		if(this.currentPage<this.totalPages-1){
			str+="<a href='javascript:void(0);' onclick='"+this.objName+".nextPage()' title='下一页'>下一页</a>&nbsp;";
			str+="<a href='javascript:void(0);' onclick='"+this.objName+".lastPage()' title='尾页'>尾页</a>&nbsp;";
		}else{
		    str+="下一页&nbsp;尾页&nbsp;";
		}
		str+="<strong><font color=red>"+(parseInt(this.currentPage)+1)+"</font>/"+this.totalPages+"</strong>页&nbsp;";
		str+="<b>"+this.pageSize+"</b>条记录/页&nbsp;转到:";
		str+="<select name='page' size='1' onchange='"+this.objName+".goPage(this.value)'>";		
		for(var i=0;i<this.totalPages;i+=1){
			if(this.currentPage==i){
				str+="<option value='"+i+"' selected>第"+(i+1)+"页</option>";
			}else{
				str+="<option value='"+i+"' >第"+(i+1)+"页</option>";
			}
		}
		str+="</select>";
		str+="	    </td>";
		str+="    </tr>";
		str+="</table>"
		
		tool.innerHTML=str;
		try{
			br_cc.cc_tb( {'box' : this.tbodyId});
		}catch(exception){}
	}
}
/**
*  更新显示表格内容
*/
Page.prototype.updateTableRows= function(){
    /**
	 *  起始记录 = 每页数*当前页
	 *  结束记录 = 每页数+起始记录>=总记录数?总记录数:每页数+起始记录
	 */
	var startRowNum = this.pageSize * this.currentPage;  
	var endRowNum = this.pageSize+startRowNum >= this.rowCount ? this.rowCount : this.pageSize+startRowNum;
	var tempRows = this.cloneRows();
	
	var removedTBody = this.table.removeChild(this.tbody);
	var newTBody = document.createElement("TBODY");
	newTBody.setAttribute("id", this.tbodyId);

	for(var i=startRowNum; i < endRowNum; i+=1){
		newTBody.appendChild(tempRows[i]);
	}
	this.table.appendChild(newTBody);
	/**
	 *code:this.dataRows = tempRows;恢复原始操作行集合.
	 *this.dataRows为this.tbody的一个引用，
	 *移除this.tbody那么this.dataRows引用将销失	
	 */
	this.dataRows = tempRows;
	this.tbody = newTBody;

	this.updateTableTool();
};


/**
 * 备份原始操作行集合
 */
Page.prototype.cloneRows = function(){
	var tempRows = [];
		for(var i=0; i<this.dataRows.length; i+=1){
		/**
		code:this.dataRows[i].cloneNode(param), 
		param = 1 or true:复制以指定节点发展出去的所有节点,
		param = 0 or false:只有指定的节点和它的属性被复制.
		*/
		tempRows[i] = this.dataRows[i].cloneNode(1);
	}
	return tempRows;
};

/**
 *下一页
 */
Page.prototype.nextPage = function(){
	if(this.currentPage + 1 < this.totalPages){
		this.currentPage += 1;
		this.updateTableRows();
	}
};
/**
 *上一页
 */
Page.prototype.prePage = function(){
	if(this.currentPage >= 1){
		this.currentPage -= 1;
		this.updateTableRows();
	}
};
/**
 *首页
 */
Page.prototype.firstPage = function(){
	if(this.currentPage != 0){
		this.currentPage = 0;
		this.updateTableRows();
	} 
};
/**
 *尾页
 */
Page.prototype.lastPage = function(){
	if(this.currentPage+1 != this.totalPages){
		this.currentPage = this.totalPages - 1;
		this.updateTableRows();
	}
};
/**
 *指定页
 */
Page.prototype.goPage = function(iPageIndex){
	if(iPageIndex > this.totalPages-1){
		this.currentPage = this.totalPages - 1;
	}else if(iPageIndex < 0){
		this.currentPage = 0;
	}else{
		this.currentPage = iPageIndex;
	}
	this.updateTableRows();
};
