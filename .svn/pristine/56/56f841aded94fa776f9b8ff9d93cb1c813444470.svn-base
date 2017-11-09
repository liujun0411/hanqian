

function jsSelectIsExitItem(objSelect, objItemValue) {        
     var isExit = false;        
     for (var i = 0; i < objSelect.options.length; i++) {        
         if (objSelect.options[i].value == objItemValue) {        
             isExit = true;        
             break;        
         }        
     }        
     return isExit;        
}       


/*1判断select选项中 是否存在Value="paraValue"的Item 
2向select选项中 加入一个Item 
3从select选项中 删除一个Item 
4删除select中选中的项 
5修改select选项中 value="paraValue"的text为"paraText" 
6设置select中text="paraText"的第一个Item为选中 
7设置select中value="paraValue"的Item为选中 
8得到select的当前选中项的value 
9得到select的当前选中项的text 
10得到select的当前选中项的Index 
11清空select的项*/


//动态删除select中的所有options： 
function deleteAllOptions(sel){ 
	sel.options.length=0; 
} 
//动态删除select中的某一项option： 
function deleteOption(sel,indx){ 
	sel.options.remove(indx); 
} 
//动态添加select中的项option: 
function addOption(sel,text,value){ 
	sel.options.add(new Option(text,value)); 
} 

// 1.判断select选项中 是否存在Value="paraValue"的Item        
function jsSelectIsExitItem(objSelect, objItemValue) {        
     var isExit = false;        
     for (var i = 0; i < objSelect.options.length; i++) {        
         if (objSelect.options[i].value == objItemValue) {        
             isExit = true;        
             break;        
         }        
     }        
     return isExit;        
}         
   
// 2.向select选项中 加入一个Item        
function jsAddItemToSelect(objSelect, objItemText, objItemValue) {        
     //判断是否存在        
if (jsSelectIsExitItem(objSelect, objItemValue)) {
        // alert("该Item的Value值已经存在");        
	}else{
         var varItem = new Option(objItemText, objItemValue);      
         objSelect.options.add(varItem);     
         //alert("成功加入");     
	}
}
   
// 3.从select选项中 删除一个Item        
function jsRemoveItemFromSelect(objSelect, objItemValue) {        
     //判断是否存在        
     if (jsSelectIsExitItem(objSelect, objItemValue)) {        
         for (var i = 0; i < objSelect.options.length; i++) {        
             if (objSelect.options[i].value == objItemValue) {        
                 objSelect.options.remove(i);        
                 break;        
             }        
         }        
         //alert("成功删除");        
     } else {        
         //alert("该select中 不存在该项");        
     }        
}    
   
   
// 4.删除select中选中的项    
function jsRemoveSelectedItemFromSelect(objSelect) {        
     var length = objSelect.options.length - 1;    
     for(var i = length; i >= 0; i--){    
         if(objSelect[i].selected == true){    
             objSelect.options[i] = null;    
         }    
     }    
}      
   
// 5.修改select选项中 value="paraValue"的text为"paraText"        
function jsUpdateItemToSelect(objSelect, objItemText, objItemValue) {        
     //判断是否存在        
     if (jsSelectIsExitItem(objSelect, objItemValue)) {        
         for (var i = 0; i < objSelect.options.length; i++) {        
             if (objSelect.options[i].value == objItemValue) {        
                 objSelect.options[i].text = objItemText;        
                 break;        
             }        
         }        
         alert("成功修改");        
     } else {        
         alert("该select中 不存在该项");        
     }        
}        
   
// 6.设置select中text="paraText"的第一个Item为选中        
function jsSelectItemByValue(objSelect, objItemText) {            
     //判断是否存在        
     var isExit = false;        
     for (var i = 0; i < objSelect.options.length; i++) {        
         if (objSelect.options[i].text == objItemText) {        
             objSelect.options[i].selected = true;        
             isExit = true;        
             break;        
         }        
     }              
     //Show出结果        
     if (isExit) {        
         //alert("成功选中");        
     } else {        
         //alert("该select中 不存在该项");        
     }        
}        
   
/*// 7.设置select中value="paraValue"的Item为选中    
objSelect.value = objItemValue;    
       
// 8.得到select的当前选中项的value    
var currSelectValue = objSelect.value;    
       
// 9.得到select的当前选中项的text    
var currSelectText = objSelect.options[document.all.objSelect.selectedIndex].text;    
       
// 10.得到select的当前选中项的Index    
var currSelectIndex = objSelect.selectedIndex;    
       
// 11.清空select的项    
objSelect.options.length = 0; 


function watch_ini(){ // 初始
	for(var i=0; i<arguments.length; i++){
	   var oOption=new Option(arguments[i],arguments[i]);
	   document.getElementById("MySelect")[i]=oOption;
	}
}
function watch_add(f){ // 增加
   var oOption=new Option(f.word.value,f.word.value);
   f.keywords[f.keywords.length]=oOption;
}
function watch_sel(f){ // 编辑
	f.word.value = f.keywords[f.keywords.selectedIndex].text;
}
function watch_mod(f){ // 修改
	f.keywords[f.keywords.selectedIndex].text = f.word.value;
}
function watch_del(f){ // 删除
	f.keywords.remove(f.keywords.selectedIndex);
}
function watch_set(f){ // 保存
	var set = "";
	for(var i=0; i<f.keywords.length; i++){
		set += f.keywords[i].text + ";";
	}
	confirm(set);
}
 */

