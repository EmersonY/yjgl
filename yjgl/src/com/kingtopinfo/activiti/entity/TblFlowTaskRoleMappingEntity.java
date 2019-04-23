package com.kingtopinfo.activiti.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.TblFlowTaskRoleMappingEntity
 * @Description TBL_FLOW_TASK_ROLE_MAPPING表映射实体类
 * @author cyf
 * @date 2017-09-20 10:48:40
 * @version 1.0
 * @remark create by generator
 */
public class TblFlowTaskRoleMappingEntity {
	private String dxlx;  //对象类型 1-角色，2-用户
	private String roleid;  //角色ID
	private String userid;  //用户ID
	private String flowtaskroleid;  //流程角色配置ID
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date updatetime;  //更新时间
	private String processid;  //流程ID
	private String taskid;  //任务ID
	/**
	 * Vo
	 */
	private String pbaseuserid;// 父用户信息编号
	private String username;//用户名
	
	public String getPbaseuserid() {
		return pbaseuserid;
	}
	
	public void setPbaseuserid(String pbaseuserid) {
		this.pbaseuserid = pbaseuserid;
	}
	
	public String getDxlx(){  
		return dxlx;  
	}
	  
	public void setDxlx(String dxlx){  
		this.dxlx = dxlx;  
	}  
	public String getRoleid(){  
		return roleid;  
	}
	  
	public void setRoleid(String roleid){  
		this.roleid = roleid;  
	}  
	public String getUserid(){  
		return userid;  
	}
	  
	public void setUserid(String userid){  
		this.userid = userid;  
	}  
	public String getFlowtaskroleid(){  
		return flowtaskroleid;  
	}
	  
	public void setFlowtaskroleid(String flowtaskroleid){  
		this.flowtaskroleid = flowtaskroleid;  
	}  
	public java.util.Date getUpdatetime(){  
		return updatetime;  
	}
	  
	public void setUpdatetime(java.util.Date updatetime){  
		this.updatetime = updatetime;  
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}