package com.kingtopinfo.base.entity;

import java.io.Serializable;

/**
 * @ClassName com.kingtopinfo.base.entity.TblBaseUserExtEntity
 * @Description TBL_BASE_USER_EXT表映射实体类
 * @author cyf
 * @date 2017-06-05 11:30:16
 * @version 1.0
 * @remark create by generator
 */

public class TblBaseUserExtEntity implements Serializable {
	
	private static final long	serialVersionUID	= 5207787702360414143L;
	private String			baseuserextid;	// 用户扩展信息编号
	private java.util.Date	updatetime;		// 更新时间
	private String			baseuserid;		// 用户信息编号
	private String			tel;			// 固定电话
	private String			phone;			// 移动电话
	private Integer			gender;			// 性别
	private String			updateuserid;	// 更新人
	private int count;
	
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	public String getBaseuserextid() {
		return baseuserextid;
	}
	
	public void setBaseuserextid(String baseuserextid) {
		this.baseuserextid = baseuserextid;
	}
	
	public java.util.Date getUpdatetime() {
		return updatetime;
	}
	
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getBaseuserid() {
		return baseuserid;
	}
	
	public void setBaseuserid(String baseuserid) {
		this.baseuserid = baseuserid;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getGender() {
		return gender;
	}
	
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public String getUpdateuserid() {
		return updateuserid;
	}
	
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	
}