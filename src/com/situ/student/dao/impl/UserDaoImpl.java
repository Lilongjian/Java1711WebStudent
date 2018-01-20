package com.situ.student.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.situ.student.dao.IUserDao;
import com.situ.student.entity.User;
import com.situ.student.util.C3P0Util;

public class UserDaoImpl implements IUserDao {
	private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());

	@Override
	public User login(String name, String password) {
		String sql = "select * from user where name = ? and password = ?";
		Object[] params = {name,password};
		User user = null;
		try {
			user = queryRunner.query(sql, new BeanHandler<User>(User.class), params);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				        
		
		return user;
	}

}
