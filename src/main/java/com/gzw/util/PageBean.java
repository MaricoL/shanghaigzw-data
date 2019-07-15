package com.gzw.util;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
	//当前页
	private Integer currentPage = 1;
	//每页显示条数
	private Integer pageSize = 10;
	//总条数
	private long totalNum;
	//是否有下一页
	 private Integer isMore;
	 //总页数
	 private Integer totalPage;
	 //开始索引
	 private Integer startIndex;
	 //分页结果
	 private List<T> items;
	 
	 public PageBean() {
		 super();
	 }
	 
	 public PageBean(Integer currentPage, Integer pageSize, long totalNum) {
		 super();
		 this.currentPage = currentPage;
		 this.pageSize = pageSize;
		 this.totalNum = totalNum;
		 this.totalPage = (int) ((this.totalNum+this.pageSize-1)/this.pageSize);
		 this.startIndex = (this.currentPage-1)*this.pageSize;
		 this.isMore = this.currentPage >= this.totalPage?0:1;
	 }

}
