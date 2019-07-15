
var charts = [];
//页面初始化
function init()
{
	getData1();// 企业总体情况数据获取
	getData2();// 平台公司统计
	getData3();// 企业地域分布统计
	getData4();// 企业行业分布统计
	getData5();// 企业到期/过期
	getData6();// 企业差异对比
    // 适应bootstrap变换尺寸
    window.onresize = function () {
        for(var i = 0; i < charts.length; i++){
            charts[i].resize();
        }
    }
}



//企业总体情况数据获取
function getData1(){
	$.ajax({
		url : basePath+"/property/getOverallSituation",
		type : "POST",
		data : {},
		//async: false,
		success : function(data1) {
			if(data1=="")
				return;
			var data=JSON.parse(data1);
			//$("#countryCompany").text(data[0].totalCompany);
			$("#totalCompany").text(data[0].totalCompany);
			$("#fullCapitalCompany").text(data[0].fullCapitalCompany);
			$("#holdingCompany").text(data[0].holdingCompany);
			$("#shareholdingCompany").text(data[0].shareholdingCompany);
			$("#continuityCompany").text(data[0].continuityCompany);
			$("#revokeCompany").text(data[0].revokeCompany);
			$("#departmentalizedCompany").text(data[0].departmentalizedCompany);		
		}
	});
}

// 平台公司统计
function getData2(){
	$.ajax({
		url : basePath+"/property/getPlatformCompany",
		type : "POST",
		data : {},
		//async: false,
		success : function(data1) {
			createTableCq(data1,$("#table_cq"))
		}
	});

}

function createTableCq(data1,obj){
	var tr="";	
	if(data1==null || data1.length==0)
	{
		tr="<tr><td style=\"text-align: center\">无数据</td><tr>"
	}
	else
		var data=JSON.parse(data1);
		for (var i = 0; i < data.length; i++) {
			if (i%2==1) {
				tr=tr+"<tr class='success'>";
			} else {
				tr=tr+"<tr>";
			}
			
		    tr=tr+"<td>"+data[i].company_name+"</td>"+"<td>"+data[i].totalCompany+"</td>"+"<td>"+data[i].fullCapitalCompany+"</td>"+"<td>"+data[i].holdingCompany+"</td>"+"<td>"+data[i].shareholdingCompany+"</td>"+"<td>"+data[i].continuityCompany+"</td>"+"<td>"+data[i].revokeCompany+"</td>";
			tr=tr+"</tr>";
		}
	obj.append(tr);
}

//企业地域分布统计
function getData3(){
	$.ajax({
		url : basePath+"/index/getBusinessDistribution",
		type : "POST",
		data : {},
		//async: false,
		success : function(data1) {
			if(data1=="")
				return;
			createEchartDistribution(data1);
		}
	});
}

/*function createEchartDistribution(data){
	var myChart=echarts.init(document.getElementById('LocationDistributionChart'));
	var data2=JSON.parse(data);
	var data1="";
	for (var i = 0; i < data2.length; i++) {
		data1+=data2[i].name+",";			
	}
	if(data1.length>0){
		data1=data1.substr(0,data1.length-1);
	} 

	var data3 = data2.map(function(item,i){
		var labelline = 10;
		var length2 = 10;
		switch(i){
			case 1:
				labelline = 0;
				length2 = 70;
				break;
			case 2:
				labelline =11;
				length2 = 5;
				break;
			case 3:
				labelline = 30;
				length2 = 70;
				break;
			case 4:
				labelline = 40;
				length2 = 5;
				break;
		}
		return {
			value:item.number,
			name:item.name,
			labelLine: { normal:{show: true, length:labelline,length2:length2}}
			
		}
	});
	var labelList = [];
	for (var i = 0; i < data3.length; i++) {
		labelList.push(data3[i].name);
	}
	var option = {
	        title : {
	            text: '企业地域分布图',
	            x:'center',
	            y:10
	        },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'right',
		        data:labelList
		    },
		    series: [
		        {
		            name:'地域分布',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '40%'],
		            label: {
		                normal: {
		                    position: 'inner'
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            }
		        },
		        {
		            name:'地域分布',
		            type:'pie',
		            radius: ['40%', '65%'],
		            type: 'pie',
		            radius : '65%',
		            center: ['50%', '50%'],
		            label: {
		                normal: {
		                    formatter: '{b|{b}：}{c}  {per|{d}%}  ',
		                    backgroundColor: '#eee',
		                    borderColor: '#aaa',
		                    borderWidth: 1,
		                    borderRadius: 4,
		                    // shadowBlur:3,
		                    // shadowOffsetX: 2,
		                    // shadowOffsetY: 2,
		                    // shadowColor: '#999',
		                     padding: [0,2],
		                    rich: {
		                        a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },
		                        // abg: {
		                        //     backgroundColor: '#333',
		                        //     width: '100%',
		                        //     align: 'right',
		                        //     height: 22,
		                        //     borderRadius: [4, 4, 0, 0]
		                        // },
		                        hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },
		                        b: {
		                            fontSize: 12,
		                            lineHeight: 25
		                        },
		                        per: {
		                            color: '#eee',
		                            backgroundColor: '#334455',
		                            padding: [2, 4],
		                            borderRadius: 2
		                        }
		                    }
		                }
		            },
		            data:data3
		        }
		    ]
		};
		charts.push(myChart);
		myChart.setOption(option);
}*/

//企业行业分布统计
function getData4(){
	$.ajax({
		url : basePath+"/index/getBusinessIndustry",
		type : "POST",
		data : {},
		//async: false,
		success : function(data1) {
			if(data1=="")
				return;
			createEchartIndustry(data1);
		}
	});

}

/*function createEchartIndustry(data){
	var myChart=echarts.init(document.getElementById('LocationDistributionChart2'));
	var data2=JSON.parse(data);
	var data3 = data2.map(function(item){
		return {
			value:item.number,
			name:item.name
		}
	});
	var labelList = [];
	for (var i = 0; i < data3.length; i++) {
		labelList.push(data3[i].name);
	}
	var option = {
	        title : {
	            text: '企业行业分布图',
	            x:'center',
	            y:10
	        },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'right',
		        data:labelList
		    },
		    series: [
		        {
		            name:'行业分布',
		            type:'pie',
		            selectedMode: 'single',
		            //radius: [0, '40%'],
		
		            label: {
		                normal: {
		                    position: 'inner'
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            }
		        },
		        {
		            name:'行业分布',
		            type:'pie',
		            radius: ['40%', '65%'],
		            type: 'pie',
		            radius : '65%',
		            center: ['50%', '50%'],
		            label: {
		                normal: {
		                    formatter: '{b|{b}：}{c}  {per|{d}%}  ',
		                    backgroundColor: '#eee',
		                    borderColor: '#aaa',
		                    borderWidth: 1,
		                    borderRadius: 4,
		                    // shadowBlur:3,
		                    // shadowOffsetX: 2,
		                    // shadowOffsetY: 2,
		                    // shadowColor: '#999',
		                     padding: [0, 2],
		                    rich: {
		                        a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },
		                        // abg: {
		                        //     backgroundColor: '#333',
		                        //     width: '100%',
		                        //     align: 'right',
		                        //     height: 22,
		                        //     borderRadius: [4, 4, 0, 0]
		                        // },
		                        hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },
		                        b: {
		                            fontSize: 12,
		                            lineHeight: 25
		                        },
		                        per: {
		                            color: '#eee',
		                            backgroundColor: '#334455',
		                            padding: [2, 4],
		                            borderRadius: 2
		                        }
		                    }
		                }
		            },
		            data:data3
		        }
		    ]
		};
		charts.push(myChart);
		myChart.setOption(option)
}*/

//企业到期/过期
function getData5(){
	$.ajax({
		url : basePath+"/index/getExpiredCompCount",
		type : "POST",
		data : {},
		//async: false,
		success : function(data1) {
			if(data1=="")
				return;
			createEchartExpiredCompCount(data1);
		}
	});
}

function createEchartExpiredCompCount(data){
	//预警
    var warnChart = echarts.init(document.getElementById("chart-warn"));
    var data2=JSON.parse(data);
	var data1="";
	var data3 = data2.map(function(item){
		return {
			value:item.value,
			name:item.name
		}
	});
	var labelList = [];
	for (var i = 0; i < data3.length; i++) {
		labelList.push(data3[i].name);
	}
    var warnoption = {
        title : {
            text: '企业到期/过期分布图',
            x:'center',
            y:10
        },
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data:labelList
        },
        series:[{
            type: 'pie',
            radius : '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            label:{
               // formatter:"{b} {d}%",
                formatter: "{b}：{c}  ({d}%)",
            },
            
            data: data3
        }
        ]
    };
    charts.push(warnChart);
    warnChart.setOption(warnoption);

}
//企业差异对比
function getData6(){
	$.ajax({
		url : basePath+"/index/getComparedCompCount",
		type : "POST",
		data : {},
		//async: false,
		success : function(data1) {
			if(data1=="")
				return;
			createEchartComparedCompCount(data1);
		}
	});
}

function createEchartComparedCompCount(data){
	//预警
    var compareChart = echarts.init(document.getElementById("chart-compare"));
    var data2=JSON.parse(data);
	var data1="";
	var data3 = data2.map(function(item){
		return {
			value:item.value,
			name:item.name
		}
	});
	var labelList = [];
	for (var i = 0; i < data3.length; i++) {
		labelList.push(data3[i].name);
	}
    var compareOption = {
        title : {
            text: '企业差异对比图',
            x:'center',
            y:10
        },
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data:data1 ,
        },
        series:[{
            type: 'pie',
            radius : '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            label:{
               // formatter:"{b} {d}%",
                formatter: "{b}：{c}  ({d}%)",
            },
            
            data: data3
        }
        ]
    };
    charts.push(compareChart);
    compareChart.setOption(compareOption);

}

//地域分布统计
function createEchartDistribution(data){
	var areaChart = echarts.init(document.getElementById("LocationDistributionChart"));
	charts.push(areaChart);
	var data2=JSON.parse(data);
		var data3 = data2.map(function(item,i){
			return {
				value:item.number,
				name:item.name
			}
		});
		var labelList = [];
		for (var i = 0; i < data3.length; i++) {
			labelList.push(data3[i].name);
		}
	var areaOption = { color: ['#FF0000'],
	    title : {
	        text: '企业地域分布图',
	        x:'center',
	        y:10
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : labelList,
	            axisTick: {
	                alignWithLabel: true
	            },
	            axisLine: {
	                   lineStyle: {
	                       type: 'solid',
	                       color: '#000000',//左边线的颜色
	                       width:'1'//坐标线的宽度
	                   }
	               },
	               axisLabel: {
	                   interval:0,
	                   rotate:40,
	                   textStyle: {
	                       color: '#000000',//坐标值得具体的颜色

	                   }
	               }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'地域分布数',
	            type:'bar',
	            barWidth: '60%',
	            data:data3
	        }
	    ]};
	areaChart.setOption(areaOption);
}

//行业分布统计
function createEchartIndustry(data){
	var indusChart = echarts.init(document.getElementById("LocationDistributionChart2"));
	charts.push(indusChart);
	var data2=JSON.parse(data);
		var data3 = data2.map(function(item,i){
			return {
				value:item.number,
				name:item.name
			}
		});
		var labelList = [];
		for (var i = 0; i < data3.length; i++) {
			labelList.push(data3[i].name);
		}
	var indusOption = { color: ['#3398DB'],
	    title : {
	        text: '企业行业分布图',
	        x:'center',
	        y:10
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : labelList,
	            axisTick: {
	                alignWithLabel: true
	            },
	            axisLine: {
	                   lineStyle: {
	                       type: 'solid',
	                       color: '#000000',//左边线的颜色
	                       width:'1'//坐标线的宽度
	                   }
	               },
	               axisLabel: {
	                   interval:0,
	                   rotate:40,
	                   textStyle: {
	                       color: '#000000',//坐标值得具体的颜色

	                   }
	               }

	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'行业分布数',
	            type:'bar',
	            barWidth: '60%',
	            data:data3
	        }
	    ]};
	indusChart.setOption(indusOption);
}

