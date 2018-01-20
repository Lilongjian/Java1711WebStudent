package com.situ.student.test;

import org.junit.Test;

import com.situ.student.dao.IBanjiDao;
import com.situ.student.dao.impl.BanjiDaoImpl;
import com.situ.student.entity.Banji;

public class BanjiDaoImplTest {
	
	@Test
	public void testAdd(){
		IBanjiDao iBanjiDao = new BanjiDaoImpl();
		Banji banji = new Banji("平面设计");
		int result = iBanjiDao.add(banji);
		if (result > 0) {
			System.out.println("添加成功");			
		}else{
			System.out.println("添加失败");
		}
	}

}
