<%@page import="com.situ.student.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Student student = (Student)request.getAttribute("student");
	%>
	<form action="<%=request.getContextPath()%>/student?method=update" method="post">
		<input type="hidden" name="id" value="<%=student.getId()%>"/>
		<%-- 姓名：<input type="text" name="name" value="<%=student.getName()%>"/><br/> --%>
		姓名：<input type="text" name="name" value=""/><br/>
		年龄：<input type="text" name="age" value="<%=student.getAge()%>"/><br/>
		性别：<input type="text" name="gender" value="<%=student.getGender()%>"/><br/>
		地址：<input type="text" name="address" value="<%=student.getAddress()%>"/><br/>
	<input type="submit" value="修改"/>
</form>
</body>
</html>