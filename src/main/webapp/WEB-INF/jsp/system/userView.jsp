<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户信息查看</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
<!--[if lt IE 9]>
    <script src='${pageContext.request.contextPath}/assets/javascripts/html5shiv.js' type='text/javascript'></script>
    <![endif]-->

<!-- / flatty theme -->
<link href='${pageContext.request.contextPath}/assets/stylesheets/light-theme.css'
	id='color-settings-body-color' media='all' rel='stylesheet' type='text/css' />	
	
<link href="${pageContext.request.contextPath}/assets/font/iconfont.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/assets/css/demo.css" rel="stylesheet" type="text/css">
<!-- / window 弹出提示-->
<link href='${pageContext.request.contextPath}/css/window.css' rel='stylesheet' type='text/css' />
<!-- / jquery -->
<script src='${pageContext.request.contextPath}/js/jquery.min.js' type='text/javascript'></script>
<script src='${pageContext.request.contextPath}/js/window.js' type='text/javascript'></script>

</head>
<body>

<form accept-charset="UTF-8" id="form1" method="post" style="margin-bottom: 0;" enctype="multipart/form-data">
	<div class="main_title">用户信息查看</div>
	
	<div class="container">
		<div class="list_container">
			<div class="table_container_title">用户信息查看</div>
				<table class="popup_table">
				     <tr>
						<td><span class="red">*</span>姓名：</td>
						<td><input id="userName" name="userName" placeholder="姓名" type="text" value="${user.userName}" readonly="true"></td>
						<td><span class="red">*</span>账号：</td>
						<td><input id="account" name="account" placeholder="账号" type="text" value="${user.account}" readonly="true"></td>
						<td>性别：</td>
						<td>
							<input name="userSex" type="radio" value="1" disabled="disabled" class="radio_style" <c:if test="${user.userSex eq 1}">checked</c:if>><span class="radio_select">男</span> 
							<input name="userSex" type="radio" value="0" disabled="disabled" class="radio_style" <c:if test="${user.userSex eq 0}">checked</c:if>><span class="radio_select">女</span>
						</td>
					</tr>
					<tr>
						<c:if test="${empty user}">
			               <td><label class="control-label" for="password">初始密码：</label></td>
			               <td>
			               <input id="password" name="password" placeholder="初始密码" type="text">
			               </td>  
		            	</c:if>
					</tr>
					<tr>
						
						
					    <td></td>
						<td></td>
					</tr>
				</table>
				<input type="hidden" name="isDel" value=0 /> 
				<input type="hidden" name="id" value="${user.id}"/>
               	<input type="hidden" name="createPersonName" value="${user.createPersonName}"/>
               	<input type="hidden" name="createPersonId" value="${user.createPersonId}"/>
               	<input type="hidden" name="createDate" value="${user.createDate}"/>
			</div>
			<div class="popup_button_container">
				<button class="popup_button blue_button" type="button" onclick="_close();">关闭</button>
			</div>
		</div>
</form>
</body>
</html>



<script src='${pageContext.request.contextPath}/assets/javascripts/bootstrap/bootstrap.min.js' type='text/javascript'></script>

<script type="text/javascript">
	function _close(){
		window.close();
	}
</script>