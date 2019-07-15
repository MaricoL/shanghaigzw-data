<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>公共统计规则编辑页面</title>
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
	<div class="main_title">公共统计规则管理</div>
	<div class='container'>
		<div class='list_container'>
			<div class='table_container_title'>公共统计规则新增与编辑</div>
				<table class="popup_table">
					<tr>
						<td><span class="red">*</span>公共统计规则编号：</td>
						<td><input id="publicStaruleNum" name="publicStaruleNum" placeholder="公共统计规则编号" type="text" value="${publicSta.publicStaruleNum }" ></td>
						<td><span class="red">*</span>公共统计规则名称：</td>
						<td><input id="publicStaruleName" name="publicStaruleName" placeholder="公共统计规则名称" type="text" value="${publicSta.publicStaruleName }" ></td>
						<td>规则描述：</td>
						<td><input id="ruleDescription" name="ruleDescription" placeholder="规则描述" type="text" value="${publicSta.ruleDescription }" ></td>					
					</tr>
					
					<tr>
						<td valign="top">备注：</td>
						<td colspan="5"><textarea
								style="width: 100%; height: 200px; border: 1px solid #cbcbcb; border-radius: 4px; font-size: 16px; text-indent: 4px;"
								id="remark" name="remark" placeholder="备注">${publicSta.remark }</textarea>
						</td>
					</tr>
			</table>
			</div>
			<div class="popup_button_container">
			<input id="id" name="id" type="hidden" value="${publicSta.id }">
				<input type="button" class="popup_button main_button" onclick="_save();" value="保存">
				<input type="button" class="popup_button blue_button" onclick="_close();" value="关闭">
			</div>
		</div>

</form>
</body>
</html>
	<script type="text/javascript">
		function _save() {
			if(!vaild.all())
			{
				return false;
			}
			if (check()) {
				$("#getuscd").val($("#uscd").val());
				var url = '${pageContext.request.contextPath}/publicsta/_addupdate';
				$.ajax({
					type : 'post',
					url : url,
					dateType : 'text',
					data : $("#form1").serialize(),
					beforeSend : function(XMLHttpRequest) {
						$("#loading").show();
					},
					success : function(data) {
						if (data.flag == "1") {
							win.successAlert(data.message, function() {
							 
							});							
							setTimeout(_close,2e3);
							window.opener.location.reload();
						} else {
							parent.win.errorAlert("保存失败！" + data.message,
									function() {
									});
						}
					
					},
					complete : function(XMLHttpRequest, textStatus) {
						$("#loading").hide();
					},

					error : function() {
						parent.win.errorAlert("保存失败！", function() {
						});
					}
				});
			}
		}
		function check() {
			var flag = true;
			if (!chkIsEmpty("publicStaruleNum")) {
				flag = false;
				win.generalAlert("公共统计规则编号不能为空！")
				return;
			}
			/* if (!chkIsEmpty("sealName")) {
				flag = false;
				win.generalAlert("印章名称不能为空！");
				return;
			} */
			if (!chkIsEmpty("publicStaruleName")) {
				flag = false;
				win.generalAlert("公共统计规则名称不能为空！")
				return;
			}
			return flag;
		}
		// 判断为空
		function chkIsEmpty(objId) {
			if (!$.trim($("#" + objId).val())) { // "",null,undefined
				return false;
			} else {
				return true;
			}
		}
		function _close(){
			window.close();
		}
	</script>

