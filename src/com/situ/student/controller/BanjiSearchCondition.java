package com.situ.student.controller;

public class BanjiSearchCondition {
	//当前第几页
		private Integer pageNo;
		//一页有多少条数据
		private Integer pageSize;
		private String name;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public BanjiSearchCondition() {
			super();
			// TODO Auto-generated constructor stub
		}
		public BanjiSearchCondition(Integer pageNo, Integer pageSize, String name) {
			super();
			this.pageNo = pageNo;
			this.pageSize = pageSize;
			this.name = name;
		}
		@Override
		public String toString() {
			return "BanjiSearchCondition [pageNo=" + pageNo + ", pageSize=" + pageSize + ", name=" + name + "]";
		}
        		
}
