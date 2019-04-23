package com.kingtopinfo.base.entity;

import java.io.Serializable;

/**
 * @ClassName entity.TblBaseRoleResourceMappingEntity
 * @Description TBL_BASE_ROLE_RESOURCE_MAPPING表映射实体类
 * @author cyf
 * @date 2017-07-28 10:54:04
 * @version 1.0
 * @remark create by generator
 */
public class TblBaseRoleResourceMappingEntity implements Serializable {
	private static final long	serialVersionUID	= 142741051674831888L;
	private String	baseresourceid;		// 资源ID
	private String	baseroleresourceid;	// 角色资源ID
	private String	baseroleid;			// 角色ID
	
	public String getBaseresourceid(){  
		return baseresourceid;  
	}
	  
	public void setBaseresourceid(String baseresourceid){  
		this.baseresourceid = baseresourceid;  
	}  
	public String getBaseroleresourceid(){  
		return baseroleresourceid;  
	}
	  
	public void setBaseroleresourceid(String baseroleresourceid){  
		this.baseroleresourceid = baseroleresourceid;  
	}  
	public String getBaseroleid(){  
		return baseroleid;  
	}
	  
	public void setBaseroleid(String baseroleid){  
		this.baseroleid = baseroleid;  
	}  
	
}