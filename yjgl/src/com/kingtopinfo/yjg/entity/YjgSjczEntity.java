package com.kingtopinfo.yjg.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingtopinfo.activiti.entity.ActTaskEntity;
import com.kingtopinfo.base.entity.TblBaseImageEntity;

/**
 * @ClassName entity.YjgSjczEntity
 * @Description YJG_SJCZ表映射实体类
 * @author cyf
 * @date 2017-09-26 10:50:50
 * @version 1.0
 * @remark create by generator
 */
public class YjgSjczEntity extends ActTaskEntity implements Serializable {
	private static final long			serialVersionUID	= -470410531028824487L;
	private String						sjczid;										// 事件处置编号
	private String						czrxm;										// 处置人姓名
	private String						czgcms;										// 处置过程描述
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date				czsj;										// 处置时间
	private String						czr;										// 操作人
	private String						bz;											// 备注
	private Integer						isdel;										// 删除标识
	private String						sjdjid;										// 事件登记编号
	private List<TblBaseImageEntity>	imagelist;
	private List<TblBaseImageEntity>	vediolist;
	private int							sqzt;
	private String						czsjStr;									// 处置时间
	private String						czzt;										// 处置状态 0:处置前 1：处置中2：已完成 3:未达标
	
	private String		dbzt;
	
	
	public String getDbzt() {
		return dbzt;
	}

	public void setDbzt(String dbzt) {
		this.dbzt = dbzt;
	}

	public String getSjczid() {
		return sjczid;
	}
	
	public void setSjczid(String sjczid) {
		this.sjczid = sjczid;
	}
	
	public String getCzrxm() {
		return czrxm;
	}
	
	public String getCzsjStr() {
		return czsjStr;
	}
	
	public void setCzsjStr(String czsjStr) {
		this.czsjStr = czsjStr;
	}
	
	public void setCzrxm(String czrxm) {
		this.czrxm = czrxm;
	}
	
	public String getCzgcms() {
		return czgcms;
	}
	
	public void setCzgcms(String czgcms) {
		this.czgcms = czgcms;
	}
	
	public java.util.Date getCzsj() {
		return czsj;
	}
	
	public void setCzsj(java.util.Date czsj) {
		this.czsj = czsj;
	}
	
	public String getCzr() {
		return czr;
	}
	
	public void setCzr(String czr) {
		this.czr = czr;
	}
	
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	public Integer getIsdel() {
		return isdel;
	}
	
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	
	public String getSjdjid() {
		return sjdjid;
	}
	
	public void setSjdjid(String sjdjid) {
		this.sjdjid = sjdjid;
	}
	
	public List<TblBaseImageEntity> getImagelist() {
		return imagelist;
	}
	
	public void setImagelist(List<TblBaseImageEntity> imagelist) {
		this.imagelist = imagelist;
	}
	
	public List<TblBaseImageEntity> getVediolist() {
		return vediolist;
	}
	
	public String getCzzt() {
		return czzt;
	}
	
	public void setVediolist(List<TblBaseImageEntity> vediolist) {
		this.vediolist = vediolist;
	}
	
	public void setCzzt(String czzt) {
		this.czzt = czzt;
	}
	
	public int getSqzt() {
		return sqzt;
	}
	
	public void setSqzt(int sqzt) {
		this.sqzt = sqzt;
	}
	
}