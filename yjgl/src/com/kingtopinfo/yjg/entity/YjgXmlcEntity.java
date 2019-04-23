package com.kingtopinfo.yjg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.YjgXmlcEntity
 * @Description YJG_XMLC表映射实体类
 * @author cyf
 * @date 2017-09-21 11:09:29
 * @version 1.0
 * @remark create by generator
 */
public class YjgXmlcEntity {
	private String sjdjid;  //事件ID
	private String xmlcid;  //主键id
	private String cjr;  //创建人
	private Integer state;  //状态
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date cjsj;  //创建时间
	private String instanceid;  //流程实例ID
	
	public String getSjdjid(){  
		return sjdjid;  
	}
	  
	public void setSjdjid(String sjdjid){  
		this.sjdjid = sjdjid;  
	}  
	public String getXmlcid(){  
		return xmlcid;  
	}
	  
	public void setXmlcid(String xmlcid){  
		this.xmlcid = xmlcid;  
	}  
	public String getCjr(){  
		return cjr;  
	}
	  
	public void setCjr(String cjr){  
		this.cjr = cjr;  
	}  
	public Integer getState(){  
		return state;  
	}
	  
	public void setState(Integer state){  
		this.state = state;  
	}  
	public java.util.Date getCjsj(){  
		return cjsj;  
	}
	  
	public void setCjsj(java.util.Date cjsj){  
		this.cjsj = cjsj;  
	}  
	public String getInstanceid(){  
		return instanceid;  
	}
	  
	public void setInstanceid(String instanceid){  
		this.instanceid = instanceid;  
	}  
	
}