<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>页面分配</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
<!--[if lt IE 9]>
    <script src='${pageContext.request.contextPath}/assets/javascripts/html5shiv.js' type='text/javascript'></script>
    <![endif]-->

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

	<form accept-charset="UTF-8" id="form1" method="post" style="margin-bottom: 0;" enctype="multipart/form-data">

		<div class="main_title">角色分配</div>
		<span class="state_title">用户名称：</span>
		<input class="state_input" name="userName" value="${user.userName }" id="userName" readonly="readonly"/>
		<input type="hidden" id="userId" name="userId" value="${user.id}"/>
		<input type="hidden" id="roleIds" name="roleIds"/>
		<div class="container">
			<div class="table_container"> 
				<div class="table_container_title">角色信息</div>
				<c:if test="${not empty roles}">
					<c:forEach items="${roles}" var="item" varStatus="status">
						<div class="menu_choice">
							<div class="menu_choice_name">${item.roleName}</div>
							<div class="menu_choice_checkbox"><input type="checkbox" name="role" value="${item.id}" <c:if test="${item.checked eq 1 }">checked</c:if>></div>
						</div>
					</c:forEach>		
				</c:if>
				<div class="clearfix"></div>
			</div>
			<div class="popup_button_container">
				<button class="popup_button main_button" onclick="_save();">保存</button>
				<button class="popup_button blue_button" onclick="_close();">关闭</button>
			</div>
		</div>
		
	</form>
</body>
</html>
<script src='${pageContext.request.contextPath}/js/jquery.min.js' type='text/javascript'></script>
<script type="text/javascript">
	function _close(){
		window.close();
	}
	
	function _save(){
		var url = "${pageContext.request.contextPath}/user/_saveUserRoles";
		var getRoles = $("input[name='role']:checked");
		var roleId = "";
		$.each(getRoles,function(){
			roleId += $(this).attr("value") + ",";
	  	})
		$("#roleIds").val(roleId);
		$.ajax({
		    url: url,
			type: "post",
		    data: $("#form1").serialize(),
		    dataTyep:"JSON",
	        async: false,
		    success: function(data) {
		        if(data == 'success'){
		        	win.successAlert("保存成功！");
					setTimeout(window.close(),2e3);
		        }
		        else{
		        	win.errorAlert("保存失败！");
		        }
		    }, 
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                win.errorAlert("保存失败！！！");
            }
		});
	}
</script>