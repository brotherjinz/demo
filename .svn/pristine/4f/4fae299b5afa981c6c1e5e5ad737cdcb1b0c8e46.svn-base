package com.web.util;

public class Pagen {

	private int pageNo;

	private int pageSize=10;

	public Pagen() {
	}

	public Pagen(Integer pageNo, Integer pageSize) {
		this.pageNo = ((null == pageNo)||pageNo <=0 ? 1 : pageNo);
		this.pageSize = ((null == pageSize)||pageSize <= 0 ? 10 : pageSize);
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = (pageNo <= 0 ? 1 : pageNo);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = (pageSize <= 0 ? 1 : pageSize);
	}

	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	public String getSQLByPage(String sql){
		return "select * from (select x.*, rownum rn from (" + sql + ") x ) where rn > (" + pageNo + "-1)*" + pageSize + " and  rn <= " + pageNo + "*" + pageSize;
	}
	
	public static String getSQLByPage(String sql, int num, int pagesize) {
		return "select * from (select x.*, rownum rn from (" + sql + ") x ) where rn > (" + num + "-1)*" + pagesize + " and  rn <= " + num + "*" + pagesize;
	}
}
