package com.situ.student.service;

import java.util.List;

import com.situ.student.controller.StudentSearchCondition;
import com.situ.student.entity.Course;
import com.situ.student.entity.Student;

public interface ICourseService {

	List<Course> findAll();

	/**
	 * 添加学生
	 * @param student
	 * @return Constant.ADD_SUCCESS=1 添加成功
	 * 		   Constant.ADD_FAIL=2     添加数据库失败
	 * 		   Constant.ADD_NAME_REPEAT=3     名字重复
	 */
	int add(Course course);

	/*List<Student> showStudentAndBanjiInfo();*/

	List<Course> findByName(String name);

	boolean deleteById(int id);

	 Course findById(int id);

	boolean update(Course course);


	/*List<Student> searchByCondition(StudentSearchCondition studentSearchCondition);*/

}
