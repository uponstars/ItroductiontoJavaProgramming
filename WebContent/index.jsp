<%@page import="com.starofdream.entity.Page"%>
<%@page import="com.starofdream.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息列表</title>
</head>
<body>
<!-- 	<script type="text/javascript">
		var myselect = document.getElementById("pageSize");
		var index = myselect.selectedIndex;
		var pageSize = myselect.options[index].value;
	</script> -->
	<c:if test="${empty requestScope.error }">
		<c:choose>
			<c:when test="${requestScope.error == 'addError' }">
				<p>增加失败！</p>
			</c:when>
			<c:when test="${requestScope.error == 'success' }">
				<p>增加成功！</p>
			</c:when>
		</c:choose>
	</c:if>
	<table border="1px" >
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>地址</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${requestScope.p.students }" var="student">
			<tr>
					<td><a href="QueryStudentBySnoServlet?sno=${student.sno }">${student.sno }</a></td>
					<td>${student.sname }</td>
					<td>${student.sage }</td>
					<td>${student.saddress }</td>
					<td><a href="DeleteStudentServlet?sno=${student.sno }">删除</a></td>
				</tr>
		</c:forEach>
	</table>
	<a href="add.jsp">新增</a><br/>
	<c:choose>
		<c:if test="${requestScope.p.getCurrentPage == requestScope.p.getTotalPage }">
			<a href="QueryStudentsByPage?currentPage=1">首页</a>
			<a href="QueryStudentsByPage?currentPage=${requestScope.p.currentPage - 1 }">上一页</a>
		</c:if>
		<c:if test="${requestScope.p.getCurrentPage == 1 }">
			<a href="QueryStudentsByPage?currentPage=${requestScope.p.currentPage + 1 }">下一页</a>
			<a href="QueryStudentsByPage?currentPage=${requestScope.p.totalPage }">末页</a>
		</c:if>
		<c:otherwise>
			<a href="QueryStudentsByPage?currentPage=1">首页</a>
			<a href="QueryStudentsByPage?currentPage=${requestScope.p.currentPage - 1 }">上一页</a>
			<a href="QueryStudentsByPage?currentPage=${requestScope.p.currentPage + 1 }">下一页</a>
			<a href="QueryStudentsByPage?currentPage=${requestScope.p.totalPage }">末页</a>
		</c:otherwise>
	</c:choose>
	<br/>
	<select id="pageSize">
		<option value="5">5</option>
		<option value="10">10</option>
		<option value="15">15</option>
		<option value="20">20</option>
	</select>
</body>
</html>