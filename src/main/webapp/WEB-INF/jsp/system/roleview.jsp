<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色信息填报页面</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
	<!--[if lt IE 9]>
	    <script src='${pageContext.request.contextPath}/assets/javascripts/html5shiv.js' type='text/javascript'></script>
	    <![endif]-->
	
	<!-- / flatty theme -->
	<link href='${pageContext.request.contextPath}/assets/stylesheets/light-theme.css'
		id='color-settings-body-color' media='all' rel='stylesheet' type='text/css' />	
		
	<link href='${pageContext.request.contextPath}/assets/stylesheets/bootstrap/bootstrap.css' rel='stylesheet' type='text/css' />
	<link href="${pageContext.request.contextPath}/assets/font/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/assets/css/demo.css" rel="stylesheet" type="text/css">
	<!-- / window 弹出提示-->
	<link href='${pageContext.request.contextPath}/css/window.css' rel='stylesheet' type='text/css' />

</head>
<body>

<form accept-charset="UTF-8" id="form1" method="post" style="margin-bottom: 0;" enctype="multipart/form-data">
	<div class="main_title">角色信息查看</div>
	
	<div class="container">
		<div class="list_container">
			<div class="table_container_title">角色信息查看</div>
				<table class="popup_table">
					<tr>
						<td><span class="red">*</span>角色名称：</td>
						<td><input id="roleName" name="roleName" placeholder="角色名称：" type="text" value="${role.roleName}" readonly="true"></td>
						<td><span class="red">*</span>职能概述：</td>
						<td><input id="roleDescription" name="roleDescription" placeholder="职能概述：" type="text" value="${role.roleDescription}" readonly="true"></td>
						<td><span class="red">*</span>角色编码：</td>
						<td><input id="rolenum" name="rolenum" placeholder="角色编码：" type="text" value="${role.rolenum}" readonly="true"></td>
					
					</tr>
				</table>
				<input type="hidden" name="isDel" value="${role.isDel}"/> 
				<input type="hidden" name="id" value="${role.id}"/> 
			</div>
			<div class="popup_button_container">
				<button class="popup_button blue_button" type="button" onclick="_close();">关闭</button>
			</div>
		</div>
</form>
</body>
</html>

<script src='${pageContext.request.contextPath}/assets/javascripts/bootstrap/bootstrap.min.js' type='text/javascript'></script>

<!-- 完成新增与更改 -->
<script type="text/javascript">
	function _close(){
		window.close();
	}
</script>