
	function Yjbopen(divID,divTU)
		{ 
			var mytr = document.getElementById(divID);
			var mytr2 = document.getElementById(divTU);
		if (mytr.style.display=="")  {
			mytr.style.display="none"; 
			mytr2.src='manager/images/imgs/sanjiao_xia.gif';
		 }else {  
			mytr.style.display="";
			mytr2.src='manager/images/imgs/sanjiao_shang.gif';
		   }
		   
		}
