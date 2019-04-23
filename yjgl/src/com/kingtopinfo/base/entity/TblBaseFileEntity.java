package com.kingtopinfo.base.entity;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.TblBaseFileEntity
 * @Description TBL_BASE_FILE表映射实体类
 * @author cyf
 * @date 2017-08-18 14:04:49
 * @version 1.0
 * @remark create by generator
 */
public class TblBaseFileEntity implements Serializable {
	private static final long	serialVersionUID	= -3736726758039931109L;
	private String operatype;  //业务类型
	private String remark;  //备注
	private String operaid;  //业务编号
	private String cjr;  //创建人
	private String filename;  //文件名称
	private String extend;  //扩展名
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private java.util.Date gxsj;  //更新时间
	private Long filesize;  //文件大小
	private String filepath;  //文件路径
	private String fileid;  //编号
	private Integer state;  //有效标识
	
	/** 临时VO属性 **/
	private int count; 
	private MultipartFile		imageFile;
	private MultipartFile		yjImageFile;
	private MultipartFile		vediofile;
	private MultipartFile		yjVediofile;
	private MultipartFile[]		imageFiles;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public MultipartFile[] getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(MultipartFile[] imageFiles) {
		this.imageFiles = imageFiles;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}
	
	public MultipartFile getVediofile() {
		return vediofile;
	}
	
	public void setVediofile(MultipartFile vediofile) {
		this.vediofile = vediofile;
	}
	
	public MultipartFile getYjVediofile() {
		return yjVediofile;
	}
	
	public void setYjVediofile(MultipartFile yjVediofile) {
		this.yjVediofile = yjVediofile;
	}
	
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	public MultipartFile getYjImageFile() {
		return yjImageFile;
	}
	
	public void setYjImageFile(MultipartFile yjImageFile) {
		this.yjImageFile = yjImageFile;
	}
	
	public String getOperatype(){  
		return operatype;  
	}
	  
	public void setOperatype(String operatype){  
		this.operatype = operatype;  
	}  
	public String getRemark(){  
		return remark;  
	}
	  
	public void setRemark(String remark){  
		this.remark = remark;  
	}  
	public String getOperaid(){  
		return operaid;  
	}
	  
	public void setOperaid(String operaid){  
		this.operaid = operaid;  
	}  
	public String getCjr(){  
		return cjr;  
	}
	  
	public void setCjr(String cjr){  
		this.cjr = cjr;  
	}  
	public String getFilename(){  
		return filename;  
	}
	  
	public void setFilename(String filename){  
		this.filename = filename;  
	}  
	public String getExtend(){  
		return extend;  
	}
	  
	public void setExtend(String extend){  
		this.extend = extend;  
	}  
	public java.util.Date getGxsj(){  
		return gxsj;  
	}
	  
	public void setGxsj(java.util.Date gxsj){  
		this.gxsj = gxsj;  
	}  
	public Long getFilesize(){  
		return filesize;  
	}
	  
	public void setFilesize(Long filesize){  
		this.filesize = filesize;  
	}  
	public String getFilepath(){  
		return filepath;  
	}
	  
	public void setFilepath(String filepath){  
		this.filepath = filepath;  
	}  
	public String getFileid(){  
		return fileid;  
	}
	  
	public void setFileid(String fileid){  
		this.fileid = fileid;  
	}  
	public Integer getState(){  
		return state;  
	}
	  
	public void setState(Integer state){  
		this.state = state;  
	}

}