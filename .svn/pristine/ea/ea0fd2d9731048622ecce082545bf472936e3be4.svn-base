var findAlarmHis = function() {
	var reVal;
	var jsonDoc = $.ajax( {
		type : "POST",
		url : "statisticsSafe_findAlarmCount.action",
		dataType : "json",
		cache : false,
		async : false,
		error : function(jsonObj) {
		},
		success : function(jsonObj) {
			if(jsonObj!=null&&jsonObj!=""){
				var levelOne = jsonObj.levelOne;
				var levelTwo = jsonObj.levelTwo;
				var levelThree = jsonObj.levelThree;
				var levelCountOne=jsonObj.levelCountOne;
				var levelCountTwo=jsonObj.levelCountTwo;
				var levelCountThree=jsonObj.levelCountThree;
				var criterionval = jsonObj.criterionval;
				//高危
				if (levelOne != null && levelOne > 0) {
					reVal = 1;
				} else {
					
					if (levelTwo != null && levelTwo > 0) {
						if (levelTwo >= criterionval) {
							reVal = 2;
						} else if((levelTwo < criterionval && levelThree>criterionval)|| 1==1 ){
							reVal = 3;
						}else{
							reVal = 5;
						}
					} else {
						if (levelThree != null && levelThree > 0) {
							if (levelThree < criterionval) {
								reVal = 4;
							}
						} else {
							reVal = 5;
						}
					}
				}
			}else{
				reVal = 5;
			}
		}
	});
	return reVal;
}


var findSpecialAlarmHis = function() {
	var reVal;
	var jsonDoc = $.ajax( {
		type : "POST",
		url : "statisticsSafe_findSpecialAlarmCount.action",
		cache : false,
		async : false,
		error : function(jsonObj) {
		},
		success : function(jsonObj) {
			reVal = jsonObj.toString();
		}
	});
	return reVal;
}

var findAlarmHisCount = function() {
	var reVal;
	var jsonDoc = $.ajax( {
		type : "POST",
		url : "statisticsSafe_findAlarmHisCount.action",
		cache : false,
		async : false,
		error : function(jsonObj) {
		},
		success : function(jsonObj) {
			reVal = jsonObj.toString();
		}
	});
	return reVal;
}
