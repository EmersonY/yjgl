package com.kingtopinfo.yjg.entity;

import java.io.Serializable;
	
	/**
 * @author hyh
 * @version 1.0
 */
public class YjgSjdjTimeEntity extends YjgBaseSjdjEntity implements Serializable {
	private static final long	serialVersionUID	= 3542267052335795357L;
	String year; //导出事件年份
	String month; //导出事件月份
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	
	
}