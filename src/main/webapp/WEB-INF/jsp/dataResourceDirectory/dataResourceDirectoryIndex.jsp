<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/dtree/dtree.css" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/css/demo.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/dtree/dtree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mCustomScrollbar.min.js"></script>
<style type="text/css">
.dtree{
	font-size: 14px;
}
</style>
</head>
<body>
<div class="main_title">资源目录</div>
	<div>
		<div id="treeDiv" class="cnblogs_Highlighter" style="float: left; height: 800px;">
			<pre class="brush:javascript">
				<script type="text/javascript">
					tree = new dTree('tree');//创建一个对象.
					tree.add("GZW000", "-1", "国资委", "", "", "", "", "", false);
					tree.add("GZW001", "GZW000", "党委办公室", "", "", "", "", "", false);
					tree.add("GZW002", "GZW000", "组织处", "", "", "", "", "", false);
					tree.add("GZW003", "GZW000", "宣传处", "", "", "", "", "", false);
					tree.add("GZW004", "GZW000", "企业领导人员管理处", "", "", "", "", "", false);
					tree.add("GZW005", "GZW000", "老干部处", "", "", "", "", "", false);
					tree.add("GZW006", "GZW000", "办公室", "", "", "", "", "", false);
					tree.add("GZW007", "GZW000", "人事处", "", "", "", "", "", false);
					tree.add("GZW008", "GZW000", "研究室", "", "", "", "", "", false);
					tree.add("GZW009", "GZW000", "政策法规处", "", "", "", "", "", false);
					tree.add("GZW0010", "GZW000", "规划发展处", "", "", "", "", "", false);
					tree.add("GZW0011", "GZW000", "企业改革处", "", "", "", "", "", false);
					tree.add("GZW0012", "GZW000", "金融企业评价处", "", "", "", "", "", false);
					tree.add("GZW0013", "GZW000", "产权管理处", "", "", "", "", "", false);
					tree.add("GZW0014", "GZW000", "评估管理处", "", "", "", "", "", false);
					tree.add("GZW0015", "GZW000", "审计监督处（稽查办公室）", "", "", "", "", "", false);
					tree.add("GZW0016", "GZW000", "财务评价处", "", "", "", "", "", false);
					tree.add("GZW0017", "GZW000", "业绩考核处", "", "", "", "", "", false);
					tree.add("GZW0018", "GZW000", "企业分配处", "", "", "", "", "", false);
					tree.add("GZW0019", "GZW000", "综合协调处", "", "", "", "", "", false);
					tree.add("GZW0020", "GZW000", "公司治理处", "", "", "", "", "", false);
					tree.add("GZW0021", "GZW000", "信息化管理处", "", "", "", "", "", false);
					tree.add("GZW0022", "GZW000", "信访办公室", "", "", "", "", "", false);
					tree.add("GZW0023", "GZW000", "机关党委", "", "", "", "", "", false);
				
					
					
					 $.ajax({
						     url:'tList',
						     type:'get',
						     error:function(json){
						              alert("notlived!");
						     },
						     async:false ,
						     success:function(data){
							
								for(var i = 0; i < data.length; i++){
	
									var nodeId=data[i].id; 
									var parentId=data[i].pid; 
									var hrefAddress="informationDetail?id="+nodeId+"&name="+data[i].name; 
									var nodeName=data[i].name;
									if(data[i].level==2){
										hrefAddress="";
									}
									tree.add(nodeId,parentId,nodeName,hrefAddress,"","right","","",false);
								}
							
							}
					 });
					
					
					
					document.write(tree);
					
					
					function toSecond(id){
						alert(id);
					}
				</script>
			</pre>
		</div>
		<iframe id="right" name="right" frameborder="1"  scrolling="no"  style="padding: 0px; width: 80%;float: right; height: 800px; border-width: 0px;"></iframe>
	</div>
             <script>
				               /*  
				                    tree.add(id,pid,name,url,title,target,icon,iconOpen,open);
				                    id       
				 ：节点自身的id
				                    pid      
				 ：节点的父节点的id
				                    name     
				 ：节点显示在页面上的名称
				                    url      
				 ：节点的链接地址
				                    title    
				 ：鼠标放在节点上所出现的提示信息
				                    target   
				 ：节点链接所打开的目标frame（如框架目标mainFrame或是_blank,_self之类）
				                    icon     
				 ：节点关闭时的显示图片的路径
				                    iconOpen 
				 ：节点打开时的显示图片的路径
				                    open     
				 ：布尔型，节点是否打开（默认为false）
				                    ------------------------------------------------
				                    东城区、西城区、崇文区、宣武区、朝阳区、丰台区、石景山区、
				                    海淀区、门头沟区、房山区、通州区、顺义区、
				 昌平区、
				                   大兴区、怀柔区、平谷区
				 、 密云县、延庆县
				                   ------------------------------------------------
				                */
        
			</script>
   <script type="text/javascript">
		(function($){
		    $(window).on("load",function(){
		        $("#treeDiv").mCustomScrollbar();
		    });
		})(jQuery);
	</script>
</body>
</html>