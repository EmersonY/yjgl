package com.kingtopinfo.yjg.entity;
	
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingtopinfo.sjwh.entity.YjgJgxxEntity;
	
/**
 * @ClassName entity.YjgSjdjEntity
 * @Description YJG_SJDJ表映射实体类
 * @author cyf
 * @date 2017-08-24 10:56:58
 * @version 1.0
 * @remark create by generator
 */
public class YjgBaseSjdjEntity extends YjgJgxxEntity implements Serializable {
	private static final long	serialVersionUID	= 3542267052335795357L;
	private Integer				sqzt;										// 处理状态 //0:未处理1:已派单，待确认管辖2:已认领，待确认权责3:无认领，待重新派单（兜底）4:待上报处置情况5:处置完成，待审核6:已兜底7:已解决
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date				jssj;										// 结束时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date				scsj;	
	private String				sqztname;
	private String				yzjb;										// 严重级别
	private String				jgid;										// 井盖ID
	private String				sbrxm;										// 上报人姓名
	private String				updateuserid;								// 更新人
	private String				baseuserid;									// 上报人编号
	private String				wzms;										// 位置描述
	private String				jglx;										// 井盖类型
	private Integer				sjlx;										// 事件类型 1:窨井事件 2:非窨井事件
	private String				yzb;										// Y坐标
	private String				xzb;										// X坐标
	private String				ssdl;										// 所属道路
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date				updatetime;									// 更新时间
	private String				cssjdjpid;									// 从属事件登记编号
	private String				xzqh;										// 行政区划
	private String				bz;											// 备注
	private Integer				isdel;										// 删除标识
	private String				sbrdh;										// 上报人电话
	private Integer				xxly;										// 信息来源
	private String				sjdjdh;										// 事件登记单号
	private String				updator;									// 更新人姓名
	private String				ckqx;										// 查看权限
	private String				xxlyname;	
	private String				isline;										// 是否线下 1:是 0:不是
	/**
	 * VO
	 */
	private String				fyjsjdjid;
	private String				count;										// 数量
	
	public String getIsline() {
		return isline;
	}
	
	public void setIsline(String isline) {
		this.isline = isline;
	}
	
	public String getCkqx() {
		return ckqx;
	}
	
	public void setCkqx(String ckqx) {
		this.ckqx = ckqx;
	}
	
	public String getXxlyname() {
		return xxlyname;
	}

	public void setXxlyname(String xxlyname) {
		this.xxlyname = xxlyname;
	}

	public String getFyjsjdjid() {
		return fyjsjdjid;
	}
	
	public String getSqztname() {
		return sqztname;
	}

	public void setSqztname(String sqztname) {
		this.sqztname = sqztname;
	}

	public void setFyjsjdjid(String fyjsjdjid) {
		this.fyjsjdjid = fyjsjdjid;
	}
	
	public Integer getSqzt() {
		return sqzt;
	}
	
	public void setSqzt(Integer sqzt) {
		this.sqzt = sqzt;
	}
	
	public String getCount() {
		return count;
	}
	
	public void setCount(String count) {
		this.count = count;
	}
	
	public String getYzjb() {
		return yzjb;
	}
	
	public void setYzjb(String yzjb) {
		this.yzjb = yzjb;
	}
	
	public String getJgid() {
		return jgid;
	}
	
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	
	public String getSbrxm() {
		return sbrxm;
	}
	
	public void setSbrxm(String sbrxm) {
		this.sbrxm = sbrxm;
	}
	
	public String getUpdateuserid() {
		return updateuserid;
	}
	
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	
	public String getBaseuserid() {
		return baseuserid;
	}
	
	public void setBaseuserid(String baseuserid) {
		this.baseuserid = baseuserid;
	}
	
	public String getWzms() {
		return wzms;
	}
	
	public void setWzms(String wzms) {
		this.wzms = wzms;
	}
	
	public String getJglx() {
		return jglx;
	}
	
	public void setJglx(String jglx) {
		this.jglx = jglx;
	}
	
	public Integer getSjlx() {
		return sjlx;
	}
	
	public void setSjlx(Integer sjlx) {
		this.sjlx = sjlx;
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
	
	public String getCssjdjpid() {
		return cssjdjpid;
	}
	
	public void setCssjdjpid(String cssjdjpid) {
		this.cssjdjpid = cssjdjpid;
	}
	
	public String getXzqh() {
		return xzqh;
	}
	
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
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
	
	public String getSbrdh() {
		return sbrdh;
	}
	
	public void setSbrdh(String sbrdh) {
		this.sbrdh = sbrdh;
	}
	
	public Integer getXxly() {
		return xxly;
	}
	
	public void setXxly(Integer xxly) {
		this.xxly = xxly;
	}
	
	public String getSjdjdh() {
		return sjdjdh;
	}
	
	public void setSjdjdh(String sjdjdh) {
		this.sjdjdh = sjdjdh;
	}
	
	public Date getJssj() {
		return jssj;
	}
	
	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}
	
	public Date getScsj() {
		return scsj;
	}
	
	public void setScsj(Date scsj) {
		this.scsj = scsj;
	}
	
	public Date getUpdatetime() {
		return updatetime;
	}
	
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getUpdator() {
		return updator;
	}
	
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	
}