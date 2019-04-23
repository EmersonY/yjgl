package com.kingtopinfo.app.entity;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.YjgAppversionEntity
 * @Description YJG_APPVERSION表映射实体类
 * @author cyf
 * @date 2018-01-02 09:51:19
 * @version 1.0
 * @remark create by generator
 */
public class YjgAppversionEntity {
	private String updateperson;  //更新人
	private int versioncode;  //版本号
	private String id;  //编号
	private int sfqzgx;  //0不强制更新，1强制更新
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date updatetime;  //更新时间
	private String versionname;  //版本名称
	/**
	 * VO
	 */
	private MultipartFile	appfile;
	
	public MultipartFile getAppfile() {
		return appfile;
	}
	
	public void setAppfile(MultipartFile appfile) {
		this.appfile = appfile;
	}
	
	public String getUpdateperson(){  
		return updateperson;  
	}
	  
	public void setUpdateperson(String updateperson){  
		this.updateperson = updateperson;  
	}  
	public int getVersioncode(){  
		return versioncode;  
	}
	  
	public void setVersioncode(int versioncode){  
		this.versioncode = versioncode;  
	}  
	public String getId(){  
		return id;  
	}
	  
	public void setId(String id){  
		this.id = id;  
	}  
	public int getSfqzgx(){  
		return sfqzgx;  
	}
	  
	public void setSfqzgx(int sfqzgx){  
		this.sfqzgx = sfqzgx;  
	}  
	public java.util.Date getUpdatetime(){  
		return updatetime;  
	}
	  
	public void setUpdatetime(java.util.Date updatetime){  
		this.updatetime = updatetime;  
	}  
	public String getVersionname(){  
		return versionname;  
	}
	  
	public void setVersionname(String versionname){  
		this.versionname = versionname;  
	}  
	
}