package com.kingtopinfo.base.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kingtopinfo.base.util.FilePathUtil;

/**
 * @author cyf
 * @date 2017-08-18 14:04:49
 * @version 1.0
 * @remark create by generator
 */
public class TblBaseImageEntity  {

	  private String name;       //图片的名字
	  private String path;       //图片的路径
	  private long size;         //图片的大小
	  private int width;         //图片的宽度
	  private int height;        //图片的高度
	  private String mimeType;   //图片的类型
	  private String type;   //图片的类型
	public String getName() {
			return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	  
	  
	  
	
}