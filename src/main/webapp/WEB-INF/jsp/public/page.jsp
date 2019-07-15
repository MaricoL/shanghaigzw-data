<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<div class="clearfix" style="text-align:right;padding-right:12px;"> 
		<div class="pagination pagination-large">
		<ul>
			<li class="previous"><a href="javascript:;" onclick="prev()">«</a></li>
			
			<c:if test="${pageBean.totalPage>6}">
				<c:if test="${pageBean.currentPage<=6/2}">
					<c:forEach var="x" begin="1" end="6">
						<c:if test="${pageBean.currentPage==x}">
							<li class="active"><a >${x}</a></li>
						</c:if>
						<c:if test="${pageBean.currentPage!=x}">
							<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${pageBean.currentPage>6/2&&pageBean.currentPage<=pageBean.totalPage - 6/2}">
					<c:forEach var="x" begin="${pageBean.currentPage +1 - 6/2}" end="${pageBean.currentPage + 6/2}">
						<c:if test="${pageBean.currentPage==x}">
							<li class="active"><a >${x}</a></li>
						</c:if>
						<c:if test="${pageBean.currentPage!=x}">
							<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${pageBean.currentPage>pageBean.totalPage - 6/2}">
					<c:forEach var="x" begin="${pageBean.totalPage +1 - 6}" end="${pageBean.totalPage}">
						<c:if test="${pageBean.currentPage==x}">
							<li class="active"><a >${x}</a></li>
						</c:if>
						<c:if test="${pageBean.currentPage!=x}">
							<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
			</c:if>
			<c:if test="${pageBean.totalPage<=6}">
				<c:forEach var="x" begin="1" end="${pageBean.totalPage}">
					<c:if test="${pageBean.currentPage==x}">
						<li class="active"><a >${x}</a></li>
					</c:if>
					<c:if test="${pageBean.currentPage!=x}">
						<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
					</c:if>
				</c:forEach>
			</c:if>
			
			<li class="next"><a href="javascript:;" onclick="next()">»</a></li>
		</ul>
		</div>
	</div>
	<script type="text/javascript">
		
		var tempcurrentPage = ${pageBean.currentPage };
		var totalPage = ${pageBean.totalPage };
		var searchurl='${searchurl}'
		function prev(){
			var params = {};
			var currentPage = parseInt(tempcurrentPage) - 1;
			if(currentPage == 0){
				currentPage = 1;
			}
  			params.currentPage = currentPage; 
		    pagequery(currentPage);
		}
		function next(){
			var params = {};
			var currentPage = parseInt(tempcurrentPage) + 1;
			if(currentPage >= totalPage){
				currentPage = totalPage;
			}
  			params.currentPage = currentPage; 
		    pagequery(currentPage);
		}
		function page(currentPage){
		    pagequery(currentPage);
		}
		function pagequery(currentPage)
		{
			var url="";
			if(searchurl.indexOf('?')>0)
			    url = searchurl+"&currentPage="+currentPage;
			else
				url = searchurl+"?currentPage="+currentPage;
			var form = document.forms[0];
			form.action =url;
			form.method = "POST";
			form.submit();	
		}
		
	</script>
</html>