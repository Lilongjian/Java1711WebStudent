<%@page import="com.situ.student.entity.Course"%>
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
		Course course = (Course)request.getAttribute("course");
	System.out.println("第二次:" + course);
	%>
	<form action="<%=request.getContextPath()%>/course?method=update" method="post">
		<input type="hidden" name="id" value="<%=course.getId()%>"/>
		课程：<input type="text" name="name" value="<%=course.getName()%>"/><br/>
		学分：<input type="text" name="credit" value="<%=course.getCredit()%>"/><br/>
	<input type="submit" value="修改"/>
</form>
</body>
</html>