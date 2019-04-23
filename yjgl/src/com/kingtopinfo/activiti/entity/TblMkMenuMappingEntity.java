package com.kingtopinfo.activiti.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.TblMkMenuMappingEntity
 * @Description TBL_MK_MENU_MAPPING表映射实体类
 * @author cyf
 * @date 2017-09-18 10:07:05
 * @version 1.0
 * @remark create by generator
 */
public class TblMkMenuMappingEntity implements Serializable {
	private static final long	serialVersionUID	= 7752127201757449251L;
	private String roleid;  //角色编号
	private String id;  //id编号
	private String menuid;  //菜单id
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date gxsj;  //更新时间
	
	public String getRoleid(){  
		return roleid;  
	}
	  
	public void setRoleid(String roleid){  
		this.roleid = roleid;  
	}  
	public String getId(){  
		return id;  
	}
	  
	public void setId(String id){  
		this.id = id;  
	}  
	public String getMenuid(){  
		return menuid;  
	}
	  
	public void setMenuid(String menuid){  
		this.menuid = menuid;  
	}  
	public java.util.Date getGxsj(){  
		return gxsj;  
	}
	  
	public void setGxsj(java.util.Date gxsj){  
		this.gxsj = gxsj;  
	}  
	
}