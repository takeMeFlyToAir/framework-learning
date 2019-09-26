package com.zzr.sys.permission.core;

import java.io.Serializable;


public class Pager<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private int pageNo = 0;

	private int pageSize = 10;

	private String sSearch;

	private T condition;

	private String sortName;//排序名称

	private String sortOrder;//排序规则 desc 或者 asc


	public Pager(){
	}

	public Pager(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public T getCondition() {
		return condition;
	}

	public void setCondition(T condition) {
		this.condition = condition;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getsSearch() {
		if(sSearch == null){
			return "";
		}
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
}

