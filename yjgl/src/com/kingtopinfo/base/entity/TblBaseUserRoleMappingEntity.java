package com.kingtopinfo.base.entity;

import java.io.Serializable;

/**
 * @ClassName com.kingtopinfo.base.entity.TblBaseUserRoleMappingEntity
 * @Description TBL_BASE_USER_ROLE_MAPPING表映射实体类
 * @author cyf
 * @date 2017-06-05 14:25:06
 * @version 1.0
 * @remark create by generator
 */

public class TblBaseUserRoleMappingEntity implements Serializable {
	
	private static final long	serialVersionUID	= -212519414999939029L;
	private String			baseuserid;			// 用户编号
	private String			baseroleid;			// 角色编号
	private java.util.Date	updatetime;			// 更新时间
	private String			baseuserrolemid;	// 用户角色映射编号
	private String			updateuserid;		// 更新人
	
	public String getBaseuserid() {
		return baseuserid;
	}
	
	public void setBaseuserid(String baseuserid) {
		this.baseuserid = baseuserid;
	}
	
	public String getBaseroleid() {
		return baseroleid;
	}
	
	public void setBaseroleid(String baseroleid) {
		this.baseroleid = baseroleid;
	}
	
	public java.util.Date getUpdatetime() {
		return updatetime;
	}
	
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getBaseuserrolemid() {
		return baseuserrolemid;
	}
	
	public void setBaseuserrolemid(String baseuserrolemid) {
		this.baseuserrolemid = baseuserrolemid;
	}
	
	public String getUpdateuserid() {
		return updateuserid;
	}
	
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	
}