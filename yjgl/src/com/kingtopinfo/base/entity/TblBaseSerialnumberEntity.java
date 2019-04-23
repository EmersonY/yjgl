package com.kingtopinfo.base.entity;

import java.io.Serializable;

/**
 * @ClassName entity.TblBaseSerialnumberEntity
 * @Description TBL_BASE_SERIALNUMBER表映射实体类
 * @author cyf
 * @date 2017-09-11 11:28:17
 * @version 1.0
 * @remark create by generator
 */
public class TblBaseSerialnumberEntity implements Serializable {
	private static final long	serialVersionUID	= -384081698163910047L;
	private String type;  //序列化类型
	private String serialnumberid;  //序列化ID
	private String seq;  //序列化值
	private String datestr;  //日期
	
	public String getType(){  
		return type;  
	}
	  
	public void setType(String type){  
		this.type = type;  
	}  
	public String getSerialnumberid(){  
		return serialnumberid;  
	}
	  
	public void setSerialnumberid(String serialnumberid){  
		this.serialnumberid = serialnumberid;  
	}  
	public String getSeq(){  
		return seq;  
	}
	  
	public void setSeq(String seq){  
		this.seq = seq;  
	}  
	public String getDatestr(){  
		return datestr;  
	}
	  
	public void setDatestr(String datestr){  
		this.datestr = datestr;  
	}  
	
}