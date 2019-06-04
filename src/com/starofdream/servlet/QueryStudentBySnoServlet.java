package com.starofdream.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.starofdream.entity.Student;
import com.starofdream.service.IStudentService;
import com.starofdream.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryStudentBySnoServlet
 */
public class QueryStudentBySnoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接收前端的值
		int sno = Integer.parseInt(request.getParameter("sno"));
		
		//调用service层查询
		IStudentService studentServiceImpl = new StudentServiceImpl();
		Student student = studentServiceImpl.queryStudentBySno(sno);
		
		//将学生信息设置进request域中
		request.setAttribute("student", student);
		//将学生信息传到前端页面studentInfo.jsp显示
		request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
