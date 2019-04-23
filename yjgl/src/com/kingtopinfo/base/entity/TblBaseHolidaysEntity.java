package com.kingtopinfo.base.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.TblBaseHolidaysEntity
 * @Description TBL_BASE_HOLIDAYS表映射实体类
 * @author cyf
 * @date 2018-01-12 09:14:28
 * @version 1.0
 * @remark create by generator
 */
public class TblBaseHolidaysEntity extends TblBaseEntity {
	private Integer isholiday;  //是否节假日 1-是 0-否
	private String createperson;  //创建人
	private Integer year;  //年份
	private String remark;  //备注
	private String holidayname;  //节假日名称
	private String updateperson;  //更新人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date updatetime;  //更新时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date createtime;  //创建时间
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date			holidaydate;	// 节假日日期
	private String holidaytype;  //节假日类型 1-休假 2-上班
	private String holidaysid;  //节假日安排ID
	
	public Integer getIsholiday(){  
		return isholiday;  
	}
	  
	public void setIsholiday(Integer isholiday){  
		this.isholiday = isholiday;  
	}  
	public String getCreateperson(){  
		return createperson;  
	}
	  
	public void setCreateperson(String createperson){  
		this.createperson = createperson;  
	}  
	public Integer getYear(){  
		return year;  
	}
	  
	public void setYear(Integer year){  
		this.year = year;  
	}  
	public String getRemark(){  
		return remark;  
	}
	  
	public void setRemark(String remark){  
		this.remark = remark;  
	}  
	public String getHolidayname(){  
		return holidayname;  
	}
	  
	public void setHolidayname(String holidayname){  
		this.holidayname = holidayname;  
	}  
	public String getUpdateperson(){  
		return updateperson;  
	}
	  
	public void setUpdateperson(String updateperson){  
		this.updateperson = updateperson;  
	}  
	public java.util.Date getUpdatetime(){  
		return updatetime;  
	}
	  
	public void setUpdatetime(java.util.Date updatetime){  
		this.updatetime = updatetime;  
	}  
	public java.util.Date getCreatetime(){  
		return createtime;  
	}
	  
	public void setCreatetime(java.util.Date createtime){  
		this.createtime = createtime;  
	}  
	public java.util.Date getHolidaydate(){  
		return holidaydate;  
	}
	  
	public void setHolidaydate(java.util.Date holidaydate){  
		this.holidaydate = holidaydate;  
	}  
	public String getHolidaytype(){  
		return holidaytype;  
	}
	  
	public void setHolidaytype(String holidaytype){  
		this.holidaytype = holidaytype;  
	}  
	public String getHolidaysid(){  
		return holidaysid;  
	}
	  
	public void setHolidaysid(String holidaysid){  
		this.holidaysid = holidaysid;  
	}  
	
}