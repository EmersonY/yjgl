package com.kingtopinfo.yjg.entity;

import java.io.Serializable;
import java.util.List;

import com.kingtopinfo.base.entity.TblBaseImageEntity;
	
	/**
 * @ClassName entity.YjgSjdjEntity
 * @Description YJG_SJDJ表映射实体类
 * @author cyf
 * @date 2017-08-24 10:56:58
 * @version 1.0
 * @remark create by generator
 */
public class YjgSjdjEntity extends YjgBaseSjdjEntity implements Serializable {
	private static final long			serialVersionUID	= 3542267052335795357L;
	private String						sjdjid;										// 事件登记编号
	
	/**
	 * VO
	 */
	private String				fyjsjdjid;									// 非事件登记编号
	private String				sjczid;										// 进度ID
	private String				userName;	
	
	//app固有
	private int				itemType;	
	private List<TblBaseImageEntity> xckctp;
	private String remark;
	private String  operatype;
	private String  xckcsp;
	private String  dbzt;
	private String  wdbbz;
	
	
	
	public String getWdbbz() {
		return wdbbz;
	}

	public void setWdbbz(String wdbbz) {
		this.wdbbz = wdbbz;
	}

	public String getDbzt() {
		return dbzt;
	}

	public void setDbzt(String dbzt) {
		this.dbzt = dbzt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSjdjid() {
		return sjdjid;
	}
	
	public void setSjdjid(String sjdjid) {
		this.sjdjid = sjdjid;
	}
	
	public String getFyjsjdjid() {
		return fyjsjdjid;
	}
	
	public String getSjczid() {
		return sjczid;
	}
	
	public void setSjczid(String sjczid) {
		this.sjczid = sjczid;
	}
	
	public void setFyjsjdjid(String fyjsjdjid) {
		this.fyjsjdjid = fyjsjdjid;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public List<TblBaseImageEntity> getXckctp() {
		return xckctp;
	}

	public void setXckctp(List<TblBaseImageEntity> xckctp) {
		this.xckctp = xckctp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public String getXckcsp() {
		return xckcsp;
	}

	public void setXckcsp(String xckcsp) {
		this.xckcsp = xckcsp;
	}
	
}