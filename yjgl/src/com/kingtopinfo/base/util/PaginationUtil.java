package com.kingtopinfo.base.util;

public class PaginationUtil {

	public static Long page(Long rows,Integer row){
		
		if((rows%row) == 0){
			return rows/row;
		}else{
			return (rows/row)+1;
		}
		
	}
}
