package com.kingtopinfo.activiti.entity;

import java.io.Serializable;

public class TblFlowChartEntity implements Serializable {

	private static final long	serialVersionUID	= -6158336313435742181L;
	private String id;
	private String name;
	private double sx;
	private double sy;
	private double ex;
	private double ey;
	private double w;
	private double h;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSx() {
		return sx;
	}
	public void setSx(double sx) {
		this.sx = sx;
	}
	public double getSy() {
		return sy;
	}
	public void setSy(double sy) {
		this.sy = sy;
	}
	public double getEx() {
		return ex;
	}
	public void setEx(double ex) {
		this.ex = ex;
	}
	public double getEy() {
		return ey;
	}
	public void setEy(double ey) {
		this.ey = ey;
	}
	public double getW() {
		return w;
	}
	public void setW(double w) {
		this.w = w;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
}

