<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小码哥客户关系管理系统</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="/js/jquery-easyui/jquery.min.js"></script> 
</head>
<body>
  <section class="container">
    <div class="login">
      <h1>用户登录</h1>
      <form method="post" action="/login" >
        <p><input type="text" name="username" value="" placeholder="账号"></p>
        <p><input type="password" name="password" value="" placeholder="密码"></p>
        <p class="submit">
        	<input type="button" value="登录" onclick="submitForm();">
        	<input type="button" value="重置" onclick="resetForm();">
        </p>
      </form>
    </div>
  </section>
<div style="text-align:center;" class="login-help">
	<p>Copyright ©2015 广州小码哥教育科技有限公司</p>
</div>

<script type="text/javascript">
function submitForm(){
	//发ajax请求给后台
	//{"success":true,"msg":"登陆成功"}
	//{"success":false,"msg":"登陆失败"}
	
	$.ajax({
		   type: "POST",
		   url: "/login",
		   data: $("form").serialize(), //jquery将form信息封装成对象的方式
		   success: function(data){
				if(data.success){
					//跳转到登陆页面
					window.location.href="/index";					
				}else{
					alert(data.msg);
				}
		   },
			dataType:"json"
		});
	
	
}

function resetForm(){

	
}

</script>


</html>