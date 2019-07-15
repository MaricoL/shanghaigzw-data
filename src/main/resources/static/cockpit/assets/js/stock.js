/**
 * 
 */

  
	

	  function getjOrgChart(){
		  
		  
		  $.ajax({
	        	url : basePath+"/cockpit/_getManageTree",
	            type: 'POST',
	            dataType: 'JSON',
	            data: $("#form3").serialize(),
	            cache : false,
				async : false,
	            success: function(result){
	            	console.log(result);
	                var showlist = $("<ul id='org' style='display:none'></ul>");//style='display:none'
	                var data=[];
	                data.push(result);
	                showall(data, showlist);
	                console.log(showlist);
	                $("#jOrgChart").append(showlist);
	                $("#org").jOrgChart( {
	                    chartElement : '#jOrgChart',//指定在某个dom生成jorgchart
	                    dragAndDrop : false //设置是否可拖动
	                });

	            }
	        });
		  
		  
		  
	  }
	  
	  
	  
	    function showall(menu_list, parent) {
	        $.each(menu_list, function(index, val) {
	        	
	            if(val.children!=null && val.children.length > 0){
	                var li = $("<li></li>");
	                li.append("<a href='javascript:void(0)' onclick=getOrgId("+val.id+");>"+val.name+"</a>").append("<ul></ul>").appendTo(parent);
	                //递归显示
	                showall(val.children, $(li).children().eq(1));
	            }else{
	                $("<li></li>").append("<a href='javascript:void(0)' onclick=getOrgId("+val.id+");>"+val.name+"</a>").appendTo(parent);
	            }
	        });

	    }
	    
	    //更新数据
		function refreshorgData(){
			// 释放图表实例，释放后实例不再可用。
			//myChart.dispose();
			$("#jOrgChart").html("");
			getjOrgChart();
		}