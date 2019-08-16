package com.web.info.system;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 系统底层分页对象(功能描述)
 * @author 张明虎 zhangminghu@yuntengzhiyong.com
 * @date 2014年12月4日 上午12:37:23
 */
public class Pager<T> {
	/**
	 * 每页显示的条数
	 */
	private int pageSize;

	/**
	 * 总页数
	 */
	private long totalPage;

	/**
	 * 当前页
	 */
	private int CurrentPage;

	/**
	 * 当前页面起始记录数
	 */
	private int offset;

	/**
	 * 总条录数
	 */
	private long total;

	/**
	 * 分页的数据
	 */
	private List<T> rows = new ArrayList<T>();

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.CurrentPage = currentPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
