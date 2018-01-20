<%@page import="com.situ.student.entity.Banji"%>
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
	           Banji banji = (Banji)request.getAttribute("banji");
	%>
	<form action="<%=request.getContextPath()%>/banji?method=update" method="post">
		<input type="hidden" name="id" value="<%=banji.getId()%>"/>
		班名：<input type="text" name="name" value="<%=banji.getName()%>"/><br/>
	<input type="submit" value="修改"/>
</form>
</body>
</html>