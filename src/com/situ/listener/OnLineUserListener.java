package com.situ.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.junit.Test;

import com.situ.student.entity.User;

public class OnLineUserListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		 //将创建好的空表放到list集合
		List<User> onLineUserList = new ArrayList<User>();
		//放大servletContext域对象中
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("onLineUserList", onLineUserList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
