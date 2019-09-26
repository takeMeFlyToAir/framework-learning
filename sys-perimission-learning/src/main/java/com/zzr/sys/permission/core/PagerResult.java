package com.zzr.sys.permission.core;

import java.io.Serializable;
import java.util.List;

/**
 * Bootstrap Table返回值
 * @author zzr
 *
 */
public class PagerResult<T> implements Serializable {

	private static final long serialVersionUID = 3141067807832984876L;

	private int pageNo = 1;

	private int pageSize = 10;

	private List<T> list;


	private long rowCount;

	/** 显示页码数 */
	private int offsetSize = 3;
	/**
	 * 分页条件，从多少条查数据
	 */
	private int offset = 3;

	/**
     * 过滤后的总条数
	 */
	private long recordsFiltered;

	private String sEcho;

	public PagerResult(){
	}

	public PagerResult(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public PagerResult(List<T> list, long rowCount) {
		this.list = list;
		this.rowCount = rowCount;
	}

	public void initData(List<T> list, long rowCount){
		this.list = list;
		this.rowCount = rowCount;
	}

	public int getOffsetSize() {
		return offsetSize;
	}

	public void setOffsetSize(int offsetSize) {
		this.offsetSize = offsetSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> data) {
		this.list = data;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public int getOffset() {
		return (pageNo-1)*pageSize;
	}

	public void setOffset() {
		this.offset = (pageNo-1)*pageSize;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
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

	/**
	 * 第一条数据位置
	 *
	 * @return
	 */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 获取总页数
	 */
	public long getPageCount() {
		if (rowCount % pageSize == 0)
			return rowCount / pageSize;
		else
			return (rowCount / pageSize) + 1;
	}
}

