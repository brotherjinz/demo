package com.web.util;

import java.util.List;

public class PageResult<T> {

	private int total=0;
	private String pageTool = null;
	private List<T> pageList = null;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getPageTool() {
		return pageTool;
	}
	public void setPageTool(String pageTool) {
		this.pageTool = pageTool;
	}
	public List<T> getPageList() {
		return pageList;
	}
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

}
