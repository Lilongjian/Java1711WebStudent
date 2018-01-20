package com.situ.student.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ModelConvert {

	public static List<Map<String, Object>> converList(ResultSet resultSet) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();//获得字段数或字段名
			int columnCount = metaData.getColumnCount();
		    while (resultSet.next()) {//迭代resultset
		    	Map<String, Object> map = new HashMap<String,Object>();
				for (int i = 1; i < columnCount; i++) {
					map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
					
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
