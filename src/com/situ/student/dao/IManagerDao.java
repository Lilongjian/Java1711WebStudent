package com.situ.student.dao;

import java.util.List;
import java.util.Map;

import com.situ.student.controller.ManagerSearchCondition;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Course;
import com.situ.student.entity.Manager;


public interface IManagerDao {

	List<Map<String, Object>> findAll();

	List<Map<String, Object>> findPageBeanList(ManagerSearchCondition managerSearchCondition);

	List<Banji> searchByBanjiName(String banjiName);

	List<Course> searchByCourseName(String courseName);

	int addCourse(int banjiid, int courseid);


}
