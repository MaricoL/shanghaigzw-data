<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>基础数据标准列表</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport' />
<!--[if lt IE 9]>
    <script src='${pageContext.request.contextPath}/assets/javascripts/html5shiv.js' type='text/javascript'></script>
    <![endif]-->
<link
	href='${pageContext.request.contextPath}/assets/stylesheets/bootstrap/bootstrap.css'
	rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/assets/font/iconfont.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/css/demo.css"
	rel="stylesheet" type="text/css">
<!-- / window 弹出提示-->
<link href='${pageContext.request.contextPath}/css/window.css'
	rel='stylesheet' type='text/css' />
<!-- / jquery -->
<script src='${pageContext.request.contextPath}/js/jquery.min.js'
	type='text/javascript'></script>
<script src='${pageContext.request.contextPath}/js/window.js'
	type='text/javascript'></script>
	<!-- 监控enter键 -->
<script src='${pageContext.request.contextPath}/js/enterKey.js'
	type='text/javascript'></script>
</head>
<body>
<form>
	<div class='main_title'><span>基础数据标准查询</span></div>
	<span class="state_title">标准编号：</span>
	<input class="state_input" name="standardNum" id="standardNum" value="${conditionEntity.standardNum }"/>
	<span class="state_title">代码值：</span>
	<input class="state_input" name="codeValue" id="codeValue" value="${conditionEntity.codeValue }"/>
	
	<div class="clearfix"></div>
	<button class="but_add" type="button" onclick="javascript:_upload();" >导入</button>
	<button class="but_add" type="button" onclick="javascript:_export();" >导出</button>
	<button type="button" class="but_add" onclick="javascript:_editBasicDataSta();"><i class="iconfont icon-xinzeng"></i>新增</button>	
	<button class="but_search"  type="button" onclick="_downloadFile();"><i class="iconfont icon-chaxun"></i>导出模板</button>
	<button class="but_search"  type="button" onclick="_query();"><i class="iconfont icon-chaxun"></i>查询</button>
	<div class="clearfix"></div>		
			<div class='container'>
					<div class='table_container'>
						<div class="table_container_title">基础数据标准列表</div>
								<table rules=none>
										<tr>
											<th width="5%">序号</th>
											<th width="10%">标准编号</th>
											<th width="10%">标准名称</th>
											<th width="10%">代码值</th>
											<th width="20%">代码含义</th>
											<th width="30%">备注</th>
											<th>操作</th>
										</tr>
										<c:if test="${not empty pageBean.items }">
											<c:forEach items="${pageBean.items}" var="item"
												varStatus="status">
												<tr>
													<td align="center">${status.index+1}</td>
													<td align="center">${item.standardNum }</td>
													<td align="center">${item.standardName }</td>
													<td align="center">${item.codeValue }</td>
													<td align="left">${item.codeMeaning }</td>
													<td align="left">${item.remark }</td>
													<td align="center">
													<input type="button" class="table_button" onclick="javascript:_viewBasicDataSta('${item.id}');" value="查看">
													<input type="button" class="table_button" onclick="javascript:_editBasicDataSta('${item.id}');" value="修改">
													<input type="button" class="table_button" onclick="javascript:_deleteBasicDataSta('${item.id}');" value="删除">
													</td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${empty pageBean.items }">
											<tr>
												<td colspan="7" style="text-align: center;">查询无记录</td>
											</tr>
										</c:if>
								</table>
						</div>
					</div>
	</form>
	</body>
</html>
<%-- 	<jsp:include page="/jsp/cq/includeJs.jsp" /> --%>
	<script type="text/javascript">
		// 查询
		function _query() {
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "POST";
			form.submit();
		}

		// 删除产权信息
		function _deleteBasicDataSta(id){
			var params = {
					"id" : id
				};
				parent.win
						.confirm(
								'确定要删除吗？',
								function(r) {
									if (r) {
										var url = '${pageContext.request.contextPath}/basicDataSta/_delete';
										$.ajax({
											type : 'post',
											url : url,
											dateType : 'json',
											data : params,
											beforeSend : function(XMLHttpRequest) {
												$("#loading").show();
											},
											success : function(data) {
												if (data.flag == "1") {
													win.successAlert(data.message,
															function() {
																//window.close();
															});
													setTimeout(_query, 2e3);
												} else {
													parent.win.errorAlert("删除失败！"
															+ data.message,
															function() {
															});
												}
											},
											error : function() {
												parent.win.errorAlert("删除失败！",
														function() {
														});
											}
										});
									}
								});

			}
		

		function _editBasicDataSta(id) {
			if(id == '' || id == undefined){
				var url = "${pageContext.request.contextPath}/basicDataSta/_edit";
			}else{
				var url = "${pageContext.request.contextPath}/basicDataSta/_edit?id="+id;
			}
			window.open(url,"编辑界面");
			}
		function _viewBasicDataSta(id) {
			var url = "${pageContext.request.contextPath}/basicDataSta/_view?id="+id;
			window.open(url,"查看界面");
			}
		function _upload() {	
			var url = "${pageContext.request.contextPath}/basicDataSta/_upload";			
			window.open(url,"上传界面");
			}
		// 导出
		/* function _export() {
				var url = "${pageContext.request.contextPath}/basicDataSta/_export";
			window.open(url, "信息导出");
		} */
		function _export() {
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/basicDataSta/_export";
			form.method = "POST";
			form.submit();
		}
		
		//下载
		function _downloadFile() {
			var url="static/temp/dataSta/基础数据导入模板.xlsx";
			var name="基础数据导入模板.xlsx";
			console.log(url);
			var u = "${pageContext.request.contextPath}/file/downloadtemp?_url="
					+ url + "&_name=" + name;
			var u2 = "${pageContext.request.contextPath}/file/todownloadtemp?_url="
					+ url + "&_name=" + name;

			$.ajax({
				url : u2,
				type : "POST",
				success : function(data) {
					console.log(data);
					if (data.search("success") != -1) {
						window.location.href = u;
					} else {
						win.errorAlert("下载失败！");
					}
				},
				error : function(data) {
					win.errorAlert("下载失败！");
				}
			});
		}
	</script>

