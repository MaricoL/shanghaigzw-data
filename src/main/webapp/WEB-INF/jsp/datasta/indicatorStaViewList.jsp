<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>指标标准列表</title>
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
	<div class='main_title'><span>指标标准查询</span></div>
	<span class="state_title">标准类别：</span>
	<input class="state_input" name="staCategory" id="staCategory" value="${conditionEntity.staCategory }"/>
	<span class="state_title">指标标准编号：</span>
	<input class="state_input" name="indicatorStaNum" id="indicatorStaNum" value="${conditionEntity.indicatorStaNum }"/>
	
	<div class="clearfix"></div>
	<button class="but_add" type="button" onclick="javascript:_export();" >导出</button>
	<button class="but_search"  type="button" onclick="_query();"><i class="iconfont icon-chaxun"></i>查询</button>
	<div class="clearfix"></div>		
			<div class='container'>
					<div class='table_container'>
						<div class="table_container_title">指标标准列表</div>
								<table rules=none>
										<tr>
											<th width="5%">序号</th>
											<th width="10%">标准类别</th>
											<th width="10%">指标标准编号</th>
											<th width="15%">指标中文名称</th>
											<th width="10%">标准主题</th>											
											<th width="8%">指标格式</th>
											<th width="10%">权威系统来源</th>
											<th>操作</th>
										</tr>
										<c:if test="${not empty pageBean.items }">
											<c:forEach items="${pageBean.items}" var="item"
												varStatus="status">
												<tr>
													<td align="center">${status.index+1}</td>
													<td align="center">${item.staCategory }</td>
													<td align="center">${item.indicatorStaNum }</td>
													<td align="center">${item.indicatorCname }</td>
													<td align="center">${item.staTheme }</td>
													<td align="center">${item.indicatorFormat }</td>
													<td align="center">${item.authoritativeSystemSource }</td>
													<td align="center">
													<input type="button" class="table_button" onclick="javascript:_viewIndicatorSta('${item.id}');" value="查看">
													</td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${empty pageBean.items }">
											<tr>
												<td colspan="8" style="text-align: center;">查询无记录</td>
											</tr>
										</c:if>
								</table>
								<jsp:include page="${pageContext.request.contextPath}/public/page.jsp" />
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

	
		function _viewIndicatorSta(id) {
			var url = "${pageContext.request.contextPath}/indicatorsta/_view?id="+id;
			window.open(url,"查看界面");
			}
		// 导出
		// 导出
		function _export() {
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/indicatorsta/_export";
			form.method = "POST";
			form.submit();
		}
	</script>

