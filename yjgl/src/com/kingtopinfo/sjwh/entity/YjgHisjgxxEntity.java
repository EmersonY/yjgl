package com.kingtopinfo.sjwh.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingtopinfo.base.entity.TblBaseEntity;

/**
 * @ClassName entity.YjgHisjgxxEntity
 * @Description YJG_HISJGXX表映射实体类
 * @author cyf
 * @date 2017-10-19 16:19:36
 * @version 1.0
 * @remark create by generator
 */
public class YjgHisjgxxEntity extends TblBaseEntity implements Serializable{
	private static final long	serialVersionUID	= 982036156904804015L;
	private String				hisssdl;									// 历史所属道路
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date		hisczsj;									// 操作时间
	private String				hissfzw;									// 历史是否有防坠网
	private String				hisczr;										// 操作人
	private String				hisxzb;										// X坐标
	private String				hisjgid;									// 历史井盖ID
	private String				hisyzb;										// Y坐标
	private String				hisjgxz;									// 历史井盖形状
	private String				hisqsdw;									// 历史权属单位
	private String				hisjglx;									// 历史井盖类型
	private String				hisxzqh;									// 历史行政区划
	private String				hisdljssj;									// 历史道路建设时间
	private String				hisjgcz;									// 历史井盖材质
	private String				hisjggg;									// 历史井盖规格
	private String				hisjgbh;									// 历史井盖编号
	private String				hisjngj;									// 历史井内管径
	private String				hisgldw;									// 历史管理单位
	private String				hisjgzt;									// 历史井盖状态
	
	public String getHisssdl() {
		return hisssdl;
	}
	
	public void setHisssdl(String hisssdl) {
		this.hisssdl = hisssdl;
	}
	
	public java.util.Date getHisczsj() {
		return hisczsj;
	}
	
	public void setHisczsj(java.util.Date hisczsj) {
		this.hisczsj = hisczsj;
	}
	
	public String getHissfzw() {
		return hissfzw;
	}
	
	public void setHissfzw(String hissfzw) {
		this.hissfzw = hissfzw;
	}
	
	public String getHisczr() {
		return hisczr;
	}
	
	public void setHisczr(String hisczr) {
		this.hisczr = hisczr;
	}
	
	public String getHisxzb() {
		return hisxzb;
	}
	
	public void setHisxzb(String hisxzb) {
		this.hisxzb = hisxzb;
	}
	
	public String getHisjgid() {
		return hisjgid;
	}
	
	public void setHisjgid(String hisjgid) {
		this.hisjgid = hisjgid;
	}
	
	public String getHisyzb() {
		return hisyzb;
	}
	
	public void setHisyzb(String hisyzb) {
		this.hisyzb = hisyzb;
	}
	
	public String getHisjgxz() {
		return hisjgxz;
	}
	
	public void setHisjgxz(String hisjgxz) {
		this.hisjgxz = hisjgxz;
	}
	
	public String getHisqsdw() {
		return hisqsdw;
	}
	
	public void setHisqsdw(String hisqsdw) {
		this.hisqsdw = hisqsdw;
	}
	
	public String getHisjglx() {
		return hisjglx;
	}
	
	public void setHisjglx(String hisjglx) {
		this.hisjglx = hisjglx;
	}
	
	public String getHisxzqh() {
		return hisxzqh;
	}
	
	public void setHisxzqh(String hisxzqh) {
		this.hisxzqh = hisxzqh;
	}
	
	public String getHisdljssj() {
		return hisdljssj;
	}
	
	public void setHisdljssj(String hisdljssj) {
		this.hisdljssj = hisdljssj;
	}
	
	public String getHisjgcz() {
		return hisjgcz;
	}
	
	public void setHisjgcz(String hisjgcz) {
		this.hisjgcz = hisjgcz;
	}
	
	public String getHisjggg() {
		return hisjggg;
	}
	
	public void setHisjggg(String hisjggg) {
		this.hisjggg = hisjggg;
	}
	
	public String getHisjgbh() {
		return hisjgbh;
	}
	
	public void setHisjgbh(String hisjgbh) {
		this.hisjgbh = hisjgbh;
	}
	
	public String getHisjngj() {
		return hisjngj;
	}
	
	public void setHisjngj(String hisjngj) {
		this.hisjngj = hisjngj;
	}
	
	public String getHisgldw() {
		return hisgldw;
	}
	
	public void setHisgldw(String hisgldw) {
		this.hisgldw = hisgldw;
	}
	
	public String getHisjgzt() {
		return hisjgzt;
	}
	
	public void setHisjgzt(String hisjgzt) {
		this.hisjgzt = hisjgzt;
	}
	
}