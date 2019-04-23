package com.kingtopinfo.base.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName com.kingtopinfo.base.entity.TblBaseRoleEntity
 * @Description TBL_BASE_ROLE表映射实体类
 * @author cyf
 * @date 2017-06-05 14:21:42
 * @version 1.0
 * @remark create by generator
 */

public class TblBaseRoleEntity implements Serializable {
	
	private static final long	serialVersionUID	= 3905715770153571128L;
	
	private String				baserolemenumid;							// 编号
	private String				baserolepid;								// 角色父编号
	private Integer				state;										// 有效状态
	private String				baseroleresourceid;							// 外键ID
	private String				baseroleid;									// 角色编号
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date		updatetime;									// 更新时间
	private String				baseuserrolemid;							// 用户角色映射编号
	private String				updateuserid;								// 更新人
	private String				rolename;									// 角色名称
	private String				baseroletype;								// 角色类别
	private String				attribute;									// 属性
	
	/**
	 * VO
	 */
	private String			id;
	private String 			pid;
	
	public String getBaserolemenumid() {
		return baserolemenumid;
	}
	
	public void setBaserolemenumid(String baserolemenumid) {
		this.baserolemenumid = baserolemenumid;
	}
	
	public String getBaserolepid() {
		return baserolepid;
	}
	
	public void setBaserolepid(String baserolepid) {
		this.baserolepid = baserolepid;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getBaseroleresourceid() {
		return baseroleresourceid;
	}
	
	public void setBaseroleresourceid(String baseroleresourceid) {
		this.baseroleresourceid = baseroleresourceid;
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
	
	public String getRolename() {
		return rolename;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPid() {
		return pid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getBaseroletype() {
		return baseroletype;
	}
	
	public void setBaseroletype(String baseroletype) {
		this.baseroletype = baseroletype;
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
}