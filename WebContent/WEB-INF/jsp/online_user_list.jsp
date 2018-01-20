<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%--  <%@include file ="../common/Base.jsp" %> --%>
    <%@ include file="../common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>登录在线</title>
		</head>
		<body>
	    <nav class="navbar navbar-default">
		<div>
		     <ul class="nav navbar-nav">
		     <li class="active"><a href="${ctx}/student?method=pageList"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;主页<span class="sr-only">(current)</span></a></li>
		 	 <li> <a href="${ctx}/login?method=logout">  <span>${user.name}</span> <span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出</a></li>
		 	 </ul>
		</div>
					<table class="table table-hover">
				      <thead>
				        <tr>
				          <th>ID</th>
				          <th>用户名</th>
				          <th>密码</th>
				        </tr>
				      </thead>
				      <tbody>
				      	<c:forEach items="${onLineUserList}" var="user">
					        <tr>
					          <td>${user.id}</td>
					          <td>${user.name}</td>
					          <td>${user.password}</td>
					        </tr>
				      	</c:forEach>
				      </tbody>
				    </table>
				    </nav>
				    </div>
	</body>
</html>