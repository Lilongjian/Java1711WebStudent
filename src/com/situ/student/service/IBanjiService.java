package com.situ.student.service;

import java.util.List;

import com.situ.student.controller.BanjiSearchCondition;
import com.situ.student.controller.PageBean;
import com.situ.student.controller.StudentSearchCondition;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Student;

public interface IBanjiService {

	List<Banji> findAll();

	/**
	 * 添加学生
	 * @param student
	 * @return Constant.ADD_SUCCESS=1 添加成功
	 * 		   Constant.ADD_FAIL=2     添加数据库失败
	 * 		   Constant.ADD_NAME_REPEAT=3     名字重复
	 */
	int add(Banji banji);

	List<Banji> findByName(String name);

	boolean deleteById(int id);

	Banji findById(int id);

	boolean update(Banji banji);

	boolean deleteAll(String[] ids);

	PageBean getPageBean(int pageNo, int pageSize);

	PageBean<Banji> searchByCondition(BanjiSearchCondition banjiSearchCondition);
    
}
