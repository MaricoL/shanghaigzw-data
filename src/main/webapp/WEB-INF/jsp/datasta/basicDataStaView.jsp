<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>基础数据标准编辑页面</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport' />
<!--[if lt IE 9]>
    <script src='${pageContext.request.contextPath}/assets/javascripts/html5shiv.js' type='text/javascript'></script>
    <![endif]-->

<!-- / flatty theme -->
<link href='${pageContext.request.contextPath}/assets/stylesheets/light-theme.css' id='color-settings-body-color' media='all' rel='stylesheet' type='text/css' />	
	
<link href="${pageContext.request.contextPath}/assets/font/iconfont.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/assets/css/demo.css" rel="stylesheet" type="text/css">
<!-- / window 弹出提示-->
<link href='${pageContext.request.contextPath}/css/window.css' rel='stylesheet' type='text/css' />
<!-- / jquery -->
<script src='${pageContext.request.contextPath}/js/jquery.min.js' type='text/javascript'></script>
<script src='${pageContext.request.contextPath}/js/window.js' type='text/javascript'></script>
<script src='${pageContext.request.contextPath}/assets/javascripts/bootstrap/bootstrap.min.js' type='text/javascript'></script>
<!-- 时间插件 -->
<link href='${pageContext.request.contextPath}/css/jedate.css' rel='stylesheet' type='text/css' />
<!-- 校验 -->
<link href='${pageContext.request.contextPath}/css/vaild.css' rel='stylesheet' type='text/css' />
<!-- 校验 -->
<script src='${pageContext.request.contextPath}/js/vaild.js' type='text/javascript'></script>
<!-- 日期插件 -->
<script src='${pageContext.request.contextPath}/js/jquery.jedate.min.js' type='text/javascript'></script>

</head>
<body>
<form accept-charset="UTF-8" id="form1" method="post" style="margin-bottom: 0;" enctype="multipart/form-data">
	<div class="main_title">基础数据标准查看</div>
	<div class='container'>
		<div class='list_container'>
			<div class='table_container_title'>基础数据标准查看</div>
				<table class="popup_table">
					<tr>
						<td><span class="red">*</span>标准编号：</td>
						<td><input id="standardNum" name="standardNum" placeholder="标准编号" type="text" disabled="disabled" value="${basicDataSta.standardNum }" ></td>
						<td><span class="red">*</span>标准名称：</td>
						<td><input id="standardName" name="standardName" placeholder="标准名称" type="text" disabled="disabled" value="${basicDataSta.standardName }" ></td>
						<td>代码值：</td>
						<td><input id="codeValue" name="codeValue" placeholder="代码值" type="text" disabled="disabled" value="${basicDataSta.codeValue }" ></td>					
					</tr>
					<tr>
						<td>代码含义：</td>
						<td><input id="codeMeaning" name="codeMeaning" placeholder="代码含义" type="text" disabled="disabled" value="${basicDataSta.codeMeaning }" ></td>
					</tr>
					
					<tr>
						<td valign="top">备注：</td>
						<td colspan="5"><textarea disabled="disabled" 
								style="width: 100%; height: 200px; border: 1px solid #cbcbcb; border-radius: 4px; font-size: 16px; text-indent: 4px;"
								id="remark" name="remark" placeholder="备注">${basicDataSta.remark }</textarea>
						</td>
					</tr>
			</table>
			</div>
			<div class="popup_button_container">
			<input id="id" name="id" type="hidden" value="${basicDataSta.id }">
				<input type="button" class="popup_button blue_button" onclick="_close();" value="关闭">
			</div>
		</div>

</form>
</body>
</html>
	<script type="text/javascript">

		function _close(){
			window.close();
		}
	</script>

