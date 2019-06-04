package com.starofdream.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.starofdream.dao.IStudentDao;
import com.starofdream.entity.Student;
import com.starofdream.util.DBUtil;

//数据访问层，原子性的增删改查
public class StudentDaoImpl implements IStudentDao {

	/**
	 * 在数据库中增加学生信息
	 * 
	 * @param student 传入一个包含学生信息的学生对象
	 * @return true 增加成功 false 增加失败
	 */
	public boolean addStudent(Student student) {
		String sql = "insert into student values (?, ?, ?, ?)";
		Object[] params = {student.getSno(), student.getSname(), student.getSage(), student.getSaddress()};
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * 根据sno修改学生信息
	 * 
	 * @param sno     根据的id
	 * @param student 要更新的学生信息
	 * @return true 修改成功 false 修改失败
	 */
	public boolean updateStudentBySno(int sno, Student student) {
		String sql = "update student set sname = ?, sage = ?, saddress = ? where id = ?";
		Object[] params = {student.getSname(), student.getSage(), student.getSaddress(), sno};
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * 根据学号删除学生信息
	 * 
	 * @param sno
	 * @return true 删除成功 false 删除失败
	 */
	public boolean deleteStudentBySno(int sno) {
		String sql = "delete from student where id = ?";
		Object[] params = {sno};
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * 根据学号判断此人是否存在
	 * 
	 * @param sno
	 * @return boolean true 此人存在 false 此人不存在
	 */
	public boolean isExist(int sno) {
		return queryStudentBySno(sno) == null ? false : true;
	}

	/**
	 * 根据学号查询学生信息
	 * 
	 * @param snoa
	 * @return student Object student Object 此人存在并返回查询对象 null 此人不存在
	 */
	public Student queryStudentBySno(int sno) {
		Student student = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student where id = ?";
			Object[] params = {sno};
			rs = DBUtil.executeQuery(sql, params);
			
			while (rs.next()) {
				int no = rs.getInt("id");
				String sname = rs.getString("sname");
				int sage = rs.getInt("sage");
				String saddress = rs.getString("saddress");
				
				student = new Student(no, sname, sage, saddress);
			}
			return student;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}

	/**
	 * 根据sname查询学生信息
	 * 
	 * @param sname
	 * @return Student 所查询到的学生信息 null 查询失败
	 */
	public Student queryStudentBySname(String sname) {
		Student student = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student where sname = ?";
			Object[] params = {sname};
			rs = DBUtil.executeQuery(sql, params);

			if (rs.next()) {
				int no = rs.getInt("id");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no, name, age, address);
			}

			return student;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}

	/**
	 * 
	 * @param 导入的参数
	 * 
	 * @return 返回一个学生对象的集合
	 */
	public List<Student> queryStudentBySage(int sage) {
		List<Student> students = new ArrayList<>();
		Student student = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student where sage = ?";
			Object[] params = {sage};
			rs = DBUtil.executeQuery(sql, params);

			while (rs.next()) {
				int no = rs.getInt("id");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no, name, age, address);
				students.add(student);
			}

			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}
	/**
	 * 查询数据库student表中的所有学生信息
	 * 
	 * @return List<Student> 返回所有学生组成的一个集合 null 查询失败
	 */
	public List<Student> queryAllStudents() {
		List<Student> students = new ArrayList<>();
		Student student = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student";
			Object[] params = null;
			rs = DBUtil.executeQuery(sql, params);

			while (rs.next()) {
				int no = rs.getInt("id");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no, name, age, address);
				students.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return students;
	}

	/**
	 * 查询总数据的量
	 * @return 返回查询到的数据库行数
	 */
	public int getTotalCount() {
		String sql = "select count(1) from student";
		return DBUtil.getTotalCount(sql);
	}

	/**
	 * 分页查询
	 * @param
	 * currentPage 当前页数
	 * pageSize 页面大小，即数据条数
	 * @return
	 * 学生信息
	 */
	public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
		ResultSet rs = null;
		Student student = null;
		List<Student> students = new ArrayList<>();
		try {
			Object[] params = {(currentPage - 1) * pageSize, pageSize};
			String sql = "select * from student limit ?, ?";
			rs = DBUtil.executeQuery(sql, params);
			
			while (rs.next()) {
				student = new Student(rs.getInt("id"), rs.getString("sname"), rs.getInt("sage"), rs.getString("saddress"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return students;
	}

}
