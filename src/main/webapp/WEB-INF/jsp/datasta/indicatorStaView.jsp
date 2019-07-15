<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>指标标准查看页面</title>
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
	<div class="main_title">指标标准查看</div>
	<div class='container'>
		<div class='list_container'>
			<div class='table_container_title'>指标标准查看</div>
				<table class="popup_table">
					<tr>
						<td><span class="red">*</span>标准类别：</td>
						<td><input id="staCategory" name="staCategory" placeholder="标准类别" disabled="disabled" type="text" value="${indicatorSta.staCategory }" ></td>
						<td><span class="red">*</span>指标标准编号：</td>
						<td><input id="indicatorStaNum" name="indicatorStaNum" placeholder="指标标准编号" disabled="disabled" type="text" value="${indicatorSta.indicatorStaNum }" ></td>
						<td><span class="red">*</span>标准主题：</td>
						<td><input id="staTheme" name="staTheme" placeholder="标准主题" disabled="disabled" type="text" value="${indicatorSta.staTheme }" ></td>					
					</tr>
					<tr>
						<td>标准大类：</td>
						<td><input id="staClass" name="staClass" placeholder="标准大类" disabled="disabled" type="text" value="${indicatorSta.staClass }" ></td>
						<td>标准子类：</td>
						<td><input id="staSubclass" name="staSubclass" placeholder="标准子类" disabled="disabled" type="text" value="${indicatorSta.staSubclass }" ></td>
						<td>指标中文名称：</td>
						<td><input id="indicatorCname" name="indicatorCname" placeholder="指标中文名称" disabled="disabled" type="text" value="${indicatorSta.indicatorCname }" ></td>					
					</tr>
					<tr>
						<td>指标中文类别：</td>
						<td><input id="indicatorCalias" name="indicatorCalias" placeholder="指标中文类别" disabled="disabled" type="text" value="${indicatorSta.indicatorCalias }" ></td>
						<td>指标英文名称：</td>
						<td><input id="indicatorEname" name="indicatorEname" placeholder="指标英文名称" disabled="disabled" type="text" value="${indicatorSta.indicatorEname }" ></td>
						<td>相关指标标准：</td>
						<td><input id="relevantIndicatorSta" name="relevantIndicatorSta" placeholder="相关指标标准" disabled="disabled" type="text" value="${indicatorSta.relevantIndicatorSta }" ></td>					
					</tr>
					<tr>
						<td>相关基础数据标准：</td>
						<td><input id="relevantBasicDataSta" name="relevantBasicDataSta" placeholder="相关基础数据标准" disabled="disabled" type="text" value="${indicatorSta.relevantBasicDataSta }" ></td>
						<td>业务定义：</td>
						<td><input id="businessDefinition" name="businessDefinition" placeholder="业务定义" disabled="disabled" type="text" value="${indicatorSta.businessDefinition }" ></td>
						<td>统计口径：</td>
						<td><input id="statisticalCaliber" name="statisticalCaliber" placeholder="统计口径" disabled="disabled" type="text" value="${indicatorSta.statisticalCaliber }" ></td>					
					</tr>
					<tr>
						<td>业务规则：</td>
						<td><input id="businessRules" name="businessRules" placeholder="业务规则" disabled="disabled" type="text" value="${indicatorSta.businessRules }" ></td>
						<td>公共统计规则：</td>
						<td><input id="publicStatisticalRules" name="publicStatisticalRules" placeholder="公共统计规则" disabled="disabled" type="text" value="${indicatorSta.publicStatisticalRules }" ></td>
						<td>指标格式：</td>
						<td><input id="indicatorFormat" name="indicatorFormat" placeholder="指标格式" disabled="disabled" type="text" value="${indicatorSta.indicatorFormat }" ></td>					
					</tr>
					<tr>
						<td>常用单位：</td>
						<td><input id="commonUnit" name="commonUnit" placeholder="常用单位" disabled="disabled" type="text" value="${indicatorSta.commonUnit }" ></td>
						<td>数据长度：</td>
						<td><input id="dataLength" name="dataLength" placeholder="数据长度" disabled="disabled" type="text" value="${indicatorSta.dataLength }" ></td>
						<td>取值范围：</td>
						<td><input id="ranges" name="ranges" placeholder="取值范围" disabled="disabled" type="text" value="${indicatorSta.ranges }" ></td>					
					</tr>
					<tr>
						<td>取值精度：</td>
						<td><input id="valueAccuracy" name="valueAccuracy" placeholder="取值精度" disabled="disabled" type="text" value="${indicatorSta.valueAccuracy }" ></td>
						<td>权威系统来源：</td>
						<td><input id="authoritativeSystemSource" name="authoritativeSystemSource" placeholder="权威系统来源" disabled="disabled" type="text" value="${indicatorSta.authoritativeSystemSource }" ></td>
						<td>制定依据：</td>
						<td><input id="basisMaking" name="basisMaking" placeholder="制定依据" disabled="disabled" type="text" value="${indicatorSta.basisMaking }" ></td>					
					</tr>
					<tr>
						<td>标准责任部门：</td>
						<td><input id="staResponsibilityDepartment" name="staResponsibilityDepartment" placeholder="标准责任部门" disabled="disabled" type="text" value="${indicatorSta.staResponsibilityDepartment }" ></td>
						<td>最低生成频次：</td>
						<td><input id="minGenerationFrequency" name="minGenerationFrequency" placeholder="最低生成频次" disabled="disabled" type="text" value="${indicatorSta.minGenerationFrequency }" ></td>									
					</tr>
			</table>
			</div>
			<div class="popup_button_container">
			<input id="id" name="id" type="hidden" value="${indicatorSta.id }">
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

