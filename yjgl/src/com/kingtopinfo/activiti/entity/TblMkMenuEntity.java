package com.kingtopinfo.activiti.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.TblMkMenuEntity
 * @Description TBL_MK_MENU表映射实体类
 * @author cyf
 * @date 2017-09-18 09:25:29
 * @version 1.0
 * @remark create by generator
 */
public class TblMkMenuEntity implements Serializable {
	private static final long	serialVersionUID	= -196233227565755876L;
	private String url;  //链接地址
	private String ywlxsx;  //业务类型缩写
	private String id;  //编号
	private Integer sequ;  //排列顺序
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date updatetime;  //更新时间
	private String name;  //名字
	private String type;  //类型标识
	private Integer state;  //有效标识
	private String pid;  //父编号
	
	public String getUrl(){  
		return url;  
	}
	  
	public void setUrl(String url){  
		this.url = url;  
	}  
	public String getYwlxsx(){  
		return ywlxsx;  
	}
	  
	public void setYwlxsx(String ywlxsx){  
		this.ywlxsx = ywlxsx;  
	}  
	public String getId(){  
		return id;  
	}
	  
	public void setId(String id){  
		this.id = id;  
	}  
	public Integer getSequ(){  
		return sequ;  
	}
	  
	public void setSequ(Integer sequ){  
		this.sequ = sequ;  
	}  
	public java.util.Date getUpdatetime(){  
		return updatetime;  
	}
	  
	public void setUpdatetime(java.util.Date updatetime){  
		this.updatetime = updatetime;  
	}  
	public String getName(){  
		return name;  
	}
	  
	public void setName(String name){  
		this.name = name;  
	}  
	public String getType(){  
		return type;  
	}
	  
	public void setType(String type){  
		this.type = type;  
	}  
	public Integer getState(){  
		return state;  
	}
	  
	public void setState(Integer state){  
		this.state = state;  
	}  
	public String getPid(){  
		return pid;  
	}
	  
	public void setPid(String pid){  
		this.pid = pid;  
	}  
	
}