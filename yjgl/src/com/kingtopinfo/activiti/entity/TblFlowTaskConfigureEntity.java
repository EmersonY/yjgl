package com.kingtopinfo.activiti.entity;


/**
 * @ClassName entity.TblFlowTaskConfigureEntity
 * @Description TBL_FLOW_TASK_CONFIGURE表映射实体类
 * @author cyf
 * @date 2017-09-20 15:23:49
 * @version 1.0
 * @remark create by generator
 */
public class TblFlowTaskConfigureEntity {
	private String taskcode;  //任务代码，对应流程中审批意见表的意见类型字段
	private Long maxtimes;  //最大持续时间数（单位：秒）
	private String businessurl;  //业务url地址
	private String overtimehandle;  //超过最大持续时间数操作
	private String flowtaskconfigureid;  //任务基础配置ID
	private String processid;  //流程ID
	private String taskid;  //任务ID
	private String taskname;  //任务NAME
	
	// 传递参数
	
	private String	buttonid;				// 按钮id组
	private String	nodeid;					// 关联节点组
	
	public String getTaskcode(){  
		return taskcode;  
	}
	  
	public void setTaskcode(String taskcode){  
		this.taskcode = taskcode;  
	}  
	public Long getMaxtimes(){  
		return maxtimes;  
	}
	  
	public void setMaxtimes(Long maxtimes){  
		this.maxtimes = maxtimes;  
	}  
	public String getBusinessurl(){  
		return businessurl;  
	}
	  
	public void setBusinessurl(String businessurl){  
		this.businessurl = businessurl;  
	}  
	public String getOvertimehandle(){  
		return overtimehandle;  
	}
	  
	public void setOvertimehandle(String overtimehandle){  
		this.overtimehandle = overtimehandle;  
	}  
	public String getFlowtaskconfigureid(){  
		return flowtaskconfigureid;  
	}
	  
	public void setFlowtaskconfigureid(String flowtaskconfigureid){  
		this.flowtaskconfigureid = flowtaskconfigureid;  
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
	public String getTaskname(){  
		return taskname;  
	}
	  
	public void setTaskname(String taskname){  
		this.taskname = taskname;  
	}  
	
	public String getButtonid() {
		return buttonid;
	}
	
	public void setButtonid(String buttonid) {
		this.buttonid = buttonid;
	}
	
	public String getNodeid() {
		return nodeid;
	}
	
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	
}