package com.kingtopinfo.base.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author cyf
 * @date 2017-08-18 14:04:49
 * @version 1.0
 * @remark create by generator
 */
public class TblBaseEntity implements Serializable {
	
	private static final long	serialVersionUID	= -6934785358749171142L;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date				timeStart;									// 查询开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date				timeEnd;									// 查询结束时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date				timeStartCopy;								// 查询开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date				timeEndCopy;								// 查询结束时间
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date				timeStartNoDay;								// 查询开始时间
	private String				order				= "desc";
	private String				sort;
	private String				baseType;									// 基础类型
	
	public Date getTimeStartNoDay() {
		return timeStartNoDay;
	}
	
	public void setTimeStartNoDay(Date timeStartNoDay) {
		this.timeStartNoDay = timeStartNoDay;
	}
	
	public Date getTimeStartCopy() {
		return timeStartCopy;
	}
	
	public void setTimeStartCopy(Date timeStartCopy) {
		this.timeStartCopy = timeStartCopy;
	}
	
	public Date getTimeEndCopy() {
		return timeEndCopy;
	}
	
	public void setTimeEndCopy(Date timeEndCopy) {
		this.timeEndCopy = timeEndCopy;
	}
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getSort() {
		return sort;
	}
	
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public Date getTimeStart() {
		return timeStart;
	}
	
	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
	
	public Date getTimeEnd() {
		return timeEnd;
	}
	
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	public String getBaseType() {
		return baseType;
	}
	
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}
	
}