package com.kingtopinfo.yjg.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingtopinfo.activiti.entity.ActTaskEntity;

/**
 * @ClassName entity.YjgSjdjUserMappingEntity
 * @Description YJG_SJDJ_USER_MAPPING表映射实体类
 * @author cyf
 * @date 2017-09-21 17:43:08
 * @version 1.0
 * @remark create by generator
 */
public class YjgSjdjUserMappingEntity extends ActTaskEntity implements Serializable {
	private static final long	serialVersionUID	= 1252530930413711474L;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date		czsj;										// 操作时间
	private Integer				sfjd;										// 是否接单 0:未确认 1:接受 2:不接受
	private String				baseuserid;									// 用户信息编号
	private String				jjly;										// 拒绝理由
	private String				sjdjusermappingid;							// 角色案件映射编号
	private String				sjdjid;										// 事件登记编号
	private String				type;										// 类型：1:权属 2:施工队
	
	public java.util.Date getCzsj() {
		return czsj;
	}
	
	public void setCzsj(java.util.Date czsj) {
		this.czsj = czsj;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getSfjd() {
		return sfjd;
	}
	
	public void setSfjd(Integer sfjd) {
		this.sfjd = sfjd;
	}
	
	public String getBaseuserid() {
		return baseuserid;
	}
	
	public void setBaseuserid(String baseuserid) {
		this.baseuserid = baseuserid;
	}
	
	public String getJjly() {
		return jjly;
	}
	
	public void setJjly(String jjly) {
		this.jjly = jjly;
	}
	
	public String getSjdjusermappingid() {
		return sjdjusermappingid;
	}
	
	public void setSjdjusermappingid(String sjdjusermappingid) {
		this.sjdjusermappingid = sjdjusermappingid;
	}
	
	public String getSjdjid() {
		return sjdjid;
	}
	
	public void setSjdjid(String sjdjid) {
		this.sjdjid = sjdjid;
	}
	
}