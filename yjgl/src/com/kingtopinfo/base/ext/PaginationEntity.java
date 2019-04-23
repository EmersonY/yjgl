package com.kingtopinfo.base.ext;

import java.util.ArrayList;
import java.util.List;

public class PaginationEntity<T> {

	private Integer total;
	private List<T>	rows	= new ArrayList<T>();
	
	public List<T> getRows() {
		return rows;
	}
	
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
