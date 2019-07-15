<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>指标标准编辑页面</title>
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
	<div class="main_title">指标标准管理</div>
	<div class='container'>
		<div class='list_container'>
			<div class='table_container_title'>指标标准新增与编辑</div>
				<table class="popup_table">
					<tr>
						<td><span class="red">*</span>标准类别：</td>
						<td><input id="staCategory" name="staCategory" placeholder="标准类别" type="text" value="${indicatorSta.staCategory }" ></td>
						<td><span class="red">*</span>指标标准编号：</td>
						<td><input id="indicatorStaNum" name="indicatorStaNum" placeholder="指标标准编号" type="text" value="${indicatorSta.indicatorStaNum }" ></td>
						<td>标准主题：</td>
						<td><input id="staTheme" name="staTheme" placeholder="标准主题" type="text" value="${indicatorSta.staTheme }" ></td>					
					</tr>
					<tr>
						<td>标准大类：</td>
						<td><input id="staClass" name="staClass" placeholder="标准大类" type="text" value="${indicatorSta.staClass }" ></td>
						<td>标准子类：</td>
						<td><input id="staSubclass" name="staSubclass" placeholder="标准子类" type="text" value="${indicatorSta.staSubclass }" ></td>
						<td><span class="red">*</span>指标中文名称：</td>
						<td><input id="indicatorCname" name="indicatorCname" placeholder="指标中文名称" type="text" value="${indicatorSta.indicatorCname }" ></td>					
					</tr>
					<tr>
						<td>指标中文类别：</td>
						<td><input id="indicatorCalias" name="indicatorCalias" placeholder="指标中文类别" type="text" value="${indicatorSta.indicatorCalias }" ></td>
						<td>指标英文名称：</td>
						<td><input id="indicatorEname" name="indicatorEname" placeholder="指标英文名称" type="text" value="${indicatorSta.indicatorEname }" ></td>
						<td>相关指标标准：</td>
						<td><input id="relevantIndicatorSta" name="relevantIndicatorSta" placeholder="相关指标标准" type="text" value="${indicatorSta.relevantIndicatorSta }" ></td>					
					</tr>
					<tr>
						<td>相关基础数据标准：</td>
						<td><input id="relevantBasicDataSta" name="relevantBasicDataSta" placeholder="相关基础数据标准" type="text" value="${indicatorSta.relevantBasicDataSta }" ></td>
						<td>业务定义：</td>
						<td><input id="businessDefinition" name="businessDefinition" placeholder="业务定义" type="text" value="${indicatorSta.businessDefinition }" ></td>
						<td>统计口径：</td>
						<td><input id="statisticalCaliber" name="statisticalCaliber" placeholder="统计口径" type="text" value="${indicatorSta.statisticalCaliber }" ></td>					
					</tr>
					<tr>
						<td>业务规则：</td>
						<td><input id="businessRules" name="businessRules" placeholder="业务规则" type="text" value="${indicatorSta.businessRules }" ></td>
						<td>公共统计规则：</td>
						<td><input id="publicStatisticalRules" name="publicStatisticalRules" placeholder="公共统计规则" type="text" value="${indicatorSta.publicStatisticalRules }" ></td>
						<td>指标格式：</td>
						<td><input id="indicatorFormat" name="indicatorFormat" placeholder="指标格式" type="text" value="${indicatorSta.indicatorFormat }" ></td>					
					</tr>
					<tr>
						<td>常用单位：</td>
						<td><input id="commonUnit" name="commonUnit" placeholder="常用单位" type="text" value="${indicatorSta.commonUnit }" ></td>
						<td>数据长度：</td>
						<td><input id="dataLength" name="dataLength" placeholder="数据长度" type="text" value="${indicatorSta.dataLength }" ></td>
						<td>取值范围：</td>
						<td><input id="ranges" name="ranges" placeholder="取值范围" type="text" value="${indicatorSta.ranges }" ></td>					
					</tr>
					<tr>
						<td>取值精度：</td>
						<td><input id="valueAccuracy" name="valueAccuracy" placeholder="取值精度" type="text" value="${indicatorSta.valueAccuracy }" ></td>
						<td>权威系统来源：</td>
						<td><input id="authoritativeSystemSource" name="authoritativeSystemSource" placeholder="权威系统来源" type="text" value="${indicatorSta.authoritativeSystemSource }" ></td>
						<td>制定依据：</td>
						<td><input id="basisMaking" name="basisMaking" placeholder="制定依据" type="text" value="${indicatorSta.basisMaking }" ></td>					
					</tr>
					<tr>
						<td>标准责任部门：</td>
						<td><input id="staResponsibilityDepartment" name="staResponsibilityDepartment" placeholder="标准责任部门" type="text" value="${indicatorSta.staResponsibilityDepartment }" ></td>
						<td>最低生成频次：</td>
						<td><input id="minGenerationFrequency" name="minGenerationFrequency" placeholder="最低生成频次" type="text" value="${indicatorSta.minGenerationFrequency }" ></td>									
					</tr>
			</table>
			</div>
			<div class="popup_button_container">
			<input id="id" name="id" type="hidden" value="${indicatorSta.id }">
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
				var url = '${pageContext.request.contextPath}/indicatorsta/_addupdate';
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
			if (!chkIsEmpty("indicatorStaNum")) {
				flag = false;
				win.generalAlert("指标标准编号不能为空！")
				return;
			}
			/* if (!chkIsEmpty("sealName")) {
				flag = false;
				win.generalAlert("印章名称不能为空！");
				return;
			} */
			if (!chkIsEmpty("indicatorCname")) {
				flag = false;
				win.generalAlert("指标中文名称不能为空！")
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

