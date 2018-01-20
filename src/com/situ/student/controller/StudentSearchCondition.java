package com.situ.student.controller;

public class StudentSearchCondition {
	//当前第几页
	private Integer pageNo;
	//一页有多少条数据
	private Integer pageSize;
	private String name;
	private String age;
	private String gender;
	
	
	
	public StudentSearchCondition(Integer pageNo, Integer pageSize, String name, String age, String gender) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public StudentSearchCondition(String name, String age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public StudentSearchCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
//	@Override
//	public String toString() {
//		return "StudentSearchCondition [name=" + name + ", age=" + age + ", gender=" + gender + "]";
//	}
	@Override
	public String toString() {
		return "StudentSearchCondition [pageNo=" + pageNo + ", pageSize=" + pageSize + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + "]";
	}
	
	

}
