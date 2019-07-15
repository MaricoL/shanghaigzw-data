/**
 * 
 */

var container;
var zoom;
var rootData;
var showUscd;
		$(document).ready(function() {
			
			resizeScreen();
			var companyUscd=$("#compId").val();
			var companyName=$("#compId option:selected").text();
			getData(companyUscd,companyName);
			showDetail(companyUscd);
			$(".btn_search").click(function(){
				var companyUscd=$("#compId").val();
				var companyName=$("#compId option:selected").text();
				showDetail(companyUscd);
				getData(companyUscd,companyName);
				showUscd=companyUscd;
			});
		});
		function changeScreen(dom) {
			if (!isFullScreen()) {
				$(dom).find('i').attr('class', 'fa fa-compress');
				launchFullScreen($('#screenArea')[0]);
			} else {
				$(dom).find('i').attr('class', 'fa fa-expand');
				exitFullScreen();
			}
		}

		function maoScale(type) {
			var scale = zoom.scale();
			if (type == 1) {
				scale += 0.3;
			} else if (type == 2) {
				scale -= 0.3;
			}
			if (scale >= 0.3 && scale <= 2) {
				zoom.scale(scale);
				container.transition().duration(500).attr(
						"transform",
						"translate(" + zoom.translate() + ")scale("
								+ zoom.scale() + ")");
			}
		}

		function maoRefresh() {
			draw(rootData);
		}

		function getData(companyUscd,companyName) {
			var title=$.trim(companyName)+"企业图谱";
			$(".TreeTitle").text(title);
			
			$.get(basePath+"/indexMap/listcqEnterpriseAtlas", {"compUscd":companyUscd,"compName":companyName}, function(data) {
				rootData = eval("(" + data + ")");
				traverseTreeId(rootData);
				draw(rootData);
			});
		}

		function draw(root) {
			//将实体聚集成树状图
			tree = d3.layout.cluster().size([ 360, 600 ]).separation(
					function(a, b) {
						return (a.parent == b.parent ? 2 : 3) / a.depth;
					});
			$("#main").empty();
			svg = d3.select("#main").append("svg").attr("xmlns",
					"http://www.w3.org/2000/svg");
			svg.empty();
			
			d3.select('svg').attr('width', $('#main').width());
			d3.select('svg').attr('height', $('#main').height());

			//drawLegend(svg);
			//drawWaterMark(svg);


			container = svg.append("g");
			linkContainer = container.append("g");
			
			//创建缩放行为 0.4为最小 2为最大
			zoom = d3.behavior.zoom().scaleExtent([ 0.4, 2 ])
					.on("zoom", zoomed);
			svg.call(zoom);

			initLocation();

			function zoomed() {
				//translate：当前的平移偏移量  scale：尺寸 
				container.attr("transform", "translate(" + d3.event.translate
						+ ")scale(" + d3.event.scale + ")");
			}

			function initLocation() {
				zoom
						.translate([ svg.attr('width') / 2,
								svg.attr('height') / 2 ]);
				zoom.scale(0.8);
				container.attr("transform", "translate(" + zoom.translate()
						+ ")scale(" + zoom.scale() + ")");
				/* shuiying.attr("transform", "translate(" + zoom.translate()
						+ ")scale(" + zoom.scale() + ")"); */
			}

			drawTree(root);
		}

		function drawTree(data) {
			//新建一个径向对角线生成器
			var diagonal = d3.svg.diagonal.radial().projection(function(d) {
				return [ d.y, d.x / 180 * Math.PI ];
			});
			data.x0 = 0;
			data.y0 = 0;
			// 计算簇布局并返回节点数组
			nodes = tree.nodes(rootData);
			//技术树节点之间的父子连接
			links = tree.links(nodes);

			var pathLength = 150;
			if (nodes.length > 100) {
				pathLength = 200;
			}
			//d.depth 猜测是层级数
			nodes.forEach(function(d) {
				if (d.depth > 2) {
					d.y = d.depth * (d.depth / 2) * 150;
				} else {
					d.y = d.depth * pathLength;
				}
			});

			//var shuiyingImg = shuiying.append("image")
			//.attr("xlink:href", "/material/theme/chacha/cms/v2/images/shuiying.png").attr("x", -220).attr("y", -80);

			var linkUpdate = linkContainer.selectAll(".link").data(links,
					function(d) {
						return d.target.id;
					});
			//为缺失的元素返回占位符
			var linkEnter = linkUpdate.enter();
			//返回不再需要的元素
			var linkExit = linkUpdate.exit();
			
			//线属性
			linkEnter.append("path").attr("class", "link").attr("d",
					function(d) {
						var o = {
							x : data.x0,
							y : data.y0
						};
						return diagonal({
							source : o,
							target : o
						});
					}).transition().duration(500).attr("d", diagonal);
			//线属性
			linkUpdate.transition()
					.duration(500)
					.attr("d", diagonal)
					.attr("style",function(d){
						return "fill: none; stroke-opacity: 1; stroke: "+getColor(d.source.Category,d)+"; stroke-width: 1px;";
					});

			linkExit.transition().duration(500).attr("d", function(d) {
				var o = {
					x : data.x,
					y : data.y
				};
				return diagonal({
					source : o,
					target : o
				});
			}).remove();


			//圆
			var nodeUpdate = container.selectAll(".node").data(nodes,
					function(d) {
						return d.id;
					});
			var nodeEnter = nodeUpdate.enter();
			var nodeExit = nodeUpdate.exit();

			var enterNodes = nodeEnter.append("g").attr("class", function(d) {
				return "node";
			}).attr("transform", function(d) {
				return "translate(" + project(data.x0, data.y0) + ")";
			});
			enterNodes.append("circle").attr("r", 0).attr("fill", function(d) {
				//获取颜色
				return getColor(d.Category);
			}).attr("stroke", function(d) {
				//获取颜色
				return getColor(d.Category);
			}).attr("stroke-opacity", 0.5).attr("stroke-width", function(d) {
				if (d.depth == 0) {
					return 20;
				}

				if (d.depth == 1) {
					return 15;
				}

				return 0;
			}).on("click", function(d) {
				if (d.depth > 0) {
					toggle(d);
					drawTree(d);
				}
			});

			enterNodes.append("path").attr("d", function(d) {
				if (d.depth > 0 && d._children) {
					return "M-6 -1 H-1 V-6 H1 V-1 H6 V1 H1 V6 H-1 V1 H-6 Z";
				} else if (d.depth > 0 && d.children) {
					return "M-6 -1 H6 V1 H-6 Z";
				}
			}).attr("fill", "#ffffff").attr("stroke", "#ffffff").attr(
					"stroke-width", "0.5").on("click", function(d) {
				if (d.depth > 0) {
					toggle(d);
					drawTree(d);
				}
			});
			enterNodes.append("text").attr("dy", function(d) {
				if (d.depth == 0) {
					return "-1.5em";
				}
				return "0.31em";
			}).attr("x", function(d) {
				if (d.depth == 0) {
					return d.ShortName.length * 8;
				}
				return d.x < 180 ? 15 : -15;
			}).text(function(d) {
				if(d.InvestRate)
					return d.x >= 180?d.InvestRate+"% "+d.ShortName:d.ShortName+" "+d.InvestRate+"%";
				else
					return d.ShortName;
			}).style("text-anchor", function(d) {
				if (d.depth == 0) {
					return "end";
				}
				return d.x < 180 ? "start" : "end";
			}).style("fill-opacity", 0).attr("transform", function(d) {
				if (d.depth > 0) {
					return "rotate(" + (d.x < 180 ? d.x - 90 : d.x + 90) + ")";
				} else {
					return "rotate(0)";
				}
			}).style("font-size", function(d) {
				//设置字体大小
				if (d.depth == 0) {
					return "16px";
				}
				if (d.Category == 100) {
					return "18px";
				}
				return "14px";
			}).attr("fill", function(d) {
				//返回字体颜色
				/* if(d.Category==100)
					return "#FEF955";
				return "#ffffff"; */
			}).on("click", function(d) {
				/* if (d.KeyNo && d.depth > 0) {
					if (d.KeyNo.indexOf("_") < 0) {
						showDetail(d.KeyNo);
					}
					
				} */
				
				if(d.Uscd&&d.Uscd!=""){
					showUscd=d.Uscd;
					showDetail(d.Uscd);
				}
					
					
			});

			var updateNodes = nodeUpdate.transition().duration(500).attr(
					"transform", function(d) {
						return "translate(" + project(d.x, d.y) + ")";
					});
			updateNodes.select("text").style("fill-opacity", 1).attr(
					"transform",
					function(d) {
						if (d.depth > 0) {
							return "rotate("
									+ (d.x < 180 ? d.x - 90 : d.x + 90) + ")";
						} else {
							return "rotate(0)";
						}
					}).attr("x", function(d) {
				if (d.depth == 0) {
					return d.ShortName.length * 8;
				}
				return d.x < 180 ? 15 : -15;
			}).attr("fill", function(d) {
				//设置字体颜色
				if(d.Category==100)
					return "#FEF955";
				return "#ffffff";
			}).style("text-anchor", function(d) {
				if (d.depth == 0) {
					return "end";
				}
				return d.x < 180 ? "start" : "end";
			});
			updateNodes.select("circle").attr("r", function(d) {
				//设置圆大小
				if (d.depth == 0) {
					return 20;
				}

				if (d.depth == 1) {
					return 15;
				}
				if(d.Category==100)
					return 13;

				return 9;
			});
			updateNodes.select("path").attr("d", function(d) {
				if (d.depth > 0 && d._children) {
					return "M-6 -1 H-1 V-6 H1 V-1 H6 V1 H1 V6 H-1 V1 H-6 Z";
				} else if (d.depth > 0 && d.children) {
					return "M-6 -1 H6 V1 H-6 Z";
				}
			});

			var exitNodes = nodeExit.transition().duration(500).attr(
					"transform", function(d) {
						return "translate(" + project(data.x, data.y) + ")";
					}).remove();
			exitNodes.select("circle").attr("r", 0);

			exitNodes.select("text").style("fill-opacity", 0);

			nodes.forEach(function(d) {
				d.x0 = d.x;
				d.y0 = d.y;
			});

		}
		
		function getColor(category1,d){
			//主节点
			if (category1 == 1) {
				return "#035F92";
			}
			//主要成员
			if (category1 == 2) {
				return "#dc6449";
			}
			//股东
			if (category1 == 3) {
				return "#744fe1";
			}
			//对外投资
			if (category1 == 4) {
				return "#ffa64a";
			}
			//对外投资
			if (category1 == 100) {
				return "#FEF955";
			}

			return null;
		}

		function toggle(d) {
			if (d.children) {
				d._children = d.children;
				d.children = null;
			} else {
				d.children = d._children;
				d._children = null;
			}
		}

		function project(x, y) {
			var angle = (x - 90) / 180 * Math.PI, radius = y;
			return [ radius * Math.cos(angle), radius * Math.sin(angle) ];
		}



		

		function traverseTreeId(node) {
			var id = 1;
			trId(node);
			function trId(node) {
				if (!node.id) {
					
					node.id = id;
					id++;
				}
				if (node.children) {
					for ( var i = 0; i < node.children.length; i++) {
						trId(node.children[i]);
					}
				}
			}
		}

		function resizeScreen() {
			var height = window.innerHeight;
			$('#screenArea').height(height - 135);
			/* if (document.body.clientHeight > 700) {
			    $('#screenArea').height(document.body.clientHeight - 66);
			} else {
			    $('#screenArea').height(640);
			} */
		}

		

		function isFullScreen() {
			if (document.fullscreen) {
				return true;
			} else if (document.mozFullScreen) {
				return true;
			} else if (document.webkitIsFullScreen) {
				return true;
			} else if (document.msFullscreenElement) {
				return true;
			} else {
				return false;
			}
		}
		
		function launchFullScreen(element) { 
		  if(element.requestFullscreen) { 
		    element.requestFullscreen(); 
		  }else if(element.mozRequestFullScreen) { 
		    element.mozRequestFullScreen(); 
		  }else if(element.webkitRequestFullscreen) { 
		    element.webkitRequestFullscreen(); 
		  } else if(element.msRequestFullscreen) { 
		    element.msRequestFullscreen(); 
		  } 
		}
		function exitFullScreen(){
		  if(document.exitFullscreen){
		    document.exitFullscreen();
		  }
		  else if(document.mozCancelFullScreen){
		    document.mozCancelFullScreen();
		  }
		  else if(document.msExitFullscreen){
		    document.msExitFullscreen();
		  }
		  else if(document.webkitCancelFullScreen){
		    document.webkitCancelFullScreen();
		  }
		}
		
		function showDetail(compUscd){
			$("#companyDetail").show();
			var sendData;
			sendData={"compUscd":compUscd};
			$.get(basePath+"/indexMap/cqCompanyInfo", sendData, function(data) {
				var data=eval("(" + data + ")");
				if(!data.result){
					return;
				}
				
				var compInfo=data.entity;
				var comp=compInfo.companyBasicInfoCq;
				var djgShowlist = compInfo.djgShowlist;
				var stockStatisticsCqList=compInfo.stockStatisticsCqList;
				var sealList=compInfo.sealList;
				$("#chName").text(comp.chName);
				$("#legalName").text(comp.legalName);
				$("#registCapital").text(comp.registCapital+"万元（人民币）");
				$("#establishmentTime").text(comp.establishmentTime);
				$("#stockTable").empty();
				$("#djgTable").empty();
				$("#sealTable").empty();
				$("#djgTable").append('<tr><th style="width: 10%;">序号</th><th>姓名</th><th>职位</th></tr>');
				$("#stockTable").append('<tr><th style="width: 10%;">序号</th><th>名称</th><th style="width: 20%;">比例(%)</th></tr>');
				$("#sealTable").append('<tr><th style="width: 10%;">序号</th><th>名称</th></tr>');
				$.each(stockStatisticsCqList, function(i, item){
					$("#stockTable").append('<tr><td>'+(i+1)+'</td><td style="text-align:left;">'+item.stockName+'</td><td>'+item.rate+'</td></tr>');
				});
				$.each(djgShowlist, function(i, item){
					$("#djgTable").append('<tr><td>'+(i+1)+'</td><td style="text-align:left;"><a href="javascript:void(0);" style="color:antiquewhite;text-decoration:underline;" onclick="toPost(this)">'+item.personName+'</a></td><td>'+item.personPostType+'</td></tr>');
				});
				$.each(sealList, function(i, item){
					$("#sealTable").append('<tr><td>'+(i+1)+'</td><td style="text-align:left;">'+item+'</td></tr>');
				});
			});
		}	
		
		function toCompangAtlas(){
			var compName=$("#chName").text();
			showDetail(showUscd);
			getData(showUscd,compName);
			$("#compId").val("showUscd");
			$("#compId").val(compName);
		}
		
		
		
		function toPost(e){
			console.log($(e).text());
			if ($("#menu_7").attr("class") == "current") {
				return;
			} else {
				resetTabs2();
				$("#menu_7").addClass("current");
				$($("#menu_7").attr("name")).fadeIn();
			}
			$("#person_name").val($(e).text());
			getdjg(null);
		}
		
		function toCompangInfo(region){
			
			if ($("#menu_4").attr("class") == "current") {
				return;
			} else {
				
				resetTabs2();
				debugger;
				$("#menu_4").addClass("current");
				$($("#menu_4").attr("name")).fadeIn();
			}
			$("#region").val(region);
			
			_queryProperty();
		}
		
		
		
		
		
		
		//非企业图谱js
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		function initIndex(){
			getOverallSituation();
			getPlatformCompany();
			getChangePropertyInformation();
			getBusinessDistribution();
			getBusinessIndustry();
		}
		
		function getOverallSituation(){
			$.ajax({
				url : basePath+"/property/getOverallSituation",
				type : "POST",
				data : {},
				async: false,
				success : function(data1) {
					if(data1=="")
						return;
					var data=JSON.parse(data1);
					$("#countryCompany").text(data[0].totalCompany);
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
		
		function getPlatformCompany(){
			$.ajax({
				url : basePath+"/property/getPlatformCompany",
				type : "POST",
				data : {},
				async: false,
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
					tr=tr+"<tr>";
				    tr=tr+"<td style=\"width: 25%\">"+data[i].company_name+"</td>"+"<td style=\"width:7%\">"+data[i].totalCompany+"</td>"+"<td>"+data[i].fullCapitalCompany+"</td>"+"<td>"+data[i].holdingCompany+"</td>"+"<td>"+data[i].shareholdingCompany+"</td>"+"<td>"+data[i].continuityCompany+"</td>"+"<td>"+data[i].revokeCompany+"</td>";
					tr=tr+"</tr>";
				}
			obj.append(tr);
		}
		
		function getChangePropertyInformation(){
			$.ajax({
				url : basePath+"/property/getChangePropertyInformation",
				type : "POST",
				data : {},
				async: false,
				success : function(data) {
					if(data==""){
						return;
					}{
						$("#revockedInformation").text(data.object[0].revokeCompany);
						$("#servePerson").text(data.object[1].revokeCompany);	
						$("#leavePerson").text(data.object[2].revokeCompany);
						$("#stockChangeCompany").text(data.stockChangeCompany);
					}
				}
			});
		}
		
		function getBusinessDistribution(){
			$.ajax({
				url : basePath+"/property/getBusinessDistribution",
				type : "POST",
				data : {},
				async: false,
				success : function(data1) {
					if(data1=="")
						return;
					createTableBusiness(data1,$("#table_business"))
					createEchartDistribution(data1)
					createDataArea(data1,$("#form_area"))
				}
			});
		}
		
		function createTableBusiness(data1,obj){
			var tr="";	
			var data="";
			var name=""
			if(data1==null || data1.length==0)
			{
				tr="<tr><td style=\"text-align: center\">无数据</td><tr>"
			}
			else
				var data=JSON.parse(data1);
				for (var i = 0; i < data.length; i++) {

					tr=tr+"<tr>";
				    tr=tr+"<td style=\"width:65.3%\">"+data[i].name+"</td>"+"<td>"+data[i].number+"</td>";
					tr=tr+"</tr>";
				}
				obj.append(tr);

		}
		
		function createDataArea(data1,obj){
			obj.empty();
			var div="";	
			var data=JSON.parse(data1);
			for (var i = 0; i < data.length; i++) {
				div=div+"<div class=\"form_content_tab\">";
			    div=div+"<div class=\"form_content_tab1\">"+data[i].number+"</div>"+"<div class=\"form_content_tab2\">"+data[i].name+"</div>";
				div=div+"</div>";
			}
			obj.append(div);

		}
		
		function getBusinessIndustry(){
			$.ajax({
				url : basePath+"/property/getBusinessIndustry",
				type : "POST",
				data : {},
				async: false,
				success : function(data1) {
					if(data1=="")
						return;
					createTableIndustry(data1,$("#table_industrty"))
					createEchartIndustry(data1)
				}
			});

		}
		function createTableIndustry(data1,obj){
/*			obj.empty();*/
			var tr="";	
			if(data1==null || data1.length==0)
			{
				tr="<tr><td style=\"text-align: center\">无数据</td><tr>"
			}
			else
				var data=JSON.parse(data1);
				for (var i = 0; i < data.length; i++) {

					tr=tr+"<tr>";
				    tr=tr+"<td style=\"width:48.4%\">"+data[i].name+"</td>"+"<td>"+data[i].number+"</td>";
					tr=tr+"</tr>";
				}
				obj.append(tr);
		}
		
	function getPropertyInformation(pageNum){
		var companyName = $("#companyName").val();
		var compType = $("#compType").val();
		var region = $("#region").val();
		var isHolding = $("#isHolding").val();
		$.ajax({
			url : basePath+"/property/getPropertyInformation",
			type : "POST",
			data : {companyName:companyName,compType:compType,region:region,isHolding:isHolding,currentPage:pageNum},
			async: false,
			success : function(data1) {
				if(data1=="")
					return;
				createTableProperty(data1)
				
			}
		});

	}
	
	function getSealInformation(pageNum){
		var sealName = $("#sealName").val();
		var sealType = $("#sealType").val();
		var storageCompany = $("#storageCompany").val();
		$.ajax({
			url : basePath+"/property/getSealInformation",
			type : "POST",
			data : {sealName:sealName,sealType:sealType,storageCompany:storageCompany,currentPage:pageNum},
			async: false,
			success : function(data1) {
				if(data1=="")
					return;
				createTableSeal(data1)
				
			}
		});

	}
	
	function getIntellectualProperty(pageNum){
		var knowcqName = $("#knowcqName").val();
		var knowlcqKind = $("#knowlcqKind").val();
		var zscqCompName = $("#zscqCompName").val();
		$.ajax({
			url : basePath+"/property/getIntellectualProperty",
			type : "POST",
			data : {knowcqName:knowcqName,knowlcqKind:knowlcqKind,zscqCompName:zscqCompName,currentPage:pageNum},
			async: false,
			success : function(data1) {
				if(data1=="")
					return;
				createTableIntellectual(data1)
				
			}
		});

	}
	
	function getdjg(pageNum){
		var person_name = $("#person_name").val();
		
		$.ajax({
			url : basePath+"/property/getdjg",
			type : "POST",
			data : {person_name:person_name,currentPage:pageNum},
			async: false,
			success : function(data1) {
				if(data1=="")
					return;
				createTableIntellectual1(data1)
				
			}
		});

	}
	

	var pageStr = "";	
	function createTableProperty(data){
		$("#table_property").empty();
		$("#page-list-div").empty();
		var htmlStr = "<tr><th>序号</th><th>公司名称</th><th>法人代表</th><th>注册资本(万元)</th><th>工商状态</th><th>企业状态</th></tr>";
		var name="property";
		pagination(data,name);//分页
		// 总页数
		var pageNum = data.pageBean.currentPage;
		// 当前页
		var pageSize = data.pageBean.pageSize;
		var dataList = data.pageBean.items;
		if (dataList.length > 0) {
			$("#page-list-div").append(pageStr);
			$.each(dataList, function(k, v) {
				//公司名称
				var chName = v.chName;
				//法人代表
				var legalName = v.legalName;
				//注册资本(万元)
				var registCapital = v.registCapital;
				//工商状态
				var busRegStatus = v.busRegStatus;
				//企业状态
				var operationsStatus = v.operationsStatus;
				// id
				var compId = v.id;
				htmlStr += "<tr >";
				htmlStr += "<td style='text-align: center'>" + (k + 1 + pageSize * (pageNum - 1)) + "</td>";
				//htmlStr += "<td style='text-align: center'><a target='_blank' style='color: white;' onclick='javascript:_viewMenu("+compId+");'>" + chName + "</a></td>";
				htmlStr += "<td style='text-align: center'><a target='_blank' style='color: white;' href='"+basePath+"/property/_getCqCompositeView?id="+compId+"'>" + chName + "</a></td>";
				htmlStr += "<td style='text-align: center'>" + legalName + "</td>";
				htmlStr += "<td style='text-align: center'>" + registCapital + "</td>";
				htmlStr += "<td style='text-align: center'>" + busRegStatus + "</td>";
				htmlStr += "<td style='text-align: center'>" + operationsStatus + "</td>";
				htmlStr += "</tr>";
			});
		} else {
			$("#table_property").empty();
			htmlStr = "<tr><td colspan=6  style='text-align: center'>	无 数 据 		</td></tr>";
		}
		$("#table_property").append(htmlStr);
	}
	
	function createTableIntellectual1(data){
		$("#table_Intellectual1").empty();
		$("#page-list-intellectual1").empty();
		var htmlStr = "<tr><th width=\"8%\">序号</th><th width=\"15%\">姓名</th> <th width=\"25%\"> 公司名称</th><th>任职信息</th></tr>";
		var name="djg";
		pagination(data,name);//分页
		// 总页数
		var pageNum = data.pageBean.currentPage;
		// 当前页
		var pageSize = data.pageBean.pageSize;
		var dataList = data.pageBean.items;
		if (dataList.length > 0) {
			
			$("#page-list-intellectual1").append(pageStr);
			$.each(dataList, function(k, v) {
				//姓名
				var person_name = v.person_name;
				var comp_name = v.comp_name;
				//任职情况
				/*var is_leave = v.is_leave;*/
				//职务
				/*var post_type = v.post_type;*/
				//公司名称
				/*var comp_name = v.comp_name;*/
				//任职信息
				var date = v.date;
			
				htmlStr += "<tr >";
				htmlStr += "<td style='text-align: center'>" + (k + 1 + pageSize * (pageNum - 1)) + "</td>";
				htmlStr += "<td style='text-align: center'>" + person_name + "</td>";
				htmlStr += "<td style='text-align: left'>" + comp_name + "</td>";
				htmlStr += "<td style='text-align: left'>" + date + "</td>";
				/*+ "</td>";*/
				
				/*htmlStr += "<td style='text-align: center'>" + post_type + "</td>";
				htmlStr += "<td style='text-align: center'>" + comp_name + "</td>";  */  
			htmlStr += "</tr>";
			});
		} else {
			$("#table_Intellectual1").empty();
			htmlStr = "<tr><td colspan=6  style='text-align: center'>	无 数 据 		</td></tr>";
		}
		$("#table_Intellectual1").append(htmlStr);
	}

	function createTableSeal(data){
		$("#table_Seal").empty();
		$("#page-list-seal").empty();
		var htmlStr = "<tr><th>序号</th><th>公司名称</th><th>印章名称</th><th>印章类型</th><th>保管公司</th><th>保管人</th></tr>";
		var name="seal";
		pagination(data,name);//分页
		// 总页数
		var pageNum = data.pageBean.currentPage;
		// 当前页
		var pageSize = data.pageBean.pageSize;
		var dataList = data.pageBean.items;
		if (dataList.length > 0) {
			$("#page-list-seal").append(pageStr);
			$.each(dataList, function(k, v) {
				//公司名称
				var compName = v.uscd;
				//印章名称
				var sealName = v.sealName;
				//印章类型
				var sealType = v.sealType;
				//保管公司
				var storageCompany = v.storageCompanyId;
				//保管人
				var storagePerson = v.storagePerson;
				htmlStr += "<tr >";
				htmlStr += "<td style='text-align: center'>" + (k + 1 + pageSize * (pageNum - 1)) + "</td>";
				htmlStr += "<td style='text-align: center'>" + compName + "</td>";
				htmlStr += "<td style='text-align: center'>" + sealName + "</td>";
				htmlStr += "<td style='text-align: center'>" + sealType + "</td>";
				htmlStr += "<td style='text-align: center'>" + storageCompany + "</td>";
				htmlStr += "<td style='text-align: center'>" + storagePerson + "</td>";
				htmlStr += "</tr>";
			});
		} else {
			$("#table_Seal").empty();
			htmlStr = "<tr><td colspan=6  style='text-align: center'>	无 数 据 		</td></tr>";
		}
		$("#table_Seal").append(htmlStr);
	}
	
	
	function createTableIntellectual(data){
		$("#table_Intellectual").empty();
		$("#page-list-intellectual").empty();
		var htmlStr = "<tr><th>序号</th><th>公司名称</th><th>知识产权名称</th><th>知识产权类型</th><th>注册时间</th><th>附件</th></tr>";
		var name="intellectual";
		pagination(data,name);//分页
		// 总页数
		var pageNum = data.pageBean.currentPage;
		// 当前页
		var pageSize = data.pageBean.pageSize;
		var dataList = data.pageBean.items;
		if (dataList.length > 0) {
			
			$("#page-list-intellectual").append(pageStr);
			$.each(dataList, function(k, v) {
				//公司名称
				var zscqCompName = v.zscqCompName;
				//知识产权名称
				var knowcqName = v.knowcqName;
				//知识产权类型
				var knowlcqKind = v.knowlcqKind;
				//注册时间
				var createDate = v.createDate;
				//附件
				var file = v.afile;
				var fileName="";
				var filePath="";
		        if(file!=null){
		        	for (var i = 0; i < file.length; i++) {
						fileName=file[i].fileName;
						filePath=file[i].filePath;
					}
		        }
				htmlStr += "<tr >";
				htmlStr += "<td style='text-align: center'>" + (k + 1 + pageSize * (pageNum - 1)) + "</td>";
				htmlStr += "<td style='text-align: center'>" + zscqCompName + "</td>";
				htmlStr += "<td style='text-align: center'>" + knowcqName + "</td>";
				htmlStr += "<td style='text-align: center'>" + knowlcqKind + "</td>";
				htmlStr += "<td style='text-align: center'>" + createDate + "</td>";      /*onclick=\"downloadFile("+"'"+filePath+"','"+fileName+"')\"*/
				htmlStr += "<td style='text-align: center'><a href=\""+basePath+filePath+"\"\"    target=\"_blank\"  style='color:antiquewhite;text-decoration:underline'>"+fileName+"</a></td>";
			htmlStr += "</tr>";                            
			});
		} else {
			$("#table_Intellectual").empty();
			htmlStr = "<tr><td colspan=6  style='text-align: center'>	无 数 据 		</td></tr>";
		}
		$("#table_Intellectual").append(htmlStr);
	}
	
	function createEchartDistribution(data){
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
		})
		var option = {
			    tooltip: {
			        trigger: 'item',
			        formatter: "{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data:data1
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
			          /*  name:'地域分布',*/
			            type:'pie',
			            radius: ['40%', '65%'],
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
			                    /*    a: {
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
			                /*        hr: {
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
			myChart.setOption(option);
	}
	
	function createEchartIndustry(data){
		var myChart=echarts.init(document.getElementById('LocationDistributionChart2'));
		var data2=JSON.parse(data);
		var data1="";
		for (var i = 0; i < data2.length; i++) {
			data1+=data2[i].name+",";			
		}
		if(data1.length>0){
			data1=data1.substr(0,data1.length-1);
		} 
		var data3 = data2.map(function(item){
			return {
				value:item.number,
				name:item.name
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
			        data:data1
			    },
			    series: [
			        {
			            name:'行业分布',
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
			            radius: ['50%', '75%'],
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
	
	
	
	var tempPageNum = 0;
	var tempTotalPage = 0;
	function pagination(data,name){
		if(pageStr!=""){
			pageStr="";
		}
		var totalRecords = data.pageBean.totalNum;
		// 总记录数
		var totalPage = data.pageBean.totalPage;
		// 总页数
		var pageNum = data.pageBean.currentPage;
		// 当前页
		var pageSize = data.pageBean.pageSize;
		// 每页记录数
		tempPageNum = pageNum;
		tempTotalPage = totalPage;
		pageStr += "<span style='margin-top:4px;color:white'>&nbsp;" + totalPage + "页 /共" + totalRecords + "条</span>";
		pageStr += "<li class='previous'><a href='javascript:;' onclick='prev(\""+name+"\")'>«</a></li>";
		if (totalPage > 6) {
			if (pageNum <= 3) {
				for (i = 1; i <= 6; i++) {
					if (pageNum == i) {
						pageStr += "<li class='active'><a >" + i + "</a></li>";
					} else {
						pageStr += "<li><a href='javascript:;' onclick='page(" + i + ",\""+name+"\");'>" + i + "</a></li>";
					}
				}
			}
			if (pageNum > 3 && pageNum <= totalPage - 3) {
				for (i = pageNum + 1 - 3; i <= pageNum + 3; i++) {
					if (pageNum == i) {
						pageStr += "<li class='active'><a >" + i + "</a></li>";
					} else {
						pageStr += "<li><a href='javascript:;' onclick='page(" + i + ",\""+name+"\");'>" + i + "</a></li>";
					}
				}
			}
			if (pageNum > totalPage - 3) {
				for (i = totalPage + 1 - 6; i <= totalPage; i++) {
					if (pageNum == i) {
						pageStr += "<li class='active'><a >" + i + "</a></li>";
					} else {
						pageStr += "<li><a href='javascript:;' onclick='page(" + i + ",\""+name+"\");'>" + i + "</a></li>";
					}
				}
			}
		} else {
			for (i = 1; i <= totalPage; i++) {
				if (pageNum == i) {
					pageStr += "<li class='active'><a >" + i + "</a></li>";
				} else {
					pageStr += "<li><a href='javascript:;' onclick='page(" + i + ",\""+name+"\");'>" + i + "</a></li>";
				}
			}
		}
		pageStr += "<li class='next'><a href='javascript:;' onclick='next(\""+name+"\")'>»</a></li>";
	}
	
	function downloadFile(url,name){
		var u=basePath+"/file/download?_url="+url+"&_name="+name;
		var u2=basePath+"/file/judgedownload?_url="+url+"&_name="+name;

		
		
		 $.ajax({  
		     url :u2,  
		     type : "POST",  
	         success : function(data) {
	        	 if(data.search("success") != -1){
	        		 window.location.href=u;
	        	 }else{
	        		 win.errorAlert("下载失败！");
	        	 }
		     },  
		     error : function(data) {  
		     	win.errorAlert("下载失败！");
		     }  
		}); 
	}

	function prev(name) {
		
		var pageNum = parseInt(tempPageNum) - 1;
		if (pageNum == 0) {
			pageNum = 1;
		}
		if(name=="property"){
			getPropertyInformation(pageNum);
		}else if(name=="seal"){
			getSealInformation(pageNum)
		}else if(name=="intellectual"){
			getIntellectualProperty(pageNum)
		}else{
			getdjg(pageNum)
		}
		

	}
	function next(name) {
		var pageNum = parseInt(tempPageNum) + 1;
		if (pageNum >= tempTotalPage) {
			pageNum = tempTotalPage;
		}
		if(name=="property"){
			getPropertyInformation(pageNum);
		}else if(name=="seal"){
			getSealInformation(pageNum)
		}else if(name=="intellectual"){
			getIntellectualProperty(pageNum)
		}else{
			getdjg(pageNum)
		}

	}
	function page(pageNum,name) {
		if(name=="property"){
			getPropertyInformation(pageNum);
		}else if(name=="seal"){
			getSealInformation(pageNum)
		}else if(name=="intellectual"){
			getIntellectualProperty(pageNum)
		}else{
			getdjg(pageNum)
		}
	}
	
	// 查看
	function _viewMenu(id) {
		var url = basePath+"/property/_getCqCompositeView?id=" + id;
		window.open(url);
	}