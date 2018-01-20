package com.situ.student.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.situ.student.controller.BanjiSearchCondition;
import com.situ.student.controller.StudentSearchCondition;
import com.situ.student.dao.IBanjiDao;
import com.situ.student.dao.IStudentDao;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Student;
import com.situ.student.util.JDBCUtil;
import com.sun.org.apache.regexp.internal.REUtil;

import jdk.internal.org.objectweb.asm.commons.StaticInitMerger;

public class BanjiDaoImpl implements IBanjiDao {

	@Override
	public int add(Banji banji) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO banji(id,NAME) VALUES(?,?);";
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, banji.getId());
			preparedStatement.setString(2, banji.getName());
			// the number of milliseconds since January 1, 1970, 00:00:00 GMT
			// represented by this date.
			int result = preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
			if (result > 0) {
				System.out.println("添加成功");
			}else{
				System.out.println("添加失败");
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement);
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Banji banji) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		String sql = "UPDATE banji SET name=? WHERE id=?;";
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banji.getName());
			preparedStatement.setInt(2, banji.getId());
			count = preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement);
		}

		return count;
	}

	@Override
	public Banji findById(Integer searchId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME "
				+ "FROM banji where id=?;";
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, searchId);
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			if (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Banji banji = new Banji(id, name);
				return banji;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		
		return null;
	}

	@Override
	public List<Banji> findByName(String searchName) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM banji where name=?;";
		List<Banji> list = new ArrayList<Banji>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, searchName);
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Banji banji = new Banji(id, name);
				list.add(banji);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Banji> findAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM banji;";
		List<Banji> list = new ArrayList<Banji>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Banji banji = new Banji(id, name);
				list.add(banji);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public boolean checkName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT NAME FROM banji WHERE NAME=?;";
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return false;
	}


	@Override
	public int deleteById(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		String sql = "DELETE FROM banji WHERE id=?;";
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement);
		}

		return count;
	}

	@Override
	public boolean deleteAll(String[] ids) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "delete from banji where id=?";
		    preparedStatement = connection.prepareStatement(sql);
		    for (String string : ids) {
				preparedStatement.setInt(1, Integer.parseInt("string"));
				preparedStatement.addBatch();
			}
		    int[] id = preparedStatement.executeBatch();
		    return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Banji> findPageBeanList(int offset, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM banji limit ?,?;";
		List<Banji> list = new ArrayList<Banji>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Banji banji = new Banji(id, name);
				list.add(banji);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int getTotalCount(BanjiSearchCondition banjiSearchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select count(*) from banji where 1=1 ";
		int count = 0;
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUtil.getConnection();
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return count;
	}

	@Override
	public List<Banji> findPageBeanList(BanjiSearchCondition banjiSearchCondition) {
		Connection connection = null;    
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select id,name from banji where name=? ";
		List<Banji> list = new ArrayList<Banji>();
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUtil.getConnection();
			/*if (banjiSearchCondition.getName() != null
					&& !"".equals(banjiSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + banjiSearchCondition.getName() + "%");
			}*/
			sql +=" limit ?,?";
			
			preparedStatement = connection.prepareStatement(sql);
			/*for(int i = 0; i < conditionList.size();i++){
				  preparedStatement.setObject(i+1, conditionList.get(i));
			}*/
			preparedStatement.setObject(1, banjiSearchCondition.getName());
			int offset = (banjiSearchCondition.getPageNo() - 1) * banjiSearchCondition.getPageSize();
			preparedStatement.setInt(2,offset);
			preparedStatement.setInt(3, banjiSearchCondition.getPageSize());
			
			resultSet = preparedStatement.executeQuery();
			System.out.println("456" + resultSet);
			System.out.println("高级搜索" + preparedStatement);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Banji banji = new Banji(id, name);
				list.add(banji);
				System.out.println(banji);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int getTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select count(*) from banji where 1=1 ";
		int count = 0;
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUtil.getConnection();
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return count;
	}

}
