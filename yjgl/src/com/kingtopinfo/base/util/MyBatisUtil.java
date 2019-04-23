package com.kingtopinfo.base.util;

import org.apache.ibatis.session.RowBounds;

public class MyBatisUtil {

	/**
	 * 通过当前页和每页条数，创建RowBounds
	 * @param page
	 * @param row
	 * @return
	 */
	public static RowBounds rowBounds(int row,int page){
		int start = (page-1)*row;
		return new RowBounds(start,row);
	}
}
