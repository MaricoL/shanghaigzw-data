<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色管理</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />

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
	<form>
		<div class="main_title">角色管理</div>
		<span class="state_title">角色名称：</span>
		<input  class="state_input" name="roleName" value="${role.roleName}" id="roleName" />
		
		
		<div class="clearfix"></div>
		<button class="but_add" type="button" onclick="javascript:_add();"><i class="iconfont icon-xinzeng"></i>新增</button>
	    <button class="but_search" onclick="_query();"><i class="iconfont icon-chaxun"></i>查询</button>
		<div class="clearfix"></div>
		<div class="container">
			<div class="table_container"> 
				<div class="table_container_title">角色列表</div>
				<table rules=none>
					<tr>
						<th width="5%">序号</th>
						<th width="15%">角色名称</th>
						<th width="25%">职能概述</th>
						<th width="25%">角色编码</th>
						<th>操作</th>
					</tr>
					<c:if test="${not empty pageBean.items }">
						<c:forEach items="${pageBean.items}" var="item"
							varStatus="status">
							<tr>
								<td align="center">${status.index+1}</td>
								<td>${item.roleName}</td>
								<td>${item.roleDescription}</td>
								<td>${item.rolenum}</td>
								
								<td align="center">
								  
									<button class="table_button" type="button" onclick="javascript:_view('${item.id}');">查看</button>
								    <button class="table_button" type="button" onclick="javascript:_edit('${item.id}');">修改</button>
								    <button class="table_button" type="button" onclick="javascript:_deleterole('${item.id}');">删除</button>
								   <%--  <button class="table_button" type="button" onclick="javascript:_setMenus('${item.id}');">菜单分配</button>
								    <button class="table_button" type="button" onclick="javascript:_setPages('${item.id}');">页面分配</button>
								    <button class="table_button" type="button" onclick="javascript:_setButtons('${item.id}');">按钮分配</button> --%>
									<%-- <a href="${pageContext.request.contextPath}/role/_view?id=${item.id}" class="btn btn-default"target="_blank">查看</a>
					                <a href="${pageContext.request.contextPath}/role/_edit?id=${item.id}" class="btn btn-default" target="_blank">修改</a>
					                <a href="javascript:_deleterole('${item.id}')" class="btn btn-default">注销</a> --%>
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
				<jsp:include page="${pageContext.request.contextPath}/public/page.jsp" />
			</div>
		</div>
	</form>
</body>
</html>

<script type="text/javascript">
	function _query() {
		var form = document.forms[0];
		form.action = "${searchurl}";
		form.method = "POST";
		form.submit();
	}

	function _deleterole(id) {
		parent.win.confirm('确定要删除吗？',function(r) {
			if (r) {
				var url = "${pageContext.request.contextPath}/role/_cancel";
				$.ajax({
					url : url,
					type : "POST",
					dataType : "json",
					cache : false,
					async : false,
					data : {
						"id" : id
					},
					success : function(data) {
						if (data.flag == "1") {
							parent.win.successAlert(
									data.message,
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
						parent.win.errorAlert("删除失败！！！",
								function() {
								});
					}
				});
		}
	_query();});
	
}; 
	// 新增
	function _add() {
		var url = "${pageContext.request.contextPath}/role/_edit";
		window.open(url, "角色新增");
	}
	// 查看
	function _view(id) {
		var url = "${pageContext.request.contextPath}/role/_view?id="+id;
		window.open(url, "角色信息查看");
	}
	// 修改
	function _edit(id) {
		var url = "${pageContext.request.contextPath}/role/_edit?id="+id;
		window.open(url, "角色信息修改");
	}
	// 菜单分配
	function _setMenus(id) {
		var url = "${pageContext.request.contextPath}/role/_setMenus?id="+id;
		window.open(url, "角色菜单分配");
	}
	// 菜单分配
	function _setPages(id) {
		var url = "${pageContext.request.contextPath}/role/_setPages?id="+id;
		window.open(url, "角色菜单分配");
	}
	// 按钮分配
	function _setButtons(id) {
		var url = "${pageContext.request.contextPath}/role/_setButtons?id="+id;
		window.open(url, "角色按钮分配");
	}
	/* function _editMenu(id) {
		if(id == '' || id == undefined){
			var url = "${pageContext.request.contextPath}/sysMenu/_edit";
		}else{
			var url = "${pageContext.request.contextPath}/sysMenu/_edit?id="+id;
		}
		window.open(url,"菜单编辑界面");
	} */
</script>