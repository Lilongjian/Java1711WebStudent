package com.situ.student.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.situ.student.controller.ManagerSearchCondition;
import com.situ.student.dao.IManagerDao;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Course;
import com.situ.student.entity.Manager;
import com.situ.student.entity.Student;
import com.situ.student.util.C3P0Util;
import com.situ.student.util.JDBCUtil;
import com.situ.student.util.ModelConvert;

public class ManagerDaoImpl implements IManagerDao {
	private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
	@Override
	public List<Map<String, Object>> findAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			try {
				connection = JDBCUtil.getConnection();
				String sql = "SELECT s.name  AS s_name,age,b.name AS b_name,c.credit,c.name AS c_name,c.id "
						+ "FROM student AS s INNER JOIN banji AS b ON s.banji_id=b.id INNER JOIN banji_course AS "
						+ "bc ON b.id=bc.banji_id INNER JOIN course AS c ON bc.course_id=c.id;";
				preparedStatement = connection.prepareStatement(sql);
				System.out.println(preparedStatement.toString());
				resultSet = preparedStatement.executeQuery();
				System.out.println(resultSet);
				list = ModelConvert.converList(resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}

	public List<Map<String, Object>> findAllByDBUtils() {
		System.out.println("ManagerDaoImpl.findAllByDBUtils()");
		String sql = "SELECT s.name  AS s_name,age,b.name AS b_name,c.name AS c_name,c.credit "
				+ "FROM student AS s INNER JOIN banji AS b ON s.banji_id=b.id "
				+ "INNER JOIN banji_course AS bc ON b.id=bc.banji_id "
				+ "INNER JOIN course AS c ON bc.course_id=c.id;";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = queryRunner.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> findPageBeanList(ManagerSearchCondition managerSearchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql="SELECT s.name  AS s_name,age,b.name AS b_name,c.credit,c.name AS c_name,c.id FROM student AS s INNER JOIN banji AS b ON s.banji_id=b.id INNER JOIN banji_course AS bc ON b.id=bc.banji_id "
				+ "INNER JOIN course AS c ON bc.course_id=c.id where 1=1 ";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUtil.getConnection();
			if (managerSearchCondition.getStudentName() != null && !"".equals(managerSearchCondition.getStudentName())) {
				sql+=" and s.name like ? ";
				conditionList.add("%" + managerSearchCondition.getStudentName() + "%");
			}
			if (managerSearchCondition.getBanjiName() != null && !"".equals(managerSearchCondition.getBanjiName())) {
				sql+=" and b.name = ? ";
				conditionList.add(managerSearchCondition.getBanjiName());								
			}
			if (managerSearchCondition.getCourseName() != null && !"".equals(managerSearchCondition.getCourseName())) {
				sql+=" and c.name = ? ";
				conditionList.add(managerSearchCondition.getCourseName());								
			}
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < conditionList.size(); i++) {
				preparedStatement.setObject(i+1,conditionList.get(i) );
			    }
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			/*while(resultSet.next()){
				String studentName = resultSet.getString("s.name");
				System.out.println(studentName);
				int age = resultSet.getInt("age");
				String banjiName = resultSet.getString("b.name");
				String courseName = resultSet.getString("c.name");
				int credit = resultSet.getInt("credit");
				Manager manager = new Manager(studentName, age, banjiName, courseName, credit);
			list.add(manager);
			}*/
			list = ModelConvert.converList(resultSet);
			System.out.println("多表查询" + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Banji> searchByBanjiName(String banjiName) {
		/*Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql="SELECT id,name FROM banji  WHERE NAME=?;";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1,banjiName);
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			list = ModelConvert.converList(resultSet);
			System.out.println("多表查询" + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;*/
		List<Banji> list = null;
		try {
			String sql = "SELECT id,name FROM banji  WHERE NAME=?;";
			Object[] params = {banjiName};
			list = queryRunner.query(sql, new BeanListHandler<Banji>(Banji.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Course> searchByCourseName(String courseName) {
		// TODO Auto-generated method stub
		List<Course> list = null;
		try {
			String sql = "SELECT id,name FROM course  WHERE NAME=?;";
			Object[] params = {courseName};
			list = queryRunner.query(sql, new BeanListHandler<Course>(Course.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int addCourse(int banjiid, int courseid) {
	 int count = 0;
   	 String sql = "INSERT INTO banji_course(banji_id,course_id) VALUES(?,?);";
   	 Object[] params = {banjiid,courseid};
   	 try {
			count = queryRunner.update(sql, params);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 return count;
	}


}
