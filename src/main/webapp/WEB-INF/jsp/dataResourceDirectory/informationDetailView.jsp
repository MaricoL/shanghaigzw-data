<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link href='${pageContext.request.contextPath}/assets/stylesheets/bootstrap/bootstrap.css' rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/assets/font/iconfont.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/assets/css/demo.css" rel="stylesheet" type="text/css">
<!-- / window 弹出提示-->
<link href='${pageContext.request.contextPath}/css/window.css' rel='stylesheet' type='text/css' />
<!-- / jquery -->
<script src='${pageContext.request.contextPath}/js/jquery.min.js' type='text/javascript'></script>
<script src='${pageContext.request.contextPath}/js/window.js' type='text/javascript'></script>
</head>
<body>

<div class="main_title">资源详情</div>
<div class="container">
	<form>
			<div class="table_container"> 
				<div class="table_container_title">${name}</div>
				<input type="hidden" name="id" value="${id}"/>
				<input type="hidden" name="name" value="${name}"/>
				<table rules=none>
					<tr>
						<th>序号<th>信息资源标识符</th><th>信息项标识符</th><th>中文名称</th><th>英文名称</th><th>定义说明</th>
						<th>数据类型</th><th>数据长度</th><th>共享类型</th><th>共享方式</th><th>所属处室</th>
					</tr>
					
					<!-- <tr>
						<td>GZW004-010001</td><td>GZW004-0100010001</td><td>姓名</td><td></td><td>姓名</td>
						<td>0|字符型</td><td></td><td>1|依申请共享</td><td>6|介质交换</td><td>企业领导人员管理处</td>
					</tr> -->
					 <c:if test="${not empty pageBean.items }">
						<c:forEach items="${pageBean.items}" var="item"
							varStatus="status">
							<tr>	
								<td align="center">${status.index+1}</td>
								<td>${item.informationResourceIdentifier}</td>
								<td>${item.informationItemIdentifier}</td>
								<td>${item.chineseName}</td>
								<td>${item.englishName}</td>
								<td>${item.explanatoryDefinition}</td>
								<td>${item.dataType}</td>
								<td>${item.dataLength}</td>
								<td>${item.sharedType}</td>
								<td>${item.sharingMode}</td>
								<td>${item.departmentName}</td>
								
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty pageBean.items }">
						<tr>
							<td colspan="10" style="text-align: center;">查询无记录</td>
						</tr>
					</c:if> 
				</table>
				<jsp:include page="../public/page.jsp" />
			</div>
		</form>
</div>



</body>
</html>