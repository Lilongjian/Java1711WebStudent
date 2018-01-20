<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%--  <%@include file ="../common/Base.jsp" %> --%>
    <%@ include file="../common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>班级表</title>
		<script type="text/javascript">
		/* $(function(){
			$("#gender option[value='${searchCondition.gender}']").prop("selected",true);
		}) */
		function delStudent(id) {
			var isDel = confirm("有外键约束无法删除？");
			if(isDel) {
				location.href = "${ctx}/banji?method=findAll";
				//location.href = "${pageContext.request.contextPath}/delete.do?id=" + id;
			}
		}
		function checkall(){
			$("input[name=checked]").prop("checked",$("#checkalls").is(":checked"));
		}
		function deleteAll(){
			var isConfirmDelete = confirm("确认批量删除？");
			if(isConfirmDelete){
				$("#mainForm").attr("action","${ctx}/banji?method=deleteAll");
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
		        <li><a href="${ctx}/student?method=searchByCondition"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;学生管理 <span class="sr-only">(current)</span></a></li>
		        <li class="active"><a href="#"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a></li>
		        <li><a href="${ctx}/course?method=findAll"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a></li>
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
					  <a href="${ctx}/banji?method=pageList" class="list-group-item active">
					    班级列表
					  </a>
					  <a href="${ctx}/banji?method=getStudentAdd" class="list-group-item">班级添加</a>
					</div>
				</div>
				<!-- 左边部分 end-->
				<!-- 右边部分 begin-->
				<div class="col-md-10">
				<form action="${ctx}/banji?method=searchByCondition" method="post">
				姓名：<input type="text" name="name" value="${banjiSearchCondition.name }">
				<input type="submit" value="搜索"/>
				</form>
				<form action="" method="post" id="mainForm">
				<input type="button" value="批量删除" onclick="deleteAll()">
					<table class="table table-hover">
				      <thead>
				        <tr>
				          <th><input type="checkbox" id="checkalls" onclick="checkall()"></th>
				          <th>班号</th>
				          <th>班名</th>
				          <th>删除</th>
				          <th>修改</th>
				        </tr>
				      </thead>
				      <tbody>
				      	<c:forEach items="${pageBean.list}" var="banji">
					        <tr>
					          <td><input type="checkbox" name="checked" value="${banji.id}"></td>
					          <td>${banji.id}</td>
					          <td>${banji.name}</td>
					          <td><a href="javascript:delStudent(${banji.id})">删除</a></td>
					          <%-- <td><a href="<%=request.getContextPath()%>/delete.do?id=<%=student.getId()%>">删除</a></td> --%>
					          <td><a href="${ctx}/banji?method=toUpdate&id=${banji.id}">修改</a></td>
					        </tr>
				      	</c:forEach>
				      </tbody>
				    </table>
				    </form>
				    
				    <!-- 表单分页 -->
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
					      <a href="${ctx}/banji?method=pageList&pageNo=${pageBean.pageNo-1}&pageSzie=5" aria-label="Previous">
					      <%-- <a href="javascript:goPage('${pageBean.pageNo-1}')" aria-label="Previous"> --%>
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    </c:if>
					   
					    
					    <c:forEach begin="1" end="${pageBean.totalPage}" var = "page">
					    <c:if test="${pageBean.pageNo != page}">
					    <li><a href="${ctx}/banji?method=pageList&pageNo=${page}&pageSize=5">${page}</a></li>
					   <%--  <li><a href="javascript:goPage('${page}')">${page}</a></li> --%>
					    </c:if>
					    <c:if test="${pageBean.pageNo == page}">
					    <li ><a href="${ctx}/banji?method=pageList&pageNo=${page}&pageSize=5">${page}</a></li>
					    <%-- <li class="active"><a href="javascript:void(0)">${page}</a></li> --%>
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
					      <a href="${ctx}/banji?method=pageList&pageNo=${pageBean.pageNo+1}&pageSzie=5" aria-label="Next">
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