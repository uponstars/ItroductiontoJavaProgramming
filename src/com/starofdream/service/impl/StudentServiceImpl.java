package com.starofdream.service.impl;

import java.util.List;

import com.starofdream.dao.IStudentDao;
import com.starofdream.dao.impl.StudentDaoImpl;
import com.starofdream.entity.Student;
import com.starofdream.service.IStudentService;

//业务逻辑层，逻辑性地增删改查（对DAO层进行组装）
public class StudentServiceImpl implements IStudentService {
	IStudentDao studentDaoImpl = new StudentDaoImpl();
	/**
	 * 通过sno查询学生信息
	 * @param sno 所根据的sno
	 * @return
	 * Student 返回查询到的学生信息
	 * null 查询失败
	 */
	public Student queryStudentBySno(int sno) {
		return studentDaoImpl.queryStudentBySno(sno);
	}
	/**
	 *查询全部学生信息
	 * @return
	 * List<Student> 返回所有学生的集合
	 * null 查询失败
	 */
	public List<Student> queryAllStudents() {
		return studentDaoImpl.queryAllStudents();
	}
	/**
	 * 根据sno查询找到学生并更新学生信息
	 * @param sno 所根据的id
	 * @param student 要更新的学生信息
	 * @return
	 * true 更新成功
	 * false 更新失败
	 */
	public boolean updateStudentBySno(int sno, Student student) {
		if (studentDaoImpl.isExist(sno)) {
			return studentDaoImpl.updateStudentBySno(sno, student);
		}
		return false;
	}
	/**
	 * 通过sno删除学生信息
	 * @param sno
	 * @return
	 * true 删除成功
	 * false 删除失败
	 */
	public boolean deleteStudentBySno(int sno) {
		if (studentDaoImpl.isExist(sno)) {
			return studentDaoImpl.deleteStudentBySno(sno);
		}
		return false;
	}
	/**
	 * 判断数据库中是否存在此人
	 * 1.存在，提示此人已存在，不增加此人
	 * 2.不存在，增加此人
	 * @param student
	 * @return
	 * true 增加成功
	 * false 增加失败
	 */
	public boolean addStudent(Student student) {
		if (!studentDaoImpl.isExist(student.getSno())) {
			studentDaoImpl.addStudent(student);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 获取数据总数
	 */
	public int getTotalCount() {
		return studentDaoImpl.getTotalCount();
	}
	/**
	 * 分页查询数据
	 */
	public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
		return studentDaoImpl.queryStudentsByPage(currentPage, pageSize);
	}
}
