package com.kingtopinfo.yjg.entity;
	
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingtopinfo.activiti.entity.ActTaskEntity;
import com.kingtopinfo.base.action.TblBaseTypeAction;
import com.kingtopinfo.base.entity.TblBaseTypeEntity;
	
/**
 * @ClassName entity.YjgSjdjEntity
 * @Description YJG_SJDJ表映射实体类
 * @author cyf
 * @date 2017-08-24 10:56:58
 * @version 1.0
 * @remark create by generator
 */
public class YjgAppTypeEntity  implements Serializable {
	private static final long	serialVersionUID	= 3542267052335795357L;
	private List<String>  namelist;
	
	private List<String> valuelist;
	private List<String> jglxlist;
	private List<String> jgczlist;
	private List<String> jggglist;
	private List<String> jgztlist;
	private List<String> jgxzlist;
	private List<String> ssdllist;
	private List<String> yzjblist;
	private List<String> sfzwlist;
	private List<String> jngjlist;
	private List<String> qsjslxlist;
	
	
	public List<String> getQsjslxlist() {
		return qsjslxlist;
	}
	public void setQsjslxlist(List<String> qsjslxlist) {
		this.qsjslxlist = qsjslxlist;
	}
	public List<String> getJngjlist() {
		return jngjlist;
	}
	public void setJngjlist(List<String> jngjlist) {
		this.jngjlist = jngjlist;
	}
	public List<String> getSfzwlist() {
		return sfzwlist;
	}
	public void setSfzwlist(List<String> sfzwlist) {
		this.sfzwlist = sfzwlist;
	}
	public List<String> getYzjblist() {
		return yzjblist;
	}
	public void setYzjblist(List<String> yzjblist) {
		this.yzjblist = yzjblist;
	}
	public List<String> getNamelist() {
		return namelist;
	}
	public void setNamelist(List<String> namelist) {
		this.namelist = namelist;
	}
	public List<String> getValuelist() {
		return valuelist;
	}
	public void setValuelist(List<String> valuelist) {
		this.valuelist = valuelist;
	}
	public List<String> getJglxlist() {
		return jglxlist;
	}
	public void setJglxlist(List<String> jglxlist) {
		this.jglxlist = jglxlist;
	}
	public List<String> getJgczlist() {
		return jgczlist;
	}
	public void setJgczlist(List<String> jgczlist) {
		this.jgczlist = jgczlist;
	}
	public List<String> getJggglist() {
		return jggglist;
	}
	public void setJggglist(List<String> jggglist) {
		this.jggglist = jggglist;
	}
	public List<String> getJgztlist() {
		return jgztlist;
	}
	public void setJgztlist(List<String> jgztlist) {
		this.jgztlist = jgztlist;
	}
	public List<String> getJgxzlist() {
		return jgxzlist;
	}
	public void setJgxzlist(List<String> jgxzlist) {
		this.jgxzlist = jgxzlist;
	}
	public List<String> getSsdllist() {
		return ssdllist;
	}
	public void setSsdllist(List<String> ssdllist) {
		this.ssdllist = ssdllist;
	}
	
	
	

}