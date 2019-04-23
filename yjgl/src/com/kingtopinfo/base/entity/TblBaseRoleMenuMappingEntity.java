package com.kingtopinfo.base.entity;

import java.io.Serializable;

/**
 * @ClassName com.kingtopinfo.base.entity.TblBaseRoleMenuMappingEntity
 * @Description TBL_BASE_ROLE_MENU_MAPPING表映射实体类
 * @author cyf
 * @date 2017-06-05 14:20:01
 * @version 1.0
 * @remark create by generator
 */

public class TblBaseRoleMenuMappingEntity implements Serializable {
	
	private static final long	serialVersionUID	= 3469049890250306573L;
	private String baserolemenumid;  //编号
	private String basemenuid;  //菜单编号
	private String baseroleid;  //角色编号
	private java.util.Date updatetime;  //更新时间
	private String updateuserid;  //更新人
	
	public String getBaserolemenumid(){  
		return baserolemenumid;  
	}
	  
	public void setBaserolemenumid(String baserolemenumid){  
		this.baserolemenumid = baserolemenumid;  
	}  
	public String getBasemenuid(){  
		return basemenuid;  
	}
	  
	public void setBasemenuid(String basemenuid){  
		this.basemenuid = basemenuid;  
	}  
	
	public String getBaseroleid(){  
		return baseroleid;  
	}
	  
	public void setBaseroleid(String baseroleid){  
		this.baseroleid = baseroleid;  
	}  
	public java.util.Date getUpdatetime(){  
		return updatetime;  
	}
	  
	public void setUpdatetime(java.util.Date updatetime){  
		this.updatetime = updatetime;  
	}  
	public String getUpdateuserid(){  
		return updateuserid;  
	}
	  
	public void setUpdateuserid(String updateuserid){  
		this.updateuserid = updateuserid;  
	}  
	
}