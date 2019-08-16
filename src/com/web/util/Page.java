package com.web.util;

public class Page {

	private int page=1;

	private int rows = 10;
	
	public Page() {}
	
	public Page(Integer page, Integer rows) {
		this.page = ((null == page)||page <=0 ? 1 : page);
		this.rows = ((null == rows)||rows <= 0 ? 10 : rows);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = (page <= 0 ? 1 : page);
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = (rows <= 0 ? 10 : rows);
	}

	public int getFirstResult() {
		return (page - 1) * rows;
	}
	
	public String getSQLByPage(String sql){
		 return "select * from (select x.*, rownum rn from (" + sql + ") x ) where rn > (" + page + "-1)*" + rows + " and  rn <= " + page + "*" + rows;

	}
	

}
