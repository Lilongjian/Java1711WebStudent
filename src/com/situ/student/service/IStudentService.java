package com.situ.student.service;

import java.util.List;

import com.situ.student.controller.PageBean;
import com.situ.student.controller.StudentSearchCondition;
import com.situ.student.entity.Student;

public interface IStudentService {

	List<Student> findAll();

	/**
	 * 添加学生
	 * @param student
	 * @return Constant.ADD_SUCCESS=1 添加成功
	 * 		   Constant.ADD_FAIL=2     添加数据库失败
	 * 		   Constant.ADD_NAME_REPEAT=3     名字重复
	 */
	int add(Student student);

	List<Student> showStudentAndBanjiInfo();

	List<Student> findByName(String name);

	boolean deleteById(int id);

	Student findById(int id);

	boolean update(Student student);

	

	PageBean getPageBean(int pageNo, int pageSize);

	PageBean searchByCondition(StudentSearchCondition studentSearchCondition);

	boolean checkName(String name);

	boolean deleteAll(String[] ids);

}
