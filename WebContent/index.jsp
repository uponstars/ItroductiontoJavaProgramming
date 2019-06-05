<%@page import="com.starofdream.entity.Page"%>
<%@page import="com.starofdream.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息列表</title>
</head>
<body>
	<script type="text/javascript">
		var myselect = document.getElementById("pageSize");
		var index = myselect.selectedIndex;
		var pageSize = myselect.options[index].value;
		
	</script>
	<%
		String error = (String) request.getAttribute("error");
		if (error != null) {
			if (error.equals("addError")) {
				out.print("增加失败！");
			} else if (error.equals("success")){
				out.print("增加成功！");
			}
		}
	%>
	<table border="1px" >
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>地址</th>
			<th>操作</th>
		</tr>
		<%
			//获取request域中的数据
			Page p = (Page) request.getAttribute("p");
			for (Student student : p.getStudents()) {
		%>
				<tr>
					<td><a href="QueryStudentBySnoServlet?sno=<%=student.getSno() %>"><%=student.getSno() %></a></td>
					<td><%=student.getSname() %></td>
					<td><%=student.getSage() %></td>
					<td><%=student.getSaddress() %></td>
					<td><a href="DeleteStudentServlet?sno=<%=student.getSno() %>">删除</a></td>
				</tr>
		<%
			}
		%>
	</table>
	<a href="add.jsp">新增</a><br/>
	<%
		if (p.getCurrentPage() == p.getTotalPage()) { //尾页显示首页、上一页
	%>
			<a href="QueryStudentsByPage?currentPage=1">首页</a>
			<a href="QueryStudentsByPage?currentPage=<%= p.getCurrentPage() - 1 %>">上一页</a>
	<%
		}
		else if (p.getCurrentPage() == 1) { //首页显示下一页、尾页
	%>
			<a href="QueryStudentsByPage?currentPage=<%= p.getCurrentPage() + 1 %>">下一页</a>
			<a href="QueryStudentsByPage?currentPage=<%= p.getTotalPage() %>">末页</a>
	<%
		} else { //中间页显示全部
	%>
	<a href="QueryStudentsByPage?currentPage=1">首页</a>
	<a href="QueryStudentsByPage?currentPage=<%= p.getCurrentPage() - 1 %>">上一页</a>
	<a href="QueryStudentsByPage?currentPage=<%= p.getCurrentPage() + 1 %>">下一页</a>
	<a href="QueryStudentsByPage?currentPage=<%= p.getTotalPage() %>">末页</a>
	<% } %>
	<br/>
	<select id="pageSize">
		<option value="5">5</option>
		<option value="10">10</option>
		<option value="15">15</option>
		<option value="20">20</option>
	</select>
</body>
</html>