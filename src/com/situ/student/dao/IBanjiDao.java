package com.situ.student.dao;


import java.util.List;

import com.situ.student.controller.BanjiMainServlet;
import com.situ.student.controller.BanjiSearchCondition;
import com.situ.student.entity.Banji;
/**
 * student操作的dao 
 */
public interface IBanjiDao {
	/**
	 * 添加学生到数据库
	 * @param student 要添加的学生
	 * @return 返回影响的行数
	 */
	public abstract int add(Banji banji);//save
	
	public abstract int delete(Integer id);
	
	public abstract int update(Banji banji);//modify
	
	public abstract Banji findById(Integer id);
	
	public abstract List<Banji> findByName(String name);
	
	public abstract List<Banji> findAll();

	/**
	 * 检测此用户名是否存在
	 * @param name 用户名
	 * @return 用户名存在：true  不存在：false
	 */
	public abstract boolean checkName(String name);


	public abstract int deleteById(int id);

	public abstract boolean deleteAll(String[] ids);

	public abstract List<Banji> findPageBeanList(int offset, int pageSize);

	public abstract int getTotalCount(BanjiSearchCondition banjiSearchCondition);

	public abstract List<Banji> findPageBeanList(BanjiSearchCondition banjiSearchCondition);

	public abstract int getTotalCount();
}
