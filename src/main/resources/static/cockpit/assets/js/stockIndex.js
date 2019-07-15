$(document).ready(function(){
	initStockIndex();
	});




//页面初始化
function initStockIndex()
{
	getStockData();
	
}

//加载index对应的数据
function getStockData(){
	$.ajax({
		url : basePath+"/cockpit/_mainStockView",
		type : "POST",
		dataType : 'json',
		cache : false,
		async : false,
		data : {},
		success : function(data) {
			//console.log(data);
			var basePoint = null;
			var sList = null;
			if (data!="" && data!=null && data!="undefined") {
				basePoint = data.basePoint;
				sList = data.sList;
			} else {
				return;
			}
			if (basePoint!=""&& basePoint!=null && basePoint!="undefined") {
				if (sList!=null && sList.length>0) {
					console.log("size:::::::::::::::"+sList.length);
					$("#stockTopPint").attr("class","graph_mid_con2");
				} else {
					$("#stockTopPint").attr("class","graph_mid_con2_1");
				}
				
				$("#stockTopPint").html(basePoint.chName);
			}
			
			var childrenHtml = "<span class='graph_bottom_con1'>";
			$.each(sList,function(index,item){  //遍历键值对
				childrenHtml += "<div class='graph_bottom_con'>"+item.chName+"<span>"+item.stockPercent+"%</span>"+"</div>"
						
			});
			childrenHtml += "</span>";
			$("#stockChildren").append(childrenHtml);
		}
	});
}