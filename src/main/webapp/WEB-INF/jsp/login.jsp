<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<style>
		*{margin:0;padding:0;border:0;}
		html,body{
			height:100%;
			min-height:662px;
			min-width: 1024px;
		}
		.wrap{
			background:url(img/login.jpg) no-repeat 100%;
			height:100%;
			position: relative;
		}
		.login{
			position: absolute;
			width:35%;
			height:30%;
			top:0;left:0;right:0;bottom:0;margin:auto;
			color:rgb(0,126,236);
		}
		.login h1{
			height:10%;
			padding-bottom:10%;
			text-align:center;
		}
		.login>div{
			width: 70%;
			background:rgba(0,0,0,0.6);
			margin:0 auto 5%;
			height:18%;
			display: flex;
			align-items:center;
		}
		.login>div.inputs img{
			height:60%;
			margin:2%;
		}
		.login>div.inputs input{
			background:none;
			color:#FFF;
			height: 100%;
			border: 0px;outline:none;
		}
		.login>div.inputs input::-webkit-input-placeholder {
	         color: #FFF;
	         font-size: 14px;
	         letter-spacing: 3px;
	     }
		.login .btnLogin{
			cursor:pointer;
			border-radius: 5px;
			background:rgb(0,126,236);
			color:#FFF;
		}
		.login .btnLogin span{
			margin:0 auto;
			letter-spacing: 5px;
		}
		.login .btnLogin span:hover{
			font-weight: bold;
		}
	</style>
	<script src='assets/javascripts/jquery/jquery.min.js' type='text/javascript'></script>
</head>
<body>
	<div class="wrap">
		<div class="login">
			<h1 style="color: rgb(243, 221, 227)">上海国资委数据治理平台</h1>
			<div class="inputs">
				<img src="img/user.png" alt=""><input id="userName" name="userName" type="text" placeholder="账号">
			</div>
			<div class="inputs">
				<img src="img/psw.png" alt=""><input id="password" name="password" type="password" placeholder="密码">
			</div>
			<div class="inputs">
				<span style="background-color:white;width:28px;height:28px;border-radius: 14px;text-align: center;font-size: 10px;color: #000;margin-right: 10px;margin-left: 10px;line-height: 28px;font-weight: bold">验</span><input placeholder="验证码" name="checkCode" id="checkCode" type="text">
				    <img id ="imageCheckCode" style="position:relative;margin-left: 36%;" src="${pageContext.request.contextPath}/pictureCheckCode/getCheckCode" onclick="refresh()"  alt="验证码"/>
			</div>
			<div class="btnLogin" name="button" type="submit" id="submit"><span>登录</span></div>
		</div>
	</div>
	

	<script type='text/javascript'>
	$(document).ready(function(){
		$("#userName").keydown(function(e){
			if(e.which == 13){
				$("#submit").click();
			}
		});
		
		$("#password").keydown(function(e) {
			if (e.which == 13) {
				$("#submit").click();
			}
		});
		
		$("#submit").click(function() {
			var account = $("#userName").val();
			if(account == ''){
				alert("账号必须填写!!");
				return;
			}
			if($("#password").val() == ''){
				alert("密码必须填写！！");
				return;
			}
			/* if($("#checkCode").val() == ''){
				alert("验证码必须填写！！");
				return;
			} */
			$.ajax({
				url : "${pageContext.request.contextPath}/pictureCheckCode/checkCode",
				type :"post",
				data :{
					code : $("#checkCode").val()
				},
				success : function(data){
					/* if(data=="false"){
						alert("验证码不对！请重新输入！");
						return;
					}else{ */
						$.ajax({
							url:"${pageContext.request.contextPath}/validateLogin",
							type:"POST",
							data:{account:$("#userName").val(),password:$("#password").val()},
							success:function(data){
								if(data == 'success'){
									location.href = '${pageContext.request.contextPath}/index';
								}else{
									alert("账号或密码错误");
								}
							},
							error:function(){
								alert("登录失败");
							}
						
						});
					/* } */
				},
				error : function() {
					alert("登陆失败");
					return;
				}
			});
		})
	});
	function refresh(){
		document.getElementById('imageCheckCode').src="${pageContext.request.contextPath}/pictureCheckCode/getCheckCode"+"?randomNum="+Math.random()* 1000;
	}
</script>
</body>
</html>
