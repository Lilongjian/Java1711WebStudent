package com.situ.student.service;

import java.util.List;
import java.util.Map;

import com.situ.student.controller.ManagerSearchCondition;
import com.situ.student.controller.PageBean;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Course;
import com.situ.student.entity.Manager;


public interface IManagerService {

	List<Map<String, Object>> findAll();

	List<Map<String, Object>> searchByCondition(ManagerSearchCondition managerSearchCondition);

	List<Banji> searchByBanjiName(String banjiName);

	List<Course> searchByCourseName(String courseName);

	int addCourse(int banjiid, int courseid);


}
