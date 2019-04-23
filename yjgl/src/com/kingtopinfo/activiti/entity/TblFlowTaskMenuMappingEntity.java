package com.kingtopinfo.activiti.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.TblFlowTaskMenuMappingEntity
 * @Description TBL_FLOW_TASK_MENU_MAPPING表映射实体类
 * @author cyf
 * @date 2017-09-20 09:39:45
 * @version 1.0
 * @remark create by generator
 */
public class TblFlowTaskMenuMappingEntity implements Serializable {
	private static final long	serialVersionUID	= -2524265354725711024L;
	private String taskid;  //任务id
	private String processid;  //流程id
	private String flowmenuid;  //菜单id
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date gxsj;  //更新时间
	private String flowtaskmenumappingid;  //id
	
	public String getTaskid(){  
		return taskid;  
	}
	  
	public void setTaskid(String taskid){  
		this.taskid = taskid;  
	}  
	public String getProcessid(){  
		return processid;  
	}
	  
	public void setProcessid(String processid){  
		this.processid = processid;  
	}  
	public String getFlowmenuid(){  
		return flowmenuid;  
	}
	  
	public void setFlowmenuid(String flowmenuid){  
		this.flowmenuid = flowmenuid;  
	}  
	public java.util.Date getGxsj(){  
		return gxsj;  
	}
	  
	public void setGxsj(java.util.Date gxsj){  
		this.gxsj = gxsj;  
	}  
	public String getFlowtaskmenumappingid(){  
		return flowtaskmenumappingid;  
	}
	  
	public void setFlowtaskmenumappingid(String flowtaskmenumappingid){  
		this.flowtaskmenumappingid = flowtaskmenumappingid;  
	}  
	
}