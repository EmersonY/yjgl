package com.kingtopinfo.yjg.entity;

import java.util.List;

import com.kingtopinfo.base.entity.TblBaseEntity;
import com.kingtopinfo.echarts.Option;
import com.kingtopinfo.ywtj.vo.YjlxtjVo;

public class YjgYwtjEntity extends TblBaseEntity {
	private Option option; //echarts统计
	
	private String type; //统计类型
	
	private String code; //数据字典
	
	private String roleid; //角色id
	
	private String pid; //父id
	
	private int cs; //次数
	
	private Double hour; //小时
	
	private String name; //名称
	
	private List<YjlxtjVo>			list;
	
	private List<YjgBaseSjdjEntity>	sjdjList;

	public List<YjgBaseSjdjEntity> getSjdjList() {
		return sjdjList;
	}
	
	public void setSjdjList(List<YjgBaseSjdjEntity> sjdjList) {
		this.sjdjList = sjdjList;
	}
	
	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public int getCs() {
		return cs;
	}

	public void setCs(int cs) {
		this.cs = cs;
	}

	public List<YjlxtjVo> getList() {
		return list;
	}
	
	public void setList(List<YjlxtjVo> list) {
		this.list = list;
	}
	
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Double getHour() {
		return hour;
	}

	public void setHour(Double hour) {
		this.hour = hour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
