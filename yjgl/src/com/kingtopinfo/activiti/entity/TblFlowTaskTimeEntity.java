package com.kingtopinfo.activiti.entity;

import java.io.Serializable;

/**
 * @ClassName entity.TblFlowTaskTimeEntity
 * @Description TBL_FLOW_TASK_TIME表映射实体类
 * @author cyf
 * @date 2017-09-19 14:51:08
 * @version 1.0
 * @remark create by generator
 */
public class TblFlowTaskTimeEntity implements Serializable {
	private static final long	serialVersionUID	= -8688980219508848087L;
	private String processid;  //流程id
	private Integer time;  //总时长
	private String id;  //
	
	public String getProcessid(){  
		return processid;  
	}
	  
	public void setProcessid(String processid){  
		this.processid = processid;  
	}  
	public Integer getTime(){  
		return time;  
	}
	  
	public void setTime(Integer time){  
		this.time = time;  
	}  
	public String getId(){  
		return id;  
	}
	  
	public void setId(String id){  
		this.id = id;  
	}  
	
}