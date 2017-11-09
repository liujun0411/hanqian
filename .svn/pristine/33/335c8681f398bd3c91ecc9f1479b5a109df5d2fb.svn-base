$(function(){
	var gaojingCounts1;
	var gaojingTishi1;
	var count = findAlarmHighcharCount();
	//var count = undefined;
	//var count ={'a':'0','b':'0','c':'0'};
	if(count!=undefined){
	 var gaojingColor1 = gaojingColor(count);
		 gaojingCounts1 = gaojingCounts(count);
		 gaojingTishi1 = gaojingTishi(count);
	}else{
		 gaojingCounts1 = 0;
		 gaojingTishi1 = "暂无数据";
	}
	highchartsUI(gaojingCounts1,gaojingColor1,gaojingTishi1);
});
//   获取颜色值
gaojingColor = function(count){
	var color;
	if(count.a==0&&count.b==0&&count.c==0){
		color = "#55BF3B";   // 绿色
	}else if(count.a!=0){
		color = "#DF5353";   // 红色
	}else if(count.b!=0){
		color = "#FF9A00";   //橘色
	}else if(count.c!=0){
		color = "#DDDF0D";   // 黄色#DDDF0D
	}
	return color;
}
//  获取 告警的个数
//一级告警  *10 二级告警 *5 三级告警不动
gaojingCounts = function(count){
	var counts;
	if(count.a==0&&count.b==0&&count.c==0){
		counts = 100;
	}else if(count.a!=0){
		counts = parseInt(count.a)*10;
	}else if(count.b!=0){
		counts = parseInt(count.b)*5;
	}else if(count.c!=0){
		counts = parseInt(count.c);
	}
	return counts;
}
//获取提示告警信息
gaojingTishi = function (count){
	var prompt;
	if(count.a==0&&count.b==0&&count.c==0){
		prompt ="<span style='color:#55BF3B;font-size:24px;'>安全</span></br>无告警";
	}else if(count.a!=0){
		prompt ="<span style='color:#DF5353;font-size:24px;'>危险</span></br>一级告警&nbsp;(&nbsp;"+count.a+"&nbsp;)";
	}else if(count.b!=0){
		prompt ="<span style='color:#FF9A00;font-size:24px;'>警告</span></br>二级告警&nbsp;(&nbsp"+count.b+"&nbsp;)";
	}else if(count.c!=0){
		prompt ="<span style='color:#DDDF0D;font-size:24px;'>提醒</span></br>三级告警&nbsp;(&nbsp{y}&nbsp;)";
	}
	return prompt;
 }

/**
 * 获取告警等级个数
 * 
 */
findAlarmHighcharCount=function() {
	var gaojingCountJSON;
	var jsonDoc = $.ajax( {
		type : "POST",
		url : "statisticsSafe_findAlarmHighchartsCount.action",
		dataType : "json",
		cache : false,
		async : false,
		error : function(jsonObj) {
		},
		success : function(jsonObj) {
			gaojingCountJSON=jsonObj;
	     }
	});
	return gaojingCountJSON;
}
highchartsUI = function (gaojingCounts1,gaojingColor1,gaojingTishi1) {
var gaugeOptions = {
        chart: {
            type: 'solidgauge' //选择哪种图像
        },
        title: null,
        pane: {
            center: ['50%', '85%'],
            size: '140%',      //调节大小
            startAngle: -90,   //左边的延伸度
            endAngle: 90,      //右边的延伸度
            background: {
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
                innerRadius: '60%',
                outerRadius: '100%',
                shape: 'arc'
            }
        },
        tooltip: {
            enabled: false    //启用或禁用提示框
        },
		/**
		  *  tickPixelInterval  显示表盘内部刻度的
		  */
        yAxis: {
            stops: [
                   [1,gaojingColor1]
            ],
            lineWidth: 0,   //轴线的宽度
            minorTickInterval: null,  //坐标轴的值允许的最小刻度间隔。比如缩放轴用来显示每天的数据时可以用来阻止轴上显示小时的时间。
            tickPixelInterval: 400,   //和这个无关
            tickWidth: 0,
            title: {
                y: -70
            },
            labels: {
                y: 16
            }
        },

        plotOptions: {
            solidgauge: {
                dataLabels: {
                    y: 5,
                    borderWidth: 0,
                    useHTML: true
                }
            }
        }
    };

	/**
	  *  title.text 元素代表：高危这字不是固定的分别： 高危 中危 危险 告警 安全
	  *  min 指标左下显示的数字
	  *  max 指标右下显示的数字
	  */
    $('#container-speed').highcharts(Highcharts.merge(gaugeOptions, {
        yAxis: {
          min: 0,
          max: 100,
         /* *  title: {
         *          text: '高危'     
         *          }
		 */
        },

        credits: {
            enabled: false
        },
          /**
			*   规则：如果有1级告警，就显示1级告警，有2级告警就显示2级告警，以此类推
			*/
        series: [{
            name: 'Speed',
            data: [gaojingCounts1],
            dataLabels: {
                format:
                	'<div style="text-align:center"><span style="font-size:14px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">'+
                    gaojingTishi1
                    +'</span><br/>' +
                       '</div>'
            },
        }]
    }));
}