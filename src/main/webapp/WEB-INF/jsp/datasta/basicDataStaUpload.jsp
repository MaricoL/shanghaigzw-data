<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>基础数据标准导入页面</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />

<link
	href='${pageContext.request.contextPath}/assets/stylesheets/light-theme.css'
	id='color-settings-body-color' media='all' rel='stylesheet'
	type='text/css' />	
	
<link href='${pageContext.request.contextPath}/assets/stylesheets/bootstrap/bootstrap.css' rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/assets/font/iconfont.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/assets/css/demo.css" rel="stylesheet" type="text/css">
<!-- / window 弹出提示-->
<link href='${pageContext.request.contextPath}/css/window.css' rel='stylesheet' type='text/css' />

<!-- 时间插件 -->
<link href='${pageContext.request.contextPath}/css/jedate.css' rel='stylesheet' type='text/css' />

<!-- / jquery -->
<script src='${pageContext.request.contextPath}/js/jquery.min.js' type='text/javascript'></script>
<script src='${pageContext.request.contextPath}/js/window.js' type='text/javascript'></script>
<!-- 日期插件 -->
<script src='${pageContext.request.contextPath}/js/jquery.jedate.min.js' type='text/javascript'></script>

</head>
<body>

<form accept-charset="UTF-8" id="form1" method="post" style="margin-bottom: 0;" enctype="multipart/form-data">
	<div class="main_title">基础数据标准文件上传</div>
	
	<div class="container">
		<div class="list_container">
			<div class="table_container_title">基础数据标准文件上传</div>
				<table class="popup_table">
					<tr>
						<td>上传附件：</td>
						<td><a
							href="javascript:void(0);" class="upload upload1">
							<input
								type="file" style="display: none;" name="reasonf" id="input1"
								onchange="getfilename1(this);"><span id="addFile">未上传任何文件</span>
						</a>
							<div class="popup_add"
								onclick="document.getElementById('input1').click();"
								style="cursor: pointer;">
								<i class="iconfont icon-fujianshangchuan"></i> <span>上传</span>
							</div><td> 
					</tr>					
				</table>
				<div class="clearfix" style="height: 20px;"></div>
				
			</div>		     		           
			<div class="popup_button_container">
				<input type="button" class="popup_button main_button" onclick="_upload();" value="导入文件">
				<input type="button" class="popup_button blue_button" onclick="_close();" value="关闭">
			</div>
						
		
	</div>
</form>


<div id="loading" style="height: 100%; position: absolute; width: 100%; top: 0; text-align: center; background-color: #e2d9d9; opacity: 0.5;">
	<img src='${pageContext.request.contextPath}/assets/images/ajax-loaders/11.gif' style="position: absolute; top: 50%;" />
</div>

<script src='${pageContext.request.contextPath}/assets/javascripts/bootstrap/bootstrap.min.js' type='text/javascript'></script>



<!-- 完成新增与更改 -->
<script type="text/javascript">

$("#loading").hide();

$('input.time').jeDate({
	format:"YYYY-MM-DD"
});

function _upload() {

	   parent.win.confirm('确定要导入吗？',function(r) {
			if (r) {	
				var formData = new FormData($("#form1")[0]); 
				var url = '${pageContext.request.contextPath}/basicDataSta/inport';
				$.ajax({
					type : 'post',
					url : url,
					dataType : 'json',
					cache : false,
					async : false,
					enctype: 'multipart/form-data',
					contentType: false,					
					data : formData,
					processData: false,
					beforeSend : function(XMLHttpRequest) {
						$("#loading").show();
					},
					success : function(data) {
						if (data.flag == "1") {
							win.successAlert(data.message,function() {
								
						});
						
							setTimeout(_close,2e3);
						window.opener.location.reload();
						} else {
							parent.win.errorAlert("保存失败！" + data.message,function() {
							});
						}
					},
					complete : function(XMLHttpRequest,textStatus) {
						$("#loading").hide();
					},
					error : function() {
						parent.win.errorAlert("保存失败！",function() {
						});
					}
				});
			}
		})
	}

	    function getfilename1(el) {
			_fileChk(el);// 文件校验
			var _el = el.files;
			var _name = "";
			for (var i = 0; i < _el.length; i++) {
				if (i == _el.length - 1) {
					_name += _el[i].name
				} else {
					_name += _el[i].name + '、'
				}
				$('.upload1 span').html(_name);
			}
			
			
			// 添加删除
			if(_name!=null && _name!=""){
				//$('.upload1 span').html("");// 清空文件名
				var delHtml = "<i class=del style='cursor: pointer;' onclick='clearFile()'>&times;</i>";
				$('.upload1 span').append(delHtml);
			}
		};
		
			var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
			// 附件校验
			function _fileChk(target, id) {
				var fileSize = 0;
				var filetypes = [ 
						".xls",".xlsx"];
				var filepath = target.value;
				var filemaxsize = 1024 * 5;//5M 
				if (filepath) {
					var isnext = false;
					var fileend = filepath.substring(filepath.lastIndexOf(".")).toLowerCase(); // 取最后一个“.”
					if (filetypes && filetypes.length > 0) {
						for (var i = 0; i < filetypes.length; i++) {
							if (filetypes[i] == fileend) {
								isnext = true;
								break;
							}
						}
					}
					if (!isnext) {
						win
								.generalAlert("请上传以下类型的附件！(.xls)");

						target.value = "";
						return;
					}
				} else {
					return;
				}
				if (isIE && !target.files) {
					var filePath = target.value;
					var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
					if (!fileSystem.FileExists(filePath)) {
						win.generalAlert("附件不存在，请重新输入！");
						return;
					}
					var file = fileSystem.GetFile(filePath);
					fileSize = file.Size;
				} else {
					fileSize = target.files[0].size;
				}

				var size = fileSize / 1024;
				if (size > filemaxsize) {
					win.generalAlert("附件大小不能大于" + filemaxsize*6 / 1024 + "M！");
					target.value = "";
					return;
				}
				if (size <= 0) {
					win.generalAlert("附件大小不能为0M！");
					target.value = "";
					return;
				}
			}
			

	 function chkIsEmpty(objId) {
		if (!$.trim($("#" + objId).val())) { // "",null,undefined
			return false;
		} else {
			return true;
		}
	}  
	
	
	 function delfile(e,f,type){
		 parent.win.confirm('确认删除此文件吗？',function(r) {
			if (r) { 
		var id= $("#id").val();
		var url = "${pageContext.request.contextPath}/manger/delfile?id="+id+"&uuid="+f+"&type="+type;
		 $.ajax({
	        url : url,
	        type : "POST",
	        async: false,
	        cache: false,
	        contentType: false,
	        processData: false,
	        success : function(data) {
	            if (data == "success"){
	                win.successAlert("删除成功",function(){	             	                
	            });
         $("#fileListDiv").remove();
         var attachmentVal = $("#file").val();
         if (attachmentVal==f) {
         	$("#file").val("");
			} 
         return; 
	             
				}else{
	                win.errorAlert("删除失败");
	                return;
				}
	        }
	    });
			 }}) 
	}

	
			
			function clearFile(){
				parent.win.confirm('确认删除此文件吗？',function(r) {
					if (r) {
						var id = $("#id").val();
						if (id!=null || id!="") {// 新增时
							
						} else{
							$("#file").val("");
						}
						$("#input1").val("");  
						$('.upload1 span').html("未上传任何文件");
						console.log("file::::::::::::::::"+$("#file").val());
						
					}})	
			}

	function _close(){
		window.close();
	}
</script>


</body>
<style type="text/css">
select.error {
	border-color: red !important;  
}
</style>

</html>