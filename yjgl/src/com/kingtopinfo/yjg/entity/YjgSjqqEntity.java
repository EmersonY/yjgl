package com.kingtopinfo.yjg.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.YjgSjqqEntity
 * @Description YJG_SJQQ表映射实体类
 * @author cyf
 * @date 2017-09-07 11:06:59
 * @version 1.0
 * @remark create by generator
 */
public class YjgSjqqEntity extends YjgBaseSjdjEntity implements Serializable {
	private static final long	serialVersionUID	= 5428957847451389369L;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date		czsj;										// 操作时间
	private String				sjqsid;										// 事件权属编号
	private String				baseuserid;									// 操作人编号
	private String				qsmc;										// 权属名称
	private String				sjdjid;										// 事件登记编号
	private Integer				isdel;										// 删除标识 0：是 1：否
	private String				bz;											// 接件备注
	private String				czr;										// 操作人
	private String				qsid;										// 权属ID
	private String				wdbbz;										// 未达标备注
	private String				dbzt;										// 达标状态 0: 未确认 1:达标 2未达标
	private String				qssgid;										// 权属施工层编号
	private String				qssgmc;										// 权属施工层姓名
	
	/*
	 * VO
	 */
	private String				pbaseuserid;								// 用户父ID;
	
	public String getDbzt() {
		return dbzt;
	}
	
	public void setDbzt(String dbzt) {
		this.dbzt = dbzt;
	}
	
	public String getQssgid() {
		return qssgid;
	}
	
	public void setQssgid(String qssgid) {
		this.qssgid = qssgid;
	}
	
	public String getQssgmc() {
		return qssgmc;
	}
	
	public void setQssgmc(String qssgmc) {
		this.qssgmc = qssgmc;
	}
	
	public String getPbaseuserid() {
		return pbaseuserid;
	}
	
	public void setPbaseuserid(String pbaseuserid) {
		this.pbaseuserid = pbaseuserid;
	}
	
	public java.util.Date getCzsj() {
		return czsj;
	}
	
	public void setCzsj(java.util.Date czsj) {
		this.czsj = czsj;
	}
	
	public String getWdbbz() {
		return wdbbz;
	}
	
	public void setWdbbz(String wdbbz) {
		this.wdbbz = wdbbz;
	}
	
	public String getSjqsid() {
		return sjqsid;
	}
	
	public void setSjqsid(String sjqsid) {
		this.sjqsid = sjqsid;
	}
	
	public String getBaseuserid() {
		return baseuserid;
	}
	
	public void setBaseuserid(String baseuserid) {
		this.baseuserid = baseuserid;
	}
	
	public String getQsmc() {
		return qsmc;
	}
	
	public void setQsmc(String qsmc) {
		this.qsmc = qsmc;
	}
	
	public String getSjdjid() {
		return sjdjid;
	}
	
	public void setSjdjid(String sjdjid) {
		this.sjdjid = sjdjid;
	}
	
	public Integer getIsdel() {
		return isdel;
	}
	
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	public String getCzr() {
		return czr;
	}
	
	public void setCzr(String czr) {
		this.czr = czr;
	}
	
	public String getQsid() {
		return qsid;
	}
	
	public void setQsid(String qsid) {
		this.qsid = qsid;
	}
	
}