package com.kingtopinfo.base.entity;

import java.io.Serializable;

/**
 * @ClassName entity.TblBaseResourceEntity
 * @Description TBL_BASE_RESOURCE表映射实体类
 * @author cyf
 * @date 2017-09-11 14:46:44
 * @version 1.0
 * @remark create by generator
 */
public class TblBaseResourceEntity implements Serializable {
	private static final long	serialVersionUID	= -7039158139387305019L;
	private String baseresourceid;  //资源ID
	private String updater;  //操作人
	private Integer status;  //有效状态
	private String remark;  //描述
	private String resourceno;  //资源编号
	private String menuname;  //菜单名称
	private String basemenuid;  //菜单ID
	private String name;  //资源名称
	
	public String getBaseresourceid(){  
		return baseresourceid;  
	}
	  
	public void setBaseresourceid(String baseresourceid){  
		this.baseresourceid = baseresourceid;  
	}  
	public String getUpdater(){  
		return updater;  
	}
	  
	public void setUpdater(String updater){  
		this.updater = updater;  
	}  
	public Integer getStatus(){  
		return status;  
	}
	  
	public void setStatus(Integer status){  
		this.status = status;  
	}  
	public String getRemark(){  
		return remark;  
	}
	  
	public void setRemark(String remark){  
		this.remark = remark;  
	}  
	public String getResourceno(){  
		return resourceno;  
	}
	  
	public void setResourceno(String resourceno){  
		this.resourceno = resourceno;  
	}  
	public String getMenuname(){  
		return menuname;  
	}
	  
	public void setMenuname(String menuname){  
		this.menuname = menuname;  
	}  
	public String getBasemenuid(){  
		return basemenuid;  
	}
	  
	public void setBasemenuid(String basemenuid){  
		this.basemenuid = basemenuid;  
	}  
	public String getName(){  
		return name;  
	}
	  
	public void setName(String name){  
		this.name = name;  
	}  
	
}