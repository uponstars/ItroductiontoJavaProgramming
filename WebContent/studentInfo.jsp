<%@page import="com.starofdream.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Student student = (Student) request.getAttribute("student");
	%>
	<!-- 通过表单展示此人信息 -->
	<form action="UpdateStudentServlet">
		学号：<input type="text" name="sno" value="${student.sno }" readonly="readonly"/><br/>
		姓名：<input type="text" name="sname" value="${student.sname }" /><br/>
		年龄：<input type="text" name="sage" value="${student.sage }" /><br/>
		地址：<input type="text" name="saddress" value="${student.saddress }" /><br/>
		<input type="submit" value="修改"/>
		<a href="QueryAllStudentsServlet">返回</a>
	</form>
</body>
</html>