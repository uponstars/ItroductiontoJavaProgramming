package com.starofdream.dao;

import java.util.List;

import com.starofdream.entity.Student;

public interface IStudentDao {

	public boolean addStudent(Student student);

	public boolean updateStudentBySno(int sno, Student student);

	public boolean deleteStudentBySno(int sno);
	
	public int getTotalCount();

	public boolean isExist(int sno);

	public Student queryStudentBySno(int sno);

	public Student queryStudentBySname(String sname);

	public List<Student> queryStudentBySage(int sage);

	public List<Student> queryAllStudents();
	
	public List<Student> queryStudentsByPage(int currentPage, int pageSize);
}
