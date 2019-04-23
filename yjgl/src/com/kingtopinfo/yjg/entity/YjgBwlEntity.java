package com.kingtopinfo.yjg.entity;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.YjgBwlEntity
 * @Description YJG_BWL表映射实体类
 * @author cyf
 * @date 2018-01-11 10:32:10
 * @version 1.0
 * @remark create by generator
 */
public class YjgBwlEntity {
	private String bwlzt;  //备忘录状态 1：已读 0：未读
	private String bwlbt;  //备忘录标题
	private String bwljb;  //备忘录级别
	private String baseuserid;  //用户信息编号
	private String bwlwz;  //位置
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bwldate;  //日期
	private String bwlnr;  //备忘录内容
	private String bwlid;  //备忘录编号
	/**
	 * VO
	 */
	private String			bwltimeStart;
	private String			bwltimeEnd;
	private int				searchMonth;
	
	public String getBwltimeStart() {
		return bwltimeStart;
	}
	
	public void setBwltimeStart(String bwltimeStart) {
		this.bwltimeStart = bwltimeStart;
	}
	
	public String getBwltimeEnd() {
		return bwltimeEnd;
	}
	
	public void setBwltimeEnd(String bwltimeEnd) {
		this.bwltimeEnd = bwltimeEnd;
	}
	
	public int getSearchMonth() {
		return searchMonth;
	}
	
	public void setSearchMonth(int searchMonth) {
		this.searchMonth = searchMonth;
	}
	
	public String getBwlzt(){  
		return bwlzt;  
	}
	  
	public void setBwlzt(String bwlzt){  
		this.bwlzt = bwlzt;  
	}  
	public String getBwlbt(){  
		return bwlbt;  
	}
	  
	public void setBwlbt(String bwlbt){  
		this.bwlbt = bwlbt;  
	}  
	public String getBwljb(){  
		return bwljb;  
	}
	  
	public void setBwljb(String bwljb){  
		this.bwljb = bwljb;  
	}  
	public String getBaseuserid(){  
		return baseuserid;  
	}
	  
	public void setBaseuserid(String baseuserid){  
		this.baseuserid = baseuserid;  
	}  
	public String getBwlwz(){  
		return bwlwz;  
	}
	  
	public void setBwlwz(String bwlwz){  
		this.bwlwz = bwlwz;  
	}  
	public java.util.Date getBwldate(){  
		return bwldate;  
	}
	  
	public void setBwldate(java.util.Date bwldate){  
		this.bwldate = bwldate;  
	}  
	public String getBwlnr(){  
		return bwlnr;  
	}
	  
	public void setBwlnr(String bwlnr){  
		this.bwlnr = bwlnr;  
	}  
	public String getBwlid(){  
		return bwlid;  
	}
	  
	public void setBwlid(String bwlid){  
		this.bwlid = bwlid;  
	}  
	
}