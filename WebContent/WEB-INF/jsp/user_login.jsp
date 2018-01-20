<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ include file="../common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
       #location{
       margin:50px auto;
       }
 </style>
<script src="/Java1711Web/lib/jquery/jquery-1.11.1.js" type="text/javascript"></script>
 <script type="text/javascript">
 function refreshCode(){
	$("#codeImg").attr("src","/Java1711Web/checkImg?" + Math.random()); 
 }
 </script>
</head>
<body>
      
      <form action="${pageContext.request.contextPath}/login?method=login" method="post" id="location">
	  <div class="form-group">
	    <label for="exampleInputEmail1">用户名：</label>
	    <input type="text" name="name">
	   </div>
	  <div class="form-group">
	    <label for="exampleInputPassword1">密码：</label>
	    <input type="password" name="password"><br/>
	  </div>
	  验证码：<input type="text" name="checkCode">&nbsp;&nbsp;<img src="${ctx}/checkImg" id="codeImg" onclick="refreshCode()"><br>
	  <button type="submit" class="btn btn-default" >登录</button>
	</form>
	
</body>
</html>