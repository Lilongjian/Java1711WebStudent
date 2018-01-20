package com.situ.student.service.impl;

import java.util.List;

import com.situ.student.dao.ICourseDao;
import com.situ.student.dao.impl.CourseDaoImpl;
import com.situ.student.entity.Course;
import com.situ.student.service.ICourseService;
import com.situ.student.util.Constant;

public class CourseServiceImpl implements ICourseService {
	private  ICourseDao courseDao = new CourseDaoImpl();
	
	@Override
	public List<Course> findAll() {
		List<Course> list = courseDao.findAll();
		for (Course course : list) {
			
		}
		return courseDao.findAll();
	}

	@Override
	public int add(Course course) {
		//判断此用户名是否存在，如果存在就显示：此用户名已经存在
		if (courseDao.checkName(course.getName())) {
			return Constant.ADD_NAME_REPEAT;
		} else {//用户名不存在，可以直接添加到数据库
			// return studentDao.add(student) > 0 ? true : false;
			int count = courseDao.add(course);
			if (count > 0) {
				return Constant.ADD_SUCCESS;
			} 
			return Constant.ADD_FAIL;
		}
	}

	@Override
	public List<Course> findByName(String name) {
		return courseDao.findByName(name);
	}

	@Override
	public boolean deleteById(int id) {
		if (courseDao.deleteById(id) > 0) {
			return true;
		} 
		
		return false;
	}

	@Override
	public Course findById(int id) {
		return courseDao.findById(id);
	}

	@Override
	public boolean update(Course course) {
		if (courseDao.update(course) > 0) {
		return true;
		} 
		
		return false;
	}
	/*private IStudentDao studentDao = new StudentDaoImpl();

	@Override
	public List<Student> findAll() {
		List<Student> list = studentDao.findAll();
		for (Student student : list) {
			
		}
		return studentDao.findAll();
	}

	@Override
	public int add(Student student) {
		//判断此用户名是否存在，如果存在就显示：此用户名已经存在
		if (studentDao.checkName(student.getName())) {
			return Constant.ADD_NAME_REPEAT;
		} else {//用户名不存在，可以直接添加到数据库
			// return studentDao.add(student) > 0 ? true : false;
			int count = studentDao.add(student);
			if (count > 0) {
				return Constant.ADD_SUCCESS;
			} 
			return Constant.ADD_FAIL;
		}
	}

	@Override
	public List<Student> showStudentAndBanjiInfo() {
		return studentDao.showStudentAndBanjiInfo();
	}

	@Override
	public List<Student> findByName(String name) {
		return studentDao.findByName(name);
	}

	@Override
	public boolean deleteById(int id) {
		if (studentDao.deleteById(id) > 0) {
			return true;
		} 
		
		return false;
		
		if (studentDao.deleteById(id) > 0) {
			return true;
		} else {
			return false;
		}
		
		
		int count = studentDao.deleteById(id);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Student findById(int id) {
		return studentDao.findById(id);
	}

	@Override
	public boolean update(Student student) {
		if (studentDao.update(student) > 0) {
			return true;
		} 
		
		return false;
	}

	@Override
	public List<Student> searchByCondition(StudentSearchCondition studentSearchCondition) {
		// TODO Auto-generated method stub
		return studentDao.searchByCondition(studentSearchCondition);
	}*/

	

}
