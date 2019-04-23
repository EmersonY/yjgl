package com.kingtopinfo.sjwh.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingtopinfo.base.entity.TblBaseEntity;
import com.kingtopinfo.base.entity.TblBaseImageEntity;

/**
 * @ClassName entity.YjgLsjgxxEntity
 * @Description YJG_LSJGXX表映射实体类
 * @author cyf
 * @date 2017-11-09 09:38:29
 * @version 1.0
 * @remark create by generator
 */
public class YjgLsjgxxEntity extends TblBaseEntity implements Serializable {
	private static final long	serialVersionUID	= -7833874193413115112L;
	private Integer				isdel;										// 删除状态
	private String				sbr;										// 上报人
	private String				lsqsdw;										// 临时权属单位
	private String				lsjgzt;										// 临时井盖状态
	private String				lsjgid;										// 临时井盖ID
	private String				lsjngj;										// 临时井内管径
	private String				lsjggg;										// 临时井盖规格
	private String				lsxzqh;										// 临时行政区划
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date		sbsj;										// 上报时间
	private String				lsyzb;										// Y坐标
	private String				shrxm;										// 审核人
	private String				lsjgcz;										// 临时井盖材质
	private String				shzt;										// 审核状态:0:未确认 1:审核通过 2:审核不通过
	private String				lsssdl;										// 临时所属道路
	private String				lsgldw;										// 临时管理单位
	private String				lsxzb;										// X坐标
	private String				lsdljssj;									// 临时道路建设时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date		shsj;										// 审核时间
	private String				lsjgbh;										// 临时井盖编号
	private String				lssfzw;										// 临时是否有防坠网
	private String				bglx;										// 变更类型 0:新增 1:更改 2:删除
	private String				lsjglx;										// 临时井盖类型
	private String				lsjgxz;										// 临时井盖形状
	private String				lsjgsl;										// 临时井盖数量
	private String				lsjs;										// 临时井盖深度

	private String				token;	
	private List<TblBaseImageEntity>	jgzp;	
	private String				operatype;	
	private List<TblBaseImageEntity>		jmzp;	
	private List<TblBaseImageEntity>			jnzp;	
	private List<TblBaseImageEntity>		jgzbzp;	
	
	
	
	public String getLsjgsl() {
		return lsjgsl;
	}
	
	public void setLsjgsl(String lsjgsl) {
		this.lsjgsl = lsjgsl;
	}
	
	public String getLsjs() {
		return lsjs;
	}
	
	public void setLsjs(String lsjs) {
		this.lsjs = lsjs;
	}
	
	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public List<TblBaseImageEntity> getJgzp() {
		return jgzp;
	}

	public void setJgzp(List<TblBaseImageEntity> jgzp) {
		this.jgzp = jgzp;
	}

	

	public List<TblBaseImageEntity> getJmzp() {
		return jmzp;
	}

	public void setJmzp(List<TblBaseImageEntity> jmzp) {
		this.jmzp = jmzp;
	}

	public List<TblBaseImageEntity> getJnzp() {
		return jnzp;
	}

	public void setJnzp(List<TblBaseImageEntity> jnzp) {
		this.jnzp = jnzp;
	}

	public List<TblBaseImageEntity> getJgzbzp() {
		return jgzbzp;
	}

	public void setJgzbzp(List<TblBaseImageEntity> jgzbzp) {
		this.jgzbzp = jgzbzp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getIsdel() {
		return isdel;
	}
	
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	
	public String getSbr() {
		return sbr;
	}
	
	public void setSbr(String sbr) {
		this.sbr = sbr;
	}
	
	public String getLsqsdw() {
		return lsqsdw;
	}
	
	public void setLsqsdw(String lsqsdw) {
		this.lsqsdw = lsqsdw;
	}
	
	public String getLsjgzt() {
		return lsjgzt;
	}
	
	public void setLsjgzt(String lsjgzt) {
		this.lsjgzt = lsjgzt;
	}
	
	public String getLsjgid() {
		return lsjgid;
	}
	
	public void setLsjgid(String lsjgid) {
		this.lsjgid = lsjgid;
	}
	
	public String getLsjngj() {
		return lsjngj;
	}
	
	public void setLsjngj(String lsjngj) {
		this.lsjngj = lsjngj;
	}
	
	public String getLsjggg() {
		return lsjggg;
	}
	
	public void setLsjggg(String lsjggg) {
		this.lsjggg = lsjggg;
	}
	
	public String getLsxzqh() {
		return lsxzqh;
	}
	
	public void setLsxzqh(String lsxzqh) {
		this.lsxzqh = lsxzqh;
	}
	
	public java.util.Date getSbsj() {
		return sbsj;
	}
	
	public void setSbsj(java.util.Date sbsj) {
		this.sbsj = sbsj;
	}
	
	public String getLsyzb() {
		return lsyzb;
	}
	
	public void setLsyzb(String lsyzb) {
		this.lsyzb = lsyzb;
	}
	
	public String getShrxm() {
		return shrxm;
	}
	
	public void setShrxm(String shrxm) {
		this.shrxm = shrxm;
	}
	
	public String getLsjgcz() {
		return lsjgcz;
	}
	
	public void setLsjgcz(String lsjgcz) {
		this.lsjgcz = lsjgcz;
	}
	
	public String getShzt() {
		return shzt;
	}
	
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	
	public String getLsssdl() {
		return lsssdl;
	}
	
	public void setLsssdl(String lsssdl) {
		this.lsssdl = lsssdl;
	}
	
	public String getLsgldw() {
		return lsgldw;
	}
	
	public void setLsgldw(String lsgldw) {
		this.lsgldw = lsgldw;
	}
	
	public String getLsxzb() {
		return lsxzb;
	}
	
	public void setLsxzb(String lsxzb) {
		this.lsxzb = lsxzb;
	}
	
	public String getLsdljssj() {
		return lsdljssj;
	}
	
	public void setLsdljssj(String lsdljssj) {
		this.lsdljssj = lsdljssj;
	}
	
	public java.util.Date getShsj() {
		return shsj;
	}
	
	public void setShsj(java.util.Date shsj) {
		this.shsj = shsj;
	}
	
	public String getLsjgbh() {
		return lsjgbh;
	}
	
	public void setLsjgbh(String lsjgbh) {
		this.lsjgbh = lsjgbh;
	}
	
	public String getLssfzw() {
		return lssfzw;
	}
	
	public void setLssfzw(String lssfzw) {
		this.lssfzw = lssfzw;
	}
	
	public String getBglx() {
		return bglx;
	}
	
	public void setBglx(String bglx) {
		this.bglx = bglx;
	}
	
	public String getLsjglx() {
		return lsjglx;
	}
	
	public void setLsjglx(String lsjglx) {
		this.lsjglx = lsjglx;
	}
	
	public String getLsjgxz() {
		return lsjgxz;
	}
	
	public void setLsjgxz(String lsjgxz) {
		this.lsjgxz = lsjgxz;
	}
	
}