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
 * Servlet implementation class UpdateStudentServlet
 */
public class UpdateStudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接受前端传来的值
		int sno = Integer.parseInt(request.getParameter("sno"));
		String sname = request.getParameter("sname");
		int sage = Integer.parseInt(request.getParameter("sage"));
		String saddress = request.getParameter("saddress");
		
		//组装JavaBean
		Student student = new Student(sname, sage, saddress);
		//修改学生信息
		IStudentService studentServiceImpl = new StudentServiceImpl();
		boolean result = studentServiceImpl.updateStudentBySno(sno, student);
		
		//向前端返回结果
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		if (result) {
			response.sendRedirect("QueryAllStudentsServlet");
		} else {
			response.getWriter().print("修改失败！");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
