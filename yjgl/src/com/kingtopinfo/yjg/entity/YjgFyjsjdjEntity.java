package com.kingtopinfo.yjg.entity;

import java.io.Serializable;
	
	/**
 * @ClassName entity.YjgFyjsjdjEntity
 * @Description YJG_FYJSJDJ表映射实体类
 * @author cyf
 * @date 2017-08-25 15:02:05
 * @version 1.0
 * @remark create by generator
 */
public class YjgFyjsjdjEntity extends YjgBaseSjdjEntity implements Serializable {
	
	private static final long	serialVersionUID	= 7825249584547534449L;
	private String				fyjsjdjid;									// 非窨井事件登记编号
	
	/**
	 * VO
	 */
	private String				sjdjid;										// 窨井事件登记编号
	
	public String getFyjsjdjid() {
		return fyjsjdjid;
	}
	
	public void setFyjsjdjid(String fyjsjdjid) {
		this.fyjsjdjid = fyjsjdjid;
	}
	
	public String getSjdjid() {
		return sjdjid;
	}
	
	public void setSjdjid(String sjdjid) {
		this.sjdjid = sjdjid;
	}
	
	}