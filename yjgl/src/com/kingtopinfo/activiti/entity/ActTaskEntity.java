package com.kingtopinfo.activiti.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingtopinfo.base.entity.TblBaseEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;

public class ActTaskEntity extends TblBaseEntity implements Serializable {
	private static final long	serialVersionUID	= -3170019574551965413L;
	
	/**
	 * 流程实例ID
	 */
	private String instanceid;
	
	/**
	 * 任务ID
	 */
	private String taskid;			
	
	/**
	 * 流程定义ID
	 */
	private String definitionid;
	
	/**
	 * 任务的定义的key （在流程图文件中定义的任务的id）
	 */
	private String taskkey;
	
	/**
	 * 任务执行ID
	 */
	private String executionid;
	
	/**
	 * 父任务ID
	 */
	private String parenttaskid;
	
	/**
	 * 任务名称（在流程图文件中定义的任务的name）
	 */
	private String taskname;
	
	/**
	 * 任务描述
	 */
	private String taskdescription;
	
	/**
	 * 任务的执行者/任务的执行者ID
	 */
	private String assignee;
	
	/**
	 * 任务的开始时间
	 */
	private Date taskstarttime;
	
	/**
	 * 任务的完成时间
	 */
	private Date taskendtime;
	
	/**
	 * 任务持续时间数（单位小时）
	 */
	private Long duration;
	
	/**
	 * 版本
	 */
	private String dataversion;
	
	/**
	 * 任务候选人
	 */
	List<TblBaseUserEntity>		candidates;
	
	/**
	 * 流程定义key
	 */
	private String definitionkey = "xmyjglxt";
	
	/**
	 * 流程节点类型
	 */
	private String acttype;
	
	/**
	 * 传递参数
	 */
	private Map<String, Object> variables = new HashMap<String, Object>();
	
	/**
	 * 任务集合
	 */
	private List<ActTaskEntity> taskEntitys;
	
	/**
	 * 意见
	 */
	private String opinion;
	
	/**
	 * 当前系统用户ID
	 */
	private String userid;
	
	/**
	 * 当前系统用户Name
	 */
	private String username;
	
	/**
	 * 超时时间数：单位（秒） >0表示超时 == 0 表示刚好 <0表示未超时
	 */
	private long timeout; //
	
	private long gotime;	//已走时间
	
	/**
	 * 任务预计最大持续时间数（单位：小时）
	 */
	private long maxtimes; //

	public String getInstanceid() {
		return instanceid;
	}

	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getDefinitionid() {
		return definitionid;
	}

	public void setDefinitionid(String definitionid) {
		this.definitionid = definitionid;
	}

	public String getTaskkey() {
		return taskkey;
	}

	public void setTaskkey(String taskkey) {
		this.taskkey = taskkey;
	}

	public String getExecutionid() {
		return executionid;
	}

	public void setExecutionid(String executionid) {
		this.executionid = executionid;
	}

	public String getParenttaskid() {
		return parenttaskid;
	}

	public void setParenttaskid(String parenttaskid) {
		this.parenttaskid = parenttaskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskdescription() {
		return taskdescription;
	}

	public void setTaskdescription(String taskdescription) {
		this.taskdescription = taskdescription;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getTaskstarttime() {
		return taskstarttime;
	}

	public void setTaskstarttime(Date taskstarttime) {
		this.taskstarttime = taskstarttime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getTaskendtime() {
		return taskendtime;
	}

	public void setTaskendtime(Date taskendtime) {
		this.taskendtime = taskendtime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getDataversion() {
		return dataversion;
	}

	public void setDataversion(String dataversion) {
		this.dataversion = dataversion;
	}

	public List<TblBaseUserEntity> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<TblBaseUserEntity> candidates) {
		this.candidates = candidates;
	}

	public String getDefinitionkey() {
		return definitionkey;
	}

	public void setDefinitionkey(String definitionkey) {
		this.definitionkey = definitionkey;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public List<ActTaskEntity> getTaskEntitys() {
		return taskEntitys;
	}

	public void setTaskEntitys(List<ActTaskEntity> taskEntitys) {
		this.taskEntitys = taskEntitys;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public long getMaxtimes() {
		return maxtimes;
	}

	public void setMaxtimes(long maxtimes) {
		this.maxtimes = maxtimes;
	}

	public long getGotime() {
		return gotime;
	}

	public void setGotime(long gotime) {
		this.gotime = gotime;
	}

	public String getActtype() {
		return acttype;
	}

	public void setActtype(String acttype) {
		this.acttype = acttype;
	}

	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}

