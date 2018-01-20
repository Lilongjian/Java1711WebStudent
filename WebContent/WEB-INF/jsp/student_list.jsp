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
			var isDel = confirm("您确认要 删除么？");
			if(isDel) {
				location.href = "${ctx}/student?method=delete&id="+id;
				//location.href = "${pageContext.request.contextPath}/delete.do?id=" + id;
			}
		}
		
		function goPage(pageNo){
			$("#pageNo").val(pageNo);
			$("#searchForm").submit();
		}
		function checkAll(){
			$("input[name=checkedIds]").prop("checked",$("#checkAlls").is(":checked"));
		}
		function deleteAll(){
			var isConfirmDelete = confirm("确认批量删除吗?");
			if(isConfirmDelete){
				$("#mainForm").attr("action","${ctx}/student?method=deleteAll");
				$("#mainForm").submit();
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
		        <li class="active"><a href="student_list.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;学生管理 <span class="sr-only">(current)</span></a></li>
		        <li><a href="${ctx}/banji?method=pageList"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a></li>
		        <li><a href="${ctx}/course?method=findAll"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a></li>
		        <li><a href="${ctx}/managerServlet?method=getManagerPage"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a></li>
		         <li><a href="${ctx}/login?method=getOnLinePage"><span class="glyphicon glyphicon-gift" aria-hidden="true"></span>&nbsp;&nbsp;在线列表</a></li>
		      </ul>
		      
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="${ctx}/login?method=logout"><span style="color:red"><b>${user.name}</b>&nbsp;&nbsp;</span><span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出</a></li>
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
					    学生列表
					  </a>
					  <a href="${ctx}/student?method=getStudentAdd" class="list-group-item">学生添加</a>
					</div>
				</div>
				<!-- 左边部分 end-->
				<!-- 右边部分 begin-->
				<div class="col-md-10">
				<%-- <form action="${ctx}/student?method=searchByCondition" method="post"> --%>
				<form action="${ctx}/student?method=searchByCondition" method="post" id="searchForm">
				<input type="hidden" name="pageNo" id="pageNo"><br/>
				姓名：<input type="text" name="name" value="${searchCondition.name }">
				年龄：<input type="text" name="age" value="${searchCondition.age }">
				性别：<select id="gender" name="gender">
				<option value="">不限</option>
				<option value="男">男</option>
				<option value="女">女</option>
				</select>
				<input type="submit" value="搜索"/>
				</form>
				<input type="button" value="批量删除" onclick="deleteAll()"/>
					<%-- <table class="table table-hover">
				      <thead>
				        <tr>
				          <th><input type="checkbox" id="checkAlls" onclick="checkAll()"></th>
				          <th>ID</th>
				          <th>姓名</th>
				          <th>年龄</th>
				          <th>性别</th>
				          <th>地址</th>
				          <th>删除</th>
				          <th>修改</th>
				        </tr>
				      </thead>
				      <tbody>
				      	<c:forEach items="${pageBean.list}" var="student">
					        <tr>
					          <td><input type="checkbox" name="checkedIds"/></td>
					          <td>${student.id}</td>
					          <td>${student.name}</td>
					          <td>${student.age}</td>
					          <td>${student.gender}</td>
					          <td>${student.address}</td>
					          <td><a href="javascript:delStudent(${student.id})">删除</a></td>
					          <td><a href="<%=request.getContextPath()%>/delete.do?id=<%=student.getId()%>">删除</a></td>
					          <td><a href="${ctx}/student?method=toUpdate&id=${student.id}">修改</a></td>
					        </tr>
				      	</c:forEach>
				      </tbody>
				    </table> --%>
				   <!--  加一个from表单 -->
				    <form action="" id="mainForm" method="post">
				    <table class="table table-hover">
				      <thead>
				        <tr>
				          <th><input type="checkbox" id="checkAlls" onclick="checkAll()"></th>
				          <th>ID</th>
				          <th>姓名</th>
				          <th>年龄</th>
				          <th>性别</th>
				          <th>地址</th>
				          <th>删除</th>
				          <th>修改</th>
				        </tr>
				      </thead>
				      <tbody>
				      	<c:forEach items="${pageBean.list}" var="student">
					        <tr>
					          <td><input type="checkbox" name="checkedIds" value="${student.id}"/></td>
					          <td>${student.id}</td>
					          <td>${student.name}</td>
					          <td>${student.age}</td>
					          <td>${student.gender}</td>
					          <td>${student.address}</td>
					          <td><a href="javascript:delStudent(${student.id})">删除</a></td>
					          <%-- <td><a href="<%=request.getContextPath()%>/delete.do?id=<%=student.getId()%>">删除</a></td> --%>
					          <td><a href="${ctx}/student?method=toUpdate&id=${student.id}">修改</a></td>
					        </tr>
				      	</c:forEach>
				      </tbody>
				    </table>
				    </form>
				    
				    <nav aria-label="Page navigation">
					  <ul class="pagination">
					  
					<!--  上一页begin -->
					    <c:if test="${pageBean.pageNo == 1 }">
					    <li class="disabled">
					      <a href="javascript:void(0)" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    </c:if>
					    <c:if test="${pageBean.pageNo != 1 }">
					    <li>
					      <%-- <a href="${ctx}/student?method=pageList&pageNo=${pageBean.pageNo-1}&pageSzie=3" aria-label="Previous"> --%>
					      <a href="javascript:goPage('${pageBean.pageNo-1}')" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    </c:if>
					   
					    
					    <c:forEach begin="1" end="${pageBean.totalPage}" var = "page">
					    <c:if test="${pageBean.pageNo != page}">
					   <%--  <li><a href="${ctx}/student?method=pageList&pageNo=${page}&pageSize=3">${page}</a></li> --%>
					    <li><a href="javascript:goPage('${page}')">${page}</a></li>
					    </c:if>
					    <c:if test="${pageBean.pageNo == page}">
					   <%--  <li ><a href="${ctx}/student?method=pageList&pageNo=${page}&pageSize=3">${page}</a></li> --%>
					    <li class="active"><a href="javascript:void(0)">${page}</a></li>
					    </c:if>
					    </c:forEach>
					    
					    
					     <c:if test="${pageBean.pageNo == pageBean.totalPage }">
					      <li class="disabled">
					      <a href="javascript:void(0)" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    </c:if>
					     <c:if test="${pageBean.pageNo != pageBean.totalPage }">
					      <li>
					      <a href="${ctx}/student?method=pageList&pageNo=${pageBean.pageNo+1}&pageSzie=8" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    </c:if>
					    
					    
					  </ul>
					</nav>
				</div>
				<!-- 右边部分 end-->
			</div>
			
		</div>
		<!-- 内容部分 end-->
	</body>
</html>