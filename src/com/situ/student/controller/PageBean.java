package com.situ.student.controller;

import java.util.List;

import com.situ.student.entity.Student;

public class PageBean<T> {
	//当前是第几页
	private Integer pageNo;
	//一页有多少数据
	private Integer pageSize;
	//总记录数
    private Integer totalCount;
    //共有多少也
	private Integer totalPage;
	//当前页的数据
	private List<T> list;
	
	
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PageBean(Integer pageNo, Integer pageSize, Integer totalCount, Integer totalPage, List<T> list) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.list = list;
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
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount=" + totalCount + ", totalPage="
				+ totalPage + ", list=" + list + "]";
	}
	
}
