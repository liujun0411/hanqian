// JavaScript Document
/**
* js td分页类
* @param pageSize 每页显示列数
* @param tableId  表格 Id
* @param trId  表格<tr> Id,此项为要分页的主体内容
* @param objName  实例化对象名,String型
* @param funName  执行事件
* @Version 1.0.0
* @author 陈志平 2011-11-11
* 
* 修改 2013-03-26 李格
* 调用方法 
* var mytable=new Page(5,'table1','tr1','mytable');
* 
*/
function Page(pageSize,tableId,trId,objName,funName){
	this.pageSize = pageSize;	//每行列数
	this.tableId = tableId;		//表格Id
	this.trId = trId;			//ttr Id
	this.objName = objName;
	this.funName = funName;
	
	this.colCount = 0;			//总列数
	this.totalPages = 0;		//总页数
	this.currentPage = 0;		//当前页
	this.table = null;			//表格引用
	this.tr	   = null;			//行引用
	this.dataColumn = 0;		//记录列引用
	
	try{
		this.init(); //初始化;	
	}catch(e){}
};
/**
初始化
*/
Page.prototype.init = function(){
	this.table = document.getElementById(this.tableId);//获取table引用
	this.tr = this.table.rows[this.trId];			   //获取tr引用	
	this.dataColumn = this.tr.cells;				   //获得行的所有列
	this.colCount = this.dataColumn.length;
	try{
		this.pageSize = (this.pageSize <= 0) || (this.pageSize>this.colCount) ? this.colCount : this.pageSize; 
		this.totalPages = parseInt(this.colCount%this.pageSize==0?this.colCount/this.pageSize:this.colCount/this.pageSize+1);
	}catch(exception){}


	this.updateRowsColumn();
};

/**
*  更新显示表格行内容
*/
Page.prototype.updateRowsColumn= function(){
    /**
	 *  起始记录 = 每页数*当前页
	 *  结束记录 = 每页数+起始记录>=总记录数?总记录数:每页数+起始记录
	 */
	var startColNum = this.pageSize * this.currentPage;  
	var endColNum = this.pageSize+startColNum >= this.colCount ? this.colCount : this.pageSize+startColNum;
	var tempCols = this.cloneCols();
	
	
	//火狐中不支持this.tr.removeNode(true);
	this.tr.parentNode.removeChild(this.tr);

	var newTR = document.createElement("TR");
	newTR.setAttribute("id", this.trId);

	//前一页
	if(startColNum != 0){
		var goPre=document.createElement("td");
		goPre.innerHTML='<span style="font-family:webdings;cursor: pointer;" onclick="try {'+this.funName+'} catch (e) {}'+this.objName+'.prePage();"><img src="manager/images/common/control_left.png"></span>';
		newTR.appendChild(goPre);
	}
	for(var i=startColNum; i < endColNum; i+=1){
		newTR.appendChild(tempCols[i]);
	}
	//下一页
	
	if(endColNum != this.colCount){
		var goNext = document.createElement("td");
		goNext.innerHTML='<span style="font-family:webdings;cursor: pointer;"  onclick="try {'+this.funName+'} catch (e) {}'+this.objName+'.nextPage();"><img src="manager/images/common/control_right.png"></span>';
		newTR.appendChild(goNext);
	}
	this.table.tBodies[0].appendChild(newTR);	
	
	
	/**
	 *code:this.dataRows = tempCols;恢复原始操列集合.
	 *this.dataRows为this.tbody的一个引用，
	 *移除this.tbody那么this.dataRows引用将销失	
	 */
	this.dataColumn = tempCols;
	this.tr = newTR;
	
	
};


/**
 * 备份原始列集合
 */
Page.prototype.cloneCols = function(){
	var tempCols = [];		
		for(var i=0; i<this.dataColumn.length; i+=1){
		/**
		code:this.dataColumn[i].cloneNode(param), 
		param = 1 or true:复制以指定节点发展出去的所有节点,
		param = 0 or false:只有指定的节点和它的属性被复制.
		*/
		tempCols[i] = this.dataColumn[i].cloneNode(1);

		}
		
	return tempCols;
};

/**
 *下一页
 */
Page.prototype.nextPage = function(){
	if(this.currentPage + 1 < this.totalPages){
		this.currentPage += 1;
		this.updateRowsColumn();
	}
};
/**
 *上一页
 */
Page.prototype.prePage = function(){
	if(this.currentPage >= 1){
		this.currentPage -= 1;
		this.updateRowsColumn();
	}
};
/**
 *首页
 */
Page.prototype.firstPage = function(){
	if(this.currentPage != 0){
		this.currentPage = 0;
		this.updateRowsColumn();
	} 
};
/**
 *尾页
 */
Page.prototype.lastPage = function(){
	if(this.currentPage+1 != this.totalPages){
		this.currentPage = this.totalPages - 1;
		this.updateRowsColumn();
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
	this.updateRowsColumn();
};
