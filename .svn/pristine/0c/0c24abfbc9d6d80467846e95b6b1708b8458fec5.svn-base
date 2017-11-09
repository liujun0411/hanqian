
var hospLogo = "";
var hospName = "";

function getHospLogo(hospId){
	if(hospId.length >0){
		switch(hospId){
		case "lh":
			hospLogo = "images/imgbg/fenzhan_tu_longhuan_logo.png";
			hospName = "龙华医院";
			break;
		case "xk":
			hospLogo = "images/imgbg/fenzhan_tu_xiongke_logo.png";
			hospName = "胸科医院";
			break;
		case "rj":
			hospLogo = "images/imgbg/fenzhan_tu_renji_logo.png";
			hospName = "仁济医院";
			break;
		case "fk":
			hospLogo = "images/imgbg/fenzhan_tu_feike_logo.png";
			hospName = "肺科医院";
			break;
			default:
				break;
		}
	}
	document.write("<img src=" + hospLogo + " alt=" + hospName + " />");
}

function LoginOut(roleMessage){
	var loginFlag = "";
	if(roleMessage.length >0 && roleMessage.indexOf("2") > -1){
		//医院用户
		loginFlag = "?loginFlag=hosp";
	}
	if(window.confirm('是否确定注销?')){
		window.location='${pageContext.request.contextPath}/LoginAction_LoginOut.action'+loginFlag;
	}
}

function goIndex(skUrl){
	if(skUrl != ""){
		window.location=skUrl;
	} else {
		window.location='showFrame_showHospFlow';
	}
}

function basic(str,roleMessage,loginFlag){
	if(roleMessage.indexOf("2") > -1){
	//医院用户
		if(loginFlag == "hosp")
			location.href='LoginAction_backHosp.action?showFlag='+str;
		else
			location.href='manager/showFrame_showHospFlow.action?showFlag='+str;
	} else {
	//其他用户
		//location.href='manager/webmap.jsp?showFlag='+str;
		location.href='manager/showFrame_showFlow.action?showFlag='+str;
	}
}

function showinfo(){
	document.getElementById("admin_nr_right").style.overflow="auto";
}

function getHospTree(){
	$("#gai_daohangyiyuan").css("display","block");
	$("#hospDaohang").css("display", "none");
}

function hiddenHospTree(){
	$("#gai_daohangyiyuan").css("display","none");
	$("#hospDaohang").css("display", "block");
}