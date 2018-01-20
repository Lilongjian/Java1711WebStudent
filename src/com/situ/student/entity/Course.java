package com.situ.student.entity;

public class Course {
	private Integer id;
	private String name;
    private Integer credit;
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(Integer id, String name, Integer credit) {
		super();
		this.id = id;
		this.name = name;
		this.credit = credit;
	}
	
	public Course(String name, Integer credit) {
		super();
		this.name = name;
		this.credit = credit;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", credit=" + credit + "]";
	}
	
    

}
