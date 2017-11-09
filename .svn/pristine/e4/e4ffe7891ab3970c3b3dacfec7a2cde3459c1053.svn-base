
var selectMid = "md1";							
var selectSubM = "";
var leftmenuid ="1";

//加载二级子菜单
function loadSecondMenu(divobj){
	var sm = new Array("医院基本信息","楼宇基建","设备管理","维修检保养","资产和物资材料管理"); 
	var text="<table width='1000' border='0'><tr height='29' valign='middle' class='fz_dh_zt2'><td width='20'></td><td width='15'><img src='images/imgbg/daohan_kuan2.jpg'/></td>";


	for(var i=0; i < sm.length; i++) { 
		text +="<td width='"+(sm[i].length*20)+"'><a href=''>"+sm[i]+"</a></td><td width='15'><img src='images/imgbg/daohan_kuan1.png' id='smdiv"+i+"' /></td>"; 
	} 

	text +="</tr></table>";

	divobj.innerHTML= text;
}

// 选中子菜单
function changeSubMSelect(imgid) {
	document.getElementById(selectSubM).src = "images/imgbg/daohan_kuan1.jpg";
	selectSubM = imgid;
	document.getElementById(imgid).src = "images/imgbg/daohan_kuan2.jpg";
	
}

//主菜单单击事件
function changSelectMenu(mid) {
	alert(mid);
	//菜单选中
	document.getElementById(selectMid).className = "fenzhan_daohan_lei_1";
	selectMid = mid;
	document.getElementById(mid).className = "fenzhan_daohan_lei_2";
	
	//选中子菜单
	changeSubMSelect("s"+mid);
}


