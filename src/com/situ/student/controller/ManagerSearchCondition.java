package com.situ.student.controller;

public class ManagerSearchCondition {
	private String studentName;
	private String banjiName;
	private String courseName;
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	
	public ManagerSearchCondition(String studentName, String banjiName, String courseName) {
		super();
		this.studentName = studentName;
		this.banjiName = banjiName;
		this.courseName = courseName;
	}
	
	public ManagerSearchCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ManagerSearchCondition(String banjiName, String courseName) {
		super();
		this.banjiName = banjiName;
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "ManagerSearchCondition [studentName=" + studentName + ", banjiName=" + banjiName + ", courseName="
				+ courseName + "]";
	}
	
    	
}
