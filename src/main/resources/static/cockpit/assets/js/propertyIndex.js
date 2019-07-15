//页面初始化
function init()
{
	getData(true);
	
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