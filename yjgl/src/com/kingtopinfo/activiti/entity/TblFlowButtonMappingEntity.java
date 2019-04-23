package com.kingtopinfo.activiti.entity;

import java.io.Serializable;

/**
 * @ClassName entity.TblFlowButtonMappingEntity
 * @Description TBL_FLOW_BUTTON_MAPPING表映射实体类
 * @author cyf
 * @date 2017-09-19 15:07:18
 * @version 1.0
 * @remark create by generator
 */
public class TblFlowButtonMappingEntity implements Serializable {
	private static final long	serialVersionUID	= -1152216423320479371L;
	private String id;  //主键ID
	private String processid;  //流程ID
	private String taskid;  //任务ID
	private String buttonid;  //按钮ID
	
	public String getId(){  
		return id;  
	}
	  
	public void setId(String id){  
		this.id = id;  
	}  
	public String getProcessid(){  
		return processid;  
	}
	  
	public void setProcessid(String processid){  
		this.processid = processid;  
	}  
	public String getTaskid(){  
		return taskid;  
	}
	  
	public void setTaskid(String taskid){  
		this.taskid = taskid;  
	}  
	public String getButtonid(){  
		return buttonid;  
	}
	  
	public void setButtonid(String buttonid){  
		this.buttonid = buttonid;  
	}  
	
}