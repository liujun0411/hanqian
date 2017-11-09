/*
 * 打开新窗口
 * f:链接地址
 * n:窗口的名称
 * w:窗口的宽度
 * h:窗口的高度
 * s:窗口是否有滚动条，1：有滚动条；0：没有滚动条
 */
function openWin(f,n,w,h,s){
	sb = s == "1" ? "1" : "0";
	l = (screen.width - w)/2;
	t = (screen.height - h)/2;
	sFeatures = "left="+ l +",top="+ t +",height="+ h +",width="+ w
			+ ",center=1,scrollbars=" + sb + ",status=0,directories=0,channelmode=0";
	openwin = window.open(f , n , sFeatures );
	if (!openwin.opener)
		openwin.opener = self;
	openwin.focus();
	return openwin;
}

/*
 * 打开删除窗口
 */
function openDeleteDialog(url,confirmString){
	var c = confirmString;
	if(c == null || c == ''){
		c = "你确认要删除记录吗？";
	}
	if(confirm(c)){
		return window.showModalDialog(url,"window123","dialogHeight:234px;dialogWidth:271px;resizable:no;help:yes;status:no;scroll:no");
	}
	return false;
}

/*
 * 删除记录
 */
function del(url,info){
	if(openDeleteDialog(url,info)){
		window.location.reload(true);
	}
}


//医院行政区菜单切换调用方法
function changehos(hospid,hospurl){	
	var url=window.top.frames["middle"].frames["workflow"].location.href;
	var nowurl="";
    if(url.indexOf("?") == -1){
		nowurl = url +"?hospid="+hospid;
	}else{
		if(url.indexOf("myfirst") != -1){
			nowurl = url.substring(url.indexOf("myfirst")+8,url.length);	
			if(nowurl.indexOf("&") != -1){
				nowurl = nowurl.substring(0,nowurl.indexOf("&")) + "?" + nowurl.substring(nowurl.indexOf("&")+1,nowurl.length);
				if(nowurl.indexOf("hospid") != -1){
					nowurl = nowurl.substring(0,nowurl.indexOf("hospid")) + "hospid=" + hospid;
				} else {
					nowurl = nowurl + "&hospid=" + hospid;
				}
			}
			
		} else if(url.indexOf("hospid") != -1){
			nowurl = url.substring(0,url.indexOf("hospid")) + "hospid=" + hospid;
		} else {
			nowurl = url + "&hospid=" + hospid;
		}
	
		nowurl = nowurl.replace("&&","&");
		nowurl = nowurl.replace("?&","?");
	}				
    window.top.frames["middle"].frames["workflow"].location =nowurl;			
}
