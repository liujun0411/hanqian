
// JavaScript Document
/**
* 二级及联菜单
* 设备类型的及联更新
* @author 陈志平 2011-12-23
* 
*/



		//设备类型
		Node=function(id,name,pid){
			this.id=id;
			this.name=name;
			this.pid=pid;
		};

		//设备类型集合
		Menu=function(id,sSelInEL,showId){
			this.aNodes=[];				//菜单项
			this.fSelId = id;			//一级菜单Id
			this.sSelInEL = sSelInEL;   //二级菜单展示位置
			this.showId = showId;		//选中值展示Id
			this.backId;				//备注Id
			this.sSelId = "_subsel";	//二级菜单Id
			this.isNotSub = false;		//有子菜单选中不赋值
			this.isNs = false;						
			this.initfSelOnchange();
		};
		
		//初始化一级菜单事件
		Menu.prototype.initfSelOnchange=function() {
			var selobj = document.getElementById(this.fSelId);
			var obj = this;
			selobj.onchange=function(){
				obj.getSubSel(this.value);				
			}
		};
		
		//有子菜单选中不赋值
		Menu.prototype.setIsNotSub=function(istrue){
			this.isNotSub = istrue;		
		}
		
		
		
		//添加设备类型
		Menu.prototype.add=function(id,  name, pid) {
			this.aNodes[this.aNodes.length] = new Node(id, name, pid);
		};

		//更新二级菜单
		Menu.prototype.getSubSel=function(id,isNoset){
			
			try{				
				var span = document.getElementById(this.sSelInEL);	
				span.innerHTML = "";
				this.isNs = false;
				if(id.length>0){
					var sel = document.createElement("select");
					sel.id = this.sSelId;
					var obj = this;
					sel.onchange=function(){
						obj.isNs = false;
						obj.setValue(obj.showId,this.value);						
					}
				
					sel.add(new Option("--请选择--","_sub_"));
					
					var istrue = false;
					for(var i=0;i<this.aNodes.length;i+=1){					
						if(id==this.aNodes[i].pid){
							sel.add(new Option(this.aNodes[i].name,this.aNodes[i].id));	
							istrue = true;
						}
						
					}
					if(istrue){
						span.appendChild(sel);
					}
					
					
					this.isNs = istrue;
				}								
			}catch(e){}
			this.backId = id;
			
			if(isNoset){
				return;
			}
			
			this.setValue(this.showId,id);
		};

		//所选值
		Menu.prototype.setValue=function(showId,value){
			var obj = document.getElementById(showId);
			
			if(value == "_sub_"){
				obj.value = this.backId;
			}else{
				obj.value = value;
			}
			if(this.isNotSub && (this.isNs || value == "_sub_")) {
				obj.value = "";
			}
			
		};
		
		//选中菜单
		Menu.prototype.goTo=function(id){	
			if(id.length<1){
				return;				
			}
			var fsel = document.getElementById(this.fSelId);

			try{
				var mid="";
				var msid="";
				for(var i=0;i<this.aNodes.length;i+=1){
				   if(id == this.aNodes[i].id){
					  mid = this.aNodes[i].id;
					  msid = this.aNodes[i].pid
					  break;
				   }
				}
				
				if(""!=msid){
					var tmp = msid;
					msid = mid;
					mid = tmp
				}

				//选中一级菜单
				for(var i=0;i<fsel.options.length;i+=1){
					if(fsel.options[i].value==mid){
						fsel.options[i].selected = true;
						break;
					}
				}
				
				//选中二级菜单
				this.getSubSel(mid,true);
				if(""!=msid){
					try{
						var ssel = document.getElementById(this.sSelId);
						for(var i=0;i<ssel.options.length;i+=1){
							if(ssel.options[i].value==msid){
								ssel.options[i].selected = true;
								break;
							}
						}
					}catch(ex){}
				}
				
				
			}catch(e){}
		};
		
		
		
		