package com.kingtopinfo.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName com.kingtopinfo.entity.TBaseTypeEntity
 * @Description T_BASE_TYPE表映射实体类
 * @author dzb@kingtopinfo.com
 * @date 2013-11-19 15:45:37
 * @version 1.0
 * @remark create by generator
 */

public class TblBaseTypeEntity implements Serializable {
	
	private static final long	serialVersionUID	= -4366215632578739967L;
	private String basetypeid;	//数据字典编号
	private String name;	//名称
	private String code;	//代码
	private String value;	//值
	private String type; //数据字典类型
	private String basetypepid;	 //数据字典父编号（用于树状数据字典）
	private Integer sequ;	//序号（默认顺序）
	private Integer state;	//数据字典状态（1：有效 0：无效）
	private Integer ver;	//版本
	private String updateuserid;	// 更新人
	private Date	updatetime;									// 更新时间
	private String attribute; // 属性
	private String codeattrbute;//代码属性
	private String module;  //所属模块
	
	/**
	 * VO
	 */
	private String			id;
	private String			pid;
	private String			stateStr;
	
	public String getCodeattrbute() {
		return codeattrbute;
	}
	public void setCodeattrbute(String codeattrbute) {
		this.codeattrbute = codeattrbute;
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
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public String getBasetypeid() {
		return basetypeid;
	}
	public void setBasetypeid(String basetypeid) {
		this.basetypeid = basetypeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBasetypepid() {
		return basetypepid;
	}
	public void setBasetypepid(String basetypepid) {
		this.basetypepid = basetypepid;
	}
	public Integer getSequ() {
		return sequ;
	}
	public void setSequ(Integer sequ) {
		this.sequ = sequ;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	public String getUpdateuserid() {
		return updateuserid;
	}
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	
}