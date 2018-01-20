<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%--  <%@include file ="../common/Base.jsp" %> --%>
    <%@ include file="../common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>学生表</title>
		<script type="text/javascript">
		$(function(){
			$("#gender option[value='${searchCondition.gender}']").prop("selected",true);
		})
		function delStudent(id) {
			var isDel = confirm("有外键约束无法删除？");
			if(isDel) {
				location.href = "${ctx}/course?method=findAll&id="+id;
				//location.href = "${pageContext.request.contextPath}/delete.do?id=" + id;
			}
		}
</script>
	</head>
	<body>
		<!--导航 begin -->
		<nav class="navbar navbar-default">
		  <div class="container">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">教务管理系统</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li ><a href="${ctx}/student?method=searchByCondition"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;学生管理 <span class="sr-only">(current)</span></a></li>
		        <li><a href="${ctx}/banji?method=pageList"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a></li>
		        <li class="active"><a href="#"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a></li>
		        <li><a href="${ctx}/managerServlet?method=getManagerPage"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a></li>
		      </ul>
		      
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="${ctx}/jsp/login.jsp"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid --> 
		</nav>
		<!--导航 end -->
		
		<!-- 内容部分 begin-->
		<div class="container">
			<div class="row">
				<!-- 左边部分 begin-->
				<div class="col-md-2">
					<div class="list-group">
					  <a href="#" class="list-group-item active">
					    课程列表
					  </a>
					  <a href="${ctx}/course?method=getStudentAdd" class="list-group-item">课程添加</a>
					</div>
				</div>
				<!-- 左边部分 end-->
				<!-- 右边部分 begin-->
				<div class="col-md-10">
				<form action="${ctx}/student?method=searchByCondition" method="post">
				课程：<input type="text" name="name" value="${searchCondition.name }">
			<%-- 	年龄：<input type="text" name="age" value="${searchCondition.age }"> --%>
				<!-- 性别：<select id="gender" name="gender">
				<option value="">不限</option>
				<option value="男">男</option>
				<option value="女">女</option>
				</select> -->
				<input type="submit" value="搜索"/>
				</form>
					<table class="table table-hover">
				      <thead>
				        <tr>
				          <th>课程号</th>
				          <th>课程</th>
				          <th>学分</th>
				          <th>删除</th>
				          <th>修改</th>
				        </tr>
				      </thead>
				      <tbody>
				      	<c:forEach items="${list}" var="course">
					        <tr>
					          <td>${course.id}</td>
					          <td>${course.name}</td>
					          <td>${course.credit}</td>
					          <td><a href="javascript:delStudent(${course.id})">删除</a></td>
					          <%-- <td><a href="<%=request.getContextPath()%>/delete.do?id=<%=student.getId()%>">删除</a></td> --%>
					          <td><a href="${ctx}/course?method=toUpdate&id=${course.id}">修改</a></td>
					        </tr>
				      	</c:forEach>
				      </tbody>
				    </table>
				</div>
				<!-- 右边部分 end-->
			</div>
			
		</div>
		<!-- 内容部分 end-->
	</body>
</html>