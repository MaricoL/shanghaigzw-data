
	function initIndex(){
		
		getPlatformCompany();
		getData2(true);
		getData3(true);
		getData4(true);
		getData5(true);
		getData6(true);
		getData7(true);
		getData8(true);
		getData9(true);
		getData10(true);
		getData11(true);
		getData10(true);
		getData11(true);
		getData12(true);
		getData13(true);
		getData14(true);
		getData15(true);
		getData16(true);
		getData17(true);
		getData18(true);
		getData19(true);
		getCasePicture(true);
		getTotalcase(true);
//		getQueryCase(true);
		
//		getPropertyInformation(true);
		
	}
	
	
	//累计所有案件
	function getTotalcase(index){
		console.log(basePath+"/legal/_legalIndex")
		$.ajax({
			url : basePath+"/legal/_legalIndex",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				$("#total").text(data[0].total);
			}
		});
	}

	
	function getPlatformCompany(){
		$.ajax({
			url : basePath+"/legal/_getLegalCountByMoney",
			type : "POST",
			data : {},
			async: false,
			success : function(data1) {
				createTableLegal(data1,$("#table_law"))

			}
		});
	
	}
	function createTableLegal(data1,obj){
//		obj.empty();
		var tr="";	
		if(data1==null || data1.length==0)
		{
			tr="<tr><td style=\"text-align: center\">无数据</td><tr>"
		}
		else
			var data=JSON.parse(data1);
			for (var i = 0; i < data.length; i++) {
				tr=tr+"<tr>";
			    tr=tr+"<td style=\"width: 25%\">"+data[i].vc_comp_name+"</td>"+"<td style=\"width: 18%\">"+data[i].minshi+"</td>"+"<td style=\"width: 18%\">"+data[i].xingshi+"</td>"+"<td style=\"width: 18%\">"+data[i].feisu+"</td>"+"<td style=\"width: 18%\">"+data[i].xingzheng+"</td>";
				tr=tr+"</tr>";
			}
		obj.append(tr);
	}
	


	//本年所有案件
	function getData2(index){
		console.log(basePath+"/legal/_totalCaseIndex")
		$.ajax({
			url : basePath+"/legal/_totalCaseIndex",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#totalCaseByYear").text(data[0].totalCaseByYear);
					
			}
		});
	}
	

	//本年所有案件
	function getData3(index){
		console.log(basePath+"/legal/_caseEveryYear")
		$.ajax({
			url : basePath+"/legal/_caseEveryYear",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#tt").text(data[0].tt);
					
			}
		});
	}


	function getData4(){
		$.ajax({
			url : basePath+"/legal/_litigationCase",
			type : "POST",
			data : {},
			async: false,
			success : function(data1) {
				if(data1=="")
					return;
				var data=JSON.parse(data1);
				$("#zhusu").text(data[0].zhusu);
					
			}
		});
	}

	

	//获取被诉案件
	function getData5(index){
		console.log(basePath+"/legal/_BeiSuCase")
		$.ajax({
			url : basePath+"/legal/_BeiSuCase",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#beisu").text(data[0].beisu);
					
			}
		});
	}





	//获取主诉案件标的额
	function getData6(index){
		console.log(basePath+"/legal/_zsCaseMoney")
		$.ajax({
			url : basePath+"/legal/_zsCaseMoney",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#zsCaseMoney").text(data[0].zsCaseMoney);
					
			}
		});
	}





	//获取被诉案件标的额
	function getData7(index){
		console.log(basePath+"/legal/_bsCaseMoney")
		$.ajax({
			url : basePath+"/legal/_bsCaseMoney",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#bsCaseMoney").text(data[0].bsCaseMoney);
					
			}
		});
	}


	//申请人案件数
	function getData8(index){
		console.log(basePath+"/legal/_sqrCase")
		$.ajax({
			url : basePath+"/legal/_sqrCase",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#sqr").text(data[0].sqr);
					
			}
		});
	}


	//被申请人案件数
	function getData9(index){
		console.log(basePath+"/legal/_bsqrCase")
		$.ajax({
			url : basePath+"/legal/_bsqrCase",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#bsqr").text(data[0].bsqr);
					
			}
		});
	}


	//申请人的案件标金额
	function getData10(index){
		console.log(basePath+"/legal/_sqrCaseMoney")
		$.ajax({
			url : basePath+"/legal/_sqrCaseMoney",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#sqrMoney").text(data[0].sqrMoney);
					
			}
		});
	}
	//被申请人案件标的金额
	function getData11(index){
		console.log(basePath+"/legal/_bsqrCaseMoney")
		$.ajax({
			url : basePath+"/legal/_bsqrCaseMoney",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#bsqrMoney").text(data[0].bsqrMoney);
					
			}
		});
	}



	function getData12(index){
		console.log(basePath+"/legal/_getFinishCase")
		$.ajax({
			url : basePath+"/legal/_getFinishCase",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#banjie").text(data[0].banjie);
					
			}
		});
	}


	function getData13(index){
		console.log(basePath+"/legal/_getNoFinishCase")
		$.ajax({
			url : basePath+"/legal/_getNoFinishCase",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#zaiban").text(data[0].zaiban);
					
			}
		});
	}



	function getData14(index){
		console.log(basePath+"/legal/_getCaseTotalMoney")
		$.ajax({
			url : basePath+"/legal/_getCaseTotalMoney",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#caseMoney").text(data[0].caseMoney);
					
			}
		});
	}



	function getData15(index){
		console.log(basePath+"/legal/_getZaibanTotalMoney")
		$.ajax({
			url : basePath+"/legal/_getZaibanTotalMoney",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#zaibanMoney").text(data[0].zaibanMoney);
					
			}
		});
	}
	

	function getData16(index){
		console.log(basePath+"/legal/_getBanjieTotalMoney")
		$.ajax({
			url : basePath+"/legal/_getBanjieTotalMoney",
			type : "POST",
			data : {},
			async: false,
			success : function(data) {
				console.log(data)
				if(data=="")
					return;
				var data=JSON.parse(data);
				
				$("#banjieMoney").text(data[0].banjieMoney);
					
			}
		});
	}


	
	

//案件标的额分类图
function getData17(){
	$.ajax({
		url : basePath+"/legal/_getLegalTotalMoney",
		type : "POST",
		data : {},
		async: false,
		success : function(data1) {
			createTableLegalCaseMoney(data1,$("#table_allCaseMoney"))
			createEchartDistribution(data1)
		}
	});

}

function createTableLegalCaseMoney(data1,obj){
//	obj.empty();
	var tr="";	
	if(data1==null || data1.length==0)
	{
		tr="<tr><td style=\"text-align: center\">无数据</td><tr>"
	}
	else
		var data=JSON.parse(data1);
		for (var i = 0; i < data.length; i++) {
			tr=tr+"<tr>";
			 tr=tr+"</td>"+"<td style=\"width: 20%\">"+data[i].caseMoneyKind+"</td>"+"<td  style=\"width: 20%\">"+data[i].allCaseMoney+"</td>"+"<td  style=\"width: 20%\">"+data[i].money+"</td>"+"<td  style=\"width: 20%\">"+data[i].percent+"</td>";
			tr=tr+"</tr>";
		}
	obj.append(tr);
}



//案件标的额范围
function createEchartDistribution(data){
	var myChart=echarts.init(document.getElementById('LocationDistributionChart'));
	var data2=JSON.parse(data);
	var data1="";
	for (var i = 0; i < data2.length; i++) {
		data1+=data2[i].caseMoneyKind+",";			
	}
	if(data1.length>0){
		data1=data1.substr(0,data1.length-1);
	} 
	var data3 = data2.map(function(item,i){
		console.log(item)
		console.log(i)
		
		var labelline = 10;
		var length2 = 10;
//		小于100
		switch(i){
			
		case 1:
			labelline = 15;
			length2 = 50;
//			100-200
			break;
		case 2:
			labelline =40;
			length2 = 50;
//			200-300
			break;
		case 3:
			labelline = -10;
			length2 = 140;
			break;
		case 4:
			labelline =-40;
			length2 = 100;
//			大于500
			break;
				
		
			
			
		}
		
		return {
			value:item.allCaseMoney,
			name:item.caseMoneyKind,
			labelLine: { normal:{show: true, length:labelline,length2:length2}}
		}
	})
	var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:data3
		    },
		    series: [
		        {
		            name:'案件标额范围',
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
		       
		            type:'pie',
		            radius: ['45%', '65%'],
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
		                     /*   a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },*/
		                        // abg: {
		                        //     backgroundColor: '#333',
		                        //     width: '100%',
		                        //     align: 'right',
		                        //     height: 22,
		                        //     borderRadius: [4, 4, 0, 0]
		                        // },
		                 /*       hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },*/
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
		myChart.setOption(option)
}

/*
function createEchartDistribution(data){
	var myChart=echarts.init(document.getElementById('LocationDistributionChart'));
	var data2=JSON.parse(data);
	var data1="";
	for (var i = 0; i < data2.length; i++) {
		data1+=data2[i].caseMoneyKind+",";			
	}
	if(data1.length>0){
		data1=data1.substr(0,data1.length-1);
	} 
	var data3 = data2.map(function(item){
		return {
			value:item.allCaseMoney,
			name:item.caseMoneyKind
		}
	})
	var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:data3
		    },
		    series: [
		        {
		            name:'案件标的额范围',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '50%'],
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
		            name:'案件标的额范围',
		            type:'pie',
		            radius: ['40%', '65%'],
		            label: {
		                normal: {
		                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
		                    backgroundColor: '#eee',
		                    borderColor: '#aaa',
		                    borderWidth: 1,
		                    borderRadius: 4,
		                  
		                    rich: {
		                        a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },
		                      
		                        hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },
		                        b: {
		                            fontSize: 16,
		                            lineHeight: 33
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
		myChart.setOption(option);
}*/



//在办办结案件数量饼图
function getData18(){
	$.ajax({
		url : basePath+"/legal/_getCaseCountInfo",
		type : "POST",
		data : {},
		async: false,
		success : function(data1) {
			
			createGetCaseInfo1(data1)
		}
	});

}


function createGetCaseInfo1(data){
	

		var myChart=echarts.init(document.getElementById('LocationDistributionChart3'));
		var data2=JSON.parse(data);
		var data1="";
		for (var i = 0; i < data2.length; i++) {
			data1+=data2[i].casetype+",";			
		}
		if(data1.length>0){
			data1=data1.substr(0,data1.length-1);
		} 
		var data3 = data2.map(function(item,i){
			
			var labelline = 25;
			var length2 = 10;
//			
			switch(i){
			case 1:
				labelline = 1;
				length2 =10;
				break;
			}
			return {
				value:item.mm,
				name:item.casetype,
				labelLine: { normal:{show: true, length:labelline,length2:length2}}
			}
		})
		var option = {
			    tooltip: {
			        trigger: 'item',
			        formatter: "{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data:data3
			    },
			    series: [
			        {
			            name:'案件数量情况',
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
			         /*   name:'行业分布',*/
			            type:'pie',
			            radius: ['45%', '65%'],
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
			                     /*   a: {
			                            color: '#999',
			                            lineHeight: 22,
			                            align: 'center'
			                        },*/
			                        // abg: {
			                        //     backgroundColor: '#333',
			                        //     width: '100%',
			                        //     align: 'right',
			                        //     height: 22,
			                        //     borderRadius: [4, 4, 0, 0]
			                        // },
			                 /*       hr: {
			                            borderColor: '#aaa',
			                            width: '100%',
			                            borderWidth: 0.5,
			                            height: 0
			                        },*/
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
			
	/*var myChart=echarts.init(document.getElementById('LocationDistributionChart3'));
	var data2=JSON.parse(data);
	var data1="";
	for (var i = 0; i < data2.length; i++) {
		data1+=data2[i].casetype+",";			
	}
	if(data1.length>0){
		data1=data1.substr(0,data1.length-1);
	} 
	var data3 = data2.map(function(item){
		return {
			value:item.mm,
			name:item.casetype
		}
	})
	var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:data3
		    },
		    series: [
		        {
		            name:'牛奶集团案件情况',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '50%'],
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
		            name:'牛奶集团案件情况',
		            type:'pie',
		            radius: ['40%', '65%'],
		            label: {
		                normal: {
		                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
		                    backgroundColor: '#eee',
		                    borderColor: '#aaa',
		                    borderWidth: 1,
		                    borderRadius: 4,
		                   
		                    rich: {
		                        a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },
		                        
		                        hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },
		                        b: {
		                            fontSize: 16,
		                            lineHeight: 33
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
		};*/
	


		myChart.setOption(option);
}



//在办办结案件标的金额
function getData19(){
	$.ajax({
		url : basePath+"/legal/_getAllCaseMoney",
		type : "POST",
		data : {},
		async: false,
		success : function(data1) {
			
			createAllCase(data1)
		}
	});

}


function createAllCase(data){
	
	var myChart=echarts.init(document.getElementById('LocationDistributionChart4'));
	var data2=JSON.parse(data);
	var data1="";
	for (var i = 0; i < data2.length; i++) {
		data1+=data2[i].caseTypeMoney+",";			
	}
	if(data1.length>0){
		data1=data1.substr(0,data1.length-1);
	} 
	var data3 = data2.map(function(item){
		var labelline = 15;
		var length2 = 10;
		
		switch(i){
		case 1:
			labelline = -5;
			length2 = 20;
			break;
		}
		return {
			value:item.zbCaseMoney,
			name:item.caseTypeMoney,
			labelLine: { normal:{show: true, length:labelline,length2:length2}}
		}
	})
	var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:data3
		    },
		    series: [
		        {
		            name:'案件金额情况',
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
		         /*   name:'行业分布',*/
		            type:'pie',
		            radius: ['45%', '65%'],
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
		                     /*   a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },*/
		                        // abg: {
		                        //     backgroundColor: '#333',
		                        //     width: '100%',
		                        //     align: 'right',
		                        //     height: 22,
		                        //     borderRadius: [4, 4, 0, 0]
		                        // },
		                 /*       hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },*/
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
	/*var myChart=echarts.init(document.getElementById('LocationDistributionChart4'));
	var data2=JSON.parse(data);
	var data1="";
	for (var i = 0; i < data2.length; i++) {
		data1+=data2[i].caseTypeMoney+",";			
	}
	if(data1.length>0){
		data1=data1.substr(0,data1.length-1);
	} 
	var data3 = data2.map(function(item){
		return {
			value:item.zbCaseMoney,
			name:item.caseTypeMoney
		}
	})
	var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:data3
		    },
		    series: [
		        {
		            name:'牛奶集团案件情况',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '50%'],
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
		            name:'牛奶集团案件情况',
		            type:'pie',
		            radius: ['40%', '65%'],
		            label: {
		                normal: {
		                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
		                    backgroundColor: '#eee',
		                    borderColor: '#aaa',
		                    borderWidth: 1,
		                    borderRadius: 4,
		                  
		                    rich: {
		                        a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },
		                       
		                        hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },
		                        b: {
		                            fontSize: 16,
		                            lineHeight: 33
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
		};*/
	
		myChart.setOption(option);
}

//案件分类图
function getCasePicture(){
	$.ajax({
		url : basePath+"/legal/_getPicture",
		type : "POST",
		data : {},
		async: false,
		success : function(data1) {
			
			createCasePicture(data1)
		}
	});

}

function createCasePicture(data){
	var myChart=echarts.init(document.getElementById('LocationDistributionChart5'));
	var data2=JSON.parse(data);
	var data1="";
	for (var i = 0; i < data2.length; i++) {
		data1+=data2[i].kind+",";			
	}
	if(data1.length>0){
		data1=data1.substr(0,data1.length-1);
	} 
	var data3 = data2.map(function(item,i){
		console.log(item)
		console.log(i)
		var labelline = 15;
		var length2 = 30;
		switch(i){
		case 1:
			labelline = 32;
			length2 = 70;
//			刑事案件
			break;
		case 2:
			labelline =0;
			length2 = 10;
			break;
//			非诉
		case 3:
			labelline = 45;
			length2 = 0;
//			行政
			break;
		}
		return {
			value:item.ms,
			name:item.kind,
			labelLine: { normal:{show: true, length:labelline,length2:length2}}
		}
	})
	var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:data3
		    },
		    series: [
		        {
		            name:'案件类型分布',
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
		         /*   name:'行业分布',*/
		            type:'pie',
		            radius: ['45%', '65%'],
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
		                     /*   a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },*/
		                        // abg: {
		                        //     backgroundColor: '#333',
		                        //     width: '100%',
		                        //     align: 'right',
		                        //     height: 22,
		                        //     borderRadius: [4, 4, 0, 0]
		                        // },
		                 /*       hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },*/
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
		myChart.setOption(option)
}
/*
function createCasePicture(data){
	var myChart=echarts.init(document.getElementById('LocationDistributionChart5'));
	var data2=JSON.parse(data);
	var data1="";
	for (var i = 0; i < data2.length; i++) {
		data1+=data2[i].kind+",";			
	}
	if(data1.length>0){
		data1=data1.substr(0,data1.length-1);
	} 
	var data3 = data2.map(function(item){
		return {
			value:item.ms,
			name:item.kind
		}
	})
	var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:data3
		    },
		    series: [
		        {
		            name:'牛奶集团案件情况',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '50%'],
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
		            name:'牛奶集团案件情况',
		            type:'pie',
		            radius: ['40%', '65%'],
		            label: {
		                normal: {
		                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
		                    backgroundColor: '#eee',
		                    borderColor: '#aaa',
		                    borderWidth: 1,
		                    borderRadius: 4,
		                  
		                    rich: {
		                        a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },
		                       
		                        hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },
		                        b: {
		                            fontSize: 16,
		                            lineHeight: 33
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
		myChart.setOption(option);
}*/


	
	