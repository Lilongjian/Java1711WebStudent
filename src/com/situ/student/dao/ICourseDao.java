package com.situ.student.dao;


import java.util.List;

import com.situ.student.entity.Course;
/**
 * student操作的dao 
 */
public interface ICourseDao {
	/**
	 * 添加学生到数据库
	 * @param student 要添加的学生
	 * @return 返回影响的行数
	 */
	public abstract int add(Course course);//save
	
	public abstract int delete(Integer id);
	
	public abstract int update(Course course);//modify
	
	public abstract Course findById(Integer id);
	
	public abstract List<Course> findByName(String name);
	
	public abstract List<Course> findAll();

	/**
	 * 检测此用户名是否存在
	 * @param name 用户名
	 * @return 用户名存在：true  不存在：false
	 */
	public abstract boolean checkName(String name);


	public abstract int deleteById(int id);
}
