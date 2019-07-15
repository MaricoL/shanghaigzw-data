//页面初始化
function init()
{
	getData(true);
	getData1(true);
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
	getData28(true);
	getData29(true);
	
}

//加载index对应的数据
function getData(index){
	console.log(basePath+"/property/_propertyIndex")
	$.ajax({
		url : basePath+"/property/_propertyIndex",
		type : "POST",
		data : {},
		async: false,
		success : function(data1) {
			console.log(data1)
			if(data1=="")
				return;
			var data=JSON.parse(data1);
			console.log(data[0].continuityCompany)
			$("#totalCompany").text(data[0].totalCompany);
			$("#continuityCompany").text(data[0].continuityCompany);
			$("#revokeCompany").text(data[0].revokeCompany);
			$("#fullCapitalCompany").text(data[0].fullCapitalCompany);
			$("#holdingCompany").text(data[0].holdingCompany);
			$("#shareholdingCompany").text(data[0].shareholdingCompany);
			$("#totalPerson").text(data[0].totalPerson);
			$("#totalSeal").text(data[0].totalSeal);
			$("#totalKnow").text(data[0].totalKnow);			
		}
	});
}


//累计所有案件
function getData1(index){
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



//获取主诉案件
function getData4(index){
	console.log(basePath+"/legal/_litigationCase")
	$.ajax({
		url : basePath+"/legal/_litigationCase",
		type : "POST",
		data : {},
		async: false,
		success : function(data) {
			console.log(data)
			if(data=="")
				return;
			var data=JSON.parse(data);
			
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


function getData28(index){
	console.log(basePath+"/legal/_getRoll")
	$.ajax({
		url : basePath+"/legal/_getRoll",
		type : "POST",
		data : {},
		async: false,
		success : function(data) {
			console.log(data)
			if(data=="")
				return;
			var data=JSON.parse(data);
			
			$("#test").text(data[0].test);
				
		}
	});
}



function getData29(){
	$.ajax({
		url : basePath+"/legal/_getRoll",
		type : "POST",
		data : {},
		async: false,
		success : function(data1) {
			createtest(data1,$("#caseList"))
			
		}
	});

}

function createtest(data1,obj){
	obj.empty();
	var li="";	
	if(data1==null || data1.length==0)
	{
		li="<li>无数据</li>"
	}
	else
		var data=JSON.parse(data1);
		for (var i = 0; i < data.length; i++) {
			li=li+"<li>"+data[i].test+"</li>";
		}
	obj.append(li);
}

