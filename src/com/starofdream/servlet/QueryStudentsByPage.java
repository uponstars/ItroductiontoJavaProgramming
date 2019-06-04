package com.starofdream.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.starofdream.entity.Page;
import com.starofdream.entity.Student;
import com.starofdream.service.IStudentService;
import com.starofdream.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryStudentsByPage
 */
public class QueryStudentsByPage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IStudentService service = new StudentServiceImpl();
		int count = service.getTotalCount();
		//将分页所需的5个字段封装到Page对象中
		Page page = new Page();
		page.setTotalCount(count);
		String cPage = request.getParameter("currentPage");
		if (cPage == null) {
			cPage = "1";
		}
		int currentPage = Integer.parseInt(cPage);
		page.setCurrentPage(currentPage);
		int pageSize = 10;
		page.setPageSize(pageSize);
		List<Student> students = service.queryStudentsByPage(currentPage, pageSize);
		page.setStudents(students);
		
		request.setAttribute("p", page);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
