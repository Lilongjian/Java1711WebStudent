package com.situ.student.entity;

import java.io.Serializable;
import java.util.Date;

public class Manager implements Serializable {
	private String studentName;
	private Integer age;
	private String banjiName;
	private String courseName;
	private Integer credit;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public String getBanjiName() {
		return banjiName;
	}
	public void setBanjiName(String banjiName) {
		this.banjiName = banjiName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Manager(String studentName, Integer age, String banjiName, String courseName, Integer credit) {
		super();
		this.studentName = studentName;
		this.age = age;
		this.banjiName = banjiName;
		this.courseName = courseName;
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "Manager [age=" + age + ", studentName=" + studentName + ", credit=" + credit + ", banjiName="
				+ banjiName + ", courseName=" + courseName + "]";
	}
	


}
