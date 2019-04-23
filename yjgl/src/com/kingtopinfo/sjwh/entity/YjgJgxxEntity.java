package com.kingtopinfo.sjwh.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingtopinfo.activiti.entity.ActTaskEntity;

/**
 * @ClassName entity.YjgJgxxEntity
 * @Description YJG_JGXX表映射实体类
 * @author cyf
 * @date 2017-10-19 16:18:13
 * @version 1.0
 * @remark create by generator
 */
public class YjgJgxxEntity extends ActTaskEntity implements Serializable {
	private static final long	serialVersionUID	= -1360665924820783883L;
	private String				jgcz;										// 井盖材质
	private String				jgid;										// 井盖ID
	private String				gldw;										// 管理单位
	private String				jgbh;										// 井盖编号
	private String				jgzt;										// 井盖状态
	private String				jggg;										// 井盖规格
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date		czsj;										// 操作时间
	private String				qsdw;										// 权属单位
	private String				jglx;										// 井盖类型
	private String				yzb;										// Y坐标
	private String				xzb;										// X坐标
	private String				ssdl;										// 所属道路
	private String				jgxz;										// 井盖形状
	private String				xzqh;										// 行政区划
	private String				sfzw;										// 是否有防坠网
	private String				czr;										// 操作人
	private String				jngj;										// 井内管径
	private String				rtzt;										// 是否入图0:否1:是
	private String				dljssj;										// 道路建设时间
	private String				js;											// 井深
	private String				jgsl;										// 井盖数量
	
	/** 临时VO属性 **/
	
	private MultipartFile		nbImageFile;								// 内部
	private MultipartFile		jjImageFile;								// 近景
	private MultipartFile		yjImageFile;								// 远景
	
	public String getJs() {
		return js;
	}
	
	public void setJs(String js) {
		this.js = js;
	}
	
	public String getJgsl() {
		return jgsl;
	}
	
	public void setJgsl(String jgsl) {
		this.jgsl = jgsl;
	}
	public MultipartFile getNbImageFile() {
		return nbImageFile;
	}
	
	public void setNbImageFile(MultipartFile nbImageFile) {
		this.nbImageFile = nbImageFile;
	}
	
	public MultipartFile getJjImageFile() {
		return jjImageFile;
	}
	
	public void setJjImageFile(MultipartFile jjImageFile) {
		this.jjImageFile = jjImageFile;
	}
	
	public MultipartFile getYjImageFile() {
		return yjImageFile;
	}
	
	public void setYjImageFile(MultipartFile yjImageFile) {
		this.yjImageFile = yjImageFile;
	}
	
	public String getJgcz() {
		return jgcz;
	}
	
	public void setJgcz(String jgcz) {
		this.jgcz = jgcz;
	}
	
	public String getJgid() {
		return jgid;
	}
	
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	
	public String getGldw() {
		return gldw;
	}
	
	public void setGldw(String gldw) {
		this.gldw = gldw;
	}
	
	public String getJgbh() {
		return jgbh;
	}
	
	public void setJgbh(String jgbh) {
		this.jgbh = jgbh;
	}
	
	public String getJgzt() {
		return jgzt;
	}
	
	public void setJgzt(String jgzt) {
		this.jgzt = jgzt;
	}
	
	public String getJggg() {
		return jggg;
	}
	
	public void setJggg(String jggg) {
		this.jggg = jggg;
	}
	
	public String getQsdw() {
		return qsdw;
	}
	
	public void setQsdw(String qsdw) {
		this.qsdw = qsdw;
	}
	
	public String getJglx() {
		return jglx;
	}
	
	public void setJglx(String jglx) {
		this.jglx = jglx;
	}
	
	public String getYzb() {
		return yzb;
	}
	
	public void setYzb(String yzb) {
		this.yzb = yzb;
	}
	
	public String getXzb() {
		return xzb;
	}
	
	public void setXzb(String xzb) {
		this.xzb = xzb;
	}
	
	public String getSsdl() {
		return ssdl;
	}
	
	public void setSsdl(String ssdl) {
		this.ssdl = ssdl;
	}
	
	public String getJgxz() {
		return jgxz;
	}
	
	public void setJgxz(String jgxz) {
		this.jgxz = jgxz;
	}
	
	public String getXzqh() {
		return xzqh;
	}
	
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	
	public String getSfzw() {
		return sfzw;
	}
	
	public void setSfzw(String sfzw) {
		this.sfzw = sfzw;
	}
	
	public String getCzr() {
		return czr;
	}
	
	public void setCzr(String czr) {
		this.czr = czr;
	}
	
	public String getJngj() {
		return jngj;
	}
	
	public void setJngj(String jngj) {
		this.jngj = jngj;
	}
	
	public String getRtzt() {
		return rtzt;
	}
	
	public void setRtzt(String rtzt) {
		this.rtzt = rtzt;
	}
	
	public String getDljssj() {
		return dljssj;
	}
	
	public void setDljssj(String dljssj) {
		this.dljssj = dljssj;
	}
	
	public java.util.Date getCzsj() {
		return czsj;
	}
	
	public void setCzsj(java.util.Date czsj) {
		this.czsj = czsj;
	}
	
}